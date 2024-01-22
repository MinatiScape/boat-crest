package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.runtime.SendRequest;
import java.util.Objects;
/* loaded from: classes6.dex */
public final class b extends SendRequest {

    /* renamed from: a  reason: collision with root package name */
    public final TransportContext f8083a;
    public final String b;
    public final Event<?> c;
    public final Transformer<?, byte[]> d;
    public final Encoding e;

    /* renamed from: com.google.android.datatransport.runtime.b$b  reason: collision with other inner class name */
    /* loaded from: classes6.dex */
    public static final class C0369b extends SendRequest.Builder {

        /* renamed from: a  reason: collision with root package name */
        public TransportContext f8084a;
        public String b;
        public Event<?> c;
        public Transformer<?, byte[]> d;
        public Encoding e;

        @Override // com.google.android.datatransport.runtime.SendRequest.Builder
        public SendRequest.Builder a(Encoding encoding) {
            Objects.requireNonNull(encoding, "Null encoding");
            this.e = encoding;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.SendRequest.Builder
        public SendRequest.Builder b(Event<?> event) {
            Objects.requireNonNull(event, "Null event");
            this.c = event;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.SendRequest.Builder
        public SendRequest build() {
            String str = "";
            if (this.f8084a == null) {
                str = " transportContext";
            }
            if (this.b == null) {
                str = str + " transportName";
            }
            if (this.c == null) {
                str = str + " event";
            }
            if (this.d == null) {
                str = str + " transformer";
            }
            if (this.e == null) {
                str = str + " encoding";
            }
            if (str.isEmpty()) {
                return new b(this.f8084a, this.b, this.c, this.d, this.e);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.google.android.datatransport.runtime.SendRequest.Builder
        public SendRequest.Builder c(Transformer<?, byte[]> transformer) {
            Objects.requireNonNull(transformer, "Null transformer");
            this.d = transformer;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.SendRequest.Builder
        public SendRequest.Builder setTransportContext(TransportContext transportContext) {
            Objects.requireNonNull(transportContext, "Null transportContext");
            this.f8084a = transportContext;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.SendRequest.Builder
        public SendRequest.Builder setTransportName(String str) {
            Objects.requireNonNull(str, "Null transportName");
            this.b = str;
            return this;
        }
    }

    @Override // com.google.android.datatransport.runtime.SendRequest
    public Encoding b() {
        return this.e;
    }

    @Override // com.google.android.datatransport.runtime.SendRequest
    public Event<?> c() {
        return this.c;
    }

    @Override // com.google.android.datatransport.runtime.SendRequest
    public Transformer<?, byte[]> e() {
        return this.d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SendRequest) {
            SendRequest sendRequest = (SendRequest) obj;
            return this.f8083a.equals(sendRequest.f()) && this.b.equals(sendRequest.g()) && this.c.equals(sendRequest.c()) && this.d.equals(sendRequest.e()) && this.e.equals(sendRequest.b());
        }
        return false;
    }

    @Override // com.google.android.datatransport.runtime.SendRequest
    public TransportContext f() {
        return this.f8083a;
    }

    @Override // com.google.android.datatransport.runtime.SendRequest
    public String g() {
        return this.b;
    }

    public int hashCode() {
        return ((((((((this.f8083a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode()) * 1000003) ^ this.e.hashCode();
    }

    public String toString() {
        return "SendRequest{transportContext=" + this.f8083a + ", transportName=" + this.b + ", event=" + this.c + ", transformer=" + this.d + ", encoding=" + this.e + "}";
    }

    public b(TransportContext transportContext, String str, Event<?> event, Transformer<?, byte[]> transformer, Encoding encoding) {
        this.f8083a = transportContext;
        this.b = str;
        this.c = event;
        this.d = transformer;
        this.e = encoding;
    }
}
