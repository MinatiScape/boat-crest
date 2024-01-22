package com.mappls.sdk.services.api.whoami.model;

import androidx.annotation.Keep;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes8.dex */
public class LicensingResponse {
    @SerializedName("licenseType")
    @Expose
    private String licenseType;
    @SerializedName("headers")
    @Expose
    private LicensingHeader licensingHeader;
    @SerializedName("outputParameters")
    @Expose
    private LicensingOutputParams licensingOutputParams;
    @SerializedName("parameters")
    @Expose
    private LicensingParams licensingParams;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    @Expose
    private String name;
    @SerializedName("userLoginRequired")
    @Expose
    private Boolean userLoginRequired;

    public String getLicenseType() {
        return this.licenseType;
    }

    public LicensingHeader getLicensingHeader() {
        return this.licensingHeader;
    }

    public LicensingOutputParams getLicensingOutputParams() {
        return this.licensingOutputParams;
    }

    public LicensingParams getLicensingParams() {
        return this.licensingParams;
    }

    public String getName() {
        return this.name;
    }

    public Boolean isUserLoginRequired() {
        return this.userLoginRequired;
    }

    public void setLicenseType(String str) {
        this.licenseType = str;
    }

    public void setLicensingHeader(LicensingHeader licensingHeader) {
        this.licensingHeader = licensingHeader;
    }

    public void setLicensingOutputParams(LicensingOutputParams licensingOutputParams) {
        this.licensingOutputParams = licensingOutputParams;
    }

    public void setLicensingParams(LicensingParams licensingParams) {
        this.licensingParams = licensingParams;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setUserLoginRequired(Boolean bool) {
        this.userLoginRequired = bool;
    }
}
