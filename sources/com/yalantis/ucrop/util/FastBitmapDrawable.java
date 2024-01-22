package com.yalantis.ucrop.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
/* loaded from: classes12.dex */
public class FastBitmapDrawable extends Drawable {
    public Bitmap b;
    public int d;
    public int e;

    /* renamed from: a  reason: collision with root package name */
    public final Paint f13882a = new Paint(2);
    public int c = 255;

    public FastBitmapDrawable(Bitmap bitmap) {
        setBitmap(bitmap);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Bitmap bitmap = this.b;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        canvas.drawBitmap(this.b, (Rect) null, getBounds(), this.f13882a);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.c;
    }

    public Bitmap getBitmap() {
        return this.b;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.e;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.d;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        return this.e;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        return this.d;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.c = i;
        this.f13882a.setAlpha(i);
    }

    public void setBitmap(Bitmap bitmap) {
        this.b = bitmap;
        if (bitmap != null) {
            this.d = bitmap.getWidth();
            this.e = this.b.getHeight();
            return;
        }
        this.e = 0;
        this.d = 0;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f13882a.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean z) {
        this.f13882a.setFilterBitmap(z);
    }
}
