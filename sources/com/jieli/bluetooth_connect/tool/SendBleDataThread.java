package com.jieli.bluetooth_connect.tool;

import android.bluetooth.BluetoothDevice;
import androidx.annotation.NonNull;
import com.jieli.bluetooth_connect.impl.BluetoothBle;
import com.jieli.bluetooth_connect.interfaces.listener.OnThreadStateListener;
import com.jieli.bluetooth_connect.interfaces.listener.OnWriteDataCallback;
import com.jieli.bluetooth_connect.util.JL_Log;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
/* loaded from: classes11.dex */
public class SendBleDataThread extends Thread {
    private static final String TAG = SendBleDataThread.class.getSimpleName();
    private volatile boolean isDataSend;
    private volatile boolean isThreadWaiting;
    private volatile boolean isWaitingForCallback;
    private final BluetoothBle mBleManager;
    private BleSendTask mCurrentTask;
    private final OnThreadStateListener mListener;
    private final LinkedBlockingQueue<BleSendTask> mQueue;
    private volatile int retryNum;

    /* loaded from: classes11.dex */
    public static class BleSendTask {
        private UUID characteristicUUID;
        private byte[] data;
        private OnWriteDataCallback mCallback;
        private BluetoothDevice mDevice;
        private UUID serviceUUID;
        private int status = -1;

        public BleSendTask(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, byte[] bArr, OnWriteDataCallback onWriteDataCallback) {
            setDevice(bluetoothDevice).setServiceUUID(uuid).setCharacteristicUUID(uuid2).setData(bArr).setCallback(onWriteDataCallback);
        }

        public boolean equals(Object obj) {
            if (obj instanceof BleSendTask) {
                BleSendTask bleSendTask = (BleSendTask) obj;
                BluetoothDevice bluetoothDevice = this.mDevice;
                return bluetoothDevice != null && this.serviceUUID != null && this.characteristicUUID != null && bluetoothDevice.equals(bleSendTask.getDevice()) && this.serviceUUID.equals(bleSendTask.getServiceUUID()) && this.characteristicUUID.equals(bleSendTask.getCharacteristicUUID());
            }
            return false;
        }

        public OnWriteDataCallback getCallback() {
            return this.mCallback;
        }

        public UUID getCharacteristicUUID() {
            return this.characteristicUUID;
        }

        public byte[] getData() {
            return this.data;
        }

        public BluetoothDevice getDevice() {
            return this.mDevice;
        }

        public UUID getServiceUUID() {
            return this.serviceUUID;
        }

        public int getStatus() {
            return this.status;
        }

        public int hashCode() {
            BluetoothDevice bluetoothDevice = this.mDevice;
            if (bluetoothDevice != null && this.serviceUUID != null && this.characteristicUUID != null) {
                return bluetoothDevice.hashCode() + this.serviceUUID.hashCode() + this.characteristicUUID.hashCode();
            }
            return super.hashCode();
        }

        public BleSendTask setCallback(OnWriteDataCallback onWriteDataCallback) {
            this.mCallback = onWriteDataCallback;
            return this;
        }

        public BleSendTask setCharacteristicUUID(UUID uuid) {
            this.characteristicUUID = uuid;
            return this;
        }

        public BleSendTask setData(byte[] bArr) {
            this.data = bArr;
            return this;
        }

        public BleSendTask setDevice(BluetoothDevice bluetoothDevice) {
            this.mDevice = bluetoothDevice;
            return this;
        }

        public BleSendTask setServiceUUID(UUID uuid) {
            this.serviceUUID = uuid;
            return this;
        }

        public BleSendTask setStatus(int i) {
            this.status = i;
            return this;
        }

        @NonNull
        public String toString() {
            return "BleSendTask{mDevice=" + this.mDevice + ", serviceUUID=" + this.serviceUUID + ", characteristicUUID=" + this.characteristicUUID + ", data=" + Arrays.toString(this.data) + ", status=" + this.status + ", mCallback=" + this.mCallback + '}';
        }
    }

    public SendBleDataThread(BluetoothBle bluetoothBle, OnThreadStateListener onThreadStateListener) {
        super(TAG);
        this.mQueue = new LinkedBlockingQueue<>();
        this.isDataSend = false;
        this.isThreadWaiting = false;
        this.isWaitingForCallback = false;
        this.retryNum = 0;
        this.mBleManager = bluetoothBle;
        this.mListener = onThreadStateListener;
    }

    private boolean addSendData(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, byte[] bArr, OnWriteDataCallback onWriteDataCallback) {
        boolean z;
        if (this.isDataSend) {
            try {
                this.mQueue.put(new BleSendTask(bluetoothDevice, uuid, uuid2, bArr, onWriteDataCallback));
                z = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
                z = false;
            }
            if (z && this.isThreadWaiting && !this.isWaitingForCallback) {
                this.isThreadWaiting = false;
                synchronized (this.mQueue) {
                    this.mQueue.notify();
                }
            }
            return z;
        }
        return false;
    }

