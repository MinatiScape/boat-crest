package com.coveiot.android.leonardo.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.coveiot.android.boat.GenericBandApplication;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager;
import com.coveiot.android.leonardo.performance.Constants;
import com.coveiot.android.leonardo.remotecamera.CameraActivity;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.leaderboard.eventbus.CloveEventBusManager;
import com.coveiot.sdk.ble.api.model.CameraState;
import com.coveiot.sdk.ble.api.response.CameraEventRes;
import com.coveiot.sdk.ble.helper.LogsHelper;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class CameraEventReceiver extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final int f5419a = Constants.STRESS_NOTIFICATION_ID;

    public final void a(Context context) {
        if (UserDataManager.getInstance(context).isCameraLaunched().booleanValue()) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNull(applicationContext, "null cannot be cast to non-null type com.coveiot.android.boat.GenericBandApplication");
        if (!((GenericBandApplication) applicationContext).isAppTerminated()) {
            Intent intent = new Intent(context, CameraActivity.class);
            intent.addFlags(268435456);
            context.startActivity(intent);
            return;
        }
        NotificationManager.getInstance().notifyRemoteCameraOpenRequest(context, this.f5419a);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NotNull Context context, @NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (companion.isIDODevice(context) || companion.isTouchELXDevice(context) || !intent.hasExtra(com.coveiot.android.bleabstract.Constants.CAMERA_BROADCAST_INTENT_EXTRA)) {
            return;
        }
        Bundle extras = intent.getExtras();
        Intrinsics.checkNotNull(extras);
        Serializable serializable = extras.getSerializable(com.coveiot.android.bleabstract.Constants.CAMERA_BROADCAST_INTENT_EXTRA);
        Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.sdk.ble.api.response.CameraEventRes");
        CameraEventRes cameraEventRes = (CameraEventRes) serializable;
        LogsHelper.d("CameraEventRes", cameraEventRes.toString());
        if (cameraEventRes.getState() == CameraState.ENTER) {
            if (UserDataManager.getInstance(context).isCameraLaunched().booleanValue()) {
                return;
            }
            Context applicationContext = context.getApplicationContext();
            Intrinsics.checkNotNull(applicationContext, "null cannot be cast to non-null type com.coveiot.android.boat.GenericBandApplication");
            if (!((GenericBandApplication) applicationContext).isAppTerminated()) {
                Intent intent2 = new Intent(context, CameraActivity.class);
                intent2.addFlags(268435456);
                context.startActivity(intent2);
                return;
            }
            NotificationManager.getInstance().notifyRemoteCameraOpenRequest(context, this.f5419a);
        } else if (cameraEventRes.getState() == CameraState.EXIT) {
            Boolean isCameraLaunched = UserDataManager.getInstance(context).isCameraLaunched();
            Intrinsics.checkNotNullExpressionValue(isCameraLaunched, "getInstance(context).isCameraLaunched");
            if (isCameraLaunched.booleanValue()) {
                CloveEventBusManager.getInstance().getEventBus().post(cameraEventRes);
            }
        } else if (cameraEventRes.getState() == CameraState.CAPTURE) {
            Boolean isCameraLaunched2 = UserDataManager.getInstance(context).isCameraLaunched();
            Intrinsics.checkNotNullExpressionValue(isCameraLaunched2, "getInstance(context).isCameraLaunched");
            if (isCameraLaunched2.booleanValue()) {
                CloveEventBusManager.getInstance().getEventBus().post(cameraEventRes);
            } else {
                a(context);
            }
        }
    }
}
