package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
/* loaded from: classes.dex */
public abstract class WidgetRun implements Dependency {

    /* renamed from: a  reason: collision with root package name */
    public ConstraintWidget f931a;
    public e b;
    public ConstraintWidget.DimensionBehaviour dimensionBehavior;
    public int matchConstraintsType;
    public androidx.constraintlayout.core.widgets.analyzer.b c = new androidx.constraintlayout.core.widgets.analyzer.b(this);
    public int orientation = 0;
    public boolean d = false;
    public DependencyNode start = new DependencyNode(this);
    public DependencyNode end = new DependencyNode(this);
    public b mRunType = b.NONE;

    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f932a;

        static {
            int[] iArr = new int[ConstraintAnchor.Type.values().length];
            f932a = iArr;
            try {
                iArr[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f932a[ConstraintAnchor.Type.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f932a[ConstraintAnchor.Type.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f932a[ConstraintAnchor.Type.BASELINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f932a[ConstraintAnchor.Type.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* loaded from: classes.dex */
    public enum b {
        NONE,
        START,
        END,
        CENTER
    }

    public WidgetRun(ConstraintWidget constraintWidget) {
        this.f931a = constraintWidget;
    }

    public abstract void a();

    public final void addTarget(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i) {
        dependencyNode.g.add(dependencyNode2);
        dependencyNode.c = i;
        dependencyNode2.f.add(dependencyNode);
    }

    public abstract void applyToWidget();

    public abstract void b();

    public final void c(int i, int i2) {
        int i3;
        int i4 = this.matchConstraintsType;
        if (i4 == 0) {
            this.c.resolve(getLimitedDimension(i2, i));
        } else if (i4 == 1) {
            this.c.resolve(Math.min(getLimitedDimension(this.c.h, i), i2));
        } else if (i4 == 2) {
            ConstraintWidget parent = this.f931a.getParent();
            if (parent != null) {
                androidx.constraintlayout.core.widgets.analyzer.b bVar = (i == 0 ? parent.horizontalRun : parent.verticalRun).c;
                if (bVar.resolved) {
                    ConstraintWidget constraintWidget = this.f931a;
                    this.c.resolve(getLimitedDimension((int) ((bVar.value * (i == 0 ? constraintWidget.mMatchConstraintPercentWidth : constraintWidget.mMatchConstraintPercentHeight)) + 0.5f), i));
                }
            }
        } else if (i4 != 3) {
        } else {
            ConstraintWidget constraintWidget2 = this.f931a;
            WidgetRun widgetRun = constraintWidget2.horizontalRun;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = widgetRun.dimensionBehavior;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
            if (dimensionBehaviour == dimensionBehaviour2 && widgetRun.matchConstraintsType == 3) {
                VerticalWidgetRun verticalWidgetRun = constraintWidget2.verticalRun;
                if (verticalWidgetRun.dimensionBehavior == dimensionBehaviour2 && verticalWidgetRun.matchConstraintsType == 3) {
                    return;
                }
            }
            if (i == 0) {
                widgetRun = constraintWidget2.verticalRun;
            }
            if (widgetRun.c.resolved) {
                float dimensionRatio = constraintWidget2.getDimensionRatio();
                if (i == 1) {
                    i3 = (int) ((widgetRun.c.value / dimensionRatio) + 0.5f);
                } else {
                    i3 = (int) ((dimensionRatio * widgetRun.c.value) + 0.5f);
                }
                this.c.resolve(i3);
            }
        }
    }

    public abstract boolean d();

    public final int getLimitedDimension(int i, int i2) {
        int max;
        if (i2 == 0) {
            ConstraintWidget constraintWidget = this.f931a;
            int i3 = constraintWidget.mMatchConstraintMaxWidth;
            max = Math.max(constraintWidget.mMatchConstraintMinWidth, i);
            if (i3 > 0) {
                max = Math.min(i3, i);
            }
            if (max == i) {
                return i;
            }
        } else {
            ConstraintWidget constraintWidget2 = this.f931a;
            int i4 = constraintWidget2.mMatchConstraintMaxHeight;
            max = Math.max(constraintWidget2.mMatchConstraintMinHeight, i);
            if (i4 > 0) {
                max = Math.min(i4, i);
            }
            if (max == i) {
                return i;
            }
        }
        return max;
    }

    public final DependencyNode getTarget(ConstraintAnchor constraintAnchor) {
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.mOwner;
        int i = a.f932a[constraintAnchor2.mType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i != 5) {
                            return null;
                        }
                        return constraintWidget.verticalRun.end;
                    }
                    return constraintWidget.verticalRun.baseline;
                }
                return constraintWidget.verticalRun.start;
            }
            return constraintWidget.horizontalRun.end;
        }
        return constraintWidget.horizontalRun.start;
    }

    public long getWrapDimension() {
        androidx.constraintlayout.core.widgets.analyzer.b bVar = this.c;
        if (bVar.resolved) {
            return bVar.value;
        }
        return 0L;
    }

    public boolean isCenterConnection() {
        int size = this.start.g.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            if (this.start.g.get(i2).f925a != this) {
                i++;
            }
        }
        int size2 = this.end.g.size();
        for (int i3 = 0; i3 < size2; i3++) {
            if (this.end.g.get(i3).f925a != this) {
                i++;
            }
        }
        return i >= 2;
    }

    public boolean isDimensionResolved() {
        return this.c.resolved;
    }

    public boolean isResolved() {
        return this.d;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.Dependency
    public void update(Dependency dependency) {
    }

    public void updateRunCenter(Dependency dependency, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i) {
        DependencyNode target = getTarget(constraintAnchor);
        DependencyNode target2 = getTarget(constraintAnchor2);
        if (target.resolved && target2.resolved) {
            int margin = target.value + constraintAnchor.getMargin();
            int margin2 = target2.value - constraintAnchor2.getMargin();
            int i2 = margin2 - margin;
            if (!this.c.resolved && this.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                c(i, i2);
            }
            androidx.constraintlayout.core.widgets.analyzer.b bVar = this.c;
            if (bVar.resolved) {
                if (bVar.value == i2) {
                    this.start.resolve(margin);
                    this.end.resolve(margin2);
                    return;
                }
                ConstraintWidget constraintWidget = this.f931a;
                float horizontalBiasPercent = i == 0 ? constraintWidget.getHorizontalBiasPercent() : constraintWidget.getVerticalBiasPercent();
                if (target == target2) {
                    margin = target.value;
                    margin2 = target2.value;
                    horizontalBiasPercent = 0.5f;
                }
                this.start.resolve((int) (margin + 0.5f + (((margin2 - margin) - this.c.value) * horizontalBiasPercent)));
                this.end.resolve(this.start.value + this.c.value);
            }
        }
    }

    public void updateRunEnd(Dependency dependency) {
    }

    public void updateRunStart(Dependency dependency) {
    }

    public long wrapSize(int i) {
        int i2;
        androidx.constraintlayout.core.widgets.analyzer.b bVar = this.c;
        if (bVar.resolved) {
            long j = bVar.value;
            if (isCenterConnection()) {
                i2 = this.start.c - this.end.c;
            } else if (i == 0) {
                i2 = this.start.c;
            } else {
                return j - this.end.c;
            }
            return j + i2;
        }
        return 0L;
    }

    public final void addTarget(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i, androidx.constraintlayout.core.widgets.analyzer.b bVar) {
        dependencyNode.g.add(dependencyNode2);
        dependencyNode.g.add(this.c);
        dependencyNode.d = i;
        dependencyNode.e = bVar;
        dependencyNode2.f.add(dependencyNode);
        bVar.f.add(dependencyNode);
    }

    public final DependencyNode getTarget(ConstraintAnchor constraintAnchor, int i) {
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.mOwner;
        WidgetRun widgetRun = i == 0 ? constraintWidget.horizontalRun : constraintWidget.verticalRun;
        int i2 = a.f932a[constraintAnchor2.mType.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 5) {
                        return null;
                    }
                }
            }
            return widgetRun.end;
        }
        return widgetRun.start;
    }
}
