package com.clevertap.android.sdk.task;

import com.clevertap.android.sdk.CleverTapInstanceConfig;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
public class CTExecutorFactory {

    /* renamed from: a  reason: collision with root package name */
    public static final Map<String, CTExecutors> f2682a = Collections.synchronizedMap(new HashMap());

    public static CTExecutors executors(CleverTapInstanceConfig cleverTapInstanceConfig) {
        if (cleverTapInstanceConfig != null) {
            Map<String, CTExecutors> map = f2682a;
            CTExecutors cTExecutors = map.get(cleverTapInstanceConfig.getAccountId());
            if (cTExecutors == null) {
                synchronized (CTExecutorFactory.class) {
                    cTExecutors = map.get(cleverTapInstanceConfig.getAccountId());
                    if (cTExecutors == null) {
                        cTExecutors = new CTExecutors(cleverTapInstanceConfig);
                        map.put(cleverTapInstanceConfig.getAccountId(), cTExecutors);
                    }
                }
            }
            return cTExecutors;
        }
        throw new IllegalArgumentException("Can't create task for null config");
    }
}
