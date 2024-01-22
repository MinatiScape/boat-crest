package org.bouncycastle.pqc.jcajce.provider.xmss;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.pqc.crypto.xmss.XMSSKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSKeyPairGenerator;
import org.bouncycastle.pqc.crypto.xmss.XMSSParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSPublicKeyParameters;
import org.bouncycastle.pqc.jcajce.spec.XMSSParameterSpec;
/* loaded from: classes13.dex */
public class XMSSKeyPairGeneratorSpi extends KeyPairGenerator {

    /* renamed from: a  reason: collision with root package name */
    public XMSSKeyGenerationParameters f15354a;
    public ASN1ObjectIdentifier b;
    public XMSSKeyPairGenerator c;
    public SecureRandom d;
    public boolean e;

    public XMSSKeyPairGeneratorSpi() {
        super("XMSS");
        this.c = new XMSSKeyPairGenerator();
        this.d = new SecureRandom();
        this.e = false;
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public KeyPair generateKeyPair() {
        if (!this.e) {
            XMSSKeyGenerationParameters xMSSKeyGenerationParameters = new XMSSKeyGenerationParameters(new XMSSParameters(10, new SHA512Digest()), this.d);
            this.f15354a = xMSSKeyGenerationParameters;
            this.c.init(xMSSKeyGenerationParameters);
            this.e = true;
        }
        AsymmetricCipherKeyPair generateKeyPair = this.c.generateKeyPair();
        return new KeyPair(new BCXMSSPublicKey(this.b, (XMSSPublicKeyParameters) generateKeyPair.getPublic()), new BCXMSSPrivateKey(this.b, (XMSSPrivateKeyParameters) generateKeyPair.getPrivate()));
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(int i, SecureRandom secureRandom) {
        throw new IllegalArgumentException("use AlgorithmParameterSpec");
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        XMSSKeyGenerationParameters xMSSKeyGenerationParameters;
        if (!(algorithmParameterSpec instanceof XMSSParameterSpec)) {
            throw new InvalidAlgorithmParameterException("parameter object not a XMSSParameterSpec");
        }
        XMSSParameterSpec xMSSParameterSpec = (XMSSParameterSpec) algorithmParameterSpec;
        if (xMSSParameterSpec.getTreeDigest().equals("SHA256")) {
            this.b = NISTObjectIdentifiers.id_sha256;
            xMSSKeyGenerationParameters = new XMSSKeyGenerationParameters(new XMSSParameters(xMSSParameterSpec.getHeight(), new SHA256Digest()), secureRandom);
        } else if (xMSSParameterSpec.getTreeDigest().equals("SHA512")) {
            this.b = NISTObjectIdentifiers.id_sha512;
            xMSSKeyGenerationParameters = new XMSSKeyGenerationParameters(new XMSSParameters(xMSSParameterSpec.getHeight(), new SHA512Digest()), secureRandom);
        } else if (!xMSSParameterSpec.getTreeDigest().equals("SHAKE128")) {
            if (xMSSParameterSpec.getTreeDigest().equals("SHAKE256")) {
                this.b = NISTObjectIdentifiers.id_shake256;
                xMSSKeyGenerationParameters = new XMSSKeyGenerationParameters(new XMSSParameters(xMSSParameterSpec.getHeight(), new SHAKEDigest(256)), secureRandom);
            }
            this.c.init(this.f15354a);
            this.e = true;
        } else {
            this.b = NISTObjectIdentifiers.id_shake128;
            xMSSKeyGenerationParameters = new XMSSKeyGenerationParameters(new XMSSParameters(xMSSParameterSpec.getHeight(), new SHAKEDigest(128)), secureRandom);
        }
        this.f15354a = xMSSKeyGenerationParameters;
        this.c.init(this.f15354a);
        this.e = true;
    }
}
