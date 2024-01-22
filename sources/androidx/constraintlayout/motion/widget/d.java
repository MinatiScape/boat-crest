package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.R;
import androidx.core.widget.NestedScrollView;
import org.xmlpull.v1.XmlPullParser;
/* loaded from: classes.dex */
public class d {
    public static final float[][] G = {new float[]{0.5f, 0.0f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}, new float[]{0.5f, 1.0f}, new float[]{0.5f, 0.5f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}};
    public static final float[][] H = {new float[]{0.0f, -1.0f}, new float[]{0.0f, 1.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}};
    public float A;
    public float B;
    public float C;
    public float D;
    public int E;
    public int F;

    /* renamed from: a  reason: collision with root package name */
    public int f962a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public float g;
    public float h;
    public float i;
    public float j;
    public int k;
    public boolean l;
    public float m;
    public float n;
    public boolean o;
    public float[] p;
    public int[] q;
    public float r;
    public float s;
    public final MotionLayout t;
    public float u;
    public float v;
    public boolean w;
    public float x;
    public int y;
    public float z;

    /* loaded from: classes.dex */
    public class a implements View.OnTouchListener {
        public a(d dVar) {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return false;
        }
    }

    /* loaded from: classes.dex */
    public class b implements NestedScrollView.OnScrollChangeListener {
        public b(d dVar) {
        }

