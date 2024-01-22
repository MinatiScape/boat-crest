package com.mappls.sdk.maps.covid;

import androidx.annotation.Keep;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.messaging.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.geocoding.GeoCodingCriteria;
import com.szabh.smable3.entity.BleNotificationSettings;
@Keep
/* loaded from: classes11.dex */
public class InteractiveItemDetails {
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("alias_1")
    @Expose
    private String alias1;
    @SerializedName("alias_2")
    @Expose
    private String alias2;
    @SerializedName("alias_3")
    @Expose
    private String alias3;
    @SerializedName("awareness_")
    @Expose
    private String awareness;
    @SerializedName("branch_nme")
    @Expose
    private String branchName;
    @SerializedName("childcateg")
    @Expose
    private Integer childCategory;
    @SerializedName("city_nme")
    @Expose
    private String cityName;
    @SerializedName("conf_forgn")
    @Expose
    private Integer confForgn;
    @SerializedName("conf_ind")
    @Expose
    private Integer confInd;
    @SerializedName("cured")
    @Expose
    private Integer cured;
    @SerializedName("death")
    @Expose
    private Integer death;
    @SerializedName("descriptio")
    @Expose
    private String description;
    @SerializedName("dst_id")
    @Expose
    private String districtId;
    @SerializedName("dst_nme")
    @Expose
    private String districtName;
    @SerializedName("em_tel")
    @Expose
    private String emTel;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("expiry")
    @Expose
    private String expiry;
    @SerializedName("fax")
    @Expose
    private String fax;
    @SerializedName("free_food")
    @Expose
    private String freeFood;
    @SerializedName("ftr_cry")
    @Expose
    private String ftrCry;
    @SerializedName("helpline_n")
    @Expose
    private String helplineNumber;
    @SerializedName("hotline")
    @Expose
    private String hotline;
    @SerializedName("hotspt_rmk")
    @Expose
    private String hotspotRemark;
    @SerializedName(Constants.ScionAnalytics.PARAM_LABEL)
    @Expose
    private String label;
    @SerializedName("last_updated_on")
    @Expose
    private String lastUpdatedOn;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName(alternate = {"location_n"}, value = "loc_nme")
    @Expose
    private String locationName;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("eloc")
    @Expose
    private String mapplsPin;
    @SerializedName("med_assist")
    @Expose
    private String medicalAssist;
    @SerializedName("mob_tel")
    @Expose
    private String mobTel;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    @Expose
    private String name;
    @SerializedName("no_hospita")
    @Expose
    private String noHospital;
    @SerializedName("parentcate")
    @Expose
    private Integer parentCategory;
    @SerializedName(GeoCodingCriteria.POD_PINCODE)
    @Expose
    private String pincode;
    @SerializedName("placename")
    @Expose
    private String placeName;
    @SerializedName("poplr_nme")
    @Expose
    private String popularName;
    @SerializedName("relief_cam")
    @Expose
    private String reliefCamp;
    @SerializedName("sdst_nme")
    @Expose
    private String sdstNme;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("stt_id")
    @Expose
    private String stateId;
    @SerializedName("stt_nme")
    @Expose
    private String stateName;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;
    @SerializedName("subchildca")
    @Expose
    private Integer subChildcategory;
    @SerializedName("sub_cry")
    @Expose
    private String subCry;
    @SerializedName("slc_nme")
    @Expose
    private String subLocationName;
    @SerializedName("sslc_nme")
    @Expose
    private String subSubLocationName;
    @SerializedName(BleNotificationSettings.CALL)
    @Expose
    private String tel;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("web")
    @Expose
    private String web;
    @SerializedName("zone")
    @Expose
    private String zone;

    public String getAddress() {
        return this.address;
    }

    public String getAlias1() {
        return this.alias1;
    }

    public String getAlias2() {
        return this.alias2;
    }

    public String getAlias3() {
        return this.alias3;
    }

