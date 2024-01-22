package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.subtle.EngineWrapper;
import com.google.crypto.tink.subtle.Enums;
import java.security.GeneralSecurityException;
import java.security.Signature;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
/* loaded from: classes10.dex */
public final class RsaSsaPkcs1SignJce implements PublicKeySign {

    /* renamed from: a  reason: collision with root package name */
    public final RSAPrivateCrtKey f11042a;
    public final RSAPublicKey b;
    public final String c;

    public RsaSsaPkcs1SignJce(RSAPrivateCrtKey rSAPrivateCrtKey, Enums.HashType hashType) throws GeneralSecurityException {
        Validators.validateSignatureHash(hashType);
        Validators.validateRsaModulusSize(rSAPrivateCrtKey.getModulus().bitLength());
        Validators.validateRsaPublicExponent(rSAPrivateCrtKey.getPublicExponent());
        this.f11042a = rSAPrivateCrtKey;
        this.c = SubtleUtil.toRsaSsaPkcs1Algo(hashType);
        this.b = (RSAPublicKey) EngineFactory.KEY_FACTORY.getInstance("RSA").generatePublic(new RSAPublicKeySpec(rSAPrivateCrtKey.getModulus(), rSAPrivateCrtKey.getPublicExponent()));
    }

    @Override // com.google.crypto.tink.PublicKeySign
    public byte[] sign(byte[] bArr) throws GeneralSecurityException {
        EngineFactory<EngineWrapper.TSignature, Signature> engineFactory = EngineFactory.SIGNATURE;
        Signature engineFactory2 = engineFactory.getInstance(this.c);
        engineFactory2.initSign(this.f11042a);
        engineFactory2.update(bArr);
        byte[] sign = engineFactory2.sign();
        Signature engineFactory3 = engineFactory.getInstance(this.c);
        engineFactory3.initVerify(this.b);
        engineFactory3.update(bArr);
        if (engineFactory3.verify(sign)) {
            return sign;
        }
        throw new RuntimeException("Security bug: RSA signature computation error");
    }
}
