package okio;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Deprecated(message = "changed in Okio 2.x")
/* renamed from: okio.-DeprecatedUtf8  reason: invalid class name */
/* loaded from: classes12.dex */
public final class DeprecatedUtf8 {
    @NotNull
    public static final DeprecatedUtf8 INSTANCE = new DeprecatedUtf8();

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "string.utf8Size()", imports = {"okio.utf8Size"}))
    public final long size(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        return Utf8.size$default(string, 0, 0, 3, null);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "string.utf8Size(beginIndex, endIndex)", imports = {"okio.utf8Size"}))
    public final long size(@NotNull String string, int i, int i2) {
        Intrinsics.checkNotNullParameter(string, "string");
        return Utf8.size(string, i, i2);
    }
}
