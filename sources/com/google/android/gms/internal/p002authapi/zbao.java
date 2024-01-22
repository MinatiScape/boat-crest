package com.google.android.gms.internal.p002authapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.auth.api.identity.CredentialSavingClient;
import com.google.android.gms.auth.api.identity.SaveAccountLinkingTokenRequest;
import com.google.android.gms.auth.api.identity.SaveAccountLinkingTokenResult;
import com.google.android.gms.auth.api.identity.SavePasswordRequest;
import com.google.android.gms.auth.api.identity.SavePasswordResult;
import com.google.android.gms.auth.api.identity.zbc;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
/* renamed from: com.google.android.gms.internal.auth-api.zbao  reason: invalid package */
/* loaded from: classes6.dex */
public final class zbao extends GoogleApi implements CredentialSavingClient {
    public static final Api.ClientKey k;
    public static final Api.AbstractClientBuilder l;
    public static final Api m;
    public final String j;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        k = clientKey;
        a aVar = new a();
        l = aVar;
        m = new Api("Auth.Api.Identity.CredentialSaving.API", aVar, clientKey);
    }

    public zbao(@NonNull Activity activity, @NonNull zbc zbcVar) {
        super(activity, (Api<zbc>) m, zbcVar, GoogleApi.Settings.DEFAULT_SETTINGS);
        this.j = zbbb.zba();
    }

    @Override // com.google.android.gms.auth.api.identity.CredentialSavingClient
    public final Status getStatusFromIntent(@Nullable Intent intent) {
        if (intent == null) {
            return Status.RESULT_INTERNAL_ERROR;
        }
        Status status = (Status) SafeParcelableSerializer.deserializeFromIntentExtra(intent, NotificationCompat.CATEGORY_STATUS, Status.CREATOR);
        return status == null ? Status.RESULT_INTERNAL_ERROR : status;
    }

    @Override // com.google.android.gms.auth.api.identity.CredentialSavingClient
    public final Task<SaveAccountLinkingTokenResult> saveAccountLinkingToken(@NonNull SaveAccountLinkingTokenRequest saveAccountLinkingTokenRequest) {
        Preconditions.checkNotNull(saveAccountLinkingTokenRequest);
        SaveAccountLinkingTokenRequest.Builder zba = SaveAccountLinkingTokenRequest.zba(saveAccountLinkingTokenRequest);
        zba.zba(this.j);
        final SaveAccountLinkingTokenRequest build = zba.build();
        return doRead(TaskApiCall.builder().setFeatures(zbba.zbg).run(new RemoteCall() { // from class: com.google.android.gms.internal.auth-api.zbaj
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                zbao zbaoVar = zbao.this;
                SaveAccountLinkingTokenRequest saveAccountLinkingTokenRequest2 = build;
                ((zbz) ((zbw) obj).getService()).zbc(new b(zbaoVar, (TaskCompletionSource) obj2), (SaveAccountLinkingTokenRequest) Preconditions.checkNotNull(saveAccountLinkingTokenRequest2));
            }
        }).setAutoResolveMissingFeatures(false).setMethodKey(1535).build());
    }

    @Override // com.google.android.gms.auth.api.identity.CredentialSavingClient
    public final Task<SavePasswordResult> savePassword(@NonNull SavePasswordRequest savePasswordRequest) {
        Preconditions.checkNotNull(savePasswordRequest);
        SavePasswordRequest.Builder zba = SavePasswordRequest.zba(savePasswordRequest);
        zba.zba(this.j);
        final SavePasswordRequest build = zba.build();
        return doRead(TaskApiCall.builder().setFeatures(zbba.zbe).run(new RemoteCall() { // from class: com.google.android.gms.internal.auth-api.zbak
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                zbao zbaoVar = zbao.this;
                SavePasswordRequest savePasswordRequest2 = build;
                ((zbz) ((zbw) obj).getService()).zbd(new c(zbaoVar, (TaskCompletionSource) obj2), (SavePasswordRequest) Preconditions.checkNotNull(savePasswordRequest2));
            }
        }).setAutoResolveMissingFeatures(false).setMethodKey(1536).build());
    }

    public zbao(@NonNull Context context, @NonNull zbc zbcVar) {
        super(context, m, zbcVar, GoogleApi.Settings.DEFAULT_SETTINGS);
        this.j = zbbb.zba();
    }
}
