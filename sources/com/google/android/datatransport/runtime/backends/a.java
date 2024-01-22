package com.google.android.datatransport.runtime.backends;

import androidx.annotation.Nullable;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.backends.BackendRequest;
import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes6.dex */
public final class a extends BackendRequest {

    /* renamed from: a  reason: collision with root package name */
    public final Iterable<EventInternal> f8087a;
    public final byte[] b;

    /* loaded from: classes6.dex */
    public static final class b extends BackendRequest.Builder {

        /* renamed from: a  reason: collision with root package name */
        public Iterable<EventInternal> f8088a;
        public byte[] b;

        @Override // com.google.android.datatransport.runtime.backends.BackendRequest.Builder
        public BackendRequest build() {
            String str = "";
            if (this.f8088a == null) {
                str = " events";
            }
            if (str.isEmpty()) {
                return new a(this.f8088a, this.b);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.google.android.datatransport.runtime.backends.BackendRequest.Builder
        public BackendRequest.Builder setEvents(Iterable<EventInternal> iterable) {
            Objects.requireNonNull(iterable, "Null events");
            this.f8088a = iterable;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.backends.BackendRequest.Builder
        public BackendRequest.Builder setExtras(@Nullable byte[] bArr) {
            this.b = bArr;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BackendRequest) {
            BackendRequest backendRequest = (BackendRequest) obj;
            if (this.f8087a.equals(backendRequest.getEvents())) {
                if (Arrays.equals(this.b, backendRequest instanceof a ? ((a) backendRequest).b : backendRequest.getExtras())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.google.android.datatransport.runtime.backends.BackendRequest
    public Iterable<EventInternal> getEvents() {
        return this.f8087a;
    }

    @Override // com.google.android.datatransport.runtime.backends.BackendRequest
    @Nullable
    public byte[] getExtras() {
        return this.b;
    }

    public int hashCode() {
        return ((this.f8087a.hashCode() ^ 1000003) * 1000003) ^ Arrays.hashCode(this.b);
    }

    public String toString() {
        return "BackendRequest{events=" + this.f8087a + ", extras=" + Arrays.toString(this.b) + "}";
    }

    public a(Iterable<EventInternal> iterable, @Nullable byte[] bArr) {
        this.f8087a = iterable;
        this.b = bArr;
    }
}
