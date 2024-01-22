package com.coveiot.android.bleabstract.response;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class DeviceInfoData {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f3592a;
    @Nullable
    public String b;
    @Nullable
    public String c;
    @Nullable
    public String d;
    @Nullable
    public String e;
    @Nullable
    public String f;
    @Nullable
    public String g;
    @Nullable
    public String h;
    @Nullable
    public Integer i;
    @Nullable
    public Integer j;
    @Nullable
    public Integer k;
    @Nullable
    public Integer l;
    @Nullable
    public String m;
    @Nullable
    public Integer n;
    @Nullable
    public Integer o;
    @Nullable
    public Boolean p;

    @Nullable
    public final String getDeviceName() {
        return this.c;
    }

    @Nullable
    public final String getFwVersion() {
        return this.e;
    }

    @Nullable
    public final String getHwVersion() {
        return this.f;
    }

    @Nullable
    public final String getMacAddress() {
        return this.h;
    }

    @Nullable
    public final String getManufacturerName() {
        return this.d;
    }

    @Nullable
    public final String getModelNo() {
        return this.b;
    }

    @Nullable
    public final Integer getPreviewRadius() {
        return this.n;
    }

    @Nullable
    public final Integer getScreenFullHeight() {
        return this.j;
    }

    @Nullable
    public final Integer getScreenFullWidth() {
        return this.i;
    }

    @Nullable
    public final Integer getScreenPreviewHeight() {
        return this.l;
    }

    @Nullable
    public final Integer getScreenPreviewWidth() {
        return this.k;
    }

    @Nullable
    public final Integer getScreenShape() {
        return this.o;
    }

    @Nullable
    public final String getSerialNo() {
        return this.f3592a;
    }

    @Nullable
    public final String getSoftwareRevision() {
        return this.g;
    }

    @Nullable
    public final String getWatchID() {
        return this.m;
    }

    @Nullable
    public final Boolean isRGBAFormatWatchScreen() {
        return this.p;
    }

    public final void setDeviceName(@Nullable String str) {
        this.c = str;
    }

    public final void setFwVersion(@Nullable String str) {
        this.e = str;
    }

    public final void setHwVersion(@Nullable String str) {
        this.f = str;
    }

    public final void setMacAddress(@Nullable String str) {
        this.h = str;
    }

    public final void setManufacturerName(@Nullable String str) {
        this.d = str;
    }

    public final void setModelNo(@Nullable String str) {
        this.b = str;
    }

    public final void setPreviewRadius(@Nullable Integer num) {
        this.n = num;
    }

    public final void setRGBAFormatWatchScreen(@Nullable Boolean bool) {
        this.p = bool;
    }

    public final void setScreenFullHeight(@Nullable Integer num) {
        this.j = num;
    }

    public final void setScreenFullWidth(@Nullable Integer num) {
        this.i = num;
    }

    public final void setScreenPreviewHeight(@Nullable Integer num) {
        this.l = num;
    }

    public final void setScreenPreviewWidth(@Nullable Integer num) {
        this.k = num;
    }

    public final void setScreenShape(@Nullable Integer num) {
        this.o = num;
    }

    public final void setSerialNo(@Nullable String str) {
        this.f3592a = str;
    }

    public final void setSoftwareRevision(@Nullable String str) {
        this.g = str;
    }

    public final void setWatchID(@Nullable String str) {
        this.m = str;
    }

    @NotNull
    public String toString() {
        return "DeviceInfoData(serialNo=" + this.f3592a + ", modelNo=" + this.b + ", deviceName=" + this.c + ", manufacturerName=" + this.d + ", fwVersion=" + this.e + ", hwVersion=" + this.f + ", softwareRevision=" + this.g + ", macAddress=" + this.h + "),screenFullWidth=" + this.i + "),screenFullHeight=" + this.j + "),screenPreviewWidth=" + this.k + "),screenPreviewHeight=" + this.l + "),watchID=" + this.m + "),previewRadius=" + this.n + "), screenShape=" + this.o + "),";
    }
}
