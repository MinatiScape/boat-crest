package com.coveiot.coveaccess.diagnostics.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class DiagnosticResultRequest {
    @SerializedName("tzOffset")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6477a;
    @SerializedName("device")
    @Expose
    private Device b;
    @SerializedName("testSummary")
    @Expose
    private TestSummary c;

    public Device getDevice() {
        return this.b;
    }

    public TestSummary getTestSummary() {
        return this.c;
    }

    public String getTzOffset() {
        return this.f6477a;
    }

    public void setDevice(Device device) {
        this.b = device;
    }

    public void setTestSummary(TestSummary testSummary) {
        this.c = testSummary;
    }

    public void setTzOffset(String str) {
        this.f6477a = str;
    }
}
