package com.htsmart.wristband2.bean;

import com.htsmart.wristband2.dial.DialDrawer;
/* loaded from: classes11.dex */
public class LcdShape {

    /* renamed from: a  reason: collision with root package name */
    public final int f11963a;
    public final DialDrawer.Shape b;

    public LcdShape(int i, DialDrawer.Shape shape) {
        this.f11963a = i;
        this.b = shape;
    }

    public int getLcd() {
        return this.f11963a;
    }

    public DialDrawer.Shape getShape() {
        return this.b;
    }
}
