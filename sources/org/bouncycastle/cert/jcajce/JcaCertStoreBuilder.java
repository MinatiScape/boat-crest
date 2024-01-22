package org.bouncycastle.cert.jcajce;

import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.cert.CRLException;
import java.security.cert.CertStore;
import java.security.cert.CertificateException;
import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.util.Store;
/* loaded from: classes12.dex */
public class JcaCertStoreBuilder {
    public Object c;

    /* renamed from: a  reason: collision with root package name */
    public List f14485a = new ArrayList();
    public List b = new ArrayList();
    public JcaX509CertificateConverter d = new JcaX509CertificateConverter();
    public JcaX509CRLConverter e = new JcaX509CRLConverter();
    public String f = "Collection";

    public final CollectionCertStoreParameters a(JcaX509CertificateConverter jcaX509CertificateConverter, JcaX509CRLConverter jcaX509CRLConverter) throws CertificateException, CRLException {
        ArrayList arrayList = new ArrayList(this.f14485a.size() + this.b.size());
        for (X509CertificateHolder x509CertificateHolder : this.f14485a) {
            arrayList.add(jcaX509CertificateConverter.getCertificate(x509CertificateHolder));
        }
        for (X509CRLHolder x509CRLHolder : this.b) {
            arrayList.add(jcaX509CRLConverter.getCRL(x509CRLHolder));
        }
        return new CollectionCertStoreParameters(arrayList);
    }

    public JcaCertStoreBuilder addCRL(X509CRLHolder x509CRLHolder) {
        this.b.add(x509CRLHolder);
        return this;
    }

    public JcaCertStoreBuilder addCRLs(Store store) {
        this.b.addAll(store.getMatches(null));
        return this;
    }

    public JcaCertStoreBuilder addCertificate(X509CertificateHolder x509CertificateHolder) {
        this.f14485a.add(x509CertificateHolder);
        return this;
    }

    public JcaCertStoreBuilder addCertificates(Store store) {
        this.f14485a.addAll(store.getMatches(null));
        return this;
    }

    public CertStore build() throws GeneralSecurityException {
        CollectionCertStoreParameters a2 = a(this.d, this.e);
        Object obj = this.c;
        return obj instanceof String ? CertStore.getInstance(this.f, a2, (String) obj) : obj instanceof Provider ? CertStore.getInstance(this.f, a2, (Provider) obj) : CertStore.getInstance(this.f, a2);
    }

    public JcaCertStoreBuilder setProvider(String str) {
        this.d.setProvider(str);
        this.e.setProvider(str);
        this.c = str;
        return this;
    }

    public JcaCertStoreBuilder setProvider(Provider provider) {
        this.d.setProvider(provider);
        this.e.setProvider(provider);
        this.c = provider;
        return this;
    }

    public JcaCertStoreBuilder setType(String str) {
        this.f = str;
        return this;
    }
}
