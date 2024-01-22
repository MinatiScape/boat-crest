package org.bouncycastle.x509;

import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.CRL;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLSelector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Selector;
import org.bouncycastle.x509.extension.X509ExtensionUtil;
/* loaded from: classes13.dex */
public class X509CRLStoreSelector extends X509CRLSelector implements Selector {
    public boolean h = false;
    public boolean i = false;
    public BigInteger j = null;
    public byte[] k = null;
    public boolean l = false;
    public X509AttributeCertificate m;

    public static X509CRLStoreSelector getInstance(X509CRLSelector x509CRLSelector) {
        if (x509CRLSelector != null) {
            X509CRLStoreSelector x509CRLStoreSelector = new X509CRLStoreSelector();
            x509CRLStoreSelector.setCertificateChecking(x509CRLSelector.getCertificateChecking());
            x509CRLStoreSelector.setDateAndTime(x509CRLSelector.getDateAndTime());
            try {
                x509CRLStoreSelector.setIssuerNames(x509CRLSelector.getIssuerNames());
                x509CRLStoreSelector.setIssuers(x509CRLSelector.getIssuers());
                x509CRLStoreSelector.setMaxCRLNumber(x509CRLSelector.getMaxCRL());
                x509CRLStoreSelector.setMinCRLNumber(x509CRLSelector.getMinCRL());
                return x509CRLStoreSelector;
            } catch (IOException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }
        throw new IllegalArgumentException("cannot create from null selector");
    }

    @Override // java.security.cert.X509CRLSelector, java.security.cert.CRLSelector, org.bouncycastle.util.Selector
    public Object clone() {
        X509CRLStoreSelector x509CRLStoreSelector = getInstance(this);
        x509CRLStoreSelector.h = this.h;
        x509CRLStoreSelector.i = this.i;
        x509CRLStoreSelector.j = this.j;
        x509CRLStoreSelector.m = this.m;
        x509CRLStoreSelector.l = this.l;
        x509CRLStoreSelector.k = Arrays.clone(this.k);
        return x509CRLStoreSelector;
    }

    public X509AttributeCertificate getAttrCertificateChecking() {
        return this.m;
    }

    public byte[] getIssuingDistributionPoint() {
        return Arrays.clone(this.k);
    }

    public BigInteger getMaxBaseCRLNumber() {
        return this.j;
    }

    public boolean isCompleteCRLEnabled() {
        return this.i;
    }

    public boolean isDeltaCRLIndicatorEnabled() {
        return this.h;
    }

    public boolean isIssuingDistributionPointEnabled() {
        return this.l;
    }

    @Override // org.bouncycastle.util.Selector
    public boolean match(Object obj) {
        if (obj instanceof X509CRL) {
            X509CRL x509crl = (X509CRL) obj;
            try {
                byte[] extensionValue = x509crl.getExtensionValue(X509Extensions.DeltaCRLIndicator.getId());
                ASN1Integer aSN1Integer = extensionValue != null ? ASN1Integer.getInstance(X509ExtensionUtil.fromExtensionValue(extensionValue)) : null;
                if (isDeltaCRLIndicatorEnabled() && aSN1Integer == null) {
                    return false;
                }
                if (!isCompleteCRLEnabled() || aSN1Integer == null) {
                    if (aSN1Integer == null || this.j == null || aSN1Integer.getPositiveValue().compareTo(this.j) != 1) {
                        if (this.l) {
                            byte[] extensionValue2 = x509crl.getExtensionValue(X509Extensions.IssuingDistributionPoint.getId());
                            byte[] bArr = this.k;
                            if (bArr == null) {
                                if (extensionValue2 != null) {
                                    return false;
                                }
                            } else if (!Arrays.areEqual(extensionValue2, bArr)) {
                                return false;
                            }
                        }
                        return super.match((CRL) x509crl);
                    }
                    return false;
                }
                return false;
            } catch (Exception unused) {
                return false;
            }
        }
        return false;
    }

    @Override // java.security.cert.X509CRLSelector, java.security.cert.CRLSelector
    public boolean match(CRL crl) {
        return match((Object) crl);
    }

    public void setAttrCertificateChecking(X509AttributeCertificate x509AttributeCertificate) {
        this.m = x509AttributeCertificate;
    }

    public void setCompleteCRLEnabled(boolean z) {
        this.i = z;
    }

    public void setDeltaCRLIndicatorEnabled(boolean z) {
        this.h = z;
    }

    public void setIssuingDistributionPoint(byte[] bArr) {
        this.k = Arrays.clone(bArr);
    }

    public void setIssuingDistributionPointEnabled(boolean z) {
        this.l = z;
    }

    public void setMaxBaseCRLNumber(BigInteger bigInteger) {
        this.j = bigInteger;
    }
}
