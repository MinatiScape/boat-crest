package com.htsmart.wristband2.a.a;

import com.htsmart.wristband2.exceptions.SyncTerminateException;
import com.htsmart.wristband2.packet.AccessUtils;
import com.htsmart.wristband2.packet.PacketData;
import com.htsmart.wristband2.packet.SyncDataParser;
import com.htsmart.wristband2.utils.WristbandLog;
import io.reactivex.ObservableEmitter;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
/* loaded from: classes11.dex */
public class i extends f<List<SyncDataParser.DataItem>> {

    /* loaded from: classes11.dex */
    public class a implements Predicate<PacketData> {
        public a(i iVar) {
        }

        @Override // io.reactivex.functions.Predicate
        /* renamed from: a */
        public boolean test(PacketData packetData) throws Exception {
            return packetData.getCmdId() == 5 && packetData.getKeyId() >= 81 && packetData.getKeyId() <= 87;
        }
    }

    /* loaded from: classes11.dex */
    public class b extends o<PacketData, List<SyncDataParser.DataItem>> {
        public List<byte[]> j;
        public byte k;

        public b(i iVar, ObservableEmitter<List<SyncDataParser.DataItem>> observableEmitter, m mVar) {
            super(observableEmitter, mVar);
            this.j = new ArrayList(7);
        }

        public /* synthetic */ b(i iVar, ObservableEmitter observableEmitter, m mVar, a aVar) {
            this(iVar, observableEmitter, mVar);
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
            byte keyId = packetData.getKeyId();
            byte b = this.k;
            if ((b != 0 || keyId != 81) && keyId - b != 1) {
                a(new SyncTerminateException(2));
                return;
            }
            this.j.add(packetData.getKeyData());
            this.k = keyId;
            if (keyId == 87) {
                this.b.onNext(i.c(this.j));
                onComplete();
            }
        }
    }

    public i(com.htsmart.wristband2.a.d.c cVar) {
        super(cVar);
    }

    public static List<SyncDataParser.DataItem> c(List<byte[]> list) {
        byte[] bArr;
        if (list == null || list.size() <= 0) {
            return null;
        }
        int i = 8;
        byte[] bArr2 = new byte[8];
        ArrayList arrayList = new ArrayList(870);
        SyncDataParser.DataHeader dataHeader = null;
        int i2 = 8;
        int i3 = 0;
        boolean z = true;
        for (byte[] bArr3 : list) {
            if (bArr3 == null || bArr3.length == 0) {
                bArr2 = bArr2;
                i = 8;
            } else {
                int length = bArr3.length;
                int i4 = 0;
                while (i4 < length) {
                    bArr2[i3] = bArr3[i4];
                    i3++;
                    if (i3 == i2) {
                        if (z) {
                            SyncDataParser.DataHeader parserHeader = AccessUtils.parserHeader(bArr2, Byte.MAX_VALUE, null);
                            if (parserHeader.itemCount != 0) {
                                i2 = 2;
                                bArr = bArr2;
                                z = false;
                            } else {
                                WristbandLog.w("数据无效(空数据包)", new Object[0]);
                                bArr = bArr2;
                            }
                            dataHeader = parserHeader;
                        } else if (dataHeader != null) {
                            int i5 = ((bArr2[0] & 255) << i) | (bArr2[1] & 255);
                            SyncDataParser.DataItem dataItem = new SyncDataParser.DataItem();
                            bArr = bArr2;
                            dataItem.timeStamp = dataHeader.getIndexTime();
                            dataItem.value = i5;
                            arrayList.add(dataItem);
                            int i6 = dataHeader.itemIndex + 1;
                            dataHeader.itemIndex = i6;
                            if (i6 == dataHeader.itemCount) {
                                z = true;
                                i2 = 8;
                            }
                        }
                        i3 = 0;
                        i4++;
                        bArr2 = bArr;
                        i = 8;
                    }
                    bArr = bArr2;
                    i4++;
                    bArr2 = bArr;
                    i = 8;
                }
            }
        }
        return arrayList;
    }

    @Override // com.htsmart.wristband2.a.a.f
    public void a(ObservableEmitter<List<SyncDataParser.DataItem>> observableEmitter, m mVar) throws Throwable {
        b bVar = new b(this, observableEmitter, mVar, null);
        this.f11933a.j().filter(new a(this)).timeout(10L, TimeUnit.SECONDS).subscribe(bVar);
        try {
            this.f11933a.a(new PacketData((byte) 5, com.htsmart.wristband2.a.a.a.d1), mVar);
        } catch (Exception e) {
            bVar.a(e);
        }
    }
}
