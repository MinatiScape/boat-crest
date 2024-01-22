package com.yalantis.ucrop.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import com.yalantis.ucrop.util.RotationGestureDetector;
/* loaded from: classes12.dex */
public class GestureCropImageView extends CropImageView {
    public ScaleGestureDetector A;
    public RotationGestureDetector B;
    public GestureDetector C;
    public float D;
    public float E;
    public boolean F;
    public boolean G;
    public int H;

    /* loaded from: classes12.dex */
    public class b extends GestureDetector.SimpleOnGestureListener {
        public b() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            GestureCropImageView gestureCropImageView = GestureCropImageView.this;
            gestureCropImageView.zoomImageToPosition(gestureCropImageView.getDoubleTapTargetScale(), motionEvent.getX(), motionEvent.getY(), 200L);
            return super.onDoubleTap(motionEvent);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            GestureCropImageView.this.postTranslate(-f, -f2);
            return true;
        }
    }

    /* loaded from: classes12.dex */
    public class c extends RotationGestureDetector.SimpleOnRotationGestureListener {
        public c() {
        }

        @Override // com.yalantis.ucrop.util.RotationGestureDetector.SimpleOnRotationGestureListener, com.yalantis.ucrop.util.RotationGestureDetector.OnRotationGestureListener
        public boolean onRotation(RotationGestureDetector rotationGestureDetector) {
            GestureCropImageView.this.postRotate(rotationGestureDetector.getAngle(), GestureCropImageView.this.D, GestureCropImageView.this.E);
            return true;
        }
    }

    /* loaded from: classes12.dex */
    public class d extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        public d() {
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            GestureCropImageView.this.postScale(scaleGestureDetector.getScaleFactor(), GestureCropImageView.this.D, GestureCropImageView.this.E);
            return true;
        }
    }

    public GestureCropImageView(Context context) {
        super(context);
        this.F = true;
        this.G = true;
        this.H = 5;
    }

    public int getDoubleTapScaleSteps() {
        return this.H;
    }

    public float getDoubleTapTargetScale() {
        return getCurrentScale() * ((float) Math.pow(getMaxScale() / getMinScale(), 1.0f / this.H));
    }

    @Override // com.yalantis.ucrop.view.TransformImageView
    public void init() {
        super.init();
        l();
    }

    public boolean isRotateEnabled() {
        return this.F;
    }

    public boolean isScaleEnabled() {
        return this.G;
    }

    public final void l() {
        this.C = new GestureDetector(getContext(), new b(), null, true);
        this.A = new ScaleGestureDetector(getContext(), new d());
        this.B = new RotationGestureDetector(new c());
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if ((motionEvent.getAction() & 255) == 0) {
            cancelAllAnimations();
        }
        if (motionEvent.getPointerCount() > 1) {
            this.D = (motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f;
            this.E = (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f;
        }
        this.C.onTouchEvent(motionEvent);
        if (this.G) {
            this.A.onTouchEvent(motionEvent);
        }
        if (this.F) {
            this.B.onTouchEvent(motionEvent);
        }
        if ((motionEvent.getAction() & 255) == 1) {
            setImageToWrapCropBounds();
        }
        return true;
    }

    public void setDoubleTapScaleSteps(int i) {
        this.H = i;
    }

    public void setRotateEnabled(boolean z) {
        this.F = z;
    }

    public void setScaleEnabled(boolean z) {
        this.G = z;
    }

    public GestureCropImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GestureCropImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.F = true;
        this.G = true;
        this.H = 5;
    }
}
