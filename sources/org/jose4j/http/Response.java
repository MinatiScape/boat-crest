package org.jose4j.http;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes13.dex */
public class Response implements SimpleResponse {

    /* renamed from: a  reason: collision with root package name */
    public int f15499a;
    public String b;
    public Map<String, List<String>> c = new HashMap();
    public String d;

    public Response(int i, String str, Map<String, List<String>> map, String str2) {
        this.f15499a = i;
        this.b = str;
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            this.c.put(a(entry.getKey()), entry.getValue());
        }
        this.d = str2;
    }

    public final String a(String str) {
        if (str != null) {
            return str.toLowerCase().trim();
        }
        return null;
    }

    @Override // org.jose4j.http.SimpleResponse
    public String getBody() {
        return this.d;
    }

    @Override // org.jose4j.http.SimpleResponse
    public Collection<String> getHeaderNames() {
        return this.c.keySet();
    }

    @Override // org.jose4j.http.SimpleResponse
    public List<String> getHeaderValues(String str) {
        return this.c.get(a(str));
    }

    @Override // org.jose4j.http.SimpleResponse
    public int getStatusCode() {
        return this.f15499a;
    }

    @Override // org.jose4j.http.SimpleResponse
    public String getStatusMessage() {
        return this.b;
    }

    public String toString() {
        return "SimpleResponse{statusCode=" + this.f15499a + ", statusMessage='" + this.b + "', headers=" + this.c + ", body='" + this.d + "'}";
    }
}
