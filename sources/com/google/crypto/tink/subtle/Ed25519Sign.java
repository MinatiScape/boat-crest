package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeySign;
import java.security.GeneralSecurityException;
import java.util.Arrays;
/* loaded from: classes10.dex */
public final class Ed25519Sign implements PublicKeySign {
    public static final int SECRET_KEY_LEN = 32;

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f11029a;
    public final byte[] b;

    /* loaded from: classes10.dex */
    public static final class KeyPair {

        /* renamed from: a  reason: collision with root package name */
        public final byte[] f11030a;
        public final byte[] b;

        public KeyPair(byte[] bArr, byte[] bArr2) {
            this.f11030a = bArr;
            this.b = bArr2;
        }

        public static KeyPair newKeyPair() throws GeneralSecurityException {
            byte[] randBytes = Random.randBytes(32);
            return new KeyPair(f.u(f.j(randBytes)), randBytes);
        }

        public byte[] getPrivateKey() {
            byte[] bArr = this.b;
            return Arrays.copyOf(bArr, bArr.length);
        }

        public byte[] getPublicKey() {
            byte[] bArr = this.f11030a;
            return Arrays.copyOf(bArr, bArr.length);
        }
    }

    public Ed25519Sign(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length == 32) {
            byte[] j = f.j(bArr);
            this.f11029a = j;
            this.b = f.u(j);
            return;
        }
        throw new IllegalArgumentException(String.format("Given private key's length is not %s", 32));
    }

    @Override // com.google.crypto.tink.PublicKeySign
    public byte[] sign(byte[] bArr) throws GeneralSecurityException {
        return f.w(bArr, this.b, this.f11029a);
    }
}
