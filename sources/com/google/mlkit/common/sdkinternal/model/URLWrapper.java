package com.google.mlkit.common.sdkinternal.model;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
@KeepForSdk
/* loaded from: classes10.dex */
public class URLWrapper {

    /* renamed from: a  reason: collision with root package name */
    public final URL f11604a;

    @KeepForSdk
    public URLWrapper(@NonNull String str) throws MalformedURLException {
        this.f11604a = new URL(str);
    }

    @NonNull
    @KeepForSdk
    public URLConnection openConnection() throws IOException {
        return this.f11604a.openConnection();
    }
}
