package okhttp3.internal.platform.android;

import java.util.logging.Level;
import java.util.logging.LogRecord;
/* loaded from: classes12.dex */
public final class AndroidLogKt {
    public static final int a(LogRecord logRecord) {
        if (logRecord.getLevel().intValue() > Level.INFO.intValue()) {
            return 5;
        }
        return logRecord.getLevel().intValue() == Level.INFO.intValue() ? 4 : 3;
    }
}
