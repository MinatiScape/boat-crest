package kotlinx.coroutines.internal;

import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class f extends CtorCache {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final f f14191a = new f();
    @NotNull
    public static final ReentrantReadWriteLock b = new ReentrantReadWriteLock();
    @NotNull
    public static final WeakHashMap<Class<? extends Throwable>, Function1<Throwable, Throwable>> c = new WeakHashMap<>();

    @Override // kotlinx.coroutines.internal.CtorCache
    @NotNull
    public Function1<Throwable, Throwable> get(@NotNull Class<? extends Throwable> cls) {
        Function1<Throwable, Throwable> a2;
        ReentrantReadWriteLock reentrantReadWriteLock = b;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        readLock.lock();
        try {
            Function1<Throwable, Throwable> function1 = c.get(cls);
            if (function1 == null) {
                readLock = reentrantReadWriteLock.readLock();
                int i = 0;
                int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
                int i2 = 0;
                while (i2 < readHoldCount) {
                    i2++;
                }
                ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
                writeLock.lock();
                try {
                    WeakHashMap<Class<? extends Throwable>, Function1<Throwable, Throwable>> weakHashMap = c;
                    Function1<Throwable, Throwable> function12 = weakHashMap.get(cls);
                    if (function12 == null) {
                        a2 = ExceptionsConstructorKt.a(cls);
                        weakHashMap.put(cls, a2);
                        return a2;
                    }
                    while (i < readHoldCount) {
                        i++;
                        readLock.lock();
                    }
                    writeLock.unlock();
                    return function12;
                } finally {
                    while (i < readHoldCount) {
                        i++;
                        readLock.lock();
                    }
                    writeLock.unlock();
                }
            }
            return function1;
        } finally {
            readLock.unlock();
        }
    }
}
