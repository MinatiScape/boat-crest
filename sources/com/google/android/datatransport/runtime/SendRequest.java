package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.runtime.b;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class SendRequest {

    @AutoValue.Builder
    /* loaded from: classes6.dex */
    public static abstract class Builder {
        public abstract Builder a(Encoding encoding);

        public abstract Builder b(Event<?> event);

        public abstract SendRequest build();

        public abstract Builder c(Transformer<?, byte[]> transformer);

        public <T> Builder setEvent(Event<T> event, Encoding encoding, Transformer<T, byte[]> transformer) {
            b(event);
            a(encoding);
            c(transformer);
            return this;
        }

        public abstract Builder setTransportContext(TransportContext transportContext);

        public abstract Builder setTransportName(String str);
    }

    public static Builder a() {
        return new b.C0369b();
    }

    public abstract Encoding b();

    public abstract Event<?> c();

    public byte[] d() {
        return e().apply(c().getPayload());
    }

    public abstract Transformer<?, byte[]> e();

    public abstract TransportContext f();

    public abstract String g();
}
