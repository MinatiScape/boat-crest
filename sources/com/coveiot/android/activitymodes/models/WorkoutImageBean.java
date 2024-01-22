package com.coveiot.android.activitymodes.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class WorkoutImageBean {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Object f2854a;

    public WorkoutImageBean(@NotNull Object image) {
        Intrinsics.checkNotNullParameter(image, "image");
        this.f2854a = image;
    }

    public static /* synthetic */ WorkoutImageBean copy$default(WorkoutImageBean workoutImageBean, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = workoutImageBean.f2854a;
        }
        return workoutImageBean.copy(obj);
    }

    @NotNull
    public final Object component1() {
        return this.f2854a;
    }

    @NotNull
    public final WorkoutImageBean copy(@NotNull Object image) {
        Intrinsics.checkNotNullParameter(image, "image");
        return new WorkoutImageBean(image);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof WorkoutImageBean) && Intrinsics.areEqual(this.f2854a, ((WorkoutImageBean) obj).f2854a);
    }

    @NotNull
    public final Object getImage() {
        return this.f2854a;
    }

    public int hashCode() {
        return this.f2854a.hashCode();
    }

    @NotNull
    public String toString() {
        return "WorkoutImageBean(image=" + this.f2854a + HexStringBuilder.COMMENT_END_CHAR;
    }
}
