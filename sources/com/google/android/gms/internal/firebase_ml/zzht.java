package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Arrays;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
/* loaded from: classes7.dex */
public final class zzht extends zzhh {
    public static final String[] d;
    public final zzhq c;

    static {
        String[] strArr = {"DELETE", "GET", "HEAD", "OPTIONS", "POST", "PUT", "TRACE"};
        d = strArr;
        Arrays.sort(strArr);
    }

    public zzht() {
        this(null, null, null);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhh
    public final boolean zzaj(String str) {
        return Arrays.binarySearch(d, str) >= 0;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhh
    public final /* synthetic */ zzhk zzc(String str, String str2) throws IOException {
        Object[] objArr = {str};
        if (zzaj(str)) {
            HttpURLConnection zza = this.c.zza(new URL(str2));
            zza.setRequestMethod(str);
            if (zza instanceof HttpsURLConnection) {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) zza;
            }
            return new q0(zza);
        }
        throw new IllegalArgumentException(zzms.zzb("HTTP method %s not supported", objArr));
    }

    public zzht(zzhq zzhqVar, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier) {
        zzhp zzhpVar;
        if (System.getProperty("com.google.api.client.should_use_proxy") != null) {
            zzhpVar = new zzhp(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(System.getProperty("https.proxyHost"), Integer.parseInt(System.getProperty("https.proxyPort")))));
        } else {
            zzhpVar = new zzhp();
        }
        this.c = zzhpVar;
    }
}
