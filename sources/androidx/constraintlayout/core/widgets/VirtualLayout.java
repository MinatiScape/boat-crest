package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.util.HashSet;
/* loaded from: classes.dex */
public class VirtualLayout extends HelperWidget {
    public int Z = 0;
    public int a0 = 0;
    public int b0 = 0;
    public int c0 = 0;
    public int d0 = 0;
    public int e0 = 0;
    public boolean f0 = false;
    public int g0 = 0;
    public int h0 = 0;
    public BasicMeasure.Measure mMeasure = new BasicMeasure.Measure();
    public BasicMeasure.Measurer i0 = null;

    public void applyRtl(boolean z) {
        int i = this.b0;
        if (i > 0 || this.c0 > 0) {
            if (z) {
                this.d0 = this.c0;
                this.e0 = i;
                return;
            }
            this.d0 = i;
            this.e0 = this.c0;
        }
    }

    public void captureWidgets() {
        for (int i = 0; i < this.mWidgetsCount; i++) {
            ConstraintWidget constraintWidget = this.mWidgets[i];
            if (constraintWidget != null) {
                constraintWidget.setInVirtualLayout(true);
            }
        }
    }

    public boolean contains(HashSet<ConstraintWidget> hashSet) {
        for (int i = 0; i < this.mWidgetsCount; i++) {
            if (hashSet.contains(this.mWidgets[i])) {
                return true;
            }
        }
        return false;
    }

    public int getMeasuredHeight() {
        return this.h0;
    }

    public int getMeasuredWidth() {
        return this.g0;
    }

    public int getPaddingBottom() {
        return this.a0;
    }

    public int getPaddingLeft() {
        return this.d0;
    }

    public int getPaddingRight() {
        return this.e0;
    }

    public int getPaddingTop() {
        return this.Z;
    }

    public void measure(int i, int i2, int i3, int i4) {
    }

    public void measure(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i2) {
        while (this.i0 == null && getParent() != null) {
            this.i0 = ((ConstraintWidgetContainer) getParent()).getMeasurer();
        }
        BasicMeasure.Measure measure = this.mMeasure;
        measure.horizontalBehavior = dimensionBehaviour;
        measure.verticalBehavior = dimensionBehaviour2;
        measure.horizontalDimension = i;
        measure.verticalDimension = i2;
        this.i0.measure(constraintWidget, measure);
        constraintWidget.setWidth(this.mMeasure.measuredWidth);
        constraintWidget.setHeight(this.mMeasure.measuredHeight);
        constraintWidget.setHasBaseline(this.mMeasure.measuredHasBaseline);
        constraintWidget.setBaselineDistance(this.mMeasure.measuredBaseline);
    }

    public boolean measureChildren() {
        ConstraintWidget constraintWidget = this.mParent;
        BasicMeasure.Measurer measurer = constraintWidget != null ? ((ConstraintWidgetContainer) constraintWidget).getMeasurer() : null;
        if (measurer == null) {
            return false;
        }
        int i = 0;
        while (true) {
            boolean z = true;
            if (i >= this.mWidgetsCount) {
                return true;
            }
            ConstraintWidget constraintWidget2 = this.mWidgets[i];
            if (constraintWidget2 != null && !(constraintWidget2 instanceof Guideline)) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = constraintWidget2.getDimensionBehaviour(0);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = constraintWidget2.getDimensionBehaviour(1);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviour != dimensionBehaviour3 || constraintWidget2.mMatchConstraintDefaultWidth == 1 || dimensionBehaviour2 != dimensionBehaviour3 || constraintWidget2.mMatchConstraintDefaultHeight == 1) {
                    z = false;
                }
                if (!z) {
                    if (dimensionBehaviour == dimensionBehaviour3) {
                        dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    }
                    if (dimensionBehaviour2 == dimensionBehaviour3) {
                        dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    }
                    BasicMeasure.Measure measure = this.mMeasure;
                    measure.horizontalBehavior = dimensionBehaviour;
                    measure.verticalBehavior = dimensionBehaviour2;
                    measure.horizontalDimension = constraintWidget2.getWidth();
                    this.mMeasure.verticalDimension = constraintWidget2.getHeight();
                    measurer.measure(constraintWidget2, this.mMeasure);
                    constraintWidget2.setWidth(this.mMeasure.measuredWidth);
                    constraintWidget2.setHeight(this.mMeasure.measuredHeight);
                    constraintWidget2.setBaselineDistance(this.mMeasure.measuredBaseline);
                }
            }
            i++;
        }
    }

    public boolean needSolverPass() {
        return this.f0;
    }

    public void needsCallbackFromSolver(boolean z) {
        this.f0 = z;
    }

    public void setMeasure(int i, int i2) {
        this.g0 = i;
        this.h0 = i2;
    }

    public void setPadding(int i) {
        this.Z = i;
        this.a0 = i;
        this.b0 = i;
        this.c0 = i;
    }

    public void setPaddingBottom(int i) {
        this.a0 = i;
    }

    public void setPaddingEnd(int i) {
        this.c0 = i;
    }

    public void setPaddingLeft(int i) {
        this.d0 = i;
    }

    public void setPaddingRight(int i) {
        this.e0 = i;
    }

    public void setPaddingStart(int i) {
        this.b0 = i;
        this.d0 = i;
        this.e0 = i;
    }

    public void setPaddingTop(int i) {
        this.Z = i;
    }

    @Override // androidx.constraintlayout.core.widgets.HelperWidget, androidx.constraintlayout.core.widgets.Helper
    public void updateConstraints(ConstraintWidgetContainer constraintWidgetContainer) {
        captureWidgets();
    }
}
