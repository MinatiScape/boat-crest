package kotlinx.coroutines.flow.internal;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class SafeCollectorKt {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final Function3<FlowCollector<Object>, Object, Continuation<? super Unit>, Object> f14161a = (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(a.INSTANCE, 3);

    /* loaded from: classes12.dex */
    public /* synthetic */ class a extends FunctionReferenceImpl implements Function3<FlowCollector<? super Object>, Object, Unit>, SuspendFunction {
        public static final a INSTANCE = new a();

        public a() {
            super(3, FlowCollector.class, "emit", "emit(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0);
        }

        @Override // kotlin.jvm.functions.Function3
        @Nullable
        public final Object invoke(@NotNull FlowCollector<Object> flowCollector, @Nullable Object obj, @NotNull Continuation<? super Unit> continuation) {
            return flowCollector.emit(obj, continuation);
        }
    }

    public static final /* synthetic */ Function3 access$getEmitFun$p() {
        return f14161a;
    }
}
