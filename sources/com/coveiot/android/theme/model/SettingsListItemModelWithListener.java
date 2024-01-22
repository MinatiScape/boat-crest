package com.coveiot.android.theme.model;

import com.coveiot.android.theme.ItemClickListenerNew;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class SettingsListItemModelWithListener {
    @Nullable
    private final String additionalText;
    @Nullable
    private final Integer additionalTextColor;
    @Nullable
    private final Integer iconResource;
    @NotNull
    private final ItemClickListenerNew listener;
    @NotNull
    private final String name;

    public SettingsListItemModelWithListener(@Nullable Integer num, @NotNull String name, @NotNull ItemClickListenerNew listener, @Nullable String str, @Nullable Integer num2) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.iconResource = num;
        this.name = name;
        this.listener = listener;
        this.additionalText = str;
        this.additionalTextColor = num2;
    }

    public static /* synthetic */ SettingsListItemModelWithListener copy$default(SettingsListItemModelWithListener settingsListItemModelWithListener, Integer num, String str, ItemClickListenerNew itemClickListenerNew, String str2, Integer num2, int i, Object obj) {
        if ((i & 1) != 0) {
            num = settingsListItemModelWithListener.iconResource;
        }
        if ((i & 2) != 0) {
            str = settingsListItemModelWithListener.name;
        }
        String str3 = str;
        if ((i & 4) != 0) {
            itemClickListenerNew = settingsListItemModelWithListener.listener;
        }
        ItemClickListenerNew itemClickListenerNew2 = itemClickListenerNew;
        if ((i & 8) != 0) {
            str2 = settingsListItemModelWithListener.additionalText;
        }
        String str4 = str2;
        if ((i & 16) != 0) {
            num2 = settingsListItemModelWithListener.additionalTextColor;
        }
        return settingsListItemModelWithListener.copy(num, str3, itemClickListenerNew2, str4, num2);
    }

    @Nullable
    public final Integer component1() {
        return this.iconResource;
    }

    @NotNull
    public final String component2() {
        return this.name;
    }

    @NotNull
    public final ItemClickListenerNew component3() {
        return this.listener;
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
    public final SettingsListItemModelWithListener copy(@Nullable Integer num, @NotNull String name, @NotNull ItemClickListenerNew listener, @Nullable String str, @Nullable Integer num2) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(listener, "listener");
        return new SettingsListItemModelWithListener(num, name, listener, str, num2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SettingsListItemModelWithListener) {
            SettingsListItemModelWithListener settingsListItemModelWithListener = (SettingsListItemModelWithListener) obj;
            return Intrinsics.areEqual(this.iconResource, settingsListItemModelWithListener.iconResource) && Intrinsics.areEqual(this.name, settingsListItemModelWithListener.name) && Intrinsics.areEqual(this.listener, settingsListItemModelWithListener.listener) && Intrinsics.areEqual(this.additionalText, settingsListItemModelWithListener.additionalText) && Intrinsics.areEqual(this.additionalTextColor, settingsListItemModelWithListener.additionalTextColor);
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

    @Nullable
    public final Integer getIconResource() {
        return this.iconResource;
    }

    @NotNull
    public final ItemClickListenerNew getListener() {
        return this.listener;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        Integer num = this.iconResource;
        int hashCode = (((((num == null ? 0 : num.hashCode()) * 31) + this.name.hashCode()) * 31) + this.listener.hashCode()) * 31;
        String str = this.additionalText;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        Integer num2 = this.additionalTextColor;
        return hashCode2 + (num2 != null ? num2.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "SettingsListItemModelWithListener(iconResource=" + this.iconResource + ", name=" + this.name + ", listener=" + this.listener + ", additionalText=" + this.additionalText + ", additionalTextColor=" + this.additionalTextColor + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ SettingsListItemModelWithListener(Integer num, String str, ItemClickListenerNew itemClickListenerNew, String str2, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(num, str, itemClickListenerNew, str2, (i & 16) != 0 ? null : num2);
    }
}
