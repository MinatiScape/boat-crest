package com.ido.ble.callback;
/* loaded from: classes11.dex */
public class e {

    /* loaded from: classes11.dex */
    public static class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            for (b bVar : com.ido.ble.callback.b.N().J()) {
                bVar.a();
            }
        }
    }

    /* loaded from: classes11.dex */
    public interface b {
        void a();
    }

    public static final void a() {
        com.ido.ble.callback.b.N().a(new a());
    }
}
