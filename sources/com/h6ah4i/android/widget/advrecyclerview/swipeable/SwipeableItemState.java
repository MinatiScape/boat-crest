package com.h6ah4i.android.widget.advrecyclerview.swipeable;
/* loaded from: classes11.dex */
public class SwipeableItemState {

    /* renamed from: a  reason: collision with root package name */
    public int f11924a;

    public int getFlags() {
        return this.f11924a;
    }

    public boolean isActive() {
        return (this.f11924a & 2) != 0;
    }

    public boolean isSwiping() {
        return (this.f11924a & 1) != 0;
    }

    public boolean isUpdated() {
        return (this.f11924a & Integer.MIN_VALUE) != 0;
    }

    public void setFlags(int i) {
        this.f11924a = i;
    }
}
