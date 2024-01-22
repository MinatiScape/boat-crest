package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KeyTypeManager;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.AesGcmKey;
import com.google.crypto.tink.proto.AesGcmKeyFormat;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.AesGcmJce;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
/* loaded from: classes10.dex */
public final class AesGcmKeyManager extends KeyTypeManager<AesGcmKey> {

    /* loaded from: classes10.dex */
    public class a extends KeyTypeManager.PrimitiveFactory<Aead, AesGcmKey> {
        public a(Class cls) {
            super(cls);
        }

        @Override // com.google.crypto.tink.KeyTypeManager.PrimitiveFactory
        /* renamed from: b */
        public Aead getPrimitive(AesGcmKey aesGcmKey) throws GeneralSecurityException {
            return new AesGcmJce(aesGcmKey.getKeyValue().toByteArray());
        }
    }

    /* loaded from: classes10.dex */
    public class b extends KeyTypeManager.KeyFactory<AesGcmKeyFormat, AesGcmKey> {
        public b(Class cls) {
            super(cls);
        }

        @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
        /* renamed from: a */
        public AesGcmKey createKey(AesGcmKeyFormat aesGcmKeyFormat) throws GeneralSecurityException {
            return AesGcmKey.newBuilder().setKeyValue(ByteString.copyFrom(Random.randBytes(aesGcmKeyFormat.getKeySize()))).setVersion(AesGcmKeyManager.this.getVersion()).build();
        }

        @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
        /* renamed from: b */
        public AesGcmKey deriveKey(AesGcmKeyFormat aesGcmKeyFormat, InputStream inputStream) throws GeneralSecurityException {
            Validators.validateVersion(aesGcmKeyFormat.getVersion(), AesGcmKeyManager.this.getVersion());
            byte[] bArr = new byte[aesGcmKeyFormat.getKeySize()];
            try {
                if (inputStream.read(bArr) == aesGcmKeyFormat.getKeySize()) {
                    return AesGcmKey.newBuilder().setKeyValue(ByteString.copyFrom(bArr)).setVersion(AesGcmKeyManager.this.getVersion()).build();
                }
                throw new GeneralSecurityException("Not enough pseudorandomness given");
            } catch (IOException e) {
                throw new GeneralSecurityException("Reading pseudorandomness failed", e);
            }
        }

        @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
        /* renamed from: c */
        public AesGcmKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
            return AesGcmKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
        /* renamed from: d */
        public void validateKeyFormat(AesGcmKeyFormat aesGcmKeyFormat) throws GeneralSecurityException {
            Validators.validateAesKeySize(aesGcmKeyFormat.getKeySize());
        }
    }

    public AesGcmKeyManager() {
        super(AesGcmKey.class, new a(Aead.class));
    }

    public static final KeyTemplate aes128GcmTemplate() {
        return b(16, KeyTemplate.OutputPrefixType.TINK);
    }

    public static final KeyTemplate aes256GcmTemplate() {
        return b(32, KeyTemplate.OutputPrefixType.TINK);
    }

    public static KeyTemplate b(int i, KeyTemplate.OutputPrefixType outputPrefixType) {
        return KeyTemplate.create(new AesGcmKeyManager().getKeyType(), AesGcmKeyFormat.newBuilder().setKeySize(i).build().toByteArray(), outputPrefixType);
    }

    public static final KeyTemplate rawAes128GcmTemplate() {
        return b(16, KeyTemplate.OutputPrefixType.RAW);
    }

    public static final KeyTemplate rawAes256GcmTemplate() {
        return b(32, KeyTemplate.OutputPrefixType.RAW);
    }

    public static void register(boolean z) throws GeneralSecurityException {
        Registry.registerKeyManager(new AesGcmKeyManager(), z);
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesGcmKey";
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyTypeManager.KeyFactory<?, AesGcmKey> keyFactory() {
        return new b(AesGcmKeyFormat.class);
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.KeyTypeManager
    public AesGcmKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return AesGcmKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public void validateKey(AesGcmKey aesGcmKey) throws GeneralSecurityException {
        Validators.validateVersion(aesGcmKey.getVersion(), getVersion());
        Validators.validateAesKeySize(aesGcmKey.getKeyValue().size());
    }
}
