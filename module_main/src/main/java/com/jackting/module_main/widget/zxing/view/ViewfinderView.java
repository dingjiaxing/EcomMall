/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jackting.module_main.widget.zxing.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.google.zxing.ResultPoint;
import com.jackting.module_main.R;

import java.util.Collection;
import java.util.HashSet;
import com.jackting.module_main.widget.zxing.camera.CameraManager;


/**
 * This view is overlaid on top of the camera preview. It adds the viewfinder
 * rectangle and partial transparency outside it, as well as the laser scanner
 * animation and result points.
 * 
 * @author dswitkin@google.com (Daniel Switkin)
 */
public final class ViewfinderView extends View {
	/** 扫描页面透明度 */
	private static final int[] SCANNER_ALPHA = { 0, 64, 128, 192, 255, 192,
			128, 64 };
	/** 动画延迟 */
	private static final long ANIMATION_DELAY = 10L;
	private static final int OPAQUE = 0xFF;// 不透明

	/**
	 * 四个蓝色边角对应的长度
	 */
	private int ScreenRate;

	/**
	 * 四个边角对应的宽度
	 */
	private static final int CORNER_WIDTH = 5;
	/**
	 * 扫描框中的中间线的宽度
	 */
	private static final int MIDDLE_LINE_WIDTH = 6;

	/**
	 * 扫描框中的中间线的与扫描框左右的间隙
	 */
	private static final int MIDDLE_LINE_PADDING = 100;

	/**
	 * 中间那条线每次刷新移动的距离
	 */
	private static final int SPEEN_DISTANCE = 5;

	/**
	 * 手机的屏幕密度
	 */
	private static float density;
	/**
	 * 字体大小
	 */
	private static final int TEXT_SIZE = 16;
	/**
	 * 字体距离扫描框下面的距离
	 */
	private static final int TEXT_PADDING_TOP = 80;

	private final Paint paint;
	/** 返回的照片 */
	private Bitmap resultBitmap;
	/** 遮盖物颜色 */
	private final int maskColor;
	/** 结果颜色 */
	private final int resultColor;
	/** 框架颜色 */
	private final int frameColor;
	/** 扫描线颜色 */
	private final int laserColor;
	/** 结果点的颜色 */
	private final int resultPointColor;
	private int scannerAlpha;// 扫描透明度
	/** 可能的结果点数 */
	private Collection<ResultPoint> possibleResultPoints;
	/** 最后的结果点数 */
	private Collection<ResultPoint> lastPossibleResultPoints;

	/**
	 * 中间滑动线的最顶端位置
	 */
	private int slideTop;

	/**
	 * 中间滑动线的最底端位置
	 */
	private int slideBottom;
	private boolean isFirst;
	
	// 10.10修改,扫描框下面的字宽
	int tvWidth = 400;
	// 扫描框四周的图片的宽高
	int w,h = 72;

	// This constructor is used when the class is built from an XML resource.
	public ViewfinderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		density = context.getResources().getDisplayMetrics().density;
		// 将像素转化成dp
		ScreenRate = (int) (25 * density);

		// Initialize these once for performance rather than calling them every
		// time in onDraw().
		paint = new Paint();
		Resources resources = getResources();
		maskColor = resources.getColor(R.color.main_viewfinder_mask);
		resultColor = resources.getColor(R.color.main_backgroud);
        frameColor = resources.getColor(R.color.main_viewfinder_laser);

