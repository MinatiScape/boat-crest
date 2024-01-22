package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes.dex */
public class ChainRun extends WidgetRun {
    public ArrayList<WidgetRun> e;
    public int f;

    public ChainRun(ConstraintWidget constraintWidget, int i) {
        super(constraintWidget);
        this.e = new ArrayList<>();
        this.orientation = i;
        e();
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void a() {
        Iterator<WidgetRun> it = this.e.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        int size = this.e.size();
        if (size < 1) {
            return;
        }
        ConstraintWidget constraintWidget = this.e.get(0).f931a;
        ConstraintWidget constraintWidget2 = this.e.get(size - 1).f931a;
        if (this.orientation == 0) {
            ConstraintAnchor constraintAnchor = constraintWidget.mLeft;
            ConstraintAnchor constraintAnchor2 = constraintWidget2.mRight;
            DependencyNode target = getTarget(constraintAnchor, 0);
            int margin = constraintAnchor.getMargin();
            ConstraintWidget f = f();
            if (f != null) {
                margin = f.mLeft.getMargin();
            }
            if (target != null) {
                addTarget(this.start, target, margin);
            }
            DependencyNode target2 = getTarget(constraintAnchor2, 0);
            int margin2 = constraintAnchor2.getMargin();
            ConstraintWidget g = g();
            if (g != null) {
                margin2 = g.mRight.getMargin();
            }
            if (target2 != null) {
                addTarget(this.end, target2, -margin2);
            }
        } else {
            ConstraintAnchor constraintAnchor3 = constraintWidget.mTop;
            ConstraintAnchor constraintAnchor4 = constraintWidget2.mBottom;
            DependencyNode target3 = getTarget(constraintAnchor3, 1);
            int margin3 = constraintAnchor3.getMargin();
            ConstraintWidget f2 = f();
            if (f2 != null) {
                margin3 = f2.mTop.getMargin();
            }
            if (target3 != null) {
                addTarget(this.start, target3, margin3);
            }
            DependencyNode target4 = getTarget(constraintAnchor4, 1);
            int margin4 = constraintAnchor4.getMargin();
            ConstraintWidget g2 = g();
            if (g2 != null) {
                margin4 = g2.mBottom.getMargin();
            }
            if (target4 != null) {
                addTarget(this.end, target4, -margin4);
            }
        }
        this.start.updateDelegate = this;
        this.end.updateDelegate = this;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        for (int i = 0; i < this.e.size(); i++) {
            this.e.get(i).applyToWidget();
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void b() {
        this.b = null;
        Iterator<WidgetRun> it = this.e.iterator();
        while (it.hasNext()) {
            it.next().b();
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public boolean d() {
        int size = this.e.size();
        for (int i = 0; i < size; i++) {
            if (!this.e.get(i).d()) {
                return false;
            }
        }
        return true;
    }

    public final void e() {
        ConstraintWidget constraintWidget;
        ConstraintWidget constraintWidget2 = this.f931a;
        ConstraintWidget previousChainMember = constraintWidget2.getPreviousChainMember(this.orientation);
        while (true) {
            ConstraintWidget constraintWidget3 = previousChainMember;
            constraintWidget = constraintWidget2;
            constraintWidget2 = constraintWidget3;
            if (constraintWidget2 == null) {
                break;
            }
            previousChainMember = constraintWidget2.getPreviousChainMember(this.orientation);
        }
        this.f931a = constraintWidget;
        this.e.add(constraintWidget.getRun(this.orientation));
        ConstraintWidget nextChainMember = constraintWidget.getNextChainMember(this.orientation);
        while (nextChainMember != null) {
            this.e.add(nextChainMember.getRun(this.orientation));
            nextChainMember = nextChainMember.getNextChainMember(this.orientation);
        }
        Iterator<WidgetRun> it = this.e.iterator();
        while (it.hasNext()) {
            WidgetRun next = it.next();
            int i = this.orientation;
            if (i == 0) {
                next.f931a.horizontalChainRun = this;
            } else if (i == 1) {
                next.f931a.verticalChainRun = this;
            }
        }
        if ((this.orientation == 0 && ((ConstraintWidgetContainer) this.f931a.getParent()).isRtl()) && this.e.size() > 1) {
            ArrayList<WidgetRun> arrayList = this.e;
            this.f931a = arrayList.get(arrayList.size() - 1).f931a;
        }
        this.f = this.orientation == 0 ? this.f931a.getHorizontalChainStyle() : this.f931a.getVerticalChainStyle();
    }

    public final ConstraintWidget f() {
        for (int i = 0; i < this.e.size(); i++) {
            WidgetRun widgetRun = this.e.get(i);
            if (widgetRun.f931a.getVisibility() != 8) {
                return widgetRun.f931a;
            }
        }
        return null;
    }

    public final ConstraintWidget g() {
        for (int size = this.e.size() - 1; size >= 0; size--) {
            WidgetRun widgetRun = this.e.get(size);
            if (widgetRun.f931a.getVisibility() != 8) {
                return widgetRun.f931a;
            }
        }
        return null;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public long getWrapDimension() {
        int size = this.e.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            WidgetRun widgetRun = this.e.get(i);
            j = j + widgetRun.start.c + widgetRun.getWrapDimension() + widgetRun.end.c;
        }
        return j;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ChainRun ");
        sb.append(this.orientation == 0 ? "horizontal : " : "vertical : ");
        Iterator<WidgetRun> it = this.e.iterator();
        while (it.hasNext()) {
            sb.append("<");
            sb.append(it.next());
            sb.append("> ");
        }
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:271:0x0400, code lost:
        r7 = r7 - r10;
     */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00e9  */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void update(androidx.constraintlayout.core.widgets.analyzer.Dependency r27) {
        /*
            Method dump skipped, instructions count: 1064
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.ChainRun.update(androidx.constraintlayout.core.widgets.analyzer.Dependency):void");
    }
}
