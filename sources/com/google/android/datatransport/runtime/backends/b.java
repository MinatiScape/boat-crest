package com.google.android.datatransport.runtime.backends;

import com.google.android.datatransport.runtime.backends.BackendResponse;
import java.util.Objects;
/* loaded from: classes6.dex */
public final class b extends BackendResponse {

    /* renamed from: a  reason: collision with root package name */
    public final BackendResponse.Status f8089a;
    public final long b;

    public b(BackendResponse.Status status, long j) {
        Objects.requireNonNull(status, "Null status");
        this.f8089a = status;
        this.b = j;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BackendResponse) {
            BackendResponse backendResponse = (BackendResponse) obj;
            return this.f8089a.equals(backendResponse.getStatus()) && this.b == backendResponse.getNextRequestWaitMillis();
        }
        return false;
    }

    @Override // com.google.android.datatransport.runtime.backends.BackendResponse
    public long getNextRequestWaitMillis() {
        return this.b;
    }

    @Override // com.google.android.datatransport.runtime.backends.BackendResponse
    public BackendResponse.Status getStatus() {
        return this.f8089a;
    }

    public int hashCode() {
        long j = this.b;
        return ((this.f8089a.hashCode() ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        return "BackendResponse{status=" + this.f8089a + ", nextRequestWaitMillis=" + this.b + "}";
    }
}
