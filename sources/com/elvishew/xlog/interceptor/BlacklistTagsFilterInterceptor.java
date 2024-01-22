package com.elvishew.xlog.interceptor;

import com.elvishew.xlog.LogItem;
import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes9.dex */
public class BlacklistTagsFilterInterceptor extends AbstractFilterInterceptor {

    /* renamed from: a  reason: collision with root package name */
    public Iterable<String> f7871a;

    public BlacklistTagsFilterInterceptor(String... strArr) {
        this(Arrays.asList(strArr));
    }

    @Override // com.elvishew.xlog.interceptor.AbstractFilterInterceptor
    public boolean reject(LogItem logItem) {
        Iterable<String> iterable = this.f7871a;
        if (iterable != null) {
            for (String str : iterable) {
                if (logItem.tag.equals(str)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public BlacklistTagsFilterInterceptor(Iterable<String> iterable) {
        Objects.requireNonNull(iterable);
        this.f7871a = iterable;
    }
}
