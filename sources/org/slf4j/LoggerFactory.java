package org.slf4j;

import com.clevertap.android.sdk.Constants;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import org.slf4j.event.SubstituteLoggingEvent;
import org.slf4j.helpers.NOPLoggerFactory;
import org.slf4j.helpers.SubstituteLogger;
import org.slf4j.helpers.SubstituteLoggerFactory;
import org.slf4j.helpers.Util;
import org.slf4j.impl.StaticLoggerBinder;
/* loaded from: classes13.dex */
public final class LoggerFactory {

    /* renamed from: a  reason: collision with root package name */
    public static volatile int f15569a;
    public static SubstituteLoggerFactory b = new SubstituteLoggerFactory();
    public static NOPLoggerFactory c = new NOPLoggerFactory();
    public static boolean d = Util.safeGetBooleanSystemProperty("slf4j.detectLoggerNameMismatch");
    public static final String[] e = {"1.6", "1.7"};
    public static String f = "org/slf4j/impl/StaticLoggerBinder.class";

    public static final void a() {
        Set<URL> set = null;
        try {
            if (!i()) {
                set = f();
                p(set);
            }
            StaticLoggerBinder.getSingleton();
            f15569a = 3;
            o(set);
            g();
            m();
            b.clear();
        } catch (Exception e2) {
            e(e2);
            throw new IllegalStateException("Unexpected initialization failure", e2);
        } catch (NoClassDefFoundError e3) {
            if (j(e3.getMessage())) {
                f15569a = 4;
                Util.report("Failed to load class \"org.slf4j.impl.StaticLoggerBinder\".");
                Util.report("Defaulting to no-operation (NOP) logger implementation");
                Util.report("See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.");
                return;
            }
            e(e3);
            throw e3;
        } catch (NoSuchMethodError e4) {
            String message = e4.getMessage();
            if (message != null && message.contains("org.slf4j.impl.StaticLoggerBinder.getSingleton()")) {
                f15569a = 2;
                Util.report("slf4j-api 1.6.x (or later) is incompatible with this binding.");
                Util.report("Your binding is version 1.5.5 or earlier.");
                Util.report("Upgrade your binding to version 1.6.x.");
            }
            throw e4;
        }
    }

    public static void b(SubstituteLoggingEvent substituteLoggingEvent, int i) {
        if (substituteLoggingEvent.getLogger().isDelegateEventAware()) {
            c(i);
        } else if (substituteLoggingEvent.getLogger().isDelegateNOP()) {
        } else {
            d();
        }
    }

    public static void c(int i) {
        Util.report("A number (" + i + ") of logging calls during the initialization phase have been intercepted and are");
        Util.report("now being replayed. These are subject to the filtering rules of the underlying logging system.");
        Util.report("See also http://www.slf4j.org/codes.html#replay");
    }

    public static void d() {
        Util.report("The following set of substitute loggers may have been accessed");
        Util.report("during the initialization phase. Logging calls during this");
        Util.report("phase were not honored. However, subsequent logging calls to these");
        Util.report("loggers will work as normally expected.");
        Util.report("See also http://www.slf4j.org/codes.html#substituteLogger");
    }

    public static void e(Throwable th) {
        f15569a = 2;
        Util.report("Failed to instantiate SLF4J LoggerFactory", th);
    }

    public static Set<URL> f() {
        Enumeration<URL> resources;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        try {
            ClassLoader classLoader = LoggerFactory.class.getClassLoader();
            if (classLoader == null) {
                resources = ClassLoader.getSystemResources(f);
            } else {
                resources = classLoader.getResources(f);
            }
            while (resources.hasMoreElements()) {
                linkedHashSet.add(resources.nextElement());
            }
        } catch (IOException e2) {
            Util.report("Error getting resources from path", e2);
        }
        return linkedHashSet;
    }

    public static void g() {
        synchronized (b) {
            b.postInitialization();
            for (SubstituteLogger substituteLogger : b.getLoggers()) {
                substituteLogger.setDelegate(getLogger(substituteLogger.getName()));
            }
        }
    }

