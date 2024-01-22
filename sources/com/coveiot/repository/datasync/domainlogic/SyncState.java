package com.coveiot.repository.datasync.domainlogic;
/* loaded from: classes9.dex */
public class SyncState {

    /* renamed from: a  reason: collision with root package name */
    public boolean f7388a;
    public long b;
    public String c;
    public int d;

    public SyncState(boolean z, long j) {
        this.f7388a = z;
        this.b = j;
    }

    public int getErrorCode() {
        return this.d;
    }

    public String getErrorMsg() {
        return this.c;
    }

    public long getTimeStamp() {
        return this.b;
    }

    public boolean isSuccess() {
        return this.f7388a;
    }

    public void setErrorCode(int i) {
        this.d = i;
    }

    public void setErrorMsg(String str) {
        this.c = str;
    }
}
