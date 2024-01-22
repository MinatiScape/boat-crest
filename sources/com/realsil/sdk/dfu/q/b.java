package com.realsil.sdk.dfu.q;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import com.realsil.sdk.core.bluetooth.GlobalGatt;
import com.realsil.sdk.core.bluetooth.impl.BluetoothGattImpl;
import com.realsil.sdk.core.bluetooth.scanner.LeScannerPresenter;
import com.realsil.sdk.core.bluetooth.utils.BluetoothHelper;
import com.realsil.sdk.core.bluetooth.utils.BluetoothUuid;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import com.realsil.sdk.dfu.DfuConstants;
import com.realsil.sdk.dfu.DfuException;
import com.realsil.sdk.dfu.exception.OtaException;
import com.realsil.sdk.dfu.image.stream.BaseBinInputStream;
import com.realsil.sdk.dfu.internal.base.DfuThreadCallback;
import com.realsil.sdk.dfu.m.f;
import com.realsil.sdk.dfu.model.ConnectionParameters;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import kotlin.UShort;
/* loaded from: classes12.dex */
public abstract class b extends com.realsil.sdk.dfu.m.c {
    public BluetoothGattCharacteristic Y;
    public BluetoothGattCharacteristic Z;
    public BluetoothGattCharacteristic a0;
    public BluetoothGattCharacteristic b0;
    public BluetoothGattCharacteristic c0;
    public List<BluetoothGattCharacteristic> d0;
    public final BluetoothGattCallback e0;
    public byte f0;

    /* loaded from: classes12.dex */
    public class a extends BluetoothGattCallback {
        public a() {
        }

        public final synchronized void a(byte[] bArr) {
            if (bArr != null) {
                if (bArr.length >= 2) {
                    int i = bArr[0] & 255;
                    int i2 = bArr[1] & 255;
                    if (b.this.VDBG) {
                        ZLogger.v(String.format("responseType = %02X , requestOpCode = %02X", Integer.valueOf(i), Integer.valueOf(i2)));
                    }
                    if (i == 16) {
                        if (i2 == 7) {
                            synchronized (b.this.p) {
                                if (b.this.f0 == 7) {
                                    b.this.I = bArr;
                                    b.this.K = true;
                                    b.this.p.notifyAll();
                                } else {
                                    ZLogger.v(b.this.VDBG, "ignore connection parameters notification");
                                }
                            }
                        } else if (i2 != 8) {
                            synchronized (b.this.p) {
                                b.this.I = bArr;
                                b.this.K = true;
                                b.this.p.notifyAll();
                            }
                        } else {
                            byte b = bArr.length >= 3 ? bArr[2] : (byte) 0;
                            boolean z = b.this.DBG;
                            ZLogger.v(z, "remote state changed, busyMode=" + ((int) b));
                            synchronized (b.this.v) {
                                b.this.u = b == 1;
                                b.this.v.notifyAll();
                            }
                        }
                    }
                    return;
                }
            }
            ZLogger.w("notification data invalid");
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            a(bluetoothGattCharacteristic.getValue());
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            if (i == 0) {
                b.this.mReadRxData = bluetoothGattCharacteristic.getValue();
            } else {
                b.this.mErrorState = i | 1024;
                ZLogger.w(String.format(Locale.US, "read Characteristic error:0x%04X", Integer.valueOf(b.this.mErrorState)));
            }
            b.this.notifyReadLock();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            byte[] value = bluetoothGattCharacteristic.getValue();
            if (i == 0) {
                b.this.mWriteRetransFlag = false;
                if (b.this.T != null && b.this.T.equals(bluetoothGattCharacteristic.getUuid())) {
                    if (value != null) {
                        b.this.getDfuProgressInfo().addBytesSent(value.length);
                        b.this.notifyProcessChanged();
                    } else {
                        ZLogger.w("characteristic'value is null, exception");
                    }
                }
            } else if (i == 257 || i == 143) {
                if (b.this.T != null && b.this.T.equals(bluetoothGattCharacteristic.getUuid())) {
                    if (i == 143) {
                        b.this.mWriteRetransFlag = false;
                        if (value != null) {
                            b.this.getDfuProgressInfo().addBytesSent(value.length);
                            b.this.notifyProcessChanged();
                        } else {
                            ZLogger.w("characteristic'value is null, exception");
                        }
                    } else {
                        b.this.mWriteRetransFlag = true;
                        if (b.this.DBG) {
                            ZLogger.d("write image packet error, status=" + i + ", please retry.");
                        }
                    }
                }
            } else {
                b.this.mErrorState = i | 1024;
                ZLogger.w(String.format("Characteristic write error: 0x%04X", Integer.valueOf(b.this.mErrorState)));
            }
            b.this.g();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            if (i != 0) {
                if (i2 == 0) {
                    b.this.setConnectionState(0);
                }
                b.this.mErrorState = i | 2048;
            } else if (i2 == 2) {
                b bVar = b.this;
                if (bVar.mAborted) {
                    ZLogger.w("task already aborted, ignore");
                    return;
                } else if (bVar.mConnectionState == 256) {
                    BluetoothGattImpl.refresh(bluetoothGatt);
                    b.this.n();
                    return;
                }
            } else if (i2 == 0) {
                if (b.this.mProcessState == 521) {
                    b.this.mErrorState = i | 2048;
                    b bVar2 = b.this;
                    if (bVar2.DBG) {
                        ZLogger.d(String.format("disconnect in OTA process, mErrorState: 0x%04X", Integer.valueOf(bVar2.mErrorState)));
                    }
                    b.this.g();
                }
                b.this.setConnectionState(0);
            }
            b.this.notifyConnectionLock();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            if (i == 0) {
                if (com.realsil.sdk.dfu.m.f.f13618a.equals(bluetoothGattDescriptor.getUuid())) {
                    b.this.J = true;
                }
            } else {
                b.this.mErrorState = i | 1024;
            }
            b.this.e();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            if (i2 == 0 && b.this.getDfuConfig().isMtuUpdateEnabled()) {
                b.this.c(i);
            }
            b.this.L = true;
            b.this.e();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onPhyUpdate(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
            super.onPhyUpdate(bluetoothGatt, i, i2, i3);
            b bVar = b.this;
            ZLogger.v(bVar.VDBG, String.format("onPhyUpdate: mConnectionState=0x%04X", Integer.valueOf(bVar.mConnectionState)));
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            b bVar = b.this;
            if (bVar.mAborted) {
                ZLogger.w("task already aborted, ignore");
            } else if (i == 0) {
                try {
                    bVar.O = UUID.fromString(bVar.getDfuConfig().getOtaServiceUuid());
                    b bVar2 = b.this;
                    bVar2.S = UUID.fromString(bVar2.getDfuConfig().getDfuServiceUuid());
                    b bVar3 = b.this;
                    bVar3.T = UUID.fromString(bVar3.getDfuConfig().getDfuDataUuid());
                    b bVar4 = b.this;
                    bVar4.U = UUID.fromString(bVar4.getDfuConfig().getDfuControlPointUuid());
                } catch (Exception e) {
                    ZLogger.w(e.toString());
                }
                b.this.e(bluetoothGatt);
                b.this.d(bluetoothGatt);
                b.this.c(bluetoothGatt);
                b.this.setConnectionState(515);
                b.this.notifyConnectionLock();
            } else {
                bVar.mErrorState = i | 2048;
                b.this.notifyConnectionLock();
            }
        }
    }