    public String getAwareness() {
        return this.awareness;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public Integer getChildCategory() {
        return this.childCategory;
    }

    public String getCityName() {
        return this.cityName;
    }

    public Integer getConfForgn() {
        return this.confForgn;
    }

    public Integer getConfInd() {
        return this.confInd;
    }

    public Integer getCured() {
        return this.cured;
    }

    public Integer getDeath() {
        return this.death;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDistrictId() {
        return this.districtId;
    }

    public String getDistrictName() {
        return this.districtName;
    }

    public String getEmTel() {
        return this.emTel;
    }

    public String getEmail() {
        return this.email;
    }

    public String getExpiry() {
        return this.expiry;
    }

    public String getFax() {
        return this.fax;
    }

    public String getFreeFood() {
        return this.freeFood;
    }

    public String getFtrCry() {
        return this.ftrCry;
    }

    public String getHelplineNumber() {
        return this.helplineNumber;
    }

    public String getHotline() {
        return this.hotline;
    }

    public String getHotspotRemark() {
        return this.hotspotRemark;
    }

    public String getLabel() {
        return this.label;
    }

    public String getLastUpdatedOn() {
        return this.lastUpdatedOn;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public String getLocationName() {
        return this.locationName;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public String getMapplsPin() {
        return this.mapplsPin;
    }

    public String getMedicalAssist() {
        return this.medicalAssist;
    }

    public String getMobTel() {
        return this.mobTel;
    }

    public String getName() {
        return this.name;
    }

    public String getNoHospital() {
        return this.noHospital;
    }

    public Integer getParentCategory() {
        return this.parentCategory;
    }

    public String getPincode() {
        return this.pincode;
    }

    public String getPlaceName() {
        return this.placeName;
    }

    public String getPopularName() {
        return this.popularName;
    }

    public String getReliefCamp() {
        return this.reliefCamp;
    }

    public String getSdstNme() {
        return this.sdstNme;
    }

    public String getSource() {
        return this.source;
    }

    public String getStateId() {
        return this.stateId;
    }

    public String getStateName() {
        return this.stateName;
    }

    public String getStatus() {
        return this.status;
    }

    public Integer getSubChildcategory() {
        return this.subChildcategory;
    }

    public String getSubCry() {
        return this.subCry;
    }

    public String getSubLocationName() {
        return this.subLocationName;
    }

    public String getSubSubLocationName() {
        return this.subSubLocationName;
    }

    public String getTel() {
        return this.tel;
    }

    public Integer getTotal() {
        return this.total;
    }

    public String getType() {
        return this.type;
    }

    public String getWeb() {
        return this.web;
    }

    public String getZone() {
        return this.zone;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setAlias1(String str) {
        this.alias1 = str;
    }

    public void setAlias2(String str) {
        this.alias2 = str;
    }

    public void setAlias3(String str) {
        this.alias3 = str;
    }

    public void setAwareness(String str) {
        this.awareness = str;
    }

    public void setBranchName(String str) {
        this.branchName = str;
    }

    public void setChildCategory(Integer num) {
        this.childCategory = num;
    }

    public void setCityName(String str) {
        this.cityName = str;
    }

    public void setConfForgn(Integer num) {
        this.confForgn = num;
    }

    public void setConfInd(Integer num) {
        this.confInd = num;
    }

    public void setCured(Integer num) {
        this.cured = num;
    }

    public void setDeath(Integer num) {
        this.death = num;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setDistrictId(String str) {
        this.districtId = str;
    }

    public void setDistrictName(String str) {
        this.districtName = str;
    }

    public void setEmTel(String str) {
        this.emTel = str;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public void setExpiry(String str) {
        this.expiry = str;
    }

    public void setFax(String str) {
        this.fax = str;
    }

    public void setFreeFood(String str) {
        this.freeFood = str;
    }

    public void setFtrCry(String str) {
        this.ftrCry = str;
    }

    public void setHelplineNumber(String str) {
        this.helplineNumber = str;
    }

    public void setHotline(String str) {
        this.hotline = str;
    }

    public void setHotspotRemark(String str) {
        this.hotspotRemark = str;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public void setLastUpdatedOn(String str) {
        this.lastUpdatedOn = str;
    }

    public void setLatitude(Double d) {
        this.latitude = d;
    }

    public void setLocationName(String str) {
        this.locationName = str;
    }

    public void setLongitude(Double d) {
        this.longitude = d;
    }

    public void setMapplsPin(String str) {
        this.mapplsPin = str;
    }

    public void setMedicalAssist(String str) {
        this.medicalAssist = str;
    }

    public void setMobTel(String str) {
        this.mobTel = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setNoHospital(String str) {
        this.noHospital = str;
    }

    public void setParentCategory(Integer num) {
        this.parentCategory = num;
    }

    public void setPincode(String str) {
        this.pincode = str;
    }

    public void setPlaceName(String str) {
        this.placeName = str;
    }

    public void setPopularName(String str) {
        this.popularName = str;
    }

    public void setReliefCamp(String str) {
        this.reliefCamp = str;
    }

    public void setSdstNme(String str) {
        this.sdstNme = str;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public void setStateId(String str) {
        this.stateId = str;
    }

    public void setStateName(String str) {
        this.stateName = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setSubChildcategory(Integer num) {
        this.subChildcategory = num;
    }

    public void setSubCry(String str) {
        this.subCry = str;
    }

    public void setSubLocationName(String str) {
        this.subLocationName = str;
    }

    public void setSubSubLocationName(String str) {
        this.subSubLocationName = str;
    }

    public void setTel(String str) {
        this.tel = str;
    }

    public void setTotal(Integer num) {
        this.total = num;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setWeb(String str) {
        this.web = str;
    }

    public void setZone(String str) {
        this.zone = str;
    }
}
