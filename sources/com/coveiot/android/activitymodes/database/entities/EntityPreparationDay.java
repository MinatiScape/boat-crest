package com.coveiot.android.activitymodes.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import com.coveiot.android.activitymodes.BASEUNIT;
import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(foreignKeys = {@ForeignKey(childColumns = {"plan_id"}, entity = EntityPreparationPlan.class, onDelete = 5, onUpdate = 1, parentColumns = {"planId"})}, indices = {@Index({"plan_id"})}, primaryKeys = {"date", "plan_id"}, tableName = "entity_preparation_day")
/* loaded from: classes2.dex */
public final class EntityPreparationDay implements Serializable {
    public List<EntityPreparationActivity> activities;
    @NonNull
    public String date;
    private int day_number;
    @Nullable
    private String name;
    @NonNull
    public String plan_id;
    private int scheduleId;
    private int week_number;

    @NotNull
    public final List<EntityPreparationActivity> getActivities() {
        List<EntityPreparationActivity> list = this.activities;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("activities");
        return null;
    }

    @NotNull
    public final String getDate() {
        String str = this.date;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("date");
        return null;
    }

    public final int getDay_number() {
        return this.day_number;
    }

    @Nullable
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getPlan_id() {
        String str = this.plan_id;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("plan_id");
        return null;
    }

    public final int getScheduleId() {
        return this.scheduleId;
    }

    public final int getTarget() {
        int i = 0;
        for (EntityPreparationActivity entityPreparationActivity : getActivities()) {
            if (m.equals(entityPreparationActivity.getActivityBaseUnit(), BASEUNIT.METERS.toString(), true) && entityPreparationActivity.getTarget() != null) {
                String target = entityPreparationActivity.getTarget();
                Intrinsics.checkNotNull(target);
                i += Integer.parseInt(target);
            }
            if (m.equals(entityPreparationActivity.getActivityBaseUnit(), BASEUNIT.KILOMETERS.toString(), true) && entityPreparationActivity.getTarget() != null) {
                String target2 = entityPreparationActivity.getTarget();
                Intrinsics.checkNotNull(target2);
                i += Integer.parseInt(target2) * 1000;
            }
        }
        return i;
    }

    public final int getWeek_number() {
        return this.week_number;
    }

    public final void setActivities(@NotNull List<EntityPreparationActivity> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.activities = list;
    }

    public final void setDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.date = str;
    }

    public final void setDay_number(int i) {
        this.day_number = i;
    }

    public final void setName(@Nullable String str) {
        this.name = str;
    }

    public final void setPlan_id(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.plan_id = str;
    }

    public final void setScheduleId(int i) {
        this.scheduleId = i;
    }

    public final void setWeek_number(int i) {
        this.week_number = i;
    }
}
