package kotlin.io;

import java.io.File;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes12.dex */
public final class ExceptionsKt {
    public static final String a(File file, File file2, String str) {
        StringBuilder sb = new StringBuilder(file.toString());
        if (file2 != null) {
            sb.append(" -> " + file2);
        }
        if (str != null) {
            sb.append(": " + str);
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "sb.toString()");
        return sb2;
    }
}
