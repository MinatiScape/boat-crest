package com.google.android.datatransport.runtime;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.TransportContext;
import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes6.dex */
public final class c extends TransportContext {

    /* renamed from: a  reason: collision with root package name */
    public final String f8094a;
    public final byte[] b;
    public final Priority c;

    /* loaded from: classes6.dex */
    public static final class b extends TransportContext.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f8095a;
        public byte[] b;
        public Priority c;

        @Override // com.google.android.datatransport.runtime.TransportContext.Builder
        public TransportContext build() {
            String str = "";
            if (this.f8095a == null) {
                str = " backendName";
            }
            if (this.c == null) {
                str = str + " priority";
            }
            if (str.isEmpty()) {
                return new c(this.f8095a, this.b, this.c);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.google.android.datatransport.runtime.TransportContext.Builder
        public TransportContext.Builder setBackendName(String str) {
            Objects.requireNonNull(str, "Null backendName");
            this.f8095a = str;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.TransportContext.Builder
        public TransportContext.Builder setExtras(@Nullable byte[] bArr) {
            this.b = bArr;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.TransportContext.Builder
        public TransportContext.Builder setPriority(Priority priority) {
            Objects.requireNonNull(priority, "Null priority");
            this.c = priority;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TransportContext) {
            TransportContext transportContext = (TransportContext) obj;
            if (this.f8094a.equals(transportContext.getBackendName())) {
                if (Arrays.equals(this.b, transportContext instanceof c ? ((c) transportContext).b : transportContext.getExtras()) && this.c.equals(transportContext.getPriority())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.google.android.datatransport.runtime.TransportContext
    public String getBackendName() {
        return this.f8094a;
    }

    @Override // com.google.android.datatransport.runtime.TransportContext
    @Nullable
    public byte[] getExtras() {
        return this.b;
    }

    @Override // com.google.android.datatransport.runtime.TransportContext
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Priority getPriority() {
        return this.c;
    }

    public int hashCode() {
        return ((((this.f8094a.hashCode() ^ 1000003) * 1000003) ^ Arrays.hashCode(this.b)) * 1000003) ^ this.c.hashCode();
    }

    public c(String str, @Nullable byte[] bArr, Priority priority) {
        this.f8094a = str;
        this.b = bArr;
        this.c = priority;
    }
}
