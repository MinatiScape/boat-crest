package androidx.constraintlayout.core.state.helpers;

import androidx.constraintlayout.core.state.Reference;
import androidx.constraintlayout.core.state.State;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.Guideline;
/* loaded from: classes.dex */
public class GuidelineReference implements Facade, Reference {

    /* renamed from: a  reason: collision with root package name */
    public final State f912a;
    public int b;
    public Guideline c;
    public int d = -1;
    public int e = -1;
    public float f = 0.0f;
    public Object g;

    public GuidelineReference(State state) {
        this.f912a = state;
    }

    @Override // androidx.constraintlayout.core.state.helpers.Facade, androidx.constraintlayout.core.state.Reference
    public void apply() {
        this.c.setOrientation(this.b);
        int i = this.d;
        if (i != -1) {
            this.c.setGuideBegin(i);
            return;
        }
        int i2 = this.e;
        if (i2 != -1) {
            this.c.setGuideEnd(i2);
        } else {
            this.c.setGuidePercent(this.f);
        }
    }

    public GuidelineReference end(Object obj) {
        this.d = -1;
        this.e = this.f912a.convertDimension(obj);
        this.f = 0.0f;
        return this;
    }

    @Override // androidx.constraintlayout.core.state.helpers.Facade, androidx.constraintlayout.core.state.Reference
    public ConstraintWidget getConstraintWidget() {
        if (this.c == null) {
            this.c = new Guideline();
        }
        return this.c;
    }

    @Override // androidx.constraintlayout.core.state.Reference
    public Facade getFacade() {
        return null;
    }

    @Override // androidx.constraintlayout.core.state.Reference
    public Object getKey() {
        return this.g;
    }

    public int getOrientation() {
        return this.b;
    }

    public GuidelineReference percent(float f) {
        this.d = -1;
        this.e = -1;
        this.f = f;
        return this;
    }

    @Override // androidx.constraintlayout.core.state.Reference
    public void setConstraintWidget(ConstraintWidget constraintWidget) {
        if (constraintWidget instanceof Guideline) {
            this.c = (Guideline) constraintWidget;
        } else {
            this.c = null;
        }
    }

    @Override // androidx.constraintlayout.core.state.Reference
    public void setKey(Object obj) {
        this.g = obj;
    }

    public void setOrientation(int i) {
        this.b = i;
    }

    public GuidelineReference start(Object obj) {
        this.d = this.f912a.convertDimension(obj);
        this.e = -1;
        this.f = 0.0f;
        return this;
    }
}
