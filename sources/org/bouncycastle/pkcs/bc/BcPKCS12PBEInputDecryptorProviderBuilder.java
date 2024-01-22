package org.bouncycastle.pkcs.bc;

import java.io.InputStream;
import org.bouncycastle.asn1.pkcs.PKCS12PBEParams;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.io.CipherInputStream;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.operator.InputDecryptor;
import org.bouncycastle.operator.InputDecryptorProvider;
/* loaded from: classes13.dex */
public class BcPKCS12PBEInputDecryptorProviderBuilder {

    /* renamed from: a  reason: collision with root package name */
    public ExtendedDigest f15266a;

    /* loaded from: classes13.dex */
    public class a implements InputDecryptorProvider {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ char[] f15267a;

        /* renamed from: org.bouncycastle.pkcs.bc.BcPKCS12PBEInputDecryptorProviderBuilder$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0912a implements InputDecryptor {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ AlgorithmIdentifier f15268a;
            public final /* synthetic */ PaddedBufferedBlockCipher b;

            public C0912a(a aVar, AlgorithmIdentifier algorithmIdentifier, PaddedBufferedBlockCipher paddedBufferedBlockCipher) {
                this.f15268a = algorithmIdentifier;
                this.b = paddedBufferedBlockCipher;
            }

            @Override // org.bouncycastle.operator.InputDecryptor
            public AlgorithmIdentifier getAlgorithmIdentifier() {
                return this.f15268a;
            }

            @Override // org.bouncycastle.operator.InputDecryptor
            public InputStream getInputStream(InputStream inputStream) {
                return new CipherInputStream(inputStream, this.b);
            }
        }

        public a(char[] cArr) {
            this.f15267a = cArr;
        }

        @Override // org.bouncycastle.operator.InputDecryptorProvider
        public InputDecryptor get(AlgorithmIdentifier algorithmIdentifier) {
            PaddedBufferedBlockCipher c = org.bouncycastle.pkcs.bc.a.c(algorithmIdentifier.getAlgorithm());
            c.init(false, org.bouncycastle.pkcs.bc.a.a(algorithmIdentifier.getAlgorithm(), BcPKCS12PBEInputDecryptorProviderBuilder.this.f15266a, c.getBlockSize(), PKCS12PBEParams.getInstance(algorithmIdentifier.getParameters()), this.f15267a));
            return new C0912a(this, algorithmIdentifier, c);
        }
    }

    public BcPKCS12PBEInputDecryptorProviderBuilder() {
        this(new SHA1Digest());
    }

    public BcPKCS12PBEInputDecryptorProviderBuilder(ExtendedDigest extendedDigest) {
        this.f15266a = extendedDigest;
    }

    public InputDecryptorProvider build(char[] cArr) {
        return new a(cArr);
    }
}
