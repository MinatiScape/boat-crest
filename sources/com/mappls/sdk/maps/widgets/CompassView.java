package com.mappls.sdk.maps.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import com.mappls.sdk.maps.MapplsMap;
/* loaded from: classes11.dex */
public final class CompassView extends ImageView implements Runnable {
    public static final long TIME_MAP_NORTH_ANIMATION = 150;
    public static final long TIME_WAIT_IDLE = 500;
    public float h;
    public boolean i;
    @Nullable
    public ViewPropertyAnimatorCompat j;
    public MapplsMap.OnCompassAnimationListener k;
    public boolean l;

    /* loaded from: classes11.dex */
    public class a extends ViewPropertyAnimatorListenerAdapter {
        public a() {
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationEnd(View view) {
            CompassView.this.setLayerType(0, null);
            CompassView.this.setVisibility(4);
            CompassView.this.resetAnimation();
        }
    }

    public CompassView(@NonNull Context context) {
        super(context);
        this.h = 0.0f;
        this.i = true;
        this.l = false;
        a(context);
    }

    public final void a(Context context) {
        setEnabled(false);
        int i = (int) (context.getResources().getDisplayMetrics().density * 48.0f);
        setLayoutParams(new ViewGroup.LayoutParams(i, i));
    }

    public final void b() {
        if (this.l) {
            this.k.onCompassAnimation();
        }
    }

    public void fadeCompassViewFacingNorth(boolean z) {
        this.i = z;
    }

    public Drawable getCompassImage() {
        return getDrawable();
    }

    public void injectCompassAnimationListener(@NonNull MapplsMap.OnCompassAnimationListener onCompassAnimationListener) {
        this.k = onCompassAnimationListener;
    }

    public void isAnimating(boolean z) {
        this.l = z;
    }

    public boolean isFacingNorth() {
        return ((double) Math.abs(this.h)) >= 359.0d || ((double) Math.abs(this.h)) <= 1.0d;
    }

    public boolean isFadeCompassViewFacingNorth() {
        return this.i;
    }

    public boolean isHidden() {
        return this.i && isFacingNorth();
    }

    public void resetAnimation() {
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = this.j;
        if (viewPropertyAnimatorCompat != null) {
            viewPropertyAnimatorCompat.cancel();
        }
        this.j = null;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (isHidden()) {
            this.k.onCompassAnimationFinished();
            resetAnimation();
            setLayerType(2, null);
            ViewPropertyAnimatorCompat duration = ViewCompat.animate(this).alpha(0.0f).setDuration(500L);
            this.j = duration;
            duration.setListener(new a());
        }
    }

    public void setCompassImage(Drawable drawable) {
        setImageDrawable(drawable);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (z && !isHidden()) {
            resetAnimation();
            setAlpha(1.0f);
            setVisibility(0);
            update(this.h);
            return;
        }
        resetAnimation();
        setAlpha(0.0f);
        setVisibility(4);
    }

    public void update(double d) {
        this.h = (float) d;
        if (isEnabled()) {
            if (isHidden()) {
                if (getVisibility() == 4 || this.j != null) {
                    return;
                }
                postDelayed(this, 500L);
                return;
            }
            resetAnimation();
            setAlpha(1.0f);
            setVisibility(0);
            b();
            setRotation(this.h);
        }
    }

    public void setCompassImage(Bitmap bitmap) {
        setImageBitmap(bitmap);
    }

    public CompassView(@NonNull Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = 0.0f;
        this.i = true;
        this.l = false;
        a(context);
    }

    public CompassView(@NonNull Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = 0.0f;
        this.i = true;
        this.l = false;
        a(context);
    }
}
