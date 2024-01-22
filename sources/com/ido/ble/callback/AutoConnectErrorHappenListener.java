package com.ido.ble.callback;
/* loaded from: classes11.dex */
public class AutoConnectErrorHappenListener {

    /* loaded from: classes11.dex */
    public enum ErrorHappenType {
        NOT_FIND_DEVICE,
        GATT_ERROR_FIND_DEVICE,
        GATT_ERROR_OTHER,
        DISCOVER_SERVICE_FAILED,
        ENABLE_NOTIFY_FAILED,
        ENCRYPTED_FAILED
    }

    /* loaded from: classes11.dex */
    public interface IListener {
        void onErrorHappen(ErrorHappenType errorHappenType, String str);
    }

    public static void onErrorHappen(final ErrorHappenType errorHappenType, final String str) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.AutoConnectErrorHappenListener.1
            @Override // java.lang.Runnable
            public void run() {
                for (IListener iListener : b.N().e()) {
                    iListener.onErrorHappen(ErrorHappenType.this, str);
                }
            }
        });
    }
}
