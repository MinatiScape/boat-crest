package com.htsmart.wristband2.a.a;

import androidx.annotation.NonNull;
import com.htsmart.wristband2.bean.HealthyDataResult;
import com.htsmart.wristband2.bean.WristbandVersion;
import com.htsmart.wristband2.exceptions.PacketDataFormatException;
import com.htsmart.wristband2.exceptions.TemperatureRealTimeException;
import com.htsmart.wristband2.packet.PacketData;
import com.htsmart.wristband2.utils.BytesUtil;
import com.htsmart.wristband2.utils.Utils;
import io.reactivex.ObservableEmitter;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.functions.Predicate;
import java.util.concurrent.TimeUnit;
/* loaded from: classes11.dex */
public class k extends f<HealthyDataResult> {
    public static final int b = 2;
    public final int c;
    public final int d;
    public final boolean e;
    public final WristbandVersion f;

    /* loaded from: classes11.dex */
    public class a implements Action {
        public final /* synthetic */ m h;

        public a(m mVar) {
            this.h = mVar;
        }

        @Override // io.reactivex.functions.Action
        public void run() throws Exception {
            k kVar = k.this;
            kVar.f11933a.a(com.htsmart.wristband2.a.e.b.a(kVar.c, 0, false), this.h);
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
            k kVar = k.this;
            kVar.f11933a.a(com.htsmart.wristband2.a.e.b.a(kVar.c, 0, false), this.h);
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Predicate<PacketData> {
        public c(k kVar) {
        }

        @Override // io.reactivex.functions.Predicate
        /* renamed from: a */
        public boolean test(@NonNull PacketData packetData) {
            return packetData.getCmdId() == 5 && packetData.getKeyId() == 36;
        }
    }

    /* loaded from: classes11.dex */
    public class d extends o<PacketData, HealthyDataResult> {
        public d(ObservableEmitter<HealthyDataResult> observableEmitter, m mVar) {
            super(observableEmitter, mVar);
        }

        public /* synthetic */ d(k kVar, ObservableEmitter observableEmitter, m mVar, a aVar) {
            this(observableEmitter, mVar);
        }

        @Override // io.reactivex.Observer
        /* renamed from: b */
        public void onNext(@NonNull PacketData packetData) {
            try {
                c(packetData);
            } catch (Exception e) {
                Exceptions.throwIfFatal(e);
                onError(e);
            }
        }

        public final void c(PacketData packetData) throws Exception {
            byte[] keyData = packetData.getKeyData();
            if (keyData != null) {
                int i = 11;
                if (keyData.length >= 11) {
                    HealthyDataResult healthyDataResult = new HealthyDataResult();
                    healthyDataResult.setHeartRate(keyData[6] & 255);
                    healthyDataResult.setOxygen(keyData[7] & 255);
                    healthyDataResult.setDiastolicPressure(keyData[8] & 255);
                    healthyDataResult.setSystolicPressure(keyData[9] & 255);
                    healthyDataResult.setRespiratoryRate(keyData[10] & 255);
                    if (k.this.f.isTemperatureEnabled()) {
                        if (keyData.length < 16) {
                            throw new PacketDataFormatException("HealthyRealTimeOperation", packetData);
                        }
                        if (k.this.e) {
                            int i2 = keyData[11] & 255;
                            if (i2 > 1) {
                                throw new TemperatureRealTimeException(i2);
                            }
                            healthyDataResult.setTemperatureWrist(BytesUtil.bytes2Short(keyData, 12, 2, true) / 100.0f);
                            healthyDataResult.setTemperatureBody(BytesUtil.bytes2Short(keyData, 14, 2, true) / 100.0f);
                        }
                        i = 16;
                    }
                    if (k.this.f.isPressureEnabled()) {
                        if (keyData.length < i + 1) {
                            throw new PacketDataFormatException("HealthyRealTimeOperation", packetData);
                        }
                        healthyDataResult.setPressure(keyData[i] & 255);
                    }
                    this.b.onNext(healthyDataResult);
                    if (k.this.c != 4 || !k.this.f.isAirPumpBloodPressure() || healthyDataResult.getDiastolicPressure() <= 0 || healthyDataResult.getSystolicPressure() <= 0) {
                        return;
                    }
                    onComplete();
                    return;
                }
            }
            throw new PacketDataFormatException("HealthyRealTimeOperation", packetData);
        }
    }

    public k(com.htsmart.wristband2.a.d.c cVar, int i, int i2, WristbandVersion wristbandVersion) {
        super(cVar);
        this.c = i;
        this.d = i2;
        this.e = Utils.isFlagEnable(i, 32);
        this.f = wristbandVersion;
    }

    @Override // com.htsmart.wristband2.a.a.f
    public void a(ObservableEmitter<HealthyDataResult> observableEmitter, m mVar) throws Throwable {
        d dVar = new d(this, observableEmitter, mVar, null);
        this.f11933a.j().filter(new c(this)).take(this.d, TimeUnit.MINUTES).observeOn(this.f11933a.m()).doOnTerminate(new b(mVar)).doOnDispose(new a(mVar)).subscribe(dVar);
        try {
            this.f11933a.a(com.htsmart.wristband2.a.e.b.a(this.c, this.d, true), mVar);
        } catch (Exception e) {
            dVar.a(e);
        }
    }
}
