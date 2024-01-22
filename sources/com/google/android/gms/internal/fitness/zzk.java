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
public final class zzk extends zzj<zzbo> {
    public static final Api<Api.ApiOptions.NoOptions> API;
    public static final zzf.zza M = zzf.zza.FIT_BLE;
    public static final Api.ClientKey<zzk> N;
    public static final Api<Api.ApiOptions.HasGoogleSignInAccountOptions> zzoz;

    static {
        Api.ClientKey<zzk> clientKey = new Api.ClientKey<>();
        N = clientKey;
        API = new Api<>("Fitness.BLE_API", new zzm(), clientKey);
        zzoz = new Api<>("Fitness.BLE_CLIENT", new r5(), clientKey);
    }

    public zzk(Context context, Looper looper, ClientSettings clientSettings, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, M, connectionCallbacks, onConnectionFailedListener, clientSettings);
    }

    @Override // com.google.android.gms.internal.fitness.zzj, com.google.android.gms.common.internal.BaseGmsClient
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoogleFitBleApi");
        if (queryLocalInterface instanceof zzbo) {
            return (zzbo) queryLocalInterface;
        }
        return new zzbr(iBinder);
    }

    @Override // com.google.android.gms.internal.fitness.zzj, com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    @Override // com.google.android.gms.internal.fitness.zzj, com.google.android.gms.common.internal.BaseGmsClient
    public final String getServiceDescriptor() {
        return "com.google.android.gms.fitness.internal.IGoogleFitBleApi";
    }

    @Override // com.google.android.gms.internal.fitness.zzj, com.google.android.gms.common.internal.BaseGmsClient
    public final String getStartServiceAction() {
        return "com.google.android.gms.fitness.BleApi";
    }
}
