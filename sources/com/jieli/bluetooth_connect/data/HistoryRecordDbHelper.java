package com.jieli.bluetooth_connect.data;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import com.jieli.bluetooth_connect.bean.BluetoothOption;
import com.jieli.bluetooth_connect.bean.history.HistoryRecord;
import com.jieli.bluetooth_connect.data.dao.HistoryRecordDao;
import com.jieli.bluetooth_connect.util.BluetoothUtil;
import com.jieli.bluetooth_connect.util.ConnectUtil;
import com.jieli.bluetooth_connect.util.JL_Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* loaded from: classes11.dex */
public class HistoryRecordDbHelper {
    private final BtConnectDatabase mBtConnectDatabase;
    private final Context mContext;
    private final ObserverHelper mObserverHelper;
    private final ExecutorService mThreadPool = Executors.newSingleThreadExecutor();

    public HistoryRecordDbHelper(Context context, BluetoothOption bluetoothOption) {
        Objects.requireNonNull(context, "Please call initDb method at first!");
        this.mContext = context;
        this.mBtConnectDatabase = BtConnectDatabase.buildDatabase(context);
        this.mObserverHelper = new ObserverHelper();
        syncSystemDeviceList(bluetoothOption);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$clearAllHistoryRecord$4() {
        clearHistoryRecords(getHistoryRecordList());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$saveHistoryRecord$0(BluetoothDevice bluetoothDevice, int i) {
        HistoryRecord historyRecordByDevice = getHistoryRecordByDevice(bluetoothDevice);
        boolean z = historyRecordByDevice == null;
        if (z) {
            historyRecordByDevice = new HistoryRecord();
        } else if (bluetoothDevice.getAddress().equals(historyRecordByDevice.getUpdateAddress())) {
            return;
        }
        historyRecordByDevice.setName(bluetoothDevice.getName());
        historyRecordByDevice.setAddress(bluetoothDevice.getAddress());
        historyRecordByDevice.setDevType(bluetoothDevice.getType());
        historyRecordByDevice.setConnectType(i);
        historyRecordByDevice.setOnlineTime(System.currentTimeMillis());
        JL_Log.d("HistoryRecordDbHelper", String.format(Locale.getDefault(), "saveHistoryRecord : %s, connectWay : %d, isAddRecord = %s", BluetoothUtil.printBtDeviceInfo(this.mContext, bluetoothDevice), Integer.valueOf(i), Boolean.valueOf(z)));
        if (z) {
            saveHistoryRecord(historyRecordByDevice);
        } else {
            updateHistoryRecord(historyRecordByDevice);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateDeviceIDs$1(BluetoothDevice bluetoothDevice, int i, int i2, int i3) {
        HistoryRecord historyRecordByDevice = getHistoryRecordByDevice(bluetoothDevice);
        if (historyRecordByDevice == null) {
            return;
        }
        historyRecordByDevice.setVid(i);
        historyRecordByDevice.setUid(i2);
        historyRecordByDevice.setPid(i3);
        updateHistoryRecord(historyRecordByDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateDeviceInfo$2(BluetoothDevice bluetoothDevice, int i, String str) {
        HistoryRecord historyRecordByDevice = getHistoryRecordByDevice(bluetoothDevice);
        if (historyRecordByDevice == null) {
            return;
        }
        historyRecordByDevice.setSdkFlag(i);
        historyRecordByDevice.setMappedAddress(str);
        updateHistoryRecord(historyRecordByDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:30:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ void lambda$updateGpsInfo$3(android.bluetooth.BluetoothDevice r10, double r11, double r13, int r15, long r16) {
        /*
            r9 = this;
            r0 = r15
            com.jieli.bluetooth_connect.bean.history.HistoryRecord r7 = r9.getHistoryRecordByDevice(r10)
            if (r7 == 0) goto L62
            r1 = 0
            int r3 = (r11 > r1 ? 1 : (r11 == r1 ? 0 : -1))
            if (r3 != 0) goto L12
            int r1 = (r13 > r1 ? 1 : (r13 == r1 ? 0 : -1))
            if (r1 != 0) goto L12
            goto L62
        L12:
            r1 = 0
            r8 = 1
            if (r0 == 0) goto L3a
            if (r0 == r8) goto L19
            goto L5b
        L19:
            double r2 = r7.getRightDevLatitude()
            int r0 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1))
            if (r0 != 0) goto L31
            double r2 = r7.getRightDevLongitude()
            int r0 = (r13 > r2 ? 1 : (r13 == r2 ? 0 : -1))
            if (r0 != 0) goto L31
            long r2 = r7.getRightDevUpdateTime()
            int r0 = (r16 > r2 ? 1 : (r16 == r2 ? 0 : -1))
            if (r0 <= 0) goto L5b
        L31:
            r0 = r7
            r1 = r11
            r3 = r13
            r5 = r16
            r0.updateRightDevGpsInfo(r1, r3, r5)
            goto L5a
        L3a:
            double r2 = r7.getLeftDevLatitude()
            int r0 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1))
            if (r0 != 0) goto L52
            double r2 = r7.getLeftDevLongitude()
            int r0 = (r13 > r2 ? 1 : (r13 == r2 ? 0 : -1))
            if (r0 != 0) goto L52
            long r2 = r7.getLeftDevUpdateTime()
            int r0 = (r16 > r2 ? 1 : (r16 == r2 ? 0 : -1))
            if (r0 <= 0) goto L5b
        L52:
            r0 = r7
            r1 = r11
            r3 = r13
            r5 = r16
            r0.updateLeftDevGpsInfo(r1, r3, r5)
        L5a:
            r1 = r8
        L5b:
            r0 = r9
            if (r1 == 0) goto L61
            r9.updateHistoryRecord(r7)
        L61:
            return
        L62:
            r0 = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jieli.bluetooth_connect.data.HistoryRecordDbHelper.lambda$updateGpsInfo$3(android.bluetooth.BluetoothDevice, double, double, int, long):void");
    }

    public void addHistoryRecordObserver(HistoryRecordObserver historyRecordObserver) {
        this.mObserverHelper.addHistoryRecordObserver(historyRecordObserver);
    }

    public void clearAllHistoryRecord() {
        if (this.mThreadPool.isShutdown()) {
            return;
        }
        this.mThreadPool.submit(new Runnable() { // from class: com.jieli.bluetooth_connect.data.a
            @Override // java.lang.Runnable
            public final void run() {
                HistoryRecordDbHelper.this.lambda$clearAllHistoryRecord$4();
            }
        });
    }

    public void clearHistoryRecords(List<HistoryRecord> list) {
        if (list == null) {
            return;
        }
        this.mBtConnectDatabase.historyRecordDao().removeHistoryRecords(list);
        this.mObserverHelper.onDelete(null);
    }

    public void deleteHistoryRecord(HistoryRecord historyRecord) {
        if (historyRecord == null) {
            return;
        }
        this.mBtConnectDatabase.historyRecordDao().removeHistoryRecord(historyRecord);
        this.mObserverHelper.onDelete(historyRecord);
    }

    public void destroy() {
        if (!this.mThreadPool.isShutdown()) {
            this.mThreadPool.shutdownNow();
        }
        this.mObserverHelper.release();
    }

    public HistoryRecord getHistoryRecordByDevice(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return null;
        }
        return getHistoryRecordByMac(bluetoothDevice.getAddress());
    }

    public HistoryRecord getHistoryRecordByMac(String str) {
        if (BluetoothAdapter.checkBluetoothAddress(str)) {
            return getHistoryRecordDao().getHistoryRecord(str);
        }
        return null;
    }

    public HistoryRecordDao getHistoryRecordDao() {
        return this.mBtConnectDatabase.historyRecordDao();
    }

    public List<HistoryRecord> getHistoryRecordList() {
        return getHistoryRecordDao().getHistoryRecordList();
    }

    public String getMappedAddress(String str) {
        HistoryRecord historyRecordByMac = getHistoryRecordByMac(str);
        if (historyRecordByMac == null) {
            return null;
        }
        if (str.equals(historyRecordByMac.getAddress())) {
            return historyRecordByMac.getMappedAddress();
        }
        return historyRecordByMac.getAddress();
    }

    public boolean isMatchDevice(String str, String str2) {
        if (BluetoothAdapter.checkBluetoothAddress(str) && BluetoothAdapter.checkBluetoothAddress(str2)) {
            boolean equals = str.equals(str2);
            if (equals) {
                return equals;
            }
            String mappedAddress = getMappedAddress(str);
            if (BluetoothAdapter.checkBluetoothAddress(mappedAddress)) {
                equals = mappedAddress.equals(str2);
            }
            if (equals) {
                return equals;
            }
            String mappedAddress2 = getMappedAddress(str2);
            return BluetoothAdapter.checkBluetoothAddress(mappedAddress2) ? mappedAddress2.equals(str) : equals;
        }
        return false;
    }

    public void removeHistoryRecordObserver(HistoryRecordObserver historyRecordObserver) {
        this.mObserverHelper.removeHistoryRecordObserver(historyRecordObserver);
    }

    public void saveHistoryRecord(HistoryRecord historyRecord) {
        if (historyRecord == null) {
            return;
        }
        this.mBtConnectDatabase.historyRecordDao().addHistoryRecord(historyRecord);
        this.mObserverHelper.onInsert(historyRecord);
    }

    public void syncSystemDeviceList(BluetoothOption bluetoothOption) {
        List<HistoryRecord> historyRecordList;
        if ((bluetoothOption == null || !bluetoothOption.isNotAssociatedEDR()) && BluetoothUtil.isBluetoothEnable() && (historyRecordList = getHistoryRecordList()) != null) {
            List<BluetoothDevice> pairedDevices = BluetoothUtil.getPairedDevices(this.mContext);
            ArrayList arrayList = new ArrayList();
            for (HistoryRecord historyRecord : historyRecordList) {
                if (historyRecord.getConnectType() != 0 && historyRecord.getMappedAddress() != null) {
                    if (pairedDevices != null) {
                        Iterator<BluetoothDevice> it = pairedDevices.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                if (isMatchDevice(historyRecord.getAddress(), it.next().getAddress())) {
                                    historyRecord = null;
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                    }
                    if (historyRecord != null) {
                        arrayList.add(historyRecord);
                    }
                }
            }
            if (arrayList.isEmpty()) {
                return;
            }
            clearHistoryRecords(arrayList);
        }
    }

    public void updateDeviceIDs(final BluetoothDevice bluetoothDevice, final int i, final int i2, final int i3) {
        if (bluetoothDevice == null || this.mThreadPool.isShutdown()) {
            return;
        }
        this.mThreadPool.submit(new Runnable() { // from class: com.jieli.bluetooth_connect.data.d
            @Override // java.lang.Runnable
            public final void run() {
                HistoryRecordDbHelper.this.lambda$updateDeviceIDs$1(bluetoothDevice, i, i2, i3);
            }
        });
    }

    public void updateDeviceInfo(final BluetoothDevice bluetoothDevice, final int i, final String str) {
        if (bluetoothDevice == null || this.mThreadPool.isShutdown()) {
            return;
        }
        this.mThreadPool.submit(new Runnable() { // from class: com.jieli.bluetooth_connect.data.e
            @Override // java.lang.Runnable
            public final void run() {
                HistoryRecordDbHelper.this.lambda$updateDeviceInfo$2(bluetoothDevice, i, str);
            }
        });
    }

    public void updateGpsInfo(final BluetoothDevice bluetoothDevice, final int i, final double d, final double d2, final long j) {
        if (bluetoothDevice == null || this.mThreadPool.isShutdown()) {
            return;
        }
        this.mThreadPool.submit(new Runnable() { // from class: com.jieli.bluetooth_connect.data.b
            @Override // java.lang.Runnable
            public final void run() {
                HistoryRecordDbHelper.this.lambda$updateGpsInfo$3(bluetoothDevice, d, d2, i, j);
            }
        });
    }

    public void updateHistoryRecord(HistoryRecord historyRecord) {
        if (historyRecord == null) {
            return;
        }
        this.mBtConnectDatabase.historyRecordDao().updateHistoryRecord(historyRecord);
        this.mObserverHelper.onModify(historyRecord);
    }

    @SuppressLint({"MissingPermission"})
    public void saveHistoryRecord(final BluetoothDevice bluetoothDevice, final int i) {
        if (bluetoothDevice == null || this.mThreadPool.isShutdown() || !ConnectUtil.isHasConnectPermission(this.mContext)) {
            return;
        }
        this.mThreadPool.submit(new Runnable() { // from class: com.jieli.bluetooth_connect.data.c
            @Override // java.lang.Runnable
            public final void run() {
                HistoryRecordDbHelper.this.lambda$saveHistoryRecord$0(bluetoothDevice, i);
            }
        });
    }
}
