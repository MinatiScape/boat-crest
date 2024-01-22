package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.Helper;
import androidx.constraintlayout.core.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.core.widgets.analyzer.WidgetRun;
/* loaded from: classes.dex */
public class HorizontalWidgetRun extends WidgetRun {
    public static int[] e = new int[2];

    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f927a;

        static {
            int[] iArr = new int[WidgetRun.b.values().length];
            f927a = iArr;
            try {
                iArr[WidgetRun.b.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f927a[WidgetRun.b.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f927a[WidgetRun.b.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public HorizontalWidgetRun(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        this.start.b = DependencyNode.a.LEFT;
        this.end.b = DependencyNode.a.RIGHT;
        this.orientation = 0;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void a() {
        ConstraintWidget parent;
        ConstraintWidget parent2;
        ConstraintWidget constraintWidget = this.f931a;
        if (constraintWidget.measured) {
            this.c.resolve(constraintWidget.getWidth());
        }
        if (!this.c.resolved) {
            ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = this.f931a.getHorizontalDimensionBehaviour();
            this.dimensionBehavior = horizontalDimensionBehaviour;
            if (horizontalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                if (horizontalDimensionBehaviour == dimensionBehaviour && (parent2 = this.f931a.getParent()) != null && (parent2.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.FIXED || parent2.getHorizontalDimensionBehaviour() == dimensionBehaviour)) {
                    int width = (parent2.getWidth() - this.f931a.mLeft.getMargin()) - this.f931a.mRight.getMargin();
                    addTarget(this.start, parent2.horizontalRun.start, this.f931a.mLeft.getMargin());
                    addTarget(this.end, parent2.horizontalRun.end, -this.f931a.mRight.getMargin());
                    this.c.resolve(width);
                    return;
                } else if (this.dimensionBehavior == ConstraintWidget.DimensionBehaviour.FIXED) {
                    this.c.resolve(this.f931a.getWidth());
                }
            }
        } else {
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = this.dimensionBehavior;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
            if (dimensionBehaviour2 == dimensionBehaviour3 && (parent = this.f931a.getParent()) != null && (parent.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.FIXED || parent.getHorizontalDimensionBehaviour() == dimensionBehaviour3)) {
                addTarget(this.start, parent.horizontalRun.start, this.f931a.mLeft.getMargin());
                addTarget(this.end, parent.horizontalRun.end, -this.f931a.mRight.getMargin());
                return;
            }
        }
        b bVar = this.c;
        if (bVar.resolved) {
            ConstraintWidget constraintWidget2 = this.f931a;
            if (constraintWidget2.measured) {
                ConstraintAnchor[] constraintAnchorArr = constraintWidget2.mListAnchors;
                if (constraintAnchorArr[0].mTarget != null && constraintAnchorArr[1].mTarget != null) {
                    if (constraintWidget2.isInHorizontalChain()) {
                        this.start.c = this.f931a.mListAnchors[0].getMargin();
                        this.end.c = -this.f931a.mListAnchors[1].getMargin();
                        return;
                    }
                    DependencyNode target = getTarget(this.f931a.mListAnchors[0]);
                    if (target != null) {
                        addTarget(this.start, target, this.f931a.mListAnchors[0].getMargin());
                    }
                    DependencyNode target2 = getTarget(this.f931a.mListAnchors[1]);
                    if (target2 != null) {
                        addTarget(this.end, target2, -this.f931a.mListAnchors[1].getMargin());
                    }
                    this.start.delegateToWidgetRun = true;
                    this.end.delegateToWidgetRun = true;
                    return;
                } else if (constraintAnchorArr[0].mTarget != null) {
                    DependencyNode target3 = getTarget(constraintAnchorArr[0]);
                    if (target3 != null) {
                        addTarget(this.start, target3, this.f931a.mListAnchors[0].getMargin());
                        addTarget(this.end, this.start, this.c.value);
                        return;
                    }
                    return;
                } else if (constraintAnchorArr[1].mTarget != null) {
                    DependencyNode target4 = getTarget(constraintAnchorArr[1]);
                    if (target4 != null) {
                        addTarget(this.end, target4, -this.f931a.mListAnchors[1].getMargin());
                        addTarget(this.start, this.end, -this.c.value);
                        return;
                    }
                    return;
                } else if ((constraintWidget2 instanceof Helper) || constraintWidget2.getParent() == null || this.f931a.getAnchor(ConstraintAnchor.Type.CENTER).mTarget != null) {
                    return;
                } else {
                    addTarget(this.start, this.f931a.getParent().horizontalRun.start, this.f931a.getX());
                    addTarget(this.end, this.start, this.c.value);
                    return;
                }
            }
        }
        if (this.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            ConstraintWidget constraintWidget3 = this.f931a;
            int i = constraintWidget3.mMatchConstraintDefaultWidth;
            if (i == 2) {
                ConstraintWidget parent3 = constraintWidget3.getParent();
                if (parent3 != null) {
                    b bVar2 = parent3.verticalRun.c;
                    this.c.g.add(bVar2);
                    bVar2.f.add(this.c);
                    b bVar3 = this.c;
                    bVar3.delegateToWidgetRun = true;
                    bVar3.f.add(this.start);
                    this.c.f.add(this.end);
                }
            } else if (i == 3) {
                if (constraintWidget3.mMatchConstraintDefaultHeight == 3) {
                    this.start.updateDelegate = this;
                    this.end.updateDelegate = this;
                    VerticalWidgetRun verticalWidgetRun = constraintWidget3.verticalRun;
                    verticalWidgetRun.start.updateDelegate = this;
                    verticalWidgetRun.end.updateDelegate = this;
                    bVar.updateDelegate = this;
                    if (constraintWidget3.isInVerticalChain()) {
                        this.c.g.add(this.f931a.verticalRun.c);
                        this.f931a.verticalRun.c.f.add(this.c);
                        VerticalWidgetRun verticalWidgetRun2 = this.f931a.verticalRun;
                        verticalWidgetRun2.c.updateDelegate = this;
                        this.c.g.add(verticalWidgetRun2.start);
                        this.c.g.add(this.f931a.verticalRun.end);
                        this.f931a.verticalRun.start.f.add(this.c);
                        this.f931a.verticalRun.end.f.add(this.c);
                    } else if (this.f931a.isInHorizontalChain()) {
                        this.f931a.verticalRun.c.g.add(this.c);
                        this.c.f.add(this.f931a.verticalRun.c);
                    } else {
                        this.f931a.verticalRun.c.g.add(this.c);
                    }
                } else {
                    b bVar4 = constraintWidget3.verticalRun.c;
                    bVar.g.add(bVar4);
                    bVar4.f.add(this.c);
                    this.f931a.verticalRun.start.f.add(this.c);
                    this.f931a.verticalRun.end.f.add(this.c);
                    b bVar5 = this.c;
                    bVar5.delegateToWidgetRun = true;
                    bVar5.f.add(this.start);
                    this.c.f.add(this.end);
                    this.start.g.add(this.c);
                    this.end.g.add(this.c);
                }
            }
        }
        ConstraintWidget constraintWidget4 = this.f931a;
        ConstraintAnchor[] constraintAnchorArr2 = constraintWidget4.mListAnchors;
        if (constraintAnchorArr2[0].mTarget != null && constraintAnchorArr2[1].mTarget != null) {
            if (constraintWidget4.isInHorizontalChain()) {
                this.start.c = this.f931a.mListAnchors[0].getMargin();
                this.end.c = -this.f931a.mListAnchors[1].getMargin();
                return;
            }
            DependencyNode target5 = getTarget(this.f931a.mListAnchors[0]);
            DependencyNode target6 = getTarget(this.f931a.mListAnchors[1]);
            if (target5 != null) {
                target5.addDependency(this);
            }
            if (target6 != null) {
                target6.addDependency(this);
            }
            this.mRunType = WidgetRun.b.CENTER;
        } else if (constraintAnchorArr2[0].mTarget != null) {
            DependencyNode target7 = getTarget(constraintAnchorArr2[0]);
            if (target7 != null) {
                addTarget(this.start, target7, this.f931a.mListAnchors[0].getMargin());
                addTarget(this.end, this.start, 1, this.c);
            }
        } else if (constraintAnchorArr2[1].mTarget != null) {
            DependencyNode target8 = getTarget(constraintAnchorArr2[1]);
            if (target8 != null) {
                addTarget(this.end, target8, -this.f931a.mListAnchors[1].getMargin());
                addTarget(this.start, this.end, -1, this.c);
            }
        } else if ((constraintWidget4 instanceof Helper) || constraintWidget4.getParent() == null) {
        } else {
            addTarget(this.start, this.f931a.getParent().horizontalRun.start, this.f931a.getX());
            addTarget(this.end, this.start, 1, this.c);
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        DependencyNode dependencyNode = this.start;
        if (dependencyNode.resolved) {
            this.f931a.setX(dependencyNode.value);
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void b() {
        this.b = null;
        this.start.clear();
        this.end.clear();
        this.c.clear();
        this.d = false;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public boolean d() {
        return this.dimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.f931a.mMatchConstraintDefaultWidth == 0;
    }

    public final void e(int[] iArr, int i, int i2, int i3, int i4, float f, int i5) {
        int i6 = i2 - i;
        int i7 = i4 - i3;
        if (i5 != -1) {
            if (i5 == 0) {
                iArr[0] = (int) ((i7 * f) + 0.5f);
                iArr[1] = i7;
                return;
            } else if (i5 != 1) {
                return;
            } else {
                iArr[0] = i6;
                iArr[1] = (int) ((i6 * f) + 0.5f);
                return;
            }
        }
        int i8 = (int) ((i7 * f) + 0.5f);
        int i9 = (int) ((i6 / f) + 0.5f);
        if (i8 <= i6) {
            iArr[0] = i8;
            iArr[1] = i7;
        } else if (i9 <= i7) {
            iArr[0] = i6;
            iArr[1] = i9;
        }
    }

    public void f() {
        this.d = false;
        this.start.clear();
        this.start.resolved = false;
        this.end.clear();
        this.end.resolved = false;
        this.c.resolved = false;
    }

    public String toString() {
        return "HorizontalRun " + this.f931a.getDebugName();
    }

    /* JADX WARN: Code restructure failed: missing block: B:119:0x02b9, code lost:
        if (r14 != 1) goto L131;
     */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void update(androidx.constraintlayout.core.widgets.analyzer.Dependency r17) {
        /*
            Method dump skipped, instructions count: 1087
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun.update(androidx.constraintlayout.core.widgets.analyzer.Dependency):void");
    }
}
