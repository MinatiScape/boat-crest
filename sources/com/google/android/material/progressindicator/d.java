package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import com.google.android.material.progressindicator.BaseProgressIndicatorSpec;
/* loaded from: classes10.dex */
public abstract class d<S extends BaseProgressIndicatorSpec> {

    /* renamed from: a  reason: collision with root package name */
    public S f10334a;
    public c b;

    public d(S s) {
        this.f10334a = s;
    }

    public abstract void a(@NonNull Canvas canvas, @FloatRange(from = 0.0d, to = 1.0d) float f);

    public abstract void b(@NonNull Canvas canvas, @NonNull Paint paint, @FloatRange(from = 0.0d, to = 1.0d) float f, @FloatRange(from = 0.0d, to = 1.0d) float f2, @ColorInt int i);

    public abstract void c(@NonNull Canvas canvas, @NonNull Paint paint);

    public abstract int d();

    public abstract int e();

    public void f(@NonNull c cVar) {
        this.b = cVar;
    }

    public void g(@NonNull Canvas canvas, @FloatRange(from = 0.0d, to = 1.0d) float f) {
        this.f10334a.c();
        a(canvas, f);
    }
}
