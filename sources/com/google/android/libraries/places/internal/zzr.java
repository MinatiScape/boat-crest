package com.google.android.libraries.places.internal;

import android.net.wifi.ScanResult;
import java.util.Comparator;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final /* synthetic */ class zzr implements Comparator {
    public static final Comparator zza = new zzr();

    private zzr() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return zzs.zza((ScanResult) obj, (ScanResult) obj2);
    }
}
