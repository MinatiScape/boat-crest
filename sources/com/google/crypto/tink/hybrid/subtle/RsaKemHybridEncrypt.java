package com.google.crypto.tink.hybrid.subtle;

import com.google.crypto.tink.HybridEncrypt;
import com.google.crypto.tink.aead.subtle.AeadFactory;
import com.google.crypto.tink.subtle.Hkdf;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPublicKey;
import javax.crypto.Cipher;
/* loaded from: classes10.dex */
public final class RsaKemHybridEncrypt implements HybridEncrypt {

    /* renamed from: a  reason: collision with root package name */
    public final RSAPublicKey f10849a;
    public final String b;
    public final byte[] c;
    public final AeadFactory d;

    public RsaKemHybridEncrypt(RSAPublicKey rSAPublicKey, String str, byte[] bArr, AeadFactory aeadFactory) throws GeneralSecurityException {
        a.d(rSAPublicKey.getModulus());
        this.f10849a = rSAPublicKey;
        this.b = str;
        this.c = bArr;
        this.d = aeadFactory;
    }

    @Override // com.google.crypto.tink.HybridEncrypt
    public byte[] encrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] c = a.c(this.f10849a.getModulus());
        Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
        cipher.init(1, this.f10849a);
        byte[] doFinal = cipher.doFinal(c);
        byte[] encrypt = this.d.createAead(Hkdf.computeHkdf(this.b, c, this.c, bArr2, this.d.getKeySizeInBytes())).encrypt(bArr, a.f10850a);
        return ByteBuffer.allocate(doFinal.length + encrypt.length).put(doFinal).put(encrypt).array();
    }
}
