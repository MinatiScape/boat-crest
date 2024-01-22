package com.google.firebase.ml.common.internal.modeldownload;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
/* loaded from: classes10.dex */
public final class zzai {

    /* renamed from: a  reason: collision with root package name */
    public final URL f11391a;

    public zzai(String str) throws MalformedURLException {
        this.f11391a = new URL(str);
    }

    public final URLConnection openConnection() throws IOException {
        return this.f11391a.openConnection();
    }
}
