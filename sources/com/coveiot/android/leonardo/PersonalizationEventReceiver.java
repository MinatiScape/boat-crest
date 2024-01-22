package com.coveiot.android.leonardo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.coveiot.android.bleabstract.Constants;
import com.coveiot.sdk.ble.api.response.SendPersonalizedMessageRes;
import com.coveiot.utils.utility.LogHelper;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class PersonalizationEventReceiver extends BroadcastReceiver {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f4644a = "PersonalizationEventReceiver";

    @NotNull
    public final String getTAG() {
        return this.f4644a;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NotNull Context context, @NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (intent.hasExtra(Constants.PERSONALIZED_MESSAGE_BROADCAST_INTENT_EXTRA)) {
            Bundle extras = intent.getExtras();
            Intrinsics.checkNotNull(extras);
            Serializable serializable = extras.getSerializable(Constants.PERSONALIZED_MESSAGE_BROADCAST_INTENT_EXTRA);
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.sdk.ble.api.response.SendPersonalizedMessageRes");
            SendPersonalizedMessageRes sendPersonalizedMessageRes = (SendPersonalizedMessageRes) serializable;
            LogHelper.d(this.f4644a, sendPersonalizedMessageRes.toString());
            if (sendPersonalizedMessageRes.getButtonAction() != 2) {
                return;
            }
            String str = this.f4644a;
            LogHelper.d(str, "buttonId == " + sendPersonalizedMessageRes.getButtonId());
        }
    }
}
