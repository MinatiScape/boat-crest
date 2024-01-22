package com.mappls.sdk.services.api.event.route.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Keep;
import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.BleConst;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import com.mappls.sdk.services.utils.MapplsUtils;
@Keep
/* loaded from: classes4.dex */
public class ReportDetails implements Parcelable {
    public static final Parcelable.Creator<ReportDetails> CREATOR = new a();
    @SerializedName("addedBy")
    @Expose
    private String addedBy;
    @SerializedName("addedByName")
    @Expose
    private String addedByName;
    @SerializedName(alternate = {AppMeasurementSdk.ConditionalUserProperty.NAME}, value = "address")
    @Expose
    private String address;
    @SerializedName("childCategory")
    @Expose
    private String childCategory;
    @SerializedName("childCategoryId")
    @Expose
    private Integer childCategoryId;
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
    @SerializedName("nodeIdx")
    @Expose
    private Long nodeIdx;
    @SerializedName("parentCategory")
    @Expose
    private String parentCategory;
    @SerializedName("parentCategoryId")
    @Expose
    private Integer parentCategoryId;
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
    private Long usersCount;

    /* loaded from: classes4.dex */
    public static class a implements Parcelable.Creator<ReportDetails> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public ReportDetails createFromParcel(Parcel parcel) {
            return new ReportDetails(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public ReportDetails[] newArray(int i) {
            return new ReportDetails[i];
        }
    }

