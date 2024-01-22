package com.google.crypto.tink;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;
/* loaded from: classes10.dex */
public final class Registry {

    /* renamed from: a  reason: collision with root package name */
    public static final Logger f10830a = Logger.getLogger(Registry.class.getName());
    public static final ConcurrentMap<String, f> b = new ConcurrentHashMap();
    public static final ConcurrentMap<String, e> c = new ConcurrentHashMap();
    public static final ConcurrentMap<String, Boolean> d = new ConcurrentHashMap();
    public static final ConcurrentMap<String, Catalogue<?>> e = new ConcurrentHashMap();
    public static final ConcurrentMap<Class<?>, PrimitiveWrapper<?, ?>> f = new ConcurrentHashMap();

    /* loaded from: classes10.dex */
    public class a implements f {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ KeyManager f10831a;

        public a(KeyManager keyManager) {
            this.f10831a = keyManager;
        }

        @Override // com.google.crypto.tink.Registry.f
        public Class<?> a() {
            return null;
        }

        @Override // com.google.crypto.tink.Registry.f
        public Class<?> b() {
            return this.f10831a.getClass();
        }

        @Override // com.google.crypto.tink.Registry.f
        public Set<Class<?>> c() {
            return Collections.singleton(this.f10831a.getPrimitiveClass());
        }

        @Override // com.google.crypto.tink.Registry.f
        public <Q> KeyManager<Q> d(Class<Q> cls) throws GeneralSecurityException {
            if (this.f10831a.getPrimitiveClass().equals(cls)) {
                return this.f10831a;
            }
            throw new InternalError("This should never be called, as we always first check supportedPrimitives.");
        }

        @Override // com.google.crypto.tink.Registry.f
        public KeyManager<?> e() {
            return this.f10831a;
        }
    }

    /* loaded from: classes10.dex */
    public class b implements f {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ KeyTypeManager f10832a;

        public b(KeyTypeManager keyTypeManager) {
            this.f10832a = keyTypeManager;
        }

        @Override // com.google.crypto.tink.Registry.f
        public Class<?> a() {
            return null;
        }

        @Override // com.google.crypto.tink.Registry.f
        public Class<?> b() {
            return this.f10832a.getClass();
        }

        @Override // com.google.crypto.tink.Registry.f
        public Set<Class<?>> c() {
            return this.f10832a.supportedPrimitives();
        }

        @Override // com.google.crypto.tink.Registry.f
        public <Q> KeyManager<Q> d(Class<Q> cls) throws GeneralSecurityException {
            try {
                return new KeyManagerImpl(this.f10832a, cls);
            } catch (IllegalArgumentException e) {
                throw new GeneralSecurityException("Primitive type not supported", e);
            }
        }

        @Override // com.google.crypto.tink.Registry.f
        public KeyManager<?> e() {
            KeyTypeManager keyTypeManager = this.f10832a;
            return new KeyManagerImpl(keyTypeManager, keyTypeManager.a());
        }
    }

    /* loaded from: classes10.dex */
    public class c implements f {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ PrivateKeyTypeManager f10833a;
        public final /* synthetic */ KeyTypeManager b;

        public c(PrivateKeyTypeManager privateKeyTypeManager, KeyTypeManager keyTypeManager) {
            this.f10833a = privateKeyTypeManager;
            this.b = keyTypeManager;
        }

        @Override // com.google.crypto.tink.Registry.f
        public Class<?> a() {
            return this.b.getClass();
        }

        @Override // com.google.crypto.tink.Registry.f
        public Class<?> b() {
            return this.f10833a.getClass();
        }

        @Override // com.google.crypto.tink.Registry.f
        public Set<Class<?>> c() {
            return this.f10833a.supportedPrimitives();
        }

        @Override // com.google.crypto.tink.Registry.f
        public <Q> KeyManager<Q> d(Class<Q> cls) throws GeneralSecurityException {
            try {
                return new PrivateKeyManagerImpl(this.f10833a, this.b, cls);
            } catch (IllegalArgumentException e) {
                throw new GeneralSecurityException("Primitive type not supported", e);
            }
        }

