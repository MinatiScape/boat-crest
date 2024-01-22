package com.h6ah4i.android.widget.advrecyclerview.draggable;
/* loaded from: classes11.dex */
public class DraggableItemState {

    /* renamed from: a  reason: collision with root package name */
    public int f11900a;

    public int getFlags() {
        return this.f11900a;
    }

    public boolean isActive() {
        return (this.f11900a & 2) != 0;
    }

    public boolean isDragging() {
        return (this.f11900a & 1) != 0;
    }

    public boolean isInRange() {
        return (this.f11900a & 4) != 0;
    }

    public boolean isUpdated() {
        return (this.f11900a & Integer.MIN_VALUE) != 0;
    }

    public void setFlags(int i) {
        this.f11900a = i;
    }
}
