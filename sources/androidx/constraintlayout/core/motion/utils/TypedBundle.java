package androidx.constraintlayout.core.motion.utils;

import java.util.Arrays;
/* loaded from: classes.dex */
public class TypedBundle {

    /* renamed from: a  reason: collision with root package name */
    public int[] f888a = new int[10];
    public int[] b = new int[10];
    public int c = 0;
    public int[] d = new int[10];
    public float[] e = new float[10];
    public int f = 0;
    public int[] g = new int[5];
    public String[] h = new String[5];
    public int i = 0;
    public int[] j = new int[4];
    public boolean[] k = new boolean[4];
    public int l = 0;

    public void add(int i, int i2) {
        int i3 = this.c;
        int[] iArr = this.f888a;
        if (i3 >= iArr.length) {
            this.f888a = Arrays.copyOf(iArr, iArr.length * 2);
            int[] iArr2 = this.b;
            this.b = Arrays.copyOf(iArr2, iArr2.length * 2);
        }
        int[] iArr3 = this.f888a;
        int i4 = this.c;
        iArr3[i4] = i;
        int[] iArr4 = this.b;
        this.c = i4 + 1;
        iArr4[i4] = i2;
    }

    public void addIfNotNull(int i, String str) {
        if (str != null) {
            add(i, str);
        }
    }

    public void applyDelta(TypedValues typedValues) {
        for (int i = 0; i < this.c; i++) {
            typedValues.setValue(this.f888a[i], this.b[i]);
        }
        for (int i2 = 0; i2 < this.f; i2++) {
            typedValues.setValue(this.d[i2], this.e[i2]);
        }
        for (int i3 = 0; i3 < this.i; i3++) {
            typedValues.setValue(this.g[i3], this.h[i3]);
        }
        for (int i4 = 0; i4 < this.l; i4++) {
            typedValues.setValue(this.j[i4], this.k[i4]);
        }
    }

    public void clear() {
        this.l = 0;
        this.i = 0;
        this.f = 0;
        this.c = 0;
    }

    public int getInteger(int i) {
        for (int i2 = 0; i2 < this.c; i2++) {
            if (this.f888a[i2] == i) {
                return this.b[i2];
            }
        }
        return -1;
    }

    public void add(int i, float f) {
        int i2 = this.f;
        int[] iArr = this.d;
        if (i2 >= iArr.length) {
            this.d = Arrays.copyOf(iArr, iArr.length * 2);
            float[] fArr = this.e;
            this.e = Arrays.copyOf(fArr, fArr.length * 2);
        }
        int[] iArr2 = this.d;
        int i3 = this.f;
        iArr2[i3] = i;
        float[] fArr2 = this.e;
        this.f = i3 + 1;
        fArr2[i3] = f;
    }

    public void applyDelta(TypedBundle typedBundle) {
        for (int i = 0; i < this.c; i++) {
            typedBundle.add(this.f888a[i], this.b[i]);
        }
        for (int i2 = 0; i2 < this.f; i2++) {
            typedBundle.add(this.d[i2], this.e[i2]);
        }
        for (int i3 = 0; i3 < this.i; i3++) {
            typedBundle.add(this.g[i3], this.h[i3]);
        }
        for (int i4 = 0; i4 < this.l; i4++) {
            typedBundle.add(this.j[i4], this.k[i4]);
        }
    }

    public void add(int i, String str) {
        int i2 = this.i;
        int[] iArr = this.g;
        if (i2 >= iArr.length) {
            this.g = Arrays.copyOf(iArr, iArr.length * 2);
            String[] strArr = this.h;
            this.h = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
        }
        int[] iArr2 = this.g;
        int i3 = this.i;
        iArr2[i3] = i;
        String[] strArr2 = this.h;
        this.i = i3 + 1;
        strArr2[i3] = str;
    }

    public void add(int i, boolean z) {
        int i2 = this.l;
        int[] iArr = this.j;
        if (i2 >= iArr.length) {
            this.j = Arrays.copyOf(iArr, iArr.length * 2);
            boolean[] zArr = this.k;
            this.k = Arrays.copyOf(zArr, zArr.length * 2);
        }
        int[] iArr2 = this.j;
        int i3 = this.l;
        iArr2[i3] = i;
        boolean[] zArr2 = this.k;
        this.l = i3 + 1;
        zArr2[i3] = z;
    }
}
