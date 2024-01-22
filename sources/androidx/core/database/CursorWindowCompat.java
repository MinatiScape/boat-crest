package androidx.core.database;

import android.database.CursorWindow;
import android.os.Build;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
/* loaded from: classes.dex */
public final class CursorWindowCompat {

    @RequiresApi(15)
    /* loaded from: classes.dex */
    public static class a {
        @DoNotInline
        public static CursorWindow a(String str) {
            return new CursorWindow(str);
        }
    }

    @RequiresApi(28)
    /* loaded from: classes.dex */
    public static class b {
        @DoNotInline
        public static CursorWindow a(String str, long j) {
            return new CursorWindow(str, j);
        }
    }

    @NonNull
    public static CursorWindow create(@Nullable String str, long j) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            return b.a(str, j);
        }
        if (i >= 15) {
            return a.a(str);
        }
        return new CursorWindow(false);
    }
}
