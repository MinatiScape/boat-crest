package org.jose4j.jwe.kdf;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.HashUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes13.dex */
public class ConcatKeyDerivationFunction implements ConcatenationKeyDerivationFunctionWithSha256 {
    public static final Logger c = LoggerFactory.getLogger(ConcatKeyDerivationFunction.class);

    /* renamed from: a  reason: collision with root package name */
    public int f15520a;
    public MessageDigest b;

    public ConcatKeyDerivationFunction(String str) {
        this.b = HashUtil.getMessageDigest(str);
        b();
    }

    public long a(int i) {
        return (int) Math.ceil(i / this.f15520a);
    }

    public final void b() {
        this.f15520a = ByteUtil.bitLength(this.b.getDigestLength());
        if (c()) {
            c.trace("Hash Algorithm: {} with hashlen: {} bits", this.b.getAlgorithm(), Integer.valueOf(this.f15520a));
        }
    }

    public final boolean c() {
        return false;
    }

    public byte[] kdf(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6) {
        if (c()) {
            c.trace("KDF:\n  z: " + ByteUtil.toDebugString(bArr) + "\n  keydatalen: " + i + "  algorithmId: " + ByteUtil.toDebugString(bArr2) + "\n  partyUInfo: " + ByteUtil.toDebugString(bArr3) + "\n  partyVInfo: " + ByteUtil.toDebugString(bArr4) + "\n  suppPubInfo: " + ByteUtil.toDebugString(bArr5) + "\n  suppPrivInfo: " + ByteUtil.toDebugString(bArr6));
        }
        return kdf(bArr, i, ByteUtil.concat(bArr2, bArr3, bArr4, bArr5, bArr6));
    }

    public ConcatKeyDerivationFunction(String str, String str2) {
        this.b = HashUtil.getMessageDigest(str, str2);
        b();
    }

    @Override // org.jose4j.jwe.kdf.ConcatenationKeyDerivationFunctionWithSha256
    public byte[] kdf(byte[] bArr, int i, byte[] bArr2) {
        long a2 = a(i);
        if (c()) {
            Logger logger = c;
            logger.trace("reps: {}", String.valueOf(a2));
            logger.trace("otherInfo: {}", ByteUtil.toDebugString(bArr2));
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i2 = 1; i2 <= a2; i2++) {
            byte[] bytes = ByteUtil.getBytes(i2);
            if (c()) {
                Logger logger2 = c;
                logger2.trace("rep {} hashing ", Integer.valueOf(i2));
                logger2.trace(" counter: {}", ByteUtil.toDebugString(bytes));
                logger2.trace(" z: {}", ByteUtil.toDebugString(bArr));
                logger2.trace(" otherInfo: {}", ByteUtil.toDebugString(bArr2));
            }
            this.b.update(bytes);
            this.b.update(bArr);
            this.b.update(bArr2);
            byte[] digest = this.b.digest();
            if (c()) {
                c.trace(" k({}): {}", Integer.valueOf(i2), ByteUtil.toDebugString(digest));
            }
            byteArrayOutputStream.write(digest, 0, digest.length);
        }
        int byteLength = ByteUtil.byteLength(i);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (c()) {
            c.trace("derived key material: {}", ByteUtil.toDebugString(byteArray));
        }
        if (byteArray.length != byteLength) {
            byteArray = ByteUtil.subArray(byteArray, 0, byteLength);
            if (c()) {
                c.trace("first {} bits of derived key material: {}", Integer.valueOf(i), ByteUtil.toDebugString(byteArray));
            }
        }
        if (c()) {
            c.trace("final derived key material: {}", ByteUtil.toDebugString(byteArray));
        }
        return byteArray;
    }
}
