package com.realsil.sdk.dfu.p;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import com.realsil.sdk.core.bluetooth.GlobalGatt;
import com.realsil.sdk.core.bluetooth.impl.BluetoothGattImpl;
import com.realsil.sdk.core.bluetooth.utils.BluetoothUuid;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import com.realsil.sdk.dfu.DfuConstants;
import com.realsil.sdk.dfu.DfuException;
import com.realsil.sdk.dfu.exception.OtaException;
import com.realsil.sdk.dfu.image.stream.BaseBinInputStream;
import com.realsil.sdk.dfu.internal.base.DfuThreadCallback;
import com.realsil.sdk.dfu.m.e;
import com.realsil.sdk.dfu.m.g;
import com.realsil.sdk.dfu.m.h;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
/* loaded from: classes12.dex */
public abstract class c extends com.realsil.sdk.dfu.m.c {
    public BluetoothGattCharacteristic Y;
    public List<BluetoothGattCharacteristic> Z;
    public List<BluetoothGattCharacteristic> a0;
    public int b0;
    public final BluetoothGattCallback c0;

    /* loaded from: classes12.dex */
    public class a extends BluetoothGattCallback {
        public a() {
        }

        public final synchronized void a(byte[] bArr) {
            if (bArr != null) {
                if (bArr.length >= 2) {
                    int i = bArr[0] & 255;
                    int i2 = bArr[1] & 255;
                    ZLogger.v(c.this.DBG, String.format("responseType = %02X , requestOpCode = %02X", Integer.valueOf(i), Integer.valueOf(i2)));
                    if (i == 16) {
                        if (i2 == 7) {
                            synchronized (c.this.p) {
                                ZLogger.d("ignore connection parameters notification");
                                c.this.I = bArr;
                                c.this.K = true;
                                c.this.p.notifyAll();
                            }
                        } else if (i2 != 8) {
                            synchronized (c.this.p) {
                                c.this.I = bArr;
                                c.this.K = true;
                                c.this.p.notifyAll();
                            }
                        } else {
                            byte b = bArr.length >= 3 ? bArr[2] : (byte) 0;
                            ZLogger.d("remote state changed, busyMode=" + ((int) b));
                            synchronized (c.this.v) {
                                c.this.u = b == 1;
                                c.this.v.notifyAll();
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
                c.this.mReadRxData = bluetoothGattCharacteristic.getValue();
            } else {
                c.this.mErrorState = i | 1024;
                ZLogger.w(String.format(Locale.US, "read Characteristic error:0x%04X", Integer.valueOf(c.this.mErrorState)));
            }
            c.this.notifyReadLock();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            byte[] value = bluetoothGattCharacteristic.getValue();
            if (i == 0) {
                c.this.mWriteRetransFlag = false;
                if (c.this.T != null && c.this.T.equals(bluetoothGattCharacteristic.getUuid())) {
                    if (value != null) {
                        c.this.getDfuProgressInfo().addBytesSent(value.length);
                        c.this.notifyProcessChanged();
                    } else {
                        ZLogger.w("characteristic'value is null, exception");
                    }
                }
            } else if (i == 257 || i == 143) {
                if (c.this.T != null && c.this.T.equals(bluetoothGattCharacteristic.getUuid())) {
                    if (i == 143) {
                        c.this.mWriteRetransFlag = false;
                        if (value != null) {
                            c.this.getDfuProgressInfo().addBytesSent(value.length);
                            c.this.notifyProcessChanged();
                        } else {
                            ZLogger.w("characteristic'value is null, exception");
                        }
                    } else {
                        c.this.mWriteRetransFlag = true;
                        if (c.this.DBG) {
                            ZLogger.d("write image packet error, status=" + i + ", please retry.");
                        }
                    }
                }
            } else {
                c.this.mErrorState = i | 1024;
                ZLogger.w(String.format("Characteristic write error: 0x%04X", Integer.valueOf(c.this.mErrorState)));
            }
            c.this.g();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            if (i != 0) {
                if (i2 == 0) {
                    c.this.setConnectionState(0);
                }
                c.this.mErrorState = i | 2048;
            } else if (i2 == 2) {
                c cVar = c.this;
                if (cVar.mAborted) {
                    ZLogger.w("task already aborted, ignore");
                    return;
                } else if (cVar.mConnectionState == 256) {
                    BluetoothGattImpl.refresh(bluetoothGatt);
                    c.this.n();
                    return;
                }
            } else if (i2 == 0) {
                if (c.this.mProcessState == 521) {
                    c.this.mErrorState = i | 2048;
                    c cVar2 = c.this;
                    if (cVar2.DBG) {
                        ZLogger.d(String.format("disconnect in OTA process, mErrorState:0x%04X ", Integer.valueOf(cVar2.mErrorState)));
                    }
                    c.this.g();
                }
                c.this.setConnectionState(0);
            }
            synchronized (c.this.mConnectionLock) {
                c cVar3 = c.this;
                if (cVar3.mConnectionState != 256) {
                    cVar3.isConnectedCallbackCome = true;
                }
                c.this.mConnectionLock.notifyAll();
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            if (i == 0) {
                if (com.realsil.sdk.dfu.m.f.f13618a.equals(bluetoothGattDescriptor.getUuid())) {
                    c.this.J = true;
                }
            } else {
                c.this.mErrorState = i | 1024;
            }
            c.this.e();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            if (i2 == 0) {
                boolean z = c.this.VDBG;
                ZLogger.v(z, "mtu=" + i);
                if (c.this.getDfuConfig().isMtuUpdateEnabled()) {
                    c.this.c(i);
                }
            }
            c.this.L = true;
            c.this.e();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onPhyUpdate(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
            super.onPhyUpdate(bluetoothGatt, i, i2, i3);
            ZLogger.v(String.format("onPhyUpdate: mConnectionState=0x%04X", Integer.valueOf(c.this.mConnectionState)));
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            c cVar = c.this;
            if (cVar.mAborted) {
                ZLogger.w("task already aborted, ignore");
                return;
            }
            if (i == 0) {
                cVar.d(bluetoothGatt);
                c.this.c(bluetoothGatt);
                c.this.setConnectionState(515);
                c.this.notifyConnectionLock();
            } else {
                cVar.mErrorState = i | 2048;
            }
            synchronized (c.this.mConnectionLock) {
                c cVar2 = c.this;
                if (cVar2.mConnectionState == 515) {
                    cVar2.isConnectedCallbackCome = true;
                }
                c.this.mConnectionLock.notifyAll();
            }
        }
    }

    public c(Context context, DfuConfig dfuConfig, DfuThreadCallback dfuThreadCallback) {
        super(context, dfuConfig, dfuThreadCallback);
        this.b0 = 0;
        this.c0 = new a();
    }

    public void A() throws DfuException {
        byte[] bArr = new byte[16];
        System.arraycopy(this.mCurBinInputStream.getDfuHeader(), 0, bArr, 0, 12);
        g.b bVar = new g.b(getOtaDeviceInfo().specVersion);
        if (getOtaDeviceInfo().isAesEncryptEnabled()) {
            bVar.a(this.l.aesEncrypt(bArr, 0, 16));
        } else {
            bVar.a(bArr);
        }
        g a2 = bVar.a();
        if (this.DBG) {
            ZLogger.d(a2.toString());
        }
        a(this.W, a2.a(), false);
        if (this.DBG) {
            ZLogger.v("... Reading OPCODE_DFU_START_DFU(0x01) notification");
        }
        byte b = o()[2];
        if (b == 1) {
            return;
        }
        ZLogger.w(String.format("0x%02X(not supported), start dfu failed", Byte.valueOf(b)));
        throw new OtaException("start dfu failed", DfuException.ERROR_OPCODE_RESPONSE_NOT_SUPPORTED);
    }

    public void s() throws DfuException {
        a(new byte[]{4, 0});
    }

    public boolean t() throws DfuException {
        if (this.W == null) {
            ZLogger.w(this.DBG, "no mControlPointCharacteristic found");
            return false;
        }
        ZLogger.d(this.DBG, "<< OPCODE_DFU_ENABLE_BUFFER_CHECK_MODE(0x09)");
        a(this.W, new byte[]{9}, false);
        try {
            ZLogger.v(this.VDBG, "... Reading OPCODE_DFU_ENABLE_BUFFER_CHECK_MODE notification");
            com.realsil.sdk.dfu.m.d a2 = com.realsil.sdk.dfu.m.d.a(getOtaDeviceInfo().protocolType, getOtaDeviceInfo().specVersion, b(1600L));
            if (a2 != null && a2.a()) {
                a(a2.d);
                if (a2.e) {
                    b(a2.f);
                } else {
                    b(getOtaDeviceInfo().mtu);
                }
            }
            return true;
        } catch (DfuException unused) {
            ZLogger.w("enableBufferCheck failed, just think remote is normal function.");
            this.mErrorState = 0;
            return false;
        }
    }

    public void u() throws DfuException {
        List<BluetoothGattCharacteristic> list = this.Z;
        if (list != null && list.size() > 0) {
            getOtaDeviceInfo().setActiveImageVersionValues(null);
            for (BluetoothGattCharacteristic bluetoothGattCharacteristic : this.Z) {
                if (this.DBG) {
                    ZLogger.v("read image version : " + bluetoothGattCharacteristic.getUuid().toString());
                } else {
                    ZLogger.v("read image version");
                }
                byte[] a2 = a(bluetoothGattCharacteristic);
                if (a2 != null) {
                    getOtaDeviceInfo().appendImageVersionBytes(a2);
                }
            }
            return;
        }
        ZLogger.d("no ImageVersionCharacteristics to read");
        getOtaDeviceInfo().setActiveImageVersionValues(null);
    }

    public boolean v() throws DfuException {
        if (this.R == null) {
            return false;
        }
        if (this.DBG) {
            ZLogger.v("start to read remote device info");
        }
        byte[] a2 = a(this.R);
        if (a2 == null) {
            if (this.DBG) {
                ZLogger.v("read device info failed");
            }
            throw new OtaException("read remote device info failed", DfuException.ERROR_READ_DEVICE_INFO_ERROR);
        }
        getOtaDeviceInfo().parseX0014(a2);
        a(getOtaDeviceInfo().maxBufferchecksize);
        return true;
    }

    public boolean w() throws DfuException {
        if (this.Y == null) {
            return false;
        }
        if (this.DBG) {
            ZLogger.v("start to read remote dev Mac Addr info");
        }
        byte[] a2 = a(this.Y);
        if (a2 != null) {
            if (a2.length >= 6) {
                byte[] bArr = new byte[6];
                System.arraycopy(a2, 0, bArr, 0, 6);
                getOtaDeviceInfo().setDeviceMac(bArr);
            }
            if (a2.length >= 12) {
                byte[] bArr2 = new byte[6];
                System.arraycopy(a2, 6, bArr2, 0, 6);
                getOtaDeviceInfo().setRwsBdAddr(bArr2);
                return true;
            }
            return true;
        }
        ZLogger.w("Get remote dev Mac Addr info failed, do nothing.");
        throw new OtaException("remote dev Mac Addr info error", DfuException.ERROR_READ_REMOTE_MAC_ADDR);
    }

    public void x() throws DfuException {
        List<BluetoothGattCharacteristic> list = this.a0;
        byte[] bArr = null;
        if (list != null && list.size() > 0) {
            for (BluetoothGattCharacteristic bluetoothGattCharacteristic : this.a0) {
                if (this.DBG) {
                    ZLogger.v("read image section size : " + bluetoothGattCharacteristic.getUuid().toString());
                } else {
                    ZLogger.v("read image section size");
                }
                byte[] a2 = a(bluetoothGattCharacteristic);
                if (a2 != null) {
                    if (bArr == null) {
                        bArr = a2;
                    } else {
                        byte[] bArr2 = new byte[bArr.length + a2.length];
                        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                        System.arraycopy(a2, 0, bArr2, bArr.length, a2.length);
                        bArr = bArr2;
                    }
                }
            }
            getOtaDeviceInfo().setImageSectionSizeValues(bArr);
            return;
        }
        ZLogger.d("no ImageSectionCharacteristics to read");
        getOtaDeviceInfo().setImageSectionSizeValues(null);
    }

    public void y() throws DfuException {
        if (!this.mAborted) {
            boolean z = this.DBG;
            ZLogger.d(z, "isBufferCheckEnabled=" + getOtaDeviceInfo().isBufferCheckEnabled());
            if (getOtaDeviceInfo().isBufferCheckEnabled()) {
                if (t()) {
                    this.r = 1;
                } else {
                    this.r = 0;
                }
            } else {
                this.r = 0;
            }
            boolean z2 = this.VDBG;
            ZLogger.v(z2, "mRemoteOtaFunctionInfo=" + this.r);
            return;
        }
        throw new OtaException("user aborted", DfuException.ERROR_DFU_ABORTED);
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

    public void f(int i) throws DfuException {
        a(i, this.mImageUpdateOffset);
        int bytesSent = getDfuProgressInfo().getBytesSent();
        int i2 = this.mImageUpdateOffset;
        if (bytesSent == i2 || i2 == -1) {
            return;
        }
        ZLogger.d("mBytesSent != mImageUpdateOffset, reload image bin file");
        this.imageFileLoaded = false;
        k();
        alignmentSendBytes(this.mImageUpdateOffset, false);
    }

    public final int c(String str) {
        BluetoothDevice bluetoothDevice;
        setConnectionState(256);
        this.mErrorState = 0;
        this.isConnectedCallbackCome = false;
        if (this.DBG) {
            ZLogger.d("Connecting to device..." + str);
        }
        BluetoothGatt bluetoothGatt = null;
        try {
            bluetoothDevice = this.z.getRemoteDevice(str);
        } catch (Exception e) {
            ZLogger.w(e.toString());
            bluetoothDevice = null;
        }
        GlobalGatt globalGatt = this.G;
        if (globalGatt != null) {
            globalGatt.unRegisterCallback(str, this.c0);
            this.G.connect(str, this.c0);
            bluetoothGatt = this.G.getBluetoothGatt(str);
            this.H = bluetoothGatt;
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
        } else if (bluetoothDevice != null) {
            bluetoothGatt = bluetoothDevice.connectGatt(this.mContext, false, this.c0);
            this.H = bluetoothGatt;
            try {
                synchronized (this.mConnectionLock) {
                    if (!this.isConnectedCallbackCome && this.mErrorState == 0) {
                        if (this.DBG) {
                            ZLogger.d("wait for connect gatt for 32000 ms");
                        }
                        this.mConnectionLock.wait(32000L);
                    }
                }
            } catch (InterruptedException e3) {
                ZLogger.w("Sleeping interrupted : " + e3.toString());
                this.mErrorState = 259;
            }
        } else {
            ZLogger.w("device is null");
            this.H = null;
        }
        if (this.mErrorState == 0) {
            if (!this.isConnectedCallbackCome) {
                ZLogger.w("wait for connect, but can not connect with no callback");
                this.mErrorState = 260;
            } else if (bluetoothGatt == null || this.mConnectionState != 515) {
                ZLogger.w("connect with some error, please check. mConnectionState=" + this.mConnectionState);
                this.mErrorState = DfuException.ERROR_CONNECT_ERROR;
            }
        }
        if (this.mErrorState == 0 && this.DBG) {
            ZLogger.v("connected the device which going to upgrade");
        }
        return this.mErrorState;
    }

    public final void d(BluetoothGatt bluetoothGatt) {
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
        UUID uuid = f.f13640a;
        BluetoothGattCharacteristic characteristic = bluetoothGattService.getCharacteristic(uuid);
        this.Q = characteristic;
        if (characteristic == null) {
            ZLogger.w("OTA_CONTROL_ENTER_OTA_MODE_CHARACTERISTIC not found: " + uuid);
        } else {
            if (this.DBG) {
                ZLogger.d("find OTA_CONTROL_ENTER_OTA_MODE_CHARACTERISTIC = " + uuid);
                ZLogger.d(BluetoothGattImpl.parseProperty2(this.Q.getProperties()));
            }
            this.Q.setWriteType(1);
        }
        BluetoothGattService bluetoothGattService2 = this.P;
        UUID uuid2 = f.b;
        BluetoothGattCharacteristic characteristic2 = bluetoothGattService2.getCharacteristic(uuid2);
        this.Y = characteristic2;
        if (characteristic2 == null) {
            ZLogger.w("OTA_MAC_ADDR_CHARACTERISTIC_UUID not found:" + uuid2);
        } else if (this.DBG) {
            ZLogger.d("find OTA_MAC_ADDR_CHARACTERISTIC_UUID = " + uuid2);
            ZLogger.d(BluetoothGattImpl.parseProperty2(this.Q.getProperties()));
        }
        BluetoothGattService bluetoothGattService3 = this.P;
        UUID uuid3 = f.c;
        BluetoothGattCharacteristic characteristic3 = bluetoothGattService3.getCharacteristic(uuid3);
        this.R = characteristic3;
        if (characteristic3 == null) {
            ZLogger.w("OTA_DEVICE_INFO_CHARACTERISTIC_UUID not found:" + uuid3);
        } else if (this.DBG) {
            ZLogger.d("find OTA_DEVICE_INFO_CHARACTERISTIC_UUID: " + uuid3);
            ZLogger.d(BluetoothGattImpl.parseProperty2(this.R.getProperties()));
        }
        this.Z = new ArrayList();
        int i = 65504;
        while (true) {
            if (i >= 65519) {
                break;
            }
            UUID fromShortValue = BluetoothUuid.fromShortValue(i);
            BluetoothGattCharacteristic characteristic4 = this.P.getCharacteristic(fromShortValue);
            if (characteristic4 == null) {
                if (this.VDBG) {
                    ZLogger.v("not found image version characteristic:" + fromShortValue.toString());
                }
            } else {
                if (this.VDBG) {
                    ZLogger.v("find image version characteristic: " + fromShortValue.toString());
                }
                this.Z.add(characteristic4);
                i++;
            }
        }
        this.a0 = new ArrayList();
        for (int i2 = 65524; i2 < 65526; i2++) {
            UUID fromShortValue2 = BluetoothUuid.fromShortValue(i2);
            BluetoothGattCharacteristic characteristic5 = this.P.getCharacteristic(fromShortValue2);
            if (characteristic5 == null) {
                if (this.DBG) {
                    ZLogger.d("not found image session size characteristic:" + fromShortValue2.toString());
                    return;
                }
                return;
            }
            if (this.DBG) {
                ZLogger.d("find image session size characteristic: " + fromShortValue2.toString());
            }
            this.a0.add(characteristic5);
        }
    }

    public void e(int i) {
        a(i, false);
    }

    public boolean b(byte[] bArr, int i) throws DfuException {
        if (bArr == null) {
            ZLogger.w("buffer == null");
            return false;
        }
        if (this.DBG) {
            ZLogger.v(String.format(Locale.US, "bufferCheck (%d) >> (%d) %s", Integer.valueOf(i), Integer.valueOf(bArr.length), DataConverter.bytes2Hex(bArr)));
        }
        short a2 = a(bArr, i);
        if (this.DBG) {
            ZLogger.d("<< OPCODE_DFU_REPORT_BUFFER_CRC(0x0A)");
        }
        a(this.W, new byte[]{10, (byte) (i & 255), (byte) (i >> 8), (byte) (a2 & 255), (byte) ((a2 >> 8) & 255)}, false);
        if (this.DBG) {
            ZLogger.d("... waiting OPCODE_DFU_REPORT_BUFFER_CRC(0x0A) response");
        }
        byte[] o = o();
        byte b = o[2];
        ByteBuffer wrap = ByteBuffer.wrap(o);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        this.mImageUpdateOffset = wrap.getInt(3);
        if (this.DBG) {
            ZLogger.d(String.format(Locale.US, "status:0x%04X, mImageUpdateOffset=0x%08X(%d)", Byte.valueOf(b), Integer.valueOf(this.mImageUpdateOffset), Integer.valueOf(this.mImageUpdateOffset)));
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

    public int a(String str, int i) {
        int i2 = 0;
        while (a()) {
            int c = c(str);
            if (c == 0) {
                return 0;
            }
            if ((c & (-2049)) != 133) {
                b(this.H);
            } else {
                ZLogger.w("connect fail with GATT_ERROR, do not need disconnect");
            }
            a(this.H);
            try {
                Thread.sleep(1600L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i2++;
            ZLogger.d("tryConnectTime=" + i2);
            if (i2 > i) {
                return c;
            }
        }
        return DfuException.ERROR_DFU_ABORTED;
    }

    public void a(int i, int i2) throws DfuException {
        com.realsil.sdk.dfu.m.e a2 = new e.b(getOtaDeviceInfo().specVersion).a(i).b(i2).a();
        if (this.DBG) {
            ZLogger.d(a2.toString());
        }
        a(this.W, a2.a(), false);
    }

    public void a(int i, byte b) throws DfuException {
        h a2 = new h.b(getOtaDeviceInfo().protocolType, getOtaDeviceInfo().specVersion).a(i).a(b).a();
        if (this.DBG) {
            ZLogger.d(a2.toString());
        }
        a(this.W, a2.a(), false);
        if (this.DBG) {
            ZLogger.v("... waiting DFU_VALIDATE_FW_IMAGE response");
        }
        byte b2 = o()[2];
        if (b2 == 1) {
            return;
        }
        if (b2 == 5) {
            ZLogger.w(String.format("0x%02X, Validate FW failed, CRC check error", Byte.valueOf(b2)));
            throw new OtaException("Validate FW failed", 517);
        } else {
            ZLogger.w(String.format("0x%02X(not supported), Validate FW failed", Byte.valueOf(b2)));
            throw new OtaException("Validate FW failed", DfuException.ERROR_OPCODE_RESPONSE_NOT_SUPPORTED);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x005a, code lost:
        if (r5 != (getDfuProgressInfo().getBytesSent() + 12)) goto L9;
     */
    /* JADX WARN: Removed duplicated region for block: B:17:0x006d A[Catch: IOException -> 0x01fd, TryCatch #0 {IOException -> 0x01fd, blocks: (B:10:0x004c, B:12:0x0051, B:15:0x0069, B:17:0x006d, B:19:0x0082, B:21:0x0089, B:25:0x00bc, B:27:0x00c0, B:29:0x00d9, B:23:0x009a, B:24:0x00aa, B:14:0x005c), top: B:68:0x004c }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00d9 A[Catch: IOException -> 0x01fd, TRY_LEAVE, TryCatch #0 {IOException -> 0x01fd, blocks: (B:10:0x004c, B:12:0x0051, B:15:0x0069, B:17:0x006d, B:19:0x0082, B:21:0x0089, B:25:0x00bc, B:27:0x00c0, B:29:0x00d9, B:23:0x009a, B:24:0x00aa, B:14:0x005c), top: B:68:0x004c }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0109 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(android.bluetooth.BluetoothGatt r18, android.bluetooth.BluetoothGattCharacteristic r19, com.realsil.sdk.dfu.image.stream.BaseBinInputStream r20) throws com.realsil.sdk.dfu.DfuException {
        /*
            Method dump skipped, instructions count: 526
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.dfu.p.c.b(android.bluetooth.BluetoothGatt, android.bluetooth.BluetoothGattCharacteristic, com.realsil.sdk.dfu.image.stream.BaseBinInputStream):void");
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
            ZLogger.d("<< OPCODE_DFU_ACTIVE_IMAGE_RESET(0x04)");
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
            ZLogger.i("image active success");
            d(this.mErrorState);
            closeInputStream(this.mCurBinInputStream);
            return;
        }
        throw new OtaException(i);
    }

    public final int c(BluetoothGatt bluetoothGatt) {
        try {
            this.O = UUID.fromString(getDfuConfig().getOtaServiceUuid());
            this.S = UUID.fromString(getDfuConfig().getDfuServiceUuid());
            this.T = UUID.fromString(getDfuConfig().getDfuDataUuid());
            this.U = UUID.fromString(getDfuConfig().getDfuControlPointUuid());
        } catch (Exception e) {
            ZLogger.w(e.toString());
        }
        BluetoothGattService service = bluetoothGatt.getService(this.S);
        this.V = service;
        if (service == null) {
            ZLogger.d("DFU_SERVICE not found:" + this.S);
            return DfuException.ERROR_NO_SERVICE_FOUND_OR_LOSS;
        }
        if (this.DBG) {
            ZLogger.d("find DFU_SERVICE: " + this.S.toString());
        }
        BluetoothGattCharacteristic characteristic = this.V.getCharacteristic(this.U);
        this.W = characteristic;
        if (characteristic == null) {
            ZLogger.d("not found DFU_CONTROL_POINT_UUID: " + this.U.toString());
            return DfuException.ERROR_NO_CHARACTERISTIC_FOUND_OR_LOSS;
        }
        if (this.DBG) {
            ZLogger.d("find DFU_CONTROL_POINT_UUID: " + this.U.toString());
        }
        this.W.setWriteType(2);
        ZLogger.d(BluetoothGattImpl.parseProperty2(this.W.getProperties()));
        BluetoothGattCharacteristic characteristic2 = this.V.getCharacteristic(this.T);
        this.X = characteristic2;
        if (characteristic2 == null) {
            ZLogger.d("not found DFU_DATA_UUID: " + this.T.toString());
            return DfuException.ERROR_NO_CHARACTERISTIC_FOUND_OR_LOSS;
        }
        if (this.DBG) {
            ZLogger.d("find DFU_DATA_UUID: " + this.T.toString());
        }
        this.X.setWriteType(1);
        ZLogger.d(BluetoothGattImpl.parseProperty2(this.X.getProperties()));
        return 0;
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
        this.D.stopScan();
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

    public void a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, BaseBinInputStream baseBinInputStream) throws DfuException {
        int read;
        c();
        this.mErrorState = 0;
        this.lastPacketTransferred = false;
        int i = this.MAX_PACKET_SIZE;
        byte[] bArr = new byte[i];
        while (!this.lastPacketTransferred) {
            if (!this.mAborted) {
                startSpeedControl();
                if (this.DBG) {
                    ZLogger.v(getDfuProgressInfo().toString());
                }
                try {
                    if (getDfuProgressInfo().getBytesSent() == 0) {
                        int i2 = this.MAX_PACKET_SIZE;
                        byte[] bArr2 = new byte[i2];
                        baseBinInputStream.read(bArr2, i2 - 12);
                        System.arraycopy(baseBinInputStream.getHeaderBuf(), 0, bArr, 0, 12);
                        System.arraycopy(bArr2, 0, bArr, 12, this.MAX_PACKET_SIZE - 12);
                        read = this.MAX_PACKET_SIZE;
                    } else {
                        read = baseBinInputStream.read(bArr, i);
                    }
                    if (getDfuProgressInfo().getRemainSizeInBytes() < this.MAX_PACKET_SIZE) {
                        ZLogger.v("reach the end of the file, only read some");
                        read = getDfuProgressInfo().getRemainSizeInBytes();
                    }
                    int i3 = read;
                    if (i3 <= 0) {
                        if (getDfuProgressInfo().isFileSendOver()) {
                            ZLogger.d("image file has already been send over");
                            return;
                        }
                        ZLogger.w("Error while reading file with size: " + i3);
                        throw new OtaException("Error while reading file", 257);
                    }
                    if (getOtaDeviceInfo().isAesEncryptEnabled()) {
                        for (int i4 = i3; i4 > 0; i4 -= 16) {
                            if (i4 >= 16) {
                                int i5 = i3 - i4;
                                System.arraycopy(this.l.aesEncrypt(bArr, i5, 16), 0, bArr, i5, 16);
                                if (getOtaDeviceInfo().getAesEncryptMode() == 0) {
                                    break;
                                }
                            }
                        }
                    }
                    a(bluetoothGatt, bluetoothGattCharacteristic, bArr, i3, false);
                    i();
                    blockSpeedControl();
                } catch (IOException unused) {
                    throw new OtaException("Error while reading file", 257);
                }
            } else {
                throw new OtaException("user aborted", DfuException.ERROR_DFU_ABORTED);
            }
        }
    }

    public boolean a(BaseBinInputStream baseBinInputStream, int i, int i2, int i3) {
        int i4;
        if (baseBinInputStream.otaTempBufferCheckOrder == 0) {
            return false;
        }
        ZLogger.v(this.DBG, String.format(Locale.US, "nextBinSize=%d, mBytesSentBuffer=%d, bufferSize=%d", Integer.valueOf(baseBinInputStream.remainSizeInBytes()), Integer.valueOf(i2), Integer.valueOf(i3)));
        if (baseBinInputStream.otaTempBufferCheckOrder == 1) {
            i4 = baseBinInputStream.remainSizeInBytes();
        } else if (i < 0 || i >= this.pendingImageInputStreams.size()) {
            i4 = 0;
        } else {
            int i5 = 0;
            for (int i6 = i; i6 < this.pendingImageInputStreams.size(); i6++) {
                BaseBinInputStream baseBinInputStream2 = this.pendingImageInputStreams.get(i6);
                if (baseBinInputStream2.otaTempBufferCheckOrder != baseBinInputStream.otaTempBufferCheckOrder) {
                    break;
                }
                i5 += baseBinInputStream2.remainSizeInBytes();
            }
            i4 = i5;
        }
        return i4 + i2 > i3;
    }
}
