package com.abupdate.iot_libs.utils;

import com.abupdate.trace.Trace;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
/* loaded from: classes.dex */
public class f implements HostnameVerifier {
    @Override // javax.net.ssl.HostnameVerifier
    public boolean verify(String str, SSLSession sSLSession) {
        if (str.endsWith("adups.com")) {
            return true;
        }
        Trace.e("MyHostnameVerifier", "host name verify failed ! ");
        return false;
    }
}
