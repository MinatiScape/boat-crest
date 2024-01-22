package com.blankj.utilcode.util;

import android.content.ClipData;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.IntRange;
import androidx.annotation.RequiresApi;
import androidx.collection.SimpleArrayMap;
import com.blankj.utilcode.util.b;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes.dex */
public final class LogUtils {
    public static final int A = 7;
    public static final int D = 3;
    public static final int E = 6;
    public static final int I = 4;
    public static final int V = 2;
    public static final int W = 5;
    public static SimpleDateFormat e;

    /* renamed from: a  reason: collision with root package name */
    public static final char[] f2261a = {'V', 'D', 'I', 'W', 'E', 'A'};
    public static final String b = System.getProperty("file.separator");
    public static final String c = System.getProperty("line.separator");
    public static final Config d = new Config(null);
    public static final ExecutorService f = Executors.newSingleThreadExecutor();
    public static final SimpleArrayMap<Class, IFormatter> g = new SimpleArrayMap<>();

    /* loaded from: classes.dex */
    public static final class Config {

        /* renamed from: a  reason: collision with root package name */
        public String f2262a;
        public String b;
        public String c;
        public String d;
        public boolean e;
        public boolean f;
        public String g;
        public boolean h;
        public boolean i;
        public boolean j;
        public boolean k;
        public boolean l;
        public int m;
        public int n;
        public int o;
        public int p;
        public int q;
        public String r;
        public IFileWriter s;
        public OnConsoleOutputListener t;
        public OnFileOutputListener u;
        public b.a v;

        public /* synthetic */ Config(a aVar) {
            this();
        }

        public final Config addFileExtraHead(Map<String, String> map) {
            this.v.c(map);
            return this;
        }

        public final <T> Config addFormatter(IFormatter<T> iFormatter) {
            if (iFormatter != null) {
                LogUtils.g.put(LogUtils.s(iFormatter), iFormatter);
            }
            return this;
        }

        public final char getConsoleFilter() {
            return LogUtils.f2261a[this.m - 2];
        }

        public final String getDefaultDir() {
            return this.f2262a;
        }

        public final String getDir() {
            String str = this.b;
            return str == null ? this.f2262a : str;
        }

        public final String getFileExtension() {
            return this.d;
        }

        public final char getFileFilter() {
            return LogUtils.f2261a[this.n - 2];
        }

        public final String getFilePrefix() {
            return this.c;
        }

        public final String getGlobalTag() {
            return com.blankj.utilcode.util.b.C0(this.g) ? "" : this.g;
        }

        public final String getProcessName() {
            String str = this.r;
            return str == null ? "" : str.replace(":", "_");
        }

        public final int getSaveDays() {
            return this.q;
        }

        public final int getStackDeep() {
            return this.o;
        }

        public final int getStackOffset() {
            return this.p;
        }

        public final boolean haveSetOnConsoleOutputListener() {
            return this.t != null;
        }

        public final boolean haveSetOnFileOutputListener() {
            return this.u != null;
        }

        public final boolean isLog2ConsoleSwitch() {
            return this.f;
        }

        public final boolean isLog2FileSwitch() {
            return this.j;
        }

        public final boolean isLogBorderSwitch() {
            return this.k;
        }

        public final boolean isLogHeadSwitch() {
            return this.i;
        }

        public final boolean isLogSwitch() {
            return this.e;
        }

        public final boolean isSingleTagSwitch() {
            return this.l;
        }

        public final Config setBorderSwitch(boolean z) {
            this.k = z;
            return this;
        }

        public final Config setConsoleFilter(int i) {
            this.m = i;
            return this;
        }

        public final Config setConsoleSwitch(boolean z) {
            this.f = z;
            return this;
        }

        public final Config setDir(String str) {
            if (!com.blankj.utilcode.util.b.C0(str)) {
                if (!str.endsWith(LogUtils.b)) {
                    str = str + LogUtils.b;
                }
                this.b = str;
            } else {
                this.b = null;
            }
            return this;
        }

        public final Config setFileExtension(String str) {
            if (com.blankj.utilcode.util.b.C0(str)) {
                this.d = ".txt";
            } else if (str.startsWith(".")) {
                this.d = str;
            } else {
                this.d = "." + str;
            }
            return this;
        }

