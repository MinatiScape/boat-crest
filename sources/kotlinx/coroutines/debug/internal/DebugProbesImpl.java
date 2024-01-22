package kotlinx.coroutines.debug.internal;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.KotlinVersion;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.r;
import kotlin.comparisons.f;
import kotlin.concurrent.ThreadsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.ranges.h;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlin.text.Typography;
import kotlin.text.m;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineId;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.JobSupport;
import kotlinx.coroutines.debug.internal.DebugProbesImpl;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class DebugProbesImpl {
    @NotNull
    public static final DebugProbesImpl INSTANCE;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final SimpleDateFormat f14156a;
    @Nullable
    public static Thread b;
    @NotNull
    public static final ConcurrentWeakMap<a<?>, Boolean> c;
    @NotNull
    public static final /* synthetic */ kotlinx.coroutines.debug.internal.a d;
    public static final /* synthetic */ AtomicLongFieldUpdater e;
    @NotNull
    public static final ReentrantReadWriteLock f;
    public static boolean g;
    public static boolean h;
    @Nullable
    public static final Function1<Boolean, Unit> i;
    private static volatile int installations;
    @NotNull
    public static final ConcurrentWeakMap<CoroutineStackFrame, DebugCoroutineInfoImpl> j;

    /* loaded from: classes12.dex */
    public static final class a<T> implements Continuation<T>, CoroutineStackFrame {
        @JvmField
        @NotNull
        public final Continuation<T> h;
        @JvmField
        @NotNull
        public final DebugCoroutineInfoImpl i;
        @Nullable
        public final CoroutineStackFrame j;

        /* JADX WARN: Multi-variable type inference failed */
        public a(@NotNull Continuation<? super T> continuation, @NotNull DebugCoroutineInfoImpl debugCoroutineInfoImpl, @Nullable CoroutineStackFrame coroutineStackFrame) {
            this.h = continuation;
            this.i = debugCoroutineInfoImpl;
            this.j = coroutineStackFrame;
        }

        @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
        @Nullable
        public CoroutineStackFrame getCallerFrame() {
            CoroutineStackFrame coroutineStackFrame = this.j;
            if (coroutineStackFrame == null) {
                return null;
            }
            return coroutineStackFrame.getCallerFrame();
        }

        @Override // kotlin.coroutines.Continuation
        @NotNull
        public CoroutineContext getContext() {
            return this.h.getContext();
        }

        @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
        @Nullable
        public StackTraceElement getStackTraceElement() {
            CoroutineStackFrame coroutineStackFrame = this.j;
            if (coroutineStackFrame == null) {
                return null;
            }
            return coroutineStackFrame.getStackTraceElement();
        }

        @Override // kotlin.coroutines.Continuation
        public void resumeWith(@NotNull Object obj) {
            DebugProbesImpl.INSTANCE.o(this);
            this.h.resumeWith(obj);
        }

        @NotNull
        public String toString() {
            return this.h.toString();
        }
    }

    /* loaded from: classes12.dex */
    public static final class b extends Lambda implements Function1<a<?>, Boolean> {
        public static final b INSTANCE = new b();

        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        @NotNull
        public final Boolean invoke(@NotNull a<?> aVar) {
            return Boolean.valueOf(!DebugProbesImpl.INSTANCE.j(aVar));
        }
    }

    /* loaded from: classes12.dex */
    public static final class c extends Lambda implements Function0<Unit> {
        public static final c INSTANCE = new c();

        public c() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2() {
            DebugProbesImpl.j.runWeakRefQueueCleaningLoopUntilInterrupted();
        }
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [kotlinx.coroutines.debug.internal.a] */
    static {
        DebugProbesImpl debugProbesImpl = new DebugProbesImpl();
        INSTANCE = debugProbesImpl;
        f14156a = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        c = new ConcurrentWeakMap<>(false, 1, null);
        d = new Object(0L) { // from class: kotlinx.coroutines.debug.internal.a
            public volatile long sequenceNumber;

            {
                this.sequenceNumber = r1;
            }
        };
        f = new ReentrantReadWriteLock();
        g = true;
        h = true;
        i = debugProbesImpl.i();
        j = new ConcurrentWeakMap<>(true);
        e = AtomicLongFieldUpdater.newUpdater(kotlinx.coroutines.debug.internal.a.class, "sequenceNumber");
    }

    public final void a(Job job, Map<Job, DebugCoroutineInfoImpl> map, StringBuilder sb, String str) {
        DebugCoroutineInfoImpl debugCoroutineInfoImpl = map.get(job);
        if (debugCoroutineInfoImpl == null) {
            if (!(job instanceof ScopeCoroutine)) {
                sb.append(str + h(job) + '\n');
                str = Intrinsics.stringPlus(str, "\t");
            }
        } else {
            String state = debugCoroutineInfoImpl.getState();
            sb.append(str + h(job) + ", continuation is " + state + " at line " + ((StackTraceElement) CollectionsKt___CollectionsKt.firstOrNull((List<? extends Object>) debugCoroutineInfoImpl.lastObservedStackTrace())) + '\n');
            str = Intrinsics.stringPlus(str, "\t");
        }
        for (Job job2 : job.getChildren()) {
            a(job2, map, sb, str);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T> Continuation<T> b(Continuation<? super T> continuation, StackTraceFrame stackTraceFrame) {
        if (isInstalled$kotlinx_coroutines_core()) {
            a<?> aVar = new a<>(continuation, new DebugCoroutineInfoImpl(continuation.getContext(), stackTraceFrame, e.incrementAndGet(d)), stackTraceFrame);
            ConcurrentWeakMap<a<?>, Boolean> concurrentWeakMap = c;
            concurrentWeakMap.put(aVar, Boolean.TRUE);
            if (!isInstalled$kotlinx_coroutines_core()) {
                concurrentWeakMap.clear();
            }
            return aVar;
        }
        return continuation;
    }

    public final void c(PrintStream printStream) {
        String state;
        ReentrantReadWriteLock reentrantReadWriteLock = f;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i2 = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        int i3 = 0;
        while (i3 < readHoldCount) {
            i3++;
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            DebugProbesImpl debugProbesImpl = INSTANCE;
            if (debugProbesImpl.isInstalled$kotlinx_coroutines_core()) {
                printStream.print(Intrinsics.stringPlus("Coroutines dump ", f14156a.format(Long.valueOf(System.currentTimeMillis()))));
                for (a aVar : SequencesKt___SequencesKt.sortedWith(SequencesKt___SequencesKt.filter(CollectionsKt___CollectionsKt.asSequence(debugProbesImpl.g()), b.INSTANCE), new Comparator() { // from class: kotlinx.coroutines.debug.internal.DebugProbesImpl$dumpCoroutinesSynchronized$lambda-19$$inlined$sortedBy$1
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return f.compareValues(Long.valueOf(((DebugProbesImpl.a) t).i.sequenceNumber), Long.valueOf(((DebugProbesImpl.a) t2).i.sequenceNumber));
                    }
                })) {
                    DebugCoroutineInfoImpl debugCoroutineInfoImpl = aVar.i;
                    List<StackTraceElement> lastObservedStackTrace = debugCoroutineInfoImpl.lastObservedStackTrace();
                    DebugProbesImpl debugProbesImpl2 = INSTANCE;
                    List<StackTraceElement> d2 = debugProbesImpl2.d(debugCoroutineInfoImpl.getState(), debugCoroutineInfoImpl.lastObservedThread, lastObservedStackTrace);
                    if (Intrinsics.areEqual(debugCoroutineInfoImpl.getState(), DebugCoroutineInfoImplKt.RUNNING) && d2 == lastObservedStackTrace) {
                        state = Intrinsics.stringPlus(debugCoroutineInfoImpl.getState(), " (Last suspension stacktrace, not an actual stacktrace)");
                    } else {
                        state = debugCoroutineInfoImpl.getState();
                    }
                    printStream.print("\n\nCoroutine " + aVar.h + ", state: " + state);
                    if (lastObservedStackTrace.isEmpty()) {
                        printStream.print(Intrinsics.stringPlus("\n\tat ", StackTraceRecoveryKt.artificialFrame("Coroutine creation stacktrace")));
                        debugProbesImpl2.n(printStream, debugCoroutineInfoImpl.getCreationStackTrace());
                    } else {
                        debugProbesImpl2.n(printStream, d2);
                    }
                }
                Unit unit = Unit.INSTANCE;
                return;
            }
            throw new IllegalStateException("Debug probes are not installed".toString());
        } finally {
            while (i2 < readHoldCount) {
                i2++;
                readLock.lock();
            }
            writeLock.unlock();
        }
    }

    public final List<StackTraceElement> d(String str, Thread thread, List<StackTraceElement> list) {
        Object m123constructorimpl;
        if (!Intrinsics.areEqual(str, DebugCoroutineInfoImplKt.RUNNING) || thread == null) {
            return list;
        }
        try {
            Result.Companion companion = Result.Companion;
            m123constructorimpl = Result.m123constructorimpl(thread.getStackTrace());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            m123constructorimpl = Result.m123constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m129isFailureimpl(m123constructorimpl)) {
            m123constructorimpl = null;
        }
        StackTraceElement[] stackTraceElementArr = (StackTraceElement[]) m123constructorimpl;
        if (stackTraceElementArr == null) {
            return list;
        }
        int length = stackTraceElementArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                i2 = -1;
                break;
            }
            int i3 = i2 + 1;
            StackTraceElement stackTraceElement = stackTraceElementArr[i2];
            if (Intrinsics.areEqual(stackTraceElement.getClassName(), "kotlin.coroutines.jvm.internal.BaseContinuationImpl") && Intrinsics.areEqual(stackTraceElement.getMethodName(), "resumeWith") && Intrinsics.areEqual(stackTraceElement.getFileName(), "ContinuationImpl.kt")) {
                break;
            }
            i2 = i3;
        }
        Pair<Integer, Integer> e2 = e(i2, stackTraceElementArr, list);
        int intValue = e2.component1().intValue();
        int intValue2 = e2.component2().intValue();
        if (intValue == -1) {
            return list;
        }
        ArrayList arrayList = new ArrayList((((list.size() + i2) - intValue) - 1) - intValue2);
        int i4 = i2 - intValue2;
        for (int i5 = 0; i5 < i4; i5++) {
            arrayList.add(stackTraceElementArr[i5]);
        }
        int size = list.size();
        for (int i6 = intValue + 1; i6 < size; i6++) {
            arrayList.add(list.get(i6));
        }
        return arrayList;
    }

    public final void dumpCoroutines(@NotNull PrintStream printStream) {
        synchronized (printStream) {
            INSTANCE.c(printStream);
            Unit unit = Unit.INSTANCE;
        }
    }

    @NotNull
    public final List<DebugCoroutineInfo> dumpCoroutinesInfo() {
        ReentrantReadWriteLock reentrantReadWriteLock = f;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i2 = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        int i3 = 0;
        while (i3 < readHoldCount) {
            i3++;
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            DebugProbesImpl debugProbesImpl = INSTANCE;
            if (debugProbesImpl.isInstalled$kotlinx_coroutines_core()) {
                return SequencesKt___SequencesKt.toList(SequencesKt___SequencesKt.mapNotNull(SequencesKt___SequencesKt.sortedWith(CollectionsKt___CollectionsKt.asSequence(debugProbesImpl.g()), new DebugProbesImpl$dumpCoroutinesInfoImpl$lambda12$$inlined$sortedBy$1()), new Function1<a<?>, DebugCoroutineInfo>() { // from class: kotlinx.coroutines.debug.internal.DebugProbesImpl$dumpCoroutinesInfo$$inlined$dumpCoroutinesInfoImpl$1
                    @Override // kotlin.jvm.functions.Function1
                    @Nullable
                    public final DebugCoroutineInfo invoke(@NotNull DebugProbesImpl.a<?> aVar) {
                        CoroutineContext context;
                        if (DebugProbesImpl.INSTANCE.j(aVar) || (context = aVar.i.getContext()) == null) {
                            return null;
                        }
                        return new DebugCoroutineInfo(aVar.i, context);
                    }
                }));
            }
            throw new IllegalStateException("Debug probes are not installed".toString());
        } finally {
            while (i2 < readHoldCount) {
                i2++;
                readLock.lock();
            }
            writeLock.unlock();
        }
    }

    @NotNull
    public final Object[] dumpCoroutinesInfoAsJsonAndReferences() {
        String name;
        List<DebugCoroutineInfo> dumpCoroutinesInfo = dumpCoroutinesInfo();
        int size = dumpCoroutinesInfo.size();
        ArrayList arrayList = new ArrayList(size);
        ArrayList arrayList2 = new ArrayList(size);
        ArrayList arrayList3 = new ArrayList(size);
        for (DebugCoroutineInfo debugCoroutineInfo : dumpCoroutinesInfo) {
            CoroutineContext context = debugCoroutineInfo.getContext();
            CoroutineName coroutineName = (CoroutineName) context.get(CoroutineName.Key);
            Long l = null;
            String u = (coroutineName == null || (name = coroutineName.getName()) == null) ? null : u(name);
            CoroutineDispatcher coroutineDispatcher = (CoroutineDispatcher) context.get(CoroutineDispatcher.Key);
            String u2 = coroutineDispatcher == null ? null : u(coroutineDispatcher);
            StringBuilder sb = new StringBuilder();
            sb.append("\n                {\n                    \"name\": ");
            sb.append((Object) u);
            sb.append(",\n                    \"id\": ");
            CoroutineId coroutineId = (CoroutineId) context.get(CoroutineId.Key);
            if (coroutineId != null) {
                l = Long.valueOf(coroutineId.getId());
            }
            sb.append(l);
            sb.append(",\n                    \"dispatcher\": ");
            sb.append((Object) u2);
            sb.append(",\n                    \"sequenceNumber\": ");
            sb.append(debugCoroutineInfo.getSequenceNumber());
            sb.append(",\n                    \"state\": \"");
            sb.append(debugCoroutineInfo.getState());
            sb.append("\"\n                } \n                ");
            arrayList3.add(kotlin.text.f.trimIndent(sb.toString()));
            arrayList2.add(debugCoroutineInfo.getLastObservedFrame());
            arrayList.add(debugCoroutineInfo.getLastObservedThread());
        }
        Object[] array = arrayList.toArray(new Thread[0]);
        Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        Object[] array2 = arrayList2.toArray(new CoroutineStackFrame[0]);
        Objects.requireNonNull(array2, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        Object[] array3 = dumpCoroutinesInfo.toArray(new DebugCoroutineInfo[0]);
        Objects.requireNonNull(array3, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        return new Object[]{'[' + CollectionsKt___CollectionsKt.joinToString$default(arrayList3, null, null, null, 0, null, null, 63, null) + ']', array, array2, array3};
    }

    @NotNull
    public final List<DebuggerInfo> dumpDebuggerInfo() {
        ReentrantReadWriteLock reentrantReadWriteLock = f;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i2 = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        int i3 = 0;
        while (i3 < readHoldCount) {
            i3++;
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            DebugProbesImpl debugProbesImpl = INSTANCE;
            if (debugProbesImpl.isInstalled$kotlinx_coroutines_core()) {
                return SequencesKt___SequencesKt.toList(SequencesKt___SequencesKt.mapNotNull(SequencesKt___SequencesKt.sortedWith(CollectionsKt___CollectionsKt.asSequence(debugProbesImpl.g()), new DebugProbesImpl$dumpCoroutinesInfoImpl$lambda12$$inlined$sortedBy$1()), new Function1<a<?>, DebuggerInfo>() { // from class: kotlinx.coroutines.debug.internal.DebugProbesImpl$dumpDebuggerInfo$$inlined$dumpCoroutinesInfoImpl$1
                    @Override // kotlin.jvm.functions.Function1
                    @Nullable
                    public final DebuggerInfo invoke(@NotNull DebugProbesImpl.a<?> aVar) {
                        CoroutineContext context;
                        if (DebugProbesImpl.INSTANCE.j(aVar) || (context = aVar.i.getContext()) == null) {
                            return null;
                        }
                        return new DebuggerInfo(aVar.i, context);
                    }
                }));
            }
            throw new IllegalStateException("Debug probes are not installed".toString());
        } finally {
            while (i2 < readHoldCount) {
                i2++;
                readLock.lock();
            }
            writeLock.unlock();
        }
    }

    public final Pair<Integer, Integer> e(int i2, StackTraceElement[] stackTraceElementArr, List<StackTraceElement> list) {
        int i3 = 0;
        while (i3 < 3) {
            int i4 = i3 + 1;
            int f2 = INSTANCE.f((i2 - 1) - i3, stackTraceElementArr, list);
            if (f2 != -1) {
                return TuplesKt.to(Integer.valueOf(f2), Integer.valueOf(i3));
            }
            i3 = i4;
        }
        return TuplesKt.to(-1, 0);
    }

    @NotNull
    public final List<StackTraceElement> enhanceStackTraceWithThreadDump(@NotNull DebugCoroutineInfo debugCoroutineInfo, @NotNull List<StackTraceElement> list) {
        return d(debugCoroutineInfo.getState(), debugCoroutineInfo.getLastObservedThread(), list);
    }

    @NotNull
    public final String enhanceStackTraceWithThreadDumpAsJson(@NotNull DebugCoroutineInfo debugCoroutineInfo) {
        List<StackTraceElement> enhanceStackTraceWithThreadDump = enhanceStackTraceWithThreadDump(debugCoroutineInfo, debugCoroutineInfo.lastObservedStackTrace());
        ArrayList arrayList = new ArrayList();
        for (StackTraceElement stackTraceElement : enhanceStackTraceWithThreadDump) {
            StringBuilder sb = new StringBuilder();
            sb.append("\n                {\n                    \"declaringClass\": \"");
            sb.append((Object) stackTraceElement.getClassName());
            sb.append("\",\n                    \"methodName\": \"");
            sb.append((Object) stackTraceElement.getMethodName());
            sb.append("\",\n                    \"fileName\": ");
            String fileName = stackTraceElement.getFileName();
            sb.append((Object) (fileName == null ? null : u(fileName)));
            sb.append(",\n                    \"lineNumber\": ");
            sb.append(stackTraceElement.getLineNumber());
            sb.append("\n                }\n                ");
            arrayList.add(kotlin.text.f.trimIndent(sb.toString()));
        }
        return '[' + CollectionsKt___CollectionsKt.joinToString$default(arrayList, null, null, null, 0, null, null, 63, null) + ']';
    }

    public final int f(int i2, StackTraceElement[] stackTraceElementArr, List<StackTraceElement> list) {
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt___ArraysKt.getOrNull(stackTraceElementArr, i2);
        if (stackTraceElement == null) {
            return -1;
        }
        int i3 = 0;
        for (StackTraceElement stackTraceElement2 : list) {
            if (Intrinsics.areEqual(stackTraceElement2.getFileName(), stackTraceElement.getFileName()) && Intrinsics.areEqual(stackTraceElement2.getClassName(), stackTraceElement.getClassName()) && Intrinsics.areEqual(stackTraceElement2.getMethodName(), stackTraceElement.getMethodName())) {
                return i3;
            }
            i3++;
        }
        return -1;
    }

    public final Set<a<?>> g() {
        return c.keySet();
    }

    public final boolean getEnableCreationStackTraces() {
        return h;
    }

    public final boolean getSanitizeStackTraces() {
        return g;
    }

    public final String h(Job job) {
        return job instanceof JobSupport ? ((JobSupport) job).toDebugString() : job.toString();
    }

    @NotNull
    public final String hierarchyToString(@NotNull Job job) {
        ReentrantReadWriteLock reentrantReadWriteLock = f;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i2 = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        int i3 = 0;
        while (i3 < readHoldCount) {
            i3++;
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            DebugProbesImpl debugProbesImpl = INSTANCE;
            if (debugProbesImpl.isInstalled$kotlinx_coroutines_core()) {
                Set<a<?>> g2 = debugProbesImpl.g();
                ArrayList arrayList = new ArrayList();
                for (Object obj : g2) {
                    if (((a) obj).h.getContext().get(Job.Key) != null) {
                        arrayList.add(obj);
                    }
                }
                LinkedHashMap linkedHashMap = new LinkedHashMap(h.coerceAtLeast(r.mapCapacity(kotlin.collections.f.collectionSizeOrDefault(arrayList, 10)), 16));
                for (Object obj2 : arrayList) {
                    linkedHashMap.put(JobKt.getJob(((a) obj2).h.getContext()), ((a) obj2).i);
                }
                StringBuilder sb = new StringBuilder();
                INSTANCE.a(job, linkedHashMap, sb, "");
                String sb2 = sb.toString();
                Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
                return sb2;
            }
            throw new IllegalStateException("Debug probes are not installed".toString());
        } finally {
            while (i2 < readHoldCount) {
                i2++;
                readLock.lock();
            }
            writeLock.unlock();
        }
    }

    public final Function1<Boolean, Unit> i() {
        Object m123constructorimpl;
        Object newInstance;
        try {
            Result.Companion companion = Result.Companion;
            newInstance = Class.forName("kotlinx.coroutines.debug.internal.ByteBuddyDynamicAttach").getConstructors()[0].newInstance(new Object[0]);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            m123constructorimpl = Result.m123constructorimpl(ResultKt.createFailure(th));
        }
        if (newInstance != null) {
            m123constructorimpl = Result.m123constructorimpl((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(newInstance, 1));
            if (Result.m129isFailureimpl(m123constructorimpl)) {
                m123constructorimpl = null;
            }
            return (Function1) m123constructorimpl;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Function1<kotlin.Boolean, kotlin.Unit>");
    }

    public final void install() {
        ReentrantReadWriteLock reentrantReadWriteLock = f;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i2 = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        int i3 = 0;
        while (i3 < readHoldCount) {
            i3++;
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            DebugProbesImpl debugProbesImpl = INSTANCE;
            installations++;
            if (installations > 1) {
                return;
            }
            debugProbesImpl.r();
            if (AgentInstallationType.INSTANCE.isInstalledStatically$kotlinx_coroutines_core()) {
                while (i2 < readHoldCount) {
                    i2++;
                    readLock.lock();
                }
                writeLock.unlock();
                return;
            }
            Function1<Boolean, Unit> function1 = i;
            if (function1 != null) {
                function1.invoke(Boolean.TRUE);
            }
            Unit unit = Unit.INSTANCE;
            while (i2 < readHoldCount) {
                i2++;
                readLock.lock();
            }
            writeLock.unlock();
        } finally {
            while (i2 < readHoldCount) {
                i2++;
                readLock.lock();
            }
            writeLock.unlock();
        }
    }

    public final boolean isInstalled$kotlinx_coroutines_core() {
        return installations > 0;
    }

    public final boolean j(a<?> aVar) {
        CoroutineContext context = aVar.i.getContext();
        Job job = context == null ? null : (Job) context.get(Job.Key);
        if (job != null && job.isCompleted()) {
            c.remove(aVar);
            return true;
        }
        return false;
    }

    public final boolean k(StackTraceElement stackTraceElement) {
        return m.startsWith$default(stackTraceElement.getClassName(), "kotlinx.coroutines", false, 2, null);
    }

    public final a<?> l(Continuation<?> continuation) {
        CoroutineStackFrame coroutineStackFrame = continuation instanceof CoroutineStackFrame ? (CoroutineStackFrame) continuation : null;
        if (coroutineStackFrame == null) {
            return null;
        }
        return m(coroutineStackFrame);
    }

    public final a<?> m(CoroutineStackFrame coroutineStackFrame) {
        while (!(coroutineStackFrame instanceof a)) {
            coroutineStackFrame = coroutineStackFrame.getCallerFrame();
            if (coroutineStackFrame == null) {
                return null;
            }
        }
        return (a) coroutineStackFrame;
    }

    public final void n(PrintStream printStream, List<StackTraceElement> list) {
        for (StackTraceElement stackTraceElement : list) {
            printStream.print(Intrinsics.stringPlus("\n\tat ", stackTraceElement));
        }
    }

    public final void o(a<?> aVar) {
        c.remove(aVar);
        CoroutineStackFrame lastObservedFrame$kotlinx_coroutines_core = aVar.i.getLastObservedFrame$kotlinx_coroutines_core();
        CoroutineStackFrame p = lastObservedFrame$kotlinx_coroutines_core == null ? null : p(lastObservedFrame$kotlinx_coroutines_core);
        if (p == null) {
            return;
        }
        j.remove(p);
    }

    public final CoroutineStackFrame p(CoroutineStackFrame coroutineStackFrame) {
        do {
            coroutineStackFrame = coroutineStackFrame.getCallerFrame();
            if (coroutineStackFrame == null) {
                return null;
            }
        } while (coroutineStackFrame.getStackTraceElement() == null);
        return coroutineStackFrame;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public final <T> Continuation<T> probeCoroutineCreated$kotlinx_coroutines_core(@NotNull Continuation<? super T> continuation) {
        if (isInstalled$kotlinx_coroutines_core() && l(continuation) == null) {
            return b(continuation, h ? t(q(new Exception())) : null);
        }
        return continuation;
    }

    public final void probeCoroutineResumed$kotlinx_coroutines_core(@NotNull Continuation<?> continuation) {
        w(continuation, DebugCoroutineInfoImplKt.RUNNING);
    }

    public final void probeCoroutineSuspended$kotlinx_coroutines_core(@NotNull Continuation<?> continuation) {
        w(continuation, DebugCoroutineInfoImplKt.SUSPENDED);
    }

    public final <T extends Throwable> List<StackTraceElement> q(T t) {
        StackTraceElement[] stackTrace = t.getStackTrace();
        int length = stackTrace.length;
        int i2 = -1;
        int length2 = stackTrace.length - 1;
        if (length2 >= 0) {
            while (true) {
                int i3 = length2 - 1;
                if (Intrinsics.areEqual(stackTrace[length2].getClassName(), "kotlin.coroutines.jvm.internal.DebugProbesKt")) {
                    i2 = length2;
                    break;
                } else if (i3 < 0) {
                    break;
                } else {
                    length2 = i3;
                }
            }
        }
        if (!g) {
            int i4 = length - i2;
            ArrayList arrayList = new ArrayList(i4);
            int i5 = 0;
            while (i5 < i4) {
                int i6 = i5 + 1;
                arrayList.add(i5 == 0 ? StackTraceRecoveryKt.artificialFrame("Coroutine creation stacktrace") : stackTrace[i5 + i2]);
                i5 = i6;
            }
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList((length - i2) + 1);
        arrayList2.add(StackTraceRecoveryKt.artificialFrame("Coroutine creation stacktrace"));
        while (true) {
            i2++;
            while (i2 < length) {
                if (k(stackTrace[i2])) {
                    arrayList2.add(stackTrace[i2]);
                    int i7 = i2 + 1;
                    while (i7 < length && k(stackTrace[i7])) {
                        i7++;
                    }
                    int i8 = i7 - 1;
                    int i9 = i8;
                    while (i9 > i2 && stackTrace[i9].getFileName() == null) {
                        i9--;
                    }
                    if (i9 > i2 && i9 < i8) {
                        arrayList2.add(stackTrace[i9]);
                    }
                    arrayList2.add(stackTrace[i8]);
                    i2 = i7;
                }
            }
            return arrayList2;
            arrayList2.add(stackTrace[i2]);
        }
    }

    public final void r() {
        b = ThreadsKt.thread$default(false, true, null, "Coroutines Debugger Cleaner", 0, c.INSTANCE, 21, null);
    }

    public final void s() {
        Thread thread = b;
        if (thread == null) {
            return;
        }
        b = null;
        thread.interrupt();
        thread.join();
    }

    public final void setEnableCreationStackTraces(boolean z) {
        h = z;
    }

    public final void setSanitizeStackTraces(boolean z) {
        g = z;
    }

    public final StackTraceFrame t(List<StackTraceElement> list) {
        StackTraceFrame stackTraceFrame = null;
        if (!list.isEmpty()) {
            ListIterator<StackTraceElement> listIterator = list.listIterator(list.size());
            while (listIterator.hasPrevious()) {
                stackTraceFrame = new StackTraceFrame(stackTraceFrame, listIterator.previous());
            }
        }
        return stackTraceFrame;
    }

    public final String u(Object obj) {
        StringBuilder sb = new StringBuilder();
        sb.append(Typography.quote);
        sb.append(obj);
        sb.append(Typography.quote);
        return sb.toString();
    }

    public final void uninstall() {
        ReentrantReadWriteLock reentrantReadWriteLock = f;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i2 = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        int i3 = 0;
        while (i3 < readHoldCount) {
            i3++;
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            DebugProbesImpl debugProbesImpl = INSTANCE;
            if (debugProbesImpl.isInstalled$kotlinx_coroutines_core()) {
                installations--;
                if (installations != 0) {
                    return;
                }
                debugProbesImpl.s();
                c.clear();
                j.clear();
                if (AgentInstallationType.INSTANCE.isInstalledStatically$kotlinx_coroutines_core()) {
                    while (i2 < readHoldCount) {
                        i2++;
                        readLock.lock();
                    }
                    writeLock.unlock();
                    return;
                }
                Function1<Boolean, Unit> function1 = i;
                if (function1 != null) {
                    function1.invoke(Boolean.FALSE);
                }
                Unit unit = Unit.INSTANCE;
                while (i2 < readHoldCount) {
                    i2++;
                    readLock.lock();
                }
                writeLock.unlock();
                return;
            }
            throw new IllegalStateException("Agent was not installed".toString());
        } finally {
            while (i2 < readHoldCount) {
                i2++;
                readLock.lock();
            }
            writeLock.unlock();
        }
    }

    public final void v(CoroutineStackFrame coroutineStackFrame, String str) {
        ReentrantReadWriteLock.ReadLock readLock = f.readLock();
        readLock.lock();
        try {
            DebugProbesImpl debugProbesImpl = INSTANCE;
            if (debugProbesImpl.isInstalled$kotlinx_coroutines_core()) {
                ConcurrentWeakMap<CoroutineStackFrame, DebugCoroutineInfoImpl> concurrentWeakMap = j;
                DebugCoroutineInfoImpl remove = concurrentWeakMap.remove(coroutineStackFrame);
                if (remove == null) {
                    a<?> m = debugProbesImpl.m(coroutineStackFrame);
                    CoroutineStackFrame coroutineStackFrame2 = null;
                    remove = m == null ? null : m.i;
                    if (remove == null) {
                        return;
                    }
                    CoroutineStackFrame lastObservedFrame$kotlinx_coroutines_core = remove.getLastObservedFrame$kotlinx_coroutines_core();
                    if (lastObservedFrame$kotlinx_coroutines_core != null) {
                        coroutineStackFrame2 = debugProbesImpl.p(lastObservedFrame$kotlinx_coroutines_core);
                    }
                    if (coroutineStackFrame2 != null) {
                        concurrentWeakMap.remove(coroutineStackFrame2);
                    }
                }
                remove.updateState$kotlinx_coroutines_core(str, (Continuation) coroutineStackFrame);
                CoroutineStackFrame p = debugProbesImpl.p(coroutineStackFrame);
                if (p == null) {
                    return;
                }
                concurrentWeakMap.put(p, remove);
                Unit unit = Unit.INSTANCE;
            }
        } finally {
            readLock.unlock();
        }
    }

    public final void w(Continuation<?> continuation, String str) {
        if (isInstalled$kotlinx_coroutines_core()) {
            if (Intrinsics.areEqual(str, DebugCoroutineInfoImplKt.RUNNING) && KotlinVersion.CURRENT.isAtLeast(1, 3, 30)) {
                CoroutineStackFrame coroutineStackFrame = continuation instanceof CoroutineStackFrame ? (CoroutineStackFrame) continuation : null;
                if (coroutineStackFrame == null) {
                    return;
                }
                v(coroutineStackFrame, str);
                return;
            }
            a<?> l = l(continuation);
            if (l == null) {
                return;
            }
            x(l, continuation, str);
        }
    }

    public final void x(a<?> aVar, Continuation<?> continuation, String str) {
        ReentrantReadWriteLock.ReadLock readLock = f.readLock();
        readLock.lock();
        try {
            if (INSTANCE.isInstalled$kotlinx_coroutines_core()) {
                aVar.i.updateState$kotlinx_coroutines_core(str, continuation);
                Unit unit = Unit.INSTANCE;
            }
        } finally {
            readLock.unlock();
        }
    }
}
