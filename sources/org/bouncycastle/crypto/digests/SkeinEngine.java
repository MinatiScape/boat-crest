package org.bouncycastle.crypto.digests;

import com.google.common.primitives.Longs;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.engines.ThreefishEngine;
import org.bouncycastle.crypto.params.SkeinParameters;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Memoable;
/* loaded from: classes12.dex */
public class SkeinEngine implements Memoable {
    public static final int SKEIN_1024 = 1024;
    public static final int SKEIN_256 = 256;
    public static final int SKEIN_512 = 512;
    public static final Hashtable j = new Hashtable();

    /* renamed from: a  reason: collision with root package name */
    public final ThreefishEngine f14646a;
    public final int b;
    public long[] c;
    public long[] d;
    public byte[] e;
    public Parameter[] f;
    public Parameter[] g;
    public final b h;
    public final byte[] i;

    /* loaded from: classes12.dex */
    public static class Parameter {

        /* renamed from: a  reason: collision with root package name */
        public int f14647a;
        public byte[] b;

        public Parameter(int i, byte[] bArr) {
            this.f14647a = i;
            this.b = bArr;
        }

        public int getType() {
            return this.f14647a;
        }

        public byte[] getValue() {
            return this.b;
        }
    }

    /* loaded from: classes12.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public byte[] f14648a;

        public a(long j) {
            byte[] bArr = new byte[32];
            this.f14648a = bArr;
            bArr[0] = 83;
            bArr[1] = com.htsmart.wristband2.a.a.a.W0;
            bArr[2] = 65;
            bArr[3] = 51;
            bArr[4] = 1;
            bArr[5] = 0;
            ThreefishEngine.wordToBytes(j, bArr, 8);
        }

        public byte[] a() {
            return this.f14648a;
        }
    }

    /* loaded from: classes12.dex */
    public class b {

        /* renamed from: a  reason: collision with root package name */
        public final c f14649a = new c();
        public byte[] b;
        public int c;
        public long[] d;

        public b(int i) {
            byte[] bArr = new byte[i];
            this.b = bArr;
            this.d = new long[bArr.length / 8];
        }

        public void a(long[] jArr) {
            int i = this.c;
            while (true) {
                byte[] bArr = this.b;
                if (i >= bArr.length) {
                    this.f14649a.h(true);
                    b(jArr);
                    return;
                }
                bArr[i] = 0;
                i++;
            }
        }

        public final void b(long[] jArr) {
            long[] jArr2;
            SkeinEngine skeinEngine = SkeinEngine.this;
            skeinEngine.f14646a.init(true, skeinEngine.c, this.f14649a.c());
            int i = 0;
            while (true) {
                jArr2 = this.d;
                if (i >= jArr2.length) {
                    break;
                }
                jArr2[i] = ThreefishEngine.bytesToWord(this.b, i * 8);
                i++;
            }
            SkeinEngine.this.f14646a.processBlock(jArr2, jArr);
            for (int i2 = 0; i2 < jArr.length; i2++) {
                jArr[i2] = jArr[i2] ^ this.d[i2];
            }
        }

        public void c(int i) {
            this.f14649a.f();
            this.f14649a.j(i);
            this.c = 0;
        }

        public void d(b bVar) {
            this.b = Arrays.clone(bVar.b, this.b);
            this.c = bVar.c;
            this.d = Arrays.clone(bVar.d, this.d);
            this.f14649a.g(bVar.f14649a);
        }

        public void e(byte[] bArr, int i, int i2, long[] jArr) {
            int i3 = 0;
            while (i2 > i3) {
                if (this.c == this.b.length) {
                    b(jArr);
                    this.f14649a.i(false);
                    this.c = 0;
                }
                int min = Math.min(i2 - i3, this.b.length - this.c);
                System.arraycopy(bArr, i + i3, this.b, this.c, min);
                i3 += min;
                this.c += min;
                this.f14649a.a(min);
            }
        }
    }

