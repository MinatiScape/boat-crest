package org.bouncycastle.jce.provider;

import java.util.Collection;
import org.bouncycastle.util.CollectionStore;
import org.bouncycastle.util.Selector;
import org.bouncycastle.x509.X509CollectionStoreParameters;
import org.bouncycastle.x509.X509StoreParameters;
import org.bouncycastle.x509.X509StoreSpi;
/* loaded from: classes13.dex */
public class X509StoreCertPairCollection extends X509StoreSpi {

    /* renamed from: a  reason: collision with root package name */
    public CollectionStore f15103a;

    @Override // org.bouncycastle.x509.X509StoreSpi
    public Collection engineGetMatches(Selector selector) {
        return this.f15103a.getMatches(selector);
    }

    @Override // org.bouncycastle.x509.X509StoreSpi
    public void engineInit(X509StoreParameters x509StoreParameters) {
        if (x509StoreParameters instanceof X509CollectionStoreParameters) {
            this.f15103a = new CollectionStore(((X509CollectionStoreParameters) x509StoreParameters).getCollection());
            return;
        }
        throw new IllegalArgumentException("Initialization parameters must be an instance of " + X509CollectionStoreParameters.class.getName() + ".");
    }
}
