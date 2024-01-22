package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes.dex */
public class e {
    public static int c;

    /* renamed from: a  reason: collision with root package name */
    public WidgetRun f933a;
    public ArrayList<WidgetRun> b = new ArrayList<>();

    public e(WidgetRun widgetRun, int i) {
        this.f933a = null;
        c++;
        this.f933a = widgetRun;
    }

    public void a(WidgetRun widgetRun) {
        this.b.add(widgetRun);
    }

    public long b(ConstraintWidgetContainer constraintWidgetContainer, int i) {
        long wrapDimension;
        int i2;
        WidgetRun widgetRun = this.f933a;
        if (widgetRun instanceof ChainRun) {
            if (((ChainRun) widgetRun).orientation != i) {
                return 0L;
            }
        } else if (i == 0) {
            if (!(widgetRun instanceof HorizontalWidgetRun)) {
                return 0L;
            }
        } else if (!(widgetRun instanceof VerticalWidgetRun)) {
            return 0L;
        }
        DependencyNode dependencyNode = (i == 0 ? constraintWidgetContainer.horizontalRun : constraintWidgetContainer.verticalRun).start;
        DependencyNode dependencyNode2 = (i == 0 ? constraintWidgetContainer.horizontalRun : constraintWidgetContainer.verticalRun).end;
        boolean contains = widgetRun.start.g.contains(dependencyNode);
        boolean contains2 = this.f933a.end.g.contains(dependencyNode2);
        long wrapDimension2 = this.f933a.getWrapDimension();
        if (contains && contains2) {
            long f = f(this.f933a.start, 0L);
            long e = e(this.f933a.end, 0L);
            long j = f - wrapDimension2;
            WidgetRun widgetRun2 = this.f933a;
            int i3 = widgetRun2.end.c;
            if (j >= (-i3)) {
                j += i3;
            }
            int i4 = widgetRun2.start.c;
            long j2 = ((-e) - wrapDimension2) - i4;
            if (j2 >= i4) {
                j2 -= i4;
            }
            float biasPercent = widgetRun2.f931a.getBiasPercent(i);
            float f2 = (float) (biasPercent > 0.0f ? (((float) j2) / biasPercent) + (((float) j) / (1.0f - biasPercent)) : 0L);
            long j3 = (f2 * biasPercent) + 0.5f + wrapDimension2 + (f2 * (1.0f - biasPercent)) + 0.5f;
            WidgetRun widgetRun3 = this.f933a;
            wrapDimension = widgetRun3.start.c + j3;
            i2 = widgetRun3.end.c;
        } else if (contains) {
            DependencyNode dependencyNode3 = this.f933a.start;
            return Math.max(f(dependencyNode3, dependencyNode3.c), this.f933a.start.c + wrapDimension2);
        } else if (contains2) {
            DependencyNode dependencyNode4 = this.f933a.end;
            return Math.max(-e(dependencyNode4, dependencyNode4.c), (-this.f933a.end.c) + wrapDimension2);
        } else {
            WidgetRun widgetRun4 = this.f933a;
            wrapDimension = widgetRun4.start.c + widgetRun4.getWrapDimension();
            i2 = this.f933a.end.c;
        }
        return wrapDimension - i2;
    }

    public final boolean c(WidgetRun widgetRun, int i) {
        DependencyNode dependencyNode;
        WidgetRun widgetRun2;
        DependencyNode dependencyNode2;
        WidgetRun widgetRun3;
        if (widgetRun.f931a.isTerminalWidget[i]) {
            for (Dependency dependency : widgetRun.start.f) {
                if ((dependency instanceof DependencyNode) && (widgetRun3 = (dependencyNode2 = (DependencyNode) dependency).f925a) != widgetRun && dependencyNode2 == widgetRun3.start) {
                    if (widgetRun instanceof ChainRun) {
                        Iterator<WidgetRun> it = ((ChainRun) widgetRun).e.iterator();
                        while (it.hasNext()) {
                            c(it.next(), i);
                        }
                    } else if (!(widgetRun instanceof d)) {
                        widgetRun.f931a.isTerminalWidget[i] = false;
                    }
                    c(dependencyNode2.f925a, i);
                }
            }
            for (Dependency dependency2 : widgetRun.end.f) {
                if ((dependency2 instanceof DependencyNode) && (widgetRun2 = (dependencyNode = (DependencyNode) dependency2).f925a) != widgetRun && dependencyNode == widgetRun2.start) {
                    if (widgetRun instanceof ChainRun) {
                        Iterator<WidgetRun> it2 = ((ChainRun) widgetRun).e.iterator();
                        while (it2.hasNext()) {
                            c(it2.next(), i);
                        }
                    } else if (!(widgetRun instanceof d)) {
                        widgetRun.f931a.isTerminalWidget[i] = false;
                    }
                    c(dependencyNode.f925a, i);
                }
            }
            return false;
        }
        return false;
    }

    public void d(boolean z, boolean z2) {
        if (z) {
            WidgetRun widgetRun = this.f933a;
            if (widgetRun instanceof HorizontalWidgetRun) {
                c(widgetRun, 0);
            }
        }
        if (z2) {
            WidgetRun widgetRun2 = this.f933a;
            if (widgetRun2 instanceof VerticalWidgetRun) {
                c(widgetRun2, 1);
            }
        }
    }

    public final long e(DependencyNode dependencyNode, long j) {
        WidgetRun widgetRun = dependencyNode.f925a;
        if (widgetRun instanceof d) {
            return j;
        }
        int size = dependencyNode.f.size();
        long j2 = j;
        for (int i = 0; i < size; i++) {
            Dependency dependency = dependencyNode.f.get(i);
            if (dependency instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dependency;
                if (dependencyNode2.f925a != widgetRun) {
                    j2 = Math.min(j2, e(dependencyNode2, dependencyNode2.c + j));
                }
            }
        }
        if (dependencyNode == widgetRun.end) {
            long wrapDimension = j - widgetRun.getWrapDimension();
            return Math.min(Math.min(j2, e(widgetRun.start, wrapDimension)), wrapDimension - widgetRun.start.c);
        }
        return j2;
    }

    public final long f(DependencyNode dependencyNode, long j) {
        WidgetRun widgetRun = dependencyNode.f925a;
        if (widgetRun instanceof d) {
            return j;
        }
        int size = dependencyNode.f.size();
        long j2 = j;
        for (int i = 0; i < size; i++) {
            Dependency dependency = dependencyNode.f.get(i);
            if (dependency instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dependency;
                if (dependencyNode2.f925a != widgetRun) {
                    j2 = Math.max(j2, f(dependencyNode2, dependencyNode2.c + j));
                }
            }
        }
        if (dependencyNode == widgetRun.start) {
            long wrapDimension = j + widgetRun.getWrapDimension();
            return Math.max(Math.max(j2, f(widgetRun.end, wrapDimension)), wrapDimension - widgetRun.end.c);
        }
        return j2;
    }
}
