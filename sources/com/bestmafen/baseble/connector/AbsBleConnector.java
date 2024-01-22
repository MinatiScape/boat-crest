package com.bestmafen.baseble.connector;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.bestmafen.baseble.connector.AbsBleConnector;
import com.bestmafen.baseble.connector.AbsBleConnector$mBluetoothGattCallback$2;
import com.bestmafen.baseble.connector.AbsBleConnector$mReceiver$2;
import com.bestmafen.baseble.data.ByteArrayExtKt;
import com.bestmafen.baseble.messenger.AbsBleMessenger;
import com.bestmafen.baseble.messenger.NotifyMessage;
import com.bestmafen.baseble.messenger.RequestMtuMessage;
import com.bestmafen.baseble.parser.IBleParser;
import com.bestmafen.baseble.scanner.AbsBleScanner;
import com.bestmafen.baseble.scanner.AddressFilter;
import com.bestmafen.baseble.scanner.BleDevice;
import com.bestmafen.baseble.scanner.BleScanCallback;
import com.bestmafen.baseble.scanner.ScannerFactory;
import com.bestmafen.baseble.util.BleLog;
import java.util.UUID;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public abstract class AbsBleConnector {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final String LOG_HEADER = "AbsBleConnector";
    private boolean disableStreamLog;
    private boolean isConnecting;
    @Nullable
    private BleGattCallback mBleGattCallback;
    @Nullable
    private BluetoothGatt mBluetoothGatt;
    public Context mContext;
    private boolean mNotified;
    private int mRetry;
    @Nullable
    private AbsBleScanner mScanner;
    @Nullable
    private String mTargetAddress;
    private int mTransport;
    @NotNull
    private final Lazy mBluetoothGattCallback$delegate = LazyKt__LazyJVMKt.lazy(new Function0<AbsBleConnector$mBluetoothGattCallback$2.AnonymousClass1>() { // from class: com.bestmafen.baseble.connector.AbsBleConnector$mBluetoothGattCallback$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Type inference failed for: r0v0, types: [com.bestmafen.baseble.connector.AbsBleConnector$mBluetoothGattCallback$2$1] */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final AnonymousClass1 invoke() {
            final AbsBleConnector absBleConnector = AbsBleConnector.this;
            return new BluetoothGattCallback() { // from class: com.bestmafen.baseble.connector.AbsBleConnector$mBluetoothGattCallback$2.1
                @Override // android.bluetooth.BluetoothGattCallback
                public void onCharacteristicChanged(@NotNull BluetoothGatt gatt, @NotNull BluetoothGattCharacteristic characteristic) {
                    BleGattCallback mBleGattCallback;
                    Intrinsics.checkNotNullParameter(gatt, "gatt");
                    Intrinsics.checkNotNullParameter(characteristic, "characteristic");
                    BleLog bleLog = BleLog.INSTANCE;
                    bleLog.i("AbsBleConnector onCharacteristicChanged -> " + characteristic.getUuid() + ", " + ByteArrayExtKt.getMHexString(characteristic.getValue()));
                    IBleParser mBleParser = AbsBleConnector.this.getMBleParser();
                    byte[] value = characteristic.getValue();
                    Intrinsics.checkNotNullExpressionValue(value, "characteristic.value");
                    byte[] onReceive = mBleParser.onReceive(value);
                    if (onReceive == null || (mBleGattCallback = AbsBleConnector.this.getMBleGattCallback()) == null) {
                        return;
                    }
                    mBleGattCallback.onCharacteristicChanged(onReceive);
                }

                @Override // android.bluetooth.BluetoothGattCallback
                public void onCharacteristicRead(@NotNull BluetoothGatt gatt, @NotNull BluetoothGattCharacteristic characteristic, int i) {
                    Intrinsics.checkNotNullParameter(gatt, "gatt");
                    Intrinsics.checkNotNullParameter(characteristic, "characteristic");
                    byte[] bytes = characteristic.getValue();
                    String text = characteristic.getStringValue(0);
                    BleLog bleLog = BleLog.INSTANCE;
                    bleLog.i("AbsBleConnector onCharacteristicRead -> " + characteristic.getUuid() + ", " + ByteArrayExtKt.getMHexString(bytes) + ", " + text);
                    AbsBleConnector.this.getMBleMessenger().dequeueMessage();
                    BleGattCallback mBleGattCallback = AbsBleConnector.this.getMBleGattCallback();
                    if (mBleGattCallback != null) {
                        String valueOf = String.valueOf(characteristic.getUuid());
                        Intrinsics.checkNotNullExpressionValue(bytes, "bytes");
                        Intrinsics.checkNotNullExpressionValue(text, "text");
                        mBleGattCallback.onCharacteristicRead(valueOf, bytes, text);
                    }
                }

                @Override // android.bluetooth.BluetoothGattCallback
                public void onCharacteristicWrite(@NotNull BluetoothGatt gatt, @NotNull BluetoothGattCharacteristic characteristic, int i) {
                    Intrinsics.checkNotNullParameter(gatt, "gatt");
                    Intrinsics.checkNotNullParameter(characteristic, "characteristic");
                    if (!AbsBleConnector.this.getDisableStreamLog()) {
                        BleLog bleLog = BleLog.INSTANCE;
                        bleLog.i("AbsBleConnector onCharacteristicWrite -> " + AbsBleConnector.Companion.getGattStatus(i) + ", " + characteristic.getUuid() + ", " + ByteArrayExtKt.getMHexString(characteristic.getValue()));
                    }
                    AbsBleConnector.this.getMBleMessenger().dequeueWritePacket();
                    BleGattCallback mBleGattCallback = AbsBleConnector.this.getMBleGattCallback();
                    if (mBleGattCallback != null) {
                        String valueOf = String.valueOf(characteristic.getUuid());
                        byte[] value = characteristic.getValue();
                        Intrinsics.checkNotNullExpressionValue(value, "characteristic.value");
                        mBleGattCallback.onCharacteristicWrite(valueOf, value);
                    }
                }

                @Override // android.bluetooth.BluetoothGattCallback
                public void onConnectionStateChange(@NotNull BluetoothGatt gatt, int i, int i2) {
                    boolean z;
                    BleGattCallback mBleGattCallback;
                    Intrinsics.checkNotNullParameter(gatt, "gatt");
                    boolean z2 = (i == 0) && (i2 == 2);
                    BleLog bleLog = BleLog.INSTANCE;
                    StringBuilder sb = new StringBuilder();
                    sb.append("AbsBleConnector onConnectionStateChange -> ");
                    sb.append(z2);
                    sb.append(", status=");
                    AbsBleConnector.Companion companion = AbsBleConnector.Companion;
                    sb.append(companion.getGattStatus(i));
                    sb.append(", newState=");
                    sb.append(companion.getBluetoothProfileState(i2));
                    bleLog.i(sb.toString());
                    AbsBleConnector.this.getMBleMessenger().reset();
                    AbsBleConnector.this.getMBleParser().reset();
                    if (!z2) {
                        z = AbsBleConnector.this.mNotified;
                        if (z && (mBleGattCallback = AbsBleConnector.this.getMBleGattCallback()) != null) {
                            mBleGattCallback.onConnectionStateChange(false);
                        }
                        AbsBleConnector.this.connect(true);
                        return;
                    }
                    gatt.discoverServices();
                    AbsBleConnector.this.connect(false);
                    if (AbsBleConnector.this.getMBluetoothGatt() == null) {
                        AbsBleConnector.this.setMBluetoothGatt(gatt);
                    }
                    BleGattCallback mBleGattCallback2 = AbsBleConnector.this.getMBleGattCallback();
                    if (mBleGattCallback2 != null) {
                        mBleGattCallback2.onConnectionStateChange(true);
                    }
                }

                @Override // android.bluetooth.BluetoothGattCallback
                public void onDescriptorWrite(@NotNull BluetoothGatt gatt, @NotNull BluetoothGattDescriptor descriptor, int i) {
                    Intrinsics.checkNotNullParameter(gatt, "gatt");
                    Intrinsics.checkNotNullParameter(descriptor, "descriptor");
                    BleLog.INSTANCE.i("AbsBleConnector -> onDescriptorWrite");
                    AbsBleConnector.this.mNotified = true;
                    AbsBleConnector.this.getMBleMessenger().dequeueMessage();
                }

                @Override // android.bluetooth.BluetoothGattCallback
                public void onMtuChanged(@NotNull BluetoothGatt gatt, int i, int i2) {
                    boolean z;
                    Intrinsics.checkNotNullParameter(gatt, "gatt");
                    BleLog bleLog = BleLog.INSTANCE;
                    bleLog.i("AbsBleConnector onMtuChanged -> mtu=" + i);
                    z = AbsBleConnector.this.mNotified;
                    if (z) {
                        AbsBleConnector.this.getMBleMessenger().setMPacketSize(i - 3);
                        AbsBleConnector.this.getMBleMessenger().dequeueMessage();
                        BleGattCallback mBleGattCallback = AbsBleConnector.this.getMBleGattCallback();
                        if (mBleGattCallback != null) {
                            mBleGattCallback.onMtuChanged();
                        }
                    }
                }

                @Override // android.bluetooth.BluetoothGattCallback
                public void onServicesDiscovered(@NotNull BluetoothGatt gatt, int i) {
                    Intrinsics.checkNotNullParameter(gatt, "gatt");
                    BleLog.INSTANCE.i("AbsBleConnector -> onServicesDiscovered");
                    AbsBleConnector.this.getMBleMessenger().enqueueMessage(new NotifyMessage(AbsBleConnector.this.getMService(), AbsBleConnector.this.getMNotify(), false, 4, null));
                    AbsBleConnector.this.getMBleMessenger().enqueueMessage(new RequestMtuMessage(0, 1, null));
                }
            };
        }
    });
    @NotNull
    private final Lazy mReceiver$delegate = LazyKt__LazyJVMKt.lazy(new Function0<AbsBleConnector$mReceiver$2.AnonymousClass1>() { // from class: com.bestmafen.baseble.connector.AbsBleConnector$mReceiver$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Type inference failed for: r0v0, types: [com.bestmafen.baseble.connector.AbsBleConnector$mReceiver$2$1] */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final AnonymousClass1 invoke() {
            final AbsBleConnector absBleConnector = AbsBleConnector.this;
            return new BroadcastReceiver() { // from class: com.bestmafen.baseble.connector.AbsBleConnector$mReceiver$2.1
                @Override // android.content.BroadcastReceiver
                public void onReceive(@NotNull Context context, @NotNull Intent intent) {
                    boolean z;
                    Intrinsics.checkNotNullParameter(context, "context");
                    Intrinsics.checkNotNullParameter(intent, "intent");
                    if (TextUtils.equals(intent.getAction(), "android.bluetooth.adapter.action.STATE_CHANGED")) {
                        int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
                        BleLog bleLog = BleLog.INSTANCE;
                        bleLog.i("AbsBleConnector onReceive BluetoothAdapter.ACTION_STATE_CHANGED -> state=" + AbsBleConnector.Companion.getBluetoothAdapterState(intExtra));
                        if (intExtra != 10) {
                            if (intExtra != 12) {
                                return;
                            }
                            AbsBleConnector.this.connect(true);
                            return;
                        }
                        AbsBleConnector.this.getMBleMessenger().reset();
                        AbsBleConnector.this.getMBleParser().reset();
                        z = AbsBleConnector.this.mNotified;
                        if (z) {
                            AbsBleConnector.this.mNotified = false;
                            BleGattCallback mBleGattCallback = AbsBleConnector.this.getMBleGattCallback();
                            if (mBleGattCallback != null) {
                                mBleGattCallback.onConnectionStateChange(false);
                            }
                        }
                        AbsBleConnector.this.setConnecting(false);
                    }
                }
            };
        }
    });
    private final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    @NotNull
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    @NotNull
    private final AddressFilter mScanFilter = new AddressFilter("");
    private boolean mConnectDirectly = true;
    private final int mReconnectBasePeriod = 8;
    private final int mReconnectMaxPeriod = 40;
    private final int mScanMaxDuration = 12;
    @NotNull
    private final AbsBleConnector$mReconnection$1 mReconnection = new Runnable() { // from class: com.bestmafen.baseble.connector.AbsBleConnector$mReconnection$1
        @Override // java.lang.Runnable
        public void run() {
            boolean shouldReconnect;
            int i;
            int i2;
            int i3;
            boolean z;
            BluetoothAdapter bluetoothAdapter;
            String str;
            BluetoothGatt bluetoothGatt;
            boolean z2;
            AbsBleScanner absBleScanner;
            int i4;
            AbsBleConnector.this.closeConnection(false);
            shouldReconnect = AbsBleConnector.this.shouldReconnect();
            if (!shouldReconnect) {
                AbsBleConnector.this.setConnecting(false);
                return;
            }
            AbsBleConnector absBleConnector = AbsBleConnector.this;
            i = absBleConnector.mRetry;
            absBleConnector.mRetry = i + 1;
            i2 = AbsBleConnector.this.mRetry;
            if (i2 < 1) {
                AbsBleConnector.this.mRetry = 1;
            }
            i3 = AbsBleConnector.this.mRetry;
            int mReconnectBasePeriod = i3 * AbsBleConnector.this.getMReconnectBasePeriod();
            if (mReconnectBasePeriod > AbsBleConnector.this.getMReconnectMaxPeriod()) {
                AbsBleConnector.this.mRetry = 1;
                i4 = AbsBleConnector.this.mRetry;
                mReconnectBasePeriod = i4 * AbsBleConnector.this.getMReconnectBasePeriod();
            }
            z = AbsBleConnector.this.mConnectDirectly;
            if (!z) {
                BleLog.INSTANCE.d("AbsBleConnector connect scan");
                absBleScanner = AbsBleConnector.this.mScanner;
                if (absBleScanner != null) {
                    AbsBleConnector absBleConnector2 = AbsBleConnector.this;
                    int i5 = (int) (mReconnectBasePeriod * 0.75f);
                    if (i5 > absBleConnector2.getMScanMaxDuration()) {
                        i5 = absBleConnector2.getMScanMaxDuration();
                    }
                    absBleScanner.setScanDuration(i5);
                    absBleScanner.scan(true);
                }
            } else {
                BleLog.INSTANCE.d("AbsBleConnector connect directly");
                try {
                    AbsBleConnector absBleConnector3 = AbsBleConnector.this;
                    bluetoothAdapter = absBleConnector3.mBluetoothAdapter;
                    str = AbsBleConnector.this.mTargetAddress;
                    BluetoothDevice remoteDevice = bluetoothAdapter.getRemoteDevice(str);
                    Intrinsics.checkNotNullExpressionValue(remoteDevice, "mBluetoothAdapter.getRemoteDevice(mTargetAddress)");
                    bluetoothGatt = absBleConnector3.getBluetoothGatt(remoteDevice);
                    absBleConnector3.setMBluetoothGatt(bluetoothGatt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            AbsBleConnector absBleConnector4 = AbsBleConnector.this;
            z2 = absBleConnector4.mConnectDirectly;
            absBleConnector4.mConnectDirectly = true ^ z2;
            AbsBleConnector.this.getMHandler().postDelayed(this, mReconnectBasePeriod * 1000);
        }
    };

    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final String getBluetoothAdapterState(int i) {
            switch (i) {
                case 10:
                    return "STATE_OFF";
                case 11:
                    return "STATE_TURNING_ON";
                case 12:
                    return "STATE_ON";
                case 13:
                    return "STATE_TURNING_OFF";
                default:
                    return i + " unknown";
            }
        }

        @NotNull
        public final String getBluetoothProfileState(int i) {
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            return i + " unknown";
                        }
                        return "DISCONNECTING";
                    }
                    return "CONNECTED";
                }
                return "CONNECTING";
            }
            return "DISCONNECTED";
        }

        @NotNull
        public final String getGattStatus(int i) {
            if (i == 0) {
                return i + " SUCCESS";
            } else if (i == 13) {
                return i + " INVALID_ATTRIBUTE_LENGTH";
            } else if (i == 15) {
                return i + " INSUFFICIENT_ENCRYPTION";
            } else if (i == 143) {
                return i + " CONNECTION_CONGESTED";
            } else if (i == 257) {
                return i + " FAILURE";
            } else if (i == 2) {
                return i + " READ_NOT_PERMITTED";
            } else if (i == 3) {
                return i + " WRITE_NOT_PERMITTED";
            } else if (i == 5) {
                return i + " INSUFFICIENT_AUTHENTICATION";
            } else if (i == 6) {
                return i + " REQUEST_NOT_SUPPORTED";
            } else if (i != 7) {
                return i + " unknown";
            } else {
                return i + " INVALID_OFFSET";
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BluetoothGatt getBluetoothGatt(BluetoothDevice bluetoothDevice) {
        int type = bluetoothDevice.getType();
        if (this.mTransport == 0 && type == 3) {
            this.mTransport = 2;
        }
        BleLog bleLog = BleLog.INSTANCE;
        bleLog.d("AbsBleConnector BluetoothGatt -> type = " + bluetoothDevice.getType() + " , transport = " + this.mTransport);
        if (Build.VERSION.SDK_INT >= 23) {
            BluetoothGatt connectGatt = bluetoothDevice.connectGatt(getMContext(), false, getMBluetoothGattCallback(), this.mTransport);
            Intrinsics.checkNotNullExpressionValue(connectGatt, "{\n            bluetoothD…t\n            )\n        }");
            return connectGatt;
        }
        BluetoothGatt connectGatt2 = bluetoothDevice.connectGatt(getMContext(), false, getMBluetoothGattCallback());
        Intrinsics.checkNotNullExpressionValue(connectGatt2, "{\n            bluetoothD…thGattCallback)\n        }");
        return connectGatt2;
    }

    private final AbsBleConnector$mBluetoothGattCallback$2.AnonymousClass1 getMBluetoothGattCallback() {
        return (AbsBleConnector$mBluetoothGattCallback$2.AnonymousClass1) this.mBluetoothGattCallback$delegate.getValue();
    }

    private final AbsBleConnector$mReceiver$2.AnonymousClass1 getMReceiver() {
        return (AbsBleConnector$mReceiver$2.AnonymousClass1) this.mReceiver$delegate.getValue();
    }

    public static /* synthetic */ AbsBleConnector setBleDevice$default(AbsBleConnector absBleConnector, BleDevice bleDevice, int i, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 2) != 0) {
                i = 0;
            }
            return absBleConnector.setBleDevice(bleDevice, i);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setBleDevice");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean shouldReconnect() {
        boolean z = this.mBluetoothAdapter.isEnabled() && !TextUtils.isEmpty(this.mTargetAddress);
        BleLog bleLog = BleLog.INSTANCE;
        bleLog.d("AbsBleConnector shouldReconnect -> " + z + ", BluetoothAdapter isEnabled: " + this.mBluetoothAdapter.isEnabled() + ", mTargetAddress: " + this.mTargetAddress);
        return z;
    }

    public final synchronized void closeConnection(boolean z) {
        BleGattCallback bleGattCallback;
        BleLog bleLog = BleLog.INSTANCE;
        bleLog.d("AbsBleConnector closeConnection -> stopReconnecting = " + z);
        getMBleMessenger().reset();
        getMBleParser().reset();
        if (this.mNotified) {
            this.mNotified = false;
            if (z && (bleGattCallback = this.mBleGattCallback) != null) {
                bleGattCallback.onConnectionStateChange(false);
            }
        }
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt != null) {
            Intrinsics.checkNotNull(bluetoothGatt);
            bluetoothGatt.close();
            this.mBluetoothGatt = null;
        }
        if (z) {
            this.mTargetAddress = "";
            connect(false);
        }
    }

    public final synchronized void connect(boolean z) {
        if (isConnecting() == z) {
            return;
        }
        BleLog bleLog = BleLog.INSTANCE;
        bleLog.d("AbsBleConnector connect -> " + z);
        setConnecting(z);
        this.mRetry = 0;
        if (z) {
            this.mHandler.post(this.mReconnection);
        } else {
            AbsBleScanner absBleScanner = this.mScanner;
            Intrinsics.checkNotNull(absBleScanner);
            absBleScanner.scan(false);
            this.mHandler.removeCallbacks(this.mReconnection);
        }
    }

    public final void exit() {
        BleLog.INSTANCE.d("AbsBleConnector -> exit");
        AbsBleScanner absBleScanner = this.mScanner;
        Intrinsics.checkNotNull(absBleScanner);
        absBleScanner.exit();
        closeConnection(true);
        getMContext().unregisterReceiver(getMReceiver());
    }

    @Nullable
    public final BluetoothGattCharacteristic getCharacteristic(@NotNull String serviceUuid, @NotNull String characteristicUuid) {
        Intrinsics.checkNotNullParameter(serviceUuid, "serviceUuid");
        Intrinsics.checkNotNullParameter(characteristicUuid, "characteristicUuid");
        if (this.mBluetoothGatt == null || TextUtils.isEmpty(serviceUuid) || TextUtils.isEmpty(characteristicUuid)) {
            return null;
        }
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        Intrinsics.checkNotNull(bluetoothGatt);
        BluetoothGattService service = bluetoothGatt.getService(UUID.fromString(serviceUuid));
        if (service == null) {
            BleLog bleLog = BleLog.INSTANCE;
            bleLog.w("AbsBleConnector getCharacteristic -> service(" + serviceUuid + ")=null");
            return null;
        }
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(UUID.fromString(characteristicUuid));
        if (characteristic == null) {
            BleLog bleLog2 = BleLog.INSTANCE;
            bleLog2.w("AbsBleConnector getCharacteristic -> service(" + serviceUuid + "), characteristic(" + characteristicUuid + ")=null");
            return null;
        }
        return characteristic;
    }

    public final boolean getDisableStreamLog() {
        return this.disableStreamLog;
    }

    @Nullable
    public final BleGattCallback getMBleGattCallback() {
        return this.mBleGattCallback;
    }

    @NotNull
    public abstract AbsBleMessenger getMBleMessenger();

    @NotNull
    public abstract IBleParser getMBleParser();

    @Nullable
    public final BluetoothGatt getMBluetoothGatt() {
        return this.mBluetoothGatt;
    }

    @NotNull
    public final Context getMContext() {
        Context context = this.mContext;
        if (context != null) {
            return context;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mContext");
        return null;
    }

    @NotNull
    public final Handler getMHandler() {
        return this.mHandler;
    }

    @NotNull
    public abstract String getMNotify();

    public final int getMReconnectBasePeriod() {
        return this.mReconnectBasePeriod;
    }

    public final int getMReconnectMaxPeriod() {
        return this.mReconnectMaxPeriod;
    }

    public final int getMScanMaxDuration() {
        return this.mScanMaxDuration;
    }

    @NotNull
    public abstract String getMService();

    public final int getMTransport() {
        return this.mTransport;
    }

    @Nullable
    public final BluetoothGattDescriptor getNotifyDescriptor(@Nullable BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic == null) {
            BleLog.INSTANCE.w("AbsBleConnector getNotifyDescriptor -> characteristic=null");
            return null;
        }
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(UUID.fromString("00002902-0000-1000-8000-00805f9b34fb"));
        if (descriptor == null) {
            BleLog.INSTANCE.w("AbsBleConnector getNotifyDescriptor -> descriptor=null");
            return null;
        }
        return descriptor;
    }

    @NotNull
    public final AbsBleConnector init(@NotNull Context context, @NotNull BleGattCallback bleGattCallback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(bleGattCallback, "bleGattCallback");
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "context.applicationContext");
        setMContext(applicationContext);
        this.mBleGattCallback = bleGattCallback;
        this.mScanner = ScannerFactory.newInstance$default(ScannerFactory.INSTANCE, null, null, 3, null).setScanFilter(this.mScanFilter).setBleScanCallback(new BleScanCallback() { // from class: com.bestmafen.baseble.connector.AbsBleConnector$init$1
            @Override // com.bestmafen.baseble.scanner.BleScanCallback
            public void onBluetoothDisabled() {
            }

            @Override // com.bestmafen.baseble.scanner.BleScanCallback
            public void onDeviceFound(@NotNull BleDevice device) {
                AbsBleScanner absBleScanner;
                BluetoothGatt bluetoothGatt;
                Intrinsics.checkNotNullParameter(device, "device");
                BleLog bleLog = BleLog.INSTANCE;
                bleLog.d("AbsBleConnector onDeviceFound -> " + device);
                absBleScanner = AbsBleConnector.this.mScanner;
                if (absBleScanner != null) {
                    absBleScanner.scan(false);
                }
                if (AbsBleConnector.this.getMBluetoothGatt() != null) {
                    return;
                }
                AbsBleConnector absBleConnector = AbsBleConnector.this;
                bluetoothGatt = absBleConnector.getBluetoothGatt(device.getMBluetoothDevice());
                absBleConnector.setMBluetoothGatt(bluetoothGatt);
            }

            @Override // com.bestmafen.baseble.scanner.BleScanCallback
            public void onScan(boolean z) {
            }
        });
        getMBleMessenger().setMAbsBleConnector(this);
        getMContext().registerReceiver(getMReceiver(), new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
        return this;
    }

    public boolean isConnecting() {
        return this.isConnecting;
    }

    @NotNull
    public final AbsBleConnector setAddress(@NotNull String address) {
        Intrinsics.checkNotNullParameter(address, "address");
        this.mTargetAddress = address;
        this.mScanFilter.setMAddress(address);
        return this;
    }

    @NotNull
    public final AbsBleConnector setBleDevice(@NotNull BleDevice bleDevice, int i) {
        Intrinsics.checkNotNullParameter(bleDevice, "bleDevice");
        this.mTransport = i;
        return setBluetoothDevice(bleDevice.getMBluetoothDevice());
    }

    @NotNull
    public final AbsBleConnector setBluetoothDevice(@NotNull BluetoothDevice bluetoothDevice) {
        Intrinsics.checkNotNullParameter(bluetoothDevice, "bluetoothDevice");
        String address = bluetoothDevice.getAddress();
        Intrinsics.checkNotNullExpressionValue(address, "bluetoothDevice.address");
        return setAddress(address);
    }

    public void setConnecting(boolean z) {
        this.isConnecting = z;
    }

    public final void setDisableStreamLog(boolean z) {
        this.disableStreamLog = z;
    }

    public final void setMBleGattCallback(@Nullable BleGattCallback bleGattCallback) {
        this.mBleGattCallback = bleGattCallback;
    }

    public final void setMBluetoothGatt(@Nullable BluetoothGatt bluetoothGatt) {
        this.mBluetoothGatt = bluetoothGatt;
    }

    public final void setMContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.mContext = context;
    }

    public final void setMTransport(int i) {
        this.mTransport = i;
    }
}
