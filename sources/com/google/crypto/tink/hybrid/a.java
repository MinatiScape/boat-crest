package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.HybridEncrypt;
import com.google.crypto.tink.KeyTypeManager;
import com.google.crypto.tink.proto.EciesAeadHkdfParams;
import com.google.crypto.tink.proto.EciesAeadHkdfPublicKey;
import com.google.crypto.tink.proto.EciesHkdfKemParams;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.EciesAeadHkdfHybridEncrypt;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
/* loaded from: classes10.dex */
public class a extends KeyTypeManager<EciesAeadHkdfPublicKey> {

    /* renamed from: com.google.crypto.tink.hybrid.a$a  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public class C0531a extends KeyTypeManager.PrimitiveFactory<HybridEncrypt, EciesAeadHkdfPublicKey> {
        public C0531a(Class cls) {
            super(cls);
        }

        @Override // com.google.crypto.tink.KeyTypeManager.PrimitiveFactory
        /* renamed from: b */
        public HybridEncrypt getPrimitive(EciesAeadHkdfPublicKey eciesAeadHkdfPublicKey) throws GeneralSecurityException {
            EciesAeadHkdfParams params = eciesAeadHkdfPublicKey.getParams();
            EciesHkdfKemParams kemParams = params.getKemParams();
            return new EciesAeadHkdfHybridEncrypt(EllipticCurves.getEcPublicKey(c.a(kemParams.getCurveType()), eciesAeadHkdfPublicKey.getX().toByteArray(), eciesAeadHkdfPublicKey.getY().toByteArray()), kemParams.getHkdfSalt().toByteArray(), c.b(kemParams.getHkdfHashType()), c.c(params.getEcPointFormat()), new d(params.getDemParams().getAeadDem()));
        }
    }

    public a() {
        super(EciesAeadHkdfPublicKey.class, new C0531a(HybridEncrypt.class));
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    /* renamed from: b */
    public EciesAeadHkdfPublicKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return EciesAeadHkdfPublicKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    /* renamed from: c */
    public void validateKey(EciesAeadHkdfPublicKey eciesAeadHkdfPublicKey) throws GeneralSecurityException {
        Validators.validateVersion(eciesAeadHkdfPublicKey.getVersion(), getVersion());
        c.d(eciesAeadHkdfPublicKey.getParams());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey";
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
