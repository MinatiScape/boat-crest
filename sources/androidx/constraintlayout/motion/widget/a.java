package androidx.constraintlayout.motion.widget;

import android.graphics.RectF;
import android.view.View;
import java.util.HashSet;
/* loaded from: classes.dex */
public abstract class a extends Key {
    public static final float SELECTION_SLOPE = 20.0f;
    public int e = Key.UNSET;

    @Override // androidx.constraintlayout.motion.widget.Key
    public void getAttributeNames(HashSet<String> hashSet) {
    }

    public abstract boolean intersects(int i, int i2, RectF rectF, RectF rectF2, float f, float f2);

    public abstract void positionAttributes(View view, RectF rectF, RectF rectF2, float f, float f2, String[] strArr, float[] fArr);
}
