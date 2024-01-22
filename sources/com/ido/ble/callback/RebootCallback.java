package com.ido.ble.callback;
/* loaded from: classes11.dex */
public class RebootCallback {

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onFailed();

        void onSuccess();
    }

    public static final void a(final Boolean bool) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.RebootCallback.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().z()) {
                    if (bool.booleanValue()) {
                        iCallBack.onSuccess();
                    } else {
                        iCallBack.onFailed();
                    }
                }
            }
        });
    }
}
