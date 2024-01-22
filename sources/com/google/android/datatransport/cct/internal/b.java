package com.google.android.datatransport.cct.internal;

import androidx.annotation.NonNull;
import com.google.firebase.encoders.annotations.Encodable;
import java.util.List;
import java.util.Objects;
/* loaded from: classes6.dex */
public final class b extends BatchedLogRequest {

    /* renamed from: a  reason: collision with root package name */
    public final List<LogRequest> f8067a;

    public b(List<LogRequest> list) {
        Objects.requireNonNull(list, "Null logRequests");
        this.f8067a = list;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BatchedLogRequest) {
            return this.f8067a.equals(((BatchedLogRequest) obj).getLogRequests());
        }
        return false;
    }

    @Override // com.google.android.datatransport.cct.internal.BatchedLogRequest
    @NonNull
    @Encodable.Field(name = "logRequest")
    public List<LogRequest> getLogRequests() {
        return this.f8067a;
    }

    public int hashCode() {
        return this.f8067a.hashCode() ^ 1000003;
    }

    public String toString() {
        return "BatchedLogRequest{logRequests=" + this.f8067a + "}";
    }
}
