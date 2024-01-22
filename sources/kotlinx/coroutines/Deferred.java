package kotlinx.coroutines;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.selects.SelectClause1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public interface Deferred<T> extends Job {

    /* loaded from: classes12.dex */
    public static final class DefaultImpls {
        public static <T, R> R fold(@NotNull Deferred<? extends T> deferred, R r, @NotNull Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
            return (R) Job.DefaultImpls.fold(deferred, r, function2);
        }

        @Nullable
        public static <T, E extends CoroutineContext.Element> E get(@NotNull Deferred<? extends T> deferred, @NotNull CoroutineContext.Key<E> key) {
            return (E) Job.DefaultImpls.get(deferred, key);
        }

        @NotNull
        public static <T> CoroutineContext minusKey(@NotNull Deferred<? extends T> deferred, @NotNull CoroutineContext.Key<?> key) {
            return Job.DefaultImpls.minusKey(deferred, key);
        }

        @NotNull
        public static <T> CoroutineContext plus(@NotNull Deferred<? extends T> deferred, @NotNull CoroutineContext coroutineContext) {
            return Job.DefaultImpls.plus(deferred, coroutineContext);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
        @NotNull
        public static <T> Job plus(@NotNull Deferred<? extends T> deferred, @NotNull Job job) {
            return Job.DefaultImpls.plus((Job) deferred, job);
        }
    }

    @Nullable
    Object await(@NotNull Continuation<? super T> continuation);

    @ExperimentalCoroutinesApi
    T getCompleted();

    @ExperimentalCoroutinesApi
    @Nullable
    Throwable getCompletionExceptionOrNull();

    @NotNull
    SelectClause1<T> getOnAwait();
}
