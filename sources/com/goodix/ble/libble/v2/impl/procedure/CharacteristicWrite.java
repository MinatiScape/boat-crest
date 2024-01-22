package com.goodix.ble.libble.v2.impl.procedure;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Build;
import com.goodix.ble.libble.v2.gb.procedure.GBGattProcedureWrite;
import com.goodix.ble.libble.v2.impl.BleCharacteristicX;
import com.goodix.ble.libble.v2.impl.BleGattX;
import com.goodix.ble.libble.v2.impl.data.BleIntState;
import com.goodix.ble.libcomx.ILogger;
import com.goodix.ble.libcomx.event.IEventListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes5.dex */
public class CharacteristicWrite extends BleBaseProcedure implements GBGattProcedureWrite, IEventListener<BleIntState> {
    public BluetoothGattCharacteristic A;
    public a B;
    public byte[] C;
    public byte[] F;
    public int G;
    public int H;
    public BleCharacteristicX z;
    public int D = 2;
    public InputStream E = null;
    public boolean I = false;
    public boolean J = false;
    public boolean K = false;

    /* loaded from: classes5.dex */
    public class a extends BluetoothGattCallback {
        public a() {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            if (CharacteristicWrite.this.taskState != 2) {
                return;
            }
            if (i == 0) {
                CharacteristicWrite.this.f();
            } else if (i == 5 || i == 8 || i == 137) {
                CharacteristicWrite.this.finishedWithError("Insufficient Authentication");
            } else {
                String str = "Error on writing characteristic <" + bluetoothGattCharacteristic.getUuid() + ">: " + BleGattX.gattStatusToString(i);
                ILogger iLogger = CharacteristicWrite.this.logger;
                if (iLogger != null) {
                    iLogger.e("CharacteristicWrite", str);
                }
                CharacteristicWrite.this.finishedWithError(i, str);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            if (CharacteristicWrite.this.taskState == 2 && i2 != 2) {
                CharacteristicWrite.this.finishedWithError("Failed to write characteristic. The connection has been lost.");
            }
        }
    }

    @Override // com.goodix.ble.libble.v2.impl.procedure.BleBaseProcedure
    public int doWork2() {
        if (this.z == null) {
            finishedWithError("Target characteristic is null.");
            return 0;
        } else if (!this.gattX.isConnected()) {
            finishedWithError("Failed to write characteristic. The connection is not established.");
            return 0;
        } else {
            BluetoothGattCharacteristic gattCharacteristic = this.z.getGattCharacteristic();
            this.A = gattCharacteristic;
            if (gattCharacteristic == null) {
                finishedWithError("Target characteristic is not discovered.");
                return 0;
            } else if ((gattCharacteristic.getProperties() & 12) == 0) {
                finishedWithError("Target characteristic is not writable.");
                return 0;
            } else {
                this.J = false;
                this.G = 0;
                InputStream inputStream = this.E;
                if (inputStream != null) {
                    try {
                        this.H = inputStream.available();
                        i();
                    } catch (Throwable th) {
                        this.H = 0;
                        finishedWithError("Failed to load data stream: " + th.getMessage(), th);
                        return 0;
                    }
                }
                if (this.C == null) {
                    finishedWithError("Value is null.");
                    return 0;
                }
                a aVar = new a();
                this.B = aVar;
                this.gattX.register(aVar);
                if (!this.gattX.isConnected()) {
                    finishedWithError("Failed to write characteristic. The connection is not established.");
                    return 0;
                }
                this.A.setValue(this.C);
                this.A.setWriteType(this.D);
                if (!this.gattX.tryWriteCharacteristic(this.A)) {
                    finishedWithError("Failed to write characteristic.");
                    return 0;
                } else if (Build.VERSION.SDK_INT < 26) {
                    this.gattX.evtBondStateChanged().subEvent(this).setExecutor(getExecutor()).register2(this);
                    return BleBaseProcedure.COMMUNICATION_TIMEOUT;
                } else {
                    return BleBaseProcedure.COMMUNICATION_TIMEOUT;
                }
            }
        }
    }

