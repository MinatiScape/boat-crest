package com.google.android.gms.auth.api.accounttransfer;

import android.os.Bundle;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;
import java.util.ArrayList;
import java.util.Collections;
/* loaded from: classes6.dex */
public final class zzr implements Api.ApiOptions.Optional {
    public static final zzr zza;
    public final Bundle h;

    static {
        Bundle bundle = new Bundle();
        if (!bundle.containsKey("accountTypes")) {
            bundle.putStringArrayList("accountTypes", new ArrayList<>(0));
        }
        zza = new zzr(bundle, null);
    }

    public /* synthetic */ zzr(Bundle bundle, zzq zzqVar) {
        this.h = bundle;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean equals(java.lang.Object r7) {
        /*
            r6 = this;
            r0 = 1
            if (r7 != r6) goto L4
            return r0
        L4:
            boolean r1 = r7 instanceof com.google.android.gms.auth.api.accounttransfer.zzr
            r2 = 0
            if (r1 != 0) goto La
            return r2
        La:
            com.google.android.gms.auth.api.accounttransfer.zzr r7 = (com.google.android.gms.auth.api.accounttransfer.zzr) r7
            android.os.Bundle r1 = r6.zza()
            android.os.Bundle r7 = r7.zza()
            int r3 = r1.size()
            int r4 = r7.size()
            if (r3 == r4) goto L1f
            return r2
        L1f:
            java.util.Set r3 = r1.keySet()
            java.util.Iterator r3 = r3.iterator()
        L27:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L49
            java.lang.Object r4 = r3.next()
            java.lang.String r4 = (java.lang.String) r4
            boolean r5 = r7.containsKey(r4)
            if (r5 != 0) goto L3a
            return r2
        L3a:
            java.lang.Object r5 = r1.get(r4)
            java.lang.Object r4 = r7.get(r4)
            boolean r4 = com.google.android.gms.common.internal.Objects.equal(r5, r4)
            if (r4 != 0) goto L27
            return r2
        L49:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.accounttransfer.zzr.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        Bundle zza2 = zza();
        int size = zza2.size();
        ArrayList arrayList = new ArrayList(size + size);
        ArrayList arrayList2 = new ArrayList(zza2.keySet());
        Collections.sort(arrayList2);
        int size2 = arrayList2.size();
        for (int i = 0; i < size2; i++) {
            String str = (String) arrayList2.get(i);
            arrayList.add(str);
            arrayList.add(zza2.get(str));
        }
        return Objects.hashCode(arrayList);
    }

    public final Bundle zza() {
        return new Bundle(this.h);
    }
}
