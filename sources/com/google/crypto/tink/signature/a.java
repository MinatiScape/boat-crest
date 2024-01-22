package com.google.crypto.tink.signature;

import com.google.crypto.tink.KeyTypeManager;
import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.proto.EcdsaPublicKey;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.EcdsaVerifyJce;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
/* loaded from: classes10.dex */
public class a extends KeyTypeManager<EcdsaPublicKey> {

    /* renamed from: com.google.crypto.tink.signature.a$a  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public class C0534a extends KeyTypeManager.PrimitiveFactory<PublicKeyVerify, EcdsaPublicKey> {
        public C0534a(Class cls) {
            super(cls);
        }

        @Override // com.google.crypto.tink.KeyTypeManager.PrimitiveFactory
        /* renamed from: b */
        public PublicKeyVerify getPrimitive(EcdsaPublicKey ecdsaPublicKey) throws GeneralSecurityException {
            return new EcdsaVerifyJce(EllipticCurves.getEcPublicKey(f.a(ecdsaPublicKey.getParams().getCurve()), ecdsaPublicKey.getX().toByteArray(), ecdsaPublicKey.getY().toByteArray()), f.c(ecdsaPublicKey.getParams().getHashType()), f.b(ecdsaPublicKey.getParams().getEncoding()));
        }
    }

    public a() {
        super(EcdsaPublicKey.class, new C0534a(PublicKeyVerify.class));
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    /* renamed from: b */
    public EcdsaPublicKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return EcdsaPublicKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    /* renamed from: c */
    public void validateKey(EcdsaPublicKey ecdsaPublicKey) throws GeneralSecurityException {
        Validators.validateVersion(ecdsaPublicKey.getVersion(), getVersion());
        f.d(ecdsaPublicKey.getParams());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.EcdsaPublicKey";
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC;
    }
}
