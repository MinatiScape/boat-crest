package com.ido.ble.callback;
/* loaded from: classes11.dex */
public class DeviceResponseCommonCallBack {

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onResponse(int i, String str);
    }

    @Deprecated
    public static void onResponse(final int i, final String str) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.DeviceResponseCommonCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().n()) {
                    iCallBack.onResponse(i, str);
                }
            }
        });
    }
}
