package org.bouncycastle.jce.provider;

import java.security.InvalidAlgorithmParameterException;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertPathValidatorSpi;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.TBSCertificate;
import org.bouncycastle.jcajce.PKIXExtendedBuilderParameters;
import org.bouncycastle.jcajce.PKIXExtendedParameters;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.exception.ExtCertPathValidatorException;
import org.bouncycastle.x509.ExtendedPKIXParameters;
/* loaded from: classes13.dex */
public class PKIXCertPathValidatorSpi extends CertPathValidatorSpi {

    /* renamed from: a  reason: collision with root package name */
    public final JcaJceHelper f15093a = new BCJcaJceHelper();

    public static void a(X509Certificate x509Certificate) throws AnnotatedException {
        try {
            TBSCertificate.getInstance(x509Certificate.getTBSCertificate());
        } catch (IllegalArgumentException e) {
            throw new AnnotatedException(e.getMessage());
        } catch (CertificateEncodingException unused) {
            throw new AnnotatedException("unable to process TBSCertificate");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v4 */
    @Override // java.security.cert.CertPathValidatorSpi
    public CertPathValidatorResult engineValidate(CertPath certPath, CertPathParameters certPathParameters) throws CertPathValidatorException, InvalidAlgorithmParameterException {
        PKIXExtendedParameters pKIXExtendedParameters;
        List<? extends Certificate> list;
        X500Name a2;
        PublicKey cAPublicKey;
        HashSet hashSet;
        PKIXNameConstraintValidator pKIXNameConstraintValidator;
        ArrayList[] arrayListArr;
        HashSet hashSet2;
        PKIXCertPathValidatorSpi pKIXCertPathValidatorSpi = this;
        if (certPathParameters instanceof PKIXParameters) {
            PKIXExtendedParameters.Builder builder = new PKIXExtendedParameters.Builder((PKIXParameters) certPathParameters);
            if (certPathParameters instanceof ExtendedPKIXParameters) {
                ExtendedPKIXParameters extendedPKIXParameters = (ExtendedPKIXParameters) certPathParameters;
                builder.setUseDeltasEnabled(extendedPKIXParameters.isUseDeltasEnabled());
                builder.setValidityModel(extendedPKIXParameters.getValidityModel());
            }
            pKIXExtendedParameters = builder.build();
        } else if (certPathParameters instanceof PKIXExtendedBuilderParameters) {
            pKIXExtendedParameters = ((PKIXExtendedBuilderParameters) certPathParameters).getBaseParameters();
        } else if (!(certPathParameters instanceof PKIXExtendedParameters)) {
            throw new InvalidAlgorithmParameterException("Parameters must be a " + PKIXParameters.class.getName() + " instance.");
        } else {
            pKIXExtendedParameters = (PKIXExtendedParameters) certPathParameters;
        }
        if (pKIXExtendedParameters.getTrustAnchors() == null) {
            throw new InvalidAlgorithmParameterException("trustAnchors is null, this is not allowed for certification path validation.");
        }
        List<? extends Certificate> certificates = certPath.getCertificates();
        int size = certificates.size();
        if (certificates.isEmpty()) {
            throw new CertPathValidatorException("Certification path is empty.", null, certPath, -1);
        }
        Set initialPolicies = pKIXExtendedParameters.getInitialPolicies();
        try {
            TrustAnchor d = b.d((X509Certificate) certificates.get(certificates.size() - 1), pKIXExtendedParameters.getTrustAnchors(), pKIXExtendedParameters.getSigProvider());
            if (d == null) {
                list = certificates;
                try {
                    throw new CertPathValidatorException("Trust anchor for certification path not found.", null, certPath, -1);
                } catch (AnnotatedException e) {
                    e = e;
                    throw new CertPathValidatorException(e.getMessage(), e.getUnderlyingException(), certPath, list.size() - 1);
                }
            }
            a(d.getTrustedCert());
            PKIXExtendedParameters build = new PKIXExtendedParameters.Builder(pKIXExtendedParameters).setTrustAnchor(d).build();
            int i = size + 1;
            ArrayList[] arrayListArr2 = new ArrayList[i];
            for (int i2 = 0; i2 < i; i2++) {
                arrayListArr2[i2] = new ArrayList();
            }
            HashSet hashSet3 = new HashSet();
            hashSet3.add(org.bouncycastle.x509.a.ANY_POLICY);
            arrayListArr2[0].add(new PKIXPolicyNode(new ArrayList(), 0, hashSet3, null, new HashSet(), org.bouncycastle.x509.a.ANY_POLICY, false));
            PKIXNameConstraintValidator pKIXNameConstraintValidator2 = new PKIXNameConstraintValidator();
            HashSet hashSet4 = new HashSet();
            int i3 = build.isExplicitPolicyRequired() ? 0 : i;
            int i4 = build.isAnyPolicyInhibited() ? 0 : i;
            if (build.isPolicyMappingInhibited()) {
                i = 0;
            }
            X509Certificate trustedCert = d.getTrustedCert();
            try {
                if (trustedCert != null) {
                    a2 = g.e(trustedCert);
                    cAPublicKey = trustedCert.getPublicKey();
                } else {
                    a2 = g.a(d);
                    cAPublicKey = d.getCAPublicKey();
                }
                try {
                    AlgorithmIdentifier g = b.g(cAPublicKey);
                    g.getAlgorithm();
                    g.getParameters();
                    if (build.getTargetConstraints() == null || build.getTargetConstraints().match((Certificate) ((X509Certificate) certificates.get(0)))) {
                        List<PKIXCertPathChecker> certPathCheckers = build.getCertPathCheckers();
                        for (PKIXCertPathChecker pKIXCertPathChecker : certPathCheckers) {
                            pKIXCertPathChecker.init(false);
                        }
                        int i5 = size;
                        X509Certificate x509Certificate = null;
                        ?? r5 = i;
                        int i6 = i4;
                        PKIXPolicyNode pKIXPolicyNode = r5;
                        int i7 = i3;
                        int size2 = certificates.size() - 1;
                        int i8 = i7;
                        int i9 = r5;
                        while (size2 >= 0) {
                            int i10 = size - size2;
                            Set set = initialPolicies;
                            X509Certificate x509Certificate2 = (X509Certificate) certificates.get(size2);
                            boolean z = size2 == certificates.size() + (-1);
                            try {
                                a(x509Certificate2);
                                TrustAnchor trustAnchor = d;
                                JcaJceHelper jcaJceHelper = pKIXCertPathValidatorSpi.f15093a;
                                int i11 = i6;
                                List<? extends Certificate> list2 = certificates;
                                int i12 = i8;
                                PKIXExtendedParameters pKIXExtendedParameters2 = build;
                                int i13 = size2;
                                PKIXExtendedParameters pKIXExtendedParameters3 = build;
                                int i14 = i9;
                                PKIXNameConstraintValidator pKIXNameConstraintValidator3 = pKIXNameConstraintValidator2;
                                ArrayList[] arrayListArr3 = arrayListArr2;
                                h.z(certPath, pKIXExtendedParameters2, size2, cAPublicKey, z, a2, trustedCert, jcaJceHelper);
                                h.A(certPath, i13, pKIXNameConstraintValidator3);
                                PKIXPolicyNode C = h.C(certPath, i13, h.B(certPath, i13, hashSet4, pKIXPolicyNode, arrayListArr3, i11));
                                h.D(certPath, i13, C, i12);
                                if (i10 != size) {
                                    if (x509Certificate2 == null || x509Certificate2.getVersion() != 1) {
                                        h.d(certPath, i13);
                                        arrayListArr = arrayListArr3;
                                        PKIXPolicyNode c = h.c(certPath, i13, arrayListArr, C, i14);
                                        h.e(certPath, i13, pKIXNameConstraintValidator3);
                                        int f = h.f(certPath, i13, i12);
                                        int g2 = h.g(certPath, i13, i14);
                                        int h = h.h(certPath, i13, i11);
                                        i8 = h.i(certPath, i13, f);
                                        i14 = h.j(certPath, i13, g2);
                                        int k = h.k(certPath, i13, h);
                                        h.l(certPath, i13);
                                        i5 = h.n(certPath, i13, h.m(certPath, i13, i5));
                                        h.o(certPath, i13);
                                        Set<String> criticalExtensionOIDs = x509Certificate2.getCriticalExtensionOIDs();
                                        if (criticalExtensionOIDs != null) {
                                            hashSet2 = new HashSet(criticalExtensionOIDs);
                                            hashSet2.remove(h.l);
                                            hashSet2.remove(h.f15112a);
                                            hashSet2.remove(h.b);
                                            hashSet2.remove(h.c);
                                            hashSet2.remove(h.d);
                                            hashSet2.remove(h.e);
                                            hashSet2.remove(h.f);
                                            hashSet2.remove(h.g);
                                            hashSet2.remove(h.i);
                                            hashSet2.remove(h.j);
                                        } else {
                                            hashSet2 = new HashSet();
                                        }
                                        h.p(certPath, i13, hashSet2, certPathCheckers);
                                        X500Name e2 = g.e(x509Certificate2);
                                        try {
                                            pKIXNameConstraintValidator = pKIXNameConstraintValidator3;
                                            pKIXCertPathValidatorSpi = this;
                                            try {
                                                PublicKey m = b.m(certPath.getCertificates(), i13, pKIXCertPathValidatorSpi.f15093a);
                                                AlgorithmIdentifier g3 = b.g(m);
                                                g3.getAlgorithm();
                                                g3.getParameters();
                                                pKIXPolicyNode = c;
                                                i6 = k;
                                                a2 = e2;
                                                cAPublicKey = m;
                                                trustedCert = x509Certificate2;
                                                i9 = i14;
                                                size2 = i13 - 1;
                                                x509Certificate = x509Certificate2;
                                                initialPolicies = set;
                                                certificates = list2;
                                                build = pKIXExtendedParameters3;
                                                d = trustAnchor;
                                                PKIXNameConstraintValidator pKIXNameConstraintValidator4 = pKIXNameConstraintValidator;
                                                arrayListArr2 = arrayListArr;
                                                pKIXNameConstraintValidator2 = pKIXNameConstraintValidator4;
                                            } catch (CertPathValidatorException e3) {
                                                e = e3;
                                                throw new CertPathValidatorException("Next working key could not be retrieved.", e, certPath, i13);
                                            }
                                        } catch (CertPathValidatorException e4) {
                                            e = e4;
                                        }
                                    } else if (i10 != 1 || !x509Certificate2.equals(trustAnchor.getTrustedCert())) {
                                        throw new CertPathValidatorException("Version 1 certificates can't be used as CA ones.", null, certPath, i13);
                                    }
                                }
                                pKIXNameConstraintValidator = pKIXNameConstraintValidator3;
                                arrayListArr = arrayListArr3;
                                pKIXCertPathValidatorSpi = this;
                                pKIXPolicyNode = C;
                                i6 = i11;
                                i5 = i5;
                                i8 = i12;
                                i9 = i14;
                                size2 = i13 - 1;
                                x509Certificate = x509Certificate2;
                                initialPolicies = set;
                                certificates = list2;
                                build = pKIXExtendedParameters3;
                                d = trustAnchor;
                                PKIXNameConstraintValidator pKIXNameConstraintValidator42 = pKIXNameConstraintValidator;
                                arrayListArr2 = arrayListArr;
                                pKIXNameConstraintValidator2 = pKIXNameConstraintValidator42;
                            } catch (AnnotatedException e5) {
                                throw new CertPathValidatorException(e5.getMessage(), e5.getUnderlyingException(), certPath, size2);
                            }
                        }
                        PKIXExtendedParameters pKIXExtendedParameters4 = build;
                        ArrayList[] arrayListArr4 = arrayListArr2;
                        TrustAnchor trustAnchor2 = d;
                        Set set2 = initialPolicies;
                        X509Certificate x509Certificate3 = x509Certificate;
                        int i15 = size2;
                        int i16 = i15 + 1;
                        int F = h.F(certPath, i16, h.E(i8, x509Certificate3));
                        Set<String> criticalExtensionOIDs2 = x509Certificate3.getCriticalExtensionOIDs();
                        if (criticalExtensionOIDs2 != null) {
                            hashSet = new HashSet(criticalExtensionOIDs2);
                            hashSet.remove(h.l);
                            hashSet.remove(h.f15112a);
                            hashSet.remove(h.b);
                            hashSet.remove(h.c);
                            hashSet.remove(h.d);
                            hashSet.remove(h.e);
                            hashSet.remove(h.f);
                            hashSet.remove(h.g);
                            hashSet.remove(h.i);
                            hashSet.remove(h.j);
                            hashSet.remove(h.h);
                            hashSet.remove(Extension.extendedKeyUsage.getId());
                        } else {
                            hashSet = new HashSet();
                        }
                        h.G(certPath, i16, certPathCheckers, hashSet);
                        PKIXPolicyNode H = h.H(certPath, pKIXExtendedParameters4, set2, i16, arrayListArr4, pKIXPolicyNode, hashSet4);
                        if (F > 0 || H != null) {
                            return new PKIXCertPathValidatorResult(trustAnchor2, H, x509Certificate3.getPublicKey());
                        }
                        throw new CertPathValidatorException("Path processing failed on policy.", null, certPath, i15);
                    }
                    throw new ExtCertPathValidatorException("Target certificate in certification path does not match targetConstraints.", null, certPath, 0);
                } catch (CertPathValidatorException e6) {
                    throw new ExtCertPathValidatorException("Algorithm identifier of public key of trust anchor could not be read.", e6, certPath, -1);
                }
            } catch (IllegalArgumentException e7) {
                throw new ExtCertPathValidatorException("Subject of trust anchor could not be (re)encoded.", e7, certPath, -1);
            }
        } catch (AnnotatedException e8) {
            e = e8;
            list = certificates;
        }
    }
}
