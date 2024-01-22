package androidx.work.impl.constraints;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.work.Logger;
import androidx.work.impl.constraints.controllers.BatteryChargingController;
import androidx.work.impl.constraints.controllers.BatteryNotLowController;
import androidx.work.impl.constraints.controllers.ConstraintController;
import androidx.work.impl.constraints.controllers.NetworkConnectedController;
import androidx.work.impl.constraints.controllers.NetworkMeteredController;
import androidx.work.impl.constraints.controllers.NetworkNotRoamingController;
import androidx.work.impl.constraints.controllers.NetworkUnmeteredController;
import androidx.work.impl.constraints.controllers.StorageNotLowController;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class WorkConstraintsTracker implements ConstraintController.OnConstraintUpdatedCallback {
    public static final String d = Logger.tagWithPrefix("WorkConstraintsTracker");
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final WorkConstraintsCallback f1812a;
    public final ConstraintController<?>[] b;
    public final Object c;

    public WorkConstraintsTracker(@NonNull Context context, @NonNull TaskExecutor taskExecutor, @Nullable WorkConstraintsCallback workConstraintsCallback) {
        Context applicationContext = context.getApplicationContext();
        this.f1812a = workConstraintsCallback;
        this.b = new ConstraintController[]{new BatteryChargingController(applicationContext, taskExecutor), new BatteryNotLowController(applicationContext, taskExecutor), new StorageNotLowController(applicationContext, taskExecutor), new NetworkConnectedController(applicationContext, taskExecutor), new NetworkUnmeteredController(applicationContext, taskExecutor), new NetworkNotRoamingController(applicationContext, taskExecutor), new NetworkMeteredController(applicationContext, taskExecutor)};
        this.c = new Object();
    }

    public boolean areAllConstraintsMet(@NonNull String str) {
        ConstraintController<?>[] constraintControllerArr;
        synchronized (this.c) {
            for (ConstraintController<?> constraintController : this.b) {
                if (constraintController.isWorkSpecConstrained(str)) {
                    Logger.get().debug(d, String.format("Work %s constrained by %s", str, constraintController.getClass().getSimpleName()), new Throwable[0]);
                    return false;
                }
            }
            return true;
        }
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController.OnConstraintUpdatedCallback
    public void onConstraintMet(@NonNull List<String> list) {
        synchronized (this.c) {
            ArrayList arrayList = new ArrayList();
            for (String str : list) {
                if (areAllConstraintsMet(str)) {
                    Logger.get().debug(d, String.format("Constraints met for %s", str), new Throwable[0]);
                    arrayList.add(str);
                }
            }
            WorkConstraintsCallback workConstraintsCallback = this.f1812a;
            if (workConstraintsCallback != null) {
                workConstraintsCallback.onAllConstraintsMet(arrayList);
            }
        }
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController.OnConstraintUpdatedCallback
    public void onConstraintNotMet(@NonNull List<String> list) {
        synchronized (this.c) {
            WorkConstraintsCallback workConstraintsCallback = this.f1812a;
            if (workConstraintsCallback != null) {
                workConstraintsCallback.onAllConstraintsNotMet(list);
            }
        }
    }

    public void replace(@NonNull Iterable<WorkSpec> iterable) {
        synchronized (this.c) {
            for (ConstraintController<?> constraintController : this.b) {
                constraintController.setCallback(null);
            }
            for (ConstraintController<?> constraintController2 : this.b) {
                constraintController2.replace(iterable);
            }
            for (ConstraintController<?> constraintController3 : this.b) {
                constraintController3.setCallback(this);
            }
        }
    }

    public void reset() {
        synchronized (this.c) {
            for (ConstraintController<?> constraintController : this.b) {
                constraintController.reset();
            }
        }
    }
}
