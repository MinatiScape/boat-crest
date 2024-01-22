package okhttp3.logging;

import java.io.EOFException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.h;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class Utf8Kt {
    public static final boolean isProbablyUtf8(@NotNull Buffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        try {
            Buffer buffer2 = new Buffer();
            buffer.copyTo(buffer2, 0L, h.coerceAtMost(buffer.size(), 64L));
            int i = 0;
            while (i < 16) {
                i++;
                if (buffer2.exhausted()) {
                    return true;
                }
                int readUtf8CodePoint = buffer2.readUtf8CodePoint();
                if (Character.isISOControl(readUtf8CodePoint) && !Character.isWhitespace(readUtf8CodePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }
}
