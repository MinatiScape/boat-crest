package com.coveiot.khjstyledb.heartrate.model;

import kotlin.Metadata;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0012\u0010\u0013R\"\u0010\t\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\"\u0010\u0011\u001a\u00020\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0014"}, d2 = {"Lcom/coveiot/khjstyledb/heartrate/model/SessionHR;", "", "", "a", "I", "getHrValue", "()I", "setHrValue", "(I)V", "hrValue", "", "b", "J", "getHrTimeStamp", "()J", "setHrTimeStamp", "(J)V", "hrTimeStamp", "<init>", "()V", "khjstyledb_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes8.dex */
public final class SessionHR {

    /* renamed from: a  reason: collision with root package name */
    public int f7128a;
    public long b;

    public final long getHrTimeStamp() {
        return this.b;
    }

    public final int getHrValue() {
        return this.f7128a;
    }

    public final void setHrTimeStamp(long j) {
        this.b = j;
    }

    public final void setHrValue(int i) {
        this.f7128a = i;
    }
}
