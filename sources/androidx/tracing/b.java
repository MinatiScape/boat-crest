package androidx.tracing;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
@RequiresApi(29)
/* loaded from: classes.dex */
public final class b {
    public static void a(@NonNull String str, int i) {
        android.os.Trace.beginAsyncSection(str, i);
    }

    public static void b(@NonNull String str, int i) {
        android.os.Trace.endAsyncSection(str, i);
    }

    public static void c(@NonNull String str, int i) {
        android.os.Trace.setCounter(str, i);
    }
}
