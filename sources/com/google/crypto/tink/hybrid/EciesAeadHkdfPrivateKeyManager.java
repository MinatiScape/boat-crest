package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KeyTypeManager;
import com.google.crypto.tink.PrivateKeyTypeManager;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.aead.AesCtrHmacAeadKeyManager;
import com.google.crypto.tink.aead.AesGcmKeyManager;
import com.google.crypto.tink.proto.EcPointFormat;
import com.google.crypto.tink.proto.EciesAeadDemParams;
import com.google.crypto.tink.proto.EciesAeadHkdfKeyFormat;
import com.google.crypto.tink.proto.EciesAeadHkdfParams;
import com.google.crypto.tink.proto.EciesAeadHkdfPrivateKey;
import com.google.crypto.tink.proto.EciesAeadHkdfPublicKey;
import com.google.crypto.tink.proto.EciesHkdfKemParams;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.EciesAeadHkdfHybridDecrypt;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;
/* loaded from: classes10.dex */
public final class EciesAeadHkdfPrivateKeyManager extends PrivateKeyTypeManager<EciesAeadHkdfPrivateKey, EciesAeadHkdfPublicKey> {
    public static final byte[] e = new byte[0];

    /* loaded from: classes10.dex */
    public class a extends KeyTypeManager.PrimitiveFactory<HybridDecrypt, EciesAeadHkdfPrivateKey> {
        public a(Class cls) {
            super(cls);
        }

        @Override // com.google.crypto.tink.KeyTypeManager.PrimitiveFactory
        /* renamed from: b */
        public HybridDecrypt getPrimitive(EciesAeadHkdfPrivateKey eciesAeadHkdfPrivateKey) throws GeneralSecurityException {
            EciesAeadHkdfParams params = eciesAeadHkdfPrivateKey.getPublicKey().getParams();
            EciesHkdfKemParams kemParams = params.getKemParams();
            return new EciesAeadHkdfHybridDecrypt(EllipticCurves.getEcPrivateKey(com.google.crypto.tink.hybrid.c.a(kemParams.getCurveType()), eciesAeadHkdfPrivateKey.getKeyValue().toByteArray()), kemParams.getHkdfSalt().toByteArray(), com.google.crypto.tink.hybrid.c.b(kemParams.getHkdfHashType()), com.google.crypto.tink.hybrid.c.c(params.getEcPointFormat()), new d(params.getDemParams().getAeadDem()));
        }
    }

    /* loaded from: classes10.dex */
    public class b extends KeyTypeManager.KeyFactory<EciesAeadHkdfKeyFormat, EciesAeadHkdfPrivateKey> {
        public b(Class cls) {
            super(cls);
        }

        @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
        /* renamed from: a */
        public EciesAeadHkdfPrivateKey createKey(EciesAeadHkdfKeyFormat eciesAeadHkdfKeyFormat) throws GeneralSecurityException {
            KeyPair generateKeyPair = EllipticCurves.generateKeyPair(com.google.crypto.tink.hybrid.c.a(eciesAeadHkdfKeyFormat.getParams().getKemParams().getCurveType()));
            ECPoint w = ((ECPublicKey) generateKeyPair.getPublic()).getW();
            return EciesAeadHkdfPrivateKey.newBuilder().setVersion(EciesAeadHkdfPrivateKeyManager.this.getVersion()).setPublicKey(EciesAeadHkdfPublicKey.newBuilder().setVersion(EciesAeadHkdfPrivateKeyManager.this.getVersion()).setParams(eciesAeadHkdfKeyFormat.getParams()).setX(ByteString.copyFrom(w.getAffineX().toByteArray())).setY(ByteString.copyFrom(w.getAffineY().toByteArray())).build()).setKeyValue(ByteString.copyFrom(((ECPrivateKey) generateKeyPair.getPrivate()).getS().toByteArray())).build();
        }

        @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
        /* renamed from: b */
        public EciesAeadHkdfKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
            return EciesAeadHkdfKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
        /* renamed from: c */
        public void validateKeyFormat(EciesAeadHkdfKeyFormat eciesAeadHkdfKeyFormat) throws GeneralSecurityException {
            com.google.crypto.tink.hybrid.c.d(eciesAeadHkdfKeyFormat.getParams());
        }
    }

