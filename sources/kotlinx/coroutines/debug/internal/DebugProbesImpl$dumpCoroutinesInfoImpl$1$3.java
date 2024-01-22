package kotlinx.coroutines.debug.internal;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.debug.internal.DebugProbesImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class DebugProbesImpl$dumpCoroutinesInfoImpl$1$3 extends Lambda implements Function1<DebugProbesImpl.a<?>, Object> {
    public final /* synthetic */ Function2<DebugProbesImpl.a<?>, CoroutineContext, Object> $create;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DebugProbesImpl$dumpCoroutinesInfoImpl$1$3(Function2<? super DebugProbesImpl.a<?>, ? super CoroutineContext, Object> function2) {
        super(1);
        this.$create = function2;
    }

    @Override // kotlin.jvm.functions.Function1
    @Nullable
    public final Object invoke(@NotNull DebugProbesImpl.a<?> aVar) {
        CoroutineContext context;
        if (DebugProbesImpl.INSTANCE.j(aVar) || (context = aVar.i.getContext()) == null) {
            return null;
        }
        return this.$create.invoke(aVar, context);
    }
}
