package com.realsil.sdk.dfu.m;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.realsil.sdk.core.bluetooth.GlobalGatt;
import com.realsil.sdk.core.bluetooth.impl.BluetoothGattImpl;
import com.realsil.sdk.core.bluetooth.scanner.ExtendedBluetoothDevice;
import com.realsil.sdk.core.bluetooth.scanner.LeScannerPresenter;
import com.realsil.sdk.core.bluetooth.scanner.ScannerCallback;
import com.realsil.sdk.core.bluetooth.scanner.ScannerParams;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import com.realsil.sdk.dfu.DfuException;
import com.realsil.sdk.dfu.exception.OtaException;
import com.realsil.sdk.dfu.internal.base.DfuThreadCallback;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.util.Locale;
/* loaded from: classes12.dex */
public abstract class b extends com.realsil.sdk.dfu.k.b implements f {
    public LeScannerPresenter D;
    public volatile boolean E;
    public C0723b F;
    public GlobalGatt G;
    public BluetoothGatt H;
    public volatile byte[] I;
    public volatile boolean J;
    public volatile boolean K;
    public volatile boolean L;
    public Handler M;
    public Runnable N;

    /* loaded from: classes12.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b bVar = b.this;
            if (bVar.mConnectionState == 513) {
                int a2 = bVar.a(bVar.mOtaDeviceAddress);
                boolean z = b.this.DBG;
                ZLogger.v(z, ">> mBondState: " + a2);
                b.this.l();
            }
        }
    }

    /* renamed from: com.realsil.sdk.dfu.m.b$b  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public class C0723b extends ScannerCallback {
        public C0723b() {
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

        public /* synthetic */ C0723b(b bVar, a aVar) {
            this();
        }
    }

    public b(Context context, DfuConfig dfuConfig, DfuThreadCallback dfuThreadCallback) {
        super(context, dfuConfig, dfuThreadCallback);
        this.I = null;
        this.J = false;
        this.K = false;
        this.L = false;
        this.M = new Handler(Looper.getMainLooper());
        this.N = new a();
    }

    public void a(ScannerParams scannerParams, long j) throws DfuException {
        if (!this.mAborted) {
            notifyStateChanged(519);
            this.mErrorState = 0;
            this.B = false;
            b(scannerParams);
            try {
                synchronized (this.A) {
                    if (this.mErrorState == 0 && !this.B) {
                        this.A.wait(j);
                    }
                }
            } catch (InterruptedException e) {
                ZLogger.w("scanLeDevice interrupted, e = " + e.toString());
                this.mErrorState = 259;
            }
            if (this.mErrorState == 0 && !this.B) {
                ZLogger.w("didn't find the special device");
                this.mErrorState = DfuException.ERROR_CANNOT_FIND_DEVICE;
            }
            if (this.mErrorState != 0) {
                throw new OtaException("Error while scan remote ota device", this.mErrorState);
            }
            return;
        }
        throw new OtaException("user aborted", DfuException.ERROR_DFU_ABORTED);
    }

    @Override // com.realsil.sdk.dfu.internal.base.BaseDfuTask
    public boolean abort() {
        Handler handler = this.M;
        if (handler != null) {
            handler.removeCallbacks(this.N);
        }
        return super.abort();
    }

    @Override // com.realsil.sdk.dfu.k.b
    public boolean b(ScannerParams scannerParams) {
        if (this.DBG) {
            ZLogger.v("start le scan");
        }
        this.E = true;
        LeScannerPresenter leScannerPresenter = this.D;
        if (leScannerPresenter == null) {
            c(scannerParams);
        } else {
            leScannerPresenter.setScannerParams(scannerParams);
        }
        return this.D.startScan();
    }

    public final void c(ScannerParams scannerParams) {
        if (this.F == null) {
            this.F = new C0723b(this, null);
        }
        this.D = new LeScannerPresenter(this.mContext, scannerParams, this.F);
    }

    public void d(int i) {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException unused) {
        }
        ZLogger.d(String.format("terminateConnection, error = 0x%04X", Integer.valueOf(i)));
        BluetoothGatt bluetoothGatt = this.H;
        if (bluetoothGatt != null) {
            b(bluetoothGatt);
            a(this.H, getDfuConfig().isErrorActionEnabled(2));
            a(this.H);
        }
    }

    @Override // com.realsil.sdk.dfu.k.b, com.realsil.sdk.dfu.internal.base.BaseDfuTask
    public void initialize() {
        super.initialize();
        this.G = GlobalGatt.getInstance();
        c((ScannerParams) null);
    }

    public boolean l() {
        if (this.H == null) {
            ZLogger.w("mBluetoothGatt == null");
            this.mErrorState = 258;
            notifyConnectionLock();
            return false;
        } else if (this.mAborted) {
            ZLogger.w("task already aborted, ignore");
            this.mErrorState = DfuException.ERROR_DFU_ABORTED;
            return false;
        } else {
            if (this.DBG) {
                ZLogger.d("Attempting to start service discovery...");
            }
            boolean discoverServices = this.H.discoverServices();
            if (this.VDBG) {
                StringBuilder sb = new StringBuilder();
                sb.append("discoverServices ");
                sb.append(discoverServices ? "succeed" : "failed");
                ZLogger.d(sb.toString());
            }
            if (!discoverServices) {
                this.mErrorState = 258;
                notifyConnectionLock();
            }
            return discoverServices;
        }
    }

    public ScannerParams m() {
        ScannerParams scannerParams = new ScannerParams(17);
        scannerParams.setScanPeriod(31000L);
        return scannerParams;
    }

    public void n() {
        setConnectionState(513);
        if (this.M != null) {
            ZLogger.v(String.format(Locale.US, "delay to discover service for :%d ms ", 1600));
            this.M.postDelayed(this.N, 1600L);
            return;
        }
        l();
    }

    public byte[] o() throws DfuException {
        return b(getDfuConfig().getNotificationTimeout());
    }

    @Override // com.realsil.sdk.dfu.internal.base.BaseDfuTask
    public void onDestroy() {
        super.onDestroy();
        this.E = false;
        LeScannerPresenter leScannerPresenter = this.D;
        if (leScannerPresenter != null) {
            leScannerPresenter.onDestroy();
        }
    }

    public boolean p() {
        if (this.H == null) {
            ZLogger.w("mBluetoothGatt == null");
            return false;
        } else if (this.mAborted) {
            ZLogger.w("task already aborted, ignore");
            return false;
        } else if (getDfuConfig().getPhy() == 0) {
            ZLogger.v(this.DBG, "no need to set phy");
            return true;
        } else if (Build.VERSION.SDK_INT < 26) {
            ZLogger.d(this.DBG, "PHY not supported");
            return true;
        } else {
            boolean z = this.DBG;
            ZLogger.v(z, "setPreferredPhy:" + getDfuConfig().getPhy());
            int phy = getDfuConfig().getPhy();
            if (phy == 0) {
                this.H.setPreferredPhy(1, 1, 0);
            } else if (phy == 1) {
                this.H.setPreferredPhy(2, 2, 0);
            } else if (phy == 2) {
                this.H.setPreferredPhy(4, 4, 1);
            } else if (phy != 3) {
                this.H.setPreferredPhy(2, 2, 0);
            } else {
                this.H.setPreferredPhy(4, 4, 2);
            }
            return true;
        }
    }

    public boolean q() {
        this.E = false;
        LeScannerPresenter leScannerPresenter = this.D;
        if (leScannerPresenter != null) {
            return leScannerPresenter.stopScan();
        }
        return true;
    }

    public void c(int i) {
        this.MAX_PACKET_SIZE = i + (-3) > 16 ? 16 * (i / 16) : 16;
        ZLogger.v("> MAX_PACKET_SIZE=" + this.MAX_PACKET_SIZE);
    }

    public void b(BluetoothGatt bluetoothGatt) {
        int i = this.mConnectionState;
        if (i == 0 || i == 1280) {
            if (this.DBG) {
                ZLogger.d("already disconnect");
            }
        } else if (bluetoothGatt != null) {
            setConnectionState(1024);
            if (this.DBG) {
                ZLogger.v("disconnect()");
            }
            bluetoothGatt.disconnect();
            waitUntilDisconnected();
        } else {
            if (this.DBG) {
                ZLogger.v("gatt == null");
            }
            setConnectionState(0);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x00e9, code lost:
        if (r1.equals(r5.mDeviceAddress) != false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x01a2, code lost:
        if (r1.equals(r0.getAddress()) != false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x01a4, code lost:
        r1 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.realsil.sdk.core.bluetooth.scanner.ExtendedBluetoothDevice r6) {
        /*
            Method dump skipped, instructions count: 515
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.dfu.m.b.a(com.realsil.sdk.core.bluetooth.scanner.ExtendedBluetoothDevice):void");
    }

    public void b(int i) {
        if (!getDfuConfig().isBufferCheckMtuUpdateEnabled()) {
            ZLogger.d("not support update buffer check mtu size");
            return;
        }
        this.t = i + (-3) > 16 ? 16 * (i / 16) : 16;
        ZLogger.v("> mBufferCheckMtuSize=" + this.t);
    }

    public byte[] b(long j) throws DfuException {
        this.mErrorState = 0;
        this.K = true;
        try {
            synchronized (this.p) {
                if (this.mErrorState == 0 && this.I == null && this.mConnectionState == 515) {
                    this.K = false;
                    if (this.VDBG) {
                        ZLogger.v("wait for notification, wait for " + j + "ms");
                    }
                    this.p.wait(j);
                }
                if (this.mErrorState == 0 && !this.K) {
                    ZLogger.w("wait for notification, but not come");
                    this.mErrorState = DfuException.ERROR_NOTIFICATION_NO_RESPONSE;
                }
            }
        } catch (InterruptedException e) {
            ZLogger.w("readNotificationResponse interrupted, " + e.toString());
            this.mErrorState = 259;
        }
        if (this.mErrorState == 0) {
            return this.I;
        }
        throw new OtaException("Unable to receive notification", this.mErrorState);
    }

    public final void a(BluetoothGatt bluetoothGatt, boolean z) {
        if (z || bluetoothGatt.getDevice().getBondState() == 10) {
            BluetoothGattImpl.refresh(bluetoothGatt);
        }
    }

    public void a(BluetoothGatt bluetoothGatt) {
        if (bluetoothGatt != null) {
            BluetoothDevice device = bluetoothGatt.getDevice();
            boolean isErrorActionEnabled = getDfuConfig().isErrorActionEnabled(4);
            if (this.DBG) {
                ZLogger.v(String.format("close gatt connection: %s, closeClient=%b", device.getAddress(), Boolean.valueOf(isErrorActionEnabled)));
            }
            GlobalGatt globalGatt = this.G;
            if (globalGatt != null) {
                globalGatt.closeGatt(device.getAddress(), isErrorActionEnabled);
            } else if (isErrorActionEnabled) {
                bluetoothGatt.close();
            }
        }
        setConnectionState(1280);
    }

    @TargetApi(23)
    public boolean a(BluetoothGatt bluetoothGatt, int i) {
        this.mErrorState = 0;
        this.L = false;
        if (this.DBG) {
            ZLogger.d("requestMtu: " + i);
        }
        if (!bluetoothGatt.requestMtu(i)) {
            ZLogger.w("requestMtu failed");
            return false;
        }
        try {
            synchronized (this.p) {
                if (!this.L && this.mErrorState == 0) {
                    if (this.DBG) {
                        ZLogger.v("wait mtu request callback for 15000ms");
                    }
                    this.p.wait(15000L);
                }
            }
        } catch (InterruptedException e) {
            ZLogger.w("requestMtu: Sleeping interrupted, e = " + e);
        }
        if (this.L || this.mErrorState != 0) {
            return true;
        }
        if (this.DBG) {
            ZLogger.d("requestMtu No CallBack");
        }
        return false;
    }

    public void a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) throws DfuException {
        int properties = bluetoothGattCharacteristic.getProperties();
        if ((properties & 16) == 0) {
            ZLogger.w("check properties failed: " + properties);
            return;
        }
        if (this.DBG) {
            ZLogger.v("setCharacteristicNotification() - uuid: " + bluetoothGattCharacteristic.getUuid() + " enabled: " + z);
        } else {
            ZLogger.v("setCharacteristicNotification() enabled: " + z);
        }
        if (bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, z)) {
            BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(f.f13618a);
            if (descriptor != null) {
                boolean z2 = true;
                if (descriptor.getValue() == null || descriptor.getValue().length != 2 || descriptor.getValue()[0] <= 0 || descriptor.getValue()[1] != 0) {
                    z2 = false;
                }
                ZLogger.v(this.DBG, "current cccd state: " + z2);
                if (z && z2) {
                    ZLogger.w("cccd already enabled");
                    return;
                } else if (!z && !z2) {
                    ZLogger.w("cccd already disable");
                    return;
                } else {
                    this.mErrorState = 0;
                    this.J = false;
                    descriptor.setValue(z ? BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE : BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
                    if (bluetoothGatt.writeDescriptor(descriptor)) {
                        synchronized (this.p) {
                            if (this.mErrorState == 0 && !this.J) {
                                try {
                                    if (this.VDBG) {
                                        ZLogger.v("wait writeDescriptor for 15000ms");
                                    }
                                    this.p.wait(15000L);
                                } catch (InterruptedException e) {
                                    ZLogger.w("wait writeDescriptor interrupted: " + e.toString());
                                }
                            }
                        }
                        if (this.mErrorState == 0 && !this.J) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(z ? "Enabling " : "Disabling");
                            sb.append(" notifications failed");
                            ZLogger.w(sb.toString());
                            this.mErrorState = DfuException.ERROR_WRITE_CHARAC_NOTIFY_ERROR;
                        }
                        if (this.mErrorState == 0) {
                            if (this.DBG) {
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append(z ? "Enabe" : "Disable");
                                sb2.append(" notifications success");
                                ZLogger.v(sb2.toString());
                                return;
                            }
                            return;
                        }
                        throw new OtaException("Unable to set notifications state", this.mErrorState);
                    }
                    this.mErrorState = DfuException.ERROR_WRITE_CHARAC_NOTIFY_ERROR;
                    throw new OtaException("writeDescriptor failed", this.mErrorState);
                }
            }
            this.mErrorState = DfuException.ERROR_WRITE_CHARAC_NOTIFY_ERROR;
            throw new OtaException("no descriptor exist", this.mErrorState);
        }
        this.mErrorState = DfuException.ERROR_WRITE_CHARAC_NOTIFY_ERROR;
        throw new OtaException("setCharacteristicNotification failed", this.mErrorState);
    }

    public boolean a(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, boolean z) throws DfuException {
        return a(this.H, bluetoothGattCharacteristic, bArr, z);
    }

    public final boolean a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, boolean z) throws DfuException {
        return a(bluetoothGatt, bluetoothGattCharacteristic, bArr, bArr != null ? bArr.length : -1, z);
    }

    public boolean a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, int i, boolean z) throws DfuException {
        if (z || !this.mAborted) {
            if (bluetoothGattCharacteristic == null) {
                ZLogger.w("characteristic == null");
                return false;
            } else if (bArr != null && i >= 0) {
                this.I = null;
                this.mWriteRetransFlag = true;
                boolean z2 = false;
                int i2 = 0;
                while (this.mWriteRetransFlag) {
                    this.mWriteRequestCompleted = false;
                    if (i2 > 0) {
                        try {
                            if (this.DBG) {
                                ZLogger.d("re-send command just wait a while");
                            }
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {
                            ZLogger.w(e.toString());
                        }
                        if (!z && this.mAborted) {
                            throw new OtaException("user aborted", DfuException.ERROR_DFU_ABORTED);
                        }
                    }
                    long j = bluetoothGattCharacteristic.getWriteType() == 2 ? 30000L : 15000L;
                    z2 = a(bluetoothGatt, bluetoothGattCharacteristic, bArr, i);
                    if (z2) {
                        synchronized (this.j) {
                            try {
                                if (!this.mWriteRequestCompleted && this.mConnectionState == 515) {
                                    this.j.wait(j);
                                } else if (this.VDBG) {
                                    ZLogger.v("writePacket success");
                                }
                            } catch (InterruptedException e2) {
                                ZLogger.w("mWriteLock Sleeping interrupted,e:" + e2);
                                if (this.mErrorState == 0) {
                                    this.mErrorState = 259;
                                }
                            }
                        }
                        if (this.mErrorState == 0 && !this.mWriteRequestCompleted) {
                            ZLogger.w("send command but no callback");
                            this.mErrorState = 261;
                        }
                    } else {
                        ZLogger.w("writePacket failed");
                        this.mErrorState = DfuException.ERROR_WRITE_CHARAC_ERROR;
                        z2 = false;
                    }
                    if (this.mErrorState != 0 || i2 <= 3) {
                        i2++;
                    } else {
                        ZLogger.w("send command reach max try time");
                        this.mErrorState = DfuException.ERROR_SEND_COMMAND_REACH_MAX_RETRY_TIMES;
                    }
                    if (this.mErrorState != 0) {
                        throw new OtaException("Error while send command", this.mErrorState);
                    }
                }
                return z2;
            } else {
                ZLogger.w("value == null || size < 0");
                return false;
            }
        }
        throw new OtaException("user aborted", DfuException.ERROR_DFU_ABORTED);
    }

    public final boolean a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, int i) {
        if (bluetoothGatt == null) {
            ZLogger.w("gatt == null");
            return false;
        } else if (bluetoothGattCharacteristic == null) {
            ZLogger.w("characteristic == null");
            return false;
        } else {
            if (bArr.length > i) {
                byte[] bArr2 = new byte[i];
                System.arraycopy(bArr, 0, bArr2, 0, i);
                bArr = bArr2;
            }
            if (this.DBG) {
                ZLogger.v(String.format(Locale.US, "[TX] WRITE_TYPE_0x%02X, (%d)%s >> (%d)%s", Integer.valueOf(bluetoothGattCharacteristic.getWriteType()), Integer.valueOf(bluetoothGattCharacteristic.getInstanceId()), bluetoothGattCharacteristic.getUuid(), Integer.valueOf(bArr.length), DataConverter.bytes2Hex(bArr)));
            }
            bluetoothGattCharacteristic.setValue(bArr);
            return bluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic);
        }
    }

    public byte[] a(BluetoothGattCharacteristic bluetoothGattCharacteristic) throws DfuException {
        return a(this.H, bluetoothGattCharacteristic);
    }

    public byte[] a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) throws DfuException {
        if (this.mAborted) {
            throw new OtaException("user aborted", DfuException.ERROR_DFU_ABORTED);
        }
        if (bluetoothGatt == null) {
            ZLogger.w("gatt == null");
            return null;
        } else if (bluetoothGattCharacteristic == null) {
            ZLogger.w("characteristic == null");
            return null;
        } else if ((bluetoothGattCharacteristic.getProperties() & 2) == 0) {
            ZLogger.w("characteristic not support PROPERTY_READ");
            return null;
        } else {
            ZLogger.v(String.format(Locale.US, "readCharacteristic:(handler=%d) %s", Integer.valueOf(bluetoothGattCharacteristic.getInstanceId()), bluetoothGattCharacteristic.getUuid().toString()));
            this.mErrorState = 0;
            this.mReadRxData = null;
            this.mReadRequestCompleted = false;
            if (bluetoothGatt.readCharacteristic(bluetoothGattCharacteristic)) {
                synchronized (this.mReadLock) {
                    try {
                        if (this.mErrorState == 0 && !this.mReadRequestCompleted && this.mConnectionState == 515) {
                            this.mReadLock.wait(15000L);
                        }
                    } catch (InterruptedException e) {
                        ZLogger.w("mCharacteristicReadCalledLock Sleeping interrupted,e:" + e);
                        this.mErrorState = 259;
                    }
                }
                if (this.mErrorState == 0 && !this.mReadRequestCompleted) {
                    ZLogger.w("read value but no callback");
                    this.mErrorState = 261;
                }
            } else {
                ZLogger.d("readCharacteristic failed");
                this.mErrorState = DfuException.ERROR_SEND_COMMAND_FAIL;
            }
            if (this.mErrorState == 0) {
                return this.mReadRxData;
            }
            throw new OtaException("Error while send command", this.mErrorState);
        }
    }
}
