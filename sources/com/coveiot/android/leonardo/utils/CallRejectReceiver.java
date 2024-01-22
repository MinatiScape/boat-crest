package com.coveiot.android.leonardo.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.telecom.TelecomManager;
import androidx.core.content.ContextCompat;
import com.android.internal.telephony.ITelephony;
import com.coveiot.android.bleabstract.Constants;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.request.CallStatusType;
import com.coveiot.android.bleabstract.request.SendCallStatusRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.CallRejectRes;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.sdk.ble.helper.LogsHelper;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class CallRejectReceiver extends BroadcastReceiver {
    public final void a(Context context) {
        if (Build.VERSION.SDK_INT >= 28) {
            if (ContextCompat.checkSelfPermission(context, "android.permission.ANSWER_PHONE_CALLS") != 0) {
                return;
            }
            Object systemService = context.getSystemService("telecom");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.telecom.TelecomManager");
            ((TelecomManager) systemService).acceptRingingCall();
        } else if (ContextCompat.checkSelfPermission(context, "android.permission.CALL_PHONE") != 0) {
        } else {
            try {
                try {
                    Object invoke = Class.forName("android.os.ServiceManager").getMethod("getService", String.class).invoke(null, "phone");
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.os.IBinder");
                    ITelephony.Stub.asInterface((IBinder) invoke).answerRingingCall();
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (SecurityException e2) {
                    e2.printStackTrace();
                }
            } catch (ClassNotFoundException e3) {
                e3.printStackTrace();
            } catch (IllegalAccessException e4) {
                e4.printStackTrace();
            } catch (NoSuchMethodException e5) {
                e5.printStackTrace();
            } catch (InvocationTargetException e6) {
                e6.printStackTrace();
            }
        }
    }

    public final void b(Context context) {
        if (Build.VERSION.SDK_INT >= 28) {
            if (ContextCompat.checkSelfPermission(context, "android.permission.ANSWER_PHONE_CALLS") != 0) {
                return;
            }
            Object systemService = context.getSystemService("telecom");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.telecom.TelecomManager");
            ((TelecomManager) systemService).endCall();
        } else if (ContextCompat.checkSelfPermission(context, "android.permission.CALL_PHONE") != 0) {
        } else {
            try {
                try {
                    Object invoke = Class.forName("android.os.ServiceManager").getMethod("getService", String.class).invoke(null, "phone");
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.os.IBinder");
                    ITelephony.Stub.asInterface((IBinder) invoke).endCall();
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (SecurityException e2) {
                    e2.printStackTrace();
                }
            } catch (ClassNotFoundException e3) {
                e3.printStackTrace();
            } catch (IllegalAccessException e4) {
                e4.printStackTrace();
            } catch (NoSuchMethodException e5) {
                e5.printStackTrace();
            } catch (InvocationTargetException e6) {
                e6.printStackTrace();
            }
        }
    }

    public final void c(Context context, CallStatusType callStatusType) {
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if ((!companion.isTouchELXDevice(context) && !companion.isEastApexDevice(context)) || BleApiManager.getInstance(context) == null || BleApiManager.getInstance(context).getBleApi() == null) {
            return;
        }
        BleApiManager.getInstance(context).getBleApi().setUserSettings(new SendCallStatusRequest(callStatusType), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.utils.CallRejectReceiver$sendCallSyncStatus$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogsHelper.d("sendCallSyncStatus", "Success");
            }
        });
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NotNull Context context, @NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (intent.hasExtra(Constants.CALL_REJECT__BROADCAST_INTENT_EXTRA)) {
            Bundle extras = intent.getExtras();
            Intrinsics.checkNotNull(extras);
            Serializable serializable = extras.getSerializable(Constants.CALL_REJECT__BROADCAST_INTENT_EXTRA);
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.CallRejectRes");
            CallRejectRes callRejectRes = (CallRejectRes) serializable;
            LogsHelper.d("CallRejectRes", callRejectRes.toString());
            if (callRejectRes.shouldReject) {
                b(context);
                c(context, CallStatusType.REJECT);
                return;
            }
            a(context);
            c(context, CallStatusType.ACCEPT);
        }
    }
}
