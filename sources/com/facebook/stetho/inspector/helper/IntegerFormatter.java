package com.facebook.stetho.inspector.helper;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.ViewDebug;
import androidx.annotation.Nullable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
/* loaded from: classes9.dex */
public class IntegerFormatter {
    private static IntegerFormatter cachedFormatter;

    /* loaded from: classes9.dex */
    public static class IntegerFormatterWithHex extends IntegerFormatter {
        private IntegerFormatterWithHex() {
            super();
        }

        @Override // com.facebook.stetho.inspector.helper.IntegerFormatter
        @TargetApi(21)
        public String format(Integer num, @Nullable ViewDebug.ExportedProperty exportedProperty) {
            if (exportedProperty != null && exportedProperty.formatToHexString()) {
                return HexStringBuilder.DEFAULT_PREFIX + Integer.toHexString(num.intValue());
            }
            return super.format(num, exportedProperty);
        }
    }

    public static IntegerFormatter getInstance() {
        if (cachedFormatter == null) {
            synchronized (IntegerFormatter.class) {
                if (cachedFormatter == null) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        cachedFormatter = new IntegerFormatterWithHex();
                    } else {
                        cachedFormatter = new IntegerFormatter();
                    }
                }
            }
        }
        return cachedFormatter;
    }

    public String format(Integer num, @Nullable ViewDebug.ExportedProperty exportedProperty) {
        return String.valueOf(num);
    }

    private IntegerFormatter() {
    }
}
