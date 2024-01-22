package com.google.android.gms.internal.firebase_ml;

import java.util.Collection;
import java.util.HashSet;
/* loaded from: classes7.dex */
public final class zzic {

    /* renamed from: a  reason: collision with root package name */
    public final zzhx f8776a;
    public Collection<String> b = new HashSet();

    public zzic(zzhx zzhxVar) {
        this.f8776a = (zzhx) zzml.checkNotNull(zzhxVar);
    }

    public final zzic zza(Collection<String> collection) {
        this.b = collection;
        return this;
    }

    public final zzhz zzhp() {
        return new zzhz(this);
    }
}
