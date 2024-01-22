package com.google.android.gms.auth.api.accounttransfer;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.auth.zzao;
/* loaded from: classes6.dex */
public final class AccountTransfer {
    @NonNull
    public static final String ACTION_ACCOUNT_EXPORT_DATA_AVAILABLE = "com.google.android.gms.auth.ACCOUNT_EXPORT_DATA_AVAILABLE";
    @NonNull
    public static final String ACTION_ACCOUNT_IMPORT_DATA_AVAILABLE = "com.google.android.gms.auth.ACCOUNT_IMPORT_DATA_AVAILABLE";
    @NonNull
    public static final String ACTION_START_ACCOUNT_EXPORT = "com.google.android.gms.auth.START_ACCOUNT_EXPORT";
    @NonNull
    public static final String KEY_EXTRA_ACCOUNT_TYPE = "key_extra_account_type";

    /* renamed from: a  reason: collision with root package name */
    public static final Api.ClientKey f8191a;
    public static final Api.AbstractClientBuilder b;
    @NonNull
    public static final Api zza;
    @Deprecated
    public static final zzao zzb;
    @Deprecated
    public static final zzao zzc;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        f8191a = clientKey;
        a aVar = new a();
        b = aVar;
        zza = new Api("AccountTransfer.ACCOUNT_TRANSFER_API", aVar, clientKey);
        zzb = new zzao();
        zzc = new zzao();
    }

    @NonNull
    public static AccountTransferClient getAccountTransferClient(@NonNull Activity activity) {
        return new AccountTransferClient(activity, (zzr) null);
    }

    @NonNull
    public static AccountTransferClient getAccountTransferClient(@NonNull Context context) {
        return new AccountTransferClient(context, (zzr) null);
    }
}
