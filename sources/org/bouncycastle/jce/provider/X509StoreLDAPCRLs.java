package org.bouncycastle.jce.provider;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import org.bouncycastle.jce.X509LDAPCertStoreParameters;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.StoreException;
import org.bouncycastle.x509.X509CRLStoreSelector;
import org.bouncycastle.x509.X509StoreParameters;
import org.bouncycastle.x509.X509StoreSpi;
import org.bouncycastle.x509.util.LDAPStoreHelper;
/* loaded from: classes13.dex */
public class X509StoreLDAPCRLs extends X509StoreSpi {

    /* renamed from: a  reason: collision with root package name */
    public LDAPStoreHelper f15105a;

    @Override // org.bouncycastle.x509.X509StoreSpi
    public Collection engineGetMatches(Selector selector) throws StoreException {
        Collection certificateRevocationLists;
        if (selector instanceof X509CRLStoreSelector) {
            X509CRLStoreSelector x509CRLStoreSelector = (X509CRLStoreSelector) selector;
            HashSet hashSet = new HashSet();
            if (x509CRLStoreSelector.isDeltaCRLIndicatorEnabled()) {
                certificateRevocationLists = this.f15105a.getDeltaCertificateRevocationLists(x509CRLStoreSelector);
            } else {
                hashSet.addAll(this.f15105a.getDeltaCertificateRevocationLists(x509CRLStoreSelector));
                hashSet.addAll(this.f15105a.getAttributeAuthorityRevocationLists(x509CRLStoreSelector));
                hashSet.addAll(this.f15105a.getAttributeCertificateRevocationLists(x509CRLStoreSelector));
                hashSet.addAll(this.f15105a.getAuthorityRevocationLists(x509CRLStoreSelector));
                certificateRevocationLists = this.f15105a.getCertificateRevocationLists(x509CRLStoreSelector);
            }
            hashSet.addAll(certificateRevocationLists);
            return hashSet;
        }
        return Collections.EMPTY_SET;
    }

    @Override // org.bouncycastle.x509.X509StoreSpi
    public void engineInit(X509StoreParameters x509StoreParameters) {
        if (x509StoreParameters instanceof X509LDAPCertStoreParameters) {
            this.f15105a = new LDAPStoreHelper((X509LDAPCertStoreParameters) x509StoreParameters);
            return;
        }
        throw new IllegalArgumentException("Initialization parameters must be an instance of " + X509LDAPCertStoreParameters.class.getName() + ".");
    }
}
