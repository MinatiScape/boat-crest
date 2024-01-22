package kotlinx.coroutines.flow;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final /* synthetic */ class k {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final Function1<Object, Object> f14162a = b.INSTANCE;
    @NotNull
    public static final Function2<Object, Object, Boolean> b = a.INSTANCE;

    /* loaded from: classes12.dex */
    public static final class a extends Lambda implements Function2<Object, Object, Boolean> {
        public static final a INSTANCE = new a();

        public a() {
            super(2);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function2
        @NotNull
        public final Boolean invoke(@Nullable Object obj, @Nullable Object obj2) {
            return Boolean.valueOf(Intrinsics.areEqual(obj, obj2));
        }
    }

    /* loaded from: classes12.dex */
    public static final class b extends Lambda implements Function1<Object, Object> {
        public static final b INSTANCE = new b();

        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        @Nullable
        public final Object invoke(@Nullable Object obj) {
            return obj;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <T> Flow<T> a(@NotNull Flow<? extends T> flow) {
        return flow instanceof StateFlow ? flow : d(flow, f14162a, b);
    }

    @NotNull
    public static final <T> Flow<T> b(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super T, Boolean> function2) {
        return d(flow, f14162a, (Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2));
    }

    @NotNull
    public static final <T, K> Flow<T> c(@NotNull Flow<? extends T> flow, @NotNull Function1<? super T, ? extends K> function1) {
        return d(flow, function1, b);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> Flow<T> d(Flow<? extends T> flow, Function1<? super T, ? extends Object> function1, Function2<Object, Object, Boolean> function2) {
        if (flow instanceof e) {
            e eVar = (e) flow;
            if (eVar.i == function1 && eVar.j == function2) {
                return flow;
            }
        }
        return new e(flow, function1, function2);
    }
}
