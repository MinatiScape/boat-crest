package com.elvishew.xlog.interceptor;

import com.elvishew.xlog.LogItem;
import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes9.dex */
public class WhitelistTagsFilterInterceptor extends AbstractFilterInterceptor {

    /* renamed from: a  reason: collision with root package name */
    public Iterable<String> f7872a;

    public WhitelistTagsFilterInterceptor(String... strArr) {
        this(Arrays.asList(strArr));
    }

    @Override // com.elvishew.xlog.interceptor.AbstractFilterInterceptor
    public boolean reject(LogItem logItem) {
        Iterable<String> iterable = this.f7872a;
        if (iterable != null) {
            for (String str : iterable) {
                if (logItem.tag.equals(str)) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    public WhitelistTagsFilterInterceptor(Iterable<String> iterable) {
        Objects.requireNonNull(iterable);
        this.f7872a = iterable;
    }
}
