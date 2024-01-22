package com.github.mikephil.charting.jobs;

import android.view.View;
import com.github.mikephil.charting.utils.ObjectPool;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;
/* loaded from: classes9.dex */
public abstract class ViewPortJob extends ObjectPool.Poolable implements Runnable {
    public Transformer mTrans;
    public ViewPortHandler mViewPortHandler;
    public float[] pts = new float[2];
    public View view;
    public float xValue;
    public float yValue;

    public ViewPortJob(ViewPortHandler viewPortHandler, float f, float f2, Transformer transformer, View view) {
        this.xValue = 0.0f;
        this.yValue = 0.0f;
        this.mViewPortHandler = viewPortHandler;
        this.xValue = f;
        this.yValue = f2;
        this.mTrans = transformer;
        this.view = view;
    }

    public float getXValue() {
        return this.xValue;
    }

    public float getYValue() {
        return this.yValue;
    }
}
