package com.h6ah4i.android.widget.advrecyclerview.draggable;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public class ItemDraggableRange {

    /* renamed from: a  reason: collision with root package name */
    public final int f11901a;
    public final int b;

    public ItemDraggableRange(int i, int i2) {
        if (i <= i2) {
            this.f11901a = i;
            this.b = i2;
            return;
        }
        throw new IllegalArgumentException("end position (= " + i2 + ") is smaller than start position (=" + i + ")");
    }

    public boolean checkInRange(int i) {
        return i >= this.f11901a && i <= this.b;
    }

    @NonNull
    public String getClassName() {
        return "ItemDraggableRange";
    }

    public int getEnd() {
        return this.b;
    }

    public int getStart() {
        return this.f11901a;
    }

    @NonNull
    public String toString() {
        return getClassName() + "{mStart=" + this.f11901a + ", mEnd=" + this.b + '}';
    }
}
