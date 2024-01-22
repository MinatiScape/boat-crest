package com.coveiot.android.theme.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class SettingsListItemModel {
    @Nullable
    private final String additionalText;
    @Nullable
    private final Integer additionalTextColor;
    private final int iconResource;
    @NotNull
    private final String name;
    @NotNull
    private final Class<?> navigationClass;
    private final boolean shouldCheckForConnectionStatus;

    public SettingsListItemModel(int i, @NotNull String name, @NotNull Class<?> navigationClass, @Nullable String str, @Nullable Integer num, boolean z) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(navigationClass, "navigationClass");
        this.iconResource = i;
        this.name = name;
        this.navigationClass = navigationClass;
        this.additionalText = str;
        this.additionalTextColor = num;
        this.shouldCheckForConnectionStatus = z;
    }

    public static /* synthetic */ SettingsListItemModel copy$default(SettingsListItemModel settingsListItemModel, int i, String str, Class cls, String str2, Integer num, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = settingsListItemModel.iconResource;
        }
        if ((i2 & 2) != 0) {
            str = settingsListItemModel.name;
        }
        String str3 = str;
        Class<?> cls2 = cls;
        if ((i2 & 4) != 0) {
            cls2 = settingsListItemModel.navigationClass;
        }
        Class cls3 = cls2;
        if ((i2 & 8) != 0) {
            str2 = settingsListItemModel.additionalText;
        }
        String str4 = str2;
        if ((i2 & 16) != 0) {
            num = settingsListItemModel.additionalTextColor;
        }
        Integer num2 = num;
        if ((i2 & 32) != 0) {
            z = settingsListItemModel.shouldCheckForConnectionStatus;
        }
        return settingsListItemModel.copy(i, str3, cls3, str4, num2, z);
    }

    public final int component1() {
        return this.iconResource;
    }

    @NotNull
    public final String component2() {
        return this.name;
    }

    @NotNull
    public final Class<?> component3() {
        return this.navigationClass;
    }

    @Nullable
    public final String component4() {
        return this.additionalText;
    }

    @Nullable
    public final Integer component5() {
        return this.additionalTextColor;
    }

    public final boolean component6() {
        return this.shouldCheckForConnectionStatus;
    }

    @NotNull
    public final SettingsListItemModel copy(int i, @NotNull String name, @NotNull Class<?> navigationClass, @Nullable String str, @Nullable Integer num, boolean z) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(navigationClass, "navigationClass");
        return new SettingsListItemModel(i, name, navigationClass, str, num, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SettingsListItemModel) {
            SettingsListItemModel settingsListItemModel = (SettingsListItemModel) obj;
            return this.iconResource == settingsListItemModel.iconResource && Intrinsics.areEqual(this.name, settingsListItemModel.name) && Intrinsics.areEqual(this.navigationClass, settingsListItemModel.navigationClass) && Intrinsics.areEqual(this.additionalText, settingsListItemModel.additionalText) && Intrinsics.areEqual(this.additionalTextColor, settingsListItemModel.additionalTextColor) && this.shouldCheckForConnectionStatus == settingsListItemModel.shouldCheckForConnectionStatus;
        }
        return false;
    }

    @Nullable
    public final String getAdditionalText() {
        return this.additionalText;
    }

    @Nullable
    public final Integer getAdditionalTextColor() {
        return this.additionalTextColor;
    }

    public final int getIconResource() {
        return this.iconResource;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final Class<?> getNavigationClass() {
        return this.navigationClass;
    }

    public final boolean getShouldCheckForConnectionStatus() {
        return this.shouldCheckForConnectionStatus;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((((Integer.hashCode(this.iconResource) * 31) + this.name.hashCode()) * 31) + this.navigationClass.hashCode()) * 31;
        String str = this.additionalText;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        Integer num = this.additionalTextColor;
        int hashCode3 = (hashCode2 + (num != null ? num.hashCode() : 0)) * 31;
        boolean z = this.shouldCheckForConnectionStatus;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode3 + i;
    }

    @NotNull
    public String toString() {
        return "SettingsListItemModel(iconResource=" + this.iconResource + ", name=" + this.name + ", navigationClass=" + this.navigationClass + ", additionalText=" + this.additionalText + ", additionalTextColor=" + this.additionalTextColor + ", shouldCheckForConnectionStatus=" + this.shouldCheckForConnectionStatus + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ SettingsListItemModel(int i, String str, Class cls, String str2, Integer num, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, cls, str2, (i2 & 16) != 0 ? null : num, (i2 & 32) != 0 ? false : z);
    }
}