        @Override // com.google.crypto.tink.Registry.f
        public KeyManager<?> e() {
            PrivateKeyTypeManager privateKeyTypeManager = this.f10833a;
            return new PrivateKeyManagerImpl(privateKeyTypeManager, this.b, privateKeyTypeManager.a());
        }
    }

    /* loaded from: classes10.dex */
    public class d implements e {
        public d(KeyTypeManager keyTypeManager) {
        }
    }

    /* loaded from: classes10.dex */
    public interface e {
    }

    /* loaded from: classes10.dex */
    public interface f {
        Class<?> a();

        Class<?> b();

        Set<Class<?>> c();

        <P> KeyManager<P> d(Class<P> cls) throws GeneralSecurityException;

        KeyManager<?> e();
    }

    public static <T> T a(T t) {
        Objects.requireNonNull(t);
        return t;
    }

    @Deprecated
    public static synchronized void addCatalogue(String str, Catalogue<?> catalogue) throws GeneralSecurityException {
        synchronized (Registry.class) {
            if (str == null) {
                throw new IllegalArgumentException("catalogueName must be non-null.");
            }
            if (catalogue != null) {
                ConcurrentMap<String, Catalogue<?>> concurrentMap = e;
                Locale locale = Locale.US;
                if (concurrentMap.containsKey(str.toLowerCase(locale)) && !catalogue.getClass().equals(concurrentMap.get(str.toLowerCase(locale)).getClass())) {
                    Logger logger = f10830a;
                    logger.warning("Attempted overwrite of a catalogueName catalogue for name " + str);
                    throw new GeneralSecurityException("catalogue for name " + str + " has been already registered");
                }
                concurrentMap.put(str.toLowerCase(locale), catalogue);
            } else {
                throw new IllegalArgumentException("catalogue must be non-null.");
            }
        }
    }

    public static <P> f b(KeyManager<P> keyManager) {
        return new a(keyManager);
    }

    public static <KeyProtoT extends MessageLite> f c(KeyTypeManager<KeyProtoT> keyTypeManager) {
        return new b(keyTypeManager);
    }

    public static <KeyProtoT extends MessageLite> e d(KeyTypeManager<KeyProtoT> keyTypeManager) {
        return new d(keyTypeManager);
    }

    public static <KeyProtoT extends MessageLite, PublicKeyProtoT extends MessageLite> f e(PrivateKeyTypeManager<KeyProtoT, PublicKeyProtoT> privateKeyTypeManager, KeyTypeManager<PublicKeyProtoT> keyTypeManager) {
        return new c(privateKeyTypeManager, keyTypeManager);
    }

    public static synchronized void f(String str, Class<?> cls, boolean z) throws GeneralSecurityException {
        synchronized (Registry.class) {
            ConcurrentMap<String, f> concurrentMap = b;
            if (concurrentMap.containsKey(str)) {
                f fVar = concurrentMap.get(str);
                if (fVar.b().equals(cls)) {
                    if (z && !d.get(str).booleanValue()) {
                        throw new GeneralSecurityException("New keys are already disallowed for key type " + str);
                    }
                    return;
                }
                Logger logger = f10830a;
                logger.warning("Attempted overwrite of a registered key manager for key type " + str);
                throw new GeneralSecurityException(String.format("typeUrl (%s) is already registered with %s, cannot be re-registered with %s", str, fVar.b().getName(), cls.getName()));
            }
        }
    }

    public static synchronized f g(String str) throws GeneralSecurityException {
        f fVar;
        synchronized (Registry.class) {
            ConcurrentMap<String, f> concurrentMap = b;
            if (concurrentMap.containsKey(str)) {
                fVar = concurrentMap.get(str);
            } else {
                throw new GeneralSecurityException("No key manager found for key type " + str);
            }
        }
        return fVar;
    }

