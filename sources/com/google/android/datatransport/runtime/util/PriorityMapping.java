package com.google.android.datatransport.runtime.util;

import android.util.SparseArray;
import androidx.annotation.NonNull;
import com.google.android.datatransport.Priority;
import java.util.HashMap;
/* loaded from: classes6.dex */
public final class PriorityMapping {

    /* renamed from: a  reason: collision with root package name */
    public static SparseArray<Priority> f8166a = new SparseArray<>();
    public static HashMap<Priority, Integer> b;

    static {
        HashMap<Priority, Integer> hashMap = new HashMap<>();
        b = hashMap;
        hashMap.put(Priority.DEFAULT, 0);
        b.put(Priority.VERY_LOW, 1);
        b.put(Priority.HIGHEST, 2);
        for (Priority priority : b.keySet()) {
            f8166a.append(b.get(priority).intValue(), priority);
        }
    }

    public static int toInt(@NonNull Priority priority) {
        Integer num = b.get(priority);
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("PriorityMapping is missing known Priority value " + priority);
    }

    @NonNull
    public static Priority valueOf(int i) {
        Priority priority = f8166a.get(i);
        if (priority != null) {
            return priority;
        }
        throw new IllegalArgumentException("Unknown Priority for value " + i);
    }
}
