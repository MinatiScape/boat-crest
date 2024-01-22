package com.blankj.utilcode.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import com.blankj.utilcode.util.Utils;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArraySet;
/* loaded from: classes.dex */
public final class NetworkUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final Set<Utils.Consumer<WifiScanResults>> f2268a = new CopyOnWriteArraySet();
    public static Timer b;
    public static WifiScanResults c;

    /* loaded from: classes.dex */
    public static final class NetworkChangedReceiver extends BroadcastReceiver {

        /* renamed from: a  reason: collision with root package name */
        public NetworkType f2269a;
        public Set<OnNetworkStatusChangedListener> b = new HashSet();

        /* loaded from: classes.dex */
        public class a implements Runnable {
            public final /* synthetic */ OnNetworkStatusChangedListener h;

            public a(OnNetworkStatusChangedListener onNetworkStatusChangedListener) {
                this.h = onNetworkStatusChangedListener;
            }

            @Override // java.lang.Runnable
            @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
            public void run() {
                int size = NetworkChangedReceiver.this.b.size();
                NetworkChangedReceiver.this.b.add(this.h);
                if (size == 0 && NetworkChangedReceiver.this.b.size() == 1) {
                    NetworkChangedReceiver.this.f2269a = NetworkUtils.getNetworkType();
                    Utils.getApp().registerReceiver(NetworkChangedReceiver.a(), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                }
            }
        }

        /* loaded from: classes.dex */
        public class b implements Runnable {
            public final /* synthetic */ OnNetworkStatusChangedListener h;

            public b(OnNetworkStatusChangedListener onNetworkStatusChangedListener) {
                this.h = onNetworkStatusChangedListener;
            }

            @Override // java.lang.Runnable
            public void run() {
                int size = NetworkChangedReceiver.this.b.size();
                NetworkChangedReceiver.this.b.remove(this.h);
                if (size == 1 && NetworkChangedReceiver.this.b.size() == 0) {
                    Utils.getApp().unregisterReceiver(NetworkChangedReceiver.a());
                }
            }
        }

        /* loaded from: classes.dex */
        public class c implements Runnable {
            public c() {
            }

            @Override // java.lang.Runnable
            @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
            public void run() {
                NetworkType networkType = NetworkUtils.getNetworkType();
                if (NetworkChangedReceiver.this.f2269a == networkType) {
                    return;
                }
                NetworkChangedReceiver.this.f2269a = networkType;
                if (networkType == NetworkType.NETWORK_NO) {
                    for (OnNetworkStatusChangedListener onNetworkStatusChangedListener : NetworkChangedReceiver.this.b) {
                        onNetworkStatusChangedListener.onDisconnected();
                    }
                    return;
                }
                for (OnNetworkStatusChangedListener onNetworkStatusChangedListener2 : NetworkChangedReceiver.this.b) {
                    onNetworkStatusChangedListener2.onConnected(networkType);
                }
            }
        }

        /* loaded from: classes.dex */
        public static class d {

            /* renamed from: a  reason: collision with root package name */
            public static final NetworkChangedReceiver f2270a = new NetworkChangedReceiver();
        }

        public static /* synthetic */ NetworkChangedReceiver a() {
            return e();
        }

        public static NetworkChangedReceiver e() {
            return d.f2270a;
        }

        public boolean f(OnNetworkStatusChangedListener onNetworkStatusChangedListener) {
            if (onNetworkStatusChangedListener == null) {
                return false;
            }
            return this.b.contains(onNetworkStatusChangedListener);
        }

        @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
        public void g(OnNetworkStatusChangedListener onNetworkStatusChangedListener) {
            if (onNetworkStatusChangedListener == null) {
                return;
            }
            com.blankj.utilcode.util.b.U0(new a(onNetworkStatusChangedListener));
        }

        public void h(OnNetworkStatusChangedListener onNetworkStatusChangedListener) {
            if (onNetworkStatusChangedListener == null) {
                return;
            }
            com.blankj.utilcode.util.b.U0(new b(onNetworkStatusChangedListener));
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                com.blankj.utilcode.util.b.V0(new c(), 1000L);
            }
        }
    }

    /* loaded from: classes.dex */
    public enum NetworkType {
        NETWORK_ETHERNET,
        NETWORK_WIFI,
        NETWORK_5G,
        NETWORK_4G,
        NETWORK_3G,
        NETWORK_2G,
        NETWORK_UNKNOWN,
        NETWORK_NO
    }

    /* loaded from: classes.dex */
    public interface OnNetworkStatusChangedListener {
        void onConnected(NetworkType networkType);

        void onDisconnected();
    }

    /* loaded from: classes.dex */
    public static final class WifiScanResults {

        /* renamed from: a  reason: collision with root package name */
        public List<ScanResult> f2271a = new ArrayList();
        public List<ScanResult> b = new ArrayList();

        public static List<ScanResult> b(List<ScanResult> list) {
            ScanResult scanResult;
            if (list != null && !list.isEmpty()) {
                LinkedHashMap linkedHashMap = new LinkedHashMap(list.size());
                for (ScanResult scanResult2 : list) {
                    if (!TextUtils.isEmpty(scanResult2.SSID) && ((scanResult = (ScanResult) linkedHashMap.get(scanResult2.SSID)) == null || scanResult.level < scanResult2.level)) {
                        linkedHashMap.put(scanResult2.SSID, scanResult2);
                    }
                }
                return new ArrayList(linkedHashMap.values());
            }
            return new ArrayList();
        }

        public List<ScanResult> getAllResults() {
            return this.f2271a;
        }

        public List<ScanResult> getFilterResults() {
            return this.b;
        }

        public void setAllResults(List<ScanResult> list) {
            this.f2271a = list;
            this.b = b(list);
        }
    }

    /* loaded from: classes.dex */
    public static class a extends Utils.Task<Boolean> {
        public a(Utils.Consumer consumer) {
            super(consumer);
        }

        @Override // com.blankj.utilcode.util.ThreadUtils.Task
        @RequiresPermission("android.permission.INTERNET")
        /* renamed from: g */
        public Boolean doInBackground() {
            return Boolean.valueOf(NetworkUtils.isAvailable());
        }
    }

    /* loaded from: classes.dex */
    public static class b extends Utils.Task<Boolean> {
        public final /* synthetic */ String p;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(Utils.Consumer consumer, String str) {
            super(consumer);
            this.p = str;
        }

        @Override // com.blankj.utilcode.util.ThreadUtils.Task
        @RequiresPermission("android.permission.INTERNET")
        /* renamed from: g */
        public Boolean doInBackground() {
            return Boolean.valueOf(NetworkUtils.isAvailableByPing(this.p));
        }
    }

    /* loaded from: classes.dex */
    public static class c extends Utils.Task<Boolean> {
        public final /* synthetic */ String p;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(Utils.Consumer consumer, String str) {
            super(consumer);
            this.p = str;
        }

        @Override // com.blankj.utilcode.util.ThreadUtils.Task
        @RequiresPermission("android.permission.INTERNET")
        /* renamed from: g */
        public Boolean doInBackground() {
            return Boolean.valueOf(NetworkUtils.isAvailableByDns(this.p));
        }
    }

    /* loaded from: classes.dex */
    public static class d extends Utils.Task<Boolean> {
        public d(Utils.Consumer consumer) {
            super(consumer);
        }

        @Override // com.blankj.utilcode.util.ThreadUtils.Task
        @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.INTERNET"})
        /* renamed from: g */
        public Boolean doInBackground() {
            return Boolean.valueOf(NetworkUtils.isWifiAvailable());
        }
    }

    /* loaded from: classes.dex */
    public static class e extends Utils.Task<String> {
        public final /* synthetic */ boolean p;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(Utils.Consumer consumer, boolean z) {
            super(consumer);
            this.p = z;
        }

        @Override // com.blankj.utilcode.util.ThreadUtils.Task
        @RequiresPermission("android.permission.INTERNET")
        /* renamed from: g */
        public String doInBackground() {
            return NetworkUtils.getIPAddress(this.p);
        }
    }

    /* loaded from: classes.dex */
    public static class f extends Utils.Task<String> {
        public final /* synthetic */ String p;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f(Utils.Consumer consumer, String str) {
            super(consumer);
            this.p = str;
        }

        @Override // com.blankj.utilcode.util.ThreadUtils.Task
        @RequiresPermission("android.permission.INTERNET")
        /* renamed from: g */
        public String doInBackground() {
            return NetworkUtils.getDomainAddress(this.p);
        }
    }

    /* loaded from: classes.dex */
    public static class g implements Runnable {
        public final /* synthetic */ Utils.Consumer h;

        public g(Utils.Consumer consumer) {
            this.h = consumer;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (NetworkUtils.f2268a.isEmpty()) {
                NetworkUtils.f2268a.add(this.h);
                NetworkUtils.l();
                return;
            }
            this.h.accept(NetworkUtils.c);
            NetworkUtils.f2268a.add(this.h);
        }
    }

    /* loaded from: classes.dex */
    public static class h extends TimerTask {

        /* loaded from: classes.dex */
        public class a implements Runnable {
            public a(h hVar) {
            }

            @Override // java.lang.Runnable
            public void run() {
                for (Utils.Consumer consumer : NetworkUtils.f2268a) {
                    consumer.accept(NetworkUtils.c);
                }
            }
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.CHANGE_WIFI_STATE", "android.permission.ACCESS_COARSE_LOCATION"})
        public void run() {
            NetworkUtils.m();
            WifiScanResults wifiScanResult = NetworkUtils.getWifiScanResult();
            if (NetworkUtils.k(NetworkUtils.c.f2271a, wifiScanResult.f2271a)) {
                return;
            }
            WifiScanResults unused = NetworkUtils.c = wifiScanResult;
            com.blankj.utilcode.util.b.U0(new a(this));
        }
    }

    /* loaded from: classes.dex */
    public static class i implements Runnable {
        public final /* synthetic */ Utils.Consumer h;

        public i(Utils.Consumer consumer) {
            this.h = consumer;
        }

        @Override // java.lang.Runnable
        public void run() {
            NetworkUtils.f2268a.remove(this.h);
            if (NetworkUtils.f2268a.isEmpty()) {
                NetworkUtils.n();
            }
        }
    }

    public NetworkUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.CHANGE_WIFI_STATE", "android.permission.ACCESS_COARSE_LOCATION"})
    public static void addOnWifiChangedConsumer(Utils.Consumer<WifiScanResults> consumer) {
        if (consumer == null) {
            return;
        }
        com.blankj.utilcode.util.b.U0(new g(consumer));
    }

    public static String getBroadcastIpAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            new LinkedList();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (nextElement.isUp() && !nextElement.isLoopback()) {
                    List<InterfaceAddress> interfaceAddresses = nextElement.getInterfaceAddresses();
                    int size = interfaceAddresses.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        InetAddress broadcast = interfaceAddresses.get(i2).getBroadcast();
                        if (broadcast != null) {
                            return broadcast.getHostAddress();
                        }
                    }
                    continue;
                }
            }
            return "";
        } catch (SocketException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    @RequiresPermission("android.permission.INTERNET")
    public static String getDomainAddress(String str) {
        try {
            return InetAddress.getByName(str).getHostAddress();
        } catch (UnknownHostException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    @RequiresPermission("android.permission.INTERNET")
    public static Utils.Task<String> getDomainAddressAsync(String str, @NonNull Utils.Consumer<String> consumer) {
        Objects.requireNonNull(consumer, "Argument 'consumer' of type Utils.Consumer<String> (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return com.blankj.utilcode.util.b.v(new f(consumer, str));
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static String getGatewayByWifi() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        return wifiManager == null ? "" : Formatter.formatIpAddress(wifiManager.getDhcpInfo().gateway);
    }

    @RequiresPermission("android.permission.INTERNET")
    public static String getIPAddress(boolean z) {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            LinkedList linkedList = new LinkedList();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (nextElement.isUp() && !nextElement.isLoopback()) {
                    Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        linkedList.addFirst(inetAddresses.nextElement());
                    }
                }
            }
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                InetAddress inetAddress = (InetAddress) it.next();
                if (!inetAddress.isLoopbackAddress()) {
                    String hostAddress = inetAddress.getHostAddress();
                    boolean z2 = hostAddress.indexOf(58) < 0;
                    if (z) {
                        if (z2) {
                            return hostAddress;
                        }
                    } else if (!z2) {
                        int indexOf = hostAddress.indexOf(37);
                        if (indexOf < 0) {
                            return hostAddress.toUpperCase();
                        }
                        return hostAddress.substring(0, indexOf).toUpperCase();
                    }
                }
            }
            return "";
        } catch (SocketException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static Utils.Task<String> getIPAddressAsync(boolean z, @NonNull Utils.Consumer<String> consumer) {
        Objects.requireNonNull(consumer, "Argument 'consumer' of type Utils.Consumer<String> (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return com.blankj.utilcode.util.b.v(new e(consumer, z));
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static String getIpAddressByWifi() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        return wifiManager == null ? "" : Formatter.formatIpAddress(wifiManager.getDhcpInfo().ipAddress);
    }

    public static boolean getMobileDataEnabled() {
        TelephonyManager telephonyManager;
        try {
            telephonyManager = (TelephonyManager) Utils.getApp().getSystemService("phone");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (telephonyManager == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            return telephonyManager.isDataEnabled();
        }
        Method declaredMethod = telephonyManager.getClass().getDeclaredMethod("getDataEnabled", new Class[0]);
        if (declaredMethod != null) {
            return ((Boolean) declaredMethod.invoke(telephonyManager, new Object[0])).booleanValue();
        }
        return false;
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static String getNetMaskByWifi() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        return wifiManager == null ? "" : Formatter.formatIpAddress(wifiManager.getDhcpInfo().netmask);
    }

    public static String getNetworkOperatorName() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.getApp().getSystemService("phone");
        return telephonyManager == null ? "" : telephonyManager.getNetworkOperatorName();
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static NetworkType getNetworkType() {
        if (i()) {
            return NetworkType.NETWORK_ETHERNET;
        }
        NetworkInfo h2 = h();
        if (h2 != null && h2.isAvailable()) {
            if (h2.getType() == 1) {
                return NetworkType.NETWORK_WIFI;
            }
            if (h2.getType() == 0) {
                switch (h2.getSubtype()) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                    case 16:
                        return NetworkType.NETWORK_2G;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                    case 17:
                        return NetworkType.NETWORK_3G;
                    case 13:
                    case 18:
                        return NetworkType.NETWORK_4G;
                    case 19:
                    default:
                        String subtypeName = h2.getSubtypeName();
                        if (!subtypeName.equalsIgnoreCase("TD-SCDMA") && !subtypeName.equalsIgnoreCase("WCDMA") && !subtypeName.equalsIgnoreCase("CDMA2000")) {
                            return NetworkType.NETWORK_UNKNOWN;
                        }
                        return NetworkType.NETWORK_3G;
                    case 20:
                        return NetworkType.NETWORK_5G;
                }
            }
            return NetworkType.NETWORK_UNKNOWN;
        }
        return NetworkType.NETWORK_NO;
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static String getSSID() {
        WifiInfo connectionInfo;
        WifiManager wifiManager = (WifiManager) Utils.getApp().getApplicationContext().getSystemService("wifi");
        if (wifiManager == null || (connectionInfo = wifiManager.getConnectionInfo()) == null) {
            return "";
        }
        String ssid = connectionInfo.getSSID();
        return TextUtils.isEmpty(ssid) ? "" : (ssid.length() > 2 && ssid.charAt(0) == '\"' && ssid.charAt(ssid.length() - 1) == '\"') ? ssid.substring(1, ssid.length() - 1) : ssid;
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static String getServerAddressByWifi() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        return wifiManager == null ? "" : Formatter.formatIpAddress(wifiManager.getDhcpInfo().serverAddress);
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static boolean getWifiEnabled() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        if (wifiManager == null) {
            return false;
        }
        return wifiManager.isWifiEnabled();
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.ACCESS_COARSE_LOCATION"})
    public static WifiScanResults getWifiScanResult() {
        List<ScanResult> scanResults;
        WifiScanResults wifiScanResults = new WifiScanResults();
        if (getWifiEnabled() && (scanResults = ((WifiManager) Utils.getApp().getSystemService("wifi")).getScanResults()) != null) {
            wifiScanResults.setAllResults(scanResults);
        }
        return wifiScanResults;
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static NetworkInfo h() {
        ConnectivityManager connectivityManager = (ConnectivityManager) Utils.getApp().getSystemService("connectivity");
        if (connectivityManager == null) {
            return null;
        }
        return connectivityManager.getActiveNetworkInfo();
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean i() {
        NetworkInfo networkInfo;
        NetworkInfo.State state;
        ConnectivityManager connectivityManager = (ConnectivityManager) Utils.getApp().getSystemService("connectivity");
        if (connectivityManager == null || (networkInfo = connectivityManager.getNetworkInfo(9)) == null || (state = networkInfo.getState()) == null) {
            return false;
        }
        return state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING;
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean is4G() {
        NetworkInfo h2 = h();
        return h2 != null && h2.isAvailable() && h2.getSubtype() == 13;
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean is5G() {
        NetworkInfo h2 = h();
        return h2 != null && h2.isAvailable() && h2.getSubtype() == 20;
    }

    @RequiresPermission("android.permission.INTERNET")
    public static boolean isAvailable() {
        return isAvailableByDns() || isAvailableByPing(null);
    }

    @RequiresPermission("android.permission.INTERNET")
    public static Utils.Task<Boolean> isAvailableAsync(@NonNull Utils.Consumer<Boolean> consumer) {
        Objects.requireNonNull(consumer, "Argument 'consumer' of type Utils.Consumer<Boolean> (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return com.blankj.utilcode.util.b.v(new a(consumer));
    }

    @RequiresPermission("android.permission.INTERNET")
    public static boolean isAvailableByDns() {
        return isAvailableByDns("");
    }

    @RequiresPermission("android.permission.INTERNET")
    public static void isAvailableByDnsAsync(Utils.Consumer<Boolean> consumer) {
        isAvailableByDnsAsync("", consumer);
    }

    @RequiresPermission("android.permission.INTERNET")
    public static boolean isAvailableByPing() {
        return isAvailableByPing("");
    }

    @RequiresPermission("android.permission.INTERNET")
    public static void isAvailableByPingAsync(Utils.Consumer<Boolean> consumer) {
        isAvailableByPingAsync("", consumer);
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean isConnected() {
        NetworkInfo h2 = h();
        return h2 != null && h2.isConnected();
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean isMobileData() {
        NetworkInfo h2 = h();
        return h2 != null && h2.isAvailable() && h2.getType() == 0;
    }

    public static boolean isRegisteredNetworkStatusChangedListener(OnNetworkStatusChangedListener onNetworkStatusChangedListener) {
        return NetworkChangedReceiver.a().f(onNetworkStatusChangedListener);
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.INTERNET"})
    public static boolean isWifiAvailable() {
        return getWifiEnabled() && isAvailable();
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.INTERNET"})
    public static Utils.Task<Boolean> isWifiAvailableAsync(@NonNull Utils.Consumer<Boolean> consumer) {
        Objects.requireNonNull(consumer, "Argument 'consumer' of type Utils.Consumer<Boolean> (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return com.blankj.utilcode.util.b.v(new d(consumer));
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean isWifiConnected() {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) Utils.getApp().getSystemService("connectivity");
        return (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || activeNetworkInfo.getType() != 1) ? false : true;
    }

    public static boolean j(ScanResult scanResult, ScanResult scanResult2) {
        return scanResult != null && scanResult2 != null && com.blankj.utilcode.util.b.A(scanResult.BSSID, scanResult2.BSSID) && com.blankj.utilcode.util.b.A(scanResult.SSID, scanResult2.SSID) && com.blankj.utilcode.util.b.A(scanResult.capabilities, scanResult2.capabilities) && scanResult.level == scanResult2.level;
    }

    public static boolean k(List<ScanResult> list, List<ScanResult> list2) {
        if (list == null && list2 == null) {
            return true;
        }
        if (list == null || list2 == null || list.size() != list2.size()) {
            return false;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (!j(list.get(i2), list2.get(i2))) {
                return false;
            }
        }
        return true;
    }

    public static void l() {
        c = new WifiScanResults();
        Timer timer = new Timer();
        b = timer;
        timer.schedule(new h(), 0L, 3000L);
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.CHANGE_WIFI_STATE"})
    public static void m() {
        if (getWifiEnabled()) {
            ((WifiManager) Utils.getApp().getSystemService("wifi")).startScan();
        }
    }

    public static void n() {
        Timer timer = b;
        if (timer != null) {
            timer.cancel();
            b = null;
        }
    }

    public static void openWirelessSettings() {
        Utils.getApp().startActivity(new Intent("android.settings.WIRELESS_SETTINGS").setFlags(268435456));
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static void registerNetworkStatusChangedListener(OnNetworkStatusChangedListener onNetworkStatusChangedListener) {
        NetworkChangedReceiver.a().g(onNetworkStatusChangedListener);
    }

    public static void removeOnWifiChangedConsumer(Utils.Consumer<WifiScanResults> consumer) {
        if (consumer == null) {
            return;
        }
        com.blankj.utilcode.util.b.U0(new i(consumer));
    }

    @RequiresPermission("android.permission.CHANGE_WIFI_STATE")
    public static void setWifiEnabled(boolean z) {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        if (wifiManager == null || z == wifiManager.isWifiEnabled()) {
            return;
        }
        wifiManager.setWifiEnabled(z);
    }

    public static void unregisterNetworkStatusChangedListener(OnNetworkStatusChangedListener onNetworkStatusChangedListener) {
        NetworkChangedReceiver.a().h(onNetworkStatusChangedListener);
    }

    @RequiresPermission("android.permission.INTERNET")
    public static boolean isAvailableByDns(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "www.baidu.com";
        }
        try {
            return InetAddress.getByName(str) != null;
        } catch (UnknownHostException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @RequiresPermission("android.permission.INTERNET")
    public static Utils.Task isAvailableByDnsAsync(String str, @NonNull Utils.Consumer<Boolean> consumer) {
        Objects.requireNonNull(consumer, "Argument 'consumer' of type Utils.Consumer<Boolean> (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return com.blankj.utilcode.util.b.v(new c(consumer, str));
    }

    @RequiresPermission("android.permission.INTERNET")
    public static boolean isAvailableByPing(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "223.5.5.5";
        }
        return ShellUtils.execCmd(String.format("ping -c 1 %s", str), false).result == 0;
    }

    @RequiresPermission("android.permission.INTERNET")
    public static Utils.Task<Boolean> isAvailableByPingAsync(String str, @NonNull Utils.Consumer<Boolean> consumer) {
        Objects.requireNonNull(consumer, "Argument 'consumer' of type Utils.Consumer<Boolean> (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return com.blankj.utilcode.util.b.v(new b(consumer, str));
    }
}
