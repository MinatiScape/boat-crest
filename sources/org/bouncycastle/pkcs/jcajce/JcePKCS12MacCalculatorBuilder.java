package org.bouncycastle.pkcs.jcajce;

import java.io.OutputStream;
import java.security.Provider;
import java.security.SecureRandom;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.PBEParameterSpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCS12PBEParams;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.PKCS12Key;
import org.bouncycastle.jcajce.io.MacOutputStream;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.MacCalculator;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.pkcs.PKCS12MacCalculatorBuilder;
/* loaded from: classes13.dex */
public class JcePKCS12MacCalculatorBuilder implements PKCS12MacCalculatorBuilder {

    /* renamed from: a  reason: collision with root package name */
    public JcaJceHelper f15273a;
    public ASN1ObjectIdentifier b;
    public SecureRandom c;
    public int d;
    public int e;

    /* loaded from: classes13.dex */
    public class a implements MacCalculator {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ byte[] f15274a;
        public final /* synthetic */ Mac b;
        public final /* synthetic */ SecretKey c;

        public a(byte[] bArr, Mac mac, SecretKey secretKey) {
            this.f15274a = bArr;
            this.b = mac;
            this.c = secretKey;
        }

        @Override // org.bouncycastle.operator.MacCalculator
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return new AlgorithmIdentifier(JcePKCS12MacCalculatorBuilder.this.b, new PKCS12PBEParams(this.f15274a, JcePKCS12MacCalculatorBuilder.this.e));
        }

        @Override // org.bouncycastle.operator.MacCalculator
        public GenericKey getKey() {
            return new GenericKey(getAlgorithmIdentifier(), this.c.getEncoded());
        }

        @Override // org.bouncycastle.operator.MacCalculator
        public byte[] getMac() {
            return this.b.doFinal();
        }

        @Override // org.bouncycastle.operator.MacCalculator
        public OutputStream getOutputStream() {
            return new MacOutputStream(this.b);
        }
    }

    public JcePKCS12MacCalculatorBuilder() {
        this(OIWObjectIdentifiers.idSHA1);
    }

    public JcePKCS12MacCalculatorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.f15273a = new DefaultJcaJceHelper();
        this.e = 1024;
        this.b = aSN1ObjectIdentifier;
    }

    @Override // org.bouncycastle.pkcs.PKCS12MacCalculatorBuilder
    public MacCalculator build(char[] cArr) throws OperatorCreationException {
        if (this.c == null) {
            this.c = new SecureRandom();
        }
        try {
            Mac createMac = this.f15273a.createMac(this.b.getId());
            int macLength = createMac.getMacLength();
            this.d = macLength;
            byte[] bArr = new byte[macLength];
            this.c.nextBytes(bArr);
            PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(bArr, this.e);
            PKCS12Key pKCS12Key = new PKCS12Key(cArr);
            createMac.init(pKCS12Key, pBEParameterSpec);
            return new a(bArr, createMac, pKCS12Key);
        } catch (Exception e) {
            throw new OperatorCreationException("unable to create MAC calculator: " + e.getMessage(), e);
        }
    }

    @Override // org.bouncycastle.pkcs.PKCS12MacCalculatorBuilder
    public AlgorithmIdentifier getDigestAlgorithmIdentifier() {
        return new AlgorithmIdentifier(this.b, DERNull.INSTANCE);
    }

    public JcePKCS12MacCalculatorBuilder setIterationCount(int i) {
        this.e = i;
        return this;
    }

    public JcePKCS12MacCalculatorBuilder setProvider(String str) {
        this.f15273a = new NamedJcaJceHelper(str);
        return this;
    }

    public JcePKCS12MacCalculatorBuilder setProvider(Provider provider) {
        this.f15273a = new ProviderJcaJceHelper(provider);
        return this;
    }
}
