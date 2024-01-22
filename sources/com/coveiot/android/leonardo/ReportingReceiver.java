package com.coveiot.android.leonardo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.dashboard2.StatusApiDataHelper;
import com.coveiot.utils.utility.LogHelper;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class ReportingReceiver extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final int f4645a;

    public final int getConnectivityStatus(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            if (activeNetworkInfo.getType() == 1) {
                return 1;
            }
            if (activeNetworkInfo.getType() == 0) {
                return 0;
            }
        }
        return this.f4645a;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NotNull Context context, @NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        String action = intent.getAction();
        Intrinsics.checkNotNull(action);
        if (action.equals("android.location.PROVIDERS_CHANGED")) {
            StringBuilder sb = new StringBuilder();
            sb.append(" Gps status");
            Object systemService = context.getSystemService(FirebaseAnalytics.Param.LOCATION);
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.location.LocationManager");
            sb.append(((LocationManager) systemService).isProviderEnabled("gps"));
            LogHelper.d("ReportingReceiver", sb.toString());
        } else {
            String action2 = intent.getAction();
            Intrinsics.checkNotNull(action2);
            if (!action2.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                String action3 = intent.getAction();
                Intrinsics.checkNotNull(action3);
                if (!action3.equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                    String action4 = intent.getAction();
                    Intrinsics.checkNotNull(action4);
                    if (action4.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                        int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                        if (intExtra == 10) {
                            LogHelper.d("ReportingReceiver", "Bluetooth turned Off");
                        } else if (intExtra == 12) {
                            LogHelper.d("ReportingReceiver", "Bluetooth turned On");
                            if (BleApiManager.getInstance(context).getBleApi() != null) {
                                if (!BleApiManager.getInstance(context).getBleApi().isBluetoothServiceRunning()) {
                                    LogHelper.d("ReportingReceiver", "Bluetooth turned On restarting the service");
                                    BleApiManager.getInstance(context).getBleApi().restartService();
                                } else {
                                    LogHelper.d("ReportingReceiver", "Bluetooth turned On service is already running");
                                }
                            } else {
                                LogHelper.d("ReportingReceiver", "Bluetooth turned On Ble interface not available");
                            }
                        }
                    }
                }
            }
            LogHelper.d("ReportingReceiver", " Network state" + getConnectivityStatus(context));
        }
        StatusApiDataHelper.Companion.getInstance(context).saveStatusDataToServer(true);
    }
}
