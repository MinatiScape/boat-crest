package com.google.android.gms.internal.auth;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes6.dex */
public final class b extends GoogleApi implements zzg {
    public static final Api.ClientKey j;
    public static final Api.AbstractClientBuilder k;
    public static final Api l;
    public static final Logger m;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        j = clientKey;
        b3 b3Var = new b3();
        k = b3Var;
        l = new Api("GoogleAuthService.API", b3Var, clientKey);
        m = com.google.android.gms.auth.zzd.zza("GoogleAuthServiceClient");
    }

    public b(@NonNull Context context) {
        super(context, l, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public static /* bridge */ /* synthetic */ void c(Status status, Object obj, TaskCompletionSource taskCompletionSource) {
        if (TaskUtil.trySetResultOrApiException(status, obj, taskCompletionSource)) {
            return;
        }
        m.w("The task is already complete.", new Object[0]);
    }

    @Override // com.google.android.gms.internal.auth.zzg
    public final Task zza(final zzbw zzbwVar) {
        return doWrite(TaskApiCall.builder().setFeatures(com.google.android.gms.auth.zze.zzj).run(new RemoteCall() { // from class: com.google.android.gms.internal.auth.zzt
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                b bVar = b.this;
                ((zzp) ((a3) obj).getService()).zzd(new d3(bVar, (TaskCompletionSource) obj2), zzbwVar);
            }
        }).setMethodKey(1513).build());
    }

    @Override // com.google.android.gms.internal.auth.zzg
    public final Task zzb(@NonNull final AccountChangeEventsRequest accountChangeEventsRequest) {
        Preconditions.checkNotNull(accountChangeEventsRequest, "request cannot be null.");
        return doWrite(TaskApiCall.builder().setFeatures(com.google.android.gms.auth.zze.zzi).run(new RemoteCall() { // from class: com.google.android.gms.internal.auth.zzu
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                b bVar = b.this;
                AccountChangeEventsRequest accountChangeEventsRequest2 = accountChangeEventsRequest;
                ((zzp) ((a3) obj).getService()).zze(new f3(bVar, (TaskCompletionSource) obj2), accountChangeEventsRequest2);
            }
        }).setMethodKey(1515).build());
    }

    @Override // com.google.android.gms.internal.auth.zzg
    public final Task zzc(@NonNull final Account account, @NonNull final String str, final Bundle bundle) {
        Preconditions.checkNotNull(account, "Account name cannot be null!");
        Preconditions.checkNotEmpty(str, "Scope cannot be null!");
        return doWrite(TaskApiCall.builder().setFeatures(com.google.android.gms.auth.zze.zzj).run(new RemoteCall() { // from class: com.google.android.gms.internal.auth.zzs
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                b bVar = b.this;
                ((zzp) ((a3) obj).getService()).zzf(new c3(bVar, (TaskCompletionSource) obj2), account, str, bundle);
            }
        }).setMethodKey(1512).build());
    }

    @Override // com.google.android.gms.internal.auth.zzg
    public final Task zzd(@NonNull final Account account) {
        Preconditions.checkNotNull(account, "account cannot be null.");
        return doWrite(TaskApiCall.builder().setFeatures(com.google.android.gms.auth.zze.zzi).run(new RemoteCall() { // from class: com.google.android.gms.internal.auth.zzr
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                b bVar = b.this;
                ((zzp) ((a3) obj).getService()).zzg(new a(bVar, (TaskCompletionSource) obj2), account);
            }
        }).setMethodKey(1517).build());
    }

    @Override // com.google.android.gms.internal.auth.zzg
    public final Task zze(@NonNull final String str) {
        Preconditions.checkNotNull(str, "Client package name cannot be null!");
        return doWrite(TaskApiCall.builder().setFeatures(com.google.android.gms.auth.zze.zzi).run(new RemoteCall() { // from class: com.google.android.gms.internal.auth.zzq
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                b bVar = b.this;
                ((zzp) ((a3) obj).getService()).zzh(new e3(bVar, (TaskCompletionSource) obj2), str);
            }
        }).setMethodKey(1514).build());
    }
}
