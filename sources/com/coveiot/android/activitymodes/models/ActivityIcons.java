package com.coveiot.android.activitymodes.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityIcons {
    @SerializedName("activities")
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private final List<ActivitiesItem> f2845a;

    public ActivityIcons() {
        this(null, 1, null);
    }

    public ActivityIcons(@Nullable List<ActivitiesItem> list) {
        this.f2845a = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ActivityIcons copy$default(ActivityIcons activityIcons, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = activityIcons.f2845a;
        }
        return activityIcons.copy(list);
    }

    @Nullable
    public final List<ActivitiesItem> component1() {
        return this.f2845a;
    }

    @NotNull
    public final ActivityIcons copy(@Nullable List<ActivitiesItem> list) {
        return new ActivityIcons(list);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ActivityIcons) && Intrinsics.areEqual(this.f2845a, ((ActivityIcons) obj).f2845a);
    }

    @Nullable
    public final List<ActivitiesItem> getActivities() {
        return this.f2845a;
    }

    public int hashCode() {
        List<ActivitiesItem> list = this.f2845a;
        if (list == null) {
            return 0;
        }
        return list.hashCode();
    }

    @NotNull
    public String toString() {
        return "ActivityIcons(activities=" + this.f2845a + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ ActivityIcons(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list);
    }
}
