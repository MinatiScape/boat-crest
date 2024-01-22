package com.ido.ble.callback;
/* loaded from: classes11.dex */
public class DeviceUpgradeEventListener {

    /* loaded from: classes11.dex */
    public interface IListener {
        void APOLLO_onSOLibError(int i);

        void NODIC_onProgress(int i);
    }

    public static void APOLLO_onSOLibError(final int i) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.DeviceUpgradeEventListener.2
            @Override // java.lang.Runnable
            public void run() {
                for (IListener iListener : b.N().o()) {
                    iListener.APOLLO_onSOLibError(i);
                }
            }
        });
    }

    public static void NODIC_onProgress(final int i) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.DeviceUpgradeEventListener.1
            @Override // java.lang.Runnable
            public void run() {
                for (IListener iListener : b.N().o()) {
                    iListener.NODIC_onProgress(i);
                }
            }
        });
    }
}
