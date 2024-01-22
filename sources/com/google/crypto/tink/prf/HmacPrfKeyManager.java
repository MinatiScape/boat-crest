package com.google.crypto.tink.prf;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KeyTypeManager;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HmacPrfKey;
import com.google.crypto.tink.proto.HmacPrfKeyFormat;
import com.google.crypto.tink.proto.HmacPrfParams;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.PrfHmacJce;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import javax.crypto.spec.SecretKeySpec;
import org.jose4j.keys.HmacKey;
/* loaded from: classes10.dex */
public final class HmacPrfKeyManager extends KeyTypeManager<HmacPrfKey> {

    /* loaded from: classes10.dex */
    public class a extends KeyTypeManager.PrimitiveFactory<Prf, HmacPrfKey> {
        public a(Class cls) {
            super(cls);
        }

        @Override // com.google.crypto.tink.KeyTypeManager.PrimitiveFactory
        /* renamed from: b */
        public Prf getPrimitive(HmacPrfKey hmacPrfKey) throws GeneralSecurityException {
            HashType hash = hmacPrfKey.getParams().getHash();
            SecretKeySpec secretKeySpec = new SecretKeySpec(hmacPrfKey.getKeyValue().toByteArray(), HmacKey.ALGORITHM);
            int i = c.f10863a[hash.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        return new PrfHmacJce("HMACSHA512", secretKeySpec);
                    }
                    throw new GeneralSecurityException("unknown hash");
                }
                return new PrfHmacJce("HMACSHA256", secretKeySpec);
            }
            return new PrfHmacJce("HMACSHA1", secretKeySpec);
        }
    }

    /* loaded from: classes10.dex */
    public class b extends KeyTypeManager.KeyFactory<HmacPrfKeyFormat, HmacPrfKey> {
        public b(Class cls) {
            super(cls);
        }

        @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
        /* renamed from: a */
        public HmacPrfKey createKey(HmacPrfKeyFormat hmacPrfKeyFormat) {
            return HmacPrfKey.newBuilder().setVersion(HmacPrfKeyManager.this.getVersion()).setParams(hmacPrfKeyFormat.getParams()).setKeyValue(ByteString.copyFrom(Random.randBytes(hmacPrfKeyFormat.getKeySize()))).build();
        }

        @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
        /* renamed from: b */
        public HmacPrfKey deriveKey(HmacPrfKeyFormat hmacPrfKeyFormat, InputStream inputStream) throws GeneralSecurityException {
            Validators.validateVersion(hmacPrfKeyFormat.getVersion(), HmacPrfKeyManager.this.getVersion());
            byte[] bArr = new byte[hmacPrfKeyFormat.getKeySize()];
            try {
                if (inputStream.read(bArr) == hmacPrfKeyFormat.getKeySize()) {
                    return HmacPrfKey.newBuilder().setVersion(HmacPrfKeyManager.this.getVersion()).setParams(hmacPrfKeyFormat.getParams()).setKeyValue(ByteString.copyFrom(bArr)).build();
                }
                throw new GeneralSecurityException("Not enough pseudorandomness given");
            } catch (IOException e) {
                throw new GeneralSecurityException("Reading pseudorandomness failed", e);
            }
        }

        @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
        /* renamed from: c */
        public HmacPrfKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
            return HmacPrfKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
        /* renamed from: d */
        public void validateKeyFormat(HmacPrfKeyFormat hmacPrfKeyFormat) throws GeneralSecurityException {
            if (hmacPrfKeyFormat.getKeySize() >= 16) {
                HmacPrfKeyManager.d(hmacPrfKeyFormat.getParams());
                return;
            }
            throw new GeneralSecurityException("key too short");
        }
    }

    /* loaded from: classes10.dex */
    public static /* synthetic */ class c {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10863a;

        static {
            int[] iArr = new int[HashType.values().length];
            f10863a = iArr;
            try {
                iArr[HashType.SHA1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10863a[HashType.SHA256.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10863a[HashType.SHA512.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public HmacPrfKeyManager() {
        super(HmacPrfKey.class, new a(Prf.class));
    }

    public static KeyTemplate c(int i, HashType hashType) {
        return KeyTemplate.create(new HmacPrfKeyManager().getKeyType(), HmacPrfKeyFormat.newBuilder().setParams(HmacPrfParams.newBuilder().setHash(hashType).build()).setKeySize(i).build().toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }

    public static void d(HmacPrfParams hmacPrfParams) throws GeneralSecurityException {
        if (hmacPrfParams.getHash() != HashType.SHA1 && hmacPrfParams.getHash() != HashType.SHA256 && hmacPrfParams.getHash() != HashType.SHA512) {
            throw new GeneralSecurityException("unknown hash type");
        }
    }

    public static final KeyTemplate hmacSha256Template() {
        return c(32, HashType.SHA256);
    }

    public static final KeyTemplate hmacSha512Template() {
        return c(64, HashType.SHA512);
    }

    public static void register(boolean z) throws GeneralSecurityException {
        Registry.registerKeyManager(new HmacPrfKeyManager(), z);
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.HmacPrfKey";
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyTypeManager.KeyFactory<?, HmacPrfKey> keyFactory() {
        return new b(HmacPrfKeyFormat.class);
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.KeyTypeManager
    public HmacPrfKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return HmacPrfKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public void validateKey(HmacPrfKey hmacPrfKey) throws GeneralSecurityException {
        Validators.validateVersion(hmacPrfKey.getVersion(), getVersion());
        if (hmacPrfKey.getKeyValue().size() >= 16) {
            d(hmacPrfKey.getParams());
            return;
        }
        throw new GeneralSecurityException("key too short");
    }
}
