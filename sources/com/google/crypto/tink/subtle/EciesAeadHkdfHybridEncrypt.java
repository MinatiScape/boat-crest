package com.google.crypto.tink.subtle;

import com.google.crypto.tink.HybridEncrypt;
import com.google.crypto.tink.subtle.EciesHkdfSenderKem;
import com.google.crypto.tink.subtle.EllipticCurves;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.interfaces.ECPublicKey;
/* loaded from: classes10.dex */
public final class EciesAeadHkdfHybridEncrypt implements HybridEncrypt {
    public static final byte[] f = new byte[0];

    /* renamed from: a  reason: collision with root package name */
    public final EciesHkdfSenderKem f11025a;
    public final String b;
    public final byte[] c;
    public final EllipticCurves.PointFormatType d;
    public final EciesAeadHkdfDemHelper e;

    public EciesAeadHkdfHybridEncrypt(ECPublicKey eCPublicKey, byte[] bArr, String str, EllipticCurves.PointFormatType pointFormatType, EciesAeadHkdfDemHelper eciesAeadHkdfDemHelper) throws GeneralSecurityException {
        EllipticCurves.b(eCPublicKey);
        this.f11025a = new EciesHkdfSenderKem(eCPublicKey);
        this.c = bArr;
        this.b = str;
        this.d = pointFormatType;
        this.e = eciesAeadHkdfDemHelper;
    }

    @Override // com.google.crypto.tink.HybridEncrypt
    public byte[] encrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        EciesHkdfSenderKem.KemKey generateKey = this.f11025a.generateKey(this.b, this.c, bArr2, this.e.getSymmetricKeySizeInBytes(), this.d);
        byte[] encrypt = this.e.getAead(generateKey.getSymmetricKey()).encrypt(bArr, f);
        byte[] kemBytes = generateKey.getKemBytes();
        return ByteBuffer.allocate(kemBytes.length + encrypt.length).put(kemBytes).put(encrypt).array();
    }
}
