package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.text.DecimalFormat;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
/* loaded from: classes9.dex */
public class LargeValueFormatter implements IValueFormatter, IAxisValueFormatter {

    /* renamed from: a  reason: collision with root package name */
    public String[] f7946a;
    public int b;
    public DecimalFormat c;
    public String d;

    public LargeValueFormatter() {
        this.f7946a = new String[]{"", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "m", "b", RsaJsonWebKey.FACTOR_CRT_COEFFICIENT};
        this.b = 5;
        this.d = "";
        this.c = new DecimalFormat("###E00");
    }

    public final String a(double d) {
        String format = this.c.format(d);
        int numericValue = Character.getNumericValue(format.charAt(format.length() - 1));
        String replaceAll = format.replaceAll("E[0-9][0-9]", this.f7946a[Integer.valueOf(Character.getNumericValue(format.charAt(format.length() - 2)) + "" + numericValue).intValue() / 3]);
        while (true) {
            if (replaceAll.length() <= this.b && !replaceAll.matches("[0-9]+\\.[a-z]")) {
                return replaceAll;
            }
            replaceAll = replaceAll.substring(0, replaceAll.length() - 2) + replaceAll.substring(replaceAll.length() - 1);
        }
    }

    public int getDecimalDigits() {
        return 0;
    }

    @Override // com.github.mikephil.charting.formatter.IValueFormatter
    public String getFormattedValue(float f, Entry entry, int i, ViewPortHandler viewPortHandler) {
        return a(f) + this.d;
    }

    public void setAppendix(String str) {
        this.d = str;
    }

    public void setMaxLength(int i) {
        this.b = i;
    }

    public void setSuffix(String[] strArr) {
        this.f7946a = strArr;
    }

    @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
    public String getFormattedValue(float f, AxisBase axisBase) {
        return a(f) + this.d;
    }

    public LargeValueFormatter(String str) {
        this();
        this.d = str;
    }
}
