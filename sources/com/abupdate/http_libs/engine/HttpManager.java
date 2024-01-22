package com.abupdate.http_libs.engine;

import android.content.Context;
import android.os.AsyncTask;
import com.abupdate.http_libs.data.HttpConfig;
import com.abupdate.http_libs.data.c;
import com.abupdate.http_libs.request.base.Request;
import com.abupdate.http_libs.request.content.HttpBody;
import com.abupdate.trace.Trace;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
/* loaded from: classes.dex */
public class HttpManager {
    public static final String b = "HttpManager";

    /* renamed from: a  reason: collision with root package name */
    public HttpConfig f1870a;

    /* loaded from: classes.dex */
    public class b extends AsyncTask<Request, Integer, com.abupdate.http_libs.response.a> {
        public b() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a */
        public com.abupdate.http_libs.response.a doInBackground(Request[] requestArr) {
            Request request = requestArr[0];
            com.abupdate.http_libs.response.a a2 = HttpManager.this.a(request);
            com.abupdate.http_libs.b.a httpListener = request.getHttpListener();
            if (httpListener != null) {
                httpListener.a(request);
            }
            if (!HttpManager.this.g(request, a2) && httpListener != null) {
                httpListener.a(a2.getException(), a2);
            }
            return a2;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b */
        public void onPostExecute(com.abupdate.http_libs.response.a aVar) {
            aVar.getRequest().getHttpListener().a(aVar);
            super.onPostExecute(aVar);
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            super.onPreExecute();
        }
    }

    public HttpManager(HttpConfig httpConfig) {
        d(httpConfig);
    }

