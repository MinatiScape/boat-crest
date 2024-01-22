package com.ido.ble.callback;

import com.ido.ble.protocol.model.HornVoice;
/* loaded from: classes11.dex */
public class c {

    /* loaded from: classes11.dex */
    public static class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ HornVoice f12144a;

        public a(HornVoice hornVoice) {
            this.f12144a = hornVoice;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (b bVar : com.ido.ble.callback.b.N().p()) {
                bVar.a(this.f12144a);
            }
        }
    }

    /* loaded from: classes11.dex */
    public interface b {
        void a(HornVoice hornVoice);
    }

    public static void a(HornVoice hornVoice) {
        com.ido.ble.callback.b.N().a(new a(hornVoice));
    }
}
