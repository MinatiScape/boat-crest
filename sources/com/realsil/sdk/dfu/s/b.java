package com.realsil.sdk.dfu.s;

import android.content.Context;
import com.realsil.sdk.bbpro.core.transportlayer.AckPacket;
import com.realsil.sdk.bbpro.core.transportlayer.SppTransportLayer;
import com.realsil.sdk.core.bluetooth.scanner.BrEdrScannerPresenter;
import com.realsil.sdk.core.bluetooth.scanner.ExtendedBluetoothDevice;
import com.realsil.sdk.core.bluetooth.scanner.ScannerCallback;
import com.realsil.sdk.core.bluetooth.scanner.ScannerParams;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.DfuException;
import com.realsil.sdk.dfu.exception.LoadFileException;
import com.realsil.sdk.dfu.exception.OtaException;
import com.realsil.sdk.dfu.image.FirmwareLoaderX;
import com.realsil.sdk.dfu.image.LoadParams;
import com.realsil.sdk.dfu.image.stream.BaseBinInputStream;
import com.realsil.sdk.dfu.internal.base.DfuThreadCallback;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes12.dex */
public class b extends com.realsil.sdk.dfu.k.b {
    public BrEdrScannerPresenter D;
    public volatile boolean E;
    public a F;
    public volatile byte[] G;
    public volatile boolean H;
    public Set<Short> I;
    public Map<Short, AckPacket> J;
    public SppTransportLayer K;

    /* loaded from: classes12.dex */
    public class a extends ScannerCallback {
        public a() {
        }

        @Override // com.realsil.sdk.core.bluetooth.scanner.ScannerCallback
        public void onNewDevice(ExtendedBluetoothDevice extendedBluetoothDevice) {
            super.onNewDevice(extendedBluetoothDevice);
            if (!b.this.E) {
                if (b.this.DBG) {
                    ZLogger.d("is already stop the scan, do nothing");
                }
            } else if (extendedBluetoothDevice == null) {
                if (b.this.DBG) {
                    ZLogger.d("ignore, device == null");
                }
            } else {
                b.this.a(extendedBluetoothDevice);
            }
        }

        @Override // com.realsil.sdk.core.bluetooth.scanner.ScannerCallback
        public void onScanStateChanged(int i) {
            super.onScanStateChanged(i);
            if (b.this.VDBG) {
                ZLogger.v("state= " + i);
            }
        }
    }

