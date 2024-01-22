package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.view.Display;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.core.widgets.Barrier;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Flow;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.Helper;
import androidx.constraintlayout.core.widgets.HelperWidget;
import androidx.constraintlayout.core.widgets.Placeholder;
import androidx.constraintlayout.core.widgets.VirtualLayout;
import androidx.constraintlayout.motion.utils.StopLogic;
import androidx.constraintlayout.motion.utils.ViewState;
import androidx.constraintlayout.motion.widget.MotionScene;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintLayoutStates;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Constraints;
import androidx.constraintlayout.widget.R;
import androidx.constraintlayout.widget.StateSet;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes.dex */
public class MotionLayout extends ConstraintLayout implements NestedScrollingParent3 {
    public static final int DEBUG_SHOW_NONE = 0;
    public static final int DEBUG_SHOW_PATH = 2;
    public static final int DEBUG_SHOW_PROGRESS = 1;
    public static boolean IS_IN_EDIT_MODE = false;
    public static final int TOUCH_UP_COMPLETE = 0;
    public static final int TOUCH_UP_COMPLETE_TO_END = 2;
    public static final int TOUCH_UP_COMPLETE_TO_START = 1;
    public static final int TOUCH_UP_DECELERATE = 4;
    public static final int TOUCH_UP_DECELERATE_AND_COMPLETE = 5;
    public static final int TOUCH_UP_NEVER_TO_END = 7;
    public static final int TOUCH_UP_NEVER_TO_START = 6;
    public static final int TOUCH_UP_STOP = 3;
    public static final int VELOCITY_LAYOUT = 1;
    public static final int VELOCITY_POST_LAYOUT = 0;
    public static final int VELOCITY_STATIC_LAYOUT = 3;
    public static final int VELOCITY_STATIC_POST_LAYOUT = 2;
    public TransitionListener A;
    public boolean A0;
    public float B;
    public RectF B0;
    public float C;
    public View C0;
    public int D;
    public Matrix D0;
    public g E;
    public ArrayList<Integer> E0;
    public boolean F;
    public StopLogic G;
    public f H;
    public DesignTool I;
    public int J;
    public int K;
    public boolean L;
    public float M;
    public float N;
    public long O;
    public float P;
    public boolean Q;
    public ArrayList<MotionHelper> R;
    public ArrayList<MotionHelper> S;
    public ArrayList<MotionHelper> T;
    public CopyOnWriteArrayList<TransitionListener> U;
    public int V;
    public long W;
    public float a0;
    public int b0;
    public float c0;
    public int d0;
    public int e0;
    public int f0;
    public int g0;
    public MotionScene h;
    public int h0;
    public Interpolator i;
    public int i0;
    public Interpolator j;
    public float j0;
    public float k;
    public KeyCache k0;
    public int l;
    public boolean l0;
    public int m;
    public j m0;
    public boolean mMeasureDuringTransition;
    public int n;
    public Runnable n0;
    public int o;
    public int[] o0;
    public int p;
    public int p0;
    public boolean q;
    public boolean q0;
    public HashMap<View, MotionController> r;
    public int r0;
    public long s;
    public HashMap<View, ViewState> s0;
    public float t;
    public int t0;
    public float u;
    public int u0;
    public float v;
    public int v0;
    public long w;
    public Rect w0;
    public float x;
    public boolean x0;
    public boolean y;
    public k y0;
    public boolean z;
    public h z0;

    /* loaded from: classes.dex */
    public interface MotionTracker {
        void addMovement(MotionEvent motionEvent);

        void clear();

        void computeCurrentVelocity(int i);

        void computeCurrentVelocity(int i, float f);

        float getXVelocity();

        float getXVelocity(int i);

        float getYVelocity();

        float getYVelocity(int i);

        void recycle();
    }

    /* loaded from: classes.dex */
    public interface TransitionListener {
        void onTransitionChange(MotionLayout motionLayout, int i, int i2, float f);

        void onTransitionCompleted(MotionLayout motionLayout, int i);

        void onTransitionStarted(MotionLayout motionLayout, int i, int i2);

