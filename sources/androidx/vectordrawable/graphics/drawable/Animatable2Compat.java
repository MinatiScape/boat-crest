package androidx.vectordrawable.graphics.drawable;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
/* loaded from: classes.dex */
public interface Animatable2Compat extends Animatable {

    /* loaded from: classes.dex */
    public static abstract class AnimationCallback {

        /* renamed from: a  reason: collision with root package name */
        public Animatable2.AnimationCallback f1727a;

        /* loaded from: classes.dex */
        public class a extends Animatable2.AnimationCallback {
            public a() {
            }

            @Override // android.graphics.drawable.Animatable2.AnimationCallback
            public void onAnimationEnd(Drawable drawable) {
                AnimationCallback.this.onAnimationEnd(drawable);
            }

            @Override // android.graphics.drawable.Animatable2.AnimationCallback
            public void onAnimationStart(Drawable drawable) {
                AnimationCallback.this.onAnimationStart(drawable);
            }
        }

        @RequiresApi(23)
        public Animatable2.AnimationCallback a() {
            if (this.f1727a == null) {
                this.f1727a = new a();
            }
            return this.f1727a;
        }

        public void onAnimationEnd(Drawable drawable) {
        }

        public void onAnimationStart(Drawable drawable) {
        }
    }

    void clearAnimationCallbacks();

    void registerAnimationCallback(@NonNull AnimationCallback animationCallback);

    boolean unregisterAnimationCallback(@NonNull AnimationCallback animationCallback);
}
