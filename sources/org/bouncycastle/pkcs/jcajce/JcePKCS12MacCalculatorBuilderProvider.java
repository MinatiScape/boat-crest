package org.bouncycastle.pkcs.jcajce;

import java.io.OutputStream;
import java.security.Provider;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.PBEParameterSpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
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
import org.bouncycastle.pkcs.PKCS12MacCalculatorBuilderProvider;
/* loaded from: classes13.dex */
public class JcePKCS12MacCalculatorBuilderProvider implements PKCS12MacCalculatorBuilderProvider {

    /* renamed from: a  reason: collision with root package name */
    public JcaJceHelper f15275a = new DefaultJcaJceHelper();

    /* loaded from: classes13.dex */
    public class a implements PKCS12MacCalculatorBuilder {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ AlgorithmIdentifier f15276a;

        /* renamed from: org.bouncycastle.pkcs.jcajce.JcePKCS12MacCalculatorBuilderProvider$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0914a implements MacCalculator {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ ASN1ObjectIdentifier f15277a;
            public final /* synthetic */ PKCS12PBEParams b;
            public final /* synthetic */ Mac c;
            public final /* synthetic */ SecretKey d;

            public C0914a(a aVar, ASN1ObjectIdentifier aSN1ObjectIdentifier, PKCS12PBEParams pKCS12PBEParams, Mac mac, SecretKey secretKey) {
                this.f15277a = aSN1ObjectIdentifier;
                this.b = pKCS12PBEParams;
                this.c = mac;
                this.d = secretKey;
            }

            @Override // org.bouncycastle.operator.MacCalculator
            public AlgorithmIdentifier getAlgorithmIdentifier() {
                return new AlgorithmIdentifier(this.f15277a, this.b);
            }

            @Override // org.bouncycastle.operator.MacCalculator
            public GenericKey getKey() {
                return new GenericKey(getAlgorithmIdentifier(), this.d.getEncoded());
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

        public a(AlgorithmIdentifier algorithmIdentifier) {
            this.f15276a = algorithmIdentifier;
        }

        @Override // org.bouncycastle.pkcs.PKCS12MacCalculatorBuilder
        public MacCalculator build(char[] cArr) throws OperatorCreationException {
            PKCS12PBEParams pKCS12PBEParams = PKCS12PBEParams.getInstance(this.f15276a.getParameters());
            try {
                ASN1ObjectIdentifier algorithm = this.f15276a.getAlgorithm();
                Mac createMac = JcePKCS12MacCalculatorBuilderProvider.this.f15275a.createMac(algorithm.getId());
                PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(pKCS12PBEParams.getIV(), pKCS12PBEParams.getIterations().intValue());
                PKCS12Key pKCS12Key = new PKCS12Key(cArr);
                createMac.init(pKCS12Key, pBEParameterSpec);
                return new C0914a(this, algorithm, pKCS12PBEParams, createMac, pKCS12Key);
            } catch (Exception e) {
                throw new OperatorCreationException("unable to create MAC calculator: " + e.getMessage(), e);
            }
        }

        @Override // org.bouncycastle.pkcs.PKCS12MacCalculatorBuilder
        public AlgorithmIdentifier getDigestAlgorithmIdentifier() {
            return new AlgorithmIdentifier(this.f15276a.getAlgorithm(), DERNull.INSTANCE);
        }
    }

    @Override // org.bouncycastle.pkcs.PKCS12MacCalculatorBuilderProvider
    public PKCS12MacCalculatorBuilder get(AlgorithmIdentifier algorithmIdentifier) {
        return new a(algorithmIdentifier);
    }

    public JcePKCS12MacCalculatorBuilderProvider setProvider(String str) {
        this.f15275a = new NamedJcaJceHelper(str);
        return this;
    }

    public JcePKCS12MacCalculatorBuilderProvider setProvider(Provider provider) {
        this.f15275a = new ProviderJcaJceHelper(provider);
        return this;
    }
}
