package com.htsmart.wristband2.a.a;

import com.htsmart.wristband2.packet.PacketData;
import io.reactivex.ObservableEmitter;
/* loaded from: classes11.dex */
public class l extends f {
    public PacketData b;

    public l(com.htsmart.wristband2.a.d.c cVar, PacketData packetData) {
        super(cVar);
        this.b = packetData;
    }

    @Override // com.htsmart.wristband2.a.a.f
    public void a(ObservableEmitter observableEmitter, m mVar) throws Throwable {
        try {
            this.f11933a.a(this.b, mVar);
            mVar.c();
            observableEmitter.onComplete();
        } catch (Exception e) {
            mVar.c();
            observableEmitter.tryOnError(e);
        }
    }
}
