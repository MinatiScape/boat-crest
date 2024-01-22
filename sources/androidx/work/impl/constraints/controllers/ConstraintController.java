package androidx.work.impl.constraints.controllers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.work.impl.constraints.ConstraintListener;
import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.model.WorkSpec;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public abstract class ConstraintController<T> implements ConstraintListener<T> {

    /* renamed from: a  reason: collision with root package name */
    public final List<String> f1813a = new ArrayList();
    public T b;
    public ConstraintTracker<T> c;
    public OnConstraintUpdatedCallback d;

    /* loaded from: classes.dex */
    public interface OnConstraintUpdatedCallback {
        void onConstraintMet(@NonNull List<String> list);

        void onConstraintNotMet(@NonNull List<String> list);
    }

    public ConstraintController(ConstraintTracker<T> constraintTracker) {
        this.c = constraintTracker;
    }

    public abstract boolean a(@NonNull WorkSpec workSpec);

    public abstract boolean b(@NonNull T t);

    public final void c(@Nullable OnConstraintUpdatedCallback onConstraintUpdatedCallback, @Nullable T t) {
        if (this.f1813a.isEmpty() || onConstraintUpdatedCallback == null) {
            return;
        }
        if (t != null && !b(t)) {
            onConstraintUpdatedCallback.onConstraintMet(this.f1813a);
        } else {
            onConstraintUpdatedCallback.onConstraintNotMet(this.f1813a);
        }
    }

    public boolean isWorkSpecConstrained(@NonNull String str) {
        T t = this.b;
        return t != null && b(t) && this.f1813a.contains(str);
    }

    @Override // androidx.work.impl.constraints.ConstraintListener
    public void onConstraintChanged(@Nullable T t) {
        this.b = t;
        c(this.d, t);
    }

    public void replace(@NonNull Iterable<WorkSpec> iterable) {
        this.f1813a.clear();
        for (WorkSpec workSpec : iterable) {
            if (a(workSpec)) {
                this.f1813a.add(workSpec.id);
            }
        }
        if (this.f1813a.isEmpty()) {
            this.c.removeListener(this);
        } else {
            this.c.addListener(this);
        }
        c(this.d, this.b);
    }

    public void reset() {
        if (this.f1813a.isEmpty()) {
            return;
        }
        this.f1813a.clear();
        this.c.removeListener(this);
    }

    public void setCallback(@Nullable OnConstraintUpdatedCallback onConstraintUpdatedCallback) {
        if (this.d != onConstraintUpdatedCallback) {
            this.d = onConstraintUpdatedCallback;
            c(onConstraintUpdatedCallback, this.b);
        }
    }
}
