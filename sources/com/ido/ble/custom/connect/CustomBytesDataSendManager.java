package com.ido.ble.custom.connect;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.ido.ble.bluetooth.f.a;
import com.ido.ble.common.c;
import com.ido.ble.custom.CustomBLEUtils;
import com.ido.ble.custom.CustomConfig;
import com.ido.ble.logs.LogTool;
import java.util.LinkedList;
/* loaded from: classes11.dex */
public class CustomBytesDataSendManager {
    private static final int MAX_CMD_QUEUE_SIZE = 10;
    private static final long TIME_SEND_TIMEOUT = 5000;
    private static final int WHAT_SEND_NO_ANSWER_TIMEOUT = 1;
    private static CustomBytesDataSendManager manager;
    private BluetoothGattCharacteristic mWriteHealthGattCharacteristic;
    private BluetoothGattCharacteristic mWriteNormalGattCharacteristic;
    private LinkedList<byte[]> mCmdDataQueue = new LinkedList<>();
    private boolean mIsSendingCmdData = false;
    private Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.ido.ble.custom.connect.CustomBytesDataSendManager.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 1 && CustomBytesDataSendManager.this.mIsSendingCmdData) {
                CustomBytesDataSendManager.this.mIsSendingCmdData = false;
                if (CustomBytesDataSendManager.this.mCmdDataQueue.size() > 10) {
                    LogTool.b(CustomBLEUtils.LOG_CUSTOM_CONNECT_TGA, "last send out time, mCmdDataQueue.size() > 10, clear");
                    CustomBytesDataSendManager.this.mCmdDataQueue.clear();
                    return;
                }
                LogTool.b(CustomBLEUtils.LOG_CUSTOM_CONNECT_TGA, "no respond on last cmd, send next ...");
                CustomBytesDataSendManager.this.sendNextCmdData();
            }
        }
    };

    private BluetoothGattCharacteristic getGattCharacteristic(BluetoothGatt bluetoothGatt, byte[] bArr) {
        if (isHealthCmd(bArr)) {
            if (this.mWriteHealthGattCharacteristic == null) {
                this.mWriteHealthGattCharacteristic = a.a(bluetoothGatt);
            }
            return this.mWriteHealthGattCharacteristic;
        }
        if (this.mWriteNormalGattCharacteristic == null) {
            this.mWriteNormalGattCharacteristic = a.b(bluetoothGatt);
        }
        return this.mWriteNormalGattCharacteristic;
    }

    public static CustomBytesDataSendManager getManager() {
        if (manager == null) {
            manager = new CustomBytesDataSendManager();
        }
        return manager;
    }

    private void handleCmdDataQueue() {
        if (this.mCmdDataQueue.size() > 10) {
            LogTool.b(CustomBLEUtils.LOG_CUSTOM_CONNECT_TGA, "cmd queue is out of max size, handle it...");
            for (int i = 0; i < 8; i++) {
                this.mCmdDataQueue.pollFirst();
            }
        }
    }

    private boolean isHealthCmd(byte[] bArr) {
        return bArr[0] == 8 || bArr[0] == 9;
    }

    public static void receiverDeviceData(byte[] bArr) {
        LogTool.d(CustomBLEUtils.LOG_CUSTOM_CONNECT_TGA, "receive <= " + c.b(bArr));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendNextCmdData() {
        if (this.mIsSendingCmdData) {
            return;
        }
        if (this.mCmdDataQueue.size() == 0) {
            this.mIsSendingCmdData = false;
            return;
        }
        byte[] poll = this.mCmdDataQueue.poll();
        if (poll == null) {
            this.mIsSendingCmdData = false;
            sendNextCmdData();
            return;
        }
        boolean writeBytes = writeBytes(poll);
        LogTool.d(CustomBLEUtils.LOG_CUSTOM_CONNECT_TGA, "send => " + c.b(poll));
        this.mIsSendingCmdData = true;
        if (writeBytes) {
            this.mHandler.sendEmptyMessageDelayed(1, 5000L);
            return;
        }
        this.mIsSendingCmdData = false;
        LogTool.b(CustomBLEUtils.LOG_CUSTOM_CONNECT_TGA, "send failed ( " + c.b(poll) + ")");
        sendNextCmdData();
    }

    private boolean writeBytes(byte[] bArr) {
        String str;
        if (CustomConfig.getConfig().getIEnableNotifyCallback().isConnectedAndReady()) {
            BluetoothGattCharacteristic gattCharacteristic = getGattCharacteristic(CustomConfig.getConfig().getIEnableNotifyCallback().getConnectedGatt(), bArr);
            if (gattCharacteristic == null || (gattCharacteristic.getProperties() | 8) <= 0) {
                str = "send(), characteristic error!";
            } else {
                gattCharacteristic.setValue(bArr);
                if ((gattCharacteristic.getProperties() & 12) != 0) {
                    boolean writeCharacteristic = CustomConfig.getConfig().getIEnableNotifyCallback().getConnectedGatt().writeCharacteristic(gattCharacteristic);
                    if (!writeCharacteristic) {
                        LogTool.b(CustomBLEUtils.LOG_CUSTOM_CONNECT_TGA, "send(), writeCharacteristic() error!");
                    }
                    return writeCharacteristic;
                }
                str = "send(), characteristic.properties error!";
            }
        } else {
            str = "send(), isConnectedAndReady = false. send failed";
        }
        LogTool.b(CustomBLEUtils.LOG_CUSTOM_CONNECT_TGA, str);
        return false;
    }

    public void addCmdData(byte[] bArr, boolean z) {
        if (bArr == null || bArr.length == 0) {
            LogTool.b(CustomBLEUtils.LOG_CUSTOM_CONNECT_TGA, "onAddCmd() ignore, data is null");
            return;
        }
        if (z) {
            this.mCmdDataQueue.addFirst(bArr);
        } else {
            this.mCmdDataQueue.add(bArr);
        }
        sendNextCmdData();
    }

    public void deviceResponseOnLastSend(byte[] bArr, int i) {
        if (i == 0) {
            LogTool.d(CustomBLEUtils.LOG_CUSTOM_CONNECT_TGA, "onDeviceResponseOnLastSend( " + c.b(bArr) + ")");
        } else {
            LogTool.b(CustomBLEUtils.LOG_CUSTOM_CONNECT_TGA, "onDeviceResponseOnLastSend[failed]( " + c.b(bArr) + ")");
        }
        this.mIsSendingCmdData = false;
        this.mHandler.removeMessages(1);
        handleCmdDataQueue();
        sendNextCmdData();
    }

    public void reset() {
        this.mCmdDataQueue.clear();
        this.mWriteHealthGattCharacteristic = null;
        this.mWriteNormalGattCharacteristic = null;
    }
}
