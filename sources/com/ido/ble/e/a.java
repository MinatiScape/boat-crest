package com.ido.ble.e;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import com.ido.ble.bluetooth.f.e;
import com.ido.ble.logs.LogTool;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.UUID;
import org.eclipse.paho.android.service.MqttServiceConstants;
/* loaded from: classes11.dex */
public class a {
    private static final String g = "HIDConnectManager";
    private static a i;

    /* renamed from: a  reason: collision with root package name */
    private String f12208a;
    private BluetoothDevice b;
    private d c;
    private final BroadcastReceiver d = new C0594a();
    private BluetoothProfile.ServiceListener e = new b();
    public static final UUID f = UUID.fromString("00001812-0000-1000-8000-00805f9b34fb");
    private static Handler h = new Handler(Looper.getMainLooper());

    /* renamed from: com.ido.ble.e.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0594a extends BroadcastReceiver {
        public C0594a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String str;
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            String action = intent.getAction();
            LogTool.d(a.g, "onReceive mac is " + bluetoothDevice.getAddress() + ";action=" + action);
            if (action.equals("android.bluetooth.device.action.ACL_CONNECTED")) {
                str = "onReceive ACTION_ACL_CONNECTED ";
            } else if (!action.equals("android.bluetooth.device.action.ACL_DISCONNECTED")) {
                if (action.equals("android.bluetooth.device.action.BOND_STATE_CHANGED")) {
                    int intExtra = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", -1);
                    LogTool.d(a.g, "onReceive bondState is " + intExtra);
                    if (intExtra == 12) {
                        a.this.e();
                        LogTool.d(a.g, "createBond success, mac is" + bluetoothDevice.getAddress());
                        if (bluetoothDevice.getAddress().equals(a.this.f12208a)) {
                            if (a.this.c != null) {
                                a.this.c.a(bluetoothDevice.getAddress());
                                a.this.c = null;
                            }
                            a.this.a(bluetoothDevice);
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            } else {
                str = "onReceive ACTION_ACL_DISCONNECTED ";
            }
            LogTool.d(a.g, str);
        }
    }

    /* loaded from: classes11.dex */
    public class b implements BluetoothProfile.ServiceListener {
        public b() {
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            LogTool.d(a.g, "[onServiceConnected] profile=" + i);
            int a2 = a.a();
            LogTool.d(a.g, "[onServiceConnected] getInputDeviceHiddenConstant=" + a2);
            if (i == a2) {
                try {
                    if (a.this.b != null) {
                        LogTool.d(a.g, "[onServiceConnected] getMethod(\"connect\")=" + a2);
                        bluetoothProfile.getClass().getMethod(MqttServiceConstants.CONNECT_ACTION, BluetoothDevice.class).invoke(bluetoothProfile, a.this.b);
                    }
                } catch (Exception e) {
                    LogTool.b(a.g, "[onServiceConnected] " + e.getMessage());
                }
            }
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            LogTool.d(a.g, "[onServiceDisconnected] profile=" + i);
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ BluetoothDevice f12211a;

        public c(BluetoothDevice bluetoothDevice) {
            this.f12211a = bluetoothDevice;
        }

        @Override // java.lang.Runnable
        public void run() {
            LogTool.d(a.g, "[startBondTimeOut] after create bond.not receive broadcast");
            a aVar = a.this;
            BluetoothDevice c = aVar.c(aVar.f12208a);
            if (c == null) {
                LogTool.d(a.g, "[startBondTimeOut] retry createBond.");
                this.f12211a.createBond();
                return;
            }
            LogTool.d(a.g, "[startBondTimeOut] check bonded status is true");
            a.this.a(c);
        }
    }

    /* loaded from: classes11.dex */
    public interface d {
        void a(String str);
    }

    private a() {
    }

    public static /* synthetic */ int a() {
        return b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BluetoothDevice bluetoothDevice) {
        LogTool.d(g, "[connectIfBonded] mac is " + bluetoothDevice.getAddress());
        this.b = bluetoothDevice;
        try {
            BluetoothAdapter.getDefaultAdapter().getProfileProxy(com.ido.ble.b.b(), this.e, b());
        } catch (Exception e) {
            LogTool.b(g, "[connectIfBonded] " + e.getMessage());
        }
    }

    public static boolean a(BluetoothGatt bluetoothGatt) {
        if (bluetoothGatt.getService(f) == null) {
            return false;
        }
        if (e.c(bluetoothGatt.getDevice().getAddress())) {
            LogTool.d(g, "[isNeedCreateBond] has paired. mac is " + bluetoothGatt.getDevice().getAddress());
            return false;
        }
        return true;
    }

    @SuppressLint({"NewApi"})
    private static int b() {
        Field[] fields = BluetoothProfile.class.getFields();
        int length = fields.length;
        for (int i2 = 0; i2 < length; i2++) {
            Field field = fields[i2];
            int modifiers = field.getModifiers();
            if (Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers) && Modifier.isFinal(modifiers)) {
                try {
                    if (field.getName().equals("INPUT_DEVICE")) {
                        return field.getInt(null);
                    }
                    continue;
                } catch (Exception e) {
                    LogTool.b(g, "[getInputDeviceHiddenConstant] " + e.getMessage());
                }
            }
        }
        return -1;
    }

    private void b(BluetoothDevice bluetoothDevice) {
        h.postDelayed(new c(bluetoothDevice), 10000L);
    }

    private void b(String str) {
        List<BluetoothDevice> connectedDevices = ((BluetoothManager) com.ido.ble.common.e.a().getSystemService("bluetooth")).getConnectedDevices(7);
        if (connectedDevices == null || connectedDevices.size() == 0) {
            LogTool.d(g, "[createBond] deviceList is null");
            return;
        }
        for (BluetoothDevice bluetoothDevice : connectedDevices) {
            if (str.equals(bluetoothDevice.getAddress())) {
                boolean createBond = bluetoothDevice.createBond();
                LogTool.d(g, "[createBond] to create bond. result = " + createBond);
                b(bluetoothDevice);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BluetoothDevice c(String str) {
        for (BluetoothDevice bluetoothDevice : BluetoothAdapter.getDefaultAdapter().getBondedDevices()) {
            if (bluetoothDevice.getAddress().equals(str)) {
                return bluetoothDevice;
            }
        }
        return null;
    }

    public static a c() {
        if (i == null) {
            a aVar = new a();
            i = aVar;
            aVar.d();
        }
        return i;
    }

    private void d() {
        LogTool.d(g, "init ");
        IntentFilter intentFilter = new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        com.ido.ble.b.b().registerReceiver(this.d, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        h.removeCallbacksAndMessages(null);
    }

    public void a(BluetoothDevice bluetoothDevice, d dVar) {
        LogTool.d(g, "[connect1] start, address=" + bluetoothDevice.getAddress());
        this.f12208a = bluetoothDevice.getAddress();
        this.c = dVar;
        LogTool.d(g, "[connect1] create bond.");
        bluetoothDevice.createBond();
    }

    public void a(String str) {
        LogTool.d(g, "[connect] start, address=" + str);
        this.f12208a = str;
        BluetoothDevice c2 = c(str);
        if (c2 != null) {
            LogTool.d(g, "[connect] has bonded.");
            a(c2);
            return;
        }
        LogTool.d(g, "[connect] not bonded.");
        b(str);
    }
}
