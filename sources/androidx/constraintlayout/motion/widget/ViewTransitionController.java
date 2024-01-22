package androidx.constraintlayout.motion.widget;

import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.constraintlayout.motion.widget.ViewTransition;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.SharedValues;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
/* loaded from: classes.dex */
public class ViewTransitionController {

    /* renamed from: a  reason: collision with root package name */
    public final MotionLayout f961a;
    public HashSet<View> c;
    public ArrayList<ViewTransition.b> e;
    public ArrayList<ViewTransition> b = new ArrayList<>();
    public String d = "ViewTransitionController";
    public ArrayList<ViewTransition.b> f = new ArrayList<>();

    /* loaded from: classes.dex */
    public class a implements SharedValues.SharedValuesListener {
        public final /* synthetic */ ViewTransition h;
        public final /* synthetic */ int i;
        public final /* synthetic */ boolean j;
        public final /* synthetic */ int k;

        public a(ViewTransition viewTransition, int i, boolean z, int i2) {
            this.h = viewTransition;
            this.i = i;
            this.j = z;
            this.k = i2;
        }

        @Override // androidx.constraintlayout.widget.SharedValues.SharedValuesListener
        public void onNewValue(int i, int i2, int i3) {
            int sharedValueCurrent = this.h.getSharedValueCurrent();
            this.h.setSharedValueCurrent(i2);
            if (this.i != i || sharedValueCurrent == i2) {
                return;
            }
            if (this.j) {
                if (this.k == i2) {
                    int childCount = ViewTransitionController.this.f961a.getChildCount();
                    for (int i4 = 0; i4 < childCount; i4++) {
                        View childAt = ViewTransitionController.this.f961a.getChildAt(i4);
                        if (this.h.i(childAt)) {
                            int currentState = ViewTransitionController.this.f961a.getCurrentState();
                            ConstraintSet constraintSet = ViewTransitionController.this.f961a.getConstraintSet(currentState);
                            ViewTransition viewTransition = this.h;
                            ViewTransitionController viewTransitionController = ViewTransitionController.this;
                            viewTransition.c(viewTransitionController, viewTransitionController.f961a, currentState, constraintSet, childAt);
                        }
                    }
                }
            } else if (this.k != i2) {
                int childCount2 = ViewTransitionController.this.f961a.getChildCount();
                for (int i5 = 0; i5 < childCount2; i5++) {
                    View childAt2 = ViewTransitionController.this.f961a.getChildAt(i5);
                    if (this.h.i(childAt2)) {
                        int currentState2 = ViewTransitionController.this.f961a.getCurrentState();
                        ConstraintSet constraintSet2 = ViewTransitionController.this.f961a.getConstraintSet(currentState2);
                        ViewTransition viewTransition2 = this.h;
                        ViewTransitionController viewTransitionController2 = ViewTransitionController.this;
                        viewTransition2.c(viewTransitionController2, viewTransitionController2.f961a, currentState2, constraintSet2, childAt2);
                    }
                }
            }
        }
    }

    public ViewTransitionController(MotionLayout motionLayout) {
        this.f961a = motionLayout;
    }

    public void add(ViewTransition viewTransition) {
        this.b.add(viewTransition);
        this.c = null;
        if (viewTransition.getStateTransition() == 4) {
            h(viewTransition, true);
        } else if (viewTransition.getStateTransition() == 5) {
            h(viewTransition, false);
        }
    }

    public void b(ViewTransition.b bVar) {
        if (this.e == null) {
            this.e = new ArrayList<>();
        }
        this.e.add(bVar);
    }

    public void c() {
        ArrayList<ViewTransition.b> arrayList = this.e;
        if (arrayList == null) {
            return;
        }
        Iterator<ViewTransition.b> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.e.removeAll(this.f);
        this.f.clear();
        if (this.e.isEmpty()) {
            this.e = null;
        }
    }

    public boolean d(int i, MotionController motionController) {
        Iterator<ViewTransition> it = this.b.iterator();
        while (it.hasNext()) {
            ViewTransition next = it.next();
            if (next.e() == i) {
                next.f.addAllFrames(motionController);
                return true;
            }
        }
        return false;
    }

