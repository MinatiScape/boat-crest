package com.elvishew.xlog.internal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import com.elvishew.xlog.formatter.message.object.BundleFormatter;
import com.elvishew.xlog.formatter.message.object.IntentFormatter;
import com.elvishew.xlog.formatter.message.object.ObjectFormatter;
import com.elvishew.xlog.printer.AndroidPrinter;
import com.elvishew.xlog.printer.ConsolePrinter;
import com.elvishew.xlog.printer.Printer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes9.dex */
public class Platform {

    /* renamed from: a  reason: collision with root package name */
    public static final Platform f7873a = c();

    /* loaded from: classes9.dex */
    public static class a extends Platform {
        public static final Map<Class<?>, ObjectFormatter<?>> b;

        static {
            HashMap hashMap = new HashMap();
            hashMap.put(Bundle.class, new BundleFormatter());
            hashMap.put(Intent.class, new IntentFormatter());
            b = Collections.unmodifiableMap(hashMap);
        }

        @Override // com.elvishew.xlog.internal.Platform
        public Map<Class<?>, ObjectFormatter<?>> a() {
            return b;
        }

        @Override // com.elvishew.xlog.internal.Platform
        public Printer b() {
            return new AndroidPrinter();
        }

        @Override // com.elvishew.xlog.internal.Platform
        public String d() {
            return Build.VERSION.SDK_INT < 19 ? "\n" : System.lineSeparator();
        }

        @Override // com.elvishew.xlog.internal.Platform
        public void error(String str) {
            Log.e("XLog", str);
        }

        @Override // com.elvishew.xlog.internal.Platform
        public void warn(String str) {
            Log.w("XLog", str);
        }
    }

    public static Platform c() {
        try {
            Class.forName("android.os.Build");
            if (Build.VERSION.SDK_INT != 0) {
                return new a();
            }
        } catch (ClassNotFoundException unused) {
        }
        return new Platform();
    }

    public static Platform get() {
        return f7873a;
    }

    public Map<Class<?>, ObjectFormatter<?>> a() {
        return Collections.emptyMap();
    }

    public Printer b() {
        return new ConsolePrinter();
    }

    @SuppressLint({"NewApi"})
    public String d() {
        return System.lineSeparator();
    }

    public void error(String str) {
        System.out.println(str);
    }

    public void warn(String str) {
        System.out.println(str);
    }
}
