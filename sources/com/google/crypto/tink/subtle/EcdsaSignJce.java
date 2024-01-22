package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Enums;
import java.security.GeneralSecurityException;
import java.security.Signature;
import java.security.interfaces.ECPrivateKey;
/* loaded from: classes10.dex */
public final class EcdsaSignJce implements PublicKeySign {

    /* renamed from: a  reason: collision with root package name */
    public final ECPrivateKey f11022a;
    public final String b;
    public final EllipticCurves.EcdsaEncoding c;

    public EcdsaSignJce(ECPrivateKey eCPrivateKey, Enums.HashType hashType, EllipticCurves.EcdsaEncoding ecdsaEncoding) throws GeneralSecurityException {
        this.f11022a = eCPrivateKey;
        this.b = SubtleUtil.toEcdsaAlgo(hashType);
        this.c = ecdsaEncoding;
    }

    @Override // com.google.crypto.tink.PublicKeySign
    public byte[] sign(byte[] bArr) throws GeneralSecurityException {
        Signature engineFactory = EngineFactory.SIGNATURE.getInstance(this.b);
        engineFactory.initSign(this.f11022a);
        engineFactory.update(bArr);
        byte[] sign = engineFactory.sign();
        return this.c == EllipticCurves.EcdsaEncoding.IEEE_P1363 ? EllipticCurves.ecdsaDer2Ieee(sign, EllipticCurves.fieldSizeInBytes(this.f11022a.getParams().getCurve()) * 2) : sign;
    }
}
