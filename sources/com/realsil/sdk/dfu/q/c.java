package com.realsil.sdk.dfu.q;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.realsil.sdk.core.bluetooth.scanner.ScannerParams;
import com.realsil.sdk.core.bluetooth.scanner.compat.CompatScanFilter;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.DfuConstants;
import com.realsil.sdk.dfu.DfuException;
import com.realsil.sdk.dfu.exception.OtaException;
import com.realsil.sdk.dfu.image.stream.BaseBinInputStream;
import com.realsil.sdk.dfu.internal.base.BaseDfuTask;
import com.realsil.sdk.dfu.internal.base.DfuThreadCallback;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.realsil.sdk.dfu.model.OtaDeviceInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
/* loaded from: classes12.dex */
public class c extends b {
    public c(Context context, DfuConfig dfuConfig, DfuThreadCallback dfuThreadCallback) {
        super(context, dfuConfig, dfuThreadCallback);
    }

    public final int C() {
        if (this.P == null) {
            ZLogger.w("OTA SERVICE not found:" + this.O.toString());
            return DfuException.ERROR_NO_SERVICE_FOUND_OR_LOSS;
        } else if (this.Q == null) {
            ZLogger.w("not found OTA_CONTROL_ENTER_OTA_MODE_CHARACTERISTIC : " + h.f13645a.toString());
            return DfuException.ERROR_NO_CHARACTERISTIC_FOUND_OR_LOSS;
        } else {
            return 0;
        }
    }

    public final boolean D() throws DfuException {
        if (this.otaModeEnabled) {
            if (TextUtils.isEmpty(this.mOtaDeviceAddress)) {
                ArrayList arrayList = new ArrayList();
                if (getOtaDeviceInfo().icType != 5 && getOtaDeviceInfo().icType != 9 && getOtaDeviceInfo().icType != 4 && getOtaDeviceInfo().icType != 6 && getOtaDeviceInfo().icType != 7 && getOtaDeviceInfo().icType != 8 && getOtaDeviceInfo().icType != 13 && getOtaDeviceInfo().icType != 10 && getOtaDeviceInfo().icType != 11 && getOtaDeviceInfo().icType != 12) {
                    arrayList.add(new CompatScanFilter.Builder().setManufacturerData(getDfuConfig().getManufacturerId(), BaseDfuTask.convertAddress(this.mDeviceAddress)).build());
                } else {
                    arrayList.add(new CompatScanFilter.Builder().setManufacturerData(getDfuConfig().getManufacturerId(), getOtaDeviceInfo().getDeviceMac()).build());
                }
                ScannerParams m = m();
                m.setScanFilters(arrayList);
                a(m, 31000L);
            }
            d(this.mOtaDeviceAddress);
            if (!this.otaEnvironmentPrepared) {
                G();
            } else {
                j();
            }
            if (this.mCurBinInputStream == null) {
                f(4097);
                return false;
            }
        } else {
            e(this.mOtaDeviceAddress);
            int C = C();
            if (C == 0) {
                if (!this.otaEnvironmentPrepared) {
                    G();
                } else {
                    j();
                }
                if (this.mCurBinInputStream == null) {
                    f(4097);
                    return false;
                } else if (!E()) {
                    f(DfuException.ERROR_ENTER_OTA_MODE_FAILED);
                    return false;
                } else {
                    ArrayList arrayList2 = new ArrayList();
                    if (getOtaDeviceInfo().icType != 5 && getOtaDeviceInfo().icType != 9 && getOtaDeviceInfo().icType != 4 && getOtaDeviceInfo().icType != 6 && getOtaDeviceInfo().icType != 7 && getOtaDeviceInfo().icType != 8 && getOtaDeviceInfo().icType != 10 && getOtaDeviceInfo().icType != 11 && getOtaDeviceInfo().icType != 12) {
                        arrayList2.add(new CompatScanFilter.Builder().setManufacturerData(getDfuConfig().getManufacturerId(), BaseDfuTask.convertAddress(this.mDeviceAddress)).build());
                    } else {
                        arrayList2.add(new CompatScanFilter.Builder().setManufacturerData(getDfuConfig().getManufacturerId(), getOtaDeviceInfo().getDeviceMac()).build());
                    }
                    ScannerParams m2 = m();
                    m2.setScanFilters(arrayList2);
                    a(m2, 31000L);
                    d(this.mOtaDeviceAddress);
                }
            } else {
                throw new OtaException("load ota service failed", C);
            }
        }
        r();
        this.otaModeEnabled = true;
        return true;
    }

