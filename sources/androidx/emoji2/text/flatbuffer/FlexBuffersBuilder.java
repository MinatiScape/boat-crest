package androidx.emoji2.text.flatbuffer;

import androidx.emoji2.text.flatbuffer.FlexBuffers;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
/* loaded from: classes.dex */
public class FlexBuffersBuilder {
    public static final int BUILDER_FLAG_NONE = 0;
    public static final int BUILDER_FLAG_SHARE_ALL = 7;
    public static final int BUILDER_FLAG_SHARE_KEYS = 1;
    public static final int BUILDER_FLAG_SHARE_KEYS_AND_STRINGS = 3;
    public static final int BUILDER_FLAG_SHARE_KEY_VECTORS = 4;
    public static final int BUILDER_FLAG_SHARE_STRINGS = 2;

    /* renamed from: a  reason: collision with root package name */
    public final androidx.emoji2.text.flatbuffer.b f1274a;
    public final ArrayList<b> b;
    public final HashMap<String, Integer> c;
    public final HashMap<String, Integer> d;
    public final int e;
    public boolean f;
    public Comparator<b> g;

    /* loaded from: classes.dex */
    public class a implements Comparator<b> {
        public a() {
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(b bVar, b bVar2) {
            byte b;
            byte b2;
            int i = bVar.e;
            int i2 = bVar2.e;
            do {
                b = FlexBuffersBuilder.this.f1274a.get(i);
                b2 = FlexBuffersBuilder.this.f1274a.get(i2);
                if (b == 0) {
                    return b - b2;
                }
                i++;
                i2++;
            } while (b == b2);
            return b - b2;
        }
    }

    public FlexBuffersBuilder(int i) {
        this(new ArrayReadWriteBuf(i), 1);
    }

    public static int h(long j) {
        if (j <= FlexBuffers.c.a((byte) -1)) {
            return 0;
        }
        if (j <= FlexBuffers.c.c((short) -1)) {
            return 1;
        }
        return j <= FlexBuffers.c.b(-1) ? 2 : 3;
    }

    public final int b(int i) {
        int i2 = 1 << i;
        int q = b.q(this.f1274a.writePosition(), i2);
        while (true) {
            int i3 = q - 1;
            if (q == 0) {
                return i2;
            }
            this.f1274a.put((byte) 0);
            q = i3;
        }
    }

    public final b c(int i, int i2) {
        long j = i2;
        int max = Math.max(0, h(j));
        int i3 = i;
        while (i3 < this.b.size()) {
            i3++;
            max = Math.max(max, b.i(4, 0, this.b.get(i3).e, this.f1274a.writePosition(), i3));
        }
        int b2 = b(max);
        l(j, b2);
        int writePosition = this.f1274a.writePosition();
        while (i < this.b.size()) {
            int i4 = this.b.get(i).e;
            m(this.b.get(i).e, b2);
            i++;
        }
        return new b(-1, FlexBuffers.o(4, 0), max, writePosition);
    }

    public final b d(int i, int i2, int i3, boolean z, boolean z2, b bVar) {
        int i4;
        int i5;
        int i6 = i3;
        long j = i6;
        int max = Math.max(0, h(j));
        if (bVar != null) {
            max = Math.max(max, bVar.h(this.f1274a.writePosition(), 0));
            i4 = 3;
        } else {
            i4 = 1;
        }
        int i7 = 4;
        int i8 = max;
        for (int i9 = i2; i9 < this.b.size(); i9++) {
            i8 = Math.max(i8, this.b.get(i9).h(this.f1274a.writePosition(), i9 + i4));
            if (z && i9 == i2) {
                i7 = this.b.get(i9).f1275a;
                if (!FlexBuffers.j(i7)) {
                    throw new FlexBuffers.FlexBufferException("TypedVector does not support this element type");
                }
            }
        }
        int i10 = i2;
        int b2 = b(i8);
        if (bVar != null) {
            m(bVar.d, b2);
            l(1 << bVar.b, b2);
        }
        if (!z2) {
            l(j, b2);
        }
        int writePosition = this.f1274a.writePosition();
        for (int i11 = i10; i11 < this.b.size(); i11++) {
            i(this.b.get(i11), b2);
        }
        if (!z) {
            while (i10 < this.b.size()) {
                this.f1274a.put(this.b.get(i10).s(i8));
                i10++;
            }
        }
        if (bVar != null) {
            i5 = 9;
        } else if (z) {
            if (!z2) {
                i6 = 0;
            }
            i5 = FlexBuffers.o(i7, i6);
        } else {
            i5 = 10;
        }
        return new b(i, i5, i8, writePosition);
    }

