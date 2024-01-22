package com.google.android.datatransport.cct.internal;
/* loaded from: classes6.dex */
public final class f extends LogResponse {

    /* renamed from: a  reason: collision with root package name */
    public final long f8074a;

    public f(long j) {
        this.f8074a = j;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof LogResponse) && this.f8074a == ((LogResponse) obj).getNextRequestWaitMillis();
    }

    @Override // com.google.android.datatransport.cct.internal.LogResponse
    public long getNextRequestWaitMillis() {
        return this.f8074a;
    }

    public int hashCode() {
        long j = this.f8074a;
        return ((int) (j ^ (j >>> 32))) ^ 1000003;
    }

    public String toString() {
        return "LogResponse{nextRequestWaitMillis=" + this.f8074a + "}";
    }
}
