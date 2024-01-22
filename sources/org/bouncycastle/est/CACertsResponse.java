package org.bouncycastle.est;

import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.util.Store;
/* loaded from: classes13.dex */
public class CACertsResponse {

    /* renamed from: a  reason: collision with root package name */
    public final Store<X509CertificateHolder> f14904a;
    public Store<X509CRLHolder> b;
    public final ESTRequest c;
    public final Source d;
    public final boolean e;

    public CACertsResponse(Store<X509CertificateHolder> store, Store<X509CRLHolder> store2, ESTRequest eSTRequest, Source source, boolean z) {
        this.f14904a = store;
        this.c = eSTRequest;
        this.d = source;
        this.e = z;
        this.b = store2;
    }

    public Store<X509CertificateHolder> getCertificateStore() {
        Store<X509CertificateHolder> store = this.f14904a;
        if (store != null) {
            return store;
        }
        throw new IllegalStateException("Response has no certificates.");
    }

    public Store<X509CRLHolder> getCrlStore() {
        Store<X509CRLHolder> store = this.b;
        if (store != null) {
            return store;
        }
        throw new IllegalStateException("Response has no CRLs.");
    }

    public ESTRequest getRequestToRetry() {
        return this.c;
    }

    public Object getSession() {
        return this.d.getSession();
    }

    public boolean hasCRLs() {
        return this.b != null;
    }

    public boolean hasCertificates() {
        return this.f14904a != null;
    }

    public boolean isTrusted() {
        return this.e;
    }
}
