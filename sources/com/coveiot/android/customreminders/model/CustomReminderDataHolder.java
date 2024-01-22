package com.coveiot.android.customreminders.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class CustomReminderDataHolder {
    @NotNull
    private final CustomReminder customReminder;
    private final int position;

    public CustomReminderDataHolder(@NotNull CustomReminder customReminder, int i) {
        Intrinsics.checkNotNullParameter(customReminder, "customReminder");
        this.customReminder = customReminder;
        this.position = i;
    }

    public static /* synthetic */ CustomReminderDataHolder copy$default(CustomReminderDataHolder customReminderDataHolder, CustomReminder customReminder, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            customReminder = customReminderDataHolder.customReminder;
        }
        if ((i2 & 2) != 0) {
            i = customReminderDataHolder.position;
        }
        return customReminderDataHolder.copy(customReminder, i);
    }

    @NotNull
    public final CustomReminder component1() {
        return this.customReminder;
    }

    public final int component2() {
        return this.position;
    }

    @NotNull
    public final CustomReminderDataHolder copy(@NotNull CustomReminder customReminder, int i) {
        Intrinsics.checkNotNullParameter(customReminder, "customReminder");
        return new CustomReminderDataHolder(customReminder, i);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CustomReminderDataHolder) {
            CustomReminderDataHolder customReminderDataHolder = (CustomReminderDataHolder) obj;
            return Intrinsics.areEqual(this.customReminder, customReminderDataHolder.customReminder) && this.position == customReminderDataHolder.position;
        }
        return false;
    }

    @NotNull
    public final CustomReminder getCustomReminder() {
        return this.customReminder;
    }

    public final int getPosition() {
        return this.position;
    }

    public int hashCode() {
        return (this.customReminder.hashCode() * 31) + Integer.hashCode(this.position);
    }

    @NotNull
    public String toString() {
        return "CustomReminderDataHolder(customReminder=" + this.customReminder + ", position=" + this.position + HexStringBuilder.COMMENT_END_CHAR;
    }
}
