package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.HashMap;
/* loaded from: classes.dex */
public class Flow extends VirtualLayout {
    public static final int HORIZONTAL_ALIGN_CENTER = 2;
    public static final int HORIZONTAL_ALIGN_END = 1;
    public static final int HORIZONTAL_ALIGN_START = 0;
    public static final int VERTICAL_ALIGN_BASELINE = 3;
    public static final int VERTICAL_ALIGN_BOTTOM = 1;
    public static final int VERTICAL_ALIGN_CENTER = 2;
    public static final int VERTICAL_ALIGN_TOP = 0;
    public static final int WRAP_ALIGNED = 2;
    public static final int WRAP_CHAIN = 1;
    public static final int WRAP_CHAIN_NEW = 3;
    public static final int WRAP_NONE = 0;
    public ConstraintWidget[] G0;
    public int j0 = -1;
    public int k0 = -1;
    public int l0 = -1;
    public int m0 = -1;
    public int n0 = -1;
    public int o0 = -1;
    public float p0 = 0.5f;
    public float q0 = 0.5f;
    public float r0 = 0.5f;
    public float s0 = 0.5f;
    public float t0 = 0.5f;
    public float u0 = 0.5f;
    public int v0 = 0;
    public int w0 = 0;
    public int x0 = 2;
    public int y0 = 2;
    public int z0 = 0;
    public int A0 = -1;
    public int B0 = 0;
    public ArrayList<a> C0 = new ArrayList<>();
    public ConstraintWidget[] D0 = null;
    public ConstraintWidget[] E0 = null;
    public int[] F0 = null;
    public int H0 = 0;

    /* loaded from: classes.dex */
    public class a {

        /* renamed from: a  reason: collision with root package name */
        public int f920a;
        public ConstraintAnchor d;
        public ConstraintAnchor e;
        public ConstraintAnchor f;
        public ConstraintAnchor g;
        public int h;
        public int i;
        public int j;
        public int k;
        public int q;
        public ConstraintWidget b = null;
        public int c = 0;
        public int l = 0;
        public int m = 0;
        public int n = 0;
        public int o = 0;
        public int p = 0;

        public a(int i, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i2) {
            this.f920a = 0;
            this.h = 0;
            this.i = 0;
            this.j = 0;
            this.k = 0;
            this.q = 0;
            this.f920a = i;
            this.d = constraintAnchor;
            this.e = constraintAnchor2;
            this.f = constraintAnchor3;
            this.g = constraintAnchor4;
            this.h = Flow.this.getPaddingLeft();
            this.i = Flow.this.getPaddingTop();
            this.j = Flow.this.getPaddingRight();
            this.k = Flow.this.getPaddingBottom();
            this.q = i2;
        }

        public void b(ConstraintWidget constraintWidget) {
            if (this.f920a == 0) {
                int I = Flow.this.I(constraintWidget, this.q);
                if (constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.p++;
                    I = 0;
                }
                this.l += I + (constraintWidget.getVisibility() != 8 ? Flow.this.v0 : 0);
                int H = Flow.this.H(constraintWidget, this.q);
                if (this.b == null || this.c < H) {
                    this.b = constraintWidget;
                    this.c = H;
                    this.m = H;
                }
            } else {
                int I2 = Flow.this.I(constraintWidget, this.q);
                int H2 = Flow.this.H(constraintWidget, this.q);
                if (constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.p++;
                    H2 = 0;
                }
                this.m += H2 + (constraintWidget.getVisibility() != 8 ? Flow.this.w0 : 0);
                if (this.b == null || this.c < I2) {
                    this.b = constraintWidget;
                    this.c = I2;
                    this.l = I2;
                }
            }
            this.o++;
        }

        public void c() {
            this.c = 0;
            this.b = null;
            this.l = 0;
            this.m = 0;
            this.n = 0;
            this.o = 0;
            this.p = 0;
        }

