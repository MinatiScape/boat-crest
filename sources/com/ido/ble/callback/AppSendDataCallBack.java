package com.ido.ble.callback;
/* loaded from: classes11.dex */
public class AppSendDataCallBack {

    /* loaded from: classes11.dex */
    public enum DataType {
        WEATHER,
        WEATHER_V3,
        WEATHER_SUN_TIME
    }

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onFailed(DataType dataType);

        void onSuccess(DataType dataType);
    }

    public static void a(final boolean z, final DataType dataType) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.AppSendDataCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().d()) {
                    if (z) {
                        iCallBack.onSuccess(dataType);
                    } else {
                        iCallBack.onFailed(dataType);
                    }
                }
            }
        });
    }
}