    private void callbackResult(BleSendTask bleSendTask, boolean z) {
        if (bleSendTask != null && bleSendTask.getCallback() != null) {
            bleSendTask.getCallback().onBleResult(bleSendTask.getDevice(), bleSendTask.getServiceUUID(), bleSendTask.getCharacteristicUUID(), z, bleSendTask.getData());
        } else {
            JL_Log.i(TAG, "getCallback is null.");
        }
    }

    public boolean addSendTask(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, byte[] bArr, OnWriteDataCallback onWriteDataCallback) {
        BluetoothBle bluetoothBle = this.mBleManager;
        if (bluetoothBle == null || bluetoothDevice == null || !bluetoothBle.isConnectedBleDevice(bluetoothDevice) || uuid == null || uuid2 == null || bArr == null || bArr.length == 0) {
            return false;
        }
        int bleMtu = this.mBleManager.getBleMtu(bluetoothDevice);
        String str = TAG;
        JL_Log.d(str, "addSendTask : " + bleMtu);
        if (bleMtu >= 128) {
            bleMtu -= 6;
            JL_Log.d(str, "addSendTask : after >> " + bleMtu);
        }
        int i = bleMtu;
        if (i <= 0) {
            return false;
        }
        int length = bArr.length;
        int i2 = length / i;
        boolean z = false;
        for (int i3 = 0; i3 < i2; i3++) {
            byte[] bArr2 = new byte[i];
            System.arraycopy(bArr, i3 * i, bArr2, 0, i);
            z = addSendData(bluetoothDevice, uuid, uuid2, bArr2, onWriteDataCallback);
        }
        int i4 = length % i;
        if (i4 != 0) {
            byte[] bArr3 = new byte[i4];
            System.arraycopy(bArr, length - i4, bArr3, 0, i4);
            return addSendData(bluetoothDevice, uuid, uuid2, bArr3, onWriteDataCallback);
        }
        return z;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        JL_Log.d(TAG, "send ble data thread is started.");
        OnThreadStateListener onThreadStateListener = this.mListener;
        if (onThreadStateListener != null) {
            onThreadStateListener.onStart(getId(), getName());
        }
        if (this.mBleManager != null) {
            synchronized (this.mQueue) {
                while (this.isDataSend) {
                    this.mCurrentTask = null;
                    this.isThreadWaiting = false;
                    this.isWaitingForCallback = false;
                    if (this.mQueue.isEmpty()) {
                        this.isThreadWaiting = true;
                        JL_Log.d(TAG, "queue is empty, so waiting for data");
                        try {
                            this.mQueue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        BleSendTask peek = this.mQueue.peek();
                        this.mCurrentTask = peek;
                        if (peek != null) {
                            this.isWaitingForCallback = this.mBleManager.writeDataByBleSync(peek.mDevice, this.mCurrentTask.getServiceUUID(), this.mCurrentTask.getCharacteristicUUID(), this.mCurrentTask.getData());
                            if (this.isWaitingForCallback) {
                                try {
                                    this.mQueue.wait(6000L);
                                } catch (InterruptedException e2) {
                                    e2.printStackTrace();
                                }
                            } else {
                                this.mCurrentTask.setStatus(-1);
                            }
                            JL_Log.d(TAG, "data send ret :" + this.mCurrentTask.getStatus());
                            if (this.mCurrentTask.getStatus() != 0) {
                                this.retryNum++;
                                if (this.retryNum >= 3) {
                                    callbackResult(this.mCurrentTask, false);
                                } else if (this.mCurrentTask.getStatus() != -1) {
                                    this.mCurrentTask.setStatus(-1);
                                    try {
                                        Thread.sleep(10L);
                                    } catch (InterruptedException e3) {
                                        e3.printStackTrace();
                                    }
                                }
                            } else {
                                callbackResult(this.mCurrentTask, true);
                            }
                        }
                        this.retryNum = 0;
                        this.mQueue.poll();
                    }
                }
            }
            this.isWaitingForCallback = false;
            this.isThreadWaiting = false;
            this.mQueue.clear();
            OnThreadStateListener onThreadStateListener2 = this.mListener;
            if (onThreadStateListener2 != null) {
                onThreadStateListener2.onEnd(getId(), getName());
            }
            JL_Log.d(TAG, "send ble data thread exit.");
        }
    }

    @Override // java.lang.Thread
    public synchronized void start() {
        this.isDataSend = true;
        super.start();
    }

    public synchronized void stopThread() {
        this.isDataSend = false;
        wakeupSendThread(null);
    }

    public void wakeupSendThread(BleSendTask bleSendTask) {
        BleSendTask bleSendTask2;
        if (bleSendTask == null || ((bleSendTask2 = this.mCurrentTask) != null && bleSendTask2.equals(bleSendTask))) {
            if (bleSendTask != null) {
                bleSendTask.setCallback(this.mCurrentTask.getCallback());
                this.mCurrentTask = bleSendTask;
            }
            synchronized (this.mQueue) {
                if (this.isThreadWaiting) {
                    if (this.isWaitingForCallback) {
                        this.mQueue.notifyAll();
                    } else {
                        this.mQueue.notify();
                    }
                } else if (this.isWaitingForCallback) {
                    this.mQueue.notify();
                }
            }
        }
    }
}
