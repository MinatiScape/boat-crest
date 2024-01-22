package com.clevertap.android.sdk;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Arrays;
/* loaded from: classes2.dex */
public class CTInboxStyleConfig implements Parcelable {
    public static final Parcelable.Creator<CTInboxStyleConfig> CREATOR = new a();
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public String n;
    public String o;
    public String p;
    public String q;
    public String r;
    public String[] s;
    public String t;

    /* loaded from: classes2.dex */
    public class a implements Parcelable.Creator<CTInboxStyleConfig> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public CTInboxStyleConfig createFromParcel(Parcel parcel) {
            return new CTInboxStyleConfig(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public CTInboxStyleConfig[] newArray(int i) {
            return new CTInboxStyleConfig[i];
        }
    }

    public CTInboxStyleConfig() {
        this.k = Constants.WHITE;
        this.l = "App Inbox";
        this.m = "#333333";
        this.j = "#D3D4DA";
        this.h = "#333333";
        this.p = "#1C84FE";
        this.t = "#808080";
        this.q = "#1C84FE";
        this.r = Constants.WHITE;
        this.s = new String[0];
        this.n = "No Message(s) to show";
        this.o = Constants.BLACK;
        this.i = "ALL";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getBackButtonColor() {
        return this.h;
    }

    public String getFirstTabTitle() {
        return this.i;
    }

    public String getInboxBackgroundColor() {
        return this.j;
    }

    public String getNavBarColor() {
        return this.k;
    }

    public String getNavBarTitle() {
        return this.l;
    }

    public String getNavBarTitleColor() {
        return this.m;
    }

    public String getNoMessageViewText() {
        return this.n;
    }

    public String getNoMessageViewTextColor() {
        return this.o;
    }

    public String getSelectedTabColor() {
        return this.p;
    }

    public String getSelectedTabIndicatorColor() {
        return this.q;
    }

    public String getTabBackgroundColor() {
        return this.r;
    }

    public ArrayList<String> getTabs() {
        return this.s == null ? new ArrayList<>() : new ArrayList<>(Arrays.asList(this.s));
    }

    public String getUnselectedTabColor() {
        return this.t;
    }

    public boolean isUsingTabs() {
        String[] strArr = this.s;
        return strArr != null && strArr.length > 0;
    }

    public void setBackButtonColor(String str) {
        this.h = str;
    }

    public void setFirstTabTitle(String str) {
        this.i = str;
    }

    public void setInboxBackgroundColor(String str) {
        this.j = str;
    }

    public void setNavBarColor(String str) {
        this.k = str;
    }

    public void setNavBarTitle(String str) {
        this.l = str;
    }

    public void setNavBarTitleColor(String str) {
        this.m = str;
    }

    public void setNoMessageViewText(String str) {
        this.n = str;
    }

    public void setNoMessageViewTextColor(String str) {
        this.o = str;
    }

    public void setSelectedTabColor(String str) {
        this.p = str;
    }

    public void setSelectedTabIndicatorColor(String str) {
        this.q = str;
    }

    public void setTabBackgroundColor(String str) {
        this.r = str;
    }

    public void setTabs(ArrayList<String> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        if (arrayList.size() > 2) {
            arrayList = new ArrayList<>(arrayList.subList(0, 2));
        }
        this.s = (String[]) arrayList.toArray(new String[0]);
    }

    public void setUnselectedTabColor(String str) {
        this.t = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.k);
        parcel.writeString(this.l);
        parcel.writeString(this.m);
        parcel.writeString(this.j);
        parcel.writeStringArray(this.s);
        parcel.writeString(this.h);
        parcel.writeString(this.p);
        parcel.writeString(this.t);
        parcel.writeString(this.q);
        parcel.writeString(this.r);
        parcel.writeString(this.n);
        parcel.writeString(this.o);
        parcel.writeString(this.i);
    }

    public CTInboxStyleConfig(CTInboxStyleConfig cTInboxStyleConfig) {
        this.k = cTInboxStyleConfig.k;
        this.l = cTInboxStyleConfig.l;
        this.m = cTInboxStyleConfig.m;
        this.j = cTInboxStyleConfig.j;
        this.h = cTInboxStyleConfig.h;
        this.p = cTInboxStyleConfig.p;
        this.t = cTInboxStyleConfig.t;
        this.q = cTInboxStyleConfig.q;
        this.r = cTInboxStyleConfig.r;
        String[] strArr = cTInboxStyleConfig.s;
        this.s = strArr == null ? new String[0] : (String[]) Arrays.copyOf(strArr, strArr.length);
        this.n = cTInboxStyleConfig.n;
        this.o = cTInboxStyleConfig.o;
        this.i = cTInboxStyleConfig.i;
    }

    public CTInboxStyleConfig(Parcel parcel) {
        this.k = parcel.readString();
        this.l = parcel.readString();
        this.m = parcel.readString();
        this.j = parcel.readString();
        this.s = parcel.createStringArray();
        this.h = parcel.readString();
        this.p = parcel.readString();
        this.t = parcel.readString();
        this.q = parcel.readString();
        this.r = parcel.readString();
        this.n = parcel.readString();
        this.o = parcel.readString();
        this.i = parcel.readString();
    }
}
