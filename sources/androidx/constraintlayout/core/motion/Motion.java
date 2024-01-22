package androidx.constraintlayout.core.motion;

import androidx.constraintlayout.core.motion.key.MotionKey;
import androidx.constraintlayout.core.motion.key.MotionKeyAttributes;
import androidx.constraintlayout.core.motion.key.MotionKeyCycle;
import androidx.constraintlayout.core.motion.key.MotionKeyPosition;
import androidx.constraintlayout.core.motion.key.MotionKeyTimeCycle;
import androidx.constraintlayout.core.motion.key.MotionKeyTrigger;
import androidx.constraintlayout.core.motion.utils.CurveFit;
import androidx.constraintlayout.core.motion.utils.DifferentialInterpolator;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.core.motion.utils.KeyCycleOscillator;
import androidx.constraintlayout.core.motion.utils.KeyFrameArray;
import androidx.constraintlayout.core.motion.utils.Rect;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.motion.utils.Utils;
import androidx.constraintlayout.core.motion.utils.ViewState;
import com.clevertap.android.sdk.Constants;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
/* loaded from: classes.dex */
public class Motion implements TypedValues {
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
    public int A;
    public MotionWidget B;
    public int C;
    public float D;
    public DifferentialInterpolator E;
    public boolean F;

    /* renamed from: a  reason: collision with root package name */
    public MotionWidget f861a;
    public int b;
    public MotionPaths c;
    public MotionPaths d;
    public androidx.constraintlayout.core.motion.a e;
    public androidx.constraintlayout.core.motion.a f;
    public CurveFit[] g;
    public CurveFit h;
    public float i;
    public float j;
    public float k;
    public float l;
    public int[] m;
    public double[] n;
    public double[] o;
    public String[] p;
    public int[] q;
    public int r;
    public float[] s;
    public ArrayList<MotionPaths> t;
    public ArrayList<MotionKey> u;
    public HashMap<String, TimeCycleSplineSet> v;
    public HashMap<String, SplineSet> w;
    public HashMap<String, KeyCycleOscillator> x;
    public MotionKeyTrigger[] y;
    public int z;

    /* loaded from: classes.dex */
    public static class a implements DifferentialInterpolator {

        /* renamed from: a  reason: collision with root package name */
        public float f862a;
        public final /* synthetic */ Easing b;

        public a(Easing easing) {
            this.b = easing;
        }

        @Override // androidx.constraintlayout.core.motion.utils.DifferentialInterpolator
        public float getInterpolation(float f) {
            this.f862a = f;
            return (float) this.b.get(f);
        }

        @Override // androidx.constraintlayout.core.motion.utils.DifferentialInterpolator
        public float getVelocity() {
            return (float) this.b.getDiff(this.f862a);
        }
    }

    public Motion(MotionWidget motionWidget) {
        new Rect();
        this.b = -1;
        this.c = new MotionPaths();
        this.d = new MotionPaths();
        this.e = new androidx.constraintlayout.core.motion.a();
        this.f = new androidx.constraintlayout.core.motion.a();
        this.i = 0.0f;
        this.j = 1.0f;
        this.r = 4;
        this.s = new float[4];
        this.t = new ArrayList<>();
        this.u = new ArrayList<>();
        this.z = -1;
        this.A = -1;
        this.B = null;
        this.C = -1;
        this.D = Float.NaN;
        this.E = null;
        this.F = false;
        setView(motionWidget);
    }

    public static DifferentialInterpolator b(int i, String str, int i2) {
        if (i != -1) {
            return null;
        }
        return new a(Easing.getInterpolator(str));
    }

