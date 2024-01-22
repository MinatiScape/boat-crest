package androidx.recyclerview.widget;

import androidx.annotation.NonNull;
/* loaded from: classes.dex */
public class BatchingListUpdateCallback implements ListUpdateCallback {
    public final ListUpdateCallback h;
    public int i = 0;
    public int j = -1;
    public int k = -1;
    public Object l = null;

    public BatchingListUpdateCallback(@NonNull ListUpdateCallback listUpdateCallback) {
        this.h = listUpdateCallback;
    }

    public void dispatchLastEvent() {
        int i = this.i;
        if (i == 0) {
            return;
        }
        if (i == 1) {
            this.h.onInserted(this.j, this.k);
        } else if (i == 2) {
            this.h.onRemoved(this.j, this.k);
        } else if (i == 3) {
            this.h.onChanged(this.j, this.k, this.l);
        }
        this.l = null;
        this.i = 0;
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onChanged(int i, int i2, Object obj) {
        int i3;
        if (this.i == 3) {
            int i4 = this.j;
            int i5 = this.k;
            if (i <= i4 + i5 && (i3 = i + i2) >= i4 && this.l == obj) {
                this.j = Math.min(i, i4);
                this.k = Math.max(i5 + i4, i3) - this.j;
                return;
            }
        }
        dispatchLastEvent();
        this.j = i;
        this.k = i2;
        this.l = obj;
        this.i = 3;
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onInserted(int i, int i2) {
        int i3;
        if (this.i == 1 && i >= (i3 = this.j)) {
            int i4 = this.k;
            if (i <= i3 + i4) {
                this.k = i4 + i2;
                this.j = Math.min(i, i3);
                return;
            }
        }
        dispatchLastEvent();
        this.j = i;
        this.k = i2;
        this.i = 1;
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onMoved(int i, int i2) {
        dispatchLastEvent();
        this.h.onMoved(i, i2);
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onRemoved(int i, int i2) {
        int i3;
        if (this.i == 2 && (i3 = this.j) >= i && i3 <= i + i2) {
            this.k += i2;
            this.j = i;
            return;
        }
        dispatchLastEvent();
        this.j = i;
        this.k = i2;
        this.i = 2;
    }
}
