package rx.plugins;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes13.dex */
public class RxJavaPlugins {
    public static final RxJavaPlugins f = new RxJavaPlugins();
    public static final RxJavaErrorHandler g = new a();

    /* renamed from: a  reason: collision with root package name */
    public final AtomicReference<RxJavaErrorHandler> f15692a = new AtomicReference<>();
    public final AtomicReference<RxJavaObservableExecutionHook> b = new AtomicReference<>();
    public final AtomicReference<RxJavaSingleExecutionHook> c = new AtomicReference<>();
    public final AtomicReference<RxJavaCompletableExecutionHook> d = new AtomicReference<>();
    public final AtomicReference<RxJavaSchedulersHook> e = new AtomicReference<>();

    /* loaded from: classes13.dex */
    public static class a extends RxJavaErrorHandler {
    }

    /* loaded from: classes13.dex */
    public class b extends RxJavaCompletableExecutionHook {
        public b(RxJavaPlugins rxJavaPlugins) {
        }
    }

    public static Object a(Class<?> cls, Properties properties) {
        Properties properties2 = (Properties) properties.clone();
        String simpleName = cls.getSimpleName();
        String property = properties2.getProperty("rxjava.plugin." + simpleName + ".implementation");
        if (property == null) {
            Iterator it = properties2.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry entry = (Map.Entry) it.next();
                String obj = entry.getKey().toString();
                if (obj.startsWith("rxjava.plugin.") && obj.endsWith(".class") && simpleName.equals(entry.getValue().toString())) {
                    String str = "rxjava.plugin." + obj.substring(0, obj.length() - 6).substring(14) + ".impl";
                    String property2 = properties2.getProperty(str);
                    if (property2 == null) {
                        throw new IllegalStateException("Implementing class declaration for " + simpleName + " missing: " + str);
                    }
                    property = property2;
                }
            }
        }
        if (property != null) {
            try {
                return Class.forName(property).asSubclass(cls).newInstance();
            } catch (ClassCastException e) {
                throw new IllegalStateException(simpleName + " implementation is not an instance of " + simpleName + ": " + property, e);
            } catch (ClassNotFoundException e2) {
                throw new IllegalStateException(simpleName + " implementation class not found: " + property, e2);
            } catch (IllegalAccessException e3) {
                throw new IllegalStateException(simpleName + " implementation not able to be accessed: " + property, e3);
            } catch (InstantiationException e4) {
                throw new IllegalStateException(simpleName + " implementation not able to be instantiated: " + property, e4);
            }
        }
        return null;
    }

    @Deprecated
    public static RxJavaPlugins getInstance() {
        return f;
    }

    public RxJavaCompletableExecutionHook getCompletableExecutionHook() {
        if (this.d.get() == null) {
            Object a2 = a(RxJavaCompletableExecutionHook.class, System.getProperties());
            if (a2 == null) {
                this.d.compareAndSet(null, new b(this));
            } else {
                this.d.compareAndSet(null, (RxJavaCompletableExecutionHook) a2);
            }
        }
        return this.d.get();
    }

    public RxJavaErrorHandler getErrorHandler() {
        if (this.f15692a.get() == null) {
            Object a2 = a(RxJavaErrorHandler.class, System.getProperties());
            if (a2 == null) {
                this.f15692a.compareAndSet(null, g);
            } else {
                this.f15692a.compareAndSet(null, (RxJavaErrorHandler) a2);
            }
        }
        return this.f15692a.get();
    }

    public RxJavaObservableExecutionHook getObservableExecutionHook() {
        if (this.b.get() == null) {
            Object a2 = a(RxJavaObservableExecutionHook.class, System.getProperties());
            if (a2 == null) {
                this.b.compareAndSet(null, rx.plugins.a.a());
            } else {
                this.b.compareAndSet(null, (RxJavaObservableExecutionHook) a2);
            }
        }
        return this.b.get();
    }

    public RxJavaSchedulersHook getSchedulersHook() {
        if (this.e.get() == null) {
            Object a2 = a(RxJavaSchedulersHook.class, System.getProperties());
            if (a2 == null) {
                this.e.compareAndSet(null, RxJavaSchedulersHook.getDefaultInstance());
            } else {
                this.e.compareAndSet(null, (RxJavaSchedulersHook) a2);
            }
        }
        return this.e.get();
    }

    public RxJavaSingleExecutionHook getSingleExecutionHook() {
        if (this.c.get() == null) {
            Object a2 = a(RxJavaSingleExecutionHook.class, System.getProperties());
            if (a2 == null) {
                this.c.compareAndSet(null, rx.plugins.b.a());
            } else {
                this.c.compareAndSet(null, (RxJavaSingleExecutionHook) a2);
            }
        }
        return this.c.get();
    }

    public void registerCompletableExecutionHook(RxJavaCompletableExecutionHook rxJavaCompletableExecutionHook) {
        if (this.d.compareAndSet(null, rxJavaCompletableExecutionHook)) {
            return;
        }
        throw new IllegalStateException("Another strategy was already registered: " + this.c.get());
    }

    public void registerErrorHandler(RxJavaErrorHandler rxJavaErrorHandler) {
        if (this.f15692a.compareAndSet(null, rxJavaErrorHandler)) {
            return;
        }
        throw new IllegalStateException("Another strategy was already registered: " + this.f15692a.get());
    }

    public void registerObservableExecutionHook(RxJavaObservableExecutionHook rxJavaObservableExecutionHook) {
        if (this.b.compareAndSet(null, rxJavaObservableExecutionHook)) {
            return;
        }
        throw new IllegalStateException("Another strategy was already registered: " + this.b.get());
    }

    public void registerSchedulersHook(RxJavaSchedulersHook rxJavaSchedulersHook) {
        if (this.e.compareAndSet(null, rxJavaSchedulersHook)) {
            return;
        }
        throw new IllegalStateException("Another strategy was already registered: " + this.e.get());
    }

    public void registerSingleExecutionHook(RxJavaSingleExecutionHook rxJavaSingleExecutionHook) {
        if (this.c.compareAndSet(null, rxJavaSingleExecutionHook)) {
            return;
        }
        throw new IllegalStateException("Another strategy was already registered: " + this.c.get());
    }

    public void reset() {
        RxJavaPlugins rxJavaPlugins = f;
        rxJavaPlugins.f15692a.set(null);
        rxJavaPlugins.b.set(null);
        rxJavaPlugins.c.set(null);
        rxJavaPlugins.d.set(null);
        rxJavaPlugins.e.set(null);
    }
}