    public b(Context context, DfuConfig dfuConfig, DfuThreadCallback dfuThreadCallback) {
        super(context, dfuConfig, dfuThreadCallback);
        this.e0 = new a();
        this.f0 = (byte) -1;
    }

    public void A() throws DfuException {
        ZLogger.v("<< OPCODE_DFU_START_DFU(0x01)");
        byte[] bArr = new byte[16];
        System.arraycopy(this.mCurBinInputStream.getHeaderBuf(), 0, bArr, 0, 12);
        byte[] bArr2 = new byte[17];
        bArr2[0] = 1;
        if (getOtaDeviceInfo().isAesEncryptEnabled()) {
            System.arraycopy(this.l.aesEncrypt(bArr, 0, 16), 0, bArr2, 1, 16);
        } else {
            System.arraycopy(bArr, 0, bArr2, 1, 16);
        }
        a(this.W, bArr2, false);
        if (this.DBG) {
            ZLogger.v("... Reading OPCODE_DFU_START_DFU(0x01) notification");
        }
        byte b = o()[2];
        if (b == 1) {
            return;
        }
        ZLogger.e(String.format("0x%02X(not supported), start dfu failed", Byte.valueOf(b)));
        throw new OtaException("start dfu failed", DfuException.ERROR_OPCODE_RESPONSE_NOT_SUPPORTED);
    }

    public void B() throws DfuException {
        ZLogger.d("<< OPCODE_DFU_CONNECTION_PARAMETER_UPDATE(0x07)");
        this.f0 = (byte) 7;
        byte[] bArr = new byte[9];
        bArr[0] = 7;
        ConnectionParameters connectionParameters = getDfuConfig().getConnectionParameters();
        if (connectionParameters != null) {
            bArr[1] = (byte) (connectionParameters.getMinInterval() & 255);
            bArr[2] = (byte) ((connectionParameters.getMinInterval() >> 8) & 255);
            bArr[3] = (byte) (connectionParameters.getMaxInterval() & 255);
            bArr[4] = (byte) ((connectionParameters.getMaxInterval() >> 8) & 255);
            bArr[5] = (byte) (connectionParameters.getLatency() & 255);
            bArr[6] = (byte) ((connectionParameters.getLatency() >> 8) & 255);
            bArr[7] = (byte) (connectionParameters.getTimeout() & 255);
            bArr[8] = (byte) ((connectionParameters.getTimeout() >> 8) & 255);
        }
        a(this.W, bArr, false);
        try {
            if (this.DBG) {
                ZLogger.d("... waiting OPCODE_DFU_CONNECTION_PARAMETER_UPDATE(0x07) response");
            }
            o();
        } catch (DfuException e) {
            ZLogger.w("ignore connection parameters update exception: " + e.getMessage());
            this.mErrorState = 0;
        }
        this.f0 = (byte) -1;
    }

