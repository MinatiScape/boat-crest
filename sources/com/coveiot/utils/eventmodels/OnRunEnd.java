package com.coveiot.utils.eventmodels;
/* loaded from: classes9.dex */
public final class OnRunEnd {

    /* renamed from: a  reason: collision with root package name */
    public boolean f7617a;
    public boolean b;
    public boolean c;

    public OnRunEnd(boolean z) {
        this.f7617a = true;
        this.b = false;
        this.c = false;
        this.f7617a = z;
    }

    public final boolean isSaveSession() {
        return this.f7617a;
    }

    public boolean isSessionDestroyed() {
        return this.b;
    }

    public boolean isShowDetailsScreen() {
        return this.c;
    }

    public final void setSaveSession(boolean z) {
        this.f7617a = z;
    }

    public void setShowDetailsScreen(boolean z) {
        this.c = z;
    }

    public OnRunEnd(boolean z, boolean z2) {
        this.f7617a = true;
        this.b = false;
        this.c = false;
        this.f7617a = z;
        this.b = z2;
    }

    public OnRunEnd(boolean z, boolean z2, boolean z3) {
        this.f7617a = true;
        this.b = false;
        this.c = false;
        this.f7617a = z;
        this.b = z2;
        this.c = z3;
    }
}
