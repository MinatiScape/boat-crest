package com.realsil.sdk.dfu.v;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Handler;
import android.text.TextUtils;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.DfuException;
import com.realsil.sdk.dfu.RtkDfu;
import com.realsil.sdk.dfu.image.FirmwareLoaderX;
import com.realsil.sdk.dfu.image.LoadParams;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.realsil.sdk.dfu.model.DfuProgressInfo;
import com.realsil.sdk.dfu.model.OtaDeviceInfo;
import com.realsil.sdk.dfu.model.Throughput;
import com.realsil.sdk.dfu.utils.ConnectParams;
import com.realsil.sdk.dfu.utils.DfuAdapter;
import java.io.File;
import java.util.HashMap;
import java.util.Locale;
/* loaded from: classes12.dex */
public abstract class e extends DfuAdapter {
    public com.realsil.sdk.dfu.w.b A = new a();
    public UsbManager x;
    public UsbDevice y;
    public String z;

    /* loaded from: classes12.dex */
    public class a extends com.realsil.sdk.dfu.w.b {
        public a() {
        }

        @Override // com.realsil.sdk.dfu.w.b
        public void a(boolean z, com.realsil.sdk.dfu.w.a aVar) {
            if (z) {
                ZLogger.i("onServiceConnectionStateChange connected");
                e.this.k = aVar;
                e.this.notifyStateChanged(258);
                return;
            }
            ZLogger.w("onServiceConnectionStateChange disconnected");
            e.this.k = null;
            e.this.notifyStateChanged(0);
        }

        @Override // com.realsil.sdk.dfu.internal.base.DfuThreadCallback
        public void onError(int i) {
            e.this.notifyError(i);
        }

        @Override // com.realsil.sdk.dfu.internal.base.DfuThreadCallback
        public void onProgressChanged(DfuProgressInfo dfuProgressInfo) {
            super.onProgressChanged(dfuProgressInfo);
            e.this.notifyProgressChanged(dfuProgressInfo);
        }

        @Override // com.realsil.sdk.dfu.internal.base.DfuThreadCallback
        public void onStateChanged(int i, Throughput throughput) {
            super.onStateChanged(i, throughput);
            if (e.this.n == null) {
                ZLogger.v(e.this.j, "no callback registered");
            } else {
                e.this.n.onProcessStateChanged(i, throughput);
            }
        }
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public boolean abort() {
        com.realsil.sdk.dfu.w.a aVar = this.k;
        if (aVar != null) {
            return aVar.a();
        }
        ZLogger.w("dfu has not been initialized");
        c();
        return false;
    }

    public boolean activeImage(boolean z) {
        com.realsil.sdk.dfu.w.a aVar = this.k;
        if (aVar != null) {
            return aVar.a(z);
        }
        ZLogger.w("dfu has not been initialized");
        c();
        return false;
    }

    public boolean checkBatteryLevel(int i) {
        if (getOtaDeviceInfo() == null) {
            ZLogger.w("ignore preverify, please call connectDevice() method to connect and get otaDeviceInfo first.");
            return true;
        } else if (getOtaDeviceInfo().isBasSupported()) {
            return getOtaDeviceInfo().getPrimaryBat() >= i;
        } else {
            ZLogger.w("ignore preverify, bas not supported");
            return true;
        }
    }

    public boolean checkImage(String str, boolean z, OtaDeviceInfo otaDeviceInfo) throws DfuException {
        return checkImage(0, str, z, false, false, otaDeviceInfo);
    }

    public boolean checkUpgrade(File file, int i) {
        return checkUpgrade(file, true, i);
    }

    public void close() {
        disconnect();
        destroy();
    }