        public final Config setFileFilter(int i) {
            this.n = i;
            return this;
        }

        public final Config setFilePrefix(String str) {
            if (com.blankj.utilcode.util.b.C0(str)) {
                this.c = "util";
            } else {
                this.c = str;
            }
            return this;
        }

        public final Config setFileWriter(IFileWriter iFileWriter) {
            this.s = iFileWriter;
            return this;
        }

        public final Config setGlobalTag(String str) {
            if (com.blankj.utilcode.util.b.C0(str)) {
                this.g = "";
                this.h = true;
            } else {
                this.g = str;
                this.h = false;
            }
            return this;
        }

        public final Config setLog2FileSwitch(boolean z) {
            this.j = z;
            return this;
        }

        public final Config setLogHeadSwitch(boolean z) {
            this.i = z;
            return this;
        }

        public final Config setLogSwitch(boolean z) {
            this.e = z;
            return this;
        }

        public final Config setOnConsoleOutputListener(OnConsoleOutputListener onConsoleOutputListener) {
            this.t = onConsoleOutputListener;
            return this;
        }

        public final Config setOnFileOutputListener(OnFileOutputListener onFileOutputListener) {
            this.u = onFileOutputListener;
            return this;
        }

        public final Config setSaveDays(@IntRange(from = 1) int i) {
            this.q = i;
            return this;
        }

        public final Config setSingleTagSwitch(boolean z) {
            this.l = z;
            return this;
        }

        public final Config setStackDeep(@IntRange(from = 1) int i) {
            this.o = i;
            return this;
        }

        public final Config setStackOffset(@IntRange(from = 0) int i) {
            this.p = i;
            return this;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("process: ");
            sb.append(getProcessName());
            sb.append(LogUtils.c);
            sb.append("logSwitch: ");
            sb.append(isLogSwitch());
            sb.append(LogUtils.c);
            sb.append("consoleSwitch: ");
            sb.append(isLog2ConsoleSwitch());
            sb.append(LogUtils.c);
            sb.append("tag: ");
            sb.append(getGlobalTag().equals("") ? "null" : getGlobalTag());
            sb.append(LogUtils.c);
            sb.append("headSwitch: ");
            sb.append(isLogHeadSwitch());
            sb.append(LogUtils.c);
            sb.append("fileSwitch: ");
            sb.append(isLog2FileSwitch());
            sb.append(LogUtils.c);
            sb.append("dir: ");
            sb.append(getDir());
            sb.append(LogUtils.c);
            sb.append("filePrefix: ");
            sb.append(getFilePrefix());
            sb.append(LogUtils.c);
            sb.append("borderSwitch: ");
            sb.append(isLogBorderSwitch());
            sb.append(LogUtils.c);
            sb.append("singleTagSwitch: ");
            sb.append(isSingleTagSwitch());
            sb.append(LogUtils.c);
            sb.append("consoleFilter: ");
            sb.append(getConsoleFilter());
            sb.append(LogUtils.c);
            sb.append("fileFilter: ");
            sb.append(getFileFilter());
            sb.append(LogUtils.c);
            sb.append("stackDeep: ");
            sb.append(getStackDeep());
            sb.append(LogUtils.c);
            sb.append("stackOffset: ");
            sb.append(getStackOffset());
            sb.append(LogUtils.c);
            sb.append("saveDays: ");
            sb.append(getSaveDays());
            sb.append(LogUtils.c);
            sb.append("formatter: ");
            sb.append(LogUtils.g);
            sb.append(LogUtils.c);
            sb.append("fileWriter: ");
            sb.append(this.s);
            sb.append(LogUtils.c);
            sb.append("onConsoleOutputListener: ");
            sb.append(this.t);
            sb.append(LogUtils.c);
            sb.append("onFileOutputListener: ");
            sb.append(this.u);
            sb.append(LogUtils.c);
            sb.append("fileExtraHeader: ");
            sb.append(this.v.f());
            return sb.toString();
        }

