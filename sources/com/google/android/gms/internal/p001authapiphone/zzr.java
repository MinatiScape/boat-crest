package com.google.android.gms.internal.p001authapiphone;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.auth.api.phone.SmsCodeAutofillClient;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
/* renamed from: com.google.android.gms.internal.auth-api-phone.zzr  reason: invalid package */
/* loaded from: classes6.dex */
public final class zzr extends GoogleApi<Api.ApiOptions.NoOptions> implements SmsCodeAutofillClient {
    public static final Api.ClientKey<zzw> j;
    public static final Api.AbstractClientBuilder<zzw, Api.ApiOptions.NoOptions> k;
    public static final Api<Api.ApiOptions.NoOptions> l;

    static {
        Api.ClientKey<zzw> clientKey = new Api.ClientKey<>();
        j = clientKey;
        b bVar = new b();
        k = bVar;
        l = new Api<>("SmsCodeAutofill.API", bVar, clientKey);
    }

    public zzr(Activity activity) {
        super(activity, l, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @Override // com.google.android.gms.auth.api.phone.SmsCodeAutofillClient
    public final Task<Integer> checkPermissionState() {
        return doRead(TaskApiCall.builder().setFeatures(zzac.zza).run(new RemoteCall() { // from class: com.google.android.gms.internal.auth-api-phone.zzk
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                ((zzh) ((zzw) obj).getService()).zzc(new d(zzr.this, (TaskCompletionSource) obj2));
            }
        }).setMethodKey(1564).build());
    }

    @Override // com.google.android.gms.auth.api.phone.SmsCodeAutofillClient
    public final Task<Boolean> hasOngoingSmsRequest(final String str) {
        Preconditions.checkNotNull(str);
        Preconditions.checkArgument(!str.isEmpty(), "The package name cannot be empty.");
        return doRead(TaskApiCall.builder().setFeatures(zzac.zza).run(new RemoteCall() { // from class: com.google.android.gms.internal.auth-api-phone.zzm
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                zzr zzrVar = zzr.this;
                ((zzh) ((zzw) obj).getService()).zzd(str, new e(zzrVar, (TaskCompletionSource) obj2));
            }
        }).setMethodKey(1565).build());
    }

    @Override // com.google.android.gms.auth.api.phone.SmsCodeAutofillClient
    public final Task<Void> startSmsCodeRetriever() {
        return doWrite(TaskApiCall.builder().setFeatures(zzac.zza).run(new RemoteCall() { // from class: com.google.android.gms.internal.auth-api-phone.zzl
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                ((zzh) ((zzw) obj).getService()).zze(new c(zzr.this, (TaskCompletionSource) obj2));
            }
        }).setMethodKey(1563).build());
    }

    public zzr(Context context) {
        super(context, l, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