        public void d(boolean z, int i, boolean z2) {
            ConstraintWidget constraintWidget;
            char c;
            float f;
            float f2;
            int i2 = this.o;
            for (int i3 = 0; i3 < i2 && this.n + i3 < Flow.this.H0; i3++) {
                ConstraintWidget constraintWidget2 = Flow.this.G0[this.n + i3];
                if (constraintWidget2 != null) {
                    constraintWidget2.resetAnchors();
                }
            }
            if (i2 == 0 || this.b == null) {
                return;
            }
            boolean z3 = z2 && i == 0;
            int i4 = -1;
            int i5 = -1;
            for (int i6 = 0; i6 < i2; i6++) {
                int i7 = z ? (i2 - 1) - i6 : i6;
                if (this.n + i7 >= Flow.this.H0) {
                    break;
                }
                ConstraintWidget constraintWidget3 = Flow.this.G0[this.n + i7];
                if (constraintWidget3 != null && constraintWidget3.getVisibility() == 0) {
                    if (i4 == -1) {
                        i4 = i6;
                    }
                    i5 = i6;
                }
            }
            ConstraintWidget constraintWidget4 = null;
            if (this.f920a == 0) {
                ConstraintWidget constraintWidget5 = this.b;
                constraintWidget5.setVerticalChainStyle(Flow.this.k0);
                int i8 = this.i;
                if (i > 0) {
                    i8 += Flow.this.w0;
                }
                constraintWidget5.mTop.connect(this.e, i8);
                if (z2) {
                    constraintWidget5.mBottom.connect(this.g, this.k);
                }
                if (i > 0) {
                    this.e.mOwner.mBottom.connect(constraintWidget5.mTop, 0);
                }
                char c2 = 3;
                if (Flow.this.y0 == 3 && !constraintWidget5.hasBaseline()) {
                    for (int i9 = 0; i9 < i2; i9++) {
                        int i10 = z ? (i2 - 1) - i9 : i9;
                        if (this.n + i10 >= Flow.this.H0) {
                            break;
                        }
                        constraintWidget = Flow.this.G0[this.n + i10];
                        if (constraintWidget.hasBaseline()) {
                            break;
                        }
                    }
                }
                constraintWidget = constraintWidget5;
                int i11 = 0;
                while (i11 < i2) {
                    int i12 = z ? (i2 - 1) - i11 : i11;
                    if (this.n + i12 >= Flow.this.H0) {
                        return;
                    }
                    ConstraintWidget constraintWidget6 = Flow.this.G0[this.n + i12];
                    if (constraintWidget6 == null) {
                        constraintWidget6 = constraintWidget4;
                        c = c2;
                    } else {
                        if (i11 == 0) {
                            constraintWidget6.connect(constraintWidget6.mLeft, this.d, this.h);
                        }
                        if (i12 == 0) {
                            int i13 = Flow.this.j0;
                            float f3 = Flow.this.p0;
                            if (z) {
                                f3 = 1.0f - f3;
                            }
                            if (this.n != 0 || Flow.this.l0 == -1) {
                                if (z2 && Flow.this.n0 != -1) {
                                    i13 = Flow.this.n0;
                                    if (z) {
                                        f2 = Flow.this.t0;
                                        f = 1.0f - f2;
                                        f3 = f;
                                    } else {
                                        f = Flow.this.t0;
                                        f3 = f;
                                    }
                                }
                            } else {
                                i13 = Flow.this.l0;
                                if (z) {
                                    f2 = Flow.this.r0;
                                    f = 1.0f - f2;
                                    f3 = f;
                                } else {
                                    f = Flow.this.r0;
                                    f3 = f;
                                }
                            }
                            constraintWidget6.setHorizontalChainStyle(i13);
                            constraintWidget6.setHorizontalBiasPercent(f3);
                        }
                        if (i11 == i2 - 1) {
                            constraintWidget6.connect(constraintWidget6.mRight, this.f, this.j);
                        }
                        if (constraintWidget4 != null) {
                            constraintWidget6.mLeft.connect(constraintWidget4.mRight, Flow.this.v0);
                            if (i11 == i4) {
                                constraintWidget6.mLeft.setGoneMargin(this.h);
                            }
                            constraintWidget4.mRight.connect(constraintWidget6.mLeft, 0);
                            if (i11 == i5 + 1) {
                                constraintWidget4.mRight.setGoneMargin(this.j);
                            }
                        }
                        if (constraintWidget6 != constraintWidget5) {
                            c = 3;
                            if (Flow.this.y0 != 3 || !constraintWidget.hasBaseline() || constraintWidget6 == constraintWidget || !constraintWidget6.hasBaseline()) {
                                int i14 = Flow.this.y0;
                                if (i14 == 0) {
                                    constraintWidget6.mTop.connect(constraintWidget5.mTop, 0);
                                } else if (i14 == 1) {
                                    constraintWidget6.mBottom.connect(constraintWidget5.mBottom, 0);
                                } else if (z3) {
                                    constraintWidget6.mTop.connect(this.e, this.i);
                                    constraintWidget6.mBottom.connect(this.g, this.k);
                                } else {
                                    constraintWidget6.mTop.connect(constraintWidget5.mTop, 0);
                                    constraintWidget6.mBottom.connect(constraintWidget5.mBottom, 0);
                                }
                            } else {
                                constraintWidget6.mBaseline.connect(constraintWidget.mBaseline, 0);
                            }
                        } else {
                            c = 3;
                        }
                    }
                    i11++;
                    c2 = c;
                    constraintWidget4 = constraintWidget6;
                }
                return;
            }
            ConstraintWidget constraintWidget7 = this.b;
            constraintWidget7.setHorizontalChainStyle(Flow.this.j0);
            int i15 = this.h;
            if (i > 0) {
                i15 += Flow.this.v0;
            }
            if (z) {
                constraintWidget7.mRight.connect(this.f, i15);
                if (z2) {
                    constraintWidget7.mLeft.connect(this.d, this.j);
                }
                if (i > 0) {
                    this.f.mOwner.mLeft.connect(constraintWidget7.mRight, 0);
                }
            } else {
                constraintWidget7.mLeft.connect(this.d, i15);
                if (z2) {
                    constraintWidget7.mRight.connect(this.f, this.j);
                }
                if (i > 0) {
                    this.d.mOwner.mRight.connect(constraintWidget7.mLeft, 0);
                }
            }
            for (int i16 = 0; i16 < i2 && this.n + i16 < Flow.this.H0; i16++) {
                ConstraintWidget constraintWidget8 = Flow.this.G0[this.n + i16];
                if (constraintWidget8 != null) {
                    if (i16 == 0) {
                        constraintWidget8.connect(constraintWidget8.mTop, this.e, this.i);
                        int i17 = Flow.this.k0;
                        float f4 = Flow.this.q0;
                        if (this.n != 0 || Flow.this.m0 == -1) {
                            if (z2 && Flow.this.o0 != -1) {
                                i17 = Flow.this.o0;
                                f4 = Flow.this.u0;
                            }
                        } else {
                            i17 = Flow.this.m0;
                            f4 = Flow.this.s0;
                        }
                        constraintWidget8.setVerticalChainStyle(i17);
                        constraintWidget8.setVerticalBiasPercent(f4);
                    }
                    if (i16 == i2 - 1) {
                        constraintWidget8.connect(constraintWidget8.mBottom, this.g, this.k);
                    }
                    if (constraintWidget4 != null) {
                        constraintWidget8.mTop.connect(constraintWidget4.mBottom, Flow.this.w0);
                        if (i16 == i4) {
                            constraintWidget8.mTop.setGoneMargin(this.i);
                        }
                        constraintWidget4.mBottom.connect(constraintWidget8.mTop, 0);
                        if (i16 == i5 + 1) {
                            constraintWidget4.mBottom.setGoneMargin(this.k);
                        }
                    }
                    if (constraintWidget8 != constraintWidget7) {
                        if (z) {
                            int i18 = Flow.this.x0;
                            if (i18 == 0) {
                                constraintWidget8.mRight.connect(constraintWidget7.mRight, 0);
                            } else if (i18 == 1) {
                                constraintWidget8.mLeft.connect(constraintWidget7.mLeft, 0);
                            } else if (i18 == 2) {
                                constraintWidget8.mLeft.connect(constraintWidget7.mLeft, 0);
                                constraintWidget8.mRight.connect(constraintWidget7.mRight, 0);
                            }
                        } else {
                            int i19 = Flow.this.x0;
                            if (i19 == 0) {
                                constraintWidget8.mLeft.connect(constraintWidget7.mLeft, 0);
                            } else if (i19 == 1) {
                                constraintWidget8.mRight.connect(constraintWidget7.mRight, 0);
                            } else if (i19 == 2) {
                                if (z3) {
                                    constraintWidget8.mLeft.connect(this.d, this.h);
                                    constraintWidget8.mRight.connect(this.f, this.j);
                                } else {
                                    constraintWidget8.mLeft.connect(constraintWidget7.mLeft, 0);
                                    constraintWidget8.mRight.connect(constraintWidget7.mRight, 0);
                                }
                            }
                            constraintWidget4 = constraintWidget8;
                        }
                    }
                    constraintWidget4 = constraintWidget8;
                }
            }
        }

