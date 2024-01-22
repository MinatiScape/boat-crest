package com.blankj.utilcode.util;

import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes.dex */
public class TouchUtils {
    public static final int DOWN = 8;
    public static final int LEFT = 1;
    public static final int RIGHT = 4;
    public static final int UNKNOWN = 0;
    public static final int UP = 2;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Direction {
    }

    /* loaded from: classes.dex */
    public static abstract class OnTouchUtilsListener implements View.OnTouchListener {
        public int h;
        public int i;
        public int j;
        public int k;
        public int l;
        public int m;
        public int n;
        public VelocityTracker o;
        public int p;
        public int q;

        public OnTouchUtilsListener() {
            a(-1, -1);
        }

        public final void a(int i, int i2) {
            this.i = i;
            this.j = i2;
            this.k = i;
            this.l = i2;
            this.m = 0;
            this.n = 0;
            VelocityTracker velocityTracker = this.o;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }

        public abstract boolean onDown(View view, int i, int i2, MotionEvent motionEvent);

        public abstract boolean onMove(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, MotionEvent motionEvent);

        public abstract boolean onStop(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, MotionEvent motionEvent);

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (this.h == 0) {
                this.h = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
            }
            if (this.p == 0) {
                this.p = ViewConfiguration.get(view.getContext()).getScaledMaximumFlingVelocity();
            }
            if (this.q == 0) {
                this.q = ViewConfiguration.get(view.getContext()).getScaledMinimumFlingVelocity();
            }
            if (this.o == null) {
                this.o = VelocityTracker.obtain();
            }
            this.o.addMovement(motionEvent);
            int action = motionEvent.getAction();
            if (action != 0) {
                if (action != 1) {
                    if (action == 2) {
                        return onUtilsMove(view, motionEvent);
                    }
                    if (action != 3) {
                        return false;
                    }
                }
                return onUtilsStop(view, motionEvent);
            }
            return onUtilsDown(view, motionEvent);
        }

        public boolean onUtilsDown(View view, MotionEvent motionEvent) {
            int rawX = (int) motionEvent.getRawX();
            int rawY = (int) motionEvent.getRawY();
            a(rawX, rawY);
            view.setPressed(true);
            return onDown(view, rawX, rawY, motionEvent);
        }

        public boolean onUtilsMove(View view, MotionEvent motionEvent) {
            int rawX = (int) motionEvent.getRawX();
            int rawY = (int) motionEvent.getRawY();
            if (this.i == -1) {
                a(rawX, rawY);
                view.setPressed(true);
            }
            if (this.m != 1) {
                if (Math.abs(rawX - this.k) < this.h && Math.abs(rawY - this.l) < this.h) {
                    return true;
                }
                this.m = 1;
                if (Math.abs(rawX - this.k) >= Math.abs(rawY - this.l)) {
                    if (rawX - this.k < 0) {
                        this.n = 1;
                    } else {
                        this.n = 4;
                    }
                } else if (rawY - this.l < 0) {
                    this.n = 2;
                } else {
                    this.n = 8;
                }
            }
            boolean onMove = onMove(view, this.n, rawX, rawY, rawX - this.k, rawY - this.l, rawX - this.i, rawY - this.j, motionEvent);
            this.k = rawX;
            this.l = rawY;
            return onMove;
        }

        public boolean onUtilsStop(View view, MotionEvent motionEvent) {
            int i;
            int i2;
            int rawX = (int) motionEvent.getRawX();
            int rawY = (int) motionEvent.getRawY();
            VelocityTracker velocityTracker = this.o;
            if (velocityTracker != null) {
                velocityTracker.computeCurrentVelocity(1000, this.p);
                int xVelocity = (int) this.o.getXVelocity();
                int yVelocity = (int) this.o.getYVelocity();
                this.o.recycle();
                if (Math.abs(xVelocity) < this.q) {
                    xVelocity = 0;
                }
                if (Math.abs(yVelocity) < this.q) {
                    yVelocity = 0;
                }
                this.o = null;
                i = xVelocity;
                i2 = yVelocity;
            } else {
                i = 0;
                i2 = 0;
            }
            view.setPressed(false);
            boolean onStop = onStop(view, this.n, rawX, rawY, rawX - this.i, rawY - this.j, i, i2, motionEvent);
            if (motionEvent.getAction() == 1 && this.m == 0) {
                if (motionEvent.getEventTime() - motionEvent.getDownTime() <= 1000) {
                    view.performClick();
                } else {
                    view.performLongClick();
                }
            }
            a(-1, -1);
            return onStop;
        }
    }

    public TouchUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void setOnTouchListener(View view, OnTouchUtilsListener onTouchUtilsListener) {
        if (view == null || onTouchUtilsListener == null) {
            return;
        }
        view.setOnTouchListener(onTouchUtilsListener);
    }
}
