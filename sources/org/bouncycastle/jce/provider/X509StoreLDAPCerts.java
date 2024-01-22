package org.bouncycastle.jce.provider;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import org.bouncycastle.jce.X509LDAPCertStoreParameters;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.StoreException;
import org.bouncycastle.x509.X509CertPairStoreSelector;
import org.bouncycastle.x509.X509CertStoreSelector;
import org.bouncycastle.x509.X509CertificatePair;
import org.bouncycastle.x509.X509StoreParameters;
import org.bouncycastle.x509.X509StoreSpi;
import org.bouncycastle.x509.util.LDAPStoreHelper;
/* loaded from: classes13.dex */
public class X509StoreLDAPCerts extends X509StoreSpi {

    /* renamed from: a  reason: collision with root package name */
    public LDAPStoreHelper f15107a;

    public final Collection a(X509CertStoreSelector x509CertStoreSelector) throws StoreException {
        HashSet hashSet = new HashSet();
        X509CertPairStoreSelector x509CertPairStoreSelector = new X509CertPairStoreSelector();
        x509CertPairStoreSelector.setForwardSelector(x509CertStoreSelector);
        x509CertPairStoreSelector.setReverseSelector(new X509CertStoreSelector());
        HashSet<X509CertificatePair> hashSet2 = new HashSet(this.f15107a.getCrossCertificatePairs(x509CertPairStoreSelector));
        HashSet hashSet3 = new HashSet();
        HashSet hashSet4 = new HashSet();
        for (X509CertificatePair x509CertificatePair : hashSet2) {
            if (x509CertificatePair.getForward() != null) {
                hashSet3.add(x509CertificatePair.getForward());
            }
            if (x509CertificatePair.getReverse() != null) {
                hashSet4.add(x509CertificatePair.getReverse());
            }
        }
        hashSet.addAll(hashSet3);
        hashSet.addAll(hashSet4);
        return hashSet;
    }

    @Override // org.bouncycastle.x509.X509StoreSpi
    public Collection engineGetMatches(Selector selector) throws StoreException {
        Collection userCertificates;
        if (selector instanceof X509CertStoreSelector) {
            X509CertStoreSelector x509CertStoreSelector = (X509CertStoreSelector) selector;
            HashSet hashSet = new HashSet();
            if (x509CertStoreSelector.getBasicConstraints() <= 0) {
                if (x509CertStoreSelector.getBasicConstraints() == -2) {
                    userCertificates = this.f15107a.getUserCertificates(x509CertStoreSelector);
                    hashSet.addAll(userCertificates);
                    return hashSet;
                }
                hashSet.addAll(this.f15107a.getUserCertificates(x509CertStoreSelector));
            }
            hashSet.addAll(this.f15107a.getCACertificates(x509CertStoreSelector));
            userCertificates = a(x509CertStoreSelector);
            hashSet.addAll(userCertificates);
            return hashSet;
        }
        return Collections.EMPTY_SET;
    }

    @Override // org.bouncycastle.x509.X509StoreSpi
    public void engineInit(X509StoreParameters x509StoreParameters) {
        if (x509StoreParameters instanceof X509LDAPCertStoreParameters) {
            this.f15107a = new LDAPStoreHelper((X509LDAPCertStoreParameters) x509StoreParameters);
            return;
        }
        throw new IllegalArgumentException("Initialization parameters must be an instance of " + X509LDAPCertStoreParameters.class.getName() + ".");
    }
}
