package com.coveiot.android.leonardo.phonelocator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/* loaded from: classes5.dex */
public class PhoneFinderNotificationReciever extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        PhoneLocator.getInstance(context).stopPlayingAudio();
    }
}
