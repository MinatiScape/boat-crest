package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
/* loaded from: classes7.dex */
public final class zzhp implements zzhq {

    /* renamed from: a  reason: collision with root package name */
    public final Proxy f8773a;

    public zzhp() {
        this(null);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhq
    public final HttpURLConnection zza(URL url) throws IOException {
        Proxy proxy = this.f8773a;
        return (HttpURLConnection) (proxy == null ? url.openConnection() : url.openConnection(proxy));
    }

    public zzhp(Proxy proxy) {
        this.f8773a = proxy;
    }
}
