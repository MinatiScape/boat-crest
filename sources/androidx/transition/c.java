package androidx.transition;

import android.animation.TypeEvaluator;
/* loaded from: classes.dex */
public class c implements TypeEvaluator<float[]> {

    /* renamed from: a  reason: collision with root package name */
    public float[] f1710a;

    public c(float[] fArr) {
        this.f1710a = fArr;
    }

    @Override // android.animation.TypeEvaluator
    /* renamed from: a */
    public float[] evaluate(float f, float[] fArr, float[] fArr2) {
        float[] fArr3 = this.f1710a;
        if (fArr3 == null) {
            fArr3 = new float[fArr.length];
        }
        for (int i = 0; i < fArr3.length; i++) {
            float f2 = fArr[i];
            fArr3[i] = f2 + ((fArr2[i] - f2) * f);
        }
        return fArr3;
    }
}