    public final int e(String str) {
        if (str == null) {
            return -1;
        }
        int writePosition = this.f1274a.writePosition();
        if ((this.e & 1) != 0) {
            Integer num = this.c.get(str);
            if (num == null) {
                byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
                this.f1274a.put(bytes, 0, bytes.length);
                this.f1274a.put((byte) 0);
                this.c.put(str, Integer.valueOf(writePosition));
                return writePosition;
            }
            return num.intValue();
        }
        byte[] bytes2 = str.getBytes(StandardCharsets.UTF_8);
        this.f1274a.put(bytes2, 0, bytes2.length);
        this.f1274a.put((byte) 0);
        this.c.put(str, Integer.valueOf(writePosition));
        return writePosition;
    }

    public int endMap(String str, int i) {
        int e = e(str);
        ArrayList<b> arrayList = this.b;
        Collections.sort(arrayList.subList(i, arrayList.size()), this.g);
        b d = d(e, i, this.b.size() - i, false, false, c(i, this.b.size() - i));
        while (this.b.size() > i) {
            ArrayList<b> arrayList2 = this.b;
            arrayList2.remove(arrayList2.size() - 1);
        }
        this.b.add(d);
        return (int) d.d;
    }

    public int endVector(String str, int i, boolean z, boolean z2) {
        b d = d(e(str), i, this.b.size() - i, z, z2, null);
        while (this.b.size() > i) {
            ArrayList<b> arrayList = this.b;
            arrayList.remove(arrayList.size() - 1);
        }
        this.b.add(d);
        return (int) d.d;
    }

    public final void f(String str, long j) {
        b w;
        int e = e(str);
        int h = h(j);
        if (h == 0) {
            w = b.x(e, (int) j);
        } else if (h == 1) {
            w = b.u(e, (int) j);
        } else if (h == 2) {
            w = b.v(e, (int) j);
        } else {
            w = b.w(e, j);
        }
        this.b.add(w);
    }

    public ByteBuffer finish() {
        int b2 = b(this.b.get(0).h(this.f1274a.writePosition(), 0));
        i(this.b.get(0), b2);
        this.f1274a.put(this.b.get(0).r());
        this.f1274a.put((byte) b2);
        this.f = true;
        return ByteBuffer.wrap(this.f1274a.data(), 0, this.f1274a.writePosition());
    }

    public final void g(String str, long j) {
        this.b.add(b.w(e(str), j));
    }

    public androidx.emoji2.text.flatbuffer.b getBuffer() {
        return this.f1274a;
    }

    public final void i(b bVar, int i) {
        int i2 = bVar.f1275a;
        if (i2 != 0 && i2 != 1 && i2 != 2) {
            if (i2 == 3) {
                k(bVar.c, i);
                return;
            } else if (i2 != 26) {
                m(bVar.d, i);
                return;
            }
        }
        l(bVar.d, i);
    }

    public final b j(int i, byte[] bArr, int i2, boolean z) {
        int h = h(bArr.length);
        l(bArr.length, b(h));
        int writePosition = this.f1274a.writePosition();
        this.f1274a.put(bArr, 0, bArr.length);
        if (z) {
            this.f1274a.put((byte) 0);
        }
        return b.f(i, writePosition, i2, h);
    }

    public final void k(double d, int i) {
        if (i == 4) {
            this.f1274a.putFloat((float) d);
        } else if (i == 8) {
            this.f1274a.putDouble(d);
        }
    }

    public final void l(long j, int i) {
        if (i == 1) {
            this.f1274a.put((byte) j);
        } else if (i == 2) {
            this.f1274a.putShort((short) j);
        } else if (i == 4) {
            this.f1274a.putInt((int) j);
        } else if (i != 8) {
        } else {
            this.f1274a.putLong(j);
        }
    }

    public final void m(long j, int i) {
        l((int) (this.f1274a.writePosition() - j), i);
    }

    public final b n(int i, String str) {
        return j(i, str.getBytes(StandardCharsets.UTF_8), 5, true);
    }

    public int putBlob(byte[] bArr) {
        return putBlob(null, bArr);
    }

    public void putBoolean(boolean z) {
        putBoolean(null, z);
    }

    public void putFloat(float f) {
        putFloat((String) null, f);
    }

    public void putInt(int i) {
        putInt((String) null, i);
    }

    public int putString(String str) {
        return putString(null, str);
    }

    public void putUInt(int i) {
        f(null, i);
    }

    public void putUInt64(BigInteger bigInteger) {
        g(null, bigInteger.longValue());
    }

    public int startMap() {
        return this.b.size();
    }

    public int startVector() {
        return this.b.size();
    }

    public FlexBuffersBuilder() {
        this(256);
    }

    public int putBlob(String str, byte[] bArr) {
        b j = j(e(str), bArr, 25, false);
        this.b.add(j);
        return (int) j.d;
    }

