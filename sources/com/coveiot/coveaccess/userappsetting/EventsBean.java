package com.coveiot.coveaccess.userappsetting;

import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import org.bouncycastle.i18n.ErrorBundle;
/* loaded from: classes8.dex */
public class EventsBean {
    @SerializedName(ErrorBundle.SUMMARY_ENTRY)

    /* renamed from: a  reason: collision with root package name */
    private String f6810a;
    @SerializedName(SavingTrackHelper.POINT_COL_DESCRIPTION)
    private String b;
    @SerializedName("start")
    private String c;
    @SerializedName("tzOffset")
    private String d;
    @SerializedName("remindBefore")
    private Integer e;

    public String getDescription() {
        return this.b;
    }

    public Integer getRemindBefore() {
        return this.e;
    }

    public String getStart() {
        return this.c;
    }

    public String getSummary() {
        return this.f6810a;
    }

    public String getTzOffset() {
        return this.d;
    }

    public void setDescription(String str) {
        this.b = str;
    }

    public void setRemindBefore(Integer num) {
        this.e = num;
    }

    public void setStart(String str) {
        this.c = str;
    }

    public void setSummary(String str) {
        this.f6810a = str;
    }

    public void setTzOffset(String str) {
        this.d = str;
    }
}
