package org.bouncycastle.jcajce;

import java.math.BigInteger;
import java.security.cert.CRL;
import java.security.cert.CRLSelector;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.X509CRLSelector;
import java.security.cert.X509Certificate;
import java.util.Collection;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Selector;
/* loaded from: classes13.dex */
public class PKIXCRLStoreSelector<T extends CRL> implements Selector<T> {
    public final CRLSelector h;
    public final boolean i;
    public final boolean j;
    public final BigInteger k;
    public final byte[] l;
    public final boolean m;

    /* loaded from: classes13.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final CRLSelector f14926a;
        public boolean b = false;
        public boolean c = false;
        public BigInteger d = null;
        public byte[] e = null;
        public boolean f = false;

        public Builder(CRLSelector cRLSelector) {
            this.f14926a = (CRLSelector) cRLSelector.clone();
        }

        public PKIXCRLStoreSelector<? extends CRL> build() {
            return new PKIXCRLStoreSelector<>(this);
        }

        public Builder setCompleteCRLEnabled(boolean z) {
            this.c = z;
            return this;
        }

        public Builder setDeltaCRLIndicatorEnabled(boolean z) {
            this.b = z;
            return this;
        }

        public void setIssuingDistributionPoint(byte[] bArr) {
            this.e = Arrays.clone(bArr);
        }

        public void setIssuingDistributionPointEnabled(boolean z) {
            this.f = z;
        }

        public void setMaxBaseCRLNumber(BigInteger bigInteger) {
            this.d = bigInteger;
        }
    }

    /* loaded from: classes13.dex */
    public static class b extends X509CRLSelector {
        public final PKIXCRLStoreSelector h;

        public b(PKIXCRLStoreSelector pKIXCRLStoreSelector) {
            this.h = pKIXCRLStoreSelector;
            if (pKIXCRLStoreSelector.h instanceof X509CRLSelector) {
                X509CRLSelector x509CRLSelector = (X509CRLSelector) pKIXCRLStoreSelector.h;
                setCertificateChecking(x509CRLSelector.getCertificateChecking());
                setDateAndTime(x509CRLSelector.getDateAndTime());
                setIssuers(x509CRLSelector.getIssuers());
                setMinCRLNumber(x509CRLSelector.getMinCRL());
                setMaxCRLNumber(x509CRLSelector.getMaxCRL());
            }
        }

        @Override // java.security.cert.X509CRLSelector, java.security.cert.CRLSelector
        public boolean match(CRL crl) {
            PKIXCRLStoreSelector pKIXCRLStoreSelector = this.h;
            return pKIXCRLStoreSelector == null ? crl != null : pKIXCRLStoreSelector.match(crl);
        }
    }

    public PKIXCRLStoreSelector(Builder builder) {
        this.h = builder.f14926a;
        this.i = builder.b;
        this.j = builder.c;
        this.k = builder.d;
        this.l = builder.e;
        this.m = builder.f;
    }

    public static Collection<? extends CRL> getCRLs(PKIXCRLStoreSelector pKIXCRLStoreSelector, CertStore certStore) throws CertStoreException {
        return certStore.getCRLs(new b(pKIXCRLStoreSelector));
    }

    @Override // org.bouncycastle.util.Selector
    public Object clone() {
        return this;
    }

    public X509Certificate getCertificateChecking() {
        CRLSelector cRLSelector = this.h;
        if (cRLSelector instanceof X509CRLSelector) {
            return ((X509CRLSelector) cRLSelector).getCertificateChecking();
        }
        return null;
    }

    public byte[] getIssuingDistributionPoint() {
        return Arrays.clone(this.l);
    }

    public BigInteger getMaxBaseCRLNumber() {
        return this.k;
    }

    public boolean isCompleteCRLEnabled() {
        return this.j;
    }

    public boolean isDeltaCRLIndicatorEnabled() {
        return this.i;
    }

    public boolean isIssuingDistributionPointEnabled() {
        return this.m;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0067, code lost:
        if (org.bouncycastle.util.Arrays.areEqual(r0, r1) == false) goto L34;
     */
    @Override // org.bouncycastle.util.Selector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean match(java.security.cert.CRL r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof java.security.cert.X509CRL
            if (r0 != 0) goto Lb
        L4:
            java.security.cert.CRLSelector r0 = r4.h
            boolean r5 = r0.match(r5)
            return r5
        Lb:
            r0 = r5
            java.security.cert.X509CRL r0 = (java.security.cert.X509CRL) r0
            r1 = 0
            r2 = 0
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = org.bouncycastle.asn1.x509.Extension.deltaCRLIndicator     // Catch: java.lang.Exception -> L69
            java.lang.String r3 = r3.getId()     // Catch: java.lang.Exception -> L69
            byte[] r3 = r0.getExtensionValue(r3)     // Catch: java.lang.Exception -> L69
            if (r3 == 0) goto L28
            org.bouncycastle.asn1.ASN1OctetString r1 = org.bouncycastle.asn1.ASN1OctetString.getInstance(r3)     // Catch: java.lang.Exception -> L69
            byte[] r1 = r1.getOctets()     // Catch: java.lang.Exception -> L69
            org.bouncycastle.asn1.ASN1Integer r1 = org.bouncycastle.asn1.ASN1Integer.getInstance(r1)     // Catch: java.lang.Exception -> L69
        L28:
            boolean r3 = r4.isDeltaCRLIndicatorEnabled()
            if (r3 == 0) goto L31
            if (r1 != 0) goto L31
            return r2
        L31:
            boolean r3 = r4.isCompleteCRLEnabled()
            if (r3 == 0) goto L3a
            if (r1 == 0) goto L3a
            return r2
        L3a:
            if (r1 == 0) goto L4e
            java.math.BigInteger r3 = r4.k
            if (r3 == 0) goto L4e
            java.math.BigInteger r1 = r1.getPositiveValue()
            java.math.BigInteger r3 = r4.k
            int r1 = r1.compareTo(r3)
            r3 = 1
            if (r1 != r3) goto L4e
            return r2
        L4e:
            boolean r1 = r4.m
            if (r1 == 0) goto L4
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = org.bouncycastle.asn1.x509.Extension.issuingDistributionPoint
            java.lang.String r1 = r1.getId()
            byte[] r0 = r0.getExtensionValue(r1)
            byte[] r1 = r4.l
            if (r1 != 0) goto L63
            if (r0 == 0) goto L4
            return r2
        L63:
            boolean r0 = org.bouncycastle.util.Arrays.areEqual(r0, r1)
            if (r0 != 0) goto L4
        L69:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.PKIXCRLStoreSelector.match(java.security.cert.CRL):boolean");
    }
}
