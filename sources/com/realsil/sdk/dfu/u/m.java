package com.realsil.sdk.dfu.u;

import android.content.Context;
import android.util.SparseIntArray;
import com.realsil.sdk.core.bluetooth.BluetoothProfileManager;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.DfuConstants;
import com.realsil.sdk.dfu.DfuException;
import com.realsil.sdk.dfu.exception.LoadFileException;
import com.realsil.sdk.dfu.exception.OtaException;
import com.realsil.sdk.dfu.image.FirmwareLoaderX;
import com.realsil.sdk.dfu.image.LoadParams;
import com.realsil.sdk.dfu.image.stream.BaseBinInputStream;
import com.realsil.sdk.dfu.internal.base.DfuThreadCallback;
import com.realsil.sdk.dfu.model.BinInfo;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.realsil.sdk.dfu.model.OtaDeviceInfo;
import com.realsil.sdk.dfu.params.QcConfig;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
/* loaded from: classes12.dex */
public class m extends l {
    public QcConfig M;
    public int N;
    public boolean O;
    public SparseIntArray P;
    public int Q;

    public m(Context context, DfuConfig dfuConfig, QcConfig qcConfig, DfuThreadCallback dfuThreadCallback) {
        super(context, dfuConfig, dfuThreadCallback);
        this.Q = 0;
        this.M = qcConfig;
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x0128, code lost:
        if (r1 == false) goto L2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0165, code lost:
        D();
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0169, code lost:
        com.realsil.sdk.core.logger.ZLogger.v("processForceCopyProcedure failed");
     */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00d8 A[Catch: DfuException -> 0x012b, TRY_ENTER, TryCatch #2 {DfuException -> 0x012b, blocks: (B:8:0x000f, B:11:0x0016, B:13:0x0022, B:15:0x002b, B:17:0x002f, B:42:0x00d8, B:44:0x00de, B:46:0x00e8, B:48:0x00ee, B:50:0x00fa, B:52:0x0100, B:54:0x010b, B:55:0x0113, B:37:0x00ba), top: B:84:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x011a A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean A() {
        /*
            Method dump skipped, instructions count: 367
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.dfu.u.m.A():boolean");
    }

    public final void B() throws DfuException {
        this.otaEnvironmentPrepared = false;
        if (!a()) {
            e(DfuException.ERROR_DFU_ABORTED);
            return;
        }
        notifyStateChanged(517);
        try {
            Thread.sleep(1500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        QcConfig qcConfig = this.M;
        if (qcConfig != null) {
            try {
                a((short) 1554, new byte[]{(byte) (qcConfig.getIndicator() & 255)});
            } catch (Exception unused) {
                ZLogger.v("CMD_OTA_TEST error, ignore");
            }
        }
        this.mOtaDeviceInfo = new OtaDeviceInfo(17, 2);
        v();
        if (getOtaDeviceInfo().isRwsEnabled()) {
            if (getOtaDeviceInfo().specVersion >= 6) {
                if (this.O && getOtaDeviceInfo().getBudRole() != this.N) {
                    throw new OtaException(String.format("role swap failed, bud role(%d) din't change, target is %d", Integer.valueOf(getOtaDeviceInfo().getBudRole()), Integer.valueOf(this.N)), 283);
                }
            } else {
                z();
                if (getOtaDeviceInfo().getRwsUpdateFlag() != 0) {
                    throw new OtaException("rws state not ready", DfuException.ERROR_DFU_SPP_RWS_NOT_READY);
                }
            }
        }
        s();
        if (getOtaDeviceInfo().isBankEnabled() && getOtaDeviceInfo().specVersion >= 5) {
            u();
        }
        if (getOtaDeviceInfo().getUpdateMechanism() == 3) {
            t();
        }
        if (this.DBG) {
            ZLogger.d(getOtaDeviceInfo().toString());
        }
        k();
        this.otaEnvironmentPrepared = true;
        ZLogger.d("Ota Environment prepared.");
    }

    public final int C() throws DfuException {
        BaseBinInputStream baseBinInputStream;
        notifyStateChanged(DfuConstants.PROGRESS_START_DFU_PROCESS);
        getDfuProgressInfo().start();
        if (this.DBG) {
            ZLogger.v("processCopyProcedure ...");
            ZLogger.v(getDfuProgressInfo().toString());
        }
        try {
            c(getDfuProgressInfo().getCurImageId());
            getDfuProgressInfo().sendOver();
            notifyProcessChanged();
            this.mBytesSentBuffer += getDfuProgressInfo().getBytesSent();
            if (getDfuProgressInfo().isLastImageFile()) {
                ZLogger.v(this.VDBG, "no pendding image file to upload");
                getDfuProgressInfo().setActiveImageSize(this.mBytesSentBuffer);
                return 3;
            }
            ZLogger.d("has pendding image file to upload");
            if (getOtaDeviceInfo().getUpdateMechanism() == 1) {
                this.mOtaDeviceAddress = this.mDeviceAddress;
                this.otaModeEnabled = true;
                this.mBytesSentBuffer = 0;
                a(true);
                int activeImageSize = getDfuProgressInfo().getActiveImageSize() / 30;
                a(30000L);
            } else if (getOtaDeviceInfo().getUpdateMechanism() == 3 && (baseBinInputStream = this.mNextBinInputStream) != null && a(baseBinInputStream, this.mBytesSentBuffer, getOtaDeviceInfo().otaTempBufferSize * 4096)) {
                ZLogger.d("make device to enter the ota advertiser mode, and let the app continue update image");
                this.otaModeEnabled = true;
                this.mBytesSentBuffer = 0;
                a((byte) 1, true);
                int activeImageSize2 = getDfuProgressInfo().getActiveImageSize() / 30;
                a(30000L);
            }
            return 2;
        } catch (DfuException unused) {
            return 1;
        }
    }

    public final void D() throws DfuException {
        SparseIntArray sparseIntArray = this.P;
        if (sparseIntArray != null && sparseIntArray.size() > 0) {
            for (int i = 0; i < this.P.size(); i++) {
                int keyAt = this.P.keyAt(i);
                ZLogger.v(String.format(Locale.US, "force copy data image:%d, imageId=0x%02X, value=0x%02X", Integer.valueOf(i), Integer.valueOf(keyAt), Integer.valueOf(this.P.valueAt(i))));
                c(keyAt);
            }
            return;
        }
        ZLogger.v("no force copy images exist");
    }

    public final int E() throws DfuException {
        if (G()) {
            this.mBytesSentBuffer += getDfuProgressInfo().getBytesSent();
            if (getDfuProgressInfo().isLastImageFile()) {
                ZLogger.d("no pendding image file to upload.");
                if (getOtaDeviceInfo().specVersion >= 3) {
                    a(getDfuProgressInfo().getCurImageId(), (byte) 1);
                } else {
                    a(getDfuProgressInfo().getCurImageId(), (byte) 0);
                }
                getDfuProgressInfo().setActiveImageSize(this.mBytesSentBuffer);
                return 3;
            }
            ZLogger.d("has pendding image file to upload");
            if (getOtaDeviceInfo().getUpdateMechanism() == 1) {
                if (getOtaDeviceInfo().specVersion >= 3) {
                    a(getDfuProgressInfo().getCurImageId(), (byte) 1);
                } else {
                    a(getDfuProgressInfo().getCurImageId(), (byte) 0);
                }
                this.mOtaDeviceAddress = this.mDeviceAddress;
                this.otaModeEnabled = true;
                this.mBytesSentBuffer = 0;
                a(true);
                int activeImageSize = getDfuProgressInfo().getActiveImageSize() / 30;
                a(30000L);
                return 2;
            } else if (getOtaDeviceInfo().getUpdateMechanism() == 3) {
                BaseBinInputStream baseBinInputStream = this.mNextBinInputStream;
                if (baseBinInputStream != null) {
                    if (a(baseBinInputStream, this.mBytesSentBuffer, getOtaDeviceInfo().otaTempBufferSize * 4096)) {
                        if (getOtaDeviceInfo().specVersion >= 3) {
                            a(getDfuProgressInfo().getCurImageId(), (byte) 1);
                        } else {
                            a(getDfuProgressInfo().getCurImageId(), (byte) 0);
                        }
                        ZLogger.d("make device to enter the ota advertiser mode, and let the app continue update image");
                        this.otaModeEnabled = true;
                        this.mBytesSentBuffer = 0;
                        a((byte) 1, true);
                        int activeImageSize2 = getDfuProgressInfo().getActiveImageSize() / 30;
                        a(30000L);
                        return 2;
                    }
                    a(getDfuProgressInfo().getCurImageId(), (byte) 0);
                    return 2;
                } else if (getOtaDeviceInfo().specVersion >= 3) {
                    a(getDfuProgressInfo().getCurImageId(), (byte) 1);
                    return 2;
                } else {
                    a(getDfuProgressInfo().getCurImageId(), (byte) 0);
                    return 2;
                }
            } else {
                a(getDfuProgressInfo().getCurImageId(), (byte) 0);
                return 2;
            }
        }
        return 0;
    }

    public final int F() throws DfuException {
        if (!a()) {
            e(DfuException.ERROR_DFU_ABORTED);
            return 0;
        }
        notifyStateChanged(DfuConstants.PROGRESS_START_DFU_PROCESS);
        ZLogger.d(String.format("mOtaWorkMode=0x%04X, ICType=%2X", Integer.valueOf(this.mOtaWorkMode), Integer.valueOf(getOtaDeviceInfo().icType)));
        ZLogger.v(getDfuProgressInfo().toString());
        getDfuProgressInfo().start();
        f(getDfuProgressInfo().getCurImageId());
        if (!getDfuConfig().isBreakpointResumeEnabled()) {
            this.mImageUpdateOffset = 0;
        }
        ZLogger.v(this.DBG, String.format(Locale.US, "mImageUpdateOffset=0x%08X(%d)", Integer.valueOf(this.mImageUpdateOffset), Integer.valueOf(this.mImageUpdateOffset)));
        if (this.mImageUpdateOffset == 0) {
            x();
        }
        if (this.mImageUpdateOffset >= getDfuProgressInfo().getImageSizeInBytes()) {
            ZLogger.d(this.DBG, "Last send reach the bottom");
        } else {
            i(getDfuProgressInfo().getCurImageId());
        }
        getDfuProgressInfo().sendOver();
        notifyProcessChanged();
        this.mBytesSentBuffer += getDfuProgressInfo().getBytesSent();
        if (getDfuProgressInfo().isLastImageFile()) {
            ZLogger.d("no pendding image file to upload,");
            try {
                a(getDfuProgressInfo().getCurImageId(), (byte) 1);
                getDfuProgressInfo().setActiveImageSize(this.mBytesSentBuffer);
                return 3;
            } catch (DfuException unused) {
                getDfuProgressInfo().setNextFileIndex(getDfuProgressInfo().getLastFileIndex());
                return 1;
            }
        }
        ZLogger.d("has pendding image file to upload");
        if (getOtaDeviceInfo().getUpdateMechanism() == 1) {
            try {
                a(getDfuProgressInfo().getCurImageId(), (byte) 1);
                this.mOtaDeviceAddress = this.mDeviceAddress;
                this.otaModeEnabled = true;
                this.mBytesSentBuffer = 0;
                a(true);
                int activeImageSize = getDfuProgressInfo().getActiveImageSize() / 30;
                a(30000L);
            } catch (DfuException unused2) {
                getDfuProgressInfo().setNextFileIndex(getDfuProgressInfo().getLastFileIndex());
                return 1;
            }
        } else if (getOtaDeviceInfo().getUpdateMechanism() == 3) {
            BaseBinInputStream baseBinInputStream = this.mNextBinInputStream;
            if (baseBinInputStream != null) {
                if (a(baseBinInputStream, this.mBytesSentBuffer, getOtaDeviceInfo().otaTempBufferSize * 4096)) {
                    try {
                        a(getDfuProgressInfo().getCurImageId(), (byte) 1);
                        ZLogger.d("make device to enter the ota advertiser mode, and let the app continue update image");
                        this.otaModeEnabled = true;
                        this.mBytesSentBuffer = 0;
                        a((byte) 1, true);
                        int activeImageSize2 = getDfuProgressInfo().getActiveImageSize() / 30;
                        a(30000L);
                    } catch (DfuException unused3) {
                        getDfuProgressInfo().setNextFileIndex(getDfuProgressInfo().getLastFileIndex());
                        return 1;
                    }
                } else {
                    try {
                        a(getDfuProgressInfo().getCurImageId(), (byte) 0);
                    } catch (DfuException unused4) {
                        getDfuProgressInfo().setNextFileIndex(getDfuProgressInfo().getLastFileIndex());
                        return 1;
                    }
                }
            } else {
                try {
                    a(getDfuProgressInfo().getCurImageId(), (byte) 1);
                } catch (DfuException unused5) {
                    getDfuProgressInfo().setNextFileIndex(getDfuProgressInfo().getLastFileIndex());
                    return 1;
                }
            }
        } else {
            try {
                a(getDfuProgressInfo().getCurImageId(), (byte) 0);
            } catch (DfuException unused6) {
                getDfuProgressInfo().setNextFileIndex(getDfuProgressInfo().getLastFileIndex());
                return 1;
            }
        }
        return 2;
    }

    public final boolean G() throws DfuException {
        if (!a()) {
            e(DfuException.ERROR_DFU_ABORTED);
            return false;
        } else if (getOtaDeviceInfo().isAesEncryptEnabled() && !b()) {
            e(4113);
            return false;
        } else {
            notifyStateChanged(DfuConstants.PROGRESS_START_DFU_PROCESS);
            if (this.DBG) {
                ZLogger.d(String.format("mOtaWorkMode=0x%04X, ICType=%2X", Integer.valueOf(this.mOtaWorkMode), Integer.valueOf(getOtaDeviceInfo().icType)));
            }
            ZLogger.v(getDfuProgressInfo().toString());
            f(getDfuProgressInfo().getCurImageId());
            if (!getDfuConfig().isBreakpointResumeEnabled() && this.mImageUpdateOffset != 0) {
                if ((getOtaDeviceInfo().icType == 4 || getOtaDeviceInfo().icType == 6 || getOtaDeviceInfo().icType == 8) && getOtaDeviceInfo().specVersion <= 4) {
                    ZLogger.d("clear soc data");
                    w();
                }
                this.mImageUpdateOffset = 0;
            }
            boolean z = this.DBG;
            ZLogger.v(z, "isBufferCheckEnabled=" + getOtaDeviceInfo().isBufferCheckEnabled());
            if (!getOtaDeviceInfo().isBufferCheckEnabled()) {
                this.r = 0;
            } else if (q()) {
                this.r = 1;
            } else {
                this.r = 0;
            }
            boolean z2 = this.DBG;
            ZLogger.v(z2, "mRemoteOtaFunctionInfo=" + this.r);
            getDfuProgressInfo().start();
            if (this.mImageUpdateOffset == 0) {
                x();
            }
            if (this.mImageUpdateOffset >= getDfuProgressInfo().getImageSizeInBytes()) {
                boolean z3 = this.DBG;
                if (z3) {
                    ZLogger.d(z3, "Last send reach the bottom");
                }
            } else {
                i(getDfuProgressInfo().getCurImageId());
                if (this.r == 1) {
                    c(this.mCurBinInputStream);
                } else {
                    d(this.mCurBinInputStream);
                }
            }
            getDfuProgressInfo().sendOver();
            return true;
        }
    }

    @Override // com.realsil.sdk.dfu.internal.base.BaseDfuTask
    public void alignmentSendBytes(int i, boolean z) {
        if (i != 0) {
            try {
                int max = Math.max(i - 12, 0);
                byte[] bArr = new byte[getDfuProgressInfo().getImageSizeInBytes()];
                if (z) {
                    this.mCurBinInputStream.read(bArr, max);
                } else {
                    this.mCurBinInputStream.read(bArr, 0, max);
                }
            } catch (IOException e) {
                ZLogger.w(e.toString());
                return;
            }
        }
        getDfuProgressInfo().setBytesSent(i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x004f, code lost:
        if (r9 != getDfuProgressInfo().getBytesSent()) goto L9;
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x007c A[Catch: IOException -> 0x0202, TryCatch #0 {IOException -> 0x0202, blocks: (B:10:0x0042, B:12:0x0047, B:16:0x0076, B:18:0x007c, B:23:0x00c8, B:25:0x00cc, B:27:0x00e5, B:28:0x0103, B:30:0x0106, B:20:0x00a3, B:21:0x00b3, B:15:0x0053), top: B:70:0x0042 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e5 A[Catch: IOException -> 0x0202, TryCatch #0 {IOException -> 0x0202, blocks: (B:10:0x0042, B:12:0x0047, B:16:0x0076, B:18:0x007c, B:23:0x00c8, B:25:0x00cc, B:27:0x00e5, B:28:0x0103, B:30:0x0106, B:20:0x00a3, B:21:0x00b3, B:15:0x0053), top: B:70:0x0042 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0106 A[Catch: IOException -> 0x0202, TRY_LEAVE, TryCatch #0 {IOException -> 0x0202, blocks: (B:10:0x0042, B:12:0x0047, B:16:0x0076, B:18:0x007c, B:23:0x00c8, B:25:0x00cc, B:27:0x00e5, B:28:0x0103, B:30:0x0106, B:20:0x00a3, B:21:0x00b3, B:15:0x0053), top: B:70:0x0042 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0129 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void c(com.realsil.sdk.dfu.image.stream.BaseBinInputStream r15) throws com.realsil.sdk.dfu.DfuException {
        /*
            Method dump skipped, instructions count: 531
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.dfu.u.m.c(com.realsil.sdk.dfu.image.stream.BaseBinInputStream):void");
    }

    public final void d(BaseBinInputStream baseBinInputStream) throws DfuException {
        int read;
        ZLogger.d("uploadFirmwareImageForBeeUpdate");
        c();
        this.mErrorState = 0;
        this.lastPacketTransferred = false;
        int i = this.MAX_PACKET_SIZE;
        byte[] bArr = new byte[i];
        while (!this.lastPacketTransferred) {
            if (!this.mAborted) {
                startSpeedControl();
                ZLogger.v(getDfuProgressInfo().toString());
                try {
                    if (getDfuProgressInfo().getBytesSent() == 0) {
                        int i2 = this.MAX_PACKET_SIZE;
                        byte[] bArr2 = new byte[i2];
                        baseBinInputStream.read(bArr2, i2 - 12);
                        System.arraycopy(baseBinInputStream.getHeaderBuf(), 0, bArr, 0, 12);
                        System.arraycopy(bArr2, 0, bArr, 12, this.MAX_PACKET_SIZE - 12);
                        read = this.MAX_PACKET_SIZE;
                        getDfuProgressInfo().setBytesSent(0);
                    } else {
                        read = baseBinInputStream.read(bArr, i);
                    }
                    if (getDfuProgressInfo().getRemainSizeInBytes() < this.MAX_PACKET_SIZE) {
                        ZLogger.v(this.DBG, "reach the end of the file, only read some");
                        read = getDfuProgressInfo().getRemainSizeInBytes();
                    }
                    if (read <= 0) {
                        if (getDfuProgressInfo().isFileSendOver()) {
                            ZLogger.d("image file has already been send over");
                            return;
                        }
                        ZLogger.w("Error while reading file with size: " + read);
                        throw new OtaException("Error while reading file", 257);
                    }
                    if (getOtaDeviceInfo().isAesEncryptEnabled()) {
                        for (int i3 = read; i3 > 0; i3 -= 16) {
                            if (i3 >= 16) {
                                int i4 = read - i3;
                                System.arraycopy(this.l.aesEncrypt(bArr, i4, 16), 0, bArr, i4, 16);
                                if (getOtaDeviceInfo().getAesEncryptMode() == 0) {
                                    break;
                                }
                            }
                        }
                    }
                    if (a((short) 1539, bArr, read)) {
                        getDfuProgressInfo().addBytesSent(read);
                        notifyProcessChanged();
                    }
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

    @Override // com.realsil.sdk.dfu.internal.base.BaseDfuTask
    public void executeOtaProcedure() {
        int innerCheck;
        super.executeOtaProcedure();
        try {
            setName("ProcessorXS0011ForceCopy");
            ZLogger.d("ProcessorXS0011ForceCopy running.");
            innerCheck = innerCheck();
        } catch (DfuException e) {
            ZLogger.w(e.toString());
            e(e.getErrCode());
        } catch (Exception e2) {
            ZLogger.w(e2.toString());
            e(0);
        }
        if (innerCheck != 0) {
            e(innerCheck);
            return;
        }
        notifyStateChanged(514);
        this.mOtaDeviceAddress = this.mDeviceAddress;
        this.otaModeEnabled = true;
        this.mBytesSentBuffer = 0;
        this.O = false;
        if (A()) {
            if (getOtaDeviceInfo().isRwsEnabled()) {
                ZLogger.v("RWS, no need to disconnect manully");
                if (getOtaDeviceInfo().specVersion <= 5) {
                    a(false);
                } else {
                    this.O = true;
                    g(0);
                    if (getOtaDeviceInfo().getBudRole() == 1) {
                        this.N = 2;
                    } else if (getOtaDeviceInfo().getBudRole() == 2) {
                        this.N = 1;
                    } else {
                        this.N = 1;
                    }
                }
                notifyStateChanged(DfuConstants.PROGRESS_HAND_OVER_PROCESSING);
                ZLogger.d(this.DBG, "wait master to handover ...");
                try {
                    Thread.sleep(getDfuConfig().getHandoverTimeout() * 1000);
                } catch (InterruptedException unused) {
                }
                ZLogger.d("start to upload secondary bud ...");
                this.otaModeEnabled = true;
                this.otaEnvironmentPrepared = false;
                this.mBytesSentBuffer = 0;
                if (A()) {
                    if (!this.o) {
                        notifyStateChanged(DfuConstants.PROGRESS_PENDING_ACTIVE_IMAGE);
                    } else {
                        a(true);
                        if (getDfuConfig().isCompleteActionEnabled(1)) {
                            BluetoothProfileManager.getInstance().disconnectA2dpSource(this.z.getRemoteDevice(this.mOtaDeviceAddress));
                            BluetoothProfileManager.getInstance().disconnectHfp(this.mOtaDeviceAddress);
                        }
                        notifyStateChanged(258);
                    }
                }
            } else if (!this.o) {
                notifyStateChanged(DfuConstants.PROGRESS_PENDING_ACTIVE_IMAGE);
            } else {
                a(true);
                if (getDfuConfig().isCompleteActionEnabled(1)) {
                    BluetoothProfileManager.getInstance().disconnectA2dpSource(this.z.getRemoteDevice(this.mOtaDeviceAddress));
                    BluetoothProfileManager.getInstance().disconnectHfp(this.mOtaDeviceAddress);
                }
                notifyStateChanged(258);
            }
        }
        closeInputStream(this.mCurBinInputStream);
        ZLogger.d(this.DBG, "DfuThread stopped");
        if (this.mProcessState == 525) {
            notifyStateChanged(259);
        }
    }

    public final void i(int i) throws DfuException {
        if (this.mImageUpdateOffset == 0 && getOtaDeviceInfo().specVersion < 3) {
            this.mImageUpdateOffset = 12;
        }
        ZLogger.d(this.DBG, String.format(Locale.US, "mImageUpdateOffset=0x%08X(%d)", Integer.valueOf(this.mImageUpdateOffset), Integer.valueOf(this.mImageUpdateOffset)));
        int bytesSent = getDfuProgressInfo().getBytesSent();
        int i2 = this.mImageUpdateOffset;
        if (bytesSent == i2 || i2 == -1) {
            return;
        }
        ZLogger.w("mBytesSent != mImageUpdateOffset, reload image bin file");
        this.imageFileLoaded = false;
        k();
        alignmentSendBytes(this.mImageUpdateOffset, false);
    }

    @Override // com.realsil.sdk.dfu.s.b, com.realsil.sdk.dfu.k.b
    public void j() {
        int maxFileCount = getDfuProgressInfo().getMaxFileCount();
        int nextFileIndex = getDfuProgressInfo().getNextFileIndex();
        if (nextFileIndex < 0 || nextFileIndex >= maxFileCount) {
            ZLogger.v("invalid FileIndex: " + nextFileIndex + ", reset to 0");
            nextFileIndex = 0;
        }
        getDfuProgressInfo().setCurrentFileIndex(nextFileIndex);
        BaseBinInputStream baseBinInputStream = this.pendingImageInputStreams.get(nextFileIndex);
        this.mCurBinInputStream = baseBinInputStream;
        if (baseBinInputStream != null) {
            if (this.DBG) {
                ZLogger.v(String.format("mCurBinInputStream's binId=0x%04X", Integer.valueOf(baseBinInputStream.getBinId())));
            }
            getDfuProgressInfo().initialize(this.mCurBinInputStream.getBinId(), this.mCurBinInputStream.getImageId(), this.mCurBinInputStream.getImageVersion(), this.mCurBinInputStream.remainSizeInBytes() + 12, getDfuConfig().isThroughputEnabled());
        } else {
            ZLogger.v(this.VDBG, "mCurBinInputStream == null");
        }
        int i = nextFileIndex + 1;
        if (i < maxFileCount) {
            this.mNextBinInputStream = this.pendingImageInputStreams.get(i);
            this.mNextBinIndex = i;
            return;
        }
        this.mNextBinInputStream = null;
        this.mNextBinIndex = -1;
    }

    @Override // com.realsil.sdk.dfu.s.b, com.realsil.sdk.dfu.k.b
    public void k() throws LoadFileException {
        closeInputStream(this.mCurBinInputStream);
        BinInfo loadImageBinInfo = FirmwareLoaderX.loadImageBinInfo(new LoadParams.Builder().preferredIcType(getDfuConfig().getPrimaryIcType()).fileLocation(getDfuConfig().getFileLocation()).setFilePath(this.m).setFileSuffix(getDfuConfig().getFileSuffix()).with(this.mContext).setWorkMode(this.mOtaWorkMode).setFileIndicator(this.n).setOtaDeviceInfo(getOtaDeviceInfo()).setIcCheckEnabled(getDfuConfig().isIcCheckEnabled()).setSectionSizeCheckEnabled(getDfuConfig().isSectionSizeCheckEnabled()).versionCheckEnabled(getDfuConfig().isVersionCheckEnabled(), getDfuConfig().getVersionCheckMode()).dataImageValidateEnabled(true).build());
        this.mBinIno = loadImageBinInfo;
        if (loadImageBinInfo != null) {
            if (loadImageBinInfo.status == 4096) {
                this.pendingImageInputStreams = loadImageBinInfo.supportBinInputStreams;
                this.P = loadImageBinInfo.forceCopyImages;
            } else {
                this.pendingImageInputStreams = null;
                this.P = null;
            }
        } else {
            this.P = null;
            this.pendingImageInputStreams = null;
        }
        List<BaseBinInputStream> list = this.pendingImageInputStreams;
        if (list != null && list.size() > 0) {
            if (getDfuProgressInfo().getNextFileIndex() == 0) {
                this.C = new int[this.pendingImageInputStreams.size()];
            }
            getDfuProgressInfo().setMaxFileCount(this.pendingImageInputStreams.size());
            if (this.DBG) {
                ZLogger.v(getDfuProgressInfo().toString());
            }
            j();
            this.imageFileLoaded = true;
            return;
        }
        ZLogger.d("pendingImageInputStreams == null || pendingImageInputStreams.size() <= 0");
        throw new LoadFileException("load image file error", 4097);
    }

    public final boolean y() throws DfuException {
        d(this.mOtaDeviceAddress);
        if (!this.otaEnvironmentPrepared) {
            B();
        } else {
            j();
        }
        if (this.mCurBinInputStream == null) {
            e(4097);
            return false;
        }
        return true;
    }

    public final void z() throws DfuException {
        ZLogger.d(this.DBG, String.format("<< CMD_OTA_GET_OTHER_INFO (0x%04X)", (short) 1547));
        byte[] b = b((short) 1547, (byte[]) null);
        if (b != null && b.length > 0) {
            getOtaDeviceInfo().setRwsUpdateFlag(b[0] & 1);
        } else {
            getOtaDeviceInfo().setRwsUpdateFlag(0);
        }
    }
}
