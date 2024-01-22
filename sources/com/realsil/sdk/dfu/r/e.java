package com.realsil.sdk.dfu.r;

import android.content.Context;
import android.os.Build;
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
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Locale;
import kotlin.UShort;
/* loaded from: classes12.dex */
public class e extends c {
    public e(Context context, DfuConfig dfuConfig, DfuThreadCallback dfuThreadCallback) {
        super(context, dfuConfig, dfuThreadCallback);
    }

    public final boolean B() throws DfuException {
        d(this.mOtaDeviceAddress);
        r();
        if (!this.otaEnvironmentPrepared) {
            D();
        } else {
            j();
        }
        if (this.mCurBinInputStream == null) {
            e(4097);
            return false;
        }
        return true;
    }

    public final boolean C() {
        while (a()) {
            try {
                if (!B() || !E()) {
                    return false;
                }
                this.mBytesSentBuffer += getDfuProgressInfo().getBytesSent();
                if (getDfuProgressInfo().isLastImageFile()) {
                    ZLogger.d("no pendding image file to upload.");
                    a(getDfuProgressInfo().getCurImageId(), (byte) 1);
                    getDfuProgressInfo().setActiveImageSize(this.mBytesSentBuffer);
                    return true;
                }
                ZLogger.d(this.DBG, "has pendding image file to upload");
                if (getOtaDeviceInfo().getUpdateMechanism() == 1) {
                    a(getDfuProgressInfo().getCurImageId(), (byte) 1);
                    this.mOtaDeviceAddress = this.mDeviceAddress;
                    this.otaModeEnabled = true;
                    this.mBytesSentBuffer = 0;
                    s();
                    h();
                } else if (getOtaDeviceInfo().getUpdateMechanism() == 3) {
                    BaseBinInputStream baseBinInputStream = this.mNextBinInputStream;
                    if (baseBinInputStream != null) {
                        if (a(baseBinInputStream, this.mBytesSentBuffer, getOtaDeviceInfo().otaTempBufferSize * 4096)) {
                            a(getDfuProgressInfo().getCurImageId(), (byte) 1);
                            ZLogger.d("make device to enter the ota advertiser mode, and let the app continue update image");
                            this.otaModeEnabled = true;
                            this.mBytesSentBuffer = 0;
                            a((byte) 1);
                            h();
                        } else {
                            a(getDfuProgressInfo().getCurImageId(), (byte) 0);
                        }
                    } else {
                        a(getDfuProgressInfo().getCurImageId(), (byte) 1);
                    }
                } else {
                    a(getDfuProgressInfo().getCurImageId(), (byte) 0);
                }
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (DfuException e2) {
                ZLogger.w(DfuConstants.parseOtaState(this.mProcessState) + ", " + e2.toString());
                int errCode = e2.getErrCode();
                if (errCode == 4128) {
                    a(errCode, true);
                } else if (errCode != 4097 && errCode != 265) {
                    z();
                    a(errCode, false);
                } else {
                    a(errCode, false);
                }
                return false;
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
        this.mOtaDeviceInfo = new OtaDeviceInfo(16, 2);
        v();
        if (getOtaDeviceInfo().isRwsEnabled() && getOtaDeviceInfo().getRwsUpdateFlag() != 0) {
            throw new OtaException("rws state not ready", DfuException.ERROR_DFU_SPP_RWS_NOT_READY);
        }
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

    public final boolean E() throws DfuException {
        if (!a()) {
            a(DfuException.ERROR_DFU_ABORTED, true);
            return false;
        } else if (getOtaDeviceInfo().isAesEncryptEnabled() && !b()) {
            e(4113);
            return false;
        } else {
            notifyStateChanged(DfuConstants.PROGRESS_START_DFU_PROCESS);
            ZLogger.d(String.format("mOtaWorkMode=0x%04X, ICType=%2X", Integer.valueOf(this.mOtaWorkMode), Integer.valueOf(getOtaDeviceInfo().icType)));
            if (this.DBG) {
                ZLogger.v(getDfuProgressInfo().toString());
            }
            a(this.H, this.W, true);
            if (Build.VERSION.SDK_INT >= 23) {
                a(this.H, 256);
            }
            y();
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

    /* JADX WARN: Code restructure failed: missing block: B:33:0x00d5, code lost:
        if (r1.equals(r6.mDeviceAddress) != false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x011a, code lost:
        if (r1.equals(r0.getAddress()) != false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x011c, code lost:
        r1 = true;
     */
    @Override // com.realsil.sdk.dfu.m.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.realsil.sdk.core.bluetooth.scanner.ExtendedBluetoothDevice r7) {
        /*
            Method dump skipped, instructions count: 486
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.dfu.r.e.a(com.realsil.sdk.core.bluetooth.scanner.ExtendedBluetoothDevice):void");
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
                z();
                a(274, false);
            }
            return true;
        }
        return false;
    }

    public void d(ScannerParams scannerParams) throws DfuException {
        if (!this.mAborted) {
            notifyStateChanged(527);
            this.mErrorState = 0;
            this.B = false;
            b(scannerParams);
            try {
                synchronized (this.A) {
                    if (this.mErrorState == 0 && !this.B) {
                        this.A.wait(31000L);
                    }
                }
            } catch (InterruptedException e) {
                ZLogger.e("findRemoteDevice interrupted, e = " + e.toString());
                this.mErrorState = 259;
            }
            if (this.mErrorState == 0 && !this.B) {
                ZLogger.w("didn't find the secondary bud device");
                this.mErrorState = DfuException.ERROR_CANNOT_FIND_DEVICE;
            }
            if (this.mErrorState != 0) {
                throw new OtaException("Error while scan remote device", this.mErrorState);
            }
            return;
        }
        throw new OtaException("user aborted", DfuException.ERROR_DFU_ABORTED);
    }

    @Override // com.realsil.sdk.dfu.internal.base.BaseDfuTask
    public void executeOtaProcedure() {
        int innerCheck;
        super.executeOtaProcedure();
        try {
            setName("ProcessorXG0010S");
            ZLogger.d("ProcessorXG0010S running.");
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
        this.otaModeEnabled = true;
        if (C()) {
            if (getOtaDeviceInfo().isRwsEnabled()) {
                s();
                notifyStateChanged(DfuConstants.PROGRESS_HAND_OVER_PROCESSING);
                ZLogger.d("wait master to handover ...");
                ArrayList arrayList = new ArrayList();
                arrayList.add(new CompatScanFilter.Builder().setManufacturerData(getDfuConfig().getManufacturerId(), getOtaDeviceInfo().getRwsBdAddr()).build());
                ScannerParams m = m();
                m.setScanFilters(arrayList);
                d(m);
                ZLogger.d("start to upload secondary bud ...");
                this.otaModeEnabled = true;
                this.otaEnvironmentPrepared = false;
                this.mBytesSentBuffer = 0;
                if (C()) {
                    if (!this.o) {
                        notifyStateChanged(DfuConstants.PROGRESS_PENDING_ACTIVE_IMAGE);
                    } else {
                        s();
                        notifyStateChanged(258);
                    }
                }
            } else if (!this.o) {
                notifyStateChanged(DfuConstants.PROGRESS_PENDING_ACTIVE_IMAGE);
            } else {
                s();
                notifyStateChanged(258);
            }
        }
        closeInputStream(this.mCurBinInputStream);
        ZLogger.d(this.DBG, "ProcessorXG0010S stopped");
        onDestroy();
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

    public final void d(String str) throws DfuException {
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
}
