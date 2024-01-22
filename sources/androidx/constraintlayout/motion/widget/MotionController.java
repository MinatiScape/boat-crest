package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import androidx.constraintlayout.core.motion.utils.CurveFit;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.core.motion.utils.VelocityMatrix;
import androidx.constraintlayout.motion.utils.CustomSupport;
import androidx.constraintlayout.motion.utils.ViewOscillator;
import androidx.constraintlayout.motion.utils.ViewSpline;
import androidx.constraintlayout.motion.utils.ViewState;
import androidx.constraintlayout.motion.utils.ViewTimeCycle;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.clevertap.android.sdk.Constants;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
/* loaded from: classes.dex */
public class MotionController {
    public static final int DRAW_PATH_AS_CONFIGURED = 4;
    public static final int DRAW_PATH_BASIC = 1;
    public static final int DRAW_PATH_CARTESIAN = 3;
    public static final int DRAW_PATH_NONE = 0;
    public static final int DRAW_PATH_RECTANGLE = 5;
    public static final int DRAW_PATH_RELATIVE = 2;
    public static final int DRAW_PATH_SCREEN = 6;
    public static final int HORIZONTAL_PATH_X = 2;
    public static final int HORIZONTAL_PATH_Y = 3;
    public static final int PATH_PERCENT = 0;
    public static final int PATH_PERPENDICULAR = 1;
    public static final int ROTATION_LEFT = 2;
    public static final int ROTATION_RIGHT = 1;
    public static final int VERTICAL_PATH_X = 4;
    public static final int VERTICAL_PATH_Y = 5;
    public HashMap<String, ViewTimeCycle> A;
    public HashMap<String, ViewSpline> B;
    public HashMap<String, ViewOscillator> C;
    public KeyTrigger[] D;
    public int E;
    public int F;
    public View G;
    public int H;
    public float I;
    public Interpolator J;
    public boolean K;
    public View b;
    public int c;
    public CurveFit[] j;
    public CurveFit k;
    public float o;
    public float p;
    public int[] q;
    public double[] r;
    public double[] s;
    public String[] t;
    public int[] u;

    /* renamed from: a  reason: collision with root package name */
    public Rect f946a = new Rect();
    public boolean d = false;
    public int e = -1;
    public c f = new c();
    public c g = new c();
    public b h = new b();
    public b i = new b();
    public float l = Float.NaN;
    public float m = 0.0f;
    public float n = 1.0f;
    public int v = 4;
    public float[] w = new float[4];
    public ArrayList<c> x = new ArrayList<>();
    public float[] y = new float[1];
    public ArrayList<Key> z = new ArrayList<>();

    /* loaded from: classes.dex */
    public class a implements Interpolator {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Easing f947a;

        public a(Easing easing) {
            this.f947a = easing;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            return (float) this.f947a.get(f);
        }
    }

    public MotionController(View view) {
        int i = Key.UNSET;
        this.E = i;
        this.F = i;
        this.G = null;
        this.H = i;
        this.I = Float.NaN;
        this.J = null;
        this.K = false;
        setView(view);
    }

