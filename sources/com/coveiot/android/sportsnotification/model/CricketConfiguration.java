package com.coveiot.android.sportsnotification.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes7.dex */
public class CricketConfiguration {
    @SerializedName("supportedMatchFormats")

    /* renamed from: a  reason: collision with root package name */
    private List<Integer> f5869a;
    @SerializedName("supportedTeams")
    private List<String> b;
    @SerializedName("supportedDomesticMatches")
    private List<String> c;

    public List<String> getSupportedDomesticMatches() {
        return this.c;
    }

    public List<Integer> getSupportedMatchFormats() {
        return this.f5869a;
    }

    public List<String> getSupportedTeams() {
        return this.b;
    }

    public void setSupportedDomesticMatches(List<String> list) {
        this.c = list;
    }

    public void setSupportedMatchFormats(List<Integer> list) {
        this.f5869a = list;
    }

    public void setSupportedTeams(List<String> list) {
        this.b = list;
    }
}
