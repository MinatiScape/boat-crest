package com.htsmart.wristband2.a.a;

import androidx.annotation.NonNull;
import com.htsmart.wristband2.exceptions.EcgStartFailedException;
import com.htsmart.wristband2.packet.PacketData;
import com.htsmart.wristband2.utils.BytesUtil;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableSource;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.util.concurrent.TimeUnit;
/* loaded from: classes11.dex */
public class g extends f<int[]> {

    /* loaded from: classes11.dex */
    public class a implements Action {
        public final /* synthetic */ m h;

        public a(m mVar) {
            this.h = mVar;
        }

        @Override // io.reactivex.functions.Action
        public void run() throws Exception {
            g.this.f11933a.a(false);
            g.this.f11933a.a(com.htsmart.wristband2.a.e.b.a(16, 0, false), this.h);
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
            g.this.f11933a.a(false);
            g.this.f11933a.a(com.htsmart.wristband2.a.e.b.a(16, 0, false), this.h);
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Function<PacketData, ObservableSource<byte[]>> {
        public final /* synthetic */ e h;

        public c(e eVar) {
            this.h = eVar;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public ObservableSource<byte[]> apply(PacketData packetData) throws Exception {
            byte[] keyData = packetData.getKeyData();
            if (keyData[0] == 1) {
                byte b = keyData[1];
                if (b > 0) {
                    this.h.b.onNext(new int[]{1000 / b});
                } else {
                    this.h.b.onNext(new int[]{100});
                }
                g.this.f11933a.a(true);
                return g.this.f11933a.l().take(33L, TimeUnit.SECONDS);
            }
            throw new EcgStartFailedException();
        }
    }

    /* loaded from: classes11.dex */
    public class d implements Predicate<PacketData> {
        public d(g gVar) {
        }

        @Override // io.reactivex.functions.Predicate
        /* renamed from: a */
        public boolean test(PacketData packetData) throws Exception {
            return packetData.getCmdId() == 5 && packetData.getKeyId() == 65;
        }
    }

    /* loaded from: classes11.dex */
    public class e extends o<byte[], int[]> {
        public byte[] j;

        public e(ObservableEmitter<int[]> observableEmitter, m mVar) {
            super(observableEmitter, mVar);
        }

        public /* synthetic */ e(g gVar, ObservableEmitter observableEmitter, m mVar, a aVar) {
            this(observableEmitter, mVar);
        }

        public final void b(byte[] bArr) {
            int length = bArr.length / 2;
            int[] iArr = new int[length];
            for (int i = 0; i < length; i++) {
                iArr[i] = BytesUtil.bytes2Int(bArr, i * 2, 2, true);
            }
            this.b.onNext(iArr);
        }

        public final boolean c(@NonNull byte[] bArr) {
            return bArr.length == 8 && bArr[0] == -85 && bArr[1] == 0 && bArr[2] == 0 && bArr[3] == 15;
        }

        public final boolean d(@NonNull byte[] bArr) {
            return bArr.length == 15 && bArr[0] == 5 && bArr[1] == 0 && bArr[2] == 65 && bArr[3] == 0 && bArr[4] == 10;
        }

        @Override // io.reactivex.Observer
        /* renamed from: e */
        public void onNext(byte[] bArr) {
            try {
                f(bArr);
            } catch (Exception e) {
                Exceptions.throwIfFatal(e);
                onError(e);
            }
        }

        public final void f(byte[] bArr) throws Exception {
            if (this.j == null || !d(bArr)) {
                byte[] bArr2 = this.j;
                if (bArr2 != null) {
                    b(bArr2);
                    this.j = null;
                }
                if (c(bArr)) {
                    this.j = bArr;
                    return;
                } else {
                    b(bArr);
                    return;
                }
            }
            if (bArr[5] == 1) {
                this.j = null;
                return;
            }
            byte[] bArr3 = this.j;
            int i = bArr3[7] & 255;
            g.this.f11933a.a(false);
            g.this.f11933a.b(com.htsmart.wristband2.a.d.d.a(false, (i | (bArr3[6] << 8)) & 65535)).onErrorComplete().subscribe();
            onComplete();
        }
    }

    public g(com.htsmart.wristband2.a.d.c cVar) {
        super(cVar);
    }

    @Override // com.htsmart.wristband2.a.a.f
    public void a(ObservableEmitter<int[]> observableEmitter, m mVar) throws Throwable {
        e eVar = new e(this, observableEmitter, mVar, null);
        this.f11933a.j().filter(new d(this)).timeout(10L, TimeUnit.SECONDS).firstOrError().observeOn(this.f11933a.m()).flatMapObservable(new c(eVar)).doOnTerminate(new b(mVar)).doOnDispose(new a(mVar)).subscribe(eVar);
        try {
            this.f11933a.a(com.htsmart.wristband2.a.e.b.a(16, 0, true), mVar);
        } catch (Exception e2) {
            eVar.a(e2);
        }
    }
}
