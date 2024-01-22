package com.coveiot.android.activitymodes.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes2.dex */
public class CustomMessageConfiguration {
    @SerializedName("supported1kCategoriesForCustomMessage")

    /* renamed from: a  reason: collision with root package name */
    private List<Integer> f2849a;
    @SerializedName("filterBy")
    private String b;
    @SerializedName("supported1kActivityCodeForCustomMessage")
    private List<String> c;

    public String getFilterBy() {
        return this.b;
    }

    public List<String> getSupported1kActivityCodeForCustomMessage() {
        return this.c;
    }

    public List<Integer> getSupported1kCategoriesForCustomMessage() {
        return this.f2849a;
    }

    public void setFilterBy(String str) {
        this.b = str;
    }

    public void setSupported1kActivityCodeForCustomMessage(List<String> list) {
        this.c = list;
    }

    public void setSupported1kCategoriesForCustomMessage(List<Integer> list) {
        this.f2849a = list;
    }
}
