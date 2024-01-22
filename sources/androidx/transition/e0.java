package androidx.transition;

import android.annotation.SuppressLint;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
@RequiresApi(22)
/* loaded from: classes.dex */
public class e0 extends d0 {
    public static boolean j = true;

    @Override // androidx.transition.h0
    @SuppressLint({"NewApi"})
    public void f(@NonNull View view, int i, int i2, int i3, int i4) {
        if (j) {
            try {
                view.setLeftTopRightBottom(i, i2, i3, i4);
            } catch (NoSuchMethodError unused) {
                j = false;
            }
        }
    }
}
