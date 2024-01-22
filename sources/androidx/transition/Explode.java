package androidx.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import androidx.annotation.NonNull;
/* loaded from: classes.dex */
public class Explode extends Visibility {
    public static final TimeInterpolator T = new DecelerateInterpolator();
    public static final TimeInterpolator U = new AccelerateInterpolator();
    public int[] S;

    public Explode() {
        this.S = new int[2];
        setPropagation(new CircularPropagation());
    }

    private void C(TransitionValues transitionValues) {
        View view = transitionValues.view;
        view.getLocationOnScreen(this.S);
        int[] iArr = this.S;
        int i = iArr[0];
        int i2 = iArr[1];
        transitionValues.values.put("android:explode:screenBounds", new Rect(i, i2, view.getWidth() + i, view.getHeight() + i2));
    }

    public static float E(float f, float f2) {
        return (float) Math.sqrt((f * f) + (f2 * f2));
    }

    public static float F(View view, int i, int i2) {
        return E(Math.max(i, view.getWidth() - i), Math.max(i2, view.getHeight() - i2));
    }

    public final void G(View view, Rect rect, int[] iArr) {
        int centerY;
        int i;
        view.getLocationOnScreen(this.S);
        int[] iArr2 = this.S;
        int i2 = iArr2[0];
        int i3 = iArr2[1];
        Rect epicenter = getEpicenter();
        if (epicenter == null) {
            i = (view.getWidth() / 2) + i2 + Math.round(view.getTranslationX());
            centerY = (view.getHeight() / 2) + i3 + Math.round(view.getTranslationY());
        } else {
            int centerX = epicenter.centerX();
            centerY = epicenter.centerY();
            i = centerX;
        }
        float centerX2 = rect.centerX() - i;
        float centerY2 = rect.centerY() - centerY;
        if (centerX2 == 0.0f && centerY2 == 0.0f) {
            centerX2 = ((float) (Math.random() * 2.0d)) - 1.0f;
            centerY2 = ((float) (Math.random() * 2.0d)) - 1.0f;
        }
        float E = E(centerX2, centerY2);
        float F = F(view, i - i2, centerY - i3);
        iArr[0] = Math.round((centerX2 / E) * F);
        iArr[1] = Math.round(F * (centerY2 / E));
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        super.captureEndValues(transitionValues);
        C(transitionValues);
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        super.captureStartValues(transitionValues);
        C(transitionValues);
    }

    @Override // androidx.transition.Visibility
    public Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues2 == null) {
            return null;
        }
        Rect rect = (Rect) transitionValues2.values.get("android:explode:screenBounds");
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        G(viewGroup, rect, this.S);
        int[] iArr = this.S;
        return s.a(view, transitionValues2, rect.left, rect.top, translationX + iArr[0], translationY + iArr[1], translationX, translationY, T, this);
    }

    @Override // androidx.transition.Visibility
    public Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        float f;
        float f2;
        if (transitionValues == null) {
            return null;
        }
        Rect rect = (Rect) transitionValues.values.get("android:explode:screenBounds");
        int i = rect.left;
        int i2 = rect.top;
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        int[] iArr = (int[]) transitionValues.view.getTag(R.id.transition_position);
        if (iArr != null) {
            f = (iArr[0] - rect.left) + translationX;
            f2 = (iArr[1] - rect.top) + translationY;
            rect.offsetTo(iArr[0], iArr[1]);
        } else {
            f = translationX;
            f2 = translationY;
        }
        G(viewGroup, rect, this.S);
        int[] iArr2 = this.S;
        return s.a(view, transitionValues, i, i2, translationX, translationY, f + iArr2[0], f2 + iArr2[1], U, this);
    }

    public Explode(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.S = new int[2];
        setPropagation(new CircularPropagation());
    }
}
