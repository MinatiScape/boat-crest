package org.bouncycastle.jce.provider;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import org.bouncycastle.jce.X509LDAPCertStoreParameters;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.StoreException;
import org.bouncycastle.x509.X509AttributeCertStoreSelector;
import org.bouncycastle.x509.X509StoreParameters;
import org.bouncycastle.x509.X509StoreSpi;
import org.bouncycastle.x509.util.LDAPStoreHelper;
/* loaded from: classes13.dex */
public class X509StoreLDAPAttrCerts extends X509StoreSpi {

    /* renamed from: a  reason: collision with root package name */
    public LDAPStoreHelper f15104a;

    @Override // org.bouncycastle.x509.X509StoreSpi
    public Collection engineGetMatches(Selector selector) throws StoreException {
        if (selector instanceof X509AttributeCertStoreSelector) {
            X509AttributeCertStoreSelector x509AttributeCertStoreSelector = (X509AttributeCertStoreSelector) selector;
            HashSet hashSet = new HashSet();
            hashSet.addAll(this.f15104a.getAACertificates(x509AttributeCertStoreSelector));
            hashSet.addAll(this.f15104a.getAttributeCertificateAttributes(x509AttributeCertStoreSelector));
            hashSet.addAll(this.f15104a.getAttributeDescriptorCertificates(x509AttributeCertStoreSelector));
            return hashSet;
        }
        return Collections.EMPTY_SET;
    }

    @Override // org.bouncycastle.x509.X509StoreSpi
    public void engineInit(X509StoreParameters x509StoreParameters) {
        if (x509StoreParameters instanceof X509LDAPCertStoreParameters) {
            this.f15104a = new LDAPStoreHelper((X509LDAPCertStoreParameters) x509StoreParameters);
            return;
        }
        throw new IllegalArgumentException("Initialization parameters must be an instance of " + X509LDAPCertStoreParameters.class.getName() + ".");
    }
}
