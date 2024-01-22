package com.coveiot.android.customreminders.model;

import com.coveiot.android.customreminders.ReminderType;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ReminderTypeModel {
    private final int imageResource;
    @NotNull
    private final String name;
    @NotNull
    private final ReminderType reminderType;

    public ReminderTypeModel(int i, @NotNull String name, @NotNull ReminderType reminderType) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(reminderType, "reminderType");
        this.imageResource = i;
        this.name = name;
        this.reminderType = reminderType;
    }

    public static /* synthetic */ ReminderTypeModel copy$default(ReminderTypeModel reminderTypeModel, int i, String str, ReminderType reminderType, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = reminderTypeModel.imageResource;
        }
        if ((i2 & 2) != 0) {
            str = reminderTypeModel.name;
        }
        if ((i2 & 4) != 0) {
            reminderType = reminderTypeModel.reminderType;
        }
        return reminderTypeModel.copy(i, str, reminderType);
    }

    public final int component1() {
        return this.imageResource;
    }

    @NotNull
    public final String component2() {
        return this.name;
    }

    @NotNull
    public final ReminderType component3() {
        return this.reminderType;
    }

    @NotNull
    public final ReminderTypeModel copy(int i, @NotNull String name, @NotNull ReminderType reminderType) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(reminderType, "reminderType");
        return new ReminderTypeModel(i, name, reminderType);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ReminderTypeModel) {
            ReminderTypeModel reminderTypeModel = (ReminderTypeModel) obj;
            return this.imageResource == reminderTypeModel.imageResource && Intrinsics.areEqual(this.name, reminderTypeModel.name) && this.reminderType == reminderTypeModel.reminderType;
        }
        return false;
    }

    public final int getImageResource() {
        return this.imageResource;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final ReminderType getReminderType() {
        return this.reminderType;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.imageResource) * 31) + this.name.hashCode()) * 31) + this.reminderType.hashCode();
    }

    @NotNull
    public String toString() {
        return "ReminderTypeModel(imageResource=" + this.imageResource + ", name=" + this.name + ", reminderType=" + this.reminderType + HexStringBuilder.COMMENT_END_CHAR;
    }
}
