package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
@Immutable
/* loaded from: classes10.dex */
public final class q extends c {
    public final Mac h;
    public final Key i;
    public final String j;
    public final int k;
    public final boolean l;

    /* loaded from: classes10.dex */
    public static final class b extends com.google.common.hash.a {
        public final Mac b;
        public boolean c;

        @Override // com.google.common.hash.a
        public void b(byte b) {
            f();
            this.b.update(b);
        }

        @Override // com.google.common.hash.a
        public void c(ByteBuffer byteBuffer) {
            f();
            Preconditions.checkNotNull(byteBuffer);
            this.b.update(byteBuffer);
        }

        @Override // com.google.common.hash.a
        public void d(byte[] bArr) {
            f();
            this.b.update(bArr);
        }

        @Override // com.google.common.hash.a
        public void e(byte[] bArr, int i, int i2) {
            f();
            this.b.update(bArr, i, i2);
        }

        public final void f() {
            Preconditions.checkState(!this.c, "Cannot re-use a Hasher after calling hash() on it");
        }

        @Override // com.google.common.hash.Hasher
        public HashCode hash() {
            f();
            this.c = true;
            return HashCode.fromBytesNoCopy(this.b.doFinal());
        }

        public b(Mac mac) {
            this.b = mac;
        }
    }

    public q(String str, Key key, String str2) {
        Mac a2 = a(str, key);
        this.h = a2;
        this.i = (Key) Preconditions.checkNotNull(key);
        this.j = (String) Preconditions.checkNotNull(str2);
        this.k = a2.getMacLength() * 8;
        this.l = b(a2);
    }

    public static Mac a(String str, Key key) {
        try {
            Mac mac = Mac.getInstance(str);
            mac.init(key);
            return mac;
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        } catch (NoSuchAlgorithmException e2) {
            throw new IllegalStateException(e2);
        }
    }

    public static boolean b(Mac mac) {
        try {
            mac.clone();
            return true;
        } catch (CloneNotSupportedException unused) {
            return false;
        }
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return this.k;
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        if (this.l) {
            try {
                return new b((Mac) this.h.clone());
            } catch (CloneNotSupportedException unused) {
            }
        }
        return new b(a(this.h.getAlgorithm(), this.i));
    }

    public String toString() {
        return this.j;
    }
}
