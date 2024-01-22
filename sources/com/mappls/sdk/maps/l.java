package com.mappls.sdk.maps;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PointF;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.gestures.AndroidGesturesManager;
import com.mappls.sdk.gestures.MoveGestureDetector;
import com.mappls.sdk.gestures.MultiFingerTapGestureDetector;
import com.mappls.sdk.gestures.RotateGestureDetector;
import com.mappls.sdk.gestures.ShoveGestureDetector;
import com.mappls.sdk.gestures.StandardGestureDetector;
import com.mappls.sdk.gestures.StandardScaleGestureDetector;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.utils.MathUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes11.dex */
public final class l {

    /* renamed from: a  reason: collision with root package name */
    public final Transform f12732a;
    public final Projection b;
    public final UiSettings c;
    public final com.mappls.sdk.maps.b d;
    public final com.mappls.sdk.maps.g e;
    @Nullable
    public PointF m;
    public AndroidGesturesManager o;
    public Animator p;
    public Animator q;
    public boolean t;
    public final CopyOnWriteArrayList<MapplsMap.OnMapClickListener> f = new CopyOnWriteArrayList<>();
    public final CopyOnWriteArrayList<MapplsMap.OnMapLongClickListener> g = new CopyOnWriteArrayList<>();
    public final CopyOnWriteArrayList<MapplsMap.OnFlingListener> h = new CopyOnWriteArrayList<>();
    public final CopyOnWriteArrayList<MapplsMap.OnMoveListener> i = new CopyOnWriteArrayList<>();
    public final CopyOnWriteArrayList<MapplsMap.OnRotateListener> j = new CopyOnWriteArrayList<>();
    public final CopyOnWriteArrayList<MapplsMap.OnScaleListener> k = new CopyOnWriteArrayList<>();
    public final CopyOnWriteArrayList<MapplsMap.OnShoveListener> l = new CopyOnWriteArrayList<>();
    @NonNull
    public PointF n = new PointF();
    public final List<Animator> r = new ArrayList();
    @NonNull
    public Handler s = new Handler();
    @NonNull
    public final Runnable u = new a();

    /* loaded from: classes11.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            l.this.A();
        }
    }

    /* loaded from: classes11.dex */
    public class b implements ValueAnimator.AnimatorUpdateListener {
        public final /* synthetic */ PointF h;

        public b(PointF pointF) {
            this.h = pointF;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
            l.this.f12732a.E(((Float) valueAnimator.getAnimatedValue()).floatValue(), this.h);
        }
    }

