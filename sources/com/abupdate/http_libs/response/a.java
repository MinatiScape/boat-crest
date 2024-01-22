package com.abupdate.http_libs.response;

import com.abupdate.http_libs.data.b;
import com.abupdate.http_libs.data.c;
import com.abupdate.http_libs.request.base.Request;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes.dex */
public class a implements Response {

    /* renamed from: a  reason: collision with root package name */
    public String f1876a;
    public b b;
    public int c;
    public int d;
    public long e;
    public String f;
    public String g;
    public ArrayList<c> h;
    public Request i;
    public com.abupdate.http_libs.a.a j;
    public String k;

    public a(Request request) {
        this.i = request;
    }

    public long a(long j) {
        this.e = j;
        return j;
    }

    public void a(int i) {
        this.c = i;
    }

    public void a(com.abupdate.http_libs.a.a aVar) {
        this.j = aVar;
    }

    public void a(b bVar) {
        this.b = bVar;
    }

    public void a(String str) {
        if (str != null) {
            this.f1876a = str;
        }
    }

    public void a(ArrayList<c> arrayList) {
        this.h = arrayList;
    }

    public a b(String str) {
        this.f = str;
        return this;
    }

    public void b(int i) {
        this.d = i;
    }

    public a c(String str) {
        this.g = str;
        return this;
    }

    @Override // com.abupdate.http_libs.response.Response
    public String getCharset() {
        return this.f1876a;
    }

    @Override // com.abupdate.http_libs.response.Response
    public String getContent() {
        return this.k;
    }

    @Override // com.abupdate.http_libs.response.Response
    public String getContentEncoding() {
        return this.f;
    }

    @Override // com.abupdate.http_libs.response.Response
    public long getContentLength() {
        return this.e;
    }

    @Override // com.abupdate.http_libs.response.Response
    public String getContentType() {
        return this.g;
    }

    @Override // com.abupdate.http_libs.response.Response
    public com.abupdate.http_libs.a.a getException() {
        return this.j;
    }

    @Override // com.abupdate.http_libs.response.Response
    public ArrayList<c> getHeaders() {
        return this.h;
    }

    @Override // com.abupdate.http_libs.response.Response
    public b getHttpStatus() {
        return this.b;
    }

    @Override // com.abupdate.http_libs.response.Response
    public int getRedirectTimes() {
        return this.d;
    }

    @Override // com.abupdate.http_libs.response.Response
    public Request getRequest() {
        return this.i;
    }

    @Override // com.abupdate.http_libs.response.Response
    public int getRetryTimes() {
        return this.c;
    }

    @Override // com.abupdate.http_libs.response.Response
    public boolean isConnectSuccess() {
        b bVar = this.b;
        return bVar != null && bVar.c();
    }

    @Override // com.abupdate.http_libs.response.Response
    public boolean isResultOk() {
        return this.k != null;
    }

    @Override // com.abupdate.http_libs.response.Response
    public String resToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("^_^\n");
        sb.append("____________________________ IOT http response info start ____________________________");
        sb.append("\n url            : ");
        sb.append(this.i.getUrl());
        sb.append("\n status         : ");
        sb.append(this.b);
        sb.append("\n charset        : ");
        sb.append(this.f1876a);
        sb.append("\n retryTimes     : ");
        sb.append(this.c);
        sb.append("\n redirectTimes  : ");
        sb.append(this.d);
        sb.append("\n contentLength  : ");
        sb.append(this.e);
        sb.append("\n contentEncoding: ");
        sb.append(this.f);
        sb.append("\n contentType    : ");
        sb.append(this.g);
        sb.append("\n header         ");
        ArrayList<c> arrayList = this.h;
        if (arrayList == null) {
            sb.append(": null");
        } else {
            Iterator<c> it = arrayList.iterator();
            while (it.hasNext()) {
                sb.append("\n|    ");
                sb.append(it.next());
            }
        }
        sb.append("\n ");
        sb.append(this.i);
        sb.append("\n exception      : ");
        sb.append(this.j);
        sb.append("\n.");
        sb.append("\n _________________ data-start _________________");
        sb.append("\n ");
        sb.append(this.k);
        sb.append("\n _________________ data-over _________________");
        sb.append("\n____________________________ IOT http response info end ____________________________");
        return sb.toString();
    }

    @Override // com.abupdate.http_libs.response.Response
    public void setContent(String str) {
        this.k = str;
    }

    public String toString() {
        return resToString();
    }
}
