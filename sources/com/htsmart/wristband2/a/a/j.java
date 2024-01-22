package com.htsmart.wristband2.a.a;

import com.htsmart.wristband2.packet.PacketData;
import io.reactivex.ObservableEmitter;
import java.util.List;
/* loaded from: classes11.dex */
public class j extends f {
    public List<PacketData> b;

    public j(com.htsmart.wristband2.a.d.c cVar, List<PacketData> list) {
        super(cVar);
        this.b = list;
    }

    @Override // com.htsmart.wristband2.a.a.f
    public void a(ObservableEmitter observableEmitter, m mVar) throws Throwable {
        try {
            for (PacketData packetData : this.b) {
                this.f11933a.a(packetData, mVar);
            }
            mVar.c();
            observableEmitter.onComplete();
        } catch (Exception e) {
            mVar.c();
            observableEmitter.tryOnError(e);
        }
    }
}
