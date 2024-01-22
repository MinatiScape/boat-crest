package com.realsil.sdk.dfu.p;

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
import com.realsil.sdk.dfu.model.ConnectionParameters;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.realsil.sdk.dfu.model.OtaDeviceInfo;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Locale;
import kotlin.UShort;
/* loaded from: classes12.dex */
public final class d extends c {
    public d(Context context, DfuConfig dfuConfig, DfuThreadCallback dfuThreadCallback) {
        super(context, dfuConfig, dfuThreadCallback);
    }

    public final int B() {
        if (this.P == null) {
            ZLogger.w("OTA SERVICE not found:" + this.O.toString());
            return DfuException.ERROR_NO_SERVICE_FOUND_OR_LOSS;
        } else if (this.Q == null) {
            ZLogger.w("not found OTA_CONTROL_ENTER_OTA_MODE_CHARACTERISTIC : " + f.f13640a.toString());
            return DfuException.ERROR_NO_CHARACTERISTIC_FOUND_OR_LOSS;
        } else {
            return 0;
        }
    }

    public final boolean C() throws DfuException {
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
                F();
            } else {
                j();
            }
            if (this.mCurBinInputStream == null) {
                e(4097);
                return false;
            }
        } else {
            e(this.mOtaDeviceAddress);
            int B = B();
            if (B == 0) {
                if (!this.otaEnvironmentPrepared) {
                    F();
                } else {
                    j();
                }
                if (this.mCurBinInputStream == null) {
                    e(4097);
                    return false;
                } else if (!D()) {
                    e(DfuException.ERROR_ENTER_OTA_MODE_FAILED);
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
                throw new OtaException("load ota service failed", B);
            }
        }
        r();
        this.otaModeEnabled = true;
        return true;
    }

    public final boolean D() throws DfuException {
        boolean z;
        if (!this.mAborted) {
            if (this.Q == null) {
                return false;
            }
            notifyStateChanged(518);
            ZLogger.d("<< OPCODE_ENTER_OTA_MODE(0x01), enable device to enter OTA mode");
            try {
                z = a(this.Q, f.e, false);
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
            if (z && getDfuConfig().isWaitDisconnectWhenEnterOtaMode()) {
                waitUntilDisconnected();
            }
            a(this.H);
            return z;
        }
        throw new OtaException("user aborted", DfuException.ERROR_DFU_ABORTED);
    }

    public final boolean E() {
        boolean z = false;
        while (a()) {
            try {
            } catch (DfuException e) {
                ZLogger.w(DfuConstants.parseOtaState(this.mProcessState) + ", " + e.toString());
                int errCode = e.getErrCode();
                if (errCode == 4128) {
                    a(errCode, true);
                } else if (errCode != 4097 && errCode != 265) {
                    if (z() && this.mOtaWorkMode == 0) {
                        waitUntilDisconnected();
                    }
                    a(errCode, false);
                } else {
                    a(errCode, false);
                }
            }
            if (!C() || !G()) {
                return false;
            }
            this.mBytesSentBuffer += getDfuProgressInfo().getBytesSent();
            if (getDfuProgressInfo().isLastImageFile()) {
                ZLogger.d("no pendding image file to upload.");
                a(getDfuProgressInfo().getCurImageId(), (byte) 1);
                getDfuProgressInfo().setActiveImageSize(this.mBytesSentBuffer);
                if (!this.o) {
                    notifyStateChanged(DfuConstants.PROGRESS_PENDING_ACTIVE_IMAGE);
                } else {
                    s();
                    notifyStateChanged(258);
                }
                z = true;
            } else {
                ZLogger.d("has pendding image file to upload");
                if (getOtaDeviceInfo().getUpdateMechanism() == 1) {
                    a(getDfuProgressInfo().getCurImageId(), (byte) 1);
                    this.mOtaDeviceAddress = this.mDeviceAddress;
                    this.otaModeEnabled = false;
                    this.mBytesSentBuffer = 0;
                    s();
                    h();
                } else if (getOtaDeviceInfo().getUpdateMechanism() == 3) {
                    BaseBinInputStream baseBinInputStream = this.mNextBinInputStream;
                    if (baseBinInputStream != null) {
                        BaseBinInputStream baseBinInputStream2 = this.mCurBinInputStream;
                        if (baseBinInputStream2 != null) {
                            int i = baseBinInputStream2.otaTempBufferCheckOrder;
                            int i2 = baseBinInputStream.otaTempBufferCheckOrder;
                            if (i != i2 && i2 == 0) {
                                a(getDfuProgressInfo().getCurImageId(), (byte) 1);
                                ZLogger.d("make device to enter the ota advertiser mode, and let the app continue update image");
                                this.mOtaDeviceAddress = null;
                                this.otaModeEnabled = true;
                                this.mBytesSentBuffer = 0;
                                a((byte) 1);
                                h();
                            }
                        }
                        if (a(baseBinInputStream, this.mNextBinIndex, this.mBytesSentBuffer, getOtaDeviceInfo().otaTempBufferSize * 4096)) {
                            ZLogger.d("make device to enter the ota advertiser mode, and let the app continue update image");
                            a(getDfuProgressInfo().getCurImageId(), (byte) 1);
                            this.mOtaDeviceAddress = null;
                            this.otaModeEnabled = true;
                            this.mBytesSentBuffer = 0;
                            a((byte) 1);
                            h();
                        } else {
                            a(getDfuProgressInfo().getCurImageId(), (byte) 0);
                        }
                    } else {
                        a(getDfuProgressInfo().getCurImageId(), (byte) 0);
                    }
                } else {
                    a(getDfuProgressInfo().getCurImageId(), (byte) 0);
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

    public final void F() throws DfuException {
        this.otaEnvironmentPrepared = false;
        notifyStateChanged(517);
        try {
            Thread.sleep(1500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.mOtaDeviceInfo = new OtaDeviceInfo(20, 2);
        v();
        w();
        u();
        x();
        if (this.DBG) {
            ZLogger.d(getOtaDeviceInfo().toString());
        }
        k();
        this.otaEnvironmentPrepared = true;
        ZLogger.d("Ota Environment prepared.");
    }

    public final boolean G() throws DfuException {
        if (!a()) {
            a(DfuException.ERROR_DFU_ABORTED, true);
            return false;
        } else if (getOtaDeviceInfo().isAesEncryptEnabled() && !b()) {
            e(4113);
            return false;
        } else {
            notifyStateChanged(DfuConstants.PROGRESS_START_DFU_PROCESS);
            ZLogger.d(String.format("OtaWorkMode=0x%04X, ICType=%2X", Integer.valueOf(this.mOtaWorkMode), Integer.valueOf(getOtaDeviceInfo().icType)));
            if (this.DBG) {
                ZLogger.v(getDfuProgressInfo().toString());
            }
            a(this.H, this.W, true);
            if (Build.VERSION.SDK_INT >= 23) {
                a(this.H, 256);
            }
            y();
            H();
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
                ZLogger.v("Last send reach the bottom");
            } else {
                f(getDfuProgressInfo().getCurImageId());
                if (this.r == 1) {
                    b(this.H, this.X, this.mCurBinInputStream);
                } else {
                    a(this.H, this.X, this.mCurBinInputStream);
                }
            }
            getDfuProgressInfo().sendOver();
            return true;
        }
    }

    public final void H() throws DfuException {
        if (!this.mAborted) {
            ZLogger.d("<< OPCODE_DFU_CONNECTION_PARAMETER_UPDATE(0x07)");
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
                ZLogger.d(this.DBG, "... waiting OPCODE_DFU_CONNECTION_PARAMETER_UPDATE(0x07) response");
                o();
                return;
            } catch (DfuException e) {
                ZLogger.w("ignore connection parameters update exception: " + e.getMessage());
                this.mErrorState = 0;
                return;
            }
        }
        throw new OtaException("user aborted", DfuException.ERROR_DFU_ABORTED);
    }

    @Override // com.realsil.sdk.dfu.internal.base.BaseDfuTask
    public boolean activeImage(boolean z) {
        if (super.activeImage(z)) {
            if (this.mConnectionState != 515) {
                boolean z2 = this.DBG;
                ZLogger.d(z2, "start to re-connect the RCU which going to active image, current state is: " + this.mConnectionState);
                int a2 = a(this.mOtaDeviceAddress, getDfuConfig().getRetransConnectTimes());
                if (a2 != 0) {
                    ZLogger.e(String.format("Something error in OTA process, errorCode: 0x%04X, mProcessState=0x%04X", Integer.valueOf(a2), Integer.valueOf(this.mProcessState)));
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
                    e(e.getErrCode());
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
                a(m());
                if (!this.mAborted) {
                    int a3 = a(str, getDfuConfig().getRetransConnectTimes());
                    if (a3 == 0) {
                        return;
                    }
                    if (a3 == 4128) {
                        throw new OtaException("aborted, connectRemoteDevice failed", a3);
                    }
                    throw new OtaException("connectRemoteDevice failed", a3);
                }
                throw new OtaException("user aborted", DfuException.ERROR_DFU_ABORTED);
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
            setName("ProcessorXG0010N");
            ZLogger.d("ProcessorXG0010N running.");
            innerCheck = innerCheck();
        } catch (Exception e) {
            e.printStackTrace();
            ZLogger.e(e.toString());
            e(0);
        }
        if (innerCheck != 0) {
            e(innerCheck);
            return;
        }
        notifyStateChanged(514);
        this.mOtaDeviceAddress = this.mDeviceAddress;
        this.otaModeEnabled = false;
        E();
        closeInputStream(this.mCurBinInputStream);
        ZLogger.d(this.DBG, "ProcessorXG0010N stopped");
        if (this.mProcessState == 525) {
            notifyStateChanged(259);
        }
    }

    public final void g(int i) throws DfuException {
        int i2;
        if (!this.mAborted) {
            ZLogger.d(this.DBG, "<< OPCODE_DFU_REPORT_TARGET_IMAGE_INFO(0x06)");
            a(this.W, new byte[]{6, (byte) (i & 255), (byte) ((i >> 8) & 255)}, false);
            ZLogger.d(this.DBG, "... Reading OPCODE_DFU_REPORT_TARGET_IMAGE_INFO(0x06) notification");
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
            ZLogger.e(String.format("0x%04X, Get target image info failed", Integer.valueOf((int) DfuException.ERROR_OPCODE_RESPONSE_NOT_SUPPORTED)));
            throw new OtaException("Get target image info failed", DfuException.ERROR_OPCODE_RESPONSE_NOT_SUPPORTED);
        }
        throw new OtaException("user aborted", DfuException.ERROR_DFU_ABORTED);
    }
}
