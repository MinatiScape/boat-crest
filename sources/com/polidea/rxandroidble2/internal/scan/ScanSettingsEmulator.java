package com.polidea.rxandroidble2.internal.scan;

import androidx.annotation.IntRange;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.android.volley.DefaultRetryPolicy;
import com.polidea.rxandroidble2.internal.RxBleLog;
import com.polidea.rxandroidble2.internal.util.ObservableUtil;
import com.polidea.rxandroidble2.scan.ScanCallbackType;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public class ScanSettingsEmulator {

    /* renamed from: a  reason: collision with root package name */
    public final Scheduler f13478a;
    public final ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult> b;
    public final ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult> c = new e();
    public final ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult> d = new g();

    /* loaded from: classes12.dex */
    public class a implements ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult> {
        public final Observable<Long> b;
        public final /* synthetic */ Scheduler e;

        /* renamed from: a  reason: collision with root package name */
        public final Function<RxBleInternalScanResult, RxBleInternalScanResult> f13479a = ScanSettingsEmulator.g();
        public final Function<RxBleInternalScanResult, Observable<?>> c = new C0712a();
        public final Function<Observable<RxBleInternalScanResult>, Observable<RxBleInternalScanResult>> d = new b(this);

        /* renamed from: com.polidea.rxandroidble2.internal.scan.ScanSettingsEmulator$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public class C0712a implements Function<RxBleInternalScanResult, Observable<?>> {
            public C0712a() {
            }

            @Override // io.reactivex.functions.Function
            /* renamed from: a */
            public Observable<?> apply(RxBleInternalScanResult rxBleInternalScanResult) {
                return a.this.b;
            }
        }

        /* loaded from: classes12.dex */
        public class b implements Function<Observable<RxBleInternalScanResult>, Observable<RxBleInternalScanResult>> {
            public b(a aVar) {
            }

            @Override // io.reactivex.functions.Function
            /* renamed from: a */
            public Observable<RxBleInternalScanResult> apply(Observable<RxBleInternalScanResult> observable) {
                return observable.take(1L);
            }
        }

        /* loaded from: classes12.dex */
        public class c implements Function<Observable<RxBleInternalScanResult>, ObservableSource<RxBleInternalScanResult>> {
            public c() {
            }

            @Override // io.reactivex.functions.Function
            /* renamed from: a */
            public ObservableSource<RxBleInternalScanResult> apply(Observable<RxBleInternalScanResult> observable) {
                return observable.window(observable.switchMap(a.this.c)).flatMap(a.this.d).map(a.this.f13479a);
            }
        }

        public a(ScanSettingsEmulator scanSettingsEmulator, Scheduler scheduler) {
            this.e = scheduler;
            this.b = Observable.timer(10L, TimeUnit.SECONDS, scheduler);
        }

        @Override // io.reactivex.ObservableTransformer
        /* renamed from: a */
        public Observable<RxBleInternalScanResult> apply(Observable<RxBleInternalScanResult> observable) {
            return observable.publish(new c());
        }
    }

    /* loaded from: classes12.dex */
    public class b implements ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ int f13480a;
        public final /* synthetic */ long b;

        /* loaded from: classes12.dex */
        public class a implements Function<Observable<Object>, ObservableSource<?>> {
            public a() {
            }

            @Override // io.reactivex.functions.Function
            /* renamed from: a */
            public ObservableSource<?> apply(Observable<Object> observable) {
                b bVar = b.this;
                return observable.delay(bVar.b, TimeUnit.MILLISECONDS, ScanSettingsEmulator.this.f13478a);
            }
        }

        public b(int i, long j) {
            this.f13480a = i;
            this.b = j;
        }

        @Override // io.reactivex.ObservableTransformer
        /* renamed from: a */
        public Observable<RxBleInternalScanResult> apply(Observable<RxBleInternalScanResult> observable) {
            return observable.take(this.f13480a, TimeUnit.MILLISECONDS, ScanSettingsEmulator.this.f13478a).repeatWhen(new a());
        }
    }

    /* loaded from: classes12.dex */
    public class c implements ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ ObservableTransformer f13481a;

        /* loaded from: classes12.dex */
        public class a implements Function<GroupedObservable<String, RxBleInternalScanResult>, Observable<RxBleInternalScanResult>> {
            public a() {
            }

            @Override // io.reactivex.functions.Function
            /* renamed from: a */
            public Observable<RxBleInternalScanResult> apply(GroupedObservable<String, RxBleInternalScanResult> groupedObservable) {
                return groupedObservable.compose(c.this.f13481a);
            }
        }

        /* loaded from: classes12.dex */
        public class b implements Function<RxBleInternalScanResult, String> {
            public b(c cVar) {
            }

            @Override // io.reactivex.functions.Function
            /* renamed from: a */
            public String apply(RxBleInternalScanResult rxBleInternalScanResult) {
                return rxBleInternalScanResult.getBluetoothDevice().getAddress();
            }
        }

        public c(ObservableTransformer observableTransformer) {
            this.f13481a = observableTransformer;
        }

        @Override // io.reactivex.ObservableTransformer
        /* renamed from: a */
        public Observable<RxBleInternalScanResult> apply(Observable<RxBleInternalScanResult> observable) {
            return observable.groupBy(new b(this)).flatMap(new a());
        }
    }

    /* loaded from: classes12.dex */
    public class d implements Function<RxBleInternalScanResult, RxBleInternalScanResult> {
        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public RxBleInternalScanResult apply(RxBleInternalScanResult rxBleInternalScanResult) {
            return new RxBleInternalScanResult(rxBleInternalScanResult.getBluetoothDevice(), rxBleInternalScanResult.getRssi(), rxBleInternalScanResult.getTimestampNanos(), rxBleInternalScanResult.getScanRecord(), ScanCallbackType.CALLBACK_TYPE_FIRST_MATCH, rxBleInternalScanResult.isConnectable());
        }
    }

    /* loaded from: classes12.dex */
    public class e implements ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult> {
        public e() {
        }

        @Override // io.reactivex.ObservableTransformer
        /* renamed from: a */
        public Observable<RxBleInternalScanResult> apply(Observable<RxBleInternalScanResult> observable) {
            return observable.debounce(10L, TimeUnit.SECONDS, ScanSettingsEmulator.this.f13478a).map(ScanSettingsEmulator.h());
        }
    }

    /* loaded from: classes12.dex */
    public class f implements Function<RxBleInternalScanResult, RxBleInternalScanResult> {
        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public RxBleInternalScanResult apply(RxBleInternalScanResult rxBleInternalScanResult) {
            return new RxBleInternalScanResult(rxBleInternalScanResult.getBluetoothDevice(), rxBleInternalScanResult.getRssi(), rxBleInternalScanResult.getTimestampNanos(), rxBleInternalScanResult.getScanRecord(), ScanCallbackType.CALLBACK_TYPE_MATCH_LOST, rxBleInternalScanResult.isConnectable());
        }
    }

    /* loaded from: classes12.dex */
    public class g implements ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult> {

        /* loaded from: classes12.dex */
        public class a implements Function<Observable<RxBleInternalScanResult>, Observable<RxBleInternalScanResult>> {
            public a() {
            }

            @Override // io.reactivex.functions.Function
            /* renamed from: a */
            public Observable<RxBleInternalScanResult> apply(Observable<RxBleInternalScanResult> observable) {
                return Observable.merge(observable.compose(ScanSettingsEmulator.this.b), observable.compose(ScanSettingsEmulator.this.c));
            }
        }

        public g() {
        }

        @Override // io.reactivex.ObservableTransformer
        /* renamed from: a */
        public Observable<RxBleInternalScanResult> apply(Observable<RxBleInternalScanResult> observable) {
            return observable.publish(new a());
        }
    }

    @Inject
    public ScanSettingsEmulator(@Named("computation") Scheduler scheduler) {
        this.f13478a = scheduler;
        this.b = new a(this, scheduler);
    }

    public static ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult> f(ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult> observableTransformer) {
        return new c(observableTransformer);
    }

    public static Function<RxBleInternalScanResult, RxBleInternalScanResult> g() {
        return new d();
    }

    public static Function<RxBleInternalScanResult, RxBleInternalScanResult> h() {
        return new f();
    }

    public ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult> a(int i) {
        if (i != 2) {
            if (i != 4) {
                if (i != 6) {
                    return ObservableUtil.identityTransformer();
                }
                return f(this.d);
            }
            return f(this.c);
        }
        return f(this.b);
    }

    public ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult> b(int i) {
        if (i == -1) {
            RxBleLog.w("Cannot emulate opportunistic scan mode since it is OS dependent - fallthrough to low power", new Object[0]);
        } else if (i != 0) {
            if (i != 1) {
                return ObservableUtil.identityTransformer();
            }
            return d();
        }
        return e();
    }

    public final ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult> c(@IntRange(from = 0, to = 4999) int i) {
        return new b(i, Math.max(TimeUnit.SECONDS.toMillis(5L) - i, 0L));
    }

    public final ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult> d() {
        return c(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS);
    }

    public final ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult> e() {
        return c(500);
    }
}
