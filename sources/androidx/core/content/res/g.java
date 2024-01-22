package androidx.core.content.res;

import android.content.res.TypedArray;
import android.graphics.Typeface;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.annotation.StyleableRes;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@RequiresApi(26)
/* loaded from: classes.dex */
public final class g {
    static {
        new g();
    }

    @JvmStatic
    @DoNotInline
    @NotNull
    public static final Typeface a(@NotNull TypedArray typedArray, @StyleableRes int i) {
        Intrinsics.checkNotNullParameter(typedArray, "typedArray");
        Typeface font = typedArray.getFont(i);
        Intrinsics.checkNotNull(font);
        return font;
    }
}
