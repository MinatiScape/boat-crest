package org.bouncycastle.cms.jcajce;

import java.io.IOException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.cms.KeyTransRecipientInfoGenerator;
import org.bouncycastle.operator.jcajce.JceAsymmetricKeyWrapper;
import org.bouncycastle.operator.jcajce.JceKTSKeyWrapper;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes12.dex */
public class JceKTSKeyTransRecipientInfoGenerator extends KeyTransRecipientInfoGenerator {
    public static final byte[] c = Hex.decode("0c14416e6f6e796d6f75732053656e64657220202020");

    /* loaded from: classes12.dex */
    public static class a extends CertificateEncodingException {
        public final /* synthetic */ IOException val$e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String str, IOException iOException) {
            super(str);
            this.val$e = iOException;
        }

        @Override // java.lang.Throwable
        public Throwable getCause() {
            return this.val$e;
        }
    }

    /* loaded from: classes12.dex */
    public static class b extends IllegalArgumentException {
        public final /* synthetic */ IOException val$e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(String str, IOException iOException) {
            super(str);
            this.val$e = iOException;
        }

        @Override // java.lang.Throwable
        public Throwable getCause() {
            return this.val$e;
        }
    }

    public JceKTSKeyTransRecipientInfoGenerator(X509Certificate x509Certificate, String str, int i) throws CertificateEncodingException {
        this(x509Certificate, new IssuerAndSerialNumber(new JcaX509CertificateHolder(x509Certificate).toASN1Structure()), str, i);
    }

    public JceKTSKeyTransRecipientInfoGenerator(X509Certificate x509Certificate, IssuerAndSerialNumber issuerAndSerialNumber, String str, int i) throws CertificateEncodingException {
        super(issuerAndSerialNumber, new JceKTSKeyWrapper(x509Certificate, str, i, c, a(issuerAndSerialNumber)));
    }

    public JceKTSKeyTransRecipientInfoGenerator(X509Certificate x509Certificate, AlgorithmIdentifier algorithmIdentifier) throws CertificateEncodingException {
        super(new IssuerAndSerialNumber(new JcaX509CertificateHolder(x509Certificate).toASN1Structure()), new JceAsymmetricKeyWrapper(algorithmIdentifier, x509Certificate.getPublicKey()));
    }

    public JceKTSKeyTransRecipientInfoGenerator(byte[] bArr, PublicKey publicKey, String str, int i) {
        super(bArr, new JceKTSKeyWrapper(publicKey, str, i, c, b(bArr)));
    }

    public JceKTSKeyTransRecipientInfoGenerator(byte[] bArr, AlgorithmIdentifier algorithmIdentifier, PublicKey publicKey) {
        super(bArr, new JceAsymmetricKeyWrapper(algorithmIdentifier, publicKey));
    }

    public static byte[] a(IssuerAndSerialNumber issuerAndSerialNumber) throws CertificateEncodingException {
        try {
            return issuerAndSerialNumber.getEncoded(ASN1Encoding.DER);
        } catch (IOException e) {
            throw new a("Cannot process extracted IssuerAndSerialNumber: " + e.getMessage(), e);
        }
    }

    public static byte[] b(byte[] bArr) {
        try {
            return new DEROctetString(bArr).getEncoded();
        } catch (IOException e) {
            throw new b("Cannot process subject key identifier: " + e.getMessage(), e);
        }
    }

    public JceKTSKeyTransRecipientInfoGenerator setProvider(String str) {
        ((JceKTSKeyWrapper) this.wrapper).setProvider(str);
        return this;
    }

    public JceKTSKeyTransRecipientInfoGenerator setProvider(Provider provider) {
        ((JceKTSKeyWrapper) this.wrapper).setProvider(provider);
        return this;
    }
}
