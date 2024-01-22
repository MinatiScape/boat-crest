package com.mappls.sdk.maps;

import androidx.annotation.Keep;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
@Keep
/* loaded from: classes11.dex */
class StyleData {
    @SerializedName(SavingTrackHelper.POINT_COL_DESCRIPTION)
    @Expose
    private String description;
    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("logoId")
    @Expose
    private String globalLogoId;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("isDefault")
    @Expose
    private Integer isDefault;
    @SerializedName("logoId_india")
    @Expose
    private String logoIdIndia;
    @SerializedName("modified")
    @Expose
    private Integer modified;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    @Expose
    private String name;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("styleUrl")
    @Expose
    private String styleUrl;
    @SerializedName("visibility")
    @Expose
    private String visibility;

    public String getDescription() {
        return this.description;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getGlobalLogoId() {
        return this.globalLogoId;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getLogoIdIndia() {
        return this.logoIdIndia;
    }

    public Integer getModified() {
        return this.modified;
    }

    public String getName() {
        return this.name;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getStyleUrl() {
        return this.styleUrl;
    }

    public String getVisibility() {
        return this.visibility;
    }

    public Integer isDefault() {
        return this.isDefault;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setDisplayName(String str) {
        this.displayName = str;
    }

    public void setGlobalLogoId(String str) {
        this.globalLogoId = str;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public void setIsDefault(Integer num) {
        this.isDefault = num;
    }

    public void setLogoIdIndia(String str) {
        this.logoIdIndia = str;
    }

    public void setModified(Integer num) {
        this.modified = num;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setOwner(String str) {
        this.owner = str;
    }

    public void setStyleUrl(String str) {
        this.styleUrl = str;
    }

    public void setVisibility(String str) {
        this.visibility = str;
    }
}
