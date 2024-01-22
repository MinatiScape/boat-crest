package com.ido.ble.callback;
/* loaded from: classes11.dex */
public class EnterDfuModeCallback {

    /* loaded from: classes11.dex */
    public enum DfuError {
        LOW_BATTERY,
        DEVICE_NOT_SUPPORT,
        PARA_ERROR,
        PARA_OTHER
    }

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onError(DfuError dfuError);

        void onSuccess();
    }

    public static void a() {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.EnterDfuModeCallback.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().q()) {
                    iCallBack.onSuccess();
                }
            }
        });
    }

    public static void a(final DfuError dfuError) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.EnterDfuModeCallback.2
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().q()) {
                    iCallBack.onError(DfuError.this);
                }
            }
        });
    }
}
