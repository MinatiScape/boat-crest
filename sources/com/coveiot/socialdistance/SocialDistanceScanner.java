package com.coveiot.socialdistance;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.os.Handler;
import android.os.ParcelUuid;
import com.coveiot.utils.CoveEventBusManager;
import com.google.android.material.color.c;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 T2\u00020\u0001:\u0001TB\u0011\b\u0002\u0012\u0006\u0010Q\u001a\u00020L¢\u0006\u0004\bR\u0010SJ'\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\b\u0010\tJ\r\u0010\n\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bJ\r\u0010\f\u001a\u00020\u0007¢\u0006\u0004\b\f\u0010\u000bR$\u0010\u0014\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R$\u0010\u001c\u001a\u0004\u0018\u00010\u00158\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR$\u0010 \u001a\u0004\u0018\u00010\u001d8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R$\u0010+\u001a\u0004\u0018\u00010$8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R$\u00103\u001a\u0004\u0018\u00010,8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b-\u0010.\u001a\u0004\b/\u00100\"\u0004\b1\u00102R)\u0010:\u001a\u0012\u0012\u0004\u0012\u00020,04j\b\u0012\u0004\u0012\u00020,`58\u0006@\u0006¢\u0006\f\n\u0004\b6\u00107\u001a\u0004\b8\u00109R\"\u0010\u0004\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bA\u0010<\u001a\u0004\bB\u0010>\"\u0004\bC\u0010@R\"\u0010K\u001a\u00020D8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bE\u0010F\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\u0019\u0010Q\u001a\u00020L8\u0006@\u0006¢\u0006\f\n\u0004\bM\u0010N\u001a\u0004\bO\u0010P¨\u0006U"}, d2 = {"Lcom/coveiot/socialdistance/SocialDistanceScanner;", "", "", "scanInterval", "scanDuration", "Landroid/os/ParcelUuid;", "serviceUuid", "", "initialize", "(JJLandroid/os/ParcelUuid;)V", "startScan", "()V", "stopScan", "Landroid/bluetooth/le/BluetoothLeScanner;", c.f10260a, "Landroid/bluetooth/le/BluetoothLeScanner;", "getBls", "()Landroid/bluetooth/le/BluetoothLeScanner;", "setBls", "(Landroid/bluetooth/le/BluetoothLeScanner;)V", "bls", "Landroid/os/Handler;", "d", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "setHandler", "(Landroid/os/Handler;)V", "handler", "", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "Ljava/lang/Boolean;", "isScan", "()Ljava/lang/Boolean;", "setScan", "(Ljava/lang/Boolean;)V", "Landroid/bluetooth/le/ScanSettings;", "f", "Landroid/bluetooth/le/ScanSettings;", "getScanSettings", "()Landroid/bluetooth/le/ScanSettings;", "setScanSettings", "(Landroid/bluetooth/le/ScanSettings;)V", "scanSettings", "Landroid/bluetooth/le/ScanFilter;", "g", "Landroid/bluetooth/le/ScanFilter;", "getScanFilter", "()Landroid/bluetooth/le/ScanFilter;", "setScanFilter", "(Landroid/bluetooth/le/ScanFilter;)V", "scanFilter", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "i", "Ljava/util/ArrayList;", "getFilters", "()Ljava/util/ArrayList;", "filters", "j", "J", "getScanDuration", "()J", "setScanDuration", "(J)V", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "getScanInterval", "setScanInterval", "Landroid/bluetooth/le/ScanCallback;", "l", "Landroid/bluetooth/le/ScanCallback;", "getCallBack", "()Landroid/bluetooth/le/ScanCallback;", "setCallBack", "(Landroid/bluetooth/le/ScanCallback;)V", "callBack", "Landroid/content/Context;", "m", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Companion", "socialdistance_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes9.dex */
public final class SocialDistanceScanner {
    public static final Companion Companion = new Companion(null);

    /* renamed from: a  reason: collision with root package name */
    public BluetoothAdapter f7595a;
    public final ParcelUuid b;
    @Nullable
    public BluetoothLeScanner c;
    @Nullable
    public Handler d;
    @Nullable
    public Boolean e;
    @Nullable
    public ScanSettings f;
    @Nullable
    public ScanFilter g;
    public Context h;
    @NotNull
    public final ArrayList<ScanFilter> i;
    public long j;
    public long k;
    @NotNull
    public ScanCallback l;
    @NotNull
    public final Context m;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/coveiot/socialdistance/SocialDistanceScanner$Companion;", "Lcom/coveiot/socialdistance/SingletonHolder;", "Lcom/coveiot/socialdistance/SocialDistanceScanner;", "Landroid/content/Context;", "<init>", "()V", "socialdistance_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes9.dex */
    public static final class Companion extends SingletonHolder<SocialDistanceScanner, Context> {

        /* loaded from: classes9.dex */
        public static final class a extends FunctionReference implements Function1<Context, SocialDistanceScanner> {

            /* renamed from: a  reason: collision with root package name */
            public static final a f7596a = new a();

            public a() {
                super(1);
            }

            @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
            public final String getName() {
                return "<init>";
            }

            @Override // kotlin.jvm.internal.CallableReference
            public final KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinClass(SocialDistanceScanner.class);
            }

            @Override // kotlin.jvm.internal.CallableReference
            public final String getSignature() {
                return "<init>(Landroid/content/Context;)V";
            }

            @Override // kotlin.jvm.functions.Function1
            public SocialDistanceScanner invoke(Context context) {
                Context p1 = context;
                Intrinsics.checkParameterIsNotNull(p1, "p1");
                return new SocialDistanceScanner(p1, null);
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public Companion() {
            super(a.f7596a);
        }
    }