    public final float a(float f, float[] fArr) {
        float f2 = 0.0f;
        if (fArr != null) {
            fArr[0] = 1.0f;
        } else {
            float f3 = this.j;
            if (f3 != 1.0d) {
                float f4 = this.i;
                if (f < f4) {
                    f = 0.0f;
                }
                if (f > f4 && f < 1.0d) {
                    f = Math.min((f - f4) * f3, 1.0f);
                }
            }
        }
        Easing easing = this.c.h;
        float f5 = Float.NaN;
        Iterator<MotionPaths> it = this.t.iterator();
        while (it.hasNext()) {
            MotionPaths next = it.next();
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

    public void addKey(MotionKey motionKey) {
        this.u.add(motionKey);
    }

    public int buildKeyFrames(float[] fArr, int[] iArr, int[] iArr2) {
        if (fArr != null) {
            double[] timePoints = this.g[0].getTimePoints();
            if (iArr != null) {
                Iterator<MotionPaths> it = this.t.iterator();
                int i = 0;
                while (it.hasNext()) {
                    iArr[i] = it.next().w;
                    i++;
                }
            }
            if (iArr2 != null) {
                Iterator<MotionPaths> it2 = this.t.iterator();
                int i2 = 0;
                while (it2.hasNext()) {
                    iArr2[i2] = (int) (it2.next().k * 100.0f);
                    i2++;
                }
            }
            int i3 = 0;
            for (int i4 = 0; i4 < timePoints.length; i4++) {
                this.g[0].getPos(timePoints[i4], this.n);
                this.c.d(timePoints[i4], this.m, this.n, fArr, i3);
                i3 += 2;
            }
            return i3 / 2;
        }
        return 0;
    }

    public void buildPath(float[] fArr, int i) {
        double d;
        float f;
        float f2 = 1.0f;
        float f3 = 1.0f / (i - 1);
        HashMap<String, SplineSet> hashMap = this.w;
        SplineSet splineSet = hashMap == null ? null : hashMap.get("translationX");
        HashMap<String, SplineSet> hashMap2 = this.w;
        SplineSet splineSet2 = hashMap2 == null ? null : hashMap2.get("translationY");
        HashMap<String, KeyCycleOscillator> hashMap3 = this.x;
        KeyCycleOscillator keyCycleOscillator = hashMap3 == null ? null : hashMap3.get("translationX");
        HashMap<String, KeyCycleOscillator> hashMap4 = this.x;
        KeyCycleOscillator keyCycleOscillator2 = hashMap4 != null ? hashMap4.get("translationY") : null;
        int i2 = 0;
        while (i2 < i) {
            float f4 = i2 * f3;
            float f5 = this.j;
            if (f5 != f2) {
                float f6 = this.i;
                if (f4 < f6) {
                    f4 = 0.0f;
                }
                if (f4 > f6 && f4 < 1.0d) {
                    f4 = Math.min((f4 - f6) * f5, f2);
                }
            }
            float f7 = f4;
            double d2 = f7;
            Easing easing = this.c.h;
            float f8 = Float.NaN;
            Iterator<MotionPaths> it = this.t.iterator();
            float f9 = 0.0f;
            while (it.hasNext()) {
                MotionPaths next = it.next();
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
            this.g[0].getPos(d, this.n);
            CurveFit curveFit = this.h;
            if (curveFit != null) {
                double[] dArr = this.n;
                if (dArr.length > 0) {
                    curveFit.getPos(d, dArr);
                }
            }
            int i3 = i2 * 2;
            int i4 = i2;
            this.c.d(d, this.m, this.n, fArr, i3);
            if (keyCycleOscillator != null) {
                fArr[i3] = fArr[i3] + keyCycleOscillator.get(f7);
            } else if (splineSet != null) {
                fArr[i3] = fArr[i3] + splineSet.get(f7);
            }
            if (keyCycleOscillator2 != null) {
                int i5 = i3 + 1;
                fArr[i5] = fArr[i5] + keyCycleOscillator2.get(f7);
            } else if (splineSet2 != null) {
                int i6 = i3 + 1;
                fArr[i6] = fArr[i6] + splineSet2.get(f7);
            }
            i2 = i4 + 1;
            f2 = 1.0f;
        }
    }

    public void buildRect(float f, float[] fArr, int i) {
        this.g[0].getPos(a(f, null), this.n);
        this.c.h(this.m, this.n, fArr, i);
    }

    public double[] c(double d) {
        this.g[0].getPos(d, this.n);
        CurveFit curveFit = this.h;
        if (curveFit != null) {
            double[] dArr = this.n;
            if (dArr.length > 0) {
                curveFit.getPos(d, dArr);
            }
        }
        return this.n;
    }

    public final float d() {
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
            Easing easing = this.c.h;
            Iterator<MotionPaths> it = this.t.iterator();
            float f6 = Float.NaN;
            float f7 = 0.0f;
            while (it.hasNext()) {
                MotionPaths next = it.next();
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
            this.g[0].getPos(d3, this.n);
            float f9 = f4;
            int i2 = i;
            this.c.d(d3, this.m, this.n, fArr, 0);
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

    public final void e(MotionPaths motionPaths) {
        Iterator<MotionPaths> it = this.t.iterator();
        MotionPaths motionPaths2 = null;
        while (it.hasNext()) {
            MotionPaths next = it.next();
            if (motionPaths.k == next.k) {
                motionPaths2 = next;
            }
        }
        if (motionPaths2 != null) {
            this.t.remove(motionPaths2);
        }
        int binarySearch = Collections.binarySearch(this.t, motionPaths);
        if (binarySearch == 0) {
            Utils.loge("MotionController", " KeyPath position \"" + motionPaths.k + "\" outside of range");
        }
        this.t.add((-binarySearch) - 1, motionPaths);
    }

    public final void f(MotionPaths motionPaths) {
        motionPaths.n(this.f861a.getX(), this.f861a.getY(), this.f861a.getWidth(), this.f861a.getHeight());
    }

    public int getAnimateRelativeTo() {
        return this.c.s;
    }

    public void getCenter(double d, float[] fArr, float[] fArr2) {
        double[] dArr = new double[4];
        double[] dArr2 = new double[4];
        this.g[0].getPos(d, dArr);
        this.g[0].getSlope(d, dArr2);
        Arrays.fill(fArr2, 0.0f);
        this.c.e(d, this.m, dArr, fArr, dArr2, fArr2);
    }

    public float getCenterX() {
        return this.k;
    }

    public float getCenterY() {
        return this.l;
    }

    public int getDrawPath() {
        int i = this.c.i;
        Iterator<MotionPaths> it = this.t.iterator();
        while (it.hasNext()) {
            i = Math.max(i, it.next().i);
        }
        return Math.max(i, this.d.i);
    }

    public float getFinalHeight() {
        return this.d.o;
    }

    public float getFinalWidth() {
        return this.d.n;
    }

    public float getFinalX() {
        return this.d.l;
    }

    public float getFinalY() {
        return this.d.m;
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public int getId(String str) {
        return 0;
    }

    public MotionPaths getKeyFrame(int i) {
        return this.t.get(i);
    }

    public int getKeyFrameInfo(int i, int[] iArr) {
        float[] fArr = new float[2];
        Iterator<MotionKey> it = this.u.iterator();
        int i2 = 0;
        int i3 = 0;
        while (it.hasNext()) {
            MotionKey next = it.next();
            int i4 = next.mType;
            if (i4 == i || i != -1) {
                iArr[i3] = 0;
                int i5 = i3 + 1;
                iArr[i5] = i4;
                int i6 = i5 + 1;
                int i7 = next.mFramePosition;
                iArr[i6] = i7;
                double d = i7 / 100.0f;
                this.g[0].getPos(d, this.n);
                this.c.d(d, this.m, this.n, fArr, 0);
                int i8 = i6 + 1;
                iArr[i8] = Float.floatToIntBits(fArr[0]);
                int i9 = i8 + 1;
                iArr[i9] = Float.floatToIntBits(fArr[1]);
                if (next instanceof MotionKeyPosition) {
                    MotionKeyPosition motionKeyPosition = (MotionKeyPosition) next;
                    int i10 = i9 + 1;
                    iArr[i10] = motionKeyPosition.mPositionType;
                    int i11 = i10 + 1;
                    iArr[i11] = Float.floatToIntBits(motionKeyPosition.mPercentX);
                    i9 = i11 + 1;
                    iArr[i9] = Float.floatToIntBits(motionKeyPosition.mPercentY);
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
        Iterator<MotionKey> it = this.u.iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            MotionKey next = it.next();
            int i3 = next.mFramePosition;
            iArr[i] = (next.mType * 1000) + i3;
            double d = i3 / 100.0f;
            this.g[0].getPos(d, this.n);
            this.c.d(d, this.m, this.n, fArr, i2);
            i2 += 2;
            i++;
        }
        return i;
    }

    public float getStartHeight() {
        return this.c.o;
    }

    public float getStartWidth() {
        return this.c.n;
    }

    public float getStartX() {
        return this.c.l;
    }

    public float getStartY() {
        return this.c.m;
    }

    public int getTransformPivotTarget() {
        return this.A;
    }

    public MotionWidget getView() {
        return this.f861a;
    }

    public boolean interpolate(MotionWidget motionWidget, float f, long j, KeyCache keyCache) {
        double d;
        MotionWidget motionWidget2;
        float f2;
        float a2 = a(f, null);
        int i = this.C;
        if (i != -1) {
            float f3 = 1.0f / i;
            float floor = ((float) Math.floor(a2 / f3)) * f3;
            float f4 = (a2 % f3) / f3;
            if (!Float.isNaN(this.D)) {
                f4 = (f4 + this.D) % 1.0f;
            }
            DifferentialInterpolator differentialInterpolator = this.E;
            if (differentialInterpolator != null) {
                f2 = differentialInterpolator.getInterpolation(f4);
            } else {
                f2 = ((double) f4) > 0.5d ? 1.0f : 0.0f;
            }
            a2 = (f2 * f3) + floor;
        }
        float f5 = a2;
        HashMap<String, SplineSet> hashMap = this.w;
        if (hashMap != null) {
            for (SplineSet splineSet : hashMap.values()) {
                splineSet.setProperty(motionWidget, f5);
            }
        }
        CurveFit[] curveFitArr = this.g;
        if (curveFitArr != null) {
            double d2 = f5;
            curveFitArr[0].getPos(d2, this.n);
            this.g[0].getSlope(d2, this.o);
            CurveFit curveFit = this.h;
            if (curveFit != null) {
                double[] dArr = this.n;
                if (dArr.length > 0) {
                    curveFit.getPos(d2, dArr);
                    this.h.getSlope(d2, this.o);
                }
            }
            if (this.F) {
                d = d2;
            } else {
                d = d2;
                this.c.o(f5, motionWidget, this.m, this.n, this.o, null);
            }
            if (this.A != -1) {
                if (this.B == null) {
                    this.B = motionWidget.getParent().findViewById(this.A);
                }
                if (this.B != null) {
                    float top = (motionWidget2.getTop() + this.B.getBottom()) / 2.0f;
                    float left = (this.B.getLeft() + this.B.getRight()) / 2.0f;
                    if (motionWidget.getRight() - motionWidget.getLeft() > 0 && motionWidget.getBottom() - motionWidget.getTop() > 0) {
                        motionWidget.setPivotX(left - motionWidget.getLeft());
                        motionWidget.setPivotY(top - motionWidget.getTop());
                    }
                }
            }
            int i2 = 1;
            while (true) {
                CurveFit[] curveFitArr2 = this.g;
                if (i2 >= curveFitArr2.length) {
                    break;
                }
                curveFitArr2[i2].getPos(d, this.s);
                this.c.v.get(this.p[i2 - 1]).setInterpolatedValue(motionWidget, this.s);
                i2++;
            }
            androidx.constraintlayout.core.motion.a aVar = this.e;
            if (aVar.i == 0) {
                if (f5 <= 0.0f) {
                    motionWidget.setVisibility(aVar.j);
                } else if (f5 >= 1.0f) {
                    motionWidget.setVisibility(this.f.j);
                } else if (this.f.j != aVar.j) {
                    motionWidget.setVisibility(4);
                }
            }
            if (this.y != null) {
                int i3 = 0;
                while (true) {
                    MotionKeyTrigger[] motionKeyTriggerArr = this.y;
                    if (i3 >= motionKeyTriggerArr.length) {
                        break;
                    }
                    motionKeyTriggerArr[i3].conditionallyFire(f5, motionWidget);
                    i3++;
                }
            }
        } else {
            MotionPaths motionPaths = this.c;
            float f6 = motionPaths.l;
            MotionPaths motionPaths2 = this.d;
            float f7 = f6 + ((motionPaths2.l - f6) * f5);
            float f8 = motionPaths.m;
            float f9 = f8 + ((motionPaths2.m - f8) * f5);
            float f10 = motionPaths.n;
            float f11 = f10 + ((motionPaths2.n - f10) * f5);
            float f12 = motionPaths.o;
            float f13 = f7 + 0.5f;
            float f14 = f9 + 0.5f;
            motionWidget.layout((int) f13, (int) f14, (int) (f13 + f11), (int) (f14 + f12 + ((motionPaths2.o - f12) * f5)));
        }
        HashMap<String, KeyCycleOscillator> hashMap2 = this.x;
        if (hashMap2 != null) {
            for (KeyCycleOscillator keyCycleOscillator : hashMap2.values()) {
                if (keyCycleOscillator instanceof KeyCycleOscillator.PathRotateSet) {
                    double[] dArr2 = this.o;
                    ((KeyCycleOscillator.PathRotateSet) keyCycleOscillator).setPathRotate(motionWidget, f5, dArr2[0], dArr2[1]);
                } else {
                    keyCycleOscillator.setProperty(motionWidget, f5);
                }
            }
            return false;
        }
        return false;
    }

    public void setDrawPath(int i) {
        this.c.i = i;
    }

    public void setEnd(MotionWidget motionWidget) {
        MotionPaths motionPaths = this.d;
        motionPaths.j = 1.0f;
        motionPaths.k = 1.0f;
        f(motionPaths);
        this.d.n(motionWidget.getLeft(), motionWidget.getTop(), motionWidget.getWidth(), motionWidget.getHeight());
        this.d.applyParameters(motionWidget);
        this.f.g(motionWidget);
    }

    public void setPathMotionArc(int i) {
        this.z = i;
    }

    public void setStart(MotionWidget motionWidget) {
        MotionPaths motionPaths = this.c;
        motionPaths.j = 0.0f;
        motionPaths.k = 0.0f;
        motionPaths.n(motionWidget.getX(), motionWidget.getY(), motionWidget.getWidth(), motionWidget.getHeight());
        this.c.applyParameters(motionWidget);
        this.e.g(motionWidget);
    }

    public void setStartState(ViewState viewState, MotionWidget motionWidget, int i, int i2, int i3) {
        MotionPaths motionPaths = this.c;
        motionPaths.j = 0.0f;
        motionPaths.k = 0.0f;
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
        this.c.n(rect.left, rect.top, rect.width(), rect.height());
        this.e.h(rect, motionWidget, i, viewState.rotation);
    }

    public void setTransformPivotTarget(int i) {
        this.A = i;
        this.B = null;
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, float f) {
        return false;
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, int i2) {
        if (i != 509) {
            return i == 704;
        }
        setPathMotionArc(i2);
        return true;
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, boolean z) {
        return false;
    }

    public void setView(MotionWidget motionWidget) {
        this.f861a = motionWidget;
    }

    public void setup(int i, int i2, float f, long j) {
        ArrayList arrayList;
        String[] strArr;
        Class<double> cls;
        int i3;
        CustomVariable customVariable;
        SplineSet makeSpline;
        CustomVariable customVariable2;
        Integer num;
        Iterator<String> it;
        SplineSet makeSpline2;
        CustomVariable customVariable3;
        Class<double> cls2 = double.class;
        new HashSet();
        HashSet<String> hashSet = new HashSet<>();
        HashSet<String> hashSet2 = new HashSet<>();
        HashSet<String> hashSet3 = new HashSet<>();
        HashMap<String, Integer> hashMap = new HashMap<>();
        int i4 = this.z;
        if (i4 != -1) {
            this.c.r = i4;
        }
        this.e.e(this.f, hashSet2);
        ArrayList<MotionKey> arrayList2 = this.u;
        if (arrayList2 != null) {
            Iterator<MotionKey> it2 = arrayList2.iterator();
            arrayList = null;
            while (it2.hasNext()) {
                MotionKey next = it2.next();
                if (next instanceof MotionKeyPosition) {
                    MotionKeyPosition motionKeyPosition = (MotionKeyPosition) next;
                    e(new MotionPaths(i, i2, motionKeyPosition, this.c, this.d));
                    int i5 = motionKeyPosition.mCurveFit;
                    if (i5 != -1) {
                        this.b = i5;
                    }
                } else if (next instanceof MotionKeyCycle) {
                    next.getAttributeNames(hashSet3);
                } else if (next instanceof MotionKeyTimeCycle) {
                    next.getAttributeNames(hashSet);
                } else if (next instanceof MotionKeyTrigger) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add((MotionKeyTrigger) next);
                } else {
                    next.setInterpolation(hashMap);
                    next.getAttributeNames(hashSet2);
                }
            }
        } else {
            arrayList = null;
        }
        if (arrayList != null) {
            this.y = (MotionKeyTrigger[]) arrayList.toArray(new MotionKeyTrigger[0]);
        }
        char c = 1;
        if (!hashSet2.isEmpty()) {
            this.w = new HashMap<>();
            Iterator<String> it3 = hashSet2.iterator();
            while (it3.hasNext()) {
                String next2 = it3.next();
                if (next2.startsWith("CUSTOM,")) {
                    KeyFrameArray.CustomVar customVar = new KeyFrameArray.CustomVar();
                    String str = next2.split(Constants.SEPARATOR_COMMA)[c];
                    Iterator<MotionKey> it4 = this.u.iterator();
                    while (it4.hasNext()) {
                        MotionKey next3 = it4.next();
                        Iterator<String> it5 = it3;
                        HashMap<String, CustomVariable> hashMap2 = next3.mCustom;
                        if (hashMap2 != null && (customVariable3 = hashMap2.get(str)) != null) {
                            customVar.append(next3.mFramePosition, customVariable3);
                        }
                        it3 = it5;
                    }
                    it = it3;
                    makeSpline2 = SplineSet.makeCustomSplineSet(next2, customVar);
                } else {
                    it = it3;
                    makeSpline2 = SplineSet.makeSpline(next2, j);
                }
                if (makeSpline2 != null) {
                    makeSpline2.setType(next2);
                    this.w.put(next2, makeSpline2);
                }
                it3 = it;
                c = 1;
            }
            ArrayList<MotionKey> arrayList3 = this.u;
            if (arrayList3 != null) {
                Iterator<MotionKey> it6 = arrayList3.iterator();
                while (it6.hasNext()) {
                    MotionKey next4 = it6.next();
                    if (next4 instanceof MotionKeyAttributes) {
                        next4.addValues(this.w);
                    }
                }
            }
            this.e.a(this.w, 0);
            this.f.a(this.w, 100);
            for (String str2 : this.w.keySet()) {
                int intValue = (!hashMap.containsKey(str2) || (num = hashMap.get(str2)) == null) ? 0 : num.intValue();
                SplineSet splineSet = this.w.get(str2);
                if (splineSet != null) {
                    splineSet.setup(intValue);
                }
            }
        }
        if (!hashSet.isEmpty()) {
            if (this.v == null) {
                this.v = new HashMap<>();
            }
            Iterator<String> it7 = hashSet.iterator();
            while (it7.hasNext()) {
                String next5 = it7.next();
                if (!this.v.containsKey(next5)) {
                    if (next5.startsWith("CUSTOM,")) {
                        KeyFrameArray.CustomVar customVar2 = new KeyFrameArray.CustomVar();
                        String str3 = next5.split(Constants.SEPARATOR_COMMA)[1];
                        Iterator<MotionKey> it8 = this.u.iterator();
                        while (it8.hasNext()) {
                            MotionKey next6 = it8.next();
                            HashMap<String, CustomVariable> hashMap3 = next6.mCustom;
                            if (hashMap3 != null && (customVariable2 = hashMap3.get(str3)) != null) {
                                customVar2.append(next6.mFramePosition, customVariable2);
                            }
                        }
                        makeSpline = SplineSet.makeCustomSplineSet(next5, customVar2);
                    } else {
                        makeSpline = SplineSet.makeSpline(next5, j);
                    }
                    if (makeSpline != null) {
                        makeSpline.setType(next5);
                    }
                }
            }
            ArrayList<MotionKey> arrayList4 = this.u;
            if (arrayList4 != null) {
                Iterator<MotionKey> it9 = arrayList4.iterator();
                while (it9.hasNext()) {
                    MotionKey next7 = it9.next();
                    if (next7 instanceof MotionKeyTimeCycle) {
                        ((MotionKeyTimeCycle) next7).addTimeValues(this.v);
                    }
                }
            }
            for (String str4 : this.v.keySet()) {
                this.v.get(str4).setup(hashMap.containsKey(str4) ? hashMap.get(str4).intValue() : 0);
            }
        }
        int i6 = 2;
        int size = this.t.size() + 2;
        MotionPaths[] motionPathsArr = new MotionPaths[size];
        motionPathsArr[0] = this.c;
        motionPathsArr[size - 1] = this.d;
        if (this.t.size() > 0 && this.b == MotionKey.UNSET) {
            this.b = 0;
        }
        Iterator<MotionPaths> it10 = this.t.iterator();
        int i7 = 1;
        while (it10.hasNext()) {
            motionPathsArr[i7] = it10.next();
            i7++;
        }
        HashSet hashSet4 = new HashSet();
        for (String str5 : this.d.v.keySet()) {
            if (this.c.v.containsKey(str5)) {
                if (!hashSet2.contains("CUSTOM," + str5)) {
                    hashSet4.add(str5);
                }
            }
        }
        String[] strArr2 = (String[]) hashSet4.toArray(new String[0]);
        this.p = strArr2;
        this.q = new int[strArr2.length];
        int i8 = 0;
        while (true) {
            strArr = this.p;
            if (i8 >= strArr.length) {
                break;
            }
            String str6 = strArr[i8];
            this.q[i8] = 0;
            int i9 = 0;
            while (true) {
                if (i9 >= size) {
                    break;
                }
                if (motionPathsArr[i9].v.containsKey(str6) && (customVariable = motionPathsArr[i9].v.get(str6)) != null) {
                    int[] iArr = this.q;
                    iArr[i8] = iArr[i8] + customVariable.numberOfInterpolatedValues();
                    break;
                }
                i9++;
            }
            i8++;
        }
        boolean z = motionPathsArr[0].r != -1;
        int length = 18 + strArr.length;
        boolean[] zArr = new boolean[length];
        for (int i10 = 1; i10 < size; i10++) {
            motionPathsArr[i10].b(motionPathsArr[i10 - 1], zArr, this.p, z);
        }
        int i11 = 0;
        for (int i12 = 1; i12 < length; i12++) {
            if (zArr[i12]) {
                i11++;
            }
        }
        this.m = new int[i11];
        int max = Math.max(2, i11);
        this.n = new double[max];
        this.o = new double[max];
        int i13 = 0;
        for (int i14 = 1; i14 < length; i14++) {
            if (zArr[i14]) {
                this.m[i13] = i14;
                i13++;
            }
        }
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) cls2, size, this.m.length);
        double[] dArr2 = new double[size];
        for (int i15 = 0; i15 < size; i15++) {
            motionPathsArr[i15].c(dArr[i15], this.m);
            dArr2[i15] = motionPathsArr[i15].j;
        }
        int i16 = 0;
        while (true) {
            int[] iArr2 = this.m;
            if (i16 >= iArr2.length) {
                break;
            }
            if (iArr2[i16] < MotionPaths.z.length) {
                String str7 = MotionPaths.z[this.m[i16]] + " [";
                for (int i17 = 0; i17 < size; i17++) {
                    str7 = str7 + dArr[i17][i16];
                }
            }
            i16++;
        }
        this.g = new CurveFit[this.p.length + 1];
        int i18 = 0;
        while (true) {
            String[] strArr3 = this.p;
            if (i18 >= strArr3.length) {
                break;
            }
            String str8 = strArr3[i18];
            int i19 = 0;
            int i20 = 0;
            double[] dArr3 = null;
            double[][] dArr4 = null;
            while (i19 < size) {
                if (motionPathsArr[i19].i(str8)) {
                    if (dArr4 == null) {
                        dArr3 = new double[size];
                        int[] iArr3 = new int[i6];
                        iArr3[1] = motionPathsArr[i19].g(str8);
                        i3 = 0;
                        iArr3[0] = size;
                        dArr4 = (double[][]) Array.newInstance((Class<?>) cls2, iArr3);
                    } else {
                        i3 = 0;
                    }
                    cls = cls2;
                    dArr3[i20] = motionPathsArr[i19].j;
                    motionPathsArr[i19].f(str8, dArr4[i20], i3);
                    i20++;
                } else {
                    cls = cls2;
                }
                i19++;
                cls2 = cls;
                i6 = 2;
            }
            i18++;
            this.g[i18] = CurveFit.get(this.b, Arrays.copyOf(dArr3, i20), (double[][]) Arrays.copyOf(dArr4, i20));
            cls2 = cls2;
            i6 = 2;
        }
        Class<double> cls3 = cls2;
        this.g[0] = CurveFit.get(this.b, dArr2, dArr);
        if (motionPathsArr[0].r != -1) {
            int[] iArr4 = new int[size];
            double[] dArr5 = new double[size];
            double[][] dArr6 = (double[][]) Array.newInstance((Class<?>) cls3, size, 2);
            for (int i21 = 0; i21 < size; i21++) {
                iArr4[i21] = motionPathsArr[i21].r;
                dArr5[i21] = motionPathsArr[i21].j;
                dArr6[i21][0] = motionPathsArr[i21].l;
                dArr6[i21][1] = motionPathsArr[i21].m;
            }
            this.h = CurveFit.getArc(iArr4, dArr5, dArr6);
        }
        float f2 = Float.NaN;
        this.x = new HashMap<>();
        if (this.u != null) {
            Iterator<String> it11 = hashSet3.iterator();
            while (it11.hasNext()) {
                String next8 = it11.next();
                KeyCycleOscillator makeWidgetCycle = KeyCycleOscillator.makeWidgetCycle(next8);
                if (makeWidgetCycle != null) {
                    if (makeWidgetCycle.variesByPath() && Float.isNaN(f2)) {
                        f2 = d();
                    }
                    makeWidgetCycle.setType(next8);
                    this.x.put(next8, makeWidgetCycle);
                }
            }
            Iterator<MotionKey> it12 = this.u.iterator();
            while (it12.hasNext()) {
                MotionKey next9 = it12.next();
                if (next9 instanceof MotionKeyCycle) {
                    ((MotionKeyCycle) next9).addCycleValues(this.x);
                }
            }
            for (KeyCycleOscillator keyCycleOscillator : this.x.values()) {
                keyCycleOscillator.setup(f2);
            }
        }
    }

    public void setupRelative(Motion motion) {
        this.c.setupRelative(motion, motion.c);
        this.d.setupRelative(motion, motion.d);
    }

    public String toString() {
        return " start: x: " + this.c.l + " y: " + this.c.m + " end: x: " + this.d.l + " y: " + this.d.m;
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, String str) {
        if (705 == i) {
            PrintStream printStream = System.out;
            printStream.println("TYPE_INTERPOLATOR  " + str);
            this.E = b(-1, str, 0);
        }
        return false;
    }
}
