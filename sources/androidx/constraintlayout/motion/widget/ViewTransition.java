package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.motion.widget.MotionScene;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public class ViewTransition {
    public static final String CONSTRAINT_OVERRIDE = "ConstraintOverride";
    public static final String CUSTOM_ATTRIBUTE = "CustomAttribute";
    public static final String CUSTOM_METHOD = "CustomMethod";
    public static final String KEY_FRAME_SET_TAG = "KeyFrameSet";
    public static final int ONSTATE_ACTION_DOWN = 1;
    public static final int ONSTATE_ACTION_DOWN_UP = 3;
    public static final int ONSTATE_ACTION_UP = 2;
    public static final int ONSTATE_SHARED_VALUE_SET = 4;
    public static final int ONSTATE_SHARED_VALUE_UNSET = 5;
    public static final String VIEW_TRANSITION_TAG = "ViewTransition";
    public static String w = "ViewTransition";

    /* renamed from: a  reason: collision with root package name */
    public int f958a;
    public int e;
    public KeyFrames f;
    public ConstraintSet.Constraint g;
    public int j;
    public String k;
    public Context o;
    public int b = -1;
    public boolean c = false;
    public int d = 0;
    public int h = -1;
    public int i = -1;
    public int l = 0;
    public String m = null;
    public int n = -1;
    public int p = -1;
    public int q = -1;
    public int r = -1;
    public int s = -1;
    public int t = -1;
    public int u = -1;
    public int v = -1;

    /* loaded from: classes.dex */
    public class a implements Interpolator {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Easing f959a;

        public a(ViewTransition viewTransition, Easing easing) {
            this.f959a = easing;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            return (float) this.f959a.get(f);
        }
    }

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public final int f960a;
        public final int b;
        public long c;
        public MotionController d;
        public int e;
        public ViewTransitionController g;
        public Interpolator h;
        public float j;
        public float k;
        public long l;
        public boolean n;
        public KeyCache f = new KeyCache();
        public boolean i = false;
        public Rect m = new Rect();

        public b(ViewTransitionController viewTransitionController, MotionController motionController, int i, int i2, int i3, Interpolator interpolator, int i4, int i5) {
            this.n = false;
            this.g = viewTransitionController;
            this.d = motionController;
            this.e = i2;
            long nanoTime = System.nanoTime();
            this.c = nanoTime;
            this.l = nanoTime;
            this.g.b(this);
            this.h = interpolator;
            this.f960a = i4;
            this.b = i5;
            if (i3 == 3) {
                this.n = true;
            }
            this.k = i == 0 ? Float.MAX_VALUE : 1.0f / i;
            a();
        }

        public void a() {
            if (this.i) {
                c();
            } else {
                b();
            }
        }

        public void b() {
            long nanoTime = System.nanoTime();
            this.l = nanoTime;
            float f = this.j + (((float) ((nanoTime - this.l) * 1.0E-6d)) * this.k);
            this.j = f;
            if (f >= 1.0f) {
                this.j = 1.0f;
            }
            Interpolator interpolator = this.h;
            float interpolation = interpolator == null ? this.j : interpolator.getInterpolation(this.j);
            MotionController motionController = this.d;
            boolean q = motionController.q(motionController.b, interpolation, nanoTime, this.f);
            if (this.j >= 1.0f) {
                if (this.f960a != -1) {
                    this.d.getView().setTag(this.f960a, Long.valueOf(System.nanoTime()));
                }
                if (this.b != -1) {
                    this.d.getView().setTag(this.b, null);
                }
                if (!this.n) {
                    this.g.i(this);
                }
            }
            if (this.j < 1.0f || q) {
                this.g.f();
            }
        }

        public void c() {
            long nanoTime = System.nanoTime();
            this.l = nanoTime;
            float f = this.j - (((float) ((nanoTime - this.l) * 1.0E-6d)) * this.k);
            this.j = f;
            if (f < 0.0f) {
                this.j = 0.0f;
            }
            Interpolator interpolator = this.h;
            float interpolation = interpolator == null ? this.j : interpolator.getInterpolation(this.j);
            MotionController motionController = this.d;
            boolean q = motionController.q(motionController.b, interpolation, nanoTime, this.f);
            if (this.j <= 0.0f) {
                if (this.f960a != -1) {
                    this.d.getView().setTag(this.f960a, Long.valueOf(System.nanoTime()));
                }
                if (this.b != -1) {
                    this.d.getView().setTag(this.b, null);
                }
                this.g.i(this);
            }
            if (this.j > 0.0f || q) {
                this.g.f();
            }
        }

        public void d(int i, float f, float f2) {
            if (i == 1) {
                if (this.i) {
                    return;
                }
                e(true);
            } else if (i != 2) {
            } else {
                this.d.getView().getHitRect(this.m);
                if (this.m.contains((int) f, (int) f2) || this.i) {
                    return;
                }
                e(true);
            }
        }

        public void e(boolean z) {
            int i;
            this.i = z;
            if (z && (i = this.e) != -1) {
                this.k = i == 0 ? Float.MAX_VALUE : 1.0f / i;
            }
            this.g.f();
            this.l = System.nanoTime();
        }
    }

    public ViewTransition(Context context, XmlPullParser xmlPullParser) {
        boolean z;
        this.o = context;
        try {
            int eventType = xmlPullParser.getEventType();
            while (eventType != 1) {
                if (eventType == 2) {
                    String name = xmlPullParser.getName();
                    switch (name.hashCode()) {
                        case -1962203927:
                            if (name.equals(CONSTRAINT_OVERRIDE)) {
                                z = true;
                                break;
                            }
                            z = true;
                            break;
                        case -1239391468:
                            if (name.equals(KEY_FRAME_SET_TAG)) {
                                z = true;
                                break;
                            }
                            z = true;
                            break;
                        case 61998586:
                            if (name.equals(VIEW_TRANSITION_TAG)) {
                                z = false;
                                break;
                            }
                            z = true;
                            break;
                        case 366511058:
                            if (name.equals(CUSTOM_METHOD)) {
                                z = true;
                                break;
                            }
                            z = true;
                            break;
                        case 1791837707:
                            if (name.equals(CUSTOM_ATTRIBUTE)) {
                                z = true;
                                break;
                            }
                            z = true;
                            break;
                        default:
                            z = true;
                            break;
                    }
                    if (!z) {
                        j(context, xmlPullParser);
                    } else if (z) {
                        this.f = new KeyFrames(context, xmlPullParser);
                    } else if (z) {
                        this.g = ConstraintSet.buildDelta(context, xmlPullParser);
                    } else if (!z && !z) {
                        Log.e(w, Debug.getLoc() + " unknown tag " + name);
                        Log.e(w, ".xml:" + xmlPullParser.getLineNumber());
                    } else {
                        ConstraintAttribute.parse(context, xmlPullParser, this.g.mCustomConstraints);
                    }
                } else if (eventType != 3) {
                    continue;
                } else if (VIEW_TRANSITION_TAG.equals(xmlPullParser.getName())) {
                    return;
                }
                eventType = xmlPullParser.next();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(View[] viewArr) {
        if (this.p != -1) {
            for (View view : viewArr) {
                view.setTag(this.p, Long.valueOf(System.nanoTime()));
            }
        }
        if (this.q != -1) {
            for (View view2 : viewArr) {
                view2.setTag(this.q, null);
            }
        }
    }

    public void b(ViewTransitionController viewTransitionController, MotionLayout motionLayout, View view) {
        MotionController motionController = new MotionController(view);
        motionController.u(view);
        this.f.addAllFrames(motionController);
        motionController.setup(motionLayout.getWidth(), motionLayout.getHeight(), this.h, System.nanoTime());
        new b(viewTransitionController, motionController, this.h, this.i, this.b, f(motionLayout.getContext()), this.p, this.q);
    }

    public void c(ViewTransitionController viewTransitionController, MotionLayout motionLayout, int i, ConstraintSet constraintSet, final View... viewArr) {
        int[] constraintSetIds;
        if (this.c) {
            return;
        }
        int i2 = this.e;
        if (i2 == 2) {
            b(viewTransitionController, motionLayout, viewArr[0]);
            return;
        }
        if (i2 == 1) {
            for (int i3 : motionLayout.getConstraintSetIds()) {
                if (i3 != i) {
                    ConstraintSet constraintSet2 = motionLayout.getConstraintSet(i3);
                    for (View view : viewArr) {
                        ConstraintSet.Constraint constraint = constraintSet2.getConstraint(view.getId());
                        ConstraintSet.Constraint constraint2 = this.g;
                        if (constraint2 != null) {
                            constraint2.applyDelta(constraint);
                            constraint.mCustomConstraints.putAll(this.g.mCustomConstraints);
                        }
                    }
                }
            }
        }
        ConstraintSet constraintSet3 = new ConstraintSet();
        constraintSet3.clone(constraintSet);
        for (View view2 : viewArr) {
            ConstraintSet.Constraint constraint3 = constraintSet3.getConstraint(view2.getId());
            ConstraintSet.Constraint constraint4 = this.g;
            if (constraint4 != null) {
                constraint4.applyDelta(constraint3);
                constraint3.mCustomConstraints.putAll(this.g.mCustomConstraints);
            }
        }
        motionLayout.updateState(i, constraintSet3);
        int i4 = R.id.view_transition;
        motionLayout.updateState(i4, constraintSet);
        motionLayout.setState(i4, -1, -1);
        MotionScene.Transition transition = new MotionScene.Transition(-1, motionLayout.h, i4, i);
        for (View view3 : viewArr) {
            m(transition, view3);
        }
        motionLayout.setTransition(transition);
        motionLayout.transitionToEnd(new Runnable() { // from class: androidx.constraintlayout.motion.widget.e
            @Override // java.lang.Runnable
            public final void run() {
                ViewTransition.this.h(viewArr);
            }
        });
    }

    public boolean d(View view) {
        int i = this.r;
        boolean z = i == -1 || view.getTag(i) != null;
        int i2 = this.s;
        return z && (i2 == -1 || view.getTag(i2) == null);
    }

    public int e() {
        return this.f958a;
    }

    public Interpolator f(Context context) {
        int i = this.l;
        if (i != -2) {
            if (i != -1) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 4) {
                                if (i != 5) {
                                    if (i != 6) {
                                        return null;
                                    }
                                    return new AnticipateInterpolator();
                                }
                                return new OvershootInterpolator();
                            }
                            return new BounceInterpolator();
                        }
                        return new DecelerateInterpolator();
                    }
                    return new AccelerateInterpolator();
                }
                return new AccelerateDecelerateInterpolator();
            }
            return new a(this, Easing.getInterpolator(this.m));
        }
        return AnimationUtils.loadInterpolator(context, this.n);
    }

    public boolean g() {
        return !this.c;
    }

    public int getSharedValue() {
        return this.t;
    }

    public int getSharedValueCurrent() {
        return this.v;
    }

    public int getSharedValueID() {
        return this.u;
    }

    public int getStateTransition() {
        return this.b;
    }

    public boolean i(View view) {
        String str;
        if (view == null) {
            return false;
        }
        if (!(this.j == -1 && this.k == null) && d(view)) {
            if (view.getId() == this.j) {
                return true;
            }
            return this.k != null && (view.getLayoutParams() instanceof ConstraintLayout.LayoutParams) && (str = ((ConstraintLayout.LayoutParams) view.getLayoutParams()).constraintTag) != null && str.matches(this.k);
        }
        return false;
    }

    public final void j(Context context, XmlPullParser xmlPullParser) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.ViewTransition);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = obtainStyledAttributes.getIndex(i);
            if (index == R.styleable.ViewTransition_android_id) {
                this.f958a = obtainStyledAttributes.getResourceId(index, this.f958a);
            } else if (index == R.styleable.ViewTransition_motionTarget) {
                if (MotionLayout.IS_IN_EDIT_MODE) {
                    int resourceId = obtainStyledAttributes.getResourceId(index, this.j);
                    this.j = resourceId;
                    if (resourceId == -1) {
                        this.k = obtainStyledAttributes.getString(index);
                    }
                } else if (obtainStyledAttributes.peekValue(index).type == 3) {
                    this.k = obtainStyledAttributes.getString(index);
                } else {
                    this.j = obtainStyledAttributes.getResourceId(index, this.j);
                }
            } else if (index == R.styleable.ViewTransition_onStateTransition) {
                this.b = obtainStyledAttributes.getInt(index, this.b);
            } else if (index == R.styleable.ViewTransition_transitionDisable) {
                this.c = obtainStyledAttributes.getBoolean(index, this.c);
            } else if (index == R.styleable.ViewTransition_pathMotionArc) {
                this.d = obtainStyledAttributes.getInt(index, this.d);
            } else if (index == R.styleable.ViewTransition_duration) {
                this.h = obtainStyledAttributes.getInt(index, this.h);
            } else if (index == R.styleable.ViewTransition_upDuration) {
                this.i = obtainStyledAttributes.getInt(index, this.i);
            } else if (index == R.styleable.ViewTransition_viewTransitionMode) {
                this.e = obtainStyledAttributes.getInt(index, this.e);
            } else if (index == R.styleable.ViewTransition_motionInterpolator) {
                int i2 = obtainStyledAttributes.peekValue(index).type;
                if (i2 == 1) {
                    int resourceId2 = obtainStyledAttributes.getResourceId(index, -1);
                    this.n = resourceId2;
                    if (resourceId2 != -1) {
                        this.l = -2;
                    }
                } else if (i2 == 3) {
                    String string = obtainStyledAttributes.getString(index);
                    this.m = string;
                    if (string != null && string.indexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR) > 0) {
                        this.n = obtainStyledAttributes.getResourceId(index, -1);
                        this.l = -2;
                    } else {
                        this.l = -1;
                    }
                } else {
                    this.l = obtainStyledAttributes.getInteger(index, this.l);
                }
            } else if (index == R.styleable.ViewTransition_setsTag) {
                this.p = obtainStyledAttributes.getResourceId(index, this.p);
            } else if (index == R.styleable.ViewTransition_clearsTag) {
                this.q = obtainStyledAttributes.getResourceId(index, this.q);
            } else if (index == R.styleable.ViewTransition_ifTagSet) {
                this.r = obtainStyledAttributes.getResourceId(index, this.r);
            } else if (index == R.styleable.ViewTransition_ifTagNotSet) {
                this.s = obtainStyledAttributes.getResourceId(index, this.s);
            } else if (index == R.styleable.ViewTransition_SharedValueId) {
                this.u = obtainStyledAttributes.getResourceId(index, this.u);
            } else if (index == R.styleable.ViewTransition_SharedValue) {
                this.t = obtainStyledAttributes.getInteger(index, this.t);
            }
        }
        obtainStyledAttributes.recycle();
    }

    public void k(boolean z) {
        this.c = !z;
    }

    public boolean l(int i) {
        int i2 = this.b;
        return i2 == 1 ? i == 0 : i2 == 2 ? i == 1 : i2 == 3 && i == 0;
    }

    public final void m(MotionScene.Transition transition, View view) {
        int i = this.h;
        if (i != -1) {
            transition.setDuration(i);
        }
        transition.setPathMotionArc(this.d);
        transition.setInterpolatorInfo(this.l, this.m, this.n);
        int id = view.getId();
        KeyFrames keyFrames = this.f;
        if (keyFrames != null) {
            ArrayList<Key> keyFramesForView = keyFrames.getKeyFramesForView(-1);
            KeyFrames keyFrames2 = new KeyFrames();
            Iterator<Key> it = keyFramesForView.iterator();
            while (it.hasNext()) {
                keyFrames2.addKey(it.next().mo7clone().setViewId(id));
            }
            transition.addKeyFrame(keyFrames2);
        }
    }

    public void setSharedValue(int i) {
        this.t = i;
    }

    public void setSharedValueCurrent(int i) {
        this.v = i;
    }

    public void setSharedValueID(int i) {
        this.u = i;
    }

    public void setStateTransition(int i) {
        this.b = i;
    }

    public String toString() {
        return "ViewTransition(" + Debug.getName(this.o, this.f958a) + ")";
    }
}
