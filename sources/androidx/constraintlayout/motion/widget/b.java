package androidx.constraintlayout.motion.widget;

import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.core.motion.MotionPaths;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.motion.utils.ViewSpline;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintSet;
import com.clevertap.android.sdk.Constants;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
/* loaded from: classes.dex */
public class b implements Comparable<b> {
    public int j;
    public float v;
    public float h = 1.0f;
    public int i = 0;
    public float k = 0.0f;
    public float l = 0.0f;
    public float m = 0.0f;
    public float n = 0.0f;
    public float o = 1.0f;
    public float p = 1.0f;
    public float q = Float.NaN;
    public float r = Float.NaN;
    public float s = 0.0f;
    public float t = 0.0f;
    public float u = 0.0f;
    public float w = Float.NaN;
    public float x = Float.NaN;
    public LinkedHashMap<String, ConstraintAttribute> y = new LinkedHashMap<>();

    public void a(HashMap<String, ViewSpline> hashMap, int i) {
        for (String str : hashMap.keySet()) {
            ViewSpline viewSpline = hashMap.get(str);
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1249320806:
                    if (str.equals("rotationX")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1249320805:
                    if (str.equals("rotationY")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1225497657:
                    if (str.equals("translationX")) {
                        c = 2;
                        break;
                    }
                    break;
                case -1225497656:
                    if (str.equals("translationY")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1225497655:
                    if (str.equals("translationZ")) {
                        c = 4;
                        break;
                    }
                    break;
                case -1001078227:
                    if (str.equals("progress")) {
                        c = 5;
                        break;
                    }
                    break;
                case -908189618:
                    if (str.equals("scaleX")) {
                        c = 6;
                        break;
                    }
                    break;
                case -908189617:
                    if (str.equals("scaleY")) {
                        c = 7;
                        break;
                    }
                    break;
                case -760884510:
                    if (str.equals(Key.PIVOT_X)) {
                        c = '\b';
                        break;
                    }
                    break;
                case -760884509:
                    if (str.equals(Key.PIVOT_Y)) {
                        c = '\t';
                        break;
                    }
                    break;
                case -40300674:
                    if (str.equals(Key.ROTATION)) {
                        c = '\n';
                        break;
                    }
                    break;
                case -4379043:
                    if (str.equals("elevation")) {
                        c = 11;
                        break;
                    }
                    break;
                case 37232917:
                    if (str.equals("transitionPathRotate")) {
                        c = '\f';
                        break;
                    }
                    break;
                case 92909918:
                    if (str.equals("alpha")) {
                        c = '\r';
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    viewSpline.setPoint(i, Float.isNaN(this.m) ? 0.0f : this.m);
                    break;
                case 1:
                    viewSpline.setPoint(i, Float.isNaN(this.n) ? 0.0f : this.n);
                    break;
                case 2:
                    viewSpline.setPoint(i, Float.isNaN(this.s) ? 0.0f : this.s);
                    break;
                case 3:
                    viewSpline.setPoint(i, Float.isNaN(this.t) ? 0.0f : this.t);
                    break;
                case 4:
                    viewSpline.setPoint(i, Float.isNaN(this.u) ? 0.0f : this.u);
                    break;
                case 5:
                    viewSpline.setPoint(i, Float.isNaN(this.x) ? 0.0f : this.x);
                    break;
                case 6:
                    viewSpline.setPoint(i, Float.isNaN(this.o) ? 1.0f : this.o);
                    break;
                case 7:
                    viewSpline.setPoint(i, Float.isNaN(this.p) ? 1.0f : this.p);
                    break;
                case '\b':
                    viewSpline.setPoint(i, Float.isNaN(this.q) ? 0.0f : this.q);
                    break;
                case '\t':
                    viewSpline.setPoint(i, Float.isNaN(this.r) ? 0.0f : this.r);
                    break;
                case '\n':
                    viewSpline.setPoint(i, Float.isNaN(this.l) ? 0.0f : this.l);
                    break;
                case 11:
                    viewSpline.setPoint(i, Float.isNaN(this.k) ? 0.0f : this.k);
                    break;
                case '\f':
                    viewSpline.setPoint(i, Float.isNaN(this.w) ? 0.0f : this.w);
                    break;
                case '\r':
                    viewSpline.setPoint(i, Float.isNaN(this.h) ? 1.0f : this.h);
                    break;
                default:
                    if (!str.startsWith("CUSTOM")) {
                        Log.e(MotionPaths.TAG, "UNKNOWN spline " + str);
                        break;
                    } else {
                        String str2 = str.split(Constants.SEPARATOR_COMMA)[1];
                        if (this.y.containsKey(str2)) {
                            ConstraintAttribute constraintAttribute = this.y.get(str2);
                            if (viewSpline instanceof ViewSpline.CustomSet) {
                                ((ViewSpline.CustomSet) viewSpline).setPoint(i, constraintAttribute);
                                break;
                            } else {
                                Log.e(MotionPaths.TAG, str + " ViewSpline not a CustomSet frame = " + i + ", value" + constraintAttribute.getValueToInterpolate() + viewSpline);
                                break;
                            }
                        } else {
                            break;
                        }
                    }
            }
        }
    }

    public void b(View view) {
        this.j = view.getVisibility();
        this.h = view.getVisibility() != 0 ? 0.0f : view.getAlpha();
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            this.k = view.getElevation();
        }
        this.l = view.getRotation();
        this.m = view.getRotationX();
        this.n = view.getRotationY();
        this.o = view.getScaleX();
        this.p = view.getScaleY();
        this.q = view.getPivotX();
        this.r = view.getPivotY();
        this.s = view.getTranslationX();
        this.t = view.getTranslationY();
        if (i >= 21) {
            this.u = view.getTranslationZ();
        }
    }

    public void c(ConstraintSet.Constraint constraint) {
        ConstraintSet.PropertySet propertySet = constraint.propertySet;
        int i = propertySet.mVisibilityMode;
        this.i = i;
        int i2 = propertySet.visibility;
        this.j = i2;
        this.h = (i2 == 0 || i != 0) ? propertySet.alpha : 0.0f;
        ConstraintSet.Transform transform = constraint.transform;
        boolean z = transform.applyElevation;
        this.k = transform.elevation;
        this.l = transform.rotation;
        this.m = transform.rotationX;
        this.n = transform.rotationY;
        this.o = transform.scaleX;
        this.p = transform.scaleY;
        this.q = transform.transformPivotX;
        this.r = transform.transformPivotY;
        this.s = transform.translationX;
        this.t = transform.translationY;
        this.u = transform.translationZ;
        Easing.getInterpolator(constraint.motion.mTransitionEasing);
        ConstraintSet.Motion motion = constraint.motion;
        this.w = motion.mPathRotate;
        int i3 = motion.mDrawPath;
        int i4 = motion.mAnimateRelativeTo;
        this.x = constraint.propertySet.mProgress;
        for (String str : constraint.mCustomConstraints.keySet()) {
            ConstraintAttribute constraintAttribute = constraint.mCustomConstraints.get(str);
            if (constraintAttribute.isContinuous()) {
                this.y.put(str, constraintAttribute);
            }
        }
    }

    @Override // java.lang.Comparable
    /* renamed from: d */
    public int compareTo(b bVar) {
        return Float.compare(this.v, bVar.v);
    }

    public final boolean e(float f, float f2) {
        return (Float.isNaN(f) || Float.isNaN(f2)) ? Float.isNaN(f) != Float.isNaN(f2) : Math.abs(f - f2) > 1.0E-6f;
    }

    public void f(b bVar, HashSet<String> hashSet) {
        if (e(this.h, bVar.h)) {
            hashSet.add("alpha");
        }
        if (e(this.k, bVar.k)) {
            hashSet.add("elevation");
        }
        int i = this.j;
        int i2 = bVar.j;
        if (i != i2 && this.i == 0 && (i == 0 || i2 == 0)) {
            hashSet.add("alpha");
        }
        if (e(this.l, bVar.l)) {
            hashSet.add(Key.ROTATION);
        }
        if (!Float.isNaN(this.w) || !Float.isNaN(bVar.w)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.x) || !Float.isNaN(bVar.x)) {
            hashSet.add("progress");
        }
        if (e(this.m, bVar.m)) {
            hashSet.add("rotationX");
        }
        if (e(this.n, bVar.n)) {
            hashSet.add("rotationY");
        }
        if (e(this.q, bVar.q)) {
            hashSet.add(Key.PIVOT_X);
        }
        if (e(this.r, bVar.r)) {
            hashSet.add(Key.PIVOT_Y);
        }
        if (e(this.o, bVar.o)) {
            hashSet.add("scaleX");
        }
        if (e(this.p, bVar.p)) {
            hashSet.add("scaleY");
        }
        if (e(this.s, bVar.s)) {
            hashSet.add("translationX");
        }
        if (e(this.t, bVar.t)) {
            hashSet.add("translationY");
        }
        if (e(this.u, bVar.u)) {
            hashSet.add("translationZ");
        }
    }

    public void g(float f, float f2, float f3, float f4) {
    }

    public void h(Rect rect, View view, int i, float f) {
        g(rect.left, rect.top, rect.width(), rect.height());
        b(view);
        this.q = Float.NaN;
        this.r = Float.NaN;
        if (i == 1) {
            this.l = f - 90.0f;
        } else if (i != 2) {
        } else {
            this.l = f + 90.0f;
        }
    }

    public void i(Rect rect, ConstraintSet constraintSet, int i, int i2) {
        g(rect.left, rect.top, rect.width(), rect.height());
        c(constraintSet.getParameters(i2));
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        return;
                    }
                }
            }
            float f = this.l + 90.0f;
            this.l = f;
            if (f > 180.0f) {
                this.l = f - 360.0f;
                return;
            }
            return;
        }
        this.l -= 90.0f;
    }

    public void j(View view) {
        g(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        b(view);
    }
}
