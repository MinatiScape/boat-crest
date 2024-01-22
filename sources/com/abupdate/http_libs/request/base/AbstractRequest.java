package com.abupdate.http_libs.request.base;

import com.abupdate.http_libs.HttpIotUtils;
import com.abupdate.http_libs.data.a;
import com.abupdate.http_libs.request.content.HttpBody;
import com.abupdate.http_libs.response.Response;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
/* loaded from: classes.dex */
public abstract class AbstractRequest implements Request {
    public static final String CR_LF = "\r\n";
    public static final String TRANSFER_ENCODING_BINARY = "Content-Transfer-Encoding: binary\r\n";

    /* renamed from: a  reason: collision with root package name */
    public String f1872a;
    public a b;
    public com.abupdate.http_libs.b.a h;
    public String i;
    public HttpBody j;
    public SSLSocketFactory k;
    public HostnameVerifier l;
    public int d = -1;
    public int e = -1;
    public int f = -1;
    public int g = -1;
    public String c = "UTF-8";

    @Override // com.abupdate.http_libs.request.base.Request
    public Request build() {
        return this;
    }

    @Override // com.abupdate.http_libs.request.base.Request
    public Response exec() {
        return HttpIotUtils.getInstance().exec(this);
    }

    @Override // com.abupdate.http_libs.request.base.Request
    public void exec(com.abupdate.http_libs.b.a aVar) {
        this.h = aVar;
        HttpIotUtils.getInstance().exec(this);
    }

    @Override // com.abupdate.http_libs.request.base.Request
    public String getCharset() {
        return this.c;
    }

    @Override // com.abupdate.http_libs.request.base.Request
    public int getConnectTimeout() {
        return this.d;
    }

    @Override // com.abupdate.http_libs.request.base.Request
    public String getContentType() {
        return this.i;
    }

    @Override // com.abupdate.http_libs.request.base.Request
    public HostnameVerifier getHostnameVerifier() {
        return this.l;
    }

    @Override // com.abupdate.http_libs.request.base.Request
    public HttpBody getHttpBody() {
        return this.j;
    }

    @Override // com.abupdate.http_libs.request.base.Request
    public com.abupdate.http_libs.b.a getHttpListener() {
        return this.h;
    }

    @Override // com.abupdate.http_libs.request.base.Request
    public int getMaxRedirectTimes() {
        return this.g;
    }

    @Override // com.abupdate.http_libs.request.base.Request
    public int getMaxRetryTimes() {
        return this.f;
    }

    @Override // com.abupdate.http_libs.request.base.Request
    public a getMethod() {
        return this.b;
    }

    @Override // com.abupdate.http_libs.request.base.Request
    public int getSocketTimeout() {
        return this.e;
    }

    @Override // com.abupdate.http_libs.request.base.Request
    public SSLSocketFactory getSslSocketFactory() {
        return this.k;
    }

    @Override // com.abupdate.http_libs.request.base.Request
    public String getUrl() {
        return this.f1872a;
    }

    @Override // com.abupdate.http_libs.request.base.Request
    public Request setCharset(String str) {
        this.c = str;
        return this;
    }

    @Override // com.abupdate.http_libs.request.base.Request
    public Request setConnectTimeout(int i) {
        this.d = i;
        return this;
    }

    @Override // com.abupdate.http_libs.request.base.Request
    public Request setHeaderContentType(String str) {
        this.i = str;
        return this;
    }

    @Override // com.abupdate.http_libs.request.base.Request
    public Request setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.l = hostnameVerifier;
        return this;
    }

    @Override // com.abupdate.http_libs.request.base.Request
    public Request setHttpBody(HttpBody httpBody) {
        this.j = httpBody;
        return this;
    }

    @Override // com.abupdate.http_libs.request.base.Request
    public Request setMaxRedirectTimes(int i) {
        this.g = i;
        return this;
    }

    @Override // com.abupdate.http_libs.request.base.Request
    public Request setMaxRetryTimes(int i) {
        this.f = i;
        return this;
    }

    @Override // com.abupdate.http_libs.request.base.Request
    public Request setMethod(a aVar) {
        this.b = aVar;
        return this;
    }

    @Override // com.abupdate.http_libs.request.base.Request
    public Request setSocketTimeout(int i) {
        this.e = i;
        return this;
    }

    @Override // com.abupdate.http_libs.request.base.Request
    public Request setSslSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.k = sSLSocketFactory;
        return this;
    }

    @Override // com.abupdate.http_libs.request.base.Request
    public Request setUrl(String str) {
        this.f1872a = str;
        return this;
    }
}
