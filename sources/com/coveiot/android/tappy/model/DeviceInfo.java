package com.coveiot.android.tappy.model;

import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class DeviceInfo implements Serializable {
    @Nullable
    private Long deviceId;
    @Nullable
    private String formFactor;
    @Nullable
    private String friendlyName;
    @Nullable
    private String make;
    @Nullable
    private String model;
    @Nullable
    private String os;
    @Nullable
    private String osVersion;
    @Nullable
    private String status;

    @Nullable
    public final Long getDeviceId() {
        return this.deviceId;
    }

    @Nullable
    public final String getFormFactor() {
        return this.formFactor;
    }

    @Nullable
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    @Nullable
    public final String getMake() {
        return this.make;
    }

    @Nullable
    public final String getModel() {
        return this.model;
    }

    @Nullable
    public final String getOs() {
        return this.os;
    }

    @Nullable
    public final String getOsVersion() {
        return this.osVersion;
    }

    @Nullable
    public final String getStatus() {
        return this.status;
    }

    public final void setDeviceId(@Nullable Long l) {
        this.deviceId = l;
    }

    public final void setFormFactor(@Nullable String str) {
        this.formFactor = str;
    }

    public final void setFriendlyName(@Nullable String str) {
        this.friendlyName = str;
    }

    public final void setMake(@Nullable String str) {
        this.make = str;
    }

    public final void setModel(@Nullable String str) {
        this.model = str;
    }

    public final void setOs(@Nullable String str) {
        this.os = str;
    }

    public final void setOsVersion(@Nullable String str) {
        this.osVersion = str;
    }

    public final void setStatus(@Nullable String str) {
        this.status = str;
    }
}
