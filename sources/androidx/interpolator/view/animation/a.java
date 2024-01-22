package androidx.interpolator.view.animation;

import android.view.animation.Interpolator;
/* loaded from: classes.dex */
public abstract class a implements Interpolator {

    /* renamed from: a  reason: collision with root package name */
    public final float[] f1344a;
    public final float b;

    public a(float[] fArr) {
        this.f1344a = fArr;
        this.b = 1.0f / (fArr.length - 1);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        if (f >= 1.0f) {
            return 1.0f;
        }
        if (f <= 0.0f) {
            return 0.0f;
        }
        float[] fArr = this.f1344a;
        int min = Math.min((int) ((fArr.length - 1) * f), fArr.length - 2);
        float f2 = this.b;
        float f3 = (f - (min * f2)) / f2;
        float[] fArr2 = this.f1344a;
        return fArr2[min] + (f3 * (fArr2[min + 1] - fArr2[min]));
    }
}
