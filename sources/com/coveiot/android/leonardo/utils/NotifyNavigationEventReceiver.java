package com.coveiot.android.leonardo.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.coveiot.android.boat.GenericBandApplication;
import com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager;
import com.coveiot.android.leonardo.performance.Constants;
import com.coveiot.android.navigation.activities.ActivityDirections;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.sdk.ble.api.response.NotifyNavigationEventRes;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import com.mappls.sdk.navigation.MapplsNavigationHelper;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class NotifyNavigationEventReceiver extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final int f5430a = Constants.STRESS_NOTIFICATION_ID;

    @Override // android.content.BroadcastReceiver
    public void onReceive(@Nullable Context context, @NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (kotlin.text.m.equals$default(intent.getAction(), com.coveiot.android.bleabstract.Constants.NOTIFY_NAVIGATION_EVENT_BROADCAST_INTENT, false, 2, null) && intent.hasExtra(com.coveiot.android.bleabstract.Constants.NOTIFY_NAVIGATION_EVENT_BROADCAST_INTENT_EXTRA)) {
            Bundle extras = intent.getExtras();
            Intrinsics.checkNotNull(extras);
            Serializable serializable = extras.getSerializable(com.coveiot.android.bleabstract.Constants.NOTIFY_NAVIGATION_EVENT_BROADCAST_INTENT_EXTRA);
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.sdk.ble.api.response.NotifyNavigationEventRes");
            NotifyNavigationEventRes notifyNavigationEventRes = (NotifyNavigationEventRes) serializable;
            LogHelper.d("NotifyNavigationEventReceiver", new Gson().toJson(notifyNavigationEventRes), ModuleNames.BLEABSTRACT.getModuleName());
            if (notifyNavigationEventRes.getEvent() == 2) {
                SessionManager.getInstance(context).setNavigationFinishActivity(true);
                MapplsNavigationHelper.getInstance().stopNavigation();
                return;
            }
            Context applicationContext = context != null ? context.getApplicationContext() : null;
            Intrinsics.checkNotNull(applicationContext, "null cannot be cast to non-null type com.coveiot.android.boat.GenericBandApplication");
            if (!((GenericBandApplication) applicationContext).isAppTerminated()) {
                Intent intent2 = new Intent(context, ActivityDirections.class);
                intent2.putExtra("isFromBand", true);
                intent2.putExtra("placeIdOnBand", notifyNavigationEventRes.getPlaceId());
                intent2.putExtra("modeOnBand", notifyNavigationEventRes.getMode());
                intent2.setFlags(268468224);
                context.startActivity(intent2);
                return;
            }
            NotificationManager.getInstance().notifyStartNavigationRequest(context, this.f5430a, notifyNavigationEventRes);
        }
    }
}
