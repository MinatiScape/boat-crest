package com.crrepa.ble.conn.bean;
/* loaded from: classes9.dex */
public class CRPFirmwareVersionInfo {
    public static final int HS_6620_D = 4;

    /* renamed from: a  reason: collision with root package name */
    public String f7646a;
    public String b;
    public String c;
    public int d;
    public int e;
    public boolean f;

    public CRPFirmwareVersionInfo(String str, String str2, String str3, int i, int i2, boolean z) {
        this.f7646a = str;
        this.b = str2;
        this.c = str3;
        this.d = i;
        this.e = i2;
        this.f = z;
    }

    public String getChangeNotes() {
        return this.b;
    }

    public String getChangeNotesEn() {
        return this.c;
    }

    public int getMcu() {
        return this.e;
    }

    public int getType() {
        return this.d;
    }

    public String getVersion() {
        return this.f7646a;
    }

    public boolean isTpUpgrade() {
        return this.f;
    }
}
