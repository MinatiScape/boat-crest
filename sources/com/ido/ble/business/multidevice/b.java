package com.ido.ble.business.multidevice;

import android.os.Handler;
import android.os.Looper;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.UnbindCallBack;
/* loaded from: classes11.dex */
public class b {

    /* loaded from: classes11.dex */
    public static class a implements UnbindCallBack.ICallBack {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ ICommonListener f12118a;
        public final /* synthetic */ String b;
        public final /* synthetic */ Handler c;

        public a(ICommonListener iCommonListener, String str, Handler handler) {
            this.f12118a = iCommonListener;
            this.b = str;
            this.c = handler;
        }

        @Override // com.ido.ble.callback.UnbindCallBack.ICallBack
        public void onFailed() {
            com.ido.ble.callback.b.N().b(this);
            this.f12118a.onFailed(this.b);
            this.c.removeCallbacksAndMessages(null);
        }

        @Override // com.ido.ble.callback.UnbindCallBack.ICallBack
        public void onSuccess() {
            com.ido.ble.bluetooth.f.c.g().e("");
            com.ido.ble.callback.b.N().b(this);
            this.f12118a.onSuccess(this.b);
            this.c.removeCallbacksAndMessages(null);
        }
    }

    /* renamed from: com.ido.ble.business.multidevice.b$b  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static class RunnableC0578b implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ ICommonListener f12119a;
        public final /* synthetic */ String b;

        public RunnableC0578b(ICommonListener iCommonListener, String str) {
            this.f12119a = iCommonListener;
            this.b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f12119a.onFailed(this.b);
        }
    }

    private static void a(String str) {
        com.ido.ble.f.a.f.a.f(str).c();
        com.ido.ble.bluetooth.f.c.g(str).b();
        com.ido.ble.f.a.f.b.e().d(str);
    }

    public static void a(String str, ICommonListener iCommonListener) {
        BLEDevice lastConnectedDeviceInfo = LocalDataManager.getLastConnectedDeviceInfo();
        if (lastConnectedDeviceInfo == null) {
            iCommonListener.onFailed(str);
        } else if (str.equals(lastConnectedDeviceInfo.mDeviceAddress)) {
            b(str, iCommonListener);
        } else {
            a(str);
            iCommonListener.onSuccess(str);
        }
    }

    private static void b(String str, ICommonListener iCommonListener) {
        Handler handler = new Handler(Looper.getMainLooper());
        com.ido.ble.callback.b.N().a(new a(iCommonListener, str, handler));
        com.ido.ble.i.a.a.F0();
        handler.postDelayed(new RunnableC0578b(iCommonListener, str), 10000L);
    }
}
