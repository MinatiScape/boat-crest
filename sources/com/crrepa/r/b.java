package com.crrepa.r;

import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.UUID;
/* loaded from: classes9.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7834a = UUID.randomUUID().toString();

    /* loaded from: classes9.dex */
    public class a implements Runnable {
        public final /* synthetic */ com.crrepa.s.a h;
        public final /* synthetic */ long i;
        public final /* synthetic */ long j;

        public a(b bVar, com.crrepa.s.a aVar, long j, long j2) {
            this.h = aVar;
            this.i = j;
            this.j = j2;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.crrepa.s.a aVar = this.h;
            long j = this.j;
            aVar.a((((float) this.i) * 100.0f) / ((float) j), j);
        }
    }

    public c a(String str, File file, List<File> list, Map<String, File> map, String str2, String str3, Map<String, String> map2, Map<String, String> map3, com.crrepa.s.a aVar) {
        HttpURLConnection h;
        HttpURLConnection httpURLConnection = null;
        try {
            h = h(str, "POST");
        } catch (Exception e) {
            e = e;
        }
        try {
            k(h);
            if (map3 != null) {
                j(h, map3);
            }
            h.connect();
            DataOutputStream dataOutputStream = new DataOutputStream(h.getOutputStream());
            if (map2 != null) {
                dataOutputStream.write(g(map2).getBytes());
                dataOutputStream.flush();
            }
            if (file != null) {
                i(file, str2, str3, dataOutputStream, aVar);
            } else if (list != null) {
                for (File file2 : list) {
                    i(file2, str2, str3, dataOutputStream, null);
                }
            } else if (map != null) {
                for (String str4 : map.keySet()) {
                    i(map.get(str4), str4, str3, dataOutputStream, null);
                }
            }
            dataOutputStream.write(("\r\n--" + f7834a + "--\r\n").getBytes());
            dataOutputStream.flush();
            return d(h);
        } catch (Exception e2) {
            e = e2;
            httpURLConnection = h;
            return e(httpURLConnection, e);
        }
    }

    public c b(String str, String str2, String str3, Map<String, String> map) {
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = h(str, "POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            if (!TextUtils.isEmpty(str3)) {
                httpURLConnection.setRequestProperty("Content-Type", str3);
            }
            if (map != null) {
                j(httpURLConnection, map);
            }
            httpURLConnection.connect();
            if (!TextUtils.isEmpty(str2)) {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8"));
                bufferedWriter.write(str2);
                bufferedWriter.close();
            }
            return d(httpURLConnection);
        } catch (Exception e) {
            return e(httpURLConnection, e);
        }
    }

    public c c(String str, Map<String, String> map) {
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = h(str, "GET");
            httpURLConnection.setDoInput(true);
            if (map != null) {
                j(httpURLConnection, map);
            }
            httpURLConnection.connect();
            return d(httpURLConnection);
        } catch (Exception e) {
            return e(httpURLConnection, e);
        }
    }

    public final c d(HttpURLConnection httpURLConnection) throws IOException {
        c cVar = new c();
        cVar.c = httpURLConnection.getResponseCode();
        cVar.d = httpURLConnection.getContentLength();
        cVar.f7835a = httpURLConnection.getInputStream();
        cVar.b = httpURLConnection.getErrorStream();
        return cVar;
    }

    public final c e(HttpURLConnection httpURLConnection, Exception exc) {
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
        exc.printStackTrace();
        c cVar = new c();
        cVar.e = exc;
        return cVar;
    }

    public final String f(File file, String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\r\n");
        stringBuffer.append("--");
        stringBuffer.append(f7834a);
        stringBuffer.append("\r\n");
        stringBuffer.append("Content-Disposition: form-data; name=\"" + str + "\"; filename=\"" + file.getName() + "\"");
        stringBuffer.append("\r\n");
        StringBuilder sb = new StringBuilder();
        sb.append("Content-Type: ");
        sb.append(str2);
        stringBuffer.append(sb.toString());
        stringBuffer.append("\r\n");
        stringBuffer.append("Content-Lenght: " + file.length());
        stringBuffer.append("\r\n");
        stringBuffer.append("\r\n");
        return stringBuffer.toString();
    }

    public final String g(Map<String, String> map) {
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : map.keySet()) {
            stringBuffer.append("--");
            stringBuffer.append(f7834a);
            stringBuffer.append("\r\n");
            stringBuffer.append("Content-Disposition: form-data; name=\"" + str + "\"");
            stringBuffer.append("\r\n");
            stringBuffer.append("Content-Type: text/plain");
            stringBuffer.append("\r\n");
            stringBuffer.append("Content-Lenght: " + map.get(str).length());
            stringBuffer.append("\r\n");
            stringBuffer.append("\r\n");
            stringBuffer.append(map.get(str));
            stringBuffer.append("\r\n");
        }
        return stringBuffer.toString();
    }

    public final HttpURLConnection h(String str, String str2) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setReadTimeout(15000);
        httpURLConnection.setRequestMethod(str2);
        return httpURLConnection;
    }

    public final void i(File file, String str, String str2, DataOutputStream dataOutputStream, com.crrepa.s.a aVar) throws IOException {
        dataOutputStream.write(f(file, str, str2).getBytes());
        dataOutputStream.flush();
        FileInputStream fileInputStream = new FileInputStream(file);
        long length = file.length();
        byte[] bArr = new byte[2048];
        long j = 0;
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read == -1) {
                dataOutputStream.flush();
                fileInputStream.close();
                return;
            }
            dataOutputStream.write(bArr, 0, read);
            long j2 = j + read;
            if (aVar != null) {
                com.crrepa.s.a.f7838a.post(new a(this, aVar, j2, length));
            }
            j = j2;
        }
    }

    public final void j(HttpURLConnection httpURLConnection, Map<String, String> map) {
        if (map != null) {
            for (String str : map.keySet()) {
                httpURLConnection.setRequestProperty(str, map.get(str));
            }
        }
    }

    public final void k(HttpURLConnection httpURLConnection) throws ProtocolException {
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setRequestProperty(HttpHeaders.CONNECTION, "Keep-Alive");
        httpURLConnection.setRequestProperty("Charset", "UTF-8");
        httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; BOUNDARY=" + f7834a);
    }
}
