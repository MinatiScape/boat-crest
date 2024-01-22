package org.bouncycastle.jce.provider;

import java.security.InvalidAlgorithmParameterException;
import java.security.cert.CertPath;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertPathValidatorSpi;
import java.security.cert.PKIXParameters;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Set;
import org.bouncycastle.jcajce.PKIXCertStoreSelector;
import org.bouncycastle.jcajce.PKIXExtendedParameters;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.exception.ExtCertPathValidatorException;
import org.bouncycastle.x509.ExtendedPKIXParameters;
import org.bouncycastle.x509.X509AttributeCertStoreSelector;
import org.bouncycastle.x509.X509AttributeCertificate;
/* loaded from: classes13.dex */
public class PKIXAttrCertPathValidatorSpi extends CertPathValidatorSpi {

    /* renamed from: a  reason: collision with root package name */
    public final JcaJceHelper f15091a = new BCJcaJceHelper();

    @Override // java.security.cert.CertPathValidatorSpi
    public CertPathValidatorResult engineValidate(CertPath certPath, CertPathParameters certPathParameters) throws CertPathValidatorException, InvalidAlgorithmParameterException {
        PKIXExtendedParameters pKIXExtendedParameters;
        boolean z = certPathParameters instanceof ExtendedPKIXParameters;
        if (!z && !(certPathParameters instanceof PKIXExtendedParameters)) {
            throw new InvalidAlgorithmParameterException("Parameters must be a " + ExtendedPKIXParameters.class.getName() + " instance.");
        }
        Set hashSet = new HashSet();
        Set hashSet2 = new HashSet();
        Set hashSet3 = new HashSet();
        HashSet hashSet4 = new HashSet();
        if (certPathParameters instanceof PKIXParameters) {
            PKIXExtendedParameters.Builder builder = new PKIXExtendedParameters.Builder((PKIXParameters) certPathParameters);
            if (z) {
                ExtendedPKIXParameters extendedPKIXParameters = (ExtendedPKIXParameters) certPathParameters;
                builder.setUseDeltasEnabled(extendedPKIXParameters.isUseDeltasEnabled());
                builder.setValidityModel(extendedPKIXParameters.getValidityModel());
                hashSet = extendedPKIXParameters.getAttrCertCheckers();
                hashSet2 = extendedPKIXParameters.getProhibitedACAttributes();
                hashSet3 = extendedPKIXParameters.getNecessaryACAttributes();
            }
            pKIXExtendedParameters = builder.build();
        } else {
            pKIXExtendedParameters = (PKIXExtendedParameters) certPathParameters;
        }
        PKIXExtendedParameters pKIXExtendedParameters2 = pKIXExtendedParameters;
        PKIXCertStoreSelector targetConstraints = pKIXExtendedParameters2.getTargetConstraints();
        if (!(targetConstraints instanceof X509AttributeCertStoreSelector)) {
            throw new InvalidAlgorithmParameterException("TargetConstraints must be an instance of " + X509AttributeCertStoreSelector.class.getName() + " for " + getClass().getName() + " class.");
        }
        X509AttributeCertificate attributeCert = ((X509AttributeCertStoreSelector) targetConstraints).getAttributeCert();
        CertPath d = i.d(attributeCert, pKIXExtendedParameters2);
        CertPathValidatorResult e = i.e(certPath, pKIXExtendedParameters2);
        X509Certificate x509Certificate = (X509Certificate) certPath.getCertificates().get(0);
        i.f(x509Certificate, pKIXExtendedParameters2);
        i.g(x509Certificate, hashSet4);
        i.h(attributeCert, pKIXExtendedParameters2);
        i.i(attributeCert, certPath, d, pKIXExtendedParameters2, hashSet);
        i.a(attributeCert, hashSet2, hashSet3);
        try {
            i.c(attributeCert, pKIXExtendedParameters2, x509Certificate, b.q(pKIXExtendedParameters2, null, -1), certPath.getCertificates(), this.f15091a);
            return e;
        } catch (AnnotatedException e2) {
            throw new ExtCertPathValidatorException("Could not get validity date from attribute certificate.", e2);
        }
    }
}
