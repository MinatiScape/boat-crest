package com.mappls.sdk.maps.location;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Size;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.location.o;
/* loaded from: classes11.dex */
public class r extends s {
    @Nullable
    public final MapplsMap.CancelableCallback n;

    /* loaded from: classes11.dex */
    public final class b extends AnimatorListenerAdapter {
        public b() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            if (r.this.n != null) {
                r.this.n.onCancel();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (r.this.n != null) {
                r.this.n.onFinish();
            }
        }
    }

    public r(@NonNull @Size(min = 2) Float[] fArr, o.b bVar, @Nullable MapplsMap.CancelableCallback cancelableCallback) {
        super(fArr, bVar, Integer.MAX_VALUE);
        this.n = cancelableCallback;
        addListener(new b());
    }
}
