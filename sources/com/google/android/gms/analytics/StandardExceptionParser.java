package com.google.android.gms.analytics;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;
@VisibleForTesting
/* loaded from: classes6.dex */
public class StandardExceptionParser implements ExceptionParser {

    /* renamed from: a  reason: collision with root package name */
    public final TreeSet<String> f8178a = new TreeSet<>();

    public StandardExceptionParser(@RecentlyNonNull Context context, @RecentlyNonNull Collection<String> collection) {
        setIncludedPackages(context, collection);
    }

    @RecentlyNullable
    public StackTraceElement getBestStackTraceElement(@RecentlyNonNull Throwable th) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        if (stackTrace == null || (r0 = stackTrace.length) == 0) {
            return null;
        }
        for (StackTraceElement stackTraceElement : stackTrace) {
            String className = stackTraceElement.getClassName();
            Iterator<String> it = this.f8178a.iterator();
            while (it.hasNext()) {
                if (className.startsWith(it.next())) {
                    return stackTraceElement;
                }
            }
        }
        return stackTrace[0];
    }

    @RecentlyNonNull
    public Throwable getCause(@RecentlyNonNull Throwable th) {
        while (th.getCause() != null) {
            th = th.getCause();
        }
        return th;
    }

    @Override // com.google.android.gms.analytics.ExceptionParser
    @RecentlyNonNull
    public String getDescription(@Nullable String str, @RecentlyNonNull Throwable th) {
        return getDescription(getCause(th), getBestStackTraceElement(getCause(th)), str);
    }

    public void setIncludedPackages(@RecentlyNonNull Context context, @RecentlyNonNull Collection<String> collection) {
        this.f8178a.clear();
        HashSet<String> hashSet = new HashSet();
        if (collection != null) {
            hashSet.addAll(collection);
        }
        if (context != null) {
            hashSet.add(context.getApplicationContext().getPackageName());
        }
        for (String str : hashSet) {
            Iterator<String> it = this.f8178a.iterator();
            boolean z = true;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                if (str.startsWith(next)) {
                    z = false;
                } else if (next.startsWith(str)) {
                    this.f8178a.remove(next);
                }
            }
            if (z) {
                this.f8178a.add(str);
            }
        }
    }

    @RecentlyNonNull
    public String getDescription(@RecentlyNonNull Throwable th, @Nullable StackTraceElement stackTraceElement, @Nullable String str) {
        int length;
        StringBuilder sb = new StringBuilder();
        sb.append(th.getClass().getSimpleName());
        if (stackTraceElement != null) {
            String[] split = stackTraceElement.getClassName().split("\\.");
            sb.append(String.format(" (@%s:%s:%s)", (split == null || (length = split.length) <= 0) ? "unknown" : split[length - 1], stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber())));
        }
        if (str != null) {
            sb.append(String.format(" {%s}", str));
        }
        return sb.toString();
    }
}
