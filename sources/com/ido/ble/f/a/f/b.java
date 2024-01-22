package com.ido.ble.f.a.f;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.common.d;
import com.ido.ble.common.e;
import com.ido.ble.common.k;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class b extends d {
    private static final String c = "common_info";
    private static final String d = "lastConnectedDeviceInfo";
    private static final String e = "hasMigrateData";
    private static final String f = "bindMacAddressList";
    private static b g;

    private b() {
    }

    public static b e() {
        if (g == null) {
            b bVar = new b();
            g = bVar;
            bVar.a(e.a());
        }
        return g;
    }

    public void a(Context context) {
        super.a(context, c);
    }

    public void a(BLEDevice bLEDevice) {
        b(d, new Gson().toJson(bLEDevice));
    }

    public void a(boolean z) {
        b(e, z);
    }

    public List<String> b() {
        List a2;
        String a3 = a(f, "");
        if (!TextUtils.isEmpty(a3) && (a2 = k.a(a3, String[].class)) != null) {
            return new ArrayList(a2);
        }
        return new ArrayList();
    }

    public BLEDevice c() {
        return (BLEDevice) a(d, BLEDevice.class);
    }

    public void c(String str) {
        List<String> b = b();
        for (String str2 : b) {
            if (str2.equals(str)) {
                return;
            }
        }
        b.add(str);
        b(f, k.a(b));
    }

    public void d(String str) {
        List<String> b = b();
        ArrayList arrayList = new ArrayList(b);
        int i = 0;
        while (true) {
            if (i >= b.size()) {
                break;
            } else if (b.get(i).equals(str)) {
                arrayList.remove(i);
                break;
            } else {
                i++;
            }
        }
        b(f, k.a(arrayList));
    }

    public boolean d() {
        return a(e, false);
    }
}