    /* loaded from: classes10.dex */
    public static /* synthetic */ class c {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10841a;

        static {
            int[] iArr = new int[KeyTemplate.OutputPrefixType.values().length];
            f10841a = iArr;
            try {
                iArr[KeyTemplate.OutputPrefixType.TINK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10841a[KeyTemplate.OutputPrefixType.LEGACY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10841a[KeyTemplate.OutputPrefixType.RAW.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10841a[KeyTemplate.OutputPrefixType.CRUNCHY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public EciesAeadHkdfPrivateKeyManager() {
        super(EciesAeadHkdfPrivateKey.class, EciesAeadHkdfPublicKey.class, new a(HybridDecrypt.class));
    }

    public static KeyTemplate b(EllipticCurveType ellipticCurveType, HashType hashType, EcPointFormat ecPointFormat, KeyTemplate keyTemplate, KeyTemplate.OutputPrefixType outputPrefixType, byte[] bArr) {
        return KeyTemplate.create(new EciesAeadHkdfPrivateKeyManager().getKeyType(), EciesAeadHkdfKeyFormat.newBuilder().setParams(c(ellipticCurveType, hashType, ecPointFormat, keyTemplate, bArr)).build().toByteArray(), outputPrefixType);
    }

    public static EciesAeadHkdfParams c(EllipticCurveType ellipticCurveType, HashType hashType, EcPointFormat ecPointFormat, KeyTemplate keyTemplate, byte[] bArr) {
        EciesAeadDemParams.Builder newBuilder = EciesAeadDemParams.newBuilder();
        return EciesAeadHkdfParams.newBuilder().setKemParams(EciesHkdfKemParams.newBuilder().setCurveType(ellipticCurveType).setHkdfHashType(hashType).setHkdfSalt(ByteString.copyFrom(bArr)).build()).setDemParams(newBuilder.setAeadDem(com.google.crypto.tink.proto.KeyTemplate.newBuilder().setTypeUrl(keyTemplate.getTypeUrl()).setValue(ByteString.copyFrom(keyTemplate.getValue())).setOutputPrefixType(d(keyTemplate.getOutputPrefixType())).build()).build()).setEcPointFormat(ecPointFormat).build();
    }

    public static OutputPrefixType d(KeyTemplate.OutputPrefixType outputPrefixType) {
        int i = c.f10841a[outputPrefixType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        return OutputPrefixType.CRUNCHY;
                    }
                    throw new IllegalArgumentException("Unknown output prefix type");
                }
                return OutputPrefixType.RAW;
            }
            return OutputPrefixType.LEGACY;
        }
        return OutputPrefixType.TINK;
    }

    public static final KeyTemplate eciesP256HkdfHmacSha256Aes128CtrHmacSha256Template() {
        return b(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.UNCOMPRESSED, AesCtrHmacAeadKeyManager.aes128CtrHmacSha256Template(), KeyTemplate.OutputPrefixType.TINK, e);
    }

    public static final KeyTemplate eciesP256HkdfHmacSha256Aes128GcmTemplate() {
        return b(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.UNCOMPRESSED, AesGcmKeyManager.aes128GcmTemplate(), KeyTemplate.OutputPrefixType.TINK, e);
    }

    public static final KeyTemplate rawEciesP256HkdfHmacSha256Aes128CtrHmacSha256CompressedTemplate() {
        return b(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.COMPRESSED, AesCtrHmacAeadKeyManager.aes128CtrHmacSha256Template(), KeyTemplate.OutputPrefixType.RAW, e);
    }

    public static final KeyTemplate rawEciesP256HkdfHmacSha256Aes128GcmCompressedTemplate() {
        return b(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.COMPRESSED, AesGcmKeyManager.aes128GcmTemplate(), KeyTemplate.OutputPrefixType.RAW, e);
    }

    public static void registerPair(boolean z) throws GeneralSecurityException {
        Registry.registerAsymmetricKeyManagers(new EciesAeadHkdfPrivateKeyManager(), new com.google.crypto.tink.hybrid.a(), z);
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey";
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyTypeManager.KeyFactory<EciesAeadHkdfKeyFormat, EciesAeadHkdfPrivateKey> keyFactory() {
        return new b(EciesAeadHkdfKeyFormat.class);
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    @Override // com.google.crypto.tink.PrivateKeyTypeManager
    public EciesAeadHkdfPublicKey getPublicKey(EciesAeadHkdfPrivateKey eciesAeadHkdfPrivateKey) throws GeneralSecurityException {
        return eciesAeadHkdfPrivateKey.getPublicKey();
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public EciesAeadHkdfPrivateKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return EciesAeadHkdfPrivateKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public void validateKey(EciesAeadHkdfPrivateKey eciesAeadHkdfPrivateKey) throws GeneralSecurityException {
        if (!eciesAeadHkdfPrivateKey.getKeyValue().isEmpty()) {
            Validators.validateVersion(eciesAeadHkdfPrivateKey.getVersion(), getVersion());
            com.google.crypto.tink.hybrid.c.d(eciesAeadHkdfPrivateKey.getPublicKey().getParams());
            return;
        }
        throw new GeneralSecurityException("invalid ECIES private key");
    }
}
