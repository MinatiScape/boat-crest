package com.google.zxing.client.result;
/* loaded from: classes11.dex */
public final class VINParsedResult extends ParsedResult {
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;
    public final int h;
    public final char i;
    public final String j;

    public VINParsedResult(String str, String str2, String str3, String str4, String str5, String str6, int i, char c, String str7) {
        super(ParsedResultType.VIN);
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = str5;
        this.g = str6;
        this.h = i;
        this.i = c;
        this.j = str7;
    }

    public String getCountryCode() {
        return this.f;
    }

    @Override // com.google.zxing.client.result.ParsedResult
    public String getDisplayResult() {
        StringBuilder sb = new StringBuilder(50);
        sb.append(this.c);
        sb.append(' ');
        sb.append(this.d);
        sb.append(' ');
        sb.append(this.e);
        sb.append('\n');
        String str = this.f;
        if (str != null) {
            sb.append(str);
            sb.append(' ');
        }
        sb.append(this.h);
        sb.append(' ');
        sb.append(this.i);
        sb.append(' ');
        sb.append(this.j);
        sb.append('\n');
        return sb.toString();
    }

    public int getModelYear() {
        return this.h;
    }

    public char getPlantCode() {
        return this.i;
    }

    public String getSequentialNumber() {
        return this.j;
    }

    public String getVIN() {
        return this.b;
    }

    public String getVehicleAttributes() {
        return this.g;
    }

    public String getVehicleDescriptorSection() {
        return this.d;
    }

    public String getVehicleIdentifierSection() {
        return this.e;
    }

    public String getWorldManufacturerID() {
        return this.c;
    }
}
