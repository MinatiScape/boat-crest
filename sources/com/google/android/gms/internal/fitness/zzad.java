package com.google.android.gms.internal.fitness;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.internal.fitness.zzf;
/* loaded from: classes8.dex */
public final class zzad extends zzj<zzbu> {
    public static final Api<Api.ApiOptions.NoOptions> API;
    public static final zzf.zza M = zzf.zza.FIT_HISTORY;
    public static final Api.ClientKey<zzad> N;
    public static final Api<Api.ApiOptions.HasGoogleSignInAccountOptions> zzoz;

    static {
        Api.ClientKey<zzad> clientKey = new Api.ClientKey<>();
        N = clientKey;
        API = new Api<>("Fitness.API", new e(), clientKey);
        zzoz = new Api<>("Fitness.CLIENT", new g(), clientKey);
    }

    public zzad(Context context, Looper looper, ClientSettings clientSettings, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, M, connectionCallbacks, onConnectionFailedListener, clientSettings);
    }

    @Override // com.google.android.gms.internal.fitness.zzj, com.google.android.gms.common.internal.BaseGmsClient
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoogleFitHistoryApi");
        if (queryLocalInterface instanceof zzbu) {
            return (zzbu) queryLocalInterface;
        }
        return new zzbx(iBinder);
    }

    @Override // com.google.android.gms.internal.fitness.zzj, com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    @Override // com.google.android.gms.internal.fitness.zzj, com.google.android.gms.common.internal.BaseGmsClient
    public final String getServiceDescriptor() {
        return "com.google.android.gms.fitness.internal.IGoogleFitHistoryApi";
    }

    @Override // com.google.android.gms.internal.fitness.zzj, com.google.android.gms.common.internal.BaseGmsClient
    public final String getStartServiceAction() {
        return "com.google.android.gms.fitness.HistoryApi";
    }
}
