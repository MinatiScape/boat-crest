package com.google.firebase.heartbeatinfo;

import java.util.Objects;
/* loaded from: classes10.dex */
public final class b extends SdkHeartBeatResult {
    public final String h;
    public final long i;

    public b(String str, long j) {
        Objects.requireNonNull(str, "Null sdkName");
        this.h = str;
        this.i = j;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SdkHeartBeatResult) {
            SdkHeartBeatResult sdkHeartBeatResult = (SdkHeartBeatResult) obj;
            return this.h.equals(sdkHeartBeatResult.getSdkName()) && this.i == sdkHeartBeatResult.getMillis();
        }
        return false;
    }

    @Override // com.google.firebase.heartbeatinfo.SdkHeartBeatResult
    public long getMillis() {
        return this.i;
    }

    @Override // com.google.firebase.heartbeatinfo.SdkHeartBeatResult
    public String getSdkName() {
        return this.h;
    }

    public int hashCode() {
        long j = this.i;
        return ((this.h.hashCode() ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        return "SdkHeartBeatResult{sdkName=" + this.h + ", millis=" + this.i + "}";
    }
}
