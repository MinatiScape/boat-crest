package com.google.common.eventbus;

import com.google.common.base.Preconditions;
import com.google.common.collect.Queues;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
/* loaded from: classes10.dex */
public abstract class a {

    /* loaded from: classes10.dex */
    public static final class b extends a {

        /* renamed from: a  reason: collision with root package name */
        public final ConcurrentLinkedQueue<C0497a> f10610a;

        /* renamed from: com.google.common.eventbus.a$b$a  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public static final class C0497a {

            /* renamed from: a  reason: collision with root package name */
            public final Object f10611a;
            public final com.google.common.eventbus.b b;

            public C0497a(Object obj, com.google.common.eventbus.b bVar) {
                this.f10611a = obj;
                this.b = bVar;
            }
        }

        public b() {
            this.f10610a = Queues.newConcurrentLinkedQueue();
        }

        @Override // com.google.common.eventbus.a
        public void a(Object obj, Iterator<com.google.common.eventbus.b> it) {
            Preconditions.checkNotNull(obj);
            while (it.hasNext()) {
                this.f10610a.add(new C0497a(obj, it.next()));
            }
            while (true) {
                C0497a poll = this.f10610a.poll();
                if (poll == null) {
                    return;
                }
                poll.b.e(poll.f10611a);
            }
        }
    }

    /* loaded from: classes10.dex */
    public static final class c extends a {

        /* renamed from: a  reason: collision with root package name */
        public final ThreadLocal<Queue<C0499c>> f10612a;
        public final ThreadLocal<Boolean> b;

        /* renamed from: com.google.common.eventbus.a$c$a  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class C0498a extends ThreadLocal<Queue<C0499c>> {
            public C0498a(c cVar) {
            }

            @Override // java.lang.ThreadLocal
            /* renamed from: a */
            public Queue<C0499c> initialValue() {
                return Queues.newArrayDeque();
            }
        }

        /* loaded from: classes10.dex */
        public class b extends ThreadLocal<Boolean> {
            public b(c cVar) {
            }

            @Override // java.lang.ThreadLocal
            /* renamed from: a */
            public Boolean initialValue() {
                return Boolean.FALSE;
            }
        }

        /* renamed from: com.google.common.eventbus.a$c$c  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public static final class C0499c {

            /* renamed from: a  reason: collision with root package name */
            public final Object f10613a;
            public final Iterator<com.google.common.eventbus.b> b;

            public C0499c(Object obj, Iterator<com.google.common.eventbus.b> it) {
                this.f10613a = obj;
                this.b = it;
            }
        }

        public c() {
            this.f10612a = new C0498a(this);
            this.b = new b(this);
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0050 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0034 A[Catch: all -> 0x005b, LOOP:1: B:7:0x0034->B:9:0x003e, LOOP_START, TryCatch #0 {all -> 0x005b, blocks: (B:5:0x002c, B:7:0x0034, B:9:0x003e), top: B:16:0x002c }] */
        @Override // com.google.common.eventbus.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void a(java.lang.Object r4, java.util.Iterator<com.google.common.eventbus.b> r5) {
            /*
                r3 = this;
                com.google.common.base.Preconditions.checkNotNull(r4)
                com.google.common.base.Preconditions.checkNotNull(r5)
                java.lang.ThreadLocal<java.util.Queue<com.google.common.eventbus.a$c$c>> r0 = r3.f10612a
                java.lang.Object r0 = r0.get()
                java.util.Queue r0 = (java.util.Queue) r0
                com.google.common.eventbus.a$c$c r1 = new com.google.common.eventbus.a$c$c
                r2 = 0
                r1.<init>(r4, r5)
                r0.offer(r1)
                java.lang.ThreadLocal<java.lang.Boolean> r4 = r3.b
                java.lang.Object r4 = r4.get()
                java.lang.Boolean r4 = (java.lang.Boolean) r4
                boolean r4 = r4.booleanValue()
                if (r4 != 0) goto L67
                java.lang.ThreadLocal<java.lang.Boolean> r4 = r3.b
                java.lang.Boolean r5 = java.lang.Boolean.TRUE
                r4.set(r5)
            L2c:
                java.lang.Object r4 = r0.poll()     // Catch: java.lang.Throwable -> L5b
                com.google.common.eventbus.a$c$c r4 = (com.google.common.eventbus.a.c.C0499c) r4     // Catch: java.lang.Throwable -> L5b
                if (r4 == 0) goto L50
            L34:
                java.util.Iterator r5 = com.google.common.eventbus.a.c.C0499c.a(r4)     // Catch: java.lang.Throwable -> L5b
                boolean r5 = r5.hasNext()     // Catch: java.lang.Throwable -> L5b
                if (r5 == 0) goto L2c
                java.util.Iterator r5 = com.google.common.eventbus.a.c.C0499c.a(r4)     // Catch: java.lang.Throwable -> L5b
                java.lang.Object r5 = r5.next()     // Catch: java.lang.Throwable -> L5b
                com.google.common.eventbus.b r5 = (com.google.common.eventbus.b) r5     // Catch: java.lang.Throwable -> L5b
                java.lang.Object r1 = com.google.common.eventbus.a.c.C0499c.b(r4)     // Catch: java.lang.Throwable -> L5b
                r5.e(r1)     // Catch: java.lang.Throwable -> L5b
                goto L34
            L50:
                java.lang.ThreadLocal<java.lang.Boolean> r4 = r3.b
                r4.remove()
                java.lang.ThreadLocal<java.util.Queue<com.google.common.eventbus.a$c$c>> r4 = r3.f10612a
                r4.remove()
                goto L67
            L5b:
                r4 = move-exception
                java.lang.ThreadLocal<java.lang.Boolean> r5 = r3.b
                r5.remove()
                java.lang.ThreadLocal<java.util.Queue<com.google.common.eventbus.a$c$c>> r5 = r3.f10612a
                r5.remove()
                throw r4
            L67:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.eventbus.a.c.a(java.lang.Object, java.util.Iterator):void");
        }
    }

    public static a b() {
        return new b();
    }

    public static a c() {
        return new c();
    }

    public abstract void a(Object obj, Iterator<com.google.common.eventbus.b> it);
}
