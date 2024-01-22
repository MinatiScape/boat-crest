package kotlin.concurrent;

import java.util.Timer;
import kotlin.PublishedApi;
import kotlin.jvm.JvmName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@JvmName(name = "TimersKt")
/* loaded from: classes12.dex */
public final class TimersKt {
    @PublishedApi
    @NotNull
    public static final Timer timer(@Nullable String str, boolean z) {
        return str == null ? new Timer(z) : new Timer(str, z);
    }
}
