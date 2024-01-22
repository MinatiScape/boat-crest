package kotlinx.coroutines.internal;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final /* synthetic */ class c {

    /* renamed from: a  reason: collision with root package name */
    public static final int f14189a = Runtime.getRuntime().availableProcessors();

    public static final int a() {
        return f14189a;
    }

    @Nullable
    public static final String b(@NotNull String str) {
        try {
            return System.getProperty(str);
        } catch (SecurityException unused) {
            return null;
        }
    }
}
