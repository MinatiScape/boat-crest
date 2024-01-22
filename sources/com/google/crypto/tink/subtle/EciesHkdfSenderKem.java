package com.google.crypto.tink.subtle;

import com.google.crypto.tink.subtle.EllipticCurves;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
/* loaded from: classes10.dex */
public final class EciesHkdfSenderKem {

    /* renamed from: a  reason: collision with root package name */
    public ECPublicKey f11027a;

    /* loaded from: classes10.dex */
    public static final class KemKey {

        /* renamed from: a  reason: collision with root package name */
        public final ImmutableByteArray f11028a;
        public final ImmutableByteArray b;

        public KemKey(byte[] bArr, byte[] bArr2) {
            this.f11028a = ImmutableByteArray.of(bArr);
            this.b = ImmutableByteArray.of(bArr2);
        }

        public byte[] getKemBytes() {
            ImmutableByteArray immutableByteArray = this.f11028a;
            if (immutableByteArray == null) {
                return null;
            }
            return immutableByteArray.getBytes();
        }

        public byte[] getSymmetricKey() {
            ImmutableByteArray immutableByteArray = this.b;
            if (immutableByteArray == null) {
                return null;
            }
            return immutableByteArray.getBytes();
        }
    }

    public EciesHkdfSenderKem(ECPublicKey eCPublicKey) {
        this.f11027a = eCPublicKey;
    }

    public KemKey generateKey(String str, byte[] bArr, byte[] bArr2, int i, EllipticCurves.PointFormatType pointFormatType) throws GeneralSecurityException {
        KeyPair generateKeyPair = EllipticCurves.generateKeyPair(this.f11027a.getParams());
        ECPublicKey eCPublicKey = (ECPublicKey) generateKeyPair.getPublic();
        byte[] computeSharedSecret = EllipticCurves.computeSharedSecret((ECPrivateKey) generateKeyPair.getPrivate(), this.f11027a);
        byte[] pointEncode = EllipticCurves.pointEncode(eCPublicKey.getParams().getCurve(), pointFormatType, eCPublicKey.getW());
        return new KemKey(pointEncode, Hkdf.computeEciesHkdfSymmetricKey(pointEncode, computeSharedSecret, str, bArr, bArr2, i));
    }
}
