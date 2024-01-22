package org.bouncycastle.cert.crmf.jcajce;

import java.io.InputStream;
import java.security.Key;
import java.security.PrivateKey;
import java.security.Provider;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.crmf.CRMFException;
import org.bouncycastle.cert.crmf.ValueDecryptorGenerator;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.InputDecryptor;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.operator.jcajce.JceAsymmetricKeyUnwrapper;
/* loaded from: classes12.dex */
public class JceAsymmetricValueDecryptorGenerator implements ValueDecryptorGenerator {

    /* renamed from: a  reason: collision with root package name */
    public PrivateKey f14470a;
    public org.bouncycastle.cert.crmf.jcajce.a b = new org.bouncycastle.cert.crmf.jcajce.a(new DefaultJcaJceHelper());
    public Provider c = null;
    public String d = null;

    /* loaded from: classes12.dex */
    public class a implements InputDecryptor {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ AlgorithmIdentifier f14471a;
        public final /* synthetic */ Cipher b;

        public a(JceAsymmetricValueDecryptorGenerator jceAsymmetricValueDecryptorGenerator, AlgorithmIdentifier algorithmIdentifier, Cipher cipher) {
            this.f14471a = algorithmIdentifier;
            this.b = cipher;
        }

        @Override // org.bouncycastle.operator.InputDecryptor
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return this.f14471a;
        }

        @Override // org.bouncycastle.operator.InputDecryptor
        public InputStream getInputStream(InputStream inputStream) {
            return new CipherInputStream(inputStream, this.b);
        }
    }

    public JceAsymmetricValueDecryptorGenerator(PrivateKey privateKey) {
        this.f14470a = privateKey;
    }

    public final Key a(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] bArr) throws CRMFException {
        try {
            JceAsymmetricKeyUnwrapper jceAsymmetricKeyUnwrapper = new JceAsymmetricKeyUnwrapper(algorithmIdentifier, this.f14470a);
            Provider provider = this.c;
            if (provider != null) {
                jceAsymmetricKeyUnwrapper.setProvider(provider);
            }
            String str = this.d;
            if (str != null) {
                jceAsymmetricKeyUnwrapper.setProvider(str);
            }
            return new SecretKeySpec((byte[]) jceAsymmetricKeyUnwrapper.generateUnwrappedKey(algorithmIdentifier2, bArr).getRepresentation(), algorithmIdentifier2.getAlgorithm().getId());
        } catch (OperatorException e) {
            throw new CRMFException("key invalid in message: " + e.getMessage(), e);
        }
    }

    @Override // org.bouncycastle.cert.crmf.ValueDecryptorGenerator
    public InputDecryptor getValueDecryptor(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] bArr) throws CRMFException {
        return new a(this, algorithmIdentifier2, this.b.d(a(algorithmIdentifier, algorithmIdentifier2, bArr), algorithmIdentifier2));
    }

    public JceAsymmetricValueDecryptorGenerator setProvider(String str) {
        this.b = new org.bouncycastle.cert.crmf.jcajce.a(new NamedJcaJceHelper(str));
        this.c = null;
        this.d = str;
        return this;
    }

    public JceAsymmetricValueDecryptorGenerator setProvider(Provider provider) {
        this.b = new org.bouncycastle.cert.crmf.jcajce.a(new ProviderJcaJceHelper(provider));
        this.c = provider;
        this.d = null;
        return this;
    }
}
