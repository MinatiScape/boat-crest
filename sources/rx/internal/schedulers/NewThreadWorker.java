package rx.internal.schedulers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import rx.Scheduler;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.internal.util.PlatformDependent;
import rx.internal.util.RxThreadFactory;
import rx.internal.util.SubscriptionList;
import rx.internal.util.SuppressAnimalSniffer;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;
/* loaded from: classes13.dex */
public class NewThreadWorker extends Scheduler.Worker {
    public static final boolean j;
    public static volatile Object m;
    public final ScheduledExecutorService h;
    public volatile boolean i;
    public static final Object n = new Object();
    public static final ConcurrentHashMap<ScheduledThreadPoolExecutor, ScheduledThreadPoolExecutor> k = new ConcurrentHashMap<>();
    public static final AtomicReference<ScheduledExecutorService> l = new AtomicReference<>();
    public static final int PURGE_FREQUENCY = Integer.getInteger("rx.scheduler.jdk6.purge-frequency-millis", 1000).intValue();

    /* loaded from: classes13.dex */
    public static class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            NewThreadWorker.b();
        }
    }

    static {
        boolean z = Boolean.getBoolean("rx.scheduler.jdk6.purge-force");
        int androidApiVersion = PlatformDependent.getAndroidApiVersion();
        j = !z && (androidApiVersion == 0 || androidApiVersion >= 21);
    }

    public NewThreadWorker(ThreadFactory threadFactory) {
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, threadFactory);
        if (!tryEnableCancelPolicy(newScheduledThreadPool) && (newScheduledThreadPool instanceof ScheduledThreadPoolExecutor)) {
            registerExecutor((ScheduledThreadPoolExecutor) newScheduledThreadPool);
        }
        this.h = newScheduledThreadPool;
    }

    public static Method a(ScheduledExecutorService scheduledExecutorService) {
        Method[] methods;
        for (Method method : scheduledExecutorService.getClass().getMethods()) {
            if (method.getName().equals("setRemoveOnCancelPolicy")) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1 && parameterTypes[0] == Boolean.TYPE) {
                    return method;
                }
            }
        }
        return null;
    }

    @SuppressAnimalSniffer
    public static void b() {
        try {
            Iterator<ScheduledThreadPoolExecutor> it = k.keySet().iterator();
            while (it.hasNext()) {
                ScheduledThreadPoolExecutor next = it.next();
                if (!next.isShutdown()) {
                    next.purge();
                } else {
                    it.remove();
                }
            }
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            RxJavaHooks.onError(th);
        }
    }

    public static void deregisterExecutor(ScheduledExecutorService scheduledExecutorService) {
        k.remove(scheduledExecutorService);
    }

    public static void registerExecutor(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
        while (true) {
            AtomicReference<ScheduledExecutorService> atomicReference = l;
            if (atomicReference.get() != null) {
                break;
            }
            ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, new RxThreadFactory("RxSchedulerPurge-"));
            if (atomicReference.compareAndSet(null, newScheduledThreadPool)) {
                a aVar = new a();
                int i = PURGE_FREQUENCY;
                newScheduledThreadPool.scheduleAtFixedRate(aVar, i, i, TimeUnit.MILLISECONDS);
                break;
            }
            newScheduledThreadPool.shutdownNow();
        }
        k.putIfAbsent(scheduledThreadPoolExecutor, scheduledThreadPoolExecutor);
    }

    public static boolean tryEnableCancelPolicy(ScheduledExecutorService scheduledExecutorService) {
        Method a2;
        if (j) {
            if (scheduledExecutorService instanceof ScheduledThreadPoolExecutor) {
                Object obj = m;
                Object obj2 = n;
                if (obj == obj2) {
                    return false;
                }
                if (obj == null) {
                    a2 = a(scheduledExecutorService);
                    if (a2 != null) {
                        obj2 = a2;
                    }
                    m = obj2;
                } else {
                    a2 = (Method) obj;
                }
            } else {
                a2 = a(scheduledExecutorService);
            }
            if (a2 != null) {
                try {
                    a2.invoke(scheduledExecutorService, Boolean.TRUE);
                    return true;
                } catch (IllegalAccessException e) {
                    RxJavaHooks.onError(e);
                } catch (IllegalArgumentException e2) {
                    RxJavaHooks.onError(e2);
                } catch (InvocationTargetException e3) {
                    RxJavaHooks.onError(e3);
                }
            }
        }
        return false;
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return this.i;
    }

    @Override // rx.Scheduler.Worker
    public Subscription schedule(Action0 action0) {
        return schedule(action0, 0L, null);
    }

    public ScheduledAction scheduleActual(Action0 action0, long j2, TimeUnit timeUnit) {
        Future<?> schedule;
        ScheduledAction scheduledAction = new ScheduledAction(RxJavaHooks.onScheduledAction(action0));
        if (j2 <= 0) {
            schedule = this.h.submit(scheduledAction);
        } else {
            schedule = this.h.schedule(scheduledAction, j2, timeUnit);
        }
        scheduledAction.add(schedule);
        return scheduledAction;
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        this.i = true;
        this.h.shutdownNow();
        deregisterExecutor(this.h);
    }

    @Override // rx.Scheduler.Worker
    public Subscription schedule(Action0 action0, long j2, TimeUnit timeUnit) {
        if (this.i) {
            return Subscriptions.unsubscribed();
        }
        return scheduleActual(action0, j2, timeUnit);
    }

    public ScheduledAction scheduleActual(Action0 action0, long j2, TimeUnit timeUnit, CompositeSubscription compositeSubscription) {
        Future<?> schedule;
        ScheduledAction scheduledAction = new ScheduledAction(RxJavaHooks.onScheduledAction(action0), compositeSubscription);
        compositeSubscription.add(scheduledAction);
        if (j2 <= 0) {
            schedule = this.h.submit(scheduledAction);
        } else {
            schedule = this.h.schedule(scheduledAction, j2, timeUnit);
        }
        scheduledAction.add(schedule);
        return scheduledAction;
    }

    public ScheduledAction scheduleActual(Action0 action0, long j2, TimeUnit timeUnit, SubscriptionList subscriptionList) {
        Future<?> schedule;
        ScheduledAction scheduledAction = new ScheduledAction(RxJavaHooks.onScheduledAction(action0), subscriptionList);
        subscriptionList.add(scheduledAction);
        if (j2 <= 0) {
            schedule = this.h.submit(scheduledAction);
        } else {
            schedule = this.h.schedule(scheduledAction, j2, timeUnit);
        }
        scheduledAction.add(schedule);
        return scheduledAction;
    }
}
