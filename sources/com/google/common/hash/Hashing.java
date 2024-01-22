package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.zip.Adler32;
import java.util.zip.CRC32;
import java.util.zip.Checksum;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.apache.commons.codec.digest.MurmurHash3;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.jose4j.mac.MacUtil;
@Beta
/* loaded from: classes10.dex */
public final class Hashing {

    /* renamed from: a  reason: collision with root package name */
    public static final int f10644a = (int) System.currentTimeMillis();

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    @Immutable
    /* loaded from: classes10.dex */
    public static abstract class b implements k<Checksum> {
        public final HashFunction hashFunction;
        public static final b CRC_32 = new a("CRC_32", 0, "Hashing.crc32()");
        public static final b ADLER_32 = new C0512b("ADLER_32", 1, "Hashing.adler32()");
        private static final /* synthetic */ b[] $VALUES = $values();

        /* loaded from: classes10.dex */
        public enum a extends b {
            public a(String str, int i, String str2) {
                super(str, i, str2);
            }

            @Override // com.google.common.base.Supplier
            public Checksum get() {
                return new CRC32();
            }
        }

        /* renamed from: com.google.common.hash.Hashing$b$b  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public enum C0512b extends b {
            public C0512b(String str, int i, String str2) {
                super(str, i, str2);
            }

            @Override // com.google.common.base.Supplier
            public Checksum get() {
                return new Adler32();
            }
        }

        private static /* synthetic */ b[] $values() {
            return new b[]{CRC_32, ADLER_32};
        }

        public static b valueOf(String str) {
            return (b) Enum.valueOf(b.class, str);
        }

        public static b[] values() {
            return (b[]) $VALUES.clone();
        }

        private b(String str, int i, String str2) {
            this.hashFunction = new com.google.common.hash.h(this, 32, str2);
        }
    }

    /* loaded from: classes10.dex */
    public static final class c extends com.google.common.hash.b {
        @Override // com.google.common.hash.b
        public HashCode b(Hasher[] hasherArr) {
            byte[] bArr = new byte[bits() / 8];
            int i = 0;
            for (Hasher hasher : hasherArr) {
                HashCode hash = hasher.hash();
                i += hash.writeBytesTo(bArr, i, hash.bits() / 8);
            }
            return HashCode.fromBytesNoCopy(bArr);
        }

        @Override // com.google.common.hash.HashFunction
        public int bits() {
            int i = 0;
            for (HashFunction hashFunction : this.h) {
                i += hashFunction.bits();
            }
            return i;
        }

        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof c) {
                return Arrays.equals(this.h, ((c) obj).h);
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(this.h);
        }

