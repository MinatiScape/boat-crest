package com.mappls.sdk.gestures;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.core.view.GestureDetectorCompat;
@Keep
@UiThread
/* loaded from: classes11.dex */
public class StandardGestureDetector extends BaseGesture<StandardOnGestureListener> {
    private final GestureDetectorCompat gestureDetector;
    public final StandardOnGestureListener innerListener;

    /* loaded from: classes11.dex */
    public static class SimpleStandardOnGestureListener implements StandardOnGestureListener {
        @Override // android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(@NonNull MotionEvent motionEvent) {
            return false;
        }

        @Override // android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTapEvent(@NonNull MotionEvent motionEvent) {
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onDown(@NonNull MotionEvent motionEvent) {
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onFling(@NonNull MotionEvent motionEvent, @NonNull MotionEvent motionEvent2, float f, float f2) {
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onLongPress(@NonNull MotionEvent motionEvent) {
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onScroll(@NonNull MotionEvent motionEvent, @NonNull MotionEvent motionEvent2, float f, float f2) {
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onShowPress(@NonNull MotionEvent motionEvent) {
        }

        @Override // android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(@NonNull MotionEvent motionEvent) {
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(@NonNull MotionEvent motionEvent) {
            return false;
        }
    }

    /* loaded from: classes11.dex */
    public interface StandardOnGestureListener extends GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    }

    /* loaded from: classes11.dex */
    public class a implements StandardOnGestureListener {
        public a() {
        }

        @Override // android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            return StandardGestureDetector.this.canExecute(10) && ((StandardOnGestureListener) StandardGestureDetector.this.listener).onDoubleTap(motionEvent);
        }

        @Override // android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            return StandardGestureDetector.this.canExecute(11) && ((StandardOnGestureListener) StandardGestureDetector.this.listener).onDoubleTapEvent(motionEvent);
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            return StandardGestureDetector.this.canExecute(9) && ((StandardOnGestureListener) StandardGestureDetector.this.listener).onDown(motionEvent);
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return StandardGestureDetector.this.canExecute(7) && ((StandardOnGestureListener) StandardGestureDetector.this.listener).onFling(motionEvent, motionEvent2, f, f2);
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            if (StandardGestureDetector.this.canExecute(6)) {
                ((StandardOnGestureListener) StandardGestureDetector.this.listener).onLongPress(motionEvent);
            }
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return StandardGestureDetector.this.canExecute(0) && ((StandardOnGestureListener) StandardGestureDetector.this.listener).onScroll(motionEvent, motionEvent2, f, f2);
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onShowPress(MotionEvent motionEvent) {
            if (StandardGestureDetector.this.canExecute(8)) {
                ((StandardOnGestureListener) StandardGestureDetector.this.listener).onShowPress(motionEvent);
            }
        }

        @Override // android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            return StandardGestureDetector.this.canExecute(12) && ((StandardOnGestureListener) StandardGestureDetector.this.listener).onSingleTapConfirmed(motionEvent);
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return StandardGestureDetector.this.canExecute(5) && ((StandardOnGestureListener) StandardGestureDetector.this.listener).onSingleTapUp(motionEvent);
        }
    }

    public StandardGestureDetector(Context context, AndroidGesturesManager androidGesturesManager) {
        super(context, androidGesturesManager);
        a aVar = new a();
        this.innerListener = aVar;
        this.gestureDetector = new GestureDetectorCompat(context, aVar);
    }

    @Override // com.mappls.sdk.gestures.BaseGesture
    public boolean analyzeEvent(@NonNull MotionEvent motionEvent) {
        return this.gestureDetector.onTouchEvent(motionEvent);
    }

    public boolean isLongpressEnabled() {
        return this.gestureDetector.isLongpressEnabled();
    }

    public void setIsLongpressEnabled(boolean z) {
        this.gestureDetector.setIsLongpressEnabled(z);
    }
}
