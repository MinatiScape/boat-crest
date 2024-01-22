package com.google.crypto.tink;

import com.google.crypto.tink.annotations.Alpha;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import java.security.GeneralSecurityException;
@Alpha
/* loaded from: classes10.dex */
public class PrivateKeyManagerImpl<PrimitiveT, KeyProtoT extends MessageLite, PublicKeyProtoT extends MessageLite> extends KeyManagerImpl<PrimitiveT, KeyProtoT> implements PrivateKeyManager<PrimitiveT> {
    public final PrivateKeyTypeManager<KeyProtoT, PublicKeyProtoT> c;
    public final KeyTypeManager<PublicKeyProtoT> d;

    public PrivateKeyManagerImpl(PrivateKeyTypeManager<KeyProtoT, PublicKeyProtoT> privateKeyTypeManager, KeyTypeManager<PublicKeyProtoT> keyTypeManager, Class<PrimitiveT> cls) {
        super(privateKeyTypeManager, cls);
        this.c = privateKeyTypeManager;
        this.d = keyTypeManager;
    }

    @Override // com.google.crypto.tink.PrivateKeyManager
    public KeyData getPublicKeyData(ByteString byteString) throws GeneralSecurityException {
        try {
            KeyProtoT parseKey = this.c.parseKey(byteString);
            this.c.validateKey(parseKey);
            PublicKeyProtoT publicKey = this.c.getPublicKey(parseKey);
            this.d.validateKey(publicKey);
            return KeyData.newBuilder().setTypeUrl(this.d.getKeyType()).setValue(publicKey.toByteString()).setKeyMaterialType(this.d.keyMaterialType()).build();
        } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("expected serialized proto of type ", e);
        }
    }
}
