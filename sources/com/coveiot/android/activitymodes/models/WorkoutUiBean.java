package com.coveiot.android.activitymodes.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class WorkoutUiBean {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Integer f2855a;
    @NotNull
    public String b;
    @NotNull
    public String c;
    public int d;

    public WorkoutUiBean(@Nullable Integer num, @NotNull String title, @NotNull String value, int i) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(value, "value");
        this.f2855a = num;
        this.b = title;
        this.c = value;
        this.d = i;
    }

    public static /* synthetic */ WorkoutUiBean copy$default(WorkoutUiBean workoutUiBean, Integer num, String str, String str2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            num = workoutUiBean.f2855a;
        }
        if ((i2 & 2) != 0) {
            str = workoutUiBean.b;
        }
        if ((i2 & 4) != 0) {
            str2 = workoutUiBean.c;
        }
        if ((i2 & 8) != 0) {
            i = workoutUiBean.d;
        }
        return workoutUiBean.copy(num, str, str2, i);
    }

    @Nullable
    public final Integer component1() {
        return this.f2855a;
    }

    @NotNull
    public final String component2() {
        return this.b;
    }

    @NotNull
    public final String component3() {
        return this.c;
    }

    public final int component4() {
        return this.d;
    }

    @NotNull
    public final WorkoutUiBean copy(@Nullable Integer num, @NotNull String title, @NotNull String value, int i) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(value, "value");
        return new WorkoutUiBean(num, title, value, i);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WorkoutUiBean) {
            WorkoutUiBean workoutUiBean = (WorkoutUiBean) obj;
            return Intrinsics.areEqual(this.f2855a, workoutUiBean.f2855a) && Intrinsics.areEqual(this.b, workoutUiBean.b) && Intrinsics.areEqual(this.c, workoutUiBean.c) && this.d == workoutUiBean.d;
        }
        return false;
    }

    @Nullable
    public final Integer getImage() {
        return this.f2855a;
    }

    public final int getPosition() {
        return this.d;
    }

    @NotNull
    public final String getTitle() {
        return this.b;
    }

    @NotNull
    public final String getValue() {
        return this.c;
    }

    public int hashCode() {
        Integer num = this.f2855a;
        return ((((((num == null ? 0 : num.hashCode()) * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + Integer.hashCode(this.d);
    }

    public final void setImage(@Nullable Integer num) {
        this.f2855a = num;
    }

    public final void setPosition(int i) {
        this.d = i;
    }

    public final void setTitle(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.b = str;
    }

    public final void setValue(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.c = str;
    }

    @NotNull
    public String toString() {
        return "WorkoutUiBean(image=" + this.f2855a + ", title=" + this.b + ", value=" + this.c + ", position=" + this.d + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ WorkoutUiBean(Integer num, String str, String str2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(num, str, str2, (i2 & 8) != 0 ? 0 : i);
    }
}
