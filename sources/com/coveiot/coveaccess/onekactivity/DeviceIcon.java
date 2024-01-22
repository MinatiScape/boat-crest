package com.coveiot.coveaccess.onekactivity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import org.jose4j.jwk.JsonWebKey;
/* loaded from: classes8.dex */
public class DeviceIcon {
    @SerializedName("type")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6672a;
    @SerializedName("refId")
    @Expose
    private Integer b;
    @SerializedName("url")
    @Expose
    private String c;
    @SerializedName(JsonWebKey.USE_PARAMETER)
    @Expose
    private String d;
    @SerializedName("size")
    @Expose
    private List<Integer> e = null;
    @SerializedName("transparent")
    @Expose
    private Boolean f;
    @SerializedName("compressed")
    @Expose
    private Boolean g;

    public Boolean getCompressed() {
        return this.g;
    }

    public Integer getRefId() {
        return this.b;
    }

    public List<Integer> getSize() {
        return this.e;
    }

    public Boolean getTransparent() {
        return this.f;
    }

    public String getType() {
        return this.f6672a;
    }

    public String getUrl() {
        return this.c;
    }

    public String getUse() {
        return this.d;
    }

    public void setCompressed(Boolean bool) {
        this.g = bool;
    }

    public void setRefId(Integer num) {
        this.b = num;
    }

    public void setSize(List<Integer> list) {
        this.e = list;
    }

    public void setTransparent(Boolean bool) {
        this.f = bool;
    }

    public void setType(String str) {
        this.f6672a = str;
    }

    public void setUrl(String str) {
        this.c = str;
    }

    public void setUse(String str) {
        this.d = str;
    }
}
