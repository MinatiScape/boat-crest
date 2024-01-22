package com.coveiot.socialdistance;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.content.Context;
import android.os.ParcelUuid;
import android.util.Log;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0011\b\u0002\u0012\u0006\u0010\u0011\u001a\u00020\f¢\u0006\u0004\b\u0012\u0010\u0013J\u001d\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\t\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\r\u0010\u000b\u001a\u00020\u0006¢\u0006\u0004\b\u000b\u0010\nR\u0019\u0010\u0011\u001a\u00020\f8\u0006@\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0015"}, d2 = {"Lcom/coveiot/socialdistance/DeviceAdvertiser;", "", "", AppMeasurementSdk.ConditionalUserProperty.NAME, "Landroid/os/ParcelUuid;", "serviceUuid", "", "initialize", "(Ljava/lang/String;Landroid/os/ParcelUuid;)V", "startAdvertising", "()V", "stopAdvertising", "Landroid/content/Context;", "g", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Companion", "socialdistance_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes9.dex */
public final class DeviceAdvertiser {
    public static final Companion Companion = new Companion(null);

    /* renamed from: a  reason: collision with root package name */
    public BluetoothAdapter f7591a;
    public BluetoothLeAdvertiser b;
    public Context c;
    public AdvertiseData d;
    public AdvertiseSettings e;
    public final DeviceAdvertiser$mAdvertiseCallback$1 f;
    @NotNull
    public final Context g;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/coveiot/socialdistance/DeviceAdvertiser$Companion;", "Lcom/coveiot/socialdistance/SingletonHolder;", "Lcom/coveiot/socialdistance/DeviceAdvertiser;", "Landroid/content/Context;", "<init>", "()V", "socialdistance_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes9.dex */
    public static final class Companion extends SingletonHolder<DeviceAdvertiser, Context> {

        /* loaded from: classes9.dex */
        public static final class a extends FunctionReference implements Function1<Context, DeviceAdvertiser> {

            /* renamed from: a  reason: collision with root package name */
            public static final a f7592a = new a();

            public a() {
                super(1);
            }

            @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
            public final String getName() {
                return "<init>";
            }

            @Override // kotlin.jvm.internal.CallableReference
            public final KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinClass(DeviceAdvertiser.class);
            }

            @Override // kotlin.jvm.internal.CallableReference
            public final String getSignature() {
                return "<init>(Landroid/content/Context;)V";
            }

            @Override // kotlin.jvm.functions.Function1
            public DeviceAdvertiser invoke(Context context) {
                Context p1 = context;
                Intrinsics.checkParameterIsNotNull(p1, "p1");
                return new DeviceAdvertiser(p1, null);
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public Companion() {
            super(a.f7592a);
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.coveiot.socialdistance.DeviceAdvertiser$mAdvertiseCallback$1] */
    public DeviceAdvertiser(Context context) {
        this.g = context;
        this.f = new AdvertiseCallback() { // from class: com.coveiot.socialdistance.DeviceAdvertiser$mAdvertiseCallback$1
            @Override // android.bluetooth.le.AdvertiseCallback
            public void onStartFailure(int i) {
                Log.i("MainActivity", "LE Advertise Failed: " + i);
            }

            @Override // android.bluetooth.le.AdvertiseCallback
            public void onStartSuccess(@NotNull AdvertiseSettings settingsInEffect) {
                Intrinsics.checkParameterIsNotNull(settingsInEffect, "settingsInEffect");
                Log.i("MainActivity", "LE Advertise Started.");
            }
        };
        this.c = context.getApplicationContext();
    }

    @NotNull
    public final Context getContext() {
        return this.g;
    }

    public final void initialize(@NotNull String name, @NotNull ParcelUuid serviceUuid) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(serviceUuid, "serviceUuid");
        Context context = this.c;
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Object systemService = context.getSystemService("bluetooth");
        if (systemService != null) {
            BluetoothAdapter adapter = ((BluetoothManager) systemService).getAdapter();
            Intrinsics.checkExpressionValueIsNotNull(adapter, "manager.adapter");
            this.f7591a = adapter;
            if (adapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBluetoothAdapter");
            }
            this.b = adapter.getBluetoothLeAdvertiser();
            BluetoothAdapter bluetoothAdapter = this.f7591a;
            if (bluetoothAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBluetoothAdapter");
            }
            bluetoothAdapter.setName(name);
            this.e = new AdvertiseSettings.Builder().setAdvertiseMode(1).setConnectable(false).setTxPowerLevel(2).build();
            this.d = new AdvertiseData.Builder().setIncludeDeviceName(true).addServiceUuid(serviceUuid).build();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.bluetooth.BluetoothManager");
    }

    public final void startAdvertising() {
        BluetoothLeAdvertiser bluetoothLeAdvertiser = this.b;
        if (bluetoothLeAdvertiser == null) {
            return;
        }
        bluetoothLeAdvertiser.startAdvertising(this.e, this.d, this.f);
    }

    public final void stopAdvertising() {
        BluetoothLeAdvertiser bluetoothLeAdvertiser = this.b;
        if (bluetoothLeAdvertiser == null) {
            Intrinsics.throwNpe();
        }
        bluetoothLeAdvertiser.stopAdvertising(this.f);
    }

    public /* synthetic */ DeviceAdvertiser(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }
}
