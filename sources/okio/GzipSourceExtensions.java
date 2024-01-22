package okio;

import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@JvmName(name = "-GzipSourceExtensions")
/* renamed from: okio.-GzipSourceExtensions  reason: invalid class name */
/* loaded from: classes12.dex */
public final class GzipSourceExtensions {
    @NotNull
    public static final GzipSource gzip(@NotNull Source source) {
        Intrinsics.checkNotNullParameter(source, "<this>");
        return new GzipSource(source);
    }
}
