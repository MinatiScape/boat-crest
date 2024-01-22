package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes7.dex */
public final class p0 extends zzhj {

    /* renamed from: a  reason: collision with root package name */
    public final HttpURLConnection f8712a;
    public final int b;
    public final String c;
    public final ArrayList<String> d;
    public final ArrayList<String> e;

    public p0(HttpURLConnection httpURLConnection) throws IOException {
        ArrayList<String> arrayList = new ArrayList<>();
        this.d = arrayList;
        ArrayList<String> arrayList2 = new ArrayList<>();
        this.e = arrayList2;
        this.f8712a = httpURLConnection;
        int responseCode = httpURLConnection.getResponseCode();
        this.b = responseCode == -1 ? 0 : responseCode;
        this.c = httpURLConnection.getResponseMessage();
        for (Map.Entry<String, List<String>> entry : httpURLConnection.getHeaderFields().entrySet()) {
            String key = entry.getKey();
            if (key != null) {
                for (String str : entry.getValue()) {
                    if (str != null) {
                        arrayList.add(key);
                        arrayList2.add(str);
                    }
                }
            }
        }
    }

    public final long a() {
        String headerField = this.f8712a.getHeaderField("Content-Length");
        if (headerField == null) {
            return -1L;
        }
        return Long.parseLong(headerField);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhj
    public final void disconnect() {
        this.f8712a.disconnect();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhj
    public final InputStream getContent() throws IOException {
        InputStream errorStream;
        try {
            errorStream = this.f8712a.getInputStream();
        } catch (IOException unused) {
            errorStream = this.f8712a.getErrorStream();
        }
        if (errorStream == null) {
            return null;
        }
        return new r0(this, errorStream);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhj
    public final String getContentEncoding() {
        return this.f8712a.getContentEncoding();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhj
    public final String getContentType() {
        return this.f8712a.getHeaderField("Content-Type");
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhj
    public final String getReasonPhrase() {
        return this.c;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhj
    public final int getStatusCode() {
        return this.b;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhj
    public final String zzaf(int i) {
        return this.d.get(i);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhj
    public final String zzag(int i) {
        return this.e.get(i);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhj
    public final String zzgj() {
        String headerField = this.f8712a.getHeaderField(0);
        if (headerField == null || !headerField.startsWith("HTTP/1.")) {
            return null;
        }
        return headerField;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhj
    public final int zzgk() {
        return this.d.size();
    }
}
