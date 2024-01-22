package com.htsmart.wristband2.a.a;

import com.htsmart.wristband2.exceptions.GSensorStartFailedException;
import com.htsmart.wristband2.packet.PacketData;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.util.concurrent.TimeUnit;
/* loaded from: classes11.dex */
public class h extends f<byte[]> {

    /* loaded from: classes11.dex */
    public class a implements Action {
        public final /* synthetic */ m h;

        public a(m mVar) {
            this.h = mVar;
        }

        @Override // io.reactivex.functions.Action
        public void run() throws Exception {
            h.this.f11933a.a(false);
            h.this.f11933a.a(new PacketData((byte) 5, (byte) 50), this.h);
        }
    }

    /* loaded from: classes11.dex */
    public class b implements Action {
        public final /* synthetic */ m h;

        public b(m mVar) {
            this.h = mVar;
        }

        @Override // io.reactivex.functions.Action
        public void run() throws Exception {
            h.this.f11933a.a(false);
            h.this.f11933a.a(new PacketData((byte) 5, (byte) 50), this.h);
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Function<PacketData, ObservableSource<byte[]>> {
        public c() {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public ObservableSource<byte[]> apply(PacketData packetData) throws Exception {
            if (packetData.getKeyData()[0] == 1) {
                h.this.f11933a.a(true);
                return h.this.f11933a.l().take(Long.MAX_VALUE, TimeUnit.SECONDS);
            }
            throw new GSensorStartFailedException();
        }
    }

    /* loaded from: classes11.dex */
    public class d implements Predicate<PacketData> {
        public d(h hVar) {
        }

        @Override // io.reactivex.functions.Predicate
        /* renamed from: a */
        public boolean test(PacketData packetData) throws Exception {
            return packetData.getCmdId() == 5 && packetData.getKeyId() == 51;
        }
    }

    /* loaded from: classes11.dex */
    public class e extends o<byte[], byte[]> {
        public e(h hVar, ObservableEmitter<byte[]> observableEmitter, m mVar) {
            super(observableEmitter, mVar);
        }

        @Override // io.reactivex.Observer
        /* renamed from: b */
        public void onNext(@NonNull byte[] bArr) {
            try {
                this.b.onNext(bArr);
            } catch (Exception e) {
                Exceptions.throwIfFatal(e);
                onError(e);
            }
        }
    }

    public h(com.htsmart.wristband2.a.d.c cVar) {
        super(cVar);
    }

    @Override // com.htsmart.wristband2.a.a.f
    public void a(ObservableEmitter<byte[]> observableEmitter, m mVar) throws Throwable {
        e eVar = new e(this, observableEmitter, mVar);
        this.f11933a.j().filter(new d(this)).timeout(10L, TimeUnit.SECONDS).firstOrError().observeOn(this.f11933a.m()).flatMapObservable(new c()).doOnTerminate(new b(mVar)).doOnDispose(new a(mVar)).subscribe(eVar);
        try {
            this.f11933a.a(new PacketData((byte) 5, (byte) 49), mVar);
        } catch (Exception e2) {
            eVar.a(e2);
        }
    }
}
