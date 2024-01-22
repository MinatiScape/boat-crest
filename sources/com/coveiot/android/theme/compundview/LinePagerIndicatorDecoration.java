package com.coveiot.android.theme.compundview;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes7.dex */
public class LinePagerIndicatorDecoration extends RecyclerView.ItemDecoration {
    public static final float i = Resources.getSystem().getDisplayMetrics().density;

    /* renamed from: a  reason: collision with root package name */
    public int f6098a = -1;
    public int b = 1728053247;
    public final int c;
    public final float d;
    public final float e;
    public final float f;
    public final Interpolator g;
    public final Paint h;

    public LinePagerIndicatorDecoration() {
        float f = i;
        this.c = (int) (f * 16.0f);
        float f2 = 2.0f * f;
        this.d = f2;
        this.e = 16.0f * f;
        this.f = f * 4.0f;
        this.g = new AccelerateDecelerateInterpolator();
        Paint paint = new Paint();
        this.h = paint;
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(f2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
    }

    public final void a(Canvas canvas, float f, float f2, int i2, float f3, int i3) {
        this.h.setColor(this.f6098a);
        float f4 = this.e;
        float f5 = this.f + f4;
        if (f3 == 0.0f) {
            float f6 = f + (f5 * i2);
            canvas.drawLine(f6, f2, f6 + f4, f2, this.h);
            return;
        }
        float f7 = f + (i2 * f5);
        float f8 = f3 * f4;
        canvas.drawLine(f7 + f8, f2, f7 + f4, f2, this.h);
        if (i2 < i3 - 1) {
            float f9 = f7 + f5;
            canvas.drawLine(f9, f2, f9 + f8, f2, this.h);
        }
    }

    public final void b(Canvas canvas, float f, float f2, int i2) {
        this.h.setColor(this.b);
        float f3 = this.e + this.f;
        for (int i3 = 0; i3 < i2; i3++) {
            canvas.drawLine(f, f2, f + this.e, f2, this.h);
            f += f3;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        rect.bottom = this.c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        super.onDrawOver(canvas, recyclerView, state);
        int itemCount = recyclerView.getAdapter().getItemCount();
        float width = (recyclerView.getWidth() - ((this.e * itemCount) + (Math.max(0, itemCount - 1) * this.f))) / 2.0f;
        float height = recyclerView.getHeight() - (this.c / 2.0f);
        b(canvas, width, height, itemCount);
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        if (findFirstVisibleItemPosition == -1) {
            return;
        }
        View findViewByPosition = linearLayoutManager.findViewByPosition(findFirstVisibleItemPosition);
        a(canvas, width, height, findFirstVisibleItemPosition, this.g.getInterpolation((findViewByPosition.getLeft() * (-1)) / findViewByPosition.getWidth()), itemCount);
    }
}
