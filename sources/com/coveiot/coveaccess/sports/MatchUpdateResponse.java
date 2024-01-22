package com.coveiot.coveaccess.sports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class MatchUpdateResponse {
    @SerializedName("ctx")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6744a;
    @SerializedName("matchId")
    @Expose
    private Integer b;

    public String getCtx() {
        return this.f6744a;
    }

    public Integer getMatchId() {
        return this.b;
    }

    public void setCtx(String str) {
        this.f6744a = str;
    }

    public void setMatchId(Integer num) {
        this.b = num;
    }
}
