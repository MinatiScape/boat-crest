package org.bouncycastle.est.jcajce;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import org.bouncycastle.est.LimitedSource;
import org.bouncycastle.est.Source;
import org.bouncycastle.est.TLSUniqueProvider;
/* loaded from: classes13.dex */
public class d implements Source<SSLSession>, TLSUniqueProvider, LimitedSource {

    /* renamed from: a  reason: collision with root package name */
    public final SSLSocket f14921a;
    public final ChannelBindingProvider b;
    public final Long c;

    public d(SSLSocket sSLSocket, ChannelBindingProvider channelBindingProvider, Long l) {
        this.f14921a = sSLSocket;
        this.b = channelBindingProvider;
        this.c = l;
    }

    @Override // org.bouncycastle.est.Source
    /* renamed from: a */
    public SSLSession getSession() {
        return this.f14921a.getSession();
    }

    @Override // org.bouncycastle.est.Source
    public void close() throws IOException {
        this.f14921a.close();
    }

    @Override // org.bouncycastle.est.LimitedSource
    public Long getAbsoluteReadLimit() {
        return this.c;
    }

    @Override // org.bouncycastle.est.Source
    public InputStream getInputStream() throws IOException {
        return this.f14921a.getInputStream();
    }

    @Override // org.bouncycastle.est.Source
    public OutputStream getOutputStream() throws IOException {
        return this.f14921a.getOutputStream();
    }

    @Override // org.bouncycastle.est.TLSUniqueProvider
    public byte[] getTLSUnique() {
        if (isTLSUniqueAvailable()) {
            return this.b.getChannelBinding(this.f14921a, "tls-unique");
        }
        throw new IllegalStateException("No binding provider.");
    }

    @Override // org.bouncycastle.est.TLSUniqueProvider
    public boolean isTLSUniqueAvailable() {
        return this.b.canAccessChannelBinding(this.f14921a);
    }
}
