package com.ido.ble.callback;
/* loaded from: classes11.dex */
public class BindCallBack {

    /* loaded from: classes11.dex */
    public enum BindFailedError {
        ERROR_OTHER,
        ERROR_DEVICE_ALREADY_IN_BIND_STATE,
        ERROR_ENCRYPTED
    }

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onCancel();

        void onFailed(BindFailedError bindFailedError);

        void onNeedAuth(int i);

        void onReject();

        void onSuccess();
    }

    public static final void a() {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.BindCallBack.4
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().f()) {
                    iCallBack.onCancel();
                }
            }
        });
    }

    public static final void a(final int i) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.BindCallBack.6
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().f()) {
                    iCallBack.onNeedAuth(i);
                }
            }
        });
    }

    public static final void a(final BindFailedError bindFailedError) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.BindCallBack.2
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().f()) {
                    iCallBack.onFailed(BindFailedError.this);
                }
            }
        });
    }

    public static final void b() {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.BindCallBack.3
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().f()) {
                    iCallBack.onFailed(BindFailedError.ERROR_OTHER);
                }
            }
        });
    }

    public static final void c() {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.BindCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().f()) {
                    iCallBack.onSuccess();
                }
            }
        });
    }

    public static final void d() {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.BindCallBack.5
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().f()) {
                    iCallBack.onReject();
                }
            }
        });
    }
}
