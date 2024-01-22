package org.bouncycastle.pkcs.bc;

import java.io.OutputStream;
import java.security.SecureRandom;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.pkcs.PKCS12PBEParams;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.io.CipherOutputStream;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OutputEncryptor;
/* loaded from: classes13.dex */
public class BcPKCS12PBEOutputEncryptorBuilder {

    /* renamed from: a  reason: collision with root package name */
    public ExtendedDigest f15269a;
    public BufferedBlockCipher b;
    public ASN1ObjectIdentifier c;
    public SecureRandom d;
    public int e;

    /* loaded from: classes13.dex */
    public class a implements OutputEncryptor {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ PKCS12PBEParams f15270a;
        public final /* synthetic */ char[] b;

        public a(PKCS12PBEParams pKCS12PBEParams, char[] cArr) {
            this.f15270a = pKCS12PBEParams;
            this.b = cArr;
        }

        @Override // org.bouncycastle.operator.OutputEncryptor
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return new AlgorithmIdentifier(BcPKCS12PBEOutputEncryptorBuilder.this.c, this.f15270a);
        }

        @Override // org.bouncycastle.operator.OutputEncryptor
        public GenericKey getKey() {
            return new GenericKey(new AlgorithmIdentifier(BcPKCS12PBEOutputEncryptorBuilder.this.c, this.f15270a), PBEParametersGenerator.PKCS12PasswordToBytes(this.b));
        }

        @Override // org.bouncycastle.operator.OutputEncryptor
        public OutputStream getOutputStream(OutputStream outputStream) {
            return new CipherOutputStream(outputStream, BcPKCS12PBEOutputEncryptorBuilder.this.b);
        }
    }

    public BcPKCS12PBEOutputEncryptorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier, BlockCipher blockCipher) {
        this(aSN1ObjectIdentifier, blockCipher, new SHA1Digest());
    }

    public BcPKCS12PBEOutputEncryptorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier, BlockCipher blockCipher, ExtendedDigest extendedDigest) {
        this.e = 1024;
        this.c = aSN1ObjectIdentifier;
        this.b = new PaddedBufferedBlockCipher(blockCipher, new PKCS7Padding());
        this.f15269a = extendedDigest;
    }

    public OutputEncryptor build(char[] cArr) {
        if (this.d == null) {
            this.d = new SecureRandom();
        }
        byte[] bArr = new byte[20];
        this.d.nextBytes(bArr);
        PKCS12PBEParams pKCS12PBEParams = new PKCS12PBEParams(bArr, this.e);
        this.b.init(true, org.bouncycastle.pkcs.bc.a.a(this.c, this.f15269a, this.b.getBlockSize(), pKCS12PBEParams, cArr));
        return new a(pKCS12PBEParams, cArr);
    }

    public BcPKCS12PBEOutputEncryptorBuilder setIterationCount(int i) {
        this.e = i;
        return this;
    }
}
