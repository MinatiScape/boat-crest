package com.coveiot.android.activitymodes.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import com.coveiot.android.activitymodes.BASEUNIT;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Entity(foreignKeys = {@ForeignKey(childColumns = {"plan_id"}, entity = EntityPreparationPlan.class, onDelete = 5, onUpdate = 1, parentColumns = {"planId"})}, indices = {@Index({"plan_id"})}, primaryKeys = {"week_number", "plan_id"}, tableName = "entity_preparation_week")
/* loaded from: classes2.dex */
public final class EntityPreparationWeek {

    /* renamed from: a  reason: collision with root package name */
    public int f2809a;
    public int b;
    public int c;
    @NotNull
    public String d = BASEUNIT.METERS.toString();
    public List<String> daysRange;
    public List<String> introTexts;
    public String name;
    @NonNull
    public String plan_id;
    public String shortDesc;

    @NotNull
    public final List<String> getDaysRange() {
        List<String> list = this.daysRange;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("daysRange");
        return null;
    }

    @NotNull
    public final List<String> getIntroTexts() {
        List<String> list = this.introTexts;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("introTexts");
        return null;
    }

    @NotNull
    public final String getName() {
        String str = this.name;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException(AppMeasurementSdk.ConditionalUserProperty.NAME);
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

    public final int getScheduleId() {
        return this.b;
    }

    @NotNull
    public final String getShortDesc() {
        String str = this.shortDesc;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("shortDesc");
        return null;
    }

    @NotNull
    public final String getTargetBaseUnit() {
        return this.d;
    }

    public final int getWeek_number() {
        return this.f2809a;
    }

    public final int getWeeklyTarget() {
        return this.c;
    }

    public final void setDaysRange(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.daysRange = list;
    }

    public final void setIntroTexts(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.introTexts = list;
    }

    public final void setName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final void setPlan_id(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.plan_id = str;
    }

    public final void setScheduleId(int i) {
        this.b = i;
    }

    public final void setShortDesc(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.shortDesc = str;
    }

    public final void setTargetBaseUnit(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.d = str;
    }

    public final void setWeek_number(int i) {
        this.f2809a = i;
    }

    public final void setWeeklyTarget(int i) {
        this.c = i;
    }
}