        public Config() {
            this.c = "util";
            this.d = ".txt";
            this.e = true;
            this.f = true;
            this.g = "";
            this.h = true;
            this.i = true;
            this.j = false;
            this.k = true;
            this.l = true;
            this.m = 2;
            this.n = 2;
            this.o = 1;
            this.p = 0;
            this.q = -1;
            this.r = com.blankj.utilcode.util.b.N();
            this.v = new b.a("Log");
            if (com.blankj.utilcode.util.b.z0() && Utils.getApp().getExternalFilesDir(null) != null) {
                this.f2262a = Utils.getApp().getExternalFilesDir(null) + LogUtils.b + "log" + LogUtils.b;
                return;
            }
            this.f2262a = Utils.getApp().getFilesDir() + LogUtils.b + "log" + LogUtils.b;
        }

        public final Config addFileExtraHead(String str, String str2) {
            this.v.b(str, str2);
            return this;
        }

        public final Config setDir(File file) {
            String str;
            if (file == null) {
                str = null;
            } else {
                str = file.getAbsolutePath() + LogUtils.b;
            }
            this.b = str;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public interface IFileWriter {
        void write(String str, String str2);
    }

    /* loaded from: classes.dex */
    public static abstract class IFormatter<T> {
        public abstract String format(T t);
    }

    /* loaded from: classes.dex */
    public interface OnConsoleOutputListener {
        void onConsoleOutput(int i, String str, String str2);
    }

    /* loaded from: classes.dex */
    public interface OnFileOutputListener {
        void onFileOutput(String str, String str2);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface TYPE {
    }

    /* loaded from: classes.dex */
    public static class a implements Runnable {
        public final /* synthetic */ int h;
        public final /* synthetic */ f i;
        public final /* synthetic */ String j;

        public a(int i, f fVar, String str) {
            this.h = i;
            this.i = fVar;
            this.j = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i = this.h;
            String str = this.i.f2263a;
            LogUtils.x(i, str, this.i.c + this.j);
        }
    }

    /* loaded from: classes.dex */
    public static class b implements FilenameFilter {
        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return LogUtils.u(str);
        }
    }

    /* loaded from: classes.dex */
    public static class c implements FilenameFilter {
        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return LogUtils.u(str);
        }
    }

    /* loaded from: classes.dex */
    public static class d implements Runnable {
        public final /* synthetic */ File h;

        public d(File file) {
            this.h = file;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.h.delete()) {
                return;
            }
            Log.e("LogUtils", "delete " + this.h + " failed!");
        }
    }

    /* loaded from: classes.dex */
    public static final class e {
        public static String a(Object obj) {
            if (obj instanceof Object[]) {
                return Arrays.deepToString((Object[]) obj);
            }
            if (obj instanceof boolean[]) {
                return Arrays.toString((boolean[]) obj);
            }
            if (obj instanceof byte[]) {
                return Arrays.toString((byte[]) obj);
            }
            if (obj instanceof char[]) {
                return Arrays.toString((char[]) obj);
            }
            if (obj instanceof double[]) {
                return Arrays.toString((double[]) obj);
            }
            if (obj instanceof float[]) {
                return Arrays.toString((float[]) obj);
            }
            if (obj instanceof int[]) {
                return Arrays.toString((int[]) obj);
            }
            if (obj instanceof long[]) {
                return Arrays.toString((long[]) obj);
            }
            if (obj instanceof short[]) {
                return Arrays.toString((short[]) obj);
            }
            throw new IllegalArgumentException("Array has incompatible type: " + obj.getClass());
        }

        public static String b(Bundle bundle) {
            Iterator<String> it = bundle.keySet().iterator();
            if (!it.hasNext()) {
                return "Bundle {}";
            }
            StringBuilder sb = new StringBuilder(128);
            sb.append("Bundle { ");
            while (true) {
                String next = it.next();
                Object obj = bundle.get(next);
                sb.append(next);
                sb.append('=');
                if (!(obj instanceof Bundle)) {
                    sb.append(LogUtils.n(obj));
                } else {
                    sb.append(obj == bundle ? "(this Bundle)" : b((Bundle) obj));
                }
                if (!it.hasNext()) {
                    sb.append(" }");
                    return sb.toString();
                }
                sb.append(',');
                sb.append(' ');
            }
        }