    public final boolean E() throws DfuException {
        boolean z;
        if (!this.mAborted) {
            if (this.Q == null) {
                return false;
            }
            notifyStateChanged(518);
            ZLogger.d("<< OPCODE_ENTER_OTA_MODE(0x01), enable device to enter OTA mode");
            try {
                z = a(this.Q, h.i, false);
            } catch (DfuException e) {
                boolean z2 = e.getErrCode() != 267;
                ZLogger.e("<< OPCODE_ENTER_OTA_MODE(0x01) failed, ignore it :" + e.getMessage());
                this.mErrorState = 0;
                z = z2;
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            if (z && getDfuConfig().isWaitDisconnectWhenEnterOtaMode()) {
                waitUntilDisconnected();
            }
            a(this.H);
            return z;
        }
        throw new OtaException("user aborted", DfuException.ERROR_DFU_ABORTED);
    }

    public final boolean F() {
        BaseBinInputStream baseBinInputStream;
        notifyStateChanged(514);
        this.mOtaDeviceAddress = this.mDeviceAddress;
        this.otaModeEnabled = false;
        boolean z = false;
        while (a()) {
            try {
            } catch (DfuException e) {
                ZLogger.w(DfuConstants.parseOtaState(this.mProcessState) + ", " + e.toString());
                int errCode = e.getErrCode();
                if (errCode == 4128) {
                    a(errCode, true);
                } else if (errCode != 4097 && errCode != 265) {
                    if (z()) {
                        waitUntilDisconnected();
                    }
                    a(errCode, false);
                } else {
                    a(errCode, false);
                }
            }
            if (!D() || !I()) {
                return false;
            }
            this.mBytesSentBuffer += getDfuProgressInfo().getBytesSent();
            if (getDfuProgressInfo().isLastImageFile()) {
                ZLogger.v("no pendding image file to upload.");
                getDfuProgressInfo().setActiveImageSize(this.mBytesSentBuffer);
                if (!this.o) {
                    notifyStateChanged(DfuConstants.PROGRESS_PENDING_ACTIVE_IMAGE);
                } else {
                    t();
                    s();
                    notifyStateChanged(258);
                }
                z = true;
            } else {
                ZLogger.v("has pendding image file to upload");
                if (getOtaDeviceInfo().getUpdateMechanism() == 1) {
                    this.mOtaDeviceAddress = this.mDeviceAddress;
                    this.otaModeEnabled = false;
                    this.mBytesSentBuffer = 0;
                    t();
                    s();
                    h();
                } else if (getOtaDeviceInfo().getUpdateMechanism() == 3 && (baseBinInputStream = this.mNextBinInputStream) != null && a(baseBinInputStream, this.mBytesSentBuffer, getOtaDeviceInfo().otaTempBufferSize * 4096)) {
                    ZLogger.d("make device to enter the ota advertiser mode, and let the app continue update image");
                    this.mOtaDeviceAddress = null;
                    this.otaModeEnabled = true;
                    this.mBytesSentBuffer = 0;
                    t();
                    a((byte) 1);
                    h();
                }
            }
            try {
                Thread.sleep(1000L);
                continue;
            } catch (InterruptedException unused) {
            }
            if (z) {
                return z;
            }
        }
        a(DfuException.ERROR_DFU_ABORTED, true);
        return false;
    }

    public final void G() throws DfuException {
        this.otaEnvironmentPrepared = false;
        notifyStateChanged(517);
        try {
            Thread.sleep(1500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.mOtaDeviceInfo = new OtaDeviceInfo(this.h, 2);
        H();
        if (getOtaDeviceInfo().icType == 5 || getOtaDeviceInfo().icType == 9 || getOtaDeviceInfo().icType == 4 || getOtaDeviceInfo().icType == 6 || getOtaDeviceInfo().icType == 7 || getOtaDeviceInfo().icType == 8 || getOtaDeviceInfo().icType == 10 || getOtaDeviceInfo().icType == 11 || getOtaDeviceInfo().icType == 12) {
            w();
        }
        v();
        if (this.DBG) {
            ZLogger.d(getOtaDeviceInfo().toString());
        }
        k();
        this.otaEnvironmentPrepared = true;
        ZLogger.d("Ota Environment prepared.");
    }

    public final boolean H() throws DfuException {
        if (this.R == null) {
            return false;
        }
        if (this.VDBG) {
            ZLogger.v("start to read remote dev info");
        }
        byte[] a2 = a(this.R);
        if (a2 != null) {
            getOtaDeviceInfo().parseX0000(a2);
            a(getOtaDeviceInfo().maxBufferchecksize);
            return true;
        }
        ZLogger.w("Get dev info failed");
        throw new OtaException("get remote dev info failed", DfuException.ERROR_READ_DEVICE_INFO_ERROR);
    }

    public final boolean I() throws DfuException {
        if (!a()) {
            a(DfuException.ERROR_DFU_ABORTED, true);
            return false;
        } else if (getOtaDeviceInfo().isAesEncryptEnabled() && !b()) {
            f(4113);
            return false;
        } else {
            notifyStateChanged(DfuConstants.PROGRESS_START_DFU_PROCESS);
            if (this.DBG) {
                ZLogger.v(getDfuProgressInfo().toString());
            }
            a(this.H, this.W, true);
            if (Build.VERSION.SDK_INT >= 23) {
                a(this.H, 256);
            }
            p();
            if (getOtaDeviceInfo().icType <= 3) {
                this.r = y();
                if (this.VDBG) {
                    ZLogger.v("mRemoteOtaFunctionInfo=" + this.r);
                }
                if (this.r == 1) {
                    x();
                }
            } else {
                if (!getOtaDeviceInfo().isBufferCheckEnabled()) {
                    this.r = 0;
                } else {
                    this.r = u();
                }
                if (this.VDBG) {
                    ZLogger.v("mRemoteOtaFunctionInfo=" + this.r);
                }
            }
            B();
            getDfuProgressInfo().start();
            g(getDfuProgressInfo().getCurImageId());
            if (!getDfuConfig().isBreakpointResumeEnabled()) {
                this.mImageUpdateOffset = 0;
                ZLogger.d(String.format(Locale.US, "BreakpointResume disable: mImageUpdateOffset=0x%08X(%d)", 0, Integer.valueOf(this.mImageUpdateOffset)));
            }
            if (this.mImageUpdateOffset == 0) {
                A();
            }
            if (this.mImageUpdateOffset - 12 >= getDfuProgressInfo().getImageSizeInBytes()) {
                ZLogger.d("Last send reach the bottom");
            } else if (getOtaDeviceInfo().icType <= 3) {
                j(getDfuProgressInfo().getCurImageId());
                if (this.r == 1) {
                    b(this.H, this.X, this.mCurBinInputStream);
                } else {
                    c(this.H, this.X, this.mCurBinInputStream);
                }
            } else if (getOtaDeviceInfo().icType != 4 && getOtaDeviceInfo().icType != 6 && getOtaDeviceInfo().icType != 7 && getOtaDeviceInfo().icType != 8 && getOtaDeviceInfo().icType != 5 && getOtaDeviceInfo().icType != 9 && getOtaDeviceInfo().icType != 10 && getOtaDeviceInfo().icType != 11 && getOtaDeviceInfo().icType != 12) {
                i(getDfuProgressInfo().getCurImageId());
                if (this.r == 1) {
                    a(this.H, this.X, this.mCurBinInputStream);
                } else {
                    d(this.H, this.X, this.mCurBinInputStream);
                }
            } else {
                i(getDfuProgressInfo().getCurImageId());
                if (this.r == 1) {
                    a(this.H, this.X, this.mCurBinInputStream);
                } else {
                    d(this.H, this.X, this.mCurBinInputStream);
                }
            }
            getDfuProgressInfo().sendOver();
            h(getDfuProgressInfo().getCurImageId());
            return true;
        }
    }

    public final int a(String str, int i) {
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
            if (i2 >= i) {
                return c;
            }
        }
        return DfuException.ERROR_DFU_ABORTED;
    }

    @Override // com.realsil.sdk.dfu.internal.base.BaseDfuTask
    public boolean activeImage(boolean z) {
        if (super.activeImage(z)) {
            if (this.mConnectionState != 515) {
                if (this.DBG) {
                    ZLogger.d("start to re-connect the RCU which going to active image, current state is: " + this.mConnectionState);
                }
                int a2 = a(this.mOtaDeviceAddress, getDfuConfig().getRetransConnectTimes());
                if (a2 != 0) {
                    ZLogger.e("Something error in OTA process, errorCode: " + a2 + "mProcessState" + this.mProcessState);
                    a(a2, true);
                    return false;
                }
            }
            if (z) {
                try {
                    t();
                    s();
                    notifyStateChanged(258);
                } catch (DfuException e) {
                    f(e.getErrCode());
                }
            } else {
                if (z()) {
                    waitUntilDisconnected();
                }
                a(274, false);
            }
            return true;
        }
        return false;
    }

    public final void b(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, BaseBinInputStream baseBinInputStream) throws DfuException {
        int i;
        if (this.DBG) {
            ZLogger.d(String.format(Locale.US, "updateImageWithCheckBufferForBee1, packetSize=%d, mCurrentMaxBufferSize=%d", Integer.valueOf(this.MAX_PACKET_SIZE), Integer.valueOf(this.s)));
        }
        this.mErrorState = 0;
        this.lastPacketTransferred = false;
        int i2 = this.MAX_PACKET_SIZE;
        byte[] bArr = new byte[i2];
        byte[] bArr2 = new byte[this.s];
        while (!this.lastPacketTransferred) {
            if (!this.mAborted) {
                a(baseBinInputStream);
                if (this.DBG) {
                    ZLogger.v(getDfuProgressInfo().toString());
                }
                try {
                    int read = baseBinInputStream.read(bArr2);
                    if (getDfuProgressInfo().getRemainSizeInBytes() < read) {
                        read = getDfuProgressInfo().getRemainSizeInBytes();
                        if (this.DBG) {
                            ZLogger.d("Reach the bottom of the image,  checkImageBufferSize: " + read);
                        }
                    }
                    int i3 = read;
                    byte[] bArr3 = new byte[this.s];
                    int i4 = 0;
                    while (true) {
                        int i5 = 0;
                        while (i5 < i3) {
                            int min = Math.min(i2, i3 - i5);
                            System.arraycopy(bArr2, i5, bArr, 0, min);
                            if (getOtaDeviceInfo().isAesEncryptEnabled() && min >= 16) {
                                System.arraycopy(this.l.aesEncrypt(bArr, 0, 16), 0, bArr, 0, 16);
                            } else if (min <= 0) {
                                ZLogger.e("Error while reading file with bufferSize= " + min);
                                throw new OtaException("Error while reading file", 257);
                            }
                            System.arraycopy(bArr, 0, bArr3, i5, min);
                            a(bluetoothGatt, bluetoothGattCharacteristic, bArr, min, false);
                            i();
                            i5 += min;
                        }
                        if (this.VDBG) {
                            ZLogger.v("pos: " + i5 + ", checkImageBufferSize: " + i3);
                        }
                        boolean e = e(com.realsil.sdk.dfu.b.a.a(bArr2, 0, i3));
                        if (e) {
                            i = i4;
                        } else {
                            getDfuProgressInfo().addBytesSent(0 - i3);
                            i = i4 + 1;
                            ZLogger.w("check failed, retransBufferCheckTimes: " + i);
                        }
                        a(e);
                        if (i >= 3) {
                            ZLogger.w("Error while buffer check, reach max try times: " + i + ", MAX_BUFFER_CHECK_RETRANS_TIME: 3");
                            throw new OtaException("Error while buffer check", DfuException.ERROR_BUFFER_CHECK_REACH_MAX_RETRY_TIMES);
                        } else if (e) {
                            break;
                        } else {
                            i4 = i;
                        }
                    }
                } catch (IOException unused) {
                    throw new OtaException("Error while reading file", 257);
                }
            } else {
                throw new OtaException("user aborted", DfuException.ERROR_DFU_ABORTED);
            }
        }
    }

    public final void c(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, BaseBinInputStream baseBinInputStream) throws DfuException {
        c();
        this.mErrorState = 0;
        this.lastPacketTransferred = false;
        int i = this.MAX_PACKET_SIZE;
        byte[] bArr = new byte[i];
        while (!this.lastPacketTransferred) {
            if (!this.mAborted) {
                a(baseBinInputStream);
                if (this.DBG) {
                    ZLogger.v(getDfuProgressInfo().toString());
                }
                startSpeedControl();
                try {
                    int read = baseBinInputStream.read(bArr, i);
                    if (getDfuProgressInfo().getRemainSizeInBytes() < this.MAX_PACKET_SIZE) {
                        if (this.DBG) {
                            ZLogger.v("reach the end of the file, only read some");
                        }
                        read = getDfuProgressInfo().getRemainSizeInBytes();
                    }
                    int i2 = read;
                    if (i2 <= 0) {
                        if (getDfuProgressInfo().isFileSendOver()) {
                            ZLogger.d("image file has already been send over");
                            return;
                        }
                        ZLogger.e("Error while reading file with size: " + i2);
                        throw new OtaException("Error while reading file", 257);
                    }
                    if (getOtaDeviceInfo().isAesEncryptEnabled()) {
                        for (int i3 = i2; i3 > 0; i3 -= 16) {
                            if (i3 >= 16) {
                                int i4 = i2 - i3;
                                System.arraycopy(this.l.aesEncrypt(bArr, i4, 16), 0, bArr, i4, 16);
                                if (getOtaDeviceInfo().getAesEncryptMode() == 0) {
                                    break;
                                }
                            }
                        }
                    }
                    a(bluetoothGatt, bluetoothGattCharacteristic, bArr, i2, false);
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

    public final void d(String str) throws DfuException {
        if (!this.mAborted) {
            notifyStateChanged(520);
            int a2 = a(str, getDfuConfig().getRetransConnectTimes());
            if (a2 == 0) {
                return;
            }
            if (a2 == 4128) {
                throw new OtaException("aborted, connectRemoteDevice failed", a2);
            }
            throw new OtaException("connectOtaRemoteDevice failed", a2);
        }
        throw new OtaException("user aborted", DfuException.ERROR_DFU_ABORTED);
    }

    public final void e(String str) throws DfuException {
        if (!this.mAborted) {
            notifyStateChanged(516);
            int a2 = a(str, getDfuConfig().getRetransConnectTimes());
            if (a2 == 0) {
                return;
            }
            if (a2 != 4128) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new CompatScanFilter.Builder().setDeviceAddress(str).build());
                ScannerParams m = m();
                m.setScanFilters(arrayList);
                m.setAddressFilter(str);
                a(m);
                int a3 = a(str, getDfuConfig().getRetransConnectTimes());
                if (a3 == 0) {
                    return;
                }
                if (a3 == 4128) {
                    throw new OtaException("aborted, connectRemoteDevice failed", a3);
                }
                throw new OtaException("connectRemoteDevice failed", a3);
            }
            throw new OtaException("aborted, connectRemoteDevice failed", a2);
        }
        throw new OtaException("user aborted", DfuException.ERROR_DFU_ABORTED);
    }

    @Override // com.realsil.sdk.dfu.internal.base.BaseDfuTask
    public void executeOtaProcedure() {
        int innerCheck;
        super.executeOtaProcedure();
        try {
            setName("ProcessorXG0000N");
            ZLogger.d("ProcessorXG0000N running.");
            innerCheck = innerCheck();
        } catch (Exception e) {
            ZLogger.e(e.toString());
            f(0);
        }
        if (innerCheck != 0) {
            f(innerCheck);
            return;
        }
        F();
        closeInputStream(this.mCurBinInputStream);
        if (this.DBG) {
            ZLogger.d("ProcessorXG0000N stopped");
        }
        if (this.mProcessState == 525) {
            notifyStateChanged(259);
        }
    }

    public final void i(int i) throws DfuException {
        int i2 = this.mImageUpdateOffset;
        if (i2 == 0) {
            this.mImageUpdateOffset = 12;
            if (this.DBG) {
                ZLogger.v(String.format(Locale.US, "First Packet, mImageUpdateOffset=0x%08X(%d)", 12, Integer.valueOf(this.mImageUpdateOffset)));
            }
        } else if (this.DBG) {
            ZLogger.v(String.format(Locale.US, "mImageUpdateOffset=0x%08X(%d)", Integer.valueOf(i2), Integer.valueOf(this.mImageUpdateOffset)));
        }
        a(i, this.mImageUpdateOffset);
        if (this.mImageUpdateOffset == getDfuProgressInfo().getBytesSent() + 12 || this.mImageUpdateOffset == -1) {
            return;
        }
        if (this.DBG) {
            ZLogger.d(String.format(Locale.US, "mBytesSent(%d) != mImageUpdateOffset(%d), reload image bin file", Integer.valueOf(getDfuProgressInfo().getBytesSent() + 12), Integer.valueOf(this.mImageUpdateOffset)));
        }
        this.imageFileLoaded = false;
        k();
        alignmentSendBytes(this.mImageUpdateOffset, false);
    }

    public final void j(int i) throws DfuException {
        int i2 = this.mImageUpdateOffset;
        if (i2 == 0) {
            a(i, 12);
        } else {
            a(i, i2);
        }
        if (getDfuProgressInfo().getBytesSent() != this.mImageUpdateOffset) {
            ZLogger.d("mBytesSent != mImageUpdateOffset, reload image bin file");
            this.imageFileLoaded = false;
            k();
            alignmentSendBytes(this.mImageUpdateOffset, false);
        }
        if (this.DBG) {
            ZLogger.v(getDfuProgressInfo().toString());
        }
    }

    public final void d(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, BaseBinInputStream baseBinInputStream) throws DfuException {
        int read;
        ZLogger.v("uploadFirmwareImageForBeeUpdate");
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
                    if (this.mImageUpdateOffset == 0) {
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
                            ZLogger.i("image file has already been send over");
                            return;
                        }
                        ZLogger.e("Error while reading file with size: " + i3);
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
}
