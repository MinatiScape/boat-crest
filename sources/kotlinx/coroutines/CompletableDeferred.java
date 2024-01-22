package kotlinx.coroutines;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.Deferred;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public interface CompletableDeferred<T> extends Deferred<T> {

    /* loaded from: classes12.dex */
    public static final class DefaultImpls {
        public static <T, R> R fold(@NotNull CompletableDeferred<T> completableDeferred, R r, @NotNull Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
            return (R) Deferred.DefaultImpls.fold(completableDeferred, r, function2);
        }

        @Nullable
        public static <T, E extends CoroutineContext.Element> E get(@NotNull CompletableDeferred<T> completableDeferred, @NotNull CoroutineContext.Key<E> key) {
            return (E) Deferred.DefaultImpls.get(completableDeferred, key);
        }

        @NotNull
        public static <T> CoroutineContext minusKey(@NotNull CompletableDeferred<T> completableDeferred, @NotNull CoroutineContext.Key<?> key) {
            return Deferred.DefaultImpls.minusKey(completableDeferred, key);
        }

        @NotNull
        public static <T> CoroutineContext plus(@NotNull CompletableDeferred<T> completableDeferred, @NotNull CoroutineContext coroutineContext) {
            return Deferred.DefaultImpls.plus(completableDeferred, coroutineContext);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
        @NotNull
        public static <T> Job plus(@NotNull CompletableDeferred<T> completableDeferred, @NotNull Job job) {
            return Deferred.DefaultImpls.plus((Deferred) completableDeferred, job);
        }
    }

    boolean complete(T t);

    boolean completeExceptionally(@NotNull Throwable th);
}