        public int e() {
            if (this.f920a == 1) {
                return this.m - Flow.this.w0;
            }
            return this.m;
        }

        public int f() {
            if (this.f920a == 0) {
                return this.l - Flow.this.v0;
            }
            return this.l;
        }

        public void g(int i) {
            int i2 = this.p;
            if (i2 == 0) {
                return;
            }
            int i3 = this.o;
            int i4 = i / i2;
            for (int i5 = 0; i5 < i3 && this.n + i5 < Flow.this.H0; i5++) {
                ConstraintWidget constraintWidget = Flow.this.G0[this.n + i5];
                if (this.f920a == 0) {
                    if (constraintWidget != null && constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mMatchConstraintDefaultWidth == 0) {
                        Flow.this.measure(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, i4, constraintWidget.getVerticalDimensionBehaviour(), constraintWidget.getHeight());
                    }
                } else if (constraintWidget != null && constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mMatchConstraintDefaultHeight == 0) {
                    Flow.this.measure(constraintWidget, constraintWidget.getHorizontalDimensionBehaviour(), constraintWidget.getWidth(), ConstraintWidget.DimensionBehaviour.FIXED, i4);
                }
            }
            h();
        }

        public final void h() {
            this.l = 0;
            this.m = 0;
            this.b = null;
            this.c = 0;
            int i = this.o;
            for (int i2 = 0; i2 < i && this.n + i2 < Flow.this.H0; i2++) {
                ConstraintWidget constraintWidget = Flow.this.G0[this.n + i2];
                if (this.f920a != 0) {
                    int I = Flow.this.I(constraintWidget, this.q);
                    int H = Flow.this.H(constraintWidget, this.q);
                    int i3 = Flow.this.w0;
                    if (constraintWidget.getVisibility() == 8) {
                        i3 = 0;
                    }
                    this.m += H + i3;
                    if (this.b == null || this.c < I) {
                        this.b = constraintWidget;
                        this.c = I;
                        this.l = I;
                    }
                } else {
                    int width = constraintWidget.getWidth();
                    int i4 = Flow.this.v0;
                    if (constraintWidget.getVisibility() == 8) {
                        i4 = 0;
                    }
                    this.l += width + i4;
                    int H2 = Flow.this.H(constraintWidget, this.q);
                    if (this.b == null || this.c < H2) {
                        this.b = constraintWidget;
                        this.c = H2;
                        this.m = H2;
                    }
                }
            }
        }

        public void i(int i) {
            this.n = i;
        }

        public void j(int i, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i2, int i3, int i4, int i5, int i6) {
            this.f920a = i;
            this.d = constraintAnchor;
            this.e = constraintAnchor2;
            this.f = constraintAnchor3;
            this.g = constraintAnchor4;
            this.h = i2;
            this.i = i3;
            this.j = i4;
            this.k = i5;
            this.q = i6;
        }
    }

