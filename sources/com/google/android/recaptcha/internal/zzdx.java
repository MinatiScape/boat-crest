package com.google.android.recaptcha.internal;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Objects;
import java.util.Queue;
/* loaded from: classes10.dex */
public final class zzdx extends zzeb implements Serializable {
    public final int zza;
    private final Queue zzb;

    private zzdx(int i) {
        if (i >= 0) {
            this.zzb = new ArrayDeque(i);
            this.zza = i;
            return;
        }
        throw new IllegalArgumentException(zzdu.zza("maxSize (%s) must >= 0", Integer.valueOf(i)));
    }

    public static zzdx zza(int i) {
        return new zzdx(i);
    }

    @Override // com.google.android.recaptcha.internal.zzdz, java.util.Collection, java.util.Queue
    public final boolean add(Object obj) {
        Objects.requireNonNull(obj);
        if (this.zza == 0) {
            return true;
        }
        if (size() == this.zza) {
            this.zzb.remove();
        }
        this.zzb.add(obj);
        return true;
    }

    @Override // com.google.android.recaptcha.internal.zzdz, java.util.Collection
    public final boolean addAll(Collection collection) {
        int size = collection.size();
        if (size >= this.zza) {
            clear();
            int i = size - this.zza;
            zzdr.zzb(i >= 0, "number to skip cannot be negative");
            return zzee.zza(this, new zzed(collection, i).iterator());
        }
        return zzee.zza(this, collection.iterator());
    }

    @Override // com.google.android.recaptcha.internal.zzeb, java.util.Queue
    public final boolean offer(Object obj) {
        add(obj);
        return true;
    }

    @Override // com.google.android.recaptcha.internal.zzdz, com.google.android.recaptcha.internal.zzea
    public final /* synthetic */ Object zzb() {
        return this.zzb;
    }

    @Override // com.google.android.recaptcha.internal.zzeb, com.google.android.recaptcha.internal.zzdz
    public final /* synthetic */ Collection zzc() {
        return this.zzb;
    }

    @Override // com.google.android.recaptcha.internal.zzeb
    public final Queue zzd() {
        return this.zzb;
    }
}
