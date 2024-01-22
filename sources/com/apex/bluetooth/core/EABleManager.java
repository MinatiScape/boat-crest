package com.apex.bluetooth.core;

import a.a;
import a.b;
import a.d;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.abupdate.iot_libs.constant.Error;
import com.android.volley.toolbox.JsonRequest;
import com.apex.bluetooth.callback.DataReportCallback;
import com.apex.bluetooth.callback.DataResponseCallback;
import com.apex.bluetooth.callback.DeviceBugCallback;
import com.apex.bluetooth.callback.EABleCallback;
import com.apex.bluetooth.callback.GeneralCallback;
import com.apex.bluetooth.callback.HabitResultCallback;
import com.apex.bluetooth.callback.MotionDataReportCallback;
import com.apex.bluetooth.callback.MotionDataResponseCallback;
import com.apex.bluetooth.callback.OtaCallback;
import com.apex.bluetooth.core.a;
import com.apex.bluetooth.core.j;
import com.apex.bluetooth.data_package.b.b;
import com.apex.bluetooth.enumeration.BatInfoStatus;
import com.apex.bluetooth.enumeration.CommonAction;
import com.apex.bluetooth.enumeration.EABleConnectState;
import com.apex.bluetooth.enumeration.MotionReportType;
import com.apex.bluetooth.enumeration.PersonHand;
import com.apex.bluetooth.enumeration.QueryWatchInfoType;
import com.apex.bluetooth.enumeration.TimeZone;
import com.apex.bluetooth.enumeration.UnitFormat;
import com.apex.bluetooth.enumeration.VibrationIntensity;
import com.apex.bluetooth.listener.EABleConnectListener;
import com.apex.bluetooth.listener.EABleScanListener;
import com.apex.bluetooth.model.EABleAncsSw;
import com.apex.bluetooth.model.EABleAppScreenSport;
import com.apex.bluetooth.model.EABleAppSportData;
import com.apex.bluetooth.model.EABleAutoCheckSleep;
import com.apex.bluetooth.model.EABleAutoMonitor;
import com.apex.bluetooth.model.EABleBatInfo;
import com.apex.bluetooth.model.EABleBindInfo;
import com.apex.bluetooth.model.EABleBloodPressure;
import com.apex.bluetooth.model.EABleCheckSwitch;
import com.apex.bluetooth.model.EABleContact;
import com.apex.bluetooth.model.EABleDailyGoal;
import com.apex.bluetooth.model.EABleDataRespond;
import com.apex.bluetooth.model.EABleDataType;
import com.apex.bluetooth.model.EABleDev;
import com.apex.bluetooth.model.EABleDevUnit;
import com.apex.bluetooth.model.EABleDeviceLanguage;
import com.apex.bluetooth.model.EABleDistanceFormat;
import com.apex.bluetooth.model.EABleGeneralSportRespond;
import com.apex.bluetooth.model.EABleGesturesBrightScreen;
import com.apex.bluetooth.model.EABleHabit;
import com.apex.bluetooth.model.EABleHomeTimeZone;
import com.apex.bluetooth.model.EABleHr;
import com.apex.bluetooth.model.EABleInfoPush;
import com.apex.bluetooth.model.EABleMenuPage;
import com.apex.bluetooth.model.EABleMonitorReminder;
import com.apex.bluetooth.model.EABleMusicRespond;
import com.apex.bluetooth.model.EABleNotDisturb;
import com.apex.bluetooth.model.EABleOta;
import com.apex.bluetooth.model.EABlePeriodReminder;
import com.apex.bluetooth.model.EABlePersonInfo;
import com.apex.bluetooth.model.EABlePhoneResponse;
import com.apex.bluetooth.model.EABleReadDebug;
import com.apex.bluetooth.model.EABleSedentariness;
import com.apex.bluetooth.model.EABleSleepBloodSwitch;
import com.apex.bluetooth.model.EABleSocialContact;
import com.apex.bluetooth.model.EABleStartAppSports;
import com.apex.bluetooth.model.EABleSyncTime;
import com.apex.bluetooth.model.EABleWatchFace;
import com.apex.bluetooth.model.EABleWatchInfo;
import com.apex.bluetooth.model.EABleWeather;
import com.apex.bluetooth.model.EABleWeightFormat;
import com.apex.bluetooth.model.QueryInfo;
import com.apex.bluetooth.utils.LogData2File;
import com.apex.bluetooth.utils.LogUtils;
import com.goodix.ble.gr.libdfu.task.sub.ResultCode;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public final class EABleManager {
    public static EABleManager eaBleManager;
    public final String TAG = EABleManager.class.getSimpleName();
    public a eaBleBluetoothConnect;
    public EABleConnectListener eaBleConnectListener;

    public static EABleManager getInstance() {
        if (eaBleManager == null) {
            synchronized (EABleManager.class) {
                if (eaBleManager == null) {
                    eaBleManager = new EABleManager();
                }
            }
        }
        return eaBleManager;
    }

    public void addBookList(List<EABleContact> list, int i, GeneralCallback generalCallback) {
        com.apex.bluetooth.data_package.a aVar;
        int length;
        byte[] bArr;
        a aVar2 = this.eaBleBluetoothConnect;
        if (aVar2 != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
            Objects.requireNonNull(aVar3);
            a.e0.b builder = a.e0.d.toBuilder();
            if (list != null && !list.isEmpty()) {
                for (int i2 = 0; i2 < list.size(); i2++) {
                    a.e0.d.b builder2 = a.e0.d.d.toBuilder();
                    builder2.a(list.get(i2).getContactName());
                    builder2.b(list.get(i2).getContactNum());
                    builder.a(builder2.build());
                }
            }
            builder.a(i <= 0 ? 0 : 1);
            byte[] a2 = cVar.a(aVar3.a(builder.build().toByteArray(), 42));
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i3 = i.f2187a;
                if (length2 <= i3) {
                    length = 1;
                } else if (a2.length % i3 == 0) {
                    length = a2.length / i3;
                } else {
                    length = (a2.length / i3) + 1;
                }
                aVar = new com.apex.bluetooth.data_package.a(length);
                aVar.b = aVar4;
                if (length == 1) {
                    aVar.a(a2);
                } else {
                    for (int i4 = 0; i4 < length; i4++) {
                        int i5 = length - 1;
                        if (i4 < i5) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i5 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i4, bArr, 0, bArr.length);
                        aVar.a(bArr);
                    }
                }
            } else {
                aVar = null;
            }
            aVar2.a(aVar, generalCallback);
        }
    }

    public void addMonitorReminder(@NonNull EABleMonitorReminder eABleMonitorReminder, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleMonitorReminder == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                a.c0.b builder = a.c0.l.toBuilder();
                builder.a(eABleMonitorReminder.getBegin_hour());
                builder.b(eABleMonitorReminder.getBegin_minute());
                builder.e(eABleMonitorReminder.getEnd_hour());
                builder.f(eABleMonitorReminder.getEnd_minute());
                builder.i(eABleMonitorReminder.getReminderSwitch());
                builder.c(eABleMonitorReminder.getCup());
                builder.g(eABleMonitorReminder.getInterval());
                builder.h(eABleMonitorReminder.getStep_threshold());
                builder.j(eABleMonitorReminder.getWeek_cycle_bit() & (-1));
                builder.d(eABleMonitorReminder.getEaBleMonitorType().getValue());
                a2 = cVar.a(aVar3.a(builder.build().toByteArray(), 45));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public boolean checkLocationPermission(@NonNull Context context) {
        return ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") == 0 || ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0;
    }

    public void connectToPeripheral(@NonNull String str, @NonNull Context context, EABleConnectListener eABleConnectListener, int i, DataReportCallback dataReportCallback, MotionDataReportCallback motionDataReportCallback) {
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            aVar.a();
            this.eaBleBluetoothConnect = null;
        }
        this.eaBleConnectListener = eABleConnectListener;
        a aVar2 = new a(context, eABleConnectListener, i, dataReportCallback, motionDataReportCallback);
        this.eaBleBluetoothConnect = aVar2;
        if (TextUtils.isEmpty(str)) {
            EABleConnectListener eABleConnectListener2 = aVar2.f;
            if (eABleConnectListener2 != null) {
                eABleConnectListener2.connectError(1);
                return;
            }
            return;
        }
        try {
            BluetoothManager bluetoothManager = (BluetoothManager) aVar2.i.getSystemService("bluetooth");
            aVar2.e = bluetoothManager;
            if (bluetoothManager == null) {
                EABleConnectListener eABleConnectListener3 = aVar2.f;
                if (eABleConnectListener3 != null) {
                    eABleConnectListener3.unsupportedBLE();
                    return;
                }
                return;
            }
            BluetoothAdapter adapter = bluetoothManager.getAdapter();
            aVar2.b = adapter;
            if (adapter == null) {
                EABleConnectListener eABleConnectListener4 = aVar2.f;
                if (eABleConnectListener4 != null) {
                    eABleConnectListener4.unsupportedBLE();
                }
            } else if (!adapter.isEnabled()) {
                EABleConnectListener eABleConnectListener5 = aVar2.f;
                if (eABleConnectListener5 != null) {
                    eABleConnectListener5.unopenedBluetooth();
                }
            } else {
                aVar2.m = str;
                BluetoothDevice remoteDevice = aVar2.b.getRemoteDevice(str);
                aVar2.d = remoteDevice;
                a.b bVar = aVar2.h;
                if (bVar != null) {
                    com.apex.bluetooth.core.m.a aVar3 = bVar.f2170a;
                }
                com.apex.bluetooth.broadcast.a aVar4 = aVar2.o;
                if (aVar4 != null) {
                    aVar4.b = aVar2.m;
                }
                if (remoteDevice != null) {
                    new Handler(Looper.getMainLooper()).post(new b(aVar2));
                    return;
                }
                EABleConnectListener eABleConnectListener6 = aVar2.f;
                if (eABleConnectListener6 != null) {
                    eABleConnectListener6.deviceNotFind();
                }
            }
        } catch (IllegalStateException unused) {
            EABleConnectListener eABleConnectListener7 = aVar2.f;
            if (eABleConnectListener7 != null) {
                eABleConnectListener7.connectError(2);
            }
        }
    }

    public void didDiscoverPeripheral(@NonNull EABleScanListener eABleScanListener, @NonNull Context context, boolean z) {
        j a2 = j.a(context);
        synchronized (a2) {
            if (a2.d == null) {
                a2.d = (BluetoothManager) context.getSystemService("bluetooth");
            }
            if (a2.d != null) {
                if (a2.b == null) {
                    a2.b = new j.a();
                }
                if (a2.c == null) {
                    a2.c = a2.d.getAdapter();
                }
                if (a2.c != null) {
                    a2.e.add(eABleScanListener);
                    int i = 0;
                    if (!a2.c.isEnabled()) {
                        List<EABleScanListener> list = a2.e;
                        if (list != null && !list.isEmpty()) {
                            while (i < a2.e.size()) {
                                EABleScanListener eABleScanListener2 = a2.e.get(i);
                                if (eABleScanListener2 != null) {
                                    eABleScanListener2.scanError(7);
                                }
                                i++;
                            }
                        }
                    } else if (!a2.f) {
                        BluetoothLeScanner bluetoothLeScanner = a2.c.getBluetoothLeScanner();
                        if (bluetoothLeScanner != null) {
                            a2.f = true;
                            if (z) {
                                if (a2.g != null) {
                                    ArrayList arrayList = new ArrayList();
                                    while (i < a2.g.length) {
                                        arrayList.add(new ScanFilter.Builder().setDeviceName(a2.g[i]).build());
                                        i++;
                                    }
                                    bluetoothLeScanner.startScan((List<ScanFilter>) null, new ScanSettings.Builder().setScanMode(1).build(), a2.b);
                                }
                            } else {
                                bluetoothLeScanner.startScan((List<ScanFilter>) null, new ScanSettings.Builder().setScanMode(1).build(), a2.b);
                            }
                        } else {
                            while (i < a2.e.size()) {
                                EABleScanListener eABleScanListener3 = a2.e.get(i);
                                if (eABleScanListener3 != null) {
                                    eABleScanListener3.scanError(6);
                                }
                                i++;
                            }
                        }
                    }
                } else if (eABleScanListener != null) {
                    eABleScanListener.scanError(9);
                }
            } else if (eABleScanListener != null) {
                eABleScanListener.scanError(9);
            }
        }
    }

    public void disconnectPeripheral() {
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            aVar.a();
        }
        this.eaBleBluetoothConnect = null;
    }

    public boolean getCurrentOtaState() {
        com.apex.bluetooth.listener.a aVar;
        a aVar2 = this.eaBleBluetoothConnect;
        if (aVar2 == null || (aVar = aVar2.g) == null) {
            return false;
        }
        return ((c) aVar).O;
    }

    public EABleConnectState getDeviceConnectState() {
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.listener.a aVar2 = aVar.g;
            if (aVar2 != null) {
                k kVar = ((c) aVar2).p;
                if (kVar != null) {
                    return kVar.f2190a;
                }
                return EABleConnectState.STATE_IDLE;
            }
            return EABleConnectState.STATE_IDLE;
        }
        return EABleConnectState.STATE_IDLE;
    }

    public EABleConnectListener getEaBleConnectListener() {
        return this.eaBleConnectListener;
    }

    public void getMotionDataWithType(@NonNull EABleDataType eABleDataType, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleDataType == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                d.f.b builder = d.f.c.toBuilder();
                builder.a(eABleDataType.getValue());
                a2 = cVar.a(aVar3.a(builder.build().toByteArray(), 49));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void getWatchDebugInfo(EABleReadDebug eABleReadDebug, DeviceBugCallback deviceBugCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleReadDebug == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                a.h.b builder = a.h.f.toBuilder();
                if (eABleReadDebug.getType() != null) {
                    builder.a(eABleReadDebug.getType().getValue());
                }
                builder.c(eABleReadDebug.getMem_data_len());
                builder.b(eABleReadDebug.getMem_addr() & (-1));
                a2 = cVar.a(aVar3.a(builder.build().toByteArray(), 35));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, deviceBugCallback);
        }
    }

    public boolean isBLESupported(@NonNull Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
    }

    public void mobileOperationResponse(@NonNull EABlePhoneResponse eABlePhoneResponse, DataResponseCallback dataResponseCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABlePhoneResponse == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                b.j.C0078b builder = b.j.d.toBuilder();
                builder.b(eABlePhoneResponse.getId());
                builder.a(eABlePhoneResponse.getEaBleExecutiveResponse().getValue());
                a2 = cVar.a(aVar3.a(builder.build().toByteArray(), 2001));
            }
            b.a aVar4 = b.a.east_apex_02;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.c(aVar2, dataResponseCallback);
        }
    }

    public void motionDataResponse(@NonNull EABleGeneralSportRespond eABleGeneralSportRespond, MotionDataResponseCallback motionDataResponseCallback) {
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            aVar.b(new com.apex.bluetooth.data_package.c.b().a(eABleGeneralSportRespond), motionDataResponseCallback);
        }
    }

    public void musicQueryResponse(@NonNull EABleMusicRespond eABleMusicRespond, DataResponseCallback dataResponseCallback) {
        byte[] a2;
        byte[] bytes;
        byte[] bytes2;
        DataResponseCallback dataResponseCallback2;
        com.apex.bluetooth.data_package.a aVar;
        int length;
        byte[] bArr;
        a aVar2 = this.eaBleBluetoothConnect;
        if (aVar2 != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            if (eABleMusicRespond == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                EABleMusicRespond.MusicStatus e_status = eABleMusicRespond.getE_status();
                int volume = eABleMusicRespond.getVolume();
                String content = eABleMusicRespond.getContent();
                int duration = eABleMusicRespond.getDuration();
                int elapsedtime = eABleMusicRespond.getElapsedtime();
                String artist = eABleMusicRespond.getArtist();
                b.a.C0068b builder = b.a.h.toBuilder();
                if (!TextUtils.isEmpty(content) && (bytes2 = content.getBytes(Charset.forName("UTF-8"))) != null) {
                    if (bytes2.length > 128) {
                        builder.b(new String(bytes2, 0, 128, Charset.forName("UTF-8")));
                    } else {
                        builder.b(content);
                    }
                }
                if (!TextUtils.isEmpty(artist) && (bytes = artist.getBytes(Charset.forName("UTF-8"))) != null) {
                    if (bytes.length > 128) {
                        builder.a(new String(bytes, 0, 128, Charset.forName("UTF-8")));
                    } else {
                        builder.a(artist);
                    }
                }
                if (e_status != null) {
                    builder.b(e_status.getValue());
                }
                if (volume < 0) {
                    builder.d(0);
                } else if (volume > 100) {
                    builder.d(100);
                } else {
                    builder.d(volume);
                }
                if (duration < 0) {
                    builder.a(0);
                } else {
                    builder.a(duration);
                }
                if (elapsedtime < 0) {
                    builder.c(0);
                } else {
                    builder.c(elapsedtime);
                }
                a2 = cVar.a(aVar3.a(builder.build().toByteArray(), Error.WITHOUT_REGISTER_ERROR));
            }
            b.a aVar4 = b.a.east_apex_02;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                dataResponseCallback2 = dataResponseCallback;
                aVar = aVar5;
            } else {
                dataResponseCallback2 = dataResponseCallback;
                aVar = null;
            }
            aVar2.c(aVar, dataResponseCallback2);
        }
    }

    public void normalResponseWatch(@NonNull EABleDataRespond eABleDataRespond, DataResponseCallback dataResponseCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleDataRespond == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                int request_id = eABleDataRespond.getRequest_id();
                EABleDataRespond.ResultCode e_error_code = eABleDataRespond.getE_error_code();
                a2 = cVar.a((request_id <= 0 || e_error_code == null) ? null : aVar3.a(b.C0069b.d.toBuilder().b(request_id).a(e_error_code.getValue()).build().toByteArray(), 2000));
            }
            b.a aVar4 = b.a.east_apex_02;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.c(aVar2, dataResponseCallback);
        }
    }

    public void otaUpdate(@NonNull List<EABleOta> list, OtaCallback otaCallback) {
        ArrayList arrayList;
        ArrayList arrayList2;
        boolean z;
        List<EABleOta> list2 = list;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            if (list2 != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    if (list2.get(i).getOtaType() == null || TextUtils.isEmpty(list2.get(i).getFilePath()) || !new File(list2.get(i).getFilePath()).exists() || new File(list2.get(i).getFilePath()).length() <= 0) {
                        LogUtils.i(aVar.f2168a, "参数不对");
                        LogData2File.getInstance().saveLogData("OTA参数不对");
                        otaCallback.mutualFail(10);
                        return;
                    }
                }
                if (list.size() > 1) {
                    ArrayList arrayList3 = null;
                    int i2 = 0;
                    ArrayList arrayList4 = null;
                    ArrayList arrayList5 = null;
                    ArrayList arrayList6 = null;
                    ArrayList arrayList7 = null;
                    ArrayList arrayList8 = null;
                    ArrayList arrayList9 = null;
                    ArrayList arrayList10 = null;
                    while (i2 < list.size()) {
                        EABleOta eABleOta = list2.get(i2);
                        eABleOta.setByteSize((int) new File(eABleOta.getFilePath()).length());
                        if (eABleOta.getOtaType() == EABleOta.OtaType.apollo) {
                            if (arrayList5 == null) {
                                arrayList5 = new ArrayList();
                            }
                            String upperCase = eABleOta.getVersion().toUpperCase();
                            if (!TextUtils.isEmpty(upperCase)) {
                                eABleOta.setVersionCode(Math.round((Float.parseFloat(upperCase.substring(2, upperCase.indexOf("B"))) * 1000.0f) + (Float.parseFloat(upperCase.substring(upperCase.indexOf("B") + 1, upperCase.length())) * 100.0f)));
                                arrayList5.add(eABleOta);
                            }
                        } else if (eABleOta.getOtaType() == EABleOta.OtaType.hr) {
                            if (arrayList7 == null) {
                                arrayList7 = new ArrayList();
                            }
                            String upperCase2 = eABleOta.getVersion().toUpperCase();
                            if (!TextUtils.isEmpty(upperCase2)) {
                                eABleOta.setVersionCode(Math.round(Float.parseFloat(upperCase2.substring(1, upperCase2.length())) * 1000.0f));
                                arrayList7.add(eABleOta);
                            }
                        } else if (eABleOta.getOtaType() == EABleOta.OtaType.agps) {
                            if (arrayList8 == null) {
                                arrayList8 = new ArrayList();
                            }
                            arrayList8.add(eABleOta);
                        } else if (eABleOta.getOtaType() == EABleOta.OtaType.gps) {
                            if (arrayList6 == null) {
                                arrayList6 = new ArrayList();
                            }
                            arrayList6.add(eABleOta);
                        } else if (eABleOta.getOtaType() == EABleOta.OtaType.res) {
                            if (arrayList4 == null) {
                                arrayList4 = new ArrayList();
                            }
                            String upperCase3 = eABleOta.getVersion().toUpperCase();
                            if (!TextUtils.isEmpty(upperCase3)) {
                                eABleOta.setVersionCode(Math.round(Float.parseFloat(upperCase3.substring(1, upperCase3.length())) * 1000.0f));
                                arrayList4.add(eABleOta);
                            }
                        } else if (eABleOta.getOtaType() == EABleOta.OtaType.stm32) {
                            if (arrayList10 == null) {
                                arrayList10 = new ArrayList();
                            }
                            arrayList10.add(eABleOta);
                        } else {
                            if (eABleOta.getOtaType() == EABleOta.OtaType.tp) {
                                if (arrayList3 == null) {
                                    arrayList3 = new ArrayList();
                                }
                                String upperCase4 = eABleOta.getVersion().toUpperCase();
                                if (!TextUtils.isEmpty(upperCase4)) {
                                    z = true;
                                    eABleOta.setVersionCode(Math.round(Float.parseFloat(upperCase4.substring(1, upperCase4.length())) * 1000.0f));
                                    arrayList3.add(eABleOta);
                                }
                            } else {
                                z = true;
                                if (eABleOta.getOtaType() == EABleOta.OtaType.user_wf) {
                                    if (arrayList9 == null) {
                                        arrayList9 = new ArrayList();
                                    }
                                    arrayList9.add(eABleOta);
                                }
                            }
                            i2++;
                            list2 = list;
                        }
                        z = true;
                        i2++;
                        list2 = list;
                    }
                    if (arrayList4 == null || arrayList4.isEmpty()) {
                        arrayList = null;
                    } else {
                        Collections.sort(arrayList4);
                        ArrayList arrayList11 = new ArrayList();
                        arrayList11.addAll(arrayList4);
                        arrayList = arrayList11;
                    }
                    if (arrayList6 != null && !arrayList6.isEmpty()) {
                        Collections.sort(arrayList6);
                        ArrayList arrayList12 = arrayList == null ? new ArrayList() : arrayList;
                        arrayList12.addAll(arrayList6);
                        arrayList = arrayList12;
                    }
                    if (arrayList8 != null && !arrayList8.isEmpty()) {
                        Collections.sort(arrayList8);
                        ArrayList arrayList13 = arrayList == null ? new ArrayList() : arrayList;
                        arrayList13.addAll(arrayList8);
                        arrayList = arrayList13;
                    }
                    if (arrayList9 != null && !arrayList9.isEmpty()) {
                        Collections.sort(arrayList9);
                        ArrayList arrayList14 = arrayList == null ? new ArrayList() : arrayList;
                        arrayList14.addAll(arrayList9);
                        arrayList = arrayList14;
                    }
                    if (arrayList7 != null && !arrayList7.isEmpty()) {
                        Collections.sort(arrayList7);
                        ArrayList arrayList15 = arrayList == null ? new ArrayList() : arrayList;
                        arrayList15.addAll(arrayList7);
                        arrayList = arrayList15;
                    }
                    if (arrayList3 != null && !arrayList3.isEmpty()) {
                        Collections.sort(arrayList3);
                        ArrayList arrayList16 = arrayList == null ? new ArrayList() : arrayList;
                        arrayList16.addAll(arrayList3);
                        arrayList = arrayList16;
                    }
                    if (arrayList10 != null && !arrayList10.isEmpty()) {
                        Collections.sort(arrayList10);
                        ArrayList arrayList17 = arrayList == null ? new ArrayList() : arrayList;
                        arrayList17.addAll(arrayList10);
                        arrayList = arrayList17;
                    }
                    if (arrayList5 == null || arrayList5.isEmpty()) {
                        arrayList2 = arrayList;
                    } else {
                        Collections.sort(arrayList5);
                        arrayList2 = arrayList == null ? new ArrayList() : arrayList;
                        arrayList2.add(arrayList5.get(0));
                    }
                    if (arrayList2 != null && !arrayList2.isEmpty()) {
                        for (int i3 = 0; i3 < arrayList2.size(); i3++) {
                            String str = aVar.f2168a;
                            LogUtils.i(str, "排序之后的版本:" + ((EABleOta) arrayList2.get(i3)).getVersion());
                        }
                        com.apex.bluetooth.listener.a aVar2 = aVar.g;
                        if (aVar2 != null) {
                            ((c) aVar2).a(arrayList2, otaCallback);
                            return;
                        }
                        return;
                    }
                    otaCallback.mutualFail(16);
                    return;
                }
                EABleOta eABleOta2 = list2.get(0);
                eABleOta2.setByteSize((int) new File(eABleOta2.getFilePath()).length());
                com.apex.bluetooth.listener.a aVar3 = aVar.g;
                if (aVar3 != null) {
                    ((c) aVar3).a(list2, otaCallback);
                }
            } else if (list2 == null || list.isEmpty()) {
                otaCallback.mutualFail(16);
            }
        }
    }

    public void pushInfo2Watch(@NonNull EABleSocialContact eABleSocialContact, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleSocialContact == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                int id = eABleSocialContact.getId();
                EABleSocialContact.SocialContactOps e_ops = eABleSocialContact.getE_ops();
                EABleSocialContact.SocialContactType socialContactType = eABleSocialContact.geteType();
                String title = eABleSocialContact.getTitle();
                String content = eABleSocialContact.getContent();
                String date = eABleSocialContact.getDate();
                a.b.C0009b builder = a.b.h.toBuilder();
                builder.c(id);
                if (e_ops != null) {
                    builder.a(e_ops.getValue());
                }
                if (socialContactType != null) {
                    builder.b(socialContactType.getValue());
                }
                if (!TextUtils.isEmpty(title)) {
                    byte[] bytes = title.getBytes(Charset.forName("UTF-8"));
                    if (bytes != null && bytes.length > 96) {
                        title = new String(bytes, 0, 96, Charset.forName("UTF-8"));
                    }
                    builder.c(title);
                }
                if (!TextUtils.isEmpty(content)) {
                    byte[] bytes2 = content.getBytes(Charset.forName("UTF-8"));
                    if (bytes2 != null && bytes2.length > 382) {
                        content = new String(bytes2, 0, (int) ResultCode.INVALID_FILE, Charset.forName("UTF-8"));
                    }
                    builder.a(content);
                }
                if (!TextUtils.isEmpty(date)) {
                    byte[] bytes3 = date.getBytes(Charset.forName("UTF-8"));
                    if (bytes3 != null && bytes3.length > 16) {
                        date = new String(bytes3, 0, 16, Charset.forName("UTF-8"));
                    }
                    builder.b(date);
                }
                a2 = cVar.a(aVar3.a(builder.build().toByteArray(), 19));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void queryInfo(@NonNull QueryInfo queryInfo, EABleCallback eABleCallback) {
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            aVar.a(new com.apex.bluetooth.data_package.c.b().a(queryInfo.getQueryWatchInfoType(), queryInfo.getDataType()), eABleCallback);
        }
    }

    public void queryWatchInfo(@NonNull QueryWatchInfoType queryWatchInfoType, EABleCallback eABleCallback) {
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            aVar.a(new com.apex.bluetooth.data_package.c.b().a(queryWatchInfoType, 0), eABleCallback);
        }
    }

    public void requestSyncMotionData(@NonNull MotionReportType motionReportType, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (motionReportType == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                a.n0.b builder = a.n0.l.toBuilder();
                if (motionReportType == MotionReportType.sport_data_req) {
                    builder.f(1);
                }
                if (motionReportType == MotionReportType.sleep_data_req) {
                    builder.e(2);
                }
                if (motionReportType == MotionReportType.hr_data_req) {
                    builder.c(3);
                }
                if (motionReportType == MotionReportType.gps_data_req) {
                    builder.b(4);
                }
                if (motionReportType == MotionReportType.multi_sports_data_req) {
                    builder.d(5);
                }
                if (motionReportType == MotionReportType.blood_oxygen_data_req) {
                    builder.a(6);
                }
                if (motionReportType == MotionReportType.pressure_data_req) {
                    builder.i(7);
                }
                if (motionReportType == MotionReportType.step_freq_data_req) {
                    builder.g(8);
                }
                if (motionReportType == MotionReportType.pace_data_req) {
                    builder.h(9);
                }
                MotionReportType motionReportType2 = MotionReportType.resting_hr_data_req;
                a2 = cVar.a(aVar3.a(builder.build().toByteArray(), 29));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void setAncsSwitch(@NonNull EABleAncsSw eABleAncsSw, GeneralCallback generalCallback) {
        byte[] a2;
        GeneralCallback generalCallback2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleAncsSw == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                EABleAncsSw.EABleAncsSwItem s_incomingcall = eABleAncsSw.getS_incomingcall();
                EABleAncsSw.EABleAncsSwItem s_missedcall = eABleAncsSw.getS_missedcall();
                EABleAncsSw.EABleAncsSwItem s_sms = eABleAncsSw.getS_sms();
                EABleAncsSw.EABleAncsSwItem s_social = eABleAncsSw.getS_social();
                EABleAncsSw.EABleAncsSwItem s_email = eABleAncsSw.getS_email();
                EABleAncsSw.EABleAncsSwItem s_schedule = eABleAncsSw.getS_schedule();
                a.j.b builder = a.j.h.toBuilder();
                if (s_incomingcall != null) {
                    a.j.c.b builder2 = a.j.c.d.toBuilder();
                    CommonAction e_action = s_incomingcall.getE_action();
                    if (e_action != null) {
                        builder2.a(e_action.getValue());
                    }
                    builder2.b(s_incomingcall.getSw() <= 0 ? 0 : 1);
                    builder.b(builder2.build());
                }
                if (s_missedcall != null) {
                    a.j.c.b builder3 = a.j.c.d.toBuilder();
                    CommonAction e_action2 = s_missedcall.getE_action();
                    if (e_action2 != null) {
                        builder3.a(e_action2.getValue());
                    }
                    builder3.b(s_missedcall.getSw() <= 0 ? 0 : 1);
                    builder.c(builder3.build());
                }
                if (s_sms != null) {
                    a.j.c.b builder4 = a.j.c.d.toBuilder();
                    CommonAction e_action3 = s_sms.getE_action();
                    if (e_action3 != null) {
                        builder4.a(e_action3.getValue());
                    }
                    builder4.b(s_sms.getSw() <= 0 ? 0 : 1);
                    builder.e(builder4.build());
                }
                if (s_social != null) {
                    a.j.c.b builder5 = a.j.c.d.toBuilder();
                    CommonAction e_action4 = s_social.getE_action();
                    if (e_action4 != null) {
                        builder5.a(e_action4.getValue());
                    }
                    builder5.b(s_social.getSw() <= 0 ? 0 : 1);
                    builder.f(builder5.build());
                }
                if (s_email != null) {
                    a.j.c.b builder6 = a.j.c.d.toBuilder();
                    CommonAction e_action5 = s_email.getE_action();
                    if (e_action5 != null) {
                        builder6.a(e_action5.getValue());
                    }
                    builder6.b(s_email.getSw() <= 0 ? 0 : 1);
                    builder.a(builder6.build());
                }
                if (s_schedule != null) {
                    a.j.c.b builder7 = a.j.c.d.toBuilder();
                    CommonAction e_action6 = s_schedule.getE_action();
                    if (e_action6 != null) {
                        builder7.a(e_action6.getValue());
                    }
                    builder7.b(s_schedule.getSw() <= 0 ? 0 : 1);
                    builder.d(builder7.build());
                }
                a2 = cVar.a(aVar3.a(builder.build().toByteArray(), 21));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                generalCallback2 = generalCallback;
                aVar2 = aVar5;
            } else {
                generalCallback2 = generalCallback;
            }
            aVar.a(aVar2, generalCallback2);
        }
    }

    public void setAppPushSwitch(@NonNull EABleInfoPush eABleInfoPush, GeneralCallback generalCallback) {
        byte[] bArr;
        byte[] a2;
        int length;
        byte[] bArr2;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleInfoPush == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                if (eABleInfoPush.getS_app_sw() == null || eABleInfoPush.getS_app_sw().isEmpty()) {
                    bArr = null;
                } else {
                    a.k.b builder = a.k.c.toBuilder();
                    List<EABleInfoPush.EABlePushSwitch> s_app_sw = eABleInfoPush.getS_app_sw();
                    for (int i = 0; i < s_app_sw.size(); i++) {
                        builder.a(a.k.c.c.toBuilder().a(s_app_sw.get(i).getSw()).build());
                    }
                    bArr = aVar3.a(builder.build().toByteArray(), 34);
                }
                a2 = cVar.a(bArr);
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i2 = i.f2187a;
                if (length2 <= i2) {
                    length = 1;
                } else if (a2.length % i2 == 0) {
                    length = a2.length / i2;
                } else {
                    length = (a2.length / i2) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i3 = 0; i3 < length; i3++) {
                        int i4 = length - 1;
                        if (i3 < i4) {
                            bArr2 = new byte[i.f2187a];
                        } else {
                            bArr2 = new byte[a2.length - (i4 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i3, bArr2, 0, bArr2.length);
                        aVar5.a(bArr2);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void setAutoMonitor(@NonNull EABleAutoMonitor eABleAutoMonitor, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleAutoMonitor == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                a.e.b builder = a.e.f.toBuilder();
                builder.a(eABleAutoMonitor.getBlood_oxy_check_sw() <= 0 ? 0 : 1);
                builder.b(eABleAutoMonitor.getBlood_pressure_check_sw() <= 0 ? 0 : 1);
                builder.d(eABleAutoMonitor.getHeart_check_sw() <= 0 ? 0 : 1);
                builder.c(eABleAutoMonitor.getCheck_time() <= 0 ? 5 : eABleAutoMonitor.getCheck_time());
                a2 = cVar.a(aVar3.a(builder.build().toByteArray(), 37));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void setAutoSleepCheck(@NonNull EABleAutoCheckSleep eABleAutoCheckSleep, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleAutoCheckSleep == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                a.n.b builder = a.n.g.toBuilder();
                builder.e(eABleAutoCheckSleep.getWeek_cycle_bit());
                int begin_hour = eABleAutoCheckSleep.getBegin_hour();
                int begin_minute = eABleAutoCheckSleep.getBegin_minute();
                int end_hour = eABleAutoCheckSleep.getEnd_hour();
                int end_minute = eABleAutoCheckSleep.getEnd_minute();
                if (begin_hour >= 0) {
                    builder.a(begin_hour);
                }
                if (begin_minute >= 0 && begin_minute < 60) {
                    builder.b(begin_minute);
                }
                if (end_hour >= 0) {
                    builder.c(end_hour);
                }
                if (end_minute >= 0 && end_minute < 60) {
                    builder.d(end_minute);
                }
                a2 = cVar.a(aVar3.a(builder.build().toByteArray(), 16));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void setBatteryInfo(@NonNull EABleBatInfo eABleBatInfo, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleBatInfo == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                a.f.b builder = a.f.d.toBuilder();
                BatInfoStatus e_status = eABleBatInfo.getE_status();
                int i = builder.b;
                if (e_status != null) {
                    builder.a(e_status.getValue());
                }
                if (i >= 0) {
                    builder.b(i);
                }
                a2 = cVar.a(aVar3.a(builder.build().toByteArray(), 9));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i2 = i.f2187a;
                if (length2 <= i2) {
                    length = 1;
                } else if (a2.length % i2 == 0) {
                    length = a2.length / i2;
                } else {
                    length = (a2.length / i2) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i3 = 0; i3 < length; i3++) {
                        int i4 = length - 1;
                        if (i3 < i4) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i4 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i3, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void setBlackScreenTimeout(int i, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        if (i < 0) {
            i = 0;
        }
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (i < 0) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                a2 = cVar.a(i < 0 ? null : aVar3.a(a.q.c.toBuilder().a(i).build().toByteArray(), 8));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i2 = i.f2187a;
                if (length2 <= i2) {
                    length = 1;
                } else if (a2.length % i2 == 0) {
                    length = a2.length / i2;
                } else {
                    length = (a2.length / i2) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i3 = 0; i3 < length; i3++) {
                        int i4 = length - 1;
                        if (i3 < i4) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i4 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i3, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void setBloodPressure(@NonNull EABleBloodPressure eABleBloodPressure, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleBloodPressure == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                a.g.b builder = a.g.d.toBuilder();
                builder.a(eABleBloodPressure.getHigh_blood_val());
                builder.b(eABleBloodPressure.getLow_blood_val());
                a2 = cVar.a(aVar3.a(builder.build().toByteArray(), 36));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void setCaloriesSwitch(@NonNull int i, GeneralCallback generalCallback) {
        com.apex.bluetooth.data_package.a aVar;
        int length;
        byte[] bArr;
        a aVar2 = this.eaBleBluetoothConnect;
        if (aVar2 != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
            Objects.requireNonNull(aVar3);
            byte[] a2 = cVar.a(aVar3.a(a.o.c.toBuilder().a(i <= 0 ? 0 : 1).build().toByteArray(), 27));
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i2 = i.f2187a;
                if (length2 <= i2) {
                    length = 1;
                } else if (a2.length % i2 == 0) {
                    length = a2.length / i2;
                } else {
                    length = (a2.length / i2) + 1;
                }
                aVar = new com.apex.bluetooth.data_package.a(length);
                aVar.b = aVar4;
                if (length == 1) {
                    aVar.a(a2);
                } else {
                    for (int i3 = 0; i3 < length; i3++) {
                        int i4 = length - 1;
                        if (i3 < i4) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i4 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i3, bArr, 0, bArr.length);
                        aVar.a(bArr);
                    }
                }
            } else {
                aVar = null;
            }
            aVar2.a(aVar, generalCallback);
        }
    }

    public void setDailyGoal(@NonNull EABleDailyGoal eABleDailyGoal, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleDailyGoal == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                EABleDailyGoal.EABleDaily s_step = eABleDailyGoal.getS_step();
                EABleDailyGoal.EABleDaily s_calorie = eABleDailyGoal.getS_calorie();
                EABleDailyGoal.EABleDaily s_distance = eABleDailyGoal.getS_distance();
                EABleDailyGoal.EABleDaily s_duration = eABleDailyGoal.getS_duration();
                EABleDailyGoal.EABleDaily s_sleep = eABleDailyGoal.getS_sleep();
                a.r.b builder = a.r.g.toBuilder();
                if (s_step != null) {
                    a.r.c.b builder2 = a.r.c.d.toBuilder();
                    builder2.b(s_step.getSw() > 0 ? 1 : 0);
                    if (s_step.getGoal() > 0) {
                        builder2.a(s_step.getGoal());
                    }
                    builder.e(builder2.build());
                }
                if (s_calorie != null) {
                    a.r.c.b builder3 = a.r.c.d.toBuilder();
                    builder3.b(s_calorie.getSw() > 0 ? 1 : 0);
                    if (s_calorie.getGoal() > 0) {
                        builder3.a(s_calorie.getGoal());
                    }
                    builder.a(builder3.build());
                }
                if (s_distance != null) {
                    a.r.c.b builder4 = a.r.c.d.toBuilder();
                    builder4.b(s_distance.getSw() > 0 ? 1 : 0);
                    if (s_distance.getGoal() > 0) {
                        builder4.a(s_distance.getGoal());
                    }
                    builder.b(builder4.build());
                }
                if (s_duration != null) {
                    a.r.c.b builder5 = a.r.c.d.toBuilder();
                    builder5.b(s_duration.getSw() > 0 ? 1 : 0);
                    if (s_duration.getGoal() > 0) {
                        builder5.a(s_duration.getGoal());
                    }
                    builder.c(builder5.build());
                }
                if (s_sleep != null) {
                    a.r.c.b builder6 = a.r.c.d.toBuilder();
                    builder6.b(s_sleep.getSw() > 0 ? 1 : 0);
                    if (s_sleep.getGoal() > 0) {
                        builder6.a(s_sleep.getGoal());
                    }
                    builder.d(builder6.build());
                }
                a2 = cVar.a(aVar3.a(builder.build().toByteArray(), 15));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void setData2Watch(@NonNull EABleAppSportData eABleAppSportData, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleAppSportData == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                d.b.C0095b builder = d.b.e.toBuilder();
                builder.c(eABleAppSportData.getPace());
                builder.a(eABleAppSportData.getDistance());
                builder.b(eABleAppSportData.getDuration());
                a2 = cVar.a(aVar3.a(builder.build().toByteArray(), 47));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void setDevLanguage(@NonNull EABleDeviceLanguage eABleDeviceLanguage, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleDeviceLanguage == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                EABleDeviceLanguage.LanguageType e_type = eABleDeviceLanguage.getE_type();
                a2 = cVar.a(e_type == null ? null : aVar3.a(a.a0.d.toBuilder().a(e_type.getValue()).build().toByteArray(), 10));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void setDeviceOps(@NonNull EABleDev eABleDev, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleDev == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                EABleDev.DevOps e_ops = eABleDev.getE_ops();
                eABleDev.getE_ops_status();
                a2 = cVar.a(e_ops == null ? null : aVar3.a(a.s.d.toBuilder().a(e_ops.getValue()).build().toByteArray(), 12));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void setDistanceUnit(@NonNull EABleDistanceFormat eABleDistanceFormat, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleDistanceFormat == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                EABleDistanceFormat.DistanceUnit e_format = eABleDistanceFormat.getE_format();
                a2 = cVar.a(e_format == null ? null : aVar3.a(a.t.c.toBuilder().a(e_format.getValue()).build().toByteArray(), 24));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void setGesturesSwitch(@NonNull EABleGesturesBrightScreen eABleGesturesBrightScreen, GeneralCallback generalCallback) {
        byte[] bArr;
        int length;
        byte[] bArr2;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.c.a aVar2 = cVar.f2211a;
            Objects.requireNonNull(aVar2);
            com.apex.bluetooth.data_package.a aVar3 = null;
            if (eABleGesturesBrightScreen != null) {
                a.u.b e = a.u.g.toBuilder().a(eABleGesturesBrightScreen.getBegin_hour()).b(eABleGesturesBrightScreen.getBegin_minute()).d(eABleGesturesBrightScreen.getEnd_hour()).e(eABleGesturesBrightScreen.getEnd_minute());
                if (eABleGesturesBrightScreen.getBrightScreenSwitch() != null) {
                    e.c(eABleGesturesBrightScreen.getBrightScreenSwitch().getValue());
                }
                bArr = aVar2.a(e.build().toByteArray(), 28);
            } else {
                bArr = null;
            }
            byte[] a2 = cVar.a(bArr);
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                aVar3 = new com.apex.bluetooth.data_package.a(length);
                aVar3.b = aVar4;
                if (length == 1) {
                    aVar3.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr2 = new byte[i.f2187a];
                        } else {
                            bArr2 = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr2, 0, bArr2.length);
                        aVar3.a(bArr2);
                    }
                }
            }
            aVar.a(aVar3, generalCallback);
        }
    }

    public void setHabit(@NonNull EABleHabit eABleHabit, HabitResultCallback habitResultCallback) {
        byte[] a2;
        byte[] a3;
        byte[] bytes;
        HabitResultCallback habitResultCallback2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleHabit == null) {
                a3 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                a.v.b builder = a.v.f.toBuilder();
                builder.b(eABleHabit.getId());
                if (eABleHabit.getE_ops() != null) {
                    builder.a(eABleHabit.getE_ops().getValue());
                }
                List<EABleHabit.HabitItem> itemList = eABleHabit.getItemList();
                if (itemList != null && !itemList.isEmpty()) {
                    if (itemList.size() <= 20) {
                        for (int i = 0; i < itemList.size(); i++) {
                            a.v.f.b builder2 = a.v.f.p.toBuilder();
                            String content = itemList.get(i).getContent();
                            if (!TextUtils.isEmpty(content) && (bytes = content.getBytes(Charset.forName("UTF-8"))) != null && bytes.length > 64) {
                                content = new String(bytes, 0, 64, Charset.forName("UTF-8"));
                            }
                            builder2.a(content);
                            builder2.b(itemList.get(i).getBegin_hour());
                            builder2.c(itemList.get(i).getBegin_minute());
                            builder2.d(itemList.get(i).getDuration());
                            builder2.h(itemList.get(i).getEnd_hour());
                            builder2.i(itemList.get(i).getEnd_minute());
                            if (itemList.get(i).getE_action() != null) {
                                builder2.e(itemList.get(i).getE_action().getValue());
                            }
                            builder2.j(itemList.get(i).getGreenColor());
                            builder2.l(itemList.get(i).getRedColor());
                            builder2.a(itemList.get(i).getBlueColor());
                            builder2.m(itemList.get(i).getCycle());
                            if (itemList.get(i).getE_icon_id() != null) {
                                builder2.g(itemList.get(i).getE_icon_id().getValue());
                            }
                            if (itemList.get(i).getHabitState() != null) {
                                builder2.f(itemList.get(i).getHabitState().getValue());
                            }
                            builder2.k(itemList.get(i).getId());
                            builder.a(builder2);
                        }
                    } else {
                        a2 = null;
                        a3 = cVar.a(a2);
                    }
                }
                List<Integer> deleteList = eABleHabit.getDeleteList();
                if (deleteList != null && !deleteList.isEmpty()) {
                    for (int i2 = 0; i2 < deleteList.size(); i2++) {
                        a.v.c.b builder3 = a.v.c.c.toBuilder();
                        builder3.a(deleteList.get(i2).intValue());
                        builder.a(builder3);
                    }
                }
                a2 = aVar3.a(builder.build().toByteArray(), 38);
                a3 = cVar.a(a2);
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a3 != null) {
                int length2 = a3.length;
                int i3 = i.f2187a;
                if (length2 <= i3) {
                    length = 1;
                } else if (a3.length % i3 == 0) {
                    length = a3.length / i3;
                } else {
                    length = (a3.length / i3) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a3);
                } else {
                    for (int i4 = 0; i4 < length; i4++) {
                        int i5 = length - 1;
                        if (i4 < i5) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a3.length - (i5 * i.f2187a)];
                        }
                        System.arraycopy(a3, i.f2187a * i4, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                habitResultCallback2 = habitResultCallback;
                aVar2 = aVar5;
            } else {
                habitResultCallback2 = habitResultCallback;
            }
            aVar.a(aVar2, habitResultCallback2);
        }
    }

    public void setHeartRateIntervalTime(int i, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            if (i <= 0) {
                i = 0;
            }
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (i < 0) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                a2 = cVar.a(i < 0 ? null : aVar3.a(a.m.c.toBuilder().a(i).build().toByteArray(), 17));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i2 = i.f2187a;
                if (length2 <= i2) {
                    length = 1;
                } else if (a2.length % i2 == 0) {
                    length = a2.length / i2;
                } else {
                    length = (a2.length / i2) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i3 = 0; i3 < length; i3++) {
                        int i4 = length - 1;
                        if (i3 < i4) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i4 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i3, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void setHeartRateLimit(@NonNull EABleHr eABleHr, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleHr == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                a.z.b builder = a.z.e.toBuilder();
                builder.c(eABleHr.getSw() <= 0 ? 0 : 1);
                int max_hr = eABleHr.getMax_hr();
                int min_hr = eABleHr.getMin_hr();
                if (min_hr > 0) {
                    builder.b(min_hr);
                }
                if (max_hr > 0) {
                    builder.a(max_hr);
                }
                a2 = cVar.a(aVar3.a(builder.build().toByteArray(), 26));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void setHomeTimeZone(@NonNull EABleHomeTimeZone eABleHomeTimeZone, GeneralCallback generalCallback) {
        byte[] bArr;
        byte[] a2;
        byte[] bytes;
        int length;
        byte[] bArr2;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleHomeTimeZone == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                List<EABleHomeTimeZone.EABleHomeZone> s_home = eABleHomeTimeZone.getS_home();
                if (s_home == null || s_home.isEmpty() || s_home.size() > 8) {
                    bArr = null;
                } else {
                    a.y.b builder = a.y.c.toBuilder();
                    for (int i = 0; i < s_home.size(); i++) {
                        EABleHomeTimeZone.EABleHomeZone eABleHomeZone = s_home.get(i);
                        if (eABleHomeZone != null) {
                            String place = eABleHomeZone.getPlace();
                            if (!TextUtils.isEmpty(place) && (bytes = place.getBytes(Charset.forName("UTF-8"))) != null && bytes.length > 96) {
                                place = new String(bytes, 0, 96, Charset.forName(JsonRequest.PROTOCOL_CHARSET));
                            }
                            a.y.c.b builder2 = a.y.c.f.toBuilder();
                            builder2.a(place);
                            TimeZone e_time_zone = eABleHomeZone.getE_time_zone();
                            if (e_time_zone != null) {
                                builder2.a(e_time_zone.getValue());
                            }
                            if (eABleHomeZone.getTime_zone_hour() >= 0) {
                                builder2.b(eABleHomeZone.getTime_zone_hour());
                            }
                            if (eABleHomeZone.getTime_zone_minute() >= 0) {
                                builder2.c(eABleHomeZone.getTime_zone_minute());
                            }
                            builder.a(builder2.build());
                        }
                    }
                    bArr = aVar3.a(builder.build().toByteArray(), 14);
                }
                a2 = cVar.a(bArr);
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i2 = i.f2187a;
                if (length2 <= i2) {
                    length = 1;
                } else if (a2.length % i2 == 0) {
                    length = a2.length / i2;
                } else {
                    length = (a2.length / i2) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i3 = 0; i3 < length; i3++) {
                        int i4 = length - 1;
                        if (i3 < i4) {
                            bArr2 = new byte[i.f2187a];
                        } else {
                            bArr2 = new byte[a2.length - (i4 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i3, bArr2, 0, bArr2.length);
                        aVar5.a(bArr2);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void setMacAddress(@NonNull EABleWatchInfo eABleWatchInfo, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleWatchInfo == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                a.q0.b builder = a.q0.t.toBuilder();
                builder.a(eABleWatchInfo.getWatchId());
                a2 = cVar.a(aVar3.a(builder.build().toByteArray(), 3));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x021f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setMenstrualCycle(@androidx.annotation.NonNull com.apex.bluetooth.model.EABlePhysiologyData r35, com.apex.bluetooth.callback.GeneralCallback r36) {
        /*
            Method dump skipped, instructions count: 587
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apex.bluetooth.core.EABleManager.setMenstrualCycle(com.apex.bluetooth.model.EABlePhysiologyData, com.apex.bluetooth.callback.GeneralCallback):void");
    }

    public void setMenuPage(@NonNull EABleMenuPage eABleMenuPage, GeneralCallback generalCallback) {
        byte[] bArr;
        byte[] a2;
        int length;
        byte[] bArr2;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleMenuPage == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                a.x.b builder = a.x.d.toBuilder();
                List<EABleMenuPage.MenuType> typeList = eABleMenuPage.getTypeList();
                if (typeList == null || typeList.isEmpty()) {
                    bArr = null;
                } else {
                    for (int i = 0; i < typeList.size(); i++) {
                        EABleMenuPage.MenuType menuType = typeList.get(i);
                        if (menuType != null) {
                            a.x.c.b builder2 = a.x.c.c.toBuilder();
                            builder2.a(menuType.getValue());
                            builder.a(builder2.build());
                        }
                    }
                    bArr = aVar3.a(builder.build().toByteArray(), 31);
                }
                a2 = cVar.a(bArr);
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i2 = i.f2187a;
                if (length2 <= i2) {
                    length = 1;
                } else if (a2.length % i2 == 0) {
                    length = a2.length / i2;
                } else {
                    length = (a2.length / i2) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i3 = 0; i3 < length; i3++) {
                        int i4 = length - 1;
                        if (i3 < i4) {
                            bArr2 = new byte[i.f2187a];
                        } else {
                            bArr2 = new byte[a2.length - (i4 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i3, bArr2, 0, bArr2.length);
                        aVar5.a(bArr2);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void setNotDisturb(@NonNull EABleNotDisturb eABleNotDisturb, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleNotDisturb == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                a2 = cVar.a(aVar3.a(a.d0.h.toBuilder().e(eABleNotDisturb.getSw() <= 0 ? 0 : 1).a(eABleNotDisturb.getBegin_hour() > 0 ? eABleNotDisturb.getBegin_hour() : 0).b(eABleNotDisturb.getBegin_minute() > 0 ? eABleNotDisturb.getBegin_minute() : 0).c(eABleNotDisturb.getEnd_hour() > 0 ? eABleNotDisturb.getEnd_hour() : 0).d(eABleNotDisturb.getEnd_minute() > 0 ? eABleNotDisturb.getEnd_minute() : 0).f(eABleNotDisturb.getWatch_sw() <= 0 ? 0 : 1).build().toByteArray(), 13));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void setOpsBinding(@NonNull EABleBindInfo eABleBindInfo, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleBindInfo == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                EABleBindInfo.BindingOps e_ops = eABleBindInfo.getE_ops();
                String user_id = eABleBindInfo.getUser_id();
                a.c.b builder = a.c.e.toBuilder();
                if (!TextUtils.isEmpty(user_id)) {
                    byte[] bytes = user_id.getBytes(Charset.forName("UTF-8"));
                    if (bytes != null && bytes.length > 32) {
                        user_id = new String(bytes, 0, 32, Charset.forName("UTF-8"));
                    }
                    builder.a(user_id);
                }
                if (e_ops != null) {
                    builder.b(e_ops.getValue());
                }
                builder.a(eABleBindInfo.getBind_mod() <= 0 ? 0 : 1);
                a2 = cVar.a(aVar3.a(builder.build().toByteArray(), 6));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            if (aVar2 != null && eABleBindInfo.getE_ops() != null && eABleBindInfo.getE_ops() == EABleBindInfo.BindingOps.normal_begin) {
                aVar2.d = true;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void setPeriodReminder(@NonNull EABlePeriodReminder eABlePeriodReminder, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABlePeriodReminder == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                d.e.b builder = d.e.f.toBuilder();
                builder.a(eABlePeriodReminder.getPregnancyStart() > 0 ? 1 : 0);
                builder.b(eABlePeriodReminder.getPregnancyEnd() > 0 ? 1 : 0);
                builder.c(eABlePeriodReminder.getPeriodStart() > 0 ? 1 : 0);
                builder.d(eABlePeriodReminder.getPeriodEnd() > 0 ? 1 : 0);
                a2 = cVar.a(aVar3.a(builder.build().toByteArray(), 55));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:65:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x019b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setReminderOrder(@androidx.annotation.NonNull com.apex.bluetooth.model.EABleReminder r26, com.apex.bluetooth.callback.EditAttentionCallback r27) {
        /*
            Method dump skipped, instructions count: 459
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apex.bluetooth.core.EABleManager.setReminderOrder(com.apex.bluetooth.model.EABleReminder, com.apex.bluetooth.callback.EditAttentionCallback):void");
    }

    public void setScreenBrightness(int i, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        if (i < 0) {
            i = 0;
        }
        if (i > 100) {
            i = 100;
        }
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (i < 0) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                a2 = cVar.a((i < 0 || i > 100) ? null : aVar3.a(a.p.c.toBuilder().a(i).build().toByteArray(), 7));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i2 = i.f2187a;
                if (length2 <= i2) {
                    length = 1;
                } else if (a2.length % i2 == 0) {
                    length = a2.length / i2;
                } else {
                    length = (a2.length / i2) + 1;
                }
                aVar2 = new com.apex.bluetooth.data_package.a(length);
                aVar2.b = aVar4;
                if (length == 1) {
                    aVar2.a(a2);
                } else {
                    for (int i3 = 0; i3 < length; i3++) {
                        int i4 = length - 1;
                        if (i3 < i4) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i4 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i3, bArr, 0, bArr.length);
                        aVar2.a(bArr);
                    }
                }
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void setSitCheck(@NonNull EABleSedentariness eABleSedentariness, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleSedentariness == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                int interval = eABleSedentariness.getInterval();
                a.l.b builder = a.l.o.toBuilder();
                if (interval <= 0) {
                    interval = 0;
                }
                builder.e(interval);
                builder.m(eABleSedentariness.getWeek_cycle_bit());
                int begin_hour = eABleSedentariness.getBegin_hour();
                int begin_minute = eABleSedentariness.getBegin_minute();
                int end_hour = eABleSedentariness.getEnd_hour();
                int end_minute = eABleSedentariness.getEnd_minute();
                int step_threshold = eABleSedentariness.getStep_threshold();
                if (begin_hour >= 0 && begin_hour < 24) {
                    builder.a(begin_hour);
                }
                if (begin_minute >= 0 && begin_minute < 60) {
                    builder.b(begin_minute);
                }
                if (end_hour >= 0 && begin_hour < 24) {
                    builder.c(end_hour);
                }
                if (end_minute >= 0 && end_minute < 60) {
                    builder.d(end_minute);
                }
                if (step_threshold > 0) {
                    builder.k(step_threshold);
                }
                builder.l(eABleSedentariness.getSw());
                builder.j(eABleSedentariness.getNoon_sw());
                int noon_begin_hour = eABleSedentariness.getNoon_begin_hour();
                if (noon_begin_hour >= 0 && noon_begin_hour < 24) {
                    builder.f(noon_begin_hour);
                }
                int noon_begin_minute = eABleSedentariness.getNoon_begin_minute();
                if (noon_begin_minute >= 0 && noon_begin_minute < 60) {
                    builder.g(noon_begin_minute);
                }
                int noon_end_hour = eABleSedentariness.getNoon_end_hour();
                if (noon_end_hour >= 0 && noon_end_hour < 24) {
                    builder.h(noon_end_hour);
                }
                int noon_end_minute = eABleSedentariness.getNoon_end_minute();
                if (noon_end_minute >= 0 && noon_end_minute < 60) {
                    builder.i(noon_end_minute);
                }
                a2 = cVar.a(aVar3.a(builder.build().toByteArray(), 18));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void setTimeSync(@NonNull EABleSyncTime eABleSyncTime, GeneralCallback generalCallback) {
        byte[] a2;
        GeneralCallback generalCallback2;
        com.apex.bluetooth.data_package.a aVar;
        int length;
        byte[] bArr;
        a aVar2 = this.eaBleBluetoothConnect;
        if (aVar2 != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            if (eABleSyncTime == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                Log.e(aVar3.f2209a, "同步的时间参数:" + eABleSyncTime.toString());
                int year = eABleSyncTime.getYear();
                int month = eABleSyncTime.getMonth();
                int day = eABleSyncTime.getDay();
                int hour = eABleSyncTime.getHour();
                int minute = eABleSyncTime.getMinute();
                int second = eABleSyncTime.getSecond();
                EABleSyncTime.HourSystem e_hour_system = eABleSyncTime.getE_hour_system();
                EABleSyncTime.SyncMode e_sync_mode = eABleSyncTime.getE_sync_mode();
                TimeZone e_time_zone = eABleSyncTime.getE_time_zone();
                int time_zone_hour = eABleSyncTime.getTime_zone_hour();
                int time_zone_minute = eABleSyncTime.getTime_zone_minute();
                a.l0.b builder = a.l0.m.toBuilder();
                if (year > 0) {
                    builder.h(year);
                }
                if (month > 0) {
                    builder.d(month);
                }
                if (day > 0) {
                    builder.a(day);
                }
                if (hour >= 0) {
                    builder.b(hour);
                }
                if (minute >= 0) {
                    builder.c(minute);
                }
                if (second >= 0) {
                    builder.e(second);
                }
                builder.f(time_zone_hour);
                builder.g(time_zone_minute);
                if (e_hour_system != null) {
                    if (e_hour_system == EABleSyncTime.HourSystem.hour_24) {
                        builder.a(a.l0.c.hour_24);
                    } else {
                        builder.a(a.l0.c.hour_12);
                    }
                }
                if (e_sync_mode != null) {
                    if (e_sync_mode == EABleSyncTime.SyncMode.normal) {
                        builder.a(a.l0.d.normal);
                    } else {
                        builder.a(a.l0.d.watch);
                    }
                }
                if (e_time_zone != null) {
                    if (e_time_zone == TimeZone.zero) {
                        builder.a(a.l0.e.zero);
                    } else if (e_time_zone == TimeZone.east) {
                        builder.a(a.l0.e.east);
                    } else {
                        builder.a(a.l0.e.west);
                    }
                }
                a2 = cVar.a(aVar3.a(builder.build().toByteArray(), 5));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                generalCallback2 = generalCallback;
                aVar = aVar5;
            } else {
                generalCallback2 = generalCallback;
                aVar = null;
            }
            aVar2.a(aVar, generalCallback2);
        }
    }

    public void setUnifiedUnit(@NonNull EABleDevUnit eABleDevUnit, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleDevUnit == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                UnitFormat e_format = eABleDevUnit.getE_format();
                a2 = cVar.a(e_format == null ? null : aVar3.a(a.m0.c.toBuilder().a(e_format.getValue()).build().toByteArray(), 11));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void setUserInfo(@NonNull EABlePersonInfo eABlePersonInfo, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABlePersonInfo == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                int age = eABlePersonInfo.getAge();
                PersonHand e_hand_info = eABlePersonInfo.getE_hand_info();
                EABlePersonInfo.PersonSex e_sex_info = eABlePersonInfo.getE_sex_info();
                EABlePersonInfo.SkinColor e_skin_color = eABlePersonInfo.getE_skin_color();
                int height = eABlePersonInfo.getHeight();
                int weight = eABlePersonInfo.getWeight();
                a.o0.b builder = a.o0.h.toBuilder();
                if (age > 0) {
                    builder.a(age);
                }
                if (e_hand_info != null) {
                    builder.b(e_hand_info.getValue());
                }
                if (e_sex_info != null) {
                    builder.c(e_sex_info.getValue());
                }
                if (height > 0) {
                    builder.d(height);
                }
                if (weight > 0) {
                    builder.e(weight);
                }
                if (e_skin_color != null) {
                    if (e_skin_color == EABlePersonInfo.SkinColor.skin_yellow_black) {
                        builder.a(a.o0.e.skin_yellow_black);
                    } else if (e_skin_color == EABlePersonInfo.SkinColor.skin_white) {
                        builder.a(a.o0.e.skin_white);
                    } else if (e_skin_color == EABlePersonInfo.SkinColor.skin_yellow) {
                        builder.a(a.o0.e.skin_yellow);
                    } else if (e_skin_color == EABlePersonInfo.SkinColor.skin_white_yellow) {
                        builder.a(a.o0.e.skin_white_yellow);
                    } else if (e_skin_color == EABlePersonInfo.SkinColor.skin_balck) {
                        builder.a(a.o0.e.skin_balck);
                    }
                }
                a2 = cVar.a(aVar3.a(builder.build().toByteArray(), 4));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void setVibrateMode(@NonNull VibrationIntensity vibrationIntensity, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (vibrationIntensity == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                a.p0.b builder = a.p0.c.toBuilder();
                builder.a(vibrationIntensity.getValue());
                a2 = cVar.a(aVar3.a(builder.build().toByteArray(), 53));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void setWatchFace(@NonNull EABleWatchFace eABleWatchFace, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleWatchFace == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                a.r0.b builder = a.r0.h.toBuilder();
                builder.a(eABleWatchFace.getId());
                String user_wf_id = eABleWatchFace.getUser_wf_id();
                if (!TextUtils.isEmpty(user_wf_id)) {
                    builder.a(user_wf_id);
                }
                a2 = cVar.a(aVar3.a(builder.build().toByteArray(), 33));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void setWeather(@NonNull EABleWeather eABleWeather, GeneralCallback generalCallback) {
        byte[] a2;
        byte[] a3;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleWeather == null) {
                a3 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                a.s0.b builder = a.s0.f.toBuilder();
                String place = eABleWeather.getPlace();
                if (!TextUtils.isEmpty(place)) {
                    byte[] bytes = place.getBytes(Charset.forName("UTF-8"));
                    if (bytes.length > 96) {
                        place = new String(bytes, 0, 96, Charset.forName("UTF-8"));
                    }
                    builder.a(place);
                }
                builder.a((int) eABleWeather.getCurrent_temperature());
                if (eABleWeather.getTemperatureUnit() != null) {
                    if (eABleWeather.getTemperatureUnit() == EABleWeather.TemperatureUnit.centigrade) {
                        builder.a(a.s0.e.centigrade);
                    } else {
                        builder.a(a.s0.e.fahrenheit);
                    }
                }
                List<EABleWeather.EABleWeatherItem> s_day = eABleWeather.getS_day();
                if (s_day != null && !s_day.isEmpty()) {
                    if (s_day.size() <= 8) {
                        for (int i = 0; i < s_day.size(); i++) {
                            a.s0.d.b builder2 = a.s0.d.p.toBuilder();
                            builder2.f(s_day.get(i).getMin_temperature());
                            builder2.d(s_day.get(i).getMax_temperature());
                            builder2.b(s_day.get(i).getAir_humidity());
                            builder2.c(s_day.get(i).getCloudiness());
                            builder2.e(s_day.get(i).getMax_wind_power());
                            builder2.g(s_day.get(i).getMin_wind_power());
                            builder2.h((int) s_day.get(i).getSunrise_timestamp());
                            builder2.i((int) s_day.get(i).getSunset_timestamp());
                            builder2.a(s_day.get(i).getAir_grade());
                            EABleWeather.AirQuality e_air = s_day.get(i).getE_air();
                            if (e_air != null) {
                                if (e_air == EABleWeather.AirQuality.good) {
                                    builder2.a(a.s0.c.good);
                                } else if (e_air == EABleWeather.AirQuality.bad) {
                                    builder2.a(a.s0.c.bad);
                                } else {
                                    builder2.a(a.s0.c.excellent);
                                }
                            }
                            EABleWeather.WeatherType e_day_type = s_day.get(i).getE_day_type();
                            if (e_day_type != null) {
                                if (e_day_type == EABleWeather.WeatherType.clear) {
                                    builder2.a(a.s0.h.clear);
                                } else if (e_day_type == EABleWeather.WeatherType.cloudy) {
                                    builder2.a(a.s0.h.cloudy);
                                } else if (e_day_type == EABleWeather.WeatherType.drizzle) {
                                    builder2.a(a.s0.h.drizzle);
                                } else if (e_day_type == EABleWeather.WeatherType.gloomy) {
                                    builder2.a(a.s0.h.gloomy);
                                } else if (e_day_type == EABleWeather.WeatherType.moderate_rain) {
                                    builder2.a(a.s0.h.moderate_rain);
                                } else if (e_day_type == EABleWeather.WeatherType.thunderstorm) {
                                    builder2.a(a.s0.h.thunderstorm);
                                } else if (e_day_type == EABleWeather.WeatherType.heavy_rain) {
                                    builder2.a(a.s0.h.heavy_rain);
                                } else if (e_day_type == EABleWeather.WeatherType.sleet) {
                                    builder2.a(a.s0.h.sleet);
                                } else if (e_day_type == EABleWeather.WeatherType.light_snow) {
                                    builder2.a(a.s0.h.light_snow);
                                } else if (e_day_type == EABleWeather.WeatherType.moderate_snow) {
                                    builder2.a(a.s0.h.moderate_snow);
                                } else if (e_day_type == EABleWeather.WeatherType.heavy_snow) {
                                    builder2.a(a.s0.h.heavy_snow);
                                } else if (e_day_type == EABleWeather.WeatherType.typhoon) {
                                    builder2.a(a.s0.h.typhoon);
                                } else if (e_day_type == EABleWeather.WeatherType.dust) {
                                    builder2.a(a.s0.h.dust);
                                } else if (e_day_type == EABleWeather.WeatherType.sandstorm) {
                                    builder2.a(a.s0.h.sandstorm);
                                } else if (e_day_type == EABleWeather.WeatherType.fog) {
                                    builder2.a(a.s0.h.fog);
                                } else if (e_day_type == EABleWeather.WeatherType.haze) {
                                    builder2.a(a.s0.h.haze);
                                }
                            }
                            EABleWeather.WeatherType e_night_type = s_day.get(i).getE_night_type();
                            if (e_night_type != null) {
                                if (e_night_type == EABleWeather.WeatherType.clear) {
                                    builder2.b(a.s0.h.clear);
                                } else if (e_night_type == EABleWeather.WeatherType.cloudy) {
                                    builder2.b(a.s0.h.cloudy);
                                } else if (e_night_type == EABleWeather.WeatherType.drizzle) {
                                    builder2.b(a.s0.h.drizzle);
                                } else if (e_night_type == EABleWeather.WeatherType.gloomy) {
                                    builder2.b(a.s0.h.gloomy);
                                } else if (e_night_type == EABleWeather.WeatherType.moderate_rain) {
                                    builder2.b(a.s0.h.moderate_rain);
                                } else if (e_night_type == EABleWeather.WeatherType.thunderstorm) {
                                    builder2.b(a.s0.h.thunderstorm);
                                } else if (e_night_type == EABleWeather.WeatherType.heavy_rain) {
                                    builder2.b(a.s0.h.heavy_rain);
                                } else if (e_night_type == EABleWeather.WeatherType.sleet) {
                                    builder2.b(a.s0.h.sleet);
                                } else if (e_night_type == EABleWeather.WeatherType.light_snow) {
                                    builder2.b(a.s0.h.light_snow);
                                } else if (e_night_type == EABleWeather.WeatherType.moderate_snow) {
                                    builder2.b(a.s0.h.moderate_snow);
                                } else if (e_day_type == EABleWeather.WeatherType.heavy_snow) {
                                    builder2.b(a.s0.h.heavy_snow);
                                } else if (e_day_type == EABleWeather.WeatherType.typhoon) {
                                    builder2.b(a.s0.h.typhoon);
                                } else if (e_day_type == EABleWeather.WeatherType.dust) {
                                    builder2.b(a.s0.h.dust);
                                } else {
                                    builder2.b(a.s0.h.sandstorm);
                                }
                            }
                            EABleWeather.RaysLevel e_rays = s_day.get(i).getE_rays();
                            if (e_rays != null) {
                                if (e_rays == EABleWeather.RaysLevel.weak) {
                                    builder2.a(a.s0.g.weak);
                                } else if (e_rays == EABleWeather.RaysLevel.medium) {
                                    builder2.a(a.s0.g.medium);
                                } else if (e_rays == EABleWeather.RaysLevel.strong) {
                                    builder2.a(a.s0.g.strong);
                                } else if (e_rays == EABleWeather.RaysLevel.super_strong) {
                                    builder2.a(a.s0.g.super_strong);
                                } else {
                                    builder2.a(a.s0.g.very_strong);
                                }
                            }
                            EABleWeather.Moon e_moon = s_day.get(i).getE_moon();
                            if (e_moon != null) {
                                if (e_moon == EABleWeather.Moon.full_moon) {
                                    builder2.a(a.s0.f.full_moon);
                                } else if (e_moon == EABleWeather.Moon.new_moon) {
                                    builder2.a(a.s0.f.new_moon);
                                } else if (e_moon == EABleWeather.Moon.waxing_crescent_moon) {
                                    builder2.a(a.s0.f.waxing_crescent_moon);
                                } else if (e_moon == EABleWeather.Moon.quarter_moon) {
                                    builder2.a(a.s0.f.quarter_moon);
                                } else if (e_moon == EABleWeather.Moon.last_quarter_moon) {
                                    builder2.a(a.s0.f.last_quarter_moon);
                                } else if (e_moon == EABleWeather.Moon.waning_crescent_moon) {
                                    builder2.a(a.s0.f.waning_crescent_moon);
                                } else if (e_moon == EABleWeather.Moon.waning_gibbous_moon) {
                                    builder2.a(a.s0.f.waning_gibbous_moon);
                                } else if (e_moon == EABleWeather.Moon.waxing_gibbous_moon) {
                                    builder2.a(a.s0.f.waxing_gibbous_moon);
                                } else if (e_moon == EABleWeather.Moon.half_moon_1) {
                                    builder2.a(a.s0.f.half_moon_1);
                                } else {
                                    builder2.a(a.s0.f.half_moon_2);
                                }
                            }
                            builder.a(builder2);
                        }
                    } else {
                        a2 = null;
                        a3 = cVar.a(a2);
                    }
                }
                a2 = aVar3.a(builder.build().toByteArray(), 20);
                a3 = cVar.a(a2);
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a3 != null) {
                int length2 = a3.length;
                int i2 = i.f2187a;
                if (length2 <= i2) {
                    length = 1;
                } else if (a3.length % i2 == 0) {
                    length = a3.length / i2;
                } else {
                    length = (a3.length / i2) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a3);
                } else {
                    for (int i3 = 0; i3 < length; i3++) {
                        int i4 = length - 1;
                        if (i3 < i4) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a3.length - (i4 * i.f2187a)];
                        }
                        System.arraycopy(a3, i.f2187a * i3, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void setWeightUnit(@NonNull EABleWeightFormat eABleWeightFormat, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleWeightFormat == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                EABleWeightFormat.WeightUnit e_format = eABleWeightFormat.getE_format();
                a2 = cVar.a(e_format == null ? null : aVar3.a(a.t0.c.toBuilder().a(e_format.getValue()).build().toByteArray(), 25));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void startAppScreenSport(@NonNull EABleAppScreenSport eABleAppScreenSport, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleAppScreenSport == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                d.c.b builder = d.c.e.toBuilder();
                builder.b(eABleAppScreenSport.getInterval() < 1 ? 1 : eABleAppScreenSport.getInterval());
                if (eABleAppScreenSport.getEaBleAppSportType() != null) {
                    builder.a(eABleAppScreenSport.getEaBleAppSportType().getValue());
                }
                if (eABleAppScreenSport.getEaBleSportStatus() != null) {
                    builder.c(eABleAppScreenSport.getEaBleSportStatus().getValue());
                }
                a2 = cVar.a(aVar3.a(builder.build().toByteArray(), 54));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void startAppSport(@NonNull EABleStartAppSports eABleStartAppSports, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleStartAppSports == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                d.a.b builder = d.a.f.toBuilder();
                if (eABleStartAppSports.getAppSportType() != null) {
                    builder.b(eABleStartAppSports.getAppSportType().getValue());
                }
                if (eABleStartAppSports.getSportStatus() != null) {
                    builder.d(eABleStartAppSports.getSportStatus().getValue());
                }
                builder.a(eABleStartAppSports.getCheckSport());
                builder.c(eABleStartAppSports.getReportInterval() < 1 ? 1 : eABleStartAppSports.getReportInterval());
                a2 = cVar.a(aVar3.a(builder.build().toByteArray(), 46));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void startOrEndCheck(@NonNull EABleCheckSwitch eABleCheckSwitch, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleCheckSwitch == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                d.C0097d.b builder = d.C0097d.d.toBuilder();
                if (eABleCheckSwitch.getCheckType() != null) {
                    builder.a(eABleCheckSwitch.getCheckType().getValue());
                }
                builder.b(eABleCheckSwitch.getSw());
                a2 = cVar.a(aVar3.a(builder.build().toByteArray(), 48));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void startRealTimeDataReport(int i, GeneralCallback generalCallback) {
        com.apex.bluetooth.data_package.a aVar;
        int length;
        byte[] bArr;
        a aVar2 = this.eaBleBluetoothConnect;
        if (aVar2 != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
            Objects.requireNonNull(aVar3);
            d.g.b builder = d.g.c.toBuilder();
            builder.a(i > 0 ? 1 : 0);
            byte[] a2 = cVar.a(aVar3.a(builder.build().toByteArray(), 52));
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i2 = i.f2187a;
                if (length2 <= i2) {
                    length = 1;
                } else if (a2.length % i2 == 0) {
                    length = a2.length / i2;
                } else {
                    length = (a2.length / i2) + 1;
                }
                aVar = new com.apex.bluetooth.data_package.a(length);
                aVar.b = aVar4;
                if (length == 1) {
                    aVar.a(a2);
                } else {
                    for (int i3 = 0; i3 < length; i3++) {
                        int i4 = length - 1;
                        if (i3 < i4) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i4 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i3, bArr, 0, bArr.length);
                        aVar.a(bArr);
                    }
                }
            } else {
                aVar = null;
            }
            aVar2.a(aVar, generalCallback);
        }
    }

    public void startSleepBloodMonitor(@NonNull EABleSleepBloodSwitch eABleSleepBloodSwitch, GeneralCallback generalCallback) {
        byte[] a2;
        int length;
        byte[] bArr;
        a aVar = this.eaBleBluetoothConnect;
        if (aVar != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.a aVar2 = null;
            if (eABleSleepBloodSwitch == null) {
                a2 = null;
            } else {
                com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
                Objects.requireNonNull(aVar3);
                d.h.b builder = d.h.d.toBuilder();
                builder.a(eABleSleepBloodSwitch.getInterval() >= 30 ? eABleSleepBloodSwitch.getInterval() : 30);
                builder.b(eABleSleepBloodSwitch.getSw() > 0 ? 1 : 0);
                a2 = cVar.a(aVar3.a(builder.build().toByteArray(), 50));
            }
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i = i.f2187a;
                if (length2 <= i) {
                    length = 1;
                } else if (a2.length % i == 0) {
                    length = a2.length / i;
                } else {
                    length = (a2.length / i) + 1;
                }
                com.apex.bluetooth.data_package.a aVar5 = new com.apex.bluetooth.data_package.a(length);
                aVar5.b = aVar4;
                if (length == 1) {
                    aVar5.a(a2);
                } else {
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = length - 1;
                        if (i2 < i3) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i3 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i2, bArr, 0, bArr.length);
                        aVar5.a(bArr);
                    }
                }
                aVar2 = aVar5;
            }
            aVar.a(aVar2, generalCallback);
        }
    }

    public void startStressMonitor(int i, GeneralCallback generalCallback) {
        com.apex.bluetooth.data_package.a aVar;
        int length;
        byte[] bArr;
        a aVar2 = this.eaBleBluetoothConnect;
        if (aVar2 != null) {
            com.apex.bluetooth.data_package.c.c cVar = new com.apex.bluetooth.data_package.c.c();
            com.apex.bluetooth.data_package.c.a aVar3 = cVar.f2211a;
            Objects.requireNonNull(aVar3);
            d.i.b builder = d.i.c.toBuilder();
            builder.a(i > 0 ? 1 : 0);
            byte[] a2 = cVar.a(aVar3.a(builder.build().toByteArray(), 51));
            b.a aVar4 = b.a.east_apex_01;
            if (a2 != null) {
                int length2 = a2.length;
                int i2 = i.f2187a;
                if (length2 <= i2) {
                    length = 1;
                } else if (a2.length % i2 == 0) {
                    length = a2.length / i2;
                } else {
                    length = (a2.length / i2) + 1;
                }
                aVar = new com.apex.bluetooth.data_package.a(length);
                aVar.b = aVar4;
                if (length == 1) {
                    aVar.a(a2);
                } else {
                    for (int i3 = 0; i3 < length; i3++) {
                        int i4 = length - 1;
                        if (i3 < i4) {
                            bArr = new byte[i.f2187a];
                        } else {
                            bArr = new byte[a2.length - (i4 * i.f2187a)];
                        }
                        System.arraycopy(a2, i.f2187a * i3, bArr, 0, bArr.length);
                        aVar.a(bArr);
                    }
                }
            } else {
                aVar = null;
            }
            aVar2.a(aVar, generalCallback);
        }
    }

    public void stopScanPeripherals(@NonNull Context context) {
        j.a aVar;
        j a2 = j.a(context);
        BluetoothAdapter bluetoothAdapter = a2.c;
        if (bluetoothAdapter != null) {
            if (!bluetoothAdapter.isEnabled()) {
                return;
            }
            BluetoothLeScanner bluetoothLeScanner = a2.c.getBluetoothLeScanner();
            if (bluetoothLeScanner != null && (aVar = a2.b) != null) {
                bluetoothLeScanner.stopScan(aVar);
                a2.b = null;
            }
        }
        a2.f = false;
        List<EABleScanListener> list = a2.e;
        if (list != null) {
            list.clear();
        }
    }
}
