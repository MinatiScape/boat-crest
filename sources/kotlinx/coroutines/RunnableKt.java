package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class RunnableKt {
    @NotNull
    public static final Runnable Runnable(@NotNull final Function0<Unit> function0) {
        return new Runnable() { // from class: kotlinx.coroutines.RunnableKt$Runnable$1
            @Override // java.lang.Runnable
            public final void run() {
                function0.invoke();
            }
        };
    }
}
