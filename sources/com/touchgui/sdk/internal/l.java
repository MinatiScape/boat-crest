package com.touchgui.sdk.internal;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import com.clevertap.android.sdk.Constants;
import com.touchgui.sdk.TGErrorCode;
import com.touchgui.sdk.TGLogger;
import com.touchgui.sdk.TGResponseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
/* loaded from: classes12.dex */
public final class l extends BluetoothGattCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ q f13788a;

    public l(q qVar) {
        this.f13788a = qVar;
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public final void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        int i;
        ArrayList arrayList;
        if (TGLogger.isDebug() && "universalTouch".toLowerCase().contains("touch")) {
            TGLogger.d(bluetoothGatt.getDevice().getAddress(), String.format(Locale.getDefault(), "%s <= %s", bluetoothGattCharacteristic.getUuid().toString(), s.a(bluetoothGattCharacteristic.getValue())));
        }
        m mVar = this.f13788a.j;
        if (mVar != null) {
            g gVar = (g) mVar;
            byte[] value = bluetoothGattCharacteristic.getValue();
            if (bluetoothGattCharacteristic.getUuid().toString().equals("00002762-0000-1000-8000-00805f9b34fb")) {
                e2 e2Var = gVar.h;
                if (e2Var == null || (arrayList = ((a0) e2Var).h) == null) {
                    return;
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((TGResponseListener) it.next()).onResponse(value, 1);
                }
                return;
            }
            e2 e2Var2 = gVar.h;
            if (e2Var2 != null) {
                a0 a0Var = (a0) e2Var2;
                a0Var.z = 0;
                ArrayList arrayList2 = a0Var.h;
                if (arrayList2 != null) {
                    Iterator it2 = arrayList2.iterator();
                    while (it2.hasNext()) {
                        ((TGResponseListener) it2.next()).onResponse(value, 0);
                    }
                }
                i3 i3Var = a0Var.f;
                synchronized (i3Var) {
                    Iterator it3 = i3Var.b.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            d dVar = (d) it3.next();
                            if (dVar.d.a(value)) {
                                try {
                                    i = dVar.d.onResponse(value);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    i = 256;
                                }
                            } else {
                                i = 0;
                            }
                            if ((i & 2048) == 2048) {
                                dVar.d();
                                break;
                            }
                        } else {
                            Iterator it4 = i3Var.c.iterator();
                            while (it4.hasNext() && !((h3) it4.next()).a(value)) {
                            }
                        }
                    }
                }
            }
        }
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public final void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        m mVar = this.f13788a.j;
        if (mVar != null) {
            String str = ((g) mVar).b.n;
            TGLogger.e(str, "read data callback, status=" + i);
        }
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public final void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        if (TGLogger.isDebug() && "universalTouch".toLowerCase().contains("touch")) {
            byte[] value = bluetoothGattCharacteristic.getValue();
            if (value.length < 2 || (value[0] & 255) != 209 || value[1] != 2) {
                TGLogger.d(bluetoothGatt.getDevice().getAddress(), String.format(Locale.getDefault(), "%s => %s(%d)", bluetoothGattCharacteristic.getUuid().toString(), s.a(value), Integer.valueOf(i)));
            }
        }
        m mVar = this.f13788a.j;
        if (mVar != null) {
            byte[] value2 = bluetoothGattCharacteristic.getValue();
            e2 e2Var = ((g) mVar).h;
            if (e2Var != null) {
                boolean z = i == 0;
                i3 i3Var = ((a0) e2Var).f;
                synchronized (i3Var) {
                    Iterator it = i3Var.c.iterator();
                    while (it.hasNext() && !((h3) it.next()).a(value2, z)) {
                    }
                }
            }
        }
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public final void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
        synchronized (this.f13788a.l) {
            BluetoothGatt bluetoothGatt2 = this.f13788a.g;
            if (bluetoothGatt2 != null && bluetoothGatt != bluetoothGatt2) {
                TGLogger.w(bluetoothGatt.getDevice().getAddress(), "mBluetoothGatt is changed.");
                String address = this.f13788a.g.getDevice().getAddress();
                String address2 = bluetoothGatt.getDevice().getAddress();
                if (!address.equalsIgnoreCase(address2)) {
                    String address3 = bluetoothGatt.getDevice().getAddress();
                    TGLogger.w(address3, address + " != " + address2);
                    return;
                }
            }
            String address4 = bluetoothGatt.getDevice().getAddress();
            TGLogger.d(address4, "onConnectionStateChange: status=" + i + ", newState=" + i2 + ", bluetoothState=" + this.f13788a.m);
            if (i == 0 && i2 == 2) {
                this.f13788a.a(i2);
                this.f13788a.d();
                q qVar = this.f13788a;
                qVar.u.postDelayed(qVar.s, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
            } else if (i2 == 0) {
                this.f13788a.b(false);
            }
        }
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public final void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        String address = bluetoothGatt.getDevice().getAddress();
        TGLogger.d(address, "onDescriptorWrite：" + bluetoothGattDescriptor.getUuid().toString() + ", status=" + i);
        m mVar = this.f13788a.j;
        if (mVar != null) {
            g gVar = (g) mVar;
            if (i != 0) {
                String str = gVar.b.n;
                TGLogger.e(str, "onDescriptorWrite status=" + i);
                gVar.b(TGErrorCode.ERROR_WRITE_DESCRIPTOR_FAILURE);
                return;
            }
            gVar.a();
        }
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public final void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
        String address = bluetoothGatt.getDevice().getAddress();
        TGLogger.d(address, "onMtuChanged: mtu=" + i + ", status=" + i2);
        if (i2 == 0) {
            m mVar = this.f13788a.j;
            if (mVar != null) {
                g gVar = (g) mVar;
                gVar.d = i;
                if (gVar.e) {
                    gVar.b(0);
                    return;
                }
                return;
            }
            return;
        }
        q qVar = this.f13788a;
        synchronized (qVar.l) {
            qVar.h();
        }
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public final void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
        m mVar = this.f13788a.j;
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public final void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
        BluetoothGatt bluetoothGatt2;
        TGLogger.d(bluetoothGatt.getDevice().getAddress(), "onServicesDiscovered: status=" + i);
        if (i == 0) {
            m mVar = this.f13788a.j;
            if (mVar != null) {
                g gVar = (g) mVar;
                gVar.j.clear();
                BluetoothGatt bluetoothGatt3 = gVar.b.g;
                List<BluetoothGattService> services = bluetoothGatt3 == null ? null : bluetoothGatt3.getServices();
                if (services.isEmpty()) {
                    TGLogger.w(gVar.b.n, "service list is empty！！！");
                    gVar.b(10002);
                    return;
                }
                for (BluetoothGattService bluetoothGattService : services) {
                    UUID uuid = bluetoothGattService.getUuid();
                    TGLogger.d(gVar.b.n, "service uuid=" + uuid.toString());
                    Iterator it = gVar.c.iterator();
                    while (it.hasNext()) {
                        if (((String) it.next()).equalsIgnoreCase(uuid.toString())) {
                            for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
                                if ((bluetoothGattCharacteristic.getProperties() & 16) != 0) {
                                    q qVar = gVar.b;
                                    boolean z = true;
                                    if ((!c8.a(qVar.f13815a) || qVar.f == null || (bluetoothGatt2 = qVar.g) == null || !bluetoothGatt2.setCharacteristicNotification(bluetoothGattCharacteristic, true)) ? false : false) {
                                        gVar.j.addAll(bluetoothGattCharacteristic.getDescriptors());
                                    }
                                }
                            }
                        }
                    }
                }
                gVar.i = 0;
                gVar.a();
                return;
            }
            return;
        }
        q qVar2 = this.f13788a;
        synchronized (qVar2.l) {
            qVar2.h();
        }
    }
}
