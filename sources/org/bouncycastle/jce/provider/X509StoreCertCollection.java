package org.bouncycastle.jce.provider;

import java.util.Collection;
import org.bouncycastle.util.CollectionStore;
import org.bouncycastle.util.Selector;
import org.bouncycastle.x509.X509CollectionStoreParameters;
import org.bouncycastle.x509.X509StoreParameters;
import org.bouncycastle.x509.X509StoreSpi;
/* loaded from: classes13.dex */
public class X509StoreCertCollection extends X509StoreSpi {

    /* renamed from: a  reason: collision with root package name */
    public CollectionStore f15102a;

    @Override // org.bouncycastle.x509.X509StoreSpi
    public Collection engineGetMatches(Selector selector) {
        return this.f15102a.getMatches(selector);
    }

    @Override // org.bouncycastle.x509.X509StoreSpi
    public void engineInit(X509StoreParameters x509StoreParameters) {
        if (!(x509StoreParameters instanceof X509CollectionStoreParameters)) {
            throw new IllegalArgumentException(x509StoreParameters.toString());
        }
        this.f15102a = new CollectionStore(((X509CollectionStoreParameters) x509StoreParameters).getCollection());
    }
}
