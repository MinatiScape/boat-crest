package com.google.android.gms.tagmanager;

import android.os.Looper;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tagmanager.ContainerHolder;
@VisibleForTesting
/* loaded from: classes10.dex */
public final class zzaa implements ContainerHolder {
    public final Looper zza;
    public Container zzb;
    public Container zzc;
    public final Status zzd;
    public zzz zze;
    public zzy zzf;
    public boolean zzg;
    public TagManager zzh;

    public zzaa(Status status) {
        this.zzd = status;
        this.zza = null;
    }

    public zzaa(TagManager tagManager, Looper looper, Container container, zzy zzyVar) {
        this.zzh = tagManager;
        this.zza = looper == null ? Looper.getMainLooper() : looper;
        this.zzb = container;
        this.zzf = zzyVar;
        this.zzd = Status.RESULT_SUCCESS;
        tagManager.zza(this);
    }

    @Override // com.google.android.gms.tagmanager.ContainerHolder
    public final synchronized Container getContainer() {
        if (this.zzg) {
            zzdh.zza("ContainerHolder is released.");
            return null;
        }
        Container container = this.zzc;
        if (container != null) {
            this.zzb = container;
            this.zzc = null;
        }
        return this.zzb;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzd;
    }

    @Override // com.google.android.gms.tagmanager.ContainerHolder
    public final synchronized void refresh() {
        if (this.zzg) {
            zzdh.zza("Refreshing a released ContainerHolder.");
        } else {
            this.zzf.zzb();
        }
    }

    @Override // com.google.android.gms.common.api.Releasable
    public final synchronized void release() {
        if (this.zzg) {
            zzdh.zza("Releasing a released ContainerHolder.");
            return;
        }
        this.zzg = true;
        this.zzh.zzc(this);
        this.zzb.zze();
        this.zzb = null;
        this.zzc = null;
        this.zzf = null;
        this.zze = null;
    }

    @Override // com.google.android.gms.tagmanager.ContainerHolder
    public final synchronized void setContainerAvailableListener(ContainerHolder.ContainerAvailableListener containerAvailableListener) {
        if (this.zzg) {
            zzdh.zza("ContainerHolder is released.");
        } else if (containerAvailableListener == null) {
            this.zze = null;
        } else {
            this.zze = new zzz(this, containerAvailableListener, this.zza);
            if (this.zzc != null) {
                zzf();
            }
        }
    }

    public final String zza() {
        if (this.zzg) {
            zzdh.zza("getContainerId called on a released ContainerHolder.");
            return "";
        }
        return this.zzb.getContainerId();
    }

    public final String zzb() {
        if (this.zzg) {
            zzdh.zza("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
            return "";
        }
        return this.zzf.zza();
    }

    public final synchronized void zzc(Container container) {
        if (this.zzg) {
            return;
        }
        this.zzc = container;
        zzf();
    }

    public final synchronized void zzd(String str) {
        if (this.zzg) {
            return;
        }
        this.zzb.zzd(str);
    }

    public final void zze(String str) {
        if (this.zzg) {
            zzdh.zza("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        } else {
            this.zzf.zzc(str);
        }
    }

    public final void zzf() {
        zzz zzzVar = this.zze;
        if (zzzVar != null) {
            zzzVar.sendMessage(zzzVar.obtainMessage(1, this.zzc.zzc()));
        }
    }
}
