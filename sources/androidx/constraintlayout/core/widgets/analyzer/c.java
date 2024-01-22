package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.Guideline;
/* loaded from: classes.dex */
public class c extends WidgetRun {
    public c(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        constraintWidget.horizontalRun.b();
        constraintWidget.verticalRun.b();
        this.orientation = ((Guideline) constraintWidget).getOrientation();
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void a() {
        Guideline guideline = (Guideline) this.f931a;
        int relativeBegin = guideline.getRelativeBegin();
        int relativeEnd = guideline.getRelativeEnd();
        guideline.getRelativePercent();
        if (guideline.getOrientation() == 1) {
            if (relativeBegin != -1) {
                this.start.g.add(this.f931a.mParent.horizontalRun.start);
                this.f931a.mParent.horizontalRun.start.f.add(this.start);
                this.start.c = relativeBegin;
            } else if (relativeEnd != -1) {
                this.start.g.add(this.f931a.mParent.horizontalRun.end);
                this.f931a.mParent.horizontalRun.end.f.add(this.start);
                this.start.c = -relativeEnd;
            } else {
                DependencyNode dependencyNode = this.start;
                dependencyNode.delegateToWidgetRun = true;
                dependencyNode.g.add(this.f931a.mParent.horizontalRun.end);
                this.f931a.mParent.horizontalRun.end.f.add(this.start);
            }
            e(this.f931a.horizontalRun.start);
            e(this.f931a.horizontalRun.end);
            return;
        }
        if (relativeBegin != -1) {
            this.start.g.add(this.f931a.mParent.verticalRun.start);
            this.f931a.mParent.verticalRun.start.f.add(this.start);
            this.start.c = relativeBegin;
        } else if (relativeEnd != -1) {
            this.start.g.add(this.f931a.mParent.verticalRun.end);
            this.f931a.mParent.verticalRun.end.f.add(this.start);
            this.start.c = -relativeEnd;
        } else {
            DependencyNode dependencyNode2 = this.start;
            dependencyNode2.delegateToWidgetRun = true;
            dependencyNode2.g.add(this.f931a.mParent.verticalRun.end);
            this.f931a.mParent.verticalRun.end.f.add(this.start);
        }
        e(this.f931a.verticalRun.start);
        e(this.f931a.verticalRun.end);
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        if (((Guideline) this.f931a).getOrientation() == 1) {
            this.f931a.setX(this.start.value);
        } else {
            this.f931a.setY(this.start.value);
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void b() {
        this.start.clear();
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public boolean d() {
        return false;
    }

    public final void e(DependencyNode dependencyNode) {
        this.start.f.add(dependencyNode);
        dependencyNode.g.add(this.start);
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    public void update(Dependency dependency) {
        DependencyNode dependencyNode = this.start;
        if (dependencyNode.readyToSolve && !dependencyNode.resolved) {
            this.start.resolve((int) ((dependencyNode.g.get(0).value * ((Guideline) this.f931a).getRelativePercent()) + 0.5f));
        }
    }
}
