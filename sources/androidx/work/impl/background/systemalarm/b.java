package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
import androidx.work.Logger;
import androidx.work.impl.background.systemalarm.SystemAlarmDispatcher;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.model.WorkSpec;
import java.util.ArrayList;
import java.util.List;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class b {
    public static final String e = Logger.tagWithPrefix("ConstraintsCmdHandler");

    /* renamed from: a  reason: collision with root package name */
    public final Context f1808a;
    public final int b;
    public final SystemAlarmDispatcher c;
    public final WorkConstraintsTracker d;

    public b(@NonNull Context context, int i, @NonNull SystemAlarmDispatcher systemAlarmDispatcher) {
        this.f1808a = context;
        this.b = i;
        this.c = systemAlarmDispatcher;
        this.d = new WorkConstraintsTracker(context, systemAlarmDispatcher.d(), null);
    }

    @WorkerThread
    public void a() {
        List<WorkSpec> scheduledWork = this.c.e().getWorkDatabase().workSpecDao().getScheduledWork();
        ConstraintProxy.a(this.f1808a, scheduledWork);
        this.d.replace(scheduledWork);
        ArrayList<WorkSpec> arrayList = new ArrayList(scheduledWork.size());
        long currentTimeMillis = System.currentTimeMillis();
        for (WorkSpec workSpec : scheduledWork) {
            String str = workSpec.id;
            if (currentTimeMillis >= workSpec.calculateNextRunTime() && (!workSpec.hasConstraints() || this.d.areAllConstraintsMet(str))) {
                arrayList.add(workSpec);
            }
        }
        for (WorkSpec workSpec2 : arrayList) {
            String str2 = workSpec2.id;
            Intent b = CommandHandler.b(this.f1808a, str2);
            Logger.get().debug(e, String.format("Creating a delay_met command for workSpec with id (%s)", str2), new Throwable[0]);
            SystemAlarmDispatcher systemAlarmDispatcher = this.c;
            systemAlarmDispatcher.i(new SystemAlarmDispatcher.b(systemAlarmDispatcher, b, this.b));
        }
        this.d.reset();
    }
}
