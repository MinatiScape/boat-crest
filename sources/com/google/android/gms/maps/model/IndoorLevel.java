package com.google.android.gms.maps.model;

import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zzu;
/* loaded from: classes10.dex */
public final class IndoorLevel {

    /* renamed from: a  reason: collision with root package name */
    public final zzu f10077a;

    public IndoorLevel(zzu zzuVar) {
        this.f10077a = (zzu) Preconditions.checkNotNull(zzuVar);
    }

    public void activate() {
        try {
            this.f10077a.zzg();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof IndoorLevel) {
            try {
                return this.f10077a.zzh(((IndoorLevel) obj).f10077a);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        return false;
    }

    @NonNull
    public String getName() {
        try {
            return this.f10077a.zze();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    @NonNull
    public String getShortName() {
        try {
            return this.f10077a.zzf();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int hashCode() {
        try {
            return this.f10077a.zzd();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
