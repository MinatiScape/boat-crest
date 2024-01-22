package com.google.crypto.tink.signature;

import com.google.crypto.tink.proto.EcdsaKeyFormat;
import com.google.crypto.tink.proto.EcdsaParams;
import com.google.crypto.tink.proto.EcdsaSignatureEncoding;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.proto.RsaSsaPkcs1KeyFormat;
import com.google.crypto.tink.proto.RsaSsaPkcs1Params;
import com.google.crypto.tink.proto.RsaSsaPssKeyFormat;
import com.google.crypto.tink.proto.RsaSsaPssParams;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import java.math.BigInteger;
import java.security.spec.RSAKeyGenParameterSpec;
@Deprecated
/* loaded from: classes10.dex */
public final class SignatureKeyTemplates {
    public static final KeyTemplate ECDSA_P256;
    public static final KeyTemplate ECDSA_P256_IEEE_P1363;
    public static final KeyTemplate ECDSA_P256_IEEE_P1363_WITHOUT_PREFIX;
    public static final KeyTemplate ECDSA_P384;
    public static final KeyTemplate ECDSA_P384_IEEE_P1363;
    public static final KeyTemplate ECDSA_P521;
    public static final KeyTemplate ECDSA_P521_IEEE_P1363;
    public static final KeyTemplate ED25519;
    public static final KeyTemplate ED25519WithRawOutput;
    public static final KeyTemplate RSA_SSA_PKCS1_3072_SHA256_F4;
    public static final KeyTemplate RSA_SSA_PKCS1_3072_SHA256_F4_WITHOUT_PREFIX;
    public static final KeyTemplate RSA_SSA_PKCS1_4096_SHA512_F4;
    public static final KeyTemplate RSA_SSA_PSS_3072_SHA256_SHA256_32_F4;
    public static final KeyTemplate RSA_SSA_PSS_4096_SHA512_SHA512_64_F4;

    static {
        HashType hashType = HashType.SHA256;
        EllipticCurveType ellipticCurveType = EllipticCurveType.NIST_P256;
        EcdsaSignatureEncoding ecdsaSignatureEncoding = EcdsaSignatureEncoding.DER;
        OutputPrefixType outputPrefixType = OutputPrefixType.TINK;
        ECDSA_P256 = createEcdsaKeyTemplate(hashType, ellipticCurveType, ecdsaSignatureEncoding, outputPrefixType);
        HashType hashType2 = HashType.SHA512;
        EllipticCurveType ellipticCurveType2 = EllipticCurveType.NIST_P384;
        ECDSA_P384 = createEcdsaKeyTemplate(hashType2, ellipticCurveType2, ecdsaSignatureEncoding, outputPrefixType);
        EllipticCurveType ellipticCurveType3 = EllipticCurveType.NIST_P521;
        ECDSA_P521 = createEcdsaKeyTemplate(hashType2, ellipticCurveType3, ecdsaSignatureEncoding, outputPrefixType);
        EcdsaSignatureEncoding ecdsaSignatureEncoding2 = EcdsaSignatureEncoding.IEEE_P1363;
        ECDSA_P256_IEEE_P1363 = createEcdsaKeyTemplate(hashType, ellipticCurveType, ecdsaSignatureEncoding2, outputPrefixType);
        ECDSA_P384_IEEE_P1363 = createEcdsaKeyTemplate(hashType2, ellipticCurveType2, ecdsaSignatureEncoding2, outputPrefixType);
        OutputPrefixType outputPrefixType2 = OutputPrefixType.RAW;
        ECDSA_P256_IEEE_P1363_WITHOUT_PREFIX = createEcdsaKeyTemplate(hashType, ellipticCurveType, ecdsaSignatureEncoding2, outputPrefixType2);
        ECDSA_P521_IEEE_P1363 = createEcdsaKeyTemplate(hashType2, ellipticCurveType3, ecdsaSignatureEncoding2, outputPrefixType);
        ED25519 = KeyTemplate.newBuilder().setTypeUrl(new Ed25519PrivateKeyManager().getKeyType()).setOutputPrefixType(outputPrefixType).build();
        ED25519WithRawOutput = KeyTemplate.newBuilder().setTypeUrl(new Ed25519PrivateKeyManager().getKeyType()).setOutputPrefixType(outputPrefixType2).build();
        RSA_SSA_PKCS1_3072_SHA256_F4 = createRsaSsaPkcs1KeyTemplate(hashType, 3072, RSAKeyGenParameterSpec.F4, outputPrefixType);
        RSA_SSA_PKCS1_3072_SHA256_F4_WITHOUT_PREFIX = createRsaSsaPkcs1KeyTemplate(hashType, 3072, RSAKeyGenParameterSpec.F4, outputPrefixType2);
        RSA_SSA_PKCS1_4096_SHA512_F4 = createRsaSsaPkcs1KeyTemplate(hashType2, 4096, RSAKeyGenParameterSpec.F4, outputPrefixType);
        RSA_SSA_PSS_3072_SHA256_SHA256_32_F4 = createRsaSsaPssKeyTemplate(hashType, hashType, 32, 3072, RSAKeyGenParameterSpec.F4);
        RSA_SSA_PSS_4096_SHA512_SHA512_64_F4 = createRsaSsaPssKeyTemplate(hashType2, hashType2, 64, 4096, RSAKeyGenParameterSpec.F4);
    }

    public static KeyTemplate createEcdsaKeyTemplate(HashType hashType, EllipticCurveType ellipticCurveType, EcdsaSignatureEncoding ecdsaSignatureEncoding, OutputPrefixType outputPrefixType) {
        return KeyTemplate.newBuilder().setValue(EcdsaKeyFormat.newBuilder().setParams(EcdsaParams.newBuilder().setHashType(hashType).setCurve(ellipticCurveType).setEncoding(ecdsaSignatureEncoding).build()).build().toByteString()).setTypeUrl(new EcdsaSignKeyManager().getKeyType()).setOutputPrefixType(outputPrefixType).build();
    }

    public static KeyTemplate createRsaSsaPkcs1KeyTemplate(HashType hashType, int i, BigInteger bigInteger, OutputPrefixType outputPrefixType) {
        return KeyTemplate.newBuilder().setValue(RsaSsaPkcs1KeyFormat.newBuilder().setParams(RsaSsaPkcs1Params.newBuilder().setHashType(hashType).build()).setModulusSizeInBits(i).setPublicExponent(ByteString.copyFrom(bigInteger.toByteArray())).build().toByteString()).setTypeUrl(new RsaSsaPkcs1SignKeyManager().getKeyType()).setOutputPrefixType(outputPrefixType).build();
    }

    public static KeyTemplate createRsaSsaPssKeyTemplate(HashType hashType, HashType hashType2, int i, int i2, BigInteger bigInteger) {
        return KeyTemplate.newBuilder().setValue(RsaSsaPssKeyFormat.newBuilder().setParams(RsaSsaPssParams.newBuilder().setSigHash(hashType).setMgf1Hash(hashType2).setSaltLength(i).build()).setModulusSizeInBits(i2).setPublicExponent(ByteString.copyFrom(bigInteger.toByteArray())).build().toByteString()).setTypeUrl(new RsaSsaPssSignKeyManager().getKeyType()).setOutputPrefixType(OutputPrefixType.TINK).build();
    }
}
