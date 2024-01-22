package com.google.crypto.tink;

import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.KeysetInfo;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import java.io.IOException;
import java.security.GeneralSecurityException;
/* loaded from: classes10.dex */
public final class KeysetHandle {

    /* renamed from: a  reason: collision with root package name */
    public final Keyset f10825a;

    public KeysetHandle(Keyset keyset) {
        this.f10825a = keyset;
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x000e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(com.google.crypto.tink.proto.Keyset r4) throws java.security.GeneralSecurityException {
        /*
            java.util.List r4 = r4.getKeyList()
            java.util.Iterator r4 = r4.iterator()
        L8:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L5e
            java.lang.Object r0 = r4.next()
            com.google.crypto.tink.proto.Keyset$Key r0 = (com.google.crypto.tink.proto.Keyset.Key) r0
            com.google.crypto.tink.proto.KeyData r1 = r0.getKeyData()
            com.google.crypto.tink.proto.KeyData$KeyMaterialType r1 = r1.getKeyMaterialType()
            com.google.crypto.tink.proto.KeyData$KeyMaterialType r2 = com.google.crypto.tink.proto.KeyData.KeyMaterialType.UNKNOWN_KEYMATERIAL
            if (r1 == r2) goto L39
            com.google.crypto.tink.proto.KeyData r1 = r0.getKeyData()
            com.google.crypto.tink.proto.KeyData$KeyMaterialType r1 = r1.getKeyMaterialType()
            com.google.crypto.tink.proto.KeyData$KeyMaterialType r2 = com.google.crypto.tink.proto.KeyData.KeyMaterialType.SYMMETRIC
            if (r1 == r2) goto L39
            com.google.crypto.tink.proto.KeyData r1 = r0.getKeyData()
            com.google.crypto.tink.proto.KeyData$KeyMaterialType r1 = r1.getKeyMaterialType()
            com.google.crypto.tink.proto.KeyData$KeyMaterialType r2 = com.google.crypto.tink.proto.KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE
            if (r1 == r2) goto L39
            goto L8
        L39:
            java.security.GeneralSecurityException r4 = new java.security.GeneralSecurityException
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r2 = 0
            com.google.crypto.tink.proto.KeyData r3 = r0.getKeyData()
            com.google.crypto.tink.proto.KeyData$KeyMaterialType r3 = r3.getKeyMaterialType()
            r1[r2] = r3
            r2 = 1
            com.google.crypto.tink.proto.KeyData r0 = r0.getKeyData()
            java.lang.String r0 = r0.getTypeUrl()
            r1[r2] = r0
            java.lang.String r0 = "keyset contains key material of type %s for type url %s"
            java.lang.String r0 = java.lang.String.format(r0, r1)
            r4.<init>(r0)
            throw r4
        L5e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.KeysetHandle.a(com.google.crypto.tink.proto.Keyset):void");
    }

    public static void assertEnoughEncryptedKeyMaterial(EncryptedKeyset encryptedKeyset) throws GeneralSecurityException {
        if (encryptedKeyset == null || encryptedKeyset.getEncryptedKeyset().size() == 0) {
            throw new GeneralSecurityException("empty keyset");
        }
    }

    public static void assertEnoughKeyMaterial(Keyset keyset) throws GeneralSecurityException {
        if (keyset == null || keyset.getKeyCount() <= 0) {
            throw new GeneralSecurityException("empty keyset");
        }
    }

    public static KeyData b(KeyData keyData) throws GeneralSecurityException {
        if (keyData.getKeyMaterialType() == KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE) {
            KeyData publicKeyData = Registry.getPublicKeyData(keyData.getTypeUrl(), keyData.getValue());
            h(publicKeyData);
            return publicKeyData;
        }
        throw new GeneralSecurityException("The keyset contains a non-private key");
    }

    public static Keyset c(EncryptedKeyset encryptedKeyset, Aead aead) throws GeneralSecurityException {
        try {
            Keyset parseFrom = Keyset.parseFrom(aead.decrypt(encryptedKeyset.getEncryptedKeyset().toByteArray(), new byte[0]), ExtensionRegistryLite.getEmptyRegistry());
            assertEnoughKeyMaterial(parseFrom);
            return parseFrom;
        } catch (InvalidProtocolBufferException unused) {
            throw new GeneralSecurityException("invalid keyset, corrupted key material");
        }
    }

    public static EncryptedKeyset d(Keyset keyset, Aead aead) throws GeneralSecurityException {
        byte[] encrypt = aead.encrypt(keyset.toByteArray(), new byte[0]);
        try {
            if (Keyset.parseFrom(aead.decrypt(encrypt, new byte[0]), ExtensionRegistryLite.getEmptyRegistry()).equals(keyset)) {
                return EncryptedKeyset.newBuilder().setEncryptedKeyset(ByteString.copyFrom(encrypt)).setKeysetInfo(a.b(keyset)).build();
            }
            throw new GeneralSecurityException("cannot encrypt keyset");
        } catch (InvalidProtocolBufferException unused) {
            throw new GeneralSecurityException("invalid keyset, corrupted key material");
        }
    }

    public static final KeysetHandle e(Keyset keyset) throws GeneralSecurityException {
        assertEnoughKeyMaterial(keyset);
        return new KeysetHandle(keyset);
    }

    @Deprecated
    public static final KeysetHandle generateNew(com.google.crypto.tink.proto.KeyTemplate keyTemplate) throws GeneralSecurityException {
        return KeysetManager.withEmptyKeyset().rotate(keyTemplate).getKeysetHandle();
    }

    public static void h(KeyData keyData) throws GeneralSecurityException {
        Registry.getPrimitive(keyData);
    }

    public static final KeysetHandle read(KeysetReader keysetReader, Aead aead) throws GeneralSecurityException, IOException {
        EncryptedKeyset readEncrypted = keysetReader.readEncrypted();
        assertEnoughEncryptedKeyMaterial(readEncrypted);
        return new KeysetHandle(c(readEncrypted, aead));
    }

    public static final KeysetHandle readNoSecret(KeysetReader keysetReader) throws GeneralSecurityException, IOException {
        try {
            Keyset read = keysetReader.read();
            a(read);
            return e(read);
        } catch (InvalidProtocolBufferException unused) {
            throw new GeneralSecurityException("invalid keyset");
        }
    }

    public Keyset f() {
        return this.f10825a;
    }

    public final <B, P> P g(Class<P> cls, Class<B> cls2) throws GeneralSecurityException {
        return (P) Registry.wrap(Registry.getPrimitives(this, cls2), cls);
    }

    public KeysetInfo getKeysetInfo() {
        return a.b(this.f10825a);
    }

    public <P> P getPrimitive(Class<P> cls) throws GeneralSecurityException {
        Class<?> inputPrimitive = Registry.getInputPrimitive(cls);
        if (inputPrimitive != null) {
            return (P) g(cls, inputPrimitive);
        }
        throw new GeneralSecurityException("No wrapper found for " + cls.getName());
    }

    public KeysetHandle getPublicKeysetHandle() throws GeneralSecurityException {
        if (this.f10825a != null) {
            Keyset.Builder newBuilder = Keyset.newBuilder();
            for (Keyset.Key key : this.f10825a.getKeyList()) {
                newBuilder.addKey(Keyset.Key.newBuilder().mergeFrom((Keyset.Key.Builder) key).setKeyData(b(key.getKeyData())).build());
            }
            newBuilder.setPrimaryKeyId(this.f10825a.getPrimaryKeyId());
            return new KeysetHandle(newBuilder.build());
        }
        throw new GeneralSecurityException("cleartext keyset is not available");
    }

    public String toString() {
        return getKeysetInfo().toString();
    }

    public void write(KeysetWriter keysetWriter, Aead aead) throws GeneralSecurityException, IOException {
        keysetWriter.write(d(this.f10825a, aead));
    }

    public void writeNoSecret(KeysetWriter keysetWriter) throws GeneralSecurityException, IOException {
        a(this.f10825a);
        keysetWriter.write(this.f10825a);
    }

    public static final KeysetHandle generateNew(KeyTemplate keyTemplate) throws GeneralSecurityException {
        return KeysetManager.withEmptyKeyset().rotate(keyTemplate.b()).getKeysetHandle();
    }

    public static final KeysetHandle readNoSecret(byte[] bArr) throws GeneralSecurityException {
        try {
            Keyset parseFrom = Keyset.parseFrom(bArr, ExtensionRegistryLite.getEmptyRegistry());
            a(parseFrom);
            return e(parseFrom);
        } catch (InvalidProtocolBufferException unused) {
            throw new GeneralSecurityException("invalid keyset");
        }
    }

    public <P> P getPrimitive(KeyManager<P> keyManager, Class<P> cls) throws GeneralSecurityException {
        if (keyManager != null) {
            return (P) Registry.wrap(Registry.getPrimitives(this, keyManager, cls));
        }
        throw new IllegalArgumentException("customKeyManager must be non-null.");
    }
}
