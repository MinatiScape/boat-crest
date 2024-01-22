package androidx.constraintlayout.core.motion;

import androidx.constraintlayout.core.motion.utils.Rect;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import androidx.constraintlayout.core.motion.utils.Utils;
import com.clevertap.android.sdk.Constants;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
/* loaded from: classes.dex */
public class a implements Comparable<a> {
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
    public LinkedHashMap<String, CustomVariable> y = new LinkedHashMap<>();

    public void a(HashMap<String, SplineSet> hashMap, int i) {
        for (String str : hashMap.keySet()) {
            SplineSet splineSet = hashMap.get(str);
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
                case -1249320804:
                    if (str.equals("rotationZ")) {
                        c = 2;
                        break;
                    }
                    break;
                case -1225497657:
                    if (str.equals("translationX")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1225497656:
                    if (str.equals("translationY")) {
                        c = 4;
                        break;
                    }
                    break;
                case -1225497655:
                    if (str.equals("translationZ")) {
                        c = 5;
                        break;
                    }
                    break;
                case -1001078227:
                    if (str.equals("progress")) {
                        c = 6;
                        break;
                    }
                    break;
                case -987906986:
                    if (str.equals("pivotX")) {
                        c = 7;
                        break;
                    }
                    break;
                case -987906985:
                    if (str.equals("pivotY")) {
                        c = '\b';
                        break;
                    }
                    break;
                case -908189618:
                    if (str.equals("scaleX")) {
                        c = '\t';
                        break;
                    }
                    break;
                case -908189617:
                    if (str.equals("scaleY")) {
                        c = '\n';
                        break;
                    }
                    break;
                case 92909918:
                    if (str.equals("alpha")) {
                        c = 11;
                        break;
                    }
                    break;
                case 803192288:
                    if (str.equals("pathRotate")) {
                        c = '\f';
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    splineSet.setPoint(i, Float.isNaN(this.m) ? 0.0f : this.m);
                    break;
                case 1:
                    splineSet.setPoint(i, Float.isNaN(this.n) ? 0.0f : this.n);
                    break;
                case 2:
                    splineSet.setPoint(i, Float.isNaN(this.l) ? 0.0f : this.l);
                    break;
                case 3:
                    splineSet.setPoint(i, Float.isNaN(this.s) ? 0.0f : this.s);
                    break;
                case 4:
                    splineSet.setPoint(i, Float.isNaN(this.t) ? 0.0f : this.t);
                    break;
                case 5:
                    splineSet.setPoint(i, Float.isNaN(this.u) ? 0.0f : this.u);
                    break;
                case 6:
                    splineSet.setPoint(i, Float.isNaN(this.x) ? 0.0f : this.x);
                    break;
                case 7:
                    splineSet.setPoint(i, Float.isNaN(this.q) ? 0.0f : this.q);
                    break;
                case '\b':
                    splineSet.setPoint(i, Float.isNaN(this.r) ? 0.0f : this.r);
                    break;
                case '\t':
                    splineSet.setPoint(i, Float.isNaN(this.o) ? 1.0f : this.o);
                    break;
                case '\n':
                    splineSet.setPoint(i, Float.isNaN(this.p) ? 1.0f : this.p);
                    break;
                case 11:
                    splineSet.setPoint(i, Float.isNaN(this.h) ? 1.0f : this.h);
                    break;
                case '\f':
                    splineSet.setPoint(i, Float.isNaN(this.w) ? 0.0f : this.w);
                    break;
                default:
                    if (!str.startsWith("CUSTOM")) {
                        Utils.loge(MotionPaths.TAG, "UNKNOWN spline " + str);
                        break;
                    } else {
                        String str2 = str.split(Constants.SEPARATOR_COMMA)[1];
                        if (this.y.containsKey(str2)) {
                            CustomVariable customVariable = this.y.get(str2);
                            if (splineSet instanceof SplineSet.CustomSpline) {
                                ((SplineSet.CustomSpline) splineSet).setPoint(i, customVariable);
                                break;
                            } else {
                                Utils.loge(MotionPaths.TAG, str + " ViewSpline not a CustomSet frame = " + i + ", value" + customVariable.getValueToInterpolate() + splineSet);
                                break;
                            }
                        } else {
                            break;
                        }
                    }
            }
        }
    }

    public void b(MotionWidget motionWidget) {
        this.j = motionWidget.getVisibility();
        this.h = motionWidget.getVisibility() != 4 ? 0.0f : motionWidget.getAlpha();
        this.l = motionWidget.getRotationZ();
        this.m = motionWidget.getRotationX();
        this.n = motionWidget.getRotationY();
        this.o = motionWidget.getScaleX();
        this.p = motionWidget.getScaleY();
        this.q = motionWidget.getPivotX();
        this.r = motionWidget.getPivotY();
        this.s = motionWidget.getTranslationX();
        this.t = motionWidget.getTranslationY();
        this.u = motionWidget.getTranslationZ();
        for (String str : motionWidget.getCustomAttributeNames()) {
            CustomVariable customAttribute = motionWidget.getCustomAttribute(str);
            if (customAttribute != null && customAttribute.isContinuous()) {
                this.y.put(str, customAttribute);
            }
        }
    }

    @Override // java.lang.Comparable
    /* renamed from: c */
    public int compareTo(a aVar) {
        return Float.compare(this.v, aVar.v);
    }

    public final boolean d(float f, float f2) {
        return (Float.isNaN(f) || Float.isNaN(f2)) ? Float.isNaN(f) != Float.isNaN(f2) : Math.abs(f - f2) > 1.0E-6f;
    }

    public void e(a aVar, HashSet<String> hashSet) {
        if (d(this.h, aVar.h)) {
            hashSet.add("alpha");
        }
        if (d(this.k, aVar.k)) {
            hashSet.add("translationZ");
        }
        int i = this.j;
        int i2 = aVar.j;
        if (i != i2 && this.i == 0 && (i == 4 || i2 == 4)) {
            hashSet.add("alpha");
        }
        if (d(this.l, aVar.l)) {
            hashSet.add("rotationZ");
        }
        if (!Float.isNaN(this.w) || !Float.isNaN(aVar.w)) {
            hashSet.add("pathRotate");
        }
        if (!Float.isNaN(this.x) || !Float.isNaN(aVar.x)) {
            hashSet.add("progress");
        }
        if (d(this.m, aVar.m)) {
            hashSet.add("rotationX");
        }
        if (d(this.n, aVar.n)) {
            hashSet.add("rotationY");
        }
        if (d(this.q, aVar.q)) {
            hashSet.add("pivotX");
        }
        if (d(this.r, aVar.r)) {
            hashSet.add("pivotY");
        }
        if (d(this.o, aVar.o)) {
            hashSet.add("scaleX");
        }
        if (d(this.p, aVar.p)) {
            hashSet.add("scaleY");
        }
        if (d(this.s, aVar.s)) {
            hashSet.add("translationX");
        }
        if (d(this.t, aVar.t)) {
            hashSet.add("translationY");
        }
        if (d(this.u, aVar.u)) {
            hashSet.add("translationZ");
        }
        if (d(this.k, aVar.k)) {
            hashSet.add("elevation");
        }
    }

    public void f(float f, float f2, float f3, float f4) {
    }

    public void g(MotionWidget motionWidget) {
        f(motionWidget.getX(), motionWidget.getY(), motionWidget.getWidth(), motionWidget.getHeight());
        b(motionWidget);
    }

    public void h(Rect rect, MotionWidget motionWidget, int i, float f) {
        f(rect.left, rect.top, rect.width(), rect.height());
        b(motionWidget);
        this.q = Float.NaN;
        this.r = Float.NaN;
        if (i == 1) {
            this.l = f - 90.0f;
        } else if (i != 2) {
        } else {
            this.l = f + 90.0f;
        }
    }
}
