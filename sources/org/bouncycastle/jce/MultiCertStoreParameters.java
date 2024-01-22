package org.bouncycastle.jce;

import java.security.cert.CertStoreParameters;
import java.util.Collection;
/* loaded from: classes13.dex */
public class MultiCertStoreParameters implements CertStoreParameters {
    public Collection h;
    public boolean i;

    public MultiCertStoreParameters(Collection collection) {
        this(collection, true);
    }

    public MultiCertStoreParameters(Collection collection, boolean z) {
        this.h = collection;
        this.i = z;
    }

    @Override // java.security.cert.CertStoreParameters
    public Object clone() {
        return this;
    }

    public Collection getCertStores() {
        return this.h;
    }

    public boolean getSearchAllStores() {
        return this.i;
    }
}
