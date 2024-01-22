package com.blankj.utilcode.util;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes.dex */
public final class ApiUtils {

    /* renamed from: a  reason: collision with root package name */
    public Map<Class, BaseApi> f2231a;
    public Map<Class, Class> b;

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes.dex */
    public @interface Api {
        boolean isMock() default false;
    }

    /* loaded from: classes.dex */
    public static class BaseApi {
    }

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final ApiUtils f2232a = new ApiUtils();
    }

    public static ApiUtils b() {
        return b.f2232a;
    }

    @Nullable
    public static <T extends BaseApi> T getApi(@NonNull Class<T> cls) {
        Objects.requireNonNull(cls, "Argument 'apiClass' of type Class<T> (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return (T) b().a(cls);
    }

    public static void register(@Nullable Class<? extends BaseApi> cls) {
        if (cls == null) {
            return;
        }
        b().d(cls);
    }

    @NonNull
    public static String toString_() {
        String apiUtils = b().toString();
        Objects.requireNonNull(apiUtils, "Detected an attempt to return null from a method com.blankj.utilcode.util.ApiUtils.toString_() marked by @androidx.annotation.NonNull");
        return apiUtils;
    }

    public final <Result> Result a(Class cls) {
        Result result = (Result) this.f2231a.get(cls);
        if (result != null) {
            return result;
        }
        synchronized (cls) {
            Result result2 = (Result) this.f2231a.get(cls);
            if (result2 != null) {
                return result2;
            }
            Class cls2 = this.b.get(cls);
            if (cls2 != null) {
                try {
                    Result result3 = (Result) ((BaseApi) cls2.newInstance());
                    this.f2231a.put(cls, result3);
                    return result3;
                } catch (Exception unused) {
                    Log.e("ApiUtils", "The <" + cls2 + "> has no parameterless constructor.");
                    return null;
                }
            }
            Log.e("ApiUtils", "The <" + cls + "> doesn't implement.");
            return null;
        }
    }

    public final void c() {
    }

    public final void d(Class cls) {
        this.b.put(cls.getSuperclass(), cls);
    }

    public String toString() {
        return "ApiUtils: " + this.b;
    }

    public ApiUtils() {
        this.f2231a = new ConcurrentHashMap();
        this.b = new HashMap();
        c();
    }
}
