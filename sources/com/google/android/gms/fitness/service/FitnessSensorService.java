package com.google.android.gms.fitness.service;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.result.DataSourcesResult;
import com.google.android.gms.internal.fitness.zzbh;
import com.google.android.gms.internal.fitness.zzcn;
import com.google.android.gms.internal.fitness.zzer;
import com.google.android.gms.internal.fitness.zzet;
import com.google.android.gms.internal.fitness.zzeu;
import java.util.List;
/* loaded from: classes6.dex */
public abstract class FitnessSensorService extends Service {
    @NonNull
    public static final String SERVICE_INTERFACE = "com.google.android.gms.fitness.service.FitnessSensorService";
    public a h;

    @Override // android.app.Service
    @Nullable
    @CallSuper
    public IBinder onBind(@NonNull Intent intent) {
        if (SERVICE_INTERFACE.equals(intent.getAction())) {
            if (Log.isLoggable("FitnessSensorService", 3)) {
                String valueOf = String.valueOf(intent);
                String name = getClass().getName();
                StringBuilder sb = new StringBuilder(valueOf.length() + 20 + name.length());
                sb.append("Intent ");
                sb.append(valueOf);
                sb.append(" received by ");
                sb.append(name);
                Log.d("FitnessSensorService", sb.toString());
            }
            return this.h.asBinder();
        }
        return null;
    }

    @Override // android.app.Service
    @CallSuper
    public void onCreate() {
        super.onCreate();
        this.h = new a();
    }

    @NonNull
    public abstract List<DataSource> onFindDataSources(@NonNull List<DataType> list);

    public abstract boolean onRegister(@NonNull FitnessSensorServiceRequest fitnessSensorServiceRequest);

    public abstract boolean onUnregister(@NonNull DataSource dataSource);

    @VisibleForTesting
    @TargetApi(19)
    public final void zzac() {
        int callingUid = Binder.getCallingUid();
        if (PlatformVersion.isAtLeastKitKat()) {
            ((AppOpsManager) getSystemService("appops")).checkPackage(callingUid, "com.google.android.gms");
            return;
        }
        String[] packagesForUid = getPackageManager().getPackagesForUid(callingUid);
        if (packagesForUid != null) {
            for (String str : packagesForUid) {
                if (str.equals("com.google.android.gms")) {
                    return;
                }
            }
        }
        throw new SecurityException("Unauthorized caller");
    }

    /* loaded from: classes6.dex */
    public static class a extends zzeu {

        /* renamed from: a  reason: collision with root package name */
        public final FitnessSensorService f8465a;

        public a(FitnessSensorService fitnessSensorService) {
            this.f8465a = fitnessSensorService;
        }

        @Override // com.google.android.gms.internal.fitness.zzev
        public final void zza(zzer zzerVar, zzbh zzbhVar) throws RemoteException {
            this.f8465a.zzac();
            zzbhVar.zza(new DataSourcesResult(this.f8465a.onFindDataSources(zzerVar.getDataTypes()), Status.RESULT_SUCCESS));
        }

        @Override // com.google.android.gms.internal.fitness.zzev
        public final void zza(FitnessSensorServiceRequest fitnessSensorServiceRequest, zzcn zzcnVar) throws RemoteException {
            this.f8465a.zzac();
            if (this.f8465a.onRegister(fitnessSensorServiceRequest)) {
                zzcnVar.onResult(Status.RESULT_SUCCESS);
            } else {
                zzcnVar.onResult(new Status(13));
            }
        }

        @Override // com.google.android.gms.internal.fitness.zzev
        public final void zza(zzet zzetVar, zzcn zzcnVar) throws RemoteException {
            this.f8465a.zzac();
            if (this.f8465a.onUnregister(zzetVar.getDataSource())) {
                zzcnVar.onResult(Status.RESULT_SUCCESS);
            } else {
                zzcnVar.onResult(new Status(13));
            }
        }
    }
}
