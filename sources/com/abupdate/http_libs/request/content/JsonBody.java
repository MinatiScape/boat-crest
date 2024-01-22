package com.abupdate.http_libs.request.content;
/* loaded from: classes.dex */
public class JsonBody extends StringBody {
    public JsonBody(String str) {
        this(str, "UTF-8");
    }

    public JsonBody(String str, String str2) {
        super(str, "application/json", str2);
    }

    @Override // com.abupdate.http_libs.request.content.StringBody
    public String toString() {
        return "JsonBody{} " + super.toString();
    }
}
