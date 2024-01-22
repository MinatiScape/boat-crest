package com.htsmart.wristband2.a.a;

import android.text.TextUtils;
import com.clevertap.android.sdk.Constants;
import com.htsmart.wristband2.a.a.t;
import com.htsmart.wristband2.exceptions.PacketDataFormatException;
import com.htsmart.wristband2.packet.PacketData;
import com.htsmart.wristband2.utils.WristbandLog;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;
/* loaded from: classes11.dex */
public class u extends f<Integer> {
    public final t b;

    /* loaded from: classes11.dex */
    public static class a extends RuntimeException {

        /* renamed from: a  reason: collision with root package name */
        public int f11939a;

        public a(int i) {
            this.f11939a = i;
        }
    }

    public u(com.htsmart.wristband2.a.d.c cVar, t tVar) {
        super(cVar);
        this.b = tVar;
    }

    public static /* synthetic */ Integer i(Throwable th) throws Exception {
        if (th instanceof a) {
            return Integer.valueOf(((a) th).f11939a);
        }
        if (th instanceof Exception) {
            throw ((Exception) th);
        }
        throw new Exception(th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(m mVar, ObservableEmitter observableEmitter) throws Exception {
        byte[] bytes = TextUtils.isEmpty(this.b.c()) ? null : this.b.c().getBytes(StandardCharsets.UTF_8);
        byte[] bytes2 = TextUtils.isEmpty(this.b.b()) ? null : this.b.b().getBytes(StandardCharsets.UTF_8);
        int min = bytes == null ? 0 : Math.min(bytes.length, 70);
        int min2 = bytes2 == null ? 0 : Math.min(bytes2.length, 175);
        int i = min + 3;
        byte[] bArr = new byte[i + min2];
        bArr[0] = (byte) min;
        bArr[1] = (byte) min2;
        bArr[2] = (byte) (this.b.a() == null ? 0 : Math.min(this.b.a().size(), 5));
        if (min > 0) {
            System.arraycopy(bytes, 0, bArr, 3, min);
        }
        if (min2 > 0) {
            System.arraycopy(bytes2, 0, bArr, i, min2);
        }
        this.f11933a.a(new PacketData((byte) 8, (byte) 3, bArr), mVar);
        observableEmitter.onComplete();
    }

    public static /* synthetic */ boolean k(PacketData packetData) throws Exception {
        WristbandLog.i("observerBegin filter:" + ((int) packetData.getCmdId()) + Constants.SEPARATOR_COMMA + ((int) packetData.getKeyId()), new Object[0]);
        return packetData.getCmdId() == 8 && packetData.getKeyId() == 4;
    }

    public static /* synthetic */ Integer m(PacketData packetData) throws Exception {
        if (packetData.getKeyData() == null || packetData.getKeyData().length < 1) {
            throw new PacketDataFormatException("GgUserDataBegin", packetData);
        }
        int i = packetData.getKeyData()[0] & 255;
        if (i == 0) {
            return 0;
        }
        throw new a(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n(m mVar, ObservableEmitter observableEmitter) throws Exception {
        List<t.a> a2 = this.b.a();
        if (a2 != null && a2.size() > 0) {
            int min = Math.min(5, a2.size());
            for (int i = 0; i < min; i++) {
                t.a aVar = a2.get(i);
                byte[] bytes = TextUtils.isEmpty(aVar.a()) ? null : aVar.a().getBytes(StandardCharsets.UTF_8);
                byte[] bytes2 = TextUtils.isEmpty(aVar.b()) ? null : aVar.b().getBytes(StandardCharsets.UTF_8);
                int min2 = bytes == null ? 0 : Math.min(bytes.length, 60);
                int min3 = bytes2 == null ? 0 : Math.min(bytes2.length, 366);
                int i2 = min2 + 4;
                byte[] bArr = new byte[i2 + min3];
                bArr[0] = (byte) min2;
                bArr[1] = (byte) ((min3 >> 8) & 255);
                bArr[2] = (byte) (min3 & 255);
                int min4 = Math.min(min3, 190);
                bArr[3] = (byte) min4;
                if (min2 > 0) {
                    System.arraycopy(bytes, 0, bArr, 4, min2);
                }
                if (min3 > 0) {
                    System.arraycopy(bytes2, 0, bArr, i2, min4);
                }
                this.f11933a.a(new PacketData((byte) 8, (byte) 5, bArr), mVar);
                int i3 = min3 - min4;
                if (i3 > 0) {
                    byte[] bArr2 = new byte[i3 + 1];
                    bArr2[0] = (byte) i3;
                    System.arraycopy(bytes2, min4, bArr2, 1, i3);
                    this.f11933a.a(new PacketData((byte) 8, (byte) 6, bArr2), mVar);
                }
            }
        }
        observableEmitter.onComplete();
    }

    public static /* synthetic */ boolean p(PacketData packetData) throws Exception {
        WristbandLog.i("observerFinish filter:" + ((int) packetData.getCmdId()) + Constants.SEPARATOR_COMMA + ((int) packetData.getKeyId()), new Object[0]);
        return packetData.getCmdId() == 8 && packetData.getKeyId() == 7;
    }

    public static /* synthetic */ Integer q(PacketData packetData) throws Exception {
        if (packetData.getKeyData() == null || packetData.getKeyData().length < 1) {
            throw new PacketDataFormatException("GgUserDataFinish", packetData);
        }
        return Integer.valueOf(packetData.getKeyData()[0] & 255);
    }

    @Override // com.htsmart.wristband2.a.a.f
    public void a(ObservableEmitter<Integer> observableEmitter, m mVar) throws Throwable {
        ((this.b.a() == null || this.b.a().size() <= 0) ? l(mVar) : o(mVar).delaySubscription(l(mVar))).onErrorReturn(new Function() { // from class: com.htsmart.wristband2.a.a.b0
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                Integer i;
                i = u.i((Throwable) obj);
                return i;
            }
        }).subscribe(new n(observableEmitter, mVar));
    }

    public final Observable<Integer> l(final m mVar) {
        return this.f11933a.j().filter(new Predicate() { // from class: com.htsmart.wristband2.a.a.c0
            @Override // io.reactivex.functions.Predicate
            public final boolean test(Object obj) {
                boolean k;
                k = u.k((PacketData) obj);
                return k;
            }
        }).timeout(10L, TimeUnit.SECONDS).firstOrError().toObservable().map(new Function() { // from class: com.htsmart.wristband2.a.a.a0
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                Integer m;
                m = u.m((PacketData) obj);
                return m;
            }
        }).delaySubscription(Observable.create(new ObservableOnSubscribe() { // from class: com.htsmart.wristband2.a.a.x
            @Override // io.reactivex.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                u.this.j(mVar, observableEmitter);
            }
        }).subscribeOn(this.f11933a.m()));
    }

    public final Observable<Integer> o(final m mVar) {
        return this.f11933a.j().filter(new Predicate() { // from class: com.htsmart.wristband2.a.a.d0
            @Override // io.reactivex.functions.Predicate
            public final boolean test(Object obj) {
                boolean p;
                p = u.p((PacketData) obj);
                return p;
            }
        }).timeout(10L, TimeUnit.SECONDS).firstOrError().toObservable().map(new Function() { // from class: com.htsmart.wristband2.a.a.z
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                Integer q;
                q = u.q((PacketData) obj);
                return q;
            }
        }).delaySubscription(Observable.create(new ObservableOnSubscribe() { // from class: com.htsmart.wristband2.a.a.y
            @Override // io.reactivex.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                u.this.n(mVar, observableEmitter);
            }
        }).subscribeOn(this.f11933a.m()));
    }
}
