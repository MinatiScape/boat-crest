package com.coveiot.android.fitnessbuddies.models;

import android.text.format.DateUtils;
import java.util.Calendar;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class BuddyReminderModel {

    /* renamed from: a  reason: collision with root package name */
    public final int f4470a;
    public boolean b;
    @NotNull
    public Calendar c;

    public BuddyReminderModel(int i, boolean z, @NotNull Calendar remindDate) {
        Intrinsics.checkNotNullParameter(remindDate, "remindDate");
        this.f4470a = i;
        this.b = z;
        this.c = remindDate;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (Intrinsics.areEqual(BuddyReminderModel.class, obj != null ? obj.getClass() : null)) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.coveiot.android.fitnessbuddies.models.BuddyReminderModel");
            return this.f4470a == ((BuddyReminderModel) obj).f4470a;
        }
        return false;
    }

    public final int getBuddyUserId() {
        return this.f4470a;
    }

    public final boolean getHasRemindedBuddy() {
        return this.b;
    }

    @NotNull
    public final Calendar getRemindDate() {
        return this.c;
    }

    public int hashCode() {
        return this.f4470a;
    }

    public final boolean isRemindDataToday() {
        return DateUtils.isToday(this.c.getTimeInMillis());
    }

    public final void setHasRemindedBuddy(boolean z) {
        this.b = z;
    }

    public final void setRemindDate(@NotNull Calendar calendar) {
        Intrinsics.checkNotNullParameter(calendar, "<set-?>");
        this.c = calendar;
    }
}
