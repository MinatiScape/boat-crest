package com.h6ah4i.android.widget.advrecyclerview.adapter;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes11.dex */
public class UnwrapPositionResult {
    @Nullable
    public RecyclerView.Adapter adapter;
    public int position = -1;
    @Nullable
    public Object tag;

    public void clear() {
        this.adapter = null;
        this.tag = null;
        this.position = -1;
    }

    public boolean isValid() {
        return (this.adapter == null || this.position == -1) ? false : true;
    }
}
