package no.nordicsemi.android.dfu;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.UUID;
import no.nordicsemi.android.dfu.BaseCustomDfuImpl;
import no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException;
import no.nordicsemi.android.dfu.internal.exception.DfuException;
import no.nordicsemi.android.dfu.internal.exception.UnknownResponseException;
import no.nordicsemi.android.dfu.internal.exception.UploadAbortedException;
/* JADX INFO: Access modifiers changed from: package-private */
@SuppressLint({"MissingPermission"})
/* loaded from: classes12.dex */
public class LegacyDfuImpl extends BaseCustomDfuImpl {
    public static final UUID DEFAULT_DFU_CONTROL_POINT_UUID;
    public static final UUID DEFAULT_DFU_PACKET_UUID;
    public static final UUID DEFAULT_DFU_SERVICE_UUID;
    public static final UUID DEFAULT_DFU_VERSION_UUID;
    public static UUID DFU_CONTROL_POINT_UUID = null;
    public static UUID DFU_PACKET_UUID = null;
    public static UUID DFU_SERVICE_UUID = null;
    private static final int DFU_STATUS_SUCCESS = 1;
    public static UUID DFU_VERSION_UUID = null;
    private static final byte[] OP_CODE_ACTIVATE_AND_RESET;
    private static final int OP_CODE_ACTIVATE_AND_RESET_KEY = 5;
    private static final byte[] OP_CODE_INIT_DFU_PARAMS;
    private static final byte[] OP_CODE_INIT_DFU_PARAMS_COMPLETE;
    private static final int OP_CODE_INIT_DFU_PARAMS_KEY = 2;
    private static final byte[] OP_CODE_INIT_DFU_PARAMS_START;
    private static final int OP_CODE_PACKET_RECEIPT_NOTIF_KEY = 17;
    private static final byte[] OP_CODE_PACKET_RECEIPT_NOTIF_REQ;
    private static final int OP_CODE_PACKET_RECEIPT_NOTIF_REQ_KEY = 8;
    private static final byte[] OP_CODE_RECEIVE_FIRMWARE_IMAGE;
    private static final int OP_CODE_RECEIVE_FIRMWARE_IMAGE_KEY = 3;
    private static final byte[] OP_CODE_RESET;
    private static final int OP_CODE_RESET_KEY = 6;
    private static final int OP_CODE_RESPONSE_CODE_KEY = 16;
    private static final byte[] OP_CODE_START_DFU;
    private static final int OP_CODE_START_DFU_KEY = 1;
    private static final byte[] OP_CODE_START_DFU_V1;
    private static final byte[] OP_CODE_VALIDATE;
    private static final int OP_CODE_VALIDATE_KEY = 4;
    private final LegacyBluetoothCallback mBluetoothCallback;
    private BluetoothGattCharacteristic mControlPointCharacteristic;
    private boolean mImageSizeInProgress;
    private BluetoothGattCharacteristic mPacketCharacteristic;

    /* loaded from: classes12.dex */
    public class LegacyBluetoothCallback extends BaseCustomDfuImpl.BaseCustomBluetoothCallback {
        public LegacyBluetoothCallback() {
            super();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            if (bluetoothGattCharacteristic.getIntValue(17, 0).intValue() != 17) {
                if (!LegacyDfuImpl.this.mRemoteErrorOccurred) {
                    if (bluetoothGattCharacteristic.getIntValue(17, 2).intValue() != 1) {
                        LegacyDfuImpl.this.mRemoteErrorOccurred = true;
                    }
                    handleNotification(bluetoothGatt, bluetoothGattCharacteristic);
                }
            } else {
                LegacyDfuImpl.this.mProgressInfo.setBytesReceived(bluetoothGattCharacteristic.getIntValue(20, 1).intValue());
                handlePacketReceiptNotification(bluetoothGatt, bluetoothGattCharacteristic);
            }
            LegacyDfuImpl.this.notifyLock();
        }

