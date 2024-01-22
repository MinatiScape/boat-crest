package com.coveiot.android.leonardo.utils;

import android.content.Context;
import com.jieli.bluetooth_connect.util.JL_Log;
/* loaded from: classes5.dex */
public class JL_LogUtils {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f5424a = false;

    /* loaded from: classes5.dex */
    public class a implements JL_Log.ILogOutput {
        @Override // com.jieli.bluetooth_connect.util.JL_Log.ILogOutput
        public void output(String str) {
            com.jieli.jl_bt_ota.util.JL_Log.addLogOutput(str);
        }
    }

    public static void disableLog(Context context) {
        f5424a = false;
        com.jieli.jl_bt_ota.util.JL_Log.setLog(false);
        com.jieli.jl_bt_ota.util.JL_Log.setIsSaveLogFile(context, false);
        JL_Log.setLog(false);
        JL_Log.setLogOutput(null);
    }

    public static void enableLog(Context context) {
        if (f5424a) {
            return;
        }
        f5424a = true;
        com.jieli.jl_bt_ota.util.JL_Log.setLog(true);
        com.jieli.jl_bt_ota.util.JL_Log.setIsSaveLogFile(context, true);
        JL_Log.setLog(true);
        JL_Log.setLogOutput(new a());
    }
}
