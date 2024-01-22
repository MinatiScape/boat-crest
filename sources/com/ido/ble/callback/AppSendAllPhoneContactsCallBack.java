package com.ido.ble.callback;
/* loaded from: classes11.dex */
public class AppSendAllPhoneContactsCallBack {

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onCallBack(String str);
    }

    public static void onCallBack(final String str) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.AppSendAllPhoneContactsCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().c()) {
                    iCallBack.onCallBack(str);
                }
            }
        });
    }
}