    public final void G(boolean z) {
        ConstraintWidget constraintWidget;
        float f;
        int i;
        if (this.F0 == null || this.E0 == null || this.D0 == null) {
            return;
        }
        for (int i2 = 0; i2 < this.H0; i2++) {
            this.G0[i2].resetAnchors();
        }
        int[] iArr = this.F0;
        int i3 = iArr[0];
        int i4 = iArr[1];
        ConstraintWidget constraintWidget2 = null;
        float f2 = this.p0;
        int i5 = 0;
        while (i5 < i3) {
            if (z) {
                i = (i3 - i5) - 1;
                f = 1.0f - this.p0;
            } else {
                f = f2;
                i = i5;
            }
            ConstraintWidget constraintWidget3 = this.E0[i];
            if (constraintWidget3 != null && constraintWidget3.getVisibility() != 8) {
                if (i5 == 0) {
                    constraintWidget3.connect(constraintWidget3.mLeft, this.mLeft, getPaddingLeft());
                    constraintWidget3.setHorizontalChainStyle(this.j0);
                    constraintWidget3.setHorizontalBiasPercent(f);
                }
                if (i5 == i3 - 1) {
                    constraintWidget3.connect(constraintWidget3.mRight, this.mRight, getPaddingRight());
                }
                if (i5 > 0 && constraintWidget2 != null) {
                    constraintWidget3.connect(constraintWidget3.mLeft, constraintWidget2.mRight, this.v0);
                    constraintWidget2.connect(constraintWidget2.mRight, constraintWidget3.mLeft, 0);
                }
                constraintWidget2 = constraintWidget3;
            }
            i5++;
            f2 = f;
        }
        for (int i6 = 0; i6 < i4; i6++) {
            ConstraintWidget constraintWidget4 = this.D0[i6];
            if (constraintWidget4 != null && constraintWidget4.getVisibility() != 8) {
                if (i6 == 0) {
                    constraintWidget4.connect(constraintWidget4.mTop, this.mTop, getPaddingTop());
                    constraintWidget4.setVerticalChainStyle(this.k0);
                    constraintWidget4.setVerticalBiasPercent(this.q0);
                }
                if (i6 == i4 - 1) {
                    constraintWidget4.connect(constraintWidget4.mBottom, this.mBottom, getPaddingBottom());
                }
                if (i6 > 0 && constraintWidget2 != null) {
                    constraintWidget4.connect(constraintWidget4.mTop, constraintWidget2.mBottom, this.w0);
                    constraintWidget2.connect(constraintWidget2.mBottom, constraintWidget4.mTop, 0);
                }
                constraintWidget2 = constraintWidget4;
            }
        }
        for (int i7 = 0; i7 < i3; i7++) {
            for (int i8 = 0; i8 < i4; i8++) {
                int i9 = (i8 * i3) + i7;
                if (this.B0 == 1) {
                    i9 = (i7 * i4) + i8;
                }
                ConstraintWidget[] constraintWidgetArr = this.G0;
                if (i9 < constraintWidgetArr.length && (constraintWidget = constraintWidgetArr[i9]) != null && constraintWidget.getVisibility() != 8) {
                    ConstraintWidget constraintWidget5 = this.E0[i7];
                    ConstraintWidget constraintWidget6 = this.D0[i8];
                    if (constraintWidget != constraintWidget5) {
                        constraintWidget.connect(constraintWidget.mLeft, constraintWidget5.mLeft, 0);
                        constraintWidget.connect(constraintWidget.mRight, constraintWidget5.mRight, 0);
                    }
                    if (constraintWidget != constraintWidget6) {
                        constraintWidget.connect(constraintWidget.mTop, constraintWidget6.mTop, 0);
                        constraintWidget.connect(constraintWidget.mBottom, constraintWidget6.mBottom, 0);
                    }
                }
            }
        }
    }