    public void s() throws DfuException {
        a(new byte[]{4});
    }

    public void t() {
        if (getDfuConfig().getActiveImageDelayTime().longValue() > 0) {
            try {
                Thread.sleep(getDfuConfig().getActiveImageDelayTime().longValue());
            } catch (InterruptedException unused) {
            }
        }
    }

    public int u() throws DfuException {
        byte[] b;
        if (this.W == null) {
            ZLogger.w("no mControlPointCharacteristic found");
            return 0;
        }
        ZLogger.d("<< OPCODE_DFU_ENABLE_BUFFER_CHECK_MODE(0x09)");
        a(this.W, new byte[]{9}, false);
        try {
            if (this.DBG) {
                ZLogger.d("... Reading OPCODE_DFU_ENABLE_BUFFER_CHECK_MODE notification");
            }
            b = b(1600L);
        } catch (DfuException unused) {
            ZLogger.w("Read DFU_REPORT_OTA_FUNCTION_VERSION exception, just think remote is normal function.");
            this.mErrorState = 0;
        }
        if (b[2] == 1) {
            ByteBuffer wrap = ByteBuffer.wrap(b);
            wrap.order(ByteOrder.LITTLE_ENDIAN);
            int i = (((short) (wrap.get(4) & 255)) << 8) | ((short) (wrap.get(3) & 255));
            int i2 = ((short) (wrap.get(5) & 255)) | (((short) (wrap.get(6) & 255)) << 8);
            if (this.DBG) {
                ZLogger.v("maxBufferSize=" + i + ", bufferCheckMtuSize=" + i2);
            }
            a(i);
            b(i2);
            return 1;
        }
        ZLogger.d("Read DFU_REPORT_OTA_FUNCTION_VERSION failed, just think remote is normal function.");
        return 0;
    }

    public void v() throws DfuException {
        int i;
        short s;
        int i2;
        short s2;
        if (getOtaDeviceInfo().specVersion == 0) {
            if (this.b0 != null) {
                ZLogger.v("read patch version");
                byte[] a2 = a(this.b0);
                if (a2 != null) {
                    try {
                        ByteBuffer wrap = ByteBuffer.wrap(a2);
                        wrap.order(ByteOrder.LITTLE_ENDIAN);
                        if (getOtaDeviceInfo().icType <= 3) {
                            s2 = wrap.getShort(0);
                        } else {
                            if (getOtaDeviceInfo().icType != 5 && getOtaDeviceInfo().icType != 9 && getOtaDeviceInfo().icType != 12) {
                                s2 = wrap.getShort(0);
                            }
                            i2 = wrap.getInt(0);
                            getOtaDeviceInfo().setPatchVersion(i2);
                        }
                        i2 = s2 & UShort.MAX_VALUE;
                        getOtaDeviceInfo().setPatchVersion(i2);
                    } catch (Exception e) {
                        ZLogger.w(e.toString());
                    }
                }
            }
            if (this.a0 != null) {
                ZLogger.v("read app version");
                byte[] a3 = a(this.a0);
                if (a3 != null) {
                    try {
                        ByteBuffer wrap2 = ByteBuffer.wrap(a3);
                        wrap2.order(ByteOrder.LITTLE_ENDIAN);
                        if (getOtaDeviceInfo().icType <= 3) {
                            s = wrap2.getShort(0);
                        } else {
                            if (getOtaDeviceInfo().icType != 5 && getOtaDeviceInfo().icType != 9 && getOtaDeviceInfo().icType != 12) {
                                s = wrap2.getShort(0);
                            }
                            i = wrap2.getInt(0);
                            getOtaDeviceInfo().setAppVersion(i);
                        }
                        i = s & UShort.MAX_VALUE;
                        getOtaDeviceInfo().setAppVersion(i);
                    } catch (Exception e2) {
                        ZLogger.e(e2.toString());
                    }
                }
            }
            if (this.c0 != null) {
                ZLogger.v("read patch extension version");
                byte[] a4 = a(this.c0);
                if (a4 != null) {
                    ByteBuffer wrap3 = ByteBuffer.wrap(a4);
                    wrap3.order(ByteOrder.LITTLE_ENDIAN);
                    getOtaDeviceInfo().setPatchExtensionVersion(wrap3.getShort(0) & UShort.MAX_VALUE);
                    return;
                }
                return;
            }
            return;
        }
        List<BluetoothGattCharacteristic> list = this.d0;
        byte[] bArr = null;
        if (list != null && list.size() > 0) {
            for (BluetoothGattCharacteristic bluetoothGattCharacteristic : this.d0) {
                if (this.VDBG) {
                    ZLogger.v("read image version : " + bluetoothGattCharacteristic.getUuid().toString());
                } else {
                    ZLogger.v("read image version");
                }
                byte[] a5 = a(bluetoothGattCharacteristic);
                if (a5 != null) {
                    if (bArr == null) {
                        bArr = a5;
                    } else {
                        byte[] bArr2 = new byte[bArr.length + a5.length];
                        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                        System.arraycopy(a5, 0, bArr2, bArr.length, a5.length);
                        bArr = bArr2;
                    }
                }
            }
            getOtaDeviceInfo().setActiveImageVersionValues(bArr);
            return;
        }
        getOtaDeviceInfo().setActiveImageVersionValues(null);
        ZLogger.d("no ImageVersionCharacteristics to read");
    }

