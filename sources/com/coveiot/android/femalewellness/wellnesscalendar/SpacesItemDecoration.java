package com.coveiot.android.femalewellness.wellnesscalendar;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes4.dex */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    public int f4405a;

    public SpacesItemDecoration(int i) {
        this.f4405a = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int i = this.f4405a;
        rect.left = i;
        rect.right = i;
        rect.bottom = i;
        if (recyclerView.getChildLayoutPosition(view) == 0) {
            rect.top = this.f4405a;
        } else {
            rect.top = 0;
        }
    }
}
