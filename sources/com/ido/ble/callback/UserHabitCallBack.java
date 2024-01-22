package com.ido.ble.callback;

import com.ido.ble.protocol.model.HabitInfo;
/* loaded from: classes11.dex */
public class UserHabitCallBack {

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onGetHabitInfo(HabitInfo habitInfo);
    }

    public static final void onGetHabitInfo(final HabitInfo habitInfo) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.UserHabitCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().K()) {
                    iCallBack.onGetHabitInfo(HabitInfo.this);
                }
            }
        });
    }
}
