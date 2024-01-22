package com.elvishew.xlog.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import com.elvishew.xlog.flattener.DefaultFlattener;
import com.elvishew.xlog.flattener.Flattener;
import com.elvishew.xlog.flattener.Flattener2;
import com.elvishew.xlog.formatter.border.BorderFormatter;
import com.elvishew.xlog.formatter.border.DefaultBorderFormatter;
import com.elvishew.xlog.formatter.message.json.DefaultJsonFormatter;
import com.elvishew.xlog.formatter.message.json.JsonFormatter;
import com.elvishew.xlog.formatter.message.object.ObjectFormatter;
import com.elvishew.xlog.formatter.message.throwable.DefaultThrowableFormatter;
import com.elvishew.xlog.formatter.message.throwable.ThrowableFormatter;
import com.elvishew.xlog.formatter.message.xml.DefaultXmlFormatter;
import com.elvishew.xlog.formatter.message.xml.XmlFormatter;
import com.elvishew.xlog.formatter.stacktrace.DefaultStackTraceFormatter;
import com.elvishew.xlog.formatter.stacktrace.StackTraceFormatter;
import com.elvishew.xlog.formatter.thread.DefaultThreadFormatter;
import com.elvishew.xlog.formatter.thread.ThreadFormatter;
import com.elvishew.xlog.internal.printer.file.backup.BackupStrategyWrapper;
import com.elvishew.xlog.printer.Printer;
import com.elvishew.xlog.printer.file.backup.BackupStrategy2;
import com.elvishew.xlog.printer.file.backup.FileSizeBackupStrategy;
import com.elvishew.xlog.printer.file.clean.CleanStrategy;
import com.elvishew.xlog.printer.file.clean.NeverCleanStrategy;
import com.elvishew.xlog.printer.file.naming.ChangelessFileNameGenerator;
import com.elvishew.xlog.printer.file.naming.FileNameGenerator;
import java.util.Map;
/* loaded from: classes9.dex */
public class DefaultsFactory {
    public static Map<Class<?>, ObjectFormatter<?>> builtinObjectFormatters() {
        return Platform.get().a();
    }

    public static BackupStrategy2 createBackupStrategy() {
        return new BackupStrategyWrapper(new FileSizeBackupStrategy(PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED));
    }

    public static BorderFormatter createBorderFormatter() {
        return new DefaultBorderFormatter();
    }

    public static CleanStrategy createCleanStrategy() {
        return new NeverCleanStrategy();
    }

    public static FileNameGenerator createFileNameGenerator() {
        return new ChangelessFileNameGenerator("log");
    }

    public static Flattener createFlattener() {
        return new DefaultFlattener();
    }

    public static Flattener2 createFlattener2() {
        return new DefaultFlattener();
    }

    public static JsonFormatter createJsonFormatter() {
        return new DefaultJsonFormatter();
    }

    public static Printer createPrinter() {
        return Platform.get().b();
    }

    public static StackTraceFormatter createStackTraceFormatter() {
        return new DefaultStackTraceFormatter();
    }

    public static ThreadFormatter createThreadFormatter() {
        return new DefaultThreadFormatter();
    }

    public static ThrowableFormatter createThrowableFormatter() {
        return new DefaultThrowableFormatter();
    }

    public static XmlFormatter createXmlFormatter() {
        return new DefaultXmlFormatter();
    }
}