    public ReportDetails() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        Double d;
        Double d2;
        String str11;
        String str12;
        Long l;
        Long l2;
        String str13;
        String str14;
        String str15;
        String str16;
        String str17;
        String str18;
        Long l3;
        Long l4;
        Long l5;
        Long l6;
        Double d3;
        Double d4;
        if (obj == this) {
            return true;
        }
        if (obj instanceof ReportDetails) {
            ReportDetails reportDetails = (ReportDetails) obj;
            String str19 = this.childCategory;
            String str20 = reportDetails.childCategory;
            if ((str19 == str20 || (str19 != null && str19.equals(str20))) && (((str = this.address) == (str2 = reportDetails.address) || (str != null && str.equals(str2))) && (((str3 = this.iconBaseUrl) == (str4 = reportDetails.iconBaseUrl) || (str3 != null && str3.equals(str4))) && (((str5 = this.userProfileIcon) == (str6 = reportDetails.userProfileIcon) || (str5 != null && str5.equals(str6))) && (((str7 = this.addedByName) == (str8 = reportDetails.addedByName) || (str7 != null && str7.equals(str8))) && (((str9 = this.addedBy) == (str10 = reportDetails.addedBy) || (str9 != null && str9.equals(str10))) && (((d = this.latitude) == (d2 = reportDetails.latitude) || (d != null && d.equals(d2))) && (((str11 = this.description) == (str12 = reportDetails.description) || (str11 != null && str11.equals(str12))) && (((l = this.usersCount) == (l2 = reportDetails.usersCount) || (l != null && l.equals(l2))) && (((str13 = this.reportIcon) == (str14 = reportDetails.reportIcon) || (str13 != null && str13.equals(str14))) && (((str15 = this.parentCategory) == (str16 = reportDetails.parentCategory) || (str15 != null && str15.equals(str16))) && (((str17 = this.id) == (str18 = reportDetails.id) || (str17 != null && str17.equals(str18))) && (((l3 = this.expiry) == (l4 = reportDetails.expiry) || (l3 != null && l3.equals(l4))) && (((l5 = this.nodeIdx) == (l6 = reportDetails.nodeIdx) || (l5 != null && l5.equals(l6))) && ((d3 = this.longitude) == (d4 = reportDetails.longitude) || (d3 != null && d3.equals(d4))))))))))))))))) {
                String str21 = this.status;
                String str22 = reportDetails.status;
                if (str21 == str22) {
                    return true;
                }
                if (str21 != null && str21.equals(str22)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public String getAddedBy() {
        return this.addedBy;
    }

    public String getAddedByName() {
        return this.addedByName;
    }

    public String getAddress() {
        return this.address;
    }

    public String getChildCategory() {
        return this.childCategory;
    }

    public Integer getChildCategoryId() {
        return this.childCategoryId;
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

    public Long getNodeIdx() {
        return this.nodeIdx;
    }

    public String getParentCategory() {
        return this.parentCategory;
    }

    public Integer getParentCategoryId() {
        return this.parentCategoryId;
    }

    public String getParentReportIcon(String str) {
        String str2 = this.iconBaseUrl;
        if (str2 == null) {
            str2 = "";
        }
        Uri.Builder appendPath = Uri.parse(str2).buildUpon().appendPath(BleConst.GetDeviceTime).appendPath(Constants.KEY_ANDROID).appendPath(str);
        Uri.Builder appendPath2 = appendPath.appendPath("drawable-" + MapplsUtils.getDensityName());
        return appendPath2.appendPath(this.parentCategoryId + ".png").build().toString();
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

    public Long getUsersCount() {
        return this.usersCount;
    }

    public int hashCode() {
        String str = this.childCategory;
        int hashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
        String str2 = this.address;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.iconBaseUrl;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.userProfileIcon;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.addedByName;
        int hashCode5 = (hashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.addedBy;
        int hashCode6 = (hashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        Double d = this.latitude;
        int hashCode7 = (hashCode6 + (d == null ? 0 : d.hashCode())) * 31;
        String str7 = this.description;
        int hashCode8 = (hashCode7 + (str7 == null ? 0 : str7.hashCode())) * 31;
        Long l = this.usersCount;
        int hashCode9 = (hashCode8 + (l == null ? 0 : l.hashCode())) * 31;
        String str8 = this.reportIcon;
        int hashCode10 = (hashCode9 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.parentCategory;
        int hashCode11 = (hashCode10 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.id;
        int hashCode12 = (hashCode11 + (str10 == null ? 0 : str10.hashCode())) * 31;
        Long l2 = this.expiry;
        int hashCode13 = (hashCode12 + (l2 == null ? 0 : l2.hashCode())) * 31;
        Long l3 = this.nodeIdx;
        int hashCode14 = (hashCode13 + (l3 == null ? 0 : l3.hashCode())) * 31;
        Double d2 = this.longitude;
        int hashCode15 = (hashCode14 + (d2 == null ? 0 : d2.hashCode())) * 31;
        String str11 = this.status;
        int hashCode16 = (hashCode15 + (str11 == null ? 0 : str11.hashCode())) * 31;
        Integer num = this.parentCategoryId;
        int hashCode17 = (hashCode16 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.childCategoryId;
        return hashCode17 + (num2 != null ? num2.hashCode() : 0);
    }

    public void readFromParcel(Parcel parcel) {
        this.id = parcel.readString();
        this.parentCategory = parcel.readString();
        this.childCategory = parcel.readString();
        this.address = parcel.readString();
        this.latitude = (Double) parcel.readValue(Double.class.getClassLoader());
        this.longitude = (Double) parcel.readValue(Double.class.getClassLoader());
        this.nodeIdx = (Long) parcel.readValue(Long.class.getClassLoader());
        this.status = parcel.readString();
        this.reportIcon = parcel.readString();
        this.userProfileIcon = parcel.readString();
        this.addedByName = parcel.readString();
        this.addedBy = parcel.readString();
        this.expiry = (Long) parcel.readValue(Long.class.getClassLoader());
        this.usersCount = (Long) parcel.readValue(Long.class.getClassLoader());
        this.description = parcel.readString();
        this.iconBaseUrl = parcel.readString();
        this.parentCategoryId = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.childCategoryId = (Integer) parcel.readValue(Integer.class.getClassLoader());
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

    public void setChildCategory(String str) {
        this.childCategory = str;
    }

    public void setChildCategoryId(Integer num) {
        this.childCategoryId = num;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setExpiry(Long l) {
        this.expiry = l;
    }

    public void setIconBaseUrl(String str) {
        this.iconBaseUrl = str;
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

    public void setNodeIdx(Long l) {
        this.nodeIdx = l;
    }

    public void setParentCategory(String str) {
        this.parentCategory = str;
    }

    public void setParentCategoryId(Integer num) {
        this.parentCategoryId = num;
    }

    public void setReportIcon(String str) {
        this.reportIcon = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setUserProfileIcon(String str) {
        this.userProfileIcon = str;
    }

    public void setUsersCount(Long l) {
        this.usersCount = l;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ReportDetails.class.getName());
        sb.append('@');
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append('[');
        sb.append("id");
        sb.append('=');
        String str = this.id;
        if (str == null) {
            str = "<null>";
        }
        sb.append(str);
        sb.append(',');
        sb.append("parentCategory");
        sb.append('=');
        String str2 = this.parentCategory;
        if (str2 == null) {
            str2 = "<null>";
        }
        sb.append(str2);
        sb.append(',');
        sb.append("childCategory");
        sb.append('=');
        String str3 = this.childCategory;
        if (str3 == null) {
            str3 = "<null>";
        }
        sb.append(str3);
        sb.append(',');
        sb.append("address");
        sb.append('=');
        String str4 = this.address;
        if (str4 == null) {
            str4 = "<null>";
        }
        sb.append(str4);
        sb.append(',');
        sb.append("latitude");
        sb.append('=');
        Double d = this.latitude;
        if (d == null) {
            d = "<null>";
        }
        sb.append(d);
        sb.append(',');
        sb.append("longitude");
        sb.append('=');
        Double d2 = this.longitude;
        if (d2 == null) {
            d2 = "<null>";
        }
        sb.append(d2);
        sb.append(',');
        sb.append("nodeIdx");
        sb.append('=');
        Long l = this.nodeIdx;
        if (l == null) {
            l = "<null>";
        }
        sb.append(l);
        sb.append(',');
        sb.append(NotificationCompat.CATEGORY_STATUS);
        sb.append('=');
        String str5 = this.status;
        if (str5 == null) {
            str5 = "<null>";
        }
        sb.append(str5);
        sb.append(',');
        sb.append("reportIcon");
        sb.append('=');
        String str6 = this.reportIcon;
        if (str6 == null) {
            str6 = "<null>";
        }
        sb.append(str6);
        sb.append(',');
        sb.append("userProfileIcon");
        sb.append('=');
        String str7 = this.userProfileIcon;
        if (str7 == null) {
            str7 = "<null>";
        }
        sb.append(str7);
        sb.append(',');
        sb.append("addedByName");
        sb.append('=');
        String str8 = this.addedByName;
        if (str8 == null) {
            str8 = "<null>";
        }
        sb.append(str8);
        sb.append(',');
        sb.append("addedBy");
        sb.append('=');
        String str9 = this.addedBy;
        if (str9 == null) {
            str9 = "<null>";
        }
        sb.append(str9);
        sb.append(',');
        sb.append("expiry");
        sb.append('=');
        Long l2 = this.expiry;
        if (l2 == null) {
            l2 = "<null>";
        }
        sb.append(l2);
        sb.append(',');
        sb.append("usersCount");
        sb.append('=');
        Long l3 = this.usersCount;
        if (l3 == null) {
            l3 = "<null>";
        }
        sb.append(l3);
        sb.append(',');
        sb.append(SavingTrackHelper.POINT_COL_DESCRIPTION);
        sb.append('=');
        String str10 = this.description;
        if (str10 == null) {
            str10 = "<null>";
        }
        sb.append(str10);
        sb.append(',');
        sb.append("iconBaseUrl");
        sb.append('=');
        String str11 = this.iconBaseUrl;
        if (str11 == null) {
            str11 = "<null>";
        }
        sb.append(str11);
        sb.append(',');
        sb.append("parentCategoryId");
        sb.append('=');
        Integer num = this.parentCategoryId;
        if (num == null) {
            num = "<null>";
        }
        sb.append(num);
        sb.append(',');
        sb.append("childCategoryId");
        sb.append('=');
        Integer num2 = this.childCategoryId;
        sb.append(num2 != null ? num2 : "<null>");
        sb.append(',');
        if (sb.charAt(sb.length() - 1) == ',') {
            sb.setCharAt(sb.length() - 1, ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.parentCategory);
        parcel.writeString(this.childCategory);
        parcel.writeString(this.address);
        parcel.writeValue(this.latitude);
        parcel.writeValue(this.longitude);
        parcel.writeValue(this.nodeIdx);
        parcel.writeString(this.status);
        parcel.writeString(this.reportIcon);
        parcel.writeString(this.userProfileIcon);
        parcel.writeString(this.addedByName);
        parcel.writeString(this.addedBy);
        parcel.writeValue(this.expiry);
        parcel.writeValue(this.usersCount);
        parcel.writeString(this.description);
        parcel.writeString(this.iconBaseUrl);
        parcel.writeValue(this.parentCategoryId);
        parcel.writeValue(this.childCategoryId);
    }

    public ReportDetails(Parcel parcel) {
        this.id = parcel.readString();
        this.parentCategory = parcel.readString();
        this.childCategory = parcel.readString();
        this.address = parcel.readString();
        this.latitude = (Double) parcel.readValue(Double.class.getClassLoader());
        this.longitude = (Double) parcel.readValue(Double.class.getClassLoader());
        this.nodeIdx = (Long) parcel.readValue(Long.class.getClassLoader());
        this.status = parcel.readString();
        this.reportIcon = parcel.readString();
        this.userProfileIcon = parcel.readString();
        this.addedByName = parcel.readString();
        this.addedBy = parcel.readString();
        this.expiry = (Long) parcel.readValue(Long.class.getClassLoader());
        this.usersCount = (Long) parcel.readValue(Long.class.getClassLoader());
        this.description = parcel.readString();
        this.iconBaseUrl = parcel.readString();
        this.parentCategoryId = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.childCategoryId = (Integer) parcel.readValue(Integer.class.getClassLoader());
    }

    public String getParentReportIcon(String str, int i) {
        String str2 = this.iconBaseUrl;
        if (str2 == null) {
            str2 = "";
        }
        Uri.Builder buildUpon = Uri.parse(str2).buildUpon();
        Uri.Builder appendPath = buildUpon.appendPath("" + i).appendPath(Constants.KEY_ANDROID).appendPath(str);
        Uri.Builder appendPath2 = appendPath.appendPath("drawable-" + MapplsUtils.getDensityName());
        return appendPath2.appendPath(this.parentCategoryId + ".png").build().toString();
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
