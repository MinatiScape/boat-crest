package androidx.core.graphics;

import android.graphics.BlendMode;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Build;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.graphics.a;
import androidx.core.util.Pair;
/* loaded from: classes.dex */
public final class PaintCompat {

    /* renamed from: a  reason: collision with root package name */
    public static final ThreadLocal<Pair<Rect, Rect>> f1040a = new ThreadLocal<>();

    @RequiresApi(23)
    /* loaded from: classes.dex */
    public static class a {
        @DoNotInline
        public static boolean a(Paint paint, String str) {
            return paint.hasGlyph(str);
        }
    }

    @RequiresApi(29)
    /* loaded from: classes.dex */
    public static class b {
        @DoNotInline
        public static void a(Paint paint, Object obj) {
            paint.setBlendMode((BlendMode) obj);
        }
    }

    public static Pair<Rect, Rect> a() {
        ThreadLocal<Pair<Rect, Rect>> threadLocal = f1040a;
        Pair<Rect, Rect> pair = threadLocal.get();
        if (pair == null) {
            Pair<Rect, Rect> pair2 = new Pair<>(new Rect(), new Rect());
            threadLocal.set(pair2);
            return pair2;
        }
        pair.first.setEmpty();
        pair.second.setEmpty();
        return pair;
    }

    public static boolean hasGlyph(@NonNull Paint paint, @NonNull String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            return a.a(paint, str);
        }
        int length = str.length();
        if (length == 1 && Character.isWhitespace(str.charAt(0))) {
            return true;
        }
        float measureText = paint.measureText("\udfffd");
        float measureText2 = paint.measureText("m");
        float measureText3 = paint.measureText(str);
        float f = 0.0f;
        if (measureText3 == 0.0f) {
            return false;
        }
        if (str.codePointCount(0, str.length()) > 1) {
            if (measureText3 > measureText2 * 2.0f) {
                return false;
            }
            int i = 0;
            while (i < length) {
                int charCount = Character.charCount(str.codePointAt(i)) + i;
                f += paint.measureText(str, i, charCount);
                i = charCount;
            }
            if (measureText3 >= f) {
                return false;
            }
        }
        if (measureText3 != measureText) {
            return true;
        }
        Pair<Rect, Rect> a2 = a();
        paint.getTextBounds("\udfffd", 0, 2, a2.first);
        paint.getTextBounds(str, 0, length, a2.second);
        return !a2.first.equals(a2.second);
    }

    public static boolean setBlendMode(@NonNull Paint paint, @Nullable BlendModeCompat blendModeCompat) {
        if (Build.VERSION.SDK_INT >= 29) {
            b.a(paint, blendModeCompat != null ? a.b.a(blendModeCompat) : null);
            return true;
        } else if (blendModeCompat != null) {
            PorterDuff.Mode a2 = androidx.core.graphics.a.a(blendModeCompat);
            paint.setXfermode(a2 != null ? new PorterDuffXfermode(a2) : null);
            return a2 != null;
        } else {
            paint.setXfermode(null);
            return true;
        }
    }
}
