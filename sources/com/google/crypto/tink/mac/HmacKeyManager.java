package com.google.crypto.tink.mac;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KeyTypeManager;
import com.google.crypto.tink.Mac;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HmacKey;
import com.google.crypto.tink.proto.HmacKeyFormat;
import com.google.crypto.tink.proto.HmacParams;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.PrfHmacJce;
import com.google.crypto.tink.subtle.PrfMac;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import javax.crypto.spec.SecretKeySpec;
/* loaded from: classes10.dex */
public final class HmacKeyManager extends KeyTypeManager<HmacKey> {

    /* loaded from: classes10.dex */
    public class a extends KeyTypeManager.PrimitiveFactory<Mac, HmacKey> {
        public a(Class cls) {
            super(cls);
        }

        @Override // com.google.crypto.tink.KeyTypeManager.PrimitiveFactory
        /* renamed from: b */
        public Mac getPrimitive(HmacKey hmacKey) throws GeneralSecurityException {
            HashType hash = hmacKey.getParams().getHash();
            SecretKeySpec secretKeySpec = new SecretKeySpec(hmacKey.getKeyValue().toByteArray(), org.jose4j.keys.HmacKey.ALGORITHM);
            int tagSize = hmacKey.getParams().getTagSize();
            int i = c.f10859a[hash.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        return new PrfMac(new PrfHmacJce("HMACSHA512", secretKeySpec), tagSize);
                    }
                    throw new GeneralSecurityException("unknown hash");
                }
                return new PrfMac(new PrfHmacJce("HMACSHA256", secretKeySpec), tagSize);
            }
            return new PrfMac(new PrfHmacJce("HMACSHA1", secretKeySpec), tagSize);
        }
    }

    /* loaded from: classes10.dex */
    public class b extends KeyTypeManager.KeyFactory<HmacKeyFormat, HmacKey> {
        public b(Class cls) {
            super(cls);
        }

        @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
        /* renamed from: a */
        public HmacKey createKey(HmacKeyFormat hmacKeyFormat) throws GeneralSecurityException {
            return HmacKey.newBuilder().setVersion(HmacKeyManager.this.getVersion()).setParams(hmacKeyFormat.getParams()).setKeyValue(ByteString.copyFrom(Random.randBytes(hmacKeyFormat.getKeySize()))).build();
        }

        @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
        /* renamed from: b */
        public HmacKey deriveKey(HmacKeyFormat hmacKeyFormat, InputStream inputStream) throws GeneralSecurityException {
            Validators.validateVersion(hmacKeyFormat.getVersion(), HmacKeyManager.this.getVersion());
            byte[] bArr = new byte[hmacKeyFormat.getKeySize()];
            try {
                if (inputStream.read(bArr) == hmacKeyFormat.getKeySize()) {
                    return HmacKey.newBuilder().setVersion(HmacKeyManager.this.getVersion()).setParams(hmacKeyFormat.getParams()).setKeyValue(ByteString.copyFrom(bArr)).build();
                }
                throw new GeneralSecurityException("Not enough pseudorandomness given");
            } catch (IOException e) {
                throw new GeneralSecurityException("Reading pseudorandomness failed", e);
            }
        }

        @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
        /* renamed from: c */
        public HmacKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
            return HmacKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
        /* renamed from: d */
        public void validateKeyFormat(HmacKeyFormat hmacKeyFormat) throws GeneralSecurityException {
            if (hmacKeyFormat.getKeySize() >= 16) {
                HmacKeyManager.d(hmacKeyFormat.getParams());
                return;
            }
            throw new GeneralSecurityException("key too short");
        }
    }

    /* loaded from: classes10.dex */
    public static /* synthetic */ class c {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10859a;

        static {
            int[] iArr = new int[HashType.values().length];
            f10859a = iArr;
            try {
                iArr[HashType.SHA1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10859a[HashType.SHA256.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10859a[HashType.SHA512.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public HmacKeyManager() {
        super(HmacKey.class, new a(Mac.class));
    }

    public static KeyTemplate c(int i, int i2, HashType hashType) {
        return KeyTemplate.create(new HmacKeyManager().getKeyType(), HmacKeyFormat.newBuilder().setParams(HmacParams.newBuilder().setHash(hashType).setTagSize(i2).build()).setKeySize(i).build().toByteArray(), KeyTemplate.OutputPrefixType.TINK);
    }

    public static void d(HmacParams hmacParams) throws GeneralSecurityException {
        if (hmacParams.getTagSize() >= 10) {
            int i = c.f10859a[hmacParams.getHash().ordinal()];
            if (i == 1) {
                if (hmacParams.getTagSize() > 20) {
                    throw new GeneralSecurityException("tag size too big");
                }
                return;
            } else if (i == 2) {
                if (hmacParams.getTagSize() > 32) {
                    throw new GeneralSecurityException("tag size too big");
                }
                return;
            } else if (i == 3) {
                if (hmacParams.getTagSize() > 64) {
                    throw new GeneralSecurityException("tag size too big");
                }
                return;
            } else {
                throw new GeneralSecurityException("unknown hash type");
            }
        }
        throw new GeneralSecurityException("tag size too small");
    }

    public static final KeyTemplate hmacSha256HalfDigestTemplate() {
        return c(32, 16, HashType.SHA256);
    }

    public static final KeyTemplate hmacSha256Template() {
        return c(32, 32, HashType.SHA256);
    }

    public static final KeyTemplate hmacSha512HalfDigestTemplate() {
        return c(64, 32, HashType.SHA512);
    }

    public static final KeyTemplate hmacSha512Template() {
        return c(64, 64, HashType.SHA512);
    }

    public static void register(boolean z) throws GeneralSecurityException {
        Registry.registerKeyManager(new HmacKeyManager(), z);
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.HmacKey";
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyTypeManager.KeyFactory<?, HmacKey> keyFactory() {
        return new b(HmacKeyFormat.class);
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.KeyTypeManager
    public HmacKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return HmacKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public void validateKey(HmacKey hmacKey) throws GeneralSecurityException {
        Validators.validateVersion(hmacKey.getVersion(), getVersion());
        if (hmacKey.getKeyValue().size() >= 16) {
            d(hmacKey.getParams());
            return;
        }
        throw new GeneralSecurityException("key too short");
    }
}
