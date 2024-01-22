package androidx.constraintlayout.core;

import androidx.constraintlayout.core.ArrayRow;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.PrintStream;
import java.util.Arrays;
/* loaded from: classes.dex */
public class ArrayLinkedVariables implements ArrayRow.ArrayRowVariables {
    public static float k = 0.001f;
    public final ArrayRow b;
    public final Cache mCache;

    /* renamed from: a  reason: collision with root package name */
    public int f851a = 0;
    public int c = 8;
    public SolverVariable d = null;
    public int[] e = new int[8];
    public int[] f = new int[8];
    public float[] g = new float[8];
    public int h = -1;
    public int i = -1;
    public boolean j = false;

    public ArrayLinkedVariables(ArrayRow arrayRow, Cache cache) {
        this.b = arrayRow;
        this.mCache = cache;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void add(SolverVariable solverVariable, float f, boolean z) {
        float f2 = k;
        if (f <= (-f2) || f >= f2) {
            int i = this.h;
            if (i == -1) {
                this.h = 0;
                this.g[0] = f;
                this.e[0] = solverVariable.id;
                this.f[0] = -1;
                solverVariable.usageInRowCount++;
                solverVariable.addToRow(this.b);
                this.f851a++;
                if (this.j) {
                    return;
                }
                int i2 = this.i + 1;
                this.i = i2;
                int[] iArr = this.e;
                if (i2 >= iArr.length) {
                    this.j = true;
                    this.i = iArr.length - 1;
                    return;
                }
                return;
            }
            int i3 = -1;
            for (int i4 = 0; i != -1 && i4 < this.f851a; i4++) {
                int[] iArr2 = this.e;
                int i5 = iArr2[i];
                int i6 = solverVariable.id;
                if (i5 == i6) {
                    float[] fArr = this.g;
                    float f3 = fArr[i] + f;
                    float f4 = k;
                    if (f3 > (-f4) && f3 < f4) {
                        f3 = 0.0f;
                    }
                    fArr[i] = f3;
                    if (f3 == 0.0f) {
                        if (i == this.h) {
                            this.h = this.f[i];
                        } else {
                            int[] iArr3 = this.f;
                            iArr3[i3] = iArr3[i];
                        }
                        if (z) {
                            solverVariable.removeFromRow(this.b);
                        }
                        if (this.j) {
                            this.i = i;
                        }
                        solverVariable.usageInRowCount--;
                        this.f851a--;
                        return;
                    }
                    return;
                }
                if (iArr2[i] < i6) {
                    i3 = i;
                }
                i = this.f[i];
            }
            int i7 = this.i;
            int i8 = i7 + 1;
            if (this.j) {
                int[] iArr4 = this.e;
                if (iArr4[i7] != -1) {
                    i7 = iArr4.length;
                }
            } else {
                i7 = i8;
            }
            int[] iArr5 = this.e;
            if (i7 >= iArr5.length && this.f851a < iArr5.length) {
                int i9 = 0;
                while (true) {
                    int[] iArr6 = this.e;
                    if (i9 >= iArr6.length) {
                        break;
                    } else if (iArr6[i9] == -1) {
                        i7 = i9;
                        break;
                    } else {
                        i9++;
                    }
                }
            }
            int[] iArr7 = this.e;
            if (i7 >= iArr7.length) {
                i7 = iArr7.length;
                int i10 = this.c * 2;
                this.c = i10;
                this.j = false;
                this.i = i7 - 1;
                this.g = Arrays.copyOf(this.g, i10);
                this.e = Arrays.copyOf(this.e, this.c);
                this.f = Arrays.copyOf(this.f, this.c);
            }
            this.e[i7] = solverVariable.id;
            this.g[i7] = f;
            if (i3 != -1) {
                int[] iArr8 = this.f;
                iArr8[i7] = iArr8[i3];
                iArr8[i3] = i7;
            } else {
                this.f[i7] = this.h;
                this.h = i7;
            }
            solverVariable.usageInRowCount++;
            solverVariable.addToRow(this.b);
            this.f851a++;
            if (!this.j) {
                this.i++;
            }
            int i11 = this.i;
            int[] iArr9 = this.e;
            if (i11 >= iArr9.length) {
                this.j = true;
                this.i = iArr9.length - 1;
            }
        }
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public final void clear() {
        int i = this.h;
        for (int i2 = 0; i != -1 && i2 < this.f851a; i2++) {
            SolverVariable solverVariable = this.mCache.d[this.e[i]];
            if (solverVariable != null) {
                solverVariable.removeFromRow(this.b);
            }
            i = this.f[i];
        }
        this.h = -1;
        this.i = -1;
        this.j = false;
        this.f851a = 0;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public boolean contains(SolverVariable solverVariable) {
        int i = this.h;
        if (i == -1) {
            return false;
        }
        for (int i2 = 0; i != -1 && i2 < this.f851a; i2++) {
            if (this.e[i] == solverVariable.id) {
                return true;
            }
            i = this.f[i];
        }
        return false;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void display() {
        int i = this.f851a;
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
        int i = this.h;
        for (int i2 = 0; i != -1 && i2 < this.f851a; i2++) {
            float[] fArr = this.g;
            fArr[i] = fArr[i] / f;
            i = this.f[i];
        }
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public final float get(SolverVariable solverVariable) {
        int i = this.h;
        for (int i2 = 0; i != -1 && i2 < this.f851a; i2++) {
            if (this.e[i] == solverVariable.id) {
                return this.g[i];
            }
            i = this.f[i];
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public int getCurrentSize() {
        return this.f851a;
    }

    public int getHead() {
        return this.h;
    }

    public final int getId(int i) {
        return this.e[i];
    }

    public final int getNextIndice(int i) {
        return this.f[i];
    }

    public final float getValue(int i) {
        return this.g[i];
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public SolverVariable getVariable(int i) {
        int i2 = this.h;
        for (int i3 = 0; i2 != -1 && i3 < this.f851a; i3++) {
            if (i3 == i) {
                return this.mCache.d[this.e[i2]];
            }
            i2 = this.f[i2];
        }
        return null;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public float getVariableValue(int i) {
        int i2 = this.h;
        for (int i3 = 0; i2 != -1 && i3 < this.f851a; i3++) {
            if (i3 == i) {
                return this.g[i2];
            }
            i2 = this.f[i2];
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public int indexOf(SolverVariable solverVariable) {
        int i = this.h;
        if (i == -1) {
            return -1;
        }
        for (int i2 = 0; i != -1 && i2 < this.f851a; i2++) {
            if (this.e[i] == solverVariable.id) {
                return i;
            }
            i = this.f[i];
        }
        return -1;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void invert() {
        int i = this.h;
        for (int i2 = 0; i != -1 && i2 < this.f851a; i2++) {
            float[] fArr = this.g;
            fArr[i] = fArr[i] * (-1.0f);
            i = this.f[i];
        }
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public final void put(SolverVariable solverVariable, float f) {
        if (f == 0.0f) {
            remove(solverVariable, true);
            return;
        }
        int i = this.h;
        if (i == -1) {
            this.h = 0;
            this.g[0] = f;
            this.e[0] = solverVariable.id;
            this.f[0] = -1;
            solverVariable.usageInRowCount++;
            solverVariable.addToRow(this.b);
            this.f851a++;
            if (this.j) {
                return;
            }
            int i2 = this.i + 1;
            this.i = i2;
            int[] iArr = this.e;
            if (i2 >= iArr.length) {
                this.j = true;
                this.i = iArr.length - 1;
                return;
            }
            return;
        }
        int i3 = -1;
        for (int i4 = 0; i != -1 && i4 < this.f851a; i4++) {
            int[] iArr2 = this.e;
            int i5 = iArr2[i];
            int i6 = solverVariable.id;
            if (i5 == i6) {
                this.g[i] = f;
                return;
            }
            if (iArr2[i] < i6) {
                i3 = i;
            }
            i = this.f[i];
        }
        int i7 = this.i;
        int i8 = i7 + 1;
        if (this.j) {
            int[] iArr3 = this.e;
            if (iArr3[i7] != -1) {
                i7 = iArr3.length;
            }
        } else {
            i7 = i8;
        }
        int[] iArr4 = this.e;
        if (i7 >= iArr4.length && this.f851a < iArr4.length) {
            int i9 = 0;
            while (true) {
                int[] iArr5 = this.e;
                if (i9 >= iArr5.length) {
                    break;
                } else if (iArr5[i9] == -1) {
                    i7 = i9;
                    break;
                } else {
                    i9++;
                }
            }
        }
        int[] iArr6 = this.e;
        if (i7 >= iArr6.length) {
            i7 = iArr6.length;
            int i10 = this.c * 2;
            this.c = i10;
            this.j = false;
            this.i = i7 - 1;
            this.g = Arrays.copyOf(this.g, i10);
            this.e = Arrays.copyOf(this.e, this.c);
            this.f = Arrays.copyOf(this.f, this.c);
        }
        this.e[i7] = solverVariable.id;
        this.g[i7] = f;
        if (i3 != -1) {
            int[] iArr7 = this.f;
            iArr7[i7] = iArr7[i3];
            iArr7[i3] = i7;
        } else {
            this.f[i7] = this.h;
            this.h = i7;
        }
        solverVariable.usageInRowCount++;
        solverVariable.addToRow(this.b);
        int i11 = this.f851a + 1;
        this.f851a = i11;
        if (!this.j) {
            this.i++;
        }
        int[] iArr8 = this.e;
        if (i11 >= iArr8.length) {
            this.j = true;
        }
        if (this.i >= iArr8.length) {
            this.j = true;
            this.i = iArr8.length - 1;
        }
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public final float remove(SolverVariable solverVariable, boolean z) {
        if (this.d == solverVariable) {
            this.d = null;
        }
        int i = this.h;
        if (i == -1) {
            return 0.0f;
        }
        int i2 = 0;
        int i3 = -1;
        while (i != -1 && i2 < this.f851a) {
            if (this.e[i] == solverVariable.id) {
                if (i == this.h) {
                    this.h = this.f[i];
                } else {
                    int[] iArr = this.f;
                    iArr[i3] = iArr[i];
                }
                if (z) {
                    solverVariable.removeFromRow(this.b);
                }
                solverVariable.usageInRowCount--;
                this.f851a--;
                this.e[i] = -1;
                if (this.j) {
                    this.i = i;
                }
                return this.g[i];
            }
            i2++;
            i3 = i;
            i = this.f[i];
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public int sizeInBytes() {
        return (this.e.length * 4 * 3) + 0 + 36;
    }

    public String toString() {
        int i = this.h;
        String str = "";
        for (int i2 = 0; i != -1 && i2 < this.f851a; i2++) {
            str = ((str + " -> ") + this.g[i] + " : ") + this.mCache.d[this.e[i]];
            i = this.f[i];
        }
        return str;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public float use(ArrayRow arrayRow, boolean z) {
        float f = get(arrayRow.f852a);
        remove(arrayRow.f852a, z);
        ArrayRow.ArrayRowVariables arrayRowVariables = arrayRow.variables;
        int currentSize = arrayRowVariables.getCurrentSize();
        for (int i = 0; i < currentSize; i++) {
            SolverVariable variable = arrayRowVariables.getVariable(i);
            add(variable, arrayRowVariables.get(variable) * f, z);
        }
        return f;
    }
}