    public boolean w() throws DfuException {
        if (this.Z == null) {
            return false;
        }
        if (this.VDBG) {
            ZLogger.v("start to read remote dev Mac Addr info");
        }
        byte[] a2 = a(this.Z);
        if (a2 != null && a2.length >= 6) {
            byte[] bArr = new byte[6];
            System.arraycopy(a2, 0, bArr, 0, 6);
            getOtaDeviceInfo().setDeviceMac(bArr);
            return true;
        }
        ZLogger.w("Get remote dev Mac Addr info failed, do nothing.");
        throw new OtaException("remote dev Mac Addr info error", DfuException.ERROR_READ_REMOTE_MAC_ADDR);
    }

    public void x() throws DfuException {
        ZLogger.d("<<  OPCODE_DFU_REPORT_CURRENT_BUFFER_SIZE(0x0A)");
        a(this.W, new byte[]{10}, false);
        if (this.DBG) {
            ZLogger.d("... Reading OPCODE_DFU_REPORT_CURRENT_BUFFER_SIZE notification");
        }
        byte[] o = o();
        byte b = o[2];
        if (b == 1) {
            ByteBuffer wrap = ByteBuffer.wrap(o);
            wrap.order(ByteOrder.LITTLE_ENDIAN);
            int i = wrap.getInt(3);
            ZLogger.v(String.format(Locale.US, "maxBufferCheckSize=(0x%04X, %d)", Integer.valueOf(i), Integer.valueOf(i)));
            a(i);
            return;
        }
        ZLogger.w("Get remote buffer size info failed, status: " + ((int) b));
        throw new OtaException("Get remote buffer size info failed", b | 512);
    }

    public int y() throws DfuException {
        byte[] b;
        byte b2;
        if (this.W == null) {
            ZLogger.w("no mControlPointCharacteristic found");
            return 0;
        }
        ZLogger.v("<< OPCODE_DFU_REPORT_OTA_FUNCTION_VERSION(0x09)");
        a(this.W, new byte[]{9}, false);
        try {
            if (this.DBG) {
                ZLogger.d("Reading OPCODE_DFU_REPORT_OTA_FUNCTION_VERSION notification");
            }
            b = b(1600L);
            b2 = b[2];
        } catch (DfuException unused) {
            ZLogger.w("Reading OPCODE_DFU_REPORT_OTA_FUNCTION_VERSION notification failed, just think remote is normal function.");
            this.mErrorState = 0;
        }
        if (b2 == 1) {
            ByteBuffer.wrap(b).order(ByteOrder.LITTLE_ENDIAN);
            return 1;
        }
        ZLogger.w("reportOtaFunctionVersion failed, status: " + ((int) b2));
        return 0;
    }

    public boolean z() {
        try {
            ZLogger.d(this.DBG, "<< OPCODE_DFU_RESET_SYSTEM (0x05)");
            return a(this.W, new byte[]{5}, true);
        } catch (DfuException e) {
            ZLogger.w(String.format("Send OPCODE_DFU_RESET_SYSTEM failed, ignore it, errorcode= 0x%04X", Integer.valueOf(e.getErrCode())));
            this.mErrorState = 0;
            return false;
        }
    }

    public void f(int i) {
        a(i, false);
    }

    public void g(int i) throws DfuException {
        int i2;
        if (this.DBG) {
            ZLogger.d("<< OPCODE_DFU_REPORT_TARGET_IMAGE_INFO(0x06)");
        }
        a(this.W, new byte[]{6, (byte) (i & 255), (byte) ((i >> 8) & 255)}, false);
        if (this.DBG) {
            ZLogger.v("... Reading OPCODE_DFU_REPORT_TARGET_IMAGE_INFO(0x06) notification");
        }
        byte[] o = o();
        int length = o != null ? o.length : 0;
        if ((length > 2 ? o[2] : (byte) -2) == 1) {
            ByteBuffer wrap = ByteBuffer.wrap(o);
            wrap.order(ByteOrder.LITTLE_ENDIAN);
            if (length >= 11) {
                i2 = wrap.getShort(3) & UShort.MAX_VALUE;
                this.mImageUpdateOffset = wrap.getInt(7);
            } else if (length >= 9) {
                i2 = wrap.getShort(3) & UShort.MAX_VALUE;
                this.mImageUpdateOffset = wrap.getInt(5);
            } else {
                this.mImageUpdateOffset = 0;
                i2 = 0;
            }
            ZLogger.d(String.format(Locale.US, "mOriginalFwVersion=%d, mImageUpdateOffset=0x%08X(%d)", Integer.valueOf(i2), Integer.valueOf(this.mImageUpdateOffset), Integer.valueOf(this.mImageUpdateOffset)));
            return;
        }
        ZLogger.e(String.format("0x%02X, Get target image info failed", Integer.valueOf((int) DfuException.ERROR_OPCODE_RESPONSE_NOT_SUPPORTED)));
        throw new OtaException("Get target image info failed", DfuException.ERROR_OPCODE_RESPONSE_NOT_SUPPORTED);
    }

