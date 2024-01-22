package com.clevertap.android.sdk.inapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.webkit.WebView;
@SuppressLint({"ViewConstructor"})
/* loaded from: classes2.dex */
public class e extends WebView {
    public final Point h;
    public int i;
    public int j;
    public int k;
    public int l;

    @SuppressLint({"ResourceType"})
    public e(Context context, int i, int i2, int i3, int i4) {
        super(context);
        this.h = new Point();
        this.k = i;
        this.i = i2;
        this.l = i3;
        this.j = i4;
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        setHorizontalFadingEdgeEnabled(false);
        setVerticalFadingEdgeEnabled(false);
        setOverScrollMode(2);
        setBackgroundColor(0);
        setId(188293);
    }

    public void a() {
        int i = this.k;
        if (i != 0) {
            this.h.x = (int) TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
        } else {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            this.h.x = (int) ((displayMetrics.widthPixels * this.l) / 100.0f);
        }
        int i2 = this.i;
        if (i2 != 0) {
            this.h.y = (int) TypedValue.applyDimension(1, i2, getResources().getDisplayMetrics());
            return;
        }
        DisplayMetrics displayMetrics2 = getResources().getDisplayMetrics();
        this.h.y = (int) ((displayMetrics2.heightPixels * this.j) / 100.0f);
    }

    @Override // android.webkit.WebView, android.widget.AbsoluteLayout, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        a();
        Point point = this.h;
        setMeasuredDimension(point.x, point.y);
    }

    @Override // android.view.View
    public boolean performClick() {
        return super.performClick();
    }
}
