package com.apex.bluetooth.core;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import com.apex.bluetooth.listener.EABleScanListener;
import com.apex.bluetooth.model.EABleDevice;
import com.jstyle.blesdk1860.constant.BleConst;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class j {
    public static j h;
    public a b;
    public BluetoothAdapter c;
    public BluetoothManager d;
    public volatile boolean f;

    /* renamed from: a  reason: collision with root package name */
    public final String f2188a = j.class.getSimpleName();
    public String[] g = {"APEX A02", "APEX M02", "APEX M02L", "APEX M51", "iTouch Flex", "APEX G01", "APEX G02", "APEX G03", "APEX G07", "APEX G01A", "APEX G02A", "APEX G03A", "Love & Sports Smartwatch", "Love & Sports Fit", "Ranger Smart", "APEX G06"};
    public List<EABleScanListener> e = new ArrayList();

    /* loaded from: classes.dex */
    public class a extends ScanCallback {
        public a() {
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onBatchScanResults(List<ScanResult> list) {
            super.onBatchScanResults(list);
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int i) {
            super.onScanFailed(i);
            j.this.f = false;
            for (int i2 = 0; i2 < j.this.e.size(); i2++) {
                EABleScanListener eABleScanListener = j.this.e.get(i2);
                if (eABleScanListener != null) {
                    if (i == 1) {
                        eABleScanListener.scanError(10);
                    } else if (i == 2) {
                        eABleScanListener.scanError(11);
                    } else if (i == 3) {
                        eABleScanListener.scanError(12);
                    } else if (i == 4) {
                        eABleScanListener.scanError(13);
                    } else if (i == 5) {
                        eABleScanListener.scanError(14);
                    } else if (i == 6) {
                        eABleScanListener.scanError(15);
                    }
                }
            }
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, ScanResult scanResult) {
            byte[] bytes;
            String str;
            super.onScanResult(i, scanResult);
            String name = scanResult.getDevice().getName();
            if (TextUtils.isEmpty(name)) {
                return;
            }
            int i2 = 0;
            if (name.contains("E03")) {
                Log.e(j.this.f2188a, "扫描出来的E03蓝牙名:" + name);
                EABleDevice eABleDevice = new EABleDevice();
                eABleDevice.setDeviceAddress(scanResult.getDevice().getAddress());
                eABleDevice.setDeviceName(scanResult.getDevice().getName());
                eABleDevice.setRssi(scanResult.getRssi());
                eABleDevice.setType(scanResult.getDevice().getType());
                eABleDevice.setDeviceSign(scanResult.getDevice().getAddress());
                while (i2 < j.this.e.size()) {
                    EABleScanListener eABleScanListener = j.this.e.get(i2);
                    if (eABleScanListener != null) {
                        eABleScanListener.scanDevice(eABleDevice);
                    }
                    i2++;
                }
                return;
            }
            ScanRecord scanRecord = scanResult.getScanRecord();
            if (scanRecord == null || (bytes = scanRecord.getBytes()) == null) {
                return;
            }
            byte[] bArr = null;
            boolean z = false;
            int i3 = 0;
            boolean z2 = true;
            while (z2) {
                if (i3 < bytes.length - 1) {
                    int i4 = bytes[i3] & 255;
                    int i5 = bytes[i3 + 1] & 255;
                    if (i5 == 255) {
                        if ((255 & bytes[i3]) == 9) {
                            int i6 = i4 - 1;
                            byte[] bArr2 = new byte[i6];
                            System.arraycopy(bytes, i3 + 2, bArr2, 0, i6);
                            bArr = bArr2;
                        }
                    } else if (i5 == 3) {
                        if ((bytes[i3] & 255) == 3) {
                            int i7 = i3 + 3;
                            if (i7 <= bytes.length - 1) {
                                if ((bytes[i3 + 2] & 255) == 0 && (255 & bytes[i7]) == 136) {
                                    z2 = false;
                                    z = true;
                                }
                            }
                        } else if (i4 == 5) {
                            int i8 = i3 + 5;
                            if (i8 <= bytes.length - 1) {
                                if ((bytes[i3 + 2] & 255) == 0 && (bytes[i3 + 3] & 255) == 136 && (bytes[i3 + 4] & 255) == 17 && (255 & bytes[i8]) == 0) {
                                    z2 = false;
                                    z = true;
                                }
                            }
                        }
                    }
                    i3 += i4 + 1;
                }
                z2 = false;
            }
            if (z) {
                EABleDevice eABleDevice2 = new EABleDevice();
                eABleDevice2.setDeviceAddress(scanResult.getDevice().getAddress());
                eABleDevice2.setDeviceName(scanResult.getDevice().getName());
                eABleDevice2.setRssi(scanResult.getRssi());
                eABleDevice2.setType(scanResult.getDevice().getType());
                eABleDevice2.setDeviceSign(scanResult.getDevice().getAddress());
                String str2 = "";
                if (bArr != null) {
                    str = "";
                    for (byte b : bArr) {
                        String hexString = Integer.toHexString(b & 255);
                        StringBuilder sb = new StringBuilder();
                        sb.append(str);
                        if (hexString.length() < 2) {
                            hexString = BleConst.GetDeviceTime + hexString;
                        }
                        sb.append(hexString);
                        str = sb.toString();
                    }
                } else {
                    str = "";
                }
                if (!TextUtils.isEmpty(str)) {
                    String bigInteger = new BigInteger(str, 16).toString();
                    if (bigInteger.length() > 18) {
                        bigInteger = bigInteger.substring(bigInteger.length() - 18, bigInteger.length());
                    } else {
                        int length = 18 - bigInteger.length();
                        if (length > 0) {
                            for (int i9 = 0; i9 < length; i9++) {
                                str2 = str2 + BleConst.GetDeviceTime;
                            }
                            bigInteger = str2 + bigInteger;
                        }
                    }
                    eABleDevice2.setDeviceSign(bigInteger);
                } else {
                    eABleDevice2.setDeviceSign(scanResult.getDevice().getAddress());
                }
                while (i2 < j.this.e.size()) {
                    EABleScanListener eABleScanListener2 = j.this.e.get(i2);
                    if (eABleScanListener2 != null) {
                        eABleScanListener2.scanDevice(eABleDevice2);
                    }
                    i2++;
                }
                return;
            }
            EABleDevice eABleDevice3 = new EABleDevice();
            eABleDevice3.setDeviceAddress(scanResult.getDevice().getAddress());
            eABleDevice3.setDeviceName(scanResult.getDevice().getName());
            eABleDevice3.setRssi(scanResult.getRssi());
            eABleDevice3.setType(scanResult.getDevice().getType());
            eABleDevice3.setDeviceSign(scanResult.getDevice().getAddress());
            while (i2 < j.this.e.size()) {
                EABleScanListener eABleScanListener3 = j.this.e.get(i2);
                if (eABleScanListener3 != null) {
                    eABleScanListener3.scanDevice(eABleDevice3);
                }
                i2++;
            }
        }
    }

    public static j a(@NonNull Context context) {
        if (h == null) {
            synchronized (j.class) {
                if (h == null) {
                    h = new j();
                }
            }
        }
        return h;
    }
}
