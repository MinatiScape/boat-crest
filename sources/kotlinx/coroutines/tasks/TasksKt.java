package kotlinx.coroutines.tasks;

import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.RuntimeExecutionException;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlin.sequences.Sequence;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.ChildHandle;
import kotlinx.coroutines.ChildJob;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.selects.SelectClause0;
import kotlinx.coroutines.selects.SelectClause1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class TasksKt {

    /* loaded from: classes12.dex */
    public static final class a extends Lambda implements Function1<Throwable, Unit> {
        public final /* synthetic */ CancellationTokenSource $cancellationTokenSource;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(CancellationTokenSource cancellationTokenSource) {
            super(1);
            this.$cancellationTokenSource = cancellationTokenSource;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable Throwable th) {
            this.$cancellationTokenSource.cancel();
        }
    }

    /* loaded from: classes12.dex */
    public static final class b extends Lambda implements Function1<Throwable, Unit> {
        public final /* synthetic */ CancellationTokenSource $cancellation;
        public final /* synthetic */ TaskCompletionSource<T> $source;
        public final /* synthetic */ Deferred<T> $this_asTask;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public b(CancellationTokenSource cancellationTokenSource, Deferred<? extends T> deferred, TaskCompletionSource<T> taskCompletionSource) {
            super(1);
            this.$cancellation = cancellationTokenSource;
            this.$this_asTask = deferred;
            this.$source = taskCompletionSource;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable Throwable th) {
            if (th instanceof CancellationException) {
                this.$cancellation.cancel();
                return;
            }
            Throwable completionExceptionOrNull = this.$this_asTask.getCompletionExceptionOrNull();
            if (completionExceptionOrNull == null) {
                this.$source.setResult(this.$this_asTask.getCompleted());
                return;
            }
            TaskCompletionSource<T> taskCompletionSource = this.$source;
            Exception exc = completionExceptionOrNull instanceof Exception ? (Exception) completionExceptionOrNull : null;
            if (exc == null) {
                exc = new RuntimeExecutionException(completionExceptionOrNull);
            }
            taskCompletionSource.setException(exc);
        }
    }

    /* loaded from: classes12.dex */
    public static final class c<TResult> implements OnCompleteListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CancellableContinuation<T> f14203a;

        /* JADX WARN: Multi-variable type inference failed */
        public c(CancellableContinuation<? super T> cancellableContinuation) {
            this.f14203a = cancellableContinuation;
        }

        @Override // com.google.android.gms.tasks.OnCompleteListener
        public final void onComplete(@NotNull Task<T> task) {
            Exception exception = task.getException();
            if (exception == null) {
                if (task.isCanceled()) {
                    CancellableContinuation.DefaultImpls.cancel$default(this.f14203a, null, 1, null);
                    return;
                }
                Continuation continuation = this.f14203a;
                Object result = task.getResult();
                Result.Companion companion = Result.Companion;
                continuation.resumeWith(Result.m123constructorimpl(result));
                return;
            }
            Continuation continuation2 = this.f14203a;
            Result.Companion companion2 = Result.Companion;
            continuation2.resumeWith(Result.m123constructorimpl(ResultKt.createFailure(exception)));
        }
    }

    /* loaded from: classes12.dex */
    public static final class d extends Lambda implements Function1<Throwable, Unit> {
        public final /* synthetic */ CancellationTokenSource $cancellationTokenSource;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(CancellationTokenSource cancellationTokenSource) {
            super(1);
            this.$cancellationTokenSource = cancellationTokenSource;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable Throwable th) {
            this.$cancellationTokenSource.cancel();
        }
    }

    @NotNull
    public static final <T> Deferred<T> asDeferred(@NotNull Task<T> task) {
        return b(task, null);
    }

    @NotNull
    public static final <T> Task<T> asTask(@NotNull Deferred<? extends T> deferred) {
        CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource(cancellationTokenSource.getToken());
        deferred.invokeOnCompletion(new b(cancellationTokenSource, deferred, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    @Nullable
    public static final <T> Object await(@NotNull Task<T> task, @NotNull Continuation<? super T> continuation) {
        return d(task, null, continuation);
    }

    public static final <T> Deferred<T> b(Task<T> task, CancellationTokenSource cancellationTokenSource) {
        final CompletableDeferred CompletableDeferred$default = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        if (task.isComplete()) {
            Exception exception = task.getException();
            if (exception == null) {
                if (task.isCanceled()) {
                    Job.DefaultImpls.cancel$default((Job) CompletableDeferred$default, (CancellationException) null, 1, (Object) null);
                } else {
                    CompletableDeferred$default.complete(task.getResult());
                }
            } else {
                CompletableDeferred$default.completeExceptionally(exception);
            }
        } else {
            task.addOnCompleteListener(kotlinx.coroutines.tasks.a.h, new OnCompleteListener() { // from class: kotlinx.coroutines.tasks.b
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task2) {
                    TasksKt.c(CompletableDeferred.this, task2);
                }
            });
        }
        if (cancellationTokenSource != null) {
            CompletableDeferred$default.invokeOnCompletion(new a(cancellationTokenSource));
        }
        return new Deferred<T>(CompletableDeferred$default) { // from class: kotlinx.coroutines.tasks.TasksKt$asDeferredImpl$3
            public final /* synthetic */ CompletableDeferred<T> h;
            public final /* synthetic */ CompletableDeferred<T> i;

            {
                this.i = CompletableDeferred$default;
                this.h = CompletableDeferred$default;
            }

            @Override // kotlinx.coroutines.Job
            @InternalCoroutinesApi
            @NotNull
            public ChildHandle attachChild(@NotNull ChildJob childJob) {
                return this.h.attachChild(childJob);
            }

            @Override // kotlinx.coroutines.Deferred
            @Nullable
            public Object await(@NotNull Continuation<? super T> continuation) {
                return this.h.await(continuation);
            }

            @Override // kotlinx.coroutines.Job
            @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
            public /* synthetic */ void cancel() {
                this.h.cancel();
            }

            @Override // kotlinx.coroutines.Job
            public void cancel(@Nullable CancellationException cancellationException) {
                this.h.cancel(cancellationException);
            }

            @Override // kotlinx.coroutines.Job
            @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
            public /* synthetic */ boolean cancel(Throwable th) {
                return this.h.cancel(th);
            }

            @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
            public <R> R fold(R r, @NotNull Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
                return (R) this.h.fold(r, function2);
            }

            @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
            @Nullable
            public <E extends CoroutineContext.Element> E get(@NotNull CoroutineContext.Key<E> key) {
                return (E) this.h.get(key);
            }

            @Override // kotlinx.coroutines.Job
            @InternalCoroutinesApi
            @NotNull
            public CancellationException getCancellationException() {
                return this.h.getCancellationException();
            }

            @Override // kotlinx.coroutines.Job
            @NotNull
            public Sequence<Job> getChildren() {
                return this.h.getChildren();
            }

            @Override // kotlinx.coroutines.Deferred
            @ExperimentalCoroutinesApi
            public T getCompleted() {
                return this.h.getCompleted();
            }

            @Override // kotlinx.coroutines.Deferred
            @ExperimentalCoroutinesApi
            @Nullable
            public Throwable getCompletionExceptionOrNull() {
                return this.h.getCompletionExceptionOrNull();
            }

            @Override // kotlin.coroutines.CoroutineContext.Element
            @NotNull
            public CoroutineContext.Key<?> getKey() {
                return this.h.getKey();
            }

            @Override // kotlinx.coroutines.Deferred
            @NotNull
            public SelectClause1<T> getOnAwait() {
                return this.h.getOnAwait();
            }

            @Override // kotlinx.coroutines.Job
            @NotNull
            public SelectClause0 getOnJoin() {
                return this.h.getOnJoin();
            }

            @Override // kotlinx.coroutines.Job
            @NotNull
            public DisposableHandle invokeOnCompletion(@NotNull Function1<? super Throwable, Unit> function1) {
                return this.h.invokeOnCompletion(function1);
            }

            @Override // kotlinx.coroutines.Job
            @InternalCoroutinesApi
            @NotNull
            public DisposableHandle invokeOnCompletion(boolean z, boolean z2, @NotNull Function1<? super Throwable, Unit> function1) {
                return this.h.invokeOnCompletion(z, z2, function1);
            }

            @Override // kotlinx.coroutines.Job
            public boolean isActive() {
                return this.h.isActive();
            }

            @Override // kotlinx.coroutines.Job
            public boolean isCancelled() {
                return this.h.isCancelled();
            }

            @Override // kotlinx.coroutines.Job
            public boolean isCompleted() {
                return this.h.isCompleted();
            }

            @Override // kotlinx.coroutines.Job
            @Nullable
            public Object join(@NotNull Continuation<? super Unit> continuation) {
                return this.h.join(continuation);
            }

            @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
            @NotNull
            public CoroutineContext minusKey(@NotNull CoroutineContext.Key<?> key) {
                return this.h.minusKey(key);
            }

            @Override // kotlin.coroutines.CoroutineContext
            @NotNull
            public CoroutineContext plus(@NotNull CoroutineContext coroutineContext) {
                return this.h.plus(coroutineContext);
            }

            @Override // kotlinx.coroutines.Job
            @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
            @NotNull
            public Job plus(@NotNull Job job) {
                return this.h.plus(job);
            }

            @Override // kotlinx.coroutines.Job
            public boolean start() {
                return this.h.start();
            }
        };
    }

    public static final void c(CompletableDeferred completableDeferred, Task task) {
        Exception exception = task.getException();
        if (exception == null) {
            if (task.isCanceled()) {
                Job.DefaultImpls.cancel$default((Job) completableDeferred, (CancellationException) null, 1, (Object) null);
                return;
            } else {
                completableDeferred.complete(task.getResult());
                return;
            }
        }
        completableDeferred.completeExceptionally(exception);
    }

    public static final <T> Object d(Task<T> task, CancellationTokenSource cancellationTokenSource, Continuation<? super T> continuation) {
        if (task.isComplete()) {
            Exception exception = task.getException();
            if (exception == null) {
                if (!task.isCanceled()) {
                    return task.getResult();
                }
                throw new CancellationException("Task " + task + " was cancelled normally.");
            }
            throw exception;
        }
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        task.addOnCompleteListener(kotlinx.coroutines.tasks.a.h, new c(cancellableContinuationImpl));
        if (cancellationTokenSource != null) {
            cancellableContinuationImpl.invokeOnCancellation(new d(cancellationTokenSource));
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    @ExperimentalCoroutinesApi
    @NotNull
    public static final <T> Deferred<T> asDeferred(@NotNull Task<T> task, @NotNull CancellationTokenSource cancellationTokenSource) {
        return b(task, cancellationTokenSource);
    }

    @ExperimentalCoroutinesApi
    @Nullable
    public static final <T> Object await(@NotNull Task<T> task, @NotNull CancellationTokenSource cancellationTokenSource, @NotNull Continuation<? super T> continuation) {
        return d(task, cancellationTokenSource, continuation);
    }
}
