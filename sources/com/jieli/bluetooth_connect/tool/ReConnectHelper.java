package com.jieli.bluetooth_connect.tool;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.jieli.bluetooth_connect.bean.ble.BleScanMessage;
import com.jieli.bluetooth_connect.bean.history.HistoryRecord;
import com.jieli.bluetooth_connect.impl.BluetoothManager;
import com.jieli.bluetooth_connect.interfaces.IBluetoothOperation;
import com.jieli.bluetooth_connect.interfaces.callback.BluetoothEventCallback;
import com.jieli.bluetooth_connect.interfaces.callback.OnHistoryRecordCallback;
import com.jieli.bluetooth_connect.util.BluetoothUtil;
import com.jieli.bluetooth_connect.util.ConnectUtil;
import com.jieli.bluetooth_connect.util.JL_Log;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes11.dex */
public class ReConnectHelper {
    private static final long DEFAULT_TASK_TIMEOUT = 36000;
    private static final int FAIL_CONNECT_MAX = 3;
    private static final int MSG_RECONNECT_TIMEOUT = 39270;
    private static final long RECONNECT_TIMEOUT = 40000;
    private static final long SCAN_LIMIT = 16000;
    private static final String TAG = "ReConnectHelper";
    private static int sTaskID = 1;
    private boolean isReStartTask;
    private long leftTime;
    private final BluetoothEventCallback mBluetoothEventCallback;
    private final IBluetoothOperation mBtOp;
    private final Context mContext;
    private long startTime;
    private final List<ReconnectTask> mTaskList = new CopyOnWriteArrayList();
    private final Handler mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.jieli.bluetooth_connect.tool.a
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            boolean lambda$new$0;
            lambda$new$0 = ReConnectHelper.this.lambda$new$0(message);
            return lambda$new$0;
        }
    });

    /* loaded from: classes11.dex */
    public static class ReconnectTask {
        public static final int TASK_TYPE_DEVICE = 1;
        public static final int TASK_TYPE_HISTORY = 2;
        private String address;
        private OnHistoryRecordCallback callback;
        private int connectFailCount;
        private int connectWay;
        private int taskId;
        private int taskTimeout;
        private int taskType;

        public ReconnectTask(String str) {
            setAddress(str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ReconnectTask reconnectTask = (ReconnectTask) obj;
            return this.taskType == reconnectTask.taskType && Objects.equals(this.address, reconnectTask.address);
        }

        @NonNull
        public String getAddress() {
            return this.address;
        }

        public OnHistoryRecordCallback getCallback() {
            return this.callback;
        }

        public int getConnectFailCount() {
            return this.connectFailCount;
        }

        public int getConnectWay() {
            return this.connectWay;
        }

        public int getTaskId() {
            return this.taskId;
        }

        public int getTaskTimeout() {
            return this.taskTimeout;
        }

        public int getTaskType() {
            return this.taskType;
        }

        public int hashCode() {
            return Objects.hash(this.address, Integer.valueOf(this.connectWay), Integer.valueOf(this.taskType));
        }

        public void setAddress(@NonNull String str) {
            this.address = str;
        }

        public void setCallback(OnHistoryRecordCallback onHistoryRecordCallback) {
            this.callback = onHistoryRecordCallback;
        }

        public void setConnectFailCount(int i) {
            this.connectFailCount = i;
        }

        public void setConnectWay(int i) {
            this.connectWay = i;
        }

        public void setTaskId(int i) {
            this.taskId = i;
        }

        public void setTaskTimeout(int i) {
            this.taskTimeout = i;
        }

        public void setTaskType(int i) {
            this.taskType = i;
        }

        @NonNull
        public String toString() {
            return "ReconnectTask{taskId=" + this.taskId + ", address='" + this.address + "', connectWay=" + this.connectWay + ", taskType=" + this.taskType + ", taskTimeout=" + this.taskTimeout + ", connectFailCount=" + this.connectFailCount + '}';
        }
    }

    public ReConnectHelper(Context context, IBluetoothOperation iBluetoothOperation) {
        BluetoothEventCallback bluetoothEventCallback = new BluetoothEventCallback() { // from class: com.jieli.bluetooth_connect.tool.ReConnectHelper.1
            @Override // com.jieli.bluetooth_connect.interfaces.callback.BluetoothEventCallback, com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
            public void onAdapterStatus(boolean z, boolean z2) {
                if (z && ReConnectHelper.this.isReconnecting() && ReConnectHelper.this.isReStartTask) {
                    ReConnectHelper.this.reconnectTask();
                }
            }

            @Override // com.jieli.bluetooth_connect.interfaces.callback.BluetoothEventCallback, com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
            public void onConnection(BluetoothDevice bluetoothDevice, int i) {
                boolean isReconnectDev = ReConnectHelper.this.isReconnectDev(bluetoothDevice);
                String str = ReConnectHelper.TAG;
                JL_Log.i(str, "-onConnection- device ： " + bluetoothDevice + ", status : " + i + ", isReconnectDevice = " + isReconnectDev + ", isReStartTask = " + ReConnectHelper.this.isReStartTask);
                if (isReconnectDev) {
                    ReConnectHelper.this.isReStartTask = false;
                    if (i == 2) {
                        ReConnectHelper.this.removeTask(bluetoothDevice.getAddress(), true);
                    } else if (i == 0) {
                        ReConnectHelper.this.resumeTask(bluetoothDevice.getAddress());
                    }
                } else if (ReConnectHelper.this.isReStartTask) {
                    ReConnectHelper.this.reconnectTask();
                }
            }

            @Override // com.jieli.bluetooth_connect.interfaces.callback.BluetoothEventCallback, com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
            public void onDiscovery(BluetoothDevice bluetoothDevice, BleScanMessage bleScanMessage) {
                ReconnectTask findReconnectTask;
                if (bluetoothDevice == null) {
                    return;
                }
                boolean isReconnecting = ReConnectHelper.this.isReconnecting();
                String otaBleAddress = bleScanMessage.getOtaBleAddress();
                String str = ReConnectHelper.TAG;
                JL_Log.i(str, "-onDiscovery- device ： " + BluetoothUtil.printBtDeviceInfo(ReConnectHelper.this.mContext, bluetoothDevice) + ", isOTA: " + bleScanMessage.isOTA() + ", getOtaBleAddress : " + otaBleAddress);
                boolean z = false;
                if (bleScanMessage.isOTA() && otaBleAddress != null) {
                    String str2 = ReConnectHelper.TAG;
                    JL_Log.i(str2, "-onDiscovery- device ： " + bluetoothDevice + ", isOTA =true  , getOtaBleAddress : " + otaBleAddress);
                    ReconnectTask findReconnectTask2 = ReConnectHelper.this.findReconnectTask(otaBleAddress);
                    if (findReconnectTask2 == null) {
                        otaBleAddress = bluetoothDevice.getAddress();
                        findReconnectTask2 = ReConnectHelper.this.findReconnectTask(bluetoothDevice.getAddress());
                    }
                    boolean z2 = findReconnectTask2 != null;
                    if (z2) {
                        findReconnectTask2.setConnectWay(0);
                    }
                    z = z2;
                }
                if (!z) {
                    otaBleAddress = bluetoothDevice.getAddress();
                    z = ReConnectHelper.this.isReconnectDev(bluetoothDevice);
                }
                String str3 = ReConnectHelper.TAG;
                JL_Log.i(str3, "-onDiscovery- device ： " + bluetoothDevice + ", isReconnecting : " + isReconnecting + ", isReconnectDevice = " + z);
                if (isReconnecting && z && (findReconnectTask = ReConnectHelper.this.findReconnectTask(otaBleAddress)) != null) {
                    ReConnectHelper.this.pauseTask(otaBleAddress);
                    if (bleScanMessage.isOTA()) {
                        HistoryRecord historyRecord = ReConnectHelper.this.mBtOp.getHistoryRecord(otaBleAddress);
                        if (historyRecord != null) {
                            historyRecord.setUpdateAddress(bluetoothDevice.getAddress());
                            ((BluetoothManager) ReConnectHelper.this.mBtOp).getHistoryRecordHelper().updateHistoryRecord(historyRecord);
                        }
                    } else {
                        bluetoothDevice = ReConnectHelper.this.findTargetDevice(bluetoothDevice, bleScanMessage, findReconnectTask.getConnectWay());
                    }
                    ReConnectHelper.this.mBtOp.connectBtDevice(bluetoothDevice, findReconnectTask.getConnectWay());
                }
            }

            @Override // com.jieli.bluetooth_connect.interfaces.callback.BluetoothEventCallback, com.jieli.bluetooth_connect.interfaces.callback.IBluetoothEventCallback
            public void onDiscoveryStatus(boolean z, boolean z2) {
                String str = ReConnectHelper.TAG;
                JL_Log.i(str, "-onDiscoveryStatus- bStart ： " + z2 + ", isReconnecting : " + ReConnectHelper.this.isReconnecting());
                if (z2 || !ReConnectHelper.this.isReconnecting()) {
                    return;
                }
                ReConnectHelper.this.reconnectTask(false);
            }
        };
        this.mBluetoothEventCallback = bluetoothEventCallback;
        this.mContext = (Context) ConnectUtil.checkNotNull(context);
        IBluetoothOperation iBluetoothOperation2 = (IBluetoothOperation) ConnectUtil.checkNotNull(iBluetoothOperation);
        this.mBtOp = iBluetoothOperation2;
        iBluetoothOperation2.registerBluetoothCallback(bluetoothEventCallback);
    }

    private void addTask(ReconnectTask reconnectTask) {
        boolean z = false;
        r0 = 0;
        int i = 0;
        z = false;
        if (reconnectTask != null && !this.mTaskList.contains(reconnectTask)) {
            if (reconnectTask.getTaskType() == 1) {
                if (!this.mTaskList.isEmpty()) {
                    Iterator it = new ArrayList(this.mTaskList).iterator();
                    while (it.hasNext()) {
                        if (1 == ((ReconnectTask) it.next()).getTaskType()) {
                            i++;
                        }
                    }
                }
                this.mTaskList.add(i, reconnectTask);
                z = true;
            } else {
                z = this.mTaskList.add(reconnectTask);
            }
        }
        if (z) {
            reconnectTask.setTaskId(autoIncTaskId());
            if (reconnectTask.getTaskTimeout() == 0) {
                reconnectTask.setTaskTimeout(36000);
            }
            Handler handler = this.mHandler;
            handler.sendMessageDelayed(handler.obtainMessage(reconnectTask.getTaskId(), reconnectTask.getAddress()), reconnectTask.getTaskTimeout());
            startReconnectTask();
        }
    }

    private static int autoIncTaskId() {
        int i = sTaskID;
        int i2 = i + 1;
        sTaskID = i2;
        if (i2 >= 256) {
            sTaskID = 1;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: doReconnectEvent */
    public void lambda$doReconnectEvent$2(final ReconnectTask reconnectTask) {
        boolean startDeviceScan;
        if (!BluetoothUtil.isBluetoothEnable()) {
            JL_Log.w(TAG, "-doReconnectEvent- bluetooth enable");
            this.isReStartTask = true;
        } else if (this.mBtOp.isConnecting()) {
            this.isReStartTask = true;
            String str = TAG;
            JL_Log.w(str, "-doReconnectEvent- isConnecting ： " + this.mBtOp.isConnecting());
        } else {
            if (reconnectTask.getTaskType() != 2 || this.mBtOp.getConnectedDevice() == null || this.mBtOp.getBluetoothOption().isUseMultiDevice()) {
                BluetoothDevice findSysConnectedDevice = findSysConnectedDevice(reconnectTask.address);
                String str2 = TAG;
                JL_Log.i(str2, "-doReconnectEvent- device ： " + BluetoothUtil.printBtDeviceInfo(this.mContext, findSysConnectedDevice) + ", reconnectTask = " + reconnectTask);
                if (findSysConnectedDevice != null && !this.mBtOp.connectBtDevice(findSysConnectedDevice, reconnectTask.getConnectWay())) {
                    int connectFailCount = reconnectTask.getConnectFailCount() + 1;
                    if (connectFailCount < 3) {
                        reconnectTask.setConnectFailCount(connectFailCount);
                        this.mHandler.postDelayed(new Runnable() { // from class: com.jieli.bluetooth_connect.tool.c
                            @Override // java.lang.Runnable
                            public final void run() {
                                ReConnectHelper.this.lambda$doReconnectEvent$1(reconnectTask);
                            }
                        }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
                        return;
                    }
                    removeTask(reconnectTask, false);
                    return;
                }
                if (this.mBtOp.isScanning()) {
                    boolean z = this.mBtOp.getScanType() == 2;
                    if (!z) {
                        z = (reconnectTask.getConnectWay() == 1 && this.mBtOp.getScanType() == 1) || (reconnectTask.getConnectWay() == 0 && this.mBtOp.getScanType() == 0);
                    }
                    JL_Log.i(str2, "-doReconnectEvent- isScanOk : " + z);
                    if (z) {
                        return;
                    }
                    stopScan();
                    try {
                        Thread.sleep(50L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                getNowLeftTime();
                String str3 = TAG;
                JL_Log.d(str3, "-doReconnectEvent- leftTime ： " + this.leftTime + ", startTime : " + this.startTime);
                if (this.leftTime >= 24000) {
                    if (reconnectTask.getConnectWay() == 1) {
                        startDeviceScan = this.mBtOp.startDeviceScan(SCAN_LIMIT);
                        JL_Log.i(str3, "-doReconnectEvent- startDeviceScan ： " + startDeviceScan);
                    } else {
                        startDeviceScan = this.mBtOp.startBLEScan(SCAN_LIMIT);
                        JL_Log.i(str3, "-doReconnectEvent- startBLEScan ： " + startDeviceScan);
                    }
                } else {
                    int i = reconnectTask.getConnectWay() == 1 ? 0 : 2;
                    startDeviceScan = this.mBtOp.startDeviceScan(i, this.leftTime);
                    JL_Log.i(str3, "-doReconnectEvent- startAllDeviceScan ： " + startDeviceScan + ", way = " + i);
                }
                if (startDeviceScan) {
                    return;
                }
                this.mHandler.postDelayed(new Runnable() { // from class: com.jieli.bluetooth_connect.tool.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        ReConnectHelper.this.lambda$doReconnectEvent$2(reconnectTask);
                    }
                }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
                return;
            }
            JL_Log.w(TAG, "-doReconnectEvent- single device manager ");
            stopReconnect();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ReconnectTask findReconnectTask(String str) {
        if (BluetoothAdapter.checkBluetoothAddress(str) && !this.mTaskList.isEmpty()) {
            Iterator it = new ArrayList(this.mTaskList).iterator();
            while (it.hasNext()) {
                ReconnectTask reconnectTask = (ReconnectTask) it.next();
                if (isMatchAddress(reconnectTask.getAddress(), str)) {
                    return reconnectTask;
                }
            }
            return null;
        }
        return null;
    }

    private BluetoothDevice findSysConnectedDevice(String str) {
        List<BluetoothDevice> systemConnectedBtDeviceList = BluetoothUtil.getSystemConnectedBtDeviceList(this.mContext);
        if (systemConnectedBtDeviceList != null) {
            for (BluetoothDevice bluetoothDevice : systemConnectedBtDeviceList) {
                if (bluetoothDevice.getAddress().equals(str)) {
                    return bluetoothDevice;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"MissingPermission"})
    public BluetoothDevice findTargetDevice(@NonNull BluetoothDevice bluetoothDevice, @NonNull BleScanMessage bleScanMessage, int i) {
        HistoryRecord historyRecord;
        if (i == 0) {
            if (ConnectUtil.isHasConnectPermission(this.mContext) && bluetoothDevice.getType() == 1) {
                BluetoothDevice bluetoothDevice2 = null;
                HistoryRecord historyRecord2 = this.mBtOp.getHistoryRecord(bluetoothDevice.getAddress());
                if (historyRecord2 != null) {
                    bluetoothDevice2 = BluetoothUtil.getRemoteDevice(this.mContext, historyRecord2.getConnectType() == 0 ? historyRecord2.getAddress() : historyRecord2.getMappedAddress());
                }
                return (bluetoothDevice2 == null || BluetoothUtil.deviceEquals(bluetoothDevice, bluetoothDevice2)) ? bluetoothDevice : bluetoothDevice2;
            }
            return bluetoothDevice;
        }
        BluetoothDevice remoteDevice = BluetoothUtil.getRemoteDevice(this.mContext, bleScanMessage.getEdrAddr());
        if (remoteDevice == null && (historyRecord = this.mBtOp.getHistoryRecord(bluetoothDevice.getAddress())) != null) {
            remoteDevice = BluetoothUtil.getRemoteDevice(this.mContext, historyRecord.getConnectType() == 1 ? historyRecord.getAddress() : historyRecord.getMappedAddress());
        }
        return remoteDevice != null ? remoteDevice : bluetoothDevice;
    }

    private long getCurrentTime() {
        return Calendar.getInstance().getTimeInMillis();
    }

    private void getNowLeftTime() {
        this.leftTime = RECONNECT_TIMEOUT - (getCurrentTime() - this.startTime);
    }

    private boolean isMatchAddress(String str, String str2) {
        if (BluetoothAdapter.checkBluetoothAddress(str) && BluetoothAdapter.checkBluetoothAddress(str2)) {
            HistoryRecord historyRecord = this.mBtOp.getHistoryRecord(str);
            if (historyRecord == null) {
                return str.equals(str2);
            }
            return str2.equals(historyRecord.getAddress()) || str2.equals(historyRecord.getMappedAddress()) || str2.equals(historyRecord.getUpdateAddress());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$new$0(Message message) {
        if (message.what == MSG_RECONNECT_TIMEOUT) {
            this.isReStartTask = false;
            if (this.mTaskList.isEmpty()) {
                return true;
            }
            startReconnectTask();
            return true;
        }
        Object obj = message.obj;
        if (obj instanceof String) {
            String str = (String) obj;
            String str2 = TAG;
            JL_Log.w(str2, "-timeout- address ： " + str);
            removeTask(str, false);
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pauseTask(String str) {
        if (isReconnecting()) {
            getNowLeftTime();
            String str2 = TAG;
            JL_Log.w(str2, "-pauseTask- leftTime ： " + this.leftTime + ", startTime : " + this.startTime + ", " + str);
            ReconnectTask findReconnectTask = findReconnectTask(str);
            if (findReconnectTask == null) {
                return;
            }
            this.mHandler.removeMessages(findReconnectTask.getTaskId());
            this.mHandler.removeMessages(MSG_RECONNECT_TIMEOUT);
            stopScan();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reconnectTask() {
        reconnectTask(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeTask(String str, boolean z) {
        ReconnectTask findReconnectTask = findReconnectTask(str);
        if (findReconnectTask != null) {
            removeTask(findReconnectTask, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resumeTask(String str) {
        ReconnectTask findReconnectTask;
        String str2 = TAG;
        JL_Log.w(str2, "-resumeTask- LeftTime ： " + this.leftTime);
        if (this.leftTime <= 0 || (findReconnectTask = findReconnectTask(str)) == null) {
            return;
        }
        Handler handler = this.mHandler;
        handler.sendMessageDelayed(handler.obtainMessage(findReconnectTask.getTaskId(), findReconnectTask.getAddress()), this.leftTime);
        startReconnectTask();
    }

    private void setStartTime(long j) {
        this.startTime = j;
    }

    private void startReconnectTask() {
        if (this.mTaskList.isEmpty() || isReconnecting()) {
            return;
        }
        this.mHandler.sendEmptyMessageDelayed(MSG_RECONNECT_TIMEOUT, RECONNECT_TIMEOUT);
        reconnectTask();
    }

    private void stopScan() {
        this.mBtOp.stopDeviceScan();
        this.mBtOp.stopBLEScan();
    }

    public void destroy() {
        this.mBtOp.unregisterBluetoothCallback(this.mBluetoothEventCallback);
        this.mHandler.removeCallbacksAndMessages(null);
    }

    public boolean isReconnectDev(BluetoothDevice bluetoothDevice) {
        return (bluetoothDevice == null || findReconnectTask(bluetoothDevice.getAddress()) == null) ? false : true;
    }

    public boolean isReconnecting() {
        return this.mHandler.hasMessages(MSG_RECONNECT_TIMEOUT);
    }

    public void reconnectDevice(String str, int i, OnHistoryRecordCallback onHistoryRecordCallback) {
        reconnectDevice(str, i, 0, onHistoryRecordCallback);
    }

    public void reconnectHistory(@NonNull HistoryRecord historyRecord) {
        String mappedAddress = (historyRecord.getConnectType() == 1 && historyRecord.getDevType() == 5) ? historyRecord.getMappedAddress() : historyRecord.getAddress();
        int connectType = historyRecord.getDevType() == 5 ? 0 : historyRecord.getConnectType();
        ReconnectTask reconnectTask = new ReconnectTask(mappedAddress);
        reconnectTask.setConnectWay(connectType);
        reconnectTask.setTaskType(2);
        addTask(reconnectTask);
    }

    public void stopReconnect() {
        if (!this.mTaskList.isEmpty()) {
            Iterator it = new ArrayList(this.mTaskList).iterator();
            while (it.hasNext()) {
                ReconnectTask reconnectTask = (ReconnectTask) it.next();
                if (reconnectTask.getCallback() != null) {
                    reconnectTask.getCallback().onFailed(1, "stop reconnect task.");
                }
                this.mHandler.removeMessages(reconnectTask.getTaskId());
            }
            this.mTaskList.clear();
        }
        this.mHandler.removeMessages(MSG_RECONNECT_TIMEOUT);
        this.mHandler.removeCallbacksAndMessages(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reconnectTask(boolean z) {
        this.isReStartTask = false;
        if (this.mTaskList.isEmpty()) {
            return;
        }
        ReconnectTask reconnectTask = this.mTaskList.get(0);
        if (z) {
            setStartTime(getCurrentTime());
        }
        getNowLeftTime();
        lambda$doReconnectEvent$2(reconnectTask);
    }

    public void reconnectDevice(String str, int i, int i2, OnHistoryRecordCallback onHistoryRecordCallback) {
        ReconnectTask reconnectTask = new ReconnectTask(str);
        reconnectTask.setTaskType(1);
        reconnectTask.setConnectWay(i);
        reconnectTask.setTaskTimeout(i2);
        reconnectTask.setCallback(onHistoryRecordCallback);
        addTask(reconnectTask);
    }

    private void removeTask(ReconnectTask reconnectTask, boolean z) {
        if (reconnectTask != null && this.mTaskList.remove(reconnectTask)) {
            if (reconnectTask.getCallback() != null) {
                if (z) {
                    reconnectTask.getCallback().onSuccess(this.mBtOp.getHistoryRecord(reconnectTask.getAddress()));
                } else {
                    reconnectTask.getCallback().onFailed(9, String.format(Locale.getDefault(), "connect device[%s] timeout.", reconnectTask.getAddress()));
                }
            }
            this.mHandler.removeMessages(reconnectTask.getTaskId());
            if (!this.mTaskList.isEmpty()) {
                startReconnectTask();
            } else {
                this.mHandler.removeMessages(MSG_RECONNECT_TIMEOUT);
            }
        }
    }
}