        @Override // androidx.core.widget.NestedScrollView.OnScrollChangeListener
        public void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
        }
    }

    public d(Context context, MotionLayout motionLayout, XmlPullParser xmlPullParser) {
        this.f962a = 0;
        this.b = 0;
        this.c = 0;
        this.d = -1;
        this.e = -1;
        this.f = -1;
        this.g = 0.5f;
        this.h = 0.5f;
        this.i = 0.5f;
        this.j = 0.5f;
        this.k = -1;
        this.l = false;
        this.m = 0.0f;
        this.n = 1.0f;
        this.o = false;
        this.p = new float[2];
        this.q = new int[2];
        this.u = 4.0f;
        this.v = 1.2f;
        this.w = true;
        this.x = 1.0f;
        this.y = 0;
        this.z = 10.0f;
        this.A = 10.0f;
        this.B = 1.0f;
        this.C = Float.NaN;
        this.D = Float.NaN;
        this.E = 0;
        this.F = 0;
        this.t = motionLayout;
        c(context, Xml.asAttributeSet(xmlPullParser));
    }

    public void A() {
        View view;
        int i = this.d;
        if (i != -1) {
            view = this.t.findViewById(i);
            if (view == null) {
                Log.e("TouchResponse", "cannot find TouchAnchorId @id/" + Debug.getName(this.t.getContext(), this.d));
            }
        } else {
            view = null;
        }
        if (view instanceof NestedScrollView) {
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            nestedScrollView.setOnTouchListener(new a(this));
            nestedScrollView.setOnScrollChangeListener(new b(this));
        }
    }

    public float a(float f, float f2) {
        return (f * this.m) + (f2 * this.n);
    }

    public final void b(TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            if (index == R.styleable.OnSwipe_touchAnchorId) {
                this.d = typedArray.getResourceId(index, this.d);
            } else if (index == R.styleable.OnSwipe_touchAnchorSide) {
                int i2 = typedArray.getInt(index, this.f962a);
                this.f962a = i2;
                float[][] fArr = G;
                this.h = fArr[i2][0];
                this.g = fArr[i2][1];
            } else if (index == R.styleable.OnSwipe_dragDirection) {
                int i3 = typedArray.getInt(index, this.b);
                this.b = i3;
                float[][] fArr2 = H;
                if (i3 < fArr2.length) {
                    this.m = fArr2[i3][0];
                    this.n = fArr2[i3][1];
                } else {
                    this.n = Float.NaN;
                    this.m = Float.NaN;
                    this.l = true;
                }
            } else if (index == R.styleable.OnSwipe_maxVelocity) {
                this.u = typedArray.getFloat(index, this.u);
            } else if (index == R.styleable.OnSwipe_maxAcceleration) {
                this.v = typedArray.getFloat(index, this.v);
            } else if (index == R.styleable.OnSwipe_moveWhenScrollAtTop) {
                this.w = typedArray.getBoolean(index, this.w);
            } else if (index == R.styleable.OnSwipe_dragScale) {
                this.x = typedArray.getFloat(index, this.x);
            } else if (index == R.styleable.OnSwipe_dragThreshold) {
                this.z = typedArray.getFloat(index, this.z);
            } else if (index == R.styleable.OnSwipe_touchRegionId) {
                this.e = typedArray.getResourceId(index, this.e);
            } else if (index == R.styleable.OnSwipe_onTouchUp) {
                this.c = typedArray.getInt(index, this.c);
            } else if (index == R.styleable.OnSwipe_nestedScrollFlags) {
                this.y = typedArray.getInteger(index, 0);
            } else if (index == R.styleable.OnSwipe_limitBoundsTo) {
                this.f = typedArray.getResourceId(index, 0);
            } else if (index == R.styleable.OnSwipe_rotationCenterId) {
                this.k = typedArray.getResourceId(index, this.k);
            } else if (index == R.styleable.OnSwipe_springDamping) {
                this.A = typedArray.getFloat(index, this.A);
            } else if (index == R.styleable.OnSwipe_springMass) {
                this.B = typedArray.getFloat(index, this.B);
            } else if (index == R.styleable.OnSwipe_springStiffness) {
                this.C = typedArray.getFloat(index, this.C);
            } else if (index == R.styleable.OnSwipe_springStopThreshold) {
                this.D = typedArray.getFloat(index, this.D);
            } else if (index == R.styleable.OnSwipe_springBoundary) {
                this.E = typedArray.getInt(index, this.E);
            } else if (index == R.styleable.OnSwipe_autoCompleteMode) {
                this.F = typedArray.getInt(index, this.F);
            }
        }
    }

    public final void c(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.OnSwipe);
        b(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
    }

    public int d() {
        return this.F;
    }

    public int e() {
        return this.y;
    }

    public RectF f(ViewGroup viewGroup, RectF rectF) {
        View findViewById;
        int i = this.f;
        if (i == -1 || (findViewById = viewGroup.findViewById(i)) == null) {
            return null;
        }
        rectF.set(findViewById.getLeft(), findViewById.getTop(), findViewById.getRight(), findViewById.getBottom());
        return rectF;
    }

    public float g() {
        return this.v;
    }

    public float h() {
        return this.u;
    }

    public boolean i() {
        return this.w;
    }

    public float j(float f, float f2) {
        this.t.J(this.d, this.t.getProgress(), this.h, this.g, this.p);
        float f3 = this.m;
        if (f3 != 0.0f) {
            float[] fArr = this.p;
            if (fArr[0] == 0.0f) {
                fArr[0] = 1.0E-7f;
            }
            return (f * f3) / fArr[0];
        }
        float[] fArr2 = this.p;
        if (fArr2[1] == 0.0f) {
            fArr2[1] = 1.0E-7f;
        }
        return (f2 * this.n) / fArr2[1];
    }

    public int k() {
        return this.E;
    }

    public float l() {
        return this.A;
    }

    public float m() {
        return this.B;
    }

    public float n() {
        return this.C;
    }

    public float o() {
        return this.D;
    }

    public RectF p(ViewGroup viewGroup, RectF rectF) {
        View findViewById;
        int i = this.e;
        if (i == -1 || (findViewById = viewGroup.findViewById(i)) == null) {
            return null;
        }
        rectF.set(findViewById.getLeft(), findViewById.getTop(), findViewById.getRight(), findViewById.getBottom());
        return rectF;
    }

    public int q() {
        return this.e;
    }

    public boolean r() {
        return this.o;
    }

    public void s(MotionEvent motionEvent, MotionLayout.MotionTracker motionTracker, int i, MotionScene motionScene) {
        float f;
        int i2;
        float f2;
        if (this.l) {
            t(motionEvent, motionTracker, i, motionScene);
            return;
        }
        motionTracker.addMovement(motionEvent);
        int action = motionEvent.getAction();
        if (action == 0) {
            this.r = motionEvent.getRawX();
            this.s = motionEvent.getRawY();
            this.o = false;
        } else if (action == 1) {
            this.o = false;
            motionTracker.computeCurrentVelocity(1000);
            float xVelocity = motionTracker.getXVelocity();
            float yVelocity = motionTracker.getYVelocity();
            float progress = this.t.getProgress();
            int i3 = this.d;
            if (i3 != -1) {
                this.t.J(i3, progress, this.h, this.g, this.p);
            } else {
                float min = Math.min(this.t.getWidth(), this.t.getHeight());
                float[] fArr = this.p;
                fArr[1] = this.n * min;
                fArr[0] = min * this.m;
            }
            float f3 = this.m;
            float[] fArr2 = this.p;
            float f4 = fArr2[0];
            float f5 = fArr2[1];
            if (f3 != 0.0f) {
                f = xVelocity / fArr2[0];
            } else {
                f = yVelocity / fArr2[1];
            }
            float f6 = !Float.isNaN(f) ? (f / 3.0f) + progress : progress;
            if (f6 == 0.0f || f6 == 1.0f || (i2 = this.c) == 3) {
                if (0.0f >= f6 || 1.0f <= f6) {
                    this.t.setState(MotionLayout.k.FINISHED);
                    return;
                }
                return;
            }
            float f7 = ((double) f6) < 0.5d ? 0.0f : 1.0f;
            if (i2 == 6) {
                if (progress + f < 0.0f) {
                    f = Math.abs(f);
                }
                f7 = 1.0f;
            }
            if (this.c == 7) {
                if (progress + f > 1.0f) {
                    f = -Math.abs(f);
                }
                f7 = 0.0f;
            }
            this.t.touchAnimateTo(this.c, f7, f);
            if (0.0f >= progress || 1.0f <= progress) {
                this.t.setState(MotionLayout.k.FINISHED);
            }
        } else if (action != 2) {
        } else {
            float rawY = motionEvent.getRawY() - this.s;
            float rawX = motionEvent.getRawX() - this.r;
            if (Math.abs((this.m * rawX) + (this.n * rawY)) > this.z || this.o) {
                float progress2 = this.t.getProgress();
                if (!this.o) {
                    this.o = true;
                    this.t.setProgress(progress2);
                }
                int i4 = this.d;
                if (i4 != -1) {
                    this.t.J(i4, progress2, this.h, this.g, this.p);
                } else {
                    float min2 = Math.min(this.t.getWidth(), this.t.getHeight());
                    float[] fArr3 = this.p;
                    fArr3[1] = this.n * min2;
                    fArr3[0] = min2 * this.m;
                }
                float f8 = this.m;
                float[] fArr4 = this.p;
                if (Math.abs(((f8 * fArr4[0]) + (this.n * fArr4[1])) * this.x) < 0.01d) {
                    float[] fArr5 = this.p;
                    fArr5[0] = 0.01f;
                    fArr5[1] = 0.01f;
                }
                if (this.m != 0.0f) {
                    f2 = rawX / this.p[0];
                } else {
                    f2 = rawY / this.p[1];
                }
                float max = Math.max(Math.min(progress2 + f2, 1.0f), 0.0f);
                if (this.c == 6) {
                    max = Math.max(max, 0.01f);
                }
                if (this.c == 7) {
                    max = Math.min(max, 0.99f);
                }
                float progress3 = this.t.getProgress();
                if (max != progress3) {
                    int i5 = (progress3 > 0.0f ? 1 : (progress3 == 0.0f ? 0 : -1));
                    if (i5 == 0 || progress3 == 1.0f) {
                        this.t.F(i5 == 0);
                    }
                    this.t.setProgress(max);
                    motionTracker.computeCurrentVelocity(1000);
                    this.t.k = this.m != 0.0f ? motionTracker.getXVelocity() / this.p[0] : motionTracker.getYVelocity() / this.p[1];
                } else {
                    this.t.k = 0.0f;
                }
                this.r = motionEvent.getRawX();
                this.s = motionEvent.getRawY();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x02bd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void t(android.view.MotionEvent r24, androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker r25, int r26, androidx.constraintlayout.motion.widget.MotionScene r27) {
        /*
            Method dump skipped, instructions count: 833
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.d.t(android.view.MotionEvent, androidx.constraintlayout.motion.widget.MotionLayout$MotionTracker, int, androidx.constraintlayout.motion.widget.MotionScene):void");
    }

    public String toString() {
        if (Float.isNaN(this.m)) {
            return Key.ROTATION;
        }
        return this.m + " , " + this.n;
    }

    public void u(float f, float f2) {
        float f3;
        float progress = this.t.getProgress();
        if (!this.o) {
            this.o = true;
            this.t.setProgress(progress);
        }
        this.t.J(this.d, progress, this.h, this.g, this.p);
        float f4 = this.m;
        float[] fArr = this.p;
        if (Math.abs((f4 * fArr[0]) + (this.n * fArr[1])) < 0.01d) {
            float[] fArr2 = this.p;
            fArr2[0] = 0.01f;
            fArr2[1] = 0.01f;
        }
        float f5 = this.m;
        if (f5 != 0.0f) {
            f3 = (f * f5) / this.p[0];
        } else {
            f3 = (f2 * this.n) / this.p[1];
        }
        float max = Math.max(Math.min(progress + f3, 1.0f), 0.0f);
        if (max != this.t.getProgress()) {
            this.t.setProgress(max);
        }
    }

    public void v(float f, float f2) {
        float f3;
        this.o = false;
        float progress = this.t.getProgress();
        this.t.J(this.d, progress, this.h, this.g, this.p);
        float f4 = this.m;
        float[] fArr = this.p;
        float f5 = fArr[0];
        float f6 = this.n;
        float f7 = fArr[1];
        if (f4 != 0.0f) {
            f3 = (f * f4) / fArr[0];
        } else {
            f3 = (f2 * f6) / fArr[1];
        }
        if (!Float.isNaN(f3)) {
            progress += f3 / 3.0f;
        }
        if (progress != 0.0f) {
            boolean z = progress != 1.0f;
            int i = this.c;
            if ((i != 3) && z) {
                this.t.touchAnimateTo(i, ((double) progress) >= 0.5d ? 1.0f : 0.0f, f3);
            }
        }
    }

    public void w(float f, float f2) {
        this.r = f;
        this.s = f2;
    }

    public void x(boolean z) {
        if (z) {
            float[][] fArr = H;
            fArr[4] = fArr[3];
            fArr[5] = fArr[2];
            float[][] fArr2 = G;
            fArr2[5] = fArr2[2];
            fArr2[6] = fArr2[1];
        } else {
            float[][] fArr3 = H;
            fArr3[4] = fArr3[2];
            fArr3[5] = fArr3[3];
            float[][] fArr4 = G;
            fArr4[5] = fArr4[1];
            fArr4[6] = fArr4[2];
        }
        float[][] fArr5 = G;
        int i = this.f962a;
        this.h = fArr5[i][0];
        this.g = fArr5[i][1];
        int i2 = this.b;
        float[][] fArr6 = H;
        if (i2 >= fArr6.length) {
            return;
        }
        this.m = fArr6[i2][0];
        this.n = fArr6[i2][1];
    }

    public void y(int i) {
        this.c = i;
    }

    public void z(float f, float f2) {
        this.r = f;
        this.s = f2;
        this.o = false;
    }

    public d(MotionLayout motionLayout, OnSwipe onSwipe) {
        this.f962a = 0;
        this.b = 0;
        this.c = 0;
        this.d = -1;
        this.e = -1;
        this.f = -1;
        this.g = 0.5f;
        this.h = 0.5f;
        this.i = 0.5f;
        this.j = 0.5f;
        this.k = -1;
        this.l = false;
        this.m = 0.0f;
        this.n = 1.0f;
        this.o = false;
        this.p = new float[2];
        this.q = new int[2];
        this.u = 4.0f;
        this.v = 1.2f;
        this.w = true;
        this.x = 1.0f;
        this.y = 0;
        this.z = 10.0f;
        this.A = 10.0f;
        this.B = 1.0f;
        this.C = Float.NaN;
        this.D = Float.NaN;
        this.E = 0;
        this.F = 0;
        this.t = motionLayout;
        this.d = onSwipe.getTouchAnchorId();
        int touchAnchorSide = onSwipe.getTouchAnchorSide();
        this.f962a = touchAnchorSide;
        if (touchAnchorSide != -1) {
            float[][] fArr = G;
            this.h = fArr[touchAnchorSide][0];
            this.g = fArr[touchAnchorSide][1];
        }
        int dragDirection = onSwipe.getDragDirection();
        this.b = dragDirection;
        float[][] fArr2 = H;
        if (dragDirection < fArr2.length) {
            this.m = fArr2[dragDirection][0];
            this.n = fArr2[dragDirection][1];
        } else {
            this.n = Float.NaN;
            this.m = Float.NaN;
            this.l = true;
        }
        this.u = onSwipe.getMaxVelocity();
        this.v = onSwipe.getMaxAcceleration();
        this.w = onSwipe.getMoveWhenScrollAtTop();
        this.x = onSwipe.getDragScale();
        this.z = onSwipe.getDragThreshold();
        this.e = onSwipe.getTouchRegionId();
        this.c = onSwipe.getOnTouchUp();
        this.y = onSwipe.getNestedScrollFlags();
        this.f = onSwipe.getLimitBoundsTo();
        this.k = onSwipe.getRotationCenterId();
        this.E = onSwipe.getSpringBoundary();
        this.A = onSwipe.getSpringDamping();
        this.B = onSwipe.getSpringMass();
        this.C = onSwipe.getSpringStiffness();
        this.D = onSwipe.getSpringStopThreshold();
        this.F = onSwipe.getAutoCompleteMode();
    }
}
