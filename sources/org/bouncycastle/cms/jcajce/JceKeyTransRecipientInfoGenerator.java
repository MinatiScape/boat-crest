package org.bouncycastle.cms.jcajce;

import java.security.Provider;
import java.security.PublicKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.cms.KeyTransRecipientInfoGenerator;
import org.bouncycastle.operator.jcajce.JceAsymmetricKeyWrapper;
/* loaded from: classes12.dex */
public class JceKeyTransRecipientInfoGenerator extends KeyTransRecipientInfoGenerator {
    public JceKeyTransRecipientInfoGenerator(X509Certificate x509Certificate) throws CertificateEncodingException {
        super(new IssuerAndSerialNumber(new JcaX509CertificateHolder(x509Certificate).toASN1Structure()), new JceAsymmetricKeyWrapper(x509Certificate));
    }

    public JceKeyTransRecipientInfoGenerator(X509Certificate x509Certificate, AlgorithmIdentifier algorithmIdentifier) throws CertificateEncodingException {
        super(new IssuerAndSerialNumber(new JcaX509CertificateHolder(x509Certificate).toASN1Structure()), new JceAsymmetricKeyWrapper(algorithmIdentifier, x509Certificate.getPublicKey()));
    }

    public JceKeyTransRecipientInfoGenerator(byte[] bArr, PublicKey publicKey) {
        super(bArr, new JceAsymmetricKeyWrapper(publicKey));
    }

    public JceKeyTransRecipientInfoGenerator(byte[] bArr, AlgorithmIdentifier algorithmIdentifier, PublicKey publicKey) {
        super(bArr, new JceAsymmetricKeyWrapper(algorithmIdentifier, publicKey));
    }

    public JceKeyTransRecipientInfoGenerator setAlgorithmMapping(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        ((JceAsymmetricKeyWrapper) this.wrapper).setAlgorithmMapping(aSN1ObjectIdentifier, str);
        return this;
    }

    public JceKeyTransRecipientInfoGenerator setProvider(String str) {
        ((JceAsymmetricKeyWrapper) this.wrapper).setProvider(str);
        return this;
    }

    public JceKeyTransRecipientInfoGenerator setProvider(Provider provider) {
        ((JceAsymmetricKeyWrapper) this.wrapper).setProvider(provider);
        return this;
    }
}