    public static Interpolator j(Context context, int i, String str, int i2) {
        if (i != -2) {
            if (i != -1) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 4) {
                                if (i != 5) {
                                    return null;
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
            return new a(Easing.getInterpolator(str));
        }
        return AnimationUtils.loadInterpolator(context, i2);
    }

    public void a(ArrayList<Key> arrayList) {
        this.z.addAll(arrayList);
    }

    public void addKey(Key key) {
        this.z.add(key);
    }

    public int b(float[] fArr, int[] iArr) {
        if (fArr != null) {
            double[] timePoints = this.j[0].getTimePoints();
            if (iArr != null) {
                Iterator<c> it = this.x.iterator();
                int i = 0;
                while (it.hasNext()) {
                    iArr[i] = it.next().v;
                    i++;
                }
            }
            int i2 = 0;
            for (int i3 = 0; i3 < timePoints.length; i3++) {
                this.j[0].getPos(timePoints[i3], this.r);
                this.f.f(timePoints[i3], this.q, this.r, fArr, i2);
                i2 += 2;
            }
            return i2 / 2;
        }
        return 0;
    }

    public void c(float[] fArr, int i) {
        double d;
        float f;
        float f2 = 1.0f;
        float f3 = 1.0f / (i - 1);
        HashMap<String, ViewSpline> hashMap = this.B;
        ViewSpline viewSpline = hashMap == null ? null : hashMap.get("translationX");
        HashMap<String, ViewSpline> hashMap2 = this.B;
        ViewSpline viewSpline2 = hashMap2 == null ? null : hashMap2.get("translationY");
        HashMap<String, ViewOscillator> hashMap3 = this.C;
        ViewOscillator viewOscillator = hashMap3 == null ? null : hashMap3.get("translationX");
        HashMap<String, ViewOscillator> hashMap4 = this.C;
        ViewOscillator viewOscillator2 = hashMap4 != null ? hashMap4.get("translationY") : null;
        int i2 = 0;
        while (i2 < i) {
            float f4 = i2 * f3;
            float f5 = this.n;
            if (f5 != f2) {
                float f6 = this.m;
                if (f4 < f6) {
                    f4 = 0.0f;
                }
                if (f4 > f6 && f4 < 1.0d) {
                    f4 = Math.min((f4 - f6) * f5, f2);
                }
            }
            float f7 = f4;
            double d2 = f7;
            Easing easing = this.f.h;
            float f8 = Float.NaN;
            Iterator<c> it = this.x.iterator();
            float f9 = 0.0f;
            while (it.hasNext()) {
                c next = it.next();
                Easing easing2 = next.h;
                double d3 = d2;
                if (easing2 != null) {
                    float f10 = next.j;
                    if (f10 < f7) {
                        f9 = f10;
                        easing = easing2;
                    } else if (Float.isNaN(f8)) {
                        f8 = next.j;
                    }
                }
                d2 = d3;
            }
            double d4 = d2;
            if (easing != null) {
                if (Float.isNaN(f8)) {
                    f8 = 1.0f;
                }
                d = (((float) easing.get((f7 - f9) / f)) * (f8 - f9)) + f9;
            } else {
                d = d4;
            }
            this.j[0].getPos(d, this.r);
            CurveFit curveFit = this.k;
            if (curveFit != null) {
                double[] dArr = this.r;
                if (dArr.length > 0) {
                    curveFit.getPos(d, dArr);
                }
            }
            int i3 = i2 * 2;
            int i4 = i2;
            this.f.f(d, this.q, this.r, fArr, i3);
            if (viewOscillator != null) {
                fArr[i3] = fArr[i3] + viewOscillator.get(f7);
            } else if (viewSpline != null) {
                fArr[i3] = fArr[i3] + viewSpline.get(f7);
            }
            if (viewOscillator2 != null) {
                int i5 = i3 + 1;
                fArr[i5] = fArr[i5] + viewOscillator2.get(f7);
            } else if (viewSpline2 != null) {
                int i6 = i3 + 1;
                fArr[i6] = fArr[i6] + viewSpline2.get(f7);
            }
            i2 = i4 + 1;
            f2 = 1.0f;
        }
    }

    public void d(float f, float[] fArr, int i) {
        this.j[0].getPos(g(f, null), this.r);
        this.f.j(this.q, this.r, fArr, i);
    }

    public void e(float[] fArr, int i) {
        float f = 1.0f / (i - 1);
        for (int i2 = 0; i2 < i; i2++) {
            this.j[0].getPos(g(i2 * f, null), this.r);
            this.f.j(this.q, this.r, fArr, i2 * 8);
        }
    }

    public void f(boolean z) {
        if (!"button".equals(Debug.getName(this.b)) || this.D == null) {
            return;
        }
        int i = 0;
        while (true) {
            KeyTrigger[] keyTriggerArr = this.D;
            if (i >= keyTriggerArr.length) {
                return;
            }
            keyTriggerArr[i].conditionallyFire(z ? -100.0f : 100.0f, this.b);
            i++;
        }
    }

    public final float g(float f, float[] fArr) {
        float f2 = 0.0f;
        if (fArr != null) {
            fArr[0] = 1.0f;
        } else {
            float f3 = this.n;
            if (f3 != 1.0d) {
                float f4 = this.m;
                if (f < f4) {
                    f = 0.0f;
                }
                if (f > f4 && f < 1.0d) {
                    f = Math.min((f - f4) * f3, 1.0f);
                }
            }
        }
        Easing easing = this.f.h;
        float f5 = Float.NaN;
        Iterator<c> it = this.x.iterator();
        while (it.hasNext()) {
            c next = it.next();
            Easing easing2 = next.h;
            if (easing2 != null) {
                float f6 = next.j;
                if (f6 < f) {
                    easing = easing2;
                    f2 = f6;
                } else if (Float.isNaN(f5)) {
                    f5 = next.j;
                }
            }
        }
        if (easing != null) {
            float f7 = (Float.isNaN(f5) ? 1.0f : f5) - f2;
            double d = (f - f2) / f7;
            f = (((float) easing.get(d)) * f7) + f2;
            if (fArr != null) {
                fArr[0] = (float) easing.getDiff(d);
            }
        }
        return f;
    }

    public int getAnimateRelativeTo() {
        return this.f.r;
    }

    public void getCenter(double d, float[] fArr, float[] fArr2) {
        double[] dArr = new double[4];
        double[] dArr2 = new double[4];
        this.j[0].getPos(d, dArr);
        this.j[0].getSlope(d, dArr2);
        Arrays.fill(fArr2, 0.0f);
        this.f.g(d, this.q, dArr, fArr, dArr2, fArr2);
    }

    public float getCenterX() {
        return this.o;
    }

    public float getCenterY() {
        return this.p;
    }

    public int getDrawPath() {
        int i = this.f.i;
        Iterator<c> it = this.x.iterator();
        while (it.hasNext()) {
            i = Math.max(i, it.next().i);
        }
        return Math.max(i, this.g.i);
    }

    public float getFinalHeight() {
        return this.g.o;
    }

    public float getFinalWidth() {
        return this.g.n;
    }

    public float getFinalX() {
        return this.g.l;
    }

    public float getFinalY() {
        return this.g.m;
    }

    public int getKeyFrameInfo(int i, int[] iArr) {
        float[] fArr = new float[2];
        Iterator<Key> it = this.z.iterator();
        int i2 = 0;
        int i3 = 0;
        while (it.hasNext()) {
            Key next = it.next();
            int i4 = next.mType;
            if (i4 == i || i != -1) {
                iArr[i3] = 0;
                int i5 = i3 + 1;
                iArr[i5] = i4;
                int i6 = i5 + 1;
                int i7 = next.f939a;
                iArr[i6] = i7;
                double d = i7 / 100.0f;
                this.j[0].getPos(d, this.r);
                this.f.f(d, this.q, this.r, fArr, 0);
                int i8 = i6 + 1;
                iArr[i8] = Float.floatToIntBits(fArr[0]);
                int i9 = i8 + 1;
                iArr[i9] = Float.floatToIntBits(fArr[1]);
                if (next instanceof KeyPosition) {
                    KeyPosition keyPosition = (KeyPosition) next;
                    int i10 = i9 + 1;
                    iArr[i10] = keyPosition.o;
                    int i11 = i10 + 1;
                    iArr[i11] = Float.floatToIntBits(keyPosition.k);
                    i9 = i11 + 1;
                    iArr[i9] = Float.floatToIntBits(keyPosition.l);
                }
                int i12 = i9 + 1;
                iArr[i3] = i12 - i3;
                i2++;
                i3 = i12;
            }
        }
        return i2;
    }

    public int getKeyFramePositions(int[] iArr, float[] fArr) {
        Iterator<Key> it = this.z.iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            Key next = it.next();
            int i3 = next.f939a;
            iArr[i] = (next.mType * 1000) + i3;
            double d = i3 / 100.0f;
            this.j[0].getPos(d, this.r);
            this.f.f(d, this.q, this.r, fArr, i2);
            i2 += 2;
            i++;
        }
        return i;
    }

