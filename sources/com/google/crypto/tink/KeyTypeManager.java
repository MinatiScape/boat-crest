package com.google.crypto.tink;

import com.google.crypto.tink.annotations.Alpha;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
@Alpha
/* loaded from: classes10.dex */
public abstract class KeyTypeManager<KeyProtoT extends MessageLite> {

    /* renamed from: a  reason: collision with root package name */
    public final Class<KeyProtoT> f10822a;
    public final Map<Class<?>, PrimitiveFactory<?, KeyProtoT>> b;
    public final Class<?> c;

    /* loaded from: classes10.dex */
    public static abstract class KeyFactory<KeyFormatProtoT extends MessageLite, KeyT> {

        /* renamed from: a  reason: collision with root package name */
        public final Class<KeyFormatProtoT> f10823a;

        public KeyFactory(Class<KeyFormatProtoT> cls) {
            this.f10823a = cls;
        }

        public abstract KeyT createKey(KeyFormatProtoT keyformatprotot) throws GeneralSecurityException;

        public KeyT deriveKey(KeyFormatProtoT keyformatprotot, InputStream inputStream) throws GeneralSecurityException {
            throw new GeneralSecurityException("deriveKey not implemented for key of type " + this.f10823a.toString());
        }

        public final Class<KeyFormatProtoT> getKeyFormatClass() {
            return this.f10823a;
        }

        public abstract KeyFormatProtoT parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException;

        public abstract void validateKeyFormat(KeyFormatProtoT keyformatprotot) throws GeneralSecurityException;
    }

    /* loaded from: classes10.dex */
    public static abstract class PrimitiveFactory<PrimitiveT, KeyT> {

        /* renamed from: a  reason: collision with root package name */
        public final Class<PrimitiveT> f10824a;

        public PrimitiveFactory(Class<PrimitiveT> cls) {
            this.f10824a = cls;
        }

        public final Class<PrimitiveT> a() {
            return this.f10824a;
        }

        public abstract PrimitiveT getPrimitive(KeyT keyt) throws GeneralSecurityException;
    }

    @SafeVarargs
    public KeyTypeManager(Class<KeyProtoT> cls, PrimitiveFactory<?, KeyProtoT>... primitiveFactoryArr) {
        this.f10822a = cls;
        HashMap hashMap = new HashMap();
        for (PrimitiveFactory<?, KeyProtoT> primitiveFactory : primitiveFactoryArr) {
            if (!hashMap.containsKey(primitiveFactory.a())) {
                hashMap.put(primitiveFactory.a(), primitiveFactory);
            } else {
                throw new IllegalArgumentException("KeyTypeManager constructed with duplicate factories for primitive " + primitiveFactory.a().getCanonicalName());
            }
        }
        if (primitiveFactoryArr.length > 0) {
            this.c = primitiveFactoryArr[0].a();
        } else {
            this.c = Void.class;
        }
        this.b = Collections.unmodifiableMap(hashMap);
    }

    public final Class<?> a() {
        return this.c;
    }

    public final Class<KeyProtoT> getKeyClass() {
        return this.f10822a;
    }

    public abstract String getKeyType();

    public final <P> P getPrimitive(KeyProtoT keyprotot, Class<P> cls) throws GeneralSecurityException {
        PrimitiveFactory<?, KeyProtoT> primitiveFactory = this.b.get(cls);
        if (primitiveFactory != null) {
            return (P) primitiveFactory.getPrimitive(keyprotot);
        }
        throw new IllegalArgumentException("Requested primitive class " + cls.getCanonicalName() + " not supported.");
    }

    public abstract int getVersion();

    public KeyFactory<?, KeyProtoT> keyFactory() {
        throw new UnsupportedOperationException("Creating keys is not supported.");
    }

    public abstract KeyData.KeyMaterialType keyMaterialType();

    public abstract KeyProtoT parseKey(ByteString byteString) throws InvalidProtocolBufferException;

    public final Set<Class<?>> supportedPrimitives() {
        return this.b.keySet();
    }

    public abstract void validateKey(KeyProtoT keyprotot) throws GeneralSecurityException;
}
