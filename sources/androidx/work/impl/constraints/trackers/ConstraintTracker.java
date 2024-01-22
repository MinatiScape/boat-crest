package androidx.work.impl.constraints.trackers;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.work.Logger;
import androidx.work.impl.constraints.ConstraintListener;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public abstract class ConstraintTracker<T> {
    public static final String d = Logger.tagWithPrefix("ConstraintTracker");

    /* renamed from: a  reason: collision with root package name */
    public final Object f1815a = new Object();
    public final Set<ConstraintListener<T>> b = new LinkedHashSet();
    public T c;
    public final Context mAppContext;
    public final TaskExecutor mTaskExecutor;

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public final /* synthetic */ List h;

        public a(List list) {
            this.h = list;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (ConstraintListener constraintListener : this.h) {
                constraintListener.onConstraintChanged(ConstraintTracker.this.c);
            }
        }
    }

    public ConstraintTracker(@NonNull Context context, @NonNull TaskExecutor taskExecutor) {
        this.mAppContext = context.getApplicationContext();
        this.mTaskExecutor = taskExecutor;
    }

    public void addListener(ConstraintListener<T> constraintListener) {
        synchronized (this.f1815a) {
            if (this.b.add(constraintListener)) {
                if (this.b.size() == 1) {
                    this.c = getInitialState();
                    Logger.get().debug(d, String.format("%s: initial state = %s", getClass().getSimpleName(), this.c), new Throwable[0]);
                    startTracking();
                }
                constraintListener.onConstraintChanged(this.c);
            }
        }
    }

    public abstract T getInitialState();

    public void removeListener(ConstraintListener<T> constraintListener) {
        synchronized (this.f1815a) {
            if (this.b.remove(constraintListener) && this.b.isEmpty()) {
                stopTracking();
            }
        }
    }

    public void setState(T t) {
        synchronized (this.f1815a) {
            T t2 = this.c;
            if (t2 != t && (t2 == null || !t2.equals(t))) {
                this.c = t;
                this.mTaskExecutor.getMainThreadExecutor().execute(new a(new ArrayList(this.b)));
            }
        }
    }

    public abstract void startTracking();

    public abstract void stopTracking();
}
