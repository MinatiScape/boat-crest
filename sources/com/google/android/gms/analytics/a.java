package com.google.android.gms.analytics;

import android.content.BroadcastReceiver;
/* loaded from: classes6.dex */
public final class a implements Runnable {
    public final /* synthetic */ BroadcastReceiver.PendingResult h;

    public a(CampaignTrackingReceiver campaignTrackingReceiver, BroadcastReceiver.PendingResult pendingResult) {
        this.h = pendingResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BroadcastReceiver.PendingResult pendingResult = this.h;
        if (pendingResult != null) {
            pendingResult.finish();
        }
    }
}
