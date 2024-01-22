package com.coveiot.coveaccess.sleepscore.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.maps.style.layers.Property;
/* loaded from: classes8.dex */
public class UserInfo {
    @SerializedName("age")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private Integer f6719a;
    @SerializedName("gender")
    @Expose
    private String b;
    @SerializedName(Property.ICON_TEXT_FIT_HEIGHT)
    @Expose
    private Integer c;
    @SerializedName("weight")
    @Expose
    private Integer d;

    public Integer getAge() {
        return this.f6719a;
    }

    public String getGender() {
        return this.b;
    }

    public Integer getHeight() {
        return this.c;
    }

    public Integer getWeight() {
        return this.d;
    }

    public void setAge(Integer num) {
        this.f6719a = num;
    }

    public void setGender(String str) {
        this.b = str;
    }

    public void setHeight(Integer num) {
        this.c = num;
    }

    public void setWeight(Integer num) {
        this.d = num;
    }
}