    public void h(int i) throws DfuException {
        ZLogger.d("<< OPCODE_DFU_VALIDATE_FW_IMAGE (0x03)");
        a(this.W, new byte[]{3, (byte) (i & 255), (byte) ((i >> 8) & 255)}, false);
        int i2 = 10000;
        if ((getOtaDeviceInfo().icType == 5 || getOtaDeviceInfo().icType == 9 || getOtaDeviceInfo().icType == 12) && getDfuProgressInfo().getImageSizeInBytes() > 2097152) {
            i2 = Math.max(((getDfuProgressInfo().getImageSizeInBytes() / 1048576) + 1) * 4 * 1000, 10000);
        }
        if (this.DBG) {
            ZLogger.d("... waiting DFU_VALIDATE_FW_IMAGE response for " + i2);
        }
        byte b = b(i2)[2];
        if (b == 1) {
            return;
        }
        if (b == 5) {
            ZLogger.e(String.format("0x%02X, Validate FW failed, CRC check error", Byte.valueOf(b)));
            throw new OtaException("Validate FW failed", 517);
        } else {
            ZLogger.e(String.format("0x%02X(not supported), Validate FW failed", Byte.valueOf(b)));
            throw new OtaException("Validate FW failed", DfuException.ERROR_OPCODE_RESPONSE_NOT_SUPPORTED);
        }
    }

    public final void e(BluetoothGatt bluetoothGatt) {
        BluetoothGattService service = bluetoothGatt.getService(this.O);
        this.P = service;
        if (service == null) {
            ZLogger.w("OTA_SERVICE not found: " + this.O.toString());
            return;
        }
        if (this.DBG) {
            ZLogger.d("find OTA_SERVICE: " + this.O.toString());
        }
        BluetoothGattService bluetoothGattService = this.P;
        UUID uuid = h.f13645a;
        BluetoothGattCharacteristic characteristic = bluetoothGattService.getCharacteristic(uuid);
        this.Q = characteristic;
        if (characteristic == null && this.mOtaWorkMode == 0) {
            ZLogger.d("OTA_CONTROL_ENTER_OTA_MODE_CHARACTERISTIC not found: " + uuid);
        } else if (this.VDBG) {
            ZLogger.v("find OTA_CONTROL_ENTER_OTA_MODE_CHARACTERISTIC = " + uuid);
            ZLogger.v(BluetoothGattImpl.parseProperty2(this.Q.getProperties()));
        }
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.Q;
        if (bluetoothGattCharacteristic != null) {
            bluetoothGattCharacteristic.setWriteType(1);
        }
        BluetoothGattService bluetoothGattService2 = this.P;
        UUID uuid2 = h.b;
        BluetoothGattCharacteristic characteristic2 = bluetoothGattService2.getCharacteristic(uuid2);
        this.Z = characteristic2;
        if (characteristic2 == null) {
            ZLogger.d("OTA_MAC_ADDR_CHARACTERISTIC_UUID not found:" + uuid2);
        } else if (this.VDBG) {
            ZLogger.v("find OTA_MAC_ADDR_CHARACTERISTIC_UUID = " + uuid2);
            ZLogger.v(BluetoothGattImpl.parseProperty2(this.Q.getProperties()));
        }
        BluetoothGattService bluetoothGattService3 = this.P;
        UUID uuid3 = h.c;
        BluetoothGattCharacteristic characteristic3 = bluetoothGattService3.getCharacteristic(uuid3);
        this.b0 = characteristic3;
        if (characteristic3 == null) {
            ZLogger.d("OTA_READ_PATCH_CHARACTERISTIC_UUID not found:" + uuid3);
        } else if (this.VDBG) {
            ZLogger.v("find OTA_PATCH_VERSION_CHARACTERISTIC_UUID: " + uuid3.toString());
            ZLogger.v(BluetoothGattImpl.parseProperty2(this.b0.getProperties()));
        }
        BluetoothGattService bluetoothGattService4 = this.P;
        UUID uuid4 = h.d;
        BluetoothGattCharacteristic characteristic4 = bluetoothGattService4.getCharacteristic(uuid4);
        this.a0 = characteristic4;
        if (characteristic4 == null) {
            ZLogger.d("OTA_READ_APP_CHARACTERISTIC_UUID not found: " + uuid4);
        } else if (this.VDBG) {
            ZLogger.v("find OTA_APP_VERSION_CHARACTERISTIC_UUID: " + uuid4.toString());
            ZLogger.v(BluetoothGattImpl.parseProperty2(this.a0.getProperties()));
        }
        BluetoothGattService bluetoothGattService5 = this.P;
        UUID uuid5 = h.e;
        BluetoothGattCharacteristic characteristic5 = bluetoothGattService5.getCharacteristic(uuid5);
        this.c0 = characteristic5;
        if (characteristic5 == null) {
            ZLogger.d("OTA_PATCH_EXTENSION_VERSION_CHARACTERISTIC_UUID not found:" + uuid5);
        } else if (this.VDBG) {
            ZLogger.v("find OTA_PATCH_EXTENSION_VERSION_CHARACTERISTIC_UUID: " + uuid5.toString());
            ZLogger.v(BluetoothGattImpl.parseProperty2(this.c0.getProperties()));
        }
        BluetoothGattService bluetoothGattService6 = this.P;
        UUID uuid6 = h.g;
        BluetoothGattCharacteristic characteristic6 = bluetoothGattService6.getCharacteristic(uuid6);
        this.R = characteristic6;
        if (characteristic6 == null) {
            ZLogger.d("OTA_DEVICE_INFO_CHARACTERISTIC_UUID not found:" + uuid6);
        } else if (this.VDBG) {
            ZLogger.v("find OTA_DEVICE_INFO_CHARACTERISTIC_UUID: " + uuid6);
            ZLogger.v(BluetoothGattImpl.parseProperty2(this.R.getProperties()));
        }
        this.d0 = new ArrayList();
        for (int i = 65504; i < 65519; i++) {
            UUID fromShortValue = BluetoothUuid.fromShortValue(i);
            BluetoothGattCharacteristic characteristic7 = this.P.getCharacteristic(fromShortValue);
            if (characteristic7 == null) {
                if (this.VDBG) {
                    ZLogger.v("not found image version characteristic:" + fromShortValue.toString());
                    return;
                }
                return;
            }
            if (this.VDBG) {
                ZLogger.v("find image version characteristic: " + fromShortValue.toString());
                ZLogger.v(BluetoothGattImpl.parseProperty2(characteristic7.getProperties()));
            }
            this.d0.add(characteristic7);
        }
    }

