package com.htsmart.wristband2.a.e;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.coveiot.sdk.ble.api.BleUUID;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.htsmart.wristband2.WristbandApplication;
import com.htsmart.wristband2.WristbandManager;
import com.htsmart.wristband2.a.a.t;
import com.htsmart.wristband2.bean.BatteryStatus;
import com.htsmart.wristband2.bean.ConnectionError;
import com.htsmart.wristband2.bean.ConnectionState;
import com.htsmart.wristband2.bean.DialBinInfo;
import com.htsmart.wristband2.bean.DialUiInfo;
import com.htsmart.wristband2.bean.ExerciseTarget;
import com.htsmart.wristband2.bean.GameRankingTrend;
import com.htsmart.wristband2.bean.GameSpace;
import com.htsmart.wristband2.bean.HealthyDataResult;
import com.htsmart.wristband2.bean.LcdShape;
import com.htsmart.wristband2.bean.PhotovoltaicStation;
import com.htsmart.wristband2.bean.PowerSaveMode;
import com.htsmart.wristband2.bean.SportPush;
import com.htsmart.wristband2.bean.SyncDataRaw;
import com.htsmart.wristband2.bean.WristbandAlarm;
import com.htsmart.wristband2.bean.WristbandConfig;
import com.htsmart.wristband2.bean.WristbandContacts;
import com.htsmart.wristband2.bean.WristbandHabit;
import com.htsmart.wristband2.bean.WristbandNotification;
import com.htsmart.wristband2.bean.WristbandSchedule;
import com.htsmart.wristband2.bean.WristbandVersion;
import com.htsmart.wristband2.bean.assist.AssistInfo;
import com.htsmart.wristband2.bean.config.AbstractConfig;
import com.htsmart.wristband2.bean.config.BloodPressureConfig;
import com.htsmart.wristband2.bean.config.BrightnessVibrateConfig;
import com.htsmart.wristband2.bean.config.DrinkWaterConfig;
import com.htsmart.wristband2.bean.config.FunctionConfig;
import com.htsmart.wristband2.bean.config.HandWashingReminderConfig;
import com.htsmart.wristband2.bean.config.HealthyConfig;
import com.htsmart.wristband2.bean.config.NotDisturbConfig;
import com.htsmart.wristband2.bean.config.NotificationConfig;
import com.htsmart.wristband2.bean.config.PageConfig;
import com.htsmart.wristband2.bean.config.ProtectionReminderConfig;
import com.htsmart.wristband2.bean.config.SedentaryConfig;
import com.htsmart.wristband2.bean.config.TurnWristLightingConfig;
import com.htsmart.wristband2.bean.config.WarnBloodPressureConfig;
import com.htsmart.wristband2.bean.config.WarnHeartRateConfig;
import com.htsmart.wristband2.bean.config.WomenHealthyConfig;
import com.htsmart.wristband2.bean.data.GameData;
import com.htsmart.wristband2.bean.data.SportRealTimeData;
import com.htsmart.wristband2.bean.data.SportRealTimeStatus;
import com.htsmart.wristband2.bean.peripherals.Peripherals;
import com.htsmart.wristband2.bean.peripherals.PeripheralsData;
import com.htsmart.wristband2.bean.peripherals.PeripheralsRequest;
import com.htsmart.wristband2.bean.peripherals.PeripheralsResponse;
import com.htsmart.wristband2.bean.weather.WeatherForecast;
import com.htsmart.wristband2.bean.weather.WeatherToday;
import com.htsmart.wristband2.dial.DialDrawer;
import com.htsmart.wristband2.exceptions.AuthenticatedException;
import com.htsmart.wristband2.exceptions.PacketDataFormatException;
import com.htsmart.wristband2.exceptions.SyncBusyException;
import com.htsmart.wristband2.exceptions.SyncStartFailedException;
import com.htsmart.wristband2.packet.PacketData;
import com.htsmart.wristband2.packet.SyncDataParser;
import com.htsmart.wristband2.utils.BytesUtil;
import com.htsmart.wristband2.utils.WristbandLog;
import com.jieli.jl_rcsp.constant.Command;
import com.polidea.rxandroidble2.RxBleDevice;
import com.polidea.rxandroidble2.exceptions.BleDisconnectedException;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.reactivestreams.Publisher;
/* loaded from: classes11.dex */
public class d extends com.htsmart.wristband2.a.a.a implements WristbandManager {
    public volatile String J;
    public volatile boolean K;
    public volatile String L;
    public volatile boolean M;
    public volatile WristbandConfig S2;
    public Disposable T2;
    public volatile boolean X2;
    public volatile int Y2;
    public volatile float Z2;
    public volatile float a3;
    public volatile int e3;
    public volatile boolean N = false;
    public BleDisconnectedException U2 = new BleDisconnectedException();
    public Subject<ConnectionState> V2 = PublishSubject.create();
    public Subject<ConnectionError> W2 = PublishSubject.create();
    public byte[] b3 = null;
    public volatile boolean c3 = false;
    public Subject<Integer> d3 = PublishSubject.create().toSerialized();
    public AtomicInteger f3 = new AtomicInteger(0);
    public final s1 g3 = new l0();

    /* loaded from: classes11.dex */
    public class a implements Action {
        public final /* synthetic */ String h;

        public a(String str) {
            this.h = str;
        }

        @Override // io.reactivex.functions.Action
        public void run() throws Exception {
            d.this.I0(this.h);
        }
    }

    /* loaded from: classes11.dex */
    public class a0 implements Function<PacketData, BatteryStatus> {
        public a0(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public BatteryStatus apply(PacketData packetData) throws Exception {
            return com.htsmart.wristband2.a.e.b.d(packetData);
        }
    }

    /* loaded from: classes11.dex */
    public class a1 implements Function<PacketData, Byte> {
        public a1(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Byte apply(PacketData packetData) throws Exception {
            return Byte.valueOf(com.htsmart.wristband2.a.e.b.h(packetData));
        }
    }

    /* loaded from: classes11.dex */
    public class b implements Function<PacketData, SportRealTimeStatus> {
        public final /* synthetic */ GregorianCalendar h;

        public b(d dVar, GregorianCalendar gregorianCalendar) {
            this.h = gregorianCalendar;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public SportRealTimeStatus apply(@NonNull PacketData packetData) throws Exception {
            byte[] keyData = packetData.getKeyData();
            if (keyData == null || keyData.length < 11) {
                throw new PacketDataFormatException("", packetData);
            }
            return new SportRealTimeStatus((int) (SyncDataParser.parserTime4Bytes(keyData, 3, this.h) / 1000), keyData[2] & 255);
        }
    }

    /* loaded from: classes11.dex */
    public class b0 implements Function<SparseArray<SportPush>, SingleSource<SparseArray<SportPush>>> {

        /* loaded from: classes11.dex */
        public class a implements Function<PacketData, SparseArray<SportPush>> {
            public final /* synthetic */ SparseArray h;

            public a(b0 b0Var, SparseArray sparseArray) {
                this.h = sparseArray;
            }

            @Override // io.reactivex.functions.Function
            /* renamed from: a */
            public SparseArray<SportPush> apply(@NonNull PacketData packetData) throws Exception {
                byte[] keyData = packetData.getKeyData();
                if (keyData == null || keyData.length < 1) {
                    throw new PacketDataFormatException("parserSportPush", packetData);
                }
                int i = keyData[0] & 255;
                if (i > 0) {
                    if (keyData.length < (i * 3) + 1) {
                        throw new PacketDataFormatException("parserSportPush", packetData);
                    }
                    int i2 = 0;
                    for (int i3 = 0; i3 < i; i3++) {
                        int i4 = i3 * 3;
                        int bytes2Int = BytesUtil.bytes2Int(keyData, i4 + 1, 2, true);
                        boolean z = keyData[i4 + 3] > 0;
                        SportPush sportPush = (SportPush) this.h.get(bytes2Int);
                        if (sportPush != null) {
                            sportPush.setExist(true);
                            if (z) {
                                sportPush.setPushEnabled(true);
                                sportPush.setBinFlag((byte) (i2 + Command.CMD_NOTIFY_DEVICE_APP_INFO));
                                i2++;
                            }
                        }
                    }
                }
                return this.h;
            }
        }

        public b0() {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public SingleSource<SparseArray<SportPush>> apply(@NonNull SparseArray<SportPush> sparseArray) throws Exception {
            return d.this.a(new PacketData((byte) 2, (byte) -126), new PacketData((byte) 2, (byte) -125)).map(new a(this, sparseArray));
        }
    }

    /* loaded from: classes11.dex */
    public class b1 implements Action {
        public b1() {
        }

