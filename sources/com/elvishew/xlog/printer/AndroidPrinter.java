package com.elvishew.xlog.printer;

import android.util.Log;
import com.mappls.sdk.navigation.NavigationConstants;
/* loaded from: classes9.dex */
public class AndroidPrinter implements Printer {

    /* renamed from: a  reason: collision with root package name */
    public boolean f7876a;
    public int b;

    public AndroidPrinter() {
        this(false, NavigationConstants.UI_HANDLER_MAP_CONTROLS);
    }

    public static int a(String str, int i, int i2) {
        if (i2 == str.length() || str.charAt(i2) == '\n') {
            return i2;
        }
        for (int i3 = i2 - 1; i < i3; i3--) {
            if (str.charAt(i3) == '\n') {
                return i3;
            }
        }
        return i2;
    }

    public void b(int i, String str, String str2) {
        Log.println(i, str, str2);
    }

    @Override // com.elvishew.xlog.printer.Printer
    public void println(int i, String str, String str2) {
        int length = str2.length();
        int i2 = 0;
        while (i2 < length) {
            if (str2.charAt(i2) == '\n') {
                i2++;
            } else {
                int min = Math.min(this.b + i2, length);
                if (this.f7876a) {
                    int indexOf = str2.indexOf(10, i2);
                    if (indexOf != -1) {
                        min = indexOf;
                    }
                } else {
                    min = a(str2, i2, min);
                }
                b(i, str, str2.substring(i2, min));
                i2 = min;
            }
        }
    }

    public AndroidPrinter(boolean z) {
        this(z, NavigationConstants.UI_HANDLER_MAP_CONTROLS);
    }

    public AndroidPrinter(int i) {
        this(false, i);
    }

    public AndroidPrinter(boolean z, int i) {
        this.f7876a = z;
        this.b = i;
    }
}
