package com.github.mikephil.charting.components;

import com.github.mikephil.charting.utils.Utils;
/* loaded from: classes9.dex */
public class XAxis extends AxisBase {
    public int mLabelWidth = 1;
    public int mLabelHeight = 1;
    public int mLabelRotatedWidth = 1;
    public int mLabelRotatedHeight = 1;
    public float mLabelRotationAngle = 0.0f;
    public boolean h = false;
    public XAxisPosition i = XAxisPosition.TOP;

    /* loaded from: classes9.dex */
    public enum XAxisPosition {
        TOP,
        BOTTOM,
        BOTH_SIDED,
        TOP_INSIDE,
        BOTTOM_INSIDE
    }

    public XAxis() {
        this.mYOffset = Utils.convertDpToPixel(4.0f);
    }

    public float getLabelRotationAngle() {
        return this.mLabelRotationAngle;
    }

    public XAxisPosition getPosition() {
        return this.i;
    }

    public boolean isAvoidFirstLastClippingEnabled() {
        return this.h;
    }

    public void setAvoidFirstLastClipping(boolean z) {
        this.h = z;
    }

    public void setLabelRotationAngle(float f) {
        this.mLabelRotationAngle = f;
    }

    public void setPosition(XAxisPosition xAxisPosition) {
        this.i = xAxisPosition;
    }
}
