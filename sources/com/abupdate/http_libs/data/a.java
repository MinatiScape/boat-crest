package com.abupdate.http_libs.data;

import com.android.volley.toolbox.HttpClientStack;
/* loaded from: classes.dex */
public enum a {
    Get("GET"),
    Head("HEAD"),
    Trace("TRACE"),
    Options("OPTIONS"),
    Delete("DELETE"),
    Put("PUT"),
    Post("POST"),
    Patch(HttpClientStack.HttpPatch.METHOD_NAME);
    
    private String i;

    a(String str) {
        this.i = str;
    }

    public String a() {
        return this.i;
    }
}
