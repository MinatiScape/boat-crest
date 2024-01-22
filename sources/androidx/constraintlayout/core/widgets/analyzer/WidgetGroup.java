package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.Chain;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes.dex */
public class WidgetGroup {
    public static int g;
    public int b;
    public int d;

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<ConstraintWidget> f929a = new ArrayList<>();
    public boolean c = false;
    public ArrayList<a> e = null;
    public int f = -1;

    /* loaded from: classes.dex */
    public class a {

        /* renamed from: a  reason: collision with root package name */
        public WeakReference<ConstraintWidget> f930a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;

        public a(WidgetGroup widgetGroup, ConstraintWidget constraintWidget, LinearSystem linearSystem, int i) {
            this.f930a = new WeakReference<>(constraintWidget);
            this.b = linearSystem.getObjectVariableValue(constraintWidget.mLeft);
            this.c = linearSystem.getObjectVariableValue(constraintWidget.mTop);
            this.d = linearSystem.getObjectVariableValue(constraintWidget.mRight);
            this.e = linearSystem.getObjectVariableValue(constraintWidget.mBottom);
            this.f = linearSystem.getObjectVariableValue(constraintWidget.mBaseline);
            this.g = i;
        }

        public void a() {
            ConstraintWidget constraintWidget = this.f930a.get();
            if (constraintWidget != null) {
                constraintWidget.setFinalFrame(this.b, this.c, this.d, this.e, this.f, this.g);
            }
        }
    }

    public WidgetGroup(int i) {
        this.b = -1;
        this.d = 0;
        int i2 = g;
        g = i2 + 1;
        this.b = i2;
        this.d = i;
    }

    public final boolean a(ConstraintWidget constraintWidget) {
        return this.f929a.contains(constraintWidget);
    }

    public boolean add(ConstraintWidget constraintWidget) {
        if (this.f929a.contains(constraintWidget)) {
            return false;
        }
        this.f929a.add(constraintWidget);
        return true;
    }

    public void apply() {
        if (this.e != null && this.c) {
            for (int i = 0; i < this.e.size(); i++) {
                this.e.get(i).a();
            }
        }
    }

    public final String b() {
        int i = this.d;
        return i == 0 ? "Horizontal" : i == 1 ? "Vertical" : i == 2 ? "Both" : "Unknown";
    }

    public final int c(LinearSystem linearSystem, ArrayList<ConstraintWidget> arrayList, int i) {
        int objectVariableValue;
        int objectVariableValue2;
        ConstraintWidgetContainer constraintWidgetContainer = (ConstraintWidgetContainer) arrayList.get(0).getParent();
        linearSystem.reset();
        constraintWidgetContainer.addToSolver(linearSystem, false);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            arrayList.get(i2).addToSolver(linearSystem, false);
        }
        if (i == 0 && constraintWidgetContainer.mHorizontalChainsSize > 0) {
            Chain.applyChainConstraints(constraintWidgetContainer, linearSystem, arrayList, 0);
        }
        if (i == 1 && constraintWidgetContainer.mVerticalChainsSize > 0) {
            Chain.applyChainConstraints(constraintWidgetContainer, linearSystem, arrayList, 1);
        }
        try {
            linearSystem.minimize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.e = new ArrayList<>();
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            this.e.add(new a(this, arrayList.get(i3), linearSystem, i));
        }
        if (i == 0) {
            objectVariableValue = linearSystem.getObjectVariableValue(constraintWidgetContainer.mLeft);
            objectVariableValue2 = linearSystem.getObjectVariableValue(constraintWidgetContainer.mRight);
            linearSystem.reset();
        } else {
            objectVariableValue = linearSystem.getObjectVariableValue(constraintWidgetContainer.mTop);
            objectVariableValue2 = linearSystem.getObjectVariableValue(constraintWidgetContainer.mBottom);
            linearSystem.reset();
        }
        return objectVariableValue2 - objectVariableValue;
    }

    public void cleanup(ArrayList<WidgetGroup> arrayList) {
        int size = this.f929a.size();
        if (this.f != -1 && size > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                WidgetGroup widgetGroup = arrayList.get(i);
                if (this.f == widgetGroup.b) {
                    moveTo(this.d, widgetGroup);
                }
            }
        }
        if (size == 0) {
            arrayList.remove(this);
        }
    }

    public void clear() {
        this.f929a.clear();
    }

    public int getId() {
        return this.b;
    }

    public int getOrientation() {
        return this.d;
    }

    public boolean intersectWith(WidgetGroup widgetGroup) {
        for (int i = 0; i < this.f929a.size(); i++) {
            if (widgetGroup.a(this.f929a.get(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean isAuthoritative() {
        return this.c;
    }

    public int measureWrap(LinearSystem linearSystem, int i) {
        if (this.f929a.size() == 0) {
            return 0;
        }
        return c(linearSystem, this.f929a, i);
    }

    public void moveTo(int i, WidgetGroup widgetGroup) {
        Iterator<ConstraintWidget> it = this.f929a.iterator();
        while (it.hasNext()) {
            ConstraintWidget next = it.next();
            widgetGroup.add(next);
            if (i == 0) {
                next.horizontalGroup = widgetGroup.getId();
            } else {
                next.verticalGroup = widgetGroup.getId();
            }
        }
        this.f = widgetGroup.b;
    }

    public void setAuthoritative(boolean z) {
        this.c = z;
    }

    public void setOrientation(int i) {
        this.d = i;
    }

    public int size() {
        return this.f929a.size();
    }

    public String toString() {
        Iterator<ConstraintWidget> it;
        String str = b() + " [" + this.b + "] <";
        while (this.f929a.iterator().hasNext()) {
            str = str + HexStringBuilder.DEFAULT_SEPARATOR + it.next().getDebugName();
        }
        return str + " >";
    }
}
