package com.mappls.sdk.plugin.directions.view;

import android.graphics.DashPathEffect;
/* loaded from: classes11.dex */
public class x {

    /* renamed from: a  reason: collision with root package name */
    public DashPathEffect f13127a;
    public float b;
    public float c;
    public float d;

    public DashPathEffect a(float f, float f2, float f3) {
        if (this.b != f || this.c != f2 || this.d != f3) {
            this.b = f;
            this.c = f2;
            this.d = f3;
            this.f13127a = new DashPathEffect(new float[]{f, f2}, f3);
        }
        return this.f13127a;
    }
}
