package com.jackting.common.base;

import android.content.Context;
import android.os.Bundle;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jackting.common.base.interfaces.IFragment;
import com.jackting.common.base.interfaces.IView;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IFragment, IView {

    protected final String TAG = this.getClass().getSimpleName();
    private Unbinder mUnbinder;
    protected View rootView;
    @Inject
    @Nullable
    protected P presenter;
    protected Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View root = null;
        try {
            int layoutResId = getContentViewResId();
            if(layoutResId != 0){
                root=inflater.inflate(getContentViewResId(),container,false);
                mUnbinder = ButterKnife.bind(this, root);
            }
        }catch (Exception e){
            if(e instanceof InflateException) throw e;
            e.printStackTrace();
        }
        if(root == null){
            root = super.onCreateView(inflater, container, savedInstanceState);
        }
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView = view;
        daggerInit();
        if(presenter!=null){
            presenter.takeView(this);
        }
        if(useEventBus()){
            EventBus.getDefault().register(this);
        }
        init(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(useEventBus()){
            EventBus.getDefault().unregister(this);
        }
        if(mUnbinder!=null && mUnbinder != Unbinder.EMPTY){
            mUnbinder.unbind();
        }
        if(presenter != null){
            presenter.dropView();
        }
        presenter = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    //默认不使用EventBus，如果要使用请重写该方法并返回true
    public boolean useEventBus() {
        return false;
    }
}
