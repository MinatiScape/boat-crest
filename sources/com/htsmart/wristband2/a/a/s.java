package com.htsmart.wristband2.a.a;

import com.htsmart.wristband2.packet.PacketData;
import io.reactivex.ObservableEmitter;
import io.reactivex.functions.Predicate;
import java.util.concurrent.TimeUnit;
/* loaded from: classes11.dex */
public class s extends f<PacketData> {
    public PacketData b;
    public PacketData c;

    /* loaded from: classes11.dex */
    public class a implements Predicate<PacketData> {
        public a() {
        }

        @Override // io.reactivex.functions.Predicate
        /* renamed from: a */
        public boolean test(PacketData packetData) throws Exception {
            return packetData.getCmdId() == s.this.c.getCmdId() && packetData.getKeyId() == s.this.c.getKeyId();
        }
    }

    public s(com.htsmart.wristband2.a.d.c cVar, PacketData packetData, PacketData packetData2) {
        super(cVar);
        this.b = packetData;
        this.c = packetData2;
    }

    @Override // com.htsmart.wristband2.a.a.f
    public void a(ObservableEmitter<PacketData> observableEmitter, m mVar) throws Throwable {
        n nVar = new n(observableEmitter, mVar);
        this.f11933a.j().filter(new a()).firstOrError().timeout(10L, TimeUnit.SECONDS).toObservable().subscribe(nVar);
        try {
            this.f11933a.a(this.b, mVar);
        } catch (Exception e) {
            nVar.a(e);
        }
    }
}
