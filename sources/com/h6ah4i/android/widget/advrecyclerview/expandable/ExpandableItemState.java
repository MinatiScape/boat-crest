package com.h6ah4i.android.widget.advrecyclerview.expandable;
/* loaded from: classes11.dex */
public class ExpandableItemState {

    /* renamed from: a  reason: collision with root package name */
    public int f11914a;

    public int getFlags() {
        return this.f11914a;
    }

    public boolean hasExpandedStateChanged() {
        return (this.f11914a & 8) != 0;
    }

    public boolean isChild() {
        return (this.f11914a & 2) != 0;
    }

    public boolean isExpanded() {
        return (this.f11914a & 4) != 0;
    }

    public boolean isGroup() {
        return (this.f11914a & 1) != 0;
    }

    public boolean isSwiping() {
        return (this.f11914a & 4) != 0;
    }

    public boolean isUpdated() {
        return (this.f11914a & Integer.MIN_VALUE) != 0;
    }

    public void setFlags(int i) {
        this.f11914a = i;
    }
}
