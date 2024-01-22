package com.htsmart.wristband2.dfu;

import android.app.ActivityManager;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import com.htsmart.wristband2.dfu.k;
import com.htsmart.wristband2.utils.WristbandLog;
import no.nordicsemi.android.dfu.DfuProgressListener;
import no.nordicsemi.android.dfu.DfuProgressListenerAdapter;
import no.nordicsemi.android.dfu.DfuServiceInitiator;
import no.nordicsemi.android.dfu.DfuServiceListenerHelper;
/* loaded from: classes11.dex */
public class g extends k {
    public volatile boolean c;
    public final DfuProgressListener d;

    /* loaded from: classes11.dex */
    public class a extends DfuProgressListenerAdapter {
        public a() {
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
        public void onDeviceConnecting(@NonNull String str) {
            WristbandLog.i("onDeviceConnecting", new Object[0]);
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
        public void onDeviceDisconnecting(@NonNull String str) {
            WristbandLog.i("onDeviceDisconnecting", new Object[0]);
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
        public void onDfuAborted(@NonNull String str) {
            WristbandLog.i("onDfuAborted", new Object[0]);
            g.this.c(DfuManager.ERROR_CODE_DFU_PROCESS_ABORT);
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
        public void onDfuCompleted(@NonNull String str) {
            WristbandLog.i("onDfuCompleted", new Object[0]);
            g.this.i();
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
        public void onDfuProcessStarting(@NonNull String str) {
            WristbandLog.i("onDfuProcessStarting", new Object[0]);
            g.this.f(0);
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
        public void onEnablingDfuMode(@NonNull String str) {
            WristbandLog.i("onEnablingDfuMode", new Object[0]);
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
        public void onError(@NonNull String str, int i, int i2, String str2) {
            WristbandLog.i("onError error:" + i + " errorType:" + i2 + " message:" + str2, new Object[0]);
            g.this.c(i);
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
        public void onFirmwareValidating(@NonNull String str) {
            WristbandLog.i("onFirmwareValidating", new Object[0]);
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
        public void onProgressChanged(@NonNull String str, int i, float f, float f2, int i2, int i3) {
            WristbandLog.i("onProgressChanged:" + i, new Object[0]);
            g.this.f(i);
        }
    }

    public g(Context context) {
        super(context);
        this.d = new a();
    }

    @Override // com.htsmart.wristband2.dfu.k
    public void a() {
        DfuServiceListenerHelper.registerProgressListener(this.f12004a, this.d);
    }

    @Override // com.htsmart.wristband2.dfu.k
    public void a(@NonNull BluetoothDevice bluetoothDevice, @NonNull String str, boolean z) {
        if (h()) {
            c(2147483646);
            return;
        }
        this.c = false;
        DfuServiceInitiator forceDfu = new DfuServiceInitiator(bluetoothDevice.getAddress()).setDeviceName(bluetoothDevice.getName()).setKeepBond(false).setForceDfu(false);
        int i = Build.VERSION.SDK_INT;
        DfuServiceInitiator unsafeExperimentalButtonlessServiceInSecureDfuEnabled = forceDfu.setPacketsReceiptNotificationsEnabled(i < 23).setPacketsReceiptNotificationsValue(12).setPrepareDataObjectDelay(400L).setUnsafeExperimentalButtonlessServiceInSecureDfuEnabled(true);
        unsafeExperimentalButtonlessServiceInSecureDfuEnabled.setZip(null, str);
        if (i >= 26) {
            DfuServiceInitiator.createDfuNotificationChannel(this.f12004a);
            unsafeExperimentalButtonlessServiceInSecureDfuEnabled.setForeground(true);
        }
        unsafeExperimentalButtonlessServiceInSecureDfuEnabled.setDisableNotification(true);
        unsafeExperimentalButtonlessServiceInSecureDfuEnabled.start(this.f12004a, DfuNordicService.class);
    }

    @Override // com.htsmart.wristband2.dfu.k
    public void b() {
        DfuServiceListenerHelper.unregisterProgressListener(this.f12004a, this.d);
    }

    public final void c(int i) {
        if (this.c) {
            return;
        }
        this.c = true;
        k.a aVar = this.b;
        if (aVar != null) {
            aVar.a(i);
        }
    }

    public final void f(int i) {
        k.a aVar;
        if (this.c || (aVar = this.b) == null) {
            return;
        }
        aVar.onProgressChanged(i);
    }

    public final boolean h() {
        String name = DfuNordicService.class.getName();
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) this.f12004a.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE)) {
            if (name.equals(runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public final void i() {
        if (this.c) {
            return;
        }
        this.c = true;
        k.a aVar = this.b;
        if (aVar != null) {
            aVar.onSuccess();
        }
    }
}
