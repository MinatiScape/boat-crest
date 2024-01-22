package com.google.crypto.tink.prf;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KeyTypeManager;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HkdfPrfKey;
import com.google.crypto.tink.proto.HkdfPrfKeyFormat;
import com.google.crypto.tink.proto.HkdfPrfParams;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.Enums;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import com.google.crypto.tink.subtle.prf.HkdfStreamingPrf;
import com.google.crypto.tink.subtle.prf.PrfImpl;
import com.google.crypto.tink.subtle.prf.StreamingPrf;
import java.security.GeneralSecurityException;
/* loaded from: classes10.dex */
public class HkdfPrfKeyManager extends KeyTypeManager<HkdfPrfKey> {

    /* loaded from: classes10.dex */
    public class a extends KeyTypeManager.PrimitiveFactory<StreamingPrf, HkdfPrfKey> {
        public a(Class cls) {
            super(cls);
        }

        @Override // com.google.crypto.tink.KeyTypeManager.PrimitiveFactory
        /* renamed from: b */
        public StreamingPrf getPrimitive(HkdfPrfKey hkdfPrfKey) throws GeneralSecurityException {
            return new HkdfStreamingPrf(HkdfPrfKeyManager.e(hkdfPrfKey.getParams().getHash()), hkdfPrfKey.getKeyValue().toByteArray(), hkdfPrfKey.getParams().getSalt().toByteArray());
        }
    }

    /* loaded from: classes10.dex */
    public class b extends KeyTypeManager.PrimitiveFactory<Prf, HkdfPrfKey> {
        public b(Class cls) {
            super(cls);
        }

        @Override // com.google.crypto.tink.KeyTypeManager.PrimitiveFactory
        /* renamed from: b */
        public Prf getPrimitive(HkdfPrfKey hkdfPrfKey) throws GeneralSecurityException {
            return PrfImpl.wrap(new HkdfStreamingPrf(HkdfPrfKeyManager.e(hkdfPrfKey.getParams().getHash()), hkdfPrfKey.getKeyValue().toByteArray(), hkdfPrfKey.getParams().getSalt().toByteArray()));
        }
    }

    /* loaded from: classes10.dex */
    public class c extends KeyTypeManager.KeyFactory<HkdfPrfKeyFormat, HkdfPrfKey> {
        public c(Class cls) {
            super(cls);
        }

        @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
        /* renamed from: a */
        public HkdfPrfKey createKey(HkdfPrfKeyFormat hkdfPrfKeyFormat) throws GeneralSecurityException {
            return HkdfPrfKey.newBuilder().setKeyValue(ByteString.copyFrom(Random.randBytes(hkdfPrfKeyFormat.getKeySize()))).setVersion(HkdfPrfKeyManager.this.getVersion()).setParams(hkdfPrfKeyFormat.getParams()).build();
        }

        @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
        /* renamed from: b */
        public HkdfPrfKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
            return HkdfPrfKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
        /* renamed from: c */
        public void validateKeyFormat(HkdfPrfKeyFormat hkdfPrfKeyFormat) throws GeneralSecurityException {
            HkdfPrfKeyManager.f(hkdfPrfKeyFormat.getKeySize());
            HkdfPrfKeyManager.g(hkdfPrfKeyFormat.getParams());
        }
    }

    /* loaded from: classes10.dex */
    public static /* synthetic */ class d {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10862a;

        static {
            int[] iArr = new int[HashType.values().length];
            f10862a = iArr;
            try {
                iArr[HashType.SHA1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10862a[HashType.SHA256.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10862a[HashType.SHA384.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10862a[HashType.SHA512.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public HkdfPrfKeyManager() {
        super(HkdfPrfKey.class, new a(StreamingPrf.class), new b(Prf.class));
    }

    public static Enums.HashType e(HashType hashType) throws GeneralSecurityException {
        int i = d.f10862a[hashType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        return Enums.HashType.SHA512;
                    }
                    throw new GeneralSecurityException("HashType " + hashType.name() + " not known in");
                }
                return Enums.HashType.SHA384;
            }
            return Enums.HashType.SHA256;
        }
        return Enums.HashType.SHA1;
    }

    public static void f(int i) throws GeneralSecurityException {
        if (i < 32) {
            throw new GeneralSecurityException("Invalid HkdfPrfKey/HkdfPrfKeyFormat: Key size too short");
        }
    }

    public static void g(HkdfPrfParams hkdfPrfParams) throws GeneralSecurityException {
        if (hkdfPrfParams.getHash() != HashType.SHA256 && hkdfPrfParams.getHash() != HashType.SHA512) {
            throw new GeneralSecurityException("Invalid HkdfPrfKey/HkdfPrfKeyFormat: Unsupported hash");
        }
    }

    public static final KeyTemplate hkdfSha256Template() {
        return KeyTemplate.create(staticKeyType(), HkdfPrfKeyFormat.newBuilder().setKeySize(32).setParams(HkdfPrfParams.newBuilder().setHash(HashType.SHA256)).build().toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }

    public static void register(boolean z) throws GeneralSecurityException {
        Registry.registerKeyManager(new HkdfPrfKeyManager(), z);
    }

    public static String staticKeyType() {
        return new HkdfPrfKeyManager().getKeyType();
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.HkdfPrfKey";
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyTypeManager.KeyFactory<?, HkdfPrfKey> keyFactory() {
        return new c(HkdfPrfKeyFormat.class);
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.KeyTypeManager
    public HkdfPrfKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return HkdfPrfKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public void validateKey(HkdfPrfKey hkdfPrfKey) throws GeneralSecurityException {
        Validators.validateVersion(hkdfPrfKey.getVersion(), getVersion());
        f(hkdfPrfKey.getKeyValue().size());
        g(hkdfPrfKey.getParams());
    }
}
