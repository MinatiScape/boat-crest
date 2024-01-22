package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.MapMaker;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.Weak;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@CanIgnoreReturnValue
@Beta
@GwtIncompatible
/* loaded from: classes10.dex */
public class CycleDetectingLockFactory {
    public static final ConcurrentMap<Class<? extends Enum>, Map<? extends Enum, h>> b = new MapMaker().weakKeys().makeMap();
    public static final Logger c = Logger.getLogger(CycleDetectingLockFactory.class.getName());
    public static final ThreadLocal<ArrayList<h>> d = new a();

    /* renamed from: a  reason: collision with root package name */
    public final Policy f10782a;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    @Beta
    /* loaded from: classes10.dex */
    public static abstract class Policies implements Policy {
        public static final Policies THROW = new a("THROW", 0);
        public static final Policies WARN = new b("WARN", 1);
        public static final Policies DISABLED = new c("DISABLED", 2);
        private static final /* synthetic */ Policies[] $VALUES = $values();

        /* loaded from: classes10.dex */
        public enum a extends Policies {
            public a(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.util.concurrent.CycleDetectingLockFactory.Policy
            public void handlePotentialDeadlock(PotentialDeadlockException potentialDeadlockException) {
                throw potentialDeadlockException;
            }
        }

        /* loaded from: classes10.dex */
        public enum b extends Policies {
            public b(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.util.concurrent.CycleDetectingLockFactory.Policy
            public void handlePotentialDeadlock(PotentialDeadlockException potentialDeadlockException) {
                CycleDetectingLockFactory.c.log(Level.SEVERE, "Detected potential deadlock", (Throwable) potentialDeadlockException);
            }
        }

        /* loaded from: classes10.dex */
        public enum c extends Policies {
            public c(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.util.concurrent.CycleDetectingLockFactory.Policy
            public void handlePotentialDeadlock(PotentialDeadlockException potentialDeadlockException) {
            }
        }

        private static /* synthetic */ Policies[] $values() {
            return new Policies[]{THROW, WARN, DISABLED};
        }

        private Policies(String str, int i) {
        }

        public static Policies valueOf(String str) {
            return (Policies) Enum.valueOf(Policies.class, str);
        }

        public static Policies[] values() {
            return (Policies[]) $VALUES.clone();
        }

        public /* synthetic */ Policies(String str, int i, a aVar) {
            this(str, i);
        }
    }

    @Beta
    /* loaded from: classes10.dex */
    public interface Policy {
        void handlePotentialDeadlock(PotentialDeadlockException potentialDeadlockException);
    }

    @Beta
    /* loaded from: classes10.dex */
    public static final class PotentialDeadlockException extends g {
        private final g conflictingStackTrace;

        public /* synthetic */ PotentialDeadlockException(h hVar, h hVar2, g gVar, a aVar) {
            this(hVar, hVar2, gVar);
        }

        public g getConflictingStackTrace() {
            return this.conflictingStackTrace;
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            StringBuilder sb = new StringBuilder(super.getMessage());
            for (Throwable th = this.conflictingStackTrace; th != null; th = th.getCause()) {
                sb.append(", ");
                sb.append(th.getMessage());
            }
            return sb.toString();
        }

        private PotentialDeadlockException(h hVar, h hVar2, g gVar) {
            super(hVar, hVar2);
            this.conflictingStackTrace = gVar;
            initCause(gVar);
        }
    }

    @Beta
    /* loaded from: classes10.dex */
    public static final class WithExplicitOrdering<E extends Enum<E>> extends CycleDetectingLockFactory {
        public final Map<E, h> e;

        @VisibleForTesting
        public WithExplicitOrdering(Policy policy, Map<E, h> map) {
            super(policy, null);
            this.e = map;
        }

        public ReentrantLock newReentrantLock(E e) {
            return newReentrantLock((WithExplicitOrdering<E>) e, false);
        }

        public ReentrantReadWriteLock newReentrantReadWriteLock(E e) {
            return newReentrantReadWriteLock((WithExplicitOrdering<E>) e, false);
        }

