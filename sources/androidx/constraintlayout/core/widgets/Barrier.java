package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.HashMap;
/* loaded from: classes.dex */
public class Barrier extends HelperWidget {
    public static final int BOTTOM = 3;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int TOP = 2;
    public int Z = 0;
    public boolean a0 = true;
    public int b0 = 0;
    public boolean c0 = false;

    public Barrier() {
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void addToSolver(LinearSystem linearSystem, boolean z) {
        Object[] objArr;
        boolean z2;
        int i;
        int i2;
        int i3;
        ConstraintAnchor[] constraintAnchorArr = this.mListAnchors;
        constraintAnchorArr[0] = this.mLeft;
        constraintAnchorArr[2] = this.mTop;
        constraintAnchorArr[1] = this.mRight;
        constraintAnchorArr[3] = this.mBottom;
        int i4 = 0;
        while (true) {
            objArr = this.mListAnchors;
            if (i4 >= objArr.length) {
                break;
            }
            objArr[i4].e = linearSystem.createObjectVariable(objArr[i4]);
            i4++;
        }
        int i5 = this.Z;
        if (i5 < 0 || i5 >= 4) {
            return;
        }
        ConstraintAnchor constraintAnchor = objArr[i5];
        if (!this.c0) {
            allSolved();
        }
        if (this.c0) {
            this.c0 = false;
            int i6 = this.Z;
            if (i6 == 0 || i6 == 1) {
                linearSystem.addEquality(this.mLeft.e, this.mX);
                linearSystem.addEquality(this.mRight.e, this.mX);
                return;
            } else if (i6 == 2 || i6 == 3) {
                linearSystem.addEquality(this.mTop.e, this.mY);
                linearSystem.addEquality(this.mBottom.e, this.mY);
                return;
            } else {
                return;
            }
        }
        for (int i7 = 0; i7 < this.mWidgetsCount; i7++) {
            ConstraintWidget constraintWidget = this.mWidgets[i7];
            if ((this.a0 || constraintWidget.allowedInBarrier()) && ((((i2 = this.Z) == 0 || i2 == 1) && constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mLeft.mTarget != null && constraintWidget.mRight.mTarget != null) || (((i3 = this.Z) == 2 || i3 == 3) && constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mTop.mTarget != null && constraintWidget.mBottom.mTarget != null))) {
                z2 = true;
                break;
            }
        }
        z2 = false;
        boolean z3 = this.mLeft.hasCenteredDependents() || this.mRight.hasCenteredDependents();
        boolean z4 = this.mTop.hasCenteredDependents() || this.mBottom.hasCenteredDependents();
        int i8 = !z2 && (((i = this.Z) == 0 && z3) || ((i == 2 && z4) || ((i == 1 && z3) || (i == 3 && z4)))) ? 5 : 4;
        for (int i9 = 0; i9 < this.mWidgetsCount; i9++) {
            ConstraintWidget constraintWidget2 = this.mWidgets[i9];
            if (this.a0 || constraintWidget2.allowedInBarrier()) {
                SolverVariable createObjectVariable = linearSystem.createObjectVariable(constraintWidget2.mListAnchors[this.Z]);
                ConstraintAnchor[] constraintAnchorArr2 = constraintWidget2.mListAnchors;
                int i10 = this.Z;
                constraintAnchorArr2[i10].e = createObjectVariable;
                int i11 = (constraintAnchorArr2[i10].mTarget == null || constraintAnchorArr2[i10].mTarget.mOwner != this) ? 0 : constraintAnchorArr2[i10].mMargin + 0;
                if (i10 != 0 && i10 != 2) {
                    linearSystem.addGreaterBarrier(constraintAnchor.e, createObjectVariable, this.b0 + i11, z2);
                } else {
                    linearSystem.addLowerBarrier(constraintAnchor.e, createObjectVariable, this.b0 - i11, z2);
                }
                linearSystem.addEquality(constraintAnchor.e, createObjectVariable, this.b0 + i11, i8);
            }
        }
        int i12 = this.Z;
        if (i12 == 0) {
            linearSystem.addEquality(this.mRight.e, this.mLeft.e, 0, 8);
            linearSystem.addEquality(this.mLeft.e, this.mParent.mRight.e, 0, 4);
            linearSystem.addEquality(this.mLeft.e, this.mParent.mLeft.e, 0, 0);
        } else if (i12 == 1) {
            linearSystem.addEquality(this.mLeft.e, this.mRight.e, 0, 8);
            linearSystem.addEquality(this.mLeft.e, this.mParent.mLeft.e, 0, 4);
            linearSystem.addEquality(this.mLeft.e, this.mParent.mRight.e, 0, 0);
        } else if (i12 == 2) {
            linearSystem.addEquality(this.mBottom.e, this.mTop.e, 0, 8);
            linearSystem.addEquality(this.mTop.e, this.mParent.mBottom.e, 0, 4);
            linearSystem.addEquality(this.mTop.e, this.mParent.mTop.e, 0, 0);
        } else if (i12 == 3) {
            linearSystem.addEquality(this.mTop.e, this.mBottom.e, 0, 8);
            linearSystem.addEquality(this.mTop.e, this.mParent.mTop.e, 0, 4);
            linearSystem.addEquality(this.mTop.e, this.mParent.mBottom.e, 0, 0);
        }
    }

    public boolean allSolved() {
        int i;
        int i2;
        int i3;
        int i4 = 0;
        boolean z = true;
        while (true) {
            i = this.mWidgetsCount;
            if (i4 >= i) {
                break;
            }
            ConstraintWidget constraintWidget = this.mWidgets[i4];
            if ((this.a0 || constraintWidget.allowedInBarrier()) && ((((i2 = this.Z) == 0 || i2 == 1) && !constraintWidget.isResolvedHorizontally()) || (((i3 = this.Z) == 2 || i3 == 3) && !constraintWidget.isResolvedVertically()))) {
                z = false;
            }
            i4++;
        }
        if (!z || i <= 0) {
            return false;
        }
        int i5 = 0;
        boolean z2 = false;
        for (int i6 = 0; i6 < this.mWidgetsCount; i6++) {
            ConstraintWidget constraintWidget2 = this.mWidgets[i6];
            if (this.a0 || constraintWidget2.allowedInBarrier()) {
                if (!z2) {
                    int i7 = this.Z;
                    if (i7 == 0) {
                        i5 = constraintWidget2.getAnchor(ConstraintAnchor.Type.LEFT).getFinalValue();
                    } else if (i7 == 1) {
                        i5 = constraintWidget2.getAnchor(ConstraintAnchor.Type.RIGHT).getFinalValue();
                    } else if (i7 == 2) {
                        i5 = constraintWidget2.getAnchor(ConstraintAnchor.Type.TOP).getFinalValue();
                    } else if (i7 == 3) {
                        i5 = constraintWidget2.getAnchor(ConstraintAnchor.Type.BOTTOM).getFinalValue();
                    }
                    z2 = true;
                }
                int i8 = this.Z;
                if (i8 == 0) {
                    i5 = Math.min(i5, constraintWidget2.getAnchor(ConstraintAnchor.Type.LEFT).getFinalValue());
                } else if (i8 == 1) {
                    i5 = Math.max(i5, constraintWidget2.getAnchor(ConstraintAnchor.Type.RIGHT).getFinalValue());
                } else if (i8 == 2) {
                    i5 = Math.min(i5, constraintWidget2.getAnchor(ConstraintAnchor.Type.TOP).getFinalValue());
                } else if (i8 == 3) {
                    i5 = Math.max(i5, constraintWidget2.getAnchor(ConstraintAnchor.Type.BOTTOM).getFinalValue());
                }
            }
        }
        int i9 = i5 + this.b0;
        int i10 = this.Z;
        if (i10 != 0 && i10 != 1) {
            setFinalVertical(i9, i9);
        } else {
            setFinalHorizontal(i9, i9);
        }
        this.c0 = true;
        return true;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public boolean allowedInBarrier() {
        return true;
    }

    @Deprecated
    public boolean allowsGoneWidget() {
        return this.a0;
    }

    @Override // androidx.constraintlayout.core.widgets.HelperWidget, androidx.constraintlayout.core.widgets.ConstraintWidget
    public void copy(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> hashMap) {
        super.copy(constraintWidget, hashMap);
        Barrier barrier = (Barrier) constraintWidget;
        this.Z = barrier.Z;
        this.a0 = barrier.a0;
        this.b0 = barrier.b0;
    }

    public boolean getAllowsGoneWidget() {
        return this.a0;
    }

    public int getBarrierType() {
        return this.Z;
    }

    public int getMargin() {
        return this.b0;
    }

    public int getOrientation() {
        int i = this.Z;
        if (i == 0 || i == 1) {
            return 0;
        }
        return (i == 2 || i == 3) ? 1 : -1;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public boolean isResolvedHorizontally() {
        return this.c0;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public boolean isResolvedVertically() {
        return this.c0;
    }

    public void markWidgets() {
        for (int i = 0; i < this.mWidgetsCount; i++) {
            ConstraintWidget constraintWidget = this.mWidgets[i];
            if (this.a0 || constraintWidget.allowedInBarrier()) {
                int i2 = this.Z;
                if (i2 == 0 || i2 == 1) {
                    constraintWidget.setInBarrier(0, true);
                } else if (i2 == 2 || i2 == 3) {
                    constraintWidget.setInBarrier(1, true);
                }
            }
        }
    }

    public void setAllowsGoneWidget(boolean z) {
        this.a0 = z;
    }

    public void setBarrierType(int i) {
        this.Z = i;
    }

    public void setMargin(int i) {
        this.b0 = i;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public String toString() {
        String str = "[Barrier] " + getDebugName() + " {";
        for (int i = 0; i < this.mWidgetsCount; i++) {
            ConstraintWidget constraintWidget = this.mWidgets[i];
            if (i > 0) {
                str = str + ", ";
            }
            str = str + constraintWidget.getDebugName();
        }
        return str + "}";
    }

    public Barrier(String str) {
        setDebugName(str);
    }
}
