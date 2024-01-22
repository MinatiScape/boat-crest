package com.google.android.gms.gcm;

import android.os.Bundle;
import androidx.annotation.CallSuper;
import com.google.android.gms.iid.zze;
import com.google.android.gms.internal.gcm.zzm;
import com.google.firebase.messaging.Constants;
import java.util.Iterator;
@Deprecated
/* loaded from: classes6.dex */
public class GcmListenerService extends zze {
    public com.google.android.gms.internal.gcm.zzl m = zzm.zzdk;

    public static void c(Bundle bundle) {
        Iterator<String> it = bundle.keySet().iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next != null && next.startsWith(Constants.MessagePayloadKeys.RESERVED_CLIENT_LIB_PREFIX)) {
                it.remove();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:65:0x0105, code lost:
        if (r3.importance == 100) goto L60;
     */
    @Override // com.google.android.gms.iid.zze
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void handleIntent(android.content.Intent r9) {
        /*
            Method dump skipped, instructions count: 430
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.gcm.GcmListenerService.handleIntent(android.content.Intent):void");
    }

    @Override // android.app.Service
    @CallSuper
    public void onCreate() {
        super.onCreate();
        zzm.zzab();
        this.m = zzm.zzdk;
    }

    public void onDeletedMessages() {
    }

    public void onMessageReceived(String str, Bundle bundle) {
    }

    public void onMessageSent(String str) {
    }

    public void onSendError(String str, String str2) {
    }
}