        public ReentrantLock newReentrantLock(E e, boolean z) {
            if (this.f10782a == Policies.DISABLED) {
                return new ReentrantLock(z);
            }
            return new c(this, this.e.get(e), z, null);
        }

        public ReentrantReadWriteLock newReentrantReadWriteLock(E e, boolean z) {
            if (this.f10782a == Policies.DISABLED) {
                return new ReentrantReadWriteLock(z);
            }
            return new e(this, this.e.get(e), z, null);
        }
    }

    /* loaded from: classes10.dex */
    public class a extends ThreadLocal<ArrayList<h>> {
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public ArrayList<h> initialValue() {
            return Lists.newArrayListWithCapacity(3);
        }
    }

    /* loaded from: classes10.dex */
    public interface b {
        h getLockGraphNode();

        boolean isAcquiredByCurrentThread();
    }

    /* loaded from: classes10.dex */
    public final class c extends ReentrantLock implements b {
        private final h lockGraphNode;

        public /* synthetic */ c(CycleDetectingLockFactory cycleDetectingLockFactory, h hVar, boolean z, a aVar) {
            this(hVar, z);
        }

        @Override // com.google.common.util.concurrent.CycleDetectingLockFactory.b
        public h getLockGraphNode() {
            return this.lockGraphNode;
        }

        @Override // com.google.common.util.concurrent.CycleDetectingLockFactory.b
        public boolean isAcquiredByCurrentThread() {
            return isHeldByCurrentThread();
        }

