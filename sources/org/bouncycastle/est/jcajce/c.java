package org.bouncycastle.est.jcajce;

import java.util.Set;
import org.bouncycastle.est.ESTClient;
import org.bouncycastle.est.ESTClientProvider;
import org.bouncycastle.est.ESTException;
/* loaded from: classes13.dex */
public class c implements ESTClientProvider {

    /* renamed from: a  reason: collision with root package name */
    public final JsseHostnameAuthorizer f14920a;
    public final SSLSocketFactoryCreator b;
    public final int c;
    public final ChannelBindingProvider d;
    public final Set<String> e;
    public final Long f;
    public final boolean g;

    public c(JsseHostnameAuthorizer jsseHostnameAuthorizer, SSLSocketFactoryCreator sSLSocketFactoryCreator, int i, ChannelBindingProvider channelBindingProvider, Set<String> set, Long l, boolean z) {
        this.f14920a = jsseHostnameAuthorizer;
        this.b = sSLSocketFactoryCreator;
        this.c = i;
        this.d = channelBindingProvider;
        this.e = set;
        this.f = l;
        this.g = z;
    }

    @Override // org.bouncycastle.est.ESTClientProvider
    public boolean isTrusted() {
        return this.b.isTrusted();
    }

    @Override // org.bouncycastle.est.ESTClientProvider
    public ESTClient makeClient() throws ESTException {
        try {
            return new a(new b(this.b.createFactory(), this.f14920a, this.c, this.d, this.e, this.f, this.g));
        } catch (Exception e) {
            throw new ESTException(e.getMessage(), e.getCause());
        }
    }
}