    public float getStartHeight() {
        return this.f.o;
    }

    public float getStartWidth() {
        return this.f.n;
    }

    public float getStartX() {
        return this.f.l;
    }

    public float getStartY() {
        return this.f.m;
    }

    public int getTransformPivotTarget() {
        return this.F;
    }

    public View getView() {
        return this.b;
    }

    public int h(String str, float[] fArr, int i) {
        ViewSpline viewSpline = this.B.get(str);
        if (viewSpline == null) {
            return -1;
        }
        for (int i2 = 0; i2 < fArr.length; i2++) {
            fArr[i2] = viewSpline.get(i2 / (fArr.length - 1));
        }
        return fArr.length;
    }

    public void i(float f, float f2, float f3, float[] fArr) {
        double[] dArr;
        float g = g(f, this.y);
        CurveFit[] curveFitArr = this.j;
        int i = 0;
        if (curveFitArr != null) {
            double d = g;
            curveFitArr[0].getSlope(d, this.s);
            this.j[0].getPos(d, this.r);
            float f4 = this.y[0];
            while (true) {
                dArr = this.s;
                if (i >= dArr.length) {
                    break;
                }
                dArr[i] = dArr[i] * f4;
                i++;
            }
            CurveFit curveFit = this.k;
            if (curveFit != null) {
                double[] dArr2 = this.r;
                if (dArr2.length > 0) {
                    curveFit.getPos(d, dArr2);
                    this.k.getSlope(d, this.s);
                    this.f.q(f2, f3, fArr, this.q, this.s, this.r);
                    return;
                }
                return;
            }
            this.f.q(f2, f3, fArr, this.q, dArr, this.r);
            return;
        }
        c cVar = this.g;
        float f5 = cVar.l;
        c cVar2 = this.f;
        float f6 = f5 - cVar2.l;
        float f7 = cVar.m - cVar2.m;
        float f8 = (cVar.n - cVar2.n) + f6;
        float f9 = (cVar.o - cVar2.o) + f7;
        fArr[0] = (f6 * (1.0f - f2)) + (f8 * f2);
        fArr[1] = (f7 * (1.0f - f3)) + (f9 * f3);
    }

    public c k(int i) {
        return this.x.get(i);
    }

