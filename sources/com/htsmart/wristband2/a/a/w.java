package com.htsmart.wristband2.a.a;

import android.annotation.SuppressLint;
import com.htsmart.wristband2.a.e.d;
import com.htsmart.wristband2.exceptions.SyncTerminateException;
import com.htsmart.wristband2.packet.PacketData;
import com.htsmart.wristband2.utils.BytesUtil;
import io.reactivex.ObservableEmitter;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
/* loaded from: classes11.dex */
public class w extends f<List<byte[]>> {
    public byte b;
    public d.s1 c;
    public boolean d;
    public List<byte[]> e;

    /* loaded from: classes11.dex */
    public class a implements Predicate<PacketData> {
        public a(w wVar) {
        }

        @Override // io.reactivex.functions.Predicate
        /* renamed from: a */
        public boolean test(PacketData packetData) throws Exception {
            return packetData.getKeyId() == 8;
        }
    }

    /* loaded from: classes11.dex */
    public class b implements Predicate<PacketData> {
        public b(w wVar) {
        }

        @Override // io.reactivex.functions.Predicate
        /* renamed from: a */
        public boolean test(PacketData packetData) throws Exception {
            return packetData.getCmdId() == 5 && (packetData.getKeyId() == 7 || packetData.getKeyId() == 48 || packetData.getKeyId() == 8);
        }
    }

    /* loaded from: classes11.dex */
    public class c extends o<PacketData, List<byte[]>> {
        public c(ObservableEmitter<List<byte[]>> observableEmitter, m mVar) {
            super(observableEmitter, mVar);
        }

        public /* synthetic */ c(w wVar, ObservableEmitter observableEmitter, m mVar, a aVar) {
            this(observableEmitter, mVar);
        }

        @Override // io.reactivex.Observer
        /* renamed from: b */
        public void onNext(PacketData packetData) {
            try {
                c(packetData);
            } catch (Exception e) {
                Exceptions.throwIfFatal(e);
                onError(e);
            }
        }

        public final void c(PacketData packetData) throws Exception {
            int i;
            if (packetData.getKeyId() == 7) {
                w.this.d = true;
            } else if (!w.this.d) {
                a(new SyncTerminateException(w.this.b));
            } else if (packetData.getKeyId() == 48) {
                byte[] keyData = packetData.getKeyData();
                w.this.e.add(keyData);
                w.this.c.a(keyData.length);
            } else if (packetData.getKeyId() == 8) {
                byte[] keyData2 = packetData.getKeyData();
                int bytes2Int = (keyData2 == null || keyData2.length != 4) ? 0 : BytesUtil.bytes2Int(keyData2, true);
                if (w.this.e != null) {
                    i = 0;
                    for (int i2 = 0; i2 < w.this.e.size(); i2++) {
                        i += ((byte[]) w.this.e.get(i2)).length;
                    }
                } else {
                    i = 0;
                }
                boolean z = bytes2Int == i;
                w.this.f11933a.a(com.htsmart.wristband2.a.e.b.a(z), this.c);
                if (!z) {
                    a(new SyncTerminateException(w.this.b));
                    return;
                }
                this.b.onNext(w.this.e);
                onComplete();
            }
        }
    }

    public w(com.htsmart.wristband2.a.d.c cVar, byte b2, d.s1 s1Var) {
        super(cVar);
        this.d = false;
        this.e = new ArrayList(100);
        this.b = b2;
        this.c = s1Var;
    }

    @Override // com.htsmart.wristband2.a.a.f
    @SuppressLint({"CheckResult"})
    public void a(ObservableEmitter<List<byte[]>> observableEmitter, m mVar) throws Throwable {
        c cVar = new c(this, observableEmitter, mVar, null);
        this.f11933a.j().filter(new b(this)).timeout(10L, TimeUnit.SECONDS).takeUntil(new a(this)).subscribe(cVar);
        try {
            this.f11933a.a(com.htsmart.wristband2.a.e.b.b(this.b), mVar);
        } catch (Exception e) {
            cVar.a(e);
        }
    }
}
