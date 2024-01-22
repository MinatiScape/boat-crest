package com.coveiot.android.smartalert.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SmartAlertTemplateConfig implements Serializable {
    @SerializedName("deviceConfig")
    @Expose
    @Nullable
    private DeviceInfo deviceConfig;
    @SerializedName("messageType")
    @Expose
    @NotNull
    private String messageTargetType = MessageTargetType.NUDGE.getType();
    @SerializedName("smart_alert_templateId_config")
    @Expose
    @Nullable
    private List<InputDynamicFieldConfiguration> smartAlertTemplateIdIdConfig;

    @Nullable
    public final DeviceInfo getDeviceConfig() {
        return this.deviceConfig;
    }

    @NotNull
    public final String getMessageTargetType() {
        return this.messageTargetType;
    }

    @Nullable
    public final List<InputDynamicFieldConfiguration> getSmartAlertTemplateIdIdConfig() {
        return this.smartAlertTemplateIdIdConfig;
    }

    public final void setDeviceConfig(@Nullable DeviceInfo deviceInfo) {
        this.deviceConfig = deviceInfo;
    }

    public final void setMessageTargetType(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.messageTargetType = str;
    }

    public final void setSmartAlertTemplateIdIdConfig(@Nullable List<InputDynamicFieldConfiguration> list) {
        this.smartAlertTemplateIdIdConfig = list;
    }
}
