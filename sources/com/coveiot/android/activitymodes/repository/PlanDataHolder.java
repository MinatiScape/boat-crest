package com.coveiot.android.activitymodes.repository;

import com.coveiot.android.activitymodes.database.entities.EntityPlanSchedule;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationDay;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationWeek;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class PlanDataHolder {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final EntityPreparationPlan f2857a;
    @NotNull
    public final EntityPlanSchedule b;
    @NotNull
    public final List<EntityPreparationWeek> c;
    @NotNull
    public final List<EntityPreparationDay> d;
    public final int e;

    public PlanDataHolder(@NotNull EntityPreparationPlan entityPreparationPlan, @NotNull EntityPlanSchedule planSchedule, @NotNull List<EntityPreparationWeek> weeks, @NotNull List<EntityPreparationDay> days, int i) {
        Intrinsics.checkNotNullParameter(entityPreparationPlan, "entityPreparationPlan");
        Intrinsics.checkNotNullParameter(planSchedule, "planSchedule");
        Intrinsics.checkNotNullParameter(weeks, "weeks");
        Intrinsics.checkNotNullParameter(days, "days");
        this.f2857a = entityPreparationPlan;
        this.b = planSchedule;
        this.c = weeks;
        this.d = days;
        this.e = i;
    }

    @NotNull
    public final List<EntityPreparationDay> getDays() {
        return this.d;
    }

    @NotNull
    public final EntityPreparationPlan getEntityPreparationPlan() {
        return this.f2857a;
    }

    @NotNull
    public final EntityPlanSchedule getPlanSchedule() {
        return this.b;
    }

    public final int getPreviousPlansSubscribed() {
        return this.e;
    }

    @NotNull
    public final List<EntityPreparationWeek> getWeeks() {
        return this.c;
    }
}