    public static String b(InputStream inputStream, String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        if (inputStream != null) {
            while (true) {
                try {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        return new String(byteArrayOutputStream.toByteArray(), str);
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                } catch (IOException e) {
                    String str2 = b;
                    Trace.d(str2, "getResultString() exception:" + e.toString());
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    public static HttpConfig build(Context context) {
        return new HttpConfig(context);
    }

    public com.abupdate.http_libs.response.a a(Request request) {
        if (request.getConnectTimeout() <= 0) {
            request.setConnectTimeout(this.f1870a.getConnectTimeout());
        }
        if (request.getSocketTimeout() <= 0) {
            request.setSocketTimeout(this.f1870a.getSocketTimeout());
        }
        if (request.getMethod() == null) {
            request.setMethod(com.abupdate.http_libs.data.a.Get);
        }
        if (request.getCharset() == null) {
            request.setCharset("UTF-8");
        }
        if (request.getMaxRetryTimes() <= 0) {
            request.setMaxRetryTimes(this.f1870a.getRetryTimes());
        }
        if (request.getMaxRedirectTimes() <= 0) {
            request.setMaxRedirectTimes(this.f1870a.getRedirectTimes());
        }
        if (request.getContentType() == null) {
            request.setHeaderContentType(HttpConfig.CONTENT_TYPE);
        }
        return new com.abupdate.http_libs.response.a(request);
    }

    public final String c(String str, String str2) {
        if (str != null) {
            for (String str3 : str.split(";")) {
                String trim = str3.trim();
                if (trim.toLowerCase().startsWith("charset=")) {
                    return trim.substring(8);
                }
            }
        }
        return str2 == null ? "UTF-8" : str2;
    }

    public final void d(HttpConfig httpConfig) {
        this.f1870a = httpConfig;
        String str = b;
        Trace.d(str, "initConfig() " + httpConfig.toString());
    }

    public final void e(HttpURLConnection httpURLConnection, Request request) {
        HttpBody httpBody = request.getHttpBody();
        if (httpBody == null || request.getMethod() != com.abupdate.http_libs.data.a.Post) {
            return;
        }
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Content-Type", httpBody.getContentType());
        OutputStream outputStream = httpURLConnection.getOutputStream();
        httpBody.writeTo(outputStream);
        outputStream.close();
    }

    public void enqueue(Request request) {
        new b().execute(request);
    }

    public com.abupdate.http_libs.response.a execute(Request request) {
        com.abupdate.http_libs.response.a a2 = a(request);
        if (request.getHttpListener() != null) {
            request.getHttpListener().b(true);
        }
        g(request, a2);
        return a2;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x004c A[LOOP:0: B:3:0x0009->B:18:0x004c, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x004b A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean g(com.abupdate.http_libs.request.base.Request r7, com.abupdate.http_libs.response.a r8) {
        /*
            r6 = this;
            r0 = 0
            r8.b(r0)
            r8.a(r0)
            r1 = 1
            r2 = r1
        L9:
            int r3 = r7.getMaxRetryTimes()
            int r3 = r3 + r1
            if (r2 > r3) goto L4f
            if (r2 <= r1) goto L2f
            int r3 = r8.getRetryTimes()
            int r3 = r3 + r1
            r8.a(r3)
            com.abupdate.http_libs.b.a r3 = r7.getHttpListener()
            if (r3 == 0) goto L2f
            com.abupdate.http_libs.b.a r3 = r7.getHttpListener()
            int r4 = r7.getMaxRetryTimes()
            int r5 = r8.getRetryTimes()
            r3.a(r7, r4, r5)
        L2f:
            boolean r3 = r6.h(r7, r8)     // Catch: java.io.IOException -> L34 com.abupdate.http_libs.a.a -> L41
            goto L49
        L34:
            r3 = move-exception
            r3.printStackTrace()
            com.abupdate.http_libs.a.a r4 = new com.abupdate.http_libs.a.a
            r4.<init>(r3)
            r8.a(r4)
            goto L48
        L41:
            r3 = move-exception
            r3.printStackTrace()
            r8.a(r3)
        L48:
            r3 = r0
        L49:
            if (r3 == 0) goto L4c
            return r1
        L4c:
            int r2 = r2 + 1
            goto L9
        L4f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.abupdate.http_libs.engine.HttpManager.g(com.abupdate.http_libs.request.base.Request, com.abupdate.http_libs.response.a):boolean");
    }

    public final HttpConfig getConfig() {
        return this.f1870a;
    }

    public final boolean h(Request request, com.abupdate.http_libs.response.a aVar) {
        HttpsURLConnection httpsURLConnection;
        List<String> value;
        String url = request.getUrl();
        InputStream inputStream = null;
        try {
            URL url2 = new URL(url);
            if (url.startsWith("https")) {
                HttpsURLConnection httpsURLConnection2 = (HttpsURLConnection) url2.openConnection();
                if (request.getHostnameVerifier() != null) {
                    httpsURLConnection2.setHostnameVerifier(request.getHostnameVerifier());
                }
                httpsURLConnection = httpsURLConnection2;
                if (request.getSslSocketFactory() != null) {
                    httpsURLConnection2.setSSLSocketFactory(request.getSslSocketFactory());
                    httpsURLConnection = httpsURLConnection2;
                }
            } else {
                httpsURLConnection = (HttpURLConnection) url2.openConnection();
            }
            httpsURLConnection.setConnectTimeout(request.getConnectTimeout());
            httpsURLConnection.setReadTimeout(request.getSocketTimeout());
            httpsURLConnection.setRequestMethod(request.getMethod().a());
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setRequestProperty("Content-Type", request.getContentType());
            e(httpsURLConnection, request);
            int responseCode = httpsURLConnection.getResponseCode();
            if (responseCode != 200) {
                String str = b;
                Trace.d(str, "connect() responseCode：" + responseCode);
            }
            com.abupdate.http_libs.data.b bVar = new com.abupdate.http_libs.data.b(responseCode, httpsURLConnection.getResponseMessage());
            aVar.a(bVar);
            InputStream inputStream2 = httpsURLConnection.getInputStream();
            if (inputStream2 != null) {
                ArrayList<c> arrayList = new ArrayList<>();
                for (Map.Entry<String, List<String>> entry : httpsURLConnection.getHeaderFields().entrySet()) {
                    if (entry.getKey() != null && (value = entry.getValue()) != null) {
                        for (String str2 : value) {
                            arrayList.add(new c(entry.getKey(), str2));
                        }
                    }
                }
                aVar.a(arrayList);
                aVar.a(httpsURLConnection.getContentLength());
                aVar.b(httpsURLConnection.getContentEncoding());
                aVar.c(httpsURLConnection.getContentType());
                if (responseCode > 299 && responseCode != 600) {
                    if (responseCode <= 399) {
                        if (aVar.getRedirectTimes() < request.getMaxRedirectTimes()) {
                            aVar.b(aVar.getRedirectTimes() + 1);
                            if (request.getHttpListener() != null) {
                                request.getHttpListener().b(request, request.getMaxRedirectTimes(), aVar.getRedirectTimes());
                            }
                            URL url3 = httpsURLConnection.getURL();
                            String str3 = b;
                            Trace.d(str3, "connect() 重定向:" + url3.toString());
                            request.setUrl(url3.toString());
                            h(request, aVar);
                        }
                    } else if (responseCode <= 499) {
                        throw new com.abupdate.http_libs.a.a(bVar.a(), new Throwable(bVar.d()));
                    } else {
                        if (responseCode < 599) {
                            throw new com.abupdate.http_libs.a.a(bVar.a(), new Throwable(bVar.d()));
                        }
                    }
                    inputStream2.close();
                    return false;
                }
                aVar.a(c(aVar.getContentType(), request.getCharset()));
                String b2 = b(inputStream2, request.getCharset());
                aVar.setContent(b2);
                if (request.getHttpListener() != null) {
                    request.getHttpListener().a(b2, aVar);
                }
                inputStream2.close();
                return true;
            }
            throw new com.abupdate.http_libs.a.a(1);
        } catch (Throwable th) {
            if (0 != 0) {
                inputStream.close();
            }
            throw th;
        }
    }
}
