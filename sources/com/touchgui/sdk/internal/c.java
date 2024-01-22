package com.touchgui.sdk.internal;

import android.util.LruCache;
import java.security.SecureRandom;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
/* loaded from: classes12.dex */
public abstract class c {

    /* renamed from: a  reason: collision with root package name */
    public static final LruCache f13749a = new LruCache(5);
    public static final TrustManager[] b = {new b()};

    /* JADX WARN: Code restructure failed: missing block: B:45:0x00ff, code lost:
        if (r4 != null) goto L37;
     */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00f7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0111 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x010c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00fc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int a(java.lang.String r8, int r9, java.lang.String r10) {
        /*
            Method dump skipped, instructions count: 282
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.touchgui.sdk.internal.c.a(java.lang.String, int, java.lang.String):int");
    }

    public static int a(String str) {
        Integer num = (Integer) f13749a.get(str);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public static /* synthetic */ boolean a(String str, SSLSession sSLSession) {
        if ("touchlink-aws.touchgui.com".equals(str)) {
            return true;
        }
        return HttpsURLConnection.getDefaultHostnameVerifier().verify(str, sSLSession);
    }

    public static SSLSocketFactory a(HttpsURLConnection httpsURLConnection) {
        SSLSocketFactory sSLSocketFactory = httpsURLConnection.getSSLSocketFactory();
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, b, new SecureRandom());
            httpsURLConnection.setSSLSocketFactory(sSLContext.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sSLSocketFactory;
    }
}
