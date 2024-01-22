package org.bouncycastle.est;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.util.Store;
/* loaded from: classes13.dex */
public class EnrollmentResponse {

    /* renamed from: a  reason: collision with root package name */
    public final Store<X509CertificateHolder> f14911a;
    public final long b;
    public final ESTRequest c;
    public final Source d;

    public EnrollmentResponse(Store<X509CertificateHolder> store, long j, ESTRequest eSTRequest, Source source) {
        this.f14911a = store;
        this.b = j;
        this.c = eSTRequest;
        this.d = source;
    }

    public boolean canRetry() {
        return this.b < System.currentTimeMillis();
    }

    public long getNotBefore() {
        return this.b;
    }

    public ESTRequest getRequestToRetry() {
        return this.c;
    }

    public Object getSession() {
        return this.d.getSession();
    }

    public Source getSource() {
        return this.d;
    }

    public Store<X509CertificateHolder> getStore() {
        return this.f14911a;
    }

    public boolean isCompleted() {
        return this.c == null;
    }
}
