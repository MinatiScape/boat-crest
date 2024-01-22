package com.realsil.sdk.core.bluetooth;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothHealth;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import com.realsil.sdk.core.RtkCore;
import com.realsil.sdk.core.bluetooth.impl.BluetoothA2dpImpl;
import com.realsil.sdk.core.bluetooth.impl.BluetoothA2dpSinkImpl;
import com.realsil.sdk.core.bluetooth.impl.BluetoothAvrcpControllerImpl;
import com.realsil.sdk.core.bluetooth.impl.BluetoothHeadsetImpl;
import com.realsil.sdk.core.bluetooth.impl.BluetoothHidHostImpl;
import com.realsil.sdk.core.bluetooth.impl.BluetoothInputDeviceImpl;
import com.realsil.sdk.core.bluetooth.impl.BluetoothProfileImpl;
import com.realsil.sdk.core.bluetooth.utils.BluetoothHelper;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes12.dex */
public final class BluetoothProfileManager {
    public static final int INDICATOR_A2DP = 1;
    public static final int INDICATOR_A2DP_SINK = 2;
    public static final int INDICATOR_AVRCP = 16;
    public static final int INDICATOR_FULL = 255;
    public static final int INDICATOR_HEADSET = 4;
    public static final int INDICATOR_HID = 8;

    /* renamed from: a  reason: collision with root package name */
    public static BluetoothProfileManager f13554a;
    public boolean b;
    public boolean c;
    public Context d;
    public List<BluetoothProfileCallback> e;
    public BluetoothManager f;
    public BluetoothAdapter g;
    public BluetoothHealth j;
    public BluetoothHeadset h = null;
    public BluetoothA2dp i = null;
    public BluetoothProfile k = null;
    public BluetoothProfile l = null;
    public BluetoothProfile m = null;
    public ProfileBroadcastReceiver n = null;
    public BluetoothProfile.ServiceListener o = new a();