    public final int d(BluetoothGatt bluetoothGatt) {
        BluetoothGattService service = bluetoothGatt.getService(this.S);
        this.V = service;
        if (service == null) {
            ZLogger.d("DFU_SERVICE not found:" + this.S);
            return DfuException.ERROR_NO_SERVICE_FOUND_OR_LOSS;
        }
        if (this.VDBG) {
            ZLogger.d("find DFU_SERVICE: " + this.S.toString());
        }
        BluetoothGattCharacteristic characteristic = this.V.getCharacteristic(this.U);
        this.W = characteristic;
        if (characteristic == null) {
            ZLogger.d("not found DFU_CONTROL_POINT_UUID: " + this.U.toString());
            return DfuException.ERROR_NO_CHARACTERISTIC_FOUND_OR_LOSS;
        }
        if (this.VDBG) {
            ZLogger.d("find DFU_CONTROL_POINT_UUID: " + this.U.toString());
        }
        this.W.setWriteType(2);
        if (this.VDBG) {
            ZLogger.v(BluetoothGattImpl.parseProperty2(this.W.getProperties()));
        }
        BluetoothGattCharacteristic characteristic2 = this.V.getCharacteristic(this.T);
        this.X = characteristic2;
        if (characteristic2 == null) {
            ZLogger.d("not found DFU_DATA_UUID: " + this.T.toString());
            return DfuException.ERROR_NO_CHARACTERISTIC_FOUND_OR_LOSS;
        }
        if (this.VDBG) {
            ZLogger.v("find DFU_DATA_UUID: " + this.T.toString());
        }
        this.X.setWriteType(1);
        if (this.VDBG) {
            ZLogger.v(BluetoothGattImpl.parseProperty2(this.X.getProperties()));
            return 0;
        }
        return 0;
    }

    public int c(String str) {
        BluetoothGatt connectGatt;
        BluetoothDevice b = b(str);
        if (b == null) {
            return DfuException.ERROR_CONNECT_ERROR;
        }
        setConnectionState(256);
        this.mErrorState = 0;
        this.isConnectedCallbackCome = false;
        if (this.DBG) {
            ZLogger.v(String.format("Connecting to device:%s, isConnectedCallbackCome=%b", BluetoothHelper.formatAddress(str, true), Boolean.valueOf(this.isConnectedCallbackCome)));
        }
        GlobalGatt globalGatt = this.G;
        if (globalGatt != null) {
            globalGatt.unRegisterCallback(str, this.e0);
            this.G.connect(str, this.e0);
            connectGatt = this.G.getBluetoothGatt(str);
            this.H = connectGatt;
            try {
                synchronized (this.mConnectionLock) {
                    if (this.VDBG) {
                        ZLogger.v(String.format("isConnectedCallbackCome=%b, mErrorState=0x%04X", Boolean.valueOf(this.isConnectedCallbackCome), Integer.valueOf(this.mErrorState)));
                    }
                    if (!this.isConnectedCallbackCome && this.mErrorState == 0) {
                        if (this.DBG) {
                            ZLogger.d("wait for connect gatt for 32000 ms");
                        }
                        this.mConnectionLock.wait(32000L);
                    }
                }
            } catch (InterruptedException e) {
                ZLogger.w("Sleeping interrupted : " + e.toString());
                this.mErrorState = 259;
            }
        } else {
            connectGatt = b.connectGatt(this.mContext, false, this.e0);
            this.H = connectGatt;
            try {
                synchronized (this.mConnectionLock) {
                    if (!this.isConnectedCallbackCome && this.mErrorState == 0) {
                        if (this.DBG) {
                            ZLogger.d("wait for connect gatt for 32000 ms");
                        }
                        this.mConnectionLock.wait(32000L);
                    }
                }
            } catch (InterruptedException e2) {
                ZLogger.w("Sleeping interrupted : " + e2.toString());
                this.mErrorState = 259;
            }
        }
        if (this.mErrorState == 0) {
            if (!this.isConnectedCallbackCome) {
                ZLogger.w("wait for connect, but can not connect with no callback");
                this.mErrorState = 260;
            } else if (connectGatt != null && this.mConnectionState == 515) {
                if (this.DBG) {
                    ZLogger.v("connected the device which going to upgrade");
                }
            } else {
                ZLogger.w("connect with some error, please check. mConnectionState=" + this.mConnectionState);
                this.mErrorState = DfuException.ERROR_CONNECT_ERROR;
            }
        }
        return this.mErrorState;
    }

