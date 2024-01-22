package com.coveiot.coveaccess.respiratoryrate;

import com.coveiot.coveaccess.respiratoryrate.model.PPGRecord;
import com.coveiot.coveaccess.respiratoryrate.model.UserInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public class RespiratoryRateApiReq {
    @SerializedName("userInfo")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private UserInfo f6691a;
    @SerializedName("deviceType")
    @Expose
    private String b;
    @SerializedName("ppgRecords")
    @Expose
    private List<PPGRecord> c = new ArrayList();

    public String getDeviceType() {
        return this.b;
    }

    public List<PPGRecord> getPpgRecords() {
        return this.c;
    }

    public UserInfo getUserInfo() {
        return this.f6691a;
    }

    public void setDeviceType(String str) {
        this.b = str;
    }

    public void setPpgRecords(ArrayList<PPGRecord> arrayList) {
        this.c = arrayList;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.f6691a = userInfo;
    }
}
