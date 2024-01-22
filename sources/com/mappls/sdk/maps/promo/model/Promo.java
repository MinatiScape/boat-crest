package com.mappls.sdk.maps.promo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
/* loaded from: classes11.dex */
public class Promo {
    @SerializedName("promoType")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f12822a;
    @SerializedName(CMSAttributeTableGenerator.CONTENT_TYPE)
    @Expose
    private String b;
    @SerializedName("content")
    @Expose
    private Object c;

    public Object getContent() {
        return this.c;
    }

    public String getContentType() {
        return this.b;
    }

    public String getPromoType() {
        return this.f12822a;
    }

    public void setContent(Object obj) {
        this.c = obj;
    }

    public void setContentType(String str) {
        this.b = str;
    }

    public void setPromoType(String str) {
        this.f12822a = str;
    }
}
