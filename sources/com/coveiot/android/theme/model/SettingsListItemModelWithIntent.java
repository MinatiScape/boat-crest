package com.coveiot.android.theme.model;

import android.content.Intent;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class SettingsListItemModelWithIntent {
    @Nullable
    private final String additionalText;
    @Nullable
    private final Integer additionalTextColor;
    private final int iconResource;
    @NotNull
    private final String name;
    @NotNull
    private final Intent navigationIntent;

    public SettingsListItemModelWithIntent(int i, @NotNull String name, @NotNull Intent navigationIntent, @Nullable String str, @Nullable Integer num) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(navigationIntent, "navigationIntent");
        this.iconResource = i;
        this.name = name;
        this.navigationIntent = navigationIntent;
        this.additionalText = str;
        this.additionalTextColor = num;
    }

    public static /* synthetic */ SettingsListItemModelWithIntent copy$default(SettingsListItemModelWithIntent settingsListItemModelWithIntent, int i, String str, Intent intent, String str2, Integer num, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = settingsListItemModelWithIntent.iconResource;
        }
        if ((i2 & 2) != 0) {
            str = settingsListItemModelWithIntent.name;
        }
        String str3 = str;
        if ((i2 & 4) != 0) {
            intent = settingsListItemModelWithIntent.navigationIntent;
        }
        Intent intent2 = intent;
        if ((i2 & 8) != 0) {
            str2 = settingsListItemModelWithIntent.additionalText;
        }
        String str4 = str2;
        if ((i2 & 16) != 0) {
            num = settingsListItemModelWithIntent.additionalTextColor;
        }
        return settingsListItemModelWithIntent.copy(i, str3, intent2, str4, num);
    }

    public final int component1() {
        return this.iconResource;
    }

    @NotNull
    public final String component2() {
        return this.name;
    }

    @NotNull
    public final Intent component3() {
        return this.navigationIntent;
    }

    @Nullable
    public final String component4() {
        return this.additionalText;
    }

    @Nullable
    public final Integer component5() {
        return this.additionalTextColor;
    }

    @NotNull
    public final SettingsListItemModelWithIntent copy(int i, @NotNull String name, @NotNull Intent navigationIntent, @Nullable String str, @Nullable Integer num) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(navigationIntent, "navigationIntent");
        return new SettingsListItemModelWithIntent(i, name, navigationIntent, str, num);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SettingsListItemModelWithIntent) {
            SettingsListItemModelWithIntent settingsListItemModelWithIntent = (SettingsListItemModelWithIntent) obj;
            return this.iconResource == settingsListItemModelWithIntent.iconResource && Intrinsics.areEqual(this.name, settingsListItemModelWithIntent.name) && Intrinsics.areEqual(this.navigationIntent, settingsListItemModelWithIntent.navigationIntent) && Intrinsics.areEqual(this.additionalText, settingsListItemModelWithIntent.additionalText) && Intrinsics.areEqual(this.additionalTextColor, settingsListItemModelWithIntent.additionalTextColor);
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
    public final Intent getNavigationIntent() {
        return this.navigationIntent;
    }

    public int hashCode() {
        int hashCode = ((((Integer.hashCode(this.iconResource) * 31) + this.name.hashCode()) * 31) + this.navigationIntent.hashCode()) * 31;
        String str = this.additionalText;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        Integer num = this.additionalTextColor;
        return hashCode2 + (num != null ? num.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "SettingsListItemModelWithIntent(iconResource=" + this.iconResource + ", name=" + this.name + ", navigationIntent=" + this.navigationIntent + ", additionalText=" + this.additionalText + ", additionalTextColor=" + this.additionalTextColor + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ SettingsListItemModelWithIntent(int i, String str, Intent intent, String str2, Integer num, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, intent, str2, (i2 & 16) != 0 ? null : num);
    }
}