    public float l(int i, float f, float f2) {
        c cVar = this.g;
        float f3 = cVar.l;
        c cVar2 = this.f;
        float f4 = cVar2.l;
        float f5 = f3 - f4;
        float f6 = cVar.m;
        float f7 = cVar2.m;
        float f8 = f6 - f7;
        float f9 = f4 + (cVar2.n / 2.0f);
        float f10 = f7 + (cVar2.o / 2.0f);
        float hypot = (float) Math.hypot(f5, f8);
        if (hypot < 1.0E-7d) {
            return Float.NaN;
        }
        float f11 = f - f9;
        float f12 = f2 - f10;
        if (((float) Math.hypot(f11, f12)) == 0.0f) {
            return 0.0f;
        }
        float f13 = (f11 * f5) + (f12 * f8);
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i != 5) {
                                return 0.0f;
                            }
                            return f12 / f8;
                        }
                        return f11 / f8;
                    }
                    return f12 / f5;
                }
                return f11 / f5;
            }
            return (float) Math.sqrt((hypot * hypot) - (f13 * f13));
        }
        return f13 / hypot;
    }

    public androidx.constraintlayout.motion.widget.a m(int i, int i2, float f, float f2) {
        RectF rectF = new RectF();
        c cVar = this.f;
        float f3 = cVar.l;
        rectF.left = f3;
        float f4 = cVar.m;
        rectF.top = f4;
        rectF.right = f3 + cVar.n;
        rectF.bottom = f4 + cVar.o;
        RectF rectF2 = new RectF();
        c cVar2 = this.g;
        float f5 = cVar2.l;
        rectF2.left = f5;
        float f6 = cVar2.m;
        rectF2.top = f6;
        rectF2.right = f5 + cVar2.n;
        rectF2.bottom = f6 + cVar2.o;
        Iterator<Key> it = this.z.iterator();
        while (it.hasNext()) {
            Key next = it.next();
            if (next instanceof androidx.constraintlayout.motion.widget.a) {
                androidx.constraintlayout.motion.widget.a aVar = (androidx.constraintlayout.motion.widget.a) next;
                if (aVar.intersects(i, i2, rectF, rectF2, f, f2)) {
                    return aVar;
                }
            }
        }
        return null;
    }

    public void n(float f, int i, int i2, float f2, float f3, float[] fArr) {
        float g = g(f, this.y);
        HashMap<String, ViewSpline> hashMap = this.B;
        ViewSpline viewSpline = hashMap == null ? null : hashMap.get("translationX");
        HashMap<String, ViewSpline> hashMap2 = this.B;
        ViewSpline viewSpline2 = hashMap2 == null ? null : hashMap2.get("translationY");
        HashMap<String, ViewSpline> hashMap3 = this.B;
        ViewSpline viewSpline3 = hashMap3 == null ? null : hashMap3.get(Key.ROTATION);
        HashMap<String, ViewSpline> hashMap4 = this.B;
        ViewSpline viewSpline4 = hashMap4 == null ? null : hashMap4.get("scaleX");
        HashMap<String, ViewSpline> hashMap5 = this.B;
        ViewSpline viewSpline5 = hashMap5 == null ? null : hashMap5.get("scaleY");
        HashMap<String, ViewOscillator> hashMap6 = this.C;
        ViewOscillator viewOscillator = hashMap6 == null ? null : hashMap6.get("translationX");
        HashMap<String, ViewOscillator> hashMap7 = this.C;
        ViewOscillator viewOscillator2 = hashMap7 == null ? null : hashMap7.get("translationY");
        HashMap<String, ViewOscillator> hashMap8 = this.C;
        ViewOscillator viewOscillator3 = hashMap8 == null ? null : hashMap8.get(Key.ROTATION);
        HashMap<String, ViewOscillator> hashMap9 = this.C;
        ViewOscillator viewOscillator4 = hashMap9 == null ? null : hashMap9.get("scaleX");
        HashMap<String, ViewOscillator> hashMap10 = this.C;
        ViewOscillator viewOscillator5 = hashMap10 != null ? hashMap10.get("scaleY") : null;
        VelocityMatrix velocityMatrix = new VelocityMatrix();
        velocityMatrix.clear();
        velocityMatrix.setRotationVelocity(viewSpline3, g);
        velocityMatrix.setTranslationVelocity(viewSpline, viewSpline2, g);
        velocityMatrix.setScaleVelocity(viewSpline4, viewSpline5, g);
        velocityMatrix.setRotationVelocity(viewOscillator3, g);
        velocityMatrix.setTranslationVelocity(viewOscillator, viewOscillator2, g);
        velocityMatrix.setScaleVelocity(viewOscillator4, viewOscillator5, g);
        CurveFit curveFit = this.k;
        if (curveFit != null) {
            double[] dArr = this.r;
            if (dArr.length > 0) {
                double d = g;
                curveFit.getPos(d, dArr);
                this.k.getSlope(d, this.s);
                this.f.q(f2, f3, fArr, this.q, this.s, this.r);
            }
            velocityMatrix.applyTransform(f2, f3, i, i2, fArr);
            return;
        }
        int i3 = 0;
        if (this.j != null) {
            double g2 = g(g, this.y);
            this.j[0].getSlope(g2, this.s);
            this.j[0].getPos(g2, this.r);
            float f4 = this.y[0];
            while (true) {
                double[] dArr2 = this.s;
                if (i3 < dArr2.length) {
                    dArr2[i3] = dArr2[i3] * f4;
                    i3++;
                } else {
                    this.f.q(f2, f3, fArr, this.q, dArr2, this.r);
                    velocityMatrix.applyTransform(f2, f3, i, i2, fArr);
                    return;
                }
            }
        } else {
            c cVar = this.g;
            float f5 = cVar.l;
            c cVar2 = this.f;
            float f6 = f5 - cVar2.l;
            float f7 = cVar.m - cVar2.m;
            ViewOscillator viewOscillator6 = viewOscillator4;
            float f8 = (cVar.o - cVar2.o) + f7;
            fArr[0] = (f6 * (1.0f - f2)) + (((cVar.n - cVar2.n) + f6) * f2);
            fArr[1] = (f7 * (1.0f - f3)) + (f8 * f3);
            velocityMatrix.clear();
            velocityMatrix.setRotationVelocity(viewSpline3, g);
            velocityMatrix.setTranslationVelocity(viewSpline, viewSpline2, g);
            velocityMatrix.setScaleVelocity(viewSpline4, viewSpline5, g);
            velocityMatrix.setRotationVelocity(viewOscillator3, g);
            velocityMatrix.setTranslationVelocity(viewOscillator, viewOscillator2, g);
            velocityMatrix.setScaleVelocity(viewOscillator6, viewOscillator5, g);
            velocityMatrix.applyTransform(f2, f3, i, i2, fArr);
        }
    }

    public final float o() {
        char c;
        float f;
        float f2;
        float[] fArr = new float[2];
        float f3 = 1.0f / 99;
        double d = 0.0d;
        double d2 = 0.0d;
        float f4 = 0.0f;
        int i = 0;
        while (i < 100) {
            float f5 = i * f3;
            double d3 = f5;
            Easing easing = this.f.h;
            Iterator<c> it = this.x.iterator();
            float f6 = Float.NaN;
            float f7 = 0.0f;
            while (it.hasNext()) {
                c next = it.next();
                Easing easing2 = next.h;
                if (easing2 != null) {
                    float f8 = next.j;
                    if (f8 < f5) {
                        easing = easing2;
                        f7 = f8;
                    } else if (Float.isNaN(f6)) {
                        f6 = next.j;
                    }
                }
            }
            if (easing != null) {
                if (Float.isNaN(f6)) {
                    f6 = 1.0f;
                }
                d3 = (((float) easing.get((f5 - f7) / f2)) * (f6 - f7)) + f7;
            }
            this.j[0].getPos(d3, this.r);
            float f9 = f4;
            int i2 = i;
            this.f.f(d3, this.q, this.r, fArr, 0);
            if (i2 > 0) {
                c = 0;
                f = (float) (f9 + Math.hypot(d2 - fArr[1], d - fArr[0]));
            } else {
                c = 0;
                f = f9;
            }
            d = fArr[c];
            i = i2 + 1;
            f4 = f;
            d2 = fArr[1];
        }
        return f4;
    }

    public final void p(c cVar) {
        int binarySearch = Collections.binarySearch(this.x, cVar);
        if (binarySearch == 0) {
            Log.e("MotionController", " KeyPath position \"" + cVar.k + "\" outside of range");
        }
        this.x.add((-binarySearch) - 1, cVar);
    }

    public boolean q(View view, float f, long j, KeyCache keyCache) {
        ViewTimeCycle.PathRotate pathRotate;
        boolean z;
        int i;
        double d;
        View view2;
        float f2;
        float g = g(f, null);
        int i2 = this.H;
        if (i2 != Key.UNSET) {
            float f3 = 1.0f / i2;
            float floor = ((float) Math.floor(g / f3)) * f3;
            float f4 = (g % f3) / f3;
            if (!Float.isNaN(this.I)) {
                f4 = (f4 + this.I) % 1.0f;
            }
            Interpolator interpolator = this.J;
            if (interpolator != null) {
                f2 = interpolator.getInterpolation(f4);
            } else {
                f2 = ((double) f4) > 0.5d ? 1.0f : 0.0f;
            }
            g = (f2 * f3) + floor;
        }
        float f5 = g;
        HashMap<String, ViewSpline> hashMap = this.B;
        if (hashMap != null) {
            for (ViewSpline viewSpline : hashMap.values()) {
                viewSpline.setProperty(view, f5);
            }
        }
        HashMap<String, ViewTimeCycle> hashMap2 = this.A;
        if (hashMap2 != null) {
            ViewTimeCycle.PathRotate pathRotate2 = null;
            boolean z2 = false;
            for (ViewTimeCycle viewTimeCycle : hashMap2.values()) {
                if (viewTimeCycle instanceof ViewTimeCycle.PathRotate) {
                    pathRotate2 = (ViewTimeCycle.PathRotate) viewTimeCycle;
                } else {
                    z2 |= viewTimeCycle.setProperty(view, f5, j, keyCache);
                }
            }
            z = z2;
            pathRotate = pathRotate2;
        } else {
            pathRotate = null;
            z = false;
        }
        CurveFit[] curveFitArr = this.j;
        if (curveFitArr != null) {
            double d2 = f5;
            curveFitArr[0].getPos(d2, this.r);
            this.j[0].getSlope(d2, this.s);
            CurveFit curveFit = this.k;
            if (curveFit != null) {
                double[] dArr = this.r;
                if (dArr.length > 0) {
                    curveFit.getPos(d2, dArr);
                    this.k.getSlope(d2, this.s);
                }
            }
            if (this.K) {
                d = d2;
            } else {
                d = d2;
                this.f.r(f5, view, this.q, this.r, this.s, null, this.d);
                this.d = false;
            }
            if (this.F != Key.UNSET) {
                if (this.G == null) {
                    this.G = ((View) view.getParent()).findViewById(this.F);
                }
                if (this.G != null) {
                    float top = (view2.getTop() + this.G.getBottom()) / 2.0f;
                    float left = (this.G.getLeft() + this.G.getRight()) / 2.0f;
                    if (view.getRight() - view.getLeft() > 0 && view.getBottom() - view.getTop() > 0) {
                        view.setPivotX(left - view.getLeft());
                        view.setPivotY(top - view.getTop());
                    }
                }
            }
            HashMap<String, ViewSpline> hashMap3 = this.B;
            if (hashMap3 != null) {
                for (ViewSpline viewSpline2 : hashMap3.values()) {
                    if (viewSpline2 instanceof ViewSpline.PathRotate) {
                        double[] dArr2 = this.s;
                        if (dArr2.length > 1) {
                            ((ViewSpline.PathRotate) viewSpline2).setPathRotate(view, f5, dArr2[0], dArr2[1]);
                        }
                    }
                }
            }
            if (pathRotate != null) {
                double[] dArr3 = this.s;
                i = 1;
                z |= pathRotate.setPathRotate(view, keyCache, f5, j, dArr3[0], dArr3[1]);
            } else {
                i = 1;
            }
            int i3 = i;
            while (true) {
                CurveFit[] curveFitArr2 = this.j;
                if (i3 >= curveFitArr2.length) {
                    break;
                }
                curveFitArr2[i3].getPos(d, this.w);
                CustomSupport.setInterpolatedValue(this.f.u.get(this.t[i3 - 1]), view, this.w);
                i3++;
            }
            b bVar = this.h;
            if (bVar.i == 0) {
                if (f5 <= 0.0f) {
                    view.setVisibility(bVar.j);
                } else if (f5 >= 1.0f) {
                    view.setVisibility(this.i.j);
                } else if (this.i.j != bVar.j) {
                    view.setVisibility(0);
                }
            }
            if (this.D != null) {
                int i4 = 0;
                while (true) {
                    KeyTrigger[] keyTriggerArr = this.D;
                    if (i4 >= keyTriggerArr.length) {
                        break;
                    }
                    keyTriggerArr[i4].conditionallyFire(f5, view);
                    i4++;
                }
            }
        } else {
            i = 1;
            c cVar = this.f;
            float f6 = cVar.l;
            c cVar2 = this.g;
            float f7 = f6 + ((cVar2.l - f6) * f5);
            float f8 = cVar.m;
            float f9 = f8 + ((cVar2.m - f8) * f5);
            float f10 = cVar.n;
            float f11 = cVar2.n;
            float f12 = cVar.o;
            float f13 = cVar2.o;
            float f14 = f7 + 0.5f;
            int i5 = (int) f14;
            float f15 = f9 + 0.5f;
            int i6 = (int) f15;
            int i7 = (int) (f14 + ((f11 - f10) * f5) + f10);
            int i8 = (int) (f15 + ((f13 - f12) * f5) + f12);
            int i9 = i7 - i5;
            int i10 = i8 - i6;
            if (f11 != f10 || f13 != f12 || this.d) {
                view.measure(View.MeasureSpec.makeMeasureSpec(i9, 1073741824), View.MeasureSpec.makeMeasureSpec(i10, 1073741824));
                this.d = false;
            }
            view.layout(i5, i6, i7, i8);
        }
        HashMap<String, ViewOscillator> hashMap4 = this.C;
        if (hashMap4 != null) {
            for (ViewOscillator viewOscillator : hashMap4.values()) {
                if (viewOscillator instanceof ViewOscillator.PathRotateSet) {
                    double[] dArr4 = this.s;
                    ((ViewOscillator.PathRotateSet) viewOscillator).setPathRotate(view, f5, dArr4[0], dArr4[i]);
                } else {
                    viewOscillator.setProperty(view, f5);
                }
            }
        }
        return z;
    }

    public void r(View view, androidx.constraintlayout.motion.widget.a aVar, float f, float f2, String[] strArr, float[] fArr) {
        RectF rectF = new RectF();
        c cVar = this.f;
        float f3 = cVar.l;
        rectF.left = f3;
        float f4 = cVar.m;
        rectF.top = f4;
        rectF.right = f3 + cVar.n;
        rectF.bottom = f4 + cVar.o;
        RectF rectF2 = new RectF();
        c cVar2 = this.g;
        float f5 = cVar2.l;
        rectF2.left = f5;
        float f6 = cVar2.m;
        rectF2.top = f6;
        rectF2.right = f5 + cVar2.n;
        rectF2.bottom = f6 + cVar2.o;
        aVar.positionAttributes(view, rectF, rectF2, f, f2, strArr, fArr);
    }

    public void remeasure() {
        this.d = true;
    }

    public final void s(c cVar) {
        cVar.p((int) this.b.getX(), (int) this.b.getY(), this.b.getWidth(), this.b.getHeight());
    }

    public void setDrawPath(int i) {
        this.f.i = i;
    }

    public void setPathMotionArc(int i) {
        this.E = i;
    }

    public void setStartState(ViewState viewState, View view, int i, int i2, int i3) {
        c cVar = this.f;
        cVar.j = 0.0f;
        cVar.k = 0.0f;
        Rect rect = new Rect();
        if (i == 1) {
            int i4 = viewState.left + viewState.right;
            rect.left = ((viewState.top + viewState.bottom) - viewState.width()) / 2;
            rect.top = i2 - ((i4 + viewState.height()) / 2);
            rect.right = rect.left + viewState.width();
            rect.bottom = rect.top + viewState.height();
        } else if (i == 2) {
            int i5 = viewState.left + viewState.right;
            rect.left = i3 - (((viewState.top + viewState.bottom) + viewState.width()) / 2);
            rect.top = (i5 - viewState.height()) / 2;
            rect.right = rect.left + viewState.width();
            rect.bottom = rect.top + viewState.height();
        }
        this.f.p(rect.left, rect.top, rect.width(), rect.height());
        this.h.h(rect, view, i, viewState.rotation);
    }

    public void setTransformPivotTarget(int i) {
        this.F = i;
        this.G = null;
    }

    public void setView(View view) {
        this.b = view;
        this.c = view.getId();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            ((ConstraintLayout.LayoutParams) layoutParams).getConstraintTag();
        }
    }

    public void setup(int i, int i2, float f, long j) {
        ArrayList arrayList;
        String[] strArr;
        ConstraintAttribute constraintAttribute;
        ViewTimeCycle makeSpline;
        ConstraintAttribute constraintAttribute2;
        Integer num;
        ViewSpline makeSpline2;
        ConstraintAttribute constraintAttribute3;
        new HashSet();
        HashSet<String> hashSet = new HashSet<>();
        HashSet<String> hashSet2 = new HashSet<>();
        HashSet<String> hashSet3 = new HashSet<>();
        HashMap<String, Integer> hashMap = new HashMap<>();
        int i3 = this.E;
        if (i3 != Key.UNSET) {
            this.f.q = i3;
        }
        this.h.f(this.i, hashSet2);
        ArrayList<Key> arrayList2 = this.z;
        if (arrayList2 != null) {
            Iterator<Key> it = arrayList2.iterator();
            arrayList = null;
            while (it.hasNext()) {
                Key next = it.next();
                if (next instanceof KeyPosition) {
                    KeyPosition keyPosition = (KeyPosition) next;
                    p(new c(i, i2, keyPosition, this.f, this.g));
                    int i4 = keyPosition.e;
                    if (i4 != Key.UNSET) {
                        this.e = i4;
                    }
                } else if (next instanceof KeyCycle) {
                    next.getAttributeNames(hashSet3);
                } else if (next instanceof KeyTimeCycle) {
                    next.getAttributeNames(hashSet);
                } else if (next instanceof KeyTrigger) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add((KeyTrigger) next);
                } else {
                    next.setInterpolation(hashMap);
                    next.getAttributeNames(hashSet2);
                }
            }
        } else {
            arrayList = null;
        }
        int i5 = 0;
        if (arrayList != null) {
            this.D = (KeyTrigger[]) arrayList.toArray(new KeyTrigger[0]);
        }
        char c = 1;
        if (!hashSet2.isEmpty()) {
            this.B = new HashMap<>();
            Iterator<String> it2 = hashSet2.iterator();
            while (it2.hasNext()) {
                String next2 = it2.next();
                if (next2.startsWith("CUSTOM,")) {
                    SparseArray sparseArray = new SparseArray();
                    String str = next2.split(Constants.SEPARATOR_COMMA)[c];
                    Iterator<Key> it3 = this.z.iterator();
                    while (it3.hasNext()) {
                        Key next3 = it3.next();
                        HashMap<String, ConstraintAttribute> hashMap2 = next3.d;
                        if (hashMap2 != null && (constraintAttribute3 = hashMap2.get(str)) != null) {
                            sparseArray.append(next3.f939a, constraintAttribute3);
                        }
                    }
                    makeSpline2 = ViewSpline.makeCustomSpline(next2, sparseArray);
                } else {
                    makeSpline2 = ViewSpline.makeSpline(next2);
                }
                if (makeSpline2 != null) {
                    makeSpline2.setType(next2);
                    this.B.put(next2, makeSpline2);
                }
                c = 1;
            }
            ArrayList<Key> arrayList3 = this.z;
            if (arrayList3 != null) {
                Iterator<Key> it4 = arrayList3.iterator();
                while (it4.hasNext()) {
                    Key next4 = it4.next();
                    if (next4 instanceof KeyAttributes) {
                        next4.addValues(this.B);
                    }
                }
            }
            this.h.a(this.B, 0);
            this.i.a(this.B, 100);
            for (String str2 : this.B.keySet()) {
                int intValue = (!hashMap.containsKey(str2) || (num = hashMap.get(str2)) == null) ? 0 : num.intValue();
                ViewSpline viewSpline = this.B.get(str2);
                if (viewSpline != null) {
                    viewSpline.setup(intValue);
                }
            }
        }
        if (!hashSet.isEmpty()) {
            if (this.A == null) {
                this.A = new HashMap<>();
            }
            Iterator<String> it5 = hashSet.iterator();
            while (it5.hasNext()) {
                String next5 = it5.next();
                if (!this.A.containsKey(next5)) {
                    if (next5.startsWith("CUSTOM,")) {
                        SparseArray sparseArray2 = new SparseArray();
                        String str3 = next5.split(Constants.SEPARATOR_COMMA)[1];
                        Iterator<Key> it6 = this.z.iterator();
                        while (it6.hasNext()) {
                            Key next6 = it6.next();
                            HashMap<String, ConstraintAttribute> hashMap3 = next6.d;
                            if (hashMap3 != null && (constraintAttribute2 = hashMap3.get(str3)) != null) {
                                sparseArray2.append(next6.f939a, constraintAttribute2);
                            }
                        }
                        makeSpline = ViewTimeCycle.makeCustomSpline(next5, sparseArray2);
                    } else {
                        makeSpline = ViewTimeCycle.makeSpline(next5, j);
                    }
                    if (makeSpline != null) {
                        makeSpline.setType(next5);
                        this.A.put(next5, makeSpline);
                    }
                }
            }
            ArrayList<Key> arrayList4 = this.z;
            if (arrayList4 != null) {
                Iterator<Key> it7 = arrayList4.iterator();
                while (it7.hasNext()) {
                    Key next7 = it7.next();
                    if (next7 instanceof KeyTimeCycle) {
                        ((KeyTimeCycle) next7).addTimeValues(this.A);
                    }
                }
            }
            for (String str4 : this.A.keySet()) {
                this.A.get(str4).setup(hashMap.containsKey(str4) ? hashMap.get(str4).intValue() : 0);
            }
        }
        int i6 = 2;
        int size = this.x.size() + 2;
        c[] cVarArr = new c[size];
        cVarArr[0] = this.f;
        cVarArr[size - 1] = this.g;
        if (this.x.size() > 0 && this.e == -1) {
            this.e = 0;
        }
        Iterator<c> it8 = this.x.iterator();
        int i7 = 1;
        while (it8.hasNext()) {
            cVarArr[i7] = it8.next();
            i7++;
        }
        HashSet hashSet4 = new HashSet();
        for (String str5 : this.g.u.keySet()) {
            if (this.f.u.containsKey(str5)) {
                if (!hashSet2.contains("CUSTOM," + str5)) {
                    hashSet4.add(str5);
                }
            }
        }
        String[] strArr2 = (String[]) hashSet4.toArray(new String[0]);
        this.t = strArr2;
        this.u = new int[strArr2.length];
        int i8 = 0;
        while (true) {
            strArr = this.t;
            if (i8 >= strArr.length) {
                break;
            }
            String str6 = strArr[i8];
            this.u[i8] = 0;
            int i9 = 0;
            while (true) {
                if (i9 >= size) {
                    break;
                }
                if (cVarArr[i9].u.containsKey(str6) && (constraintAttribute = cVarArr[i9].u.get(str6)) != null) {
                    int[] iArr = this.u;
                    iArr[i8] = iArr[i8] + constraintAttribute.numberOfInterpolatedValues();
                    break;
                }
                i9++;
            }
            i8++;
        }
        boolean z = cVarArr[0].q != Key.UNSET;
        int length = 18 + strArr.length;
        boolean[] zArr = new boolean[length];
        for (int i10 = 1; i10 < size; i10++) {
            cVarArr[i10].d(cVarArr[i10 - 1], zArr, this.t, z);
        }
        int i11 = 0;
        for (int i12 = 1; i12 < length; i12++) {
            if (zArr[i12]) {
                i11++;
            }
        }
        this.q = new int[i11];
        int max = Math.max(2, i11);
        this.r = new double[max];
        this.s = new double[max];
        int i13 = 0;
        for (int i14 = 1; i14 < length; i14++) {
            if (zArr[i14]) {
                this.q[i13] = i14;
                i13++;
            }
        }
        double[][] dArr = (double[][]) Array.newInstance(double.class, size, this.q.length);
        double[] dArr2 = new double[size];
        for (int i15 = 0; i15 < size; i15++) {
            cVarArr[i15].e(dArr[i15], this.q);
            dArr2[i15] = cVarArr[i15].j;
        }
        int i16 = 0;
        while (true) {
            int[] iArr2 = this.q;
            if (i16 >= iArr2.length) {
                break;
            }
            if (iArr2[i16] < c.y.length) {
                String str7 = c.y[this.q[i16]] + " [";
                for (int i17 = 0; i17 < size; i17++) {
                    str7 = str7 + dArr[i17][i16];
                }
            }
            i16++;
        }
        this.j = new CurveFit[this.t.length + 1];
        int i18 = 0;
        while (true) {
            String[] strArr3 = this.t;
            if (i18 >= strArr3.length) {
                break;
            }
            String str8 = strArr3[i18];
            int i19 = i5;
            int i20 = i19;
            double[] dArr3 = null;
            double[][] dArr4 = null;
            while (i19 < size) {
                if (cVarArr[i19].k(str8)) {
                    if (dArr4 == null) {
                        dArr3 = new double[size];
                        int[] iArr3 = new int[i6];
                        iArr3[1] = cVarArr[i19].i(str8);
                        iArr3[i5] = size;
                        dArr4 = (double[][]) Array.newInstance(double.class, iArr3);
                    }
                    dArr3[i20] = cVarArr[i19].j;
                    cVarArr[i19].h(str8, dArr4[i20], 0);
                    i20++;
                }
                i19++;
                i6 = 2;
                i5 = 0;
            }
            i18++;
            this.j[i18] = CurveFit.get(this.e, Arrays.copyOf(dArr3, i20), (double[][]) Arrays.copyOf(dArr4, i20));
            i6 = 2;
            i5 = 0;
        }
        this.j[0] = CurveFit.get(this.e, dArr2, dArr);
        if (cVarArr[0].q != Key.UNSET) {
            int[] iArr4 = new int[size];
            double[] dArr5 = new double[size];
            double[][] dArr6 = (double[][]) Array.newInstance(double.class, size, 2);
            for (int i21 = 0; i21 < size; i21++) {
                iArr4[i21] = cVarArr[i21].q;
                dArr5[i21] = cVarArr[i21].j;
                dArr6[i21][0] = cVarArr[i21].l;
                dArr6[i21][1] = cVarArr[i21].m;
            }
            this.k = CurveFit.getArc(iArr4, dArr5, dArr6);
        }
        float f2 = Float.NaN;
        this.C = new HashMap<>();
        if (this.z != null) {
            Iterator<String> it9 = hashSet3.iterator();
            while (it9.hasNext()) {
                String next8 = it9.next();
                ViewOscillator makeSpline3 = ViewOscillator.makeSpline(next8);
                if (makeSpline3 != null) {
                    if (makeSpline3.variesByPath() && Float.isNaN(f2)) {
                        f2 = o();
                    }
                    makeSpline3.setType(next8);
                    this.C.put(next8, makeSpline3);
                }
            }
            Iterator<Key> it10 = this.z.iterator();
            while (it10.hasNext()) {
                Key next9 = it10.next();
                if (next9 instanceof KeyCycle) {
                    ((KeyCycle) next9).addCycleValues(this.C);
                }
            }
            for (ViewOscillator viewOscillator : this.C.values()) {
                viewOscillator.setup(f2);
            }
        }
    }

    public void setupRelative(MotionController motionController) {
        this.f.s(motionController, motionController.f);
        this.g.s(motionController, motionController.g);
    }

    public void t(Rect rect, Rect rect2, int i, int i2, int i3) {
        if (i == 1) {
            int i4 = rect.left + rect.right;
            rect2.left = ((rect.top + rect.bottom) - rect.width()) / 2;
            rect2.top = i3 - ((i4 + rect.height()) / 2);
            rect2.right = rect2.left + rect.width();
            rect2.bottom = rect2.top + rect.height();
        } else if (i == 2) {
            int i5 = rect.left + rect.right;
            rect2.left = i2 - (((rect.top + rect.bottom) + rect.width()) / 2);
            rect2.top = (i5 - rect.height()) / 2;
            rect2.right = rect2.left + rect.width();
            rect2.bottom = rect2.top + rect.height();
        } else if (i == 3) {
            int i6 = rect.left + rect.right;
            rect2.left = ((rect.height() / 2) + rect.top) - (i6 / 2);
            rect2.top = i3 - ((i6 + rect.height()) / 2);
            rect2.right = rect2.left + rect.width();
            rect2.bottom = rect2.top + rect.height();
        } else if (i != 4) {
        } else {
            int i7 = rect.left + rect.right;
            rect2.left = i2 - (((rect.bottom + rect.top) + rect.width()) / 2);
            rect2.top = (i7 - rect.height()) / 2;
            rect2.right = rect2.left + rect.width();
            rect2.bottom = rect2.top + rect.height();
        }
    }

    public String toString() {
        return " start: x: " + this.f.l + " y: " + this.f.m + " end: x: " + this.g.l + " y: " + this.g.m;
    }

    public void u(View view) {
        c cVar = this.f;
        cVar.j = 0.0f;
        cVar.k = 0.0f;
        this.K = true;
        cVar.p(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        this.g.p(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        this.h.j(view);
        this.i.j(view);
    }

    public void v(Rect rect, ConstraintSet constraintSet, int i, int i2) {
        int i3 = constraintSet.mRotate;
        if (i3 != 0) {
            t(rect, this.f946a, i3, i, i2);
            rect = this.f946a;
        }
        c cVar = this.g;
        cVar.j = 1.0f;
        cVar.k = 1.0f;
        s(cVar);
        this.g.p(rect.left, rect.top, rect.width(), rect.height());
        this.g.a(constraintSet.getParameters(this.c));
        this.i.i(rect, constraintSet, i3, this.c);
    }

    public void w(View view) {
        c cVar = this.f;
        cVar.j = 0.0f;
        cVar.k = 0.0f;
        cVar.p(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        this.h.j(view);
    }

    public void x(Rect rect, ConstraintSet constraintSet, int i, int i2) {
        int i3 = constraintSet.mRotate;
        if (i3 != 0) {
            t(rect, this.f946a, i3, i, i2);
        }
        c cVar = this.f;
        cVar.j = 0.0f;
        cVar.k = 0.0f;
        s(cVar);
        this.f.p(rect.left, rect.top, rect.width(), rect.height());
        ConstraintSet.Constraint parameters = constraintSet.getParameters(this.c);
        this.f.a(parameters);
        this.l = parameters.motion.mMotionStagger;
        this.h.i(rect, constraintSet, i3, this.c);
        this.F = parameters.transform.transformPivotTarget;
        ConstraintSet.Motion motion = parameters.motion;
        this.H = motion.mQuantizeMotionSteps;
        this.I = motion.mQuantizeMotionPhase;
        Context context = this.b.getContext();
        ConstraintSet.Motion motion2 = parameters.motion;
        this.J = j(context, motion2.mQuantizeInterpolatorType, motion2.mQuantizeInterpolatorString, motion2.mQuantizeInterpolatorID);
    }
}
