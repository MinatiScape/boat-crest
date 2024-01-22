package com.google.android.gms.internal.fido;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender;
import androidx.annotation.Nullable;
import com.google.android.gms.fido.fido2.Fido2PendingIntent;
@Deprecated
/* loaded from: classes7.dex */
public final class zzi implements Fido2PendingIntent {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final PendingIntent f8639a;

    public zzi(@Nullable PendingIntent pendingIntent) {
        this.f8639a = pendingIntent;
    }

    @Override // com.google.android.gms.fido.fido2.Fido2PendingIntent
    public final boolean hasPendingIntent() {
        return this.f8639a != null;
    }

    @Override // com.google.android.gms.fido.fido2.Fido2PendingIntent
    public final void launchPendingIntent(Activity activity, int i) throws IntentSender.SendIntentException {
        PendingIntent pendingIntent = this.f8639a;
        if (pendingIntent != null) {
            activity.startIntentSenderForResult(pendingIntent.getIntentSender(), i, null, 0, 0, 0);
            return;
        }
        throw new IllegalStateException("No PendingIntent available");
    }
}
