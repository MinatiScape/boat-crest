package androidx.work;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.lifecycle.LiveData;
import com.google.common.util.concurrent.ListenableFuture;
/* loaded from: classes.dex */
public interface Operation {
    @SuppressLint({"SyntheticAccessor"})
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final State.SUCCESS SUCCESS = new State.SUCCESS();
    @SuppressLint({"SyntheticAccessor"})
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final State.IN_PROGRESS IN_PROGRESS = new State.IN_PROGRESS();

    /* loaded from: classes.dex */
    public static abstract class State {

        /* loaded from: classes.dex */
        public static final class FAILURE extends State {

            /* renamed from: a  reason: collision with root package name */
            public final Throwable f1782a;

            public FAILURE(@NonNull Throwable th) {
                this.f1782a = th;
            }

            @NonNull
            public Throwable getThrowable() {
                return this.f1782a;
            }

            @NonNull
            public String toString() {
                return String.format("FAILURE (%s)", this.f1782a.getMessage());
            }
        }

        /* loaded from: classes.dex */
        public static final class IN_PROGRESS extends State {
            @NonNull
            public String toString() {
                return "IN_PROGRESS";
            }

            public IN_PROGRESS() {
            }
        }

        /* loaded from: classes.dex */
        public static final class SUCCESS extends State {
            @NonNull
            public String toString() {
                return "SUCCESS";
            }

            public SUCCESS() {
            }
        }
    }

    @NonNull
    ListenableFuture<State.SUCCESS> getResult();

    @NonNull
    LiveData<State> getState();
}
