package com.google.maps.android.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import com.google.maps.android.R;
/* loaded from: classes10.dex */
public class a extends Drawable {

    /* renamed from: a  reason: collision with root package name */
    public final Drawable f11568a;
    public final Drawable b;
    public int c = -1;

    public a(Context context) {
        this.b = ContextCompat.getDrawable(context, R.drawable.amu_bubble_mask);
        this.f11568a = ContextCompat.getDrawable(context, R.drawable.amu_bubble_shadow);
    }

    public void a(int i) {
        this.c = i;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.b.draw(canvas);
        canvas.drawColor(this.c, PorterDuff.Mode.SRC_IN);
        this.f11568a.draw(canvas);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        return this.b.getPadding(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(int i, int i2, int i3, int i4) {
        this.b.setBounds(i, i2, i3, i4);
        this.f11568a.setBounds(i, i2, i3, i4);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        throw new UnsupportedOperationException();
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(Rect rect) {
        this.b.setBounds(rect);
        this.f11568a.setBounds(rect);
    }
}
