package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.common.hash.BloomFilter;
import com.google.common.math.LongMath;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLongArray;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes10.dex */
public abstract class g implements BloomFilter.c {
    public static final g MURMUR128_MITZ_32 = new a("MURMUR128_MITZ_32", 0);
    public static final g MURMUR128_MITZ_64 = new g("MURMUR128_MITZ_64", 1) { // from class: com.google.common.hash.g.b
        private long lowerEight(byte[] bArr) {
            return Longs.fromBytes(bArr[7], bArr[6], bArr[5], bArr[4], bArr[3], bArr[2], bArr[1], bArr[0]);
        }

        private long upperEight(byte[] bArr) {
            return Longs.fromBytes(bArr[15], bArr[14], bArr[13], bArr[12], bArr[11], bArr[10], bArr[9], bArr[8]);
        }

        @Override // com.google.common.hash.g, com.google.common.hash.BloomFilter.c
        public <T> boolean mightContain(T t, Funnel<? super T> funnel, int i, c cVar) {
            long b2 = cVar.b();
            byte[] bytesInternal = Hashing.murmur3_128().hashObject(t, funnel).getBytesInternal();
            long lowerEight = lowerEight(bytesInternal);
            long upperEight = upperEight(bytesInternal);
            for (int i2 = 0; i2 < i; i2++) {
                if (!cVar.d((Long.MAX_VALUE & lowerEight) % b2)) {
                    return false;
                }
                lowerEight += upperEight;
            }
            return true;
        }

        @Override // com.google.common.hash.g, com.google.common.hash.BloomFilter.c
        public <T> boolean put(T t, Funnel<? super T> funnel, int i, c cVar) {
            long b2 = cVar.b();
            byte[] bytesInternal = Hashing.murmur3_128().hashObject(t, funnel).getBytesInternal();
            long lowerEight = lowerEight(bytesInternal);
            long upperEight = upperEight(bytesInternal);
            boolean z = false;
            for (int i2 = 0; i2 < i; i2++) {
                z |= cVar.f((Long.MAX_VALUE & lowerEight) % b2);
                lowerEight += upperEight;
            }
            return z;
        }
    };
    private static final /* synthetic */ g[] $VALUES = $values();

    /* loaded from: classes10.dex */
    public enum a extends g {
        public a(String str, int i) {
            super(str, i, null);
        }

        @Override // com.google.common.hash.g, com.google.common.hash.BloomFilter.c
        public <T> boolean mightContain(T t, Funnel<? super T> funnel, int i, c cVar) {
            long b = cVar.b();
            long asLong = Hashing.murmur3_128().hashObject(t, funnel).asLong();
            int i2 = (int) asLong;
            int i3 = (int) (asLong >>> 32);
            for (int i4 = 1; i4 <= i; i4++) {
                int i5 = (i4 * i3) + i2;
                if (i5 < 0) {
                    i5 = ~i5;
                }
                if (!cVar.d(i5 % b)) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.common.hash.g, com.google.common.hash.BloomFilter.c
        public <T> boolean put(T t, Funnel<? super T> funnel, int i, c cVar) {
            long b = cVar.b();
            long asLong = Hashing.murmur3_128().hashObject(t, funnel).asLong();
            int i2 = (int) asLong;
            int i3 = (int) (asLong >>> 32);
            boolean z = false;
            for (int i4 = 1; i4 <= i; i4++) {
                int i5 = (i4 * i3) + i2;
                if (i5 < 0) {
                    i5 = ~i5;
                }
                z |= cVar.f(i5 % b);
            }
            return z;
        }
    }

    private static /* synthetic */ g[] $values() {
        return new g[]{MURMUR128_MITZ_32, MURMUR128_MITZ_64};
    }

    private g(String str, int i) {
    }

    public static g valueOf(String str) {
        return (g) Enum.valueOf(g.class, str);
    }

    public static g[] values() {
        return (g[]) $VALUES.clone();
    }

    @Override // com.google.common.hash.BloomFilter.c
    public abstract /* synthetic */ <T> boolean mightContain(T t, Funnel<? super T> funnel, int i, c cVar);

    @Override // com.google.common.hash.BloomFilter.c
    public abstract /* synthetic */ <T> boolean put(T t, Funnel<? super T> funnel, int i, c cVar);

    public /* synthetic */ g(String str, int i, a aVar) {
        this(str, i);
    }

    /* loaded from: classes10.dex */
    public static final class c {

        /* renamed from: a  reason: collision with root package name */
        public final AtomicLongArray f10655a;
        public final n b;

        public c(long j) {
            Preconditions.checkArgument(j > 0, "data length is zero!");
            this.f10655a = new AtomicLongArray(Ints.checkedCast(LongMath.divide(j, 64L, RoundingMode.CEILING)));
            this.b = o.a();
        }

        public static long[] g(AtomicLongArray atomicLongArray) {
            int length = atomicLongArray.length();
            long[] jArr = new long[length];
            for (int i = 0; i < length; i++) {
                jArr[i] = atomicLongArray.get(i);
            }
            return jArr;
        }

        public long a() {
            return this.b.sum();
        }

        public long b() {
            return this.f10655a.length() * 64;
        }

        public c c() {
            return new c(g(this.f10655a));
        }

        public boolean d(long j) {
            return ((1 << ((int) j)) & this.f10655a.get((int) (j >>> 6))) != 0;
        }

        public void e(c cVar) {
            long j;
            long j2;
            boolean z;
            Preconditions.checkArgument(this.f10655a.length() == cVar.f10655a.length(), "BitArrays must be of equal length (%s != %s)", this.f10655a.length(), cVar.f10655a.length());
            for (int i = 0; i < this.f10655a.length(); i++) {
                long j3 = cVar.f10655a.get(i);
                while (true) {
                    j = this.f10655a.get(i);
                    j2 = j | j3;
                    if (j == j2) {
                        z = false;
                        break;
                    } else if (this.f10655a.compareAndSet(i, j, j2)) {
                        z = true;
                        break;
                    }
                }
                if (z) {
                    this.b.add(Long.bitCount(j2) - Long.bitCount(j));
                }
            }
        }

        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof c) {
                return Arrays.equals(g(this.f10655a), g(((c) obj).f10655a));
            }
            return false;
        }

        public boolean f(long j) {
            long j2;
            long j3;
            if (d(j)) {
                return false;
            }
            int i = (int) (j >>> 6);
            long j4 = 1 << ((int) j);
            do {
                j2 = this.f10655a.get(i);
                j3 = j2 | j4;
                if (j2 == j3) {
                    return false;
                }
            } while (!this.f10655a.compareAndSet(i, j2, j3));
            this.b.increment();
            return true;
        }

        public int hashCode() {
            return Arrays.hashCode(g(this.f10655a));
        }

        public c(long[] jArr) {
            Preconditions.checkArgument(jArr.length > 0, "data length is zero!");
            this.f10655a = new AtomicLongArray(jArr);
            this.b = o.a();
            long j = 0;
            for (long j2 : jArr) {
                j += Long.bitCount(j2);
            }
            this.b.add(j);
        }
    }
}
