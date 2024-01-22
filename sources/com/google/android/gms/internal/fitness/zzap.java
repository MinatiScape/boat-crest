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
public final class zzap extends zzj<zzby> {
    public static final Api<Api.ApiOptions.NoOptions> API;
    public static final zzf.zza M = zzf.zza.FIT_SENSORS;
    public static final Api.ClientKey<zzap> N;
    public static final Api<Api.ApiOptions.HasGoogleSignInAccountOptions> zzoz;

    static {
        Api.ClientKey<zzap> clientKey = new Api.ClientKey<>();
        N = clientKey;
        API = new Api<>("Fitness.SENSORS_API", new o(), clientKey);
        zzoz = new Api<>("Fitness.SENSORS_CLIENT", new q(), clientKey);
    }

    public zzap(Context context, Looper looper, ClientSettings clientSettings, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, M, connectionCallbacks, onConnectionFailedListener, clientSettings);
    }

    @Override // com.google.android.gms.internal.fitness.zzj, com.google.android.gms.common.internal.BaseGmsClient
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoogleFitSensorsApi");
        if (queryLocalInterface instanceof zzby) {
            return (zzby) queryLocalInterface;
        }
        return new zzcb(iBinder);
    }

    @Override // com.google.android.gms.internal.fitness.zzj, com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    @Override // com.google.android.gms.internal.fitness.zzj, com.google.android.gms.common.internal.BaseGmsClient
    public final String getServiceDescriptor() {
        return "com.google.android.gms.fitness.internal.IGoogleFitSensorsApi";
    }

    @Override // com.google.android.gms.internal.fitness.zzj, com.google.android.gms.common.internal.BaseGmsClient
    public final String getStartServiceAction() {
        return "com.google.android.gms.fitness.SensorsApi";
    }
}