    /* loaded from: classes11.dex */
    public class c extends AnimatorListenerAdapter {
        public c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            l.this.f12732a.f();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            l.this.D();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            l.this.f12732a.f();
            l.this.e.onCameraMoveStarted(1);
        }
    }

    /* loaded from: classes11.dex */
    public final class d extends MoveGestureDetector.SimpleOnMoveGestureListener {
        public d() {
        }

        @Override // com.mappls.sdk.gestures.MoveGestureDetector.SimpleOnMoveGestureListener, com.mappls.sdk.gestures.MoveGestureDetector.OnMoveGestureListener
        public boolean onMove(@NonNull MoveGestureDetector moveGestureDetector, float f, float f2) {
            if (f != 0.0f || f2 != 0.0f) {
                l.this.e.onCameraMoveStarted(1);
                if (l.this.c != null && !l.this.c.isHorizontalScrollGesturesEnabled()) {
                    f = 0.0f;
                }
                l.this.f12732a.u(-f, -f2, 0L);
                l.this.P(moveGestureDetector);
            }
            return true;
        }

        @Override // com.mappls.sdk.gestures.MoveGestureDetector.SimpleOnMoveGestureListener, com.mappls.sdk.gestures.MoveGestureDetector.OnMoveGestureListener
        public boolean onMoveBegin(@NonNull MoveGestureDetector moveGestureDetector) {
            if (l.this.c == null || l.this.c.isScrollGesturesEnabled()) {
                l.this.B();
                l.this.N(moveGestureDetector);
                return true;
            }
            return false;
        }

        @Override // com.mappls.sdk.gestures.MoveGestureDetector.SimpleOnMoveGestureListener, com.mappls.sdk.gestures.MoveGestureDetector.OnMoveGestureListener
        public void onMoveEnd(@NonNull MoveGestureDetector moveGestureDetector, float f, float f2) {
            l.this.D();
            l.this.O(moveGestureDetector);
        }

        public /* synthetic */ d(l lVar, a aVar) {
            this();
        }
    }

    /* loaded from: classes11.dex */
    public final class e extends RotateGestureDetector.SimpleOnRotateGestureListener {

        /* renamed from: a  reason: collision with root package name */
        public final float f12734a;
        public final float b;
        public final float c;
        public final double d;
        public final float e;

        /* loaded from: classes11.dex */
        public class a implements ValueAnimator.AnimatorUpdateListener {
            public final /* synthetic */ PointF h;

            public a(PointF pointF) {
                this.h = pointF;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
                PointF pointF = this.h;
                l.this.f12732a.x(l.this.f12732a.n() + ((Float) valueAnimator.getAnimatedValue()).floatValue(), pointF.x, pointF.y, 0L);
            }
        }

        /* loaded from: classes11.dex */
        public class b extends AnimatorListenerAdapter {
            public b() {
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                l.this.f12732a.f();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                l.this.D();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                l.this.f12732a.f();
                l.this.e.onCameraMoveStarted(1);
            }
        }

        public e(float f, double d, float f2, float f3, float f4) {
            this.f12734a = f;
            this.b = f2;
            this.c = f3;
            this.d = d * 2.2000000000000003E-4d;
            this.e = f4;
        }

        public final Animator a(float f, long j, @NonNull PointF pointF) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(f, 0.0f);
            ofFloat.setDuration(j);
            ofFloat.setInterpolator(new DecelerateInterpolator());
            ofFloat.addUpdateListener(new a(pointF));
            ofFloat.addListener(new b());
            return ofFloat;
        }

        @NonNull
        public final PointF b(@NonNull RotateGestureDetector rotateGestureDetector) {
            if (l.this.m != null) {
                return l.this.m;
            }
            return rotateGestureDetector.getFocalPoint();
        }

        @Override // com.mappls.sdk.gestures.RotateGestureDetector.SimpleOnRotateGestureListener, com.mappls.sdk.gestures.RotateGestureDetector.OnRotateGestureListener
        public boolean onRotate(@NonNull RotateGestureDetector rotateGestureDetector, float f, float f2) {
            l.this.e.onCameraMoveStarted(1);
            double n = l.this.f12732a.n() + f;
            PointF b2 = b(rotateGestureDetector);
            l.this.f12732a.w(n, b2.x, b2.y);
            l.this.S(rotateGestureDetector);
            return true;
        }

        @Override // com.mappls.sdk.gestures.RotateGestureDetector.SimpleOnRotateGestureListener, com.mappls.sdk.gestures.RotateGestureDetector.OnRotateGestureListener
        public boolean onRotateBegin(@NonNull RotateGestureDetector rotateGestureDetector) {
            if (l.this.c == null || l.this.c.isRotateGesturesEnabled()) {
                float abs = Math.abs(rotateGestureDetector.getDeltaSinceLast());
                double eventTime = rotateGestureDetector.getCurrentEvent().getEventTime();
                double eventTime2 = rotateGestureDetector.getPreviousEvent().getEventTime();
                if (eventTime == eventTime2) {
                    return false;
                }
                double d = abs / (eventTime - eventTime2);
                float abs2 = Math.abs(rotateGestureDetector.getDeltaSinceStart());
                if (d < 0.04d || ((d > 0.07d && abs2 < 5.0f) || ((d > 0.15d && abs2 < 7.0f) || (d > 0.5d && abs2 < 15.0f)))) {
                    return false;
                }
                if (l.this.c != null && l.this.c.isIncreaseScaleThresholdWhenRotating()) {
                    l.this.o.getStandardScaleGestureDetector().setSpanSinceStartThreshold(this.f12734a);
                    l.this.o.getStandardScaleGestureDetector().interrupt();
                }
                l.this.B();
                l.this.Q(rotateGestureDetector);
                return true;
            }
            return false;
        }

        @Override // com.mappls.sdk.gestures.RotateGestureDetector.SimpleOnRotateGestureListener, com.mappls.sdk.gestures.RotateGestureDetector.OnRotateGestureListener
        public void onRotateEnd(@NonNull RotateGestureDetector rotateGestureDetector, float f, float f2, float f3) {
            if (l.this.c != null && l.this.c.isIncreaseScaleThresholdWhenRotating()) {
                l.this.o.getStandardScaleGestureDetector().setSpanSinceStartThreshold(this.e);
            }
            l.this.R(rotateGestureDetector);
            float clamp = MathUtils.clamp(f3 * this.b, -30.0f, 30.0f);
            double abs = Math.abs(rotateGestureDetector.getDeltaSinceLast()) / (Math.abs(f) + Math.abs(f2));
            if ((l.this.c != null && !l.this.c.isRotateVelocityAnimationEnabled()) || Math.abs(clamp) < this.c || (l.this.o.getStandardScaleGestureDetector().isInProgress() && abs < this.d)) {
                l.this.D();
                return;
            }
            l.this.q = a(clamp, (long) ((Math.log(Math.abs(clamp) + (1.0d / Math.pow(2.718281828459045d, 2.0d))) + 2.0d) * 150.0d), b(rotateGestureDetector));
            l lVar = l.this;
            lVar.i0(lVar.q);
        }
    }

    /* loaded from: classes11.dex */
    public final class f extends StandardScaleGestureDetector.SimpleStandardOnScaleGestureListener {

        /* renamed from: a  reason: collision with root package name */
        public final float f12735a;
        public final float b;
        public final float c;
        public final double d;
        public boolean e;
        public float f;
        public double g;
        public double h;

        public f(double d, float f, float f2, float f3) {
            this.f12735a = f;
            this.b = f2;
            this.c = f3;
            this.d = d * 0.004d;
        }

        public final double a(double d, boolean z) {
            double clamp = MathUtils.clamp(d * 2.5d * 1.0E-4d, 0.0d, 2.5d);
            return z ? -clamp : clamp;
        }

        @NonNull
        public final PointF b(@NonNull StandardScaleGestureDetector standardScaleGestureDetector) {
            if (l.this.m != null) {
                return l.this.m;
            }
            if (this.e) {
                return new PointF((l.this.c != null ? l.this.c.getWidth() : l.this.b.f()) / 2.0f, (l.this.c != null ? l.this.c.getHeight() : l.this.b.d()) / 2.0f);
            }
            return standardScaleGestureDetector.getFocalPoint();
        }

        @Override // com.mappls.sdk.gestures.StandardScaleGestureDetector.SimpleStandardOnScaleGestureListener, com.mappls.sdk.gestures.StandardScaleGestureDetector.StandardOnScaleGestureListener
        public boolean onScale(@NonNull StandardScaleGestureDetector standardScaleGestureDetector) {
            l.this.e.onCameraMoveStarted(1);
            PointF b = b(standardScaleGestureDetector);
            if (this.e) {
                double abs = Math.abs(standardScaleGestureDetector.getCurrentEvent().getY() - l.this.n.y);
                boolean z = standardScaleGestureDetector.getCurrentEvent().getY() < l.this.n.y;
                double normalize = MathUtils.normalize(abs, 0.0d, this.g, 0.0d, 4.0d);
                double d = this.h;
                l.this.f12732a.E((z ? d - normalize : d + normalize) * (l.this.c != null ? l.this.c.getZoomRate() : 1.0d), b);
            } else {
                l.this.f12732a.F((Math.log(standardScaleGestureDetector.getScaleFactor()) / Math.log(1.5707963267948966d)) * 0.6499999761581421d * (l.this.c != null ? l.this.c.getZoomRate() : 1.0f), b);
            }
            l.this.V(standardScaleGestureDetector);
            this.f = Math.abs(standardScaleGestureDetector.getCurrentSpan() - standardScaleGestureDetector.getPreviousSpan());
            return true;
        }

        @Override // com.mappls.sdk.gestures.StandardScaleGestureDetector.SimpleStandardOnScaleGestureListener, com.mappls.sdk.gestures.StandardScaleGestureDetector.StandardOnScaleGestureListener
        public boolean onScaleBegin(@NonNull StandardScaleGestureDetector standardScaleGestureDetector) {
            this.e = standardScaleGestureDetector.getPointersCount() == 1;
            if (l.this.c == null || l.this.c.isZoomGesturesEnabled()) {
                if (this.e) {
                    if (l.this.c != null && !l.this.c.isQuickZoomGesturesEnabled()) {
                        return false;
                    }
                    l.this.o.getMoveGestureDetector().setEnabled(false);
                } else if (standardScaleGestureDetector.getPreviousSpan() <= 0.0f) {
                    return false;
                } else {
                    float currentSpan = standardScaleGestureDetector.getCurrentSpan();
                    float previousSpan = standardScaleGestureDetector.getPreviousSpan();
                    double eventTime = standardScaleGestureDetector.getCurrentEvent().getEventTime();
                    double eventTime2 = standardScaleGestureDetector.getPreviousEvent().getEventTime();
                    if (eventTime == eventTime2) {
                        return false;
                    }
                    double abs = Math.abs(currentSpan - previousSpan) / (eventTime - eventTime2);
                    if (abs < this.f12735a) {
                        return false;
                    }
                    if (!l.this.o.getRotateGestureDetector().isInProgress()) {
                        if (Math.abs(l.this.o.getRotateGestureDetector().getDeltaSinceLast()) > 0.4d && abs < this.b) {
                            return false;
                        }
                        if (l.this.c != null && l.this.c.isDisableRotateWhenScaling()) {
                            l.this.o.getRotateGestureDetector().setEnabled(false);
                        }
                    }
                }
                this.g = Resources.getSystem().getDisplayMetrics().heightPixels;
                this.h = l.this.f12732a.o();
                l.this.B();
                l.this.T(standardScaleGestureDetector);
                this.f = Math.abs(standardScaleGestureDetector.getCurrentSpan() - standardScaleGestureDetector.getPreviousSpan());
                return true;
            }
            return false;
        }

        @Override // com.mappls.sdk.gestures.StandardScaleGestureDetector.SimpleStandardOnScaleGestureListener, com.mappls.sdk.gestures.StandardScaleGestureDetector.StandardOnScaleGestureListener
        public void onScaleEnd(@NonNull StandardScaleGestureDetector standardScaleGestureDetector, float f, float f2) {
            if (this.e) {
                l.this.o.getMoveGestureDetector().setEnabled(true);
            } else {
                l.this.o.getRotateGestureDetector().setEnabled(true);
            }
            l.this.U(standardScaleGestureDetector);
            float abs = Math.abs(f) + Math.abs(f2);
            if ((l.this.c != null && !l.this.c.isScaleVelocityAnimationEnabled()) || abs < this.c || this.f / abs < this.d) {
                l.this.D();
                return;
            }
            double a2 = a(abs, standardScaleGestureDetector.isScalingOut());
            double o = l.this.f12732a.o();
            PointF b = b(standardScaleGestureDetector);
            long log = (long) ((Math.log(Math.abs(a2) + (1.0d / Math.pow(2.718281828459045d, 2.0d))) + 2.0d) * 150.0d);
            l lVar = l.this;
            lVar.p = lVar.C(o, a2, b, log);
            l lVar2 = l.this;
            lVar2.i0(lVar2.p);
        }
    }

    /* loaded from: classes11.dex */
    public final class g extends ShoveGestureDetector.SimpleOnShoveGestureListener {
        public g() {
        }

        @Override // com.mappls.sdk.gestures.ShoveGestureDetector.SimpleOnShoveGestureListener, com.mappls.sdk.gestures.ShoveGestureDetector.OnShoveGestureListener
        public boolean onShove(@NonNull ShoveGestureDetector shoveGestureDetector, float f, float f2) {
            l.this.e.onCameraMoveStarted(1);
            l.this.f12732a.D(Double.valueOf(MathUtils.clamp(l.this.f12732a.p() - (f * 0.1f), 0.0d, 60.0d)));
            l.this.Y(shoveGestureDetector);
            return true;
        }

        @Override // com.mappls.sdk.gestures.ShoveGestureDetector.SimpleOnShoveGestureListener, com.mappls.sdk.gestures.ShoveGestureDetector.OnShoveGestureListener
        public boolean onShoveBegin(@NonNull ShoveGestureDetector shoveGestureDetector) {
            if (l.this.c == null || l.this.c.isTiltGesturesEnabled()) {
                l.this.B();
                l.this.o.getMoveGestureDetector().setEnabled(false);
                l.this.W(shoveGestureDetector);
                return true;
            }
            return false;
        }

        @Override // com.mappls.sdk.gestures.ShoveGestureDetector.SimpleOnShoveGestureListener, com.mappls.sdk.gestures.ShoveGestureDetector.OnShoveGestureListener
        public void onShoveEnd(@NonNull ShoveGestureDetector shoveGestureDetector, float f, float f2) {
            l.this.D();
            l.this.o.getMoveGestureDetector().setEnabled(true);
            l.this.X(shoveGestureDetector);
        }

        public /* synthetic */ g(l lVar, a aVar) {
            this();
        }
    }

    /* loaded from: classes11.dex */
    public final class h extends StandardGestureDetector.SimpleStandardOnGestureListener {
        public final float h;

        public h(float f) {
            this.h = f;
        }

        @Override // com.mappls.sdk.gestures.StandardGestureDetector.SimpleStandardOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            if (motionEvent.getActionMasked() == 0) {
                l.this.n = new PointF(motionEvent.getX(), motionEvent.getY());
                l.this.F();
            }
            if (motionEvent.getActionMasked() == 1) {
                float abs = Math.abs(motionEvent.getX() - l.this.n.x);
                float abs2 = Math.abs(motionEvent.getY() - l.this.n.y);
                float f = this.h;
                if (abs > f || abs2 > f) {
                    return false;
                }
                if (l.this.c == null || (l.this.c.isZoomGesturesEnabled() && l.this.c.isDoubleTapGesturesEnabled())) {
                    if (l.this.m != null) {
                        l lVar = l.this;
                        lVar.n = lVar.m;
                    }
                    l lVar2 = l.this;
                    lVar2.m0(lVar2.n, false);
                    return true;
                }
                return false;
            }
            return super.onDoubleTapEvent(motionEvent);
        }

        @Override // com.mappls.sdk.gestures.StandardGestureDetector.SimpleStandardOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        @Override // com.mappls.sdk.gestures.StandardGestureDetector.SimpleStandardOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            double d;
            if (l.this.c == null || l.this.c.isScrollGesturesEnabled()) {
                if (l.this.c == null || l.this.c.isFlingVelocityAnimationEnabled()) {
                    float c = l.this.c != null ? l.this.c.c() : 1.0f;
                    double hypot = Math.hypot(f / c, f2 / c);
                    if (hypot < 1000.0d) {
                        return false;
                    }
                    double p = l.this.f12732a.p();
                    double d2 = (p != 0.0d ? p / 10.0d : 0.0d) + 1.5d;
                    double d3 = c;
                    double d4 = (f / d2) / d3;
                    double d5 = (f2 / d2) / d3;
                    long j = (long) (((hypot / 7.0d) / d2) + 150.0d);
                    if (l.this.c == null || l.this.c.isHorizontalScrollGesturesEnabled()) {
                        d = d4;
                    } else if (Math.abs(Math.toDegrees(Math.atan(d4 / d5))) > 75.0d) {
                        return false;
                    } else {
                        d = 0.0d;
                    }
                    l.this.f12732a.f();
                    l.this.K();
                    l.this.e.onCameraMoveStarted(1);
                    l.this.f12732a.u(d, d5, j);
                    return true;
                }
                return false;
            }
            return false;
        }

        @Override // com.mappls.sdk.gestures.StandardGestureDetector.SimpleStandardOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            l.this.M(new PointF(motionEvent.getX(), motionEvent.getY()));
        }

        @Override // com.mappls.sdk.gestures.StandardGestureDetector.SimpleStandardOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());
            if (l.this.d.z(pointF)) {
                return true;
            }
            if (l.this.c != null && l.this.c.isDeselectMarkersOnTap()) {
                l.this.d.j();
            }
            l.this.L(pointF);
            return true;
        }

        @Override // com.mappls.sdk.gestures.StandardGestureDetector.SimpleStandardOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            l.this.f12732a.f();
            return true;
        }
    }

    /* loaded from: classes11.dex */
    public final class i implements MultiFingerTapGestureDetector.OnMultiFingerTapGestureListener {
        public i() {
        }

        @Override // com.mappls.sdk.gestures.MultiFingerTapGestureDetector.OnMultiFingerTapGestureListener
        public boolean onMultiFingerTap(@NonNull MultiFingerTapGestureDetector multiFingerTapGestureDetector, int i) {
            PointF focalPoint;
            if ((l.this.c == null || l.this.c.isZoomGesturesEnabled()) && i == 2) {
                l.this.f12732a.f();
                l.this.e.onCameraMoveStarted(1);
                if (l.this.m != null) {
                    focalPoint = l.this.m;
                } else {
                    focalPoint = multiFingerTapGestureDetector.getFocalPoint();
                }
                l.this.n0(focalPoint, false);
                return true;
            }
            return false;
        }

        public /* synthetic */ i(l lVar, a aVar) {
            this();
        }
    }

    public l(@Nullable Context context, Transform transform, Projection projection, UiSettings uiSettings, com.mappls.sdk.maps.b bVar, com.mappls.sdk.maps.g gVar) {
        this.d = bVar;
        this.f12732a = transform;
        this.b = projection;
        this.c = uiSettings;
        this.e = gVar;
        if (context != null) {
            I(new AndroidGesturesManager(context), true);
            H(context, true);
        }
    }

    public void A() {
        this.s.removeCallbacksAndMessages(null);
        this.r.clear();
        z(this.p);
        z(this.q);
        D();
    }

    public final void B() {
        if (J()) {
            this.f12732a.f();
        }
    }

    public final Animator C(double d2, double d3, @NonNull PointF pointF, long j) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat((float) d2, (float) (d2 + d3));
        ofFloat.setDuration(j);
        ofFloat.setInterpolator(new DecelerateInterpolator());
        ofFloat.addUpdateListener(new b(pointF));
        ofFloat.addListener(new c());
        return ofFloat;
    }

    public final void D() {
        if (J()) {
            this.f12732a.r();
            this.e.onCameraIdle();
        }
    }

    public final void E() {
        if (this.t) {
            this.o.getMoveGestureDetector().setEnabled(true);
            this.t = false;
        }
    }

    public final void F() {
        this.o.getMoveGestureDetector().setEnabled(false);
        this.t = true;
    }

    public AndroidGesturesManager G() {
        return this.o;
    }

    public final void H(@NonNull Context context, boolean z) {
        if (z) {
            Resources resources = context.getResources();
            int i2 = R.dimen.mappls_defaultScaleSpanSinceStartThreshold;
            h hVar = new h(resources.getDimension(i2));
            d dVar = new d(this, null);
            Resources resources2 = context.getResources();
            int i3 = R.dimen.mappls_maps_density_constant;
            f fVar = new f(resources2.getDimension(i3), context.getResources().getDimension(R.dimen.mappls_maps_minimum_scale_speed), context.getResources().getDimension(R.dimen.mappls_maps_minimum_angled_scale_speed), context.getResources().getDimension(R.dimen.mappls_maps_minimum_scale_velocity));
            e eVar = new e(context.getResources().getDimension(R.dimen.mappls_maps_minimum_scale_span_when_rotating), context.getResources().getDimension(i3), context.getResources().getDimension(R.dimen.mappls_maps_angular_velocity_multiplier), context.getResources().getDimension(R.dimen.mappls_maps_minimum_angular_velocity), context.getResources().getDimension(i2));
            g gVar = new g(this, null);
            i iVar = new i(this, null);
            this.o.setStandardGestureListener(hVar);
            this.o.setMoveGestureListener(dVar);
            this.o.setStandardScaleGestureListener(fVar);
            this.o.setRotateGestureListener(eVar);
            this.o.setShoveGestureListener(gVar);
            this.o.setMultiFingerTapGestureListener(iVar);
        }
    }

    public final void I(@NonNull AndroidGesturesManager androidGesturesManager, boolean z) {
        if (z) {
            HashSet hashSet = new HashSet();
            hashSet.add(3);
            hashSet.add(1);
            HashSet hashSet2 = new HashSet();
            hashSet2.add(3);
            hashSet2.add(2);
            HashSet hashSet3 = new HashSet();
            hashSet3.add(1);
            hashSet3.add(6);
            androidGesturesManager.setMutuallyExclusiveGestures(hashSet, hashSet2, hashSet3);
        }
        this.o = androidGesturesManager;
        androidGesturesManager.getRotateGestureDetector().setAngleThreshold(3.0f);
    }

    public final boolean J() {
        UiSettings uiSettings = this.c;
        return (uiSettings == null || (uiSettings.isScrollGesturesEnabled() && this.o.getMoveGestureDetector().isInProgress()) || ((this.c.isZoomGesturesEnabled() && this.o.getStandardScaleGestureDetector().isInProgress()) || ((this.c.isRotateGesturesEnabled() && this.o.getRotateGestureDetector().isInProgress()) || (this.c.isTiltGesturesEnabled() && this.o.getShoveGestureDetector().isInProgress())))) ? false : true;
    }

    public void K() {
        Iterator<MapplsMap.OnFlingListener> it = this.h.iterator();
        while (it.hasNext()) {
            it.next().onFling();
        }
    }

    public void L(@NonNull PointF pointF) {
        Iterator<MapplsMap.OnMapClickListener> it = this.f.iterator();
        while (it.hasNext() && !it.next().onMapClick(this.b.fromScreenLocation(pointF))) {
        }
    }

    public void M(@NonNull PointF pointF) {
        Iterator<MapplsMap.OnMapLongClickListener> it = this.g.iterator();
        while (it.hasNext() && !it.next().onMapLongClick(this.b.fromScreenLocation(pointF))) {
        }
    }

    public void N(@NonNull MoveGestureDetector moveGestureDetector) {
        Iterator<MapplsMap.OnMoveListener> it = this.i.iterator();
        while (it.hasNext()) {
            it.next().onMoveBegin(moveGestureDetector);
        }
    }

    public void O(@NonNull MoveGestureDetector moveGestureDetector) {
        Iterator<MapplsMap.OnMoveListener> it = this.i.iterator();
        while (it.hasNext()) {
            it.next().onMoveEnd(moveGestureDetector);
        }
    }

    public void P(@NonNull MoveGestureDetector moveGestureDetector) {
        Iterator<MapplsMap.OnMoveListener> it = this.i.iterator();
        while (it.hasNext()) {
            it.next().onMove(moveGestureDetector);
        }
    }

    public void Q(@NonNull RotateGestureDetector rotateGestureDetector) {
        Iterator<MapplsMap.OnRotateListener> it = this.j.iterator();
        while (it.hasNext()) {
            it.next().onRotateBegin(rotateGestureDetector);
        }
    }

    public void R(@NonNull RotateGestureDetector rotateGestureDetector) {
        Iterator<MapplsMap.OnRotateListener> it = this.j.iterator();
        while (it.hasNext()) {
            it.next().onRotateEnd(rotateGestureDetector);
        }
    }

    public void S(@NonNull RotateGestureDetector rotateGestureDetector) {
        Iterator<MapplsMap.OnRotateListener> it = this.j.iterator();
        while (it.hasNext()) {
            it.next().onRotate(rotateGestureDetector);
        }
    }

    public void T(@NonNull StandardScaleGestureDetector standardScaleGestureDetector) {
        Iterator<MapplsMap.OnScaleListener> it = this.k.iterator();
        while (it.hasNext()) {
            it.next().onScaleBegin(standardScaleGestureDetector);
        }
    }

    public void U(@NonNull StandardScaleGestureDetector standardScaleGestureDetector) {
        Iterator<MapplsMap.OnScaleListener> it = this.k.iterator();
        while (it.hasNext()) {
            it.next().onScaleEnd(standardScaleGestureDetector);
        }
    }

    public void V(@NonNull StandardScaleGestureDetector standardScaleGestureDetector) {
        Iterator<MapplsMap.OnScaleListener> it = this.k.iterator();
        while (it.hasNext()) {
            it.next().onScale(standardScaleGestureDetector);
        }
    }

    public void W(@NonNull ShoveGestureDetector shoveGestureDetector) {
        Iterator<MapplsMap.OnShoveListener> it = this.l.iterator();
        while (it.hasNext()) {
            it.next().onShoveBegin(shoveGestureDetector);
        }
    }

    public void X(@NonNull ShoveGestureDetector shoveGestureDetector) {
        Iterator<MapplsMap.OnShoveListener> it = this.l.iterator();
        while (it.hasNext()) {
            it.next().onShoveEnd(shoveGestureDetector);
        }
    }

    public void Y(@NonNull ShoveGestureDetector shoveGestureDetector) {
        Iterator<MapplsMap.OnShoveListener> it = this.l.iterator();
        while (it.hasNext()) {
            it.next().onShove(shoveGestureDetector);
        }
    }

    public boolean Z(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) == 2 && motionEvent.getActionMasked() == 8) {
            UiSettings uiSettings = this.c;
            if (uiSettings == null || uiSettings.isZoomGesturesEnabled()) {
                this.f12732a.f();
                this.f12732a.F(motionEvent.getAxisValue(9), new PointF(motionEvent.getX(), motionEvent.getY()));
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean a0(@Nullable MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        if (motionEvent.getButtonState() == 0 || motionEvent.getButtonState() == 1) {
            if (motionEvent.getActionMasked() == 0) {
                A();
                this.f12732a.y(true);
            }
            boolean onTouchEvent = this.o.onTouchEvent(motionEvent);
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 1) {
                E();
                this.f12732a.y(false);
                if (!this.r.isEmpty()) {
                    this.s.removeCallbacksAndMessages(null);
                    for (Animator animator : this.r) {
                        animator.start();
                    }
                    this.r.clear();
                }
            } else if (actionMasked == 3) {
                this.r.clear();
                this.f12732a.y(false);
                E();
            } else if (actionMasked == 5) {
                E();
            }
            return onTouchEvent;
        }
        return false;
    }

    public void b0(MapplsMap.OnFlingListener onFlingListener) {
        this.h.remove(onFlingListener);
    }

    public void c0(MapplsMap.OnMapClickListener onMapClickListener) {
        this.f.remove(onMapClickListener);
    }

    public void d0(MapplsMap.OnMapLongClickListener onMapLongClickListener) {
        this.g.remove(onMapLongClickListener);
    }

    public void e0(MapplsMap.OnMoveListener onMoveListener) {
        this.i.remove(onMoveListener);
    }

    public void f0(MapplsMap.OnRotateListener onRotateListener) {
        this.j.remove(onRotateListener);
    }

    public void g0(MapplsMap.OnScaleListener onScaleListener) {
        this.k.remove(onScaleListener);
    }

    public void h0(MapplsMap.OnShoveListener onShoveListener) {
        this.l.remove(onShoveListener);
    }

    public final void i0(Animator animator) {
        this.r.add(animator);
        this.s.removeCallbacksAndMessages(null);
        this.s.postDelayed(this.u, 150L);
    }

    public void j0(@Nullable PointF pointF) {
        UiSettings uiSettings;
        if (pointF == null && (uiSettings = this.c) != null && uiSettings.getFocalPoint() != null) {
            pointF = this.c.getFocalPoint();
        }
        this.m = pointF;
    }

    public void k0(@NonNull Context context, @NonNull AndroidGesturesManager androidGesturesManager, boolean z, boolean z2) {
        I(androidGesturesManager, z2);
        H(context, z);
    }

    public final void l0(boolean z, @NonNull PointF pointF, boolean z2) {
        z(this.p);
        Animator C = C(this.f12732a.o(), z ? 1.0d : -1.0d, pointF, 300L);
        this.p = C;
        if (z2) {
            C.start();
        } else {
            i0(C);
        }
    }

    public void m0(@NonNull PointF pointF, boolean z) {
        l0(true, pointF, z);
    }

    public void n0(@NonNull PointF pointF, boolean z) {
        l0(false, pointF, z);
    }

    public void s(MapplsMap.OnFlingListener onFlingListener) {
        this.h.add(onFlingListener);
    }

    public void t(MapplsMap.OnMapClickListener onMapClickListener) {
        this.f.add(onMapClickListener);
    }

    public void u(MapplsMap.OnMapLongClickListener onMapLongClickListener) {
        this.g.add(onMapLongClickListener);
    }

    public void v(MapplsMap.OnMoveListener onMoveListener) {
        this.i.add(onMoveListener);
    }

    public void w(MapplsMap.OnRotateListener onRotateListener) {
        this.j.add(onRotateListener);
    }

    public void x(MapplsMap.OnScaleListener onScaleListener) {
        this.k.add(onScaleListener);
    }

    public void y(MapplsMap.OnShoveListener onShoveListener) {
        this.l.add(onShoveListener);
    }

    public final void z(@Nullable Animator animator) {
        if (animator == null || !animator.isStarted()) {
            return;
        }
        animator.cancel();
    }
}
