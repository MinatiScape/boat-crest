package com.elvishew.xlog.flattener;

import com.elvishew.xlog.LogLevel;
/* loaded from: classes9.dex */
public class DefaultFlattener implements Flattener, Flattener2 {
    @Override // com.elvishew.xlog.flattener.Flattener
    public CharSequence flatten(int i, String str, String str2) {
        return flatten(System.currentTimeMillis(), i, str, str2);
    }

    @Override // com.elvishew.xlog.flattener.Flattener2
    public CharSequence flatten(long j, int i, String str, String str2) {
        return Long.toString(j) + '|' + LogLevel.getShortLevelName(i) + '|' + str + '|' + str2;
    }
}
