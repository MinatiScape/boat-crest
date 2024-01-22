package com.jieli.bluetooth_connect.impl;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import androidx.annotation.IntRange;
import com.jieli.bluetooth_connect.bean.BluetoothOption;
import com.jieli.bluetooth_connect.bean.ErrorInfo;
import com.jieli.bluetooth_connect.interfaces.IBluetoothPair;
import com.jieli.bluetooth_connect.interfaces.listener.OnBtDevicePairListener;
import com.jieli.bluetooth_connect.tool.BtPairEventCbManager;
import com.jieli.bluetooth_connect.util.BluetoothUtil;
import com.jieli.bluetooth_connect.util.ConnectUtil;
import com.jieli.bluetooth_connect.util.JL_Log;
import java.util.concurrent.LinkedBlockingQueue;
/* loaded from: classes11.dex */
public class BluetoothPair implements IBluetoothPair {
    private static final int MSG_PAIR_TASK_TIMEOUT = 1014;
    private static final String TAG = "BluetoothPair";
    private BluetoothPairReceiver mBluetoothPairReceiver;
    private final Context mContext;
    private PairBtDeviceThread mPairBtDeviceThread;
    private volatile BluetoothDevice mPairingDevice;
    private final Handler mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.jieli.bluetooth_connect.impl.f
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            boolean lambda$new$0;
            lambda$new$0 = BluetoothPair.this.lambda$new$0(message);
            return lambda$new$0;
        }
    });
    private final BtPairEventCbManager mBtPairEventCbManager = new BtPairEventCbManager();

    /* loaded from: classes11.dex */
    public class BluetoothPairReceiver extends BroadcastReceiver {
        private BluetoothPairReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        @SuppressLint({"MissingPermission"})
        public void onReceive(Context context, Intent intent) {
            BluetoothDevice bluetoothDevice;
            if (intent != null) {
                String action = intent.getAction();
                if (TextUtils.isEmpty(action)) {
                    return;
                }
                action.hashCode();
                if (!action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                    if (action.equals("android.bluetooth.device.action.BOND_STATE_CHANGED") && (bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")) != null && ConnectUtil.isHasConnectPermission(context)) {
                        int bondState = bluetoothDevice.getBondState();
                        String str = BluetoothPair.TAG;
                        JL_Log.i(str, "recv action :ACTION_BOND_STATE_CHANGED ... device : " + BluetoothUtil.printBtDeviceInfo(context, bluetoothDevice) + " ,bound : " + bondState);
                        if (bondState == 10 || bondState == 12) {
                            BluetoothPair.this.stopPairTimeout(bluetoothDevice);
                            BluetoothPair.this.wakeupPairTaskThread(bluetoothDevice);
                        }
                        BluetoothPair.this.mBtPairEventCbManager.onBtDeviceBond(bluetoothDevice, bondState);
                        return;
                    }
                    return;
                }
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
                if (intExtra != -1) {
                    if (intExtra == 10) {
                        BluetoothPair.this.stopPairTaskThread();
                        BluetoothPair bluetoothPair = BluetoothPair.this;
                        bluetoothPair.onError(bluetoothPair.mPairingDevice, new ErrorInfo(2, "bluetooth adapter is close."));
                        BluetoothPair bluetoothPair2 = BluetoothPair.this;
                        bluetoothPair2.stopPairTimeout(bluetoothPair2.mPairingDevice);
                    }
                    BluetoothPair.this.mBtPairEventCbManager.onAdapterStatus(intExtra == 12, BluetoothUtil.hasBle(BluetoothPair.this.mContext));
                }
            }
        }
    }

    /* loaded from: classes11.dex */
    public class PairBtDeviceThread extends Thread {
        private boolean isThreadRunning;
        private boolean isWaiting;
        private boolean isWaitingForResult;
        private BluetoothDevice mPairTaskDevice;
        private final LinkedBlockingQueue<PairTask> mPairTaskQueue;

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:25:0x001e A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean addPairTask(com.jieli.bluetooth_connect.impl.BluetoothPair.PairTask r4) {
            /*
                r3 = this;
                r0 = 0
                if (r4 == 0) goto Le
                java.util.concurrent.LinkedBlockingQueue<com.jieli.bluetooth_connect.impl.BluetoothPair$PairTask> r1 = r3.mPairTaskQueue     // Catch: java.lang.InterruptedException -> La
                r1.put(r4)     // Catch: java.lang.InterruptedException -> La
                r4 = 1
                goto Lf
            La:
                r4 = move-exception
                r4.printStackTrace()
            Le:
                r4 = r0
            Lf:
                if (r4 == 0) goto L31
                boolean r1 = r3.isWaiting
                if (r1 == 0) goto L31
                boolean r1 = r3.isWaitingForResult
                if (r1 != 0) goto L31
                r3.isWaiting = r0
                java.util.concurrent.LinkedBlockingQueue<com.jieli.bluetooth_connect.impl.BluetoothPair$PairTask> r0 = r3.mPairTaskQueue
                monitor-enter(r0)
                java.lang.String r1 = com.jieli.bluetooth_connect.impl.BluetoothPair.access$500()     // Catch: java.lang.Throwable -> L2e
                java.lang.String r2 = "=PairBtDeviceThread= -addPairTask- notify"
                com.jieli.bluetooth_connect.util.JL_Log.i(r1, r2)     // Catch: java.lang.Throwable -> L2e
                java.util.concurrent.LinkedBlockingQueue<com.jieli.bluetooth_connect.impl.BluetoothPair$PairTask> r1 = r3.mPairTaskQueue     // Catch: java.lang.Throwable -> L2e
                r1.notify()     // Catch: java.lang.Throwable -> L2e
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L2e
                goto L31
            L2e:
                r4 = move-exception
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L2e
                throw r4
            L31:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jieli.bluetooth_connect.impl.BluetoothPair.PairBtDeviceThread.addPairTask(com.jieli.bluetooth_connect.impl.BluetoothPair$PairTask):boolean");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void stopThread() {
            JL_Log.i(BluetoothPair.TAG, "---stopThread---");
            this.isThreadRunning = false;
            this.mPairTaskDevice = null;
            wakeUp(null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void wakeUp(BluetoothDevice bluetoothDevice) {
            if (bluetoothDevice == null || BluetoothUtil.deviceEquals(this.mPairTaskDevice, bluetoothDevice)) {
                synchronized (this.mPairTaskQueue) {
                    if (this.isWaitingForResult) {
                        if (this.isWaiting) {
                            this.mPairTaskQueue.notifyAll();
                        } else {
                            this.mPairTaskQueue.notify();
                        }
                    } else if (this.isWaiting) {
                        this.mPairTaskQueue.notify();
                    }
                }
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            boolean unPair;
            JL_Log.i(BluetoothPair.TAG, "PairBtDeviceThread start..");
            this.isThreadRunning = true;
            synchronized (this.mPairTaskQueue) {
                while (this.isThreadRunning) {
                    this.isWaitingForResult = false;
                    this.mPairTaskDevice = null;
                    if (this.mPairTaskQueue.isEmpty()) {
                        this.isWaiting = true;
                        JL_Log.i(BluetoothPair.TAG, "-PairBtDeviceThread- mPairTaskQueue is empty, wait ...");
                        try {
                            this.mPairTaskQueue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        JL_Log.i(BluetoothPair.TAG, "-PairBtDeviceThread- mPairTaskQueue is not empty, notify ...");
                    } else {
                        this.isWaiting = false;
                        PairTask peek = this.mPairTaskQueue.peek();
                        if (peek == null) {
                            JL_Log.i(BluetoothPair.TAG, "-PairBtDeviceThread- mPairTask is null,  poll...");
                        } else {
                            this.mPairTaskDevice = peek.getDevice();
                            if (peek.getOp() == 0) {
                                unPair = BluetoothPair.this.pair(this.mPairTaskDevice, peek.getPairWay());
                            } else {
                                unPair = BluetoothPair.this.unPair(this.mPairTaskDevice);
                            }
                            String str = BluetoothPair.TAG;
                            JL_Log.i(str, "-PairBtDeviceThread- do task ret : " + unPair);
                            if (!unPair) {
                                BluetoothPair bluetoothPair = BluetoothPair.this;
                                BluetoothDevice bluetoothDevice = this.mPairTaskDevice;
                                bluetoothPair.onError(bluetoothDevice, new ErrorInfo(3, bluetoothDevice == null ? "" : bluetoothDevice.getAddress()));
                            } else {
                                this.isWaitingForResult = true;
                                JL_Log.i(BluetoothPair.TAG, "-PairBtDeviceThread- wait for system callback");
                                try {
                                    this.mPairTaskQueue.wait(30000L);
                                } catch (InterruptedException e2) {
                                    e2.printStackTrace();
                                }
                                JL_Log.i(BluetoothPair.TAG, "-PairBtDeviceThread- system callback, notify and poll ...");
                            }
                        }
                        this.mPairTaskQueue.poll();
                    }
                }
            }
            this.mPairTaskQueue.clear();
            BluetoothPair.this.mPairBtDeviceThread = null;
            JL_Log.i(BluetoothPair.TAG, "PairBtDeviceThread exit..");
        }

        private PairBtDeviceThread() {
            super("PairBtDeviceThread");
            this.mPairTaskQueue = new LinkedBlockingQueue<>();
        }
    }

    /* loaded from: classes11.dex */
    public static class PairTask {
        private static final int OP_CANCEL_PAIR = 1;
        private static final int OP_PAIR = 0;
        private final BluetoothDevice mDevice;
        private final int mOp;
        private int pairWay = 0;

        public PairTask(int i, BluetoothDevice bluetoothDevice) {
            this.mOp = i;
            this.mDevice = bluetoothDevice;
        }

        public BluetoothDevice getDevice() {
            return this.mDevice;
        }

        public int getOp() {
            return this.mOp;
        }

        public int getPairWay() {
            return this.pairWay;
        }

        public void setPairWay(int i) {
            this.pairWay = i;
        }

        public String toString() {
            return "PairTask{mOp=" + this.mOp + ", mDevice=" + this.mDevice + ", pairWay=" + this.pairWay + '}';
        }
    }

    public BluetoothPair(Context context, OnBtDevicePairListener onBtDevicePairListener) {
        this.mContext = (Context) ConnectUtil.checkNotNull(context);
        addListener(onBtDevicePairListener);
        registerReceiver();
        startPairTaskThread();
    }

    private boolean addPairTask(PairTask pairTask) {
        if (pairTask != null) {
            startPairTaskThread();
            return this.mPairBtDeviceThread.addPairTask(pairTask);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$new$0(Message message) {
        BluetoothDevice bluetoothDevice;
        if (message.what != 1014 || (bluetoothDevice = (BluetoothDevice) message.obj) == null) {
            return false;
        }
        wakeupPairTaskThread(bluetoothDevice);
        onError(bluetoothDevice, new ErrorInfo(4, bluetoothDevice.getAddress()));
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onError(BluetoothDevice bluetoothDevice, ErrorInfo errorInfo) {
        this.mBtPairEventCbManager.onPairError(bluetoothDevice, errorInfo);
    }

    private void registerReceiver() {
        if (this.mBluetoothPairReceiver == null) {
            this.mBluetoothPairReceiver = new BluetoothPairReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
            this.mContext.registerReceiver(this.mBluetoothPairReceiver, intentFilter);
        }
    }

    private void setPairingDevice(BluetoothDevice bluetoothDevice) {
        this.mPairingDevice = bluetoothDevice;
    }

    private void startPairTaskThread() {
        if (this.mPairBtDeviceThread == null) {
            PairBtDeviceThread pairBtDeviceThread = new PairBtDeviceThread();
            this.mPairBtDeviceThread = pairBtDeviceThread;
            pairBtDeviceThread.start();
        }
    }

    private void startPairTimeOut(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            onError(null, new ErrorInfo(1, "param is error."));
        } else if (this.mHandler.hasMessages(1014)) {
            onError(bluetoothDevice, new ErrorInfo(5, "device is pairing."));
        } else {
            Message obtainMessage = this.mHandler.obtainMessage(1014, bluetoothDevice);
            setPairingDevice(bluetoothDevice);
            this.mHandler.sendMessageDelayed(obtainMessage, 30000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopPairTaskThread() {
        PairBtDeviceThread pairBtDeviceThread = this.mPairBtDeviceThread;
        if (pairBtDeviceThread != null) {
            pairBtDeviceThread.stopThread();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopPairTimeout(BluetoothDevice bluetoothDevice) {
        if (this.mHandler.hasMessages(1014) && BluetoothUtil.deviceEquals(bluetoothDevice, this.mPairingDevice)) {
            this.mHandler.removeMessages(1014);
            setPairingDevice(null);
        }
    }

    private void unregisterReceiver() {
        BluetoothPairReceiver bluetoothPairReceiver = this.mBluetoothPairReceiver;
        if (bluetoothPairReceiver != null) {
            this.mContext.unregisterReceiver(bluetoothPairReceiver);
            this.mBluetoothPairReceiver = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void wakeupPairTaskThread(BluetoothDevice bluetoothDevice) {
        PairBtDeviceThread pairBtDeviceThread = this.mPairBtDeviceThread;
        if (pairBtDeviceThread != null) {
            pairBtDeviceThread.wakeUp(bluetoothDevice);
        }
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBase
    public void destroy() {
        unregisterReceiver();
        stopPairTaskThread();
        this.mBtPairEventCbManager.destroy();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothPair
    @SuppressLint({"MissingPermission"})
    public boolean isPaired(BluetoothDevice bluetoothDevice) {
        return ConnectUtil.isHasConnectPermission(this.mContext) && bluetoothDevice != null && 12 == bluetoothDevice.getBondState();
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothPair
    @SuppressLint({"MissingPermission"})
    public boolean isPairing(BluetoothDevice bluetoothDevice) {
        return ConnectUtil.isHasConnectPermission(this.mContext) && bluetoothDevice != null && 11 == bluetoothDevice.getBondState();
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothPair
    @SuppressLint({"MissingPermission"})
    public boolean pair(BluetoothDevice bluetoothDevice) {
        int i = 0;
        if (bluetoothDevice != null && ConnectUtil.isHasConnectPermission(this.mContext)) {
            if (bluetoothDevice.getType() == 3 && Build.VERSION.SDK_INT >= 23) {
                i = 1;
            }
            return pair(bluetoothDevice, i);
        }
        JL_Log.w(TAG, "-pair- device is null");
        return false;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBase
    public void setBluetoothOption(BluetoothOption bluetoothOption) {
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothPair
    public boolean tryToPair(BluetoothDevice bluetoothDevice) {
        return tryToPair(bluetoothDevice, 0);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothPair
    public boolean tryToUnPair(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice != null) {
            return addPairTask(new PairTask(1, bluetoothDevice));
        }
        return false;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothPair
    @SuppressLint({"MissingPermission"})
    public boolean unPair(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            JL_Log.e(TAG, "-unPair- device is null. ");
            return false;
        }
        boolean removeBond = BluetoothUtil.removeBond(this.mContext, bluetoothDevice);
        String str = TAG;
        JL_Log.w(str, "-unPair- result : " + removeBond);
        if (removeBond) {
            startPairTimeOut(bluetoothDevice);
            return true;
        }
        return false;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBase
    public void addListener(OnBtDevicePairListener onBtDevicePairListener) {
        this.mBtPairEventCbManager.addListener(onBtDevicePairListener);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBase
    public void removeListener(OnBtDevicePairListener onBtDevicePairListener) {
        this.mBtPairEventCbManager.removeListener(onBtDevicePairListener);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothPair
    public boolean tryToPair(BluetoothDevice bluetoothDevice, @IntRange(from = 0, to = 2) int i) {
        if (bluetoothDevice != null) {
            PairTask pairTask = new PairTask(0, bluetoothDevice);
            pairTask.setPairWay(i);
            return addPairTask(pairTask);
        }
        return false;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothPair
    @SuppressLint({"MissingPermission"})
    public boolean pair(BluetoothDevice bluetoothDevice, @IntRange(from = 0, to = 2) int i) {
        boolean createBond;
        if (bluetoothDevice != null && ConnectUtil.isHasConnectPermission(this.mContext)) {
            if (bluetoothDevice.getType() == 3 && Build.VERSION.SDK_INT >= 23) {
                createBond = BluetoothUtil.createBond(this.mContext, bluetoothDevice, i);
                String str = TAG;
                JL_Log.w(str, "-pair- createBond ret = " + createBond + ", pairWay = " + i);
            } else {
                createBond = BluetoothUtil.createBond(this.mContext, bluetoothDevice);
                String str2 = TAG;
                JL_Log.w(str2, "-pair- createBond ret = " + createBond);
            }
            if (createBond) {
                startPairTimeOut(bluetoothDevice);
                return true;
            }
            return false;
        }
        JL_Log.w(TAG, "-pair- device is null");
        return false;
    }
}
