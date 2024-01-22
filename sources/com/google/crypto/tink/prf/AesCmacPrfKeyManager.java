package com.google.crypto.tink.prf;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KeyTypeManager;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.AesCmacPrfKey;
import com.google.crypto.tink.proto.AesCmacPrfKeyFormat;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.PrfAesCmac;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
/* loaded from: classes10.dex */
public final class AesCmacPrfKeyManager extends KeyTypeManager<AesCmacPrfKey> {

    /* loaded from: classes10.dex */
    public class a extends KeyTypeManager.PrimitiveFactory<Prf, AesCmacPrfKey> {
        public a(Class cls) {
            super(cls);
        }

        @Override // com.google.crypto.tink.KeyTypeManager.PrimitiveFactory
        /* renamed from: b */
        public Prf getPrimitive(AesCmacPrfKey aesCmacPrfKey) throws GeneralSecurityException {
            return new PrfAesCmac(aesCmacPrfKey.getKeyValue().toByteArray());
        }
    }

    /* loaded from: classes10.dex */
    public class b extends KeyTypeManager.KeyFactory<AesCmacPrfKeyFormat, AesCmacPrfKey> {
        public b(AesCmacPrfKeyManager aesCmacPrfKeyManager, Class cls) {
            super(cls);
        }

        @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
        /* renamed from: a */
        public AesCmacPrfKey createKey(AesCmacPrfKeyFormat aesCmacPrfKeyFormat) {
            return AesCmacPrfKey.newBuilder().setVersion(0).setKeyValue(ByteString.copyFrom(Random.randBytes(aesCmacPrfKeyFormat.getKeySize()))).build();
        }

        @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
        /* renamed from: b */
        public AesCmacPrfKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
            return AesCmacPrfKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
        /* renamed from: c */
        public void validateKeyFormat(AesCmacPrfKeyFormat aesCmacPrfKeyFormat) throws GeneralSecurityException {
            AesCmacPrfKeyManager.c(aesCmacPrfKeyFormat.getKeySize());
        }
    }

    public AesCmacPrfKeyManager() {
        super(AesCmacPrfKey.class, new a(Prf.class));
    }

    public static final KeyTemplate aes256CmacTemplate() {
        return KeyTemplate.create(new AesCmacPrfKeyManager().getKeyType(), AesCmacPrfKeyFormat.newBuilder().setKeySize(32).build().toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }

    public static void c(int i) throws GeneralSecurityException {
        if (i != 32) {
            throw new GeneralSecurityException("AesCmacPrfKey size wrong, must be 32 bytes");
        }
    }

    public static void register(boolean z) throws GeneralSecurityException {
        Registry.registerKeyManager(new AesCmacPrfKeyManager(), z);
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesCmacPrfKey";
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyTypeManager.KeyFactory<?, AesCmacPrfKey> keyFactory() {
        return new b(this, AesCmacPrfKeyFormat.class);
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.KeyTypeManager
    public AesCmacPrfKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return AesCmacPrfKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public void validateKey(AesCmacPrfKey aesCmacPrfKey) throws GeneralSecurityException {
        Validators.validateVersion(aesCmacPrfKey.getVersion(), getVersion());
        c(aesCmacPrfKey.getKeyValue().size());
    }
}