        @RequiresApi(api = 16)
        public static void c(ClipData clipData, StringBuilder sb) {
            ClipData.Item itemAt = clipData.getItemAt(0);
            if (itemAt == null) {
                sb.append("ClipData.Item {}");
                return;
            }
            sb.append("ClipData.Item { ");
            String htmlText = itemAt.getHtmlText();
            if (htmlText != null) {
                sb.append("H:");
                sb.append(htmlText);
                sb.append("}");
                return;
            }
            CharSequence text = itemAt.getText();
            if (text != null) {
                sb.append("T:");
                sb.append(text);
                sb.append("}");
                return;
            }
            Uri uri = itemAt.getUri();
            if (uri != null) {
                sb.append("U:");
                sb.append(uri);
                sb.append("}");
                return;
            }
            Intent intent = itemAt.getIntent();
            if (intent != null) {
                sb.append("I:");
                sb.append(e(intent));
                sb.append("}");
                return;
            }
            sb.append("NULL");
            sb.append("}");
        }

        public static String d(String str) {
            try {
                StreamSource streamSource = new StreamSource(new StringReader(str));
                StreamResult streamResult = new StreamResult(new StringWriter());
                Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
                newTransformer.setOutputProperty("indent", "yes");
                newTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                newTransformer.transform(streamSource, streamResult);
                String obj = streamResult.getWriter().toString();
                return obj.replaceFirst(">", ">" + LogUtils.c);
            } catch (Exception e) {
                e.printStackTrace();
                return str;
            }
        }

        public static String e(Intent intent) {
            boolean z;
            Intent selector;
            ClipData clipData;
            StringBuilder sb = new StringBuilder(128);
            sb.append("Intent { ");
            String action = intent.getAction();
            boolean z2 = true;
            boolean z3 = false;
            if (action != null) {
                sb.append("act=");
                sb.append(action);
                z = false;
            } else {
                z = true;
            }
            Set<String> categories = intent.getCategories();
            if (categories != null) {
                if (!z) {
                    sb.append(' ');
                }
                sb.append("cat=[");
                for (String str : categories) {
                    if (!z2) {
                        sb.append(',');
                    }
                    sb.append(str);
                    z2 = false;
                }
                sb.append("]");
                z = false;
            }
            Uri data = intent.getData();
            if (data != null) {
                if (!z) {
                    sb.append(' ');
                }
                sb.append("dat=");
                sb.append(data);
                z = false;
            }
            String type = intent.getType();
            if (type != null) {
                if (!z) {
                    sb.append(' ');
                }
                sb.append("typ=");
                sb.append(type);
                z = false;
            }
            int flags = intent.getFlags();
            if (flags != 0) {
                if (!z) {
                    sb.append(' ');
                }
                sb.append("flg=0x");
                sb.append(Integer.toHexString(flags));
                z = false;
            }
            String str2 = intent.getPackage();
            if (str2 != null) {
                if (!z) {
                    sb.append(' ');
                }
                sb.append("pkg=");
                sb.append(str2);
                z = false;
            }
            ComponentName component = intent.getComponent();
            if (component != null) {
                if (!z) {
                    sb.append(' ');
                }
                sb.append("cmp=");
                sb.append(component.flattenToShortString());
                z = false;
            }
            Rect sourceBounds = intent.getSourceBounds();
            if (sourceBounds != null) {
                if (!z) {
                    sb.append(' ');
                }
                sb.append("bnds=");
                sb.append(sourceBounds.toShortString());
                z = false;
            }
            int i = Build.VERSION.SDK_INT;
            if (i >= 16 && (clipData = intent.getClipData()) != null) {
                if (!z) {
                    sb.append(' ');
                }
                c(clipData, sb);
                z = false;
            }
            Bundle extras = intent.getExtras();
            if (extras != null) {
                if (!z) {
                    sb.append(' ');
                }
                sb.append("extras={");
                sb.append(b(extras));
                sb.append('}');
            } else {
                z3 = z;
            }
            if (i >= 15 && (selector = intent.getSelector()) != null) {
                if (!z3) {
                    sb.append(' ');
                }
                sb.append("sel={");
                sb.append(selector == intent ? "(this Intent)" : e(selector));
                sb.append("}");
            }
            sb.append(" }");
            return sb.toString();
        }

        public static String f(Object obj) {
            if (obj instanceof CharSequence) {
                return com.blankj.utilcode.util.b.G(obj.toString());
            }
            try {
                return com.blankj.utilcode.util.b.U().toJson(obj);
            } catch (Throwable unused) {
                return obj.toString();
            }
        }

        public static String g(Object obj) {
            return h(obj, -1);
        }