        @Override // io.reactivex.functions.Action
        public void run() throws Exception {
            d.this.c3 = false;
            d.this.d3.onNext(Integer.valueOf((int) WristbandManager.SYNC_STATE_FAILED_UNKNOWN));
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Function<PacketData, Integer> {
        public c(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Integer apply(PacketData packetData) throws Exception {
            return Integer.valueOf(com.htsmart.wristband2.a.e.b.i(packetData));
        }
    }

    /* loaded from: classes11.dex */
    public class c0 implements Function<PacketData, Boolean> {
        public c0(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Boolean apply(PacketData packetData) throws Exception {
            return Boolean.valueOf(packetData.getKeyData()[0] == 0);
        }
    }

    /* loaded from: classes11.dex */
    public class c1 implements Function<PacketData, Byte> {
        public c1(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Byte apply(@NonNull PacketData packetData) throws Exception {
            byte[] keyData = packetData.getKeyData();
            if (keyData == null || keyData.length < 1) {
                throw new PacketDataFormatException("parserLanguage", packetData);
            }
            return Byte.valueOf(keyData[0]);
        }
    }

    /* renamed from: com.htsmart.wristband2.a.e.d$d  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0563d implements Action {
        public C0563d() {
        }

        @Override // io.reactivex.functions.Action
        public void run() throws Exception {
            d.this.close();
        }
    }

    /* loaded from: classes11.dex */
    public class d0 implements Function<PacketData, WristbandConfig> {
        public d0(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public WristbandConfig apply(PacketData packetData) throws Exception {
            return com.htsmart.wristband2.a.e.b.w(packetData);
        }
    }

    /* loaded from: classes11.dex */
    public class d1 implements Action {
        public d1() {
        }

        @Override // io.reactivex.functions.Action
        public void run() throws Exception {
            d.this.c3 = false;
            d.this.d3.onNext(127);
        }
    }

    /* loaded from: classes11.dex */
    public class e implements Predicate<PacketData> {
        public e(d dVar) {
        }

        @Override // io.reactivex.functions.Predicate
        /* renamed from: a */
        public boolean test(@NonNull PacketData packetData) throws Exception {
            return packetData.getCmdId() == 5 && packetData.getKeyId() == 68;
        }
    }

    /* loaded from: classes11.dex */
    public class e0 implements Function<int[], SparseArray<SportPush>> {
        public e0(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public SparseArray<SportPush> apply(@NonNull int[] iArr) {
            SparseArray<SportPush> sparseArray = new SparseArray<>(iArr.length);
            for (int i : iArr) {
                SportPush sportPush = new SportPush();
                sportPush.setSportType(i);
                sparseArray.put(i, sportPush);
            }
            return sparseArray;
        }
    }

    /* loaded from: classes11.dex */
    public class e1 implements Function<PacketData, WarnHeartRateConfig> {
        public e1(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public WarnHeartRateConfig apply(PacketData packetData) throws Exception {
            return com.htsmart.wristband2.a.e.b.v(packetData);
        }
    }

    /* loaded from: classes11.dex */
    public class f implements Function<PacketData, List<WristbandAlarm>> {
        public f(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public List<WristbandAlarm> apply(PacketData packetData) throws Exception {
            return com.htsmart.wristband2.a.e.b.c(packetData);
        }
    }

    /* loaded from: classes11.dex */
    public class f0 implements Function<PacketData, Boolean> {
        public f0(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Boolean apply(PacketData packetData) throws Exception {
            return Boolean.valueOf(packetData.getKeyData()[0] == 0);
        }
    }

    /* loaded from: classes11.dex */
    public class f1 implements Function<Byte, SingleSource<SyncDataRaw>> {
        public final /* synthetic */ WristbandConfig h;

        /* loaded from: classes11.dex */
        public class a implements Function<PacketData, List<byte[]>> {
            public a(f1 f1Var) {
            }

            @Override // io.reactivex.functions.Function
            /* renamed from: a */
            public List<byte[]> apply(PacketData packetData) throws Exception {
                ArrayList arrayList = new ArrayList(2);
                arrayList.add(packetData.getKeyData());
                arrayList.add(BytesUtil.long2Bytes(System.currentTimeMillis()));
                return arrayList;
            }
        }

        /* loaded from: classes11.dex */
        public class b implements Function<List<byte[]>, SyncDataRaw> {
            public final /* synthetic */ Byte h;

            public b(Byte b) {
                this.h = b;
            }

            @Override // io.reactivex.functions.Function
            /* renamed from: a */
            public SyncDataRaw apply(List<byte[]> list) throws Exception {
                return new SyncDataRaw(this.h.byteValue(), list, f1.this.h);
            }
        }

        public f1(WristbandConfig wristbandConfig) {
            this.h = wristbandConfig;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public SingleSource<SyncDataRaw> apply(Byte b2) throws Exception {
            Single<List<byte[]>> a2;
            if (b2.byteValue() == -1) {
                a2 = d.this.a(com.htsmart.wristband2.a.e.b.w(), com.htsmart.wristband2.a.e.b.W()).map(new a(this));
            } else if (b2.byteValue() == 7) {
                d dVar = d.this;
                a2 = dVar.a(dVar.g3);
            } else {
                a2 = d.this.a(b2.byteValue(), d.this.g3);
            }
            return a2.map(new b(b2));
        }
    }

    /* loaded from: classes11.dex */
    public class g implements Function<PacketData, DialUiInfo> {
        public g(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public DialUiInfo apply(PacketData packetData) throws Exception {
            return com.htsmart.wristband2.a.e.b.b(packetData);
        }
    }

    /* loaded from: classes11.dex */
    public class g0 implements Consumer<String> {
        public g0() {
        }

        @Override // io.reactivex.functions.Consumer
        /* renamed from: a */
        public void accept(String str) throws Exception {
            if (WristbandApplication.UPGRADE_PRODUCTION_TEST) {
                return;
            }
            if (!BluetoothAdapter.checkBluetoothAddress(str)) {
                WristbandLog.w("pair(%s) error:address invalid", str);
                return;
            }
            RxBleDevice rxBleDevice = d.this.getRxBleDevice();
            if (rxBleDevice != null) {
                com.htsmart.wristband2.utils.a.a(rxBleDevice.getMacAddress(), str);
            }
            if (d.this.f11942a == null) {
                WristbandLog.w("pair(%s) error:adapter null", str);
                return;
            }
            BluetoothDevice remoteDevice = d.this.f11942a.getRemoteDevice(str);
            int bondState = remoteDevice.getBondState();
            if (bondState != 10) {
                WristbandLog.w("pair(%s) skip:bondState %d", str, Integer.valueOf(bondState));
                return;
            }
            boolean J0 = d.this.J0(remoteDevice);
            WristbandLog.w("pair(%s) result:%b", str, Boolean.valueOf(J0));
            if (J0) {
                return;
            }
            Intent intent = new Intent("com.htsmart.wristband2.internal.wristband.createBondFailed");
            intent.putExtra("android.bluetooth.device.extra.DEVICE", remoteDevice);
            WristbandApplication.getContext().sendBroadcast(intent);
        }
    }

    /* loaded from: classes11.dex */
    public class g1 implements Function<PacketData, WarnBloodPressureConfig> {
        public g1(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public WarnBloodPressureConfig apply(PacketData packetData) throws Exception {
            return com.htsmart.wristband2.a.e.b.u(packetData);
        }
    }

    /* loaded from: classes11.dex */
    public class h implements Consumer<Throwable> {
        public h() {
        }

        @Override // io.reactivex.functions.Consumer
        /* renamed from: a */
        public void accept(Throwable th) throws Exception {
            WristbandLog.i(th, "AuthenticatedException doClose", new Object[0]);
            d.this.close();
        }
    }

    /* loaded from: classes11.dex */
    public class h0 implements Function<PacketData, Integer> {
        public h0() {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Integer apply(PacketData packetData) throws Exception {
            byte cmdId = packetData.getCmdId();
            byte keyId = packetData.getKeyId();
            if (cmdId == 2) {
                if (keyId == 53) {
                    keyId = 0;
                } else if (keyId == 60) {
                    d.this.o1().onErrorComplete().subscribe();
                    keyId = 1;
                } else if (keyId == 81) {
                    keyId = 21;
                } else if (keyId == 109) {
                    keyId = 22;
                } else if (keyId == 110) {
                    keyId = 23;
                } else if (keyId == 120) {
                    keyId = 16;
                } else if (keyId == 124) {
                    keyId = 6;
                } else if (keyId == -121) {
                    keyId = 31;
                } else if (keyId == -110) {
                    byte[] keyData = packetData.getKeyData();
                    if (keyData != null && keyData.length > 0) {
                        if (keyData[0] == 1) {
                            keyId = 17;
                        } else if (keyData[0] == 2) {
                            keyId = 18;
                        }
                    }
                } else if (keyId == -100) {
                    keyId = 41;
                } else if (keyId == -86) {
                    keyId = 81;
                }
            } else if (cmdId == 4) {
                if (keyId == 32) {
                    keyId = 2;
                }
            } else if (cmdId == 7) {
                if (keyId == 1) {
                    keyId = 3;
                } else if (keyId == 3) {
                    byte[] keyData2 = packetData.getKeyData();
                    if (keyData2 != null && keyData2.length > 0) {
                        if (keyData2[0] == 0 || keyData2[0] == 1 || keyData2[0] == 2) {
                            keyId = 11;
                        } else if (keyData2[0] == 3) {
                            keyId = 12;
                        } else if (keyData2[0] == 4) {
                            keyId = 13;
                        } else if (keyData2[0] == 5) {
                            keyId = 14;
                        } else if (keyData2[0] == 6) {
                            keyId = 15;
                        }
                    }
                } else if (keyId == 4) {
                    keyId = 4;
                } else if (keyId == 5) {
                    keyId = 5;
                }
            }
            return Integer.valueOf(keyId);
        }
    }

    /* loaded from: classes11.dex */
    public class h1 implements Function<Integer, ObservableSource<Byte>> {
        public final /* synthetic */ WristbandVersion h;

        public h1(WristbandVersion wristbandVersion) {
            this.h = wristbandVersion;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public ObservableSource<Byte> apply(Integer num) throws Exception {
            WristbandLog.i("Sync prepare total size:" + num, new Object[0]);
            d.this.e3 = num.intValue();
            return d.this.F0(this.h, num.intValue()).andThen(d.this.r0(this.h, num.intValue()));
        }
    }

    /* loaded from: classes11.dex */
    public class i implements Function<PacketData, List<WristbandSchedule>> {
        public i(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public List<WristbandSchedule> apply(PacketData packetData) throws Exception {
            return com.htsmart.wristband2.a.e.b.p(packetData);
        }
    }

    /* loaded from: classes11.dex */
    public class i0 implements Predicate<PacketData> {
        public i0(d dVar) {
        }

        @Override // io.reactivex.functions.Predicate
        /* renamed from: a */
        public boolean test(@NonNull PacketData packetData) throws Exception {
            return packetData.getCmdId() == 2 && packetData.getKeyId() == -98;
        }
    }

    /* loaded from: classes11.dex */
    public class i1 implements Action {
        public i1() {
        }

        @Override // io.reactivex.functions.Action
        public void run() throws Exception {
            d.this.S2 = WristbandConfig.newInstance(new byte[100]);
            d.this.N = true;
            d.this.V2.onNext(ConnectionState.CONNECTED);
        }
    }

    /* loaded from: classes11.dex */
    public class j implements Function<PacketData, SingleSource<DialBinInfo>> {
        public final /* synthetic */ WristbandConfig h;

        /* loaded from: classes11.dex */
        public class a implements Function<LcdShape, DialBinInfo> {
            public final /* synthetic */ DialBinInfo h;

            public a(j jVar, DialBinInfo dialBinInfo) {
                this.h = dialBinInfo;
            }

            @Override // io.reactivex.functions.Function
            /* renamed from: a */
            public DialBinInfo apply(@NonNull LcdShape lcdShape) throws Exception {
                this.h.setShape(lcdShape.getShape());
                return this.h;
            }
        }

        public j(WristbandConfig wristbandConfig) {
            this.h = wristbandConfig;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public SingleSource<DialBinInfo> apply(@NonNull PacketData packetData) throws Exception {
            DialBinInfo a2 = com.htsmart.wristband2.a.e.b.a(packetData, this.h.getWristbandVersion());
            if (this.h.getWristbandVersion().isExtLcdShape()) {
                return d.this.requestLcdShape().map(new a(this, a2));
            }
            DialDrawer.Shape createFromLcd = DialDrawer.Shape.createFromLcd(a2.getLcd());
            if (createFromLcd != null && createFromLcd.isShapeRectangle() && createFromLcd.width() != createFromLcd.height()) {
                createFromLcd.setCorners((createFromLcd.width() == 172 && createFromLcd.height() == 320) ? 18 : 48);
            }
            a2.setShape(createFromLcd);
            return Single.just(a2);
        }
    }

    /* loaded from: classes11.dex */
    public class j0 implements Function<PacketData, HealthyConfig> {
        public j0(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public HealthyConfig apply(PacketData packetData) throws Exception {
            return com.htsmart.wristband2.a.e.b.l(packetData);
        }
    }

    /* loaded from: classes11.dex */
    public class j1 implements Function<Byte, CompletableSource> {
        public j1(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public CompletableSource apply(Byte b) throws Exception {
            WristbandLog.i("Sync prepare ecgStatus:" + b, new Object[0]);
            if (b.byteValue() != 0) {
                return Completable.error(b.byteValue() == 1 ? new SyncStartFailedException(1) : b.byteValue() == 2 ? new SyncStartFailedException(2) : new SyncStartFailedException());
            }
            return Completable.complete();
        }
    }

    /* loaded from: classes11.dex */
    public class k implements Function<PacketData, SportRealTimeStatus> {
        public k(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public SportRealTimeStatus apply(@NonNull PacketData packetData) throws Exception {
            byte[] keyData = packetData.getKeyData();
            if (keyData == null || keyData.length < 8) {
                throw new PacketDataFormatException("", packetData);
            }
            return new SportRealTimeStatus((int) (SyncDataParser.parserTime4Bytes(keyData, 0, new GregorianCalendar()) / 1000), keyData[6] & 255);
        }
    }

    /* loaded from: classes11.dex */
    public class k0 implements Predicate<PacketData> {
        public k0(d dVar) {
        }

        @Override // io.reactivex.functions.Predicate
        /* renamed from: a */
        public boolean test(PacketData packetData) throws Exception {
            byte cmdId = packetData.getCmdId();
            byte keyId = packetData.getKeyId();
            if (cmdId == 2 && (keyId == 53 || keyId == 60 || keyId == 81 || keyId == 109 || keyId == 110 || keyId == 120 || keyId == 124 || keyId == -121 || keyId == -110)) {
                return true;
            }
            if (cmdId == 4 && keyId == 32) {
                return true;
            }
            return cmdId == 7 && (keyId == 1 || keyId == 3 || keyId == 4 || keyId == 5);
        }
    }

    /* loaded from: classes11.dex */
    public class k1 implements Function<PacketData, NotDisturbConfig> {
        public k1(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public NotDisturbConfig apply(PacketData packetData) throws Exception {
            return com.htsmart.wristband2.a.e.b.m(packetData);
        }
    }

    /* loaded from: classes11.dex */
    public class l implements Function<PacketData, ExerciseTarget> {
        public l(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public ExerciseTarget apply(@NonNull PacketData packetData) throws Exception {
            byte[] keyData = packetData.getKeyData();
            if (keyData == null || keyData.length < 16) {
                throw new PacketDataFormatException("parserExerciseTarget", packetData);
            }
            return new ExerciseTarget(BytesUtil.bytes2Int(keyData, 0, 4, true), BytesUtil.bytes2Int(keyData, 4, 4, true), BytesUtil.bytes2Int(keyData, 8, 4, true), SyncDataParser.parserTime4Bytes(keyData, 12, new GregorianCalendar()));
        }
    }

    /* loaded from: classes11.dex */
    public class l0 implements s1 {
        public l0() {
        }

        @Override // com.htsmart.wristband2.a.e.d.s1
        public void a(int i) {
            int i2 = d.this.e3;
            if (i2 <= 0) {
                WristbandLog.w("OnSyncSize Add:" + i + ", but totalSize is 0", new Object[0]);
                return;
            }
            WristbandLog.i("OnSyncSize Add:" + i, new Object[0]);
            int addAndGet = (int) ((((float) d.this.f3.addAndGet(i)) / ((float) i2)) * 100.0f);
            if (addAndGet > 100) {
                addAndGet = 100;
            }
            d.this.d3.onNext(Integer.valueOf(addAndGet));
        }
    }

    /* loaded from: classes11.dex */
    public class l1 implements Function<PacketData, List<Integer>> {
        public final /* synthetic */ WristbandVersion h;

        public l1(d dVar, WristbandVersion wristbandVersion) {
            this.h = wristbandVersion;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public List<Integer> apply(@NonNull PacketData packetData) throws Exception {
            byte[] keyData = packetData.getKeyData();
            if (keyData != null) {
                if (keyData.length >= 1) {
                    ArrayList arrayList = new ArrayList();
                    if (keyData.length > 2) {
                        for (int i = 1; i < keyData.length; i++) {
                            arrayList.add(Integer.valueOf(keyData[i] & 255));
                        }
                    }
                    if (this.h.isExtNucleicAcidCode()) {
                        arrayList.add(128);
                    }
                    return arrayList;
                }
            }
            throw new PacketDataFormatException("", packetData);
        }
    }

    /* loaded from: classes11.dex */
    public class m implements Function<PacketData, LcdShape> {
        public m(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public LcdShape apply(@NonNull PacketData packetData) throws Exception {
            byte[] keyData = packetData.getKeyData();
            if (keyData == null || keyData.length < 7) {
                throw new PacketDataFormatException("parserLcdShape", packetData);
            }
            int i = keyData[0] & 255;
            int i2 = keyData[1] & 255;
            int bytes2Int = BytesUtil.bytes2Int(keyData, 2, 2, true);
            return new LcdShape(i, i2 == 0 ? DialDrawer.Shape.createRectangle(bytes2Int, BytesUtil.bytes2Int(keyData, 4, 2, true), keyData[6] & 255) : DialDrawer.Shape.createCircle(bytes2Int));
        }
    }

    /* loaded from: classes11.dex */
    public class m0 implements Function<PacketData, SedentaryConfig> {
        public m0(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public SedentaryConfig apply(PacketData packetData) throws Exception {
            return com.htsmart.wristband2.a.e.b.q(packetData);
        }
    }

    /* loaded from: classes11.dex */
    public class m1 implements Function<PacketData, HealthyDataResult> {
        public final /* synthetic */ WristbandConfig h;

        public m1(d dVar, WristbandConfig wristbandConfig) {
            this.h = wristbandConfig;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public HealthyDataResult apply(PacketData packetData) throws Exception {
            return com.htsmart.wristband2.a.e.b.b(packetData, this.h.getWristbandVersion());
        }
    }

    /* loaded from: classes11.dex */
    public class n implements Function<PacketData, SportRealTimeData> {
        public final /* synthetic */ GregorianCalendar h;

        public n(d dVar, GregorianCalendar gregorianCalendar) {
            this.h = gregorianCalendar;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public SportRealTimeData apply(@NonNull PacketData packetData) throws Exception {
            byte[] keyData = packetData.getKeyData();
            if (keyData == null || keyData.length < 13) {
                throw new PacketDataFormatException("", packetData);
            }
            SportRealTimeData sportRealTimeData = new SportRealTimeData();
            sportRealTimeData.setSportTimeId((int) (SyncDataParser.parserTime4Bytes(keyData, 0, this.h) / 1000));
            sportRealTimeData.setStep(BytesUtil.bytes2Int(keyData, 4, 4, true));
            sportRealTimeData.setHeartRate(keyData[12] & 255);
            return sportRealTimeData;
        }
    }

    /* loaded from: classes11.dex */
    public class n0 implements Function<PacketData, PeripheralsRequest> {
        public n0(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public PeripheralsRequest apply(PacketData packetData) throws Exception {
            byte[] keyData = packetData.getKeyData();
            byte b = keyData[0];
            Peripherals peripherals = Peripherals.BLOOD_GLUCOSE_METER;
            if (b == peripherals.getType()) {
                return new PeripheralsRequest(peripherals, keyData[1]);
            }
            throw new IllegalArgumentException();
        }
    }

    /* loaded from: classes11.dex */
    public class n1 implements Function<Integer, SingleSource<Boolean>> {
        public final /* synthetic */ int h;
        public final /* synthetic */ boolean i;
        public final /* synthetic */ int j;

        public n1(int i, boolean z, int i2) {
            this.h = i;
            this.i = z;
            this.j = i2;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public SingleSource<Boolean> apply(@NonNull Integer num) throws Exception {
            int intValue = num.intValue();
            int i = this.h;
            if (intValue == i) {
                d dVar = d.this;
                return dVar.a(dVar.n0(i, 0, this.i ? 2 : 3, this.j)).andThen(Single.just(Boolean.TRUE));
            }
            return Single.just(Boolean.FALSE);
        }
    }

    /* loaded from: classes11.dex */
    public class o implements Function<PacketData, WristbandVersion> {
        public o(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public WristbandVersion apply(PacketData packetData) throws Exception {
            return com.htsmart.wristband2.a.e.b.x(packetData);
        }
    }

    /* loaded from: classes11.dex */
    public class o0 implements Consumer<WristbandConfig> {
        public o0() {
        }

        @Override // io.reactivex.functions.Consumer
        /* renamed from: a */
        public void accept(WristbandConfig wristbandConfig) throws Exception {
            d.this.m1().subscribe();
            d.this.b(wristbandConfig);
        }
    }

    /* loaded from: classes11.dex */
    public class o1 implements Function<PacketData, Boolean> {
        public o1(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Boolean apply(PacketData packetData) throws Exception {
            return Boolean.valueOf(com.htsmart.wristband2.a.e.b.t(packetData));
        }
    }

    /* loaded from: classes11.dex */
    public class p implements Function<PacketData, Boolean> {
        public p(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Boolean apply(@NonNull PacketData packetData) throws Exception {
            byte[] keyData = packetData.getKeyData();
            if (keyData != null) {
                if (keyData.length >= 1) {
                    return Boolean.valueOf(keyData[0] == 0);
                }
            }
            throw new PacketDataFormatException("parserLockScreen", packetData);
        }
    }

    /* loaded from: classes11.dex */
    public class p0 implements Function<PacketData, DrinkWaterConfig> {
        public p0(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public DrinkWaterConfig apply(PacketData packetData) throws Exception {
            return com.htsmart.wristband2.a.e.b.g(packetData);
        }
    }

    /* loaded from: classes11.dex */
    public class p1 implements Function<PacketData, Integer> {
        public p1(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Integer apply(@NonNull PacketData packetData) throws Exception {
            byte[] keyData = packetData.getKeyData();
            if (keyData == null || keyData.length < 8) {
                throw new PacketDataFormatException("", packetData);
            }
            return Integer.valueOf((int) (SyncDataParser.parserTime4Bytes(keyData, 0, new GregorianCalendar()) / 1000));
        }
    }

    /* loaded from: classes11.dex */
    public class q implements Predicate<PacketData> {
        public q(d dVar) {
        }

        @Override // io.reactivex.functions.Predicate
        /* renamed from: a */
        public boolean test(@NonNull PacketData packetData) throws Exception {
            return packetData.getCmdId() == 5 && packetData.getKeyId() == 70;
        }
    }

    /* loaded from: classes11.dex */
    public class q0 implements Predicate<PacketData> {
        public q0(d dVar) {
        }

        @Override // io.reactivex.functions.Predicate
        /* renamed from: a */
        public boolean test(PacketData packetData) throws Exception {
            byte[] keyData;
            return packetData.getCmdId() == 7 && packetData.getKeyId() == 8 && (keyData = packetData.getKeyData()) != null && keyData.length >= 2 && keyData[0] == Peripherals.BLOOD_GLUCOSE_METER.getType();
        }
    }

    /* loaded from: classes11.dex */
    public class q1 implements Function<Boolean, SingleSource<? extends Boolean>> {
        public final /* synthetic */ boolean h;

        public q1(boolean z) {
            this.h = z;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public SingleSource<? extends Boolean> apply(@NonNull Boolean bool) throws Exception {
            return (bool.booleanValue() || this.h) ? d.this.n1().andThen(Single.just(bool)) : Single.just(Boolean.FALSE);
        }
    }

    /* loaded from: classes11.dex */
    public class r implements Function<PacketData, NotificationConfig> {
        public r(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public NotificationConfig apply(PacketData packetData) throws Exception {
            return com.htsmart.wristband2.a.e.b.n(packetData);
        }
    }

    /* loaded from: classes11.dex */
    public class r0 implements Function<Flowable<Throwable>, Publisher<?>> {

        /* loaded from: classes11.dex */
        public class a implements Function<Throwable, Publisher<?>> {
            public a() {
            }

            @Override // io.reactivex.functions.Function
            /* renamed from: a */
            public Publisher<?> apply(Throwable th) throws Exception {
                if ((th instanceof AuthenticatedException) || !d.this.d()) {
                    d.this.W2.onNext(d.this.a(th, false));
                    return Flowable.error(th);
                }
                d.this.W2.onNext(d.this.a(th, true));
                return Flowable.timer(1000L, TimeUnit.MILLISECONDS);
            }
        }

        public r0() {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Publisher<?> apply(Flowable<Throwable> flowable) throws Exception {
            return flowable.flatMap(new a());
        }
    }

    /* loaded from: classes11.dex */
    public class s implements Consumer<Throwable> {
        public s() {
        }

        @Override // io.reactivex.functions.Consumer
        /* renamed from: a */
        public void accept(Throwable th) throws Exception {
            d.this.close();
        }
    }

    /* loaded from: classes11.dex */
    public class s0 implements Function<PacketData, TurnWristLightingConfig> {
        public s0(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public TurnWristLightingConfig apply(PacketData packetData) throws Exception {
            return com.htsmart.wristband2.a.e.b.s(packetData);
        }
    }

    /* loaded from: classes11.dex */
    public interface s1 {
        void a(int i);
    }

    /* loaded from: classes11.dex */
    public class t implements Function<PacketData, Boolean> {
        public t(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Boolean apply(PacketData packetData) throws Exception {
            return Boolean.valueOf(packetData.getKeyData()[0] == 0);
        }
    }

    /* loaded from: classes11.dex */
    public class t0 implements Function<PacketData, PowerSaveMode> {
        public t0(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public PowerSaveMode apply(@NonNull PacketData packetData) throws Exception {
            int i;
            byte[] keyData = packetData.getKeyData();
            if (keyData == null || keyData.length < 1) {
                throw new PacketDataFormatException("parserPowerSaveMode", packetData);
            }
            int i2 = 0;
            boolean z = (keyData[0] & 255) > 0;
            if (keyData.length >= 5) {
                i2 = AbstractConfig.adjustTime(((keyData[1] & 255) << 8) | (keyData[2] & 255));
                i = AbstractConfig.adjustTime((keyData[4] & 255) | ((keyData[3] & 255) << 8));
            } else {
                i = 0;
            }
            return new PowerSaveMode(z, i2, i);
        }
    }

    /* loaded from: classes11.dex */
    public class u implements Function<PacketData, PageConfig> {
        public u(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public PageConfig apply(PacketData packetData) throws Exception {
            return com.htsmart.wristband2.a.e.b.o(packetData);
        }
    }

    /* loaded from: classes11.dex */
    public class u0 implements Function<PacketData, Integer> {
        public u0(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Integer apply(PacketData packetData) throws Exception {
            return Integer.valueOf(com.htsmart.wristband2.a.e.b.r(packetData));
        }
    }

    /* loaded from: classes11.dex */
    public class v implements Function<PacketData, Boolean> {
        public v(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Boolean apply(@NonNull PacketData packetData) throws Exception {
            byte[] keyData = packetData.getKeyData();
            if (keyData != null) {
                if (keyData.length >= 1) {
                    return Boolean.valueOf(keyData[0] == 0);
                }
            }
            throw new PacketDataFormatException("parserLockGame", packetData);
        }
    }

    /* loaded from: classes11.dex */
    public class v0 implements Function<PacketData, BloodPressureConfig> {
        public v0(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public BloodPressureConfig apply(PacketData packetData) throws Exception {
            return com.htsmart.wristband2.a.e.b.f(packetData);
        }
    }

    /* loaded from: classes11.dex */
    public class w implements Function<PacketData, Byte> {
        public w(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Byte apply(@NonNull PacketData packetData) throws Exception {
            return Byte.valueOf(packetData.getKeyData()[0]);
        }
    }

    /* loaded from: classes11.dex */
    public class w0 implements Consumer<Throwable> {
        public w0() {
        }

        @Override // io.reactivex.functions.Consumer
        /* renamed from: a */
        public void accept(Throwable th) throws Exception {
            int i;
            if (th instanceof BleDisconnectedException) {
                i = -1;
            } else {
                if (th instanceof SyncStartFailedException) {
                    int reason = ((SyncStartFailedException) th).getReason();
                    if (reason == 1) {
                        i = -2;
                    } else if (reason == 2) {
                        i = -3;
                    }
                }
                i = WristbandManager.SYNC_STATE_FAILED_UNKNOWN;
            }
            d.this.c3 = false;
            d.this.d3.onNext(Integer.valueOf(i));
        }
    }

    /* loaded from: classes11.dex */
    public class x implements Function<PacketData, FunctionConfig> {
        public x(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public FunctionConfig apply(PacketData packetData) throws Exception {
            return com.htsmart.wristband2.a.e.b.j(packetData);
        }
    }

    /* loaded from: classes11.dex */
    public class x0 implements Function<Byte, SingleSource<WristbandConfig>> {
        public x0() {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public SingleSource<WristbandConfig> apply(@NonNull Byte b) throws Exception {
            if (b.byteValue() == 0) {
                if (d.this.M) {
                    d.this.M = false;
                } else {
                    d.this.K = false;
                }
                Completable g0 = d.this.g0();
                d dVar = d.this;
                return g0.andThen(dVar.G0(dVar.X2, d.this.Y2, d.this.Z2, d.this.a3)).andThen(d.this.f0());
            }
            throw new AuthenticatedException(b.byteValue());
        }
    }

    /* loaded from: classes11.dex */
    public class y implements Function<SparseArray<SportPush>, List<SportPush>> {
        public y(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public List<SportPush> apply(@NonNull SparseArray<SportPush> sparseArray) {
            if (sparseArray.size() <= 0) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList(sparseArray.size());
            for (int i = 0; i < sparseArray.size(); i++) {
                arrayList.add(sparseArray.valueAt(i));
            }
            return arrayList;
        }
    }

    /* loaded from: classes11.dex */
    public class y0 implements Action {
        public final /* synthetic */ PacketData h;

        public y0(PacketData packetData) {
            this.h = packetData;
        }

        @Override // io.reactivex.functions.Action
        public void run() throws Exception {
            d.this.b3 = this.h.getKeyData();
        }
    }

    /* loaded from: classes11.dex */
    public class z implements Function<PacketData, AssistInfo> {
        public z(d dVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public AssistInfo apply(PacketData packetData) throws Exception {
            return com.htsmart.wristband2.a.e.b.a(packetData);
        }
    }

    /* loaded from: classes11.dex */
    public class z0 implements Consumer<WristbandConfig> {
        public z0() {
        }

        @Override // io.reactivex.functions.Consumer
        /* renamed from: a */
        public void accept(WristbandConfig wristbandConfig) throws Exception {
            d.this.S2 = wristbandConfig;
            d.this.N = true;
            d.this.w0(wristbandConfig);
            d.this.b3 = null;
            d.this.V2.onNext(ConnectionState.CONNECTED);
        }
    }

    @SuppressLint({"CheckResult"})
    public d() {
        k().filter(new Predicate() { // from class: com.htsmart.wristband2.a.e.o
            @Override // io.reactivex.functions.Predicate
            public final boolean test(Object obj) {
                boolean L0;
                L0 = d.L0((PacketData) obj);
                return L0;
            }
        }).map(new Function() { // from class: com.htsmart.wristband2.a.e.h
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                String N0;
                N0 = d.N0((PacketData) obj);
                return N0;
            }
        }).delay(3L, TimeUnit.SECONDS).subscribe(new g0());
    }

    public static /* synthetic */ boolean L0(PacketData packetData) throws Exception {
        return packetData.getCmdId() == 2 && packetData.getKeyId() == -124;
    }

    public static /* synthetic */ String N0(PacketData packetData) throws Exception {
        byte[] keyData = packetData.getKeyData();
        return (keyData == null || keyData.length != 6) ? "" : BytesUtil.bytes2HexStr(keyData).replace(HexStringBuilder.DEFAULT_SEPARATOR, ":");
    }

    public static /* synthetic */ boolean R0(PacketData packetData) throws Exception {
        return packetData.getCmdId() == 2 && packetData.getKeyId() == -107;
    }

    public static /* synthetic */ String T0(PacketData packetData) throws Exception {
        String str;
        byte[] keyData = packetData.getKeyData();
        try {
            str = new String(keyData, 1, keyData[0] & 255);
        } catch (Exception e2) {
            WristbandLog.w(e2, "observerHangUpSms", new Object[0]);
            str = null;
        }
        return str == null ? "" : str;
    }

    public static /* synthetic */ List U0(PacketData packetData) throws Exception {
        byte[] keyData = packetData.getKeyData();
        if (keyData == null || keyData.length < 1) {
            throw new PacketDataFormatException("parserGameSkins", packetData);
        }
        int i2 = keyData[0] & 255;
        if (i2 <= 0) {
            return Collections.emptyList();
        }
        if (keyData.length >= (i2 * 5) + 1) {
            ArrayList arrayList = new ArrayList(i2);
            int i3 = 1;
            for (int i4 = 0; i4 < i2; i4++) {
                GameSpace gameSpace = new GameSpace();
                gameSpace.setGameType(keyData[i3] & 255);
                gameSpace.setSkinNum(BytesUtil.bytes2Int(keyData, i3 + 1, 3, true));
                gameSpace.setBinFlag((byte) (i4 + 192));
                gameSpace.setSpaceSize((keyData[i3 + 4] & 255) * 32);
                arrayList.add(gameSpace);
                i3 += 5;
            }
            return arrayList;
        }
        throw new PacketDataFormatException("parserGameSkins", packetData);
    }

    public static /* synthetic */ List X0(PacketData packetData) throws Exception {
        byte[] keyData = packetData.getKeyData();
        ArrayList arrayList = new ArrayList(3);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        int i2 = 0;
        while (true) {
            int i3 = i2 + 12;
            if (i3 > keyData.length) {
                return arrayList;
            }
            arrayList.add(com.htsmart.wristband2.a.e.b.a(keyData, i2, gregorianCalendar));
            i2 = i3;
        }
    }

    public static /* synthetic */ int[] Z0(PacketData packetData) throws Exception {
        byte[] keyData = packetData.getKeyData();
        if (keyData == null || keyData.length < 1) {
            throw new PacketDataFormatException("parserSupportGameTypes", packetData);
        }
        int i2 = 0;
        int i3 = keyData[0] & 255;
        if (i3 <= 0) {
            return new int[0];
        }
        if (keyData.length >= i3 + 1) {
            int[] iArr = new int[i3];
            while (i2 < i3) {
                int i4 = i2 + 1;
                iArr[i2] = keyData[i4] & 255;
                i2 = i4;
            }
            return iArr;
        }
        throw new PacketDataFormatException("parserSupportGameTypes", packetData);
    }

    public static /* synthetic */ int[] b1(PacketData packetData) throws Exception {
        byte[] keyData = packetData.getKeyData();
        if (keyData == null || keyData.length < 2) {
            throw new PacketDataFormatException("parserSupportSportTypes", packetData);
        }
        int i2 = 0;
        int bytes2Int = BytesUtil.bytes2Int(keyData, 0, 2, true);
        if (bytes2Int <= 0) {
            return new int[0];
        }
        if (keyData.length >= (bytes2Int + 1) * 2) {
            int[] iArr = new int[bytes2Int];
            while (i2 < bytes2Int) {
                int i3 = i2 + 1;
                iArr[i2] = BytesUtil.bytes2Int(keyData, i3 * 2, 2, true);
                i2 = i3;
            }
            return iArr;
        }
        throw new PacketDataFormatException("parserSupportSportTypes", packetData);
    }

    public static boolean d(BluetoothDevice bluetoothDevice) {
        try {
            return ((Boolean) BluetoothDevice.class.getMethod("removeBond", new Class[0]).invoke(bluetoothDevice, new Object[0])).booleanValue();
        } catch (Exception e2) {
            WristbandLog.w(e2, "removeBond %s error", bluetoothDevice.getAddress());
            return false;
        }
    }

    public static /* synthetic */ Integer d1(PacketData packetData) throws Exception {
        if (packetData.getKeyData() == null || packetData.getKeyData().length < 1) {
            throw new PacketDataFormatException("GgSetUserInfo", packetData);
        }
        return Integer.valueOf(packetData.getKeyData()[0] & 255);
    }

    public static /* synthetic */ List u0(int i2, PacketData packetData) throws Exception {
        byte[] keyData = packetData.getKeyData();
        if (keyData != null) {
            int i3 = 1;
            if (keyData.length >= 1) {
                int i4 = keyData[0] & 255;
                if (i4 <= 0) {
                    return Collections.emptyList();
                }
                ArrayList arrayList = new ArrayList(i4);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                while (true) {
                    int i5 = i3 + 12;
                    if (i5 > keyData.length) {
                        return arrayList;
                    }
                    GameData a2 = com.htsmart.wristband2.a.e.b.a(keyData, i3, gregorianCalendar);
                    if (a2.getType() == i2) {
                        arrayList.add(a2);
                    }
                    i3 = i5;
                }
            }
        }
        throw new PacketDataFormatException("parserHighestGameRecords", packetData);
    }

    @Deprecated
    public Single<FunctionConfig> A() {
        return !isConnected() ? Single.error(this.U2) : a(com.htsmart.wristband2.a.e.b.n(), com.htsmart.wristband2.a.e.b.N()).map(new x(this));
    }

    public final boolean A0(boolean z2, int i2, float f2, float f3) {
        if (i2 <= 0 || i2 > 200) {
            WristbandLog.w("age should limit (0,200]", new Object[0]);
            i2 = 20;
        }
        if (f2 <= 0.0f || f2 > 500.0f) {
            WristbandLog.w("height should limit (0,500]", new Object[0]);
            f2 = 170.0f;
        }
        if (f3 <= 0.0f || f3 > 500.0f) {
            WristbandLog.w("weight should limit (0,500]", new Object[0]);
            f3 = 50.0f;
        }
        if (this.X2 == z2 && this.Y2 == i2 && this.Z2 == f2 && this.a3 == f3) {
            return false;
        }
        this.X2 = z2;
        this.Y2 = i2;
        this.Z2 = f2;
        this.a3 = f3;
        return true;
    }

    @Deprecated
    public Single<HealthyConfig> B() {
        return !isConnected() ? Single.error(this.U2) : a(com.htsmart.wristband2.a.e.b.o(), com.htsmart.wristband2.a.e.b.O()).map(new j0(this));
    }

    @Deprecated
    public Single<NotificationConfig> C() {
        return !isConnected() ? Single.error(this.U2) : a(com.htsmart.wristband2.a.e.b.r(), com.htsmart.wristband2.a.e.b.R()).map(new r(this));
    }

    @Deprecated
    public Single<PageConfig> D() {
        return !isConnected() ? Single.error(this.U2) : a(com.htsmart.wristband2.a.e.b.s(), com.htsmart.wristband2.a.e.b.S()).map(new u(this));
    }

    @Deprecated
    public Single<SedentaryConfig> E() {
        return !isConnected() ? Single.error(this.U2) : a(com.htsmart.wristband2.a.e.b.u(), com.htsmart.wristband2.a.e.b.U()).map(new m0(this));
    }

    public final Completable F0(WristbandVersion wristbandVersion, int i2) {
        return (i2 == 0 || !wristbandVersion.isEcgEnabled()) ? Completable.complete() : p1().flatMapCompletable(new j1(this));
    }

    public final Completable G0(boolean z2, int i2, float f2, float f3) {
        return a(com.htsmart.wristband2.a.e.b.a(z2, i2, f2, f3));
    }

    @Deprecated
    public Single<TurnWristLightingConfig> H() {
        return !isConnected() ? Single.error(this.U2) : a(com.htsmart.wristband2.a.e.b.x(), com.htsmart.wristband2.a.e.b.X()).map(new s0(this));
    }

    public final void I0(@NonNull String str) {
        BluetoothAdapter bluetoothAdapter = this.f11942a;
        if (bluetoothAdapter == null) {
            WristbandLog.w("clear bond(%s) error:adapter is null", str);
            return;
        }
        BluetoothDevice remoteDevice = bluetoothAdapter.getRemoteDevice(str);
        int i2 = 0;
        while (i2 < 3 && remoteDevice.getBondState() != 10) {
            WristbandLog.w("removeBond %s result:%b", str, Boolean.valueOf(d(remoteDevice)));
            i2++;
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e2) {
                WristbandLog.w(e2, "", new Object[0]);
            }
        }
        WristbandLog.i("clear bond end state:%d", Integer.valueOf(remoteDevice.getBondState()));
    }

    @Deprecated
    public Single<WristbandVersion> J() {
        return !isConnected() ? Single.error(this.U2) : a(com.htsmart.wristband2.a.e.b.C(), com.htsmart.wristband2.a.e.b.c0()).map(new o(this));
    }

    public final boolean J0(BluetoothDevice bluetoothDevice) {
        try {
            boolean booleanValue = ((Boolean) BluetoothDevice.class.getMethod("createBond", Integer.TYPE).invoke(bluetoothDevice, 1)).booleanValue();
            WristbandLog.w("createBond %s result:%b", bluetoothDevice.getAddress(), Boolean.valueOf(booleanValue));
            return booleanValue;
        } catch (Exception e2) {
            WristbandLog.w(e2, "createBond %s error", bluetoothDevice.getAddress());
            return bluetoothDevice.createBond();
        }
    }

    @Override // com.htsmart.wristband2.a.b.a
    public void a(ConnectionError connectionError) {
        this.W2.onNext(connectionError);
    }

    @Override // com.htsmart.wristband2.a.a.a, com.htsmart.wristband2.a.d.c, com.htsmart.wristband2.a.b.a
    public void a(ConnectionState connectionState) {
        super.a(connectionState);
        if (connectionState == ConnectionState.CONNECTED) {
            h0();
            return;
        }
        i0();
        this.V2.onNext(connectionState);
    }

    public void b(@NonNull WristbandConfig wristbandConfig) {
        if (WristbandApplication.UPGRADE_PRODUCTION_TEST) {
            return;
        }
        if (!wristbandConfig.getWristbandVersion().isExtContacts()) {
            WristbandLog.i("audio device:unsupport", new Object[0]);
            return;
        }
        RxBleDevice rxBleDevice = getRxBleDevice();
        if (rxBleDevice != null) {
            String a2 = com.htsmart.wristband2.utils.a.a(rxBleDevice.getMacAddress());
            WristbandLog.i("audio device:%s", a2);
            if (this.f11942a == null || !BluetoothAdapter.checkBluetoothAddress(a2)) {
                WristbandLog.i("audio device:error", new Object[0]);
            } else if (this.f11942a.getRemoteDevice(a2).getBondState() == 12) {
                return;
            } else {
                WristbandLog.i("audio device:bonded", new Object[0]);
            }
        } else {
            WristbandLog.i("audio device:rxBleDevice is null", new Object[0]);
        }
        a(new PacketData((byte) 2, (byte) -122)).onErrorComplete().subscribe();
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable clearAudioDevice() {
        return n1();
    }

    @Override // com.htsmart.wristband2.a.b.a, com.htsmart.wristband2.WristbandManager
    public void close() {
        WristbandLog.i("Impl doClose", new Object[0]);
        super.close();
        i0();
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public void connect(@NonNull BluetoothDevice bluetoothDevice, @NonNull String str, boolean z2, boolean z3, int i2, float f2, float f3) {
        WristbandLog.i("connect address:%s userIdentity:%s bindOrLogin:%b", bluetoothDevice.getAddress(), str, Boolean.valueOf(z2));
        A0(z3, i2, f2, f3);
        x0(bluetoothDevice.getAddress(), str, z2);
        a(bluetoothDevice);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public void connect(@NonNull RxBleDevice rxBleDevice, @NonNull String str, boolean z2, boolean z3, int i2, float f2, float f3) {
        WristbandLog.i("connect address:%s userIdentity:%s bindOrLogin:%b", rxBleDevice.getMacAddress(), str, Boolean.valueOf(z2));
        A0(z3, i2, f2, f3);
        x0(rxBleDevice.getMacAddress(), str, z2);
        a(rxBleDevice);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public void connect(@NonNull String str, @NonNull String str2, boolean z2, boolean z3, int i2, float f2, float f3) {
        WristbandLog.i("connect address:%s userIdentity:%s bindOrLogin:%b", str, str2, Boolean.valueOf(z2));
        A0(z3, i2, f2, f3);
        x0(str, str2, z2);
        a(str);
    }

    public final Single<int[]> d0() {
        return !isConnected() ? Single.error(this.U2) : a(new PacketData((byte) 2, Byte.MIN_VALUE), new PacketData((byte) 2, (byte) -127)).map(new Function() { // from class: com.htsmart.wristband2.a.e.l
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                int[] b12;
                b12 = d.b1((PacketData) obj);
                return b12;
            }
        });
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable deleteWristbandNotification(boolean z2, int i2) {
        return !isConnected() ? Completable.error(this.U2) : a(new PacketData((byte) 2, (byte) -117, new byte[]{z2 ? (byte) 1 : (byte) 0, (byte) i2}));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable dismissWristbandNotification() {
        return !isConnected() ? Completable.error(this.U2) : a(new PacketData((byte) 2, (byte) -116));
    }

    public final Single<Integer> e0() {
        return a(com.htsmart.wristband2.a.e.b.v(), com.htsmart.wristband2.a.e.b.V()).map(new u0(this));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable exitSleepMonitor() {
        return !isConnected() ? Completable.error(this.U2) : a(com.htsmart.wristband2.a.e.b.b());
    }

    public final Single<WristbandConfig> f0() {
        return a(com.htsmart.wristband2.a.e.b.B(), com.htsmart.wristband2.a.e.b.b0()).map(new d0(this));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable findWristband() {
        return !isConnected() ? Completable.error(this.U2) : a(com.htsmart.wristband2.a.e.b.c());
    }

    public final Completable g0() {
        return a(com.htsmart.wristband2.a.e.b.e0());
    }

    @Override // com.htsmart.wristband2.WristbandManager
    @Nullable
    public WristbandConfig getWristbandConfig() {
        return this.S2;
    }

    public final void h0() {
        Disposable subscribe;
        this.N = false;
        if (WristbandApplication.ECG_PRODUCTION_TEST) {
            subscribe = a(new PacketData((byte) 3, (byte) 7)).observeOn(AndroidSchedulers.mainThread()).subscribe(new i1(), new s());
        } else {
            String str = this.J;
            boolean z2 = this.M && this.K;
            WristbandLog.i("userIdentity:" + str + " bindOrLogin:" + z2, new Object[0]);
            subscribe = s0(str, z2).flatMap(new x0()).retryWhen(new r0()).doOnSuccess(new o0()).observeOn(AndroidSchedulers.mainThread()).subscribe(new z0(), new h());
        }
        this.T2 = subscribe;
    }

    public final void i0() {
        this.N = false;
        Disposable disposable = this.T2;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.T2.dispose();
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public boolean isBindOrLogin() {
        return this.K;
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public boolean isConnected() {
        return this.N && super.d();
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public boolean isSyncingData() {
        return this.c3;
    }

    public final Completable m1() {
        return p().a(new com.htsmart.wristband2.a.e.c(this)).ignoreElements().onErrorComplete();
    }

    public final PacketData n0(int i2, int i3, int i4, int i5) {
        byte[] d = com.htsmart.wristband2.a.e.b.d(i2 * 1000);
        byte[] int2Bytes = BytesUtil.int2Bytes(i5, true);
        return new PacketData((byte) 5, com.crrepa.c.a.E0, new byte[]{(byte) ((i3 >> 8) & 255), (byte) (i3 & 255), (byte) i4, d[0], d[1], d[2], d[3], int2Bytes[0], int2Bytes[1], int2Bytes[2], int2Bytes[3]});
    }

    public final Completable n1() {
        RxBleDevice rxBleDevice = getRxBleDevice();
        String a2 = rxBleDevice != null ? com.htsmart.wristband2.utils.a.a(rxBleDevice.getMacAddress()) : null;
        if (BluetoothAdapter.checkBluetoothAddress(a2)) {
            TimeUnit timeUnit = TimeUnit.SECONDS;
            return Completable.timer(1L, timeUnit).andThen(Completable.fromAction(new C0563d())).andThen(Completable.timer(1L, timeUnit)).observeOn(AndroidSchedulers.mainThread()).andThen(Completable.fromAction(new a(a2)));
        }
        return Completable.complete();
    }

    public final Completable o1() {
        return !isConnected() ? Completable.error(this.U2) : a(com.htsmart.wristband2.a.e.b.d());
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Observable<byte[]> observerAliAgentData() {
        return new com.htsmart.wristband2.a.e.a(k().filter(new i0(this)));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Observable<ConnectionError> observerConnectionError() {
        return this.W2;
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Observable<ConnectionState> observerConnectionState() {
        return this.V2;
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Observable<String> observerHangUpSms() {
        return k().filter(new Predicate() { // from class: com.htsmart.wristband2.a.e.f
            @Override // io.reactivex.functions.Predicate
            public final boolean test(Object obj) {
                boolean R0;
                R0 = d.R0((PacketData) obj);
                return R0;
            }
        }).map(new Function() { // from class: com.htsmart.wristband2.a.e.n
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                String T0;
                T0 = d.T0((PacketData) obj);
                return T0;
            }
        });
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Observable<PeripheralsRequest> observerPeripheralsRequest() {
        return k().filter(new q0(this)).map(new n0(this));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Observable<SportRealTimeData> observerSportRealTimeData() {
        return k().filter(new q(this)).map(new n(this, new GregorianCalendar()));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Observable<SportRealTimeStatus> observerSportRealTimeStatus() {
        return k().filter(new e(this)).map(new b(this, new GregorianCalendar()));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Observable<Integer> observerSyncDataState() {
        return this.d3;
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Observable<Integer> observerWristbandMessage() {
        return k().filter(new k0(this)).map(new h0());
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Observable<int[]> openEcgRealTimeData() {
        Throwable syncBusyException;
        if (!isConnected()) {
            syncBusyException = this.U2;
        } else if (!this.c3) {
            return q();
        } else {
            syncBusyException = new SyncBusyException();
        }
        return Observable.error(syncBusyException);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Observable<byte[]> openGSensorRealTimeDataForTest() {
        Throwable syncBusyException;
        if (!isConnected()) {
            syncBusyException = this.U2;
        } else if (!this.c3) {
            return r();
        } else {
            syncBusyException = new SyncBusyException();
        }
        return Observable.error(syncBusyException);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Observable<HealthyDataResult> openHealthyRealTimeData(int i2) {
        return openHealthyRealTimeData(i2, 2);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Observable<HealthyDataResult> openHealthyRealTimeData(int i2, int i3) {
        WristbandConfig wristbandConfig = this.S2;
        if (!isConnected() || wristbandConfig == null) {
            return Observable.error(this.U2);
        }
        if (i3 < 1) {
            i3 = 1;
        }
        if (i3 > 255) {
            i3 = 255;
        }
        return this.c3 ? Observable.error(new SyncBusyException()) : a(i2, i3, wristbandConfig.getWristbandVersion());
    }

    public final Single<Byte> p1() {
        return !isConnected() ? Single.error(this.U2) : a(com.htsmart.wristband2.a.e.b.l(), com.htsmart.wristband2.a.e.b.L()).map(new a1(this));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<Boolean> pauseSportRealTime(int i2, int i3) {
        return !isConnected() ? Single.error(this.U2) : t0(true, i2, i3);
    }

    public final Observable<Byte> r0(WristbandVersion wristbandVersion, int i2) {
        ArrayList arrayList = new ArrayList(10);
        if (i2 == 0) {
            arrayList.add((byte) -1);
        } else {
            arrayList.add((byte) 1);
            arrayList.add((byte) 2);
            if (wristbandVersion.isHeartRateEnabled()) {
                arrayList.add((byte) 3);
                if (wristbandVersion.isMeasureDataSyncable()) {
                    arrayList.add((byte) -125);
                }
            }
            if (wristbandVersion.isOxygenEnabled()) {
                arrayList.add((byte) 4);
                if (wristbandVersion.isMeasureDataSyncable()) {
                    arrayList.add((byte) -124);
                }
            }
            if (wristbandVersion.isBloodPressureEnabled()) {
                arrayList.add((byte) 5);
                if (wristbandVersion.isMeasureDataSyncable()) {
                    arrayList.add((byte) -123);
                }
            }
            if (wristbandVersion.isRespiratoryRateEnabled()) {
                arrayList.add((byte) 6);
                if (wristbandVersion.isMeasureDataSyncable()) {
                    arrayList.add((byte) -122);
                }
            }
            if (wristbandVersion.isSportEnabled()) {
                arrayList.add((byte) 16);
            }
            if (wristbandVersion.isTemperatureEnabled()) {
                arrayList.add((byte) 17);
                if (wristbandVersion.isMeasureDataSyncable()) {
                    arrayList.add((byte) -111);
                }
            }
            if (wristbandVersion.isPressureEnabled()) {
                arrayList.add((byte) 18);
                if (wristbandVersion.isMeasureDataSyncable()) {
                    arrayList.add((byte) -110);
                }
            }
            if (wristbandVersion.isGameEnabled()) {
                arrayList.add((byte) 19);
            }
            if (wristbandVersion.isGpsEnabled()) {
                arrayList.add((byte) 10);
            }
            arrayList.add((byte) -1);
            if (wristbandVersion.isEcgEnabled()) {
                arrayList.add((byte) 7);
            }
        }
        return Observable.fromIterable(arrayList);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable replayHangUpSms(boolean z2) {
        if (isConnected()) {
            byte[] bArr = new byte[1];
            bArr[0] = z2 ? (byte) 0 : (byte) -1;
            return a(new PacketData((byte) 2, BleUUID.CMD_ID_96, bArr));
        }
        return Completable.error(this.U2);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable reportSportRealTimeData(int i2, float f2, int i3, int i4, int i5) {
        if (isConnected()) {
            byte[] d = com.htsmart.wristband2.a.e.b.d(i2 * 1000);
            byte[] int2Bytes = BytesUtil.int2Bytes((int) (f2 * 100.0f), true);
            byte[] int2Bytes2 = BytesUtil.int2Bytes(i3, true);
            byte[] int2Bytes3 = BytesUtil.int2Bytes(i4, true);
            byte[] int2Bytes4 = BytesUtil.int2Bytes(i5, true);
            return a(new PacketData((byte) 5, (byte) 71, new byte[]{d[0], d[1], d[2], d[3], int2Bytes[0], int2Bytes[1], int2Bytes[2], int2Bytes[3], int2Bytes2[0], int2Bytes2[1], int2Bytes2[2], int2Bytes2[3], int2Bytes3[0], int2Bytes3[1], int2Bytes3[2], int2Bytes3[3], int2Bytes4[0], int2Bytes4[1], int2Bytes4[2], int2Bytes4[3]}));
        }
        return Completable.error(this.U2);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<List<WristbandAlarm>> requestAlarmList() {
        return !isConnected() ? Single.error(this.U2) : a(com.htsmart.wristband2.a.e.b.e(), com.htsmart.wristband2.a.e.b.E()).map(new f(this));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<AssistInfo> requestAssistInfoForTest() {
        return a(com.htsmart.wristband2.a.e.b.f(), com.htsmart.wristband2.a.e.b.F()).map(new z(this));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<BatteryStatus> requestBattery() {
        return !isConnected() ? Single.error(this.U2) : a(com.htsmart.wristband2.a.e.b.g(), com.htsmart.wristband2.a.e.b.G()).map(new a0(this));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<List<WristbandContacts>> requestContactsList() {
        return !isConnected() ? Single.error(this.U2) : s();
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<DialBinInfo> requestDialBinInfo() {
        WristbandConfig wristbandConfig = this.S2;
        return (!isConnected() || wristbandConfig == null) ? Single.error(this.U2) : a(com.htsmart.wristband2.a.e.b.i(), com.htsmart.wristband2.a.e.b.I()).flatMap(new j(wristbandConfig));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<DialUiInfo> requestDialUiInfo() {
        return !isConnected() ? Single.error(this.U2) : a(com.htsmart.wristband2.a.e.b.j(), com.htsmart.wristband2.a.e.b.J()).map(new g(this));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<Integer> requestEnterOTA() {
        return !isConnected() ? Single.error(this.U2) : a(com.htsmart.wristband2.a.e.b.m(), com.htsmart.wristband2.a.e.b.M()).map(new c(this));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<ExerciseTarget> requestExerciseTarget() {
        return !isConnected() ? Single.error(this.U2) : a(new PacketData((byte) 2, (byte) -103), new PacketData((byte) 2, (byte) -102)).map(new l(this));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<List<GameSpace>> requestGamePushInfo() {
        return !isConnected() ? Single.error(this.U2) : a(new PacketData((byte) 2, com.htsmart.wristband2.a.a.a.R1), new PacketData((byte) 2, (byte) 119)).map(new Function() { // from class: com.htsmart.wristband2.a.e.m
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                List U0;
                U0 = d.U0((PacketData) obj);
                return U0;
            }
        });
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<List<WristbandHabit>> requestHabitList() {
        return !isConnected() ? Single.error(this.U2) : t();
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<List<GameData>> requestHighestGameRecords(@GameData.GameType final int i2) {
        WristbandConfig wristbandConfig = this.S2;
        return (!isConnected() || wristbandConfig == null) ? Single.error(this.U2) : wristbandConfig.getWristbandVersion().isExtSingleGameRecord() ? a(new PacketData((byte) 2, BleUUID.CMD_ID_90, new byte[]{(byte) i2}), new PacketData((byte) 2, (byte) -111)).map(new Function() { // from class: com.htsmart.wristband2.a.e.i
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                List X0;
                X0 = d.X0((PacketData) obj);
                return X0;
            }
        }) : a(new PacketData((byte) 2, (byte) 116), new PacketData((byte) 2, (byte) 117)).map(new Function() { // from class: com.htsmart.wristband2.a.e.e
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                List u02;
                u02 = d.u0(i2, (PacketData) obj);
                return u02;
            }
        });
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<Byte> requestLanguage() {
        return !isConnected() ? Single.error(this.U2) : a(new PacketData((byte) 2, (byte) -91), new PacketData((byte) 2, (byte) -90)).map(new c1(this));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<HealthyDataResult> requestLatestHealthy() {
        WristbandConfig wristbandConfig = this.S2;
        return (!isConnected() || wristbandConfig == null) ? Single.error(this.U2) : a(com.htsmart.wristband2.a.e.b.p(), com.htsmart.wristband2.a.e.b.P()).map(new m1(this, wristbandConfig));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<LcdShape> requestLcdShape() {
        return !isConnected() ? Single.error(this.U2) : a(new PacketData((byte) 2, com.crrepa.c.a.h1), new PacketData((byte) 2, com.crrepa.c.a.l1)).map(new m(this));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<NotDisturbConfig> requestNotDisturbConfig() {
        return !isConnected() ? Single.error(this.U2) : a(com.htsmart.wristband2.a.e.b.q(), com.htsmart.wristband2.a.e.b.Q()).map(new k1(this));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<PowerSaveMode> requestPowerSaveMode() {
        return !isConnected() ? Single.error(this.U2) : a(new PacketData((byte) 2, (byte) -89), new PacketData((byte) 2, (byte) -88)).map(new t0(this));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<List<WristbandSchedule>> requestScheduleList() {
        return !isConnected() ? Single.error(this.U2) : a(com.htsmart.wristband2.a.e.b.t(), com.htsmart.wristband2.a.e.b.T()).map(new i(this));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<List<SyncDataParser.DataItem>> requestSleepRawForTest() {
        return !isConnected() ? Single.error(this.U2) : p().a(new com.htsmart.wristband2.a.a.i(this)).singleOrError();
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<List<SportPush>> requestSportPush() {
        return !isConnected() ? Single.error(this.U2) : d0().map(new e0(this)).flatMap(new b0()).map(new y(this));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<SportRealTimeStatus> requestSportRealTimeStatus() {
        return !isConnected() ? Single.error(this.U2) : a(new PacketData((byte) 5, com.htsmart.wristband2.a.a.a.W0), new PacketData((byte) 5, (byte) 73)).map(new k(this));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<int[]> requestSupportGameTypes() {
        return !isConnected() ? Single.error(this.U2) : a(new PacketData((byte) 2, (byte) 114), new PacketData((byte) 2, (byte) 115)).map(new Function() { // from class: com.htsmart.wristband2.a.e.k
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                int[] Z0;
                Z0 = d.Z0((PacketData) obj);
                return Z0;
            }
        });
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<List<Integer>> requestSupportQrCodes() {
        WristbandConfig wristbandConfig = this.S2;
        if (wristbandConfig == null) {
            return Single.just(Collections.emptyList());
        }
        WristbandVersion wristbandVersion = wristbandConfig.getWristbandVersion();
        if (wristbandVersion.isExtGetSupportQrCode()) {
            return a(new PacketData((byte) 2, BleUUID.CMD_ID_A0), new PacketData((byte) 2, (byte) -95)).map(new l1(this, wristbandVersion));
        }
        ArrayList arrayList = new ArrayList();
        if (wristbandVersion.isExtCollectionCode()) {
            arrayList.add(1);
            arrayList.add(2);
            arrayList.add(3);
            arrayList.add(4);
        }
        if (wristbandVersion.isExtBusinessCard()) {
            arrayList.add(33);
            arrayList.add(34);
            arrayList.add(35);
            arrayList.add(36);
            arrayList.add(37);
            arrayList.add(38);
            arrayList.add(39);
            arrayList.add(40);
            arrayList.add(41);
            arrayList.add(42);
            arrayList.add(43);
        }
        if (wristbandVersion.isExtQrCodeExtra1()) {
            arrayList.add(5);
            arrayList.add(6);
            arrayList.add(7);
            arrayList.add(8);
            arrayList.add(44);
            arrayList.add(45);
            arrayList.add(46);
        }
        if (wristbandVersion.isExtNucleicAcidCode()) {
            arrayList.add(128);
        }
        return Single.just(arrayList);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<WarnBloodPressureConfig> requestWarnBloodPressureConfig() {
        return !isConnected() ? Single.error(this.U2) : a(com.htsmart.wristband2.a.e.b.z(), com.htsmart.wristband2.a.e.b.Z()).map(new g1(this));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<WarnHeartRateConfig> requestWarnHeartRateConfig() {
        return !isConnected() ? Single.error(this.U2) : a(com.htsmart.wristband2.a.e.b.A(), com.htsmart.wristband2.a.e.b.a0()).map(new e1(this));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<WristbandConfig> requestWristbandConfig() {
        return !isConnected() ? Single.error(this.U2) : f0();
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable resetWristband() {
        return !isConnected() ? Completable.error(this.U2) : a(com.htsmart.wristband2.a.e.b.D());
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable restartWristband() {
        return !isConnected() ? Completable.error(this.U2) : a(com.htsmart.wristband2.a.e.b.d0());
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<Boolean> resumeSportRealTime(int i2, int i3) {
        return !isConnected() ? Single.error(this.U2) : t0(false, i2, i3);
    }

    public final Single<Byte> s0(String str, boolean z2) {
        return a(com.htsmart.wristband2.a.e.b.a(z2, str), com.htsmart.wristband2.a.e.b.b(z2)).map(new Function() { // from class: com.htsmart.wristband2.a.e.g
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return Byte.valueOf(b.e((PacketData) obj));
            }
        });
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable sendWristbandNotification(WristbandNotification wristbandNotification) {
        if (isConnected()) {
            return a(com.htsmart.wristband2.a.e.b.a(wristbandNotification, this.S2 != null ? this.S2.getWristbandVersion().isExtNewNotificationFormat() : false));
        }
        return Completable.error(this.U2);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setAlarmList(@Nullable List<WristbandAlarm> list) {
        if (isConnected()) {
            if (list != null) {
                for (WristbandAlarm wristbandAlarm : list) {
                    wristbandAlarm.adjustAlarm();
                }
            }
            return a(com.htsmart.wristband2.a.e.b.a(list));
        }
        return Completable.error(this.U2);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setAliAgentData(byte[] bArr) {
        if (isConnected()) {
            int ceil = (int) Math.ceil(bArr.length / 381.0f);
            ArrayList arrayList = new ArrayList(ceil);
            int i2 = 0;
            while (i2 < ceil) {
                int length = i2 == ceil + (-1) ? bArr.length - (i2 * 381) : 381;
                byte[] bArr2 = new byte[length + 6];
                bArr2[0] = (byte) ceil;
                bArr2[1] = (byte) i2;
                bArr2[2] = (byte) ((bArr.length >> 8) & 255);
                bArr2[3] = (byte) (bArr.length & 255);
                bArr2[4] = (byte) ((length >> 8) & 255);
                bArr2[5] = (byte) (length & 255);
                System.arraycopy(bArr, i2 * 381, bArr2, 6, length);
                arrayList.add(new PacketData((byte) 2, (byte) -99, bArr2));
                i2++;
            }
            return a(arrayList);
        }
        return Completable.error(this.U2);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setAllowWristbandChangeSchedule(boolean z2) {
        if (isConnected()) {
            return a(new PacketData((byte) 2, (byte) 113, z2 ? new byte[]{1} : new byte[]{0}));
        }
        return Completable.error(this.U2);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<Boolean> setAudioDeviceNameForTest(int i2, byte b2, int i3, byte b3, String str) {
        byte[] bArr;
        if (i2 == 90) {
            byte[] bytes = TextUtils.isEmpty(str) ? null : str.getBytes();
            int length = bytes == null ? 0 : bytes.length;
            bArr = new byte[length + 5];
            bArr[0] = 90;
            bArr[1] = b2;
            bArr[2] = (byte) i3;
            bArr[3] = b3;
            bArr[4] = (byte) length;
            if (bytes != null && bytes.length > 0) {
                System.arraycopy(bytes, 0, bArr, 5, length);
            }
        } else if (i2 != 0) {
            return Single.error(new IllegalArgumentException("mode should be 0x5A or 0x00"));
        } else {
            bArr = new byte[5];
            bArr[1] = b2;
        }
        if (isConnected()) {
            PacketData packetData = new PacketData((byte) 2, (byte) -120);
            packetData.setKeyData(bArr);
            return a(packetData, new PacketData((byte) 2, BleUUID.CMD_ID_89)).map(new f0(this));
        }
        return Single.error(this.U2);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setBloodPressureConfig(BloodPressureConfig bloodPressureConfig) {
        return !isConnected() ? Completable.error(this.U2) : a(com.htsmart.wristband2.a.e.b.a(bloodPressureConfig));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setBrightnessVibrateConfig(BrightnessVibrateConfig brightnessVibrateConfig) {
        return !isConnected() ? Completable.error(this.U2) : a(new PacketData((byte) 2, (byte) 123, brightnessVibrateConfig.getBytes()));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setCameraStatus(boolean z2) {
        return !isConnected() ? Completable.error(this.U2) : a(com.htsmart.wristband2.a.e.b.c(z2));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setContactsList(@Nullable List<WristbandContacts> list) {
        return !isConnected() ? Completable.error(this.U2) : a(com.htsmart.wristband2.a.e.b.b(list));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setCustomAdvertising(@NonNull byte[] bArr) {
        if (isConnected()) {
            if (bArr == null || bArr.length != 6) {
                throw new IllegalArgumentException("values must be length 6");
            }
            return a(new PacketData((byte) 2, com.htsmart.wristband2.a.a.a.t1, bArr));
        }
        return Completable.error(this.U2);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setCustomLabels(String[] strArr) {
        byte[] bArr;
        int i2;
        byte[] bArr2;
        int i3;
        int i4;
        if (isConnected()) {
            byte[] bArr3 = null;
            if (strArr.length <= 0 || strArr[0] == null) {
                bArr = null;
                i2 = 0;
            } else {
                bArr = strArr[0].getBytes();
                i2 = Math.min(bArr.length, 64);
            }
            int i5 = 1;
            if (strArr.length <= 1 || strArr[1] == null) {
                bArr2 = null;
                i3 = 0;
            } else {
                bArr2 = strArr[1].getBytes();
                i3 = Math.min(bArr2.length, 64);
            }
            if (strArr.length <= 2 || strArr[2] == null) {
                i4 = 0;
            } else {
                bArr3 = strArr[2].getBytes();
                i4 = Math.min(bArr3.length, 64);
            }
            byte[] bArr4 = new byte[i2 + 3 + i3 + i4];
            bArr4[0] = (byte) i2;
            if (bArr != null && i2 > 0) {
                System.arraycopy(bArr, 0, bArr4, 1, i2);
                i5 = 1 + i2;
            }
            int i6 = i5 + 1;
            bArr4[i5] = (byte) i3;
            if (bArr2 != null && i3 > 0) {
                System.arraycopy(bArr2, 0, bArr4, i6, i3);
                i6 += i3;
            }
            int i7 = i6 + 1;
            bArr4[i6] = (byte) i4;
            if (bArr3 != null && i4 > 0) {
                System.arraycopy(bArr3, 0, bArr4, i7, i4);
            }
            return a(new PacketData((byte) 2, (byte) -97, bArr4));
        }
        return Completable.error(this.U2);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<Boolean> setDeviceAddressForTest(long j2, byte b2) {
        if (isConnected()) {
            PacketData packetData = new PacketData((byte) 2, com.htsmart.wristband2.a.a.a.u1);
            byte[] long2Bytes = BytesUtil.long2Bytes(j2);
            byte[] bArr = new byte[7];
            for (int i2 = 0; i2 < 6; i2++) {
                bArr[i2] = long2Bytes[7 - i2];
            }
            bArr[6] = b2;
            packetData.setKeyData(bArr);
            return a(packetData, new PacketData((byte) 2, (byte) 95)).map(new t(this));
        }
        return Single.error(this.U2);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<Boolean> setDeviceNameForTest(int i2, byte b2, boolean z2, boolean z3, byte b3, String str) {
        byte[] bArr;
        if (i2 == 90) {
            byte[] bytes = TextUtils.isEmpty(str) ? null : str.getBytes();
            int length = bytes == null ? 0 : bytes.length;
            if (z2 && length > 13) {
                return Single.error(new IllegalArgumentException("addBroadcastNameMacSuffix is True, nameContent.getBytes() length limit 13"));
            }
            if (!z2 && length > 17) {
                return Single.error(new IllegalArgumentException("addBroadcastNameMacSuffix is False, nameContent.getBytes() length limit 17"));
            }
            bArr = new byte[length + 6];
            bArr[0] = 90;
            bArr[1] = b2;
            bArr[2] = (byte) (!z2 ? 1 : 0);
            bArr[3] = (byte) (!z3 ? 1 : 0);
            bArr[4] = b3;
            bArr[5] = (byte) length;
            if (bytes != null && bytes.length > 0) {
                System.arraycopy(bytes, 0, bArr, 6, length);
            }
        } else if (i2 != 0) {
            return Single.error(new IllegalArgumentException("mode should be 0x5A or 0x00"));
        } else {
            bArr = new byte[6];
            bArr[1] = b2;
        }
        if (isConnected()) {
            PacketData packetData = new PacketData((byte) 2, (byte) 101);
            packetData.setKeyData(bArr);
            return a(packetData, new PacketData((byte) 2, (byte) 102)).map(new c0(this));
        }
        return Single.error(this.U2);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setDevicePageForTest(int i2, int i3, byte b2) {
        if (isConnected()) {
            PacketData packetData = new PacketData((byte) 2, com.crrepa.c.a.Z0);
            packetData.setKeyData(new byte[]{(byte) ((i2 >> 8) & 255), (byte) (i2 & 255), (byte) ((i3 >> 8) & 255), (byte) (i3 & 255), b2});
            return a(packetData);
        }
        return Completable.error(this.U2);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setDialComponents(int i2, @Nullable byte[] bArr) {
        byte[] bArr2;
        if (isConnected()) {
            PacketData packetData = new PacketData((byte) 2, com.htsmart.wristband2.a.a.a.J1);
            int length = bArr == null ? 0 : bArr.length;
            if (length <= 0) {
                bArr2 = new byte[]{(byte) i2};
            } else {
                byte[] bArr3 = new byte[length + 2];
                bArr3[0] = (byte) i2;
                bArr3[1] = (byte) length;
                System.arraycopy(bArr, 0, bArr3, 2, bArr.length);
                bArr2 = bArr3;
            }
            packetData.setKeyData(bArr2);
            return a(packetData);
        }
        return Completable.error(this.U2);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setDrinkWaterConfig(DrinkWaterConfig drinkWaterConfig) {
        return !isConnected() ? Completable.error(this.U2) : a(com.htsmart.wristband2.a.e.b.a(drinkWaterConfig));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setEngineeringMode() {
        return !isConnected() ? Completable.error(this.U2) : a(new PacketData((byte) 2, Byte.MAX_VALUE));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setExerciseTarget(int i2, int i3, int i4) {
        return !isConnected() ? Completable.error(this.U2) : a(com.htsmart.wristband2.a.e.b.a(i2, i3, i4));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setExerciseTarget(@NonNull ExerciseTarget exerciseTarget) {
        return !isConnected() ? Completable.error(this.U2) : a(com.htsmart.wristband2.a.e.b.a(exerciseTarget));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setFunctionConfig(FunctionConfig functionConfig) {
        return !isConnected() ? Completable.error(this.U2) : a(com.htsmart.wristband2.a.e.b.a(functionConfig));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setGameRankingTrends(@NonNull List<GameRankingTrend> list) {
        if (isConnected()) {
            if (list.size() > 60) {
                return Completable.error(new IllegalArgumentException("list size must<=60"));
            }
            byte[] bArr = new byte[(list.size() * 4) + 1];
            bArr[0] = (byte) list.size();
            for (int i2 = 0; i2 < list.size(); i2++) {
                int i3 = (i2 * 4) + 1;
                GameRankingTrend gameRankingTrend = list.get(i2);
                bArr[i3] = (byte) ((gameRankingTrend.getGameType() >> 8) & 255);
                bArr[i3 + 1] = (byte) (gameRankingTrend.getGameType() & 255);
                bArr[i3 + 2] = (byte) gameRankingTrend.getRanking();
                bArr[i3 + 3] = (byte) gameRankingTrend.getTrend();
            }
            return a(new PacketData((byte) 2, (byte) -113, bArr));
        }
        return Completable.error(this.U2);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setHabitList(@Nullable List<WristbandHabit> list) {
        return !isConnected() ? Completable.error(this.U2) : a(com.htsmart.wristband2.a.e.b.c(list));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setHandWashingReminderConfig(HandWashingReminderConfig handWashingReminderConfig) {
        return !isConnected() ? Completable.error(this.U2) : a(new PacketData((byte) 2, (byte) 98, handWashingReminderConfig.getBytes()));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setHealthyConfig(HealthyConfig healthyConfig) {
        WristbandConfig wristbandConfig = this.S2;
        return (!isConnected() || wristbandConfig == null) ? Completable.error(this.U2) : a(com.htsmart.wristband2.a.e.b.a(healthyConfig, wristbandConfig.getWristbandVersion().isExtHealthyConfigInterval()));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setLanguage(byte b2) {
        return !isConnected() ? Completable.error(this.U2) : a(com.htsmart.wristband2.a.e.b.a(b2));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<Boolean> setLockGame(boolean z2, byte[] bArr, int i2, int i3) {
        if (isConnected()) {
            if (bArr == null || bArr.length != 6) {
                return Single.error(new IllegalArgumentException("password must be length 6"));
            }
            if (i2 > i3) {
                return Single.error(new IllegalArgumentException("startTime must small than endTime"));
            }
            System.arraycopy(bArr, 0, r0, 1, 6);
            byte[] bArr2 = {z2 ? (byte) 1 : (byte) 0, 0, 0, 0, 0, 0, 0, (byte) ((i2 >> 8) & 255), (byte) (i2 & 255), (byte) ((i3 >> 8) & 255), (byte) (i3 & 255)};
            PacketData packetData = new PacketData((byte) 2, (byte) -115);
            packetData.setKeyData(bArr2);
            return a(packetData, new PacketData((byte) 2, (byte) -114)).map(new v(this));
        }
        return Single.error(this.U2);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<Boolean> setLockScreen(boolean z2, byte[] bArr) {
        if (isConnected()) {
            if (bArr == null || bArr.length != 6) {
                return Single.error(new IllegalArgumentException("password must be length 6"));
            }
            byte[] bArr2 = new byte[7];
            bArr2[0] = z2 ? (byte) 1 : (byte) 0;
            System.arraycopy(bArr, 0, bArr2, 1, 6);
            PacketData packetData = new PacketData((byte) 2, (byte) 104);
            packetData.setKeyData(bArr2);
            return a(packetData, new PacketData((byte) 2, (byte) 105)).map(new p(this));
        }
        return Single.error(this.U2);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setMusicInfo(String str, String str2, long j2) {
        if (isConnected()) {
            byte[] bytes = str.getBytes();
            int min = Math.min(127, bytes.length);
            byte[] bytes2 = str2.getBytes();
            int min2 = Math.min(96, bytes2.length);
            int i2 = min + 6;
            byte[] bArr = new byte[i2 + min2];
            bArr[0] = (byte) min;
            bArr[1] = (byte) min2;
            byte[] int2Bytes = BytesUtil.int2Bytes((int) j2, true);
            bArr[2] = int2Bytes[0];
            bArr[3] = int2Bytes[1];
            bArr[4] = int2Bytes[2];
            bArr[5] = int2Bytes[3];
            System.arraycopy(bytes, 0, bArr, 6, min);
            System.arraycopy(bytes2, 0, bArr, i2, min2);
            return a(new PacketData((byte) 2, (byte) -108, bArr));
        }
        return Completable.error(this.U2);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setMusicState(int i2, long j2, float f2) {
        if (isConnected()) {
            byte[] int2Bytes = BytesUtil.int2Bytes((int) j2, true);
            int i3 = (int) (f2 * 100.0f);
            return a(new PacketData((byte) 2, (byte) -109, new byte[]{(byte) i2, int2Bytes[0], int2Bytes[1], int2Bytes[2], int2Bytes[3], (byte) ((i3 >> 8) & 255), (byte) (i3 & 255)}));
        }
        return Completable.error(this.U2);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setNotDisturbConfig(NotDisturbConfig notDisturbConfig) {
        return !isConnected() ? Completable.error(this.U2) : a(com.htsmart.wristband2.a.e.b.a(notDisturbConfig));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setNotificationConfig(NotificationConfig notificationConfig) {
        return !isConnected() ? Completable.error(this.U2) : a(com.htsmart.wristband2.a.e.b.a(notificationConfig));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setPageConfig(PageConfig pageConfig) {
        return !isConnected() ? Completable.error(this.U2) : a(com.htsmart.wristband2.a.e.b.a(pageConfig));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setPeripheralsData(PeripheralsData peripheralsData) {
        return !isConnected() ? Completable.error(this.U2) : a(new PacketData((byte) 7, (byte) 10, peripheralsData.toBytes()));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setPeripheralsResponse(PeripheralsResponse peripheralsResponse) {
        return !isConnected() ? Completable.error(this.U2) : a(new PacketData((byte) 7, (byte) 9, new byte[]{peripheralsResponse.getResponse()}));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setPhotovoltaicStations(@Nullable List<PhotovoltaicStation> list) {
        return !isConnected() ? Completable.error(this.U2) : a(com.htsmart.wristband2.a.e.b.d(list));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setPowerSaveMode(@NonNull PowerSaveMode powerSaveMode) {
        byte[] bArr;
        if (isConnected()) {
            WristbandConfig wristbandConfig = this.S2;
            if (wristbandConfig != null ? wristbandConfig.getWristbandVersion().isExtPowerSavePeriod() : false) {
                int adjustTime = AbstractConfig.adjustTime(powerSaveMode.getStart());
                int adjustTime2 = AbstractConfig.adjustTime(powerSaveMode.getEnd());
                bArr = new byte[]{powerSaveMode.isEnabled() ? (byte) 1 : (byte) 0, (byte) ((adjustTime >> 8) & 255), (byte) (adjustTime & 255), (byte) ((adjustTime2 >> 8) & 255), (byte) (adjustTime2 & 255)};
            } else {
                bArr = new byte[]{powerSaveMode.isEnabled() ? (byte) 1 : (byte) 0};
            }
            return a(new PacketData((byte) 2, (byte) -87, bArr));
        }
        return Completable.error(this.U2);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setProtectionReminderConfig(ProtectionReminderConfig protectionReminderConfig) {
        return !isConnected() ? Completable.error(this.U2) : a(new PacketData((byte) 2, (byte) 90, protectionReminderConfig.getBytes()));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setScheduleList(@Nullable List<WristbandSchedule> list) {
        if (isConnected()) {
            if (list != null) {
                for (WristbandSchedule wristbandSchedule : list) {
                    wristbandSchedule.adjustSchedule();
                }
            }
            return a(com.htsmart.wristband2.a.e.b.e(list));
        }
        return Completable.error(this.U2);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setSedentaryConfig(SedentaryConfig sedentaryConfig) {
        WristbandConfig wristbandConfig = this.S2;
        return (!isConnected() || wristbandConfig == null) ? Completable.error(this.U2) : a(com.htsmart.wristband2.a.e.b.a(sedentaryConfig, wristbandConfig.getWristbandVersion().isExtSedentaryConfigInterval()));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<Byte> setSubProjectNum(String str) {
        byte[] hexStr2Bytes = BytesUtil.hexStr2Bytes(str);
        if (hexStr2Bytes == null || hexStr2Bytes.length < 1) {
            throw new IllegalArgumentException();
        }
        return !isConnected() ? Single.error(this.U2) : a(new PacketData((byte) 1, (byte) 3, new byte[]{0, hexStr2Bytes[0]}), new PacketData((byte) 1, (byte) 4)).map(new w(this));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setTurnWristLightingConfig(TurnWristLightingConfig turnWristLightingConfig) {
        return !isConnected() ? Completable.error(this.U2) : a(com.htsmart.wristband2.a.e.b.a(turnWristLightingConfig));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setUserInfo(boolean z2, int i2, float f2, float f3) {
        return !isConnected() ? Completable.error(this.U2) : !A0(z2, i2, f2, f3) ? Completable.complete() : G0(z2, i2, f2, f3);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setVibration(boolean z2, int i2) {
        return !isConnected() ? Completable.error(this.U2) : a(new PacketData((byte) 2, (byte) -118, new byte[]{z2 ? (byte) 1 : (byte) 0, (byte) i2}));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setWarnBloodPressureConfig(WarnBloodPressureConfig warnBloodPressureConfig) {
        return !isConnected() ? Completable.error(this.U2) : a(com.htsmart.wristband2.a.e.b.a(warnBloodPressureConfig));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setWarnHeartRateConfig(WarnHeartRateConfig warnHeartRateConfig) {
        return !isConnected() ? Completable.error(this.U2) : a(com.htsmart.wristband2.a.e.b.a(warnHeartRateConfig));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    @Deprecated
    public Completable setWeather(int i2, int i3, int i4, int i5, String str) {
        if (isConnected()) {
            WeatherToday weatherToday = new WeatherToday();
            weatherToday.setLowTemperature(i3);
            weatherToday.setHighTemperature(i4);
            weatherToday.setWeatherCode(i5);
            weatherToday.setCurrentTemperature(i2);
            return setWeather(str, System.currentTimeMillis(), weatherToday, null);
        }
        return Completable.error(this.U2);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setWeather(String str, long j2, @NonNull WeatherToday weatherToday, @Nullable List<WeatherForecast> list) {
        WristbandConfig wristbandConfig = this.S2;
        if (!isConnected() || wristbandConfig == null) {
            return Completable.error(this.U2);
        }
        PacketData a2 = com.htsmart.wristband2.a.e.b.a(str, j2, weatherToday, list, wristbandConfig.getWristbandVersion().isExtWeatherForecast());
        return Arrays.equals(this.b3, a2.getKeyData()) ? Completable.complete() : a(a2).doOnComplete(new y0(a2));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setWeatherException(byte b2) {
        return !isConnected() ? Completable.error(this.U2) : a(new PacketData((byte) 2, (byte) 103, new byte[]{b2}));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable setWomenHealthyConfig(WomenHealthyConfig womenHealthyConfig) {
        return !isConnected() ? Completable.error(this.U2) : a(com.htsmart.wristband2.a.e.b.a(womenHealthyConfig));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable settingQrCode(int i2, String str) {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        int i3 = i2 == 128 ? 347 : 274;
        if (bytes.length > i3) {
            return Completable.error(new IllegalArgumentException("Code length exceeds the limit of " + i3));
        }
        byte[] bArr = new byte[bytes.length + 3];
        bArr[0] = (byte) i2;
        bArr[1] = (byte) ((bytes.length >> 8) & 255);
        bArr[2] = (byte) (bytes.length & 255);
        System.arraycopy(bytes, 0, bArr, 3, bytes.length);
        return a(new PacketData((byte) 2, (byte) -123, bArr));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<Integer> specialGgSetUserData(@NonNull String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("title");
            String string2 = jSONObject.getString("msg");
            JSONArray jSONArray = jSONObject.getJSONArray("cusQrds");
            ArrayList arrayList = new ArrayList(jSONArray.length());
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                String string3 = jSONObject2.getString("tagUrl");
                String string4 = jSONObject2.getString("tagTitle");
                if (!TextUtils.isEmpty(string3) || !TextUtils.isEmpty(string4)) {
                    arrayList.add(new t.a(string3, string4));
                }
            }
            return p().a(new com.htsmart.wristband2.a.a.u(this, new com.htsmart.wristband2.a.a.t(string, string2, arrayList))).firstOrError();
        } catch (JSONException e2) {
            return Single.error(e2);
        }
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<Integer> specialGgSetUserInfo(@NonNull String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString(AppMeasurementSdk.ConditionalUserProperty.NAME);
            String string2 = jSONObject.getString("eorgName");
            byte[] bArr = null;
            byte[] bytes = TextUtils.isEmpty(string) ? null : string.getBytes(StandardCharsets.UTF_8);
            if (!TextUtils.isEmpty(string2)) {
                bArr = string2.getBytes(StandardCharsets.UTF_8);
            }
            int length = bytes == null ? 0 : bytes.length;
            int length2 = bArr == null ? 0 : bArr.length;
            int i2 = length + 2;
            byte[] bArr2 = new byte[i2 + length2];
            bArr2[0] = (byte) length;
            bArr2[1] = (byte) length2;
            if (length > 0) {
                System.arraycopy(bytes, 0, bArr2, 2, length);
            }
            if (length2 > 0) {
                System.arraycopy(bArr, 0, bArr2, i2, length2);
            }
            return a(new PacketData((byte) 8, (byte) 1, bArr2), new PacketData((byte) 8, (byte) 2)).map(new Function() { // from class: com.htsmart.wristband2.a.e.j
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    Integer d12;
                    d12 = d.d1((PacketData) obj);
                    return d12;
                }
            });
        } catch (JSONException e2) {
            return Single.error(e2);
        }
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable startSportRealTime(int i2, int i3) {
        if (isConnected()) {
            if (i3 == 4 || i3 == 8 || i3 == 16 || i3 == 20) {
                return a(n0(i2, i3, 1, 0));
            }
            throw new IllegalArgumentException("sportType error");
        }
        return Completable.error(this.U2);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable stopFindPhone() {
        return !isConnected() ? Completable.error(this.U2) : a(new PacketData((byte) 2, (byte) -104));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable stopSportRealTime(int i2) {
        return !isConnected() ? Completable.error(this.U2) : a(n0(i2, 0, 0, 0));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Observable<SyncDataRaw> syncData() {
        if (!d()) {
            this.d3.onNext(-1);
            return Observable.error(this.U2);
        } else if (this.c3) {
            return Observable.error(new SyncBusyException());
        } else {
            WristbandConfig wristbandConfig = this.S2;
            if (wristbandConfig == null) {
                this.d3.onNext(Integer.valueOf((int) WristbandManager.SYNC_STATE_FAILED_UNKNOWN));
                return Observable.error(new NullPointerException("WristbandConfig is null"));
            }
            this.c3 = true;
            this.e3 = 0;
            this.f3.set(0);
            this.d3.onNext(0);
            return m1().andThen(e0()).flatMapObservable(new h1(wristbandConfig.getWristbandVersion())).concatMapSingle(new f1(wristbandConfig)).doOnComplete(new d1()).doOnDispose(new b1()).doOnError(new w0());
        }
    }

    public final Single<Boolean> t0(boolean z2, int i2, int i3) {
        return a(new PacketData((byte) 5, com.htsmart.wristband2.a.a.a.W0), new PacketData((byte) 5, (byte) 73)).map(new p1(this)).flatMap(new n1(i2, z2, i3));
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Completable turnOffWristband() {
        return !isConnected() ? Completable.error(this.U2) : a(com.htsmart.wristband2.a.e.b.a());
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<Boolean> userUnBind() {
        return userUnBind(false);
    }

    @Override // com.htsmart.wristband2.WristbandManager
    public Single<Boolean> userUnBind(boolean z2) {
        Single map = a(com.htsmart.wristband2.a.e.b.y(), com.htsmart.wristband2.a.e.b.Y()).map(new o1(this));
        if (z2) {
            map = map.onErrorReturnItem(Boolean.TRUE);
        }
        return map.flatMap(new q1(z2));
    }

    public final void w0(WristbandConfig wristbandConfig) {
        if (wristbandConfig == null || wristbandConfig.getWristbandVersion() == null || TextUtils.isEmpty(wristbandConfig.getWristbandVersion().getRawVersion())) {
            WristbandLog.i("cacheLastConnectDevice error:null or empty raw version info", new Object[0]);
            return;
        }
        RxBleDevice rxBleDevice = getRxBleDevice();
        if (rxBleDevice == null || !d()) {
            WristbandLog.i("cacheLastConnectDevice error:no connected device", new Object[0]);
        } else {
            com.htsmart.wristband2.utils.a.a(new com.htsmart.wristband2.utils.a(rxBleDevice.getName(), rxBleDevice.getMacAddress(), wristbandConfig.getWristbandVersion().getRawVersion()));
        }
    }

    @Deprecated
    public Single<BloodPressureConfig> x() {
        return !isConnected() ? Single.error(this.U2) : a(com.htsmart.wristband2.a.e.b.h(), com.htsmart.wristband2.a.e.b.H()).map(new v0(this));
    }

    public final void x0(@NonNull String str, @NonNull String str2, boolean z2) {
        boolean equals = TextUtils.equals(str, this.L);
        boolean equals2 = TextUtils.equals(str2, this.J);
        boolean z3 = z2 == this.K;
        if (!equals) {
            this.U2 = new BleDisconnectedException(str);
        }
        if (!equals || !equals2 || !z3) {
            WristbandLog.i("checkConnect doClose", new Object[0]);
            close();
        }
        this.J = str2;
        this.K = z2;
        this.L = str;
        this.M = true;
    }

    @Deprecated
    public Single<DrinkWaterConfig> y() {
        return !isConnected() ? Single.error(this.U2) : a(com.htsmart.wristband2.a.e.b.k(), com.htsmart.wristband2.a.e.b.K()).map(new p0(this));
    }
}
