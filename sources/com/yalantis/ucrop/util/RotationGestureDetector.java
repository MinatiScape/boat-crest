package com.yalantis.ucrop.util;

import android.view.MotionEvent;
import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public class RotationGestureDetector {

    /* renamed from: a  reason: collision with root package name */
    public float f13886a;
    public float b;
    public float c;
    public float d;
    public int e = -1;
    public int f = -1;
    public float g;
    public boolean h;
    public OnRotationGestureListener i;

    /* loaded from: classes12.dex */
    public interface OnRotationGestureListener {
        boolean onRotation(RotationGestureDetector rotationGestureDetector);
    }

    /* loaded from: classes12.dex */
    public static class SimpleOnRotationGestureListener implements OnRotationGestureListener {
        @Override // com.yalantis.ucrop.util.RotationGestureDetector.OnRotationGestureListener
        public boolean onRotation(RotationGestureDetector rotationGestureDetector) {
            return false;
        }
    }

    public RotationGestureDetector(OnRotationGestureListener onRotationGestureListener) {
        this.i = onRotationGestureListener;
    }

    public final float a(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        return b((float) Math.toDegrees((float) Math.atan2(f2 - f4, f - f3)), (float) Math.toDegrees((float) Math.atan2(f6 - f8, f5 - f7)));
    }

    public final float b(float f, float f2) {
        float f3 = (f2 % 360.0f) - (f % 360.0f);
        this.g = f3;
        if (f3 < -180.0f) {
            this.g = f3 + 360.0f;
        } else if (f3 > 180.0f) {
            this.g = f3 - 360.0f;
        }
        return this.g;
    }

    public float getAngle() {
        return this.g;
    }

    public boolean onTouchEvent(@NonNull MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.c = motionEvent.getX();
            this.d = motionEvent.getY();
            this.e = motionEvent.findPointerIndex(motionEvent.getPointerId(0));
            this.g = 0.0f;
            this.h = true;
        } else if (actionMasked == 1) {
            this.e = -1;
        } else if (actionMasked != 2) {
            if (actionMasked == 5) {
                this.f13886a = motionEvent.getX();
                this.b = motionEvent.getY();
                this.f = motionEvent.findPointerIndex(motionEvent.getPointerId(motionEvent.getActionIndex()));
                this.g = 0.0f;
                this.h = true;
            } else if (actionMasked == 6) {
                this.f = -1;
            }
        } else if (this.e != -1 && this.f != -1 && motionEvent.getPointerCount() > this.f) {
            float x = motionEvent.getX(this.e);
            float y = motionEvent.getY(this.e);
            float x2 = motionEvent.getX(this.f);
            float y2 = motionEvent.getY(this.f);
            if (this.h) {
                this.g = 0.0f;
                this.h = false;
            } else {
                a(this.f13886a, this.b, this.c, this.d, x2, y2, x, y);
            }
            OnRotationGestureListener onRotationGestureListener = this.i;
            if (onRotationGestureListener != null) {
                onRotationGestureListener.onRotation(this);
            }
            this.f13886a = x2;
            this.b = y2;
            this.c = x;
            this.d = y;
        }
        return true;
    }
}