    /* loaded from: classes9.dex */
    public static final class a implements Runnable {

        /* renamed from: com.coveiot.socialdistance.SocialDistanceScanner$a$a  reason: collision with other inner class name */
        /* loaded from: classes9.dex */
        public static final class RunnableC0334a implements Runnable {
            public RunnableC0334a() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                SocialDistanceScanner.this.startScan();
            }
        }

        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            SocialDistanceScanner.this.stopScan();
            Handler handler = SocialDistanceScanner.this.getHandler();
            if (handler != null) {
                handler.postDelayed(new RunnableC0334a(), SocialDistanceScanner.this.getScanInterval());
            }
        }
    }

    public SocialDistanceScanner(Context context) {
        this.m = context;
        this.b = ParcelUuid.fromString("00000001-0000-1000-8000-00805f9b34fb");
        this.e = Boolean.FALSE;
        this.i = new ArrayList<>();
        this.l = new ScanCallback() { // from class: com.coveiot.socialdistance.SocialDistanceScanner$callBack$1
            @Override // android.bluetooth.le.ScanCallback
            public void onBatchScanResults(@NotNull List<ScanResult> results) {
                Intrinsics.checkParameterIsNotNull(results, "results");
                super.onBatchScanResults(results);
            }

            @Override // android.bluetooth.le.ScanCallback
            public void onScanFailed(int i) {
                super.onScanFailed(i);
            }

            @Override // android.bluetooth.le.ScanCallback
            public void onScanResult(int i, @NotNull ScanResult result) {
                Intrinsics.checkParameterIsNotNull(result, "result");
                result.getRssi();
                result.getDevice();
                CoveEventBusManager coveEventBusManager = CoveEventBusManager.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(coveEventBusManager, "CoveEventBusManager.getInstance()");
                coveEventBusManager.getEventBus().post(new ScanResultsEvent(result));
                super.onScanResult(i, result);
            }
        };
        this.h = context.getApplicationContext();
    }

    @Nullable
    public final BluetoothLeScanner getBls() {
        return this.c;
    }

    @NotNull
    public final ScanCallback getCallBack() {
        return this.l;
    }

    @NotNull
    public final Context getContext() {
        return this.m;
    }

    @NotNull
    public final ArrayList<ScanFilter> getFilters() {
        return this.i;
    }

    @Nullable
    public final Handler getHandler() {
        return this.d;
    }

    public final long getScanDuration() {
        return this.j;
    }

    @Nullable
    public final ScanFilter getScanFilter() {
        return this.g;
    }

    public final long getScanInterval() {
        return this.k;
    }

    @Nullable
    public final ScanSettings getScanSettings() {
        return this.f;
    }

    public final void initialize(long j, long j2, @Nullable ParcelUuid parcelUuid) {
        Context context = this.h;
        Object systemService = context != null ? context.getSystemService("bluetooth") : null;
        if (systemService != null) {
            this.f7595a = ((BluetoothManager) systemService).getAdapter();
            this.d = new Handler();
            BluetoothAdapter bluetoothAdapter = this.f7595a;
            this.c = bluetoothAdapter != null ? bluetoothAdapter.getBluetoothLeScanner() : null;
            this.f = new ScanSettings.Builder().setScanMode(2).build();
            ScanFilter.Builder builder = new ScanFilter.Builder();
            if (parcelUuid == null) {
                parcelUuid = this.b;
            }
            ScanFilter build = builder.setServiceUuid(parcelUuid).build();
            this.g = build;
            this.j = j2;
            this.k = j;
            ArrayList<ScanFilter> arrayList = this.i;
            if (build == null) {
                Intrinsics.throwNpe();
            }
            arrayList.add(build);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.bluetooth.BluetoothManager");
    }

    @Nullable
    public final Boolean isScan() {
        return this.e;
    }

    public final void setBls(@Nullable BluetoothLeScanner bluetoothLeScanner) {
        this.c = bluetoothLeScanner;
    }

    public final void setCallBack(@NotNull ScanCallback scanCallback) {
        Intrinsics.checkParameterIsNotNull(scanCallback, "<set-?>");
        this.l = scanCallback;
    }

    public final void setHandler(@Nullable Handler handler) {
        this.d = handler;
    }

    public final void setScan(@Nullable Boolean bool) {
        this.e = bool;
    }

    public final void setScanDuration(long j) {
        this.j = j;
    }

    public final void setScanFilter(@Nullable ScanFilter scanFilter) {
        this.g = scanFilter;
    }

    public final void setScanInterval(long j) {
        this.k = j;
    }

    public final void setScanSettings(@Nullable ScanSettings scanSettings) {
        this.f = scanSettings;
    }

    public final void startScan() {
        if (Intrinsics.areEqual(this.e, Boolean.FALSE)) {
            BluetoothLeScanner bluetoothLeScanner = this.c;
            if (bluetoothLeScanner != null) {
                bluetoothLeScanner.startScan(this.i, this.f, this.l);
            }
            Handler handler = this.d;
            if (handler != null) {
                handler.postDelayed(new a(), this.j);
            }
            this.e = Boolean.TRUE;
        }
    }

    public final void stopScan() {
        if (Intrinsics.areEqual(this.e, Boolean.TRUE)) {
            Handler handler = this.d;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            }
            BluetoothLeScanner bluetoothLeScanner = this.c;
            if (bluetoothLeScanner != null) {
                bluetoothLeScanner.stopScan(this.l);
            }
            this.e = Boolean.FALSE;
        }
    }

    public /* synthetic */ SocialDistanceScanner(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }
}
