package androidx.constraintlayout.core;

import androidx.constraintlayout.core.ArrayRow;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Arrays;
import java.util.Comparator;
/* loaded from: classes.dex */
public class PriorityGoalRow extends ArrayRow {
    public int f;
    public SolverVariable[] g;
    public SolverVariable[] h;
    public int i;
    public b j;

    /* loaded from: classes.dex */
    public class a implements Comparator<SolverVariable> {
        public a(PriorityGoalRow priorityGoalRow) {
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(SolverVariable solverVariable, SolverVariable solverVariable2) {
            return solverVariable.id - solverVariable2.id;
        }
    }

    /* loaded from: classes.dex */
    public class b {

        /* renamed from: a  reason: collision with root package name */
        public SolverVariable f855a;

        public b(PriorityGoalRow priorityGoalRow) {
        }

        public boolean a(SolverVariable solverVariable, float f) {
            boolean z = true;
            if (!this.f855a.inGoal) {
                for (int i = 0; i < 9; i++) {
                    float f2 = solverVariable.k[i];
                    if (f2 != 0.0f) {
                        float f3 = f2 * f;
                        if (Math.abs(f3) < 1.0E-4f) {
                            f3 = 0.0f;
                        }
                        this.f855a.k[i] = f3;
                    } else {
                        this.f855a.k[i] = 0.0f;
                    }
                }
                return true;
            }
            for (int i2 = 0; i2 < 9; i2++) {
                float[] fArr = this.f855a.k;
                fArr[i2] = fArr[i2] + (solverVariable.k[i2] * f);
                if (Math.abs(fArr[i2]) < 1.0E-4f) {
                    this.f855a.k[i2] = 0.0f;
                } else {
                    z = false;
                }
            }
            if (z) {
                PriorityGoalRow.this.q(this.f855a);
            }
            return false;
        }

        public void b(SolverVariable solverVariable) {
            this.f855a = solverVariable;
        }

        public final boolean c() {
            for (int i = 8; i >= 0; i--) {
                float f = this.f855a.k[i];
                if (f > 0.0f) {
                    return false;
                }
                if (f < 0.0f) {
                    return true;
                }
            }
            return false;
        }

        public final boolean d(SolverVariable solverVariable) {
            int i = 8;
            while (true) {
                if (i < 0) {
                    break;
                }
                float f = solverVariable.k[i];
                float f2 = this.f855a.k[i];
                if (f2 == f) {
                    i--;
                } else if (f2 < f) {
                    return true;
                }
            }
            return false;
        }

        public void e() {
            Arrays.fill(this.f855a.k, 0.0f);
        }

        public String toString() {
            String str = "[ ";
            if (this.f855a != null) {
                for (int i = 0; i < 9; i++) {
                    str = str + this.f855a.k[i] + HexStringBuilder.DEFAULT_SEPARATOR;
                }
            }
            return str + "] " + this.f855a;
        }
    }

    public PriorityGoalRow(Cache cache) {
        super(cache);
        this.f = 128;
        this.g = new SolverVariable[128];
        this.h = new SolverVariable[128];
        this.i = 0;
        this.j = new b(this);
    }

    @Override // androidx.constraintlayout.core.ArrayRow, androidx.constraintlayout.core.LinearSystem.a
    public void addError(SolverVariable solverVariable) {
        this.j.b(solverVariable);
        this.j.e();
        solverVariable.k[solverVariable.strength] = 1.0f;
        p(solverVariable);
    }

    @Override // androidx.constraintlayout.core.ArrayRow, androidx.constraintlayout.core.LinearSystem.a
    public void clear() {
        this.i = 0;
        this.b = 0.0f;
    }

    @Override // androidx.constraintlayout.core.ArrayRow, androidx.constraintlayout.core.LinearSystem.a
    public SolverVariable getPivotCandidate(LinearSystem linearSystem, boolean[] zArr) {
        int i = -1;
        for (int i2 = 0; i2 < this.i; i2++) {
            SolverVariable solverVariable = this.g[i2];
            if (!zArr[solverVariable.id]) {
                this.j.b(solverVariable);
                if (i == -1) {
                    if (!this.j.c()) {
                    }
                    i = i2;
                } else {
                    if (!this.j.d(this.g[i])) {
                    }
                    i = i2;
                }
            }
        }
        if (i == -1) {
            return null;
        }
        return this.g[i];
    }

    @Override // androidx.constraintlayout.core.ArrayRow, androidx.constraintlayout.core.LinearSystem.a
    public boolean isEmpty() {
        return this.i == 0;
    }

    public final void p(SolverVariable solverVariable) {
        int i;
        int i2 = this.i + 1;
        SolverVariable[] solverVariableArr = this.g;
        if (i2 > solverVariableArr.length) {
            SolverVariable[] solverVariableArr2 = (SolverVariable[]) Arrays.copyOf(solverVariableArr, solverVariableArr.length * 2);
            this.g = solverVariableArr2;
            this.h = (SolverVariable[]) Arrays.copyOf(solverVariableArr2, solverVariableArr2.length * 2);
        }
        SolverVariable[] solverVariableArr3 = this.g;
        int i3 = this.i;
        solverVariableArr3[i3] = solverVariable;
        int i4 = i3 + 1;
        this.i = i4;
        if (i4 > 1 && solverVariableArr3[i4 - 1].id > solverVariable.id) {
            int i5 = 0;
            while (true) {
                i = this.i;
                if (i5 >= i) {
                    break;
                }
                this.h[i5] = this.g[i5];
                i5++;
            }
            Arrays.sort(this.h, 0, i, new a(this));
            for (int i6 = 0; i6 < this.i; i6++) {
                this.g[i6] = this.h[i6];
            }
        }
        solverVariable.inGoal = true;
        solverVariable.addToRow(this);
    }

    public final void q(SolverVariable solverVariable) {
        int i = 0;
        while (i < this.i) {
            if (this.g[i] == solverVariable) {
                while (true) {
                    int i2 = this.i;
                    if (i < i2 - 1) {
                        SolverVariable[] solverVariableArr = this.g;
                        int i3 = i + 1;
                        solverVariableArr[i] = solverVariableArr[i3];
                        i = i3;
                    } else {
                        this.i = i2 - 1;
                        solverVariable.inGoal = false;
                        return;
                    }
                }
            } else {
                i++;
            }
        }
    }

    @Override // androidx.constraintlayout.core.ArrayRow
    public String toString() {
        String str = " goal -> (" + this.b + ") : ";
        for (int i = 0; i < this.i; i++) {
            this.j.b(this.g[i]);
            str = str + this.j + HexStringBuilder.DEFAULT_SEPARATOR;
        }
        return str;
    }

    @Override // androidx.constraintlayout.core.ArrayRow
    public void updateFromRow(LinearSystem linearSystem, ArrayRow arrayRow, boolean z) {
        SolverVariable solverVariable = arrayRow.f852a;
        if (solverVariable == null) {
            return;
        }
        ArrayRow.ArrayRowVariables arrayRowVariables = arrayRow.variables;
        int currentSize = arrayRowVariables.getCurrentSize();
        for (int i = 0; i < currentSize; i++) {
            SolverVariable variable = arrayRowVariables.getVariable(i);
            float variableValue = arrayRowVariables.getVariableValue(i);
            this.j.b(variable);
            if (this.j.a(solverVariable, variableValue)) {
                p(variable);
            }
            this.b += arrayRow.b * variableValue;
        }
        q(solverVariable);
    }
}
