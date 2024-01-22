package androidx.constraintlayout.core.motion.utils;

import androidx.constraintlayout.core.motion.CustomAttribute;
import androidx.constraintlayout.core.motion.CustomVariable;
import java.io.PrintStream;
import java.util.Arrays;
/* loaded from: classes.dex */
public class KeyFrameArray {

    /* loaded from: classes.dex */
    public static class CustomArray {

        /* renamed from: a  reason: collision with root package name */
        public int[] f877a = new int[101];
        public CustomAttribute[] b = new CustomAttribute[101];
        public int c;

        public CustomArray() {
            clear();
        }

        public void append(int i, CustomAttribute customAttribute) {
            if (this.b[i] != null) {
                remove(i);
            }
            this.b[i] = customAttribute;
            int[] iArr = this.f877a;
            int i2 = this.c;
            this.c = i2 + 1;
            iArr[i2] = i;
            Arrays.sort(iArr);
        }

        public void clear() {
            Arrays.fill(this.f877a, 999);
            Arrays.fill(this.b, (Object) null);
            this.c = 0;
        }

        public void dump() {
            PrintStream printStream = System.out;
            printStream.println("V: " + Arrays.toString(Arrays.copyOf(this.f877a, this.c)));
            System.out.print("K: [");
            int i = 0;
            while (i < this.c) {
                PrintStream printStream2 = System.out;
                StringBuilder sb = new StringBuilder();
                sb.append(i == 0 ? "" : ", ");
                sb.append(valueAt(i));
                printStream2.print(sb.toString());
                i++;
            }
            System.out.println("]");
        }

        public int keyAt(int i) {
            return this.f877a[i];
        }

        public void remove(int i) {
            this.b[i] = null;
            int i2 = 0;
            int i3 = 0;
            while (true) {
                int i4 = this.c;
                if (i2 < i4) {
                    int[] iArr = this.f877a;
                    if (i == iArr[i2]) {
                        iArr[i2] = 999;
                        i3++;
                    }
                    if (i2 != i3) {
                        iArr[i2] = iArr[i3];
                    }
                    i3++;
                    i2++;
                } else {
                    this.c = i4 - 1;
                    return;
                }
            }
        }

        public int size() {
            return this.c;
        }

        public CustomAttribute valueAt(int i) {
            return this.b[this.f877a[i]];
        }
    }

    /* loaded from: classes.dex */
    public static class CustomVar {

        /* renamed from: a  reason: collision with root package name */
        public int[] f878a = new int[101];
        public CustomVariable[] b = new CustomVariable[101];
        public int c;

        public CustomVar() {
            clear();
        }

        public void append(int i, CustomVariable customVariable) {
            if (this.b[i] != null) {
                remove(i);
            }
            this.b[i] = customVariable;
            int[] iArr = this.f878a;
            int i2 = this.c;
            this.c = i2 + 1;
            iArr[i2] = i;
            Arrays.sort(iArr);
        }

        public void clear() {
            Arrays.fill(this.f878a, 999);
            Arrays.fill(this.b, (Object) null);
            this.c = 0;
        }

        public void dump() {
            PrintStream printStream = System.out;
            printStream.println("V: " + Arrays.toString(Arrays.copyOf(this.f878a, this.c)));
            System.out.print("K: [");
            int i = 0;
            while (i < this.c) {
                PrintStream printStream2 = System.out;
                StringBuilder sb = new StringBuilder();
                sb.append(i == 0 ? "" : ", ");
                sb.append(valueAt(i));
                printStream2.print(sb.toString());
                i++;
            }
            System.out.println("]");
        }

        public int keyAt(int i) {
            return this.f878a[i];
        }

        public void remove(int i) {
            this.b[i] = null;
            int i2 = 0;
            int i3 = 0;
            while (true) {
                int i4 = this.c;
                if (i2 < i4) {
                    int[] iArr = this.f878a;
                    if (i == iArr[i2]) {
                        iArr[i2] = 999;
                        i3++;
                    }
                    if (i2 != i3) {
                        iArr[i2] = iArr[i3];
                    }
                    i3++;
                    i2++;
                } else {
                    this.c = i4 - 1;
                    return;
                }
            }
        }

        public int size() {
            return this.c;
        }

        public CustomVariable valueAt(int i) {
            return this.b[this.f878a[i]];
        }
    }

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public int[] f879a = new int[101];
        public float[][] b = new float[101];
        public int c;

        public a() {
            b();
        }

        public void a(int i, float[] fArr) {
            if (this.b[i] != null) {
                c(i);
            }
            this.b[i] = fArr;
            int[] iArr = this.f879a;
            int i2 = this.c;
            this.c = i2 + 1;
            iArr[i2] = i;
            Arrays.sort(iArr);
        }

        public void b() {
            Arrays.fill(this.f879a, 999);
            Arrays.fill(this.b, (Object) null);
            this.c = 0;
        }

        public void c(int i) {
            this.b[i] = null;
            int i2 = 0;
            int i3 = 0;
            while (true) {
                int i4 = this.c;
                if (i2 < i4) {
                    int[] iArr = this.f879a;
                    if (i == iArr[i2]) {
                        iArr[i2] = 999;
                        i3++;
                    }
                    if (i2 != i3) {
                        iArr[i2] = iArr[i3];
                    }
                    i3++;
                    i2++;
                } else {
                    this.c = i4 - 1;
                    return;
                }
            }
        }

        public float[] d(int i) {
            return this.b[this.f879a[i]];
        }
    }
}