    public b(Context context, DfuConfig dfuConfig, DfuThreadCallback dfuThreadCallback) {
        super(context, dfuConfig, dfuThreadCallback);
        this.G = null;
        this.H = false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x00db, code lost:
        if (r1.equals(r5.mDeviceAddress) != false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0121, code lost:
        if (r1.equals(r0.getAddress()) != false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0123, code lost:
        r1 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.realsil.sdk.core.bluetooth.scanner.ExtendedBluetoothDevice r6) {
        /*
            Method dump skipped, instructions count: 386
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.dfu.s.b.a(com.realsil.sdk.core.bluetooth.scanner.ExtendedBluetoothDevice):void");
    }

    @Override // com.realsil.sdk.dfu.k.b
    public boolean b(ScannerParams scannerParams) {
        if (this.DBG) {
            ZLogger.v("start le scan");
        }
        this.E = true;
        BrEdrScannerPresenter brEdrScannerPresenter = this.D;
        if (brEdrScannerPresenter == null) {
            c(scannerParams);
        } else {
            brEdrScannerPresenter.setScannerParams(scannerParams);
        }
        return this.D.startScan();
    }

    public final void c(ScannerParams scannerParams) {
        if (this.F == null) {
            this.F = new a();
        }
        this.D = new BrEdrScannerPresenter(this.mContext, scannerParams, this.F);
    }

    @Override // com.realsil.sdk.dfu.k.b, com.realsil.sdk.dfu.internal.base.BaseDfuTask
    public void initialize() {
        super.initialize();
        this.I = new HashSet();
        this.J = new HashMap();
        c(null);
        this.initialized = true;
        ZLogger.v("initialize success");
    }

    @Override // com.realsil.sdk.dfu.k.b
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
            getDfuProgressInfo().initialize(this.mCurBinInputStream.getBinId(), this.mCurBinInputStream.getImageId(), this.mCurBinInputStream.getImageVersion(), this.mCurBinInputStream.remainSizeInBytes(), getDfuConfig().isThroughputEnabled());
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

    @Override // com.realsil.sdk.dfu.k.b
    public void k() throws LoadFileException {
        closeInputStream(this.mCurBinInputStream);
        List<BaseBinInputStream> loadImageFile = FirmwareLoaderX.loadImageFile(new LoadParams.Builder().preferredIcType(getDfuConfig().getPrimaryIcType()).fileLocation(getDfuConfig().getFileLocation()).setFilePath(this.m).setFileSuffix(getDfuConfig().getFileSuffix()).with(this.mContext).setWorkMode(this.mOtaWorkMode).setFileIndicator(this.n).setOtaDeviceInfo(getOtaDeviceInfo()).setIcCheckEnabled(getDfuConfig().isIcCheckEnabled()).setSectionSizeCheckEnabled(getDfuConfig().isSectionSizeCheckEnabled()).versionCheckEnabled(getDfuConfig().isVersionCheckEnabled(), getDfuConfig().getVersionCheckMode()).build());
        this.pendingImageInputStreams = loadImageFile;
        if (loadImageFile != null && loadImageFile.size() > 0) {
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
        ZLogger.d(this.DBG, "pendingImageInputStreams == null || pendingImageInputStreams.size() <= 0");
        throw new LoadFileException("load image file error", 4097);
    }

    public ScannerParams l() {
        ScannerParams scannerParams = new ScannerParams(32);
        scannerParams.setScanPeriod(31000L);
        return scannerParams;
    }

    public byte[] m() throws DfuException {
        return b(getDfuConfig().getNotificationTimeout());
    }

    public boolean n() {
        this.E = false;
        BrEdrScannerPresenter brEdrScannerPresenter = this.D;
        if (brEdrScannerPresenter != null) {
            brEdrScannerPresenter.stopScan();
            return true;
        }
        return true;
    }

    @Override // com.realsil.sdk.dfu.internal.base.BaseDfuTask
    public void onDestroy() {
        super.onDestroy();
        this.E = false;
        BrEdrScannerPresenter brEdrScannerPresenter = this.D;
        if (brEdrScannerPresenter != null) {
            brEdrScannerPresenter.onDestroy();
        }
    }

    public final boolean b(short s, byte[] bArr, int i) {
        if (this.K == null) {
            ZLogger.w("mTransportLayer == null");
            return false;
        }
        if (bArr != null && bArr.length > i) {
            byte[] bArr2 = new byte[i];
            System.arraycopy(bArr, 0, bArr2, 0, i);
            bArr = bArr2;
        }
        return this.K.sendCmd(s, bArr);
    }

    public byte[] b(long j) throws DfuException {
        this.mErrorState = 0;
        this.H = true;
        try {
            synchronized (this.p) {
                if (this.mErrorState == 0 && this.G == null && this.mConnectionState == 515) {
                    this.H = false;
                    if (this.VDBG) {
                        ZLogger.v("wait for notification, wait for " + j + "ms");
                    }
                    this.p.wait(j);
                }
                if (this.mErrorState == 0 && !this.H) {
                    ZLogger.w("wait for notification, but not come");
                    this.mErrorState = DfuException.ERROR_NOTIFICATION_NO_RESPONSE;
                }
            }
        } catch (InterruptedException e) {
            ZLogger.e("readNotificationResponse interrupted, " + e.toString());
            this.mErrorState = 259;
        }
        if (this.mErrorState == 0) {
            return this.G;
        }
        throw new OtaException("Unable to receive notification", this.mErrorState);
    }

    public void b(int i) {
        this.t = i > 16 ? 16 * (i / 16) : 16;
        ZLogger.d("> mBufferCheckMtuSize=" + this.t);
    }

    public boolean a(short s, byte[] bArr) throws DfuException {
        return a(s, bArr, bArr != null ? bArr.length : -1, false);
    }

    public boolean a(short s, byte[] bArr, boolean z) throws DfuException {
        return a(s, bArr, bArr != null ? bArr.length : -1, z);
    }

    public boolean a(short s, byte[] bArr, int i) throws DfuException {
        return a(s, bArr, i, false);
    }

    public boolean a(short s, byte[] bArr, int i, boolean z) throws DfuException {
        if (this.mAborted && !z) {
            throw new OtaException("user aborted", DfuException.ERROR_DFU_ABORTED);
        }
        this.G = null;
        this.mWriteRetransFlag = true;
        boolean z2 = false;
        this.mWriteRequestCompleted = false;
        boolean b = b(s, bArr, i);
        if (b) {
            synchronized (this.j) {
                try {
                    if (!this.mWriteRequestCompleted && this.mConnectionState == 515) {
                        this.j.wait(15000L);
                    }
                } catch (InterruptedException e) {
                    ZLogger.w("mWriteLock Sleeping interrupted,e:" + e);
                    if (this.mErrorState == 0) {
                        this.mErrorState = 259;
                    }
                }
            }
            if (this.VDBG) {
                ZLogger.v(String.format("errorCode=0x%04X, mWriteRequestCompleted=%b, mConnectionState=0x%04X", 0, Boolean.valueOf(this.mWriteRequestCompleted), Integer.valueOf(this.mConnectionState)));
            }
            if (this.mErrorState == 0) {
                if (!this.mWriteRequestCompleted) {
                    ZLogger.d(this.DBG, "send command but no callback");
                    this.mErrorState = 261;
                } else if (this.mWriteRetransFlag) {
                    ZLogger.d(this.DBG, "write failed");
                    this.mErrorState = DfuException.ERROR_WRITE_CHARAC_ERROR;
                }
            }
            z2 = b;
        } else {
            ZLogger.d("write spp data error");
            this.mErrorState = DfuException.ERROR_WRITE_CHARAC_ERROR;
        }
        if (this.mErrorState == 0) {
            return z2;
        }
        throw new OtaException("Error while send command", this.mErrorState);
    }
}
