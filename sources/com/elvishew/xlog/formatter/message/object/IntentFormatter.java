package com.elvishew.xlog.formatter.message.object;

import android.content.Intent;
import com.elvishew.xlog.internal.util.ObjectToStringUtil;
/* loaded from: classes9.dex */
public class IntentFormatter implements ObjectFormatter<Intent> {
    @Override // com.elvishew.xlog.formatter.Formatter
    public String format(Intent intent) {
        return ObjectToStringUtil.intentToString(intent);
    }
}
