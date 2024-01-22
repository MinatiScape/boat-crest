package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.Barrier;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.HelperWidget;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
/* loaded from: classes.dex */
public class DependencyGraph {

    /* renamed from: a  reason: collision with root package name */
    public ConstraintWidgetContainer f924a;
    public ConstraintWidgetContainer d;
    public BasicMeasure.Measurer f;
    public BasicMeasure.Measure g;
    public ArrayList<e> h;
    public boolean b = true;
    public boolean c = true;
    public ArrayList<WidgetRun> e = new ArrayList<>();

    public DependencyGraph(ConstraintWidgetContainer constraintWidgetContainer) {
        new ArrayList();
        this.f = null;
        this.g = new BasicMeasure.Measure();
        this.h = new ArrayList<>();
        this.f924a = constraintWidgetContainer;
        this.d = constraintWidgetContainer;
    }

    public final void a(DependencyNode dependencyNode, int i, int i2, DependencyNode dependencyNode2, ArrayList<e> arrayList, e eVar) {
        WidgetRun widgetRun = dependencyNode.f925a;
        if (widgetRun.b == null) {
            ConstraintWidgetContainer constraintWidgetContainer = this.f924a;
            if (widgetRun == constraintWidgetContainer.horizontalRun || widgetRun == constraintWidgetContainer.verticalRun) {
                return;
            }
            if (eVar == null) {
                eVar = new e(widgetRun, i2);
                arrayList.add(eVar);
            }
            widgetRun.b = eVar;
            eVar.a(widgetRun);
            for (Dependency dependency : widgetRun.start.f) {
                if (dependency instanceof DependencyNode) {
                    a((DependencyNode) dependency, i, 0, dependencyNode2, arrayList, eVar);
                }
            }
            for (Dependency dependency2 : widgetRun.end.f) {
                if (dependency2 instanceof DependencyNode) {
                    a((DependencyNode) dependency2, i, 1, dependencyNode2, arrayList, eVar);
                }
            }
            if (i == 1 && (widgetRun instanceof VerticalWidgetRun)) {
                for (Dependency dependency3 : ((VerticalWidgetRun) widgetRun).baseline.f) {
                    if (dependency3 instanceof DependencyNode) {
                        a((DependencyNode) dependency3, i, 2, dependencyNode2, arrayList, eVar);
                    }
                }
            }
            for (DependencyNode dependencyNode3 : widgetRun.start.g) {
                a(dependencyNode3, i, 0, dependencyNode2, arrayList, eVar);
            }
            for (DependencyNode dependencyNode4 : widgetRun.end.g) {
                a(dependencyNode4, i, 1, dependencyNode2, arrayList, eVar);
            }
            if (i == 1 && (widgetRun instanceof VerticalWidgetRun)) {
                for (DependencyNode dependencyNode5 : ((VerticalWidgetRun) widgetRun).baseline.g) {
                    a(dependencyNode5, i, 2, dependencyNode2, arrayList, eVar);
                }
            }
        }
    }