		laserColor = resources.getColor(R.color.main_viewfinder_laser);
		resultPointColor = resources.getColor(R.color.main_possible_result_points);
		scannerAlpha = 0;
		possibleResultPoints = new HashSet<ResultPoint>(5);

	}

	@Override
	public void onDraw(Canvas canvas) {
		Rect frame = CameraManager.get().getFramingRect();
		if (frame == null) {
			return;
		}

		if (!isFirst) {
			isFirst = true;
			slideTop = frame.top + CORNER_WIDTH;
			slideBottom = frame.bottom - CORNER_WIDTH;
		}

		int width = canvas.getWidth();
		int height = canvas.getHeight();

		// 画区域
		paint.setColor(resultBitmap != null ? resultColor : maskColor);
		canvas.drawRect(0, 0, width, frame.top, paint);
		canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, paint);
		canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1,
				paint);
		canvas.drawRect(0, frame.bottom + 1, width, height, paint);

		if (resultBitmap != null) {
			// Draw the opaque result bitmap over the scanning rectangle
			paint.setAlpha(OPAQUE);
			canvas.drawBitmap(resultBitmap, frame.left, frame.top, paint);
		} else {

			// Draw a two pixel solid black border inside the framing rect
			// 画框架
			paint.setColor(frameColor);

			// 扫描框边上的角，共8个部分
			canvas.drawRect(frame.left - CORNER_WIDTH / 2, frame.top
					- CORNER_WIDTH / 2, frame.left + ScreenRate, frame.top
					+ CORNER_WIDTH / 2, paint);
			canvas.drawRect(frame.left - CORNER_WIDTH / 2, frame.top
					- CORNER_WIDTH / 2, frame.left + CORNER_WIDTH / 2,
					frame.top + ScreenRate, paint);
			canvas.drawRect(frame.left - CORNER_WIDTH / 2, frame.bottom
					- ScreenRate, frame.left + CORNER_WIDTH / 2, frame.bottom
					+ CORNER_WIDTH / 2, paint);
			canvas.drawRect(frame.left - CORNER_WIDTH / 2, frame.bottom
					- CORNER_WIDTH / 2, frame.left + ScreenRate, frame.bottom
					+ CORNER_WIDTH / 2, paint);
			canvas.drawRect(frame.right - ScreenRate, frame.top - CORNER_WIDTH
					/ 2, frame.right + CORNER_WIDTH / 2, frame.top
					+ CORNER_WIDTH / 2, paint);
			canvas.drawRect(frame.right - CORNER_WIDTH / 2, frame.top
					- CORNER_WIDTH / 2, frame.right + CORNER_WIDTH / 2,
					frame.top + ScreenRate, paint);
			canvas.drawRect(frame.right - CORNER_WIDTH / 2, frame.bottom
					- ScreenRate, frame.right + CORNER_WIDTH / 2, frame.bottom
					+ CORNER_WIDTH / 2, paint);
			canvas.drawRect(frame.right - ScreenRate, frame.bottom
					- CORNER_WIDTH / 2, frame.right + CORNER_WIDTH / 2,
					frame.bottom + CORNER_WIDTH / 2, paint);


			// 画中间移动的线或图片
			slideTop += SPEEN_DISTANCE;
			if (slideTop >= slideBottom) {
				slideTop = frame.top + CORNER_WIDTH;
			}

			// 画中间移动的线
			BitmapDrawable bitmapDrawable=((BitmapDrawable) (getResources()
					.getDrawable(R.drawable.main_icon_dimcode_scan)));
			Rect lineRect = new Rect();
			lineRect.left = frame.left;
			lineRect.right = frame.right;
			lineRect.top = slideTop;
			lineRect.bottom = slideTop+5;
			canvas.drawBitmap(bitmapDrawable.getBitmap(), null,
					lineRect, paint);

		    //画text外边框shape
			GradientDrawable drawable = (GradientDrawable) getResources().getDrawable(R.drawable.main_shape_dimcode_white_rectange);
			int w = drawable.getIntrinsicWidth();
			int h = drawable.getIntrinsicHeight();

			// 取 drawable 的颜色格式
			Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
					: Bitmap.Config.RGB_565;
			// 建立对应 bitmap
			Bitmap bm = Bitmap.createBitmap(w, h, config);
			// 建立对应 bitmap 的画布
			Canvas canvas2 = new Canvas(bm);
			drawable.setBounds(0, 0, w, h);
			// 把 drawable 内容画到画布中
		    drawable.draw(canvas2);
			DisplayMetrics dm = getResources().getDisplayMetrics();
			int left=(dm.widthPixels-470)/2;
			Rect tvRect = new Rect();
			tvRect.left = left;
			tvRect.right =left+470 ;
			tvRect.top = frame.bottom + h*3;
			tvRect.bottom = tvRect.top +44;
			canvas.drawBitmap(bm, null, tvRect, paint);

			// 画扫描框下面的字
			paint.setColor(Color.WHITE);
			paint.setTextSize(28);
			paint.setTextAlign(Paint.Align.CENTER);
			Paint.FontMetrics fontMetrics = paint.getFontMetrics();
			float top = fontMetrics.top;
			float bottom = fontMetrics.bottom;
			int baseLineY = (int) (tvRect.centerY() - top/2 - bottom/2);
			canvas.drawText("请将二维码置入方框内",tvRect.centerX(),baseLineY,paint);

			//画闪点
			Collection<ResultPoint> currentPossible = possibleResultPoints;
			Collection<ResultPoint> currentLast = lastPossibleResultPoints;
			if (currentPossible.isEmpty()) {
				lastPossibleResultPoints = null;
			} else {
				possibleResultPoints = new HashSet<ResultPoint>(5);
				lastPossibleResultPoints = currentPossible;
				paint.setAlpha(OPAQUE);
				paint.setColor(resultPointColor);
				for (ResultPoint point : currentPossible) {
//					canvas.drawCircle(frame.left + point.getX(), frame.top
//							+ point.getY(), 6.0f, paint);// 画扫描到的可能的点
				}
			}
			if (currentLast != null) {
				paint.setAlpha(OPAQUE / 2);
				paint.setColor(resultPointColor);
				for (ResultPoint point : currentLast) {
//					canvas.drawCircle(frame.left + point.getX(), frame.top
//							+ point.getY(), 3.0f, paint);
				}
			}

			// Request another update at the animation interval, but only
			// repaint the laser line,
			// not the entire viewfinder mask.
			postInvalidateDelayed(ANIMATION_DELAY, frame.left, frame.top,
					frame.right, frame.bottom);
		}
	}

	public void drawViewfinder() {
		resultBitmap = null;
		invalidate();
	}

	/**
	 * Draw a bitmap with the result points highlighted instead of the live
	 * scanning display.
	 * 
	 * @param barcode
	 *            An image of the decoded barcode.
	 */
	public void drawResultBitmap(Bitmap barcode) {
		resultBitmap = barcode;
		invalidate();
	}

	public void addPossibleResultPoint(ResultPoint point) {
		possibleResultPoints.add(point);
	}

}