    public void putBoolean(String str, boolean z) {
        this.b.add(b.g(e(str), z));
    }

    public void putFloat(String str, float f) {
        this.b.add(b.j(e(str), f));
    }

    public void putInt(String str, int i) {
        putInt(str, i);
    }

    public int putString(String str, String str2) {
        int e = e(str);
        if ((this.e & 2) != 0) {
            Integer num = this.d.get(str2);
            if (num == null) {
                b n = n(e, str2);
                this.d.put(str2, Integer.valueOf((int) n.d));
                this.b.add(n);
                return (int) n.d;
            }
            this.b.add(b.f(e, num.intValue(), 5, h(str2.length())));
            return num.intValue();
        }
        b n2 = n(e, str2);
        this.b.add(n2);
        return (int) n2.d;
    }

    public void putUInt(long j) {
        f(null, j);
    }

    @Deprecated
    public FlexBuffersBuilder(ByteBuffer byteBuffer, int i) {
        this(new ArrayReadWriteBuf(byteBuffer.array()), i);
    }

    public void putFloat(double d) {
        putFloat((String) null, d);
    }

    public void putInt(String str, long j) {
        int e = e(str);
        if (-128 <= j && j <= 127) {
            this.b.add(b.o(e, (int) j));
        } else if (-32768 <= j && j <= 32767) {
            this.b.add(b.l(e, (int) j));
        } else if (-2147483648L <= j && j <= 2147483647L) {
            this.b.add(b.m(e, (int) j));
        } else {
            this.b.add(b.n(e, j));
        }
    }

    public FlexBuffersBuilder(androidx.emoji2.text.flatbuffer.b bVar, int i) {
        this.b = new ArrayList<>();
        this.c = new HashMap<>();
        this.d = new HashMap<>();
        this.f = false;
        this.g = new a();
        this.f1274a = bVar;
        this.e = i;
    }

    public void putFloat(String str, double d) {
        this.b.add(b.k(e(str), d));
    }

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public final int f1275a;
        public final int b;
        public final double c;
        public long d;
        public int e;

        public b(int i, int i2, int i3, long j) {
            this.e = i;
            this.f1275a = i2;
            this.b = i3;
            this.d = j;
            this.c = Double.MIN_VALUE;
        }

        public static b f(int i, int i2, int i3, int i4) {
            return new b(i, i3, i4, i2);
        }

        public static b g(int i, boolean z) {
            return new b(i, 26, 0, z ? 1L : 0L);
        }

        public static int i(int i, int i2, long j, int i3, int i4) {
            if (FlexBuffers.h(i)) {
                return i2;
            }
            for (int i5 = 1; i5 <= 32; i5 *= 2) {
                int h = FlexBuffersBuilder.h((int) (((q(i3, i5) + i3) + (i4 * i5)) - j));
                if ((1 << h) == i5) {
                    return h;
                }
            }
            return 3;
        }

        public static b j(int i, float f) {
            return new b(i, 3, 2, f);
        }

        public static b k(int i, double d) {
            return new b(i, 3, 3, d);
        }

        public static b l(int i, int i2) {
            return new b(i, 1, 1, i2);
        }

        public static b m(int i, int i2) {
            return new b(i, 1, 2, i2);
        }

        public static b n(int i, long j) {
            return new b(i, 1, 3, j);
        }

        public static b o(int i, int i2) {
            return new b(i, 1, 0, i2);
        }

        public static byte p(int i, int i2) {
            return (byte) (i | (i2 << 2));
        }

        public static int q(int i, int i2) {
            return ((~i) + 1) & (i2 - 1);
        }

        public static b u(int i, int i2) {
            return new b(i, 2, 1, i2);
        }

        public static b v(int i, int i2) {
            return new b(i, 2, 2, i2);
        }

        public static b w(int i, long j) {
            return new b(i, 2, 3, j);
        }

        public static b x(int i, int i2) {
            return new b(i, 2, 0, i2);
        }

        public final int h(int i, int i2) {
            return i(this.f1275a, this.b, this.d, i, i2);
        }

        public final byte r() {
            return s(0);
        }

        public final byte s(int i) {
            return p(t(i), this.f1275a);
        }

        public final int t(int i) {
            if (FlexBuffers.h(this.f1275a)) {
                return Math.max(this.b, i);
            }
            return this.b;
        }

        public b(int i, int i2, int i3, double d) {
            this.e = i;
            this.f1275a = i2;
            this.b = i3;
            this.c = d;
            this.d = Long.MIN_VALUE;
        }
    }

    public void putInt(long j) {
        putInt((String) null, j);
    }

    public FlexBuffersBuilder(ByteBuffer byteBuffer) {
        this(byteBuffer, 1);
    }
}
