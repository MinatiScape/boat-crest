package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.aead.AeadKeyTemplates;
import com.google.crypto.tink.proto.EcPointFormat;
import com.google.crypto.tink.proto.EciesAeadDemParams;
import com.google.crypto.tink.proto.EciesAeadHkdfKeyFormat;
import com.google.crypto.tink.proto.EciesAeadHkdfParams;
import com.google.crypto.tink.proto.EciesHkdfKemParams;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
@Deprecated
/* loaded from: classes10.dex */
public final class HybridKeyTemplates {
    public static final KeyTemplate ECIES_P256_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256;
    public static final KeyTemplate ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM;
    public static final KeyTemplate ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM_COMPRESSED_WITHOUT_PREFIX;

    /* renamed from: a  reason: collision with root package name */
    public static final byte[] f10844a;

    static {
        byte[] bArr = new byte[0];
        f10844a = bArr;
        EllipticCurveType ellipticCurveType = EllipticCurveType.NIST_P256;
        HashType hashType = HashType.SHA256;
        EcPointFormat ecPointFormat = EcPointFormat.UNCOMPRESSED;
        KeyTemplate keyTemplate = AeadKeyTemplates.AES128_GCM;
        OutputPrefixType outputPrefixType = OutputPrefixType.TINK;
        ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM = createEciesAeadHkdfKeyTemplate(ellipticCurveType, hashType, ecPointFormat, keyTemplate, outputPrefixType, bArr);
        ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM_COMPRESSED_WITHOUT_PREFIX = createEciesAeadHkdfKeyTemplate(ellipticCurveType, hashType, EcPointFormat.COMPRESSED, keyTemplate, OutputPrefixType.RAW, bArr);
        ECIES_P256_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256 = createEciesAeadHkdfKeyTemplate(ellipticCurveType, hashType, ecPointFormat, AeadKeyTemplates.AES128_CTR_HMAC_SHA256, outputPrefixType, bArr);
    }

    public static KeyTemplate createEciesAeadHkdfKeyTemplate(EllipticCurveType ellipticCurveType, HashType hashType, EcPointFormat ecPointFormat, KeyTemplate keyTemplate, OutputPrefixType outputPrefixType, byte[] bArr) {
        return KeyTemplate.newBuilder().setTypeUrl(new EciesAeadHkdfPrivateKeyManager().getKeyType()).setOutputPrefixType(outputPrefixType).setValue(EciesAeadHkdfKeyFormat.newBuilder().setParams(createEciesAeadHkdfParams(ellipticCurveType, hashType, ecPointFormat, keyTemplate, bArr)).build().toByteString()).build();
    }

    public static EciesAeadHkdfParams createEciesAeadHkdfParams(EllipticCurveType ellipticCurveType, HashType hashType, EcPointFormat ecPointFormat, KeyTemplate keyTemplate, byte[] bArr) {
        return EciesAeadHkdfParams.newBuilder().setKemParams(EciesHkdfKemParams.newBuilder().setCurveType(ellipticCurveType).setHkdfHashType(hashType).setHkdfSalt(ByteString.copyFrom(bArr)).build()).setDemParams(EciesAeadDemParams.newBuilder().setAeadDem(keyTemplate).build()).setEcPointFormat(ecPointFormat).build();
    }
}
