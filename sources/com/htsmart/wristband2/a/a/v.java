package com.htsmart.wristband2.a.a;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import com.htsmart.wristband2.a.e.d;
import com.htsmart.wristband2.exceptions.SyncTerminateException;
import com.htsmart.wristband2.packet.PacketData;
import com.htsmart.wristband2.utils.BytesUtil;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableSource;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
/* loaded from: classes11.dex */
public class v extends f<List<byte[]>> {
    public d.s1 b;
    public volatile boolean c;
    public volatile List<byte[]> d;

    /* loaded from: classes11.dex */
    public class a implements Action {
        public a() {
        }

        @Override // io.reactivex.functions.Action
        public void run() throws Exception {
            v.this.f11933a.a(false);
        }
    }

    /* loaded from: classes11.dex */
    public class b implements Action {
        public b() {
        }

        @Override // io.reactivex.functions.Action
        public void run() throws Exception {
            v.this.f11933a.a(false);
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Function<PacketData, ObservableSource<byte[]>> {
        public c() {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public ObservableSource<byte[]> apply(PacketData packetData) throws Exception {
            v.this.c = true;
            byte[] keyData = packetData.getKeyData();
            v.this.d = new ArrayList((int) Math.ceil(((keyData == null || keyData.length < 4) ? 512 : BytesUtil.bytes2Int(keyData, 0, 4, true)) / 20.0f));
            v.this.f11933a.a(true);
            return v.this.f11933a.l().timeout(5L, TimeUnit.SECONDS);
        }
    }

    /* loaded from: classes11.dex */
    public class d implements Predicate<PacketData> {
        public d(v vVar) {
        }

        @Override // io.reactivex.functions.Predicate
        /* renamed from: a */
        public boolean test(PacketData packetData) throws Exception {
            return packetData.getCmdId() == 5 && packetData.getKeyId() == 7;
        }
    }

    /* loaded from: classes11.dex */
    public class e extends o<byte[], List<byte[]>> {
        public byte[] j;

        public e(ObservableEmitter<List<byte[]>> observableEmitter, m mVar) {
            super(observableEmitter, mVar);
        }

        public /* synthetic */ e(v vVar, ObservableEmitter observableEmitter, m mVar, a aVar) {
            this(observableEmitter, mVar);
        }

        public final boolean b(@NonNull byte[] bArr) {
            return bArr.length == 8 && bArr[0] == -85 && bArr[1] == 0 && bArr[2] == 0 && bArr[3] == 9;
        }

        public final boolean c(@NonNull byte[] bArr) {
            return bArr.length == 9 && bArr[0] == 5 && bArr[1] == 0 && bArr[2] == 8 && bArr[3] == 0 && bArr[4] == 4;
        }

        @Override // io.reactivex.Observer
        /* renamed from: d */
        public void onNext(byte[] bArr) {
            try {
                e(bArr);
            } catch (Exception e) {
                Exceptions.throwIfFatal(e);
                onError(e);
            }
        }

        public final void e(byte[] bArr) throws Exception {
            if (!v.this.c) {
                a(new SyncTerminateException(7));
            } else if (this.j == null || !c(bArr)) {
                if (this.j != null) {
                    v.this.d.add(this.j);
                    v.this.b.a(bArr.length);
                    this.j = null;
                }
                if (b(bArr)) {
                    this.j = bArr;
                    return;
                }
                v.this.d.add(bArr);
                v.this.b.a(bArr.length);
            } else {
                byte[] bArr2 = this.j;
                int i = ((bArr2[7] & 255) | (bArr2[6] << 8)) & 65535;
                int bytes2Int = BytesUtil.bytes2Int(bArr, 5, 4, true);
                int i2 = 0;
                for (int i3 = 0; i3 < v.this.d.size(); i3++) {
                    i2 += ((byte[]) v.this.d.get(i3)).length;
                }
                boolean z = bytes2Int == i2;
                v.this.f11933a.a(false);
                v.this.f11933a.b(com.htsmart.wristband2.a.d.d.a(false, i)).onErrorComplete().subscribe();
                v.this.f11933a.a(com.htsmart.wristband2.a.e.b.a(z), this.c);
                if (!z) {
                    a(new SyncTerminateException(7));
                    return;
                }
                this.b.onNext(v.this.d);
                onComplete();
            }
        }
    }

    public v(com.htsmart.wristband2.a.d.c cVar, d.s1 s1Var) {
        super(cVar);
        this.b = s1Var;
    }

    @Override // com.htsmart.wristband2.a.a.f
    @SuppressLint({"CheckResult"})
    public void a(ObservableEmitter<List<byte[]>> observableEmitter, m mVar) throws Throwable {
        e eVar = new e(this, observableEmitter, mVar, null);
        this.f11933a.j().filter(new d(this)).timeout(10L, TimeUnit.SECONDS).firstOrError().flatMapObservable(new c()).doOnTerminate(new b()).doOnDispose(new a()).subscribe(eVar);
        try {
            this.f11933a.a(com.htsmart.wristband2.a.e.b.b((byte) 7), mVar);
        } catch (Exception e2) {
            eVar.a(e2);
        }
    }
}
