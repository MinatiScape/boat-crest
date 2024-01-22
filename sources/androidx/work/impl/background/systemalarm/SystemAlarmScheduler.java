package androidx.work.impl.background.systemalarm;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.work.Logger;
import androidx.work.impl.Scheduler;
import androidx.work.impl.model.WorkSpec;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class SystemAlarmScheduler implements Scheduler {
    public static final String i = Logger.tagWithPrefix("SystemAlarmScheduler");
    public final Context h;

    public SystemAlarmScheduler(@NonNull Context context) {
        this.h = context.getApplicationContext();
    }

    public final void a(@NonNull WorkSpec workSpec) {
        Logger.get().debug(i, String.format("Scheduling work with workSpecId %s", workSpec.id), new Throwable[0]);
        this.h.startService(CommandHandler.e(this.h, workSpec.id));
    }

    @Override // androidx.work.impl.Scheduler
    public void cancel(@NonNull String str) {
        this.h.startService(CommandHandler.f(this.h, str));
    }

    @Override // androidx.work.impl.Scheduler
    public boolean hasLimitedSchedulingSlots() {
        return true;
    }

    @Override // androidx.work.impl.Scheduler
    public void schedule(@NonNull WorkSpec... workSpecArr) {
        for (WorkSpec workSpec : workSpecArr) {
            a(workSpec);
        }
    }
}