    public static ILoggerFactory getILoggerFactory() {
        if (f15569a == 0) {
            synchronized (LoggerFactory.class) {
                if (f15569a == 0) {
                    f15569a = 1;
                    l();
                }
            }
        }
        int i = f15569a;
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        return c;
                    }
                    throw new IllegalStateException("Unreachable code");
                }
                return StaticLoggerBinder.getSingleton().getLoggerFactory();
            }
            throw new IllegalStateException("org.slf4j.LoggerFactory could not be successfully initialized. See also http://www.slf4j.org/codes.html#unsuccessfulInit");
        }
        return b;
    }

    public static Logger getLogger(String str) {
        return getILoggerFactory().getLogger(str);
    }

    public static boolean h(Set<URL> set) {
        return set.size() > 1;
    }

    public static boolean i() {
        String safeGetSystemProperty = Util.safeGetSystemProperty("java.vendor.url");
        if (safeGetSystemProperty == null) {
            return false;
        }
        return safeGetSystemProperty.toLowerCase().contains(Constants.KEY_ANDROID);
    }

    public static boolean j(String str) {
        if (str == null) {
            return false;
        }
        return str.contains("org/slf4j/impl/StaticLoggerBinder") || str.contains("org.slf4j.impl.StaticLoggerBinder");
    }

    public static boolean k(Class<?> cls, Class<?> cls2) {
        return !cls2.isAssignableFrom(cls);
    }

    public static final void l() {
        a();
        if (f15569a == 3) {
            q();
        }
    }

    public static void m() {
        LinkedBlockingQueue<SubstituteLoggingEvent> eventQueue = b.getEventQueue();
        int size = eventQueue.size();
        ArrayList<SubstituteLoggingEvent> arrayList = new ArrayList(128);
        int i = 0;
        while (eventQueue.drainTo(arrayList, 128) != 0) {
            for (SubstituteLoggingEvent substituteLoggingEvent : arrayList) {
                n(substituteLoggingEvent);
                int i2 = i + 1;
                if (i == 0) {
                    b(substituteLoggingEvent, size);
                }
                i = i2;
            }
            arrayList.clear();
        }
    }

    public static void n(SubstituteLoggingEvent substituteLoggingEvent) {
        if (substituteLoggingEvent == null) {
            return;
        }
        SubstituteLogger logger = substituteLoggingEvent.getLogger();
        String name = logger.getName();
        if (!logger.isDelegateNull()) {
            if (logger.isDelegateNOP()) {
                return;
            }
            if (logger.isDelegateEventAware()) {
                logger.log(substituteLoggingEvent);
                return;
            } else {
                Util.report(name);
                return;
            }
        }
        throw new IllegalStateException("Delegate logger cannot be null at this state.");
    }

    public static void o(Set<URL> set) {
        if (set == null || !h(set)) {
            return;
        }
        Util.report("Actual binding is of type [" + StaticLoggerBinder.getSingleton().getLoggerFactoryClassStr() + "]");
    }

    public static void p(Set<URL> set) {
        if (h(set)) {
            Util.report("Class path contains multiple SLF4J bindings.");
            Iterator<URL> it = set.iterator();
            while (it.hasNext()) {
                Util.report("Found binding in [" + it.next() + "]");
            }
            Util.report("See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.");
        }
    }

    public static final void q() {
        try {
            String str = StaticLoggerBinder.REQUESTED_API_VERSION;
            boolean z = false;
            for (String str2 : e) {
                if (str.startsWith(str2)) {
                    z = true;
                }
            }
            if (z) {
                return;
            }
            Util.report("The requested version " + str + " by your slf4j binding is not compatible with " + Arrays.asList(e).toString());
            Util.report("See http://www.slf4j.org/codes.html#version_mismatch for further details.");
        } catch (NoSuchFieldError unused) {
        } catch (Throwable th) {
            Util.report("Unexpected problem occured during version sanity check", th);
        }
    }

    public static Logger getLogger(Class<?> cls) {
        Class<?> callingClass;
        Logger logger = getLogger(cls.getName());
        if (d && (callingClass = Util.getCallingClass()) != null && k(cls, callingClass)) {
            Util.report(String.format("Detected logger name mismatch. Given name: \"%s\"; computed name: \"%s\".", logger.getName(), callingClass.getName()));
            Util.report("See http://www.slf4j.org/codes.html#loggerNameMismatch for an explanation");
        }
        return logger;
    }
}