    public boolean b(byte[] bArr, int i) throws DfuException {
        if (bArr == null) {
            ZLogger.w("buffer == null");
            return false;
        }
        short a2 = a(bArr, i);
        if (this.VDBG) {
            ZLogger.v(String.format(Locale.US, "bufferCheck (%d) >> (%d) %s", Short.valueOf(a2), Integer.valueOf(i), DataConverter.bytes2Hex(bArr)));
        }
        if (this.DBG) {
            ZLogger.d("<< OPCODE_DFU_REPORT_BUFFER_CRC(0x0A)");
        }
        a(this.W, new byte[]{10, (byte) (i & 255), (byte) (i >> 8), (byte) (a2 & 255), (byte) ((a2 >> 8) & 255)}, false);
        if (this.VDBG) {
            ZLogger.v("... waiting OPCODE_DFU_REPORT_BUFFER_CRC(0x0A) response");
        }
        byte[] o = o();
        byte b = o[2];
        ByteBuffer wrap = ByteBuffer.wrap(o);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        this.mImageUpdateOffset = wrap.getInt(3);
        if (this.VDBG) {
            ZLogger.v(String.format(Locale.US, "status:0x%04X, mImageUpdateOffset=0x%08X(%d)", Byte.valueOf(b), Integer.valueOf(this.mImageUpdateOffset), Integer.valueOf(this.mImageUpdateOffset)));
        }
        if (b != 1) {
            if (b == 5 || b == 6 || b == 7) {
                return false;
            }
            if (b != 8) {
                throw new OtaException("ERROR_OPCODE_RESPONSE_NOT_SUPPORTED", DfuException.ERROR_OPCODE_RESPONSE_NOT_SUPPORTED);
            }
            throw new OtaException("DFU_STATUS_FLASH_ERASE_ERROR", b | 512);
        }
        return true;
    }

    public void a(boolean z) throws DfuException {
        ZLogger.d("<< OPCODE_DFU_ENSURE_CURRENT_BUFFER(0x0C)");
        a(this.W, new byte[]{12, !z ? 1 : 0}, false);
    }

    public void a(int i, int i2) throws DfuException {
        ZLogger.d("<< OPCODE_DFU_RECEIVE_FW_IMAGE (0x02)");
        a(this.W, new byte[]{2, (byte) (i & 255), (byte) ((i >> 8) & 255), (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) ((i2 >> 16) & 255), (byte) ((i2 >> 24) & 255)}, false);
    }

    public void a(byte b) throws DfuException {
        a(new byte[]{4, b});
    }

    public void a(byte[] bArr) throws DfuException {
        boolean z;
        notifyStateChanged(DfuConstants.PROGRESS_ACTIVE_IMAGE_AND_RESET);
        int i = DfuException.ERROR_DFU_ABORTED;
        boolean z2 = false;
        try {
            ZLogger.d(this.DBG, "<< OPCODE_DFU_ACTIVE_IMAGE_RESET(0x04)");
            z = a(this.W, bArr, false);
        } catch (DfuException e) {
            if (e.getErrCode() != 4128) {
                if (!getDfuConfig().isWaitActiveCmdAckEnabled()) {
                    ZLogger.d("active cmd has no response, ignore");
                    z = true;
                } else {
                    ZLogger.w("active cmd has no response, notify error");
                    i = e.getErrCode();
                }
            }
        }
        i = 0;
        z2 = z;
        if (z2) {
            ZLogger.d(this.DBG, "image active success");
            d(this.mErrorState);
            closeInputStream(this.mCurBinInputStream);
            return;
        }
        throw new OtaException(i);
    }

