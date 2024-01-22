package com.coveiot.android.customreminders.model;

import com.coveiot.android.customreminders.ReminderType;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class MainReminderListDataHolder {
    @NotNull
    private final List<CustomReminderDataHolder> customReminderDataHolderList;
    private final int reminderIcon;
    @NotNull
    private final ReminderType reminderType;
    @NotNull
    private final String reminderTypeName;

    public MainReminderListDataHolder(@NotNull ReminderType reminderType, @NotNull String reminderTypeName, int i, @NotNull List<CustomReminderDataHolder> customReminderDataHolderList) {
        Intrinsics.checkNotNullParameter(reminderType, "reminderType");
        Intrinsics.checkNotNullParameter(reminderTypeName, "reminderTypeName");
        Intrinsics.checkNotNullParameter(customReminderDataHolderList, "customReminderDataHolderList");
        this.reminderType = reminderType;
        this.reminderTypeName = reminderTypeName;
        this.reminderIcon = i;
        this.customReminderDataHolderList = customReminderDataHolderList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MainReminderListDataHolder copy$default(MainReminderListDataHolder mainReminderListDataHolder, ReminderType reminderType, String str, int i, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            reminderType = mainReminderListDataHolder.reminderType;
        }
        if ((i2 & 2) != 0) {
            str = mainReminderListDataHolder.reminderTypeName;
        }
        if ((i2 & 4) != 0) {
            i = mainReminderListDataHolder.reminderIcon;
        }
        if ((i2 & 8) != 0) {
            list = mainReminderListDataHolder.customReminderDataHolderList;
        }
        return mainReminderListDataHolder.copy(reminderType, str, i, list);
    }

    @NotNull
    public final ReminderType component1() {
        return this.reminderType;
    }

    @NotNull
    public final String component2() {
        return this.reminderTypeName;
    }

    public final int component3() {
        return this.reminderIcon;
    }

    @NotNull
    public final List<CustomReminderDataHolder> component4() {
        return this.customReminderDataHolderList;
    }

    @NotNull
    public final MainReminderListDataHolder copy(@NotNull ReminderType reminderType, @NotNull String reminderTypeName, int i, @NotNull List<CustomReminderDataHolder> customReminderDataHolderList) {
        Intrinsics.checkNotNullParameter(reminderType, "reminderType");
        Intrinsics.checkNotNullParameter(reminderTypeName, "reminderTypeName");
        Intrinsics.checkNotNullParameter(customReminderDataHolderList, "customReminderDataHolderList");
        return new MainReminderListDataHolder(reminderType, reminderTypeName, i, customReminderDataHolderList);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MainReminderListDataHolder) {
            MainReminderListDataHolder mainReminderListDataHolder = (MainReminderListDataHolder) obj;
            return this.reminderType == mainReminderListDataHolder.reminderType && Intrinsics.areEqual(this.reminderTypeName, mainReminderListDataHolder.reminderTypeName) && this.reminderIcon == mainReminderListDataHolder.reminderIcon && Intrinsics.areEqual(this.customReminderDataHolderList, mainReminderListDataHolder.customReminderDataHolderList);
        }
        return false;
    }

    @NotNull
    public final List<CustomReminderDataHolder> getCustomReminderDataHolderList() {
        return this.customReminderDataHolderList;
    }

    public final int getReminderIcon() {
        return this.reminderIcon;
    }

    @NotNull
    public final ReminderType getReminderType() {
        return this.reminderType;
    }

    @NotNull
    public final String getReminderTypeName() {
        return this.reminderTypeName;
    }

    public int hashCode() {
        return (((((this.reminderType.hashCode() * 31) + this.reminderTypeName.hashCode()) * 31) + Integer.hashCode(this.reminderIcon)) * 31) + this.customReminderDataHolderList.hashCode();
    }

    @NotNull
    public String toString() {
        return "MainReminderListDataHolder(reminderType=" + this.reminderType + ", reminderTypeName=" + this.reminderTypeName + ", reminderIcon=" + this.reminderIcon + ", customReminderDataHolderList=" + this.customReminderDataHolderList + HexStringBuilder.COMMENT_END_CHAR;
    }
}
