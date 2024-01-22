package com.abupdate.http_libs.data;

import java.io.Serializable;
/* loaded from: classes.dex */
public class c implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    private final String f1869a;
    private final String b;

    public c(String str, String str2) {
        this.f1869a = str;
        this.b = str2;
    }

    public String toString() {
        return String.format("%-20s", this.f1869a) + "=  " + this.b;
    }
}
