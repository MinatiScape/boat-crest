package com.mappls.sdk.services.api.event.nearby.model;

import android.net.Uri;
import androidx.annotation.Keep;
import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.BleConst;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import com.mappls.sdk.services.utils.MapplsUtils;
@Keep
/* loaded from: classes4.dex */
public class NearbyReport {
    @SerializedName("addedBy")
    @Expose
    private String addedBy;
    @SerializedName("addedByName")
    @Expose
    private String addedByName;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("audiosCount")
    @Expose
    private Integer audiosCount;
    @SerializedName("bearing")
    @Expose
    private Double bearing;
    @SerializedName(SavingTrackHelper.POINT_COL_CATEGORY)
    @Expose
    private String category;
    @SerializedName("childCategory")
    @Expose
    private String childCategory;
    @SerializedName("createdOn")
    @Expose
    private Long createdOn;
    @SerializedName(SavingTrackHelper.POINT_COL_DESCRIPTION)
    @Expose
    private String description;
    @SerializedName("expiry")
    @Expose
    private Long expiry;
    @SerializedName("iconBaseUrl")
    @Expose
    private String iconBaseUrl;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("parentCategory")
    @Expose
    private String parentCategory;
    @SerializedName("picturesCount")
    @Expose
    private Integer picturesCount;
    @SerializedName("reportIcon")
    @Expose
    private String reportIcon;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;
    @SerializedName("userProfileIcon")
    @Expose
    private String userProfileIcon;
    @SerializedName("usersCount")
    @Expose
    private Integer usersCount;
    @SerializedName("videosCount")
    @Expose
    private Integer videosCount;

    public String getAddedBy() {
        return this.addedBy;
    }

    public String getAddedByName() {
        return this.addedByName;
    }

    public String getAddress() {
        return this.address;
    }

    public Integer getAudiosCount() {
        return this.audiosCount;
    }

    public Double getBearing() {
        return this.bearing;
    }

    public String getCategory() {
        return this.category;
    }

    public String getChildCategory() {
        return this.childCategory;
    }

    public Long getCreatedOn() {
        return this.createdOn;
    }

    public String getDescription() {
        return this.description;
    }

    public Long getExpiry() {
        return this.expiry;
    }

    public String getId() {
        return this.id;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public String getParentCategory() {
        return this.parentCategory;
    }

    public Integer getPicturesCount() {
        return this.picturesCount;
    }

    public String getReportIcon(String str) {
        String str2 = this.iconBaseUrl;
        if (str2 == null) {
            str2 = "";
        }
        Uri.Builder appendPath = Uri.parse(str2).buildUpon().appendPath(BleConst.GetDeviceTime).appendPath(Constants.KEY_ANDROID).appendPath(str);
        return appendPath.appendPath("drawable-" + MapplsUtils.getDensityName()).appendPath(this.reportIcon).build().toString();
    }

    public String getStatus() {
        return this.status;
    }

    public String getUserProfileIcon() {
        return this.userProfileIcon;
    }

    public Integer getUsersCount() {
        return this.usersCount;
    }

    public Integer getVideosCount() {
        return this.videosCount;
    }

    public void setAddedBy(String str) {
        this.addedBy = str;
    }

    public void setAddedByName(String str) {
        this.addedByName = str;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setAudiosCount(Integer num) {
        this.audiosCount = num;
    }

    public void setBearing(Double d) {
        this.bearing = d;
    }

    public void setCategory(String str) {
        this.category = str;
    }

    public void setChildCategory(String str) {
        this.childCategory = str;
    }

    public void setCreatedOn(Long l) {
        this.createdOn = l;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setExpiry(Long l) {
        this.expiry = l;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setLatitude(Double d) {
        this.latitude = d;
    }

    public void setLongitude(Double d) {
        this.longitude = d;
    }

    public void setParentCategory(String str) {
        this.parentCategory = str;
    }

    public void setPicturesCount(Integer num) {
        this.picturesCount = num;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setUserProfileIcon(String str) {
        this.userProfileIcon = str;
    }

    public void setUsersCount(Integer num) {
        this.usersCount = num;
    }

    public void setVideosCount(Integer num) {
        this.videosCount = num;
    }

    public String getReportIcon(String str, int i) {
        String str2 = this.iconBaseUrl;
        if (str2 == null) {
            str2 = "";
        }
        Uri.Builder buildUpon = Uri.parse(str2).buildUpon();
        Uri.Builder appendPath = buildUpon.appendPath("" + i).appendPath(Constants.KEY_ANDROID).appendPath(str);
        return appendPath.appendPath("drawable-" + MapplsUtils.getDensityName()).appendPath(this.reportIcon).build().toString();
    }
}
