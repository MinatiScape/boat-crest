package androidx.lifecycle;

import androidx.annotation.MainThread;
import androidx.lifecycle.Lifecycle;
import java.util.concurrent.CancellationException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
@MainThread
/* loaded from: classes.dex */
public final class LifecycleController {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Lifecycle f1354a;
    @NotNull
    public final Lifecycle.State b;
    @NotNull
    public final DispatchQueue c;
    @NotNull
    public final LifecycleEventObserver d;

    public LifecycleController(@NotNull Lifecycle lifecycle, @NotNull Lifecycle.State minState, @NotNull DispatchQueue dispatchQueue, @NotNull final Job parentJob) {
        Intrinsics.checkNotNullParameter(lifecycle, "lifecycle");
        Intrinsics.checkNotNullParameter(minState, "minState");
        Intrinsics.checkNotNullParameter(dispatchQueue, "dispatchQueue");
        Intrinsics.checkNotNullParameter(parentJob, "parentJob");
        this.f1354a = lifecycle;
        this.b = minState;
        this.c = dispatchQueue;
        LifecycleEventObserver lifecycleEventObserver = new LifecycleEventObserver() { // from class: androidx.lifecycle.e
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                LifecycleController.b(LifecycleController.this, parentJob, lifecycleOwner, event);
            }
        };
        this.d = lifecycleEventObserver;
        if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
            Job.DefaultImpls.cancel$default(parentJob, (CancellationException) null, 1, (Object) null);
            finish();
            return;
        }
        lifecycle.addObserver(lifecycleEventObserver);
    }

    public static final void b(LifecycleController this$0, Job parentJob, LifecycleOwner source, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(parentJob, "$parentJob");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(event, "<anonymous parameter 1>");
        if (source.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
            Job.DefaultImpls.cancel$default(parentJob, (CancellationException) null, 1, (Object) null);
            this$0.finish();
        } else if (source.getLifecycle().getCurrentState().compareTo(this$0.b) < 0) {
            this$0.c.pause();
        } else {
            this$0.c.resume();
        }
    }

    @MainThread
    public final void finish() {
        this.f1354a.removeObserver(this.d);
        this.c.finish();
    }
}
