package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.MotionEvent;
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
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.R;
import androidx.constraintlayout.widget.StateSet;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public class MotionScene {
    public static final int LAYOUT_CALL_MEASURE = 2;
    public static final int LAYOUT_HONOR_REQUEST = 1;
    public static final int LAYOUT_IGNORE_REQUEST = 0;
    public static final int UNSET = -1;

    /* renamed from: a  reason: collision with root package name */
    public final MotionLayout f954a;
    public MotionEvent n;
    public MotionLayout.MotionTracker q;
    public boolean r;
    public final ViewTransitionController s;
    public float t;
    public float u;
    public StateSet b = null;
    public Transition c = null;
    public boolean d = false;
    public ArrayList<Transition> e = new ArrayList<>();
    public Transition f = null;
    public ArrayList<Transition> g = new ArrayList<>();
    public SparseArray<ConstraintSet> h = new SparseArray<>();
    public HashMap<String, Integer> i = new HashMap<>();
    public SparseIntArray j = new SparseIntArray();
    public boolean k = false;
    public int l = 400;
    public int m = 0;
    public boolean o = false;
    public boolean p = false;

    /* loaded from: classes.dex */
    public class a implements Interpolator {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Easing f956a;

        public a(MotionScene motionScene, Easing easing) {
            this.f956a = easing;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            return (float) this.f956a.get(f);
        }
    }

    public MotionScene(MotionLayout motionLayout) {
        this.f954a = motionLayout;
        this.s = new ViewTransitionController(motionLayout);
    }

    public static String stripID(String str) {
        if (str == null) {
            return "";
        }
        int indexOf = str.indexOf(47);
        return indexOf < 0 ? str : str.substring(indexOf + 1);
    }

    public final boolean A() {
        return this.q != null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final void B(Context context, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        Transition transition = null;
        try {
            int eventType = xml.getEventType();
            while (true) {
                char c = 1;
                if (eventType == 1) {
                    return;
                }
                if (eventType == 0) {
                    xml.getName();
                    continue;
                } else if (eventType != 2) {
                    continue;
                } else {
                    String name = xml.getName();
                    if (this.k) {
                        System.out.println("parsing = " + name);
                    }
                    switch (name.hashCode()) {
                        case -1349929691:
                            if (name.equals("ConstraintSet")) {
                                c = 5;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1239391468:
                            if (name.equals(ViewTransition.KEY_FRAME_SET_TAG)) {
                                c = '\b';
                                break;
                            }
                            c = 65535;
                            break;
                        case -687739768:
                            if (name.equals("Include")) {
                                c = 7;
                                break;
                            }
                            c = 65535;
                            break;
                        case 61998586:
                            if (name.equals(ViewTransition.VIEW_TRANSITION_TAG)) {
                                c = '\t';
                                break;
                            }
                            c = 65535;
                            break;
                        case 269306229:
                            if (name.equals("Transition")) {
                                break;
                            }
                            c = 65535;
                            break;
                        case 312750793:
                            if (name.equals("OnClick")) {
                                c = 3;
                                break;
                            }
                            c = 65535;
                            break;
                        case 327855227:
                            if (name.equals("OnSwipe")) {
                                c = 2;
                                break;
                            }
                            c = 65535;
                            break;
                        case 793277014:
                            if (name.equals(TypedValues.MotionScene.NAME)) {
                                c = 0;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1382829617:
                            if (name.equals("StateSet")) {
                                c = 4;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1942574248:
                            if (name.equals("include")) {
                                c = 6;
                                break;
                            }
                            c = 65535;
                            break;
                        default:
                            c = 65535;
                            break;
                    }
                    switch (c) {
                        case 0:
                            F(context, xml);
                            continue;
                        case 1:
                            ArrayList<Transition> arrayList = this.e;
                            Transition transition2 = new Transition(this, context, xml);
                            arrayList.add(transition2);
                            if (this.c == null && !transition2.b) {
                                this.c = transition2;
                                if (transition2.l != null) {
                                    this.c.l.x(this.r);
                                }
                            }
                            if (transition2.b) {
                                if (transition2.c == -1) {
                                    this.f = transition2;
                                } else {
                                    this.g.add(transition2);
                                }
                                this.e.remove(transition2);
                            }
                            transition = transition2;
                            continue;
                        case 2:
                            if (transition == null) {
                                Log.v(TypedValues.MotionScene.NAME, " OnSwipe (" + context.getResources().getResourceEntryName(i) + ".xml:" + xml.getLineNumber() + ")");
                            }
                            if (transition != null) {
                                transition.l = new d(context, this.f954a, xml);
                                continue;
                            } else {
                                continue;
                            }
                        case 3:
                            if (transition != null) {
                                transition.addOnClick(context, xml);
                                continue;
                            } else {
                                continue;
                            }
                        case 4:
                            this.b = new StateSet(context, xml);
                            continue;
                        case 5:
                            C(context, xml);
                            continue;
                        case 6:
                        case 7:
                            E(context, xml);
                            continue;
                        case '\b':
                            KeyFrames keyFrames = new KeyFrames(context, xml);
                            if (transition != null) {
                                transition.k.add(keyFrames);
                                continue;
                            } else {
                                continue;
                            }
                        case '\t':
                            this.s.add(new ViewTransition(context, xml));
                            continue;
                        default:
                            continue;
                    }
                }
                eventType = xml.next();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final int C(Context context, XmlPullParser xmlPullParser) {
        boolean z;
        boolean z2;
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.setForceId(false);
        int attributeCount = xmlPullParser.getAttributeCount();
        int i = -1;
        int i2 = -1;
        for (int i3 = 0; i3 < attributeCount; i3++) {
            String attributeName = xmlPullParser.getAttributeName(i3);
            String attributeValue = xmlPullParser.getAttributeValue(i3);
            if (this.k) {
                System.out.println("id string = " + attributeValue);
            }
            attributeName.hashCode();
            switch (attributeName.hashCode()) {
                case -1496482599:
                    if (attributeName.equals("deriveConstraintsFrom")) {
                        z = false;
                        break;
                    }
                    z = true;
                    break;
                case -1153153640:
                    if (attributeName.equals("constraintRotate")) {
                        z = true;
                        break;
                    }
                    z = true;
                    break;
                case 3355:
                    if (attributeName.equals("id")) {
                        z = true;
                        break;
                    }
                    z = true;
                    break;
                default:
                    z = true;
                    break;
            }
            switch (z) {
                case false:
                    i2 = k(context, attributeValue);
                    break;
                case true:
                    try {
                        constraintSet.mRotate = Integer.parseInt(attributeValue);
                        break;
                    } catch (NumberFormatException unused) {
                        attributeValue.hashCode();
                        switch (attributeValue.hashCode()) {
                            case -768416914:
                                if (attributeValue.equals("x_left")) {
                                    z2 = false;
                                    break;
                                }
                                z2 = true;
                                break;
                            case 3317767:
                                if (attributeValue.equals("left")) {
                                    z2 = true;
                                    break;
                                }
                                z2 = true;
                                break;
                            case 3387192:
                                if (attributeValue.equals("none")) {
                                    z2 = true;
                                    break;
                                }
                                z2 = true;
                                break;
                            case 108511772:
                                if (attributeValue.equals("right")) {
                                    z2 = true;
                                    break;
                                }
                                z2 = true;
                                break;
                            case 1954540437:
                                if (attributeValue.equals("x_right")) {
                                    z2 = true;
                                    break;
                                }
                                z2 = true;
                                break;
                            default:
                                z2 = true;
                                break;
                        }
                        switch (z2) {
                            case false:
                                constraintSet.mRotate = 4;
                                continue;
                            case true:
                                constraintSet.mRotate = 2;
                                continue;
                            case true:
                                constraintSet.mRotate = 0;
                                continue;
                            case true:
                                constraintSet.mRotate = 1;
                                continue;
                            case true:
                                constraintSet.mRotate = 3;
                                continue;
                        }
                    }
                    break;
                case true:
                    i = k(context, attributeValue);
                    this.i.put(stripID(attributeValue), Integer.valueOf(i));
                    constraintSet.mIdString = Debug.getName(context, i);
                    break;
            }
        }
        if (i != -1) {
            if (this.f954a.D != 0) {
                constraintSet.setValidateOnParse(true);
            }
            constraintSet.load(context, xmlPullParser);
            if (i2 != -1) {
                this.j.put(i, i2);
            }
            this.h.put(i, constraintSet);
        }
        return i;
    }

    public final int D(Context context, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                String name = xml.getName();
                if (2 == eventType && "ConstraintSet".equals(name)) {
                    return C(context, xml);
                }
            }
            return -1;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public final void E(Context context, XmlPullParser xmlPullParser) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.include);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = obtainStyledAttributes.getIndex(i);
            if (index == R.styleable.include_constraintSet) {
                D(context, obtainStyledAttributes.getResourceId(index, -1));
            }
        }
        obtainStyledAttributes.recycle();
    }

    public final void F(Context context, XmlPullParser xmlPullParser) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.MotionScene);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = obtainStyledAttributes.getIndex(i);
            if (index == R.styleable.MotionScene_defaultDuration) {
                int i2 = obtainStyledAttributes.getInt(index, this.l);
                this.l = i2;
                if (i2 < 8) {
                    this.l = 8;
                }
            } else if (index == R.styleable.MotionScene_layoutDuringTransition) {
                this.m = obtainStyledAttributes.getInteger(index, 0);
            }
        }
        obtainStyledAttributes.recycle();
    }

    public void G(float f, float f2) {
        Transition transition = this.c;
        if (transition == null || transition.l == null) {
            return;
        }
        this.c.l.u(f, f2);
    }

    public void H(float f, float f2) {
        Transition transition = this.c;
        if (transition == null || transition.l == null) {
            return;
        }
        this.c.l.v(f, f2);
    }

    public void I(MotionEvent motionEvent, int i, MotionLayout motionLayout) {
        MotionLayout.MotionTracker motionTracker;
        MotionEvent motionEvent2;
        RectF rectF = new RectF();
        if (this.q == null) {
            this.q = this.f954a.obtainVelocityTracker();
        }
        this.q.addMovement(motionEvent);
        if (i != -1) {
            int action = motionEvent.getAction();
            boolean z = false;
            if (action != 0) {
                if (action == 2 && !this.o) {
                    float rawY = motionEvent.getRawY() - this.u;
                    float rawX = motionEvent.getRawX() - this.t;
                    if ((rawX == 0.0d && rawY == 0.0d) || (motionEvent2 = this.n) == null) {
                        return;
                    }
                    Transition bestTransitionFor = bestTransitionFor(i, rawX, rawY, motionEvent2);
                    if (bestTransitionFor != null) {
                        motionLayout.setTransition(bestTransitionFor);
                        RectF p = this.c.l.p(this.f954a, rectF);
                        if (p != null && !p.contains(this.n.getX(), this.n.getY())) {
                            z = true;
                        }
                        this.p = z;
                        this.c.l.z(this.t, this.u);
                    }
                }
            } else {
                this.t = motionEvent.getRawX();
                this.u = motionEvent.getRawY();
                this.n = motionEvent;
                this.o = false;
                if (this.c.l != null) {
                    RectF f = this.c.l.f(this.f954a, rectF);
                    if (f == null || f.contains(this.n.getX(), this.n.getY())) {
                        RectF p2 = this.c.l.p(this.f954a, rectF);
                        if (p2 != null && !p2.contains(this.n.getX(), this.n.getY())) {
                            this.p = true;
                        } else {
                            this.p = false;
                        }
                        this.c.l.w(this.t, this.u);
                        return;
                    }
                    this.n = null;
                    this.o = true;
                    return;
                }
                return;
            }
        }
        if (this.o) {
            return;
        }
        Transition transition = this.c;
        if (transition != null && transition.l != null && !this.p) {
            this.c.l.s(motionEvent, this.q, i, this);
        }
        this.t = motionEvent.getRawX();
        this.u = motionEvent.getRawY();
        if (motionEvent.getAction() != 1 || (motionTracker = this.q) == null) {
            return;
        }
        motionTracker.recycle();
        this.q = null;
        int i2 = motionLayout.m;
        if (i2 != -1) {
            f(motionLayout, i2);
        }
    }

    public final void J(int i, MotionLayout motionLayout) {
        ConstraintSet constraintSet = this.h.get(i);
        constraintSet.derivedState = constraintSet.mIdString;
        int i2 = this.j.get(i);
        if (i2 > 0) {
            J(i2, motionLayout);
            ConstraintSet constraintSet2 = this.h.get(i2);
            if (constraintSet2 == null) {
                Log.e(TypedValues.MotionScene.NAME, "ERROR! invalid deriveConstraintsFrom: @id/" + Debug.getName(this.f954a.getContext(), i2));
                return;
            }
            constraintSet.derivedState += MqttTopic.TOPIC_LEVEL_SEPARATOR + constraintSet2.derivedState;
            constraintSet.readFallback(constraintSet2);
        } else {
            constraintSet.derivedState += "  layout";
            constraintSet.readFallback(motionLayout);
        }
        constraintSet.applyDeltaFrom(constraintSet);
    }

    public void K(MotionLayout motionLayout) {
        for (int i = 0; i < this.h.size(); i++) {
            int keyAt = this.h.keyAt(i);
            if (y(keyAt)) {
                Log.e(TypedValues.MotionScene.NAME, "Cannot be derived from yourself");
                return;
            }
            J(keyAt, motionLayout);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0013, code lost:
        if (r2 != (-1)) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void L(int r7, int r8) {
        /*
            r6 = this;
            androidx.constraintlayout.widget.StateSet r0 = r6.b
            r1 = -1
            if (r0 == 0) goto L16
            int r0 = r0.stateGetConstraintID(r7, r1, r1)
            if (r0 == r1) goto Lc
            goto Ld
        Lc:
            r0 = r7
        Ld:
            androidx.constraintlayout.widget.StateSet r2 = r6.b
            int r2 = r2.stateGetConstraintID(r8, r1, r1)
            if (r2 == r1) goto L17
            goto L18
        L16:
            r0 = r7
        L17:
            r2 = r8
        L18:
            androidx.constraintlayout.motion.widget.MotionScene$Transition r3 = r6.c
            if (r3 == 0) goto L2b
            int r3 = androidx.constraintlayout.motion.widget.MotionScene.Transition.a(r3)
            if (r3 != r8) goto L2b
            androidx.constraintlayout.motion.widget.MotionScene$Transition r3 = r6.c
            int r3 = androidx.constraintlayout.motion.widget.MotionScene.Transition.c(r3)
            if (r3 != r7) goto L2b
            return
        L2b:
            java.util.ArrayList<androidx.constraintlayout.motion.widget.MotionScene$Transition> r3 = r6.e
            java.util.Iterator r3 = r3.iterator()
        L31:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L6b
            java.lang.Object r4 = r3.next()
            androidx.constraintlayout.motion.widget.MotionScene$Transition r4 = (androidx.constraintlayout.motion.widget.MotionScene.Transition) r4
            int r5 = androidx.constraintlayout.motion.widget.MotionScene.Transition.a(r4)
            if (r5 != r2) goto L49
            int r5 = androidx.constraintlayout.motion.widget.MotionScene.Transition.c(r4)
            if (r5 == r0) goto L55
        L49:
            int r5 = androidx.constraintlayout.motion.widget.MotionScene.Transition.a(r4)
            if (r5 != r8) goto L31
            int r5 = androidx.constraintlayout.motion.widget.MotionScene.Transition.c(r4)
            if (r5 != r7) goto L31
        L55:
            r6.c = r4
            if (r4 == 0) goto L6a
            androidx.constraintlayout.motion.widget.d r7 = androidx.constraintlayout.motion.widget.MotionScene.Transition.l(r4)
            if (r7 == 0) goto L6a
            androidx.constraintlayout.motion.widget.MotionScene$Transition r7 = r6.c
            androidx.constraintlayout.motion.widget.d r7 = androidx.constraintlayout.motion.widget.MotionScene.Transition.l(r7)
            boolean r8 = r6.r
            r7.x(r8)
        L6a:
            return
        L6b:
            androidx.constraintlayout.motion.widget.MotionScene$Transition r7 = r6.f
            java.util.ArrayList<androidx.constraintlayout.motion.widget.MotionScene$Transition> r3 = r6.g
            java.util.Iterator r3 = r3.iterator()
        L73:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L87
            java.lang.Object r4 = r3.next()
            androidx.constraintlayout.motion.widget.MotionScene$Transition r4 = (androidx.constraintlayout.motion.widget.MotionScene.Transition) r4
            int r5 = androidx.constraintlayout.motion.widget.MotionScene.Transition.a(r4)
            if (r5 != r8) goto L73
            r7 = r4
            goto L73
        L87:
            androidx.constraintlayout.motion.widget.MotionScene$Transition r8 = new androidx.constraintlayout.motion.widget.MotionScene$Transition
            r8.<init>(r6, r7)
            androidx.constraintlayout.motion.widget.MotionScene.Transition.d(r8, r0)
            androidx.constraintlayout.motion.widget.MotionScene.Transition.b(r8, r2)
            if (r0 == r1) goto L99
            java.util.ArrayList<androidx.constraintlayout.motion.widget.MotionScene$Transition> r7 = r6.e
            r7.add(r8)
        L99:
            r6.c = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionScene.L(int, int):void");
    }

    public void M() {
        Transition transition = this.c;
        if (transition == null || transition.l == null) {
            return;
        }
        this.c.l.A();
    }

    public boolean N() {
        Iterator<Transition> it = this.e.iterator();
        while (it.hasNext()) {
            if (it.next().l != null) {
                return true;
            }
        }
        Transition transition = this.c;
        return (transition == null || transition.l == null) ? false : true;
    }

    public void addOnClickListeners(MotionLayout motionLayout, int i) {
        Iterator<Transition> it = this.e.iterator();
        while (it.hasNext()) {
            Transition next = it.next();
            if (next.m.size() > 0) {
                Iterator it2 = next.m.iterator();
                while (it2.hasNext()) {
                    ((Transition.TransitionOnClick) it2.next()).removeOnClickListeners(motionLayout);
                }
            }
        }
        Iterator<Transition> it3 = this.g.iterator();
        while (it3.hasNext()) {
            Transition next2 = it3.next();
            if (next2.m.size() > 0) {
                Iterator it4 = next2.m.iterator();
                while (it4.hasNext()) {
                    ((Transition.TransitionOnClick) it4.next()).removeOnClickListeners(motionLayout);
                }
            }
        }
        Iterator<Transition> it5 = this.e.iterator();
        while (it5.hasNext()) {
            Transition next3 = it5.next();
            if (next3.m.size() > 0) {
                Iterator it6 = next3.m.iterator();
                while (it6.hasNext()) {
                    ((Transition.TransitionOnClick) it6.next()).addOnClickListeners(motionLayout, i, next3);
                }
            }
        }
        Iterator<Transition> it7 = this.g.iterator();
        while (it7.hasNext()) {
            Transition next4 = it7.next();
            if (next4.m.size() > 0) {
                Iterator it8 = next4.m.iterator();
                while (it8.hasNext()) {
                    ((Transition.TransitionOnClick) it8.next()).addOnClickListeners(motionLayout, i, next4);
                }
            }
        }
    }

    public void addTransition(Transition transition) {
        int l = l(transition);
        if (l == -1) {
            this.e.add(transition);
        } else {
            this.e.set(l, transition);
        }
    }

    public boolean applyViewTransition(int i, MotionController motionController) {
        return this.s.d(i, motionController);
    }

    public Transition bestTransitionFor(int i, float f, float f2, MotionEvent motionEvent) {
        if (i != -1) {
            List<Transition> transitionsWithState = getTransitionsWithState(i);
            float f3 = 0.0f;
            Transition transition = null;
            RectF rectF = new RectF();
            for (Transition transition2 : transitionsWithState) {
                if (!transition2.o && transition2.l != null) {
                    transition2.l.x(this.r);
                    RectF p = transition2.l.p(this.f954a, rectF);
                    if (p == null || motionEvent == null || p.contains(motionEvent.getX(), motionEvent.getY())) {
                        RectF f4 = transition2.l.f(this.f954a, rectF);
                        if (f4 == null || motionEvent == null || f4.contains(motionEvent.getX(), motionEvent.getY())) {
                            float a2 = transition2.l.a(f, f2);
                            if (transition2.l.l && motionEvent != null) {
                                float x = motionEvent.getX() - transition2.l.i;
                                float y = motionEvent.getY() - transition2.l.j;
                                a2 = ((float) (Math.atan2(f2 + y, f + x) - Math.atan2(x, y))) * 10.0f;
                            }
                            float f5 = a2 * (transition2.c == i ? -1.0f : 1.1f);
                            if (f5 > f3) {
                                transition = transition2;
                                f3 = f5;
                            }
                        }
                    }
                }
            }
            return transition;
        }
        return this.c;
    }

    public void disableAutoTransition(boolean z) {
        this.d = z;
    }

    public void enableViewTransition(int i, boolean z) {
        this.s.e(i, z);
    }

    public boolean f(MotionLayout motionLayout, int i) {
        Transition transition;
        if (A() || this.d) {
            return false;
        }
        Iterator<Transition> it = this.e.iterator();
        while (it.hasNext()) {
            Transition next = it.next();
            if (next.n != 0 && ((transition = this.c) != next || !transition.isTransitionFlag(2))) {
                if (i != next.d || (next.n != 4 && next.n != 2)) {
                    if (i == next.c && (next.n == 3 || next.n == 1)) {
                        MotionLayout.k kVar = MotionLayout.k.FINISHED;
                        motionLayout.setState(kVar);
                        motionLayout.setTransition(next);
                        if (next.n == 3) {
                            motionLayout.transitionToStart();
                            motionLayout.setState(MotionLayout.k.SETUP);
                            motionLayout.setState(MotionLayout.k.MOVING);
                        } else {
                            motionLayout.setProgress(0.0f);
                            motionLayout.G(true);
                            motionLayout.setState(MotionLayout.k.SETUP);
                            motionLayout.setState(MotionLayout.k.MOVING);
                            motionLayout.setState(kVar);
                            motionLayout.P();
                        }
                        return true;
                    }
                } else {
                    MotionLayout.k kVar2 = MotionLayout.k.FINISHED;
                    motionLayout.setState(kVar2);
                    motionLayout.setTransition(next);
                    if (next.n == 4) {
                        motionLayout.transitionToEnd();
                        motionLayout.setState(MotionLayout.k.SETUP);
                        motionLayout.setState(MotionLayout.k.MOVING);
                    } else {
                        motionLayout.setProgress(1.0f);
                        motionLayout.G(true);
                        motionLayout.setState(MotionLayout.k.SETUP);
                        motionLayout.setState(MotionLayout.k.MOVING);
                        motionLayout.setState(kVar2);
                        motionLayout.P();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public int g() {
        Transition transition = this.c;
        if (transition == null || transition.l == null) {
            return 0;
        }
        return this.c.l.d();
    }

    public int gatPathMotionArc() {
        Transition transition = this.c;
        if (transition != null) {
            return transition.p;
        }
        return -1;
    }

    public ConstraintSet getConstraintSet(Context context, String str) {
        if (this.k) {
            PrintStream printStream = System.out;
            printStream.println("id " + str);
            PrintStream printStream2 = System.out;
            printStream2.println("size " + this.h.size());
        }
        for (int i = 0; i < this.h.size(); i++) {
            int keyAt = this.h.keyAt(i);
            String resourceName = context.getResources().getResourceName(keyAt);
            if (this.k) {
                PrintStream printStream3 = System.out;
                printStream3.println("Id for <" + i + "> is <" + resourceName + "> looking for <" + str + ">");
            }
            if (str.equals(resourceName)) {
                return this.h.get(keyAt);
            }
        }
        return null;
    }

    public int[] getConstraintSetIds() {
        int size = this.h.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = this.h.keyAt(i);
        }
        return iArr;
    }

    public ArrayList<Transition> getDefinedTransitions() {
        return this.e;
    }

    public int getDuration() {
        Transition transition = this.c;
        if (transition != null) {
            return transition.h;
        }
        return this.l;
    }

    public Interpolator getInterpolator() {
        int i = this.c.e;
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
            return new a(this, Easing.getInterpolator(this.c.f));
        }
        return AnimationUtils.loadInterpolator(this.f954a.getContext(), this.c.g);
    }

    public void getKeyFrames(MotionController motionController) {
        Transition transition = this.c;
        if (transition != null) {
            Iterator it = transition.k.iterator();
            while (it.hasNext()) {
                ((KeyFrames) it.next()).addFrames(motionController);
            }
            return;
        }
        Transition transition2 = this.f;
        if (transition2 != null) {
            Iterator it2 = transition2.k.iterator();
            while (it2.hasNext()) {
                ((KeyFrames) it2.next()).addFrames(motionController);
            }
        }
    }

    public float getPathPercent(View view, int i) {
        return 0.0f;
    }

    public float getStaggered() {
        Transition transition = this.c;
        if (transition != null) {
            return transition.i;
        }
        return 0.0f;
    }

    public Transition getTransitionById(int i) {
        Iterator<Transition> it = this.e.iterator();
        while (it.hasNext()) {
            Transition next = it.next();
            if (next.f955a == i) {
                return next;
            }
        }
        return null;
    }

    public List<Transition> getTransitionsWithState(int i) {
        int r = r(i);
        ArrayList arrayList = new ArrayList();
        Iterator<Transition> it = this.e.iterator();
        while (it.hasNext()) {
            Transition next = it.next();
            if (next.d == r || next.c == r) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public ConstraintSet h(int i) {
        return i(i, -1, -1);
    }

    public ConstraintSet i(int i, int i2, int i3) {
        int stateGetConstraintID;
        if (this.k) {
            PrintStream printStream = System.out;
            printStream.println("id " + i);
            PrintStream printStream2 = System.out;
            printStream2.println("size " + this.h.size());
        }
        StateSet stateSet = this.b;
        if (stateSet != null && (stateGetConstraintID = stateSet.stateGetConstraintID(i, i2, i3)) != -1) {
            i = stateGetConstraintID;
        }
        if (this.h.get(i) == null) {
            Log.e(TypedValues.MotionScene.NAME, "Warning could not find ConstraintSet id/" + Debug.getName(this.f954a.getContext(), i) + " In MotionScene");
            SparseArray<ConstraintSet> sparseArray = this.h;
            return sparseArray.get(sparseArray.keyAt(0));
        }
        return this.h.get(i);
    }

    public boolean isViewTransitionEnabled(int i) {
        return this.s.g(i);
    }

    public int j() {
        Transition transition = this.c;
        if (transition == null) {
            return -1;
        }
        return transition.c;
    }

    public final int k(Context context, String str) {
        int i;
        if (str.contains(MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
            i = context.getResources().getIdentifier(str.substring(str.indexOf(47) + 1), "id", context.getPackageName());
            if (this.k) {
                System.out.println("id getMap res = " + i);
            }
        } else {
            i = -1;
        }
        if (i == -1) {
            if (str.length() > 1) {
                return Integer.parseInt(str.substring(1));
            }
            Log.e(TypedValues.MotionScene.NAME, "error in parsing id");
            return i;
        }
        return i;
    }

    public final int l(Transition transition) {
        int i = transition.f955a;
        if (i != -1) {
            for (int i2 = 0; i2 < this.e.size(); i2++) {
                if (this.e.get(i2).f955a == i) {
                    return i2;
                }
            }
            return -1;
        }
        throw new IllegalArgumentException("The transition must have an id");
    }

    public int lookUpConstraintId(String str) {
        Integer num = this.i.get(str);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public String lookUpConstraintName(int i) {
        for (Map.Entry<String, Integer> entry : this.i.entrySet()) {
            Integer value = entry.getValue();
            if (value != null && value.intValue() == i) {
                return entry.getKey();
            }
        }
        return null;
    }

    public Key m(Context context, int i, int i2, int i3) {
        Transition transition = this.c;
        if (transition == null) {
            return null;
        }
        Iterator it = transition.k.iterator();
        while (it.hasNext()) {
            KeyFrames keyFrames = (KeyFrames) it.next();
            for (Integer num : keyFrames.getKeys()) {
                if (i2 == num.intValue()) {
                    Iterator<Key> it2 = keyFrames.getKeyFramesForView(num.intValue()).iterator();
                    while (it2.hasNext()) {
                        Key next = it2.next();
                        if (next.f939a == i3 && next.mType == i) {
                            return next;
                        }
                    }
                    continue;
                }
            }
        }
        return null;
    }

    public float n() {
        Transition transition = this.c;
        if (transition == null || transition.l == null) {
            return 0.0f;
        }
        return this.c.l.g();
    }

    public float o() {
        Transition transition = this.c;
        if (transition == null || transition.l == null) {
            return 0.0f;
        }
        return this.c.l.h();
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    public boolean p() {
        Transition transition = this.c;
        if (transition == null || transition.l == null) {
            return false;
        }
        return this.c.l.i();
    }

    public float q(float f, float f2) {
        Transition transition = this.c;
        if (transition == null || transition.l == null) {
            return 0.0f;
        }
        return this.c.l.j(f, f2);
    }

    public final int r(int i) {
        int stateGetConstraintID;
        StateSet stateSet = this.b;
        return (stateSet == null || (stateGetConstraintID = stateSet.stateGetConstraintID(i, -1, -1)) == -1) ? i : stateGetConstraintID;
    }

    public void removeTransition(Transition transition) {
        int l = l(transition);
        if (l != -1) {
            this.e.remove(l);
        }
    }

    public int s() {
        Transition transition = this.c;
        if (transition == null || transition.l == null) {
            return 0;
        }
        return this.c.l.k();
    }

    public void setConstraintSet(int i, ConstraintSet constraintSet) {
        this.h.put(i, constraintSet);
    }

    public void setDuration(int i) {
        Transition transition = this.c;
        if (transition != null) {
            transition.setDuration(i);
        } else {
            this.l = i;
        }
    }

    public void setKeyframe(View view, int i, String str, Object obj) {
        Transition transition = this.c;
        if (transition == null) {
            return;
        }
        Iterator it = transition.k.iterator();
        while (it.hasNext()) {
            Iterator<Key> it2 = ((KeyFrames) it.next()).getKeyFramesForView(view.getId()).iterator();
            while (it2.hasNext()) {
                if (it2.next().f939a == i) {
                    if (obj != null) {
                        ((Float) obj).floatValue();
                    }
                    str.equalsIgnoreCase("app:PerpendicularPath_percent");
                }
            }
        }
    }

    public void setRtl(boolean z) {
        this.r = z;
        Transition transition = this.c;
        if (transition == null || transition.l == null) {
            return;
        }
        this.c.l.x(this.r);
    }

    public void setTransition(Transition transition) {
        this.c = transition;
        if (transition == null || transition.l == null) {
            return;
        }
        this.c.l.x(this.r);
    }

    public float t() {
        Transition transition = this.c;
        if (transition == null || transition.l == null) {
            return 0.0f;
        }
        return this.c.l.l();
    }

    public float u() {
        Transition transition = this.c;
        if (transition == null || transition.l == null) {
            return 0.0f;
        }
        return this.c.l.m();
    }

    public float v() {
        Transition transition = this.c;
        if (transition == null || transition.l == null) {
            return 0.0f;
        }
        return this.c.l.n();
    }

    public boolean validateLayout(MotionLayout motionLayout) {
        return motionLayout == this.f954a && motionLayout.h == this;
    }

    public void viewTransition(int i, View... viewArr) {
        this.s.k(i, viewArr);
    }

    public float w() {
        Transition transition = this.c;
        if (transition == null || transition.l == null) {
            return 0.0f;
        }
        return this.c.l.o();
    }

    public int x() {
        Transition transition = this.c;
        if (transition == null) {
            return -1;
        }
        return transition.d;
    }

    public final boolean y(int i) {
        int i2 = this.j.get(i);
        int size = this.j.size();
        while (i2 > 0) {
            if (i2 == i) {
                return true;
            }
            int i3 = size - 1;
            if (size < 0) {
                return true;
            }
            i2 = this.j.get(i2);
            size = i3;
        }
        return false;
    }

    public boolean z(View view, int i) {
        Transition transition = this.c;
        if (transition == null) {
            return false;
        }
        Iterator it = transition.k.iterator();
        while (it.hasNext()) {
            Iterator<Key> it2 = ((KeyFrames) it.next()).getKeyFramesForView(view.getId()).iterator();
            while (it2.hasNext()) {
                if (it2.next().f939a == i) {
                    return true;
                }
            }
        }
        return false;
    }

    /* loaded from: classes.dex */
    public static class Transition {
        public static final int AUTO_ANIMATE_TO_END = 4;
        public static final int AUTO_ANIMATE_TO_START = 3;
        public static final int AUTO_JUMP_TO_END = 2;
        public static final int AUTO_JUMP_TO_START = 1;
        public static final int AUTO_NONE = 0;
        public static final int INTERPOLATE_ANTICIPATE = 6;
        public static final int INTERPOLATE_BOUNCE = 4;
        public static final int INTERPOLATE_EASE_IN = 1;
        public static final int INTERPOLATE_EASE_IN_OUT = 0;
        public static final int INTERPOLATE_EASE_OUT = 2;
        public static final int INTERPOLATE_LINEAR = 3;
        public static final int INTERPOLATE_OVERSHOOT = 5;
        public static final int INTERPOLATE_REFERENCE_ID = -2;
        public static final int INTERPOLATE_SPLINE_STRING = -1;

        /* renamed from: a  reason: collision with root package name */
        public int f955a;
        public boolean b;
        public int c;
        public int d;
        public int e;
        public String f;
        public int g;
        public int h;
        public float i;
        public final MotionScene j;
        public ArrayList<KeyFrames> k;
        public d l;
        public ArrayList<TransitionOnClick> m;
        public int n;
        public boolean o;
        public int p;
        public int q;
        public int r;

        public Transition(MotionScene motionScene, Transition transition) {
            this.f955a = -1;
            this.b = false;
            this.c = -1;
            this.d = -1;
            this.e = 0;
            this.f = null;
            this.g = -1;
            this.h = 400;
            this.i = 0.0f;
            this.k = new ArrayList<>();
            this.l = null;
            this.m = new ArrayList<>();
            this.n = 0;
            this.o = false;
            this.p = -1;
            this.q = 0;
            this.r = 0;
            this.j = motionScene;
            this.h = motionScene.l;
            if (transition != null) {
                this.p = transition.p;
                this.e = transition.e;
                this.f = transition.f;
                this.g = transition.g;
                this.h = transition.h;
                this.k = transition.k;
                this.i = transition.i;
                this.q = transition.q;
            }
        }

        public void addKeyFrame(KeyFrames keyFrames) {
            this.k.add(keyFrames);
        }

        public void addOnClick(int i, int i2) {
            Iterator<TransitionOnClick> it = this.m.iterator();
            while (it.hasNext()) {
                TransitionOnClick next = it.next();
                if (next.i == i) {
                    next.j = i2;
                    return;
                }
            }
            this.m.add(new TransitionOnClick(this, i, i2));
        }

        public String debugString(Context context) {
            String resourceEntryName = this.d == -1 ? "null" : context.getResources().getResourceEntryName(this.d);
            if (this.c == -1) {
                return resourceEntryName + " -> null";
            }
            return resourceEntryName + " -> " + context.getResources().getResourceEntryName(this.c);
        }

        public int getAutoTransition() {
            return this.n;
        }

        public int getDuration() {
            return this.h;
        }

        public int getEndConstraintSetId() {
            return this.c;
        }

        public int getId() {
            return this.f955a;
        }

        public List<KeyFrames> getKeyFrameList() {
            return this.k;
        }

        public int getLayoutDuringTransition() {
            return this.q;
        }

        public List<TransitionOnClick> getOnClickList() {
            return this.m;
        }

        public int getPathMotionArc() {
            return this.p;
        }

        public float getStagger() {
            return this.i;
        }

        public int getStartConstraintSetId() {
            return this.d;
        }

        public d getTouchResponse() {
            return this.l;
        }

        public boolean isEnabled() {
            return !this.o;
        }

        public boolean isTransitionFlag(int i) {
            return (i & this.r) != 0;
        }

        public void removeOnClick(int i) {
            TransitionOnClick transitionOnClick;
            Iterator<TransitionOnClick> it = this.m.iterator();
            while (true) {
                if (!it.hasNext()) {
                    transitionOnClick = null;
                    break;
                }
                transitionOnClick = it.next();
                if (transitionOnClick.i == i) {
                    break;
                }
            }
            if (transitionOnClick != null) {
                this.m.remove(transitionOnClick);
            }
        }

        public void setAutoTransition(int i) {
            this.n = i;
        }

        public void setDuration(int i) {
            this.h = Math.max(i, 8);
        }

        public void setEnable(boolean z) {
            setEnabled(z);
        }

        public void setEnabled(boolean z) {
            this.o = !z;
        }

        public void setInterpolatorInfo(int i, String str, int i2) {
            this.e = i;
            this.f = str;
            this.g = i2;
        }

        public void setLayoutDuringTransition(int i) {
            this.q = i;
        }

        public void setOnSwipe(OnSwipe onSwipe) {
            this.l = onSwipe == null ? null : new d(this.j.f954a, onSwipe);
        }

        public void setOnTouchUp(int i) {
            d touchResponse = getTouchResponse();
            if (touchResponse != null) {
                touchResponse.y(i);
            }
        }

        public void setPathMotionArc(int i) {
            this.p = i;
        }

        public void setStagger(float f) {
            this.i = f;
        }

        public void setTransitionFlag(int i) {
            this.r = i;
        }

        public final void t(MotionScene motionScene, Context context, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                if (index == R.styleable.Transition_constraintSetEnd) {
                    this.c = typedArray.getResourceId(index, -1);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.c);
                    if ("layout".equals(resourceTypeName)) {
                        ConstraintSet constraintSet = new ConstraintSet();
                        constraintSet.load(context, this.c);
                        motionScene.h.append(this.c, constraintSet);
                    } else if ("xml".equals(resourceTypeName)) {
                        this.c = motionScene.D(context, this.c);
                    }
                } else if (index == R.styleable.Transition_constraintSetStart) {
                    this.d = typedArray.getResourceId(index, this.d);
                    String resourceTypeName2 = context.getResources().getResourceTypeName(this.d);
                    if ("layout".equals(resourceTypeName2)) {
                        ConstraintSet constraintSet2 = new ConstraintSet();
                        constraintSet2.load(context, this.d);
                        motionScene.h.append(this.d, constraintSet2);
                    } else if ("xml".equals(resourceTypeName2)) {
                        this.d = motionScene.D(context, this.d);
                    }
                } else if (index == R.styleable.Transition_motionInterpolator) {
                    int i2 = typedArray.peekValue(index).type;
                    if (i2 == 1) {
                        int resourceId = typedArray.getResourceId(index, -1);
                        this.g = resourceId;
                        if (resourceId != -1) {
                            this.e = -2;
                        }
                    } else if (i2 == 3) {
                        String string = typedArray.getString(index);
                        this.f = string;
                        if (string != null) {
                            if (string.indexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR) > 0) {
                                this.g = typedArray.getResourceId(index, -1);
                                this.e = -2;
                            } else {
                                this.e = -1;
                            }
                        }
                    } else {
                        this.e = typedArray.getInteger(index, this.e);
                    }
                } else if (index == R.styleable.Transition_duration) {
                    int i3 = typedArray.getInt(index, this.h);
                    this.h = i3;
                    if (i3 < 8) {
                        this.h = 8;
                    }
                } else if (index == R.styleable.Transition_staggered) {
                    this.i = typedArray.getFloat(index, this.i);
                } else if (index == R.styleable.Transition_autoTransition) {
                    this.n = typedArray.getInteger(index, this.n);
                } else if (index == R.styleable.Transition_android_id) {
                    this.f955a = typedArray.getResourceId(index, this.f955a);
                } else if (index == R.styleable.Transition_transitionDisable) {
                    this.o = typedArray.getBoolean(index, this.o);
                } else if (index == R.styleable.Transition_pathMotionArc) {
                    this.p = typedArray.getInteger(index, -1);
                } else if (index == R.styleable.Transition_layoutDuringTransition) {
                    this.q = typedArray.getInteger(index, 0);
                } else if (index == R.styleable.Transition_transitionFlags) {
                    this.r = typedArray.getInteger(index, 0);
                }
            }
            if (this.d == -1) {
                this.b = true;
            }
        }

        public final void u(MotionScene motionScene, Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Transition);
            t(motionScene, context, obtainStyledAttributes);
            obtainStyledAttributes.recycle();
        }

        public void addOnClick(Context context, XmlPullParser xmlPullParser) {
            this.m.add(new TransitionOnClick(context, this, xmlPullParser));
        }

        /* loaded from: classes.dex */
        public static class TransitionOnClick implements View.OnClickListener {
            public static final int ANIM_TOGGLE = 17;
            public static final int ANIM_TO_END = 1;
            public static final int ANIM_TO_START = 16;
            public static final int JUMP_TO_END = 256;
            public static final int JUMP_TO_START = 4096;
            public final Transition h;
            public int i;
            public int j;

            public TransitionOnClick(Context context, Transition transition, XmlPullParser xmlPullParser) {
                this.i = -1;
                this.j = 17;
                this.h = transition;
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.OnClick);
                int indexCount = obtainStyledAttributes.getIndexCount();
                for (int i = 0; i < indexCount; i++) {
                    int index = obtainStyledAttributes.getIndex(i);
                    if (index == R.styleable.OnClick_targetId) {
                        this.i = obtainStyledAttributes.getResourceId(index, this.i);
                    } else if (index == R.styleable.OnClick_clickAction) {
                        this.j = obtainStyledAttributes.getInt(index, this.j);
                    }
                }
                obtainStyledAttributes.recycle();
            }

            public boolean a(Transition transition, MotionLayout motionLayout) {
                Transition transition2 = this.h;
                if (transition2 == transition) {
                    return true;
                }
                int i = transition2.c;
                int i2 = this.h.d;
                if (i2 == -1) {
                    return motionLayout.m != i;
                }
                int i3 = motionLayout.m;
                return i3 == i2 || i3 == i;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r7v4, types: [android.view.View] */
            public void addOnClickListeners(MotionLayout motionLayout, int i, Transition transition) {
                int i2 = this.i;
                MotionLayout motionLayout2 = motionLayout;
                if (i2 != -1) {
                    motionLayout2 = motionLayout.findViewById(i2);
                }
                if (motionLayout2 != null) {
                    int i3 = transition.d;
                    int i4 = transition.c;
                    if (i3 == -1) {
                        motionLayout2.setOnClickListener(this);
                        return;
                    }
                    int i5 = this.j;
                    boolean z = false;
                    boolean z2 = ((i5 & 1) != 0 && i == i3) | ((i5 & 1) != 0 && i == i3) | ((i5 & 256) != 0 && i == i3) | ((i5 & 16) != 0 && i == i4);
                    if ((i5 & 4096) != 0 && i == i4) {
                        z = true;
                    }
                    if (z2 || z) {
                        motionLayout2.setOnClickListener(this);
                        return;
                    }
                    return;
                }
                Log.e(TypedValues.MotionScene.NAME, "OnClick could not find id " + this.i);
            }

            /* JADX WARN: Removed duplicated region for block: B:44:0x00a3  */
            /* JADX WARN: Removed duplicated region for block: B:61:? A[RETURN, SYNTHETIC] */
            @Override // android.view.View.OnClickListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onClick(android.view.View r8) {
                /*
                    Method dump skipped, instructions count: 233
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionScene.Transition.TransitionOnClick.onClick(android.view.View):void");
            }

            public void removeOnClickListeners(MotionLayout motionLayout) {
                int i = this.i;
                if (i == -1) {
                    return;
                }
                View findViewById = motionLayout.findViewById(i);
                if (findViewById == null) {
                    Log.e(TypedValues.MotionScene.NAME, " (*)  could not find id " + this.i);
                    return;
                }
                findViewById.setOnClickListener(null);
            }

            public TransitionOnClick(Transition transition, int i, int i2) {
                this.i = -1;
                this.j = 17;
                this.h = transition;
                this.i = i;
                this.j = i2;
            }
        }

        public Transition(int i, MotionScene motionScene, int i2, int i3) {
            this.f955a = -1;
            this.b = false;
            this.c = -1;
            this.d = -1;
            this.e = 0;
            this.f = null;
            this.g = -1;
            this.h = 400;
            this.i = 0.0f;
            this.k = new ArrayList<>();
            this.l = null;
            this.m = new ArrayList<>();
            this.n = 0;
            this.o = false;
            this.p = -1;
            this.q = 0;
            this.r = 0;
            this.f955a = i;
            this.j = motionScene;
            this.d = i2;
            this.c = i3;
            this.h = motionScene.l;
            this.q = motionScene.m;
        }

        public Transition(MotionScene motionScene, Context context, XmlPullParser xmlPullParser) {
            this.f955a = -1;
            this.b = false;
            this.c = -1;
            this.d = -1;
            this.e = 0;
            this.f = null;
            this.g = -1;
            this.h = 400;
            this.i = 0.0f;
            this.k = new ArrayList<>();
            this.l = null;
            this.m = new ArrayList<>();
            this.n = 0;
            this.o = false;
            this.p = -1;
            this.q = 0;
            this.r = 0;
            this.h = motionScene.l;
            this.q = motionScene.m;
            this.j = motionScene;
            u(motionScene, context, Xml.asAttributeSet(xmlPullParser));
        }
    }

    public MotionScene(Context context, MotionLayout motionLayout, int i) {
        this.f954a = motionLayout;
        this.s = new ViewTransitionController(motionLayout);
        B(context, i);
        SparseArray<ConstraintSet> sparseArray = this.h;
        int i2 = R.id.motion_base;
        sparseArray.put(i2, new ConstraintSet());
        this.i.put("motion_base", Integer.valueOf(i2));
    }
}
