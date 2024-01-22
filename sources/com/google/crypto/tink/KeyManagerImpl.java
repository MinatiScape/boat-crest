package com.google.crypto.tink;

import com.google.crypto.tink.KeyTypeManager;
import com.google.crypto.tink.annotations.Alpha;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import java.security.GeneralSecurityException;
@Alpha
/* loaded from: classes10.dex */
public class KeyManagerImpl<PrimitiveT, KeyProtoT extends MessageLite> implements KeyManager<PrimitiveT> {

    /* renamed from: a  reason: collision with root package name */
    public final KeyTypeManager<KeyProtoT> f10818a;
    public final Class<PrimitiveT> b;

    /* loaded from: classes10.dex */
    public static class a<KeyFormatProtoT extends MessageLite, KeyProtoT extends MessageLite> {

        /* renamed from: a  reason: collision with root package name */
        public final KeyTypeManager.KeyFactory<KeyFormatProtoT, KeyProtoT> f10819a;

        public a(KeyTypeManager.KeyFactory<KeyFormatProtoT, KeyProtoT> keyFactory) {
            this.f10819a = keyFactory;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public KeyProtoT a(MessageLite messageLite) throws GeneralSecurityException {
            return (KeyProtoT) c((MessageLite) KeyManagerImpl.b(messageLite, "Expected proto of type " + this.f10819a.getKeyFormatClass().getName(), this.f10819a.getKeyFormatClass()));
        }

        public KeyProtoT b(ByteString byteString) throws GeneralSecurityException, InvalidProtocolBufferException {
            return c(this.f10819a.parseKeyFormat(byteString));
        }

        public final KeyProtoT c(KeyFormatProtoT keyformatprotot) throws GeneralSecurityException {
            this.f10819a.validateKeyFormat(keyformatprotot);
            return this.f10819a.createKey(keyformatprotot);
        }
    }

    public KeyManagerImpl(KeyTypeManager<KeyProtoT> keyTypeManager, Class<PrimitiveT> cls) {
        if (!keyTypeManager.supportedPrimitives().contains(cls) && !Void.class.equals(cls)) {
            throw new IllegalArgumentException(String.format("Given internalKeyMananger %s does not support primitive class %s", keyTypeManager.toString(), cls.getName()));
        }
        this.f10818a = keyTypeManager;
        this.b = cls;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <CastedT> CastedT b(Object obj, String str, Class<CastedT> cls) throws GeneralSecurityException {
        if (cls.isInstance(obj)) {
            return obj;
        }
        throw new GeneralSecurityException(str);
    }

    public final a<?, KeyProtoT> c() {
        return new a<>(this.f10818a.keyFactory());
    }

    public final PrimitiveT d(KeyProtoT keyprotot) throws GeneralSecurityException {
        if (!Void.class.equals(this.b)) {
            this.f10818a.validateKey(keyprotot);
            return (PrimitiveT) this.f10818a.getPrimitive(keyprotot, (Class<PrimitiveT>) this.b);
        }
        throw new GeneralSecurityException("Cannot create a primitive for Void");
    }

    @Override // com.google.crypto.tink.KeyManager
    public final boolean doesSupport(String str) {
        return str.equals(getKeyType());
    }

    @Override // com.google.crypto.tink.KeyManager
    public final String getKeyType() {
        return this.f10818a.getKeyType();
    }

    @Override // com.google.crypto.tink.KeyManager
    public final PrimitiveT getPrimitive(ByteString byteString) throws GeneralSecurityException {
        try {
            return d(this.f10818a.parseKey(byteString));
        } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("Failures parsing proto of type " + this.f10818a.getKeyClass().getName(), e);
        }
    }

    @Override // com.google.crypto.tink.KeyManager
    public final Class<PrimitiveT> getPrimitiveClass() {
        return this.b;
    }

    @Override // com.google.crypto.tink.KeyManager
    public int getVersion() {
        return this.f10818a.getVersion();
    }

    @Override // com.google.crypto.tink.KeyManager
    public final MessageLite newKey(ByteString byteString) throws GeneralSecurityException {
        try {
            return c().b(byteString);
        } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("Failures parsing proto of type " + this.f10818a.keyFactory().getKeyFormatClass().getName(), e);
        }
    }

    @Override // com.google.crypto.tink.KeyManager
    public final KeyData newKeyData(ByteString byteString) throws GeneralSecurityException {
        try {
            return KeyData.newBuilder().setTypeUrl(getKeyType()).setValue(c().b(byteString).toByteString()).setKeyMaterialType(this.f10818a.keyMaterialType()).build();
        } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("Unexpected proto", e);
        }
    }

    @Override // com.google.crypto.tink.KeyManager
    public final MessageLite newKey(MessageLite messageLite) throws GeneralSecurityException {
        return c().a(messageLite);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.crypto.tink.KeyManager
    public final PrimitiveT getPrimitive(MessageLite messageLite) throws GeneralSecurityException {
        return (PrimitiveT) d((MessageLite) b(messageLite, "Expected proto of type " + this.f10818a.getKeyClass().getName(), this.f10818a.getKeyClass()));
    }
}