    @Deprecated
    public static Catalogue<?> getCatalogue(String str) throws GeneralSecurityException {
        if (str != null) {
            ConcurrentMap<String, Catalogue<?>> concurrentMap = e;
            Locale locale = Locale.US;
            Catalogue<?> catalogue = concurrentMap.get(str.toLowerCase(locale));
            if (catalogue == null) {
                String format = String.format("no catalogue found for %s. ", str);
                if (str.toLowerCase(locale).startsWith("tinkaead")) {
                    format = format + "Maybe call AeadConfig.register().";
                }
                if (!str.toLowerCase(locale).startsWith("tinkdeterministicaead")) {
                    if (!str.toLowerCase(locale).startsWith("tinkstreamingaead")) {
                        if (!str.toLowerCase(locale).startsWith("tinkhybriddecrypt") && !str.toLowerCase(locale).startsWith("tinkhybridencrypt")) {
                            if (!str.toLowerCase(locale).startsWith("tinkmac")) {
                                if (!str.toLowerCase(locale).startsWith("tinkpublickeysign") && !str.toLowerCase(locale).startsWith("tinkpublickeyverify")) {
                                    if (str.toLowerCase(locale).startsWith("tink")) {
                                        format = format + "Maybe call TinkConfig.register().";
                                    }
                                } else {
                                    format = format + "Maybe call SignatureConfig.register().";
                                }
                            } else {
                                format = format + "Maybe call MacConfig.register().";
                            }
                        } else {
                            format = format + "Maybe call HybridConfig.register().";
                        }
                    } else {
                        format = format + "Maybe call StreamingAeadConfig.register().";
                    }
                } else {
                    format = format + "Maybe call DeterministicAeadConfig.register().";
                }
                throw new GeneralSecurityException(format);
            }
            return catalogue;
        }
        throw new IllegalArgumentException("catalogueName must be non-null.");
    }

    public static Class<?> getInputPrimitive(Class<?> cls) {
        PrimitiveWrapper<?, ?> primitiveWrapper = f.get(cls);
        if (primitiveWrapper == null) {
            return null;
        }
        return primitiveWrapper.getInputPrimitiveClass();
    }

    @Deprecated
    public static <P> KeyManager<P> getKeyManager(String str) throws GeneralSecurityException {
        return h(str, null);
    }

    @Deprecated
    public static <P> P getPrimitive(String str, MessageLite messageLite) throws GeneralSecurityException {
        return (P) j(str, messageLite, null);
    }

    public static <P> PrimitiveSet<P> getPrimitives(KeysetHandle keysetHandle, Class<P> cls) throws GeneralSecurityException {
        return getPrimitives(keysetHandle, null, cls);
    }

    public static KeyData getPublicKeyData(String str, ByteString byteString) throws GeneralSecurityException {
        KeyManager keyManager = getKeyManager(str);
        if (keyManager instanceof PrivateKeyManager) {
            return ((PrivateKeyManager) keyManager).getPublicKeyData(byteString);
        }
        throw new GeneralSecurityException("manager for key type " + str + " is not a PrivateKeyManager");
    }

    public static KeyManager<?> getUntypedKeyManager(String str) throws GeneralSecurityException {
        return g(str).e();
    }

    public static <P> KeyManager<P> h(String str, Class<P> cls) throws GeneralSecurityException {
        f g = g(str);
        if (cls == null) {
            return (KeyManager<P>) g.e();
        }
        if (g.c().contains(cls)) {
            return g.d(cls);
        }
        throw new GeneralSecurityException("Primitive type " + cls.getName() + " not supported by key manager of type " + g.b() + ", supported primitives: " + l(g.c()));
    }

    public static <P> P i(String str, ByteString byteString, Class<P> cls) throws GeneralSecurityException {
        return (P) h(str, cls).getPrimitive(byteString);
    }

    public static <P> P j(String str, MessageLite messageLite, Class<P> cls) throws GeneralSecurityException {
        return (P) h(str, cls).getPrimitive(messageLite);
    }

