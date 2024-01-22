package com.crrepa.ble.conn.callback;

import com.crrepa.ble.conn.bean.CRPPillReminderInfo;
import java.util.List;
/* loaded from: classes9.dex */
public interface CRPPillReminderCallback {
    void onPillReminder(int i, List<CRPPillReminderInfo> list);
}
