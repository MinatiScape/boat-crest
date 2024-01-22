package com.google.android.gms.auth.api.accounttransfer;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.auth.zzaq;
import com.google.android.gms.internal.auth.zzav;
import com.google.android.gms.internal.auth.zzax;
import com.google.android.gms.internal.auth.zzaz;
import com.google.android.gms.internal.auth.zzbb;
import com.google.android.gms.tasks.Task;
/* loaded from: classes6.dex */
public class AccountTransferClient extends GoogleApi<zzr> {
    public static final Api.ClientKey j;
    public static final Api.AbstractClientBuilder k;
    public static final Api l;
    public static final /* synthetic */ int zza = 0;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        j = clientKey;
        b bVar = new b();
        k = bVar;
        l = new Api("AccountTransfer.ACCOUNT_TRANSFER_API", bVar, clientKey);
    }

    public AccountTransferClient(@NonNull Activity activity, @Nullable zzr zzrVar) {
        super(activity, (Api<zzr>) l, zzr.zza, new GoogleApi.Settings.Builder().setMapper(new ApiExceptionMapper()).build());
    }

    @NonNull
    public Task<DeviceMetaData> getDeviceMetaData(@NonNull String str) {
        Preconditions.checkNotNull(str);
        return doRead(new g(this, 1608, new zzaq(str)));
    }

    @NonNull
    public Task<Void> notifyCompletion(@NonNull String str, int i) {
        Preconditions.checkNotNull(str);
        return doWrite(new i(this, 1610, new zzav(str, i)));
    }

    @NonNull
    public Task<byte[]> retrieveData(@NonNull String str) {
        Preconditions.checkNotNull(str);
        return doRead(new e(this, 1607, new zzax(str)));
    }

    @NonNull
    public Task<Void> sendData(@NonNull String str, @NonNull byte[] bArr) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(bArr);
        return doWrite(new c(this, 1606, new zzaz(str, bArr)));
    }

    @NonNull
    public Task<Void> showUserChallenge(@NonNull String str, @NonNull PendingIntent pendingIntent) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(pendingIntent);
        return doWrite(new h(this, 1609, new zzbb(str, pendingIntent)));
    }

    public AccountTransferClient(@NonNull Context context, @Nullable zzr zzrVar) {
        super(context, l, zzr.zza, new GoogleApi.Settings.Builder().setMapper(new ApiExceptionMapper()).build());
    }
}
