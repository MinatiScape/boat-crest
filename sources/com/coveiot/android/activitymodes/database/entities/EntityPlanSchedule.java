package com.coveiot.android.activitymodes.database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Entity(foreignKeys = {@ForeignKey(childColumns = {"plan_id"}, entity = EntityPreparationPlan.class, onDelete = 5, onUpdate = 1, parentColumns = {"planId"})}, indices = {@Index({"plan_id"})}, tableName = "entity_plan_schedule")
/* loaded from: classes2.dex */
public final class EntityPlanSchedule {
    @PrimaryKey(autoGenerate = true)

    /* renamed from: a  reason: collision with root package name */
    public int f2808a;
    public List<String> overview;
    public String plan_id;

    @NotNull
    public final List<String> getOverview() {
        List<String> list = this.overview;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("overview");
        return null;
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

    public final int getSchedule_id() {
        return this.f2808a;
    }

    public final void setOverview(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.overview = list;
    }

    public final void setPlan_id(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.plan_id = str;
    }

    public final void setSchedule_id(int i) {
        this.f2808a = i;
    }
}
