package com.abupdate.http_libs.response;

import com.abupdate.http_libs.data.b;
import com.abupdate.http_libs.data.c;
import com.abupdate.http_libs.request.base.Request;
import java.util.ArrayList;
/* loaded from: classes.dex */
public interface Response {
    String getCharset();

    String getContent();

    String getContentEncoding();

    long getContentLength();

    String getContentType();

    com.abupdate.http_libs.a.a getException();

    ArrayList<c> getHeaders();

    b getHttpStatus();

    int getRedirectTimes();

    Request getRequest();

    int getRetryTimes();

    boolean isConnectSuccess();

    boolean isResultOk();

    String resToString();

    void setContent(String str);
}
