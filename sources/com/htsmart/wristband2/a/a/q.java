package com.htsmart.wristband2.a.a;

import android.util.SparseArray;
import com.htsmart.wristband2.bean.WristbandHabit;
import com.htsmart.wristband2.exceptions.PacketDataFormatException;
import com.htsmart.wristband2.packet.PacketData;
import io.reactivex.ObservableEmitter;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
/* loaded from: classes11.dex */
public class q extends f<List<WristbandHabit>> {

    /* loaded from: classes11.dex */
    public class a implements Predicate<PacketData> {
        public a(q qVar) {
        }

        @Override // io.reactivex.functions.Predicate
        /* renamed from: a */
        public boolean test(PacketData packetData) throws Exception {
            return packetData.getCmdId() == 2 && packetData.getKeyId() == -92;
        }
    }

    /* loaded from: classes11.dex */
    public static class b extends o<PacketData, List<WristbandHabit>> {
        public final SparseArray<WristbandHabit> j;

        public b(ObservableEmitter<List<WristbandHabit>> observableEmitter, m mVar) {
            super(observableEmitter, mVar);
            this.j = new SparseArray<>(10);
        }

        public /* synthetic */ b(ObservableEmitter observableEmitter, m mVar, a aVar) {
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
            byte[] keyData = packetData.getKeyData();
            if (keyData == null || keyData.length < 1) {
                a(new PacketDataFormatException("parserResponseHabitList", packetData));
                return;
            }
            int i = keyData[0] & 255;
            List<WristbandHabit> k = com.htsmart.wristband2.a.e.b.k(packetData);
            if (k != null && k.size() > 0) {
                for (WristbandHabit wristbandHabit : k) {
                    this.j.put(wristbandHabit.getHabitId(), wristbandHabit);
                }
            }
            if (this.j.size() >= i) {
                ArrayList arrayList = new ArrayList(this.j.size());
                for (int i2 = 0; i2 < this.j.size(); i2++) {
                    arrayList.add(this.j.valueAt(i2));
                }
                this.b.onNext(arrayList);
                onComplete();
            }
        }
    }

    public q(com.htsmart.wristband2.a.d.c cVar) {
        super(cVar);
    }

    @Override // com.htsmart.wristband2.a.a.f
    public void a(ObservableEmitter<List<WristbandHabit>> observableEmitter, m mVar) throws Throwable {
        b bVar = new b(observableEmitter, mVar, null);
        this.f11933a.j().filter(new a(this)).timeout(10L, TimeUnit.SECONDS).subscribe(bVar);
        try {
            this.f11933a.a(new PacketData((byte) 2, (byte) -93), mVar);
        } catch (Exception e) {
            bVar.a(e);
        }
    }
}
