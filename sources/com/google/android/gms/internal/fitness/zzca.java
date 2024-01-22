package com.google.android.gms.internal.fitness;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.request.zzaw;
import com.google.android.gms.fitness.request.zzay;
/* loaded from: classes8.dex */
public interface zzca extends IInterface {
    void zza(SessionInsertRequest sessionInsertRequest) throws RemoteException;

    void zza(SessionReadRequest sessionReadRequest) throws RemoteException;

    void zza(zzaw zzawVar) throws RemoteException;

    void zza(zzay zzayVar) throws RemoteException;

    void zza(com.google.android.gms.fitness.request.zzba zzbaVar) throws RemoteException;

    void zza(com.google.android.gms.fitness.request.zzbc zzbcVar) throws RemoteException;
}
