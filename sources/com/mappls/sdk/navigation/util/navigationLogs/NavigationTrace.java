package com.mappls.sdk.navigation.util.navigationLogs;

import android.content.Context;
import android.os.Environment;
import androidx.annotation.Keep;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Date;
@Keep
/* loaded from: classes11.dex */
public class NavigationTrace {
    private static File file = null;
    private static File httpLogfile = null;
    private static boolean init = false;
    private static final Object mutex = new Object();

    public static void init(Context context) {
        synchronized (mutex) {
            if (!init) {
                file = new File(context.getExternalFilesDir(null), "navigation_log.txt");
                httpLogfile = new File(context.getExternalFilesDir(null), "http_log.txt");
                init = true;
            }
        }
    }

    public static void writeHTTPCalls(String str) {
        OutputStreamWriter outputStreamWriter;
        synchronized (mutex) {
            if (init) {
                if ("mounted".equals(Environment.getExternalStorageState())) {
                    OutputStreamWriter outputStreamWriter2 = null;
                    try {
                        try {
                            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(httpLogfile, true), Charset.defaultCharset());
                        } catch (Throwable th) {
                            th = th;
                        }
                        try {
                            outputStreamWriter.write(",\n" + str);
                            outputStreamWriter.close();
                        } catch (Throwable th2) {
                            th = th2;
                            outputStreamWriter2 = outputStreamWriter;
                            if (outputStreamWriter2 != null) {
                                outputStreamWriter2.close();
                            }
                            throw th;
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        }
    }

    public static void writeLine(String str) {
        OutputStreamWriter outputStreamWriter;
        synchronized (mutex) {
            if (init) {
                if ("mounted".equals(Environment.getExternalStorageState())) {
                    OutputStreamWriter outputStreamWriter2 = null;
                    try {
                        try {
                            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file, true), Charset.defaultCharset());
                        } catch (Throwable th) {
                            th = th;
                        }
                        try {
                            outputStreamWriter.write(",\n" + str);
                            outputStreamWriter.close();
                        } catch (Throwable th2) {
                            th = th2;
                            outputStreamWriter2 = outputStreamWriter;
                            if (outputStreamWriter2 != null) {
                                outputStreamWriter2.close();
                            }
                            throw th;
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        }
    }

    public static void writeLine(String str, Object... objArr) {
        synchronized (mutex) {
            if (init) {
                if ("mounted".equals(Environment.getExternalStorageState())) {
                    OutputStreamWriter outputStreamWriter = null;
                    try {
                        try {
                            OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(new FileOutputStream(file, true), Charset.defaultCharset());
                            try {
                                outputStreamWriter2.write(String.format("[" + new Date().toLocaleString() + "] " + str + "\r\n", objArr));
                                outputStreamWriter2.close();
                            } catch (Throwable th) {
                                th = th;
                                outputStreamWriter = outputStreamWriter2;
                                if (outputStreamWriter != null) {
                                    outputStreamWriter.close();
                                }
                                throw th;
                            }
                        } catch (IOException unused) {
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            }
        }
    }

    public static void writeLineNavigation(String str) {
    }
}
