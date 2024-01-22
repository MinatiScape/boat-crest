package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KeyTypeManager;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.StreamingAead;
import com.google.crypto.tink.proto.AesGcmHkdfStreamingKey;
import com.google.crypto.tink.proto.AesGcmHkdfStreamingKeyFormat;
import com.google.crypto.tink.proto.AesGcmHkdfStreamingParams;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.AesGcmHkdfStreaming;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
/* loaded from: classes10.dex */
public final class AesGcmHkdfStreamingKeyManager extends KeyTypeManager<AesGcmHkdfStreamingKey> {

    /* loaded from: classes10.dex */
    public class a extends KeyTypeManager.PrimitiveFactory<StreamingAead, AesGcmHkdfStreamingKey> {
        public a(Class cls) {
            super(cls);
        }

        @Override // com.google.crypto.tink.KeyTypeManager.PrimitiveFactory
        /* renamed from: b */
        public StreamingAead getPrimitive(AesGcmHkdfStreamingKey aesGcmHkdfStreamingKey) throws GeneralSecurityException {
            return new AesGcmHkdfStreaming(aesGcmHkdfStreamingKey.getKeyValue().toByteArray(), e.a(aesGcmHkdfStreamingKey.getParams().getHkdfHashType()), aesGcmHkdfStreamingKey.getParams().getDerivedKeySize(), aesGcmHkdfStreamingKey.getParams().getCiphertextSegmentSize(), 0);
        }
    }

    /* loaded from: classes10.dex */
    public class b extends KeyTypeManager.KeyFactory<AesGcmHkdfStreamingKeyFormat, AesGcmHkdfStreamingKey> {
        public b(Class cls) {
            super(cls);
        }

        @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
        /* renamed from: a */
        public AesGcmHkdfStreamingKey createKey(AesGcmHkdfStreamingKeyFormat aesGcmHkdfStreamingKeyFormat) throws GeneralSecurityException {
            return AesGcmHkdfStreamingKey.newBuilder().setKeyValue(ByteString.copyFrom(Random.randBytes(aesGcmHkdfStreamingKeyFormat.getKeySize()))).setParams(aesGcmHkdfStreamingKeyFormat.getParams()).setVersion(AesGcmHkdfStreamingKeyManager.this.getVersion()).build();
        }

        @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
        /* renamed from: b */
        public AesGcmHkdfStreamingKey deriveKey(AesGcmHkdfStreamingKeyFormat aesGcmHkdfStreamingKeyFormat, InputStream inputStream) throws GeneralSecurityException {
            Validators.validateVersion(aesGcmHkdfStreamingKeyFormat.getVersion(), AesGcmHkdfStreamingKeyManager.this.getVersion());
            byte[] bArr = new byte[aesGcmHkdfStreamingKeyFormat.getKeySize()];
            try {
                if (inputStream.read(bArr) == aesGcmHkdfStreamingKeyFormat.getKeySize()) {
                    return AesGcmHkdfStreamingKey.newBuilder().setKeyValue(ByteString.copyFrom(bArr)).setParams(aesGcmHkdfStreamingKeyFormat.getParams()).setVersion(AesGcmHkdfStreamingKeyManager.this.getVersion()).build();
                }
                throw new GeneralSecurityException("Not enough pseudorandomness given");
            } catch (IOException e) {
                throw new GeneralSecurityException("Reading pseudorandomness failed", e);
            }
        }

        @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
        /* renamed from: c */
        public AesGcmHkdfStreamingKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
            return AesGcmHkdfStreamingKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
        /* renamed from: d */
        public void validateKeyFormat(AesGcmHkdfStreamingKeyFormat aesGcmHkdfStreamingKeyFormat) throws GeneralSecurityException {
            if (aesGcmHkdfStreamingKeyFormat.getKeySize() >= 16) {
                AesGcmHkdfStreamingKeyManager.d(aesGcmHkdfStreamingKeyFormat.getParams());
                return;
            }
            throw new GeneralSecurityException("key_size must be at least 16 bytes");
        }
    }

    public AesGcmHkdfStreamingKeyManager() {
        super(AesGcmHkdfStreamingKey.class, new a(StreamingAead.class));
    }

    public static final KeyTemplate aes128GcmHkdf1MBTemplate() {
        return c(16, HashType.SHA256, 16, 1048576);
    }

    public static final KeyTemplate aes128GcmHkdf4KBTemplate() {
        return c(16, HashType.SHA256, 16, 4096);
    }

    public static final KeyTemplate aes256GcmHkdf1MBTemplate() {
        return c(32, HashType.SHA256, 32, 1048576);
    }

    public static final KeyTemplate aes256GcmHkdf4KBTemplate() {
        return c(32, HashType.SHA256, 32, 4096);
    }

    public static KeyTemplate c(int i, HashType hashType, int i2, int i3) {
        return KeyTemplate.create(new AesGcmHkdfStreamingKeyManager().getKeyType(), AesGcmHkdfStreamingKeyFormat.newBuilder().setKeySize(i).setParams(AesGcmHkdfStreamingParams.newBuilder().setCiphertextSegmentSize(i3).setDerivedKeySize(i2).setHkdfHashType(hashType).build()).build().toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }

    public static void d(AesGcmHkdfStreamingParams aesGcmHkdfStreamingParams) throws GeneralSecurityException {
        Validators.validateAesKeySize(aesGcmHkdfStreamingParams.getDerivedKeySize());
        if (aesGcmHkdfStreamingParams.getHkdfHashType() != HashType.UNKNOWN_HASH) {
            if (aesGcmHkdfStreamingParams.getCiphertextSegmentSize() < aesGcmHkdfStreamingParams.getDerivedKeySize() + 7 + 16 + 2) {
                throw new GeneralSecurityException("ciphertext_segment_size must be at least (derived_key_size + NONCE_PREFIX_IN_BYTES + TAG_SIZE_IN_BYTES + 2)");
            }
            return;
        }
        throw new GeneralSecurityException("unknown HKDF hash type");
    }

    public static void register(boolean z) throws GeneralSecurityException {
        Registry.registerKeyManager(new AesGcmHkdfStreamingKeyManager(), z);
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesGcmHkdfStreamingKey";
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyTypeManager.KeyFactory<?, AesGcmHkdfStreamingKey> keyFactory() {
        return new b(AesGcmHkdfStreamingKeyFormat.class);
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.KeyTypeManager
    public AesGcmHkdfStreamingKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return AesGcmHkdfStreamingKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public void validateKey(AesGcmHkdfStreamingKey aesGcmHkdfStreamingKey) throws GeneralSecurityException {
        Validators.validateVersion(aesGcmHkdfStreamingKey.getVersion(), getVersion());
        d(aesGcmHkdfStreamingKey.getParams());
    }
}
