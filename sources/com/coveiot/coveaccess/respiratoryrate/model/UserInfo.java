package com.coveiot.coveaccess.respiratoryrate.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.maps.style.layers.Property;
/* loaded from: classes8.dex */
public class UserInfo {
    @SerializedName(Property.ICON_TEXT_FIT_HEIGHT)
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private Integer f6696a;
    @SerializedName("weight")
    @Expose
    private Integer b;
    @SerializedName("gender")
    @Expose
    private String c;
    @SerializedName("age")
    @Expose
    private Integer d;

    public Integer getAge() {
        return this.d;
    }

    public String getGender() {
        return this.c;
    }

    public Integer getHeight() {
        return this.f6696a;
    }

    public Integer getWeight() {
        return this.b;
    }

    public void setAge(Integer num) {
        this.d = num;
    }

    public void setGender(String str) {
        this.c = str;
    }

    public void setHeight(Integer num) {
        this.f6696a = num;
    }

    public void setWeight(Integer num) {
        this.b = num;
    }
}
