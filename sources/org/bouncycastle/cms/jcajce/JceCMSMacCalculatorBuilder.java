package org.bouncycastle.cms.jcajce;

import java.io.OutputStream;
import java.security.AlgorithmParameters;
import java.security.Provider;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.jcajce.io.MacOutputStream;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.MacCalculator;
import org.bouncycastle.operator.jcajce.JceGenericKey;
/* loaded from: classes12.dex */
public class JceCMSMacCalculatorBuilder {

    /* renamed from: a  reason: collision with root package name */
    public final ASN1ObjectIdentifier f14583a;
    public final int b;
    public EnvelopedDataHelper c;
    public AlgorithmParameters d;
    public SecureRandom e;

    /* loaded from: classes12.dex */
    public class a implements MacCalculator {

        /* renamed from: a  reason: collision with root package name */
        public SecretKey f14584a;
        public AlgorithmIdentifier b;
        public Mac c;

        public a(JceCMSMacCalculatorBuilder jceCMSMacCalculatorBuilder, ASN1ObjectIdentifier aSN1ObjectIdentifier, int i, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) throws CMSException {
            KeyGenerator createKeyGenerator = jceCMSMacCalculatorBuilder.c.createKeyGenerator(aSN1ObjectIdentifier);
            secureRandom = secureRandom == null ? new SecureRandom() : secureRandom;
            if (i < 0) {
                createKeyGenerator.init(secureRandom);
            } else {
                createKeyGenerator.init(i, secureRandom);
            }
            this.f14584a = createKeyGenerator.generateKey();
            this.b = jceCMSMacCalculatorBuilder.c.l(aSN1ObjectIdentifier, algorithmParameters == null ? jceCMSMacCalculatorBuilder.c.k(aSN1ObjectIdentifier, this.f14584a, secureRandom) : algorithmParameters);
            this.c = jceCMSMacCalculatorBuilder.c.e(this.f14584a, this.b);
        }

        @Override // org.bouncycastle.operator.MacCalculator
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return this.b;
        }

        @Override // org.bouncycastle.operator.MacCalculator
        public GenericKey getKey() {
            return new JceGenericKey(this.b, this.f14584a);
        }

        @Override // org.bouncycastle.operator.MacCalculator
        public byte[] getMac() {
            return this.c.doFinal();
        }

        @Override // org.bouncycastle.operator.MacCalculator
        public OutputStream getOutputStream() {
            return new MacOutputStream(this.c);
        }
    }

    public JceCMSMacCalculatorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this(aSN1ObjectIdentifier, -1);
    }

    public JceCMSMacCalculatorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier, int i) {
        this.c = new EnvelopedDataHelper(new b());
        this.f14583a = aSN1ObjectIdentifier;
        this.b = i;
    }

    public MacCalculator build() throws CMSException {
        return new a(this, this.f14583a, this.b, this.d, this.e);
    }

    public JceCMSMacCalculatorBuilder setAlgorithmParameters(AlgorithmParameters algorithmParameters) {
        this.d = algorithmParameters;
        return this;
    }

    public JceCMSMacCalculatorBuilder setProvider(String str) {
        this.c = new EnvelopedDataHelper(new e(str));
        return this;
    }

    public JceCMSMacCalculatorBuilder setProvider(Provider provider) {
        this.c = new EnvelopedDataHelper(new f(provider));
        return this;
    }

    public JceCMSMacCalculatorBuilder setSecureRandom(SecureRandom secureRandom) {
        this.e = secureRandom;
        return this;
    }
}
