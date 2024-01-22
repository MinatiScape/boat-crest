package com.coveiot.coveaccess.diagnostics.model;
/* loaded from: classes8.dex */
public enum ResultType {
    PASSED("PASSED"),
    FAILED("FAILED");
    
    private String resultType;

    ResultType(String str) {
        this.resultType = str;
    }

    public String getResultType() {
        return this.resultType;
    }
}