        public c(HashFunction... hashFunctionArr) {
            super(hashFunctionArr);
            for (HashFunction hashFunction : hashFunctionArr) {
                Preconditions.checkArgument(hashFunction.bits() % 8 == 0, "the number of bits (%s) in hashFunction (%s) must be divisible by 8", hashFunction.bits(), (Object) hashFunction);
            }
        }
    }

    /* loaded from: classes10.dex */
    public static final class d {

        /* renamed from: a  reason: collision with root package name */
        public long f10645a;

        public d(long j) {
            this.f10645a = j;
        }

        public double a() {
            long j = (this.f10645a * MurmurHash3.NULL_HASHCODE) + 1;
            this.f10645a = j;
            return (((int) (j >>> 33)) + 1) / 2.147483648E9d;
        }
    }

    /* loaded from: classes10.dex */
    public static class e {

        /* renamed from: a  reason: collision with root package name */
        public static final HashFunction f10646a = new r(MessageDigestAlgorithms.MD5, "Hashing.md5()");
    }

    /* loaded from: classes10.dex */
    public static class f {

        /* renamed from: a  reason: collision with root package name */
        public static final HashFunction f10647a = new r("SHA-1", "Hashing.sha1()");
    }

    /* loaded from: classes10.dex */
    public static class g {

        /* renamed from: a  reason: collision with root package name */
        public static final HashFunction f10648a = new r("SHA-256", "Hashing.sha256()");
    }

    /* loaded from: classes10.dex */
    public static class h {

        /* renamed from: a  reason: collision with root package name */
        public static final HashFunction f10649a = new r("SHA-384", "Hashing.sha384()");
    }

    /* loaded from: classes10.dex */
    public static class i {

        /* renamed from: a  reason: collision with root package name */
        public static final HashFunction f10650a = new r("SHA-512", "Hashing.sha512()");
    }

    public static int a(int i2) {
        Preconditions.checkArgument(i2 > 0, "Number of bits must be positive");
        return (i2 + 31) & (-32);
    }

    public static HashFunction adler32() {
        return b.ADLER_32.hashFunction;
    }

    public static String b(String str, Key key) {
        return String.format("Hashing.%s(Key[algorithm=%s, format=%s])", str, key.getAlgorithm(), key.getFormat());
    }

    public static HashCode combineOrdered(Iterable<HashCode> iterable) {
        Iterator<HashCode> it = iterable.iterator();
        Preconditions.checkArgument(it.hasNext(), "Must be at least 1 hash code to combine.");
        int bits = it.next().bits() / 8;
        byte[] bArr = new byte[bits];
        for (HashCode hashCode : iterable) {
            byte[] asBytes = hashCode.asBytes();
            Preconditions.checkArgument(asBytes.length == bits, "All hashcodes must have the same bit length.");
            for (int i2 = 0; i2 < asBytes.length; i2++) {
                bArr[i2] = (byte) ((bArr[i2] * 37) ^ asBytes[i2]);
            }
        }
        return HashCode.fromBytesNoCopy(bArr);
    }

    public static HashCode combineUnordered(Iterable<HashCode> iterable) {
        Iterator<HashCode> it = iterable.iterator();
        Preconditions.checkArgument(it.hasNext(), "Must be at least 1 hash code to combine.");
        int bits = it.next().bits() / 8;
        byte[] bArr = new byte[bits];
        for (HashCode hashCode : iterable) {
            byte[] asBytes = hashCode.asBytes();
            Preconditions.checkArgument(asBytes.length == bits, "All hashcodes must have the same bit length.");
            for (int i2 = 0; i2 < asBytes.length; i2++) {
                bArr[i2] = (byte) (bArr[i2] + asBytes[i2]);
            }
        }
        return HashCode.fromBytesNoCopy(bArr);
    }

    public static HashFunction concatenating(HashFunction hashFunction, HashFunction hashFunction2, HashFunction... hashFunctionArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(hashFunction);
        arrayList.add(hashFunction2);
        arrayList.addAll(Arrays.asList(hashFunctionArr));
        return new c((HashFunction[]) arrayList.toArray(new HashFunction[0]));
    }

    public static int consistentHash(HashCode hashCode, int i2) {
        return consistentHash(hashCode.padToLong(), i2);
    }

    public static HashFunction crc32() {
        return b.CRC_32.hashFunction;
    }

    public static HashFunction crc32c() {
        return com.google.common.hash.i.h;
    }

    public static HashFunction farmHashFingerprint64() {
        return j.h;
    }

    public static HashFunction goodFastHash(int i2) {
        int a2 = a(i2);
        if (a2 == 32) {
            return t.GOOD_FAST_HASH_32;
        }
        if (a2 <= 128) {
            return s.GOOD_FAST_HASH_128;
        }
        int i3 = (a2 + 127) / 128;
        HashFunction[] hashFunctionArr = new HashFunction[i3];
        hashFunctionArr[0] = s.GOOD_FAST_HASH_128;
        int i4 = f10644a;
        for (int i5 = 1; i5 < i3; i5++) {
            i4 += 1500450271;
            hashFunctionArr[i5] = murmur3_128(i4);
        }
        return new c(hashFunctionArr);
    }

    public static HashFunction hmacMd5(Key key) {
        return new q("HmacMD5", key, b("hmacMd5", key));
    }

    public static HashFunction hmacSha1(Key key) {
        return new q("HmacSHA1", key, b("hmacSha1", key));
    }

    public static HashFunction hmacSha256(Key key) {
        return new q(MacUtil.HMAC_SHA256, key, b("hmacSha256", key));
    }

    public static HashFunction hmacSha512(Key key) {
        return new q(MacUtil.HMAC_SHA512, key, b("hmacSha512", key));
    }

    @Deprecated
    public static HashFunction md5() {
        return e.f10646a;
    }

    public static HashFunction murmur3_128(int i2) {
        return new s(i2);
    }

    public static HashFunction murmur3_32(int i2) {
        return new t(i2);
    }

    @Deprecated
    public static HashFunction sha1() {
        return f.f10647a;
    }

    public static HashFunction sha256() {
        return g.f10648a;
    }

    public static HashFunction sha384() {
        return h.f10649a;
    }

    public static HashFunction sha512() {
        return i.f10650a;
    }

    public static HashFunction sipHash24() {
        return u.SIP_HASH_24;
    }

    public static int consistentHash(long j, int i2) {
        int i3 = 0;
        Preconditions.checkArgument(i2 > 0, "buckets must be positive: %s", i2);
        d dVar = new d(j);
        while (true) {
            int a2 = (int) ((i3 + 1) / dVar.a());
            if (a2 < 0 || a2 >= i2) {
                break;
            }
            i3 = a2;
        }
        return i3;
    }

    public static HashFunction hmacMd5(byte[] bArr) {
        return hmacMd5(new SecretKeySpec((byte[]) Preconditions.checkNotNull(bArr), "HmacMD5"));
    }

    public static HashFunction hmacSha1(byte[] bArr) {
        return hmacSha1(new SecretKeySpec((byte[]) Preconditions.checkNotNull(bArr), "HmacSHA1"));
    }

    public static HashFunction hmacSha256(byte[] bArr) {
        return hmacSha256(new SecretKeySpec((byte[]) Preconditions.checkNotNull(bArr), MacUtil.HMAC_SHA256));
    }

    public static HashFunction hmacSha512(byte[] bArr) {
        return hmacSha512(new SecretKeySpec((byte[]) Preconditions.checkNotNull(bArr), MacUtil.HMAC_SHA512));
    }

    public static HashFunction murmur3_128() {
        return s.MURMUR3_128;
    }

    public static HashFunction murmur3_32() {
        return t.MURMUR3_32;
    }

    public static HashFunction sipHash24(long j, long j2) {
        return new u(2, 4, j, j2);
    }

    public static HashFunction concatenating(Iterable<HashFunction> iterable) {
        Preconditions.checkNotNull(iterable);
        ArrayList arrayList = new ArrayList();
        for (HashFunction hashFunction : iterable) {
            arrayList.add(hashFunction);
        }
        Preconditions.checkArgument(arrayList.size() > 0, "number of hash functions (%s) must be > 0", arrayList.size());
        return new c((HashFunction[]) arrayList.toArray(new HashFunction[0]));
    }
}
