package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zzt;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
/* loaded from: classes10.dex */
public final class IndoorBuilding {

    /* renamed from: a  reason: collision with root package name */
    public final com.google.android.gms.internal.maps.zzr f10076a;

    public IndoorBuilding(com.google.android.gms.internal.maps.zzr zzrVar) {
        a aVar = a.f10084a;
        this.f10076a = (com.google.android.gms.internal.maps.zzr) Preconditions.checkNotNull(zzrVar, "delegate");
        a aVar2 = (a) Preconditions.checkNotNull(aVar, "shim");
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof IndoorBuilding) {
            try {
                return this.f10076a.zzh(((IndoorBuilding) obj).f10076a);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        return false;
    }

    public int getActiveLevelIndex() {
        try {
            return this.f10076a.zzd();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getDefaultLevelIndex() {
        try {
            return this.f10076a.zze();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    @NonNull
    public List<IndoorLevel> getLevels() {
        try {
            List<IBinder> zzg = this.f10076a.zzg();
            ArrayList arrayList = new ArrayList(zzg.size());
            for (IBinder iBinder : zzg) {
                arrayList.add(new IndoorLevel(zzt.zzb(iBinder)));
            }
            return arrayList;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int hashCode() {
        try {
            return this.f10076a.zzf();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isUnderground() {
        try {
            return this.f10076a.zzi();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
