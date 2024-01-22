package kotlin.coroutines;

import kotlin.SinceKotlin;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@SinceKotlin(version = "1.3")
/* loaded from: classes12.dex */
public interface CoroutineContext {

    /* loaded from: classes12.dex */
    public static final class DefaultImpls {

        /* loaded from: classes12.dex */
        public static final class a extends Lambda implements Function2<CoroutineContext, Element, CoroutineContext> {
            public static final a INSTANCE = new a();

            public a() {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            @NotNull
            public final CoroutineContext invoke(@NotNull CoroutineContext acc, @NotNull Element element) {
                CombinedContext combinedContext;
                Intrinsics.checkNotNullParameter(acc, "acc");
                Intrinsics.checkNotNullParameter(element, "element");
                CoroutineContext minusKey = acc.minusKey(element.getKey());
                EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
                if (minusKey == emptyCoroutineContext) {
                    return element;
                }
                ContinuationInterceptor.Key key = ContinuationInterceptor.Key;
                ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) minusKey.get(key);
                if (continuationInterceptor == null) {
                    combinedContext = new CombinedContext(minusKey, element);
                } else {
                    CoroutineContext minusKey2 = minusKey.minusKey(key);
                    if (minusKey2 == emptyCoroutineContext) {
                        return new CombinedContext(element, continuationInterceptor);
                    }
                    combinedContext = new CombinedContext(new CombinedContext(minusKey2, element), continuationInterceptor);
                }
                return combinedContext;
            }
        }

        @NotNull
        public static CoroutineContext plus(@NotNull CoroutineContext coroutineContext, @NotNull CoroutineContext context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return context == EmptyCoroutineContext.INSTANCE ? coroutineContext : (CoroutineContext) context.fold(coroutineContext, a.INSTANCE);
        }
    }

    /* loaded from: classes12.dex */
    public interface Element extends CoroutineContext {

        /* loaded from: classes12.dex */
        public static final class DefaultImpls {
            public static <R> R fold(@NotNull Element element, R r, @NotNull Function2<? super R, ? super Element, ? extends R> operation) {
                Intrinsics.checkNotNullParameter(operation, "operation");
                return operation.invoke(r, element);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Nullable
            public static <E extends Element> E get(@NotNull Element element, @NotNull Key<E> key) {
                Intrinsics.checkNotNullParameter(key, "key");
                if (Intrinsics.areEqual(element.getKey(), key)) {
                    Intrinsics.checkNotNull(element, "null cannot be cast to non-null type E of kotlin.coroutines.CoroutineContext.Element.get");
                    return element;
                }
                return null;
            }

            @NotNull
            public static CoroutineContext minusKey(@NotNull Element element, @NotNull Key<?> key) {
                Intrinsics.checkNotNullParameter(key, "key");
                return Intrinsics.areEqual(element.getKey(), key) ? EmptyCoroutineContext.INSTANCE : element;
            }

            @NotNull
            public static CoroutineContext plus(@NotNull Element element, @NotNull CoroutineContext context) {
                Intrinsics.checkNotNullParameter(context, "context");
                return DefaultImpls.plus(element, context);
            }
        }

        @Override // kotlin.coroutines.CoroutineContext
        <R> R fold(R r, @NotNull Function2<? super R, ? super Element, ? extends R> function2);

        @Override // kotlin.coroutines.CoroutineContext
        @Nullable
        <E extends Element> E get(@NotNull Key<E> key);

        @NotNull
        Key<?> getKey();

        @Override // kotlin.coroutines.CoroutineContext
        @NotNull
        CoroutineContext minusKey(@NotNull Key<?> key);
    }

    /* loaded from: classes12.dex */
    public interface Key<E extends Element> {
    }

    <R> R fold(R r, @NotNull Function2<? super R, ? super Element, ? extends R> function2);

    @Nullable
    <E extends Element> E get(@NotNull Key<E> key);

    @NotNull
    CoroutineContext minusKey(@NotNull Key<?> key);

    @NotNull
    CoroutineContext plus(@NotNull CoroutineContext coroutineContext);
}
