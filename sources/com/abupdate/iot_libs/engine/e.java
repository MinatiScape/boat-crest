package com.abupdate.iot_libs.engine;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
/* loaded from: classes.dex */
public class e {
    public static e d;

    /* renamed from: a  reason: collision with root package name */
    public TelephonyManager f1894a;
    public String b;
    public GsmCellLocation c;

    public static e a() {
        if (d == null) {
            d = new e();
        }
        return d;
    }

    public String b() {
        try {
            return String.valueOf(this.c.getLac());
        } catch (Exception unused) {
            return "";
        }
    }

    public String c() {
        try {
            return String.valueOf(this.c.getCid());
        } catch (Exception unused) {
            return "";
        }
    }

    public String d() {
        try {
            return String.valueOf(Integer.parseInt(this.b.substring(0, 3)));
        } catch (Exception unused) {
            return "";
        }
    }

    public String e() {
        try {
            return String.valueOf(Integer.parseInt(this.b.substring(3)));
        } catch (Exception unused) {
            return "";
        }
    }

    public e a(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            this.f1894a = telephonyManager;
            this.b = telephonyManager.getNetworkOperator();
            this.c = (GsmCellLocation) this.f1894a.getCellLocation();
        } catch (Exception unused) {
        }
        return this;
    }
}
