package com.realsil.sdk.dfu.v;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.os.Handler;
import android.os.Looper;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.usb.GlobalUsbGatt;
import com.realsil.sdk.core.usb.UsbGatt;
import com.realsil.sdk.core.usb.UsbGattCharacteristic;
import com.realsil.sdk.core.utility.DataConverter;
import com.realsil.sdk.dfu.DfuException;
import com.realsil.sdk.dfu.exception.OtaException;
import com.realsil.sdk.dfu.internal.base.DfuThreadCallback;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.util.Locale;
/* loaded from: classes12.dex */
public abstract class f extends c implements com.realsil.sdk.dfu.m.f {
    public GlobalUsbGatt A;
    public UsbGatt B;
    public volatile byte[] C;
    public volatile boolean D;
    public volatile boolean E;
    public volatile boolean F;
    public Handler G;
    public Runnable H;

    /* loaded from: classes12.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            f fVar = f.this;
            if (fVar.mConnectionState == 513) {
                fVar.l();
            }
        }
    }

    public f(Context context, DfuConfig dfuConfig, DfuThreadCallback dfuThreadCallback) {
        super(context, dfuConfig, dfuThreadCallback);
        this.C = null;
        this.D = false;
        this.E = false;
        this.F = false;
        this.G = new Handler(Looper.getMainLooper());
        this.H = new a();
    }

    public void a(UsbGatt usbGatt) {
        if (usbGatt != null) {
            UsbDevice device = usbGatt.getDevice();
            boolean z = this.DBG;
            ZLogger.d(z, "close gatt connection: " + device.getDeviceName());
            GlobalUsbGatt globalUsbGatt = this.A;
            if (globalUsbGatt != null) {
                globalUsbGatt.closeGatt(device.getDeviceName());
            } else {
                usbGatt.close();
            }
        }
        setConnectionState(1280);
    }

    public final void a(UsbGatt usbGatt, boolean z) {
    }

    @Override // com.realsil.sdk.dfu.internal.base.BaseDfuTask
    public boolean abort() {
        Handler handler = this.G;
        if (handler != null) {
            handler.removeCallbacks(this.H);
        }
        return super.abort();
    }

    public void b(UsbGatt usbGatt) {
        int i = this.mConnectionState;
        if (i == 0 || i == 1280) {
            ZLogger.d(this.DBG, "already disconnect");
        } else if (usbGatt != null) {
            setConnectionState(1024);
            usbGatt.disconnect();
            waitUntilDisconnected();
        } else {
            ZLogger.d(this.DBG, "gatt == null");
            setConnectionState(0);
        }
    }

    public void c(int i) {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ZLogger.d(String.format("terminateConnection, error = 0x%04X", Integer.valueOf(i)));
        UsbGatt usbGatt = this.B;
        if (usbGatt != null) {
            b(usbGatt);
            a(this.B, getDfuConfig().isErrorActionEnabled(2));
            a(this.B);
        }
    }

    @Override // com.realsil.sdk.dfu.v.c, com.realsil.sdk.dfu.internal.base.BaseDfuTask
    public void initialize() {
        super.initialize();
        this.A = GlobalUsbGatt.getInstance();
    }

    public boolean l() {
        if (this.B == null) {
            ZLogger.w("mUsbGatt == null");
            this.mErrorState = 258;
            notifyConnectionLock();
            return false;
        } else if (this.mAborted) {
            ZLogger.w("task already aborted, ignore");
            return false;
        } else {
            ZLogger.d(this.DBG, "Attempting to start service discovery...");
            boolean discoverServices = this.B.discoverServices();
            boolean z = this.DBG;
            StringBuilder sb = new StringBuilder();
            sb.append("discoverServices ");
            sb.append(discoverServices ? "succeed" : "failed");
            ZLogger.d(z, sb.toString());
            if (!discoverServices) {
                this.mErrorState = 258;
                notifyConnectionLock();
            }
            return discoverServices;
        }
    }

    public void m() {
        setConnectionState(513);
        if (this.G != null) {
            ZLogger.d("delay to discover service for : 1600");
            this.G.postDelayed(this.H, 1600L);
            return;
        }
        l();
    }

    @TargetApi(23)
    public boolean a(UsbGatt usbGatt, int i) {
        this.mErrorState = 0;
        this.F = false;
        boolean z = this.DBG;
        ZLogger.d(z, "requestMtu: " + i);
        if (!usbGatt.requestMtu(i)) {
            ZLogger.w("requestMtu failed");
            return false;
        }
        try {
            synchronized (this.p) {
                if (!this.F && this.mErrorState == 0) {
                    if (this.DBG) {
                        ZLogger.v("wait mtu request callback for 15000ms");
                    }
                    this.p.wait(15000L);
                }
            }
        } catch (InterruptedException e) {
            ZLogger.w("requestMtu: Sleeping interrupted, e = " + e);
        }
        if (this.F || this.mErrorState != 0) {
            return true;
        }
        ZLogger.d(this.DBG, "requestMtu No CallBack");
        return false;
    }

    public void b(int i) {
        this.t = i + (-3) > 16 ? 16 * (i / 16) : 16;
        ZLogger.d("> mBufferCheckMtuSize=" + this.t);
    }

    public boolean a(UsbGattCharacteristic usbGattCharacteristic, byte[] bArr, boolean z) throws DfuException {
        return a(this.B, usbGattCharacteristic, bArr, z);
    }

    public final boolean a(UsbGatt usbGatt, UsbGattCharacteristic usbGattCharacteristic, byte[] bArr, boolean z) throws DfuException {
        return a(usbGatt, usbGattCharacteristic, bArr, bArr != null ? bArr.length : -1, z);
    }

    public boolean a(UsbGatt usbGatt, UsbGattCharacteristic usbGattCharacteristic, byte[] bArr, int i, boolean z) throws DfuException {
        if (z || !this.mAborted) {
            if (bArr != null && i >= 0) {
                this.C = null;
                this.mWriteRetransFlag = true;
                boolean z2 = false;
                int i2 = 0;
                while (this.mWriteRetransFlag) {
                    this.mWriteRequestCompleted = false;
                    if (i2 > 0) {
                        try {
                            ZLogger.d(this.DBG, "re-send command just wait a while");
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (!z && this.mAborted) {
                            throw new OtaException("user aborted", DfuException.ERROR_DFU_ABORTED);
                        }
                    }
                    z2 = a(usbGatt, usbGattCharacteristic, bArr, i);
                    if (z2) {
                        synchronized (this.j) {
                            try {
                                if (!this.mWriteRequestCompleted && this.mConnectionState == 515) {
                                    this.j.wait(15000L);
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
            }
            ZLogger.w("value == null || size < 0");
            return false;
        }
        throw new OtaException("user aborted", DfuException.ERROR_DFU_ABORTED);
    }

    public final boolean a(UsbGatt usbGatt, UsbGattCharacteristic usbGattCharacteristic, byte[] bArr, int i) {
        if (usbGatt == null) {
            ZLogger.w("gatt == null");
            return false;
        } else if (usbGattCharacteristic == null) {
            ZLogger.w("characteristic == null");
            return false;
        } else {
            if (bArr.length > i) {
                byte[] bArr2 = new byte[i];
                System.arraycopy(bArr, 0, bArr2, 0, i);
                bArr = bArr2;
            }
            if (this.DBG) {
                ZLogger.v(String.format(Locale.US, "[TX] WRITE_TYPE_0x%02X, %s >> (%d)%s", Integer.valueOf(usbGattCharacteristic.getWriteType()), usbGattCharacteristic.getUuid(), Integer.valueOf(bArr.length), DataConverter.bytes2Hex(bArr)));
            }
            usbGattCharacteristic.setValue(bArr);
            return usbGatt.writeCharacteristic(usbGattCharacteristic);
        }
    }

    public byte[] a(UsbGattCharacteristic usbGattCharacteristic) throws DfuException {
        return a(this.B, usbGattCharacteristic);
    }

    public byte[] a(UsbGatt usbGatt, UsbGattCharacteristic usbGattCharacteristic) throws DfuException {
        if (this.mAborted) {
            throw new OtaException("user aborted", DfuException.ERROR_DFU_ABORTED);
        }
        if (usbGatt == null) {
            ZLogger.w("gatt == null");
            return null;
        }
        this.mErrorState = 0;
        this.mReadRxData = null;
        this.mReadRequestCompleted = false;
        if (usbGatt.readCharacteristic(usbGattCharacteristic)) {
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
            ZLogger.w("readCharacteristic failed");
            this.mErrorState = DfuException.ERROR_SEND_COMMAND_FAIL;
        }
        if (this.mErrorState == 0) {
            return this.mReadRxData;
        }
        throw new OtaException("Error while send command", this.mErrorState);
    }
}
