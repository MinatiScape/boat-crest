package androidx.constraintlayout.core;

import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.Arrays;
import java.util.HashMap;
/* loaded from: classes.dex */
public class LinearSystem {
    public static long ARRAY_ROW_CREATION = 0;
    public static final boolean DEBUG = false;
    public static final boolean FULL_DEBUG = false;
    public static final boolean MEASURE = false;
    public static long OPTIMIZED_ARRAY_ROW_CREATION = 0;
    public static boolean OPTIMIZED_ENGINE = false;
    public static boolean SIMPLIFY_SYNONYMS = true;
    public static boolean SKIP_COLUMNS = true;
    public static boolean USE_BASIC_SYNONYMS = true;
    public static boolean USE_DEPENDENCY_ORDERING = false;
    public static boolean USE_SYNONYMS = true;
    public static int o = 1000;
    public static Metrics sMetrics;
    public a c;
    public ArrayRow[] f;
    public final Cache k;
    public a n;
    public boolean hasSimpleDefinition = false;

    /* renamed from: a  reason: collision with root package name */
    public int f854a = 0;
    public HashMap<String, SolverVariable> b = null;
    public int d = 32;
    public int e = 32;
    public boolean graphOptimizer = false;
    public boolean newgraphOptimizer = false;
    public boolean[] g = new boolean[32];
    public int h = 1;
    public int i = 0;
    public int j = 32;
    public SolverVariable[] l = new SolverVariable[o];
    public int m = 0;

    /* loaded from: classes.dex */
    public interface a {
        void addError(SolverVariable solverVariable);

        void clear();

        SolverVariable getKey();

        SolverVariable getPivotCandidate(LinearSystem linearSystem, boolean[] zArr);

        void initFromRow(a aVar);

        boolean isEmpty();
    }

    /* loaded from: classes.dex */
    public class b extends ArrayRow {
        public b(LinearSystem linearSystem, Cache cache) {
            this.variables = new SolverVariableValues(this, cache);
        }
    }

    public LinearSystem() {
        this.f = null;
        this.f = new ArrayRow[32];
        j();
        Cache cache = new Cache();
        this.k = cache;
        this.c = new PriorityGoalRow(cache);
        if (OPTIMIZED_ENGINE) {
            this.n = new b(this, cache);
        } else {
            this.n = new ArrayRow(cache);
        }
    }

    public static ArrayRow createRowDimensionPercent(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, float f) {
        return linearSystem.createRow().f(solverVariable, solverVariable2, f);
    }

    public static Metrics getMetrics() {
        return sMetrics;
    }

    public final SolverVariable a(SolverVariable.Type type, String str) {
        SolverVariable acquire = this.k.c.acquire();
        if (acquire == null) {
            acquire = new SolverVariable(type, str);
            acquire.setType(type, str);
        } else {
            acquire.reset();
            acquire.setType(type, str);
        }
        int i = this.m;
        int i2 = o;
        if (i >= i2) {
            int i3 = i2 * 2;
            o = i3;
            this.l = (SolverVariable[]) Arrays.copyOf(this.l, i3);
        }
        SolverVariable[] solverVariableArr = this.l;
        int i4 = this.m;
        this.m = i4 + 1;
        solverVariableArr[i4] = acquire;
        return acquire;
    }

    public void addCenterPoint(ConstraintWidget constraintWidget, ConstraintWidget constraintWidget2, float f, int i) {
        ConstraintAnchor.Type type = ConstraintAnchor.Type.LEFT;
        SolverVariable createObjectVariable = createObjectVariable(constraintWidget.getAnchor(type));
        ConstraintAnchor.Type type2 = ConstraintAnchor.Type.TOP;
        SolverVariable createObjectVariable2 = createObjectVariable(constraintWidget.getAnchor(type2));
        ConstraintAnchor.Type type3 = ConstraintAnchor.Type.RIGHT;
        SolverVariable createObjectVariable3 = createObjectVariable(constraintWidget.getAnchor(type3));
        ConstraintAnchor.Type type4 = ConstraintAnchor.Type.BOTTOM;
        SolverVariable createObjectVariable4 = createObjectVariable(constraintWidget.getAnchor(type4));
        SolverVariable createObjectVariable5 = createObjectVariable(constraintWidget2.getAnchor(type));
        SolverVariable createObjectVariable6 = createObjectVariable(constraintWidget2.getAnchor(type2));
        SolverVariable createObjectVariable7 = createObjectVariable(constraintWidget2.getAnchor(type3));
        SolverVariable createObjectVariable8 = createObjectVariable(constraintWidget2.getAnchor(type4));
        ArrayRow createRow = createRow();
        double d = f;
        double d2 = i;
        createRow.createRowWithAngle(createObjectVariable2, createObjectVariable4, createObjectVariable6, createObjectVariable8, (float) (Math.sin(d) * d2));
        addConstraint(createRow);
        ArrayRow createRow2 = createRow();
        createRow2.createRowWithAngle(createObjectVariable, createObjectVariable3, createObjectVariable5, createObjectVariable7, (float) (Math.cos(d) * d2));
        addConstraint(createRow2);
    }

