package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportFactory;
import java.util.Set;
/* loaded from: classes6.dex */
public final class g implements TransportFactory {

    /* renamed from: a  reason: collision with root package name */
    public final Set<Encoding> f8108a;
    public final TransportContext b;
    public final j c;

    public g(Set<Encoding> set, TransportContext transportContext, j jVar) {
        this.f8108a = set;
        this.b = transportContext;
        this.c = jVar;
    }

    @Override // com.google.android.datatransport.TransportFactory
    public <T> Transport<T> getTransport(String str, Class<T> cls, Transformer<T, byte[]> transformer) {
        return getTransport(str, cls, Encoding.of("proto"), transformer);
    }

    @Override // com.google.android.datatransport.TransportFactory
    public <T> Transport<T> getTransport(String str, Class<T> cls, Encoding encoding, Transformer<T, byte[]> transformer) {
        if (this.f8108a.contains(encoding)) {
            return new i(this.b, str, encoding, transformer, this.c);
        }
        throw new IllegalArgumentException(String.format("%s is not supported byt this factory. Supported encodings are: %s.", encoding, this.f8108a));
    }
}
