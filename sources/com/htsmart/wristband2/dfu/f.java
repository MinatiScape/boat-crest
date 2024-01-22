package com.htsmart.wristband2.dfu;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.htsmart.wristband2.dfu.k;
import com.htsmart.wristband2.utils.WristbandLog;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.realsil.sdk.dfu.model.DfuProgressInfo;
import com.realsil.sdk.dfu.model.OtaDeviceInfo;
import com.realsil.sdk.dfu.model.OtaModeInfo;
import com.realsil.sdk.dfu.model.Throughput;
import com.realsil.sdk.dfu.utils.ConnectParams;
import com.realsil.sdk.dfu.utils.DfuAdapter;
import com.realsil.sdk.dfu.utils.GattDfuAdapter;
import java.util.List;
/* loaded from: classes11.dex */
public class f extends k {
    public GattDfuAdapter c;
    public DfuConfig d;
    public final Object e;
    public volatile boolean f;
    public volatile boolean g;
    public boolean h;
    public Thread i;
    public final DfuAdapter.DfuHelperCallback j;

    /* loaded from: classes11.dex */
    public class a implements Runnable {
        public final /* synthetic */ BluetoothDevice h;
        public final /* synthetic */ String i;
        public final /* synthetic */ boolean j;

        public a(BluetoothDevice bluetoothDevice, String str, boolean z) {
            this.h = bluetoothDevice;
            this.i = str;
            this.j = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            f.this.h = false;
            if (!f.this.f) {
                synchronized (f.this.e) {
                    try {
                        f.this.e.wait(Constants.ONE_MIN_IN_MILLIS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (f.this.f) {
                f.this.j(this.h, this.i, this.j);
                return;
            }
            WristbandLog.w("mDfuHelper.isInitOk false", new Object[0]);
            f.this.c(2147483646);
        }
    }

    /* loaded from: classes11.dex */
    public class b extends DfuAdapter.DfuHelperCallback {
        public b() {
        }

        @Override // com.realsil.sdk.dfu.utils.DfuAdapter.DfuHelperCallback
        public void onError(int i, int i2) {
            f.this.t();
            WristbandLog.i("onError type:" + i + " code:" + i2, new Object[0]);
            f.this.c(i2);
        }

        @Override // com.realsil.sdk.dfu.utils.DfuAdapter.DfuHelperCallback
        public void onProcessStateChanged(int i, Throughput throughput) {
            super.onProcessStateChanged(i, throughput);
            WristbandLog.i("onProcessStateChanged: " + i, new Object[0]);
            if (i == 521) {
                f.this.i(0);
            } else if (i == 258) {
                f.this.t();
                f.this.q();
            }
        }

        @Override // com.realsil.sdk.dfu.utils.DfuAdapter.DfuHelperCallback
        public void onProgressChanged(DfuProgressInfo dfuProgressInfo) {
            super.onProgressChanged(dfuProgressInfo);
            if (dfuProgressInfo == null) {
                return;
            }
            WristbandLog.i("onProgressChanged: " + dfuProgressInfo.getProgress(), new Object[0]);
            f.this.i(dfuProgressInfo.getProgress());
        }

        @Override // com.realsil.sdk.dfu.utils.DfuAdapter.DfuHelperCallback
        public void onStateChanged(int i) {
            super.onStateChanged(i);
            WristbandLog.i("onStateChanged:" + i, new Object[0]);
            if (i != 258) {
                if (i == 527) {
                    f.this.s();
                    return;
                }
                return;
            }
            synchronized (f.this.e) {
                f.this.f = true;
                f.this.e.notify();
            }
        }

        @Override // com.realsil.sdk.dfu.utils.DfuAdapter.DfuHelperCallback
        public void onTargetInfoChanged(OtaDeviceInfo otaDeviceInfo) {
            super.onTargetInfoChanged(otaDeviceInfo);
        }
    }

    public f(Context context) {
        super(context);
        this.e = new Object();
        this.j = new b();
    }

    @Override // com.htsmart.wristband2.dfu.k
    public void a() {
        GattDfuAdapter gattDfuAdapter = GattDfuAdapter.getInstance(this.f12004a);
        this.c = gattDfuAdapter;
        gattDfuAdapter.initialize(this.j);
    }

    @Override // com.htsmart.wristband2.dfu.k
    public void a(@NonNull BluetoothDevice bluetoothDevice, @NonNull String str, boolean z) {
        WristbandLog.i("device=%s , filePath=%s , upgradeFirmware=%b , isOtaRunning=%b", bluetoothDevice.getAddress(), str, Boolean.valueOf(z), Boolean.valueOf(this.g));
        Thread thread = this.i;
        if (thread != null && thread.isAlive()) {
            this.i.interrupt();
            this.i = null;
        }
        Thread thread2 = new Thread(new a(bluetoothDevice, str, z));
        this.i = thread2;
        thread2.start();
    }

    @Override // com.htsmart.wristband2.dfu.k
    public void b() {
        this.h = true;
        t();
        this.c.close();
        this.b = null;
    }

    public final void c(int i) {
        if (this.h) {
            return;
        }
        this.h = true;
        k.a aVar = this.b;
        if (aVar != null) {
            aVar.a(i);
        }
    }

    public final void i(int i) {
        k.a aVar;
        if (this.h || (aVar = this.b) == null) {
            return;
        }
        aVar.onProgressChanged(i);
    }

    public final void j(@NonNull BluetoothDevice bluetoothDevice, @NonNull String str, boolean z) {
        o().setAddress(bluetoothDevice.getAddress());
        o().setLocalName(bluetoothDevice.getName());
        o().setFilePath(str);
        WristbandLog.i("upgradeFirmware:" + z, new Object[0]);
        DfuConfig o = o();
        if (z) {
            o.setOtaWorkMode(0);
        } else {
            o.setOtaWorkMode(16);
        }
        this.c.abort();
        if (this.c.connectDevice(new ConnectParams.Builder().address(bluetoothDevice.getAddress()).reconnectTimes(3).build())) {
            return;
        }
        WristbandLog.w("mDfuHelper.connectDevice failed", new Object[0]);
        c(2147483646);
    }

    public final boolean n() {
        List<OtaModeInfo> supportedModes = this.c.getSupportedModes();
        if (supportedModes == null || supportedModes.size() <= 0) {
            WristbandLog.w("mDfuHelper.getSupportedModes() is null", new Object[0]);
            return false;
        }
        for (OtaModeInfo otaModeInfo : supportedModes) {
            if (otaModeInfo.getWorkmode() == o().getOtaWorkMode()) {
                return true;
            }
        }
        return false;
    }

    public final DfuConfig o() {
        if (this.d == null) {
            DfuConfig dfuConfig = new DfuConfig();
            this.d = dfuConfig;
            dfuConfig.setChannelType(0);
            this.d.setAutomaticActiveEnabled(true);
            this.d.setFileIndicator(-1);
            this.d.setBatteryCheckEnabled(true);
            this.d.setIcCheckEnabled(false);
            this.d.setLogLevel(1);
            this.d.setSectionSizeCheckEnabled(false);
            this.d.setVersionCheckEnabled(false);
            this.d.setBreakpointResumeEnabled(false);
            this.d.addErrorAction(1);
            this.d.addErrorAction(2);
        }
        return this.d;
    }

    public final void q() {
        if (this.h) {
            return;
        }
        this.h = true;
        k.a aVar = this.b;
        if (aVar != null) {
            aVar.onSuccess();
        }
    }

    public final void s() {
        if (this.g) {
            return;
        }
        if (!n()) {
            WristbandLog.w("mDfuHelper.checkWorkMode failed", new Object[0]);
            c(Integer.MAX_VALUE);
            return;
        }
        OtaDeviceInfo otaDeviceInfo = this.c.getOtaDeviceInfo();
        WristbandLog.i("otaDeviceInfo:" + otaDeviceInfo, new Object[0]);
        if (otaDeviceInfo != null) {
            o().setProtocolType(otaDeviceInfo.getProtocolType());
        } else {
            o().setProtocolType(0);
        }
        this.g = this.c.startOtaProcess(o());
        if (this.g) {
            return;
        }
        WristbandLog.w("mDfuHelper.startOtaProcess failed", new Object[0]);
        c(DfuManager.ERROR_CODE_DFU_PROCESS_STARTUP_FAILED);
    }

    public final void t() {
        this.g = false;
        this.c.disconnect();
        this.c.abort();
        Thread thread = this.i;
        if (thread == null || !thread.isAlive()) {
            return;
        }
        this.i.interrupt();
        this.i = null;
    }
}
