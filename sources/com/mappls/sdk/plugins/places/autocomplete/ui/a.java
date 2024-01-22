package com.mappls.sdk.plugins.places.autocomplete.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.plugins.places.R;
/* loaded from: classes10.dex */
public final class a extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    public Drawable f13137a;

    public a(Context context) {
        this.f13137a = ContextCompat.getDrawable(context, R.drawable.mappls_search_list_line_divider);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public final void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        int paddingLeft = recyclerView.getPaddingLeft();
        int width = recyclerView.getWidth() - recyclerView.getPaddingRight();
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            int bottom = childAt.getBottom() + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) childAt.getLayoutParams())).bottomMargin;
            this.f13137a.setBounds(paddingLeft, bottom, width, this.f13137a.getIntrinsicHeight() + bottom);
            this.f13137a.draw(canvas);
        }
    }
}
