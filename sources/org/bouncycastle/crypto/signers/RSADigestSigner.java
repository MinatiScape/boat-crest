package org.bouncycastle.crypto.signers;

import java.io.IOException;
import java.util.Hashtable;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.DigestInfo;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.encodings.PKCS1Encoding;
import org.bouncycastle.crypto.engines.RSABlindedEngine;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class RSADigestSigner implements Signer {
    public static final Hashtable e;

    /* renamed from: a  reason: collision with root package name */
    public final AsymmetricBlockCipher f14842a;
    public final AlgorithmIdentifier b;
    public final Digest c;
    public boolean d;

    static {
        Hashtable hashtable = new Hashtable();
        e = hashtable;
        hashtable.put("RIPEMD128", TeleTrusTObjectIdentifiers.ripemd128);
        hashtable.put("RIPEMD160", TeleTrusTObjectIdentifiers.ripemd160);
        hashtable.put("RIPEMD256", TeleTrusTObjectIdentifiers.ripemd256);
        hashtable.put("SHA-1", X509ObjectIdentifiers.id_SHA1);
        hashtable.put("SHA-224", NISTObjectIdentifiers.id_sha224);
        hashtable.put("SHA-256", NISTObjectIdentifiers.id_sha256);
        hashtable.put("SHA-384", NISTObjectIdentifiers.id_sha384);
        hashtable.put("SHA-512", NISTObjectIdentifiers.id_sha512);
        hashtable.put(MessageDigestAlgorithms.SHA_512_224, NISTObjectIdentifiers.id_sha512_224);
        hashtable.put(MessageDigestAlgorithms.SHA_512_256, NISTObjectIdentifiers.id_sha512_256);
        hashtable.put(MessageDigestAlgorithms.SHA3_224, NISTObjectIdentifiers.id_sha3_224);
        hashtable.put("SHA3-256", NISTObjectIdentifiers.id_sha3_256);
        hashtable.put(MessageDigestAlgorithms.SHA3_384, NISTObjectIdentifiers.id_sha3_384);
        hashtable.put(MessageDigestAlgorithms.SHA3_512, NISTObjectIdentifiers.id_sha3_512);
        hashtable.put(MessageDigestAlgorithms.MD2, PKCSObjectIdentifiers.md2);
        hashtable.put("MD4", PKCSObjectIdentifiers.md4);
        hashtable.put(MessageDigestAlgorithms.MD5, PKCSObjectIdentifiers.md5);
    }

    public RSADigestSigner(Digest digest) {
        this(digest, (ASN1ObjectIdentifier) e.get(digest.getAlgorithmName()));
    }

    public RSADigestSigner(Digest digest, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.f14842a = new PKCS1Encoding(new RSABlindedEngine());
        this.c = digest;
        this.b = new AlgorithmIdentifier(aSN1ObjectIdentifier, DERNull.INSTANCE);
    }

    public final byte[] a(byte[] bArr) throws IOException {
        return new DigestInfo(this.b, bArr).getEncoded(ASN1Encoding.DER);
    }

    @Override // org.bouncycastle.crypto.Signer
    public byte[] generateSignature() throws CryptoException, DataLengthException {
        if (this.d) {
            byte[] bArr = new byte[this.c.getDigestSize()];
            this.c.doFinal(bArr, 0);
            try {
                byte[] a2 = a(bArr);
                return this.f14842a.processBlock(a2, 0, a2.length);
            } catch (IOException e2) {
                throw new CryptoException("unable to encode signature: " + e2.getMessage(), e2);
            }
        }
        throw new IllegalStateException("RSADigestSigner not initialised for signature generation.");
    }

    public String getAlgorithmName() {
        return this.c.getAlgorithmName() + "withRSA";
    }

    @Override // org.bouncycastle.crypto.Signer
    public void init(boolean z, CipherParameters cipherParameters) {
        this.d = z;
        AsymmetricKeyParameter asymmetricKeyParameter = cipherParameters instanceof ParametersWithRandom ? (AsymmetricKeyParameter) ((ParametersWithRandom) cipherParameters).getParameters() : (AsymmetricKeyParameter) cipherParameters;
        if (z && !asymmetricKeyParameter.isPrivate()) {
            throw new IllegalArgumentException("signing requires private key");
        }
        if (!z && asymmetricKeyParameter.isPrivate()) {
            throw new IllegalArgumentException("verification requires public key");
        }
        reset();
        this.f14842a.init(z, cipherParameters);
    }

    @Override // org.bouncycastle.crypto.Signer
    public void reset() {
        this.c.reset();
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte b) {
        this.c.update(b);
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte[] bArr, int i, int i2) {
        this.c.update(bArr, i, i2);
    }

    @Override // org.bouncycastle.crypto.Signer
    public boolean verifySignature(byte[] bArr) {
        byte[] processBlock;
        byte[] a2;
        if (this.d) {
            throw new IllegalStateException("RSADigestSigner not initialised for verification");
        }
        int digestSize = this.c.getDigestSize();
        byte[] bArr2 = new byte[digestSize];
        this.c.doFinal(bArr2, 0);
        try {
            processBlock = this.f14842a.processBlock(bArr, 0, bArr.length);
            a2 = a(bArr2);
        } catch (Exception unused) {
        }
        if (processBlock.length == a2.length) {
            return Arrays.constantTimeAreEqual(processBlock, a2);
        }
        if (processBlock.length != a2.length - 2) {
            Arrays.constantTimeAreEqual(a2, a2);
            return false;
        }
        int length = (processBlock.length - digestSize) - 2;
        int length2 = (a2.length - digestSize) - 2;
        a2[1] = (byte) (a2[1] - 2);
        a2[3] = (byte) (a2[3] - 2);
        int i = 0;
        for (int i2 = 0; i2 < digestSize; i2++) {
            i |= processBlock[length + i2] ^ a2[length2 + i2];
        }
        for (int i3 = 0; i3 < length; i3++) {
            i |= processBlock[i3] ^ a2[i3];
        }
        return i == 0;
    }
}
