package androidx.core.graphics;

import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Build;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.graphics.a;
/* loaded from: classes.dex */
public class BlendModeColorFilterCompat {

    @RequiresApi(29)
    /* loaded from: classes.dex */
    public static class a {
        @DoNotInline
        public static ColorFilter a(int i, Object obj) {
            return new BlendModeColorFilter(i, (BlendMode) obj);
        }
    }

    @Nullable
    public static ColorFilter createBlendModeColorFilterCompat(int i, @NonNull BlendModeCompat blendModeCompat) {
        if (Build.VERSION.SDK_INT >= 29) {
            Object a2 = a.b.a(blendModeCompat);
            if (a2 != null) {
                return a.a(i, a2);
            }
            return null;
        }
        PorterDuff.Mode a3 = androidx.core.graphics.a.a(blendModeCompat);
        if (a3 != null) {
            return new PorterDuffColorFilter(i, a3);
        }
        return null;
    }
}
