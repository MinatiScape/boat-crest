package androidx.constraintlayout.core;

import androidx.constraintlayout.core.ArrayRow;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.PrintStream;
import java.util.Arrays;
/* loaded from: classes.dex */
public class SolverVariableValues implements ArrayRow.ArrayRowVariables {
    public static float l = 0.001f;

    /* renamed from: a  reason: collision with root package name */
    public int f856a = 16;
    public int b = 16;
    public int[] c = new int[16];
    public int[] d = new int[16];
    public int[] e = new int[16];
    public float[] f = new float[16];
    public int[] g = new int[16];
    public int[] h = new int[16];
    public int i = 0;
    public int j = -1;
    public final ArrayRow k;
    public final Cache mCache;

    public SolverVariableValues(ArrayRow arrayRow, Cache cache) {
        this.k = arrayRow;
        this.mCache = cache;
        clear();
    }

    public final void a(SolverVariable solverVariable, int i) {
        int[] iArr;
        int i2 = solverVariable.id % this.b;
        int[] iArr2 = this.c;
        int i3 = iArr2[i2];
        if (i3 == -1) {
            iArr2[i2] = i;
        } else {
            while (true) {
                iArr = this.d;
                if (iArr[i3] == -1) {
                    break;
                }
                i3 = iArr[i3];
            }
            iArr[i3] = i;
        }
        this.d[i] = -1;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void add(SolverVariable solverVariable, float f, boolean z) {
        float f2 = l;
        if (f <= (-f2) || f >= f2) {
            int indexOf = indexOf(solverVariable);
            if (indexOf == -1) {
                put(solverVariable, f);
                return;
            }
            float[] fArr = this.f;
            fArr[indexOf] = fArr[indexOf] + f;
            float f3 = fArr[indexOf];
            float f4 = l;
            if (f3 <= (-f4) || fArr[indexOf] >= f4) {
                return;
            }
            fArr[indexOf] = 0.0f;
            remove(solverVariable, z);
        }
    }

    public final void b(int i, SolverVariable solverVariable, float f) {
        this.e[i] = solverVariable.id;
        this.f[i] = f;
        this.g[i] = -1;
        this.h[i] = -1;
        solverVariable.addToRow(this.k);
        solverVariable.usageInRowCount++;
        this.i++;
    }

    public final int c() {
        for (int i = 0; i < this.f856a; i++) {
            if (this.e[i] == -1) {
                return i;
            }
        }
        return -1;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void clear() {
        int i = this.i;
        for (int i2 = 0; i2 < i; i2++) {
            SolverVariable variable = getVariable(i2);
            if (variable != null) {
                variable.removeFromRow(this.k);
            }
        }
        for (int i3 = 0; i3 < this.f856a; i3++) {
            this.e[i3] = -1;
            this.d[i3] = -1;
        }
        for (int i4 = 0; i4 < this.b; i4++) {
            this.c[i4] = -1;
        }
        this.i = 0;
        this.j = -1;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public boolean contains(SolverVariable solverVariable) {
        return indexOf(solverVariable) != -1;
    }

    public final void d() {
        int i = this.f856a * 2;
        this.e = Arrays.copyOf(this.e, i);
        this.f = Arrays.copyOf(this.f, i);
        this.g = Arrays.copyOf(this.g, i);
        this.h = Arrays.copyOf(this.h, i);
        this.d = Arrays.copyOf(this.d, i);
        for (int i2 = this.f856a; i2 < i; i2++) {
            this.e[i2] = -1;
            this.d[i2] = -1;
        }
        this.f856a = i;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void display() {
        int i = this.i;
        System.out.print("{ ");
        for (int i2 = 0; i2 < i; i2++) {
            SolverVariable variable = getVariable(i2);
            if (variable != null) {
                PrintStream printStream = System.out;
                printStream.print(variable + " = " + getVariableValue(i2) + HexStringBuilder.DEFAULT_SEPARATOR);
            }
        }
        System.out.println(" }");
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void divideByAmount(float f) {
        int i = this.i;
        int i2 = this.j;
        for (int i3 = 0; i3 < i; i3++) {
            float[] fArr = this.f;
            fArr[i2] = fArr[i2] / f;
            i2 = this.h[i2];
            if (i2 == -1) {
                return;
            }
        }
    }

    public final void e(int i, SolverVariable solverVariable, float f) {
        int c = c();
        b(c, solverVariable, f);
        if (i != -1) {
            this.g[c] = i;
            int[] iArr = this.h;
            iArr[c] = iArr[i];
            iArr[i] = c;
        } else {
            this.g[c] = -1;
            if (this.i > 0) {
                this.h[c] = this.j;
                this.j = c;
            } else {
                this.h[c] = -1;
            }
        }
        int[] iArr2 = this.h;
        if (iArr2[c] != -1) {
            this.g[iArr2[c]] = c;
        }
        a(solverVariable, c);
    }

    public final void f(SolverVariable solverVariable) {
        int[] iArr;
        int i = solverVariable.id;
        int i2 = i % this.b;
        int[] iArr2 = this.c;
        int i3 = iArr2[i2];
        if (i3 == -1) {
            return;
        }
        if (this.e[i3] == i) {
            int[] iArr3 = this.d;
            iArr2[i2] = iArr3[i3];
            iArr3[i3] = -1;
            return;
        }
        while (true) {
            iArr = this.d;
            if (iArr[i3] == -1 || this.e[iArr[i3]] == i) {
                break;
            }
            i3 = iArr[i3];
        }
        int i4 = iArr[i3];
        if (i4 == -1 || this.e[i4] != i) {
            return;
        }
        iArr[i3] = iArr[i4];
        iArr[i4] = -1;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public float get(SolverVariable solverVariable) {
        int indexOf = indexOf(solverVariable);
        if (indexOf != -1) {
            return this.f[indexOf];
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public int getCurrentSize() {
        return this.i;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public SolverVariable getVariable(int i) {
        int i2 = this.i;
        if (i2 == 0) {
            return null;
        }
        int i3 = this.j;
        for (int i4 = 0; i4 < i2; i4++) {
            if (i4 == i && i3 != -1) {
                return this.mCache.d[this.e[i3]];
            }
            i3 = this.h[i3];
            if (i3 == -1) {
                break;
            }
        }
        return null;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public float getVariableValue(int i) {
        int i2 = this.i;
        int i3 = this.j;
        for (int i4 = 0; i4 < i2; i4++) {
            if (i4 == i) {
                return this.f[i3];
            }
            i3 = this.h[i3];
            if (i3 == -1) {
                return 0.0f;
            }
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public int indexOf(SolverVariable solverVariable) {
        int[] iArr;
        if (this.i != 0 && solverVariable != null) {
            int i = solverVariable.id;
            int i2 = this.c[i % this.b];
            if (i2 == -1) {
                return -1;
            }
            if (this.e[i2] == i) {
                return i2;
            }
            while (true) {
                iArr = this.d;
                if (iArr[i2] == -1 || this.e[iArr[i2]] == i) {
                    break;
                }
                i2 = iArr[i2];
            }
            if (iArr[i2] != -1 && this.e[iArr[i2]] == i) {
                return iArr[i2];
            }
        }
        return -1;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void invert() {
        int i = this.i;
        int i2 = this.j;
        for (int i3 = 0; i3 < i; i3++) {
            float[] fArr = this.f;
            fArr[i2] = fArr[i2] * (-1.0f);
            i2 = this.h[i2];
            if (i2 == -1) {
                return;
            }
        }
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void put(SolverVariable solverVariable, float f) {
        float f2 = l;
        if (f > (-f2) && f < f2) {
            remove(solverVariable, true);
            return;
        }
        if (this.i == 0) {
            b(0, solverVariable, f);
            a(solverVariable, 0);
            this.j = 0;
            return;
        }
        int indexOf = indexOf(solverVariable);
        if (indexOf != -1) {
            this.f[indexOf] = f;
            return;
        }
        if (this.i + 1 >= this.f856a) {
            d();
        }
        int i = this.i;
        int i2 = this.j;
        int i3 = -1;
        for (int i4 = 0; i4 < i; i4++) {
            int[] iArr = this.e;
            int i5 = iArr[i2];
            int i6 = solverVariable.id;
            if (i5 == i6) {
                this.f[i2] = f;
                return;
            }
            if (iArr[i2] < i6) {
                i3 = i2;
            }
            i2 = this.h[i2];
            if (i2 == -1) {
                break;
            }
        }
        e(i3, solverVariable, f);
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public float remove(SolverVariable solverVariable, boolean z) {
        int indexOf = indexOf(solverVariable);
        if (indexOf == -1) {
            return 0.0f;
        }
        f(solverVariable);
        float f = this.f[indexOf];
        if (this.j == indexOf) {
            this.j = this.h[indexOf];
        }
        this.e[indexOf] = -1;
        int[] iArr = this.g;
        if (iArr[indexOf] != -1) {
            int[] iArr2 = this.h;
            iArr2[iArr[indexOf]] = iArr2[indexOf];
        }
        int[] iArr3 = this.h;
        if (iArr3[indexOf] != -1) {
            iArr[iArr3[indexOf]] = iArr[indexOf];
        }
        this.i--;
        solverVariable.usageInRowCount--;
        if (z) {
            solverVariable.removeFromRow(this.k);
        }
        return f;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public int sizeInBytes() {
        return 0;
    }

    public String toString() {
        String str = hashCode() + " { ";
        int i = this.i;
        for (int i2 = 0; i2 < i; i2++) {
            SolverVariable variable = getVariable(i2);
            if (variable != null) {
                String str2 = str + variable + " = " + getVariableValue(i2) + HexStringBuilder.DEFAULT_SEPARATOR;
                int indexOf = indexOf(variable);
                String str3 = str2 + "[p: ";
                String str4 = (this.g[indexOf] != -1 ? str3 + this.mCache.d[this.e[this.g[indexOf]]] : str3 + "none") + ", n: ";
                str = (this.h[indexOf] != -1 ? str4 + this.mCache.d[this.e[this.h[indexOf]]] : str4 + "none") + "]";
            }
        }
        return str + " }";
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public float use(ArrayRow arrayRow, boolean z) {
        float f = get(arrayRow.f852a);
        remove(arrayRow.f852a, z);
        SolverVariableValues solverVariableValues = (SolverVariableValues) arrayRow.variables;
        int currentSize = solverVariableValues.getCurrentSize();
        int i = 0;
        int i2 = 0;
        while (i < currentSize) {
            int[] iArr = solverVariableValues.e;
            if (iArr[i2] != -1) {
                add(this.mCache.d[iArr[i2]], solverVariableValues.f[i2] * f, z);
                i++;
            }
            i2++;
        }
        return f;
    }
}
