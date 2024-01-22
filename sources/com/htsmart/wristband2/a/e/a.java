package com.htsmart.wristband2.a.e;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.htsmart.wristband2.packet.PacketData;
import com.htsmart.wristband2.utils.BytesUtil;
import com.htsmart.wristband2.utils.WristbandLog;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.HasUpstreamObservableSource;
import io.reactivex.plugins.RxJavaPlugins;
/* loaded from: classes11.dex */
public class a extends Observable<byte[]> implements HasUpstreamObservableSource<PacketData> {

    /* renamed from: a  reason: collision with root package name */
    public final ObservableSource<PacketData> f11949a;

    /* renamed from: com.htsmart.wristband2.a.e.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static final class C0562a implements Observer<PacketData>, Disposable {
        public final Observer<? super byte[]> h;
        public boolean i;
        public Disposable j;
        @Nullable
        public b k = null;

        public C0562a(Observer<? super byte[]> observer) {
            this.h = observer;
        }

        @Override // io.reactivex.Observer
        /* renamed from: a */
        public void onNext(@NonNull PacketData packetData) {
            byte[] keyData;
            if (this.i || (keyData = packetData.getKeyData()) == null || keyData.length < 6) {
                return;
            }
            int i = keyData[0] & 255;
            int i2 = keyData[1] & 255;
            int bytes2Int = BytesUtil.bytes2Int(keyData, 2, 2, true);
            if (i2 == 0) {
                this.k = new b(i, bytes2Int);
            }
            b bVar = this.k;
            if (bVar == null) {
                WristbandLog.i("Not receive first packet", new Object[0]);
            } else if (!bVar.b(i, bytes2Int, i2)) {
                WristbandLog.i("PacketExcept not match", new Object[0]);
            } else if (!this.k.c(keyData, 6)) {
                WristbandLog.i("PacketExcept add fail", new Object[0]);
                this.k = null;
            } else if (this.k.a()) {
                this.h.onNext(this.k.c);
                this.k = null;
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.j.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.j.isDisposed();
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            if (this.i) {
                return;
            }
            this.i = true;
            this.j.dispose();
            this.h.onComplete();
        }

        @Override // io.reactivex.Observer
        public void onError(@NonNull Throwable th) {
            if (this.i) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.i = true;
            this.j.dispose();
            this.h.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(@NonNull Disposable disposable) {
            if (DisposableHelper.validate(this.j, disposable)) {
                this.j = disposable;
                this.h.onSubscribe(this);
            }
        }
    }

    /* loaded from: classes11.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public final int f11950a;
        public final int b;
        public final byte[] c;
        public int d = 0;
        public int e = 0;

        public b(int i, int i2) {
            this.f11950a = i;
            this.b = i2;
            this.c = new byte[i2];
        }

        public boolean a() {
            return this.d == this.f11950a;
        }

        public boolean b(int i, int i2, int i3) {
            return this.f11950a == i && this.b == i2 && this.d == i3;
        }

        public boolean c(byte[] bArr, int i) {
            int length = bArr.length - i;
            int i2 = this.b;
            int i3 = this.e;
            if (i2 - i3 >= length) {
                System.arraycopy(bArr, i, this.c, i3, length);
                this.d++;
                this.e += length;
                return true;
            }
            return false;
        }
    }

    public a(ObservableSource<PacketData> observableSource) {
        this.f11949a = observableSource;
    }

    @Override // io.reactivex.internal.fuseable.HasUpstreamObservableSource
    public ObservableSource<PacketData> source() {
        return this.f11949a;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(@NonNull Observer<? super byte[]> observer) {
        this.f11949a.subscribe(new C0562a(observer));
    }
}
