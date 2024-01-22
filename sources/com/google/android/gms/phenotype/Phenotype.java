package com.google.android.gms.phenotype;

import android.net.Uri;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.phenotype.zzd;
import com.google.android.gms.internal.phenotype.zze;
@KeepForSdk
/* loaded from: classes10.dex */
public final class Phenotype {

    /* renamed from: a  reason: collision with root package name */
    public static final Api.ClientKey<zze> f10162a;
    public static final Api.AbstractClientBuilder<zze, Api.ApiOptions.NoOptions> b;

    static {
        Api.ClientKey<zze> clientKey = new Api.ClientKey<>();
        f10162a = clientKey;
        g gVar = new g();
        b = gVar;
        new Api("Phenotype.API", gVar, clientKey);
        new zzd();
    }

    @KeepForSdk
    public static Uri getContentProviderUri(String str) {
        String valueOf = String.valueOf(Uri.encode(str));
        return Uri.parse(valueOf.length() != 0 ? "content://com.google.android.gms.phenotype/".concat(valueOf) : new String("content://com.google.android.gms.phenotype/"));
    }
}