        void onTransitionTrigger(MotionLayout motionLayout, int i, boolean z, float f);
    }

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            MotionLayout.this.m0.a();
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            MotionLayout.this.q0 = false;
        }
    }

    /* loaded from: classes.dex */
    public class c implements Runnable {
        public final /* synthetic */ View h;

        public c(MotionLayout motionLayout, View view) {
            this.h = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.h.setNestedScrollingEnabled(true);
        }
    }

    /* loaded from: classes.dex */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            MotionLayout.this.m0.a();
        }
    }

    /* loaded from: classes.dex */
    public static /* synthetic */ class e {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f948a;

        static {
            int[] iArr = new int[k.values().length];
            f948a = iArr;
            try {
                iArr[k.UNDEFINED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f948a[k.SETUP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f948a[k.MOVING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f948a[k.FINISHED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* loaded from: classes.dex */
    public class f extends MotionInterpolator {

        /* renamed from: a  reason: collision with root package name */
        public float f949a = 0.0f;
        public float b = 0.0f;
        public float c;

        public f() {
        }

        public void a(float f, float f2, float f3) {
            this.f949a = f;
            this.b = f2;
            this.c = f3;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionInterpolator, android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2;
            float f3;
            float f4 = this.f949a;
            if (f4 > 0.0f) {
                float f5 = this.c;
                if (f4 / f5 < f) {
                    f = f4 / f5;
                }
                MotionLayout.this.k = f4 - (f5 * f);
                f2 = (f4 * f) - (((f5 * f) * f) / 2.0f);
                f3 = this.b;
            } else {
                float f6 = this.c;
                if ((-f4) / f6 < f) {
                    f = (-f4) / f6;
                }
                MotionLayout.this.k = (f6 * f) + f4;
                f2 = (f4 * f) + (((f6 * f) * f) / 2.0f);
                f3 = this.b;
            }
            return f2 + f3;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionInterpolator
        public float getVelocity() {
            return MotionLayout.this.k;
        }
    }

    /* loaded from: classes.dex */
    public class g {

        /* renamed from: a  reason: collision with root package name */
        public float[] f950a;
        public int[] b;
        public float[] c;
        public Path d;
        public Paint e;
        public Paint f;
        public Paint g;
        public Paint h;
        public Paint i;
        public float[] j;
        public DashPathEffect k;
        public int l;
        public Rect m = new Rect();
        public boolean n = false;
        public int o;

        public g() {
            this.o = 1;
            Paint paint = new Paint();
            this.e = paint;
            paint.setAntiAlias(true);
            this.e.setColor(-21965);
            this.e.setStrokeWidth(2.0f);
            this.e.setStyle(Paint.Style.STROKE);
            Paint paint2 = new Paint();
            this.f = paint2;
            paint2.setAntiAlias(true);
            this.f.setColor(-2067046);
            this.f.setStrokeWidth(2.0f);
            this.f.setStyle(Paint.Style.STROKE);
            Paint paint3 = new Paint();
            this.g = paint3;
            paint3.setAntiAlias(true);
            this.g.setColor(-13391360);
            this.g.setStrokeWidth(2.0f);
            this.g.setStyle(Paint.Style.STROKE);
            Paint paint4 = new Paint();
            this.h = paint4;
            paint4.setAntiAlias(true);
            this.h.setColor(-13391360);
            this.h.setTextSize(MotionLayout.this.getContext().getResources().getDisplayMetrics().density * 12.0f);
            this.j = new float[8];
            Paint paint5 = new Paint();
            this.i = paint5;
            paint5.setAntiAlias(true);
            DashPathEffect dashPathEffect = new DashPathEffect(new float[]{4.0f, 8.0f}, 0.0f);
            this.k = dashPathEffect;
            this.g.setPathEffect(dashPathEffect);
            this.c = new float[100];
            this.b = new int[50];
            if (this.n) {
                this.e.setStrokeWidth(8.0f);
                this.i.setStrokeWidth(8.0f);
                this.f.setStrokeWidth(8.0f);
                this.o = 4;
            }
        }

        public void a(Canvas canvas, HashMap<View, MotionController> hashMap, int i, int i2) {
            if (hashMap == null || hashMap.size() == 0) {
                return;
            }
            canvas.save();
            if (!MotionLayout.this.isInEditMode() && (i2 & 1) == 2) {
                String str = MotionLayout.this.getContext().getResources().getResourceName(MotionLayout.this.n) + ":" + MotionLayout.this.getProgress();
                canvas.drawText(str, 10.0f, MotionLayout.this.getHeight() - 30, this.h);
                canvas.drawText(str, 11.0f, MotionLayout.this.getHeight() - 29, this.e);
            }
            for (MotionController motionController : hashMap.values()) {
                int drawPath = motionController.getDrawPath();
                if (i2 > 0 && drawPath == 0) {
                    drawPath = 1;
                }
                if (drawPath != 0) {
                    this.l = motionController.b(this.c, this.b);
                    if (drawPath >= 1) {
                        int i3 = i / 16;
                        float[] fArr = this.f950a;
                        if (fArr == null || fArr.length != i3 * 2) {
                            this.f950a = new float[i3 * 2];
                            this.d = new Path();
                        }
                        int i4 = this.o;
                        canvas.translate(i4, i4);
                        this.e.setColor(1996488704);
                        this.i.setColor(1996488704);
                        this.f.setColor(1996488704);
                        this.g.setColor(1996488704);
                        motionController.c(this.f950a, i3);
                        b(canvas, drawPath, this.l, motionController);
                        this.e.setColor(-21965);
                        this.f.setColor(-2067046);
                        this.i.setColor(-2067046);
                        this.g.setColor(-13391360);
                        int i5 = this.o;
                        canvas.translate(-i5, -i5);
                        b(canvas, drawPath, this.l, motionController);
                        if (drawPath == 5) {
                            j(canvas, motionController);
                        }
                    }
                }
            }
            canvas.restore();
        }

        public void b(Canvas canvas, int i, int i2, MotionController motionController) {
            if (i == 4) {
                d(canvas);
            }
            if (i == 2) {
                g(canvas);
            }
            if (i == 3) {
                e(canvas);
            }
            c(canvas);
            k(canvas, i, i2, motionController);
        }

        public final void c(Canvas canvas) {
            canvas.drawLines(this.f950a, this.e);
        }

        public final void d(Canvas canvas) {
            boolean z = false;
            boolean z2 = false;
            for (int i = 0; i < this.l; i++) {
                int[] iArr = this.b;
                if (iArr[i] == 1) {
                    z = true;
                }
                if (iArr[i] == 0) {
                    z2 = true;
                }
            }
            if (z) {
                g(canvas);
            }
            if (z2) {
                e(canvas);
            }
        }

        public final void e(Canvas canvas) {
            float[] fArr = this.f950a;
            float f = fArr[0];
            float f2 = fArr[1];
            float f3 = fArr[fArr.length - 2];
            float f4 = fArr[fArr.length - 1];
            canvas.drawLine(Math.min(f, f3), Math.max(f2, f4), Math.max(f, f3), Math.max(f2, f4), this.g);
            canvas.drawLine(Math.min(f, f3), Math.min(f2, f4), Math.min(f, f3), Math.max(f2, f4), this.g);
        }

        public final void f(Canvas canvas, float f, float f2) {
            float[] fArr = this.f950a;
            float f3 = fArr[0];
            float f4 = fArr[1];
            float f5 = fArr[fArr.length - 2];
            float f6 = fArr[fArr.length - 1];
            float min = Math.min(f3, f5);
            float max = Math.max(f4, f6);
            float min2 = f - Math.min(f3, f5);
            float max2 = Math.max(f4, f6) - f2;
            String str = "" + (((int) (((min2 * 100.0f) / Math.abs(f5 - f3)) + 0.5d)) / 100.0f);
            l(str, this.h);
            canvas.drawText(str, ((min2 / 2.0f) - (this.m.width() / 2)) + min, f2 - 20.0f, this.h);
            canvas.drawLine(f, f2, Math.min(f3, f5), f2, this.g);
            String str2 = "" + (((int) (((max2 * 100.0f) / Math.abs(f6 - f4)) + 0.5d)) / 100.0f);
            l(str2, this.h);
            canvas.drawText(str2, f + 5.0f, max - ((max2 / 2.0f) - (this.m.height() / 2)), this.h);
            canvas.drawLine(f, f2, f, Math.max(f4, f6), this.g);
        }

        public final void g(Canvas canvas) {
            float[] fArr = this.f950a;
            canvas.drawLine(fArr[0], fArr[1], fArr[fArr.length - 2], fArr[fArr.length - 1], this.g);
        }

        public final void h(Canvas canvas, float f, float f2) {
            float[] fArr = this.f950a;
            float f3 = fArr[0];
            float f4 = fArr[1];
            float f5 = fArr[fArr.length - 2];
            float f6 = fArr[fArr.length - 1];
            float hypot = (float) Math.hypot(f3 - f5, f4 - f6);
            float f7 = f5 - f3;
            float f8 = f6 - f4;
            float f9 = (((f - f3) * f7) + ((f2 - f4) * f8)) / (hypot * hypot);
            float f10 = f3 + (f7 * f9);
            float f11 = f4 + (f9 * f8);
            Path path = new Path();
            path.moveTo(f, f2);
            path.lineTo(f10, f11);
            float hypot2 = (float) Math.hypot(f10 - f, f11 - f2);
            String str = "" + (((int) ((hypot2 * 100.0f) / hypot)) / 100.0f);
            l(str, this.h);
            canvas.drawTextOnPath(str, path, (hypot2 / 2.0f) - (this.m.width() / 2), -20.0f, this.h);
            canvas.drawLine(f, f2, f10, f11, this.g);
        }

        public final void i(Canvas canvas, float f, float f2, int i, int i2) {
            String str = "" + (((int) ((((f - (i / 2)) * 100.0f) / (MotionLayout.this.getWidth() - i)) + 0.5d)) / 100.0f);
            l(str, this.h);
            canvas.drawText(str, ((f / 2.0f) - (this.m.width() / 2)) + 0.0f, f2 - 20.0f, this.h);
            canvas.drawLine(f, f2, Math.min(0.0f, 1.0f), f2, this.g);
            String str2 = "" + (((int) ((((f2 - (i2 / 2)) * 100.0f) / (MotionLayout.this.getHeight() - i2)) + 0.5d)) / 100.0f);
            l(str2, this.h);
            canvas.drawText(str2, f + 5.0f, 0.0f - ((f2 / 2.0f) - (this.m.height() / 2)), this.h);
            canvas.drawLine(f, f2, f, Math.max(0.0f, 1.0f), this.g);
        }

        public final void j(Canvas canvas, MotionController motionController) {
            this.d.reset();
            for (int i = 0; i <= 50; i++) {
                motionController.d(i / 50, this.j, 0);
                Path path = this.d;
                float[] fArr = this.j;
                path.moveTo(fArr[0], fArr[1]);
                Path path2 = this.d;
                float[] fArr2 = this.j;
                path2.lineTo(fArr2[2], fArr2[3]);
                Path path3 = this.d;
                float[] fArr3 = this.j;
                path3.lineTo(fArr3[4], fArr3[5]);
                Path path4 = this.d;
                float[] fArr4 = this.j;
                path4.lineTo(fArr4[6], fArr4[7]);
                this.d.close();
            }
            this.e.setColor(1140850688);
            canvas.translate(2.0f, 2.0f);
            canvas.drawPath(this.d, this.e);
            canvas.translate(-2.0f, -2.0f);
            this.e.setColor(SupportMenu.CATEGORY_MASK);
            canvas.drawPath(this.d, this.e);
        }

        public final void k(Canvas canvas, int i, int i2, MotionController motionController) {
            int i3;
            int i4;
            float f;
            float f2;
            View view = motionController.b;
            if (view != null) {
                i3 = view.getWidth();
                i4 = motionController.b.getHeight();
            } else {
                i3 = 0;
                i4 = 0;
            }
            for (int i5 = 1; i5 < i2 - 1; i5++) {
                if (i != 4 || this.b[i5 - 1] != 0) {
                    float[] fArr = this.c;
                    int i6 = i5 * 2;
                    float f3 = fArr[i6];
                    float f4 = fArr[i6 + 1];
                    this.d.reset();
                    this.d.moveTo(f3, f4 + 10.0f);
                    this.d.lineTo(f3 + 10.0f, f4);
                    this.d.lineTo(f3, f4 - 10.0f);
                    this.d.lineTo(f3 - 10.0f, f4);
                    this.d.close();
                    int i7 = i5 - 1;
                    motionController.k(i7);
                    if (i == 4) {
                        int[] iArr = this.b;
                        if (iArr[i7] == 1) {
                            h(canvas, f3 - 0.0f, f4 - 0.0f);
                        } else if (iArr[i7] == 0) {
                            f(canvas, f3 - 0.0f, f4 - 0.0f);
                        } else if (iArr[i7] == 2) {
                            f = f4;
                            f2 = f3;
                            i(canvas, f3 - 0.0f, f4 - 0.0f, i3, i4);
                            canvas.drawPath(this.d, this.i);
                        }
                        f = f4;
                        f2 = f3;
                        canvas.drawPath(this.d, this.i);
                    } else {
                        f = f4;
                        f2 = f3;
                    }
                    if (i == 2) {
                        h(canvas, f2 - 0.0f, f - 0.0f);
                    }
                    if (i == 3) {
                        f(canvas, f2 - 0.0f, f - 0.0f);
                    }
                    if (i == 6) {
                        i(canvas, f2 - 0.0f, f - 0.0f, i3, i4);
                    }
                    canvas.drawPath(this.d, this.i);
                }
            }
            float[] fArr2 = this.f950a;
            if (fArr2.length > 1) {
                canvas.drawCircle(fArr2[0], fArr2[1], 8.0f, this.f);
                float[] fArr3 = this.f950a;
                canvas.drawCircle(fArr3[fArr3.length - 2], fArr3[fArr3.length - 1], 8.0f, this.f);
            }
        }

        public void l(String str, Paint paint) {
            paint.getTextBounds(str, 0, str.length(), this.m);
        }
    }

    /* loaded from: classes.dex */
    public class h {

        /* renamed from: a  reason: collision with root package name */
        public ConstraintWidgetContainer f951a = new ConstraintWidgetContainer();
        public ConstraintWidgetContainer b = new ConstraintWidgetContainer();
        public ConstraintSet c = null;
        public ConstraintSet d = null;
        public int e;
        public int f;

        public h() {
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x00e9  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x013d A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void a() {
            /*
                Method dump skipped, instructions count: 360
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.h.a():void");
        }

        public final void b(int i, int i2) {
            int optimizationLevel = MotionLayout.this.getOptimizationLevel();
            MotionLayout motionLayout = MotionLayout.this;
            if (motionLayout.m == motionLayout.getStartState()) {
                MotionLayout motionLayout2 = MotionLayout.this;
                ConstraintWidgetContainer constraintWidgetContainer = this.b;
                ConstraintSet constraintSet = this.d;
                motionLayout2.resolveSystem(constraintWidgetContainer, optimizationLevel, (constraintSet == null || constraintSet.mRotate == 0) ? i : i2, (constraintSet == null || constraintSet.mRotate == 0) ? i2 : i);
                ConstraintSet constraintSet2 = this.c;
                if (constraintSet2 != null) {
                    MotionLayout motionLayout3 = MotionLayout.this;
                    ConstraintWidgetContainer constraintWidgetContainer2 = this.f951a;
                    int i3 = constraintSet2.mRotate;
                    int i4 = i3 == 0 ? i : i2;
                    if (i3 == 0) {
                        i = i2;
                    }
                    motionLayout3.resolveSystem(constraintWidgetContainer2, optimizationLevel, i4, i);
                    return;
                }
                return;
            }
            ConstraintSet constraintSet3 = this.c;
            if (constraintSet3 != null) {
                MotionLayout motionLayout4 = MotionLayout.this;
                ConstraintWidgetContainer constraintWidgetContainer3 = this.f951a;
                int i5 = constraintSet3.mRotate;
                motionLayout4.resolveSystem(constraintWidgetContainer3, optimizationLevel, i5 == 0 ? i : i2, i5 == 0 ? i2 : i);
            }
            MotionLayout motionLayout5 = MotionLayout.this;
            ConstraintWidgetContainer constraintWidgetContainer4 = this.b;
            ConstraintSet constraintSet4 = this.d;
            int i6 = (constraintSet4 == null || constraintSet4.mRotate == 0) ? i : i2;
            if (constraintSet4 == null || constraintSet4.mRotate == 0) {
                i = i2;
            }
            motionLayout5.resolveSystem(constraintWidgetContainer4, optimizationLevel, i6, i);
        }

        public void c(ConstraintWidgetContainer constraintWidgetContainer, ConstraintWidgetContainer constraintWidgetContainer2) {
            ConstraintWidget constraintWidget;
            ArrayList<ConstraintWidget> children = constraintWidgetContainer.getChildren();
            HashMap<ConstraintWidget, ConstraintWidget> hashMap = new HashMap<>();
            hashMap.put(constraintWidgetContainer, constraintWidgetContainer2);
            constraintWidgetContainer2.getChildren().clear();
            constraintWidgetContainer2.copy(constraintWidgetContainer, hashMap);
            Iterator<ConstraintWidget> it = children.iterator();
            while (it.hasNext()) {
                ConstraintWidget next = it.next();
                if (next instanceof Barrier) {
                    constraintWidget = new Barrier();
                } else if (next instanceof Guideline) {
                    constraintWidget = new Guideline();
                } else if (next instanceof Flow) {
                    constraintWidget = new Flow();
                } else if (next instanceof Placeholder) {
                    constraintWidget = new Placeholder();
                } else if (next instanceof Helper) {
                    constraintWidget = new HelperWidget();
                } else {
                    constraintWidget = new ConstraintWidget();
                }
                constraintWidgetContainer2.add(constraintWidget);
                hashMap.put(next, constraintWidget);
            }
            Iterator<ConstraintWidget> it2 = children.iterator();
            while (it2.hasNext()) {
                ConstraintWidget next2 = it2.next();
                hashMap.get(next2).copy(next2, hashMap);
            }
        }

        public ConstraintWidget d(ConstraintWidgetContainer constraintWidgetContainer, View view) {
            if (constraintWidgetContainer.getCompanionWidget() == view) {
                return constraintWidgetContainer;
            }
            ArrayList<ConstraintWidget> children = constraintWidgetContainer.getChildren();
            int size = children.size();
            for (int i = 0; i < size; i++) {
                ConstraintWidget constraintWidget = children.get(i);
                if (constraintWidget.getCompanionWidget() == view) {
                    return constraintWidget;
                }
            }
            return null;
        }

        public void e(ConstraintWidgetContainer constraintWidgetContainer, ConstraintSet constraintSet, ConstraintSet constraintSet2) {
            this.c = constraintSet;
            this.d = constraintSet2;
            this.f951a = new ConstraintWidgetContainer();
            this.b = new ConstraintWidgetContainer();
            this.f951a.setMeasurer(MotionLayout.this.mLayoutWidget.getMeasurer());
            this.b.setMeasurer(MotionLayout.this.mLayoutWidget.getMeasurer());
            this.f951a.removeAllChildren();
            this.b.removeAllChildren();
            c(MotionLayout.this.mLayoutWidget, this.f951a);
            c(MotionLayout.this.mLayoutWidget, this.b);
            if (MotionLayout.this.v > 0.5d) {
                if (constraintSet != null) {
                    j(this.f951a, constraintSet);
                }
                j(this.b, constraintSet2);
            } else {
                j(this.b, constraintSet2);
                if (constraintSet != null) {
                    j(this.f951a, constraintSet);
                }
            }
            this.f951a.setRtl(MotionLayout.this.isRtl());
            this.f951a.updateHierarchy();
            this.b.setRtl(MotionLayout.this.isRtl());
            this.b.updateHierarchy();
            ViewGroup.LayoutParams layoutParams = MotionLayout.this.getLayoutParams();
            if (layoutParams != null) {
                if (layoutParams.width == -2) {
                    ConstraintWidgetContainer constraintWidgetContainer2 = this.f951a;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    constraintWidgetContainer2.setHorizontalDimensionBehaviour(dimensionBehaviour);
                    this.b.setHorizontalDimensionBehaviour(dimensionBehaviour);
                }
                if (layoutParams.height == -2) {
                    ConstraintWidgetContainer constraintWidgetContainer3 = this.f951a;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    constraintWidgetContainer3.setVerticalDimensionBehaviour(dimensionBehaviour2);
                    this.b.setVerticalDimensionBehaviour(dimensionBehaviour2);
                }
            }
        }

        public boolean f(int i, int i2) {
            return (i == this.e && i2 == this.f) ? false : true;
        }

        public void g(int i, int i2) {
            int mode = View.MeasureSpec.getMode(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            MotionLayout motionLayout = MotionLayout.this;
            motionLayout.h0 = mode;
            motionLayout.i0 = mode2;
            motionLayout.getOptimizationLevel();
            b(i, i2);
            if (((MotionLayout.this.getParent() instanceof MotionLayout) && mode == 1073741824 && mode2 == 1073741824) ? false : true) {
                b(i, i2);
                MotionLayout.this.d0 = this.f951a.getWidth();
                MotionLayout.this.e0 = this.f951a.getHeight();
                MotionLayout.this.f0 = this.b.getWidth();
                MotionLayout.this.g0 = this.b.getHeight();
                MotionLayout motionLayout2 = MotionLayout.this;
                motionLayout2.mMeasureDuringTransition = (motionLayout2.d0 == motionLayout2.f0 && motionLayout2.e0 == motionLayout2.g0) ? false : true;
            }
            MotionLayout motionLayout3 = MotionLayout.this;
            int i3 = motionLayout3.d0;
            int i4 = motionLayout3.e0;
            int i5 = motionLayout3.h0;
            if (i5 == Integer.MIN_VALUE || i5 == 0) {
                i3 = (int) (i3 + (motionLayout3.j0 * (motionLayout3.f0 - i3)));
            }
            int i6 = i3;
            int i7 = motionLayout3.i0;
            if (i7 == Integer.MIN_VALUE || i7 == 0) {
                i4 = (int) (i4 + (motionLayout3.j0 * (motionLayout3.g0 - i4)));
            }
            MotionLayout.this.resolveMeasuredDimension(i, i2, i6, i4, this.f951a.isWidthMeasuredTooSmall() || this.b.isWidthMeasuredTooSmall(), this.f951a.isHeightMeasuredTooSmall() || this.b.isHeightMeasuredTooSmall());
        }

        public void h() {
            g(MotionLayout.this.o, MotionLayout.this.p);
            MotionLayout.this.R();
        }

        public void i(int i, int i2) {
            this.e = i;
            this.f = i2;
        }

        public final void j(ConstraintWidgetContainer constraintWidgetContainer, ConstraintSet constraintSet) {
            SparseArray<ConstraintWidget> sparseArray = new SparseArray<>();
            Constraints.LayoutParams layoutParams = new Constraints.LayoutParams(-2, -2);
            sparseArray.clear();
            sparseArray.put(0, constraintWidgetContainer);
            sparseArray.put(MotionLayout.this.getId(), constraintWidgetContainer);
            if (constraintSet != null && constraintSet.mRotate != 0) {
                MotionLayout motionLayout = MotionLayout.this;
                motionLayout.resolveSystem(this.b, motionLayout.getOptimizationLevel(), View.MeasureSpec.makeMeasureSpec(MotionLayout.this.getHeight(), 1073741824), View.MeasureSpec.makeMeasureSpec(MotionLayout.this.getWidth(), 1073741824));
            }
            Iterator<ConstraintWidget> it = constraintWidgetContainer.getChildren().iterator();
            while (it.hasNext()) {
                ConstraintWidget next = it.next();
                next.setAnimated(true);
                sparseArray.put(((View) next.getCompanionWidget()).getId(), next);
            }
            Iterator<ConstraintWidget> it2 = constraintWidgetContainer.getChildren().iterator();
            while (it2.hasNext()) {
                ConstraintWidget next2 = it2.next();
                View view = (View) next2.getCompanionWidget();
                constraintSet.applyToLayoutParams(view.getId(), layoutParams);
                next2.setWidth(constraintSet.getWidth(view.getId()));
                next2.setHeight(constraintSet.getHeight(view.getId()));
                if (view instanceof ConstraintHelper) {
                    constraintSet.applyToHelper((ConstraintHelper) view, next2, layoutParams, sparseArray);
                    if (view instanceof androidx.constraintlayout.widget.Barrier) {
                        ((androidx.constraintlayout.widget.Barrier) view).validateParams();
                    }
                }
                if (Build.VERSION.SDK_INT >= 17) {
                    layoutParams.resolveLayoutDirection(MotionLayout.this.getLayoutDirection());
                } else {
                    layoutParams.resolveLayoutDirection(0);
                }
                MotionLayout.this.applyConstraintsFromLayoutParams(false, view, next2, layoutParams, sparseArray);
                if (constraintSet.getVisibilityMode(view.getId()) == 1) {
                    next2.setVisibility(view.getVisibility());
                } else {
                    next2.setVisibility(constraintSet.getVisibility(view.getId()));
                }
            }
            Iterator<ConstraintWidget> it3 = constraintWidgetContainer.getChildren().iterator();
            while (it3.hasNext()) {
                ConstraintWidget next3 = it3.next();
                if (next3 instanceof VirtualLayout) {
                    Helper helper = (Helper) next3;
                    ((ConstraintHelper) next3.getCompanionWidget()).updatePreLayout(constraintWidgetContainer, helper, sparseArray);
                    ((VirtualLayout) helper).captureWidgets();
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public class j {

        /* renamed from: a  reason: collision with root package name */
        public float f953a = Float.NaN;
        public float b = Float.NaN;
        public int c = -1;
        public int d = -1;

        public j() {
        }

        public void a() {
            int i = this.c;
            if (i != -1 || this.d != -1) {
                if (i == -1) {
                    MotionLayout.this.transitionToState(this.d);
                } else {
                    int i2 = this.d;
                    if (i2 == -1) {
                        MotionLayout.this.setState(i, -1, -1);
                    } else {
                        MotionLayout.this.setTransition(i, i2);
                    }
                }
                MotionLayout.this.setState(k.SETUP);
            }
            if (Float.isNaN(this.b)) {
                if (Float.isNaN(this.f953a)) {
                    return;
                }
                MotionLayout.this.setProgress(this.f953a);
                return;
            }
            MotionLayout.this.setProgress(this.f953a, this.b);
            this.f953a = Float.NaN;
            this.b = Float.NaN;
            this.c = -1;
            this.d = -1;
        }

        public Bundle b() {
            Bundle bundle = new Bundle();
            bundle.putFloat("motion.progress", this.f953a);
            bundle.putFloat("motion.velocity", this.b);
            bundle.putInt("motion.StartState", this.c);
            bundle.putInt("motion.EndState", this.d);
            return bundle;
        }

        public void c() {
            this.d = MotionLayout.this.n;
            this.c = MotionLayout.this.l;
            this.b = MotionLayout.this.getVelocity();
            this.f953a = MotionLayout.this.getProgress();
        }

        public void d(int i) {
            this.d = i;
        }

        public void e(float f) {
            this.f953a = f;
        }

        public void f(int i) {
            this.c = i;
        }

        public void g(Bundle bundle) {
            this.f953a = bundle.getFloat("motion.progress");
            this.b = bundle.getFloat("motion.velocity");
            this.c = bundle.getInt("motion.StartState");
            this.d = bundle.getInt("motion.EndState");
        }

        public void h(float f) {
            this.b = f;
        }
    }

    /* loaded from: classes.dex */
    public enum k {
        UNDEFINED,
        SETUP,
        MOVING,
        FINISHED
    }

    public MotionLayout(@NonNull Context context) {
        super(context);
        this.j = null;
        this.k = 0.0f;
        this.l = -1;
        this.m = -1;
        this.n = -1;
        this.o = 0;
        this.p = 0;
        this.q = true;
        this.r = new HashMap<>();
        this.s = 0L;
        this.t = 1.0f;
        this.u = 0.0f;
        this.v = 0.0f;
        this.x = 0.0f;
        this.z = false;
        this.D = 0;
        this.F = false;
        this.G = new StopLogic();
        this.H = new f();
        this.L = false;
        this.Q = false;
        this.R = null;
        this.S = null;
        this.T = null;
        this.U = null;
        this.V = 0;
        this.W = -1L;
        this.a0 = 0.0f;
        this.b0 = 0;
        this.c0 = 0.0f;
        this.mMeasureDuringTransition = false;
        this.k0 = new KeyCache();
        this.l0 = false;
        this.n0 = null;
        this.o0 = null;
        this.p0 = 0;
        this.q0 = false;
        this.r0 = 0;
        this.s0 = new HashMap<>();
        this.w0 = new Rect();
        this.x0 = false;
        this.y0 = k.UNDEFINED;
        this.z0 = new h();
        this.A0 = false;
        this.B0 = new RectF();
        this.C0 = null;
        this.D0 = null;
        this.E0 = new ArrayList<>();
        N(null);
    }

    public static boolean T(float f2, float f3, float f4) {
        if (f2 > 0.0f) {
            float f5 = f2 / f4;
            return f3 + ((f2 * f5) - (((f4 * f5) * f5) / 2.0f)) > 1.0f;
        }
        float f6 = (-f2) / f4;
        return f3 + ((f2 * f6) + (((f4 * f6) * f6) / 2.0f)) < 0.0f;
    }

    public final void A() {
        MotionScene motionScene = this.h;
        if (motionScene == null) {
            Log.e("MotionLayout", "CHECK: motion scene not set! set \"app:layoutDescription=\"@xml/file\"");
            return;
        }
        int x = motionScene.x();
        MotionScene motionScene2 = this.h;
        B(x, motionScene2.h(motionScene2.x()));
        SparseIntArray sparseIntArray = new SparseIntArray();
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        Iterator<MotionScene.Transition> it = this.h.getDefinedTransitions().iterator();
        while (it.hasNext()) {
            MotionScene.Transition next = it.next();
            if (next == this.h.c) {
                Log.v("MotionLayout", "CHECK: CURRENT");
            }
            C(next);
            int startConstraintSetId = next.getStartConstraintSetId();
            int endConstraintSetId = next.getEndConstraintSetId();
            String name = Debug.getName(getContext(), startConstraintSetId);
            String name2 = Debug.getName(getContext(), endConstraintSetId);
            if (sparseIntArray.get(startConstraintSetId) == endConstraintSetId) {
                Log.e("MotionLayout", "CHECK: two transitions with the same start and end " + name + "->" + name2);
            }
            if (sparseIntArray2.get(endConstraintSetId) == startConstraintSetId) {
                Log.e("MotionLayout", "CHECK: you can't have reverse transitions" + name + "->" + name2);
            }
            sparseIntArray.put(startConstraintSetId, endConstraintSetId);
            sparseIntArray2.put(endConstraintSetId, startConstraintSetId);
            if (this.h.h(startConstraintSetId) == null) {
                Log.e("MotionLayout", " no such constraintSetStart " + name);
            }
            if (this.h.h(endConstraintSetId) == null) {
                Log.e("MotionLayout", " no such constraintSetEnd " + name);
            }
        }
    }

    public final void B(int i2, ConstraintSet constraintSet) {
        View childAt;
        String name = Debug.getName(getContext(), i2);
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            int id = getChildAt(i3).getId();
            if (id == -1) {
                Log.w("MotionLayout", "CHECK: " + name + " ALL VIEWS SHOULD HAVE ID's " + childAt.getClass().getName() + " does not!");
            }
            if (constraintSet.getConstraint(id) == null) {
                Log.w("MotionLayout", "CHECK: " + name + " NO CONSTRAINTS for " + Debug.getName(childAt));
            }
        }
        int[] knownIds = constraintSet.getKnownIds();
        for (int i4 = 0; i4 < knownIds.length; i4++) {
            int i5 = knownIds[i4];
            String name2 = Debug.getName(getContext(), i5);
            if (findViewById(knownIds[i4]) == null) {
                Log.w("MotionLayout", "CHECK: " + name + " NO View matches id " + name2);
            }
            if (constraintSet.getHeight(i5) == -1) {
                Log.w("MotionLayout", "CHECK: " + name + "(" + name2 + ") no LAYOUT_HEIGHT");
            }
            if (constraintSet.getWidth(i5) == -1) {
                Log.w("MotionLayout", "CHECK: " + name + "(" + name2 + ") no LAYOUT_HEIGHT");
            }
        }
    }

    public final void C(MotionScene.Transition transition) {
        if (transition.getStartConstraintSetId() == transition.getEndConstraintSetId()) {
            Log.e("MotionLayout", "CHECK: start and end constraint set should not be the same!");
        }
    }

    public final void D() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            MotionController motionController = this.r.get(childAt);
            if (motionController != null) {
                motionController.w(childAt);
            }
        }
    }

    public void E(boolean z) {
        MotionScene motionScene = this.h;
        if (motionScene == null) {
            return;
        }
        motionScene.disableAutoTransition(z);
    }

    public void F(boolean z) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            MotionController motionController = this.r.get(getChildAt(i2));
            if (motionController != null) {
                motionController.f(z);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0228  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x016f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void G(boolean r24) {
        /*
            Method dump skipped, instructions count: 629
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.G(boolean):void");
    }

    public final void H() {
        boolean z;
        float signum = Math.signum(this.x - this.v);
        long nanoTime = getNanoTime();
        Interpolator interpolator = this.i;
        float f2 = this.v + (!(interpolator instanceof StopLogic) ? ((((float) (nanoTime - this.w)) * signum) * 1.0E-9f) / this.t : 0.0f);
        if (this.y) {
            f2 = this.x;
        }
        int i2 = (signum > 0.0f ? 1 : (signum == 0.0f ? 0 : -1));
        if ((i2 <= 0 || f2 < this.x) && (signum > 0.0f || f2 > this.x)) {
            z = false;
        } else {
            f2 = this.x;
            z = true;
        }
        if (interpolator != null && !z) {
            if (this.F) {
                f2 = interpolator.getInterpolation(((float) (nanoTime - this.s)) * 1.0E-9f);
            } else {
                f2 = interpolator.getInterpolation(f2);
            }
        }
        if ((i2 > 0 && f2 >= this.x) || (signum <= 0.0f && f2 <= this.x)) {
            f2 = this.x;
        }
        this.j0 = f2;
        int childCount = getChildCount();
        long nanoTime2 = getNanoTime();
        Interpolator interpolator2 = this.j;
        if (interpolator2 != null) {
            f2 = interpolator2.getInterpolation(f2);
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            MotionController motionController = this.r.get(childAt);
            if (motionController != null) {
                motionController.q(childAt, f2, nanoTime2, this.k0);
            }
        }
        if (this.mMeasureDuringTransition) {
            requestLayout();
        }
    }

    public final void I() {
        CopyOnWriteArrayList<TransitionListener> copyOnWriteArrayList;
        if ((this.A == null && ((copyOnWriteArrayList = this.U) == null || copyOnWriteArrayList.isEmpty())) || this.c0 == this.u) {
            return;
        }
        if (this.b0 != -1) {
            TransitionListener transitionListener = this.A;
            if (transitionListener != null) {
                transitionListener.onTransitionStarted(this, this.l, this.n);
            }
            CopyOnWriteArrayList<TransitionListener> copyOnWriteArrayList2 = this.U;
            if (copyOnWriteArrayList2 != null) {
                Iterator<TransitionListener> it = copyOnWriteArrayList2.iterator();
                while (it.hasNext()) {
                    it.next().onTransitionStarted(this, this.l, this.n);
                }
            }
        }
        this.b0 = -1;
        float f2 = this.u;
        this.c0 = f2;
        TransitionListener transitionListener2 = this.A;
        if (transitionListener2 != null) {
            transitionListener2.onTransitionChange(this, this.l, this.n, f2);
        }
        CopyOnWriteArrayList<TransitionListener> copyOnWriteArrayList3 = this.U;
        if (copyOnWriteArrayList3 != null) {
            Iterator<TransitionListener> it2 = copyOnWriteArrayList3.iterator();
            while (it2.hasNext()) {
                it2.next().onTransitionChange(this, this.l, this.n, this.u);
            }
        }
    }

    public void J(int i2, float f2, float f3, float f4, float[] fArr) {
        String resourceName;
        HashMap<View, MotionController> hashMap = this.r;
        View viewById = getViewById(i2);
        MotionController motionController = hashMap.get(viewById);
        if (motionController != null) {
            motionController.i(f2, f3, f4, fArr);
            float y = viewById.getY();
            this.B = f2;
            this.C = y;
            return;
        }
        if (viewById == null) {
            resourceName = "" + i2;
        } else {
            resourceName = viewById.getContext().getResources().getResourceName(i2);
        }
        Log.w("MotionLayout", "WARNING could not find view id " + resourceName);
    }

    public String K(int i2) {
        MotionScene motionScene = this.h;
        if (motionScene == null) {
            return null;
        }
        return motionScene.lookUpConstraintName(i2);
    }

    public MotionController L(int i2) {
        return this.r.get(findViewById(i2));
    }

    public final boolean M(float f2, float f3, View view, MotionEvent motionEvent) {
        boolean z;
        View childAt;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                if (M((childAt.getLeft() + f2) - view.getScrollX(), (childAt.getTop() + f3) - view.getScrollY(), viewGroup.getChildAt(childCount), motionEvent)) {
                    z = true;
                    break;
                }
            }
        }
        z = false;
        if (!z) {
            this.B0.set(f2, f3, (view.getRight() + f2) - view.getLeft(), (view.getBottom() + f3) - view.getTop());
            if ((motionEvent.getAction() != 0 || this.B0.contains(motionEvent.getX(), motionEvent.getY())) && z(view, motionEvent, -f2, -f3)) {
                return true;
            }
        }
        return z;
    }

    public final void N(AttributeSet attributeSet) {
        MotionScene motionScene;
        IS_IN_EDIT_MODE = isInEditMode();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.MotionLayout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            boolean z = true;
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.MotionLayout_layoutDescription) {
                    this.h = new MotionScene(getContext(), this, obtainStyledAttributes.getResourceId(index, -1));
                } else if (index == R.styleable.MotionLayout_currentState) {
                    this.m = obtainStyledAttributes.getResourceId(index, -1);
                } else if (index == R.styleable.MotionLayout_motionProgress) {
                    this.x = obtainStyledAttributes.getFloat(index, 0.0f);
                    this.z = true;
                } else if (index == R.styleable.MotionLayout_applyMotionScene) {
                    z = obtainStyledAttributes.getBoolean(index, z);
                } else if (index == R.styleable.MotionLayout_showPaths) {
                    if (this.D == 0) {
                        this.D = obtainStyledAttributes.getBoolean(index, false) ? 2 : 0;
                    }
                } else if (index == R.styleable.MotionLayout_motionDebug) {
                    this.D = obtainStyledAttributes.getInt(index, 0);
                }
            }
            obtainStyledAttributes.recycle();
            if (this.h == null) {
                Log.e("MotionLayout", "WARNING NO app:layoutDescription tag");
            }
            if (!z) {
                this.h = null;
            }
        }
        if (this.D != 0) {
            A();
        }
        if (this.m != -1 || (motionScene = this.h) == null) {
            return;
        }
        this.m = motionScene.x();
        this.l = this.h.x();
        this.n = this.h.j();
    }

    public int O(String str) {
        MotionScene motionScene = this.h;
        if (motionScene == null) {
            return 0;
        }
        return motionScene.lookUpConstraintId(str);
    }

    public void P() {
        MotionScene motionScene = this.h;
        if (motionScene == null) {
            return;
        }
        if (motionScene.f(this, this.m)) {
            requestLayout();
            return;
        }
        int i2 = this.m;
        if (i2 != -1) {
            this.h.addOnClickListeners(this, i2);
        }
        if (this.h.N()) {
            this.h.M();
        }
    }

    public final void Q() {
        CopyOnWriteArrayList<TransitionListener> copyOnWriteArrayList;
        if (this.A == null && ((copyOnWriteArrayList = this.U) == null || copyOnWriteArrayList.isEmpty())) {
            return;
        }
        Iterator<Integer> it = this.E0.iterator();
        while (it.hasNext()) {
            Integer next = it.next();
            TransitionListener transitionListener = this.A;
            if (transitionListener != null) {
                transitionListener.onTransitionCompleted(this, next.intValue());
            }
            CopyOnWriteArrayList<TransitionListener> copyOnWriteArrayList2 = this.U;
            if (copyOnWriteArrayList2 != null) {
                Iterator<TransitionListener> it2 = copyOnWriteArrayList2.iterator();
                while (it2.hasNext()) {
                    it2.next().onTransitionCompleted(this, next.intValue());
                }
            }
        }
        this.E0.clear();
    }

    public final void R() {
        int childCount = getChildCount();
        this.z0.a();
        boolean z = true;
        this.z = true;
        SparseArray sparseArray = new SparseArray();
        int i2 = 0;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            sparseArray.put(childAt.getId(), this.r.get(childAt));
        }
        int width = getWidth();
        int height = getHeight();
        int gatPathMotionArc = this.h.gatPathMotionArc();
        if (gatPathMotionArc != -1) {
            for (int i4 = 0; i4 < childCount; i4++) {
                MotionController motionController = this.r.get(getChildAt(i4));
                if (motionController != null) {
                    motionController.setPathMotionArc(gatPathMotionArc);
                }
            }
        }
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
        int[] iArr = new int[this.r.size()];
        int i5 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            MotionController motionController2 = this.r.get(getChildAt(i6));
            if (motionController2.getAnimateRelativeTo() != -1) {
                sparseBooleanArray.put(motionController2.getAnimateRelativeTo(), true);
                iArr[i5] = motionController2.getAnimateRelativeTo();
                i5++;
            }
        }
        if (this.T != null) {
            for (int i7 = 0; i7 < i5; i7++) {
                MotionController motionController3 = this.r.get(findViewById(iArr[i7]));
                if (motionController3 != null) {
                    this.h.getKeyFrames(motionController3);
                }
            }
            Iterator<MotionHelper> it = this.T.iterator();
            while (it.hasNext()) {
                it.next().onPreSetup(this, this.r);
            }
            for (int i8 = 0; i8 < i5; i8++) {
                MotionController motionController4 = this.r.get(findViewById(iArr[i8]));
                if (motionController4 != null) {
                    motionController4.setup(width, height, this.t, getNanoTime());
                }
            }
        } else {
            for (int i9 = 0; i9 < i5; i9++) {
                MotionController motionController5 = this.r.get(findViewById(iArr[i9]));
                if (motionController5 != null) {
                    this.h.getKeyFrames(motionController5);
                    motionController5.setup(width, height, this.t, getNanoTime());
                }
            }
        }
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt2 = getChildAt(i10);
            MotionController motionController6 = this.r.get(childAt2);
            if (!sparseBooleanArray.get(childAt2.getId()) && motionController6 != null) {
                this.h.getKeyFrames(motionController6);
                motionController6.setup(width, height, this.t, getNanoTime());
            }
        }
        float staggered = this.h.getStaggered();
        if (staggered != 0.0f) {
            boolean z2 = ((double) staggered) < 0.0d;
            float abs = Math.abs(staggered);
            float f2 = -3.4028235E38f;
            float f3 = Float.MAX_VALUE;
            int i11 = 0;
            float f4 = -3.4028235E38f;
            float f5 = Float.MAX_VALUE;
            while (true) {
                if (i11 >= childCount) {
                    z = false;
                    break;
                }
                MotionController motionController7 = this.r.get(getChildAt(i11));
                if (!Float.isNaN(motionController7.l)) {
                    break;
                }
                float finalX = motionController7.getFinalX();
                float finalY = motionController7.getFinalY();
                float f6 = z2 ? finalY - finalX : finalY + finalX;
                f5 = Math.min(f5, f6);
                f4 = Math.max(f4, f6);
                i11++;
            }
            if (!z) {
                while (i2 < childCount) {
                    MotionController motionController8 = this.r.get(getChildAt(i2));
                    float finalX2 = motionController8.getFinalX();
                    float finalY2 = motionController8.getFinalY();
                    float f7 = z2 ? finalY2 - finalX2 : finalY2 + finalX2;
                    motionController8.n = 1.0f / (1.0f - abs);
                    motionController8.m = abs - (((f7 - f5) * abs) / (f4 - f5));
                    i2++;
                }
                return;
            }
            for (int i12 = 0; i12 < childCount; i12++) {
                MotionController motionController9 = this.r.get(getChildAt(i12));
                if (!Float.isNaN(motionController9.l)) {
                    f3 = Math.min(f3, motionController9.l);
                    f2 = Math.max(f2, motionController9.l);
                }
            }
            while (i2 < childCount) {
                MotionController motionController10 = this.r.get(getChildAt(i2));
                if (!Float.isNaN(motionController10.l)) {
                    motionController10.n = 1.0f / (1.0f - abs);
                    if (z2) {
                        motionController10.m = abs - (((f2 - motionController10.l) / (f2 - f3)) * abs);
                    } else {
                        motionController10.m = abs - (((motionController10.l - f3) * abs) / (f2 - f3));
                    }
                }
                i2++;
            }
        }
    }

    public final Rect S(ConstraintWidget constraintWidget) {
        this.w0.top = constraintWidget.getY();
        this.w0.left = constraintWidget.getX();
        Rect rect = this.w0;
        int width = constraintWidget.getWidth();
        Rect rect2 = this.w0;
        rect.right = width + rect2.left;
        int height = constraintWidget.getHeight();
        Rect rect3 = this.w0;
        rect2.bottom = height + rect3.top;
        return rect3;
    }

    public void addTransitionListener(TransitionListener transitionListener) {
        if (this.U == null) {
            this.U = new CopyOnWriteArrayList<>();
        }
        this.U.add(transitionListener);
    }

    public boolean applyViewTransition(int i2, MotionController motionController) {
        MotionScene motionScene = this.h;
        if (motionScene != null) {
            return motionScene.applyViewTransition(i2, motionController);
        }
        return false;
    }

    public ConstraintSet cloneConstraintSet(int i2) {
        MotionScene motionScene = this.h;
        if (motionScene == null) {
            return null;
        }
        ConstraintSet h2 = motionScene.h(i2);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(h2);
        return constraintSet;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        long j2;
        ViewTransitionController viewTransitionController;
        ArrayList<MotionHelper> arrayList = this.T;
        if (arrayList != null) {
            Iterator<MotionHelper> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onPreDraw(canvas);
            }
        }
        G(false);
        MotionScene motionScene = this.h;
        if (motionScene != null && (viewTransitionController = motionScene.s) != null) {
            viewTransitionController.c();
        }
        super.dispatchDraw(canvas);
        if (this.h == null) {
            return;
        }
        if ((this.D & 1) == 1 && !isInEditMode()) {
            this.V++;
            long nanoTime = getNanoTime();
            long j3 = this.W;
            if (j3 != -1) {
                if (nanoTime - j3 > 200000000) {
                    this.a0 = ((int) ((this.V / (((float) j2) * 1.0E-9f)) * 100.0f)) / 100.0f;
                    this.V = 0;
                    this.W = nanoTime;
                }
            } else {
                this.W = nanoTime;
            }
            Paint paint = new Paint();
            paint.setTextSize(42.0f);
            StringBuilder sb = new StringBuilder();
            sb.append(this.a0 + " fps " + Debug.getState(this, this.l) + " -> ");
            sb.append(Debug.getState(this, this.n));
            sb.append(" (progress: ");
            sb.append(((int) (getProgress() * 1000.0f)) / 10.0f);
            sb.append(" ) state=");
            int i2 = this.m;
            sb.append(i2 == -1 ? "undefined" : Debug.getState(this, i2));
            String sb2 = sb.toString();
            paint.setColor(ViewCompat.MEASURED_STATE_MASK);
            canvas.drawText(sb2, 11.0f, getHeight() - 29, paint);
            paint.setColor(-7864184);
            canvas.drawText(sb2, 10.0f, getHeight() - 30, paint);
        }
        if (this.D > 1) {
            if (this.E == null) {
                this.E = new g();
            }
            this.E.a(canvas, this.r, this.h.getDuration(), this.D);
        }
        ArrayList<MotionHelper> arrayList2 = this.T;
        if (arrayList2 != null) {
            Iterator<MotionHelper> it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                it2.next().onPostDraw(canvas);
            }
        }
    }

    public void enableTransition(int i2, boolean z) {
        MotionScene.Transition transition = getTransition(i2);
        if (z) {
            transition.setEnabled(true);
            return;
        }
        MotionScene motionScene = this.h;
        if (transition == motionScene.c) {
            Iterator<MotionScene.Transition> it = motionScene.getTransitionsWithState(this.m).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                MotionScene.Transition next = it.next();
                if (next.isEnabled()) {
                    this.h.c = next;
                    break;
                }
            }
        }
        transition.setEnabled(false);
    }

    public void enableViewTransition(int i2, boolean z) {
        MotionScene motionScene = this.h;
        if (motionScene != null) {
            motionScene.enableViewTransition(i2, z);
        }
    }

    public void fireTransitionCompleted() {
        int i2;
        CopyOnWriteArrayList<TransitionListener> copyOnWriteArrayList;
        if ((this.A != null || ((copyOnWriteArrayList = this.U) != null && !copyOnWriteArrayList.isEmpty())) && this.b0 == -1) {
            this.b0 = this.m;
            if (this.E0.isEmpty()) {
                i2 = -1;
            } else {
                ArrayList<Integer> arrayList = this.E0;
                i2 = arrayList.get(arrayList.size() - 1).intValue();
            }
            int i3 = this.m;
            if (i2 != i3 && i3 != -1) {
                this.E0.add(Integer.valueOf(i3));
            }
        }
        Q();
        Runnable runnable = this.n0;
        if (runnable != null) {
            runnable.run();
        }
        int[] iArr = this.o0;
        if (iArr == null || this.p0 <= 0) {
            return;
        }
        transitionToState(iArr[0]);
        int[] iArr2 = this.o0;
        System.arraycopy(iArr2, 1, iArr2, 0, iArr2.length - 1);
        this.p0--;
    }

    public void fireTrigger(int i2, boolean z, float f2) {
        TransitionListener transitionListener = this.A;
        if (transitionListener != null) {
            transitionListener.onTransitionTrigger(this, i2, z, f2);
        }
        CopyOnWriteArrayList<TransitionListener> copyOnWriteArrayList = this.U;
        if (copyOnWriteArrayList != null) {
            Iterator<TransitionListener> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                it.next().onTransitionTrigger(this, i2, z, f2);
            }
        }
    }

    public ConstraintSet getConstraintSet(int i2) {
        MotionScene motionScene = this.h;
        if (motionScene == null) {
            return null;
        }
        return motionScene.h(i2);
    }

    public int[] getConstraintSetIds() {
        MotionScene motionScene = this.h;
        if (motionScene == null) {
            return null;
        }
        return motionScene.getConstraintSetIds();
    }

    public int getCurrentState() {
        return this.m;
    }

    public void getDebugMode(boolean z) {
        this.D = z ? 2 : 1;
        invalidate();
    }

    public ArrayList<MotionScene.Transition> getDefinedTransitions() {
        MotionScene motionScene = this.h;
        if (motionScene == null) {
            return null;
        }
        return motionScene.getDefinedTransitions();
    }

    public DesignTool getDesignTool() {
        if (this.I == null) {
            this.I = new DesignTool(this);
        }
        return this.I;
    }

    public int getEndState() {
        return this.n;
    }

    public long getNanoTime() {
        return System.nanoTime();
    }

    public float getProgress() {
        return this.v;
    }

    public MotionScene getScene() {
        return this.h;
    }

    public int getStartState() {
        return this.l;
    }

    public float getTargetPosition() {
        return this.x;
    }

    public MotionScene.Transition getTransition(int i2) {
        return this.h.getTransitionById(i2);
    }

    public Bundle getTransitionState() {
        if (this.m0 == null) {
            this.m0 = new j();
        }
        this.m0.c();
        return this.m0.b();
    }

    public long getTransitionTimeMs() {
        MotionScene motionScene = this.h;
        if (motionScene != null) {
            this.t = motionScene.getDuration() / 1000.0f;
        }
        return this.t * 1000.0f;
    }

    public float getVelocity() {
        return this.k;
    }

    public void getViewVelocity(View view, float f2, float f3, float[] fArr, int i2) {
        float f4;
        float f5 = this.k;
        float f6 = this.v;
        if (this.i != null) {
            float signum = Math.signum(this.x - f6);
            float interpolation = this.i.getInterpolation(this.v + 1.0E-5f);
            float interpolation2 = this.i.getInterpolation(this.v);
            f5 = (signum * ((interpolation - interpolation2) / 1.0E-5f)) / this.t;
            f4 = interpolation2;
        } else {
            f4 = f6;
        }
        Interpolator interpolator = this.i;
        if (interpolator instanceof MotionInterpolator) {
            f5 = ((MotionInterpolator) interpolator).getVelocity();
        }
        MotionController motionController = this.r.get(view);
        if ((i2 & 1) == 0) {
            motionController.n(f4, view.getWidth(), view.getHeight(), f2, f3, fArr);
        } else {
            motionController.i(f4, f2, f3, fArr);
        }
        if (i2 < 2) {
            fArr[0] = fArr[0] * f5;
            fArr[1] = fArr[1] * f5;
        }
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        if (Build.VERSION.SDK_INT >= 19) {
            return super.isAttachedToWindow();
        }
        return getWindowToken() != null;
    }

    public boolean isDelayedApplicationOfInitialState() {
        return this.x0;
    }

    public boolean isInRotation() {
        return this.q0;
    }

    public boolean isInteractionEnabled() {
        return this.q;
    }

    public boolean isViewTransitionEnabled(int i2) {
        MotionScene motionScene = this.h;
        if (motionScene != null) {
            return motionScene.isViewTransitionEnabled(i2);
        }
        return false;
    }

    public void jumpToState(int i2) {
        if (!isAttachedToWindow()) {
            this.m = i2;
        }
        if (this.l == i2) {
            setProgress(0.0f);
        } else if (this.n == i2) {
            setProgress(1.0f);
        } else {
            setTransition(i2, i2);
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout
    public void loadLayoutDescription(int i2) {
        MotionScene.Transition transition;
        if (i2 != 0) {
            try {
                MotionScene motionScene = new MotionScene(getContext(), this, i2);
                this.h = motionScene;
                if (this.m == -1) {
                    this.m = motionScene.x();
                    this.l = this.h.x();
                    this.n = this.h.j();
                }
                int i3 = Build.VERSION.SDK_INT;
                if (i3 >= 19 && !isAttachedToWindow()) {
                    this.h = null;
                    return;
                }
                if (i3 >= 17) {
                    try {
                        Display display = getDisplay();
                        this.v0 = display == null ? 0 : display.getRotation();
                    } catch (Exception e2) {
                        throw new IllegalArgumentException("unable to parse MotionScene file", e2);
                    }
                }
                MotionScene motionScene2 = this.h;
                if (motionScene2 != null) {
                    ConstraintSet h2 = motionScene2.h(this.m);
                    this.h.K(this);
                    ArrayList<MotionHelper> arrayList = this.T;
                    if (arrayList != null) {
                        Iterator<MotionHelper> it = arrayList.iterator();
                        while (it.hasNext()) {
                            it.next().onFinishedMotionScene(this);
                        }
                    }
                    if (h2 != null) {
                        h2.applyTo(this);
                    }
                    this.l = this.m;
                }
                P();
                j jVar = this.m0;
                if (jVar != null) {
                    if (this.x0) {
                        post(new a());
                        return;
                    } else {
                        jVar.a();
                        return;
                    }
                }
                MotionScene motionScene3 = this.h;
                if (motionScene3 == null || (transition = motionScene3.c) == null || transition.getAutoTransition() != 4) {
                    return;
                }
                transitionToEnd();
                setState(k.SETUP);
                setState(k.MOVING);
                return;
            } catch (Exception e3) {
                throw new IllegalArgumentException("unable to parse MotionScene file", e3);
            }
        }
        this.h = null;
    }

    public MotionTracker obtainVelocityTracker() {
        return i.a();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        MotionScene.Transition transition;
        int i2;
        Display display;
        super.onAttachedToWindow();
        if (Build.VERSION.SDK_INT >= 17 && (display = getDisplay()) != null) {
            this.v0 = display.getRotation();
        }
        MotionScene motionScene = this.h;
        if (motionScene != null && (i2 = this.m) != -1) {
            ConstraintSet h2 = motionScene.h(i2);
            this.h.K(this);
            ArrayList<MotionHelper> arrayList = this.T;
            if (arrayList != null) {
                Iterator<MotionHelper> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().onFinishedMotionScene(this);
                }
            }
            if (h2 != null) {
                h2.applyTo(this);
            }
            this.l = this.m;
        }
        P();
        j jVar = this.m0;
        if (jVar != null) {
            if (this.x0) {
                post(new d());
                return;
            } else {
                jVar.a();
                return;
            }
        }
        MotionScene motionScene2 = this.h;
        if (motionScene2 == null || (transition = motionScene2.c) == null || transition.getAutoTransition() != 4) {
            return;
        }
        transitionToEnd();
        setState(k.SETUP);
        setState(k.MOVING);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        androidx.constraintlayout.motion.widget.d touchResponse;
        int q;
        RectF p;
        MotionScene motionScene = this.h;
        if (motionScene != null && this.q) {
            ViewTransitionController viewTransitionController = motionScene.s;
            if (viewTransitionController != null) {
                viewTransitionController.j(motionEvent);
            }
            MotionScene.Transition transition = this.h.c;
            if (transition != null && transition.isEnabled() && (touchResponse = transition.getTouchResponse()) != null && ((motionEvent.getAction() != 0 || (p = touchResponse.p(this, new RectF())) == null || p.contains(motionEvent.getX(), motionEvent.getY())) && (q = touchResponse.q()) != -1)) {
                View view = this.C0;
                if (view == null || view.getId() != q) {
                    this.C0 = findViewById(q);
                }
                View view2 = this.C0;
                if (view2 != null) {
                    this.B0.set(view2.getLeft(), this.C0.getTop(), this.C0.getRight(), this.C0.getBottom());
                    if (this.B0.contains(motionEvent.getX(), motionEvent.getY()) && !M(this.C0.getLeft(), this.C0.getTop(), this.C0, motionEvent)) {
                        return onTouchEvent(motionEvent);
                    }
                }
            }
        }
        return false;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        this.l0 = true;
        try {
            if (this.h == null) {
                super.onLayout(z, i2, i3, i4, i5);
                return;
            }
            int i6 = i4 - i2;
            int i7 = i5 - i3;
            if (this.J != i6 || this.K != i7) {
                rebuildScene();
                G(true);
            }
            this.J = i6;
            this.K = i7;
        } finally {
            this.l0 = false;
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        int i4;
        int i5;
        if (this.h == null) {
            super.onMeasure(i2, i3);
            return;
        }
        boolean z = false;
        boolean z2 = (this.o == i2 && this.p == i3) ? false : true;
        if (this.A0) {
            this.A0 = false;
            P();
            Q();
            z2 = true;
        }
        if (this.mDirtyHierarchy) {
            z2 = true;
        }
        this.o = i2;
        this.p = i3;
        int x = this.h.x();
        int j2 = this.h.j();
        if ((z2 || this.z0.f(x, j2)) && this.l != -1) {
            super.onMeasure(i2, i3);
            this.z0.e(this.mLayoutWidget, this.h.h(x), this.h.h(j2));
            this.z0.h();
            this.z0.i(x, j2);
        } else {
            if (z2) {
                super.onMeasure(i2, i3);
            }
            z = true;
        }
        if (this.mMeasureDuringTransition || z) {
            int paddingTop = getPaddingTop() + getPaddingBottom();
            int width = this.mLayoutWidget.getWidth() + getPaddingLeft() + getPaddingRight();
            int height = this.mLayoutWidget.getHeight() + paddingTop;
            int i6 = this.h0;
            if (i6 == Integer.MIN_VALUE || i6 == 0) {
                width = (int) (this.d0 + (this.j0 * (this.f0 - i4)));
                requestLayout();
            }
            int i7 = this.i0;
            if (i7 == Integer.MIN_VALUE || i7 == 0) {
                height = (int) (this.e0 + (this.j0 * (this.g0 - i5)));
                requestLayout();
            }
            setMeasuredDimension(width, height);
        }
        H();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedFling(@NonNull View view, float f2, float f3, boolean z) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedPreFling(@NonNull View view, float f2, float f3) {
        return false;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedPreScroll(@NonNull View view, int i2, int i3, @NonNull int[] iArr, int i4) {
        MotionScene.Transition transition;
        androidx.constraintlayout.motion.widget.d touchResponse;
        int q;
        MotionScene motionScene = this.h;
        if (motionScene == null || (transition = motionScene.c) == null || !transition.isEnabled()) {
            return;
        }
        int i5 = -1;
        if (!transition.isEnabled() || (touchResponse = transition.getTouchResponse()) == null || (q = touchResponse.q()) == -1 || view.getId() == q) {
            if (motionScene.p()) {
                androidx.constraintlayout.motion.widget.d touchResponse2 = transition.getTouchResponse();
                if (touchResponse2 != null && (touchResponse2.e() & 4) != 0) {
                    i5 = i3;
                }
                float f2 = this.u;
                if ((f2 == 1.0f || f2 == 0.0f) && view.canScrollVertically(i5)) {
                    return;
                }
            }
            if (transition.getTouchResponse() != null && (transition.getTouchResponse().e() & 1) != 0) {
                float q2 = motionScene.q(i2, i3);
                float f3 = this.v;
                if ((f3 <= 0.0f && q2 < 0.0f) || (f3 >= 1.0f && q2 > 0.0f)) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        view.setNestedScrollingEnabled(false);
                        view.post(new c(this, view));
                        return;
                    }
                    return;
                }
            }
            float f4 = this.u;
            long nanoTime = getNanoTime();
            float f5 = i2;
            this.M = f5;
            float f6 = i3;
            this.N = f6;
            this.P = (float) ((nanoTime - this.O) * 1.0E-9d);
            this.O = nanoTime;
            motionScene.G(f5, f6);
            if (f4 != this.u) {
                iArr[0] = i2;
                iArr[1] = i3;
            }
            G(false);
            if (iArr[0] == 0 && iArr[1] == 0) {
                return;
            }
            this.L = true;
        }
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScroll(@NonNull View view, int i2, int i3, int i4, int i5, int i6) {
    }

    @Override // androidx.core.view.NestedScrollingParent3
    public void onNestedScroll(@NonNull View view, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
        if (this.L || i2 != 0 || i3 != 0) {
            iArr[0] = iArr[0] + i4;
            iArr[1] = iArr[1] + i5;
        }
        this.L = false;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScrollAccepted(@NonNull View view, @NonNull View view2, int i2, int i3) {
        this.O = getNanoTime();
        this.P = 0.0f;
        this.M = 0.0f;
        this.N = 0.0f;
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i2) {
        MotionScene motionScene = this.h;
        if (motionScene != null) {
            motionScene.setRtl(isRtl());
        }
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public boolean onStartNestedScroll(@NonNull View view, @NonNull View view2, int i2, int i3) {
        MotionScene.Transition transition;
        MotionScene motionScene = this.h;
        return (motionScene == null || (transition = motionScene.c) == null || transition.getTouchResponse() == null || (this.h.c.getTouchResponse().e() & 2) != 0) ? false : true;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onStopNestedScroll(@NonNull View view, int i2) {
        MotionScene motionScene = this.h;
        if (motionScene != null) {
            float f2 = this.P;
            if (f2 == 0.0f) {
                return;
            }
            motionScene.H(this.M / f2, this.N / f2);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionScene motionScene = this.h;
        if (motionScene != null && this.q && motionScene.N()) {
            MotionScene.Transition transition = this.h.c;
            if (transition != null && !transition.isEnabled()) {
                return super.onTouchEvent(motionEvent);
            }
            this.h.I(motionEvent, getCurrentState(), this);
            if (this.h.c.isTransitionFlag(4)) {
                return this.h.c.getTouchResponse().r();
            }
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        if (view instanceof MotionHelper) {
            MotionHelper motionHelper = (MotionHelper) view;
            if (this.U == null) {
                this.U = new CopyOnWriteArrayList<>();
            }
            this.U.add(motionHelper);
            if (motionHelper.isUsedOnShow()) {
                if (this.R == null) {
                    this.R = new ArrayList<>();
                }
                this.R.add(motionHelper);
            }
            if (motionHelper.isUseOnHide()) {
                if (this.S == null) {
                    this.S = new ArrayList<>();
                }
                this.S.add(motionHelper);
            }
            if (motionHelper.isDecorator()) {
                if (this.T == null) {
                    this.T = new ArrayList<>();
                }
                this.T.add(motionHelper);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        ArrayList<MotionHelper> arrayList = this.R;
        if (arrayList != null) {
            arrayList.remove(view);
        }
        ArrayList<MotionHelper> arrayList2 = this.S;
        if (arrayList2 != null) {
            arrayList2.remove(view);
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout
    public void parseLayoutDescription(int i2) {
        this.mConstraintLayoutSpec = null;
    }

    @Deprecated
    public void rebuildMotion() {
        Log.e("MotionLayout", "This method is deprecated. Please call rebuildScene() instead.");
        rebuildScene();
    }

    public void rebuildScene() {
        this.z0.h();
        invalidate();
    }

    public boolean removeTransitionListener(TransitionListener transitionListener) {
        CopyOnWriteArrayList<TransitionListener> copyOnWriteArrayList = this.U;
        if (copyOnWriteArrayList == null) {
            return false;
        }
        return copyOnWriteArrayList.remove(transitionListener);
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View, android.view.ViewParent
    public void requestLayout() {
        MotionScene motionScene;
        MotionScene.Transition transition;
        if (!this.mMeasureDuringTransition && this.m == -1 && (motionScene = this.h) != null && (transition = motionScene.c) != null) {
            int layoutDuringTransition = transition.getLayoutDuringTransition();
            if (layoutDuringTransition == 0) {
                return;
            }
            if (layoutDuringTransition == 2) {
                int childCount = getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    this.r.get(getChildAt(i2)).remeasure();
                }
                return;
            }
        }
        super.requestLayout();
    }

    @RequiresApi(api = 17)
    public void rotateTo(int i2, int i3) {
        this.q0 = true;
        this.t0 = getWidth();
        this.u0 = getHeight();
        int rotation = getDisplay().getRotation();
        this.r0 = (rotation + 1) % 4 <= (this.v0 + 1) % 4 ? 2 : 1;
        this.v0 = rotation;
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            ViewState viewState = this.s0.get(childAt);
            if (viewState == null) {
                viewState = new ViewState();
                this.s0.put(childAt, viewState);
            }
            viewState.getState(childAt);
        }
        this.l = -1;
        this.n = i2;
        this.h.L(-1, i2);
        this.z0.e(this.mLayoutWidget, null, this.h.h(this.n));
        this.u = 0.0f;
        this.v = 0.0f;
        invalidate();
        transitionToEnd(new b());
        if (i3 > 0) {
            this.t = i3 / 1000.0f;
        }
    }

    public void scheduleTransitionTo(int i2) {
        if (getCurrentState() == -1) {
            transitionToState(i2);
            return;
        }
        int[] iArr = this.o0;
        if (iArr == null) {
            this.o0 = new int[4];
        } else if (iArr.length <= this.p0) {
            this.o0 = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.o0;
        int i3 = this.p0;
        this.p0 = i3 + 1;
        iArr2[i3] = i2;
    }

    public void setDebugMode(int i2) {
        this.D = i2;
        invalidate();
    }

    public void setDelayedApplicationOfInitialState(boolean z) {
        this.x0 = z;
    }

    public void setInteractionEnabled(boolean z) {
        this.q = z;
    }

    public void setInterpolatedProgress(float f2) {
        if (this.h != null) {
            setState(k.MOVING);
            Interpolator interpolator = this.h.getInterpolator();
            if (interpolator != null) {
                setProgress(interpolator.getInterpolation(f2));
                return;
            }
        }
        setProgress(f2);
    }

    public void setOnHide(float f2) {
        ArrayList<MotionHelper> arrayList = this.S;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.S.get(i2).setProgress(f2);
            }
        }
    }

    public void setOnShow(float f2) {
        ArrayList<MotionHelper> arrayList = this.R;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.R.get(i2).setProgress(f2);
            }
        }
    }

    public void setProgress(float f2, float f3) {
        if (!isAttachedToWindow()) {
            if (this.m0 == null) {
                this.m0 = new j();
            }
            this.m0.e(f2);
            this.m0.h(f3);
            return;
        }
        setProgress(f2);
        setState(k.MOVING);
        this.k = f3;
        int i2 = (f3 > 0.0f ? 1 : (f3 == 0.0f ? 0 : -1));
        if (i2 != 0) {
            y(i2 > 0 ? 1.0f : 0.0f);
        } else if (f2 == 0.0f || f2 == 1.0f) {
        } else {
            y(f2 > 0.5f ? 1.0f : 0.0f);
        }
    }

    public void setScene(MotionScene motionScene) {
        this.h = motionScene;
        motionScene.setRtl(isRtl());
        rebuildScene();
    }

    public void setStartState(int i2) {
        if (!isAttachedToWindow()) {
            if (this.m0 == null) {
                this.m0 = new j();
            }
            this.m0.f(i2);
            this.m0.d(i2);
            return;
        }
        this.m = i2;
    }

    public void setState(k kVar) {
        k kVar2 = k.FINISHED;
        if (kVar == kVar2 && this.m == -1) {
            return;
        }
        k kVar3 = this.y0;
        this.y0 = kVar;
        k kVar4 = k.MOVING;
        if (kVar3 == kVar4 && kVar == kVar4) {
            I();
        }
        int i2 = e.f948a[kVar3.ordinal()];
        if (i2 != 1 && i2 != 2) {
            if (i2 == 3 && kVar == kVar2) {
                fireTransitionCompleted();
                return;
            }
            return;
        }
        if (kVar == kVar4) {
            I();
        }
        if (kVar == kVar2) {
            fireTransitionCompleted();
        }
    }

    public void setTransition(int i2, int i3) {
        if (!isAttachedToWindow()) {
            if (this.m0 == null) {
                this.m0 = new j();
            }
            this.m0.f(i2);
            this.m0.d(i3);
            return;
        }
        MotionScene motionScene = this.h;
        if (motionScene != null) {
            this.l = i2;
            this.n = i3;
            motionScene.L(i2, i3);
            this.z0.e(this.mLayoutWidget, this.h.h(i2), this.h.h(i3));
            rebuildScene();
            this.v = 0.0f;
            transitionToStart();
        }
    }

    public void setTransitionDuration(int i2) {
        MotionScene motionScene = this.h;
        if (motionScene == null) {
            Log.e("MotionLayout", "MotionScene not defined");
        } else {
            motionScene.setDuration(i2);
        }
    }

    public void setTransitionListener(TransitionListener transitionListener) {
        this.A = transitionListener;
    }

    public void setTransitionState(Bundle bundle) {
        if (this.m0 == null) {
            this.m0 = new j();
        }
        this.m0.g(bundle);
        if (isAttachedToWindow()) {
            this.m0.a();
        }
    }

    @Override // android.view.View
    public String toString() {
        Context context = getContext();
        return Debug.getName(context, this.l) + "->" + Debug.getName(context, this.n) + " (pos:" + this.v + " Dpos/Dt:" + this.k;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0037, code lost:
        if (r10 != 7) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void touchAnimateTo(int r10, float r11, float r12) {
        /*
            Method dump skipped, instructions count: 254
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.touchAnimateTo(int, float, float):void");
    }

    public void touchSpringTo(float f2, float f3) {
        if (this.h == null || this.v == f2) {
            return;
        }
        this.F = true;
        this.s = getNanoTime();
        this.t = this.h.getDuration() / 1000.0f;
        this.x = f2;
        this.z = true;
        this.G.springConfig(this.v, f2, f3, this.h.u(), this.h.v(), this.h.t(), this.h.w(), this.h.s());
        int i2 = this.m;
        this.x = f2;
        this.m = i2;
        this.i = this.G;
        this.y = false;
        this.s = getNanoTime();
        invalidate();
    }

    public void transitionToEnd() {
        y(1.0f);
        this.n0 = null;
    }

    public void transitionToStart() {
        y(0.0f);
    }

    public void transitionToState(int i2) {
        if (!isAttachedToWindow()) {
            if (this.m0 == null) {
                this.m0 = new j();
            }
            this.m0.d(i2);
            return;
        }
        transitionToState(i2, -1, -1);
    }

    public void updateState(int i2, ConstraintSet constraintSet) {
        MotionScene motionScene = this.h;
        if (motionScene != null) {
            motionScene.setConstraintSet(i2, constraintSet);
        }
        updateState();
        if (this.m == i2) {
            constraintSet.applyTo(this);
        }
    }

    public void updateStateAnimate(int i2, ConstraintSet constraintSet, int i3) {
        if (this.h != null && this.m == i2) {
            int i4 = R.id.view_transition;
            updateState(i4, getConstraintSet(i2));
            setState(i4, -1, -1);
            updateState(i2, constraintSet);
            MotionScene.Transition transition = new MotionScene.Transition(-1, this.h, i4, i2);
            transition.setDuration(i3);
            setTransition(transition);
            transitionToEnd();
        }
    }

    public void viewTransition(int i2, View... viewArr) {
        MotionScene motionScene = this.h;
        if (motionScene != null) {
            motionScene.viewTransition(i2, viewArr);
        } else {
            Log.e("MotionLayout", " no motionScene");
        }
    }

    public void y(float f2) {
        MotionScene motionScene = this.h;
        if (motionScene == null) {
            return;
        }
        float f3 = this.v;
        float f4 = this.u;
        if (f3 != f4 && this.y) {
            this.v = f4;
        }
        float f5 = this.v;
        if (f5 == f2) {
            return;
        }
        this.F = false;
        this.x = f2;
        this.t = motionScene.getDuration() / 1000.0f;
        setProgress(this.x);
        this.i = null;
        this.j = this.h.getInterpolator();
        this.y = false;
        this.s = getNanoTime();
        this.z = true;
        this.u = f5;
        this.v = f5;
        invalidate();
    }

    public final boolean z(View view, MotionEvent motionEvent, float f2, float f3) {
        Matrix matrix = view.getMatrix();
        if (matrix.isIdentity()) {
            motionEvent.offsetLocation(f2, f3);
            boolean onTouchEvent = view.onTouchEvent(motionEvent);
            motionEvent.offsetLocation(-f2, -f3);
            return onTouchEvent;
        }
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.offsetLocation(f2, f3);
        if (this.D0 == null) {
            this.D0 = new Matrix();
        }
        matrix.invert(this.D0);
        obtain.transform(this.D0);
        boolean onTouchEvent2 = view.onTouchEvent(obtain);
        obtain.recycle();
        return onTouchEvent2;
    }

    /* loaded from: classes.dex */
    public static class i implements MotionTracker {
        public static i b = new i();

        /* renamed from: a  reason: collision with root package name */
        public VelocityTracker f952a;

        public static i a() {
            b.f952a = VelocityTracker.obtain();
            return b;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public void addMovement(MotionEvent motionEvent) {
            VelocityTracker velocityTracker = this.f952a;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public void clear() {
            VelocityTracker velocityTracker = this.f952a;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public void computeCurrentVelocity(int i) {
            VelocityTracker velocityTracker = this.f952a;
            if (velocityTracker != null) {
                velocityTracker.computeCurrentVelocity(i);
            }
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public float getXVelocity() {
            VelocityTracker velocityTracker = this.f952a;
            if (velocityTracker != null) {
                return velocityTracker.getXVelocity();
            }
            return 0.0f;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public float getYVelocity() {
            VelocityTracker velocityTracker = this.f952a;
            if (velocityTracker != null) {
                return velocityTracker.getYVelocity();
            }
            return 0.0f;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public void recycle() {
            VelocityTracker velocityTracker = this.f952a;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.f952a = null;
            }
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public void computeCurrentVelocity(int i, float f) {
            VelocityTracker velocityTracker = this.f952a;
            if (velocityTracker != null) {
                velocityTracker.computeCurrentVelocity(i, f);
            }
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public float getXVelocity(int i) {
            VelocityTracker velocityTracker = this.f952a;
            if (velocityTracker != null) {
                return velocityTracker.getXVelocity(i);
            }
            return 0.0f;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public float getYVelocity(int i) {
            if (this.f952a != null) {
                return getYVelocity(i);
            }
            return 0.0f;
        }
    }

    public void transitionToEnd(Runnable runnable) {
        y(1.0f);
        this.n0 = runnable;
    }

    public void transitionToState(int i2, int i3) {
        if (!isAttachedToWindow()) {
            if (this.m0 == null) {
                this.m0 = new j();
            }
            this.m0.d(i2);
            return;
        }
        transitionToState(i2, -1, -1, i3);
    }

    public void updateState() {
        this.z0.e(this.mLayoutWidget, this.h.h(this.l), this.h.h(this.n));
        rebuildScene();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout
    public void setState(int i2, int i3, int i4) {
        setState(k.SETUP);
        this.m = i2;
        this.l = -1;
        this.n = -1;
        ConstraintLayoutStates constraintLayoutStates = this.mConstraintLayoutSpec;
        if (constraintLayoutStates != null) {
            constraintLayoutStates.updateConstraints(i2, i3, i4);
            return;
        }
        MotionScene motionScene = this.h;
        if (motionScene != null) {
            motionScene.h(i2).applyTo(this);
        }
    }

    public void setProgress(float f2) {
        int i2 = (f2 > 0.0f ? 1 : (f2 == 0.0f ? 0 : -1));
        if (i2 < 0 || f2 > 1.0f) {
            Log.w("MotionLayout", "Warning! Progress is defined for values between 0.0 and 1.0 inclusive");
        }
        if (!isAttachedToWindow()) {
            if (this.m0 == null) {
                this.m0 = new j();
            }
            this.m0.e(f2);
            return;
        }
        if (i2 <= 0) {
            if (this.v == 1.0f && this.m == this.n) {
                setState(k.MOVING);
            }
            this.m = this.l;
            if (this.v == 0.0f) {
                setState(k.FINISHED);
            }
        } else if (f2 >= 1.0f) {
            if (this.v == 0.0f && this.m == this.l) {
                setState(k.MOVING);
            }
            this.m = this.n;
            if (this.v == 1.0f) {
                setState(k.FINISHED);
            }
        } else {
            this.m = -1;
            setState(k.MOVING);
        }
        if (this.h == null) {
            return;
        }
        this.y = true;
        this.x = f2;
        this.u = f2;
        this.w = -1L;
        this.s = -1L;
        this.i = null;
        this.z = true;
        invalidate();
    }

    public void transitionToState(int i2, int i3, int i4) {
        transitionToState(i2, i3, i4, -1);
    }

    public void transitionToState(int i2, int i3, int i4, int i5) {
        StateSet stateSet;
        int convertToConstraintSet;
        MotionScene motionScene = this.h;
        if (motionScene != null && (stateSet = motionScene.b) != null && (convertToConstraintSet = stateSet.convertToConstraintSet(this.m, i2, i3, i4)) != -1) {
            i2 = convertToConstraintSet;
        }
        int i6 = this.m;
        if (i6 == i2) {
            return;
        }
        if (this.l == i2) {
            y(0.0f);
            if (i5 > 0) {
                this.t = i5 / 1000.0f;
            }
        } else if (this.n == i2) {
            y(1.0f);
            if (i5 > 0) {
                this.t = i5 / 1000.0f;
            }
        } else {
            this.n = i2;
            if (i6 != -1) {
                setTransition(i6, i2);
                y(1.0f);
                this.v = 0.0f;
                transitionToEnd();
                if (i5 > 0) {
                    this.t = i5 / 1000.0f;
                    return;
                }
                return;
            }
            this.F = false;
            this.x = 1.0f;
            this.u = 0.0f;
            this.v = 0.0f;
            this.w = getNanoTime();
            this.s = getNanoTime();
            this.y = false;
            this.i = null;
            if (i5 == -1) {
                this.t = this.h.getDuration() / 1000.0f;
            }
            this.l = -1;
            this.h.L(-1, this.n);
            SparseArray sparseArray = new SparseArray();
            if (i5 == 0) {
                this.t = this.h.getDuration() / 1000.0f;
            } else if (i5 > 0) {
                this.t = i5 / 1000.0f;
            }
            int childCount = getChildCount();
            this.r.clear();
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = getChildAt(i7);
                this.r.put(childAt, new MotionController(childAt));
                sparseArray.put(childAt.getId(), this.r.get(childAt));
            }
            this.z = true;
            this.z0.e(this.mLayoutWidget, null, this.h.h(i2));
            rebuildScene();
            this.z0.a();
            D();
            int width = getWidth();
            int height = getHeight();
            if (this.T != null) {
                for (int i8 = 0; i8 < childCount; i8++) {
                    MotionController motionController = this.r.get(getChildAt(i8));
                    if (motionController != null) {
                        this.h.getKeyFrames(motionController);
                    }
                }
                Iterator<MotionHelper> it = this.T.iterator();
                while (it.hasNext()) {
                    it.next().onPreSetup(this, this.r);
                }
                for (int i9 = 0; i9 < childCount; i9++) {
                    MotionController motionController2 = this.r.get(getChildAt(i9));
                    if (motionController2 != null) {
                        motionController2.setup(width, height, this.t, getNanoTime());
                    }
                }
            } else {
                for (int i10 = 0; i10 < childCount; i10++) {
                    MotionController motionController3 = this.r.get(getChildAt(i10));
                    if (motionController3 != null) {
                        this.h.getKeyFrames(motionController3);
                        motionController3.setup(width, height, this.t, getNanoTime());
                    }
                }
            }
            float staggered = this.h.getStaggered();
            if (staggered != 0.0f) {
                float f2 = Float.MAX_VALUE;
                float f3 = -3.4028235E38f;
                for (int i11 = 0; i11 < childCount; i11++) {
                    MotionController motionController4 = this.r.get(getChildAt(i11));
                    float finalY = motionController4.getFinalY() + motionController4.getFinalX();
                    f2 = Math.min(f2, finalY);
                    f3 = Math.max(f3, finalY);
                }
                for (int i12 = 0; i12 < childCount; i12++) {
                    MotionController motionController5 = this.r.get(getChildAt(i12));
                    float finalX = motionController5.getFinalX();
                    float finalY2 = motionController5.getFinalY();
                    motionController5.n = 1.0f / (1.0f - staggered);
                    motionController5.m = staggered - ((((finalX + finalY2) - f2) * staggered) / (f3 - f2));
                }
            }
            this.u = 0.0f;
            this.v = 0.0f;
            this.z = true;
            invalidate();
        }
    }

    public void setTransition(int i2) {
        if (this.h != null) {
            MotionScene.Transition transition = getTransition(i2);
            this.l = transition.getStartConstraintSetId();
            this.n = transition.getEndConstraintSetId();
            if (!isAttachedToWindow()) {
                if (this.m0 == null) {
                    this.m0 = new j();
                }
                this.m0.f(this.l);
                this.m0.d(this.n);
                return;
            }
            float f2 = Float.NaN;
            int i3 = this.m;
            if (i3 == this.l) {
                f2 = 0.0f;
            } else if (i3 == this.n) {
                f2 = 1.0f;
            }
            this.h.setTransition(transition);
            this.z0.e(this.mLayoutWidget, this.h.h(this.l), this.h.h(this.n));
            rebuildScene();
            if (this.v != f2) {
                if (f2 == 0.0f) {
                    F(true);
                    this.h.h(this.l).applyTo(this);
                } else if (f2 == 1.0f) {
                    F(false);
                    this.h.h(this.n).applyTo(this);
                }
            }
            this.v = Float.isNaN(f2) ? 0.0f : f2;
            if (Float.isNaN(f2)) {
                Log.v("MotionLayout", Debug.getLocation() + " transitionToStart ");
                transitionToStart();
                return;
            }
            setProgress(f2);
        }
    }

    public void setTransition(MotionScene.Transition transition) {
        this.h.setTransition(transition);
        setState(k.SETUP);
        if (this.m == this.h.j()) {
            this.v = 1.0f;
            this.u = 1.0f;
            this.x = 1.0f;
        } else {
            this.v = 0.0f;
            this.u = 0.0f;
            this.x = 0.0f;
        }
        this.w = transition.isTransitionFlag(1) ? -1L : getNanoTime();
        int x = this.h.x();
        int j2 = this.h.j();
        if (x == this.l && j2 == this.n) {
            return;
        }
        this.l = x;
        this.n = j2;
        this.h.L(x, j2);
        this.z0.e(this.mLayoutWidget, this.h.h(this.l), this.h.h(this.n));
        this.z0.i(this.l, this.n);
        this.z0.h();
        rebuildScene();
    }

    public MotionLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = null;
        this.k = 0.0f;
        this.l = -1;
        this.m = -1;
        this.n = -1;
        this.o = 0;
        this.p = 0;
        this.q = true;
        this.r = new HashMap<>();
        this.s = 0L;
        this.t = 1.0f;
        this.u = 0.0f;
        this.v = 0.0f;
        this.x = 0.0f;
        this.z = false;
        this.D = 0;
        this.F = false;
        this.G = new StopLogic();
        this.H = new f();
        this.L = false;
        this.Q = false;
        this.R = null;
        this.S = null;
        this.T = null;
        this.U = null;
        this.V = 0;
        this.W = -1L;
        this.a0 = 0.0f;
        this.b0 = 0;
        this.c0 = 0.0f;
        this.mMeasureDuringTransition = false;
        this.k0 = new KeyCache();
        this.l0 = false;
        this.n0 = null;
        this.o0 = null;
        this.p0 = 0;
        this.q0 = false;
        this.r0 = 0;
        this.s0 = new HashMap<>();
        this.w0 = new Rect();
        this.x0 = false;
        this.y0 = k.UNDEFINED;
        this.z0 = new h();
        this.A0 = false;
        this.B0 = new RectF();
        this.C0 = null;
        this.D0 = null;
        this.E0 = new ArrayList<>();
        N(attributeSet);
    }

    public MotionLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.j = null;
        this.k = 0.0f;
        this.l = -1;
        this.m = -1;
        this.n = -1;
        this.o = 0;
        this.p = 0;
        this.q = true;
        this.r = new HashMap<>();
        this.s = 0L;
        this.t = 1.0f;
        this.u = 0.0f;
        this.v = 0.0f;
        this.x = 0.0f;
        this.z = false;
        this.D = 0;
        this.F = false;
        this.G = new StopLogic();
        this.H = new f();
        this.L = false;
        this.Q = false;
        this.R = null;
        this.S = null;
        this.T = null;
        this.U = null;
        this.V = 0;
        this.W = -1L;
        this.a0 = 0.0f;
        this.b0 = 0;
        this.c0 = 0.0f;
        this.mMeasureDuringTransition = false;
        this.k0 = new KeyCache();
        this.l0 = false;
        this.n0 = null;
        this.o0 = null;
        this.p0 = 0;
        this.q0 = false;
        this.r0 = 0;
        this.s0 = new HashMap<>();
        this.w0 = new Rect();
        this.x0 = false;
        this.y0 = k.UNDEFINED;
        this.z0 = new h();
        this.A0 = false;
        this.B0 = new RectF();
        this.C0 = null;
        this.D0 = null;
        this.E0 = new ArrayList<>();
        N(attributeSet);
    }
}
