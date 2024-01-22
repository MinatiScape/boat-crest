package com.aigestudio.wheelpicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public class WheelPicker extends View implements IDebug, IWheelPicker, Runnable {
    public static final int ALIGN_CENTER = 0;
    public static final int ALIGN_LEFT = 1;
    public static final int ALIGN_RIGHT = 2;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SCROLLING = 2;
    public static final String n0 = WheelPicker.class.getSimpleName();
    public int A;
    public int B;
    public int C;
    public int D;
    public int E;
    public int F;
    public int G;
    public int H;
    public int I;
    public int J;
    public int K;
    public int L;
    public int M;
    public int N;
    public int O;
    public int P;
    public int Q;
    public int R;
    public int S;
    public int T;
    public int U;
    public int V;
    public int W;
    public int a0;
    public int b0;
    public int c0;
    public int d0;
    public boolean e0;
    public boolean f0;
    public boolean g0;
    public final Handler h;
    public boolean h0;
    public Paint i;
    public boolean i0;
    public Scroller j;
    public boolean j0;
    public VelocityTracker k;
    public boolean k0;
    public OnItemSelectedListener l;
    public boolean l0;
    public OnWheelChangeListener m;
    public boolean m0;
    public Rect n;
    public Rect o;
    public Rect p;
    public Rect q;
    public Camera r;
    public Matrix s;
    public Matrix t;
    public List u;
    public String v;
    public int w;
    public int x;
    public int y;
    public int z;

    /* loaded from: classes.dex */
    public interface OnItemSelectedListener {
        void onItemSelected(WheelPicker wheelPicker, Object obj, int i);
    }

    /* loaded from: classes.dex */
    public interface OnWheelChangeListener {
        void onWheelScrollStateChanged(int i);

        void onWheelScrolled(int i);

        void onWheelSelected(int i);
    }

    public WheelPicker(Context context) {
        this(context, null);
    }

    public final void a() {
        if (this.g0 || this.C != -1) {
            Rect rect = this.q;
            Rect rect2 = this.n;
            int i = rect2.left;
            int i2 = this.T;
            int i3 = this.K;
            rect.set(i, i2 - i3, rect2.right, i2 + i3);
        }
    }

    public final int b(int i) {
        return (int) (this.L - (Math.cos(Math.toRadians(i)) * this.L));
    }

    public final int c(int i) {
        int i2;
        if (Math.abs(i) > this.K) {
            if (this.W < 0) {
                i2 = -this.J;
            } else {
                i2 = this.J;
            }
            return i2 - i;
        }
        return -i;
    }

    public final void d() {
        int i = this.I;
        if (i == 1) {
            this.U = this.n.left;
        } else if (i != 2) {
            this.U = this.S;
        } else {
            this.U = this.n.right;
        }
        this.V = (int) (this.T - ((this.i.ascent() + this.i.descent()) / 2.0f));
    }

    public final void e() {
        int i = this.M;
        int i2 = this.J;
        int i3 = i * i2;
        this.O = this.i0 ? Integer.MIN_VALUE : ((-i2) * (this.u.size() - 1)) + i3;
        if (this.i0) {
            i3 = Integer.MAX_VALUE;
        }
        this.P = i3;
    }

    public final void f() {
        if (this.f0) {
            int i = this.E / 2;
            int i2 = this.T;
            int i3 = this.K;
            int i4 = i2 + i3;
            int i5 = i2 - i3;
            Rect rect = this.o;
            Rect rect2 = this.n;
            rect.set(rect2.left, i4 - i, rect2.right, i4 + i);
            Rect rect3 = this.p;
            Rect rect4 = this.n;
            rect3.set(rect4.left, i5 - i, rect4.right, i5 + i);
        }
    }

    public final int g(int i) {
        return (int) (Math.sin(Math.toRadians(i)) * this.L);
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public int getCurrentItemPosition() {
        return this.N;
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public int getCurtainColor() {
        return this.G;
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public List getData() {
        return this.u;
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public int getIndicatorColor() {
        return this.F;
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public int getIndicatorSize() {
        return this.E;
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public int getItemAlign() {
        return this.I;
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public int getItemSpace() {
        return this.H;
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public int getItemTextColor() {
        return this.B;
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public int getItemTextSize() {
        return this.D;
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public String getMaximumWidthText() {
        return this.v;
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public int getMaximumWidthTextPosition() {
        return this.a0;
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public int getSelectedItemPosition() {
        return this.M;
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public int getSelectedItemTextColor() {
        return this.C;
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public Typeface getTypeface() {
        Paint paint = this.i;
        if (paint != null) {
            return paint.getTypeface();
        }
        return null;
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public int getVisibleItemCount() {
        return this.w;
    }

    public final void h() {
        this.A = 0;
        this.z = 0;
        if (this.e0) {
            this.z = (int) this.i.measureText(String.valueOf(this.u.get(0)));
        } else if (i(this.a0)) {
            this.z = (int) this.i.measureText(String.valueOf(this.u.get(this.a0)));
        } else if (!TextUtils.isEmpty(this.v)) {
            this.z = (int) this.i.measureText(this.v);
        } else {
            for (Object obj : this.u) {
                String valueOf = String.valueOf(obj);
                this.z = Math.max(this.z, (int) this.i.measureText(valueOf));
            }
        }
        Paint.FontMetrics fontMetrics = this.i.getFontMetrics();
        this.A = (int) (fontMetrics.bottom - fontMetrics.top);
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public boolean hasAtmospheric() {
        return this.h0;
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public boolean hasCurtain() {
        return this.g0;
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public boolean hasIndicator() {
        return this.f0;
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public boolean hasSameWidth() {
        return this.e0;
    }

    public final boolean i(int i) {
        return i >= 0 && i < this.u.size();
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public boolean isCurved() {
        return this.j0;
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public boolean isCyclic() {
        return this.i0;
    }

    public final int j(int i, int i2, int i3) {
        return i == 1073741824 ? i2 : i == Integer.MIN_VALUE ? Math.min(i3, i2) : i3;
    }

    public final void k() {
        int i = this.I;
        if (i == 1) {
            this.i.setTextAlign(Paint.Align.LEFT);
        } else if (i != 2) {
            this.i.setTextAlign(Paint.Align.CENTER);
        } else {
            this.i.setTextAlign(Paint.Align.RIGHT);
        }
    }

    public final void l() {
        int i = this.w;
        if (i >= 2) {
            if (i % 2 == 0) {
                this.w = i + 1;
            }
            int i2 = this.w + 2;
            this.x = i2;
            this.y = i2 / 2;
            return;
        }
        throw new ArithmeticException("Wheel's visible item count can not be less than 2!");
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        String valueOf;
        int i;
        int i2;
        OnWheelChangeListener onWheelChangeListener = this.m;
        if (onWheelChangeListener != null) {
            onWheelChangeListener.onWheelScrolled(this.W);
        }
        int i3 = (-this.W) / this.J;
        int i4 = this.y;
        int i5 = i3 - i4;
        int i6 = this.M + i5;
        int i7 = -i4;
        while (i6 < this.M + i5 + this.x) {
            if (this.i0) {
                int size = i6 % this.u.size();
                if (size < 0) {
                    size += this.u.size();
                }
                valueOf = String.valueOf(this.u.get(size));
            } else {
                valueOf = i(i6) ? String.valueOf(this.u.get(i6)) : "";
            }
            this.i.setColor(this.B);
            this.i.setStyle(Paint.Style.FILL);
            int i8 = this.V;
            int i9 = this.J;
            int i10 = (i7 * i9) + i8 + (this.W % i9);
            if (this.j0) {
                int abs = i8 - Math.abs(i8 - i10);
                int i11 = this.n.top;
                int i12 = this.V;
                float f = (-(1.0f - (((abs - i11) * 1.0f) / (i12 - i11)))) * 90.0f * (i10 > i12 ? 1 : i10 < i12 ? -1 : 0);
                if (f < -90.0f) {
                    f = -90.0f;
                }
                float f2 = f <= 90.0f ? f : 90.0f;
                i = g((int) f2);
                int i13 = this.S;
                int i14 = this.I;
                if (i14 == 1) {
                    i13 = this.n.left;
                } else if (i14 == 2) {
                    i13 = this.n.right;
                }
                int i15 = this.T - i;
                this.r.save();
                this.r.rotateX(f2);
                this.r.getMatrix(this.s);
                this.r.restore();
                float f3 = -i13;
                float f4 = -i15;
                this.s.preTranslate(f3, f4);
                float f5 = i13;
                float f6 = i15;
                this.s.postTranslate(f5, f6);
                this.r.save();
                this.r.translate(0.0f, 0.0f, b(i2));
                this.r.getMatrix(this.t);
                this.r.restore();
                this.t.preTranslate(f3, f4);
                this.t.postTranslate(f5, f6);
                this.s.postConcat(this.t);
            } else {
                i = 0;
            }
            if (this.h0) {
                int i16 = this.V;
                int abs2 = (int) ((((i16 - Math.abs(i16 - i10)) * 1.0f) / this.V) * 255.0f);
                this.i.setAlpha(abs2 < 0 ? 0 : abs2);
            }
            if (this.j0) {
                i10 = this.V - i;
            }
            if (this.C != -1) {
                canvas.save();
                if (this.j0) {
                    canvas.concat(this.s);
                }
                canvas.clipRect(this.q, Region.Op.DIFFERENCE);
                float f7 = i10;
                canvas.drawText(valueOf, this.U, f7, this.i);
                canvas.restore();
                this.i.setColor(this.C);
                canvas.save();
                if (this.j0) {
                    canvas.concat(this.s);
                }
                canvas.clipRect(this.q);
                canvas.drawText(valueOf, this.U, f7, this.i);
                canvas.restore();
            } else {
                canvas.save();
                canvas.clipRect(this.n);
                if (this.j0) {
                    canvas.concat(this.s);
                }
                canvas.drawText(valueOf, this.U, i10, this.i);
                canvas.restore();
            }
            if (this.m0) {
                canvas.save();
                canvas.clipRect(this.n);
                this.i.setColor(-1166541);
                int i17 = this.T + (this.J * i7);
                Rect rect = this.n;
                float f8 = i17;
                canvas.drawLine(rect.left, f8, rect.right, f8, this.i);
                this.i.setColor(-13421586);
                this.i.setStyle(Paint.Style.STROKE);
                int i18 = i17 - this.K;
                Rect rect2 = this.n;
                canvas.drawRect(rect2.left, i18, rect2.right, i18 + this.J, this.i);
                canvas.restore();
            }
            i6++;
            i7++;
        }
        if (this.g0) {
            this.i.setColor(this.G);
            this.i.setStyle(Paint.Style.FILL);
            canvas.drawRect(this.q, this.i);
        }
        if (this.f0) {
            this.i.setColor(this.F);
            this.i.setStyle(Paint.Style.FILL);
            canvas.drawRect(this.o, this.i);
            canvas.drawRect(this.p, this.i);
        }
        if (this.m0) {
            this.i.setColor(1144254003);
            this.i.setStyle(Paint.Style.FILL);
            canvas.drawRect(0.0f, 0.0f, getPaddingLeft(), getHeight(), this.i);
            canvas.drawRect(0.0f, 0.0f, getWidth(), getPaddingTop(), this.i);
            canvas.drawRect(getWidth() - getPaddingRight(), 0.0f, getWidth(), getHeight(), this.i);
            canvas.drawRect(0.0f, getHeight() - getPaddingBottom(), getWidth(), getHeight(), this.i);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int i3 = this.z;
        int i4 = this.A;
        int i5 = this.w;
        int i6 = (i4 * i5) + (this.H * (i5 - 1));
        if (this.j0) {
            i6 = (int) ((i6 * 2) / 3.141592653589793d);
        }
        if (this.m0) {
            String str = n0;
            Log.i(str, "Wheel's content size is (" + i3 + ":" + i6 + ")");
        }
        int paddingLeft = i3 + getPaddingLeft() + getPaddingRight();
        int paddingTop = i6 + getPaddingTop() + getPaddingBottom();
        if (this.m0) {
            String str2 = n0;
            Log.i(str2, "Wheel's size is (" + paddingLeft + ":" + paddingTop + ")");
        }
        setMeasuredDimension(j(mode, size, paddingLeft), j(mode2, size2, paddingTop));
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        this.n.set(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
        if (this.m0) {
            String str = n0;
            Log.i(str, "Wheel's drawn rect size is (" + this.n.width() + ":" + this.n.height() + ") and location is (" + this.n.left + ":" + this.n.top + ")");
        }
        this.S = this.n.centerX();
        this.T = this.n.centerY();
        d();
        this.L = this.n.height() / 2;
        int height = this.n.height() / this.w;
        this.J = height;
        this.K = height / 2;
        e();
        f();
        a();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            if (getParent() != null) {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
            VelocityTracker velocityTracker = this.k;
            if (velocityTracker == null) {
                this.k = VelocityTracker.obtain();
            } else {
                velocityTracker.clear();
            }
            this.k.addMovement(motionEvent);
            if (!this.j.isFinished()) {
                this.j.abortAnimation();
                this.l0 = true;
            }
            int y = (int) motionEvent.getY();
            this.b0 = y;
            this.c0 = y;
        } else if (action == 1) {
            if (getParent() != null) {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
            if (!this.k0) {
                this.k.addMovement(motionEvent);
                if (Build.VERSION.SDK_INT >= 4) {
                    this.k.computeCurrentVelocity(1000, this.R);
                } else {
                    this.k.computeCurrentVelocity(1000);
                }
                this.l0 = false;
                int yVelocity = (int) this.k.getYVelocity();
                if (Math.abs(yVelocity) > this.Q) {
                    this.j.fling(0, this.W, 0, yVelocity, 0, 0, this.O, this.P);
                    Scroller scroller = this.j;
                    scroller.setFinalY(scroller.getFinalY() + c(this.j.getFinalY() % this.J));
                } else {
                    Scroller scroller2 = this.j;
                    int i = this.W;
                    scroller2.startScroll(0, i, 0, c(i % this.J));
                }
                if (!this.i0) {
                    int finalY = this.j.getFinalY();
                    int i2 = this.P;
                    if (finalY > i2) {
                        this.j.setFinalY(i2);
                    } else {
                        int finalY2 = this.j.getFinalY();
                        int i3 = this.O;
                        if (finalY2 < i3) {
                            this.j.setFinalY(i3);
                        }
                    }
                }
                this.h.post(this);
                VelocityTracker velocityTracker2 = this.k;
                if (velocityTracker2 != null) {
                    velocityTracker2.recycle();
                    this.k = null;
                }
            }
        } else if (action != 2) {
            if (action == 3) {
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                VelocityTracker velocityTracker3 = this.k;
                if (velocityTracker3 != null) {
                    velocityTracker3.recycle();
                    this.k = null;
                }
            }
        } else if (Math.abs(this.c0 - motionEvent.getY()) < this.d0) {
            this.k0 = true;
        } else {
            this.k0 = false;
            this.k.addMovement(motionEvent);
            OnWheelChangeListener onWheelChangeListener = this.m;
            if (onWheelChangeListener != null) {
                onWheelChangeListener.onWheelScrollStateChanged(1);
            }
            float y2 = motionEvent.getY() - this.b0;
            if (Math.abs(y2) >= 1.0f) {
                this.W = (int) (this.W + y2);
                this.b0 = (int) motionEvent.getY();
                invalidate();
            }
        }
        return true;
    }

    @Override // java.lang.Runnable
    public void run() {
        List list = this.u;
        if (list == null || list.size() == 0) {
            return;
        }
        if (this.j.isFinished() && !this.l0) {
            int i = this.J;
            if (i == 0) {
                return;
            }
            int size = (((-this.W) / i) + this.M) % this.u.size();
            if (size < 0) {
                size += this.u.size();
            }
            if (this.m0) {
                String str = n0;
                Log.i(str, size + ":" + this.u.get(size) + ":" + this.W);
            }
            this.N = size;
            OnItemSelectedListener onItemSelectedListener = this.l;
            if (onItemSelectedListener != null) {
                onItemSelectedListener.onItemSelected(this, this.u.get(size), size);
            }
            OnWheelChangeListener onWheelChangeListener = this.m;
            if (onWheelChangeListener != null) {
                onWheelChangeListener.onWheelSelected(size);
                this.m.onWheelScrollStateChanged(0);
            }
        }
        if (this.j.computeScrollOffset()) {
            OnWheelChangeListener onWheelChangeListener2 = this.m;
            if (onWheelChangeListener2 != null) {
                onWheelChangeListener2.onWheelScrollStateChanged(2);
            }
            this.W = this.j.getCurrY();
            postInvalidate();
            this.h.postDelayed(this, 16L);
        }
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setAtmospheric(boolean z) {
        this.h0 = z;
        invalidate();
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setCurtain(boolean z) {
        this.g0 = z;
        a();
        invalidate();
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setCurtainColor(int i) {
        this.G = i;
        invalidate();
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setCurved(boolean z) {
        this.j0 = z;
        requestLayout();
        invalidate();
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setCyclic(boolean z) {
        this.i0 = z;
        e();
        invalidate();
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setData(List list) {
        Objects.requireNonNull(list, "WheelPicker's data can not be null!");
        this.u = list;
        if (this.M <= list.size() - 1 && this.N <= list.size() - 1) {
            this.M = this.N;
        } else {
            int size = list.size() - 1;
            this.N = size;
            this.M = size;
        }
        this.W = 0;
        h();
        e();
        requestLayout();
        invalidate();
    }

    @Override // com.aigestudio.wheelpicker.IDebug
    public void setDebug(boolean z) {
        this.m0 = z;
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setIndicator(boolean z) {
        this.f0 = z;
        f();
        invalidate();
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setIndicatorColor(int i) {
        this.F = i;
        invalidate();
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setIndicatorSize(int i) {
        this.E = i;
        f();
        invalidate();
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setItemAlign(int i) {
        this.I = i;
        k();
        d();
        invalidate();
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setItemSpace(int i) {
        this.H = i;
        requestLayout();
        invalidate();
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setItemTextColor(int i) {
        this.B = i;
        invalidate();
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setItemTextSize(int i) {
        this.D = i;
        this.i.setTextSize(i);
        h();
        requestLayout();
        invalidate();
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setMaximumWidthText(String str) {
        Objects.requireNonNull(str, "Maximum width text can not be null!");
        this.v = str;
        h();
        requestLayout();
        invalidate();
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setMaximumWidthTextPosition(int i) {
        if (i(i)) {
            this.a0 = i;
            h();
            requestLayout();
            invalidate();
            return;
        }
        throw new ArrayIndexOutOfBoundsException("Maximum width text Position must in [0, " + this.u.size() + "), but current is " + i);
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.l = onItemSelectedListener;
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setOnWheelChangeListener(OnWheelChangeListener onWheelChangeListener) {
        this.m = onWheelChangeListener;
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setSameWidth(boolean z) {
        this.e0 = z;
        h();
        requestLayout();
        invalidate();
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setSelectedItemPosition(int i) {
        int max = Math.max(Math.min(i, this.u.size() - 1), 0);
        this.M = max;
        this.N = max;
        this.W = 0;
        e();
        requestLayout();
        invalidate();
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setSelectedItemTextColor(int i) {
        this.C = i;
        a();
        invalidate();
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setTypeface(Typeface typeface) {
        Paint paint = this.i;
        if (paint != null) {
            paint.setTypeface(typeface);
        }
        h();
        requestLayout();
        invalidate();
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setVisibleItemCount(int i) {
        this.w = i;
        l();
        requestLayout();
    }

    public WheelPicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = new Handler();
        this.Q = 50;
        this.R = 8000;
        this.d0 = 8;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.WheelPicker);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.WheelPicker_wheel_data, 0);
        this.u = Arrays.asList(getResources().getStringArray(resourceId == 0 ? R.array.WheelArrayDefault : resourceId));
        this.D = obtainStyledAttributes.getDimensionPixelSize(R.styleable.WheelPicker_wheel_item_text_size, getResources().getDimensionPixelSize(R.dimen.WheelItemTextSize));
        this.w = obtainStyledAttributes.getInt(R.styleable.WheelPicker_wheel_visible_item_count, 7);
        this.M = obtainStyledAttributes.getInt(R.styleable.WheelPicker_wheel_selected_item_position, 0);
        this.e0 = obtainStyledAttributes.getBoolean(R.styleable.WheelPicker_wheel_same_width, false);
        this.a0 = obtainStyledAttributes.getInt(R.styleable.WheelPicker_wheel_maximum_width_text_position, -1);
        this.v = obtainStyledAttributes.getString(R.styleable.WheelPicker_wheel_maximum_width_text);
        this.C = obtainStyledAttributes.getColor(R.styleable.WheelPicker_wheel_selected_item_text_color, -1);
        this.B = obtainStyledAttributes.getColor(R.styleable.WheelPicker_wheel_item_text_color, -7829368);
        this.H = obtainStyledAttributes.getDimensionPixelSize(R.styleable.WheelPicker_wheel_item_space, getResources().getDimensionPixelSize(R.dimen.WheelItemSpace));
        this.i0 = obtainStyledAttributes.getBoolean(R.styleable.WheelPicker_wheel_cyclic, false);
        this.f0 = obtainStyledAttributes.getBoolean(R.styleable.WheelPicker_wheel_indicator, false);
        this.F = obtainStyledAttributes.getColor(R.styleable.WheelPicker_wheel_indicator_color, -1166541);
        this.E = obtainStyledAttributes.getDimensionPixelSize(R.styleable.WheelPicker_wheel_indicator_size, getResources().getDimensionPixelSize(R.dimen.WheelIndicatorSize));
        this.g0 = obtainStyledAttributes.getBoolean(R.styleable.WheelPicker_wheel_curtain, false);
        this.G = obtainStyledAttributes.getColor(R.styleable.WheelPicker_wheel_curtain_color, -1996488705);
        this.h0 = obtainStyledAttributes.getBoolean(R.styleable.WheelPicker_wheel_atmospheric, false);
        this.j0 = obtainStyledAttributes.getBoolean(R.styleable.WheelPicker_wheel_curved, false);
        this.I = obtainStyledAttributes.getInt(R.styleable.WheelPicker_wheel_item_align, 0);
        obtainStyledAttributes.recycle();
        l();
        Paint paint = new Paint(69);
        this.i = paint;
        paint.setTextSize(this.D);
        k();
        h();
        this.j = new Scroller(getContext());
        if (Build.VERSION.SDK_INT >= 4) {
            ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
            this.Q = viewConfiguration.getScaledMinimumFlingVelocity();
            this.R = viewConfiguration.getScaledMaximumFlingVelocity();
            this.d0 = viewConfiguration.getScaledTouchSlop();
        }
        this.n = new Rect();
        this.o = new Rect();
        this.p = new Rect();
        this.q = new Rect();
        this.r = new Camera();
        this.s = new Matrix();
        this.t = new Matrix();
    }
}
