package com.google.android.material.divider;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
/* loaded from: classes10.dex */
public class MaterialDividerItemDecoration extends RecyclerView.ItemDecoration {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    public static final int i = R.style.Widget_MaterialComponents_MaterialDivider;
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public Drawable f10291a;
    public int b;
    @ColorInt
    public int c;
    public int d;
    public int e;
    public int f;
    public boolean g;
    public final Rect h;

    public MaterialDividerItemDecoration(@NonNull Context context, int i2) {
        this(context, null, i2);
    }

    public final void a(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView) {
        int height;
        int i2;
        canvas.save();
        if (recyclerView.getClipToPadding()) {
            i2 = recyclerView.getPaddingTop();
            height = recyclerView.getHeight() - recyclerView.getPaddingBottom();
            canvas.clipRect(recyclerView.getPaddingLeft(), i2, recyclerView.getWidth() - recyclerView.getPaddingRight(), height);
        } else {
            height = recyclerView.getHeight();
            i2 = 0;
        }
        int i3 = i2 + this.e;
        int i4 = height - this.f;
        int childCount = recyclerView.getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = recyclerView.getChildAt(i5);
            recyclerView.getLayoutManager().getDecoratedBoundsWithMargins(childAt, this.h);
            int round = this.h.right + Math.round(childAt.getTranslationX());
            this.f10291a.setBounds((round - this.f10291a.getIntrinsicWidth()) - this.b, i3, round, i4);
            this.f10291a.draw(canvas);
        }
        canvas.restore();
    }

    public final void b(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView) {
        int width;
        int i2;
        canvas.save();
        if (recyclerView.getClipToPadding()) {
            i2 = recyclerView.getPaddingLeft();
            width = recyclerView.getWidth() - recyclerView.getPaddingRight();
            canvas.clipRect(i2, recyclerView.getPaddingTop(), width, recyclerView.getHeight() - recyclerView.getPaddingBottom());
        } else {
            width = recyclerView.getWidth();
            i2 = 0;
        }
        boolean z = ViewCompat.getLayoutDirection(recyclerView) == 1;
        int i3 = i2 + (z ? this.f : this.e);
        int i4 = width - (z ? this.e : this.f);
        int childCount = recyclerView.getChildCount();
        if (!this.g) {
            childCount--;
        }
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = recyclerView.getChildAt(i5);
            recyclerView.getDecoratedBoundsWithMargins(childAt, this.h);
            int round = this.h.bottom + Math.round(childAt.getTranslationY());
            this.f10291a.setBounds(i3, (round - this.f10291a.getIntrinsicHeight()) - this.b, i4, round);
            this.f10291a.draw(canvas);
        }
        canvas.restore();
    }

    @ColorInt
    public int getDividerColor() {
        return this.c;
    }

    @Px
    public int getDividerInsetEnd() {
        return this.f;
    }

    @Px
    public int getDividerInsetStart() {
        return this.e;
    }

    @Px
    public int getDividerThickness() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        rect.set(0, 0, 0, 0);
        if (this.d == 1) {
            rect.bottom = this.f10291a.getIntrinsicHeight() + this.b;
        } else {
            rect.right = this.f10291a.getIntrinsicWidth() + this.b;
        }
    }

    public int getOrientation() {
        return this.d;
    }

    public boolean isLastItemDecorated() {
        return this.g;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        if (recyclerView.getLayoutManager() == null) {
            return;
        }
        if (this.d == 1) {
            b(canvas, recyclerView);
        } else {
            a(canvas, recyclerView);
        }
    }

    public void setDividerColor(@ColorInt int i2) {
        this.c = i2;
        Drawable wrap = DrawableCompat.wrap(this.f10291a);
        this.f10291a = wrap;
        DrawableCompat.setTint(wrap, i2);
    }

    public void setDividerColorResource(@NonNull Context context, @ColorRes int i2) {
        setDividerColor(ContextCompat.getColor(context, i2));
    }

    public void setDividerInsetEnd(@Px int i2) {
        this.f = i2;
    }

    public void setDividerInsetEndResource(@NonNull Context context, @DimenRes int i2) {
        setDividerInsetEnd(context.getResources().getDimensionPixelOffset(i2));
    }

    public void setDividerInsetStart(@Px int i2) {
        this.e = i2;
    }

    public void setDividerInsetStartResource(@NonNull Context context, @DimenRes int i2) {
        setDividerInsetStart(context.getResources().getDimensionPixelOffset(i2));
    }

    public void setDividerThickness(@Px int i2) {
        this.b = i2;
    }

    public void setDividerThicknessResource(@NonNull Context context, @DimenRes int i2) {
        setDividerThickness(context.getResources().getDimensionPixelSize(i2));
    }

    public void setLastItemDecorated(boolean z) {
        this.g = z;
    }

    public void setOrientation(int i2) {
        if (i2 != 0 && i2 != 1) {
            throw new IllegalArgumentException("Invalid orientation: " + i2 + ". It should be either HORIZONTAL or VERTICAL");
        }
        this.d = i2;
    }

    public MaterialDividerItemDecoration(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        this(context, attributeSet, R.attr.materialDividerStyle, i2);
    }

    public MaterialDividerItemDecoration(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        this.h = new Rect();
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, R.styleable.MaterialDivider, i2, i, new int[0]);
        this.c = MaterialResources.getColorStateList(context, obtainStyledAttributes, R.styleable.MaterialDivider_dividerColor).getDefaultColor();
        this.b = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MaterialDivider_dividerThickness, context.getResources().getDimensionPixelSize(R.dimen.material_divider_thickness));
        this.e = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.MaterialDivider_dividerInsetStart, 0);
        this.f = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.MaterialDivider_dividerInsetEnd, 0);
        this.g = obtainStyledAttributes.getBoolean(R.styleable.MaterialDivider_lastItemDecorated, true);
        obtainStyledAttributes.recycle();
        this.f10291a = new ShapeDrawable();
        setDividerColor(this.c);
        setOrientation(i3);
    }
}