    public void addCentering(SolverVariable solverVariable, SolverVariable solverVariable2, int i, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i2, int i3) {
        ArrayRow createRow = createRow();
        createRow.d(solverVariable, solverVariable2, i, f, solverVariable3, solverVariable4, i2);
        if (i3 != 8) {
            createRow.addError(this, i3);
        }
        addConstraint(createRow);
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x009f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00a0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void addConstraint(androidx.constraintlayout.core.ArrayRow r8) {
        /*
            r7 = this;
            if (r8 != 0) goto L3
            return
        L3:
            androidx.constraintlayout.core.Metrics r0 = androidx.constraintlayout.core.LinearSystem.sMetrics
            r1 = 1
            if (r0 == 0) goto L17
            long r3 = r0.constraints
            long r3 = r3 + r1
            r0.constraints = r3
            boolean r3 = r8.e
            if (r3 == 0) goto L17
            long r3 = r0.simpleconstraints
            long r3 = r3 + r1
            r0.simpleconstraints = r3
        L17:
            int r0 = r7.i
            r3 = 1
            int r0 = r0 + r3
            int r4 = r7.j
            if (r0 >= r4) goto L26
            int r0 = r7.h
            int r0 = r0 + r3
            int r4 = r7.e
            if (r0 < r4) goto L29
        L26:
            r7.g()
        L29:
            r0 = 0
            boolean r4 = r8.e
            if (r4 != 0) goto La1
            r8.updateFromSystem(r7)
            boolean r4 = r8.isEmpty()
            if (r4 == 0) goto L38
            return
        L38:
            r8.g()
            boolean r4 = r8.b(r7)
            if (r4 == 0) goto L98
            androidx.constraintlayout.core.SolverVariable r4 = r7.createExtraVariable()
            r8.f852a = r4
            int r5 = r7.i
            r7.b(r8)
            int r6 = r7.i
            int r5 = r5 + r3
            if (r6 != r5) goto L98
            androidx.constraintlayout.core.LinearSystem$a r0 = r7.n
            r0.initFromRow(r8)
            androidx.constraintlayout.core.LinearSystem$a r0 = r7.n
            r7.i(r0, r3)
            int r0 = r4.i
            r5 = -1
            if (r0 != r5) goto L99
            androidx.constraintlayout.core.SolverVariable r0 = r8.f852a
            if (r0 != r4) goto L76
            androidx.constraintlayout.core.SolverVariable r0 = r8.pickPivot(r4)
            if (r0 == 0) goto L76
            androidx.constraintlayout.core.Metrics r4 = androidx.constraintlayout.core.LinearSystem.sMetrics
            if (r4 == 0) goto L73
            long r5 = r4.pivots
            long r5 = r5 + r1
            r4.pivots = r5
        L73:
            r8.l(r0)
        L76:
            boolean r0 = r8.e
            if (r0 != 0) goto L7f
            androidx.constraintlayout.core.SolverVariable r0 = r8.f852a
            r0.updateReferencesWithNewDefinition(r7, r8)
        L7f:
            boolean r0 = androidx.constraintlayout.core.LinearSystem.OPTIMIZED_ENGINE
            if (r0 == 0) goto L8b
            androidx.constraintlayout.core.Cache r0 = r7.k
            androidx.constraintlayout.core.a<androidx.constraintlayout.core.ArrayRow> r0 = r0.f853a
            r0.release(r8)
            goto L92
        L8b:
            androidx.constraintlayout.core.Cache r0 = r7.k
            androidx.constraintlayout.core.a<androidx.constraintlayout.core.ArrayRow> r0 = r0.b
            r0.release(r8)
        L92:
            int r0 = r7.i
            int r0 = r0 - r3
            r7.i = r0
            goto L99
        L98:
            r3 = r0
        L99:
            boolean r0 = r8.h()
            if (r0 != 0) goto La0
            return
        La0:
            r0 = r3
        La1:
            if (r0 != 0) goto La6
            r7.b(r8)
        La6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.LinearSystem.addConstraint(androidx.constraintlayout.core.ArrayRow):void");
    }

    public ArrayRow addEquality(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        if (USE_BASIC_SYNONYMS && i2 == 8 && solverVariable2.isFinalValue && solverVariable.i == -1) {
            solverVariable.setFinalValue(this, solverVariable2.computedValue + i);
            return null;
        }
        ArrayRow createRow = createRow();
        createRow.createRowEquals(solverVariable, solverVariable2, i);
        if (i2 != 8) {
            createRow.addError(this, i2);
        }
        addConstraint(createRow);
        return createRow;
    }

    public void addGreaterBarrier(SolverVariable solverVariable, SolverVariable solverVariable2, int i, boolean z) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowGreaterThan(solverVariable, solverVariable2, createSlackVariable, i);
        addConstraint(createRow);
    }

    public void addGreaterThan(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowGreaterThan(solverVariable, solverVariable2, createSlackVariable, i);
        if (i2 != 8) {
            c(createRow, (int) (createRow.variables.get(createSlackVariable) * (-1.0f)), i2);
        }
        addConstraint(createRow);
    }

    public void addLowerBarrier(SolverVariable solverVariable, SolverVariable solverVariable2, int i, boolean z) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowLowerThan(solverVariable, solverVariable2, createSlackVariable, i);
        addConstraint(createRow);
    }

    public void addLowerThan(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowLowerThan(solverVariable, solverVariable2, createSlackVariable, i);
        if (i2 != 8) {
            c(createRow, (int) (createRow.variables.get(createSlackVariable) * (-1.0f)), i2);
        }
        addConstraint(createRow);
    }

    public void addRatio(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f, int i) {
        ArrayRow createRow = createRow();
        createRow.createRowDimensionRatio(solverVariable, solverVariable2, solverVariable3, solverVariable4, f);
        if (i != 8) {
            createRow.addError(this, i);
        }
        addConstraint(createRow);
    }

    public void addSynonym(SolverVariable solverVariable, SolverVariable solverVariable2, int i) {
        if (solverVariable.i == -1 && i == 0) {
            if (solverVariable2.o) {
                solverVariable2 = this.k.d[solverVariable2.p];
            }
            if (solverVariable.o) {
                SolverVariable solverVariable3 = this.k.d[solverVariable.p];
                return;
            } else {
                solverVariable.setSynonym(this, solverVariable2, 0.0f);
                return;
            }
        }
        addEquality(solverVariable, solverVariable2, i, 8);
    }

    public final void b(ArrayRow arrayRow) {
        int i;
        if (SIMPLIFY_SYNONYMS && arrayRow.e) {
            arrayRow.f852a.setFinalValue(this, arrayRow.b);
        } else {
            ArrayRow[] arrayRowArr = this.f;
            int i2 = this.i;
            arrayRowArr[i2] = arrayRow;
            SolverVariable solverVariable = arrayRow.f852a;
            solverVariable.i = i2;
            this.i = i2 + 1;
            solverVariable.updateReferencesWithNewDefinition(this, arrayRow);
        }
        if (SIMPLIFY_SYNONYMS && this.hasSimpleDefinition) {
            int i3 = 0;
            while (i3 < this.i) {
                if (this.f[i3] == null) {
                    System.out.println("WTF");
                }
                ArrayRow[] arrayRowArr2 = this.f;
                if (arrayRowArr2[i3] != null && arrayRowArr2[i3].e) {
                    ArrayRow arrayRow2 = arrayRowArr2[i3];
                    arrayRow2.f852a.setFinalValue(this, arrayRow2.b);
                    if (OPTIMIZED_ENGINE) {
                        this.k.f853a.release(arrayRow2);
                    } else {
                        this.k.b.release(arrayRow2);
                    }
                    this.f[i3] = null;
                    int i4 = i3 + 1;
                    int i5 = i4;
                    while (true) {
                        i = this.i;
                        if (i4 >= i) {
                            break;
                        }
                        ArrayRow[] arrayRowArr3 = this.f;
                        int i6 = i4 - 1;
                        arrayRowArr3[i6] = arrayRowArr3[i4];
                        if (arrayRowArr3[i6].f852a.i == i4) {
                            arrayRowArr3[i6].f852a.i = i6;
                        }
                        i5 = i4;
                        i4++;
                    }
                    if (i5 < i) {
                        this.f[i5] = null;
                    }
                    this.i = i - 1;
                    i3--;
                }
                i3++;
            }
            this.hasSimpleDefinition = false;
        }
    }

    public void c(ArrayRow arrayRow, int i, int i2) {
        arrayRow.a(createErrorVariable(i2, null), i);
    }

    public SolverVariable createErrorVariable(int i, String str) {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.errors++;
        }
        if (this.h + 1 >= this.e) {
            g();
        }
        SolverVariable a2 = a(SolverVariable.Type.ERROR, str);
        int i2 = this.f854a + 1;
        this.f854a = i2;
        this.h++;
        a2.id = i2;
        a2.strength = i;
        this.k.d[i2] = a2;
        this.c.addError(a2);
        return a2;
    }

    public SolverVariable createExtraVariable() {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.extravariables++;
        }
        if (this.h + 1 >= this.e) {
            g();
        }
        SolverVariable a2 = a(SolverVariable.Type.SLACK, null);
        int i = this.f854a + 1;
        this.f854a = i;
        this.h++;
        a2.id = i;
        this.k.d[i] = a2;
        return a2;
    }

    public SolverVariable createObjectVariable(Object obj) {
        SolverVariable solverVariable = null;
        if (obj == null) {
            return null;
        }
        if (this.h + 1 >= this.e) {
            g();
        }
        if (obj instanceof ConstraintAnchor) {
            ConstraintAnchor constraintAnchor = (ConstraintAnchor) obj;
            solverVariable = constraintAnchor.getSolverVariable();
            if (solverVariable == null) {
                constraintAnchor.resetSolverVariable(this.k);
                solverVariable = constraintAnchor.getSolverVariable();
            }
            int i = solverVariable.id;
            if (i == -1 || i > this.f854a || this.k.d[i] == null) {
                if (i != -1) {
                    solverVariable.reset();
                }
                int i2 = this.f854a + 1;
                this.f854a = i2;
                this.h++;
                solverVariable.id = i2;
                solverVariable.l = SolverVariable.Type.UNRESTRICTED;
                this.k.d[i2] = solverVariable;
            }
        }
        return solverVariable;
    }

    public ArrayRow createRow() {
        ArrayRow acquire;
        if (OPTIMIZED_ENGINE) {
            acquire = this.k.f853a.acquire();
            if (acquire == null) {
                acquire = new b(this, this.k);
                OPTIMIZED_ARRAY_ROW_CREATION++;
            } else {
                acquire.reset();
            }
        } else {
            acquire = this.k.b.acquire();
            if (acquire == null) {
                acquire = new ArrayRow(this.k);
                ARRAY_ROW_CREATION++;
            } else {
                acquire.reset();
            }
        }
        SolverVariable.a();
        return acquire;
    }

    public SolverVariable createSlackVariable() {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.slackvariables++;
        }
        if (this.h + 1 >= this.e) {
            g();
        }
        SolverVariable a2 = a(SolverVariable.Type.SLACK, null);
        int i = this.f854a + 1;
        this.f854a = i;
        this.h++;
        a2.id = i;
        this.k.d[i] = a2;
        return a2;
    }

    public final void d() {
        for (int i = 0; i < this.i; i++) {
            ArrayRow arrayRow = this.f[i];
            arrayRow.f852a.computedValue = arrayRow.b;
        }
    }

    public void displayReadableRows() {
        e();
        String str = " num vars " + this.f854a + "\n";
        for (int i = 0; i < this.f854a + 1; i++) {
            SolverVariable solverVariable = this.k.d[i];
            if (solverVariable != null && solverVariable.isFinalValue) {
                str = str + " $[" + i + "] => " + solverVariable + " = " + solverVariable.computedValue + "\n";
            }
        }
        String str2 = str + "\n";
        for (int i2 = 0; i2 < this.f854a + 1; i2++) {
            SolverVariable[] solverVariableArr = this.k.d;
            SolverVariable solverVariable2 = solverVariableArr[i2];
            if (solverVariable2 != null && solverVariable2.o) {
                str2 = str2 + " ~[" + i2 + "] => " + solverVariable2 + " = " + solverVariableArr[solverVariable2.p] + " + " + solverVariable2.q + "\n";
            }
        }
        String str3 = str2 + "\n\n #  ";
        for (int i3 = 0; i3 < this.i; i3++) {
            str3 = (str3 + this.f[i3].n()) + "\n #  ";
        }
        if (this.c != null) {
            str3 = str3 + "Goal: " + this.c + "\n";
        }
        System.out.println(str3);
    }

    public void displayVariablesReadableRows() {
        e();
        String str = "";
        for (int i = 0; i < this.i; i++) {
            if (this.f[i].f852a.l == SolverVariable.Type.UNRESTRICTED) {
                str = (str + this.f[i].n()) + "\n";
            }
        }
        System.out.println(str + this.c + "\n");
    }

    public final void e() {
        System.out.println("Display Rows (" + this.i + "x" + this.h + ")\n");
    }

    public final int f(a aVar) throws Exception {
        boolean z;
        int i = 0;
        while (true) {
            if (i >= this.i) {
                z = false;
                break;
            }
            ArrayRow[] arrayRowArr = this.f;
            if (arrayRowArr[i].f852a.l != SolverVariable.Type.UNRESTRICTED && arrayRowArr[i].b < 0.0f) {
                z = true;
                break;
            }
            i++;
        }
        if (z) {
            boolean z2 = false;
            int i2 = 0;
            while (!z2) {
                Metrics metrics = sMetrics;
                if (metrics != null) {
                    metrics.bfs++;
                }
                i2++;
                float f = Float.MAX_VALUE;
                int i3 = -1;
                int i4 = -1;
                int i5 = 0;
                for (int i6 = 0; i6 < this.i; i6++) {
                    ArrayRow arrayRow = this.f[i6];
                    if (arrayRow.f852a.l != SolverVariable.Type.UNRESTRICTED && !arrayRow.e && arrayRow.b < 0.0f) {
                        int i7 = 9;
                        if (SKIP_COLUMNS) {
                            int currentSize = arrayRow.variables.getCurrentSize();
                            int i8 = 0;
                            while (i8 < currentSize) {
                                SolverVariable variable = arrayRow.variables.getVariable(i8);
                                float f2 = arrayRow.variables.get(variable);
                                if (f2 > 0.0f) {
                                    int i9 = 0;
                                    while (i9 < i7) {
                                        float f3 = variable.j[i9] / f2;
                                        if ((f3 < f && i9 == i5) || i9 > i5) {
                                            i4 = variable.id;
                                            i5 = i9;
                                            i3 = i6;
                                            f = f3;
                                        }
                                        i9++;
                                        i7 = 9;
                                    }
                                }
                                i8++;
                                i7 = 9;
                            }
                        } else {
                            for (int i10 = 1; i10 < this.h; i10++) {
                                SolverVariable solverVariable = this.k.d[i10];
                                float f4 = arrayRow.variables.get(solverVariable);
                                if (f4 > 0.0f) {
                                    for (int i11 = 0; i11 < 9; i11++) {
                                        float f5 = solverVariable.j[i11] / f4;
                                        if ((f5 < f && i11 == i5) || i11 > i5) {
                                            i4 = i10;
                                            i5 = i11;
                                            i3 = i6;
                                            f = f5;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (i3 != -1) {
                    ArrayRow arrayRow2 = this.f[i3];
                    arrayRow2.f852a.i = -1;
                    Metrics metrics2 = sMetrics;
                    if (metrics2 != null) {
                        metrics2.pivots++;
                    }
                    arrayRow2.l(this.k.d[i4]);
                    SolverVariable solverVariable2 = arrayRow2.f852a;
                    solverVariable2.i = i3;
                    solverVariable2.updateReferencesWithNewDefinition(this, arrayRow2);
                } else {
                    z2 = true;
                }
                if (i2 > this.h / 2) {
                    z2 = true;
                }
            }
            return i2;
        }
        return 0;
    }

    public void fillMetrics(Metrics metrics) {
        sMetrics = metrics;
    }

    public final void g() {
        int i = this.d * 2;
        this.d = i;
        this.f = (ArrayRow[]) Arrays.copyOf(this.f, i);
        Cache cache = this.k;
        cache.d = (SolverVariable[]) Arrays.copyOf(cache.d, this.d);
        int i2 = this.d;
        this.g = new boolean[i2];
        this.e = i2;
        this.j = i2;
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.tableSizeIncrease++;
            metrics.maxTableSize = Math.max(metrics.maxTableSize, i2);
            Metrics metrics2 = sMetrics;
            metrics2.lastTableSize = metrics2.maxTableSize;
        }
    }

    public Cache getCache() {
        return this.k;
    }

    public int getMemoryUsed() {
        int i = 0;
        for (int i2 = 0; i2 < this.i; i2++) {
            ArrayRow[] arrayRowArr = this.f;
            if (arrayRowArr[i2] != null) {
                i += arrayRowArr[i2].m();
            }
        }
        return i;
    }

    public int getNumEquations() {
        return this.i;
    }

    public int getNumVariables() {
        return this.f854a;
    }

    public int getObjectVariableValue(Object obj) {
        SolverVariable solverVariable = ((ConstraintAnchor) obj).getSolverVariable();
        if (solverVariable != null) {
            return (int) (solverVariable.computedValue + 0.5f);
        }
        return 0;
    }

    public void h(a aVar) throws Exception {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.minimizeGoal++;
            metrics.maxVariables = Math.max(metrics.maxVariables, this.h);
            Metrics metrics2 = sMetrics;
            metrics2.maxRows = Math.max(metrics2.maxRows, this.i);
        }
        f(aVar);
        i(aVar, false);
        d();
    }

    public final int i(a aVar, boolean z) {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.optimize++;
        }
        for (int i = 0; i < this.h; i++) {
            this.g[i] = false;
        }
        boolean z2 = false;
        int i2 = 0;
        while (!z2) {
            Metrics metrics2 = sMetrics;
            if (metrics2 != null) {
                metrics2.iterations++;
            }
            i2++;
            if (i2 >= this.h * 2) {
                return i2;
            }
            if (aVar.getKey() != null) {
                this.g[aVar.getKey().id] = true;
            }
            SolverVariable pivotCandidate = aVar.getPivotCandidate(this, this.g);
            if (pivotCandidate != null) {
                boolean[] zArr = this.g;
                int i3 = pivotCandidate.id;
                if (zArr[i3]) {
                    return i2;
                }
                zArr[i3] = true;
            }
            if (pivotCandidate != null) {
                float f = Float.MAX_VALUE;
                int i4 = -1;
                for (int i5 = 0; i5 < this.i; i5++) {
                    ArrayRow arrayRow = this.f[i5];
                    if (arrayRow.f852a.l != SolverVariable.Type.UNRESTRICTED && !arrayRow.e && arrayRow.i(pivotCandidate)) {
                        float f2 = arrayRow.variables.get(pivotCandidate);
                        if (f2 < 0.0f) {
                            float f3 = (-arrayRow.b) / f2;
                            if (f3 < f) {
                                i4 = i5;
                                f = f3;
                            }
                        }
                    }
                }
                if (i4 > -1) {
                    ArrayRow arrayRow2 = this.f[i4];
                    arrayRow2.f852a.i = -1;
                    Metrics metrics3 = sMetrics;
                    if (metrics3 != null) {
                        metrics3.pivots++;
                    }
                    arrayRow2.l(pivotCandidate);
                    SolverVariable solverVariable = arrayRow2.f852a;
                    solverVariable.i = i4;
                    solverVariable.updateReferencesWithNewDefinition(this, arrayRow2);
                }
            } else {
                z2 = true;
            }
        }
        return i2;
    }

    public final void j() {
        int i = 0;
        if (OPTIMIZED_ENGINE) {
            while (i < this.i) {
                ArrayRow arrayRow = this.f[i];
                if (arrayRow != null) {
                    this.k.f853a.release(arrayRow);
                }
                this.f[i] = null;
                i++;
            }
            return;
        }
        while (i < this.i) {
            ArrayRow arrayRow2 = this.f[i];
            if (arrayRow2 != null) {
                this.k.b.release(arrayRow2);
            }
            this.f[i] = null;
            i++;
        }
    }

    public void minimize() throws Exception {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.minimize++;
        }
        if (this.c.isEmpty()) {
            d();
        } else if (!this.graphOptimizer && !this.newgraphOptimizer) {
            h(this.c);
        } else {
            Metrics metrics2 = sMetrics;
            if (metrics2 != null) {
                metrics2.graphOptimizer++;
            }
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= this.i) {
                    z = true;
                    break;
                } else if (!this.f[i].e) {
                    break;
                } else {
                    i++;
                }
            }
            if (!z) {
                h(this.c);
                return;
            }
            Metrics metrics3 = sMetrics;
            if (metrics3 != null) {
                metrics3.fullySolved++;
            }
            d();
        }
    }

    public void removeRow(ArrayRow arrayRow) {
        SolverVariable solverVariable;
        int i;
        if (!arrayRow.e || (solverVariable = arrayRow.f852a) == null) {
            return;
        }
        int i2 = solverVariable.i;
        if (i2 != -1) {
            while (true) {
                i = this.i;
                if (i2 >= i - 1) {
                    break;
                }
                ArrayRow[] arrayRowArr = this.f;
                int i3 = i2 + 1;
                SolverVariable solverVariable2 = arrayRowArr[i3].f852a;
                if (solverVariable2.i == i3) {
                    solverVariable2.i = i2;
                }
                arrayRowArr[i2] = arrayRowArr[i3];
                i2 = i3;
            }
            this.i = i - 1;
        }
        SolverVariable solverVariable3 = arrayRow.f852a;
        if (!solverVariable3.isFinalValue) {
            solverVariable3.setFinalValue(this, arrayRow.b);
        }
        if (OPTIMIZED_ENGINE) {
            this.k.f853a.release(arrayRow);
        } else {
            this.k.b.release(arrayRow);
        }
    }

    public void reset() {
        Cache cache;
        int i = 0;
        while (true) {
            cache = this.k;
            SolverVariable[] solverVariableArr = cache.d;
            if (i >= solverVariableArr.length) {
                break;
            }
            SolverVariable solverVariable = solverVariableArr[i];
            if (solverVariable != null) {
                solverVariable.reset();
            }
            i++;
        }
        cache.c.a(this.l, this.m);
        this.m = 0;
        Arrays.fill(this.k.d, (Object) null);
        HashMap<String, SolverVariable> hashMap = this.b;
        if (hashMap != null) {
            hashMap.clear();
        }
        this.f854a = 0;
        this.c.clear();
        this.h = 1;
        for (int i2 = 0; i2 < this.i; i2++) {
            ArrayRow[] arrayRowArr = this.f;
            if (arrayRowArr[i2] != null) {
                arrayRowArr[i2].c = false;
            }
        }
        j();
        this.i = 0;
        if (OPTIMIZED_ENGINE) {
            this.n = new b(this, this.k);
        } else {
            this.n = new ArrayRow(this.k);
        }
    }

    public void addEquality(SolverVariable solverVariable, int i) {
        if (USE_BASIC_SYNONYMS && solverVariable.i == -1) {
            float f = i;
            solverVariable.setFinalValue(this, f);
            for (int i2 = 0; i2 < this.f854a + 1; i2++) {
                SolverVariable solverVariable2 = this.k.d[i2];
                if (solverVariable2 != null && solverVariable2.o && solverVariable2.p == solverVariable.id) {
                    solverVariable2.setFinalValue(this, solverVariable2.q + f);
                }
            }
            return;
        }
        int i3 = solverVariable.i;
        if (i3 != -1) {
            ArrayRow arrayRow = this.f[i3];
            if (arrayRow.e) {
                arrayRow.b = i;
                return;
            } else if (arrayRow.variables.getCurrentSize() == 0) {
                arrayRow.e = true;
                arrayRow.b = i;
                return;
            } else {
                ArrayRow createRow = createRow();
                createRow.createRowEquals(solverVariable, i);
                addConstraint(createRow);
                return;
            }
        }
        ArrayRow createRow2 = createRow();
        createRow2.e(solverVariable, i);
        addConstraint(createRow2);
    }
}
