package org.bouncycastle.operator.jcajce;

import java.io.InputStream;
import java.security.Provider;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.cryptopro.GOST28147Parameters;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.spec.GOST28147ParameterSpec;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.InputDecryptor;
import org.bouncycastle.operator.InputDecryptorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class JceInputDecryptorProviderBuilder {

    /* renamed from: a  reason: collision with root package name */
    public JcaJceHelper f15249a = new DefaultJcaJceHelper();

    /* loaded from: classes13.dex */
    public class a implements InputDecryptorProvider {

        /* renamed from: a  reason: collision with root package name */
        public Cipher f15250a;
        public AlgorithmIdentifier b;
        public final /* synthetic */ byte[] c;

        /* renamed from: org.bouncycastle.operator.jcajce.JceInputDecryptorProviderBuilder$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0910a implements InputDecryptor {
            public C0910a() {
            }

            @Override // org.bouncycastle.operator.InputDecryptor
            public AlgorithmIdentifier getAlgorithmIdentifier() {
                return a.this.b;
            }

            @Override // org.bouncycastle.operator.InputDecryptor
            public InputStream getInputStream(InputStream inputStream) {
                return new CipherInputStream(inputStream, a.this.f15250a);
            }
        }

        public a(byte[] bArr) {
            this.c = bArr;
        }

        @Override // org.bouncycastle.operator.InputDecryptorProvider
        public InputDecryptor get(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
            Cipher cipher;
            AlgorithmParameterSpec gOST28147ParameterSpec;
            this.b = algorithmIdentifier;
            ASN1ObjectIdentifier algorithm = algorithmIdentifier.getAlgorithm();
            try {
                this.f15250a = JceInputDecryptorProviderBuilder.this.f15249a.createCipher(algorithm.getId());
                SecretKeySpec secretKeySpec = new SecretKeySpec(this.c, algorithm.getId());
                ASN1Encodable parameters = algorithmIdentifier.getParameters();
                if (parameters instanceof ASN1OctetString) {
                    cipher = this.f15250a;
                    gOST28147ParameterSpec = new IvParameterSpec(ASN1OctetString.getInstance(parameters).getOctets());
                } else {
                    GOST28147Parameters gOST28147Parameters = GOST28147Parameters.getInstance(parameters);
                    cipher = this.f15250a;
                    gOST28147ParameterSpec = new GOST28147ParameterSpec(gOST28147Parameters.getEncryptionParamSet(), gOST28147Parameters.getIV());
                }
                cipher.init(2, secretKeySpec, gOST28147ParameterSpec);
                return new C0910a();
            } catch (Exception e) {
                throw new OperatorCreationException("unable to create InputDecryptor: " + e.getMessage(), e);
            }
        }
    }

    public InputDecryptorProvider build(byte[] bArr) {
        return new a(Arrays.clone(bArr));
    }

    public JceInputDecryptorProviderBuilder setProvider(String str) {
        this.f15249a = new NamedJcaJceHelper(str);
        return this;
    }

    public JceInputDecryptorProviderBuilder setProvider(Provider provider) {
        this.f15249a = new ProviderJcaJceHelper(provider);
        return this;
    }
}
