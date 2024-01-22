package com.h6ah4i.android.widget.advrecyclerview.draggable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes11.dex */
public class f extends b {
    public f(@NonNull RecyclerView recyclerView) {
        super(recyclerView);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.draggable.b
    public int f(int i) {
        if (i != 0) {
            if (i == 1) {
                return 2;
            }
            throw new IllegalArgumentException();
        }
        return 0;
    }
}