    /* loaded from: classes12.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public long[] f14650a = new long[2];
        public boolean b;

        public c() {
            f();
        }

        public void a(int i) {
            if (!this.b) {
                long[] jArr = this.f14650a;
                long j = jArr[0] + i;
                jArr[0] = j;
                if (j > 9223372034707292160L) {
                    this.b = true;
                    return;
                }
                return;
            }
            long[] jArr2 = new long[3];
            long[] jArr3 = this.f14650a;
            jArr2[0] = jArr3[0] & 4294967295L;
            jArr2[1] = (jArr3[0] >>> 32) & 4294967295L;
            jArr2[2] = jArr3[1] & 4294967295L;
            long j2 = i;
            for (int i2 = 0; i2 < 3; i2++) {
                long j3 = j2 + jArr2[i2];
                jArr2[i2] = j3;
                j2 = j3 >>> 32;
            }
            long[] jArr4 = this.f14650a;
            jArr4[0] = ((jArr2[1] & 4294967295L) << 32) | (jArr2[0] & 4294967295L);
            jArr4[1] = (jArr2[2] & 4294967295L) | (jArr4[1] & (-4294967296L));
        }

        public int b() {
            return (int) ((this.f14650a[1] >>> 56) & 63);
        }

        public long[] c() {
            return this.f14650a;
        }

        public boolean d() {
            return (this.f14650a[1] & Long.MIN_VALUE) != 0;
        }

        public boolean e() {
            return (this.f14650a[1] & Longs.MAX_POWER_OF_TWO) != 0;
        }

        public void f() {
            long[] jArr = this.f14650a;
            jArr[0] = 0;
            jArr[1] = 0;
            this.b = false;
            i(true);
        }

        public void g(c cVar) {
            this.f14650a = Arrays.clone(cVar.f14650a, this.f14650a);
            this.b = cVar.b;
        }

        public void h(boolean z) {
            if (z) {
                long[] jArr = this.f14650a;
                jArr[1] = jArr[1] | Long.MIN_VALUE;
                return;
            }
            long[] jArr2 = this.f14650a;
            jArr2[1] = jArr2[1] & Long.MAX_VALUE;
        }

        public void i(boolean z) {
            if (z) {
                long[] jArr = this.f14650a;
                jArr[1] = jArr[1] | Longs.MAX_POWER_OF_TWO;
                return;
            }
            long[] jArr2 = this.f14650a;
            jArr2[1] = jArr2[1] & (-4611686018427387905L);
        }

        public void j(int i) {
            long[] jArr = this.f14650a;
            jArr[1] = (jArr[1] & (-274877906944L)) | ((i & 63) << 56);
        }

        public String toString() {
            return b() + " first: " + e() + ", final: " + d();
        }
    }

    static {
        f(256, 128, new long[]{-2228972824489528736L, -8629553674646093540L, 1155188648486244218L, -3677226592081559102L});
        f(256, 160, new long[]{1450197650740764312L, 3081844928540042640L, -3136097061834271170L, 3301952811952417661L});
        f(256, 224, new long[]{-4176654842910610933L, -8688192972455077604L, -7364642305011795836L, 4056579644589979102L});
        f(256, 256, new long[]{-243853671043386295L, 3443677322885453875L, -5531612722399640561L, 7662005193972177513L});
        f(512, 128, new long[]{-6288014694233956526L, 2204638249859346602L, 3502419045458743507L, -4829063503441264548L, 983504137758028059L, 1880512238245786339L, -6715892782214108542L, 7602827311880509485L});
        f(512, 160, new long[]{2934123928682216849L, -4399710721982728305L, 1684584802963255058L, 5744138295201861711L, 2444857010922934358L, -2807833639722848072L, -5121587834665610502L, 118355523173251694L});
        f(512, 224, new long[]{-3688341020067007964L, -3772225436291745297L, -8300862168937575580L, 4146387520469897396L, 1106145742801415120L, 7455425944880474941L, -7351063101234211863L, -7048981346965512457L});
        f(512, 384, new long[]{-6631894876634615969L, -5692838220127733084L, -7099962856338682626L, -2911352911530754598L, 2000907093792408677L, 9140007292425499655L, 6093301768906360022L, 2769176472213098488L});
        f(512, 512, new long[]{5261240102383538638L, 978932832955457283L, -8083517948103779378L, -7339365279355032399L, 6752626034097301424L, -1531723821829733388L, -7417126464950782685L, -5901786942805128141L});
    }

    public SkeinEngine(int i, int i2) {
        this.i = new byte[1];
        if (i2 % 8 != 0) {
            throw new IllegalArgumentException("Output size must be a multiple of 8 bits. :" + i2);
        }
        this.b = i2 / 8;
        ThreefishEngine threefishEngine = new ThreefishEngine(i);
        this.f14646a = threefishEngine;
        this.h = new b(threefishEngine.getBlockSize());
    }

    public SkeinEngine(SkeinEngine skeinEngine) {
        this(skeinEngine.getBlockSize() * 8, skeinEngine.getOutputSize() * 8);
        c(skeinEngine);
    }

    public static Parameter[] b(Parameter[] parameterArr, Parameter[] parameterArr2) {
        if (parameterArr == null) {
            return null;
        }
        if (parameterArr2 == null || parameterArr2.length != parameterArr.length) {
            parameterArr2 = new Parameter[parameterArr.length];
        }
        System.arraycopy(parameterArr, 0, parameterArr2, 0, parameterArr2.length);
        return parameterArr2;
    }

    public static void f(int i, int i2, long[] jArr) {
        j.put(l(i / 8, i2 / 8), jArr);
    }

    public static void h(Parameter[] parameterArr) {
        if (parameterArr == null) {
            return;
        }
        for (int i = 1; i < parameterArr.length; i++) {
            Parameter parameter = parameterArr[i];
            int i2 = i;
            while (i2 > 0) {
                int i3 = i2 - 1;
                if (parameter.getType() < parameterArr[i3].getType()) {
                    parameterArr[i2] = parameterArr[i3];
                    i2 = i3;
                }
            }
            parameterArr[i2] = parameter;
        }
    }

    public static Integer l(int i, int i2) {
        return new Integer(i | (i2 << 16));
    }

    public final void a() {
        if (this.h == null) {
            throw new IllegalArgumentException("Skein engine is not initialised.");
        }
    }

    public final void c(SkeinEngine skeinEngine) {
        this.h.d(skeinEngine.h);
        this.c = Arrays.clone(skeinEngine.c, this.c);
        this.d = Arrays.clone(skeinEngine.d, this.d);
        this.e = Arrays.clone(skeinEngine.e, this.e);
        this.f = b(skeinEngine.f, this.f);
        this.g = b(skeinEngine.g, this.g);
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new SkeinEngine(this);
    }

    public final void d() {
        long[] jArr = (long[]) j.get(l(getBlockSize(), getOutputSize()));
        int i = 0;
        if (this.e != null || jArr == null) {
            this.c = new long[getBlockSize() / 8];
            byte[] bArr = this.e;
            if (bArr != null) {
                i(0, bArr);
            }
            i(4, new a(this.b * 8).a());
        } else {
            this.c = Arrays.clone(jArr);
        }
        if (this.f != null) {
            while (true) {
                Parameter[] parameterArr = this.f;
                if (i >= parameterArr.length) {
                    break;
                }
                Parameter parameter = parameterArr[i];
                i(parameter.getType(), parameter.getValue());
                i++;
            }
        }
        this.d = Arrays.clone(this.c);
    }

    public int doFinal(byte[] bArr, int i) {
        a();
        if (bArr.length >= this.b + i) {
            j();
            if (this.g != null) {
                int i2 = 0;
                while (true) {
                    Parameter[] parameterArr = this.g;
                    if (i2 >= parameterArr.length) {
                        break;
                    }
                    Parameter parameter = parameterArr[i2];
                    i(parameter.getType(), parameter.getValue());
                    i2++;
                }
            }
            int blockSize = getBlockSize();
            int i3 = ((this.b + blockSize) - 1) / blockSize;
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = i4 * blockSize;
                g(i4, bArr, i + i5, Math.min(blockSize, this.b - i5));
            }
            reset();
            return this.b;
        }
        throw new OutputLengthException("Output buffer is too short to hold output");
    }

    public final void e(Hashtable hashtable) {
        Enumeration keys = hashtable.keys();
        Vector vector = new Vector();
        Vector vector2 = new Vector();
        while (keys.hasMoreElements()) {
            Integer num = (Integer) keys.nextElement();
            byte[] bArr = (byte[]) hashtable.get(num);
            if (num.intValue() == 0) {
                this.e = bArr;
            } else if (num.intValue() < 48) {
                vector.addElement(new Parameter(num.intValue(), bArr));
            } else {
                vector2.addElement(new Parameter(num.intValue(), bArr));
            }
        }
        Parameter[] parameterArr = new Parameter[vector.size()];
        this.f = parameterArr;
        vector.copyInto(parameterArr);
        h(this.f);
        Parameter[] parameterArr2 = new Parameter[vector2.size()];
        this.g = parameterArr2;
        vector2.copyInto(parameterArr2);
        h(this.g);
    }

    public final void g(long j2, byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[8];
        ThreefishEngine.wordToBytes(j2, bArr2, 0);
        long[] jArr = new long[this.c.length];
        k(63);
        this.h.e(bArr2, 0, 8, jArr);
        this.h.a(jArr);
        int i3 = ((i2 + 8) - 1) / 8;
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i4 * 8;
            int min = Math.min(8, i2 - i5);
            if (min == 8) {
                ThreefishEngine.wordToBytes(jArr[i4], bArr, i5 + i);
            } else {
                ThreefishEngine.wordToBytes(jArr[i4], bArr2, 0);
                System.arraycopy(bArr2, 0, bArr, i5 + i, min);
            }
        }
    }

    public int getBlockSize() {
        return this.f14646a.getBlockSize();
    }

    public int getOutputSize() {
        return this.b;
    }

    public final void i(int i, byte[] bArr) {
        k(i);
        this.h.e(bArr, 0, bArr.length, this.c);
        j();
    }

    public void init(SkeinParameters skeinParameters) {
        this.c = null;
        this.e = null;
        this.f = null;
        this.g = null;
        if (skeinParameters != null) {
            if (skeinParameters.getKey().length < 16) {
                throw new IllegalArgumentException("Skein key must be at least 128 bits.");
            }
            e(skeinParameters.getParameters());
        }
        d();
        k(48);
    }

    public final void j() {
        this.h.a(this.c);
    }

    public final void k(int i) {
        this.h.c(i);
    }

    public void reset() {
        long[] jArr = this.d;
        long[] jArr2 = this.c;
        System.arraycopy(jArr, 0, jArr2, 0, jArr2.length);
        k(48);
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        SkeinEngine skeinEngine = (SkeinEngine) memoable;
        if (getBlockSize() != skeinEngine.getBlockSize() || this.b != skeinEngine.b) {
            throw new IllegalArgumentException("Incompatible parameters in provided SkeinEngine.");
        }
        c(skeinEngine);
    }

    public void update(byte b2) {
        byte[] bArr = this.i;
        bArr[0] = b2;
        update(bArr, 0, 1);
    }

    public void update(byte[] bArr, int i, int i2) {
        a();
        this.h.e(bArr, i, i2, this.c);
    }
}
