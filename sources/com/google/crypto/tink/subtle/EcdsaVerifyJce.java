package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Enums;
import java.security.GeneralSecurityException;
import java.security.Signature;
import java.security.interfaces.ECPublicKey;
/* loaded from: classes10.dex */
public final class EcdsaVerifyJce implements PublicKeyVerify {

    /* renamed from: a  reason: collision with root package name */
    public final ECPublicKey f11023a;
    public final String b;
    public final EllipticCurves.EcdsaEncoding c;

    public EcdsaVerifyJce(ECPublicKey eCPublicKey, Enums.HashType hashType, EllipticCurves.EcdsaEncoding ecdsaEncoding) throws GeneralSecurityException {
        EllipticCurves.b(eCPublicKey);
        this.b = SubtleUtil.toEcdsaAlgo(hashType);
        this.f11023a = eCPublicKey;
        this.c = ecdsaEncoding;
    }

    @Override // com.google.crypto.tink.PublicKeyVerify
    public void verify(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        boolean z;
        if (this.c == EllipticCurves.EcdsaEncoding.IEEE_P1363) {
            if (bArr.length == EllipticCurves.fieldSizeInBytes(this.f11023a.getParams().getCurve()) * 2) {
                bArr = EllipticCurves.ecdsaIeee2Der(bArr);
            } else {
                throw new GeneralSecurityException("Invalid signature");
            }
        }
        if (EllipticCurves.isValidDerEncoding(bArr)) {
            Signature engineFactory = EngineFactory.SIGNATURE.getInstance(this.b);
            engineFactory.initVerify(this.f11023a);
            engineFactory.update(bArr2);
            try {
                z = engineFactory.verify(bArr);
            } catch (RuntimeException unused) {
                z = false;
            }
            if (!z) {
                throw new GeneralSecurityException("Invalid signature");
            }
            return;
        }
        throw new GeneralSecurityException("Invalid signature");
    }
}