        @Override // java.util.concurrent.locks.ReentrantLock, java.util.concurrent.locks.Lock
        public void lock() {
            CycleDetectingLockFactory.this.a(this);
            try {
                super.lock();
            } finally {
                CycleDetectingLockFactory.h(this);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantLock, java.util.concurrent.locks.Lock
        public void lockInterruptibly() throws InterruptedException {
            CycleDetectingLockFactory.this.a(this);
            try {
                super.lockInterruptibly();
            } finally {
                CycleDetectingLockFactory.h(this);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantLock, java.util.concurrent.locks.Lock
        public boolean tryLock() {
            CycleDetectingLockFactory.this.a(this);
            try {
                return super.tryLock();
            } finally {
                CycleDetectingLockFactory.h(this);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantLock, java.util.concurrent.locks.Lock
        public void unlock() {
            try {
                super.unlock();
            } finally {
                CycleDetectingLockFactory.h(this);
            }
        }

        private c(h hVar, boolean z) {
            super(z);
            this.lockGraphNode = (h) Preconditions.checkNotNull(hVar);
        }

        @Override // java.util.concurrent.locks.ReentrantLock, java.util.concurrent.locks.Lock
        public boolean tryLock(long j, TimeUnit timeUnit) throws InterruptedException {
            CycleDetectingLockFactory.this.a(this);
            try {
                return super.tryLock(j, timeUnit);
            } finally {
                CycleDetectingLockFactory.h(this);
            }
        }
    }

    /* loaded from: classes10.dex */
    public final class e extends ReentrantReadWriteLock implements b {
        private final h lockGraphNode;
        private final d readLock;
        private final f writeLock;

        public /* synthetic */ e(CycleDetectingLockFactory cycleDetectingLockFactory, h hVar, boolean z, a aVar) {
            this(cycleDetectingLockFactory, hVar, z);
        }

        @Override // com.google.common.util.concurrent.CycleDetectingLockFactory.b
        public h getLockGraphNode() {
            return this.lockGraphNode;
        }

        @Override // com.google.common.util.concurrent.CycleDetectingLockFactory.b
        public boolean isAcquiredByCurrentThread() {
            return isWriteLockedByCurrentThread() || getReadHoldCount() > 0;
        }

        private e(CycleDetectingLockFactory cycleDetectingLockFactory, h hVar, boolean z) {
            super(z);
            this.readLock = new d(this);
            this.writeLock = new f(this);
            this.lockGraphNode = (h) Preconditions.checkNotNull(hVar);
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock, java.util.concurrent.locks.ReadWriteLock
        public ReentrantReadWriteLock.ReadLock readLock() {
            return this.readLock;
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock, java.util.concurrent.locks.ReadWriteLock
        public ReentrantReadWriteLock.WriteLock writeLock() {
            return this.writeLock;
        }
    }

    /* loaded from: classes10.dex */
    public static class g extends IllegalStateException {
        public static final StackTraceElement[] EMPTY_STACK_TRACE = new StackTraceElement[0];
        public static final ImmutableSet<String> EXCLUDED_CLASS_NAMES = ImmutableSet.of(CycleDetectingLockFactory.class.getName(), g.class.getName(), h.class.getName());

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public g(com.google.common.util.concurrent.CycleDetectingLockFactory.h r4, com.google.common.util.concurrent.CycleDetectingLockFactory.h r5) {
            /*
                r3 = this;
                java.lang.String r4 = r4.d()
                java.lang.String r5 = r5.d()
                java.lang.String r0 = java.lang.String.valueOf(r4)
                int r0 = r0.length()
                int r0 = r0 + 4
                java.lang.String r1 = java.lang.String.valueOf(r5)
                int r1 = r1.length()
                int r0 = r0 + r1
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>(r0)
                r1.append(r4)
                java.lang.String r4 = " -> "
                r1.append(r4)
                r1.append(r5)
                java.lang.String r4 = r1.toString()
                r3.<init>(r4)
                java.lang.StackTraceElement[] r4 = r3.getStackTrace()
                int r5 = r4.length
                r0 = 0
            L38:
                if (r0 >= r5) goto L6d
                java.lang.Class<com.google.common.util.concurrent.CycleDetectingLockFactory$WithExplicitOrdering> r1 = com.google.common.util.concurrent.CycleDetectingLockFactory.WithExplicitOrdering.class
                java.lang.String r1 = r1.getName()
                r2 = r4[r0]
                java.lang.String r2 = r2.getClassName()
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L52
                java.lang.StackTraceElement[] r4 = com.google.common.util.concurrent.CycleDetectingLockFactory.g.EMPTY_STACK_TRACE
                r3.setStackTrace(r4)
                goto L6d
            L52:
                com.google.common.collect.ImmutableSet<java.lang.String> r1 = com.google.common.util.concurrent.CycleDetectingLockFactory.g.EXCLUDED_CLASS_NAMES
                r2 = r4[r0]
                java.lang.String r2 = r2.getClassName()
                boolean r1 = r1.contains(r2)
                if (r1 != 0) goto L6a
                java.lang.Object[] r4 = java.util.Arrays.copyOfRange(r4, r0, r5)
                java.lang.StackTraceElement[] r4 = (java.lang.StackTraceElement[]) r4
                r3.setStackTrace(r4)
                goto L6d
            L6a:
                int r0 = r0 + 1
                goto L38
            L6d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.CycleDetectingLockFactory.g.<init>(com.google.common.util.concurrent.CycleDetectingLockFactory$h, com.google.common.util.concurrent.CycleDetectingLockFactory$h):void");
        }
    }

    /* loaded from: classes10.dex */
    public static class h {

        /* renamed from: a  reason: collision with root package name */
        public final Map<h, g> f10783a = new MapMaker().weakKeys().makeMap();
        public final Map<h, PotentialDeadlockException> b = new MapMaker().weakKeys().makeMap();
        public final String c;

        public h(String str) {
            this.c = (String) Preconditions.checkNotNull(str);
        }

        public void a(Policy policy, h hVar) {
            Preconditions.checkState(this != hVar, "Attempted to acquire multiple locks with the same rank %s", hVar.d());
            if (this.f10783a.containsKey(hVar)) {
                return;
            }
            PotentialDeadlockException potentialDeadlockException = this.b.get(hVar);
            if (potentialDeadlockException != null) {
                policy.handlePotentialDeadlock(new PotentialDeadlockException(hVar, this, potentialDeadlockException.getConflictingStackTrace(), null));
                return;
            }
            g c = hVar.c(this, Sets.newIdentityHashSet());
            if (c == null) {
                this.f10783a.put(hVar, new g(hVar, this));
                return;
            }
            PotentialDeadlockException potentialDeadlockException2 = new PotentialDeadlockException(hVar, this, c, null);
            this.b.put(hVar, potentialDeadlockException2);
            policy.handlePotentialDeadlock(potentialDeadlockException2);
        }

        public void b(Policy policy, List<h> list) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                a(policy, list.get(i));
            }
        }

        @NullableDecl
        public final g c(h hVar, Set<h> set) {
            if (set.add(this)) {
                g gVar = this.f10783a.get(hVar);
                if (gVar != null) {
                    return gVar;
                }
                for (Map.Entry<h, g> entry : this.f10783a.entrySet()) {
                    h key = entry.getKey();
                    g c = key.c(hVar, set);
                    if (c != null) {
                        g gVar2 = new g(key, this);
                        gVar2.setStackTrace(entry.getValue().getStackTrace());
                        gVar2.initCause(c);
                        return gVar2;
                    }
                }
                return null;
            }
            return null;
        }

        public String d() {
            return this.c;
        }
    }

    public /* synthetic */ CycleDetectingLockFactory(Policy policy, a aVar) {
        this(policy);
    }

    @VisibleForTesting
    public static <E extends Enum<E>> Map<E, h> e(Class<E> cls) {
        EnumMap newEnumMap = Maps.newEnumMap(cls);
        E[] enumConstants = cls.getEnumConstants();
        int length = enumConstants.length;
        ArrayList newArrayListWithCapacity = Lists.newArrayListWithCapacity(length);
        int i = 0;
        for (E e2 : enumConstants) {
            h hVar = new h(f(e2));
            newArrayListWithCapacity.add(hVar);
            newEnumMap.put((EnumMap) e2, (E) hVar);
        }
        for (int i2 = 1; i2 < length; i2++) {
            ((h) newArrayListWithCapacity.get(i2)).b(Policies.THROW, newArrayListWithCapacity.subList(0, i2));
        }
        while (i < length - 1) {
            i++;
            ((h) newArrayListWithCapacity.get(i)).b(Policies.DISABLED, newArrayListWithCapacity.subList(i, length));
        }
        return Collections.unmodifiableMap(newEnumMap);
    }

    public static String f(Enum<?> r3) {
        String simpleName = r3.getDeclaringClass().getSimpleName();
        String name = r3.name();
        StringBuilder sb = new StringBuilder(simpleName.length() + 1 + String.valueOf(name).length());
        sb.append(simpleName);
        sb.append(".");
        sb.append(name);
        return sb.toString();
    }

    public static Map<? extends Enum, h> g(Class<? extends Enum> cls) {
        ConcurrentMap<Class<? extends Enum>, Map<? extends Enum, h>> concurrentMap = b;
        Map<? extends Enum, h> map = concurrentMap.get(cls);
        if (map != null) {
            return map;
        }
        Map<? extends Enum, h> e2 = e(cls);
        return (Map) MoreObjects.firstNonNull(concurrentMap.putIfAbsent(cls, e2), e2);
    }

    public static void h(b bVar) {
        if (bVar.isAcquiredByCurrentThread()) {
            return;
        }
        ArrayList<h> arrayList = d.get();
        h lockGraphNode = bVar.getLockGraphNode();
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size) == lockGraphNode) {
                arrayList.remove(size);
                return;
            }
        }
    }

    public static CycleDetectingLockFactory newInstance(Policy policy) {
        return new CycleDetectingLockFactory(policy);
    }

    public static <E extends Enum<E>> WithExplicitOrdering<E> newInstanceWithExplicitOrdering(Class<E> cls, Policy policy) {
        Preconditions.checkNotNull(cls);
        Preconditions.checkNotNull(policy);
        return new WithExplicitOrdering<>(policy, g(cls));
    }

    public final void a(b bVar) {
        if (bVar.isAcquiredByCurrentThread()) {
            return;
        }
        ArrayList<h> arrayList = d.get();
        h lockGraphNode = bVar.getLockGraphNode();
        lockGraphNode.b(this.f10782a, arrayList);
        arrayList.add(lockGraphNode);
    }

    public ReentrantLock newReentrantLock(String str) {
        return newReentrantLock(str, false);
    }

    public ReentrantReadWriteLock newReentrantReadWriteLock(String str) {
        return newReentrantReadWriteLock(str, false);
    }

    public CycleDetectingLockFactory(Policy policy) {
        this.f10782a = (Policy) Preconditions.checkNotNull(policy);
    }

    public ReentrantLock newReentrantLock(String str, boolean z) {
        if (this.f10782a == Policies.DISABLED) {
            return new ReentrantLock(z);
        }
        return new c(this, new h(str), z, null);
    }

    public ReentrantReadWriteLock newReentrantReadWriteLock(String str, boolean z) {
        if (this.f10782a == Policies.DISABLED) {
            return new ReentrantReadWriteLock(z);
        }
        return new e(this, new h(str), z, null);
    }

    /* loaded from: classes10.dex */
    public class d extends ReentrantReadWriteLock.ReadLock {
        @Weak
        public final e readWriteLock;

        public d(e eVar) {
            super(eVar);
            this.readWriteLock = eVar;
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock, java.util.concurrent.locks.Lock
        public void lock() {
            CycleDetectingLockFactory.this.a(this.readWriteLock);
            try {
                super.lock();
            } finally {
                CycleDetectingLockFactory.h(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock, java.util.concurrent.locks.Lock
        public void lockInterruptibly() throws InterruptedException {
            CycleDetectingLockFactory.this.a(this.readWriteLock);
            try {
                super.lockInterruptibly();
            } finally {
                CycleDetectingLockFactory.h(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock, java.util.concurrent.locks.Lock
        public boolean tryLock() {
            CycleDetectingLockFactory.this.a(this.readWriteLock);
            try {
                return super.tryLock();
            } finally {
                CycleDetectingLockFactory.h(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock, java.util.concurrent.locks.Lock
        public void unlock() {
            try {
                super.unlock();
            } finally {
                CycleDetectingLockFactory.h(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock, java.util.concurrent.locks.Lock
        public boolean tryLock(long j, TimeUnit timeUnit) throws InterruptedException {
            CycleDetectingLockFactory.this.a(this.readWriteLock);
            try {
                return super.tryLock(j, timeUnit);
            } finally {
                CycleDetectingLockFactory.h(this.readWriteLock);
            }
        }
    }

    /* loaded from: classes10.dex */
    public class f extends ReentrantReadWriteLock.WriteLock {
        @Weak
        public final e readWriteLock;

        public f(e eVar) {
            super(eVar);
            this.readWriteLock = eVar;
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock, java.util.concurrent.locks.Lock
        public void lock() {
            CycleDetectingLockFactory.this.a(this.readWriteLock);
            try {
                super.lock();
            } finally {
                CycleDetectingLockFactory.h(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock, java.util.concurrent.locks.Lock
        public void lockInterruptibly() throws InterruptedException {
            CycleDetectingLockFactory.this.a(this.readWriteLock);
            try {
                super.lockInterruptibly();
            } finally {
                CycleDetectingLockFactory.h(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock, java.util.concurrent.locks.Lock
        public boolean tryLock() {
            CycleDetectingLockFactory.this.a(this.readWriteLock);
            try {
                return super.tryLock();
            } finally {
                CycleDetectingLockFactory.h(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock, java.util.concurrent.locks.Lock
        public void unlock() {
            try {
                super.unlock();
            } finally {
                CycleDetectingLockFactory.h(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock, java.util.concurrent.locks.Lock
        public boolean tryLock(long j, TimeUnit timeUnit) throws InterruptedException {
            CycleDetectingLockFactory.this.a(this.readWriteLock);
            try {
                return super.tryLock(j, timeUnit);
            } finally {
                CycleDetectingLockFactory.h(this.readWriteLock);
            }
        }
    }
}
