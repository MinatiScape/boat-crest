package com.google.crypto.tink.signature;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KeyTypeManager;
import com.google.crypto.tink.PrivateKeyTypeManager;
import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.RsaSsaPssKeyFormat;
import com.google.crypto.tink.proto.RsaSsaPssParams;
import com.google.crypto.tink.proto.RsaSsaPssPrivateKey;
import com.google.crypto.tink.proto.RsaSsaPssPublicKey;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.RsaSsaPssSignJce;
import com.google.crypto.tink.subtle.RsaSsaPssVerifyJce;
import com.google.crypto.tink.subtle.Validators;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAKeyGenParameterSpec;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
/* loaded from: classes10.dex */
public final class RsaSsaPssSignKeyManager extends PrivateKeyTypeManager<RsaSsaPssPrivateKey, RsaSsaPssPublicKey> {
    public static final byte[] e = "Tink and Wycheproof.".getBytes(Charset.forName("UTF-8"));

    /* loaded from: classes10.dex */
    public class a extends KeyTypeManager.PrimitiveFactory<PublicKeySign, RsaSsaPssPrivateKey> {
        public a(Class cls) {
            super(cls);
        }

        @Override // com.google.crypto.tink.KeyTypeManager.PrimitiveFactory
        /* renamed from: b */
        public PublicKeySign getPrimitive(RsaSsaPssPrivateKey rsaSsaPssPrivateKey) throws GeneralSecurityException {
            KeyFactory engineFactory = EngineFactory.KEY_FACTORY.getInstance("RSA");
            RsaSsaPssParams params = rsaSsaPssPrivateKey.getPublicKey().getParams();
            RsaSsaPssSignJce rsaSsaPssSignJce = new RsaSsaPssSignJce((RSAPrivateCrtKey) engineFactory.generatePrivate(new RSAPrivateCrtKeySpec(new BigInteger(1, rsaSsaPssPrivateKey.getPublicKey().getN().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey.getPublicKey().getE().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey.getD().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey.getP().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey.getQ().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey.getDp().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey.getDq().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey.getCrt().toByteArray()))), f.c(params.getSigHash()), f.c(params.getMgf1Hash()), params.getSaltLength());
            try {
                new RsaSsaPssVerifyJce((RSAPublicKey) engineFactory.generatePublic(new RSAPublicKeySpec(new BigInteger(1, rsaSsaPssPrivateKey.getPublicKey().getN().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey.getPublicKey().getE().toByteArray()))), f.c(params.getSigHash()), f.c(params.getMgf1Hash()), params.getSaltLength()).verify(rsaSsaPssSignJce.sign(RsaSsaPssSignKeyManager.e), RsaSsaPssSignKeyManager.e);
                return rsaSsaPssSignJce;
            } catch (GeneralSecurityException e) {
                throw new RuntimeException("Security bug: signing with private key followed by verifying with public key failed" + e);
            }
        }
    }

    /* loaded from: classes10.dex */
    public class b extends KeyTypeManager.KeyFactory<RsaSsaPssKeyFormat, RsaSsaPssPrivateKey> {
        public b(Class cls) {
            super(cls);
        }

