package com.blankj.utilcode.util;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes.dex */
public final class BusUtils {
    public static final Object e = "nULl";

    /* renamed from: a  reason: collision with root package name */
    public final Map<String, List<b>> f2234a;
    public final Map<String, Set<Object>> b;
    public final Map<String, List<String>> c;
    public final Map<String, Map<String, Object>> d;

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes.dex */
    public @interface Bus {
        int priority() default 0;

        boolean sticky() default false;

        String tag();

        ThreadMode threadMode() default ThreadMode.POSTING;
    }

    /* loaded from: classes.dex */
    public enum ThreadMode {
        MAIN,
        IO,
        CPU,
        CACHED,
        SINGLE,
        POSTING
    }

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public final /* synthetic */ Object h;
        public final /* synthetic */ Object i;
        public final /* synthetic */ b j;
        public final /* synthetic */ boolean k;

        public a(Object obj, Object obj2, b bVar, boolean z) {
            this.h = obj;
            this.i = obj2;
            this.j = bVar;
            this.k = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            BusUtils.this.o(this.h, this.i, this.j, this.k);
        }
    }

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public String f2235a;
        public String b;
        public String c;
        public String d;
        public String e;
        public boolean f;
        public String g;
        public int h;
        public Method i;
        public List<String> j;

        public final String a() {
            String str;
            StringBuilder sb = new StringBuilder();
            sb.append(this.b);
            sb.append(MqttTopic.MULTI_LEVEL_WILDCARD);
            sb.append(this.c);
            if ("".equals(this.d)) {
                str = "()";
            } else {
                str = "(" + this.d + HexStringBuilder.DEFAULT_SEPARATOR + this.e + ")";
            }
            sb.append(str);
            return sb.toString();
        }

        public String toString() {
            return "BusInfo { tag : " + this.f2235a + ", desc: " + a() + ", sticky: " + this.f + ", threadMode: " + this.g + ", method: " + this.i + ", priority: " + this.h + " }";
        }
    }

    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public static final BusUtils f2236a = new BusUtils(null);
    }

    public /* synthetic */ BusUtils(a aVar) {
        this();
    }

    public static BusUtils e() {
        return c.f2236a;
    }

    public static void post(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'tag' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        post(str, e);
    }

    public static void postSticky(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'tag' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        postSticky(str, e);
    }

    public static void register(@Nullable Object obj) {
        e().q(obj);
    }

    public static void removeSticky(String str) {
        e().r(str);
    }

    public static String toString_() {
        return e().toString();
    }

    public static void unregister(@Nullable Object obj) {
        e().s(obj);
    }

    public final void b(Object obj, String str, Object obj2) {
        List<b> list = this.f2234a.get(str);
        if (list == null) {
            Log.e("BusUtils", "The bus of tag <" + str + "> is not exists.");
            return;
        }
        for (b bVar : list) {
            if (bVar.j.contains(obj.getClass().getName()) && bVar.f) {
                synchronized (this.d) {
                    Map<String, Object> map = this.d.get(bVar.b);
                    if (map != null && map.containsKey(str)) {
                        i(obj, obj2, bVar, true);
                    }
                }
            }
        }
    }

    public final void c(Object obj) {
        Map<String, Object> map = this.d.get(obj.getClass().getName());
        if (map == null) {
            return;
        }
        synchronized (this.d) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                b(obj, entry.getKey(), entry.getValue());
            }
        }
    }

    public final Class d(String str) throws ClassNotFoundException {
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -1325958191:
                if (str.equals("double")) {
                    c2 = 0;
                    break;
                }
                break;
            case 104431:
                if (str.equals("int")) {
                    c2 = 1;
                    break;
                }
                break;
            case 3039496:
                if (str.equals("byte")) {
                    c2 = 2;
                    break;
                }
                break;
            case 3052374:
                if (str.equals("char")) {
                    c2 = 3;
                    break;
                }
                break;
            case 3327612:
                if (str.equals("long")) {
                    c2 = 4;
                    break;
                }
                break;
            case 64711720:
                if (str.equals("boolean")) {
                    c2 = 5;
                    break;
                }
                break;
            case 97526364:
                if (str.equals(TypedValues.Custom.S_FLOAT)) {
                    c2 = 6;
                    break;
                }
                break;
            case 109413500:
                if (str.equals("short")) {
                    c2 = 7;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return Double.TYPE;
            case 1:
                return Integer.TYPE;
            case 2:
                return Byte.TYPE;
            case 3:
                return Character.TYPE;
            case 4:
                return Long.TYPE;
            case 5:
                return Boolean.TYPE;
            case 6:
                return Float.TYPE;
            case 7:
                return Short.TYPE;
            default:
                return Class.forName(str);
        }
    }

    public final Method f(b bVar) {
        try {
            return "".equals(bVar.d) ? Class.forName(bVar.b).getDeclaredMethod(bVar.c, new Class[0]) : Class.forName(bVar.b).getDeclaredMethod(bVar.c, d(bVar.d));
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public final void g() {
    }

    public final void h(Object obj, b bVar, boolean z) {
        i(null, obj, bVar, z);
    }

    public final void i(Object obj, Object obj2, b bVar, boolean z) {
        if (bVar.i == null) {
            Method f = f(bVar);
            if (f == null) {
                return;
            }
            bVar.i = f;
        }
        k(obj, obj2, bVar, z);
    }

    public final void j(Object obj, b bVar, Set<Object> set) {
        try {
            if (obj == e) {
                for (Object obj2 : set) {
                    bVar.i.invoke(obj2, new Object[0]);
                }
                return;
            }
            for (Object obj3 : set) {
                bVar.i.invoke(obj3, obj);
            }
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
    }

    public final void k(Object obj, Object obj2, b bVar, boolean z) {
        a aVar = new a(obj, obj2, bVar, z);
        String str = bVar.g;
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -1848936376:
                if (str.equals("SINGLE")) {
                    c2 = 0;
                    break;
                }
                break;
            case 2342:
                if (str.equals("IO")) {
                    c2 = 1;
                    break;
                }
                break;
            case 66952:
                if (str.equals("CPU")) {
                    c2 = 2;
                    break;
                }
                break;
            case 2358713:
                if (str.equals("MAIN")) {
                    c2 = 3;
                    break;
                }
                break;
            case 1980249378:
                if (str.equals("CACHED")) {
                    c2 = 4;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                ThreadUtils.getSinglePool().execute(aVar);
                return;
            case 1:
                ThreadUtils.getIoPool().execute(aVar);
                return;
            case 2:
                ThreadUtils.getCpuPool().execute(aVar);
                return;
            case 3:
                ThreadUtils.runOnUiThread(aVar);
                return;
            case 4:
                ThreadUtils.getCachedPool().execute(aVar);
                return;
            default:
                aVar.run();
                return;
        }
    }

    public final void l(String str, Object obj) {
        m(str, obj, false);
    }

    public final void m(String str, Object obj, boolean z) {
        List<b> list = this.f2234a.get(str);
        if (list == null) {
            Log.e("BusUtils", "The bus of tag <" + str + "> is not exists.");
            if (this.f2234a.isEmpty()) {
                Log.e("BusUtils", "Please check whether the bus plugin is applied.");
                return;
            }
            return;
        }
        for (b bVar : list) {
            h(obj, bVar, z);
        }
    }

    public final void n(String str, Object obj) {
        List<b> list = this.f2234a.get(str);
        if (list == null) {
            Log.e("BusUtils", "The bus of tag <" + str + "> is not exists.");
            return;
        }
        for (b bVar : list) {
            if (!bVar.f) {
                h(obj, bVar, false);
            } else {
                synchronized (this.d) {
                    Map<String, Object> map = this.d.get(bVar.b);
                    if (map == null) {
                        map = new ConcurrentHashMap<>();
                        this.d.put(bVar.b, map);
                    }
                    map.put(str, obj);
                }
                h(obj, bVar, true);
            }
        }
    }

    public final void o(Object obj, Object obj2, b bVar, boolean z) {
        Set<Object> hashSet = new HashSet<>();
        if (obj == null) {
            for (String str : bVar.j) {
                Set<Object> set = this.b.get(str);
                if (set != null && !set.isEmpty()) {
                    hashSet.addAll(set);
                }
            }
            if (hashSet.size() == 0) {
                if (z) {
                    return;
                }
                Log.e("BusUtils", "The " + bVar + " was not registered before.");
                return;
            }
        } else {
            hashSet.add(obj);
        }
        j(obj2, bVar, hashSet);
    }

    public final void p(Class<?> cls, String str) {
        if (this.c.get(str) == null) {
            synchronized (this.c) {
                if (this.c.get(str) == null) {
                    CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
                    for (Map.Entry<String, List<b>> entry : this.f2234a.entrySet()) {
                        for (b bVar : entry.getValue()) {
                            try {
                                if (Class.forName(bVar.b).isAssignableFrom(cls)) {
                                    copyOnWriteArrayList.add(entry.getKey());
                                    bVar.j.add(str);
                                }
                            } catch (ClassNotFoundException e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                    this.c.put(str, copyOnWriteArrayList);
                }
            }
        }
    }

    public final void q(@Nullable Object obj) {
        if (obj == null) {
            return;
        }
        Class<?> cls = obj.getClass();
        String name = cls.getName();
        boolean z = false;
        synchronized (this.b) {
            Set<Object> set = this.b.get(name);
            if (set == null) {
                set = new CopyOnWriteArraySet<>();
                this.b.put(name, set);
                z = true;
            }
            if (set.contains(obj)) {
                Log.w("BusUtils", "The bus of <" + obj + "> already registered.");
                return;
            }
            set.add(obj);
            if (z) {
                p(cls, name);
            }
            c(obj);
        }
    }

    public final void r(String str) {
        List<b> list = this.f2234a.get(str);
        if (list == null) {
            Log.e("BusUtils", "The bus of tag <" + str + "> is not exists.");
            return;
        }
        for (b bVar : list) {
            if (bVar.f) {
                synchronized (this.d) {
                    Map<String, Object> map = this.d.get(bVar.b);
                    if (map != null && map.containsKey(str)) {
                        map.remove(str);
                    }
                    return;
                }
            }
        }
    }

    public final void s(Object obj) {
        if (obj == null) {
            return;
        }
        String name = obj.getClass().getName();
        synchronized (this.b) {
            Set<Object> set = this.b.get(name);
            if (set != null && set.contains(obj)) {
                set.remove(obj);
                return;
            }
            Log.e("BusUtils", "The bus of <" + obj + "> was not registered before.");
        }
    }

    public String toString() {
        return "BusUtils: " + this.f2234a;
    }

    public BusUtils() {
        this.f2234a = new ConcurrentHashMap();
        this.b = new ConcurrentHashMap();
        this.c = new ConcurrentHashMap();
        this.d = new ConcurrentHashMap();
        g();
    }

    public static void post(@NonNull String str, @NonNull Object obj) {
        Objects.requireNonNull(str, "Argument 'tag' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(obj, "Argument 'arg' of type Object (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        e().l(str, obj);
    }

    public static void postSticky(@NonNull String str, Object obj) {
        Objects.requireNonNull(str, "Argument 'tag' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        e().n(str, obj);
    }
}