    public final int H(ConstraintWidget constraintWidget, int i) {
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int i2 = constraintWidget.mMatchConstraintDefaultHeight;
            if (i2 == 0) {
                return 0;
            }
            if (i2 == 2) {
                int i3 = (int) (constraintWidget.mMatchConstraintPercentHeight * i);
                if (i3 != constraintWidget.getHeight()) {
                    constraintWidget.setMeasureRequested(true);
                    measure(constraintWidget, constraintWidget.getHorizontalDimensionBehaviour(), constraintWidget.getWidth(), ConstraintWidget.DimensionBehaviour.FIXED, i3);
                }
                return i3;
            } else if (i2 == 1) {
                return constraintWidget.getHeight();
            } else {
                if (i2 == 3) {
                    return (int) ((constraintWidget.getWidth() * constraintWidget.mDimensionRatio) + 0.5f);
                }
            }
        }
        return constraintWidget.getHeight();
    }

    public final int I(ConstraintWidget constraintWidget, int i) {
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int i2 = constraintWidget.mMatchConstraintDefaultWidth;
            if (i2 == 0) {
                return 0;
            }
            if (i2 == 2) {
                int i3 = (int) (constraintWidget.mMatchConstraintPercentWidth * i);
                if (i3 != constraintWidget.getWidth()) {
                    constraintWidget.setMeasureRequested(true);
                    measure(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, i3, constraintWidget.getVerticalDimensionBehaviour(), constraintWidget.getHeight());
                }
                return i3;
            } else if (i2 == 1) {
                return constraintWidget.getWidth();
            } else {
                if (i2 == 3) {
                    return (int) ((constraintWidget.getHeight() * constraintWidget.mDimensionRatio) + 0.5f);
                }
            }
        }
        return constraintWidget.getWidth();
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0068  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:105:0x011b -> B:42:0x0063). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:106:0x011d -> B:42:0x0063). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:108:0x0123 -> B:42:0x0063). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:109:0x0125 -> B:42:0x0063). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void J(androidx.constraintlayout.core.widgets.ConstraintWidget[] r17, int r18, int r19, int r20, int[] r21) {
        /*
            Method dump skipped, instructions count: 306
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.Flow.J(androidx.constraintlayout.core.widgets.ConstraintWidget[], int, int, int, int[]):void");
    }

    public final void K(ConstraintWidget[] constraintWidgetArr, int i, int i2, int i3, int[] iArr) {
        int i4;
        int i5;
        int i6;
        ConstraintAnchor constraintAnchor;
        int paddingRight;
        ConstraintAnchor constraintAnchor2;
        int paddingBottom;
        int i7;
        if (i == 0) {
            return;
        }
        this.C0.clear();
        a aVar = new a(i2, this.mLeft, this.mTop, this.mRight, this.mBottom, i3);
        this.C0.add(aVar);
        if (i2 == 0) {
            i4 = 0;
            int i8 = 0;
            int i9 = 0;
            while (i9 < i) {
                ConstraintWidget constraintWidget = constraintWidgetArr[i9];
                int I = I(constraintWidget, i3);
                if (constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i4++;
                }
                int i10 = i4;
                boolean z = (i8 == i3 || (this.v0 + i8) + I > i3) && aVar.b != null;
                if (!z && i9 > 0 && (i7 = this.A0) > 0 && i9 % i7 == 0) {
                    z = true;
                }
                if (z) {
                    aVar = new a(i2, this.mLeft, this.mTop, this.mRight, this.mBottom, i3);
                    aVar.i(i9);
                    this.C0.add(aVar);
                } else if (i9 > 0) {
                    i8 += this.v0 + I;
                    aVar.b(constraintWidget);
                    i9++;
                    i4 = i10;
                }
                i8 = I;
                aVar.b(constraintWidget);
                i9++;
                i4 = i10;
            }
        } else {
            i4 = 0;
            int i11 = 0;
            int i12 = 0;
            while (i12 < i) {
                ConstraintWidget constraintWidget2 = constraintWidgetArr[i12];
                int H = H(constraintWidget2, i3);
                if (constraintWidget2.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i4++;
                }
                int i13 = i4;
                boolean z2 = (i11 == i3 || (this.w0 + i11) + H > i3) && aVar.b != null;
                if (!z2 && i12 > 0 && (i5 = this.A0) > 0 && i12 % i5 == 0) {
                    z2 = true;
                }
                if (z2) {
                    aVar = new a(i2, this.mLeft, this.mTop, this.mRight, this.mBottom, i3);
                    aVar.i(i12);
                    this.C0.add(aVar);
                } else if (i12 > 0) {
                    i11 += this.w0 + H;
                    aVar.b(constraintWidget2);
                    i12++;
                    i4 = i13;
                }
                i11 = H;
                aVar.b(constraintWidget2);
                i12++;
                i4 = i13;
            }
        }
        int size = this.C0.size();
        ConstraintAnchor constraintAnchor3 = this.mLeft;
        ConstraintAnchor constraintAnchor4 = this.mTop;
        ConstraintAnchor constraintAnchor5 = this.mRight;
        ConstraintAnchor constraintAnchor6 = this.mBottom;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight2 = getPaddingRight();
        int paddingBottom2 = getPaddingBottom();
        ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        boolean z3 = horizontalDimensionBehaviour == dimensionBehaviour || getVerticalDimensionBehaviour() == dimensionBehaviour;
        if (i4 > 0 && z3) {
            for (int i14 = 0; i14 < size; i14++) {
                a aVar2 = this.C0.get(i14);
                if (i2 == 0) {
                    aVar2.g(i3 - aVar2.f());
                } else {
                    aVar2.g(i3 - aVar2.e());
                }
            }
        }
        int i15 = paddingTop;
        int i16 = paddingRight2;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = paddingLeft;
        ConstraintAnchor constraintAnchor7 = constraintAnchor4;
        ConstraintAnchor constraintAnchor8 = constraintAnchor3;
        int i21 = paddingBottom2;
        while (i19 < size) {
            a aVar3 = this.C0.get(i19);
            if (i2 == 0) {
                if (i19 < size - 1) {
                    constraintAnchor2 = this.C0.get(i19 + 1).b.mTop;
                    paddingBottom = 0;
                } else {
                    constraintAnchor2 = this.mBottom;
                    paddingBottom = getPaddingBottom();
                }
                ConstraintAnchor constraintAnchor9 = aVar3.b.mBottom;
                ConstraintAnchor constraintAnchor10 = constraintAnchor8;
                ConstraintAnchor constraintAnchor11 = constraintAnchor8;
                int i22 = i17;
                ConstraintAnchor constraintAnchor12 = constraintAnchor7;
                int i23 = i18;
                ConstraintAnchor constraintAnchor13 = constraintAnchor5;
                ConstraintAnchor constraintAnchor14 = constraintAnchor5;
                i6 = i19;
                aVar3.j(i2, constraintAnchor10, constraintAnchor12, constraintAnchor13, constraintAnchor2, i20, i15, i16, paddingBottom, i3);
                int max = Math.max(i23, aVar3.f());
                i17 = i22 + aVar3.e();
                if (i6 > 0) {
                    i17 += this.w0;
                }
                constraintAnchor8 = constraintAnchor11;
                i18 = max;
                i15 = 0;
                constraintAnchor7 = constraintAnchor9;
                constraintAnchor = constraintAnchor14;
                int i24 = paddingBottom;
                constraintAnchor6 = constraintAnchor2;
                i21 = i24;
            } else {
                ConstraintAnchor constraintAnchor15 = constraintAnchor8;
                int i25 = i17;
                int i26 = i18;
                i6 = i19;
                if (i6 < size - 1) {
                    constraintAnchor = this.C0.get(i6 + 1).b.mLeft;
                    paddingRight = 0;
                } else {
                    constraintAnchor = this.mRight;
                    paddingRight = getPaddingRight();
                }
                ConstraintAnchor constraintAnchor16 = aVar3.b.mRight;
                aVar3.j(i2, constraintAnchor15, constraintAnchor7, constraintAnchor, constraintAnchor6, i20, i15, paddingRight, i21, i3);
                i18 = i26 + aVar3.f();
                int max2 = Math.max(i25, aVar3.e());
                if (i6 > 0) {
                    i18 += this.v0;
                }
                i17 = max2;
                i20 = 0;
                i16 = paddingRight;
                constraintAnchor8 = constraintAnchor16;
            }
            i19 = i6 + 1;
            constraintAnchor5 = constraintAnchor;
        }
        iArr[0] = i18;
        iArr[1] = i17;
    }

    public final void L(ConstraintWidget[] constraintWidgetArr, int i, int i2, int i3, int[] iArr) {
        int i4;
        int i5;
        int i6;
        ConstraintAnchor constraintAnchor;
        int paddingRight;
        ConstraintAnchor constraintAnchor2;
        int paddingBottom;
        int i7;
        if (i == 0) {
            return;
        }
        this.C0.clear();
        a aVar = new a(i2, this.mLeft, this.mTop, this.mRight, this.mBottom, i3);
        this.C0.add(aVar);
        if (i2 == 0) {
            int i8 = 0;
            i4 = 0;
            int i9 = 0;
            int i10 = 0;
            while (i10 < i) {
                int i11 = i8 + 1;
                ConstraintWidget constraintWidget = constraintWidgetArr[i10];
                int I = I(constraintWidget, i3);
                if (constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i4++;
                }
                int i12 = i4;
                boolean z = (i9 == i3 || (this.v0 + i9) + I > i3) && aVar.b != null;
                if (!z && i10 > 0 && (i7 = this.A0) > 0 && i11 > i7) {
                    z = true;
                }
                if (z) {
                    aVar = new a(i2, this.mLeft, this.mTop, this.mRight, this.mBottom, i3);
                    aVar.i(i10);
                    this.C0.add(aVar);
                    i8 = i11;
                    i9 = I;
                } else {
                    i9 = i10 > 0 ? i9 + this.v0 + I : I;
                    i8 = 0;
                }
                aVar.b(constraintWidget);
                i10++;
                i4 = i12;
            }
        } else {
            int i13 = 0;
            i4 = 0;
            int i14 = 0;
            while (i14 < i) {
                ConstraintWidget constraintWidget2 = constraintWidgetArr[i14];
                int H = H(constraintWidget2, i3);
                if (constraintWidget2.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i4++;
                }
                int i15 = i4;
                boolean z2 = (i13 == i3 || (this.w0 + i13) + H > i3) && aVar.b != null;
                if (!z2 && i14 > 0 && (i5 = this.A0) > 0 && i5 < 0) {
                    z2 = true;
                }
                if (z2) {
                    aVar = new a(i2, this.mLeft, this.mTop, this.mRight, this.mBottom, i3);
                    aVar.i(i14);
                    this.C0.add(aVar);
                } else if (i14 > 0) {
                    i13 += this.w0 + H;
                    aVar.b(constraintWidget2);
                    i14++;
                    i4 = i15;
                }
                i13 = H;
                aVar.b(constraintWidget2);
                i14++;
                i4 = i15;
            }
        }
        int size = this.C0.size();
        ConstraintAnchor constraintAnchor3 = this.mLeft;
        ConstraintAnchor constraintAnchor4 = this.mTop;
        ConstraintAnchor constraintAnchor5 = this.mRight;
        ConstraintAnchor constraintAnchor6 = this.mBottom;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight2 = getPaddingRight();
        int paddingBottom2 = getPaddingBottom();
        ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        boolean z3 = horizontalDimensionBehaviour == dimensionBehaviour || getVerticalDimensionBehaviour() == dimensionBehaviour;
        if (i4 > 0 && z3) {
            for (int i16 = 0; i16 < size; i16++) {
                a aVar2 = this.C0.get(i16);
                if (i2 == 0) {
                    aVar2.g(i3 - aVar2.f());
                } else {
                    aVar2.g(i3 - aVar2.e());
                }
            }
        }
        int i17 = paddingTop;
        int i18 = paddingRight2;
        int i19 = 0;
        int i20 = 0;
        int i21 = 0;
        int i22 = paddingLeft;
        ConstraintAnchor constraintAnchor7 = constraintAnchor4;
        ConstraintAnchor constraintAnchor8 = constraintAnchor3;
        int i23 = paddingBottom2;
        while (i21 < size) {
            a aVar3 = this.C0.get(i21);
            if (i2 == 0) {
                if (i21 < size - 1) {
                    constraintAnchor2 = this.C0.get(i21 + 1).b.mTop;
                    paddingBottom = 0;
                } else {
                    constraintAnchor2 = this.mBottom;
                    paddingBottom = getPaddingBottom();
                }
                ConstraintAnchor constraintAnchor9 = aVar3.b.mBottom;
                ConstraintAnchor constraintAnchor10 = constraintAnchor8;
                ConstraintAnchor constraintAnchor11 = constraintAnchor8;
                int i24 = i19;
                ConstraintAnchor constraintAnchor12 = constraintAnchor7;
                int i25 = i20;
                ConstraintAnchor constraintAnchor13 = constraintAnchor5;
                ConstraintAnchor constraintAnchor14 = constraintAnchor5;
                i6 = i21;
                aVar3.j(i2, constraintAnchor10, constraintAnchor12, constraintAnchor13, constraintAnchor2, i22, i17, i18, paddingBottom, i3);
                int max = Math.max(i25, aVar3.f());
                i19 = i24 + aVar3.e();
                if (i6 > 0) {
                    i19 += this.w0;
                }
                constraintAnchor8 = constraintAnchor11;
                i20 = max;
                i17 = 0;
                constraintAnchor7 = constraintAnchor9;
                constraintAnchor = constraintAnchor14;
                int i26 = paddingBottom;
                constraintAnchor6 = constraintAnchor2;
                i23 = i26;
            } else {
                ConstraintAnchor constraintAnchor15 = constraintAnchor8;
                int i27 = i19;
                int i28 = i20;
                i6 = i21;
                if (i6 < size - 1) {
                    constraintAnchor = this.C0.get(i6 + 1).b.mLeft;
                    paddingRight = 0;
                } else {
                    constraintAnchor = this.mRight;
                    paddingRight = getPaddingRight();
                }
                ConstraintAnchor constraintAnchor16 = aVar3.b.mRight;
                aVar3.j(i2, constraintAnchor15, constraintAnchor7, constraintAnchor, constraintAnchor6, i22, i17, paddingRight, i23, i3);
                i20 = i28 + aVar3.f();
                int max2 = Math.max(i27, aVar3.e());
                if (i6 > 0) {
                    i20 += this.v0;
                }
                i19 = max2;
                i22 = 0;
                i18 = paddingRight;
                constraintAnchor8 = constraintAnchor16;
            }
            i21 = i6 + 1;
            constraintAnchor5 = constraintAnchor;
        }
        iArr[0] = i20;
        iArr[1] = i19;
    }

    public final void M(ConstraintWidget[] constraintWidgetArr, int i, int i2, int i3, int[] iArr) {
        a aVar;
        if (i == 0) {
            return;
        }
        if (this.C0.size() == 0) {
            aVar = new a(i2, this.mLeft, this.mTop, this.mRight, this.mBottom, i3);
            this.C0.add(aVar);
        } else {
            a aVar2 = this.C0.get(0);
            aVar2.c();
            aVar = aVar2;
            aVar.j(i2, this.mLeft, this.mTop, this.mRight, this.mBottom, getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom(), i3);
        }
        for (int i4 = 0; i4 < i; i4++) {
            aVar.b(constraintWidgetArr[i4]);
        }
        iArr[0] = aVar.f();
        iArr[1] = aVar.e();
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void addToSolver(LinearSystem linearSystem, boolean z) {
        super.addToSolver(linearSystem, z);
        boolean z2 = getParent() != null && ((ConstraintWidgetContainer) getParent()).isRtl();
        int i = this.z0;
        if (i != 0) {
            if (i == 1) {
                int size = this.C0.size();
                int i2 = 0;
                while (i2 < size) {
                    this.C0.get(i2).d(z2, i2, i2 == size + (-1));
                    i2++;
                }
            } else if (i == 2) {
                G(z2);
            } else if (i == 3) {
                int size2 = this.C0.size();
                int i3 = 0;
                while (i3 < size2) {
                    this.C0.get(i3).d(z2, i3, i3 == size2 + (-1));
                    i3++;
                }
            }
        } else if (this.C0.size() > 0) {
            this.C0.get(0).d(z2, 0, true);
        }
        needsCallbackFromSolver(false);
    }

    @Override // androidx.constraintlayout.core.widgets.HelperWidget, androidx.constraintlayout.core.widgets.ConstraintWidget
    public void copy(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> hashMap) {
        super.copy(constraintWidget, hashMap);
        Flow flow = (Flow) constraintWidget;
        this.j0 = flow.j0;
        this.k0 = flow.k0;
        this.l0 = flow.l0;
        this.m0 = flow.m0;
        this.n0 = flow.n0;
        this.o0 = flow.o0;
        this.p0 = flow.p0;
        this.q0 = flow.q0;
        this.r0 = flow.r0;
        this.s0 = flow.s0;
        this.t0 = flow.t0;
        this.u0 = flow.u0;
        this.v0 = flow.v0;
        this.w0 = flow.w0;
        this.x0 = flow.x0;
        this.y0 = flow.y0;
        this.z0 = flow.z0;
        this.A0 = flow.A0;
        this.B0 = flow.B0;
    }

    public float getMaxElementsWrap() {
        return this.A0;
    }

    @Override // androidx.constraintlayout.core.widgets.VirtualLayout
    public void measure(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int[] iArr;
        boolean z;
        if (this.mWidgetsCount > 0 && !measureChildren()) {
            setMeasure(0, 0);
            needsCallbackFromSolver(false);
            return;
        }
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int[] iArr2 = new int[2];
        int i7 = (i2 - paddingLeft) - paddingRight;
        int i8 = this.B0;
        if (i8 == 1) {
            i7 = (i4 - paddingTop) - paddingBottom;
        }
        int i9 = i7;
        if (i8 == 0) {
            if (this.j0 == -1) {
                this.j0 = 0;
            }
            if (this.k0 == -1) {
                this.k0 = 0;
            }
        } else {
            if (this.j0 == -1) {
                this.j0 = 0;
            }
            if (this.k0 == -1) {
                this.k0 = 0;
            }
        }
        ConstraintWidget[] constraintWidgetArr = this.mWidgets;
        int i10 = 0;
        int i11 = 0;
        while (true) {
            i5 = this.mWidgetsCount;
            if (i10 >= i5) {
                break;
            }
            if (this.mWidgets[i10].getVisibility() == 8) {
                i11++;
            }
            i10++;
        }
        if (i11 > 0) {
            constraintWidgetArr = new ConstraintWidget[i5 - i11];
            int i12 = 0;
            for (int i13 = 0; i13 < this.mWidgetsCount; i13++) {
                ConstraintWidget constraintWidget = this.mWidgets[i13];
                if (constraintWidget.getVisibility() != 8) {
                    constraintWidgetArr[i12] = constraintWidget;
                    i12++;
                }
            }
            i6 = i12;
        } else {
            i6 = i5;
        }
        this.G0 = constraintWidgetArr;
        this.H0 = i6;
        int i14 = this.z0;
        if (i14 == 0) {
            iArr = iArr2;
            z = true;
            M(constraintWidgetArr, i6, this.B0, i9, iArr2);
        } else if (i14 == 1) {
            z = true;
            iArr = iArr2;
            K(constraintWidgetArr, i6, this.B0, i9, iArr2);
        } else if (i14 == 2) {
            z = true;
            iArr = iArr2;
            J(constraintWidgetArr, i6, this.B0, i9, iArr2);
        } else if (i14 != 3) {
            z = true;
            iArr = iArr2;
        } else {
            z = true;
            iArr = iArr2;
            L(constraintWidgetArr, i6, this.B0, i9, iArr2);
        }
        int i15 = iArr[0] + paddingLeft + paddingRight;
        int i16 = iArr[z ? 1 : 0] + paddingTop + paddingBottom;
        if (i == 1073741824) {
            i15 = i2;
        } else if (i == Integer.MIN_VALUE) {
            i15 = Math.min(i15, i2);
        } else if (i != 0) {
            i15 = 0;
        }
        if (i3 == 1073741824) {
            i16 = i4;
        } else if (i3 == Integer.MIN_VALUE) {
            i16 = Math.min(i16, i4);
        } else if (i3 != 0) {
            i16 = 0;
        }
        setMeasure(i15, i16);
        setWidth(i15);
        setHeight(i16);
        if (this.mWidgetsCount <= 0) {
            z = false;
        }
        needsCallbackFromSolver(z);
    }

    public void setFirstHorizontalBias(float f) {
        this.r0 = f;
    }

    public void setFirstHorizontalStyle(int i) {
        this.l0 = i;
    }

    public void setFirstVerticalBias(float f) {
        this.s0 = f;
    }

    public void setFirstVerticalStyle(int i) {
        this.m0 = i;
    }

    public void setHorizontalAlign(int i) {
        this.x0 = i;
    }

    public void setHorizontalBias(float f) {
        this.p0 = f;
    }

    public void setHorizontalGap(int i) {
        this.v0 = i;
    }

    public void setHorizontalStyle(int i) {
        this.j0 = i;
    }

    public void setLastHorizontalBias(float f) {
        this.t0 = f;
    }

    public void setLastHorizontalStyle(int i) {
        this.n0 = i;
    }

    public void setLastVerticalBias(float f) {
        this.u0 = f;
    }

    public void setLastVerticalStyle(int i) {
        this.o0 = i;
    }

    public void setMaxElementsWrap(int i) {
        this.A0 = i;
    }

    public void setOrientation(int i) {
        this.B0 = i;
    }

    public void setVerticalAlign(int i) {
        this.y0 = i;
    }

    public void setVerticalBias(float f) {
        this.q0 = f;
    }

    public void setVerticalGap(int i) {
        this.w0 = i;
    }

    public void setVerticalStyle(int i) {
        this.k0 = i;
    }

    public void setWrapMode(int i) {
        this.z0 = i;
    }
}
