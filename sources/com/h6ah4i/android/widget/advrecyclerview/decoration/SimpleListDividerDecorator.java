package com.h6ah4i.android.widget.advrecyclerview.decoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes11.dex */
public class SimpleListDividerDecorator extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    public final Drawable f11898a;
    public final Drawable b;
    public final int c;
    public final int d;
    public final boolean e;

    public SimpleListDividerDecorator(@Nullable Drawable drawable, boolean z) {
        this(drawable, null, z);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        if (this.e) {
            rect.set(0, 0, 0, 0);
        } else {
            rect.set(0, 0, this.d, this.c);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        RecyclerView recyclerView2 = recyclerView;
        int childCount = recyclerView.getChildCount();
        if (childCount == 0) {
            return;
        }
        boolean z = this.e;
        float f = 1.0f;
        float f2 = z ? 1.0f : this.d + 1.0f;
        float f3 = z ? 1.0f : this.c + 1.0f;
        int i = 0;
        while (i < childCount - 1) {
            View childAt = recyclerView2.getChildAt(i);
            i++;
            View childAt2 = recyclerView2.getChildAt(i);
            if (childAt.getVisibility() == 0 && childAt2.getVisibility() == 0) {
                float bottom = childAt.getBottom() + childAt.getTranslationY();
                float top = childAt2.getTop() + childAt2.getTranslationY();
                float right = childAt.getRight() + childAt.getTranslationX();
                float left = childAt2.getLeft() + childAt2.getTranslationX();
                if ((this.c != 0 && Math.abs(top - bottom) < f3) || (this.d != 0 && Math.abs(left - right) < f2)) {
                    if (Math.abs((ViewCompat.getTranslationZ(childAt2) + ViewCompat.getElevation(childAt2)) - (ViewCompat.getTranslationZ(childAt) + ViewCompat.getElevation(childAt))) < f) {
                        float alpha = childAt.getAlpha();
                        float alpha2 = childAt2.getAlpha();
                        int translationX = (int) (childAt.getTranslationX() + 0.5f);
                        int translationY = (int) (childAt.getTranslationY() + 0.5f);
                        if (this.c != 0) {
                            int left2 = childAt.getLeft();
                            int right2 = childAt.getRight();
                            int bottom2 = childAt.getBottom() - (this.e ? this.c : 0);
                            this.f11898a.setAlpha((int) (((alpha + alpha2) * 127.5f) + 0.5f));
                            this.f11898a.setBounds(left2 + translationX, bottom2 + translationY, right2 + translationX, bottom2 + this.c + translationY);
                            this.f11898a.draw(canvas);
                        }
                        if (this.d != 0) {
                            int right3 = childAt.getRight() - (this.e ? this.d : 0);
                            int i2 = this.d + right3;
                            int top2 = childAt.getTop();
                            int bottom3 = childAt.getBottom();
                            this.b.setAlpha((int) (((alpha + alpha2) * 127.5f) + 0.5f));
                            this.b.setBounds(right3 + translationX, top2 + translationY, i2 + translationX, bottom3 + translationY);
                            this.b.draw(canvas);
                        }
                    }
                }
            }
            recyclerView2 = recyclerView;
            f = 1.0f;
        }
    }

    public SimpleListDividerDecorator(@Nullable Drawable drawable, @Nullable Drawable drawable2, boolean z) {
        this.f11898a = drawable;
        this.b = drawable2;
        this.c = drawable != null ? drawable.getIntrinsicHeight() : 0;
        this.d = drawable2 != null ? drawable2.getIntrinsicWidth() : 0;
        this.e = z;
    }
}
