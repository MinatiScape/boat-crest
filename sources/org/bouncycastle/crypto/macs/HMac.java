package org.bouncycastle.crypto.macs;

import java.util.Hashtable;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Memoable;
/* loaded from: classes12.dex */
public class HMac implements Mac {
    public static Hashtable h;

    /* renamed from: a  reason: collision with root package name */
    public Digest f14758a;
    public int b;
    public int c;
    public Memoable d;
    public Memoable e;
    public byte[] f;
    public byte[] g;

    static {
        Hashtable hashtable = new Hashtable();
        h = hashtable;
        hashtable.put("GOST3411", Integers.valueOf(32));
        h.put(MessageDigestAlgorithms.MD2, Integers.valueOf(16));
        h.put("MD4", Integers.valueOf(64));
        h.put(MessageDigestAlgorithms.MD5, Integers.valueOf(64));
        h.put("RIPEMD128", Integers.valueOf(64));
        h.put("RIPEMD160", Integers.valueOf(64));
        h.put("SHA-1", Integers.valueOf(64));
        h.put("SHA-224", Integers.valueOf(64));
        h.put("SHA-256", Integers.valueOf(64));
        h.put("SHA-384", Integers.valueOf(128));
        h.put("SHA-512", Integers.valueOf(128));
        h.put("Tiger", Integers.valueOf(64));
        h.put("Whirlpool", Integers.valueOf(64));
    }

    public HMac(Digest digest) {
        this(digest, a(digest));
    }

    public HMac(Digest digest, int i) {
        this.f14758a = digest;
        int digestSize = digest.getDigestSize();
        this.b = digestSize;
        this.c = i;
        this.f = new byte[i];
        this.g = new byte[i + digestSize];
    }

    public static int a(Digest digest) {
        if (digest instanceof ExtendedDigest) {
            return ((ExtendedDigest) digest).getByteLength();
        }
        Integer num = (Integer) h.get(digest.getAlgorithmName());
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalArgumentException("unknown digest passed: " + digest.getAlgorithmName());
    }

    public static void b(byte[] bArr, int i, byte b) {
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) (bArr[i2] ^ b);
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i) {
        this.f14758a.doFinal(this.g, this.c);
        Memoable memoable = this.e;
        if (memoable != null) {
            ((Memoable) this.f14758a).reset(memoable);
            Digest digest = this.f14758a;
            digest.update(this.g, this.c, digest.getDigestSize());
        } else {
            Digest digest2 = this.f14758a;
            byte[] bArr2 = this.g;
            digest2.update(bArr2, 0, bArr2.length);
        }
        int doFinal = this.f14758a.doFinal(bArr, i);
        int i2 = this.c;
        while (true) {
            byte[] bArr3 = this.g;
            if (i2 >= bArr3.length) {
                break;
            }
            bArr3[i2] = 0;
            i2++;
        }
        Memoable memoable2 = this.d;
        if (memoable2 != null) {
            ((Memoable) this.f14758a).reset(memoable2);
        } else {
            Digest digest3 = this.f14758a;
            byte[] bArr4 = this.f;
            digest3.update(bArr4, 0, bArr4.length);
        }
        return doFinal;
    }

    @Override // org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return this.f14758a.getAlgorithmName() + "/HMAC";
    }

    @Override // org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return this.b;
    }

    public Digest getUnderlyingDigest() {
        return this.f14758a;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) {
        byte[] bArr;
        this.f14758a.reset();
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        int length = key.length;
        if (length > this.c) {
            this.f14758a.update(key, 0, length);
            this.f14758a.doFinal(this.f, 0);
            length = this.b;
        } else {
            System.arraycopy(key, 0, this.f, 0, length);
        }
        while (true) {
            bArr = this.f;
            if (length >= bArr.length) {
                break;
            }
            bArr[length] = 0;
            length++;
        }
        System.arraycopy(bArr, 0, this.g, 0, this.c);
        b(this.f, this.c, (byte) 54);
        b(this.g, this.c, (byte) 92);
        Digest digest = this.f14758a;
        if (digest instanceof Memoable) {
            Memoable copy = ((Memoable) digest).copy();
            this.e = copy;
            ((Digest) copy).update(this.g, 0, this.c);
        }
        Digest digest2 = this.f14758a;
        byte[] bArr2 = this.f;
        digest2.update(bArr2, 0, bArr2.length);
        Digest digest3 = this.f14758a;
        if (digest3 instanceof Memoable) {
            this.d = ((Memoable) digest3).copy();
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public void reset() {
        this.f14758a.reset();
        Digest digest = this.f14758a;
        byte[] bArr = this.f;
        digest.update(bArr, 0, bArr.length);
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte b) {
        this.f14758a.update(b);
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte[] bArr, int i, int i2) {
        this.f14758a.update(bArr, i, i2);
    }
}
