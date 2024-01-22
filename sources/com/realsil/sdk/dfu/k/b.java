package com.realsil.sdk.dfu.k;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import com.clevertap.android.sdk.Constants;
import com.realsil.sdk.core.bluetooth.scanner.ScannerParams;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.DfuException;
import com.realsil.sdk.dfu.exception.LoadFileException;
import com.realsil.sdk.dfu.exception.OtaException;
import com.realsil.sdk.dfu.image.FirmwareLoaderX;
import com.realsil.sdk.dfu.image.LoadParams;
import com.realsil.sdk.dfu.image.stream.BaseBinInputStream;
import com.realsil.sdk.dfu.internal.base.BaseDfuTask;
import com.realsil.sdk.dfu.internal.base.DfuThreadCallback;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.realsil.sdk.dfu.model.OtaDeviceInfo;
import java.util.List;
/* loaded from: classes12.dex */
public abstract class b extends BaseDfuTask {
    public final Object A;
    public volatile boolean B;
    public int[] C;
    public BluetoothManager y;
    public BluetoothAdapter z;

    public b(Context context, DfuConfig dfuConfig, DfuThreadCallback dfuThreadCallback) {
        super(context, dfuConfig, dfuThreadCallback);
        this.A = new Object();
        this.B = false;
        initialize();
    }

    public void a(ScannerParams scannerParams) throws DfuException {
        if (!this.mAborted) {
            notifyStateChanged(515);
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
                ZLogger.w("findRemoteDevice interrupted, e = " + e.toString());
                this.mErrorState = 259;
            }
            if (this.mErrorState == 0 && !this.B) {
                ZLogger.w("didn't find the remote device");
                this.mErrorState = DfuException.ERROR_CANNOT_FIND_DEVICE;
            }
            if (this.mErrorState != 0) {
                throw new OtaException("Error while scan remote device", this.mErrorState);
            }
            return;
        }
        throw new OtaException("user aborted", DfuException.ERROR_DFU_ABORTED);
    }

    public BluetoothDevice b(String str) {
        try {
            return this.z.getRemoteDevice(str);
        } catch (Exception e) {
            ZLogger.w(e.toString());
            return null;
        }
    }

    public boolean b(ScannerParams scannerParams) {
        return false;
    }

    public void i() {
        synchronized (this.v) {
            if (this.u) {
                ZLogger.d(this.DBG, "Remote busy now, just wait!");
                try {
                    this.v.wait(Constants.ONE_MIN_IN_MILLIS);
                } catch (InterruptedException unused) {
                }
                if (this.VDBG) {
                    ZLogger.v("Remote idle now, just go!");
                }
            }
        }
    }

    @Override // com.realsil.sdk.dfu.internal.base.BaseDfuTask
    public void initialize() {
        super.initialize();
        this.mOtaDeviceInfo = new OtaDeviceInfo(this.h, 2);
        if (this.y == null) {
            BluetoothManager bluetoothManager = (BluetoothManager) this.mContext.getSystemService("bluetooth");
            this.y = bluetoothManager;
            if (bluetoothManager == null) {
                ZLogger.w("Unable to initialize BluetoothManager.");
                return;
            }
        }
        BluetoothAdapter adapter = this.y.getAdapter();
        this.z = adapter;
        if (adapter == null) {
            ZLogger.w("Unable to obtain a BluetoothAdapter.");
        }
    }

    @Override // com.realsil.sdk.dfu.internal.base.BaseDfuTask
    public int innerCheck() {
        int innerCheck = super.innerCheck();
        if (innerCheck != 0) {
            return innerCheck;
        }
        if (BluetoothAdapter.checkBluetoothAddress(this.mDeviceAddress)) {
            return 0;
        }
        if (this.DBG) {
            ZLogger.w("invalid address: " + this.mDeviceAddress);
            return 4112;
        }
        ZLogger.d("invalid address: ");
        return 4112;
    }

    public void j() {
        int maxFileCount = getDfuProgressInfo().getMaxFileCount();
        int nextFileIndex = getDfuProgressInfo().getNextFileIndex();
        if (nextFileIndex < 0 || nextFileIndex >= maxFileCount) {
            ZLogger.d("invalid FileIndex: " + nextFileIndex + ", reset to 0");
            nextFileIndex = 0;
        }
        getDfuProgressInfo().setCurrentFileIndex(nextFileIndex);
        BaseBinInputStream baseBinInputStream = this.pendingImageInputStreams.get(nextFileIndex);
        this.mCurBinInputStream = baseBinInputStream;
        if (baseBinInputStream != null) {
            getDfuProgressInfo().initialize(this.mCurBinInputStream.getBinId(), this.mCurBinInputStream.getImageId(), this.mCurBinInputStream.getImageVersion(), this.mCurBinInputStream.remainSizeInBytes(), getDfuConfig().isThroughputEnabled());
        } else {
            ZLogger.v(this.VDBG, "mCurBinInputStream == null");
        }
        int i = nextFileIndex + 1;
        if (i < maxFileCount) {
            this.mNextBinInputStream = this.pendingImageInputStreams.get(i);
            this.mNextBinIndex = i;
        } else {
            this.mNextBinInputStream = null;
            this.mNextBinIndex = -1;
        }
        if (this.DBG) {
            ZLogger.v(getDfuProgressInfo().toString());
        }
    }

    public void k() throws LoadFileException {
        closeInputStream(this.mCurBinInputStream);
        List<BaseBinInputStream> loadImageFile = FirmwareLoaderX.loadImageFile(new LoadParams.Builder().preferredIcType(getDfuConfig().getPrimaryIcType()).fileLocation(getDfuConfig().getFileLocation()).setFilePath(this.m).setFileSuffix(getDfuConfig().getFileSuffix()).with(this.mContext).setFileIndicator(this.n).setWorkMode(getDfuConfig().getOtaWorkMode()).setOtaDeviceInfo(getOtaDeviceInfo()).setIcCheckEnabled(getDfuConfig().isIcCheckEnabled()).setSectionSizeCheckEnabled(getDfuConfig().isSectionSizeCheckEnabled()).versionCheckEnabled(getDfuConfig().isVersionCheckEnabled(), getDfuConfig().getVersionCheckMode()).build());
        this.pendingImageInputStreams = loadImageFile;
        if (loadImageFile != null && loadImageFile.size() > 0) {
            if (getDfuProgressInfo().getNextFileIndex() == 0) {
                this.C = new int[this.pendingImageInputStreams.size()];
            }
            getDfuProgressInfo().setMaxFileCount(this.pendingImageInputStreams.size());
            j();
            this.imageFileLoaded = true;
            return;
        }
        ZLogger.d(this.DBG, "pendingImageInputStreams is null or empty");
        throw new LoadFileException("no available file to update", 4097);
    }

    public int a(String str) {
        BluetoothDevice b;
        if (this.z == null || (b = b(str)) == null) {
            return 10;
        }
        return b.getBondState();
    }

    public void a(BluetoothDevice bluetoothDevice, int i) {
        String str;
        if (bluetoothDevice == null || (str = this.mOtaDeviceAddress) == null || !str.equals(bluetoothDevice.getAddress()) || i != 2) {
            return;
        }
        ZLogger.v("profile connected");
        f();
    }
}
