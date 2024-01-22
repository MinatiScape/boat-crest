package com.google.firebase.heartbeatinfo;

import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import java.util.Objects;
/* loaded from: classes10.dex */
public final class a extends HeartBeatResult {

    /* renamed from: a  reason: collision with root package name */
    public final String f11279a;
    public final long b;
    public final HeartBeatInfo.HeartBeat c;

    public a(String str, long j, HeartBeatInfo.HeartBeat heartBeat) {
        Objects.requireNonNull(str, "Null sdkName");
        this.f11279a = str;
        this.b = j;
        Objects.requireNonNull(heartBeat, "Null heartBeat");
        this.c = heartBeat;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof HeartBeatResult) {
            HeartBeatResult heartBeatResult = (HeartBeatResult) obj;
            return this.f11279a.equals(heartBeatResult.getSdkName()) && this.b == heartBeatResult.getMillis() && this.c.equals(heartBeatResult.getHeartBeat());
        }
        return false;
    }

    @Override // com.google.firebase.heartbeatinfo.HeartBeatResult
    public HeartBeatInfo.HeartBeat getHeartBeat() {
        return this.c;
    }

    @Override // com.google.firebase.heartbeatinfo.HeartBeatResult
    public long getMillis() {
        return this.b;
    }

    @Override // com.google.firebase.heartbeatinfo.HeartBeatResult
    public String getSdkName() {
        return this.f11279a;
    }

    public int hashCode() {
        long j = this.b;
        return ((((this.f11279a.hashCode() ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.c.hashCode();
    }

    public String toString() {
        return "HeartBeatResult{sdkName=" + this.f11279a + ", millis=" + this.b + ", heartBeat=" + this.c + "}";
    }
}
