package com.ido.ble.callback;
/* loaded from: classes11.dex */
public class DeviceLogCallBack {

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onGetHeatLog(String str);
    }

    public static void a(final String str) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.DeviceLogCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().l()) {
                    iCallBack.onGetHeatLog(str);
                }
            }
        });
    }
}
