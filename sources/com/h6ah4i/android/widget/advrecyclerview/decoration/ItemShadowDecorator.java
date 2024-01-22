package com.h6ah4i.android.widget.advrecyclerview.decoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes11.dex */
public class ItemShadowDecorator extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    public final NinePatchDrawable f11897a;
    public final Rect b;
    public final boolean c;

    public ItemShadowDecorator(@NonNull NinePatchDrawable ninePatchDrawable) {
        this(ninePatchDrawable, true);
    }

    public final boolean a(View view) {
        Drawable background;
        if (view.getVisibility() == 0 && view.getAlpha() == 1.0f && (background = view.getBackground()) != null) {
            return (!this.c && (background instanceof ColorDrawable) && ((ColorDrawable) background).getAlpha() == 0) ? false : true;
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        rect.set(0, 0, 0, 0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        int childCount = recyclerView.getChildCount();
        if (childCount == 0) {
            return;
        }
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            if (a(childAt)) {
                int translationX = (int) (childAt.getTranslationX() + 0.5f);
                int translationY = (int) (childAt.getTranslationY() + 0.5f);
                int left = childAt.getLeft() - this.b.left;
                int right = childAt.getRight() + this.b.right;
                this.f11897a.setBounds(left + translationX, (childAt.getTop() - this.b.top) + translationY, right + translationX, childAt.getBottom() + this.b.bottom + translationY);
                this.f11897a.draw(canvas);
            }
        }
    }

    public ItemShadowDecorator(@NonNull NinePatchDrawable ninePatchDrawable, boolean z) {
        Rect rect = new Rect();
        this.b = rect;
        this.f11897a = ninePatchDrawable;
        ninePatchDrawable.getPadding(rect);
        this.c = z;
    }
}