    /* loaded from: classes12.dex */
    public class ProfileBroadcastReceiver extends BroadcastReceiver {
        public ProfileBroadcastReceiver() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.content.BroadcastReceiver
        @TargetApi(19)
        public void onReceive(Context context, Intent intent) {
            char c;
            String action = intent.getAction();
            action.hashCode();
            switch (action.hashCode()) {
                case -1435586571:
                    if (action.equals("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -1021360715:
                    if (action.equals("android.bluetooth.input.profile.action.CONNECTION_STATE_CHANGED")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -855499628:
                    if (action.equals("android.bluetooth.a2dp.profile.action.PLAYING_STATE_CHANGED")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 545516589:
                    if (action.equals("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 1244161670:
                    if (action.equals("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 1347806984:
                    if (action.equals(BluetoothAvrcpControllerImpl.ACTION_CONNECTION_STATE_CHANGED)) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 1772843706:
                    if (action.equals("android.bluetooth.headset.action.VENDOR_SPECIFIC_HEADSET_EVENT")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    if (bluetoothDevice == null) {
                        return;
                    }
                    int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                    ZLogger.d(String.format(Locale.US, "%s: action=%s, state: %d->%d", BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true), action, Integer.valueOf(intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1)), Integer.valueOf(intExtra)));
                    List<BluetoothProfileCallback> list = BluetoothProfileManager.this.e;
                    if (list != null && list.size() > 0) {
                        for (BluetoothProfileCallback bluetoothProfileCallback : BluetoothProfileManager.this.e) {
                            bluetoothProfileCallback.onHfpAudioStateChanged(bluetoothDevice, intExtra);
                        }
                        return;
                    }
                    ZLogger.v(BluetoothProfileManager.this.c, "no callback registed");
                    return;
                case 1:
                    BluetoothDevice bluetoothDevice2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    int intExtra2 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                    int intExtra3 = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
                    if (bluetoothDevice2 == null) {
                        return;
                    }
                    ZLogger.d(String.format(Locale.US, "%s: action=%s, state: %d->%d", BluetoothHelper.formatAddress(bluetoothDevice2.getAddress(), true), action, Integer.valueOf(intExtra3), Integer.valueOf(intExtra2)));
                    List<BluetoothProfileCallback> list2 = BluetoothProfileManager.this.e;
                    if (list2 != null && list2.size() > 0) {
                        for (BluetoothProfileCallback bluetoothProfileCallback2 : BluetoothProfileManager.this.e) {
                            bluetoothProfileCallback2.onHidStateChanged(bluetoothDevice2, intExtra2);
                        }
                        return;
                    }
                    ZLogger.v(BluetoothProfileManager.this.c, "no callback registed");
                    return;
                case 2:
                    BluetoothDevice bluetoothDevice3 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    int intExtra4 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                    int intExtra5 = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
                    if (bluetoothDevice3 == null) {
                        return;
                    }
                    ZLogger.d(String.format(Locale.US, "%s: action=%s, state: %d->%d", BluetoothHelper.formatAddress(bluetoothDevice3.getAddress(), true), action, Integer.valueOf(intExtra5), Integer.valueOf(intExtra4)));
                    List<BluetoothProfileCallback> list3 = BluetoothProfileManager.this.e;
                    if (list3 != null && list3.size() > 0) {
                        for (BluetoothProfileCallback bluetoothProfileCallback3 : BluetoothProfileManager.this.e) {
                            bluetoothProfileCallback3.onA2dpPlayingStateChanged(bluetoothDevice3, intExtra4);
                        }
                        return;
                    }
                    ZLogger.v(BluetoothProfileManager.this.c, "no callback registed");
                    return;
                case 3:
                    BluetoothDevice bluetoothDevice4 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    int intExtra6 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                    int intExtra7 = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
                    if (bluetoothDevice4 == null) {
                        return;
                    }
                    ZLogger.d(String.format(Locale.US, "action=%s, device:%s, state: %d->%d", action, BluetoothHelper.formatAddress(bluetoothDevice4.getAddress(), true), Integer.valueOf(intExtra7), Integer.valueOf(intExtra6)));
                    List<BluetoothProfileCallback> list4 = BluetoothProfileManager.this.e;
                    if (list4 != null && list4.size() > 0) {
                        for (BluetoothProfileCallback bluetoothProfileCallback4 : BluetoothProfileManager.this.e) {
                            bluetoothProfileCallback4.onHfpConnectionStateChanged(bluetoothDevice4, intExtra6);
                        }
                        return;
                    }
                    ZLogger.v(BluetoothProfileManager.this.c, "no callback registed");
                    return;
                case 4:
                    BluetoothDevice bluetoothDevice5 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    int intExtra8 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                    int intExtra9 = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
                    if (bluetoothDevice5 == null) {
                        return;
                    }
                    ZLogger.d(String.format(Locale.US, "%s: action=%s, state: %d->%d", BluetoothHelper.formatAddress(bluetoothDevice5.getAddress(), true), action, Integer.valueOf(intExtra9), Integer.valueOf(intExtra8)));
                    List<BluetoothProfileCallback> list5 = BluetoothProfileManager.this.e;
                    if (list5 != null && list5.size() > 0) {
                        for (BluetoothProfileCallback bluetoothProfileCallback5 : BluetoothProfileManager.this.e) {
                            bluetoothProfileCallback5.onA2dpStateChanged(bluetoothDevice5, intExtra8);
                        }
                        return;
                    }
                    ZLogger.v(BluetoothProfileManager.this.c, "no callback registed");
                    return;
                case 5:
                    BluetoothDevice bluetoothDevice6 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    int intExtra10 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                    int intExtra11 = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
                    if (bluetoothDevice6 == null) {
                        return;
                    }
                    ZLogger.d(String.format(Locale.US, "%s: action=%s, state: %d->%d", BluetoothHelper.formatAddress(bluetoothDevice6.getAddress(), true), action, Integer.valueOf(intExtra11), Integer.valueOf(intExtra10)));
                    return;
                case 6:
                    BluetoothProfileManager bluetoothProfileManager = BluetoothProfileManager.this;
                    bluetoothProfileManager.getClass();
                    BluetoothDevice bluetoothDevice7 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    if (bluetoothDevice7 == null) {
                        ZLogger.v("onVendorSpecificHeadsetEvent() remote device is null");
                        return;
                    }
                    String stringExtra = intent.getStringExtra("android.bluetooth.headset.extra.VENDOR_SPECIFIC_HEADSET_EVENT_CMD");
                    if (stringExtra == null) {
                        ZLogger.v("onVendorSpecificHeadsetEvent() command is null");
                        return;
                    }
                    int intExtra12 = intent.getIntExtra("android.bluetooth.headset.extra.VENDOR_SPECIFIC_HEADSET_EVENT_CMD_TYPE", -1);
                    if (intExtra12 == 0 && intExtra12 == 1 && intExtra12 == 2 && intExtra12 == 3 && intExtra12 == 4) {
                        Object[] objArr = (Object[]) intent.getExtras().get("android.bluetooth.headset.extra.VENDOR_SPECIFIC_HEADSET_EVENT_ARGS");
                        List<BluetoothProfileCallback> list6 = bluetoothProfileManager.e;
                        if (list6 != null && list6.size() > 0) {
                            for (BluetoothProfileCallback bluetoothProfileCallback6 : bluetoothProfileManager.e) {
                                bluetoothProfileCallback6.onVendorSpecificHeadsetEvent(bluetoothDevice7, stringExtra, intExtra12, objArr);
                            }
                            return;
                        }
                        ZLogger.v(bluetoothProfileManager.c, "no callback registed");
                        return;
                    }
                    ZLogger.v("onVendorSpecificHeadsetEvent() unknown command");
                    return;
                default:
                    return;
            }
        }
    }

    /* loaded from: classes12.dex */
    public class a implements BluetoothProfile.ServiceListener {
        public a() {
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            String parseProfile = BluetoothHelper.parseProfile(i);
            ZLogger.d(parseProfile + " profile connected");
            if (i == 11) {
                BluetoothProfileManager.this.l = bluetoothProfile;
            } else if (i == 12) {
                BluetoothProfileManager.this.m = bluetoothProfile;
            } else if (i == 1) {
                BluetoothProfileManager.this.h = (BluetoothHeadset) bluetoothProfile;
            } else if (i == 2) {
                BluetoothProfileManager.this.i = (BluetoothA2dp) bluetoothProfile;
            } else if (i == 3) {
                BluetoothProfileManager.this.j = (BluetoothHealth) bluetoothProfile;
            } else if (i != 4) {
            } else {
                BluetoothProfileManager.this.k = bluetoothProfile;
            }
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            String parseProfile = BluetoothHelper.parseProfile(i);
            ZLogger.d(parseProfile + " profile disconnected");
            if (i == 1) {
                BluetoothProfileManager.this.h = null;
            } else if (i == 2) {
                BluetoothProfileManager.this.i = null;
            } else if (i == 3) {
                BluetoothProfileManager.this.j = null;
            } else if (i == 4) {
                BluetoothProfileManager.this.k = null;
            } else if (i == 11) {
                BluetoothProfileManager.this.l = null;
            } else if (i == 12) {
                BluetoothProfileManager.this.m = null;
            }
        }
    }

    public BluetoothProfileManager(Context context) {
        this.b = false;
        this.c = false;
        this.d = context.getApplicationContext();
        this.b = RtkCore.DEBUG;
        this.c = RtkCore.VDBG;
        a();
    }

    public static BluetoothProfileManager getInstance() {
        return f13554a;
    }

    public static void initial(Context context) {
        if (f13554a == null) {
            synchronized (BluetoothProfileManager.class) {
                if (f13554a == null) {
                    f13554a = new BluetoothProfileManager(context);
                }
            }
        }
    }

    public final boolean a() {
        Context context = this.d;
        if (context == null) {
            ZLogger.w("not intialized");
            return false;
        }
        if (this.g == null) {
            if (Build.VERSION.SDK_INT >= 18) {
                BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService("bluetooth");
                this.f = bluetoothManager;
                if (bluetoothManager == null) {
                    ZLogger.w("Unable to initialize BluetoothManager.");
                    return false;
                }
                this.g = bluetoothManager.getAdapter();
            } else {
                this.g = BluetoothAdapter.getDefaultAdapter();
            }
            if (this.g == null) {
                ZLogger.w("Unable to obtain a BluetoothAdapter.");
                return false;
            }
        }
        registerProfiles();
        return true;
    }

    public void addManagerCallback(BluetoothProfileCallback bluetoothProfileCallback) {
        if (bluetoothProfileCallback == null) {
            return;
        }
        if (this.e == null) {
            this.e = new CopyOnWriteArrayList();
        }
        if (!this.e.contains(bluetoothProfileCallback)) {
            this.e.add(bluetoothProfileCallback);
        }
        boolean z = this.c;
        ZLogger.d(z, "mManagerCallbacks.size=" + this.e.size());
    }

    public void close() {
        if (this.b) {
            ZLogger.v("close()");
        }
        Context context = this.d;
        if (context != null) {
            try {
                context.unregisterReceiver(this.n);
            } catch (Exception e) {
                ZLogger.e(e.toString());
            }
        }
    }

    public void closeProfileProxy(int i) {
        BluetoothAdapter bluetoothAdapter = this.g;
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            try {
                ZLogger.v(String.format(Locale.US, "profile=%d", Integer.valueOf(i)));
                if (i == 11) {
                    this.g.closeProfileProxy(i, this.l);
                } else if (i == 12) {
                    this.g.closeProfileProxy(i, this.m);
                } else if (i == 1) {
                    this.g.closeProfileProxy(i, this.h);
                } else if (i == 2) {
                    this.g.closeProfileProxy(i, this.i);
                } else if (i == 3) {
                    this.g.closeProfileProxy(i, this.j);
                } else if (i == 4) {
                    this.g.closeProfileProxy(i, this.k);
                }
                return;
            } catch (Exception e) {
                ZLogger.e(e.toString());
                return;
            }
        }
        ZLogger.w("BT not enabled");
    }

    public boolean connectA2dpSink(byte[] bArr) {
        return connectA2dpSource(BluetoothHelper.convertMac(bArr));
    }

    public boolean connectA2dpSource(byte[] bArr) {
        return connectA2dpSource(BluetoothHelper.convertMac(bArr));
    }

    public boolean connectHfpAg(byte[] bArr) {
        return connectHfpAg(BluetoothHelper.convertMac(bArr));
    }

    public boolean connectHid(BluetoothDevice bluetoothDevice) {
        if (Build.VERSION.SDK_INT >= 28) {
            return BluetoothProfileImpl.connectProfile(this.k, BluetoothHidHostImpl.CLASS_NAME, bluetoothDevice);
        }
        return BluetoothProfileImpl.connectProfile(this.k, BluetoothInputDeviceImpl.CLASS_NAME, bluetoothDevice);
    }

    public boolean connectProfile(int i, BluetoothDevice bluetoothDevice) {
        if (i != 4) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 28) {
            return BluetoothProfileImpl.connectProfile(this.k, BluetoothHidHostImpl.CLASS_NAME, bluetoothDevice);
        }
        return BluetoothProfileImpl.connectProfile(this.k, BluetoothInputDeviceImpl.CLASS_NAME, bluetoothDevice);
    }

    public boolean disConnectHid(BluetoothDevice bluetoothDevice) {
        if (Build.VERSION.SDK_INT >= 28) {
            return BluetoothProfileImpl.disconnect(this.k, BluetoothHidHostImpl.CLASS_NAME, bluetoothDevice);
        }
        return BluetoothProfileImpl.disconnect(this.k, BluetoothInputDeviceImpl.CLASS_NAME, bluetoothDevice);
    }

    public boolean disconnectA2dpSink(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            ZLogger.w("device is null");
            return false;
        }
        BluetoothProfile bluetoothProfile = this.l;
        if (bluetoothProfile == null) {
            ZLogger.w("A2DP Sink not initialized");
            return false;
        } else if (bluetoothProfile.getConnectionState(bluetoothDevice) != 2) {
            ZLogger.w("A2DP Sink already disconnected");
            return false;
        } else {
            return BluetoothProfileImpl.disconnect(this.l, BluetoothA2dpSinkImpl.CLASS_NAME, bluetoothDevice);
        }
    }

    public boolean disconnectA2dpSource(byte[] bArr) {
        return disconnectA2dpSource(BluetoothHelper.convertMac(bArr));
    }

    public boolean disconnectHfp(byte[] bArr) {
        return disconnectHfp(BluetoothHelper.convertMac(bArr));
    }

    public boolean disconnectProfile(int i, BluetoothDevice bluetoothDevice) {
        if (i != 4) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 28) {
            return BluetoothProfileImpl.disconnect(this.k, BluetoothHidHostImpl.CLASS_NAME, bluetoothDevice);
        }
        return BluetoothProfileImpl.disconnect(this.k, BluetoothInputDeviceImpl.CLASS_NAME, bluetoothDevice);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x001b, code lost:
        if (r3 != 12) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:30:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<android.bluetooth.BluetoothDevice> getConnectedDevices(int r3) {
        /*
            r2 = this;
            android.bluetooth.BluetoothAdapter r0 = r2.g
            r1 = 0
            if (r0 == 0) goto L76
            boolean r0 = r0.isEnabled()
            if (r0 != 0) goto Lc
            goto L76
        Lc:
            r0 = 1
            if (r3 == r0) goto L1e
            r0 = 2
            if (r3 == r0) goto L2c
            r0 = 4
            if (r3 == r0) goto L3a
            r0 = 11
            if (r3 == r0) goto L57
            r0 = 12
            if (r3 == r0) goto L67
            goto L75
        L1e:
            android.bluetooth.BluetoothHeadset r3 = r2.h
            if (r3 == 0) goto L27
            java.util.List r3 = r3.getConnectedDevices()
            return r3
        L27:
            java.lang.String r3 = "HEADSET profile not connected"
            com.realsil.sdk.core.logger.ZLogger.d(r3)
        L2c:
            android.bluetooth.BluetoothA2dp r3 = r2.i
            if (r3 == 0) goto L35
            java.util.List r3 = r3.getConnectedDevices()
            return r3
        L35:
            java.lang.String r3 = "A2DP profile not connected"
            com.realsil.sdk.core.logger.ZLogger.d(r3)
        L3a:
            android.bluetooth.BluetoothProfile r3 = r2.k
            if (r3 == 0) goto L52
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 28
            if (r0 < r1) goto L4b
            java.lang.String r0 = "android.bluetooth.BluetoothHidHost"
            java.util.List r3 = com.realsil.sdk.core.bluetooth.impl.BluetoothProfileImpl.getConnectedDevices(r3, r0)
            return r3
        L4b:
            java.lang.String r0 = "android.bluetooth.BluetoothInputDevice"
            java.util.List r3 = com.realsil.sdk.core.bluetooth.impl.BluetoothProfileImpl.getConnectedDevices(r3, r0)
            return r3
        L52:
            java.lang.String r3 = "HID_HOST profile not connected"
            com.realsil.sdk.core.logger.ZLogger.d(r3)
        L57:
            android.bluetooth.BluetoothProfile r3 = r2.l
            if (r3 == 0) goto L62
            java.lang.String r0 = "android.bluetooth.BluetoothA2dpSink"
            java.util.List r3 = com.realsil.sdk.core.bluetooth.impl.BluetoothProfileImpl.getConnectedDevices(r3, r0)
            return r3
        L62:
            java.lang.String r3 = "A2DP_SINK profile not connected"
            com.realsil.sdk.core.logger.ZLogger.d(r3)
        L67:
            android.bluetooth.BluetoothProfile r3 = r2.m
            if (r3 == 0) goto L70
            java.util.List r3 = r3.getConnectedDevices()
            return r3
        L70:
            java.lang.String r3 = "AVRCP profile not connected"
            com.realsil.sdk.core.logger.ZLogger.d(r3)
        L75:
            return r1
        L76:
            java.lang.String r3 = "BT not enabled"
            com.realsil.sdk.core.logger.ZLogger.w(r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.bluetooth.BluetoothProfileManager.getConnectedDevices(int):java.util.List");
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x006f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int getConnectionState(int r3, android.bluetooth.BluetoothDevice r4) {
        /*
            r2 = this;
            android.bluetooth.BluetoothAdapter r0 = r2.g
            if (r0 == 0) goto L76
            boolean r0 = r0.isEnabled()
            if (r0 != 0) goto Lb
            goto L76
        Lb:
            r0 = 1
            if (r3 == r0) goto L1d
            r0 = 2
            if (r3 == r0) goto L2b
            r0 = 4
            if (r3 == r0) goto L39
            r0 = 11
            if (r3 == r0) goto L56
            r0 = 12
            if (r3 == r0) goto L66
            goto L74
        L1d:
            android.bluetooth.BluetoothHeadset r3 = r2.h
            if (r3 == 0) goto L26
            int r3 = r3.getConnectionState(r4)
            return r3
        L26:
            java.lang.String r3 = "HEADSET profile not connected"
            com.realsil.sdk.core.logger.ZLogger.d(r3)
        L2b:
            android.bluetooth.BluetoothA2dp r3 = r2.i
            if (r3 == 0) goto L34
            int r3 = r3.getConnectionState(r4)
            return r3
        L34:
            java.lang.String r3 = "A2DP profile not connected"
            com.realsil.sdk.core.logger.ZLogger.d(r3)
        L39:
            android.bluetooth.BluetoothProfile r3 = r2.k
            if (r3 == 0) goto L51
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 28
            if (r0 < r1) goto L4a
            java.lang.String r0 = "android.bluetooth.BluetoothHidHost"
            int r3 = com.realsil.sdk.core.bluetooth.impl.BluetoothProfileImpl.getConnectionState(r3, r0, r4)
            return r3
        L4a:
            java.lang.String r0 = "android.bluetooth.BluetoothInputDevice"
            int r3 = com.realsil.sdk.core.bluetooth.impl.BluetoothProfileImpl.getConnectionState(r3, r0, r4)
            return r3
        L51:
            java.lang.String r3 = "HID_HOST profile not connected"
            com.realsil.sdk.core.logger.ZLogger.d(r3)
        L56:
            android.bluetooth.BluetoothProfile r3 = r2.l
            if (r3 == 0) goto L61
            java.lang.String r0 = "android.bluetooth.BluetoothA2dpSink"
            int r3 = com.realsil.sdk.core.bluetooth.impl.BluetoothProfileImpl.getConnectionState(r3, r0, r4)
            return r3
        L61:
            java.lang.String r3 = "A2DP_SINK profile not connected"
            com.realsil.sdk.core.logger.ZLogger.d(r3)
        L66:
            android.bluetooth.BluetoothProfile r3 = r2.m
            if (r3 == 0) goto L6f
            int r3 = r3.getConnectionState(r4)
            return r3
        L6f:
            java.lang.String r3 = "AVRCP profile not connected"
            com.realsil.sdk.core.logger.ZLogger.d(r3)
        L74:
            r3 = 0
            return r3
        L76:
            java.lang.String r3 = "BT not enabled"
            com.realsil.sdk.core.logger.ZLogger.d(r3)
            r3 = -1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.bluetooth.BluetoothProfileManager.getConnectionState(int, android.bluetooth.BluetoothDevice):int");
    }

    public BluetoothProfile getProfile(int i) {
        if (i != 1) {
            if (i != 2) {
                if (i != 4) {
                    if (i != 11) {
                        if (i != 12) {
                            return null;
                        }
                        return this.m;
                    }
                    return this.l;
                }
                return this.k;
            }
            return this.i;
        }
        return this.h;
    }

    public boolean getProfileProxy(int i) {
        try {
            if (this.g.getProfileProxy(this.d, this.o, i)) {
                if (this.c) {
                    ZLogger.v(String.format(Locale.US, "getProfileProxy %d success", Integer.valueOf(i)));
                }
                return true;
            }
            ZLogger.w(String.format(Locale.US, "getProfileProxy %d failed", Integer.valueOf(i)));
            return false;
        } catch (Exception e) {
            ZLogger.w(String.format(Locale.US, "getProfileProxy %d exception: %s", Integer.valueOf(i), e.toString()));
            return false;
        }
    }

    public int getProfileState(BluetoothProfile bluetoothProfile, BluetoothDevice bluetoothDevice) {
        BluetoothAdapter bluetoothAdapter = this.g;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            ZLogger.w("BT not enabled");
            return -1;
        } else if (bluetoothProfile == null) {
            ZLogger.d("profile is not supported");
            return -1;
        } else {
            return bluetoothProfile.getConnectionState(bluetoothDevice);
        }
    }

    public boolean isProfileSupported(int i) {
        return i != 1 ? i != 2 ? i != 4 ? i != 11 ? i == 12 && this.m != null : this.l != null : this.k != null : this.i != null : this.h != null;
    }

    public void registerProfiles() {
        if (this.g == null) {
            ZLogger.w("mBluetoothAdapter == null");
            return;
        }
        this.n = new ProfileBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        getProfileProxy(2);
        intentFilter.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.a2dp.profile.action.PLAYING_STATE_CHANGED");
        getProfileProxy(1);
        intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.headset.action.VENDOR_SPECIFIC_HEADSET_EVENT");
        getProfileProxy(4);
        intentFilter.addAction("android.bluetooth.input.profile.action.CONNECTION_STATE_CHANGED");
        getProfileProxy(11);
        intentFilter.addAction(BluetoothA2dpSinkImpl.ACTION_CONNECTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothA2dpSinkImpl.ACTION_PLAYING_STATE_CHANGED);
        getProfileProxy(12);
        intentFilter.addAction(BluetoothAvrcpControllerImpl.ACTION_CONNECTION_STATE_CHANGED);
        this.d.registerReceiver(this.n, intentFilter);
    }

    public void removeManagerCallback(BluetoothProfileCallback bluetoothProfileCallback) {
        List<BluetoothProfileCallback> list = this.e;
        if (list != null) {
            list.remove(bluetoothProfileCallback);
        }
    }

    public void setAvrcpAbsoluteVolume(int i) {
        BluetoothA2dpImpl.setAvrcpAbsoluteVolume(this.i, i);
    }

    public boolean startScoUsingVirtualVoiceCall(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            ZLogger.w("can't find remote device.");
            return false;
        }
        BluetoothHeadset bluetoothHeadset = this.h;
        if (bluetoothHeadset == null) {
            ZLogger.w("BluetoothHeadset service is not connected");
            return false;
        } else if (Build.VERSION.SDK_INT >= 28) {
            return BluetoothHeadsetImpl.startScoUsingVirtualVoiceCall(bluetoothHeadset);
        } else {
            return BluetoothHeadsetImpl.startScoUsingVirtualVoiceCall(bluetoothHeadset, bluetoothDevice);
        }
    }

    public boolean stopScoUsingVirtualVoiceCall(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            ZLogger.w("can't find remote device.");
            return false;
        }
        BluetoothHeadset bluetoothHeadset = this.h;
        if (bluetoothHeadset == null) {
            ZLogger.w("BluetoothHeadset service is not connected");
            return false;
        } else if (Build.VERSION.SDK_INT >= 28) {
            return BluetoothHeadsetImpl.stopScoUsingVirtualVoiceCall(bluetoothHeadset);
        } else {
            return BluetoothHeadsetImpl.stopScoUsingVirtualVoiceCall(bluetoothHeadset, bluetoothDevice);
        }
    }

    public boolean connectA2dpSink(String str) {
        return connectA2dpSink(a(str));
    }

    public boolean connectA2dpSource(String str) {
        return connectA2dpSource(a(str));
    }

    public boolean connectHfpAg(String str) {
        BluetoothDevice remoteDevice = this.g.getRemoteDevice(str);
        if (remoteDevice == null) {
            return false;
        }
        BluetoothHeadset bluetoothHeadset = this.h;
        if (bluetoothHeadset == null) {
            ZLogger.w("BluetoothHeadset service is not connected");
            return false;
        } else if (bluetoothHeadset.getConnectionState(remoteDevice) == 2) {
            ZLogger.w("BluetoothHeadset profile is already connected");
            return true;
        } else {
            return BluetoothProfileImpl.connectProfile(this.h, remoteDevice);
        }
    }

    public boolean disconnectA2dpSource(String str) {
        return disconnectA2dpSource(a(str));
    }

    public boolean disconnectHfp(String str) {
        BluetoothDevice remoteDevice = this.g.getRemoteDevice(str);
        if (remoteDevice == null) {
            return false;
        }
        BluetoothHeadset bluetoothHeadset = this.h;
        if (bluetoothHeadset == null) {
            ZLogger.w("BluetoothHeadset service is not connected");
            return false;
        } else if (bluetoothHeadset.getConnectionState(remoteDevice) != 2) {
            ZLogger.w("BluetoothHeadset profile is not connected");
            return false;
        } else {
            return BluetoothProfileImpl.disconnect(this.h, remoteDevice);
        }
    }

    public boolean connectA2dpSink(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            ZLogger.w("can't find remote device.");
            return false;
        }
        BluetoothProfile bluetoothProfile = this.l;
        if (bluetoothProfile == null) {
            ZLogger.w("A2DP Sink not initialized");
            return false;
        } else if (bluetoothProfile.getConnectionState(bluetoothDevice) == 2) {
            ZLogger.w("A2DP Sink already connected");
            return true;
        } else {
            BluetoothAdapter bluetoothAdapter = this.g;
            if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
                return BluetoothProfileImpl.connectProfile(this.l, BluetoothA2dpSinkImpl.CLASS_NAME, bluetoothDevice);
            }
            ZLogger.d("BT not enabled");
            return false;
        }
    }

    public boolean connectA2dpSource(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            ZLogger.w("can't find remote device.");
            return false;
        }
        BluetoothA2dp bluetoothA2dp = this.i;
        if (bluetoothA2dp == null) {
            ZLogger.w("A2DP not initialized");
            return false;
        } else if (bluetoothA2dp.getConnectionState(bluetoothDevice) == 2) {
            ZLogger.w("a2dp already connected");
            return true;
        } else {
            BluetoothAdapter bluetoothAdapter = this.g;
            if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
                BluetoothProfileImpl.setPriority(this.i, bluetoothDevice, 100);
                return BluetoothProfileImpl.connectProfile(this.i, bluetoothDevice);
            }
            ZLogger.w("BT not enabled");
            return false;
        }
    }

    public boolean disconnectA2dpSource(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            ZLogger.w("device is null");
            return false;
        }
        BluetoothA2dp bluetoothA2dp = this.i;
        if (bluetoothA2dp == null) {
            ZLogger.w("A2DP not initialized");
            return false;
        } else if (bluetoothA2dp.getConnectionState(bluetoothDevice) != 2) {
            ZLogger.w("A2DP already disconnected");
            return false;
        } else {
            BluetoothProfileImpl.setPriority(this.i, bluetoothDevice, 100);
            return BluetoothProfileImpl.disconnect(this.i, bluetoothDevice);
        }
    }

    public final BluetoothDevice a(String str) {
        BluetoothAdapter bluetoothAdapter = this.g;
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            return this.g.getRemoteDevice(str);
        }
        ZLogger.w("BT not enabled");
        return null;
    }
}
