package kotlin.jdk7;

import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.a;
import kotlin.jvm.JvmName;
import org.jetbrains.annotations.Nullable;
@JvmName(name = "AutoCloseableKt")
/* loaded from: classes12.dex */
public final class AutoCloseableKt {
    @SinceKotlin(version = "1.2")
    @PublishedApi
    public static final void closeFinally(@Nullable AutoCloseable autoCloseable, @Nullable Throwable th) {
        if (autoCloseable != null) {
            if (th == null) {
                autoCloseable.close();
                return;
            }
            try {
                autoCloseable.close();
            } catch (Throwable th2) {
                a.addSuppressed(th, th2);
            }
        }
    }
}
