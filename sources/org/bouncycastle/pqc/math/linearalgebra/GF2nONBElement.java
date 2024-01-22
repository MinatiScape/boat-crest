package org.bouncycastle.pqc.math.linearalgebra;

import android.support.v4.media.session.PlaybackStateCompat;
import com.clevertap.android.sdk.Constants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.common.primitives.Longs;
import com.h6ah4i.android.widget.advrecyclerview.adapter.ItemIdComposer;
import com.jstyle.blesdk1860.constant.BleConst;
import java.math.BigInteger;
import java.security.SecureRandom;
import kotlin.time.DurationKt;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import okhttp3.internal.ws.WebSocketProtocol;
import org.apache.commons.codec.net.a;
/* loaded from: classes13.dex */
public class GF2nONBElement extends GF2nElement {
    public static final long[] d = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH, PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM, PlaybackStateCompat.ACTION_PLAY_FROM_URI, 16384, PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID, PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH, PlaybackStateCompat.ACTION_PREPARE_FROM_URI, PlaybackStateCompat.ACTION_SET_REPEAT_MODE, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED, PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE, 4194304, 8388608, 16777216, 33554432, 67108864, 134217728, 268435456, 536870912, 1073741824, 2147483648L, 4294967296L, 8589934592L, 17179869184L, 34359738368L, 68719476736L, 137438953472L, 274877906944L, 549755813888L, 1099511627776L, 2199023255552L, 4398046511104L, 8796093022208L, 17592186044416L, 35184372088832L, 70368744177664L, 140737488355328L, 281474976710656L, 562949953421312L, 1125899906842624L, 2251799813685248L, 4503599627370496L, 9007199254740992L, 18014398509481984L, 36028797018963968L, 72057594037927936L, 144115188075855872L, 288230376151711744L, 576460752303423488L, LockFreeTaskQueueCore.FROZEN_MASK, LockFreeTaskQueueCore.CLOSED_MASK, Longs.MAX_POWER_OF_TWO, Long.MIN_VALUE};
    public static final long[] e = {1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, WebSocketProtocol.PAYLOAD_SHORT_MAX, 131071, 262143, 524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431, 67108863, 134217727, ItemIdComposer.BIT_MASK_CHILD_ID, 536870911, LockFreeTaskQueueCore.HEAD_MASK, 2147483647L, 4294967295L, 8589934591L, 17179869183L, 34359738367L, 68719476735L, 137438953471L, 274877906943L, 549755813887L, 1099511627775L, 2199023255551L, 4398046511103L, 8796093022207L, 17592186044415L, 35184372088831L, 70368744177663L, 140737488355327L, 281474976710655L, 562949953421311L, 1125899906842623L, 2251799813685247L, 4503599627370495L, 9007199254740991L, 18014398509481983L, ItemIdComposer.MAX_WRAPPED_ID, 72057594037927935L, 144115188075855871L, 288230376151711743L, 576460752303423487L, 1152921504606846975L, 2305843009213693951L, DurationKt.MAX_MILLIS, Long.MAX_VALUE, -1};
    public static final int[] f = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};

    /* renamed from: a  reason: collision with root package name */
    public int f15368a;
    public int b;
    public long[] c;

    public GF2nONBElement(GF2nONBElement gF2nONBElement) {
        GF2nField gF2nField = gF2nONBElement.mField;
        this.mField = gF2nField;
        this.mDegree = gF2nField.getDegree();
        this.f15368a = ((GF2nONBField) this.mField).e();
        this.b = ((GF2nONBField) this.mField).d();
        this.c = new long[this.f15368a];
        e(gF2nONBElement.f());
    }

    public GF2nONBElement(GF2nONBField gF2nONBField, BigInteger bigInteger) {
        this.mField = gF2nONBField;
        this.mDegree = gF2nONBField.getDegree();
        this.f15368a = gF2nONBField.e();
        this.b = gF2nONBField.d();
        this.c = new long[this.f15368a];
        c(bigInteger);
    }

    public GF2nONBElement(GF2nONBField gF2nONBField, SecureRandom secureRandom) {
        this.mField = gF2nONBField;
        this.mDegree = gF2nONBField.getDegree();
        this.f15368a = gF2nONBField.e();
        this.b = gF2nONBField.d();
        int i = this.f15368a;
        long[] jArr = new long[i];
        this.c = jArr;
        if (i <= 1) {
            jArr[0] = secureRandom.nextLong();
            long[] jArr2 = this.c;
            jArr2[0] = jArr2[0] >>> (64 - this.b);
            return;
        }
        for (int i2 = 0; i2 < this.f15368a - 1; i2++) {
            this.c[i2] = secureRandom.nextLong();
        }
        this.c[this.f15368a - 1] = secureRandom.nextLong() >>> (64 - this.b);
    }

    public GF2nONBElement(GF2nONBField gF2nONBField, byte[] bArr) {
        this.mField = gF2nONBField;
        this.mDegree = gF2nONBField.getDegree();
        this.f15368a = gF2nONBField.e();
        this.b = gF2nONBField.d();
        this.c = new long[this.f15368a];
        d(bArr);
    }

    public GF2nONBElement(GF2nONBField gF2nONBField, long[] jArr) {
        this.mField = gF2nONBField;
        this.mDegree = gF2nONBField.getDegree();
        this.f15368a = gF2nONBField.e();
        this.b = gF2nONBField.d();
        this.c = jArr;
    }

    public static GF2nONBElement ONE(GF2nONBField gF2nONBField) {
        int e2 = gF2nONBField.e();
        long[] jArr = new long[e2];
        int i = 0;
        while (true) {
            int i2 = e2 - 1;
            if (i >= i2) {
                jArr[i2] = e[gF2nONBField.d() - 1];
                return new GF2nONBElement(gF2nONBField, jArr);
            }
            jArr[i] = -1;
            i++;
        }
    }

    public static GF2nONBElement ZERO(GF2nONBField gF2nONBField) {
        return new GF2nONBElement(gF2nONBField, new long[gF2nONBField.e()]);
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nElement
    public void a() {
        this.c = new long[this.f15368a];
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GFElement
    public GFElement add(GFElement gFElement) throws RuntimeException {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.addToThis(gFElement);
        return gF2nONBElement;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GFElement
    public void addToThis(GFElement gFElement) throws RuntimeException {
        if (!(gFElement instanceof GF2nONBElement)) {
            throw new RuntimeException();
        }
        GF2nONBElement gF2nONBElement = (GF2nONBElement) gFElement;
        if (!this.mField.equals(gF2nONBElement.mField)) {
            throw new RuntimeException();
        }
        for (int i = 0; i < this.f15368a; i++) {
            long[] jArr = this.c;
            jArr[i] = jArr[i] ^ gF2nONBElement.c[i];
        }
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nElement
    public boolean b(int i) {
        return i >= 0 && i <= this.mDegree && (this.c[i >>> 6] & d[i & 63]) != 0;
    }

    public final void c(BigInteger bigInteger) {
        d(bigInteger.toByteArray());
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nElement, org.bouncycastle.pqc.math.linearalgebra.GFElement
    public Object clone() {
        return new GF2nONBElement(this);
    }

    public final void d(byte[] bArr) {
        this.c = new long[this.f15368a];
        for (int i = 0; i < bArr.length; i++) {
            long[] jArr = this.c;
            int i2 = i >>> 3;
            jArr[i2] = jArr[i2] | ((bArr[(bArr.length - 1) - i] & 255) << ((i & 7) << 3));
        }
    }

    public final void e(long[] jArr) {
        System.arraycopy(jArr, 0, this.c, 0, this.f15368a);
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GFElement
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof GF2nONBElement)) {
            return false;
        }
        GF2nONBElement gF2nONBElement = (GF2nONBElement) obj;
        for (int i = 0; i < this.f15368a; i++) {
            if (this.c[i] != gF2nONBElement.c[i]) {
                return false;
            }
        }
        return true;
    }

    public final long[] f() {
        long[] jArr = this.c;
        long[] jArr2 = new long[jArr.length];
        System.arraycopy(jArr, 0, jArr2, 0, jArr.length);
        return jArr2;
    }

    public final long[] g() {
        long[] jArr = new long[this.c.length];
        int i = 0;
        while (true) {
            int i2 = this.mDegree;
            if (i >= i2) {
                return jArr;
            }
            if (b((i2 - i) - 1)) {
                int i3 = i >>> 6;
                jArr[i3] = jArr[i3] | d[i & 63];
            }
            i++;
        }
    }

    public void h() {
        this.c = g();
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GFElement
    public int hashCode() {
        return this.c.hashCode();
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nElement
    public GF2nElement increase() {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.increaseThis();
        return gF2nONBElement;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nElement
    public void increaseThis() {
        addToThis(ONE((GF2nONBField) this.mField));
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GFElement
    public GFElement invert() throws ArithmeticException {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.invertThis();
        return gF2nONBElement;
    }

    public void invertThis() throws ArithmeticException {
        if (isZero()) {
            throw new ArithmeticException();
        }
        int i = 31;
        boolean z = false;
        while (!z && i >= 0) {
            if (((this.mDegree - 1) & d[i]) != 0) {
                z = true;
            }
            i--;
        }
        ZERO((GF2nONBField) this.mField);
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        int i2 = 1;
        for (int i3 = (i + 1) - 1; i3 >= 0; i3--) {
            GF2nElement gF2nElement = (GF2nElement) gF2nONBElement.clone();
            for (int i4 = 1; i4 <= i2; i4++) {
                gF2nElement.squareThis();
            }
            gF2nONBElement.multiplyThisBy(gF2nElement);
            i2 <<= 1;
            if (((this.mDegree - 1) & d[i3]) != 0) {
                gF2nONBElement.squareThis();
                gF2nONBElement.multiplyThisBy(this);
                i2++;
            }
        }
        gF2nONBElement.squareThis();
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GFElement
    public boolean isOne() {
        int i;
        boolean z = false;
        int i2 = 0;
        boolean z2 = true;
        while (true) {
            i = this.f15368a;
            if (i2 >= i - 1 || !z2) {
                break;
            }
            z2 = z2 && (this.c[i2] & (-1)) == -1;
            i2++;
        }
        if (z2) {
            if (z2) {
                long j = this.c[i - 1];
                long[] jArr = e;
                int i3 = this.b;
                if ((j & jArr[i3 - 1]) == jArr[i3 - 1]) {
                    z = true;
                }
            }
            return z;
        }
        return z2;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GFElement
    public boolean isZero() {
        boolean z = true;
        for (int i = 0; i < this.f15368a && z; i++) {
            z = z && (this.c[i] & (-1)) == 0;
        }
        return z;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GFElement
    public GFElement multiply(GFElement gFElement) throws RuntimeException {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.multiplyThisBy(gFElement);
        return gF2nONBElement;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GFElement
    public void multiplyThisBy(GFElement gFElement) throws RuntimeException {
        int i;
        boolean z;
        char c;
        if (!(gFElement instanceof GF2nONBElement)) {
            throw new RuntimeException("The elements have different representation: not yet implemented");
        }
        GF2nONBElement gF2nONBElement = (GF2nONBElement) gFElement;
        if (!this.mField.equals(gF2nONBElement.mField)) {
            throw new RuntimeException();
        }
        if (equals(gFElement)) {
            squareThis();
            return;
        }
        long[] jArr = this.c;
        long[] jArr2 = gF2nONBElement.c;
        int i2 = this.f15368a;
        long[] jArr3 = new long[i2];
        int[][] iArr = ((GF2nONBField) this.mField).d;
        int i3 = i2 - 1;
        long[] jArr4 = d;
        char c2 = a.SEP;
        long j = jArr4[63];
        long j2 = jArr4[this.b - 1];
        int i4 = 0;
        int i5 = 0;
        while (i5 < this.mDegree) {
            int i6 = i4;
            int i7 = i6;
            while (i6 < this.mDegree) {
                int[] iArr2 = f;
                int i8 = iArr2[i6];
                int i9 = iArr2[iArr[i6][i4]];
                int i10 = iArr[i6][i4] & 63;
                long j3 = jArr[i8];
                long[] jArr5 = d;
                if ((j3 & jArr5[i6 & 63]) != 0) {
                    if ((jArr2[i9] & jArr5[i10]) != 0) {
                        i7 ^= 1;
                    }
                    if (iArr[i6][1] != -1) {
                        int i11 = iArr2[iArr[i6][1]];
                        int i12 = iArr[i6][1];
                        c = a.SEP;
                        if ((jArr2[i11] & jArr5[i12 & 63]) != 0) {
                            i7 ^= 1;
                        }
                    } else {
                        c = a.SEP;
                    }
                } else {
                    c = c2;
                }
                i6++;
                c2 = c;
                i4 = 0;
            }
            char c3 = c2;
            int i13 = f[i5];
            int i14 = i5 & 63;
            if (i7 != 0) {
                jArr3[i13] = jArr3[i13] ^ d[i14];
            }
            if (this.f15368a > 1) {
                boolean z2 = (jArr[i3] & 1) == 1;
                int i15 = i3 - 1;
                int i16 = i15;
                while (i16 >= 0) {
                    boolean z3 = (jArr[i16] & 1) != 0;
                    jArr[i16] = jArr[i16] >>> 1;
                    if (z2) {
                        jArr[i16] = jArr[i16] ^ j;
                    }
                    i16--;
                    z2 = z3;
                }
                jArr[i3] = jArr[i3] >>> 1;
                if (z2) {
                    jArr[i3] = jArr[i3] ^ j2;
                }
                boolean z4 = (jArr2[i3] & 1) == 1;
                while (i15 >= 0) {
                    boolean z5 = (jArr2[i15] & 1) != 0;
                    jArr2[i15] = jArr2[i15] >>> 1;
                    if (z4) {
                        jArr2[i15] = jArr2[i15] ^ j;
                    }
                    i15--;
                    z4 = z5;
                }
                jArr2[i3] = jArr2[i3] >>> 1;
                if (z4) {
                    jArr2[i3] = jArr2[i3] ^ j2;
                }
                i = 0;
                z = true;
            } else {
                i = 0;
                boolean z6 = (jArr[0] & 1) == 1;
                jArr[0] = jArr[0] >>> 1;
                if (z6) {
                    jArr[0] = jArr[0] ^ j2;
                }
                boolean z7 = (jArr2[0] & 1) == 1;
                z = true;
                jArr2[0] = jArr2[0] >>> 1;
                if (z7) {
                    jArr2[0] = jArr2[0] ^ j2;
                }
            }
            i5++;
            i4 = i;
            c2 = c3;
        }
        e(jArr3);
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nElement
    public GF2nElement solveQuadraticEquation() throws RuntimeException {
        int i;
        int i2 = 1;
        if (trace() != 1) {
            long j = d[63];
            long[] jArr = new long[this.f15368a];
            int i3 = 0;
            long j2 = 0;
            while (true) {
                i = this.f15368a;
                if (i3 >= i - 1) {
                    break;
                }
                for (int i4 = i2; i4 < 64; i4++) {
                    long[] jArr2 = d;
                    long j3 = jArr2[i4];
                    long[] jArr3 = this.c;
                    if (((j3 & jArr3[i3]) == 0 || (jArr2[i4 - 1] & j2) == 0) && ((jArr3[i3] & jArr2[i4]) != 0 || (jArr2[i4 - 1] & j2) != 0)) {
                        j2 ^= jArr2[i4];
                    }
                }
                jArr[i3] = j2;
                int i5 = ((j2 & j) > 0L ? 1 : ((j2 & j) == 0L ? 0 : -1));
                j2 = ((i5 == 0 || (1 & this.c[i3 + 1]) != 1) && !(i5 == 0 && (this.c[i3 + 1] & 1) == 0)) ? 1L : 0L;
                i3++;
                i2 = 1;
            }
            int i6 = this.mDegree & 63;
            long j4 = this.c[i - 1];
            for (int i7 = 1; i7 < i6; i7++) {
                long[] jArr4 = d;
                if (((jArr4[i7] & j4) == 0 || (jArr4[i7 - 1] & j2) == 0) && ((jArr4[i7] & j4) != 0 || (jArr4[i7 - 1] & j2) != 0)) {
                    j2 ^= jArr4[i7];
                }
            }
            jArr[this.f15368a - 1] = j2;
            return new GF2nONBElement((GF2nONBField) this.mField, jArr);
        }
        throw new RuntimeException();
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nElement
    public GF2nElement square() {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.squareThis();
        return gF2nONBElement;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nElement
    public GF2nElement squareRoot() {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.squareRootThis();
        return gF2nONBElement;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nElement
    public void squareRootThis() {
        long[] f2 = f();
        int i = this.f15368a - 1;
        int i2 = this.b - 1;
        long j = d[63];
        boolean z = (f2[0] & 1) != 0;
        int i3 = i;
        while (i3 >= 0) {
            boolean z2 = (f2[i3] & 1) != 0;
            f2[i3] = f2[i3] >>> 1;
            if (z) {
                if (i3 == i) {
                    f2[i3] = f2[i3] ^ d[i2];
                } else {
                    f2[i3] = f2[i3] ^ j;
                }
            }
            i3--;
            z = z2;
        }
        e(f2);
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nElement
    public void squareThis() {
        long[] f2 = f();
        int i = this.f15368a - 1;
        int i2 = this.b - 1;
        long[] jArr = d;
        long j = jArr[63];
        boolean z = (f2[i] & jArr[i2]) != 0;
        int i3 = 0;
        while (i3 < i) {
            boolean z2 = (f2[i3] & j) != 0;
            f2[i3] = f2[i3] << 1;
            if (z) {
                f2[i3] = 1 ^ f2[i3];
            }
            i3++;
            z = z2;
        }
        long j2 = f2[i];
        long[] jArr2 = d;
        boolean z3 = (j2 & jArr2[i2]) != 0;
        f2[i] = f2[i] << 1;
        if (z) {
            f2[i] = f2[i] ^ 1;
        }
        if (z3) {
            f2[i] = jArr2[i2 + 1] ^ f2[i];
        }
        e(f2);
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nElement
    public boolean testRightmostBit() {
        return (this.c[this.f15368a - 1] & d[this.b - 1]) != 0;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GFElement
    public byte[] toByteArray() {
        int i = ((this.mDegree - 1) >> 3) + 1;
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = (i2 & 7) << 3;
            bArr[(i - i2) - 1] = (byte) ((this.c[i2 >>> 3] & (255 << i3)) >>> i3);
        }
        return bArr;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GFElement
    public BigInteger toFlexiBigInt() {
        return new BigInteger(1, toByteArray());
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GFElement
    public String toString() {
        return toString(16);
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GFElement
    public String toString(int i) {
        StringBuilder sb;
        long[] f2 = f();
        int i2 = this.b;
        String str = "";
        if (i == 2) {
            while (true) {
                i2--;
                if (i2 < 0) {
                    break;
                }
                str = (f2[f2.length - 1] & (1 << i2)) == 0 ? str + BleConst.GetDeviceTime : str + "1";
            }
            for (int length = f2.length - 2; length >= 0; length--) {
                for (int i3 = 63; i3 >= 0; i3--) {
                    if ((f2[length] & d[i3]) == 0) {
                        sb = new StringBuilder();
                        sb.append(str);
                        sb.append(BleConst.GetDeviceTime);
                    } else {
                        sb = new StringBuilder();
                        sb.append(str);
                        sb.append("1");
                    }
                    str = sb.toString();
                }
            }
        } else if (i == 16) {
            char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', Constants.INAPP_POSITION_BOTTOM, Constants.INAPP_POSITION_CENTER, 'd', 'e', 'f'};
            for (int length2 = f2.length - 1; length2 >= 0; length2--) {
                str = ((((((((((((((((str + cArr[((int) (f2[length2] >>> 60)) & 15]) + cArr[((int) (f2[length2] >>> 56)) & 15]) + cArr[((int) (f2[length2] >>> 52)) & 15]) + cArr[((int) (f2[length2] >>> 48)) & 15]) + cArr[((int) (f2[length2] >>> 44)) & 15]) + cArr[((int) (f2[length2] >>> 40)) & 15]) + cArr[((int) (f2[length2] >>> 36)) & 15]) + cArr[((int) (f2[length2] >>> 32)) & 15]) + cArr[((int) (f2[length2] >>> 28)) & 15]) + cArr[((int) (f2[length2] >>> 24)) & 15]) + cArr[((int) (f2[length2] >>> 20)) & 15]) + cArr[((int) (f2[length2] >>> 16)) & 15]) + cArr[((int) (f2[length2] >>> 12)) & 15]) + cArr[((int) (f2[length2] >>> 8)) & 15]) + cArr[((int) (f2[length2] >>> 4)) & 15]) + cArr[((int) f2[length2]) & 15]) + HexStringBuilder.DEFAULT_SEPARATOR;
            }
        }
        return str;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nElement
    public int trace() {
        int i = this.f15368a - 1;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            for (int i4 = 0; i4 < 64; i4++) {
                if ((this.c[i3] & d[i4]) != 0) {
                    i2 ^= 1;
                }
            }
        }
        int i5 = this.b;
        for (int i6 = 0; i6 < i5; i6++) {
            if ((this.c[i] & d[i6]) != 0) {
                i2 ^= 1;
            }
        }
        return i2;
    }
}
