package com.realsil.sdk.dfu.o;

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

    public final boolean A() throws DfuException {
        if (this.otaModeEnabled) {
            if (TextUtils.isEmpty(this.mOtaDeviceAddress)) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new CompatScanFilter.Builder().setManufacturerData(getDfuConfig().getManufacturerId(), getOtaDeviceInfo().getDeviceMac()).build());
                ScannerParams m = m();
                m.setScanFilters(arrayList);
                a(m, 31000L);
            }
            d(this.mOtaDeviceAddress);
            if (!this.otaEnvironmentPrepared) {
                D();
            } else {
                j();
            }
            if (this.mCurBinInputStream == null) {
                f(4097);
                return false;
            }
        } else {
            e(this.mOtaDeviceAddress);
            int z = z();
            if (z == 0) {
                if (!this.otaEnvironmentPrepared) {
                    D();
                } else {
                    j();
                }
                if (this.mCurBinInputStream == null) {
                    f(4097);
                    return false;
                } else if (!B()) {
                    f(DfuException.ERROR_ENTER_OTA_MODE_FAILED);
                    return false;
                } else {
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(new CompatScanFilter.Builder().setManufacturerData(getDfuConfig().getManufacturerId(), getOtaDeviceInfo().getDeviceMac()).build());
                    ScannerParams m2 = m();
                    m2.setScanFilters(arrayList2);
                    a(m2, 31000L);
                    d(this.mOtaDeviceAddress);
                }
            } else {
                throw new OtaException("load ota service failed", z);
            }
        }
        r();
        this.otaModeEnabled = true;
        return true;
    }

    public final boolean B() throws DfuException {
        boolean z;
        if (!this.mAborted) {
            if (this.Q == null) {
                return false;
            }
            notifyStateChanged(518);
            ZLogger.d("<< OPCODE_ENTER_OTA_MODE(0x01), enable device to enter OTA mode");
            try {
                z = a(this.Q, g.i, false);
            } catch (DfuException e) {
                boolean z2 = e.getErrCode() != 267;
                ZLogger.w("<< OPCODE_ENTER_OTA_MODE(0x01) failed, ignore it :" + e.getMessage());
                this.mErrorState = 0;
                z = z2;
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            if (getDfuConfig().isWaitDisconnectWhenEnterOtaMode()) {
                waitUntilDisconnected();
            }
            a(this.H);
            return z;
        }
        throw new OtaException("user aborted", DfuException.ERROR_DFU_ABORTED);
    }

    public final boolean C() {
        BaseBinInputStream baseBinInputStream;
        notifyStateChanged(514);
        this.mOtaDeviceAddress = this.mDeviceAddress;
        this.otaModeEnabled = false;
        boolean z = false;
        while (a()) {
            try {
            } catch (DfuException e) {
                ZLogger.w(DfuConstants.parseOtaState(this.mProcessState) + ", " + e.toString());
                int errorNumber = e.getErrorNumber();
                if (errorNumber == 4128) {
                    a(errorNumber, true);
                } else if (errorNumber != 4097 && errorNumber != 265) {
                    if (w()) {
                        waitUntilDisconnected();
                    }
                    a(errorNumber, false);
                } else {
                    a(errorNumber, false);
                }
            }
            if (!A() || !F()) {
                return false;
            }
            this.mBytesSentBuffer += getDfuProgressInfo().getBytesSent();
            if (getDfuProgressInfo().isLastImageFile()) {
                ZLogger.v("no pendding image file to upload.");
                getDfuProgressInfo().setActiveImageSize(this.mBytesSentBuffer);
                if (!this.o) {
                    notifyStateChanged(DfuConstants.PROGRESS_PENDING_ACTIVE_IMAGE);
                } else {
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
                    s();
                    h();
                } else if (getOtaDeviceInfo().getUpdateMechanism() == 3 && (baseBinInputStream = this.mNextBinInputStream) != null && a(baseBinInputStream, this.mBytesSentBuffer, getOtaDeviceInfo().otaTempBufferSize * 4096)) {
                    ZLogger.d("make device to enter the ota advertiser mode, and let the app continue update image");
                    this.mOtaDeviceAddress = null;
                    this.otaModeEnabled = true;
                    this.mBytesSentBuffer = 0;
                    a((byte) 1);
                    h();
                }
            }
            try {
                Thread.sleep(1000L);
                continue;
            } catch (InterruptedException e2) {
                e2.printStackTrace();
                continue;
            }
            if (z) {
                return z;
            }
        }
        a(DfuException.ERROR_DFU_ABORTED, true);
        return false;
    }

    public final void D() throws DfuException {
        this.otaEnvironmentPrepared = false;
        notifyStateChanged(517);
        try {
            Thread.sleep(1500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.mOtaDeviceInfo = new OtaDeviceInfo(this.h, 2);
        E();
        v();
        u();
        if (this.DBG) {
            ZLogger.d(getOtaDeviceInfo().toString());
        }
        k();
        this.otaEnvironmentPrepared = true;
        ZLogger.d("Ota Environment prepared.");
    }

    public final boolean E() throws DfuException {
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

    public final boolean F() throws DfuException {
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
            if (!getOtaDeviceInfo().isBufferCheckEnabled()) {
                this.r = 0;
            } else {
                this.r = t();
            }
            if (this.VDBG) {
                ZLogger.v("mRemoteOtaFunctionInfo=" + this.r);
            }
            y();
            getDfuProgressInfo().start();
            e(10131);
            g(getDfuProgressInfo().getCurImageId());
            if (!getDfuConfig().isBreakpointResumeEnabled()) {
                this.mImageUpdateOffset = 0;
                ZLogger.d(String.format(Locale.US, "BreakpointResume disable: mImageUpdateOffset=0x%08X(%d)", 0, Integer.valueOf(this.mImageUpdateOffset)));
            }
            if (this.mImageUpdateOffset == 0) {
                x();
            }
            if (this.mImageUpdateOffset - 12 >= getDfuProgressInfo().getImageSizeInBytes()) {
                ZLogger.v(this.VDBG, "Last send reach the bottom");
            } else {
                i(getDfuProgressInfo().getCurImageId());
                if (this.r == 1) {
                    a(this.H, this.X, this.mCurBinInputStream);
                } else {
                    b(this.H, this.X, this.mCurBinInputStream);
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
                    ZLogger.w("Something error in OTA process, errorCode: " + a2 + "mProcessState" + this.mProcessState);
                    a(a2, true);
                    return false;
                }
            }
            if (z) {
                try {
                    s();
                    notifyStateChanged(258);
                } catch (DfuException e) {
                    e.printStackTrace();
                    f(e.getErrCode());
                }
            } else {
                if (w()) {
                    waitUntilDisconnected();
                }
                a(274, false);
            }
            return true;
        }
        return false;
    }

    public final void b(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, BaseBinInputStream baseBinInputStream) throws DfuException {
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
            setName("ProcessorX0013N");
            ZLogger.d("ProcessorX0013N running.");
            innerCheck = innerCheck();
        } catch (Exception e) {
            ZLogger.w(e.toString());
            f(0);
        }
        if (innerCheck != 0) {
            f(innerCheck);
            return;
        }
        C();
        closeInputStream(this.mCurBinInputStream);
        if (this.DBG) {
            ZLogger.d("ProcessorX0013N stopped");
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
        b(i, this.mImageUpdateOffset);
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

    public final int z() {
        if (this.P == null) {
            ZLogger.w("OTA SERVICE not found:" + this.O.toString());
            return DfuException.ERROR_NO_SERVICE_FOUND_OR_LOSS;
        } else if (this.Q == null) {
            ZLogger.w("not found OTA_CONTROL_ENTER_OTA_MODE_CHARACTERISTIC : " + g.f13638a.toString());
            return DfuException.ERROR_NO_CHARACTERISTIC_FOUND_OR_LOSS;
        } else {
            return 0;
        }
    }
}
