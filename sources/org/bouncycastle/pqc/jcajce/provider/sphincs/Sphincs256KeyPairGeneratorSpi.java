package org.bouncycastle.pqc.jcajce.provider.sphincs;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.digests.SHA512tDigest;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCS256KeyGenerationParameters;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCS256KeyPairGenerator;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCSPublicKeyParameters;
import org.bouncycastle.pqc.jcajce.spec.SPHINCS256KeyGenParameterSpec;
/* loaded from: classes13.dex */
public class Sphincs256KeyPairGeneratorSpi extends KeyPairGenerator {

    /* renamed from: a  reason: collision with root package name */
    public ASN1ObjectIdentifier f15353a;
    public SPHINCS256KeyGenerationParameters b;
    public SPHINCS256KeyPairGenerator c;
    public SecureRandom d;
    public boolean e;

    public Sphincs256KeyPairGeneratorSpi() {
        super("SPHINCS256");
        this.f15353a = NISTObjectIdentifiers.id_sha512_256;
        this.c = new SPHINCS256KeyPairGenerator();
        this.d = new SecureRandom();
        this.e = false;
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public KeyPair generateKeyPair() {
        if (!this.e) {
            SPHINCS256KeyGenerationParameters sPHINCS256KeyGenerationParameters = new SPHINCS256KeyGenerationParameters(this.d, new SHA512tDigest(256));
            this.b = sPHINCS256KeyGenerationParameters;
            this.c.init(sPHINCS256KeyGenerationParameters);
            this.e = true;
        }
        AsymmetricCipherKeyPair generateKeyPair = this.c.generateKeyPair();
        return new KeyPair(new BCSphincs256PublicKey(this.f15353a, (SPHINCSPublicKeyParameters) generateKeyPair.getPublic()), new BCSphincs256PrivateKey(this.f15353a, (SPHINCSPrivateKeyParameters) generateKeyPair.getPrivate()));
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(int i, SecureRandom secureRandom) {
        throw new IllegalArgumentException("use AlgorithmParameterSpec");
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        SPHINCS256KeyGenerationParameters sPHINCS256KeyGenerationParameters;
        if (!(algorithmParameterSpec instanceof SPHINCS256KeyGenParameterSpec)) {
            throw new InvalidAlgorithmParameterException("parameter object not a SPHINCS256KeyGenParameterSpec");
        }
        SPHINCS256KeyGenParameterSpec sPHINCS256KeyGenParameterSpec = (SPHINCS256KeyGenParameterSpec) algorithmParameterSpec;
        if (!sPHINCS256KeyGenParameterSpec.getTreeDigest().equals(SPHINCS256KeyGenParameterSpec.SHA512_256)) {
            if (sPHINCS256KeyGenParameterSpec.getTreeDigest().equals("SHA3-256")) {
                this.f15353a = NISTObjectIdentifiers.id_sha3_256;
                sPHINCS256KeyGenerationParameters = new SPHINCS256KeyGenerationParameters(secureRandom, new SHA3Digest(256));
            }
            this.c.init(this.b);
            this.e = true;
        }
        this.f15353a = NISTObjectIdentifiers.id_sha512_256;
        sPHINCS256KeyGenerationParameters = new SPHINCS256KeyGenerationParameters(secureRandom, new SHA512tDigest(256));
        this.b = sPHINCS256KeyGenerationParameters;
        this.c.init(this.b);
        this.e = true;
    }
}
