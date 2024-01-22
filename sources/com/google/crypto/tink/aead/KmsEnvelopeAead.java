package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.KeyTemplate;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
/* loaded from: classes10.dex */
public final class KmsEnvelopeAead implements Aead {
    public static final byte[] c = new byte[0];

    /* renamed from: a  reason: collision with root package name */
    public final KeyTemplate f10836a;
    public final Aead b;

    public KmsEnvelopeAead(KeyTemplate keyTemplate, Aead aead) {
        this.f10836a = keyTemplate;
        this.b = aead;
    }

    public final byte[] a(byte[] bArr, byte[] bArr2) {
        return ByteBuffer.allocate(bArr.length + 4 + bArr2.length).putInt(bArr.length).put(bArr).put(bArr2).array();
    }

    @Override // com.google.crypto.tink.Aead
    public byte[] decrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        try {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            int i = wrap.getInt();
            if (i > 0 && i <= bArr.length - 4) {
                byte[] bArr3 = new byte[i];
                wrap.get(bArr3, 0, i);
                byte[] bArr4 = new byte[wrap.remaining()];
                wrap.get(bArr4, 0, wrap.remaining());
                return ((Aead) Registry.getPrimitive(this.f10836a.getTypeUrl(), this.b.decrypt(bArr3, c), Aead.class)).decrypt(bArr4, bArr2);
            }
            throw new GeneralSecurityException("invalid ciphertext");
        } catch (IndexOutOfBoundsException | NegativeArraySizeException | BufferUnderflowException e) {
            throw new GeneralSecurityException("invalid ciphertext", e);
        }
    }

    @Override // com.google.crypto.tink.Aead
    public byte[] encrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] byteArray = Registry.newKey(this.f10836a).toByteArray();
        return a(this.b.encrypt(byteArray, c), ((Aead) Registry.getPrimitive(this.f10836a.getTypeUrl(), byteArray, Aead.class)).encrypt(bArr, bArr2));
    }
}
