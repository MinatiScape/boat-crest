package com.realsil.sdk.dfu.u;

import android.content.Context;
import com.realsil.sdk.core.bluetooth.BluetoothProfileManager;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.DfuConstants;
import com.realsil.sdk.dfu.DfuException;
import com.realsil.sdk.dfu.exception.OtaException;
import com.realsil.sdk.dfu.image.stream.BaseBinInputStream;
import com.realsil.sdk.dfu.internal.base.DfuThreadCallback;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.realsil.sdk.dfu.model.OtaDeviceInfo;
import java.io.IOException;
import java.util.Locale;
/* loaded from: classes12.dex */
public class n extends l {
    public int M;
    public boolean N;

    public n(Context context, DfuConfig dfuConfig, DfuThreadCallback dfuThreadCallback) {
        super(context, dfuConfig, dfuThreadCallback);
    }

    public boolean A() {
        int C;
        boolean z = false;
        while (a()) {
            try {
            } catch (DfuException e) {
                ZLogger.e(DfuConstants.parseOtaState(this.mProcessState) + ", " + e.toString());
                int errCode = e.getErrCode();
                if (errCode == 4128) {
                    a(errCode, true);
                } else if (errCode == 4097) {
                    a(errCode, false);
                } else {
                    w();
                    e(errCode);
                }
            }
            if (!y() || (C = C()) == 0) {
                return false;
            }
            if (C == 2) {
                z = true;
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
        e(DfuException.ERROR_DFU_ABORTED);
        return false;
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
        this.mOtaDeviceInfo = new OtaDeviceInfo(17, 2);
        v();
        if (getOtaDeviceInfo().isRwsEnabled()) {
            if (getOtaDeviceInfo().specVersion >= 6) {
                if (this.N && getOtaDeviceInfo().getBudRole() != this.M) {
                    throw new OtaException("role swap failed", 283);
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
        if (D()) {
            this.mBytesSentBuffer += getDfuProgressInfo().getBytesSent();
            if (getDfuProgressInfo().isLastImageFile()) {
                ZLogger.d("no pendding image file to upload.");
                if (getOtaDeviceInfo().specVersion >= 3) {
                    a(getDfuProgressInfo().getCurImageId(), (byte) 1);
                } else {
                    a(getDfuProgressInfo().getCurImageId(), (byte) 0);
                }
                getDfuProgressInfo().setActiveImageSize(this.mBytesSentBuffer);
                return 2;
            }
            ZLogger.d("has pendding image file to upload");
            a(getDfuProgressInfo().getCurImageId(), (byte) 0);
            return 1;
        }
        return 0;
    }

    public final boolean D() throws DfuException {
        if (!a()) {
            e(DfuException.ERROR_DFU_ABORTED);
            return false;
        } else if (getOtaDeviceInfo().isAesEncryptEnabled() && !b()) {
            e(4113);
            return false;
        } else {
            notifyStateChanged(DfuConstants.PROGRESS_START_DFU_PROCESS);
            ZLogger.d(this.DBG, String.format("mOtaWorkMode=0x%04X, ICType=%2X", Integer.valueOf(this.mOtaWorkMode), Integer.valueOf(getOtaDeviceInfo().icType)));
            ZLogger.v(this.DBG, getDfuProgressInfo().toString());
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
            f(getDfuProgressInfo().getCurImageId());
            if (!getDfuConfig().isBreakpointResumeEnabled()) {
                this.mImageUpdateOffset = 0;
            }
            ZLogger.v(this.DBG, String.format(Locale.US, "mImageUpdateOffset=0x%08X(%d)", Integer.valueOf(this.mImageUpdateOffset), Integer.valueOf(this.mImageUpdateOffset)));
            if (this.mImageUpdateOffset == 0) {
                a((byte) 1);
            }
            if (this.mImageUpdateOffset >= getDfuProgressInfo().getImageSizeInBytes()) {
                ZLogger.d(this.DBG, "Last send reach the bottom");
            } else {
                i(getDfuProgressInfo().getCurImageId());
                if (this.r == 1) {
                    d(this.mCurBinInputStream);
                } else {
                    c(this.mCurBinInputStream);
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
                ZLogger.e(e.toString());
                return;
            }
        }
        getDfuProgressInfo().setBytesSent(i);
    }

    public final void c(BaseBinInputStream baseBinInputStream) throws DfuException {
        int read;
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
                        getDfuProgressInfo().addImageSizeInBytes(12);
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
                            ZLogger.i("image file has already been send over");
                            return;
                        }
                        ZLogger.e("Error while reading file with size: " + read);
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

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0048, code lost:
        if (r9 != getDfuProgressInfo().getBytesSent()) goto L9;
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0079 A[Catch: IOException -> 0x01f6, TryCatch #0 {IOException -> 0x01f6, blocks: (B:10:0x003b, B:12:0x0040, B:16:0x0073, B:18:0x0079, B:23:0x00c5, B:25:0x00c9, B:27:0x00e2, B:28:0x0100, B:30:0x0103, B:20:0x00a0, B:21:0x00b0, B:15:0x004c), top: B:70:0x003b }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e2 A[Catch: IOException -> 0x01f6, TryCatch #0 {IOException -> 0x01f6, blocks: (B:10:0x003b, B:12:0x0040, B:16:0x0073, B:18:0x0079, B:23:0x00c5, B:25:0x00c9, B:27:0x00e2, B:28:0x0100, B:30:0x0103, B:20:0x00a0, B:21:0x00b0, B:15:0x004c), top: B:70:0x003b }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0103 A[Catch: IOException -> 0x01f6, TRY_LEAVE, TryCatch #0 {IOException -> 0x01f6, blocks: (B:10:0x003b, B:12:0x0040, B:16:0x0073, B:18:0x0079, B:23:0x00c5, B:25:0x00c9, B:27:0x00e2, B:28:0x0100, B:30:0x0103, B:20:0x00a0, B:21:0x00b0, B:15:0x004c), top: B:70:0x003b }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0126 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void d(com.realsil.sdk.dfu.image.stream.BaseBinInputStream r15) throws com.realsil.sdk.dfu.DfuException {
        /*
            Method dump skipped, instructions count: 519
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.dfu.u.n.d(com.realsil.sdk.dfu.image.stream.BaseBinInputStream):void");
    }

    @Override // com.realsil.sdk.dfu.internal.base.BaseDfuTask
    public void executeOtaProcedure() {
        int innerCheck;
        super.executeOtaProcedure();
        try {
            setName("ProcessorXS0011ForceTemp");
            ZLogger.d("ProcessorXS0011ForceTemp is running.");
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
        this.mBytesSentBuffer = 0;
        this.N = false;
        if (A()) {
            if (getOtaDeviceInfo().isRwsEnabled()) {
                ZLogger.v("RWS, no need to disconnect manully");
                if (getOtaDeviceInfo().specVersion <= 5) {
                    a(false);
                } else {
                    this.N = true;
                    if (getOtaDeviceInfo().getBudRole() == 1) {
                        this.M = 2;
                    } else if (getOtaDeviceInfo().getBudRole() == 2) {
                        this.M = 1;
                    } else {
                        this.M = 1;
                    }
                    g(0);
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
        if (this.mProcessState == 525) {
            notifyStateChanged(259);
        }
        ZLogger.d("ProcessorXS0011ForceTemp stopped.");
    }

    public final void i(int i) throws DfuException {
        int i2 = this.mImageUpdateOffset;
        if (i2 == 0) {
            if (getOtaDeviceInfo().specVersion < 3) {
                this.mImageUpdateOffset = 12;
            }
            ZLogger.d(this.DBG, String.format(Locale.US, "First Packet, mImageUpdateOffset=0x%08X(%d)", Integer.valueOf(this.mImageUpdateOffset), Integer.valueOf(this.mImageUpdateOffset)));
        } else {
            ZLogger.d(this.DBG, String.format(Locale.US, "mImageUpdateOffset=0x%08X(%d)", Integer.valueOf(i2), Integer.valueOf(this.mImageUpdateOffset)));
        }
        int bytesSent = getDfuProgressInfo().getBytesSent();
        int i3 = this.mImageUpdateOffset;
        if (bytesSent == i3 || i3 == -1) {
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
        byte[] a2 = a((short) 1547);
        if (a2 != null && a2.length > 0) {
            getOtaDeviceInfo().setRwsUpdateFlag(a2[0] & 1);
        } else {
            getOtaDeviceInfo().setRwsUpdateFlag(0);
        }
    }
}