    public boolean connectDevice(String str) {
        return connectDevice(new ConnectParams.Builder().address(str).build());
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public void destroy() {
        this.y = null;
        this.z = null;
        super.destroy();
    }

    public int getCurrentOtaState() {
        com.realsil.sdk.dfu.w.a aVar = this.k;
        if (aVar != null) {
            return aVar.e();
        }
        ZLogger.w("dfu has not been initialized");
        c();
        return -1;
    }

    public OtaDeviceInfo getOtaDeviceInfo() {
        return new OtaDeviceInfo(2);
    }

    public UsbDevice getRemoteDevice(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        UsbManager usbManager = this.x;
        if (usbManager == null) {
            ZLogger.w("mUsbManager == null");
            return null;
        }
        HashMap<String, UsbDevice> deviceList = usbManager.getDeviceList();
        if (deviceList == null || deviceList.size() <= 0) {
            return null;
        }
        return deviceList.get(str);
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public boolean initialize() {
        return initialize(this.n);
    }

    public boolean isIdle() {
        return (getCurrentOtaState() & 256) == 256;
    }

    public boolean isUsbSupported() {
        return this.x != null;
    }

    public boolean startOtaProcedure(DfuConfig dfuConfig) {
        return startOtaProcedure(dfuConfig, true);
    }

    public void c() {
        this.h = RtkDfu.DEBUG_ENABLE;
        this.x = (UsbManager) this.mContext.getSystemService("usb");
        getOtaDeviceInfo().setMode(2);
    }

    public boolean checkImage(int i, String str, boolean z, boolean z2, boolean z3, OtaDeviceInfo otaDeviceInfo) throws DfuException {
        return FirmwareLoaderX.loadImageBinInfo(new LoadParams.Builder().with(this.mContext).fileLocation(i).setFilePath(str).setOtaDeviceInfo(otaDeviceInfo).setSectionSizeCheckEnabled(z2).setIcCheckEnabled(z3).versionCheckEnabled(z).build()) != null;
    }

    public boolean checkUpgrade(File file, boolean z, int i) {
        if (getOtaDeviceInfo() == null) {
            ZLogger.w("please reConnectToDevice() method to connect and get otaDeviceInfo first.");
            return false;
        }
        try {
            return checkImage(0, file.getPath(), z, false, false, getOtaDeviceInfo()) && checkBatteryLevel(i);
        } catch (DfuException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean initialize(DfuAdapter.DfuHelperCallback dfuHelperCallback) {
        this.n = dfuHelperCallback;
        if (this.q == 257) {
            ZLogger.w("STATE_INIT_BINDING_SERVICE ...");
            return false;
        }
        boolean z = true;
        if (this.k == null) {
            notifyStateChanged(257);
            z = com.realsil.sdk.dfu.w.a.a(this.mContext, this.A);
            ZLogger.v("getDfuProxy: " + z);
            if (!z) {
                notifyStateChanged(0);
            }
        } else {
            notifyStateChanged(258);
            ZLogger.d("dfu already binded");
        }
        return z;
    }

    public boolean startOtaProcedure(DfuConfig dfuConfig, boolean z) {
        if (dfuConfig != null) {
            if (this.k == null) {
                ZLogger.w("DfuProxy didn't ready");
                initialize();
                return false;
            } else if (z) {
                ZLogger.d(dfuConfig.toString());
                if (getOtaDeviceInfo() == null) {
                    ZLogger.w("ignore preverify, please call connectDevice() method to connect and get otaDeviceInfo first.");
                    return true;
                }
                try {
                    if (!checkImage(dfuConfig, getOtaDeviceInfo())) {
                        ZLogger.w("checkImage failed");
                        notifyError(4097);
                        return false;
                    } else if (!dfuConfig.isBatteryCheckEnabled() || checkBatteryLevel(dfuConfig.getLowBatteryThreshold())) {
                        return true;
                    } else {
                        ZLogger.w("checkBatteryLevel failed");
                        notifyError(269);
                        return false;
                    }
                } catch (DfuException e) {
                    notifyError(e.getErrCode());
                    return false;
                }
            } else {
                return true;
            }
        }
        ZLogger.w("dfuConfig cannot be null");
        throw new IllegalArgumentException("dfuConfig cannot be null");
    }

    public void a(DfuException dfuException) {
        if (a(dfuException.getErrCode())) {
            this.m--;
            Handler handler = this.v;
            if (handler != null) {
                handler.postDelayed(this.w, 1000L);
                return;
            }
            return;
        }
        disconnect();
        notifyError(dfuException.getErrType(), dfuException.getErrCode());
    }

    public boolean a(int i) {
        if (this.q <= 258) {
            ZLogger.w("has not be initialized");
            return false;
        }
        int i2 = this.m;
        if (i2 > 0) {
            return i == 0 || i == 1 || i == 6;
        }
        ZLogger.v(String.format(Locale.US, "reconnectTimes=%d, no need to reconnect", Integer.valueOf(i2)));
        return false;
    }

    public boolean checkImage(DfuConfig dfuConfig, OtaDeviceInfo otaDeviceInfo) throws DfuException {
        return FirmwareLoaderX.loadImageBinInfo(new LoadParams.Builder().with(this.mContext).fileLocation(dfuConfig.getFileLocation()).setFilePath(dfuConfig.getFilePath()).setSectionSizeCheckEnabled(dfuConfig.isSectionSizeCheckEnabled()).setIcCheckEnabled(dfuConfig.isIcCheckEnabled()).setFileSuffix(dfuConfig.getFileSuffix()).versionCheckEnabled(dfuConfig.isVersionCheckEnabled(), dfuConfig.getVersionCheckMode()).setOtaDeviceInfo(otaDeviceInfo).build()) != null;
    }
}