    public void a(int i, boolean z) {
        if (this.mAborted) {
            i = 4128;
        }
        if (i != 4128) {
            notifyStateChanged(260, true);
        }
        ZLogger.v(String.format("error = 0x%04X, needReset=%b", Integer.valueOf(i), Boolean.valueOf(z)));
        if (z) {
            z();
        }
        LeScannerPresenter leScannerPresenter = this.D;
        if (leScannerPresenter != null) {
            leScannerPresenter.stopScan();
        }
        closeInputStream(this.mCurBinInputStream);
        if (getDfuConfig().isErrorActionEnabled(1)) {
            d(i);
        }
        DfuThreadCallback dfuThreadCallback = this.mThreadCallback;
        if (dfuThreadCallback != null) {
            dfuThreadCallback.onError(i);
        }
        this.mAborted = true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x005f, code lost:
        if (r4 != (getDfuProgressInfo().getBytesSent() + 12)) goto L12;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0072 A[Catch: IOException -> 0x01fc, TryCatch #0 {IOException -> 0x01fc, blocks: (B:13:0x0051, B:15:0x0056, B:18:0x006e, B:20:0x0072, B:22:0x0087, B:24:0x008e, B:26:0x00af, B:28:0x00b3, B:30:0x00b7, B:31:0x00c6, B:33:0x00d0, B:35:0x00dc, B:25:0x009d, B:17:0x0061), top: B:74:0x0051 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x009d A[Catch: IOException -> 0x01fc, TryCatch #0 {IOException -> 0x01fc, blocks: (B:13:0x0051, B:15:0x0056, B:18:0x006e, B:20:0x0072, B:22:0x0087, B:24:0x008e, B:26:0x00af, B:28:0x00b3, B:30:0x00b7, B:31:0x00c6, B:33:0x00d0, B:35:0x00dc, B:25:0x009d, B:17:0x0061), top: B:74:0x0051 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00b3 A[Catch: IOException -> 0x01fc, TRY_LEAVE, TryCatch #0 {IOException -> 0x01fc, blocks: (B:13:0x0051, B:15:0x0056, B:18:0x006e, B:20:0x0072, B:22:0x0087, B:24:0x008e, B:26:0x00af, B:28:0x00b3, B:30:0x00b7, B:31:0x00c6, B:33:0x00d0, B:35:0x00dc, B:25:0x009d, B:17:0x0061), top: B:74:0x0051 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00d0 A[Catch: IOException -> 0x01fc, TryCatch #0 {IOException -> 0x01fc, blocks: (B:13:0x0051, B:15:0x0056, B:18:0x006e, B:20:0x0072, B:22:0x0087, B:24:0x008e, B:26:0x00af, B:28:0x00b3, B:30:0x00b7, B:31:0x00c6, B:33:0x00d0, B:35:0x00dc, B:25:0x009d, B:17:0x0061), top: B:74:0x0051 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0102 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(android.bluetooth.BluetoothGatt r18, android.bluetooth.BluetoothGattCharacteristic r19, com.realsil.sdk.dfu.image.stream.BaseBinInputStream r20) throws com.realsil.sdk.dfu.DfuException {
        /*
            Method dump skipped, instructions count: 525
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.dfu.q.b.a(android.bluetooth.BluetoothGatt, android.bluetooth.BluetoothGattCharacteristic, com.realsil.sdk.dfu.image.stream.BaseBinInputStream):void");
    }

    public final int c(BluetoothGatt bluetoothGatt) {
        UUID uuid = f.b.f13620a;
        BluetoothGattService service = bluetoothGatt.getService(uuid);
        if (service == null) {
            if (this.DBG) {
                ZLogger.d("DEVICE_INFORMATION_SERVICE not found:" + uuid);
                return DfuException.ERROR_NO_SERVICE_FOUND_OR_LOSS;
            }
            return DfuException.ERROR_NO_SERVICE_FOUND_OR_LOSS;
        }
        if (this.VDBG) {
            ZLogger.v("find DEVICE_INFORMATION_SERVICE: " + uuid.toString());
        }
        UUID uuid2 = f.b.e;
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(uuid2);
        this.Y = characteristic;
        if (characteristic == null) {
            ZLogger.d("DIS_PNP_ID_CHARACTERISTIC not found:" + uuid2);
            return DfuException.ERROR_NO_CHARACTERISTIC_FOUND_OR_LOSS;
        } else if (this.DBG) {
            ZLogger.d("find DIS_PNP_ID_CHARACTERISTIC: " + uuid2.toString());
            return 0;
        } else {
            return 0;
        }
    }

    public boolean e(int i) throws DfuException {
        ZLogger.d(String.format("<< OPCODE_DFU_CHECK_CURRENT_BUFFER(0x0B) , crc=0x%04X", Integer.valueOf(i)));
        a(this.W, new byte[]{11}, false);
        if (this.DBG) {
            ZLogger.d("... waiting CHECK_CURRENT_BUFFER response");
        }
        byte[] o = o();
        byte b = o[2];
        if (b == 1) {
            int i2 = ((o[4] << 8) & 65280) | (o[3] & 255);
            if (i2 == i) {
                return true;
            }
            ZLogger.w("CRC check error, local: " + i + ", remote : " + i2);
        } else {
            ZLogger.w("check current buffer failed, status: " + ((int) b));
        }
        return false;
    }

    public boolean a(BaseBinInputStream baseBinInputStream, int i, int i2) {
        ZLogger.v(this.DBG, String.format(Locale.US, "nextBinSize=%d, mBytesSentBuffer=%d, bufferSize=%d", Integer.valueOf(baseBinInputStream.remainSizeInBytes()), Integer.valueOf(i), Integer.valueOf(i2)));
        return baseBinInputStream.remainSizeInBytes() + i > i2;
    }
}
