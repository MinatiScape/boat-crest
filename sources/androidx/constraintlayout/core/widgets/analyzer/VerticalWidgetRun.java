package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.Helper;
import androidx.constraintlayout.core.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.core.widgets.analyzer.WidgetRun;
/* loaded from: classes.dex */
public class VerticalWidgetRun extends WidgetRun {
    public DependencyNode baseline;
    public b e;

    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f928a;

        static {
            int[] iArr = new int[WidgetRun.b.values().length];
            f928a = iArr;
            try {
                iArr[WidgetRun.b.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f928a[WidgetRun.b.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f928a[WidgetRun.b.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public VerticalWidgetRun(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        DependencyNode dependencyNode = new DependencyNode(this);
        this.baseline = dependencyNode;
        this.e = null;
        this.start.b = DependencyNode.a.TOP;
        this.end.b = DependencyNode.a.BOTTOM;
        dependencyNode.b = DependencyNode.a.BASELINE;
        this.orientation = 1;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void a() {
        ConstraintWidget parent;
        ConstraintWidget parent2;
        ConstraintWidget constraintWidget = this.f931a;
        if (constraintWidget.measured) {
            this.c.resolve(constraintWidget.getHeight());
        }
        if (!this.c.resolved) {
            this.dimensionBehavior = this.f931a.getVerticalDimensionBehaviour();
            if (this.f931a.hasBaseline()) {
                this.e = new androidx.constraintlayout.core.widgets.analyzer.a(this);
            }
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.dimensionBehavior;
            if (dimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (parent2 = this.f931a.getParent()) != null && parent2.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.FIXED) {
                    int height = (parent2.getHeight() - this.f931a.mTop.getMargin()) - this.f931a.mBottom.getMargin();
                    addTarget(this.start, parent2.verticalRun.start, this.f931a.mTop.getMargin());
                    addTarget(this.end, parent2.verticalRun.end, -this.f931a.mBottom.getMargin());
                    this.c.resolve(height);
                    return;
                } else if (this.dimensionBehavior == ConstraintWidget.DimensionBehaviour.FIXED) {
                    this.c.resolve(this.f931a.getHeight());
                }
            }
        } else if (this.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (parent = this.f931a.getParent()) != null && parent.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.FIXED) {
            addTarget(this.start, parent.verticalRun.start, this.f931a.mTop.getMargin());
            addTarget(this.end, parent.verticalRun.end, -this.f931a.mBottom.getMargin());
            return;
        }
        b bVar = this.c;
        boolean z = bVar.resolved;
        if (z) {
            ConstraintWidget constraintWidget2 = this.f931a;
            if (constraintWidget2.measured) {
                ConstraintAnchor[] constraintAnchorArr = constraintWidget2.mListAnchors;
                if (constraintAnchorArr[2].mTarget != null && constraintAnchorArr[3].mTarget != null) {
                    if (constraintWidget2.isInVerticalChain()) {
                        this.start.c = this.f931a.mListAnchors[2].getMargin();
                        this.end.c = -this.f931a.mListAnchors[3].getMargin();
                    } else {
                        DependencyNode target = getTarget(this.f931a.mListAnchors[2]);
                        if (target != null) {
                            addTarget(this.start, target, this.f931a.mListAnchors[2].getMargin());
                        }
                        DependencyNode target2 = getTarget(this.f931a.mListAnchors[3]);
                        if (target2 != null) {
                            addTarget(this.end, target2, -this.f931a.mListAnchors[3].getMargin());
                        }
                        this.start.delegateToWidgetRun = true;
                        this.end.delegateToWidgetRun = true;
                    }
                    if (this.f931a.hasBaseline()) {
                        addTarget(this.baseline, this.start, this.f931a.getBaselineDistance());
                        return;
                    }
                    return;
                } else if (constraintAnchorArr[2].mTarget != null) {
                    DependencyNode target3 = getTarget(constraintAnchorArr[2]);
                    if (target3 != null) {
                        addTarget(this.start, target3, this.f931a.mListAnchors[2].getMargin());
                        addTarget(this.end, this.start, this.c.value);
                        if (this.f931a.hasBaseline()) {
                            addTarget(this.baseline, this.start, this.f931a.getBaselineDistance());
                            return;
                        }
                        return;
                    }
                    return;
                } else if (constraintAnchorArr[3].mTarget != null) {
                    DependencyNode target4 = getTarget(constraintAnchorArr[3]);
                    if (target4 != null) {
                        addTarget(this.end, target4, -this.f931a.mListAnchors[3].getMargin());
                        addTarget(this.start, this.end, -this.c.value);
                    }
                    if (this.f931a.hasBaseline()) {
                        addTarget(this.baseline, this.start, this.f931a.getBaselineDistance());
                        return;
                    }
                    return;
                } else if (constraintAnchorArr[4].mTarget != null) {
                    DependencyNode target5 = getTarget(constraintAnchorArr[4]);
                    if (target5 != null) {
                        addTarget(this.baseline, target5, 0);
                        addTarget(this.start, this.baseline, -this.f931a.getBaselineDistance());
                        addTarget(this.end, this.start, this.c.value);
                        return;
                    }
                    return;
                } else if ((constraintWidget2 instanceof Helper) || constraintWidget2.getParent() == null || this.f931a.getAnchor(ConstraintAnchor.Type.CENTER).mTarget != null) {
                    return;
                } else {
                    addTarget(this.start, this.f931a.getParent().verticalRun.start, this.f931a.getY());
                    addTarget(this.end, this.start, this.c.value);
                    if (this.f931a.hasBaseline()) {
                        addTarget(this.baseline, this.start, this.f931a.getBaselineDistance());
                        return;
                    }
                    return;
                }
            }
        }
        if (!z && this.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            ConstraintWidget constraintWidget3 = this.f931a;
            int i = constraintWidget3.mMatchConstraintDefaultHeight;
            if (i != 2) {
                if (i == 3 && !constraintWidget3.isInVerticalChain()) {
                    ConstraintWidget constraintWidget4 = this.f931a;
                    if (constraintWidget4.mMatchConstraintDefaultWidth != 3) {
                        b bVar2 = constraintWidget4.horizontalRun.c;
                        this.c.g.add(bVar2);
                        bVar2.f.add(this.c);
                        b bVar3 = this.c;
                        bVar3.delegateToWidgetRun = true;
                        bVar3.f.add(this.start);
                        this.c.f.add(this.end);
                    }
                }
            } else {
                ConstraintWidget parent3 = constraintWidget3.getParent();
                if (parent3 != null) {
                    b bVar4 = parent3.verticalRun.c;
                    this.c.g.add(bVar4);
                    bVar4.f.add(this.c);
                    b bVar5 = this.c;
                    bVar5.delegateToWidgetRun = true;
                    bVar5.f.add(this.start);
                    this.c.f.add(this.end);
                }
            }
        } else {
            bVar.addDependency(this);
        }
        ConstraintWidget constraintWidget5 = this.f931a;
        ConstraintAnchor[] constraintAnchorArr2 = constraintWidget5.mListAnchors;
        if (constraintAnchorArr2[2].mTarget != null && constraintAnchorArr2[3].mTarget != null) {
            if (constraintWidget5.isInVerticalChain()) {
                this.start.c = this.f931a.mListAnchors[2].getMargin();
                this.end.c = -this.f931a.mListAnchors[3].getMargin();
            } else {
                DependencyNode target6 = getTarget(this.f931a.mListAnchors[2]);
                DependencyNode target7 = getTarget(this.f931a.mListAnchors[3]);
                if (target6 != null) {
                    target6.addDependency(this);
                }
                if (target7 != null) {
                    target7.addDependency(this);
                }
                this.mRunType = WidgetRun.b.CENTER;
            }
            if (this.f931a.hasBaseline()) {
                addTarget(this.baseline, this.start, 1, this.e);
            }
        } else if (constraintAnchorArr2[2].mTarget != null) {
            DependencyNode target8 = getTarget(constraintAnchorArr2[2]);
            if (target8 != null) {
                addTarget(this.start, target8, this.f931a.mListAnchors[2].getMargin());
                addTarget(this.end, this.start, 1, this.c);
                if (this.f931a.hasBaseline()) {
                    addTarget(this.baseline, this.start, 1, this.e);
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = this.dimensionBehavior;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviour2 == dimensionBehaviour3 && this.f931a.getDimensionRatio() > 0.0f) {
                    HorizontalWidgetRun horizontalWidgetRun = this.f931a.horizontalRun;
                    if (horizontalWidgetRun.dimensionBehavior == dimensionBehaviour3) {
                        horizontalWidgetRun.c.f.add(this.c);
                        this.c.g.add(this.f931a.horizontalRun.c);
                        this.c.updateDelegate = this;
                    }
                }
            }
        } else if (constraintAnchorArr2[3].mTarget != null) {
            DependencyNode target9 = getTarget(constraintAnchorArr2[3]);
            if (target9 != null) {
                addTarget(this.end, target9, -this.f931a.mListAnchors[3].getMargin());
                addTarget(this.start, this.end, -1, this.c);
                if (this.f931a.hasBaseline()) {
                    addTarget(this.baseline, this.start, 1, this.e);
                }
            }
        } else if (constraintAnchorArr2[4].mTarget != null) {
            DependencyNode target10 = getTarget(constraintAnchorArr2[4]);
            if (target10 != null) {
                addTarget(this.baseline, target10, 0);
                addTarget(this.start, this.baseline, -1, this.e);
                addTarget(this.end, this.start, 1, this.c);
            }
        } else if (!(constraintWidget5 instanceof Helper) && constraintWidget5.getParent() != null) {
            addTarget(this.start, this.f931a.getParent().verticalRun.start, this.f931a.getY());
            addTarget(this.end, this.start, 1, this.c);
            if (this.f931a.hasBaseline()) {
                addTarget(this.baseline, this.start, 1, this.e);
            }
            ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = this.dimensionBehavior;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
            if (dimensionBehaviour4 == dimensionBehaviour5 && this.f931a.getDimensionRatio() > 0.0f) {
                HorizontalWidgetRun horizontalWidgetRun2 = this.f931a.horizontalRun;
                if (horizontalWidgetRun2.dimensionBehavior == dimensionBehaviour5) {
                    horizontalWidgetRun2.c.f.add(this.c);
                    this.c.g.add(this.f931a.horizontalRun.c);
                    this.c.updateDelegate = this;
                }
            }
        }
        if (this.c.g.size() == 0) {
            this.c.readyToSolve = true;
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        DependencyNode dependencyNode = this.start;
        if (dependencyNode.resolved) {
            this.f931a.setY(dependencyNode.value);
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void b() {
        this.b = null;
        this.start.clear();
        this.end.clear();
        this.baseline.clear();
        this.c.clear();
        this.d = false;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public boolean d() {
        return this.dimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.f931a.mMatchConstraintDefaultHeight == 0;
    }

    public void e() {
        this.d = false;
        this.start.clear();
        this.start.resolved = false;
        this.end.clear();
        this.end.resolved = false;
        this.baseline.clear();
        this.baseline.resolved = false;
        this.c.resolved = false;
    }

    public String toString() {
        return "VerticalRun " + this.f931a.getDebugName();
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    public void update(Dependency dependency) {
        b bVar;
        float f;
        float dimensionRatio;
        float f2;
        int i;
        ConstraintWidget constraintWidget;
        int i2 = a.f928a[this.mRunType.ordinal()];
        if (i2 == 1) {
            updateRunStart(dependency);
        } else if (i2 == 2) {
            updateRunEnd(dependency);
        } else if (i2 == 3) {
            ConstraintWidget constraintWidget2 = this.f931a;
            updateRunCenter(dependency, constraintWidget2.mTop, constraintWidget2.mBottom, 1);
            return;
        }
        b bVar2 = this.c;
        if (bVar2.readyToSolve && !bVar2.resolved && this.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            ConstraintWidget constraintWidget3 = this.f931a;
            int i3 = constraintWidget3.mMatchConstraintDefaultHeight;
            if (i3 != 2) {
                if (i3 == 3 && constraintWidget3.horizontalRun.c.resolved) {
                    int dimensionRatioSide = constraintWidget3.getDimensionRatioSide();
                    if (dimensionRatioSide == -1) {
                        ConstraintWidget constraintWidget4 = this.f931a;
                        f = constraintWidget4.horizontalRun.c.value;
                        dimensionRatio = constraintWidget4.getDimensionRatio();
                    } else if (dimensionRatioSide == 0) {
                        f2 = constraintWidget.horizontalRun.c.value * this.f931a.getDimensionRatio();
                        i = (int) (f2 + 0.5f);
                        this.c.resolve(i);
                    } else if (dimensionRatioSide == 1) {
                        ConstraintWidget constraintWidget5 = this.f931a;
                        f = constraintWidget5.horizontalRun.c.value;
                        dimensionRatio = constraintWidget5.getDimensionRatio();
                    } else {
                        i = 0;
                        this.c.resolve(i);
                    }
                    f2 = f / dimensionRatio;
                    i = (int) (f2 + 0.5f);
                    this.c.resolve(i);
                }
            } else {
                ConstraintWidget parent = constraintWidget3.getParent();
                if (parent != null) {
                    if (parent.verticalRun.c.resolved) {
                        this.c.resolve((int) ((bVar.value * this.f931a.mMatchConstraintPercentHeight) + 0.5f));
                    }
                }
            }
        }
        DependencyNode dependencyNode = this.start;
        if (dependencyNode.readyToSolve) {
            DependencyNode dependencyNode2 = this.end;
            if (dependencyNode2.readyToSolve) {
                if (dependencyNode.resolved && dependencyNode2.resolved && this.c.resolved) {
                    return;
                }
                if (!this.c.resolved && this.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    ConstraintWidget constraintWidget6 = this.f931a;
                    if (constraintWidget6.mMatchConstraintDefaultWidth == 0 && !constraintWidget6.isInVerticalChain()) {
                        int i4 = this.start.g.get(0).value;
                        DependencyNode dependencyNode3 = this.start;
                        int i5 = i4 + dependencyNode3.c;
                        int i6 = this.end.g.get(0).value + this.end.c;
                        dependencyNode3.resolve(i5);
                        this.end.resolve(i6);
                        this.c.resolve(i6 - i5);
                        return;
                    }
                }
                if (!this.c.resolved && this.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && this.matchConstraintsType == 1 && this.start.g.size() > 0 && this.end.g.size() > 0) {
                    int i7 = (this.end.g.get(0).value + this.end.c) - (this.start.g.get(0).value + this.start.c);
                    b bVar3 = this.c;
                    int i8 = bVar3.h;
                    if (i7 < i8) {
                        bVar3.resolve(i7);
                    } else {
                        bVar3.resolve(i8);
                    }
                }
                if (this.c.resolved && this.start.g.size() > 0 && this.end.g.size() > 0) {
                    DependencyNode dependencyNode4 = this.start.g.get(0);
                    DependencyNode dependencyNode5 = this.end.g.get(0);
                    int i9 = dependencyNode4.value + this.start.c;
                    int i10 = dependencyNode5.value + this.end.c;
                    float verticalBiasPercent = this.f931a.getVerticalBiasPercent();
                    if (dependencyNode4 == dependencyNode5) {
                        i9 = dependencyNode4.value;
                        i10 = dependencyNode5.value;
                        verticalBiasPercent = 0.5f;
                    }
                    this.start.resolve((int) (i9 + 0.5f + (((i10 - i9) - this.c.value) * verticalBiasPercent)));
                    this.end.resolve(this.start.value + this.c.value);
                }
            }
        }
    }
}
