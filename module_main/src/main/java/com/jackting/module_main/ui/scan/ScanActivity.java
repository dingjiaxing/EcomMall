package com.jackting.module_main.ui.scan;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.client.android.BeepManager;
import com.jackting.common.base.BaseActivity;
import com.jackting.lib_router.module.main.MainIntent;
import com.jackting.lib_router.provider.IMainProvider;
import com.jackting.module_main.R;
import com.jackting.module_main.di.DaggerMainComponent;
import com.jackting.module_main.widget.zxing.camera.CameraManager;
import com.jackting.module_main.widget.zxing.decoding.CaptureActivityHandler;
import com.jackting.module_main.widget.zxing.decoding.InactivityTimer;
import com.jackting.module_main.widget.zxing.view.ViewfinderView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.IOException;
import java.util.Vector;

@Route(path = IMainProvider.SCAN_ACTIVITY)
public class ScanActivity extends BaseActivity<ScanPresenter> implements ScanContract.View,SurfaceHolder.Callback {


    @Override
    public int getContentViewResId() {
        return R.layout.main_fragment_scan;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        requestPermissions();


        CameraManager.init(getApplication());
        viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
        hasSurface = false;
        inactivityTimer = new InactivityTimer(this);// activity静止一段时间会自动关闭

        findViewById(R.id.rl_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScanActivity.this.finish();
            }
        });

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void loginSuccess() {
        Log.i(TAG,"loginSuccess");
        MainIntent.startMainActivity();
        finish();
    }

    @Override
    public void daggerInit() {
        DaggerMainComponent.builder()
                .appComponent(getAppComponent())
                .build()
                .inject(this);
    }

    private CaptureActivityHandler handler;
    private ViewfinderView viewfinderView;
    private boolean hasSurface;// surface有没有被绘制
    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    private InactivityTimer inactivityTimer;
    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private static final float BEEP_VOLUME = 0.10f;
    private boolean vibrate;// 完成扫描时是否震动提示
    private RelativeLayout rl_back;
//    private UserModel userModel;
    private String machine;


    @Override
    protected void onResume() {
        super.onResume();
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if (hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        decodeFormats = null;
        characterSet = null;

        playBeep = false;
        AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        initBeepSound();
        vibrate = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        super.onDestroy();
    }

    // 初始化照相机
    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
        } catch (Exception ioe) {
            if (handler != null) {
                handler.quitSynchronously();
                handler = null;
            }
            CameraManager.get().closeDriver();
            MaterialDialog materialDialogs = new MaterialDialog.Builder(ScanActivity.this)
                    .content("没有相机权限")
                    .positiveText("确定")
                    .canceledOnTouchOutside(false)
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick( MaterialDialog dialog,  DialogAction which) {
                            dialog.dismiss();
                            ScanActivity.this.finish();

                        }
                    })
                    .show();
            ioe.printStackTrace();

        }
        if (handler == null) {
            handler = new CaptureActivityHandler(this, decodeFormats,
                    characterSet);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;

    }

    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return handler;
    }

    public void drawViewfinder() {
        viewfinderView.drawViewfinder();

    }

    // 扫描结果数据
    public void handleDecode(Result obj, Bitmap barcode) {
        inactivityTimer.onActivity();
        // viewfinderView.drawResultBitmap(barcode);// 画结果图片
        if (obj.getText().trim().contains("http://weixin.qq.com/q")) {
            //扫描售货机二维码
            playBeepSoundAndVibrate();// 启动声音效果


        }

    }

    // 声音控制
    private void initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(beepListener);

            AssetFileDescriptor file = getResources().openRawResourceFd(
                    R.raw.beep);
            try {
                mediaPlayer.setDataSource(file.getFileDescriptor(),
                        file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mediaPlayer.prepare();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        }
    }

    private static final long VIBRATE_DURATION = 200L;

    // 启动声音功能
    private void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }

    /**
     * When the beep has finished playing, rewind to queue up another one.
     */
    private final MediaPlayer.OnCompletionListener beepListener = new MediaPlayer.OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        if (hasFocus) {

        }
    }

    private void requestPermissions() {
        //点击button之后RxPermissions会为我们申请运行时权限

        RxPermissions rxPermission = new RxPermissions(ScanActivity.this);
//        rxPermission.request(Manifest.permission.CAMERA)
//                .subscribe(new Action1<Boolean>() {
//                    @Override
//                    public void call(Boolean aBoolean) {
//                        Log.i("ScanActivity", "result:" + aBoolean);
//                        if (aBoolean) {
//
//                        } else {
//                            finish();
//                        }
//                    }
//
//                });
        rxPermission
                .request(Manifest.permission.CAMERA)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        // I can control the camera now
                    } else {
                        // Oups permission denied
                        finish();
                    }
                });

    }


    public void dialog() {
        MaterialDialog materialDialog = new MaterialDialog.Builder(ScanActivity.this)
                .content("请扫描二维码")
                .positiveText("确定")
                .canceledOnTouchOutside(false)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick( MaterialDialog dialog,  DialogAction which) {
                        dialog.dismiss();
                        ScanActivity.this.finish();
                    }
                })
                .show();
    }
}
