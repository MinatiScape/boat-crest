package no.nordicsemi.android.dfu;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.UUID;
import no.nordicsemi.android.dfu.DfuCallback;
import no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException;
import no.nordicsemi.android.dfu.internal.exception.DfuException;
import no.nordicsemi.android.dfu.internal.exception.UploadAbortedException;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScannerFactory;
import org.apache.commons.codec.language.Soundex;
/* JADX INFO: Access modifiers changed from: package-private */
@SuppressLint({"MissingPermission"})
/* loaded from: classes12.dex */
public abstract class BaseDfuImpl implements DfuService {
    public static final int INDICATIONS = 2;
    private static final int MAX_PACKET_SIZE_DEFAULT = 20;
    public static final int NOTIFICATIONS = 1;
    private static final String TAG = "DfuImpl";
    public boolean mAborted;
    private int mCurrentMtu;
    public int mError;
    public int mFileType;
    public InputStream mFirmwareStream;
    public BluetoothGatt mGatt;
    public int mImageSizeInBytes;
    public int mInitPacketSizeInBytes;
    public InputStream mInitPacketStream;
    public boolean mPaused;
    public DfuProgressInfo mProgressInfo;
    public boolean mRequestCompleted;
    public boolean mResetRequestSent;
    public DfuBaseService mService;
    public static final UUID GENERIC_ATTRIBUTE_SERVICE_UUID = new UUID(26392574038016L, -9223371485494954757L);
    public static final UUID SERVICE_CHANGED_UUID = new UUID(46200963207168L, -9223371485494954757L);
    public static final UUID CLIENT_CHARACTERISTIC_CONFIG = new UUID(45088566677504L, -9223371485494954757L);
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public final Object mLock = new Object();
    public byte[] mReceivedData = null;
    public byte[] mBuffer = new byte[20];
    public boolean mConnected = true;

    /* loaded from: classes12.dex */
    public class BaseBluetoothGattCallback extends DfuCallback.DfuGattCallback {
        public BaseBluetoothGattCallback() {
        }