    public final boolean b(ConstraintWidgetContainer constraintWidgetContainer) {
        int i;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        int i2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour4;
        Iterator<ConstraintWidget> it = constraintWidgetContainer.mChildren.iterator();
        while (it.hasNext()) {
            ConstraintWidget next = it.next();
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = next.mListDimensionBehaviors;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = dimensionBehaviourArr[0];
            ConstraintWidget.DimensionBehaviour dimensionBehaviour6 = dimensionBehaviourArr[1];
            if (next.getVisibility() == 8) {
                next.measured = true;
            } else {
                if (next.mMatchConstraintPercentWidth < 1.0f && dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    next.mMatchConstraintDefaultWidth = 2;
                }
                if (next.mMatchConstraintPercentHeight < 1.0f && dimensionBehaviour6 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    next.mMatchConstraintDefaultHeight = 2;
                }
                if (next.getDimensionRatio() > 0.0f) {
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour7 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                    if (dimensionBehaviour5 == dimensionBehaviour7 && (dimensionBehaviour6 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour6 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        next.mMatchConstraintDefaultWidth = 3;
                    } else if (dimensionBehaviour6 == dimensionBehaviour7 && (dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        next.mMatchConstraintDefaultHeight = 3;
                    } else if (dimensionBehaviour5 == dimensionBehaviour7 && dimensionBehaviour6 == dimensionBehaviour7) {
                        if (next.mMatchConstraintDefaultWidth == 0) {
                            next.mMatchConstraintDefaultWidth = 3;
                        }
                        if (next.mMatchConstraintDefaultHeight == 0) {
                            next.mMatchConstraintDefaultHeight = 3;
                        }
                    }
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour8 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviour5 == dimensionBehaviour8 && next.mMatchConstraintDefaultWidth == 1 && (next.mLeft.mTarget == null || next.mRight.mTarget == null)) {
                    dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour9 = dimensionBehaviour5;
                if (dimensionBehaviour6 == dimensionBehaviour8 && next.mMatchConstraintDefaultHeight == 1 && (next.mTop.mTarget == null || next.mBottom.mTarget == null)) {
                    dimensionBehaviour6 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour10 = dimensionBehaviour6;
                HorizontalWidgetRun horizontalWidgetRun = next.horizontalRun;
                horizontalWidgetRun.dimensionBehavior = dimensionBehaviour9;
                int i3 = next.mMatchConstraintDefaultWidth;
                horizontalWidgetRun.matchConstraintsType = i3;
                VerticalWidgetRun verticalWidgetRun = next.verticalRun;
                verticalWidgetRun.dimensionBehavior = dimensionBehaviour10;
                int i4 = next.mMatchConstraintDefaultHeight;
                verticalWidgetRun.matchConstraintsType = i4;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour11 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                if ((dimensionBehaviour9 == dimensionBehaviour11 || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) && (dimensionBehaviour10 == dimensionBehaviour11 || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)) {
                    int width = next.getWidth();
                    if (dimensionBehaviour9 == dimensionBehaviour11) {
                        i = (constraintWidgetContainer.getWidth() - next.mLeft.mMargin) - next.mRight.mMargin;
                        dimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                    } else {
                        i = width;
                        dimensionBehaviour = dimensionBehaviour9;
                    }
                    int height = next.getHeight();
                    if (dimensionBehaviour10 == dimensionBehaviour11) {
                        i2 = (constraintWidgetContainer.getHeight() - next.mTop.mMargin) - next.mBottom.mMargin;
                        dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
                    } else {
                        i2 = height;
                        dimensionBehaviour2 = dimensionBehaviour10;
                    }
                    e(next, dimensionBehaviour, i, dimensionBehaviour2, i2);
                    next.horizontalRun.c.resolve(next.getWidth());
                    next.verticalRun.c.resolve(next.getHeight());
                    next.measured = true;
                } else {
                    if (dimensionBehaviour9 == dimensionBehaviour8 && (dimensionBehaviour10 == (dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        if (i3 == 3) {
                            if (dimensionBehaviour10 == dimensionBehaviour4) {
                                e(next, dimensionBehaviour4, 0, dimensionBehaviour4, 0);
                            }
                            int height2 = next.getHeight();
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour12 = ConstraintWidget.DimensionBehaviour.FIXED;
                            e(next, dimensionBehaviour12, (int) ((height2 * next.mDimensionRatio) + 0.5f), dimensionBehaviour12, height2);
                            next.horizontalRun.c.resolve(next.getWidth());
                            next.verticalRun.c.resolve(next.getHeight());
                            next.measured = true;
                        } else if (i3 == 1) {
                            e(next, dimensionBehaviour4, 0, dimensionBehaviour10, 0);
                            next.horizontalRun.c.h = next.getWidth();
                        } else if (i3 == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = constraintWidgetContainer.mListDimensionBehaviors;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour13 = dimensionBehaviourArr2[0];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour14 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour13 == dimensionBehaviour14 || dimensionBehaviourArr2[0] == dimensionBehaviour11) {
                                e(next, dimensionBehaviour14, (int) ((next.mMatchConstraintPercentWidth * constraintWidgetContainer.getWidth()) + 0.5f), dimensionBehaviour10, next.getHeight());
                                next.horizontalRun.c.resolve(next.getWidth());
                                next.verticalRun.c.resolve(next.getHeight());
                                next.measured = true;
                            }
                        } else {
                            ConstraintAnchor[] constraintAnchorArr = next.mListAnchors;
                            if (constraintAnchorArr[0].mTarget == null || constraintAnchorArr[1].mTarget == null) {
                                e(next, dimensionBehaviour4, 0, dimensionBehaviour10, 0);
                                next.horizontalRun.c.resolve(next.getWidth());
                                next.verticalRun.c.resolve(next.getHeight());
                                next.measured = true;
                            }
                        }
                    }
                    if (dimensionBehaviour10 == dimensionBehaviour8 && (dimensionBehaviour9 == (dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        if (i4 == 3) {
                            if (dimensionBehaviour9 == dimensionBehaviour3) {
                                e(next, dimensionBehaviour3, 0, dimensionBehaviour3, 0);
                            }
                            int width2 = next.getWidth();
                            float f = next.mDimensionRatio;
                            if (next.getDimensionRatioSide() == -1) {
                                f = 1.0f / f;
                            }
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour15 = ConstraintWidget.DimensionBehaviour.FIXED;
                            e(next, dimensionBehaviour15, width2, dimensionBehaviour15, (int) ((width2 * f) + 0.5f));
                            next.horizontalRun.c.resolve(next.getWidth());
                            next.verticalRun.c.resolve(next.getHeight());
                            next.measured = true;
                        } else if (i4 == 1) {
                            e(next, dimensionBehaviour9, 0, dimensionBehaviour3, 0);
                            next.verticalRun.c.h = next.getHeight();
                        } else if (i4 == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr3 = constraintWidgetContainer.mListDimensionBehaviors;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour16 = dimensionBehaviourArr3[1];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour17 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour16 == dimensionBehaviour17 || dimensionBehaviourArr3[1] == dimensionBehaviour11) {
                                e(next, dimensionBehaviour9, next.getWidth(), dimensionBehaviour17, (int) ((next.mMatchConstraintPercentHeight * constraintWidgetContainer.getHeight()) + 0.5f));
                                next.horizontalRun.c.resolve(next.getWidth());
                                next.verticalRun.c.resolve(next.getHeight());
                                next.measured = true;
                            }
                        } else {
                            ConstraintAnchor[] constraintAnchorArr2 = next.mListAnchors;
                            if (constraintAnchorArr2[2].mTarget == null || constraintAnchorArr2[3].mTarget == null) {
                                e(next, dimensionBehaviour3, 0, dimensionBehaviour10, 0);
                                next.horizontalRun.c.resolve(next.getWidth());
                                next.verticalRun.c.resolve(next.getHeight());
                                next.measured = true;
                            }
                        }
                    }
                    if (dimensionBehaviour9 == dimensionBehaviour8 && dimensionBehaviour10 == dimensionBehaviour8) {
                        if (i3 == 1 || i4 == 1) {
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour18 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                            e(next, dimensionBehaviour18, 0, dimensionBehaviour18, 0);
                            next.horizontalRun.c.h = next.getWidth();
                            next.verticalRun.c.h = next.getHeight();
                        } else if (i4 == 2 && i3 == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr4 = constraintWidgetContainer.mListDimensionBehaviors;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour19 = dimensionBehaviourArr4[0];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour20 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour19 == dimensionBehaviour20 && dimensionBehaviourArr4[1] == dimensionBehaviour20) {
                                e(next, dimensionBehaviour20, (int) ((next.mMatchConstraintPercentWidth * constraintWidgetContainer.getWidth()) + 0.5f), dimensionBehaviour20, (int) ((next.mMatchConstraintPercentHeight * constraintWidgetContainer.getHeight()) + 0.5f));
                                next.horizontalRun.c.resolve(next.getWidth());
                                next.verticalRun.c.resolve(next.getHeight());
                                next.measured = true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public void buildGraph() {
        buildGraph(this.e);
        this.h.clear();
        e.c = 0;
        d(this.f924a.horizontalRun, 0, this.h);
        d(this.f924a.verticalRun, 1, this.h);
        this.b = false;
    }

    public final int c(ConstraintWidgetContainer constraintWidgetContainer, int i) {
        int size = this.h.size();
        long j = 0;
        for (int i2 = 0; i2 < size; i2++) {
            j = Math.max(j, this.h.get(i2).b(constraintWidgetContainer, i));
        }
        return (int) j;
    }

    public final void d(WidgetRun widgetRun, int i, ArrayList<e> arrayList) {
        for (Dependency dependency : widgetRun.start.f) {
            if (dependency instanceof DependencyNode) {
                a((DependencyNode) dependency, i, 0, widgetRun.end, arrayList, null);
            } else if (dependency instanceof WidgetRun) {
                a(((WidgetRun) dependency).start, i, 0, widgetRun.end, arrayList, null);
            }
        }
        for (Dependency dependency2 : widgetRun.end.f) {
            if (dependency2 instanceof DependencyNode) {
                a((DependencyNode) dependency2, i, 1, widgetRun.start, arrayList, null);
            } else if (dependency2 instanceof WidgetRun) {
                a(((WidgetRun) dependency2).end, i, 1, widgetRun.start, arrayList, null);
            }
        }
        if (i == 1) {
            for (Dependency dependency3 : ((VerticalWidgetRun) widgetRun).baseline.f) {
                if (dependency3 instanceof DependencyNode) {
                    a((DependencyNode) dependency3, i, 2, null, arrayList, null);
                }
            }
        }
    }

    public void defineTerminalWidgets(ConstraintWidget.DimensionBehaviour dimensionBehaviour, ConstraintWidget.DimensionBehaviour dimensionBehaviour2) {
        if (this.b) {
            buildGraph();
            Iterator<ConstraintWidget> it = this.f924a.mChildren.iterator();
            boolean z = false;
            while (it.hasNext()) {
                ConstraintWidget next = it.next();
                boolean[] zArr = next.isTerminalWidget;
                zArr[0] = true;
                zArr[1] = true;
                if (next instanceof Barrier) {
                    z = true;
                }
            }
            if (z) {
                return;
            }
            Iterator<e> it2 = this.h.iterator();
            while (it2.hasNext()) {
                e next2 = it2.next();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                next2.d(dimensionBehaviour == dimensionBehaviour3, dimensionBehaviour2 == dimensionBehaviour3);
            }
        }
    }

    public boolean directMeasure(boolean z) {
        boolean z2;
        boolean z3 = true;
        boolean z4 = z & true;
        if (this.b || this.c) {
            Iterator<ConstraintWidget> it = this.f924a.mChildren.iterator();
            while (it.hasNext()) {
                ConstraintWidget next = it.next();
                next.ensureWidgetRuns();
                next.measured = false;
                next.horizontalRun.f();
                next.verticalRun.e();
            }
            this.f924a.ensureWidgetRuns();
            ConstraintWidgetContainer constraintWidgetContainer = this.f924a;
            constraintWidgetContainer.measured = false;
            constraintWidgetContainer.horizontalRun.f();
            this.f924a.verticalRun.e();
            this.c = false;
        }
        if (b(this.d)) {
            return false;
        }
        this.f924a.setX(0);
        this.f924a.setY(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.f924a.getDimensionBehaviour(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = this.f924a.getDimensionBehaviour(1);
        if (this.b) {
            buildGraph();
        }
        int x = this.f924a.getX();
        int y = this.f924a.getY();
        this.f924a.horizontalRun.start.resolve(x);
        this.f924a.verticalRun.start.resolve(y);
        measureWidgets();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (dimensionBehaviour == dimensionBehaviour3 || dimensionBehaviour2 == dimensionBehaviour3) {
            if (z4) {
                Iterator<WidgetRun> it2 = this.e.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    } else if (!it2.next().d()) {
                        z4 = false;
                        break;
                    }
                }
            }
            if (z4 && dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.f924a.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                ConstraintWidgetContainer constraintWidgetContainer2 = this.f924a;
                constraintWidgetContainer2.setWidth(c(constraintWidgetContainer2, 0));
                ConstraintWidgetContainer constraintWidgetContainer3 = this.f924a;
                constraintWidgetContainer3.horizontalRun.c.resolve(constraintWidgetContainer3.getWidth());
            }
            if (z4 && dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.f924a.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                ConstraintWidgetContainer constraintWidgetContainer4 = this.f924a;
                constraintWidgetContainer4.setHeight(c(constraintWidgetContainer4, 1));
                ConstraintWidgetContainer constraintWidgetContainer5 = this.f924a;
                constraintWidgetContainer5.verticalRun.c.resolve(constraintWidgetContainer5.getHeight());
            }
        }
        ConstraintWidgetContainer constraintWidgetContainer6 = this.f924a;
        ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidgetContainer6.mListDimensionBehaviors;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = dimensionBehaviourArr[0];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.FIXED;
        if (dimensionBehaviour4 == dimensionBehaviour5 || dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int width = constraintWidgetContainer6.getWidth() + x;
            this.f924a.horizontalRun.end.resolve(width);
            this.f924a.horizontalRun.c.resolve(width - x);
            measureWidgets();
            ConstraintWidgetContainer constraintWidgetContainer7 = this.f924a;
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = constraintWidgetContainer7.mListDimensionBehaviors;
            if (dimensionBehaviourArr2[1] == dimensionBehaviour5 || dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int height = constraintWidgetContainer7.getHeight() + y;
                this.f924a.verticalRun.end.resolve(height);
                this.f924a.verticalRun.c.resolve(height - y);
            }
            measureWidgets();
            z2 = true;
        } else {
            z2 = false;
        }
        Iterator<WidgetRun> it3 = this.e.iterator();
        while (it3.hasNext()) {
            WidgetRun next2 = it3.next();
            if (next2.f931a != this.f924a || next2.d) {
                next2.applyToWidget();
            }
        }
        Iterator<WidgetRun> it4 = this.e.iterator();
        while (it4.hasNext()) {
            WidgetRun next3 = it4.next();
            if (z2 || next3.f931a != this.f924a) {
                if (!next3.start.resolved || ((!next3.end.resolved && !(next3 instanceof c)) || (!next3.c.resolved && !(next3 instanceof ChainRun) && !(next3 instanceof c)))) {
                    z3 = false;
                    break;
                }
            }
        }
        this.f924a.setHorizontalDimensionBehaviour(dimensionBehaviour);
        this.f924a.setVerticalDimensionBehaviour(dimensionBehaviour2);
        return z3;
    }

    public boolean directMeasureSetup(boolean z) {
        if (this.b) {
            Iterator<ConstraintWidget> it = this.f924a.mChildren.iterator();
            while (it.hasNext()) {
                ConstraintWidget next = it.next();
                next.ensureWidgetRuns();
                next.measured = false;
                HorizontalWidgetRun horizontalWidgetRun = next.horizontalRun;
                horizontalWidgetRun.c.resolved = false;
                horizontalWidgetRun.d = false;
                horizontalWidgetRun.f();
                VerticalWidgetRun verticalWidgetRun = next.verticalRun;
                verticalWidgetRun.c.resolved = false;
                verticalWidgetRun.d = false;
                verticalWidgetRun.e();
            }
            this.f924a.ensureWidgetRuns();
            ConstraintWidgetContainer constraintWidgetContainer = this.f924a;
            constraintWidgetContainer.measured = false;
            HorizontalWidgetRun horizontalWidgetRun2 = constraintWidgetContainer.horizontalRun;
            horizontalWidgetRun2.c.resolved = false;
            horizontalWidgetRun2.d = false;
            horizontalWidgetRun2.f();
            VerticalWidgetRun verticalWidgetRun2 = this.f924a.verticalRun;
            verticalWidgetRun2.c.resolved = false;
            verticalWidgetRun2.d = false;
            verticalWidgetRun2.e();
            buildGraph();
        }
        if (b(this.d)) {
            return false;
        }
        this.f924a.setX(0);
        this.f924a.setY(0);
        this.f924a.horizontalRun.start.resolve(0);
        this.f924a.verticalRun.start.resolve(0);
        return true;
    }

    public boolean directMeasureWithOrientation(boolean z, int i) {
        boolean z2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        boolean z3 = true;
        boolean z4 = z & true;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = this.f924a.getDimensionBehaviour(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = this.f924a.getDimensionBehaviour(1);
        int x = this.f924a.getX();
        int y = this.f924a.getY();
        if (z4 && (dimensionBehaviour2 == (dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviour3 == dimensionBehaviour)) {
            Iterator<WidgetRun> it = this.e.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                WidgetRun next = it.next();
                if (next.orientation == i && !next.d()) {
                    z4 = false;
                    break;
                }
            }
            if (i == 0) {
                if (z4 && dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    this.f924a.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                    ConstraintWidgetContainer constraintWidgetContainer = this.f924a;
                    constraintWidgetContainer.setWidth(c(constraintWidgetContainer, 0));
                    ConstraintWidgetContainer constraintWidgetContainer2 = this.f924a;
                    constraintWidgetContainer2.horizontalRun.c.resolve(constraintWidgetContainer2.getWidth());
                }
            } else if (z4 && dimensionBehaviour3 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.f924a.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                ConstraintWidgetContainer constraintWidgetContainer3 = this.f924a;
                constraintWidgetContainer3.setHeight(c(constraintWidgetContainer3, 1));
                ConstraintWidgetContainer constraintWidgetContainer4 = this.f924a;
                constraintWidgetContainer4.verticalRun.c.resolve(constraintWidgetContainer4.getHeight());
            }
        }
        if (i == 0) {
            ConstraintWidgetContainer constraintWidgetContainer5 = this.f924a;
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidgetContainer5.mListDimensionBehaviors;
            if (dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int width = constraintWidgetContainer5.getWidth() + x;
                this.f924a.horizontalRun.end.resolve(width);
                this.f924a.horizontalRun.c.resolve(width - x);
                z2 = true;
            }
            z2 = false;
        } else {
            ConstraintWidgetContainer constraintWidgetContainer6 = this.f924a;
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = constraintWidgetContainer6.mListDimensionBehaviors;
            if (dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int height = constraintWidgetContainer6.getHeight() + y;
                this.f924a.verticalRun.end.resolve(height);
                this.f924a.verticalRun.c.resolve(height - y);
                z2 = true;
            }
            z2 = false;
        }
        measureWidgets();
        Iterator<WidgetRun> it2 = this.e.iterator();
        while (it2.hasNext()) {
            WidgetRun next2 = it2.next();
            if (next2.orientation == i && (next2.f931a != this.f924a || next2.d)) {
                next2.applyToWidget();
            }
        }
        Iterator<WidgetRun> it3 = this.e.iterator();
        while (it3.hasNext()) {
            WidgetRun next3 = it3.next();
            if (next3.orientation == i && (z2 || next3.f931a != this.f924a)) {
                if (!next3.start.resolved || !next3.end.resolved || (!(next3 instanceof ChainRun) && !next3.c.resolved)) {
                    z3 = false;
                    break;
                }
            }
        }
        this.f924a.setHorizontalDimensionBehaviour(dimensionBehaviour2);
        this.f924a.setVerticalDimensionBehaviour(dimensionBehaviour3);
        return z3;
    }

    public final void e(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i2) {
        BasicMeasure.Measure measure = this.g;
        measure.horizontalBehavior = dimensionBehaviour;
        measure.verticalBehavior = dimensionBehaviour2;
        measure.horizontalDimension = i;
        measure.verticalDimension = i2;
        this.f.measure(constraintWidget, measure);
        constraintWidget.setWidth(this.g.measuredWidth);
        constraintWidget.setHeight(this.g.measuredHeight);
        constraintWidget.setHasBaseline(this.g.measuredHasBaseline);
        constraintWidget.setBaselineDistance(this.g.measuredBaseline);
    }

    public void invalidateGraph() {
        this.b = true;
    }

    public void invalidateMeasures() {
        this.c = true;
    }

    public void measureWidgets() {
        b bVar;
        Iterator<ConstraintWidget> it = this.f924a.mChildren.iterator();
        while (it.hasNext()) {
            ConstraintWidget next = it.next();
            if (!next.measured) {
                ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = next.mListDimensionBehaviors;
                boolean z = false;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[1];
                int i = next.mMatchConstraintDefaultWidth;
                int i2 = next.mMatchConstraintDefaultHeight;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                boolean z2 = dimensionBehaviour == dimensionBehaviour3 || (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && i == 1);
                if (dimensionBehaviour2 == dimensionBehaviour3 || (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && i2 == 1)) {
                    z = true;
                }
                b bVar2 = next.horizontalRun.c;
                boolean z3 = bVar2.resolved;
                b bVar3 = next.verticalRun.c;
                boolean z4 = bVar3.resolved;
                if (z3 && z4) {
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
                    e(next, dimensionBehaviour4, bVar2.value, dimensionBehaviour4, bVar3.value);
                    next.measured = true;
                } else if (z3 && z) {
                    e(next, ConstraintWidget.DimensionBehaviour.FIXED, bVar2.value, dimensionBehaviour3, bVar3.value);
                    if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        next.verticalRun.c.h = next.getHeight();
                    } else {
                        next.verticalRun.c.resolve(next.getHeight());
                        next.measured = true;
                    }
                } else if (z4 && z2) {
                    e(next, dimensionBehaviour3, bVar2.value, ConstraintWidget.DimensionBehaviour.FIXED, bVar3.value);
                    if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        next.horizontalRun.c.h = next.getWidth();
                    } else {
                        next.horizontalRun.c.resolve(next.getWidth());
                        next.measured = true;
                    }
                }
                if (next.measured && (bVar = next.verticalRun.e) != null) {
                    bVar.resolve(next.getBaselineDistance());
                }
            }
        }
    }

    public void setMeasurer(BasicMeasure.Measurer measurer) {
        this.f = measurer;
    }

    public void buildGraph(ArrayList<WidgetRun> arrayList) {
        arrayList.clear();
        this.d.horizontalRun.b();
        this.d.verticalRun.b();
        arrayList.add(this.d.horizontalRun);
        arrayList.add(this.d.verticalRun);
        Iterator<ConstraintWidget> it = this.d.mChildren.iterator();
        HashSet hashSet = null;
        while (it.hasNext()) {
            ConstraintWidget next = it.next();
            if (next instanceof Guideline) {
                arrayList.add(new c(next));
            } else {
                if (next.isInHorizontalChain()) {
                    if (next.horizontalChainRun == null) {
                        next.horizontalChainRun = new ChainRun(next, 0);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(next.horizontalChainRun);
                } else {
                    arrayList.add(next.horizontalRun);
                }
                if (next.isInVerticalChain()) {
                    if (next.verticalChainRun == null) {
                        next.verticalChainRun = new ChainRun(next, 1);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(next.verticalChainRun);
                } else {
                    arrayList.add(next.verticalRun);
                }
                if (next instanceof HelperWidget) {
                    arrayList.add(new d(next));
                }
            }
        }
        if (hashSet != null) {
            arrayList.addAll(hashSet);
        }
        Iterator<WidgetRun> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            it2.next().b();
        }
        Iterator<WidgetRun> it3 = arrayList.iterator();
        while (it3.hasNext()) {
            WidgetRun next2 = it3.next();
            if (next2.f931a != this.d) {
                next2.a();
            }
        }
    }
}