        @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
        /* renamed from: a */
        public RsaSsaPssPrivateKey createKey(RsaSsaPssKeyFormat rsaSsaPssKeyFormat) throws GeneralSecurityException {
            RsaSsaPssParams params = rsaSsaPssKeyFormat.getParams();
            Validators.validateRsaModulusSize(rsaSsaPssKeyFormat.getModulusSizeInBits());
            Validators.validateSignatureHash(f.c(params.getSigHash()));
            KeyPairGenerator engineFactory = EngineFactory.KEY_PAIR_GENERATOR.getInstance("RSA");
            engineFactory.initialize(new RSAKeyGenParameterSpec(rsaSsaPssKeyFormat.getModulusSizeInBits(), new BigInteger(1, rsaSsaPssKeyFormat.getPublicExponent().toByteArray())));
            KeyPair generateKeyPair = engineFactory.generateKeyPair();
            RSAPublicKey rSAPublicKey = (RSAPublicKey) generateKeyPair.getPublic();
            RSAPrivateCrtKey rSAPrivateCrtKey = (RSAPrivateCrtKey) generateKeyPair.getPrivate();
            return RsaSsaPssPrivateKey.newBuilder().setVersion(RsaSsaPssSignKeyManager.this.getVersion()).setPublicKey(RsaSsaPssPublicKey.newBuilder().setVersion(RsaSsaPssSignKeyManager.this.getVersion()).setParams(params).setE(ByteString.copyFrom(rSAPublicKey.getPublicExponent().toByteArray())).setN(ByteString.copyFrom(rSAPublicKey.getModulus().toByteArray())).build()).setD(ByteString.copyFrom(rSAPrivateCrtKey.getPrivateExponent().toByteArray())).setP(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeP().toByteArray())).setQ(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeQ().toByteArray())).setDp(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeExponentP().toByteArray())).setDq(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeExponentQ().toByteArray())).setCrt(ByteString.copyFrom(rSAPrivateCrtKey.getCrtCoefficient().toByteArray())).build();
        }

        @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
        /* renamed from: b */
        public RsaSsaPssKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
            return RsaSsaPssKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
        /* renamed from: c */
        public void validateKeyFormat(RsaSsaPssKeyFormat rsaSsaPssKeyFormat) throws GeneralSecurityException {
            f.f(rsaSsaPssKeyFormat.getParams());
            Validators.validateRsaModulusSize(rsaSsaPssKeyFormat.getModulusSizeInBits());
            Validators.validateRsaPublicExponent(new BigInteger(1, rsaSsaPssKeyFormat.getPublicExponent().toByteArray()));
        }
    }

    public RsaSsaPssSignKeyManager() {
        super(RsaSsaPssPrivateKey.class, RsaSsaPssPublicKey.class, new a(PublicKeySign.class));
    }

    public static KeyTemplate c(HashType hashType, HashType hashType2, int i, int i2, BigInteger bigInteger, KeyTemplate.OutputPrefixType outputPrefixType) {
        return KeyTemplate.create(new RsaSsaPssSignKeyManager().getKeyType(), RsaSsaPssKeyFormat.newBuilder().setParams(RsaSsaPssParams.newBuilder().setSigHash(hashType).setMgf1Hash(hashType2).setSaltLength(i).build()).setModulusSizeInBits(i2).setPublicExponent(ByteString.copyFrom(bigInteger.toByteArray())).build().toByteArray(), outputPrefixType);
    }

    public static final KeyTemplate rawRsa3072PssSha256F4Template() {
        HashType hashType = HashType.SHA256;
        return c(hashType, hashType, 32, 3072, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.RAW);
    }

    public static final KeyTemplate rawRsa4096PssSha512F4Template() {
        HashType hashType = HashType.SHA512;
        return c(hashType, hashType, 64, 4096, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.RAW);
    }

    public static void registerPair(boolean z) throws GeneralSecurityException {
        Registry.registerAsymmetricKeyManagers(new RsaSsaPssSignKeyManager(), new e(), z);
    }

    public static final KeyTemplate rsa3072PssSha256F4Template() {
        HashType hashType = HashType.SHA256;
        return c(hashType, hashType, 32, 3072, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.TINK);
    }

    public static final KeyTemplate rsa4096PssSha512F4Template() {
        HashType hashType = HashType.SHA512;
        return c(hashType, hashType, 64, 4096, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.TINK);
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.RsaSsaPssPrivateKey";
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyTypeManager.KeyFactory<RsaSsaPssKeyFormat, RsaSsaPssPrivateKey> keyFactory() {
        return new b(RsaSsaPssKeyFormat.class);
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    @Override // com.google.crypto.tink.PrivateKeyTypeManager
    public RsaSsaPssPublicKey getPublicKey(RsaSsaPssPrivateKey rsaSsaPssPrivateKey) throws GeneralSecurityException {
        return rsaSsaPssPrivateKey.getPublicKey();
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public RsaSsaPssPrivateKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return RsaSsaPssPrivateKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public void validateKey(RsaSsaPssPrivateKey rsaSsaPssPrivateKey) throws GeneralSecurityException {
        Validators.validateVersion(rsaSsaPssPrivateKey.getVersion(), getVersion());
        Validators.validateRsaModulusSize(new BigInteger(1, rsaSsaPssPrivateKey.getPublicKey().getN().toByteArray()).bitLength());
        Validators.validateRsaPublicExponent(new BigInteger(1, rsaSsaPssPrivateKey.getPublicKey().getE().toByteArray()));
        f.f(rsaSsaPssPrivateKey.getPublicKey().getParams());
    }
}
