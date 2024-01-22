package com.htsmart.wristband2.a.a;

import android.util.SparseArray;
import com.htsmart.wristband2.bean.WristbandContacts;
import com.htsmart.wristband2.exceptions.PacketDataFormatException;
import com.htsmart.wristband2.packet.PacketData;
import io.reactivex.ObservableEmitter;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
/* loaded from: classes11.dex */
public class p extends f<List<WristbandContacts>> {

    /* loaded from: classes11.dex */
    public class a implements Predicate<PacketData> {
        public a(p pVar) {
        }

        @Override // io.reactivex.functions.Predicate
        /* renamed from: a */
        public boolean test(PacketData packetData) throws Exception {
            return packetData.getCmdId() == 2 && packetData.getKeyId() == 86;
        }
    }

    /* loaded from: classes11.dex */
    public static class b extends o<PacketData, List<WristbandContacts>> {
        public SparseArray<WristbandContacts> j;

        public b(ObservableEmitter<List<WristbandContacts>> observableEmitter, m mVar) {
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
            PacketDataFormatException packetDataFormatException;
            byte[] keyData = packetData.getKeyData();
            if (keyData != null) {
                int i = 1;
                if (keyData.length >= 1) {
                    int i2 = keyData[0] & 255;
                    while (i < keyData.length) {
                        int i3 = i + 1;
                        try {
                            int i4 = i3 + 1;
                            int i5 = keyData[i3] & 255;
                            String str = new String(keyData, i4, i5);
                            int i6 = i4 + i5;
                            int i7 = i6 + 1;
                            int i8 = keyData[i6] & 255;
                            String str2 = new String(keyData, i7, i8);
                            int i9 = i7 + i8;
                            this.j.put(keyData[i] & 255, new WristbandContacts(str2, str));
                            i = i9;
                        } catch (Exception unused) {
                            packetDataFormatException = new PacketDataFormatException("parserResponseContactsList", packetData);
                        }
                    }
                    if (this.j.size() >= i2) {
                        ArrayList arrayList = new ArrayList(this.j.size());
                        for (int i10 = 0; i10 < this.j.size(); i10++) {
                            arrayList.add(this.j.valueAt(i10));
                        }
                        this.b.onNext(arrayList);
                        onComplete();
                        return;
                    }
                    return;
                }
            }
            packetDataFormatException = new PacketDataFormatException("parserResponseContactsList", packetData);
            a(packetDataFormatException);
        }
    }

    public p(com.htsmart.wristband2.a.d.c cVar) {
        super(cVar);
    }

    @Override // com.htsmart.wristband2.a.a.f
    public void a(ObservableEmitter<List<WristbandContacts>> observableEmitter, m mVar) throws Throwable {
        b bVar = new b(observableEmitter, mVar, null);
        this.f11933a.j().filter(new a(this)).timeout(10L, TimeUnit.SECONDS).subscribe(bVar);
        try {
            this.f11933a.a(new PacketData((byte) 2, (byte) 85), mVar);
        } catch (Exception e) {
            bVar.a(e);
        }
    }
}
