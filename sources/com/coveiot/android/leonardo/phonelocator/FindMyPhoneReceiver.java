package com.coveiot.android.leonardo.phonelocator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.coveiot.android.bleabstract.Constants;
import com.coveiot.sdk.ble.api.response.FindMyPhoneRes;
import com.coveiot.sdk.ble.helper.LogsHelper;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class FindMyPhoneReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(@NotNull Context context, @NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (intent.hasExtra(Constants.FIND_MY_PHONE_BROADCAST_INTENT_EXTRA)) {
            Bundle extras = intent.getExtras();
            Intrinsics.checkNotNull(extras);
            Serializable serializable = extras.getSerializable(Constants.FIND_MY_PHONE_BROADCAST_INTENT_EXTRA);
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.sdk.ble.api.response.FindMyPhoneRes");
            FindMyPhoneRes findMyPhoneRes = (FindMyPhoneRes) serializable;
            LogsHelper.d("FindMyPhoneReceiver", findMyPhoneRes.toString());
            String findMyPhoneRes2 = findMyPhoneRes.toString();
            Intrinsics.checkNotNullExpressionValue(findMyPhoneRes2, "findMyPhoneRes.toString()");
            if (StringsKt__StringsKt.contains$default((CharSequence) findMyPhoneRes2, (CharSequence) "ON", false, 2, (Object) null)) {
                PhoneLocator.getInstance(context).managePhoneFinderRingtone();
            } else {
                PhoneLocator.getInstance(context).stopPlayingAudio();
            }
        }
    }
}
