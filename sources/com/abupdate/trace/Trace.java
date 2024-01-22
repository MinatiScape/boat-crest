package com.abupdate.trace;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.jstyle.blesdk1860.constant.BleConst;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class Trace {
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int NONE = 7;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;

    /* renamed from: a  reason: collision with root package name */
    public static int f1972a = 7;
    public static boolean b = false;
    public static boolean c = true;
    public static int d = 0;
    public static String e = Environment.getExternalStorageDirectory() + "/iport_log.txt";
    public static int f = 500;
    public static String g = "iport/";

    public static boolean a() {
        File file = new File(e);
        if (!file.exists()) {
            if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
                Log.e(g, g(new IOException("Can't create the directory of trace. Please check the trace path.")));
                return false;
            }
            try {
                file.createNewFile();
                return true;
            } catch (IOException e2) {
                Log.e("Trace", g(e2));
                return false;
            }
        } else if (file.length() > f * 1024) {
            file.renameTo(new File(e + "(1)"));
            return true;
        } else {
            return true;
        }
    }

    public static <T> void array(String str, T[] tArr) {
        d = 1;
        if (tArr == null) {
            e(str, "array is null");
        } else {
            d(str, Arrays.toString(tArr));
        }
    }

    public static String b(int i) {
        String valueOf = String.valueOf(i);
        int length = 5 - valueOf.length();
        if (length < 0) {
            valueOf = valueOf.substring(-length, valueOf.length());
        }
        while (length > 0) {
            valueOf = BleConst.GetDeviceTime + valueOf;
            length--;
        }
        return valueOf;
    }

    public static String c() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int i = d + 5;
        d = 0;
        return String.format(".(%s:%s) %s()", stackTrace[i].getFileName(), Integer.valueOf(stackTrace[i].getLineNumber()), stackTrace[i].getMethodName());
    }

    public static void d(String str, String str2) {
        j(3, str, str2);
    }

    public static void e(String str, String str2) {
        j(6, str, str2);
    }

    public static String f(String str, String str2) {
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
        return format + HexStringBuilder.DEFAULT_SEPARATOR + String.format("%s ", b((int) Thread.currentThread().getId())) + String.format("%s: ", str) + str2;
    }

    public static void file(String str, File file) {
        d = 1;
        if (file != null && file.exists()) {
            try {
                byte[] bArr = new byte[4096];
                new FileInputStream(file).read(bArr);
                d(str, String.format("file name:%s,file size:%s\n%s", file.getName(), Long.valueOf(file.length()), new String(bArr, Charset.defaultCharset())));
                return;
            } catch (Exception unused) {
                e(str, "Invalid Xml");
                return;
            }
        }
        e(str, "Empty/Null file");
    }

    public static String g(Throwable th) {
        if (th == null) {
            return "";
        }
        for (Throwable th2 = th; th2 != null; th2 = th2.getCause()) {
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.flush();
        return stringWriter.toString();
    }

    public static void h(int i, String str, String str2) {
        if (i == 2) {
            Log.v(str, str2);
        } else if (i == 3) {
            Log.d(str, str2);
        } else if (i == 4) {
            Log.i(str, str2);
        } else if (i == 5) {
            Log.w(str, str2);
        } else if (i != 6) {
        } else {
            Log.e(str, str2);
        }
    }

    public static void i(String str, String str2) {
        j(4, str, str2);
    }

    public static void j(int i, String str, String str2) {
        if (i >= f1972a) {
            if (c) {
                k(str, str2);
            }
            if (b) {
                str2 = str2 + c();
            }
            h(i, g + str, str2);
        }
    }

    public static void json(String str, String str2) {
        d = 1;
        if (TextUtils.isEmpty(str2)) {
            e(str, "Empty/Null json content");
            return;
        }
        try {
            String trim = str2.trim();
            if (trim.startsWith("{")) {
                d(str, new JSONObject(trim).toString(2));
            } else if (trim.startsWith("[")) {
                d(str, new JSONArray(trim).toString(2));
            } else {
                e(str, "Invalid Json");
            }
        } catch (JSONException unused) {
            e(str, "Invalid Json");
        }
    }

    public static void k(String str, String str2) {
        FileOutputStream fileOutputStream;
        File file = new File(e);
        if (a()) {
            String f2 = f(str, str2);
            FileOutputStream fileOutputStream2 = null;
            try {
                try {
                    try {
                        fileOutputStream = new FileOutputStream(file, file.length() <= ((long) (f * 1024)));
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return;
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
                fileOutputStream.write(f2.getBytes());
                fileOutputStream.write("\n".getBytes());
                fileOutputStream.close();
            } catch (Exception e4) {
                e = e4;
                fileOutputStream2 = fileOutputStream;
                e.printStackTrace();
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream2 = fileOutputStream;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (Exception e5) {
                        e5.printStackTrace();
                    }
                }
                throw th;
            }
        }
    }

    public static void list(String str, List<?> list) {
        d = 1;
        if (list == null) {
            e(str, "lists is null");
            return;
        }
        int size = list.size() - 1;
        if (size == -1) {
            d(str, "{}");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (int i = 0; i < list.size(); i++) {
            sb.append(String.valueOf(list.get(i)));
            if (i == size) {
                sb.append('}');
            }
            sb.append(", ");
        }
        d(str, sb.toString());
    }

    public static void setLevel(int i) {
        f1972a = i;
    }

    public static void setLog_path(String str) {
        e = str;
    }

    public static void setLog_size(int i) {
        f = i;
    }

    public static void setShowPosition(boolean z) {
        b = z;
    }

    public static void v(String str, String str2) {
        j(2, str, str2);
    }

    public static void w(String str, String str2, Object... objArr) {
        j(5, str, String.format(str2, objArr));
    }

    public static void write_file(boolean z) {
        c = z;
    }

    public static void xml(String str, String str2) {
        d = 1;
        if (TextUtils.isEmpty(str2)) {
            e(str, "Empty/Null xml content");
            return;
        }
        try {
            StreamSource streamSource = new StreamSource(new StringReader(str2));
            StreamResult streamResult = new StreamResult(new StringWriter());
            Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
            newTransformer.setOutputProperty("indent", "yes");
            newTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            newTransformer.transform(streamSource, streamResult);
            d(str, streamResult.getWriter().toString().replaceFirst(">", ">\n"));
        } catch (Exception unused) {
            e(str, "Invalid Xml");
        }
    }

    public static void d(String str, String str2, Object... objArr) {
        j(3, str, String.format(str2, objArr));
    }

    public static void e(String str, String str2, Throwable th) {
        j(6, str, str2 + '\n' + g(th));
    }

    public static void i(String str, String str2, Object... objArr) {
        j(4, str, String.format(str2, objArr));
    }

    public static void w(String str, String str2) {
        j(5, str, str2);
    }

    public static void e(String str, Throwable th) {
        j(6, str, g(th));
    }
}
