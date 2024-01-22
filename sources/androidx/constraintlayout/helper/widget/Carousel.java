package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.motion.widget.MotionHelper;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.motion.widget.MotionScene;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.R;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class Carousel extends MotionHelper {
    public static final int TOUCH_UP_CARRY_ON = 2;
    public static final int TOUCH_UP_IMMEDIATE_STOP = 1;
    public float A;
    public int B;
    public int C;
    public Runnable D;
    public Adapter l;
    public final ArrayList<View> m;
    public int n;
    public int o;
    public MotionLayout p;
    public int q;
    public boolean r;
    public int s;
    public int t;
    public int u;
    public int v;
    public float w;
    public int x;
    public int y;
    public int z;

    /* loaded from: classes.dex */
    public interface Adapter {
        int count();

        void onNewItem(int i);

        void populate(View view, int i);
    }

    /* loaded from: classes.dex */
    public class a implements Runnable {

        /* renamed from: androidx.constraintlayout.helper.widget.Carousel$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class RunnableC0125a implements Runnable {
            public final /* synthetic */ float h;

            public RunnableC0125a(float f) {
                this.h = f;
            }

            @Override // java.lang.Runnable
            public void run() {
                Carousel.this.p.touchAnimateTo(5, 1.0f, this.h);
            }
        }

        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Carousel.this.p.setProgress(0.0f);
            Carousel.this.s();
            Carousel.this.l.onNewItem(Carousel.this.o);
            float velocity = Carousel.this.p.getVelocity();
            if (Carousel.this.z != 2 || velocity <= Carousel.this.A || Carousel.this.o >= Carousel.this.l.count() - 1) {
                return;
            }
            float f = velocity * Carousel.this.w;
            if (Carousel.this.o != 0 || Carousel.this.n <= Carousel.this.o) {
                if (Carousel.this.o != Carousel.this.l.count() - 1 || Carousel.this.n >= Carousel.this.o) {
                    Carousel.this.p.post(new RunnableC0125a(f));
                }
            }
        }
    }

    public Carousel(Context context) {
        super(context);
        this.l = null;
        this.m = new ArrayList<>();
        this.n = 0;
        this.o = 0;
        this.q = -1;
        this.r = false;
        this.s = -1;
        this.t = -1;
        this.u = -1;
        this.v = -1;
        this.w = 0.9f;
        this.x = 0;
        this.y = 4;
        this.z = 1;
        this.A = 2.0f;
        this.B = -1;
        this.C = 200;
        this.D = new a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void r() {
        this.p.setTransitionDuration(this.C);
        if (this.B < this.o) {
            this.p.transitionToState(this.u, this.C);
        } else {
            this.p.transitionToState(this.v, this.C);
        }
    }

    public int getCount() {
        Adapter adapter = this.l;
        if (adapter != null) {
            return adapter.count();
        }
        return 0;
    }

    public int getCurrentIndex() {
        return this.o;
    }

    public void jumpToIndex(int i) {
        this.o = Math.max(0, Math.min(getCount() - 1, i));
        refresh();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    @RequiresApi(api = 17)
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getParent() instanceof MotionLayout) {
            MotionLayout motionLayout = (MotionLayout) getParent();
            for (int i = 0; i < this.mCount; i++) {
                int i2 = this.mIds[i];
                View viewById = motionLayout.getViewById(i2);
                if (this.q == i2) {
                    this.x = i;
                }
                this.m.add(viewById);
            }
            this.p = motionLayout;
            if (this.z == 2) {
                MotionScene.Transition transition = motionLayout.getTransition(this.t);
                if (transition != null) {
                    transition.setOnTouchUp(5);
                }
                MotionScene.Transition transition2 = this.p.getTransition(this.s);
                if (transition2 != null) {
                    transition2.setOnTouchUp(5);
                }
            }
            s();
        }
    }

    @Override // androidx.constraintlayout.motion.widget.MotionHelper, androidx.constraintlayout.motion.widget.MotionLayout.TransitionListener
    public void onTransitionChange(MotionLayout motionLayout, int i, int i2, float f) {
    }

    @Override // androidx.constraintlayout.motion.widget.MotionHelper, androidx.constraintlayout.motion.widget.MotionLayout.TransitionListener
    public void onTransitionCompleted(MotionLayout motionLayout, int i) {
        int i2 = this.o;
        this.n = i2;
        if (i == this.v) {
            this.o = i2 + 1;
        } else if (i == this.u) {
            this.o = i2 - 1;
        }
        if (this.r) {
            if (this.o >= this.l.count()) {
                this.o = 0;
            }
            if (this.o < 0) {
                this.o = this.l.count() - 1;
            }
        } else {
            if (this.o >= this.l.count()) {
                this.o = this.l.count() - 1;
            }
            if (this.o < 0) {
                this.o = 0;
            }
        }
        if (this.n != this.o) {
            this.p.post(this.D);
        }
    }

    public final boolean p(int i, boolean z) {
        MotionLayout motionLayout;
        MotionScene.Transition transition;
        if (i == -1 || (motionLayout = this.p) == null || (transition = motionLayout.getTransition(i)) == null || z == transition.isEnabled()) {
            return false;
        }
        transition.setEnabled(z);
        return true;
    }

    public final void q(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Carousel);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == R.styleable.Carousel_carousel_firstView) {
                    this.q = obtainStyledAttributes.getResourceId(index, this.q);
                } else if (index == R.styleable.Carousel_carousel_backwardTransition) {
                    this.s = obtainStyledAttributes.getResourceId(index, this.s);
                } else if (index == R.styleable.Carousel_carousel_forwardTransition) {
                    this.t = obtainStyledAttributes.getResourceId(index, this.t);
                } else if (index == R.styleable.Carousel_carousel_emptyViewsBehavior) {
                    this.y = obtainStyledAttributes.getInt(index, this.y);
                } else if (index == R.styleable.Carousel_carousel_previousState) {
                    this.u = obtainStyledAttributes.getResourceId(index, this.u);
                } else if (index == R.styleable.Carousel_carousel_nextState) {
                    this.v = obtainStyledAttributes.getResourceId(index, this.v);
                } else if (index == R.styleable.Carousel_carousel_touchUp_dampeningFactor) {
                    this.w = obtainStyledAttributes.getFloat(index, this.w);
                } else if (index == R.styleable.Carousel_carousel_touchUpMode) {
                    this.z = obtainStyledAttributes.getInt(index, this.z);
                } else if (index == R.styleable.Carousel_carousel_touchUp_velocityThreshold) {
                    this.A = obtainStyledAttributes.getFloat(index, this.A);
                } else if (index == R.styleable.Carousel_carousel_infinite) {
                    this.r = obtainStyledAttributes.getBoolean(index, this.r);
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    public void refresh() {
        int size = this.m.size();
        for (int i = 0; i < size; i++) {
            View view = this.m.get(i);
            if (this.l.count() == 0) {
                u(view, this.y);
            } else {
                u(view, 0);
            }
        }
        this.p.rebuildScene();
        s();
    }

    public final void s() {
        Adapter adapter = this.l;
        if (adapter == null || this.p == null || adapter.count() == 0) {
            return;
        }
        int size = this.m.size();
        for (int i = 0; i < size; i++) {
            View view = this.m.get(i);
            int i2 = (this.o + i) - this.x;
            if (this.r) {
                if (i2 < 0) {
                    int i3 = this.y;
                    if (i3 != 4) {
                        u(view, i3);
                    } else {
                        u(view, 0);
                    }
                    if (i2 % this.l.count() == 0) {
                        this.l.populate(view, 0);
                    } else {
                        Adapter adapter2 = this.l;
                        adapter2.populate(view, adapter2.count() + (i2 % this.l.count()));
                    }
                } else if (i2 >= this.l.count()) {
                    if (i2 == this.l.count()) {
                        i2 = 0;
                    } else if (i2 > this.l.count()) {
                        i2 %= this.l.count();
                    }
                    int i4 = this.y;
                    if (i4 != 4) {
                        u(view, i4);
                    } else {
                        u(view, 0);
                    }
                    this.l.populate(view, i2);
                } else {
                    u(view, 0);
                    this.l.populate(view, i2);
                }
            } else if (i2 < 0) {
                u(view, this.y);
            } else if (i2 >= this.l.count()) {
                u(view, this.y);
            } else {
                u(view, 0);
                this.l.populate(view, i2);
            }
        }
        int i5 = this.B;
        if (i5 != -1 && i5 != this.o) {
            this.p.post(new Runnable() { // from class: androidx.constraintlayout.helper.widget.a
                @Override // java.lang.Runnable
                public final void run() {
                    Carousel.this.r();
                }
            });
        } else if (i5 == this.o) {
            this.B = -1;
        }
        if (this.s != -1 && this.t != -1) {
            if (this.r) {
                return;
            }
            int count = this.l.count();
            if (this.o == 0) {
                p(this.s, false);
            } else {
                p(this.s, true);
                this.p.setTransition(this.s);
            }
            if (this.o == count - 1) {
                p(this.t, false);
                return;
            }
            p(this.t, true);
            this.p.setTransition(this.t);
            return;
        }
        Log.w("Carousel", "No backward or forward transitions defined for Carousel!");
    }

    public void setAdapter(Adapter adapter) {
        this.l = adapter;
    }

    public final boolean t(int i, View view, int i2) {
        ConstraintSet.Constraint constraint;
        ConstraintSet constraintSet = this.p.getConstraintSet(i);
        if (constraintSet == null || (constraint = constraintSet.getConstraint(view.getId())) == null) {
            return false;
        }
        constraint.propertySet.mVisibilityMode = 1;
        view.setVisibility(i2);
        return true;
    }

    public void transitionToIndex(int i, int i2) {
        this.B = Math.max(0, Math.min(getCount() - 1, i));
        int max = Math.max(0, i2);
        this.C = max;
        this.p.setTransitionDuration(max);
        if (i < this.o) {
            this.p.transitionToState(this.u, this.C);
        } else {
            this.p.transitionToState(this.v, this.C);
        }
    }

    public final boolean u(View view, int i) {
        MotionLayout motionLayout = this.p;
        if (motionLayout == null) {
            return false;
        }
        boolean z = false;
        for (int i2 : motionLayout.getConstraintSetIds()) {
            z |= t(i2, view, i);
        }
        return z;
    }

    public Carousel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.l = null;
        this.m = new ArrayList<>();
        this.n = 0;
        this.o = 0;
        this.q = -1;
        this.r = false;
        this.s = -1;
        this.t = -1;
        this.u = -1;
        this.v = -1;
        this.w = 0.9f;
        this.x = 0;
        this.y = 4;
        this.z = 1;
        this.A = 2.0f;
        this.B = -1;
        this.C = 200;
        this.D = new a();
        q(context, attributeSet);
    }

    public Carousel(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.l = null;
        this.m = new ArrayList<>();
        this.n = 0;
        this.o = 0;
        this.q = -1;
        this.r = false;
        this.s = -1;
        this.t = -1;
        this.u = -1;
        this.v = -1;
        this.w = 0.9f;
        this.x = 0;
        this.y = 4;
        this.z = 1;
        this.A = 2.0f;
        this.B = -1;
        this.C = 200;
        this.D = new a();
        q(context, attributeSet);
    }
}
