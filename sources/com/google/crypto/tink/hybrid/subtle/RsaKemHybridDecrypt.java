package com.google.crypto.tink.hybrid.subtle;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.aead.subtle.AeadFactory;
import com.google.crypto.tink.subtle.Hkdf;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPrivateKey;
import javax.crypto.Cipher;
/* loaded from: classes10.dex */
public final class RsaKemHybridDecrypt implements HybridDecrypt {

    /* renamed from: a  reason: collision with root package name */
    public final RSAPrivateKey f10848a;
    public final String b;
    public final byte[] c;
    public final AeadFactory d;

    public RsaKemHybridDecrypt(RSAPrivateKey rSAPrivateKey, String str, byte[] bArr, AeadFactory aeadFactory) throws GeneralSecurityException {
        a.d(rSAPrivateKey.getModulus());
        this.f10848a = rSAPrivateKey;
        this.c = bArr;
        this.b = str;
        this.d = aeadFactory;
    }

    @Override // com.google.crypto.tink.HybridDecrypt
    public byte[] decrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int a2 = a.a(this.f10848a.getModulus());
        if (bArr.length >= a2) {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            byte[] bArr3 = new byte[a2];
            wrap.get(bArr3);
            Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
            cipher.init(2, this.f10848a);
            Aead createAead = this.d.createAead(Hkdf.computeHkdf(this.b, cipher.doFinal(bArr3), this.c, bArr2, this.d.getKeySizeInBytes()));
            byte[] bArr4 = new byte[wrap.remaining()];
            wrap.get(bArr4);
            return createAead.decrypt(bArr4, a.f10850a);
        }
        throw new GeneralSecurityException(String.format("Ciphertext must be of at least size %d bytes, but got %d", Integer.valueOf(a2), Integer.valueOf(bArr.length)));
    }
}