        public static String h(Object obj, int i) {
            if (obj.getClass().isArray()) {
                return a(obj);
            }
            if (obj instanceof Throwable) {
                return com.blankj.utilcode.util.b.T((Throwable) obj);
            }
            if (obj instanceof Bundle) {
                return b((Bundle) obj);
            }
            if (obj instanceof Intent) {
                return e((Intent) obj);
            }
            if (i == 32) {
                return f(obj);
            }
            if (i == 48) {
                return d(obj.toString());
            }
            return obj.toString();
        }
    }

    /* loaded from: classes.dex */
    public static final class f {

        /* renamed from: a  reason: collision with root package name */
        public String f2263a;
        public String[] b;
        public String c;

        public f(String str, String[] strArr, String str2) {
            this.f2263a = str;
            this.b = strArr;
            this.c = str2;
        }
    }

    public LogUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void A(int i, String str, String[] strArr) {
        if (strArr != null) {
            for (String str2 : strArr) {
                if (d.isLogBorderSwitch()) {
                    str2 = "│ " + str2;
                }
                v(i, str, str2);
            }
            if (d.isLogBorderSwitch()) {
                v(i, str, "├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄");
            }
        }
    }

    public static void B(int i, String str, String str2) {
        int length = str2.length();
        int i2 = length / 1100;
        if (i2 <= 0) {
            D(i, str, str2);
            return;
        }
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            int i5 = i4 + 1100;
            D(i, str, str2.substring(i4, i5));
            i3++;
            i4 = i5;
        }
        if (i4 != length) {
            D(i, str, str2.substring(i4, length));
        }
    }

    public static void C(int i, String str, String str2) {
        int length = str2.length();
        Config config = d;
        int i2 = 1100;
        int i3 = config.isLogBorderSwitch() ? (length - 113) / 1100 : length / 1100;
        if (i3 > 0) {
            int i4 = 1;
            if (config.isLogBorderSwitch()) {
                v(i, str, str2.substring(0, 1100) + c + "└────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
                while (i4 < i3) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
                    String str3 = c;
                    sb.append(str3);
                    sb.append("┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
                    sb.append(str3);
                    sb.append("│ ");
                    int i5 = i2 + 1100;
                    sb.append(str2.substring(i2, i5));
                    sb.append(str3);
                    sb.append("└────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
                    v(i, str, sb.toString());
                    i4++;
                    i2 = i5;
                }
                if (i2 != length - 113) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(HexStringBuilder.DEFAULT_SEPARATOR);
                    String str4 = c;
                    sb2.append(str4);
                    sb2.append("┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
                    sb2.append(str4);
                    sb2.append("│ ");
                    sb2.append(str2.substring(i2, length));
                    v(i, str, sb2.toString());
                    return;
                }
                return;
            }
            v(i, str, str2.substring(0, 1100));
            while (i4 < i3) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(HexStringBuilder.DEFAULT_SEPARATOR);
                sb3.append(c);
                int i6 = i2 + 1100;
                sb3.append(str2.substring(i2, i6));
                v(i, str, sb3.toString());
                i4++;
                i2 = i6;
            }
            if (i2 != length) {
                v(i, str, HexStringBuilder.DEFAULT_SEPARATOR + c + str2.substring(i2, length));
                return;
            }
            return;
        }
        v(i, str, str2);
    }

    public static void D(int i, String str, String str2) {
        String[] split;
        if (!d.isLogBorderSwitch()) {
            v(i, str, str2);
            return;
        }
        for (String str3 : str2.split(c)) {
            v(i, str, "│ " + str3);
        }
    }

    public static String E(int i, Object... objArr) {
        String str;
        if (objArr != null) {
            if (objArr.length == 1) {
                str = m(i, objArr[0]);
            } else {
                StringBuilder sb = new StringBuilder();
                int length = objArr.length;
                for (int i2 = 0; i2 < length; i2++) {
                    Object obj = objArr[i2];
                    sb.append("args");
                    sb.append("[");
                    sb.append(i2);
                    sb.append("]");
                    sb.append(" = ");
                    sb.append(n(obj));
                    sb.append(c);
                }
                str = sb.toString();
            }
        } else {
            str = "null";
        }
        return str.length() == 0 ? "log nothing" : str;
    }

    public static String F(int i, String str, String[] strArr, String str2) {
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        if (d.isLogBorderSwitch()) {
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            String str3 = c;
            sb.append(str3);
            sb.append("┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
            sb.append(str3);
            if (strArr != null) {
                for (String str4 : strArr) {
                    sb.append("│ ");
                    sb.append(str4);
                    sb.append(c);
                }
                sb.append("├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄");
                sb.append(c);
            }
            String[] split = str2.split(c);
            int length = split.length;
            while (i2 < length) {
                String str5 = split[i2];
                sb.append("│ ");
                sb.append(str5);
                sb.append(c);
                i2++;
            }
            sb.append("└────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        } else {
            if (strArr != null) {
                sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
                sb.append(c);
                int length2 = strArr.length;
                while (i2 < length2) {
                    sb.append(strArr[i2]);
                    sb.append(c);
                    i2++;
                }
            }
            sb.append(str2);
        }
        return sb.toString();
    }

    public static f G(String str) {
        String str2;
        String str3;
        String name;
        String str4;
        Config config = d;
        if (!config.h && !config.isLogHeadSwitch()) {
            str3 = config.getGlobalTag();
        } else {
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            int stackOffset = config.getStackOffset() + 3;
            if (stackOffset >= stackTrace.length) {
                String q = q(stackTrace[3]);
                if (config.h && com.blankj.utilcode.util.b.C0(str)) {
                    int indexOf = q.indexOf(46);
                    str4 = indexOf == -1 ? q : q.substring(0, indexOf);
                } else {
                    str4 = str;
                }
                return new f(str4, null, ": ");
            }
            StackTraceElement stackTraceElement = stackTrace[stackOffset];
            String q2 = q(stackTraceElement);
            if (config.h && com.blankj.utilcode.util.b.C0(str)) {
                int indexOf2 = q2.indexOf(46);
                str2 = indexOf2 == -1 ? q2 : q2.substring(0, indexOf2);
            } else {
                str2 = str;
            }
            if (config.isLogHeadSwitch()) {
                String formatter = new Formatter().format("%s, %s.%s(%s:%d)", Thread.currentThread().getName(), stackTraceElement.getClassName(), stackTraceElement.getMethodName(), q2, Integer.valueOf(stackTraceElement.getLineNumber())).toString();
                String str5 = " [" + formatter + "]: ";
                if (config.getStackDeep() <= 1) {
                    return new f(str2, new String[]{formatter}, str5);
                }
                int min = Math.min(config.getStackDeep(), stackTrace.length - stackOffset);
                String[] strArr = new String[min];
                strArr[0] = formatter;
                String formatter2 = new Formatter().format("%" + (name.length() + 2) + "s", "").toString();
                for (int i = 1; i < min; i++) {
                    StackTraceElement stackTraceElement2 = stackTrace[i + stackOffset];
                    strArr[i] = new Formatter().format("%s%s.%s(%s:%d)", formatter2, stackTraceElement2.getClassName(), stackTraceElement2.getMethodName(), q(stackTraceElement2), Integer.valueOf(stackTraceElement2.getLineNumber())).toString();
                }
                return new f(str2, strArr, str5);
            }
            str3 = str2;
        }
        return new f(str3, null, ": ");
    }

    public static void a(Object... objArr) {
        log(7, d.getGlobalTag(), objArr);
    }

    public static void aTag(String str, Object... objArr) {
        log(7, str, objArr);
    }

    public static void dTag(String str, Object... objArr) {
        log(3, str, objArr);
    }

    public static void eTag(String str, Object... objArr) {
        log(6, str, objArr);
    }

    public static void file(Object obj) {
        log(19, d.getGlobalTag(), obj);
    }

    public static Config getConfig() {
        return d;
    }

    public static String getCurrentLogFilePath() {
        return p(new Date());
    }

    public static List<File> getLogFiles() {
        File file = new File(d.getDir());
        if (file.exists()) {
            File[] listFiles = file.listFiles(new b());
            ArrayList arrayList = new ArrayList();
            Collections.addAll(arrayList, listFiles);
            return arrayList;
        }
        return new ArrayList();
    }

    public static void iTag(String str, Object... objArr) {
        log(4, str, objArr);
    }

    public static boolean j(String str, String str2) {
        File file = new File(str);
        if (file.exists()) {
            return file.isFile();
        }
        if (com.blankj.utilcode.util.b.s(file.getParentFile())) {
            try {
                k(str, str2);
                boolean createNewFile = file.createNewFile();
                if (createNewFile) {
                    z(str, str2);
                }
                return createNewFile;
            } catch (IOException e2) {
                e2.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static void json(Object obj) {
        log(35, d.getGlobalTag(), obj);
    }

    public static void k(String str, String str2) {
        Config config;
        File[] listFiles;
        if (d.getSaveDays() > 0 && (listFiles = new File(str).getParentFile().listFiles(new c())) != null && listFiles.length > 0) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd", Locale.getDefault());
            try {
                long time = simpleDateFormat.parse(str2).getTime() - (config.getSaveDays() * 86400000);
                for (File file : listFiles) {
                    String name = file.getName();
                    name.length();
                    if (simpleDateFormat.parse(l(name)).getTime() <= time) {
                        f.execute(new d(file));
                    }
                }
            } catch (ParseException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static String l(String str) {
        Matcher matcher = Pattern.compile("[0-9]{4}_[0-9]{2}_[0-9]{2}").matcher(str);
        return matcher.find() ? matcher.group() : "";
    }

    public static void log(int i, String str, Object... objArr) {
        Config config = d;
        if (config.isLogSwitch()) {
            int i2 = i & 15;
            int i3 = i & 240;
            if (config.isLog2ConsoleSwitch() || config.isLog2FileSwitch() || i3 == 16) {
                if (i2 >= config.m || i2 >= config.n) {
                    f G = G(str);
                    String E2 = E(i3, objArr);
                    if (config.isLog2ConsoleSwitch() && i3 != 16 && i2 >= config.m) {
                        w(i2, G.f2263a, G.b, E2);
                    }
                    if ((config.isLog2FileSwitch() || i3 == 16) && i2 >= config.n) {
                        f.execute(new a(i2, G, E2));
                    }
                }
            }
        }
    }

    public static String m(int i, Object obj) {
        if (obj == null) {
            return "null";
        }
        if (i == 32) {
            return e.h(obj, 32);
        }
        if (i == 48) {
            return e.h(obj, 48);
        }
        return n(obj);
    }

    public static String n(Object obj) {
        IFormatter iFormatter;
        if (obj == null) {
            return "null";
        }
        SimpleArrayMap<Class, IFormatter> simpleArrayMap = g;
        if (!simpleArrayMap.isEmpty() && (iFormatter = simpleArrayMap.get(o(obj))) != null) {
            return iFormatter.format(obj);
        }
        return e.g(obj);
    }

    public static Class o(Object obj) {
        String obj2;
        Class<?> cls = obj.getClass();
        if (cls.isAnonymousClass() || cls.isSynthetic()) {
            Type[] genericInterfaces = cls.getGenericInterfaces();
            if (genericInterfaces.length == 1) {
                Type type = genericInterfaces[0];
                while (type instanceof ParameterizedType) {
                    type = ((ParameterizedType) type).getRawType();
                }
                obj2 = type.toString();
            } else {
                Type genericSuperclass = cls.getGenericSuperclass();
                while (genericSuperclass instanceof ParameterizedType) {
                    genericSuperclass = ((ParameterizedType) genericSuperclass).getRawType();
                }
                obj2 = genericSuperclass.toString();
            }
            if (obj2.startsWith("class ")) {
                obj2 = obj2.substring(6);
            } else if (obj2.startsWith("interface ")) {
                obj2 = obj2.substring(10);
            }
            try {
                return Class.forName(obj2);
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
            }
        }
        return cls;
    }

    public static String p(Date date) {
        String substring = r().format(date).substring(0, 10);
        StringBuilder sb = new StringBuilder();
        Config config = d;
        sb.append(config.getDir());
        sb.append(config.getFilePrefix());
        sb.append("_");
        sb.append(substring);
        sb.append("_");
        sb.append(config.getProcessName());
        sb.append(config.getFileExtension());
        return sb.toString();
    }

    public static String q(StackTraceElement stackTraceElement) {
        String fileName = stackTraceElement.getFileName();
        if (fileName != null) {
            return fileName;
        }
        String className = stackTraceElement.getClassName();
        String[] split = className.split("\\.");
        if (split.length > 0) {
            className = split[split.length - 1];
        }
        int indexOf = className.indexOf(36);
        if (indexOf != -1) {
            className = className.substring(0, indexOf);
        }
        return className + ".java";
    }

    public static SimpleDateFormat r() {
        if (e == null) {
            e = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss.SSS ", Locale.getDefault());
        }
        return e;
    }

    public static <T> Class s(IFormatter<T> iFormatter) {
        Type genericSuperclass;
        Type[] genericInterfaces = iFormatter.getClass().getGenericInterfaces();
        if (genericInterfaces.length == 1) {
            genericSuperclass = genericInterfaces[0];
        } else {
            genericSuperclass = iFormatter.getClass().getGenericSuperclass();
        }
        Type type = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
        while (type instanceof ParameterizedType) {
            type = ((ParameterizedType) type).getRawType();
        }
        String obj = type.toString();
        if (obj.startsWith("class ")) {
            obj = obj.substring(6);
        } else if (obj.startsWith("interface ")) {
            obj = obj.substring(10);
        }
        try {
            return Class.forName(obj);
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void t(String str, String str2) {
        Config config = d;
        if (config.s != null) {
            config.s.write(str, str2);
        } else {
            com.blankj.utilcode.util.b.i1(str, str2, true);
        }
        if (config.u != null) {
            config.u.onFileOutput(str, str2);
        }
    }

    public static boolean u(String str) {
        return str.matches("^" + d.getFilePrefix() + "_[0-9]{4}_[0-9]{2}_[0-9]{2}_.*$");
    }

    public static void v(Object... objArr) {
        log(2, d.getGlobalTag(), objArr);
    }

    public static void vTag(String str, Object... objArr) {
        log(2, str, objArr);
    }

    public static void w(Object... objArr) {
        log(5, d.getGlobalTag(), objArr);
    }

    public static void wTag(String str, Object... objArr) {
        log(5, str, objArr);
    }

    public static void x(int i, String str, String str2) {
        Date date = new Date();
        String format = r().format(date);
        String substring = format.substring(0, 10);
        String p = p(date);
        if (!j(p, substring)) {
            Log.e("LogUtils", "create " + p + " failed!");
            return;
        }
        String substring2 = format.substring(11);
        t(p, substring2 + f2261a[i - 2] + MqttTopic.TOPIC_LEVEL_SEPARATOR + str + str2 + c);
    }

    public static void xml(String str) {
        log(51, d.getGlobalTag(), str);
    }

    public static void y(int i, String str, boolean z) {
        if (d.isLogBorderSwitch()) {
            v(i, str, z ? "┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────" : "└────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        }
    }

    public static void z(String str, String str2) {
        Config config = d;
        config.v.a("Date of Log", str2);
        t(str, config.v.toString());
    }

    public static void d(Object... objArr) {
        log(3, d.getGlobalTag(), objArr);
    }

    public static void e(Object... objArr) {
        log(6, d.getGlobalTag(), objArr);
    }

    public static void file(int i, Object obj) {
        log(i | 16, d.getGlobalTag(), obj);
    }

    public static void i(Object... objArr) {
        log(4, d.getGlobalTag(), objArr);
    }

    public static void json(int i, Object obj) {
        log(i | 32, d.getGlobalTag(), obj);
    }

    public static void v(int i, String str, String str2) {
        Log.println(i, str, str2);
        Config config = d;
        if (config.t != null) {
            config.t.onConsoleOutput(i, str, str2);
        }
    }

    public static void w(int i, String str, String[] strArr, String str2) {
        if (d.isSingleTagSwitch()) {
            C(i, str, F(i, str, strArr, str2));
            return;
        }
        y(i, str, true);
        A(i, str, strArr);
        B(i, str, str2);
        y(i, str, false);
    }

    public static void xml(int i, String str) {
        log(i | 48, d.getGlobalTag(), str);
    }

    public static void file(String str, Object obj) {
        log(19, str, obj);
    }

    public static void json(String str, Object obj) {
        log(35, str, obj);
    }

    public static void xml(String str, String str2) {
        log(51, str, str2);
    }

    public static void file(int i, String str, Object obj) {
        log(i | 16, str, obj);
    }

    public static void json(int i, String str, Object obj) {
        log(i | 32, str, obj);
    }

    public static void xml(int i, String str, String str2) {
        log(i | 48, str, str2);
    }
}
