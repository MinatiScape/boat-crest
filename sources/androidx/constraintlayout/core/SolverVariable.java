package androidx.constraintlayout.core;

import java.util.Arrays;
/* loaded from: classes.dex */
public class SolverVariable implements Comparable<SolverVariable> {
    public static final int STRENGTH_BARRIER = 6;
    public static final int STRENGTH_CENTERING = 7;
    public static final int STRENGTH_EQUALITY = 5;
    public static final int STRENGTH_FIXED = 8;
    public static final int STRENGTH_HIGH = 3;
    public static final int STRENGTH_HIGHEST = 4;
    public static final int STRENGTH_LOW = 1;
    public static final int STRENGTH_MEDIUM = 2;
    public static final int STRENGTH_NONE = 0;
    public static int r = 1;
    public float computedValue;
    public String h;
    public int i;
    public int id;
    public boolean inGoal;
    public boolean isFinalValue;
    public float[] j;
    public float[] k;
    public Type l;
    public ArrayRow[] m;
    public int n;
    public boolean o;
    public int p;
    public float q;
    public int strength;
    public int usageInRowCount;

    /* loaded from: classes.dex */
    public enum Type {
        UNRESTRICTED,
        CONSTANT,
        SLACK,
        ERROR,
        UNKNOWN
    }

    public SolverVariable(String str, Type type) {
        this.id = -1;
        this.i = -1;
        this.strength = 0;
        this.isFinalValue = false;
        this.j = new float[9];
        this.k = new float[9];
        this.m = new ArrayRow[16];
        this.n = 0;
        this.usageInRowCount = 0;
        this.o = false;
        this.p = -1;
        this.q = 0.0f;
        this.h = str;
        this.l = type;
    }

    public static void a() {
        r++;
    }

    public final void addToRow(ArrayRow arrayRow) {
        int i = 0;
        while (true) {
            int i2 = this.n;
            if (i < i2) {
                if (this.m[i] == arrayRow) {
                    return;
                }
                i++;
            } else {
                ArrayRow[] arrayRowArr = this.m;
                if (i2 >= arrayRowArr.length) {
                    this.m = (ArrayRow[]) Arrays.copyOf(arrayRowArr, arrayRowArr.length * 2);
                }
                ArrayRow[] arrayRowArr2 = this.m;
                int i3 = this.n;
                arrayRowArr2[i3] = arrayRow;
                this.n = i3 + 1;
                return;
            }
        }
    }

    public String getName() {
        return this.h;
    }

    public final void removeFromRow(ArrayRow arrayRow) {
        int i = this.n;
        int i2 = 0;
        while (i2 < i) {
            if (this.m[i2] == arrayRow) {
                while (i2 < i - 1) {
                    ArrayRow[] arrayRowArr = this.m;
                    int i3 = i2 + 1;
                    arrayRowArr[i2] = arrayRowArr[i3];
                    i2 = i3;
                }
                this.n--;
                return;
            }
            i2++;
        }
    }

    public void reset() {
        this.h = null;
        this.l = Type.UNKNOWN;
        this.strength = 0;
        this.id = -1;
        this.i = -1;
        this.computedValue = 0.0f;
        this.isFinalValue = false;
        this.o = false;
        this.p = -1;
        this.q = 0.0f;
        int i = this.n;
        for (int i2 = 0; i2 < i; i2++) {
            this.m[i2] = null;
        }
        this.n = 0;
        this.usageInRowCount = 0;
        this.inGoal = false;
        Arrays.fill(this.k, 0.0f);
    }

    public void setFinalValue(LinearSystem linearSystem, float f) {
        this.computedValue = f;
        this.isFinalValue = true;
        this.o = false;
        this.p = -1;
        this.q = 0.0f;
        int i = this.n;
        this.i = -1;
        for (int i2 = 0; i2 < i; i2++) {
            this.m[i2].updateFromFinalVariable(linearSystem, this, false);
        }
        this.n = 0;
    }

    public void setName(String str) {
        this.h = str;
    }

    public void setSynonym(LinearSystem linearSystem, SolverVariable solverVariable, float f) {
        this.o = true;
        this.p = solverVariable.id;
        this.q = f;
        int i = this.n;
        this.i = -1;
        for (int i2 = 0; i2 < i; i2++) {
            this.m[i2].updateFromSynonymVariable(linearSystem, this, false);
        }
        this.n = 0;
        linearSystem.displayReadableRows();
    }

    public void setType(Type type, String str) {
        this.l = type;
    }

    public String toString() {
        if (this.h != null) {
            return "" + this.h;
        }
        return "" + this.id;
    }

    public final void updateReferencesWithNewDefinition(LinearSystem linearSystem, ArrayRow arrayRow) {
        int i = this.n;
        for (int i2 = 0; i2 < i; i2++) {
            this.m[i2].updateFromRow(linearSystem, arrayRow, false);
        }
        this.n = 0;
    }

    @Override // java.lang.Comparable
    public int compareTo(SolverVariable solverVariable) {
        return this.id - solverVariable.id;
    }

    public SolverVariable(Type type, String str) {
        this.id = -1;
        this.i = -1;
        this.strength = 0;
        this.isFinalValue = false;
        this.j = new float[9];
        this.k = new float[9];
        this.m = new ArrayRow[16];
        this.n = 0;
        this.usageInRowCount = 0;
        this.o = false;
        this.p = -1;
        this.q = 0.0f;
        this.l = type;
    }
}