        @Override // no.nordicsemi.android.dfu.BaseCustomDfuImpl.BaseCustomBluetoothCallback
        public void onPacketCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            if (LegacyDfuImpl.this.mImageSizeInProgress) {
                DfuBaseService dfuBaseService = LegacyDfuImpl.this.mService;
                dfuBaseService.sendLogBroadcast(5, "Data written to " + bluetoothGattCharacteristic.getUuid() + ", value (0x): " + parse(bluetoothGattCharacteristic));
                LegacyDfuImpl.this.mImageSizeInProgress = false;
            }
        }
    }

    static {
        UUID uuid = new UUID(23296205844446L, 1523193452336828707L);
        DEFAULT_DFU_SERVICE_UUID = uuid;
        UUID uuid2 = new UUID(23300500811742L, 1523193452336828707L);
        DEFAULT_DFU_CONTROL_POINT_UUID = uuid2;
        UUID uuid3 = new UUID(23304795779038L, 1523193452336828707L);
        DEFAULT_DFU_PACKET_UUID = uuid3;
        UUID uuid4 = new UUID(23313385713630L, 1523193452336828707L);
        DEFAULT_DFU_VERSION_UUID = uuid4;
        DFU_SERVICE_UUID = uuid;
        DFU_CONTROL_POINT_UUID = uuid2;
        DFU_PACKET_UUID = uuid3;
        DFU_VERSION_UUID = uuid4;
        OP_CODE_START_DFU = new byte[]{1, 0};
        OP_CODE_START_DFU_V1 = new byte[]{1};
        OP_CODE_INIT_DFU_PARAMS = new byte[]{2};
        OP_CODE_INIT_DFU_PARAMS_START = new byte[]{2, 0};
        OP_CODE_INIT_DFU_PARAMS_COMPLETE = new byte[]{2, 1};
        OP_CODE_RECEIVE_FIRMWARE_IMAGE = new byte[]{3};
        OP_CODE_VALIDATE = new byte[]{4};
        OP_CODE_ACTIVATE_AND_RESET = new byte[]{5};
        OP_CODE_RESET = new byte[]{6};
        OP_CODE_PACKET_RECEIPT_NOTIF_REQ = new byte[]{8, 0, 0};
    }

    public LegacyDfuImpl(@NonNull Intent intent, @NonNull DfuBaseService dfuBaseService) {
        super(intent, dfuBaseService);
        this.mBluetoothCallback = new LegacyBluetoothCallback();
    }

    private int getStatusCode(@Nullable byte[] bArr, int i) throws UnknownResponseException {
        if (bArr != null && bArr.length == 3 && bArr[0] == 16 && bArr[1] == i && bArr[2] >= 1 && bArr[2] <= 6) {
            return bArr[2];
        }
        throw new UnknownResponseException("Invalid response received", bArr, 16, i);
    }

    private int readVersion(@Nullable BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic != null) {
            return bluetoothGattCharacteristic.getIntValue(18, 0).intValue();
        }
        return 0;
    }

    private void resetAndRestart(@NonNull BluetoothGatt bluetoothGatt, @NonNull Intent intent) throws DfuException, DeviceDisconnectedException, UploadAbortedException {
        this.mService.sendLogBroadcast(15, "Last upload interrupted. Restarting device...");
        this.mProgressInfo.setProgress(-5);
        logi("Sending Reset command (Op Code = 6)");
        writeOpCode(this.mControlPointCharacteristic, OP_CODE_RESET);
        this.mService.sendLogBroadcast(10, "Reset request sent");
        this.mService.waitUntilDisconnected();
        this.mService.sendLogBroadcast(5, "Disconnected by the remote device");
        BluetoothGattService service = bluetoothGatt.getService(BaseDfuImpl.GENERIC_ATTRIBUTE_SERVICE_UUID);
        this.mService.refreshDeviceCache(bluetoothGatt, !((service == null || service.getCharacteristic(BaseDfuImpl.SERVICE_CHANGED_UUID) == null) ? false : true));
        this.mService.close(bluetoothGatt);
        logi("Restarting the service");
        Intent intent2 = new Intent();
        intent2.fillIn(intent, 24);
        restartService(intent2, false);
    }

    private void setNumberOfPackets(@NonNull byte[] bArr, int i) {
        bArr[1] = (byte) (i & 255);
        bArr[2] = (byte) ((i >> 8) & 255);
    }

    private void writeImageSize(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) throws DeviceDisconnectedException, DfuException, UploadAbortedException {
        this.mReceivedData = null;
        this.mError = 0;
        this.mImageSizeInProgress = true;
        bluetoothGattCharacteristic.setWriteType(1);
        bluetoothGattCharacteristic.setValue(new byte[4]);
        bluetoothGattCharacteristic.setValue(i, 20, 0);
        DfuBaseService dfuBaseService = this.mService;
        dfuBaseService.sendLogBroadcast(1, "Writing to characteristic " + bluetoothGattCharacteristic.getUuid());
        DfuBaseService dfuBaseService2 = this.mService;
        dfuBaseService2.sendLogBroadcast(0, "gatt.writeCharacteristic(" + bluetoothGattCharacteristic.getUuid() + ")");
        this.mGatt.writeCharacteristic(bluetoothGattCharacteristic);
        try {
            synchronized (this.mLock) {
                while (true) {
                    if ((!this.mImageSizeInProgress || !this.mConnected || this.mError != 0 || this.mAborted) && !this.mPaused) {
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
                if (this.mError != 0) {
                    throw new DfuException("Unable to write Image Size", this.mError);
                }
                return;
            }
            throw new DeviceDisconnectedException("Unable to write Image Size: device disconnected");
        }
        throw new UploadAbortedException();
    }

    private void writeOpCode(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic, @NonNull byte[] bArr) throws DeviceDisconnectedException, DfuException, UploadAbortedException {
        boolean z = false;
        writeOpCode(bluetoothGattCharacteristic, bArr, (bArr[0] == 6 || bArr[0] == 5) ? true : true);
    }

    @Override // no.nordicsemi.android.dfu.BaseCustomDfuImpl
    public UUID getControlPointCharacteristicUUID() {
        return DFU_CONTROL_POINT_UUID;
    }

    @Override // no.nordicsemi.android.dfu.BaseCustomDfuImpl
    public UUID getDfuServiceUUID() {
        return DFU_SERVICE_UUID;
    }

    @Override // no.nordicsemi.android.dfu.BaseCustomDfuImpl
    public UUID getPacketCharacteristicUUID() {
        return DFU_PACKET_UUID;
    }

    @Override // no.nordicsemi.android.dfu.DfuService
    public boolean isClientCompatible(@NonNull Intent intent, @NonNull BluetoothGatt bluetoothGatt) {
        BluetoothGattCharacteristic characteristic;
        BluetoothGattService service = bluetoothGatt.getService(DFU_SERVICE_UUID);
        if (service == null || (characteristic = service.getCharacteristic(DFU_CONTROL_POINT_UUID)) == null || characteristic.getDescriptor(BaseDfuImpl.CLIENT_CHARACTERISTIC_CONFIG) == null) {
            return false;
        }
        this.mControlPointCharacteristic = characteristic;
        BluetoothGattCharacteristic characteristic2 = service.getCharacteristic(DFU_PACKET_UUID);
        this.mPacketCharacteristic = characteristic2;
        return characteristic2 != null;
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x03d9 A[Catch: UnknownResponseException -> 0x0205, UploadAbortedException -> 0x020d, RemoteDfuException -> 0x0673, TryCatch #2 {RemoteDfuException -> 0x0673, blocks: (B:93:0x0326, B:97:0x0330, B:99:0x03cd, B:104:0x03d5, B:106:0x03d9, B:108:0x03e4, B:110:0x0454, B:113:0x0483, B:114:0x048a, B:109:0x0427, B:116:0x048d, B:124:0x049d, B:125:0x04db, B:126:0x04fa, B:127:0x050d, B:129:0x056b, B:131:0x061f, B:135:0x064e, B:136:0x0653, B:137:0x065a, B:138:0x065b, B:139:0x0662, B:141:0x0664, B:142:0x066a, B:122:0x0499, B:143:0x066b, B:144:0x0670, B:145:0x0671, B:146:0x0672), top: B:167:0x0326 }] */
    /* JADX WARN: Removed duplicated region for block: B:124:0x049d A[Catch: UnknownResponseException -> 0x0205, UploadAbortedException -> 0x020d, RemoteDfuException -> 0x0673, TryCatch #2 {RemoteDfuException -> 0x0673, blocks: (B:93:0x0326, B:97:0x0330, B:99:0x03cd, B:104:0x03d5, B:106:0x03d9, B:108:0x03e4, B:110:0x0454, B:113:0x0483, B:114:0x048a, B:109:0x0427, B:116:0x048d, B:124:0x049d, B:125:0x04db, B:126:0x04fa, B:127:0x050d, B:129:0x056b, B:131:0x061f, B:135:0x064e, B:136:0x0653, B:137:0x065a, B:138:0x065b, B:139:0x0662, B:141:0x0664, B:142:0x066a, B:122:0x0499, B:143:0x066b, B:144:0x0670, B:145:0x0671, B:146:0x0672), top: B:167:0x0326 }] */
    /* JADX WARN: Removed duplicated region for block: B:129:0x056b A[Catch: UnknownResponseException -> 0x0205, UploadAbortedException -> 0x020d, RemoteDfuException -> 0x0673, TryCatch #2 {RemoteDfuException -> 0x0673, blocks: (B:93:0x0326, B:97:0x0330, B:99:0x03cd, B:104:0x03d5, B:106:0x03d9, B:108:0x03e4, B:110:0x0454, B:113:0x0483, B:114:0x048a, B:109:0x0427, B:116:0x048d, B:124:0x049d, B:125:0x04db, B:126:0x04fa, B:127:0x050d, B:129:0x056b, B:131:0x061f, B:135:0x064e, B:136:0x0653, B:137:0x065a, B:138:0x065b, B:139:0x0662, B:141:0x0664, B:142:0x066a, B:122:0x0499, B:143:0x066b, B:144:0x0670, B:145:0x0671, B:146:0x0672), top: B:167:0x0326 }] */
    /* JADX WARN: Removed duplicated region for block: B:138:0x065b A[Catch: UnknownResponseException -> 0x0205, UploadAbortedException -> 0x020d, RemoteDfuException -> 0x0673, TryCatch #2 {RemoteDfuException -> 0x0673, blocks: (B:93:0x0326, B:97:0x0330, B:99:0x03cd, B:104:0x03d5, B:106:0x03d9, B:108:0x03e4, B:110:0x0454, B:113:0x0483, B:114:0x048a, B:109:0x0427, B:116:0x048d, B:124:0x049d, B:125:0x04db, B:126:0x04fa, B:127:0x050d, B:129:0x056b, B:131:0x061f, B:135:0x064e, B:136:0x0653, B:137:0x065a, B:138:0x065b, B:139:0x0662, B:141:0x0664, B:142:0x066a, B:122:0x0499, B:143:0x066b, B:144:0x0670, B:145:0x0671, B:146:0x0672), top: B:167:0x0326 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0323 A[Catch: UnknownResponseException -> 0x0205, UploadAbortedException -> 0x020d, RemoteDfuException -> 0x0324, TRY_LEAVE, TryCatch #7 {RemoteDfuException -> 0x0324, blocks: (B:75:0x021c, B:78:0x0225, B:80:0x0229, B:82:0x0310, B:87:0x031c, B:88:0x0321, B:89:0x0322, B:90:0x0323), top: B:172:0x021c }] */
    @Override // no.nordicsemi.android.dfu.DfuService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void performDfu(@androidx.annotation.NonNull android.content.Intent r29) throws no.nordicsemi.android.dfu.internal.exception.DfuException, no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, no.nordicsemi.android.dfu.internal.exception.UploadAbortedException {
        /*
            Method dump skipped, instructions count: 1834
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.dfu.LegacyDfuImpl.performDfu(android.content.Intent):void");
    }

    @Override // no.nordicsemi.android.dfu.DfuCallback
    public BaseCustomDfuImpl.BaseCustomBluetoothCallback getGattCallback() {
        return this.mBluetoothCallback;
    }

    private void writeImageSize(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, int i2, int i3) throws DeviceDisconnectedException, DfuException, UploadAbortedException {
        this.mReceivedData = null;
        this.mError = 0;
        this.mImageSizeInProgress = true;
        bluetoothGattCharacteristic.setWriteType(1);
        bluetoothGattCharacteristic.setValue(new byte[12]);
        bluetoothGattCharacteristic.setValue(i, 20, 0);
        bluetoothGattCharacteristic.setValue(i2, 20, 4);
        bluetoothGattCharacteristic.setValue(i3, 20, 8);
        DfuBaseService dfuBaseService = this.mService;
        dfuBaseService.sendLogBroadcast(1, "Writing to characteristic " + bluetoothGattCharacteristic.getUuid());
        DfuBaseService dfuBaseService2 = this.mService;
        dfuBaseService2.sendLogBroadcast(0, "gatt.writeCharacteristic(" + bluetoothGattCharacteristic.getUuid() + ")");
        this.mGatt.writeCharacteristic(bluetoothGattCharacteristic);
        try {
            synchronized (this.mLock) {
                while (true) {
                    if ((!this.mImageSizeInProgress || !this.mConnected || this.mError != 0 || this.mAborted) && !this.mPaused) {
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
                if (this.mError != 0) {
                    throw new DfuException("Unable to write Image Sizes", this.mError);
                }
                return;
            }
            throw new DeviceDisconnectedException("Unable to write Image Sizes: device disconnected");
        }
        throw new UploadAbortedException();
    }
}
