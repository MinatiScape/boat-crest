package com.google.android.gms.internal.p001authapiphone;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.auth.api.phone.SmsCodeBrowserClient;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
/* renamed from: com.google.android.gms.internal.auth-api-phone.zzv  reason: invalid package */
/* loaded from: classes6.dex */
public final class zzv extends GoogleApi<Api.ApiOptions.NoOptions> implements SmsCodeBrowserClient {
    public static final Api.ClientKey<zzw> j;
    public static final Api.AbstractClientBuilder<zzw, Api.ApiOptions.NoOptions> k;
    public static final Api<Api.ApiOptions.NoOptions> l;

    static {
        Api.ClientKey<zzw> clientKey = new Api.ClientKey<>();
        j = clientKey;
        f fVar = new f();
        k = fVar;
        l = new Api<>("SmsCodeBrowser.API", fVar, clientKey);
    }

    public zzv(Activity activity) {
        super(activity, l, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @Override // com.google.android.gms.auth.api.phone.SmsCodeBrowserClient
    public final Task<Void> startSmsCodeRetriever() {
        return doWrite(TaskApiCall.builder().setFeatures(zzac.zzb).run(new RemoteCall() { // from class: com.google.android.gms.internal.auth-api-phone.zzs
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                ((zzh) ((zzw) obj).getService()).zzf(new g(zzv.this, (TaskCompletionSource) obj2));
            }
        }).setMethodKey(1566).build());
    }

    public zzv(Context context) {
        super(context, l, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