    public static <P> PrimitiveSet<P> k(KeysetHandle keysetHandle, KeyManager<P> keyManager, Class<P> cls) throws GeneralSecurityException {
        P p;
        com.google.crypto.tink.a.e(keysetHandle.f());
        PrimitiveSet<P> newPrimitiveSet = PrimitiveSet.newPrimitiveSet(cls);
        for (Keyset.Key key : keysetHandle.f().getKeyList()) {
            if (key.getStatus() == KeyStatusType.ENABLED) {
                if (keyManager != null && keyManager.doesSupport(key.getKeyData().getTypeUrl())) {
                    p = keyManager.getPrimitive(key.getKeyData().getValue());
                } else {
                    p = (P) i(key.getKeyData().getTypeUrl(), key.getKeyData().getValue(), cls);
                }
                PrimitiveSet.Entry<P> addPrimitive = newPrimitiveSet.addPrimitive(p, key);
                if (key.getKeyId() == keysetHandle.f().getPrimaryKeyId()) {
                    newPrimitiveSet.setPrimary(addPrimitive);
                }
            }
        }
        return newPrimitiveSet;
    }

    public static String l(Set<Class<?>> set) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Class<?> cls : set) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(cls.getCanonicalName());
            z = false;
        }
        return sb.toString();
    }

    public static synchronized MessageLite newKey(com.google.crypto.tink.proto.KeyTemplate keyTemplate) throws GeneralSecurityException {
        MessageLite newKey;
        synchronized (Registry.class) {
            KeyManager<?> untypedKeyManager = getUntypedKeyManager(keyTemplate.getTypeUrl());
            if (d.get(keyTemplate.getTypeUrl()).booleanValue()) {
                newKey = untypedKeyManager.newKey(keyTemplate.getValue());
            } else {
                throw new GeneralSecurityException("newKey-operation not permitted for key type " + keyTemplate.getTypeUrl());
            }
        }
        return newKey;
    }

    public static synchronized KeyData newKeyData(com.google.crypto.tink.proto.KeyTemplate keyTemplate) throws GeneralSecurityException {
        KeyData newKeyData;
        synchronized (Registry.class) {
            KeyManager<?> untypedKeyManager = getUntypedKeyManager(keyTemplate.getTypeUrl());
            if (d.get(keyTemplate.getTypeUrl()).booleanValue()) {
                newKeyData = untypedKeyManager.newKeyData(keyTemplate.getValue());
            } else {
                throw new GeneralSecurityException("newKey-operation not permitted for key type " + keyTemplate.getTypeUrl());
            }
        }
        return newKeyData;
    }

    public static synchronized <KeyProtoT extends MessageLite, PublicKeyProtoT extends MessageLite> void registerAsymmetricKeyManagers(PrivateKeyTypeManager<KeyProtoT, PublicKeyProtoT> privateKeyTypeManager, KeyTypeManager<PublicKeyProtoT> keyTypeManager, boolean z) throws GeneralSecurityException {
        Class<?> a2;
        synchronized (Registry.class) {
            if (privateKeyTypeManager != null && keyTypeManager != null) {
                String keyType = privateKeyTypeManager.getKeyType();
                String keyType2 = keyTypeManager.getKeyType();
                f(keyType, privateKeyTypeManager.getClass(), z);
                f(keyType2, keyTypeManager.getClass(), false);
                if (!keyType.equals(keyType2)) {
                    ConcurrentMap<String, f> concurrentMap = b;
                    if (concurrentMap.containsKey(keyType) && (a2 = concurrentMap.get(keyType).a()) != null && !a2.equals(keyTypeManager.getClass())) {
                        Logger logger = f10830a;
                        logger.warning("Attempted overwrite of a registered key manager for key type " + keyType + " with inconsistent public key type " + keyType2);
                        throw new GeneralSecurityException(String.format("public key manager corresponding to %s is already registered with %s, cannot be re-registered with %s", privateKeyTypeManager.getClass().getName(), a2.getName(), keyTypeManager.getClass().getName()));
                    }
                    if (!concurrentMap.containsKey(keyType) || concurrentMap.get(keyType).a() == null) {
                        concurrentMap.put(keyType, e(privateKeyTypeManager, keyTypeManager));
                        c.put(keyType, d(privateKeyTypeManager));
                    }
                    ConcurrentMap<String, Boolean> concurrentMap2 = d;
                    concurrentMap2.put(keyType, Boolean.valueOf(z));
                    if (!concurrentMap.containsKey(keyType2)) {
                        concurrentMap.put(keyType2, c(keyTypeManager));
                    }
                    concurrentMap2.put(keyType2, Boolean.FALSE);
                } else {
                    throw new GeneralSecurityException("Private and public key type must be different.");
                }
            } else {
                throw new IllegalArgumentException("given key managers must be non-null.");
            }
        }
    }

    public static synchronized <P> void registerKeyManager(KeyManager<P> keyManager) throws GeneralSecurityException {
        synchronized (Registry.class) {
            registerKeyManager((KeyManager) keyManager, true);
        }
    }

    public static synchronized <B, P> void registerPrimitiveWrapper(PrimitiveWrapper<B, P> primitiveWrapper) throws GeneralSecurityException {
        synchronized (Registry.class) {
            if (primitiveWrapper != null) {
                Class<P> primitiveClass = primitiveWrapper.getPrimitiveClass();
                ConcurrentMap<Class<?>, PrimitiveWrapper<?, ?>> concurrentMap = f;
                if (concurrentMap.containsKey(primitiveClass)) {
                    PrimitiveWrapper<?, ?> primitiveWrapper2 = concurrentMap.get(primitiveClass);
                    if (!primitiveWrapper.getClass().equals(primitiveWrapper2.getClass())) {
                        Logger logger = f10830a;
                        logger.warning("Attempted overwrite of a registered SetWrapper for type " + primitiveClass);
                        throw new GeneralSecurityException(String.format("SetWrapper for primitive (%s) is already registered to be %s, cannot be re-registered with %s", primitiveClass.getName(), primitiveWrapper2.getClass().getName(), primitiveWrapper.getClass().getName()));
                    }
                }
                concurrentMap.put(primitiveClass, primitiveWrapper);
            } else {
                throw new IllegalArgumentException("wrapper must be non-null");
            }
        }
    }

    public static <B, P> P wrap(PrimitiveSet<B> primitiveSet, Class<P> cls) throws GeneralSecurityException {
        PrimitiveWrapper<?, ?> primitiveWrapper = f.get(cls);
        if (primitiveWrapper != null) {
            if (primitiveWrapper.getInputPrimitiveClass().equals(primitiveSet.getPrimitiveClass())) {
                return (P) primitiveWrapper.wrap(primitiveSet);
            }
            throw new GeneralSecurityException("Wrong input primitive class, expected " + primitiveWrapper.getInputPrimitiveClass() + ", got " + primitiveSet.getPrimitiveClass());
        }
        throw new GeneralSecurityException("No wrapper found for " + primitiveSet.getPrimitiveClass().getName());
    }

    public static <P> KeyManager<P> getKeyManager(String str, Class<P> cls) throws GeneralSecurityException {
        return h(str, (Class) a(cls));
    }

    public static <P> P getPrimitive(String str, MessageLite messageLite, Class<P> cls) throws GeneralSecurityException {
        return (P) j(str, messageLite, (Class) a(cls));
    }

    public static <P> PrimitiveSet<P> getPrimitives(KeysetHandle keysetHandle, KeyManager<P> keyManager, Class<P> cls) throws GeneralSecurityException {
        return k(keysetHandle, keyManager, (Class) a(cls));
    }

    @Deprecated
    public static <P> P getPrimitive(String str, ByteString byteString) throws GeneralSecurityException {
        return (P) i(str, byteString, null);
    }

    public static synchronized <P> void registerKeyManager(KeyManager<P> keyManager, boolean z) throws GeneralSecurityException {
        synchronized (Registry.class) {
            if (keyManager != null) {
                String keyType = keyManager.getKeyType();
                f(keyType, keyManager.getClass(), z);
                b.putIfAbsent(keyType, b(keyManager));
                d.put(keyType, Boolean.valueOf(z));
            } else {
                throw new IllegalArgumentException("key manager must be non-null.");
            }
        }
    }

    public static <P> P getPrimitive(String str, ByteString byteString, Class<P> cls) throws GeneralSecurityException {
        return (P) i(str, byteString, (Class) a(cls));
    }

    @Deprecated
    public static <P> P getPrimitive(String str, byte[] bArr) throws GeneralSecurityException {
        return (P) getPrimitive(str, ByteString.copyFrom(bArr));
    }

    public static <P> P getPrimitive(String str, byte[] bArr, Class<P> cls) throws GeneralSecurityException {
        return (P) getPrimitive(str, ByteString.copyFrom(bArr), cls);
    }

    public static synchronized MessageLite newKey(String str, MessageLite messageLite) throws GeneralSecurityException {
        MessageLite newKey;
        synchronized (Registry.class) {
            KeyManager keyManager = getKeyManager(str);
            if (d.get(str).booleanValue()) {
                newKey = keyManager.newKey(messageLite);
            } else {
                throw new GeneralSecurityException("newKey-operation not permitted for key type " + str);
            }
        }
        return newKey;
    }

    public static synchronized KeyData newKeyData(KeyTemplate keyTemplate) throws GeneralSecurityException {
        KeyData newKeyData;
        synchronized (Registry.class) {
            newKeyData = newKeyData(keyTemplate.b());
        }
        return newKeyData;
    }

    @Deprecated
    public static <P> P getPrimitive(KeyData keyData) throws GeneralSecurityException {
        return (P) getPrimitive(keyData.getTypeUrl(), keyData.getValue());
    }

    public static <P> P getPrimitive(KeyData keyData, Class<P> cls) throws GeneralSecurityException {
        return (P) getPrimitive(keyData.getTypeUrl(), keyData.getValue(), cls);
    }

    public static synchronized <KeyProtoT extends MessageLite> void registerKeyManager(KeyTypeManager<KeyProtoT> keyTypeManager, boolean z) throws GeneralSecurityException {
        synchronized (Registry.class) {
            if (keyTypeManager != null) {
                String keyType = keyTypeManager.getKeyType();
                f(keyType, keyTypeManager.getClass(), z);
                ConcurrentMap<String, f> concurrentMap = b;
                if (!concurrentMap.containsKey(keyType)) {
                    concurrentMap.put(keyType, c(keyTypeManager));
                    c.put(keyType, d(keyTypeManager));
                }
                d.put(keyType, Boolean.valueOf(z));
            } else {
                throw new IllegalArgumentException("key manager must be non-null.");
            }
        }
    }

    public static <P> P wrap(PrimitiveSet<P> primitiveSet) throws GeneralSecurityException {
        return (P) wrap(primitiveSet, primitiveSet.getPrimitiveClass());
    }

    @Deprecated
    public static synchronized <P> void registerKeyManager(String str, KeyManager<P> keyManager) throws GeneralSecurityException {
        synchronized (Registry.class) {
            registerKeyManager(str, keyManager, true);
        }
    }

    @Deprecated
    public static synchronized <P> void registerKeyManager(String str, KeyManager<P> keyManager, boolean z) throws GeneralSecurityException {
        synchronized (Registry.class) {
            try {
                if (keyManager != null) {
                    if (str.equals(keyManager.getKeyType())) {
                        registerKeyManager(keyManager, z);
                    } else {
                        throw new GeneralSecurityException("Manager does not support key type " + str + ".");
                    }
                } else {
                    throw new IllegalArgumentException("key manager must be non-null.");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
