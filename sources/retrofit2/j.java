package retrofit2;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import retrofit2.CallAdapter;
import retrofit2.Converter;
/* loaded from: classes13.dex */
public class j {
    public static final j c = f();

    /* renamed from: a  reason: collision with root package name */
    public final boolean f15638a;
    @Nullable
    public final Constructor<MethodHandles.Lookup> b;

    /* loaded from: classes13.dex */
    public static final class a extends j {

        /* renamed from: retrofit2.j$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public static final class ExecutorC0923a implements Executor {
            public final Handler h = new Handler(Looper.getMainLooper());

            @Override // java.util.concurrent.Executor
            public void execute(Runnable runnable) {
                this.h.post(runnable);
            }
        }

        public a() {
            super(Build.VERSION.SDK_INT >= 24);
        }

        @Override // retrofit2.j
        public Executor c() {
            return new ExecutorC0923a();
        }

        @Override // retrofit2.j
        @Nullable
        public Object h(Method method, Class<?> cls, Object obj, Object... objArr) throws Throwable {
            if (Build.VERSION.SDK_INT >= 26) {
                return super.h(method, cls, obj, objArr);
            }
            throw new UnsupportedOperationException("Calling default methods on API 24 and 25 is not supported");
        }
    }

    public j(boolean z) {
        this.f15638a = z;
        Constructor<MethodHandles.Lookup> constructor = null;
        if (z) {
            try {
                constructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, Integer.TYPE);
                constructor.setAccessible(true);
            } catch (NoClassDefFoundError | NoSuchMethodException unused) {
            }
        }
        this.b = constructor;
    }

    public static j f() {
        if ("Dalvik".equals(System.getProperty("java.vm.name"))) {
            return new a();
        }
        return new j(true);
    }

    public static j g() {
        return c;
    }

    public List<? extends CallAdapter.Factory> a(@Nullable Executor executor) {
        c cVar = new c(executor);
        return this.f15638a ? Arrays.asList(b.f15596a, cVar) : Collections.singletonList(cVar);
    }

    public int b() {
        return this.f15638a ? 2 : 1;
    }

    @Nullable
    public Executor c() {
        return null;
    }

    public List<? extends Converter.Factory> d() {
        return this.f15638a ? Collections.singletonList(h.f15619a) : Collections.emptyList();
    }

    public int e() {
        return this.f15638a ? 1 : 0;
    }

    @Nullable
    @IgnoreJRERequirement
    public Object h(Method method, Class<?> cls, Object obj, Object... objArr) throws Throwable {
        Constructor<MethodHandles.Lookup> constructor = this.b;
        return (constructor != null ? constructor.newInstance(cls, -1) : MethodHandles.lookup()).unreflectSpecial(method, cls).bindTo(obj).invokeWithArguments(objArr);
    }

    @IgnoreJRERequirement
    public boolean i(Method method) {
        return this.f15638a && method.isDefault();
    }
}
