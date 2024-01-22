package com.google.android.gms.auth.api.phone;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.internal.p001authapiphone.zzw;
import com.google.android.gms.tasks.Task;
/* loaded from: classes6.dex */
public abstract class SmsRetrieverClient extends GoogleApi<Api.ApiOptions.NoOptions> implements SmsRetrieverApi {
    public static final Api.ClientKey<zzw> j;
    public static final Api.AbstractClientBuilder<zzw, Api.ApiOptions.NoOptions> k;
    public static final Api<Api.ApiOptions.NoOptions> l;

    static {
        Api.ClientKey<zzw> clientKey = new Api.ClientKey<>();
        j = clientKey;
        a aVar = new a();
        k = aVar;
        l = new Api<>("SmsRetriever.API", aVar, clientKey);
    }

    public SmsRetrieverClient(@NonNull Activity activity) {
        super(activity, l, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @Override // com.google.android.gms.auth.api.phone.SmsRetrieverApi
    @NonNull
    public abstract Task<Void> startSmsRetriever();

    @Override // com.google.android.gms.auth.api.phone.SmsRetrieverApi
    @NonNull
    public abstract Task<Void> startSmsUserConsent(@Nullable String str);

    public SmsRetrieverClient(@NonNull Context context) {
        super(context, l, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
