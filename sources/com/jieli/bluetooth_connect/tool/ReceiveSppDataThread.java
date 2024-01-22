package com.jieli.bluetooth_connect.tool;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import com.jieli.bluetooth_connect.util.JL_Log;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes11.dex */
public class ReceiveSppDataThread extends Thread {
    public static final int EXIT_REASON_IO_EXCEPTION = 2;
    public static final int EXIT_REASON_PARAM_ERROR = 1;
    public static final int EXIT_REASON_SUCCESS = 0;
    private static final String TAG = ReceiveSppDataThread.class.getSimpleName();
    private volatile boolean isRunning;
    private final int mBlockSize;
    private final BluetoothSocket mBluetoothSocket;
    private final BluetoothDevice mConnectedSppDev;
    private final OnRecvSppDataListener mOnRecvSppDataListener;

    /* loaded from: classes11.dex */
    public interface OnRecvSppDataListener {
        void onRecvSppData(long j, BluetoothDevice bluetoothDevice, byte[] bArr);

        void onThreadStart(long j);

        void onThreadStop(long j, int i, BluetoothDevice bluetoothDevice);
    }

    public ReceiveSppDataThread(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket, OnRecvSppDataListener onRecvSppDataListener) {
        this(bluetoothDevice, bluetoothSocket, 4096, onRecvSppDataListener);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        int read;
        super.run();
        JL_Log.i(TAG, "ReceiveDataThread start.");
        int i = 1;
        this.isRunning = true;
        OnRecvSppDataListener onRecvSppDataListener = this.mOnRecvSppDataListener;
        if (onRecvSppDataListener != null) {
            onRecvSppDataListener.onThreadStart(getId());
        }
        if (this.mConnectedSppDev != null) {
            byte[] bArr = new byte[this.mBlockSize];
            InputStream inputStream = null;
            BluetoothSocket bluetoothSocket = this.mBluetoothSocket;
            if (bluetoothSocket != null) {
                try {
                    inputStream = bluetoothSocket.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            JL_Log.i(TAG, "ReceiveDataThread isRunning : " + this.isRunning + ", mBluetoothSocket : " + this.mBluetoothSocket + ", inputStream : " + inputStream);
            while (this.isRunning && inputStream != null) {
                try {
                    read = inputStream.read(bArr);
                } catch (Exception e2) {
                    JL_Log.e(TAG, "-ReceiveDataThread- have an exception : " + e2.getMessage());
                    e2.printStackTrace();
                }
                if (read < 0) {
                    i = 2;
                    break;
                } else if (read == 0) {
                    Thread.sleep(30L);
                } else {
                    byte[] bArr2 = new byte[read];
                    System.arraycopy(bArr, 0, bArr2, 0, read);
                    OnRecvSppDataListener onRecvSppDataListener2 = this.mOnRecvSppDataListener;
                    if (onRecvSppDataListener2 != null) {
                        onRecvSppDataListener2.onRecvSppData(getId(), this.mConnectedSppDev, bArr2);
                    }
                }
            }
            i = 0;
        }
        this.isRunning = false;
        OnRecvSppDataListener onRecvSppDataListener3 = this.mOnRecvSppDataListener;
        if (onRecvSppDataListener3 != null) {
            onRecvSppDataListener3.onThreadStop(getId(), i, this.mConnectedSppDev);
        }
        JL_Log.i(TAG, "ReceiveDataThread exit");
    }

    public void stopThread() {
        this.isRunning = false;
    }

    public ReceiveSppDataThread(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket, int i, OnRecvSppDataListener onRecvSppDataListener) {
        super("ReceiveSppDataThread : " + bluetoothDevice);
        this.mConnectedSppDev = bluetoothDevice;
        this.mBluetoothSocket = bluetoothSocket;
        this.mBlockSize = i;
        this.mOnRecvSppDataListener = onRecvSppDataListener;
    }
}