        private String phyToString(int i) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return "UNKNOWN (" + i + ")";
                    }
                    return "LE Coded";
                }
                return "LE 2M";
            }
            return "LE 1M";
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            if (i == 0) {
                DfuBaseService dfuBaseService = BaseDfuImpl.this.mService;
                dfuBaseService.sendLogBroadcast(5, "Read Response received from " + bluetoothGattCharacteristic.getUuid() + ", value (0x): " + parse(bluetoothGattCharacteristic));
                BaseDfuImpl.this.mReceivedData = bluetoothGattCharacteristic.getValue();
                BaseDfuImpl.this.mRequestCompleted = true;
            } else {
                BaseDfuImpl baseDfuImpl = BaseDfuImpl.this;
                baseDfuImpl.loge("Characteristic read error: " + i);
                BaseDfuImpl.this.mError = i | 16384;
            }
            BaseDfuImpl.this.notifyLock();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            if (i == 0) {
                if (BaseDfuImpl.CLIENT_CHARACTERISTIC_CONFIG.equals(bluetoothGattDescriptor.getUuid())) {
                    DfuBaseService dfuBaseService = BaseDfuImpl.this.mService;
                    dfuBaseService.sendLogBroadcast(5, "Read Response received from descr." + bluetoothGattDescriptor.getCharacteristic().getUuid() + ", value (0x): " + parse(bluetoothGattDescriptor));
                    if (BaseDfuImpl.SERVICE_CHANGED_UUID.equals(bluetoothGattDescriptor.getCharacteristic().getUuid())) {
                        BaseDfuImpl.this.mRequestCompleted = true;
                    } else {
                        BaseDfuImpl.this.loge("Unknown descriptor read");
                    }
                }
            } else {
                BaseDfuImpl baseDfuImpl = BaseDfuImpl.this;
                baseDfuImpl.loge("Descriptor read error: " + i);
                BaseDfuImpl.this.mError = i | 16384;
            }
            BaseDfuImpl.this.notifyLock();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            if (i == 0) {
                if (BaseDfuImpl.CLIENT_CHARACTERISTIC_CONFIG.equals(bluetoothGattDescriptor.getUuid())) {
                    DfuBaseService dfuBaseService = BaseDfuImpl.this.mService;
                    dfuBaseService.sendLogBroadcast(5, "Data written to descr." + bluetoothGattDescriptor.getCharacteristic().getUuid() + ", value (0x): " + parse(bluetoothGattDescriptor));
                    if (BaseDfuImpl.SERVICE_CHANGED_UUID.equals(bluetoothGattDescriptor.getCharacteristic().getUuid())) {
                        DfuBaseService dfuBaseService2 = BaseDfuImpl.this.mService;
                        dfuBaseService2.sendLogBroadcast(1, "Indications enabled for " + bluetoothGattDescriptor.getCharacteristic().getUuid());
                    } else {
                        DfuBaseService dfuBaseService3 = BaseDfuImpl.this.mService;
                        dfuBaseService3.sendLogBroadcast(1, "Notifications enabled for " + bluetoothGattDescriptor.getCharacteristic().getUuid());
                    }
                }
            } else {
                BaseDfuImpl baseDfuImpl = BaseDfuImpl.this;
                baseDfuImpl.loge("Descriptor write error: " + i);
                BaseDfuImpl.this.mError = i | 16384;
            }
            BaseDfuImpl.this.notifyLock();
        }

        @Override // no.nordicsemi.android.dfu.DfuCallback.DfuGattCallback
        public void onDisconnected() {
            BaseDfuImpl baseDfuImpl = BaseDfuImpl.this;
            baseDfuImpl.mConnected = false;
            baseDfuImpl.notifyLock();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            if (i2 == 0) {
                BaseDfuImpl.this.mService.sendLogBroadcast(5, "MTU changed to: " + i);
                int i3 = i + (-3);
                BaseDfuImpl baseDfuImpl = BaseDfuImpl.this;
                if (i3 > baseDfuImpl.mBuffer.length) {
                    baseDfuImpl.mBuffer = new byte[i3];
                }
                baseDfuImpl.logi("MTU changed to: " + i);
            } else {
                BaseDfuImpl.this.logw("Changing MTU failed: " + i2 + " (mtu: " + i + ")");
                if (i2 == 4 && BaseDfuImpl.this.mCurrentMtu > 23) {
                    int i4 = BaseDfuImpl.this.mCurrentMtu - 3;
                    BaseDfuImpl baseDfuImpl2 = BaseDfuImpl.this;
                    if (i4 > baseDfuImpl2.mBuffer.length) {
                        baseDfuImpl2.mBuffer = new byte[baseDfuImpl2.mCurrentMtu - 3];
                        BaseDfuImpl.this.logi("MTU restored to: " + BaseDfuImpl.this.mCurrentMtu);
                    }
                }
            }
            BaseDfuImpl baseDfuImpl3 = BaseDfuImpl.this;
            baseDfuImpl3.mRequestCompleted = true;
            baseDfuImpl3.notifyLock();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onPhyUpdate(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
            if (i3 == 0) {
                DfuBaseService dfuBaseService = BaseDfuImpl.this.mService;
                dfuBaseService.sendLogBroadcast(5, "PHY updated (TX: " + phyToString(i) + ", RX: " + phyToString(i2) + ")");
                BaseDfuImpl baseDfuImpl = BaseDfuImpl.this;
                baseDfuImpl.logi("PHY updated (TX: " + phyToString(i) + ", RX: " + phyToString(i2) + ")");
                return;
            }
            BaseDfuImpl baseDfuImpl2 = BaseDfuImpl.this;
            baseDfuImpl2.logw("Updating PHY failed: " + i3 + " (txPhy: " + i + ", rxPhy: " + i2 + ")");
        }

        public String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            return parse(bluetoothGattCharacteristic.getValue());
        }

        public String parse(BluetoothGattDescriptor bluetoothGattDescriptor) {
            return parse(bluetoothGattDescriptor.getValue());
        }

        private String parse(byte[] bArr) {
            int length;
            if (bArr == null || (length = bArr.length) == 0) {
                return "";
            }
            char[] cArr = new char[(length * 3) - 1];
            for (int i = 0; i < length; i++) {
                int i2 = bArr[i] & 255;
                int i3 = i * 3;
                cArr[i3] = BaseDfuImpl.HEX_ARRAY[i2 >>> 4];
                cArr[i3 + 1] = BaseDfuImpl.HEX_ARRAY[i2 & 15];
                if (i != length - 1) {
                    cArr[i3 + 2] = Soundex.SILENT_MARKER;
                }
            }
            return new String(cArr);
        }
    }

    public BaseDfuImpl(@NonNull Intent intent, @NonNull DfuBaseService dfuBaseService) {
        this.mService = dfuBaseService;
        this.mProgressInfo = dfuBaseService.mProgressInfo;
    }

    private boolean createBondApi18(@NonNull BluetoothDevice bluetoothDevice) {
        try {
            Method method = bluetoothDevice.getClass().getMethod("createBond", new Class[0]);
            this.mService.sendLogBroadcast(0, "gatt.getDevice().createBond() (hidden)");
            return ((Boolean) method.invoke(bluetoothDevice, new Object[0])).booleanValue();
        } catch (Exception e) {
            Log.w(TAG, "An exception occurred while creating bond", e);
            return false;
        }
    }

    private boolean isServiceChangedCCCDEnabled() throws DeviceDisconnectedException, DfuException, UploadAbortedException {
        BluetoothGattCharacteristic characteristic;
        BluetoothGattDescriptor descriptor;
        if (this.mConnected) {
            if (!this.mAborted) {
                BluetoothGatt bluetoothGatt = this.mGatt;
                BluetoothGattService service = bluetoothGatt.getService(GENERIC_ATTRIBUTE_SERVICE_UUID);
                if (service == null || (characteristic = service.getCharacteristic(SERVICE_CHANGED_UUID)) == null || (descriptor = characteristic.getDescriptor(CLIENT_CHARACTERISTIC_CONFIG)) == null) {
                    return false;
                }
                this.mRequestCompleted = false;
                this.mError = 0;
                logi("Reading Service Changed CCCD value...");
                this.mService.sendLogBroadcast(1, "Reading Service Changed CCCD value...");
                DfuBaseService dfuBaseService = this.mService;
                dfuBaseService.sendLogBroadcast(0, "gatt.readDescriptor(" + descriptor.getUuid() + ")");
                bluetoothGatt.readDescriptor(descriptor);
                try {
                    synchronized (this.mLock) {
                        while (true) {
                            if ((this.mRequestCompleted || !this.mConnected || this.mError != 0) && !this.mPaused) {
                                break;
                            }
                            this.mLock.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    loge("Sleeping interrupted", e);
                }
                if (this.mConnected) {
                    if (this.mError == 0) {
                        return descriptor.getValue() != null && descriptor.getValue().length == 2 && descriptor.getValue()[0] == BluetoothGattDescriptor.ENABLE_INDICATION_VALUE[0] && descriptor.getValue()[1] == BluetoothGattDescriptor.ENABLE_INDICATION_VALUE[1];
                    }
                    throw new DfuException("Unable to read Service Changed CCCD", this.mError);
                }
                throw new DeviceDisconnectedException("Unable to read Service Changed CCCD: device disconnected");
            }
            throw new UploadAbortedException();
        }
        throw new DeviceDisconnectedException("Unable to read Service Changed CCCD: device disconnected");
    }

    @Override // no.nordicsemi.android.dfu.DfuController
    public void abort() {
        this.mPaused = false;
        this.mAborted = true;
        notifyLock();
    }

    public boolean createBond() {
        boolean createBondApi18;
        BluetoothDevice device = this.mGatt.getDevice();
        this.mRequestCompleted = false;
        this.mService.sendLogBroadcast(1, "Starting pairing...");
        if (Build.VERSION.SDK_INT >= 19) {
            this.mService.sendLogBroadcast(0, "gatt.getDevice().createBond()");
            createBondApi18 = device.createBond();
        } else {
            createBondApi18 = createBondApi18(device);
        }
        try {
            synchronized (this.mLock) {
                while (createBondApi18 && !this.mRequestCompleted && !this.mAborted) {
                    this.mLock.wait();
                }
            }
        } catch (InterruptedException e) {
            loge("Sleeping interrupted", e);
        }
        return createBondApi18;
    }

    public void enableCCCD(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) throws DeviceDisconnectedException, DfuException, UploadAbortedException {
        BluetoothGatt bluetoothGatt = this.mGatt;
        String str = i == 1 ? "notifications" : "indications";
        if (this.mConnected) {
            if (!this.mAborted) {
                this.mReceivedData = null;
                this.mError = 0;
                BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(CLIENT_CHARACTERISTIC_CONFIG);
                boolean z = descriptor.getValue() != null && descriptor.getValue().length == 2 && descriptor.getValue()[0] > 0 && descriptor.getValue()[1] == 0;
                if (z) {
                    return;
                }
                logi("Enabling " + str + "...");
                DfuBaseService dfuBaseService = this.mService;
                dfuBaseService.sendLogBroadcast(1, "Enabling " + str + " for " + bluetoothGattCharacteristic.getUuid());
                DfuBaseService dfuBaseService2 = this.mService;
                dfuBaseService2.sendLogBroadcast(0, "gatt.setCharacteristicNotification(" + bluetoothGattCharacteristic.getUuid() + ", true)");
                bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, true);
                descriptor.setValue(i == 1 ? BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE : BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
                DfuBaseService dfuBaseService3 = this.mService;
                StringBuilder sb = new StringBuilder();
                sb.append("gatt.writeDescriptor(");
                sb.append(descriptor.getUuid());
                sb.append(i == 1 ? ", value=0x01-00)" : ", value=0x02-00)");
                dfuBaseService3.sendLogBroadcast(0, sb.toString());
                bluetoothGatt.writeDescriptor(descriptor);
                try {
                    synchronized (this.mLock) {
                        while (true) {
                            if ((z || !this.mConnected || this.mError != 0) && !this.mPaused) {
                                break;
                            }
                            this.mLock.wait();
                            z = descriptor.getValue() != null && descriptor.getValue().length == 2 && descriptor.getValue()[0] > 0 && descriptor.getValue()[1] == 0;
                        }
                    }
                } catch (InterruptedException e) {
                    loge("Sleeping interrupted", e);
                }
                if (this.mConnected) {
                    if (this.mError == 0) {
                        return;
                    }
                    throw new DfuException("Unable to set " + str + " state", this.mError);
                }
                throw new DeviceDisconnectedException("Unable to set " + str + " state: device disconnected");
            }
            throw new UploadAbortedException();
        }
        throw new DeviceDisconnectedException("Unable to set " + str + " state: device disconnected");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(14:1|(1:3)|(1:5)|6|(12:32|33|(1:35)|36|9|10|11|(2:13|(1:15)(1:16))|17|18|(3:24|(1:26)|27)|28)|8|9|10|11|(0)|17|18|(5:20|22|24|(0)|27)|28) */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0062 A[Catch: Exception -> 0x0074, TryCatch #1 {Exception -> 0x0074, blocks: (B:16:0x005c, B:18:0x0062, B:20:0x0066, B:21:0x006d, B:22:0x0070), top: B:38:0x005c }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x009d  */
    @Override // no.nordicsemi.android.dfu.DfuService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean initialize(@androidx.annotation.NonNull android.content.Intent r6, @androidx.annotation.NonNull android.bluetooth.BluetoothGatt r7, int r8, @androidx.annotation.NonNull java.io.InputStream r9, @androidx.annotation.Nullable java.io.InputStream r10) throws no.nordicsemi.android.dfu.internal.exception.DfuException, no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, no.nordicsemi.android.dfu.internal.exception.UploadAbortedException {
        /*
            r5 = this;
            r5.mGatt = r7
            r5.mFileType = r8
            r5.mFirmwareStream = r9
            r5.mInitPacketStream = r10
            java.lang.String r0 = "no.nordicsemi.android.dfu.extra.EXTRA_PART_CURRENT"
            r1 = 1
            int r0 = r6.getIntExtra(r0, r1)
            java.lang.String r2 = "no.nordicsemi.android.dfu.extra.EXTRA_PARTS_TOTAL"
            int r2 = r6.getIntExtra(r2, r1)
            java.lang.String r3 = "no.nordicsemi.android.dfu.extra.EXTRA_CURRENT_MTU"
            r4 = 23
            int r6 = r6.getIntExtra(r3, r4)
            r5.mCurrentMtu = r6
            r6 = 15
            r3 = 2
            r4 = 4
            if (r8 <= r4) goto L3f
            java.lang.String r8 = "DFU target does not support (SD/BL)+App update, splitting into 2 parts"
            r5.logw(r8)
            no.nordicsemi.android.dfu.DfuBaseService r8 = r5.mService
            java.lang.String r2 = "Sending system components"
            r8.sendLogBroadcast(r6, r2)
            int r8 = r5.mFileType
            r8 = r8 & (-5)
            r5.mFileType = r8
            java.io.InputStream r2 = r5.mFirmwareStream
            no.nordicsemi.android.dfu.internal.ArchiveInputStream r2 = (no.nordicsemi.android.dfu.internal.ArchiveInputStream) r2
            r2.setContentType(r8)
            r2 = r3
        L3f:
            if (r0 != r3) goto L48
            no.nordicsemi.android.dfu.DfuBaseService r8 = r5.mService
            java.lang.String r4 = "Sending application"
            r8.sendLogBroadcast(r6, r4)
        L48:
            r6 = 0
            if (r10 == 0) goto L59
            boolean r8 = r10.markSupported()     // Catch: java.lang.Exception -> L59
            if (r8 == 0) goto L54
            r10.reset()     // Catch: java.lang.Exception -> L59
        L54:
            int r8 = r10.available()     // Catch: java.lang.Exception -> L59
            goto L5a
        L59:
            r8 = r6
        L5a:
            r5.mInitPacketSizeInBytes = r8
            boolean r8 = r9.markSupported()     // Catch: java.lang.Exception -> L74
            if (r8 == 0) goto L70
            boolean r8 = r9 instanceof no.nordicsemi.android.dfu.internal.ArchiveInputStream     // Catch: java.lang.Exception -> L74
            if (r8 == 0) goto L6d
            r8 = r9
            no.nordicsemi.android.dfu.internal.ArchiveInputStream r8 = (no.nordicsemi.android.dfu.internal.ArchiveInputStream) r8     // Catch: java.lang.Exception -> L74
            r8.fullReset()     // Catch: java.lang.Exception -> L74
            goto L70
        L6d:
            r9.reset()     // Catch: java.lang.Exception -> L74
        L70:
            int r6 = r9.available()     // Catch: java.lang.Exception -> L74
        L74:
            r5.mImageSizeInBytes = r6
            no.nordicsemi.android.dfu.DfuProgressInfo r8 = r5.mProgressInfo
            r8.init(r6, r0, r2)
            android.bluetooth.BluetoothDevice r6 = r7.getDevice()
            int r6 = r6.getBondState()
            r8 = 12
            if (r6 != r8) goto Lac
            java.util.UUID r6 = no.nordicsemi.android.dfu.BaseDfuImpl.GENERIC_ATTRIBUTE_SERVICE_UUID
            android.bluetooth.BluetoothGattService r6 = r7.getService(r6)
            if (r6 == 0) goto Lac
            java.util.UUID r7 = no.nordicsemi.android.dfu.BaseDfuImpl.SERVICE_CHANGED_UUID
            android.bluetooth.BluetoothGattCharacteristic r6 = r6.getCharacteristic(r7)
            if (r6 == 0) goto Lac
            boolean r7 = r5.isServiceChangedCCCDEnabled()
            if (r7 != 0) goto La0
            r5.enableCCCD(r6, r3)
        La0:
            java.lang.String r6 = "Service Changed indications enabled"
            r5.logi(r6)
            no.nordicsemi.android.dfu.DfuBaseService r7 = r5.mService
            r8 = 10
            r7.sendLogBroadcast(r8, r6)
        Lac:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.dfu.BaseDfuImpl.initialize(android.content.Intent, android.bluetooth.BluetoothGatt, int, java.io.InputStream, java.io.InputStream):boolean");
    }

    public boolean isBonded() {
        return this.mGatt.getDevice().getBondState() == 12;
    }

    public void loge(String str) {
        Log.e(TAG, str);
    }

    public void logi(String str) {
        if (DfuBaseService.DEBUG) {
            Log.i(TAG, str);
        }
    }

    public void logw(String str) {
        if (DfuBaseService.DEBUG) {
            Log.w(TAG, str);
        }
    }

    public void notifyLock() {
        synchronized (this.mLock) {
            this.mLock.notifyAll();
        }
    }

    @Override // no.nordicsemi.android.dfu.DfuCallback
    public void onBondStateChanged(int i) {
        this.mRequestCompleted = true;
        notifyLock();
    }

    public String parse(@Nullable byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) == 0) {
            return "";
        }
        char[] cArr = new char[(length * 3) - 1];
        for (int i = 0; i < length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 3;
            char[] cArr2 = HEX_ARRAY;
            cArr[i3] = cArr2[i2 >>> 4];
            cArr[i3 + 1] = cArr2[i2 & 15];
            if (i != length - 1) {
                cArr[i3 + 2] = Soundex.SILENT_MARKER;
            }
        }
        return new String(cArr);
    }

    @Override // no.nordicsemi.android.dfu.DfuController
    public void pause() {
        this.mPaused = true;
    }

    public byte[] readNotificationResponse() throws DeviceDisconnectedException, DfuException, UploadAbortedException {
        try {
            synchronized (this.mLock) {
                while (true) {
                    if ((this.mReceivedData != null || !this.mConnected || this.mError != 0 || this.mAborted) && !this.mPaused) {
                        break;
                    }
                    this.mLock.wait();
                }
            }
        } catch (InterruptedException e) {
            loge("Sleeping interrupted", e);
        }
        if (!this.mAborted) {
            if (this.mConnected) {
                if (this.mError == 0) {
                    return this.mReceivedData;
                }
                throw new DfuException("Unable to write Op Code", this.mError);
            }
            throw new DeviceDisconnectedException("Unable to write Op Code: device disconnected");
        }
        throw new UploadAbortedException();
    }

    @Override // no.nordicsemi.android.dfu.DfuService
    public void release() {
        this.mService = null;
    }

    public boolean removeBond() {
        BluetoothDevice device = this.mGatt.getDevice();
        if (device.getBondState() == 10) {
            return true;
        }
        this.mService.sendLogBroadcast(1, "Removing bond information...");
        boolean z = false;
        try {
            Method method = device.getClass().getMethod("removeBond", new Class[0]);
            this.mRequestCompleted = false;
            this.mService.sendLogBroadcast(0, "gatt.getDevice().removeBond() (hidden)");
            z = ((Boolean) method.invoke(device, new Object[0])).booleanValue();
            try {
                synchronized (this.mLock) {
                    while (!this.mRequestCompleted && !this.mAborted) {
                        this.mLock.wait();
                    }
                }
            } catch (InterruptedException e) {
                loge("Sleeping interrupted", e);
            }
        } catch (Exception e2) {
            Log.w(TAG, "An exception occurred while removing bond information", e2);
        }
        return z;
    }

    @RequiresApi(api = 21)
    public void requestMtu(@IntRange(from = 0, to = 517) int i) throws DeviceDisconnectedException, UploadAbortedException {
        if (!this.mAborted) {
            this.mRequestCompleted = false;
            this.mService.sendLogBroadcast(1, "Requesting new MTU...");
            DfuBaseService dfuBaseService = this.mService;
            dfuBaseService.sendLogBroadcast(0, "gatt.requestMtu(" + i + ")");
            if (this.mGatt.requestMtu(i)) {
                try {
                    synchronized (this.mLock) {
                        while (true) {
                            if ((this.mRequestCompleted || !this.mConnected || this.mError != 0) && !this.mPaused) {
                                break;
                            }
                            this.mLock.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    loge("Sleeping interrupted", e);
                }
                if (!this.mConnected) {
                    throw new DeviceDisconnectedException("Unable to read Service Changed CCCD: device disconnected");
                }
                return;
            }
            return;
        }
        throw new UploadAbortedException();
    }

    public void restartService(@NonNull Intent intent, boolean z) {
        String str;
        if (z) {
            long longExtra = intent.getLongExtra(DfuBaseService.EXTRA_SCAN_DELAY, 0L);
            long longExtra2 = intent.getLongExtra(DfuBaseService.EXTRA_SCAN_TIMEOUT, 5000L);
            DfuBaseService dfuBaseService = this.mService;
            dfuBaseService.sendLogBroadcast(1, "Scanning for the DFU Bootloader... (timeout " + longExtra2 + " ms)");
            if (longExtra > 0) {
                this.mService.waitFor(longExtra);
            }
            str = BootloaderScannerFactory.getScanner(this.mGatt.getDevice().getAddress()).searchUsing(this.mService.getDeviceSelector(), longExtra2);
            logi("Scanning for new address finished with: " + str);
            if (str != null) {
                DfuBaseService dfuBaseService2 = this.mService;
                dfuBaseService2.sendLogBroadcast(5, "DFU Bootloader found with address " + str);
            } else {
                this.mService.sendLogBroadcast(5, "DFU Bootloader not found. Trying the same address...");
            }
        } else {
            str = null;
        }
        if (str != null) {
            intent.putExtra(DfuBaseService.EXTRA_DEVICE_ADDRESS, str);
        }
        intent.putExtra(DfuBaseService.EXTRA_DFU_ATTEMPT, 0);
        this.mService.startService(intent);
    }

    @Override // no.nordicsemi.android.dfu.DfuController
    public void resume() {
        this.mPaused = false;
        notifyLock();
    }

    public void waitIfPaused() {
        try {
            synchronized (this.mLock) {
                while (this.mPaused) {
                    this.mLock.wait();
                }
            }
        } catch (InterruptedException e) {
            loge("Sleeping interrupted", e);
        }
    }

    public void writeOpCode(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic, @NonNull byte[] bArr, boolean z) throws DeviceDisconnectedException, DfuException, UploadAbortedException {
        if (!this.mAborted) {
            this.mReceivedData = null;
            this.mError = 0;
            this.mRequestCompleted = false;
            this.mResetRequestSent = z;
            bluetoothGattCharacteristic.setWriteType(2);
            bluetoothGattCharacteristic.setValue(bArr);
            DfuBaseService dfuBaseService = this.mService;
            dfuBaseService.sendLogBroadcast(1, "Writing to characteristic " + bluetoothGattCharacteristic.getUuid());
            DfuBaseService dfuBaseService2 = this.mService;
            dfuBaseService2.sendLogBroadcast(0, "gatt.writeCharacteristic(" + bluetoothGattCharacteristic.getUuid() + ")");
            this.mGatt.writeCharacteristic(bluetoothGattCharacteristic);
            try {
                synchronized (this.mLock) {
                    while (true) {
                        if ((this.mRequestCompleted || !this.mConnected || this.mError != 0) && !this.mPaused) {
                            break;
                        }
                        this.mLock.wait();
                    }
                }
            } catch (InterruptedException e) {
                loge("Sleeping interrupted", e);
            }
            boolean z2 = this.mResetRequestSent;
            if (!z2 && !this.mConnected) {
                throw new DeviceDisconnectedException("Unable to write Op Code " + ((int) bArr[0]) + ": device disconnected");
            } else if (z2 || this.mError == 0) {
                return;
            } else {
                throw new DfuException("Unable to write Op Code " + ((int) bArr[0]), this.mError);
            }
        }
        throw new UploadAbortedException();
    }

    public void loge(String str, Throwable th) {
        Log.e(TAG, str, th);
    }
}
