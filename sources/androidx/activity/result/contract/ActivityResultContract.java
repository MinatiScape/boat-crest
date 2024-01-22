package androidx.activity.result.contract;

import android.content.Context;
import android.content.Intent;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public abstract class ActivityResultContract<I, O> {

    /* loaded from: classes.dex */
    public static final class SynchronousResult<T> {

        /* renamed from: a  reason: collision with root package name */
        public final T f363a;

        public SynchronousResult(T t) {
            this.f363a = t;
        }

        public final T getValue() {
            return this.f363a;
        }
    }

    @NotNull
    public abstract Intent createIntent(@NotNull Context context, I i);

    @Nullable
    public SynchronousResult<O> getSynchronousResult(@NotNull Context context, I i) {
        Intrinsics.checkNotNullParameter(context, "context");
        return null;
    }

    public abstract O parseResult(int i, @Nullable Intent intent);
}
