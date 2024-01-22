package com.ido.ble.callback;
/* loaded from: classes11.dex */
public class UnbindCallBack {

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onFailed();

        void onSuccess();
    }

    public static void a() {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.UnbindCallBack.2
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().I()) {
                    iCallBack.onFailed();
                }
            }
        });
    }

    public static void b() {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.UnbindCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().I()) {
                    iCallBack.onSuccess();
                }
            }
        });
    }
}
