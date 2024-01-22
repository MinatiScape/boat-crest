package com.touchgui.sdk.bean;
/* loaded from: classes12.dex */
public class TGIotFluctuateFunction extends TGIotFunction {
    private int currentValue;
    private int maxValue;
    private int minValue;
    private int stepValue;

    public TGIotFluctuateFunction() {
        super(4);
    }

    public int getCurrentValue() {
        return this.currentValue;
    }

    public int getMaxValue() {
        return this.maxValue;
    }

    public int getMinValue() {
        return this.minValue;
    }

    public int getStepValue() {
        return this.stepValue;
    }

    public void setCurrentValue(int i) {
        this.currentValue = i;
    }

    public void setMaxValue(int i) {
        this.maxValue = i;
    }

    public void setMinValue(int i) {
        this.minValue = i;
    }

    public void setStepValue(int i) {
        this.stepValue = i;
    }
}
