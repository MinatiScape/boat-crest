package org.bouncycastle.x509;

import org.bouncycastle.util.Selector;
/* loaded from: classes13.dex */
public class X509CertPairStoreSelector implements Selector {
    public X509CertStoreSelector h;
    public X509CertStoreSelector i;
    public X509CertificatePair j;

    @Override // org.bouncycastle.util.Selector
    public Object clone() {
        X509CertPairStoreSelector x509CertPairStoreSelector = new X509CertPairStoreSelector();
        x509CertPairStoreSelector.j = this.j;
        X509CertStoreSelector x509CertStoreSelector = this.h;
        if (x509CertStoreSelector != null) {
            x509CertPairStoreSelector.setForwardSelector((X509CertStoreSelector) x509CertStoreSelector.clone());
        }
        X509CertStoreSelector x509CertStoreSelector2 = this.i;
        if (x509CertStoreSelector2 != null) {
            x509CertPairStoreSelector.setReverseSelector((X509CertStoreSelector) x509CertStoreSelector2.clone());
        }
        return x509CertPairStoreSelector;
    }

    public X509CertificatePair getCertPair() {
        return this.j;
    }

    public X509CertStoreSelector getForwardSelector() {
        return this.h;
    }

    public X509CertStoreSelector getReverseSelector() {
        return this.i;
    }

    @Override // org.bouncycastle.util.Selector
    public boolean match(Object obj) {
        try {
            if (obj instanceof X509CertificatePair) {
                X509CertificatePair x509CertificatePair = (X509CertificatePair) obj;
                X509CertStoreSelector x509CertStoreSelector = this.h;
                if (x509CertStoreSelector == null || x509CertStoreSelector.match((Object) x509CertificatePair.getForward())) {
                    X509CertStoreSelector x509CertStoreSelector2 = this.i;
                    if (x509CertStoreSelector2 == null || x509CertStoreSelector2.match((Object) x509CertificatePair.getReverse())) {
                        X509CertificatePair x509CertificatePair2 = this.j;
                        if (x509CertificatePair2 != null) {
                            return x509CertificatePair2.equals(obj);
                        }
                        return true;
                    }
                    return false;
                }
                return false;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public void setCertPair(X509CertificatePair x509CertificatePair) {
        this.j = x509CertificatePair;
    }

    public void setForwardSelector(X509CertStoreSelector x509CertStoreSelector) {
        this.h = x509CertStoreSelector;
    }

    public void setReverseSelector(X509CertStoreSelector x509CertStoreSelector) {
        this.i = x509CertStoreSelector;
    }
}