    public void e(int i, boolean z) {
        Iterator<ViewTransition> it = this.b.iterator();
        while (it.hasNext()) {
            ViewTransition next = it.next();
            if (next.e() == i) {
                next.k(z);
                return;
            }
        }
    }

    public void f() {
        this.f961a.invalidate();
    }

    public boolean g(int i) {
        Iterator<ViewTransition> it = this.b.iterator();
        while (it.hasNext()) {
            ViewTransition next = it.next();
            if (next.e() == i) {
                return next.g();
            }
        }
        return false;
    }

    public final void h(ViewTransition viewTransition, boolean z) {
        ConstraintLayout.getSharedValues().addListener(viewTransition.getSharedValueID(), new a(viewTransition, viewTransition.getSharedValueID(), z, viewTransition.getSharedValue()));
    }

    public void i(ViewTransition.b bVar) {
        this.f.add(bVar);
    }

    public void j(MotionEvent motionEvent) {
        ViewTransition viewTransition;
        int currentState = this.f961a.getCurrentState();
        if (currentState == -1) {
            return;
        }
        if (this.c == null) {
            this.c = new HashSet<>();
            Iterator<ViewTransition> it = this.b.iterator();
            while (it.hasNext()) {
                ViewTransition next = it.next();
                int childCount = this.f961a.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = this.f961a.getChildAt(i);
                    if (next.i(childAt)) {
                        childAt.getId();
                        this.c.add(childAt);
                    }
                }
            }
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        Rect rect = new Rect();
        int action = motionEvent.getAction();
        ArrayList<ViewTransition.b> arrayList = this.e;
        if (arrayList != null && !arrayList.isEmpty()) {
            Iterator<ViewTransition.b> it2 = this.e.iterator();
            while (it2.hasNext()) {
                it2.next().d(action, x, y);
            }
        }
        if (action == 0 || action == 1) {
            ConstraintSet constraintSet = this.f961a.getConstraintSet(currentState);
            Iterator<ViewTransition> it3 = this.b.iterator();
            while (it3.hasNext()) {
                ViewTransition next2 = it3.next();
                if (next2.l(action)) {
                    Iterator<View> it4 = this.c.iterator();
                    while (it4.hasNext()) {
                        View next3 = it4.next();
                        if (next2.i(next3)) {
                            next3.getHitRect(rect);
                            if (rect.contains((int) x, (int) y)) {
                                viewTransition = next2;
                                next2.c(this, this.f961a, currentState, constraintSet, next3);
                            } else {
                                viewTransition = next2;
                            }
                            next2 = viewTransition;
                        }
                    }
                }
            }
        }
    }

    public void k(int i, View... viewArr) {
        ArrayList arrayList = new ArrayList();
        Iterator<ViewTransition> it = this.b.iterator();
        ViewTransition viewTransition = null;
        while (it.hasNext()) {
            ViewTransition next = it.next();
            if (next.e() == i) {
                for (View view : viewArr) {
                    if (next.d(view)) {
                        arrayList.add(view);
                    }
                }
                if (!arrayList.isEmpty()) {
                    l(next, (View[]) arrayList.toArray(new View[0]));
                    arrayList.clear();
                }
                viewTransition = next;
            }
        }
        if (viewTransition == null) {
            Log.e(this.d, " Could not find ViewTransition");
        }
    }

    public final void l(ViewTransition viewTransition, View... viewArr) {
        int currentState = this.f961a.getCurrentState();
        if (viewTransition.e == 2) {
            viewTransition.c(this, this.f961a, currentState, null, viewArr);
        } else if (currentState == -1) {
            String str = this.d;
            Log.w(str, "No support for ViewTransition within transition yet. Currently: " + this.f961a.toString());
        } else {
            ConstraintSet constraintSet = this.f961a.getConstraintSet(currentState);
            if (constraintSet == null) {
                return;
            }
            viewTransition.c(this, this.f961a, currentState, constraintSet, viewArr);
        }
    }
}
