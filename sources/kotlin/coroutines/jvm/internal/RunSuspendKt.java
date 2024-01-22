package kotlin.coroutines.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class RunSuspendKt {
    @SinceKotlin(version = "1.3")
    public static final void runSuspend(@NotNull Function1<? super Continuation<? super Unit>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        b bVar = new b();
        ContinuationKt.startCoroutine(block, bVar);
        bVar.a();
    }
}
