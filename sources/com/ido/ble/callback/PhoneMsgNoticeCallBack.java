package com.ido.ble.callback;
/* loaded from: classes11.dex */
public class PhoneMsgNoticeCallBack {

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onCalling();

        void onNewMessage();

        void onStopCall();

        @Deprecated
        void onUnReadMessage();

        void onV3MessageNotice(int i);
    }

    public static void a() {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.PhoneMsgNoticeCallBack.2
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().x()) {
                    iCallBack.onCalling();
                }
            }
        });
    }

    public static void a(final int i) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.PhoneMsgNoticeCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().x()) {
                    iCallBack.onV3MessageNotice(i);
                }
            }
        });
    }

    public static void b() {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.PhoneMsgNoticeCallBack.4
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().x()) {
                    iCallBack.onNewMessage();
                }
            }
        });
    }

    public static void c() {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.PhoneMsgNoticeCallBack.3
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().x()) {
                    iCallBack.onStopCall();
                }
            }
        });
    }

    @Deprecated
    public static void d() {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.PhoneMsgNoticeCallBack.5
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().x()) {
                    iCallBack.onUnReadMessage();
                }
            }
        });
    }
}