    public final void f() {
        if (this.J) {
            synchronized (this) {
                if (this.J) {
                    this.K = true;
                    return;
                }
            }
        }
        try {
            if (i() <= 0) {
                finishedWithDone();
                return;
            }
            this.A.setValue(this.C);
            if (!this.gattX.tryWriteCharacteristic(this.A)) {
                finishedWithError("Failed to write next segment of data stream.");
                return;
            }
            int i = this.H;
            if (i > 0) {
                publishProgress((this.G * 100) / i);
            }
            refreshTaskTimeout();
        } catch (Throwable th) {
            finishedWithError("Failed to read data stream: " + th.getMessage(), th);
        }
    }

    public final int i() throws Throwable {
        if (this.E != null) {
            int mtu = this.gattX.getMtu() - 3;
            byte[] bArr = this.F;
            if (bArr == null || bArr.length != mtu) {
                this.F = new byte[mtu];
            }
            int read = this.E.read(this.F);
            if (read > 0) {
                byte[] bArr2 = this.C;
                if (bArr2 == null || bArr2.length != read) {
                    this.C = new byte[read];
                }
                System.arraycopy(this.F, 0, this.C, 0, read);
                this.G += read;
                return read;
            }
        }
        return 0;
    }

    @Override // com.goodix.ble.libble.v2.impl.procedure.BleBaseProcedure, com.goodix.ble.libcomx.task.Task
    public void onCleanup() {
        InputStream inputStream;
        BleGattX bleGattX = this.gattX;
        if (bleGattX != null) {
            bleGattX.evtBondStateChanged().clear(this);
            a aVar = this.B;
            if (aVar != null) {
                this.gattX.remove(aVar);
            }
        }
        if (this.I && (inputStream = this.E) != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                ILogger iLogger = this.logger;
                if (iLogger != null) {
                    String name = getName();
                    iLogger.e(name, "Failed to close stream: " + e.getMessage(), e);
                }
            }
        }
        super.onCleanup();
    }

    @Override // com.goodix.ble.libcomx.event.IEventListener
    public void onEvent(Object obj, int i, BleIntState bleIntState) {
        if (bleIntState.state == 12) {
            ILogger iLogger = this.logger;
            if (iLogger != null) {
                iLogger.v("CharacteristicWrite", "Retry to write characteristic after bonded");
            }
            if (this.gattX.tryWriteCharacteristic(this.z.getGattCharacteristic())) {
                return;
            }
            finishedWithError("Failed to write characteristic after bonded.");
        }
    }

    public void setFlowCtrl(boolean z) {
        boolean z2;
        synchronized (this) {
            z2 = false;
            if (this.J != z) {
                this.J = z;
                if (!z && this.K) {
                    this.K = false;
                    z2 = true;
                }
            }
        }
        if (z2) {
            getExecutor().execute(new Runnable() { // from class: com.goodix.ble.libble.v2.impl.procedure.b
                @Override // java.lang.Runnable
                public final void run() {
                    CharacteristicWrite.this.f();
                }
            });
        }
    }

    public void setLargeValue(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            return;
        }
        if (i < 0 && (i = i + bArr.length) < 0) {
            i = 0;
        }
        if (i2 < 0 || i2 > bArr.length) {
            i2 = bArr.length;
        }
        setValue(new ByteArrayInputStream(bArr, i, i2), true);
    }

    public void setTargetCharacteristic(BleCharacteristicX bleCharacteristicX) {
        this.z = bleCharacteristicX;
    }

    public void setValue(InputStream inputStream, boolean z) {
        this.C = null;
        this.E = inputStream;
        this.I = z;
    }

    @Override // com.goodix.ble.libble.v2.gb.procedure.GBGattProcedureWrite
    public void setValue(byte[] bArr) {
        this.C = bArr;
        this.E = null;
    }

    public void setWriteType(int i) {
        this.D = i;
    }
}
