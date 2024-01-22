package com.coveiot.utils.eventmodels;
/* loaded from: classes9.dex */
public final class OnStopSession {

    /* renamed from: a  reason: collision with root package name */
    public boolean f7619a;
    public boolean b;

    public OnStopSession(boolean z) {
        this.f7619a = true;
        this.b = false;
        this.f7619a = z;
    }

    public final boolean isSaveSession() {
        return this.f7619a;
    }

    public boolean isSessionDestroyed() {
        return this.b;
    }

    public final void setSaveSession(boolean z) {
        this.f7619a = z;
    }

    public OnStopSession(boolean z, boolean z2) {
        this.f7619a = true;
        this.b = false;
        this.b = z2;
    }
}
