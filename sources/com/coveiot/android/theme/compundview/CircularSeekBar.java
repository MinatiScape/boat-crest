package com.coveiot.android.theme.compundview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public class CircularSeekBar extends View {
    public static final int DEFAULT_CIRCLE_COLOR = -12303292;
    public static final int DEFAULT_CIRCLE_FILL_COLOR = 0;
    public static final float DEFAULT_CIRCLE_STROKE_WIDTH = 5.0f;
    public static final float DEFAULT_CIRCLE_X_RADIUS = 30.0f;
    public static final float DEFAULT_CIRCLE_Y_RADIUS = 30.0f;
    public static final float DEFAULT_END_ANGLE = 270.0f;
    public static final boolean DEFAULT_LOCK_ENABLED = true;
    public static final boolean DEFAULT_MAINTAIN_EQUAL_CIRCLE = true;
    public static final int DEFAULT_MAX = 100;
    public static final boolean DEFAULT_MOVE_OUTSIDE_CIRCLE = false;
    public static final int DEFAULT_POINTER_ALPHA = 135;
    public static final int DEFAULT_POINTER_ALPHA_ONTOUCH = 100;
    public static final float DEFAULT_POINTER_HALO_BORDER_WIDTH = 2.0f;
    public static final float DEFAULT_POINTER_HALO_WIDTH = 6.0f;
    public static final float DEFAULT_POINTER_RADIUS = 7.0f;
    public static final int DEFAULT_PROGRESS = 0;
    public static final float DEFAULT_START_ANGLE = 270.0f;
    public static final boolean DEFAULT_USE_CUSTOM_RADII = false;
    public final float DPTOPX_SCALE;
    public final float MIN_TOUCH_TARGET_DP;
    public float ccwDistanceFromEnd;
    public float ccwDistanceFromPointer;
    public float ccwDistanceFromStart;
    public float cwDistanceFromEnd;
    public float cwDistanceFromPointer;
    public float cwDistanceFromStart;
    public boolean isTouchEnabled;
    public float lastCWDistanceFromStart;
    public boolean lockAtEnd;
    public boolean lockAtStart;
    public boolean lockEnabled;
    public int mCircleColor;
    public int mCircleFillColor;
    public Paint mCircleFillPaint;
    public float mCircleHeight;
    public Paint mCirclePaint;
    public Path mCirclePath;
    public int mCircleProgressColor;
    public Paint mCircleProgressGlowPaint;
    public Paint mCircleProgressPaint;
    public Path mCircleProgressPath;
    public RectF mCircleRectF;
    public float mCircleStrokeWidth;
    public float mCircleWidth;
    public float mCircleXRadius;
    public float mCircleYRadius;
    public boolean mCustomRadii;
    public float mEndAngle;
    public boolean mIsMovingCW;
    public boolean mMaintainEqualCircle;
    public int mMax;
    public boolean mMoveOutsideCircle;
    public OnCircularSeekBarChangeListener mOnCircularSeekBarChangeListener;
    public int mPointerAlpha;
    public int mPointerAlphaOnTouch;
    public int mPointerColor;
    public Paint mPointerHaloBorderPaint;
    public float mPointerHaloBorderWidth;
    public int mPointerHaloColor;
    public int mPointerHaloColorOnTouch;
    public Paint mPointerHaloPaint;
    public float mPointerHaloWidth;
    public Paint mPointerPaint;
    public float mPointerPosition;
    public float[] mPointerPositionXY;
    public float mPointerRadius;
    public int mProgress;
    public float mProgressDegrees;
    public float mStartAngle;
    public float mTotalCircleDegrees;
    public boolean mUserIsMovingPointer;
    public static final int DEFAULT_CIRCLE_PROGRESS_COLOR = Color.argb(235, 74, 138, 255);
    public static final int DEFAULT_POINTER_COLOR = Color.argb(235, 74, 138, 255);
    public static final int DEFAULT_POINTER_HALO_COLOR = Color.argb(135, 74, 138, 255);
    public static final int DEFAULT_POINTER_HALO_COLOR_ONTOUCH = Color.argb(135, 74, 138, 255);

    /* loaded from: classes7.dex */
    public interface OnCircularSeekBarChangeListener {
        void onProgressChanged(CircularSeekBar circularSeekBar, int i, boolean z);

        void onStartTrackingTouch(CircularSeekBar circularSeekBar);

        void onStopTrackingTouch(CircularSeekBar circularSeekBar);
    }

    public CircularSeekBar(Context context) {
        super(context);
        this.DPTOPX_SCALE = getResources().getDisplayMetrics().density;
        this.MIN_TOUCH_TARGET_DP = 48.0f;
        this.mCircleRectF = new RectF();
        this.mPointerColor = DEFAULT_POINTER_COLOR;
        this.mPointerHaloColor = DEFAULT_POINTER_HALO_COLOR;
        this.mPointerHaloColorOnTouch = DEFAULT_POINTER_HALO_COLOR_ONTOUCH;
        this.mCircleColor = -12303292;
        this.mCircleFillColor = 0;
        this.mCircleProgressColor = DEFAULT_CIRCLE_PROGRESS_COLOR;
        this.mPointerAlpha = 135;
        this.mPointerAlphaOnTouch = 100;
        this.lockEnabled = true;
        this.lockAtStart = true;
        this.lockAtEnd = false;
        this.mUserIsMovingPointer = false;
        this.mPointerPositionXY = new float[2];
        this.isTouchEnabled = true;
        init(null, 0);
    }

    public void calculatePointerAngle() {
        float f = ((this.mProgress / this.mMax) * this.mTotalCircleDegrees) + this.mStartAngle;
        this.mPointerPosition = f;
        this.mPointerPosition = f % 360.0f;
    }

    public void calculatePointerXYPosition() {
        PathMeasure pathMeasure = new PathMeasure(this.mCircleProgressPath, false);
        if (pathMeasure.getPosTan(pathMeasure.getLength(), this.mPointerPositionXY, null)) {
            return;
        }
        new PathMeasure(this.mCirclePath, false).getPosTan(0.0f, this.mPointerPositionXY, null);
    }

    public void calculateProgressDegrees() {
        float f = this.mPointerPosition - this.mStartAngle;
        this.mProgressDegrees = f;
        if (f < 0.0f) {
            f += 360.0f;
        }
        this.mProgressDegrees = f;
    }

    public void calculateTotalDegrees() {
        float f = (360.0f - (this.mStartAngle - this.mEndAngle)) % 360.0f;
        this.mTotalCircleDegrees = f;
        if (f <= 0.0f) {
            this.mTotalCircleDegrees = 360.0f;
        }
    }

    public int getCircleColor() {
        return this.mCircleColor;
    }

    public int getCircleFillColor() {
        return this.mCircleFillColor;
    }

    public int getCircleProgressColor() {
        return this.mCircleProgressColor;
    }

    public boolean getIsTouchEnabled() {
        return this.isTouchEnabled;
    }

    public synchronized int getMax() {
        return this.mMax;
    }

    public int getPointerAlpha() {
        return this.mPointerAlpha;
    }

    public int getPointerAlphaOnTouch() {
        return this.mPointerAlphaOnTouch;
    }

    public int getPointerColor() {
        return this.mPointerColor;
    }

    public int getPointerHaloColor() {
        return this.mPointerHaloColor;
    }

    public int getProgress() {
        return Math.round((this.mMax * this.mProgressDegrees) / this.mTotalCircleDegrees);
    }

    public void init(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.CircularSeekBar, i, 0);
        initAttributes(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        initPaints();
    }

    public void initAttributes(TypedArray typedArray) {
        this.mCircleXRadius = typedArray.getDimension(R.styleable.CircularSeekBar_circle_x_radius, this.DPTOPX_SCALE * 30.0f);
        this.mCircleYRadius = typedArray.getDimension(R.styleable.CircularSeekBar_circle_y_radius, this.DPTOPX_SCALE * 30.0f);
        this.mPointerRadius = typedArray.getDimension(R.styleable.CircularSeekBar_pointer_radius, this.DPTOPX_SCALE * 7.0f);
        this.mPointerHaloWidth = typedArray.getDimension(R.styleable.CircularSeekBar_pointer_halo_width, this.DPTOPX_SCALE * 6.0f);
        this.mPointerHaloBorderWidth = typedArray.getDimension(R.styleable.CircularSeekBar_pointer_halo_border_width, this.DPTOPX_SCALE * 2.0f);
        this.mCircleStrokeWidth = typedArray.getDimension(R.styleable.CircularSeekBar_circle_stroke_width, this.DPTOPX_SCALE * 5.0f);
        this.mPointerColor = typedArray.getColor(R.styleable.CircularSeekBar_pointer_color, DEFAULT_POINTER_COLOR);
        this.mPointerHaloColor = typedArray.getColor(R.styleable.CircularSeekBar_pointer_halo_color, DEFAULT_POINTER_HALO_COLOR);
        this.mPointerHaloColorOnTouch = typedArray.getColor(R.styleable.CircularSeekBar_pointer_halo_color_ontouch, DEFAULT_POINTER_HALO_COLOR_ONTOUCH);
        this.mCircleColor = typedArray.getColor(R.styleable.CircularSeekBar_circle_color, -12303292);
        this.mCircleProgressColor = typedArray.getColor(R.styleable.CircularSeekBar_circle_progress_color, DEFAULT_CIRCLE_PROGRESS_COLOR);
        this.mCircleFillColor = typedArray.getColor(R.styleable.CircularSeekBar_circle_fill, 0);
        this.mPointerAlpha = Color.alpha(this.mPointerHaloColor);
        int i = typedArray.getInt(R.styleable.CircularSeekBar_pointer_alpha_ontouch, 100);
        this.mPointerAlphaOnTouch = i;
        if (i > 255 || i < 0) {
            this.mPointerAlphaOnTouch = 100;
        }
        this.mMax = typedArray.getInt(R.styleable.CircularSeekBar_max, 100);
        this.mProgress = typedArray.getInt(R.styleable.CircularSeekBar_progress, 0);
        this.mCustomRadii = typedArray.getBoolean(R.styleable.CircularSeekBar_use_custom_radii, false);
        this.mMaintainEqualCircle = typedArray.getBoolean(R.styleable.CircularSeekBar_maintain_equal_circle, true);
        this.mMoveOutsideCircle = typedArray.getBoolean(R.styleable.CircularSeekBar_move_outside_circle, false);
        this.lockEnabled = typedArray.getBoolean(R.styleable.CircularSeekBar_lock_enabled, true);
        this.mStartAngle = ((typedArray.getFloat(R.styleable.CircularSeekBar_start_angle, 270.0f) % 360.0f) + 360.0f) % 360.0f;
        float f = ((typedArray.getFloat(R.styleable.CircularSeekBar_end_angle, 270.0f) % 360.0f) + 360.0f) % 360.0f;
        this.mEndAngle = f;
        if (this.mStartAngle == f) {
            this.mEndAngle = f - 0.1f;
        }
    }

    public void initPaints() {
        Paint paint = new Paint();
        this.mCirclePaint = paint;
        paint.setAntiAlias(true);
        this.mCirclePaint.setDither(true);
        this.mCirclePaint.setColor(this.mCircleColor);
        this.mCirclePaint.setStrokeWidth(this.mCircleStrokeWidth);
        this.mCirclePaint.setStyle(Paint.Style.STROKE);
        this.mCirclePaint.setStrokeJoin(Paint.Join.ROUND);
        this.mCirclePaint.setStrokeCap(Paint.Cap.ROUND);
        Paint paint2 = new Paint();
        this.mCircleFillPaint = paint2;
        paint2.setAntiAlias(true);
        this.mCircleFillPaint.setDither(true);
        this.mCircleFillPaint.setColor(this.mCircleFillColor);
        this.mCircleFillPaint.setStyle(Paint.Style.FILL);
        Paint paint3 = new Paint();
        this.mCircleProgressPaint = paint3;
        paint3.setAntiAlias(true);
        this.mCircleProgressPaint.setDither(true);
        this.mCircleProgressPaint.setColor(this.mCircleProgressColor);
        this.mCircleProgressPaint.setStrokeWidth(this.mCircleStrokeWidth);
        this.mCircleProgressPaint.setStyle(Paint.Style.STROKE);
        this.mCircleProgressPaint.setStrokeJoin(Paint.Join.ROUND);
        this.mCircleProgressPaint.setStrokeCap(Paint.Cap.ROUND);
        Paint paint4 = new Paint();
        this.mCircleProgressGlowPaint = paint4;
        paint4.set(this.mCircleProgressPaint);
        this.mCircleProgressGlowPaint.setMaskFilter(new BlurMaskFilter(this.DPTOPX_SCALE * 5.0f, BlurMaskFilter.Blur.NORMAL));
        Paint paint5 = new Paint();
        this.mPointerPaint = paint5;
        paint5.setAntiAlias(true);
        this.mPointerPaint.setDither(true);
        this.mPointerPaint.setStyle(Paint.Style.FILL);
        this.mPointerPaint.setColor(this.mPointerColor);
        this.mPointerPaint.setStrokeWidth(this.mPointerRadius);
        Paint paint6 = new Paint();
        this.mPointerHaloPaint = paint6;
        paint6.set(this.mPointerPaint);
        this.mPointerHaloPaint.setColor(this.mPointerHaloColor);
        this.mPointerHaloPaint.setAlpha(this.mPointerAlpha);
        this.mPointerHaloPaint.setStrokeWidth(this.mPointerRadius + this.mPointerHaloWidth);
        Paint paint7 = new Paint();
        this.mPointerHaloBorderPaint = paint7;
        paint7.set(this.mPointerPaint);
        this.mPointerHaloBorderPaint.setStrokeWidth(this.mPointerHaloBorderWidth);
        this.mPointerHaloBorderPaint.setStyle(Paint.Style.STROKE);
    }

    public void initPaths() {
        Path path = new Path();
        this.mCirclePath = path;
        path.addArc(this.mCircleRectF, this.mStartAngle, this.mTotalCircleDegrees);
        Path path2 = new Path();
        this.mCircleProgressPath = path2;
        path2.addArc(this.mCircleRectF, this.mStartAngle, this.mProgressDegrees);
    }

    public void initRects() {
        RectF rectF = this.mCircleRectF;
        float f = this.mCircleWidth;
        float f2 = this.mCircleHeight;
        rectF.set(-f, -f2, f, f2);
    }

    public boolean isLockEnabled() {
        return this.lockEnabled;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.drawPath(this.mCirclePath, this.mCirclePaint);
        canvas.drawPath(this.mCircleProgressPath, this.mCircleProgressGlowPaint);
        canvas.drawPath(this.mCircleProgressPath, this.mCircleProgressPaint);
        canvas.drawPath(this.mCirclePath, this.mCircleFillPaint);
        float[] fArr = this.mPointerPositionXY;
        canvas.drawCircle(fArr[0], fArr[1], this.mPointerRadius, this.mPointerPaint);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int defaultSize = View.getDefaultSize(getSuggestedMinimumHeight(), i2);
        int defaultSize2 = View.getDefaultSize(getSuggestedMinimumWidth(), i);
        if (this.mMaintainEqualCircle) {
            int min = Math.min(defaultSize2, defaultSize);
            setMeasuredDimension(min, min);
        } else {
            setMeasuredDimension(defaultSize2, defaultSize);
        }
        float f = this.mCircleStrokeWidth;
        float f2 = this.mPointerRadius;
        float f3 = this.mPointerHaloBorderWidth;
        float f4 = (((defaultSize / 2.0f) - f) - f2) - (f3 * 1.5f);
        this.mCircleHeight = f4;
        float f5 = (((defaultSize2 / 2.0f) - f) - f2) - (f3 * 1.5f);
        this.mCircleWidth = f5;
        if (this.mCustomRadii) {
            float f6 = this.mCircleYRadius;
            if (((f6 - f) - f2) - f3 < f4) {
                this.mCircleHeight = ((f6 - f) - f2) - (f3 * 1.5f);
            }
            float f7 = this.mCircleXRadius;
            if (((f7 - f) - f2) - f3 < f5) {
                this.mCircleWidth = ((f7 - f) - f2) - (f3 * 1.5f);
            }
        }
        if (this.mMaintainEqualCircle) {
            float min2 = Math.min(this.mCircleHeight, this.mCircleWidth);
            this.mCircleHeight = min2;
            this.mCircleWidth = min2;
        }
        recalculateAll();
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        Bundle bundle = (Bundle) parcelable;
        super.onRestoreInstanceState(bundle.getParcelable("PARENT"));
        this.mMax = bundle.getInt("MAX");
        this.mProgress = bundle.getInt("PROGRESS");
        this.mCircleColor = bundle.getInt("mCircleColor");
        this.mCircleProgressColor = bundle.getInt("mCircleProgressColor");
        this.mPointerColor = bundle.getInt("mPointerColor");
        this.mPointerHaloColor = bundle.getInt("mPointerHaloColor");
        this.mPointerHaloColorOnTouch = bundle.getInt("mPointerHaloColorOnTouch");
        this.mPointerAlpha = bundle.getInt("mPointerAlpha");
        this.mPointerAlphaOnTouch = bundle.getInt("mPointerAlphaOnTouch");
        this.lockEnabled = bundle.getBoolean("lockEnabled");
        this.isTouchEnabled = bundle.getBoolean("isTouchEnabled");
        initPaints();
        recalculateAll();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putParcelable("PARENT", onSaveInstanceState);
        bundle.putInt("MAX", this.mMax);
        bundle.putInt("PROGRESS", this.mProgress);
        bundle.putInt("mCircleColor", this.mCircleColor);
        bundle.putInt("mCircleProgressColor", this.mCircleProgressColor);
        bundle.putInt("mPointerColor", this.mPointerColor);
        bundle.putInt("mPointerHaloColor", this.mPointerHaloColor);
        bundle.putInt("mPointerHaloColorOnTouch", this.mPointerHaloColorOnTouch);
        bundle.putInt("mPointerAlpha", this.mPointerAlpha);
        bundle.putInt("mPointerAlphaOnTouch", this.mPointerAlphaOnTouch);
        bundle.putBoolean("lockEnabled", this.lockEnabled);
        bundle.putBoolean("isTouchEnabled", this.isTouchEnabled);
        return bundle;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.isTouchEnabled) {
            float x = motionEvent.getX() - (getWidth() / 2);
            float y = motionEvent.getY() - (getHeight() / 2);
            float sqrt = (float) Math.sqrt(Math.pow(this.mCircleRectF.centerX() - x, 2.0d) + Math.pow(this.mCircleRectF.centerY() - y, 2.0d));
            float f = this.DPTOPX_SCALE * 48.0f;
            float f2 = this.mCircleStrokeWidth;
            float f3 = f2 < f ? f / 2.0f : f2 / 2.0f;
            float max = Math.max(this.mCircleHeight, this.mCircleWidth) + f3;
            float min = Math.min(this.mCircleHeight, this.mCircleWidth) - f3;
            float atan2 = (float) (((Math.atan2(y, x) / 3.141592653589793d) * 180.0d) % 360.0d);
            if (atan2 < 0.0f) {
                atan2 += 360.0f;
            }
            float f4 = atan2 - this.mStartAngle;
            this.cwDistanceFromStart = f4;
            if (f4 < 0.0f) {
                f4 += 360.0f;
            }
            this.cwDistanceFromStart = f4;
            this.ccwDistanceFromStart = 360.0f - f4;
            float f5 = atan2 - this.mEndAngle;
            this.cwDistanceFromEnd = f5;
            if (f5 < 0.0f) {
                f5 += 360.0f;
            }
            this.cwDistanceFromEnd = f5;
            this.ccwDistanceFromEnd = 360.0f - f5;
            int action = motionEvent.getAction();
            if (action == 0) {
                float max2 = (float) ((this.mPointerRadius * 180.0f) / (Math.max(this.mCircleHeight, this.mCircleWidth) * 3.141592653589793d));
                float f6 = this.mPointerPosition;
                float f7 = atan2 - f6;
                this.cwDistanceFromPointer = f7;
                if (f7 < 0.0f) {
                    f7 += 360.0f;
                }
                this.cwDistanceFromPointer = f7;
                float f8 = 360.0f - f7;
                this.ccwDistanceFromPointer = f8;
                int i = (sqrt > min ? 1 : (sqrt == min ? 0 : -1));
                if (i >= 0 && sqrt <= max && (f7 <= max2 || f8 <= max2)) {
                    setProgressBasedOnAngle(f6);
                    this.lastCWDistanceFromStart = this.cwDistanceFromStart;
                    this.mIsMovingCW = true;
                    this.mPointerHaloPaint.setAlpha(this.mPointerAlphaOnTouch);
                    this.mPointerHaloPaint.setColor(this.mPointerHaloColorOnTouch);
                    recalculateAll();
                    invalidate();
                    OnCircularSeekBarChangeListener onCircularSeekBarChangeListener = this.mOnCircularSeekBarChangeListener;
                    if (onCircularSeekBarChangeListener != null) {
                        onCircularSeekBarChangeListener.onStartTrackingTouch(this);
                    }
                    this.mUserIsMovingPointer = true;
                    this.lockAtEnd = false;
                    this.lockAtStart = false;
                } else if (this.cwDistanceFromStart > this.mTotalCircleDegrees) {
                    this.mUserIsMovingPointer = false;
                    return false;
                } else if (i >= 0 && sqrt <= max) {
                    setProgressBasedOnAngle(atan2);
                    this.lastCWDistanceFromStart = this.cwDistanceFromStart;
                    this.mIsMovingCW = true;
                    this.mPointerHaloPaint.setAlpha(this.mPointerAlphaOnTouch);
                    this.mPointerHaloPaint.setColor(this.mPointerHaloColorOnTouch);
                    recalculateAll();
                    invalidate();
                    OnCircularSeekBarChangeListener onCircularSeekBarChangeListener2 = this.mOnCircularSeekBarChangeListener;
                    if (onCircularSeekBarChangeListener2 != null) {
                        onCircularSeekBarChangeListener2.onStartTrackingTouch(this);
                        this.mOnCircularSeekBarChangeListener.onProgressChanged(this, this.mProgress, true);
                    }
                    this.mUserIsMovingPointer = true;
                    this.lockAtEnd = false;
                    this.lockAtStart = false;
                } else {
                    this.mUserIsMovingPointer = false;
                    return false;
                }
            } else if (action == 1) {
                this.mPointerHaloPaint.setAlpha(this.mPointerAlpha);
                this.mPointerHaloPaint.setColor(this.mPointerHaloColor);
                if (!this.mUserIsMovingPointer) {
                    return false;
                }
                this.mUserIsMovingPointer = false;
                invalidate();
                OnCircularSeekBarChangeListener onCircularSeekBarChangeListener3 = this.mOnCircularSeekBarChangeListener;
                if (onCircularSeekBarChangeListener3 != null) {
                    onCircularSeekBarChangeListener3.onStopTrackingTouch(this);
                }
            } else if (action != 2) {
                if (action == 3) {
                    this.mPointerHaloPaint.setAlpha(this.mPointerAlpha);
                    this.mPointerHaloPaint.setColor(this.mPointerHaloColor);
                    this.mUserIsMovingPointer = false;
                    invalidate();
                }
            } else if (!this.mUserIsMovingPointer) {
                return false;
            } else {
                float f9 = this.lastCWDistanceFromStart;
                float f10 = this.cwDistanceFromStart;
                if (f9 < f10) {
                    if (f10 - f9 > 180.0f && !this.mIsMovingCW) {
                        this.lockAtStart = true;
                        this.lockAtEnd = false;
                    } else {
                        this.mIsMovingCW = true;
                    }
                } else if (f9 - f10 > 180.0f && this.mIsMovingCW) {
                    this.lockAtEnd = true;
                    this.lockAtStart = false;
                } else {
                    this.mIsMovingCW = false;
                }
                if (this.lockAtStart && this.mIsMovingCW) {
                    this.lockAtStart = false;
                }
                if (this.lockAtEnd && !this.mIsMovingCW) {
                    this.lockAtEnd = false;
                }
                if (this.lockAtStart && !this.mIsMovingCW && this.ccwDistanceFromStart > 90.0f) {
                    this.lockAtStart = false;
                }
                if (this.lockAtEnd && this.mIsMovingCW && this.cwDistanceFromEnd > 90.0f) {
                    this.lockAtEnd = false;
                }
                if (!this.lockAtEnd) {
                    float f11 = this.mTotalCircleDegrees;
                    if (f10 > f11 && this.mIsMovingCW && f9 < f11) {
                        this.lockAtEnd = true;
                    }
                }
                if (this.lockAtStart && this.lockEnabled) {
                    this.mProgress = 0;
                    recalculateAll();
                    invalidate();
                    OnCircularSeekBarChangeListener onCircularSeekBarChangeListener4 = this.mOnCircularSeekBarChangeListener;
                    if (onCircularSeekBarChangeListener4 != null) {
                        onCircularSeekBarChangeListener4.onProgressChanged(this, this.mProgress, true);
                    }
                } else if (this.lockAtEnd && this.lockEnabled) {
                    this.mProgress = this.mMax;
                    recalculateAll();
                    invalidate();
                    OnCircularSeekBarChangeListener onCircularSeekBarChangeListener5 = this.mOnCircularSeekBarChangeListener;
                    if (onCircularSeekBarChangeListener5 != null) {
                        onCircularSeekBarChangeListener5.onProgressChanged(this, this.mProgress, true);
                    }
                } else if (this.mMoveOutsideCircle || sqrt <= max) {
                    if (f10 <= this.mTotalCircleDegrees) {
                        setProgressBasedOnAngle(atan2);
                    }
                    recalculateAll();
                    invalidate();
                    OnCircularSeekBarChangeListener onCircularSeekBarChangeListener6 = this.mOnCircularSeekBarChangeListener;
                    if (onCircularSeekBarChangeListener6 != null) {
                        onCircularSeekBarChangeListener6.onProgressChanged(this, this.mProgress, true);
                    }
                }
                this.lastCWDistanceFromStart = this.cwDistanceFromStart;
            }
            if (motionEvent.getAction() == 2 && getParent() != null) {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
            return true;
        }
        return false;
    }

    public void recalculateAll() {
        calculateTotalDegrees();
        calculatePointerAngle();
        calculateProgressDegrees();
        initRects();
        initPaths();
        calculatePointerXYPosition();
    }

    public void setCircleColor(int i) {
        this.mCircleColor = i;
        this.mCirclePaint.setColor(i);
        invalidate();
    }

    public void setCircleFillColor(int i) {
        this.mCircleFillColor = i;
        this.mCircleFillPaint.setColor(i);
        invalidate();
    }

    public void setCircleProgressColor(int i) {
        this.mCircleProgressColor = i;
        this.mCircleProgressPaint.setColor(i);
        invalidate();
    }

    public void setIsTouchEnabled(boolean z) {
        this.isTouchEnabled = z;
    }

    public void setLockEnabled(boolean z) {
        this.lockEnabled = z;
    }

    public void setMax(int i) {
        if (i > 0) {
            if (i <= this.mProgress) {
                this.mProgress = 0;
                OnCircularSeekBarChangeListener onCircularSeekBarChangeListener = this.mOnCircularSeekBarChangeListener;
                if (onCircularSeekBarChangeListener != null) {
                    onCircularSeekBarChangeListener.onProgressChanged(this, 0, false);
                }
            }
            this.mMax = i;
            recalculateAll();
            invalidate();
        }
    }

    public void setOnSeekBarChangeListener(OnCircularSeekBarChangeListener onCircularSeekBarChangeListener) {
        this.mOnCircularSeekBarChangeListener = onCircularSeekBarChangeListener;
    }

    public void setPointerAlpha(int i) {
        if (i < 0 || i > 255) {
            return;
        }
        this.mPointerAlpha = i;
        this.mPointerHaloPaint.setAlpha(i);
        invalidate();
    }

    public void setPointerAlphaOnTouch(int i) {
        if (i < 0 || i > 255) {
            return;
        }
        this.mPointerAlphaOnTouch = i;
    }

    public void setPointerColor(int i) {
        this.mPointerColor = i;
        this.mPointerPaint.setColor(i);
        invalidate();
    }

    public void setPointerHaloColor(int i) {
        this.mPointerHaloColor = i;
        this.mPointerHaloPaint.setColor(i);
        invalidate();
    }

    public void setProgress(int i) {
        if (this.mProgress != i) {
            this.mProgress = i;
            OnCircularSeekBarChangeListener onCircularSeekBarChangeListener = this.mOnCircularSeekBarChangeListener;
            if (onCircularSeekBarChangeListener != null) {
                onCircularSeekBarChangeListener.onProgressChanged(this, i, false);
            }
            recalculateAll();
            invalidate();
        }
    }

    public void setProgressBasedOnAngle(float f) {
        this.mPointerPosition = f;
        calculateProgressDegrees();
        this.mProgress = Math.round((this.mMax * this.mProgressDegrees) / this.mTotalCircleDegrees);
    }

    public CircularSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.DPTOPX_SCALE = getResources().getDisplayMetrics().density;
        this.MIN_TOUCH_TARGET_DP = 48.0f;
        this.mCircleRectF = new RectF();
        this.mPointerColor = DEFAULT_POINTER_COLOR;
        this.mPointerHaloColor = DEFAULT_POINTER_HALO_COLOR;
        this.mPointerHaloColorOnTouch = DEFAULT_POINTER_HALO_COLOR_ONTOUCH;
        this.mCircleColor = -12303292;
        this.mCircleFillColor = 0;
        this.mCircleProgressColor = DEFAULT_CIRCLE_PROGRESS_COLOR;
        this.mPointerAlpha = 135;
        this.mPointerAlphaOnTouch = 100;
        this.lockEnabled = true;
        this.lockAtStart = true;
        this.lockAtEnd = false;
        this.mUserIsMovingPointer = false;
        this.mPointerPositionXY = new float[2];
        this.isTouchEnabled = true;
        init(attributeSet, 0);
    }

    public CircularSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.DPTOPX_SCALE = getResources().getDisplayMetrics().density;
        this.MIN_TOUCH_TARGET_DP = 48.0f;
        this.mCircleRectF = new RectF();
        this.mPointerColor = DEFAULT_POINTER_COLOR;
        this.mPointerHaloColor = DEFAULT_POINTER_HALO_COLOR;
        this.mPointerHaloColorOnTouch = DEFAULT_POINTER_HALO_COLOR_ONTOUCH;
        this.mCircleColor = -12303292;
        this.mCircleFillColor = 0;
        this.mCircleProgressColor = DEFAULT_CIRCLE_PROGRESS_COLOR;
        this.mPointerAlpha = 135;
        this.mPointerAlphaOnTouch = 100;
        this.lockEnabled = true;
        this.lockAtStart = true;
        this.lockAtEnd = false;
        this.mUserIsMovingPointer = false;
        this.mPointerPositionXY = new float[2];
        this.isTouchEnabled = true;
        init(attributeSet, i);
    }
}
