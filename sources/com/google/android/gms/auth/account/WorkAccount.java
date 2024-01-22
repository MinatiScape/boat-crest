package com.google.android.gms.auth.account;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.auth.zzal;
/* loaded from: classes6.dex */
public class WorkAccount {
    @NonNull
    public static final Api<Api.ApiOptions.NoOptions> API;
    @NonNull
    @Deprecated
    public static final WorkAccountApi WorkAccountApi;

    /* renamed from: a  reason: collision with root package name */
    public static final Api.ClientKey f8188a;
    public static final Api.AbstractClientBuilder b;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        f8188a = clientKey;
        a aVar = new a();
        b = aVar;
        API = new Api<>("WorkAccount.API", aVar, clientKey);
        WorkAccountApi = new zzal();
    }

    @NonNull
    public static WorkAccountClient getClient(@NonNull Activity activity) {
        return new WorkAccountClient(activity);
    }

    @NonNull
    public static WorkAccountClient getClient(@NonNull Context context) {
        return new WorkAccountClient(context);
    }
}
