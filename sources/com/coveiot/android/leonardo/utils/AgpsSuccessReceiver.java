package com.coveiot.android.leonardo.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.coveiot.android.leonardo.model.AgpsFileUpdateModel;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.LogHelper;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class AgpsSuccessReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(@Nullable Context context, @Nullable Intent intent) {
        LogHelper.d("agpsreceiver", " received**** ");
        CoveEventBusManager.getInstance().getEventBus().post(new AgpsFileUpdateModel());
    }
}
