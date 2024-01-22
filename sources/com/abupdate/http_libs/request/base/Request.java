package com.abupdate.http_libs.request.base;

import com.abupdate.http_libs.b.a;
import com.abupdate.http_libs.request.content.HttpBody;
import com.abupdate.http_libs.response.Response;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
/* loaded from: classes.dex */
public interface Request {
    Request build();

    Response exec();

    void exec(a aVar);

    String getCharset();

    int getConnectTimeout();

    String getContentType();

    HostnameVerifier getHostnameVerifier();

    HttpBody getHttpBody();

    a getHttpListener();

    int getMaxRedirectTimes();

    int getMaxRetryTimes();

    com.abupdate.http_libs.data.a getMethod();

    int getSocketTimeout();

    SSLSocketFactory getSslSocketFactory();

    String getUrl();

    Request setCharset(String str);

    Request setConnectTimeout(int i);

    Request setHeaderContentType(String str);

    Request setHostnameVerifier(HostnameVerifier hostnameVerifier);

    Request setHttpBody(HttpBody httpBody);

    Request setMaxRedirectTimes(int i);

    Request setMaxRetryTimes(int i);

    Request setMethod(com.abupdate.http_libs.data.a aVar);

    Request setSocketTimeout(int i);

    Request setSslSocketFactory(SSLSocketFactory sSLSocketFactory);

    Request setUrl(String str);
}
