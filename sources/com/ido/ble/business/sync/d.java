package com.ido.ble.business.sync;

import com.ido.ble.LocalDataManager;
import com.ido.ble.protocol.model.SupportFunctionInfo;
/* loaded from: classes11.dex */
class d {
    public static boolean a() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        if (supportFunctionInfo != null) {
            return supportFunctionInfo.is_need_sync_v2;
        }
        return true;
    }

    public static boolean b() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        return (supportFunctionInfo == null || !supportFunctionInfo.timeLine || supportFunctionInfo.ex_table_main9_v3_sports) ? false : true;
    }
}
