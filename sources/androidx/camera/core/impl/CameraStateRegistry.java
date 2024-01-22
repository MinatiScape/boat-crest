package androidx.camera.core.impl;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.camera.core.Camera;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.CameraStateRegistry;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
/* loaded from: classes.dex */
public final class CameraStateRegistry {
    public final int c;
    @GuardedBy("mLock")
    public int e;

    /* renamed from: a  reason: collision with root package name */
    public final StringBuilder f695a = new StringBuilder();
    public final Object b = new Object();
    @GuardedBy("mLock")
    public final Map<Camera, a> d = new HashMap();

    /* loaded from: classes.dex */
    public interface OnOpenAvailableListener {
        void onOpenAvailable();
    }

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public CameraInternal.State f696a;
        public final Executor b;
        public final OnOpenAvailableListener c;

        public a(@Nullable CameraInternal.State state, @NonNull Executor executor, @NonNull OnOpenAvailableListener onOpenAvailableListener) {
            this.f696a = state;
            this.b = executor;
            this.c = onOpenAvailableListener;
        }

        public CameraInternal.State a() {
            return this.f696a;
        }

        public void b() {
            try {
                Executor executor = this.b;
                final OnOpenAvailableListener onOpenAvailableListener = this.c;
                Objects.requireNonNull(onOpenAvailableListener);
                executor.execute(new Runnable() { // from class: androidx.camera.core.impl.j
                    @Override // java.lang.Runnable
                    public final void run() {
                        CameraStateRegistry.OnOpenAvailableListener.this.onOpenAvailable();
                    }
                });
            } catch (RejectedExecutionException e) {
                Logger.e("CameraStateRegistry", "Unable to notify camera.", e);
            }
        }

        public CameraInternal.State c(@Nullable CameraInternal.State state) {
            CameraInternal.State state2 = this.f696a;
            this.f696a = state;
            return state2;
        }
    }

    public CameraStateRegistry(int i) {
        this.c = i;
        synchronized ("mLock") {
            this.e = i;
        }
    }

    public static boolean a(@Nullable CameraInternal.State state) {
        return state != null && state.holdsCameraSlot();
    }

    @GuardedBy("mLock")
    @WorkerThread
    public final void b() {
        if (Logger.isDebugEnabled("CameraStateRegistry")) {
            this.f695a.setLength(0);
            this.f695a.append("Recalculating open cameras:\n");
            this.f695a.append(String.format(Locale.US, "%-45s%-22s\n", "Camera", "State"));
            this.f695a.append("-------------------------------------------------------------------\n");
        }
        int i = 0;
        for (Map.Entry<Camera, a> entry : this.d.entrySet()) {
            if (Logger.isDebugEnabled("CameraStateRegistry")) {
                this.f695a.append(String.format(Locale.US, "%-45s%-22s\n", entry.getKey().toString(), entry.getValue().a() != null ? entry.getValue().a().toString() : "UNKNOWN"));
            }
            if (a(entry.getValue().a())) {
                i++;
            }
        }
        if (Logger.isDebugEnabled("CameraStateRegistry")) {
            this.f695a.append("-------------------------------------------------------------------\n");
            this.f695a.append(String.format(Locale.US, "Open count: %d (Max allowed: %d)", Integer.valueOf(i), Integer.valueOf(this.c)));
            Logger.d("CameraStateRegistry", this.f695a.toString());
        }
        this.e = Math.max(this.c - i, 0);
    }

    @Nullable
    @GuardedBy("mLock")
    public final CameraInternal.State c(Camera camera) {
        a remove = this.d.remove(camera);
        if (remove != null) {
            b();
            return remove.a();
        }
        return null;
    }

    @Nullable
    @GuardedBy("mLock")
    public final CameraInternal.State d(@NonNull Camera camera, @NonNull CameraInternal.State state) {
        CameraInternal.State c = ((a) Preconditions.checkNotNull(this.d.get(camera), "Cannot update state of camera which has not yet been registered. Register with CameraAvailabilityRegistry.registerCamera()")).c(state);
        CameraInternal.State state2 = CameraInternal.State.OPENING;
        if (state == state2) {
            Preconditions.checkState(a(state) || c == state2, "Cannot mark camera as opening until camera was successful at calling CameraAvailabilityRegistry.tryOpen()");
        }
        if (c != state) {
            b();
        }
        return c;
    }

    public void markCameraState(@NonNull Camera camera, @NonNull CameraInternal.State state) {
        CameraInternal.State d;
        List<a> singletonList;
        synchronized (this.b) {
            int i = this.e;
            if (state == CameraInternal.State.RELEASED) {
                d = c(camera);
            } else {
                d = d(camera, state);
            }
            if (d == state) {
                return;
            }
            if (i < 1 && this.e > 0) {
                singletonList = new ArrayList();
                for (Map.Entry<Camera, a> entry : this.d.entrySet()) {
                    if (entry.getValue().a() == CameraInternal.State.PENDING_OPEN) {
                        singletonList.add(entry.getValue());
                    }
                }
            } else {
                singletonList = (state != CameraInternal.State.PENDING_OPEN || this.e <= 0) ? null : Collections.singletonList(this.d.get(camera));
            }
            if (singletonList != null) {
                for (a aVar : singletonList) {
                    aVar.b();
                }
            }
        }
    }

    public void registerCamera(@NonNull Camera camera, @NonNull Executor executor, @NonNull OnOpenAvailableListener onOpenAvailableListener) {
        synchronized (this.b) {
            boolean z = !this.d.containsKey(camera);
            Preconditions.checkState(z, "Camera is already registered: " + camera);
            this.d.put(camera, new a(null, executor, onOpenAvailableListener));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0071 A[Catch: all -> 0x009b, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x001d, B:7:0x0052, B:9:0x0056, B:14:0x0069, B:16:0x0071, B:20:0x0080, B:22:0x0096, B:23:0x0099, B:13:0x0063), top: B:28:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0096 A[Catch: all -> 0x009b, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x001d, B:7:0x0052, B:9:0x0056, B:14:0x0069, B:16:0x0071, B:20:0x0080, B:22:0x0096, B:23:0x0099, B:13:0x0063), top: B:28:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean tryOpenCamera(@androidx.annotation.NonNull androidx.camera.core.Camera r10) {
        /*
            r9 = this;
            java.lang.Object r0 = r9.b
            monitor-enter(r0)
            java.util.Map<androidx.camera.core.Camera, androidx.camera.core.impl.CameraStateRegistry$a> r1 = r9.d     // Catch: java.lang.Throwable -> L9b
            java.lang.Object r1 = r1.get(r10)     // Catch: java.lang.Throwable -> L9b
            androidx.camera.core.impl.CameraStateRegistry$a r1 = (androidx.camera.core.impl.CameraStateRegistry.a) r1     // Catch: java.lang.Throwable -> L9b
            java.lang.String r2 = "Camera must first be registered with registerCamera()"
            java.lang.Object r1 = androidx.core.util.Preconditions.checkNotNull(r1, r2)     // Catch: java.lang.Throwable -> L9b
            androidx.camera.core.impl.CameraStateRegistry$a r1 = (androidx.camera.core.impl.CameraStateRegistry.a) r1     // Catch: java.lang.Throwable -> L9b
            java.lang.String r2 = "CameraStateRegistry"
            boolean r2 = androidx.camera.core.Logger.isDebugEnabled(r2)     // Catch: java.lang.Throwable -> L9b
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L52
            java.lang.StringBuilder r2 = r9.f695a     // Catch: java.lang.Throwable -> L9b
            r2.setLength(r4)     // Catch: java.lang.Throwable -> L9b
            java.lang.StringBuilder r2 = r9.f695a     // Catch: java.lang.Throwable -> L9b
            java.util.Locale r5 = java.util.Locale.US     // Catch: java.lang.Throwable -> L9b
            java.lang.String r6 = "tryOpenCamera(%s) [Available Cameras: %d, Already Open: %b (Previous state: %s)]"
            r7 = 4
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch: java.lang.Throwable -> L9b
            r7[r4] = r10     // Catch: java.lang.Throwable -> L9b
            int r10 = r9.e     // Catch: java.lang.Throwable -> L9b
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch: java.lang.Throwable -> L9b
            r7[r3] = r10     // Catch: java.lang.Throwable -> L9b
            r10 = 2
            androidx.camera.core.impl.CameraInternal$State r8 = r1.a()     // Catch: java.lang.Throwable -> L9b
            boolean r8 = a(r8)     // Catch: java.lang.Throwable -> L9b
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r8)     // Catch: java.lang.Throwable -> L9b
            r7[r10] = r8     // Catch: java.lang.Throwable -> L9b
            r10 = 3
            androidx.camera.core.impl.CameraInternal$State r8 = r1.a()     // Catch: java.lang.Throwable -> L9b
            r7[r10] = r8     // Catch: java.lang.Throwable -> L9b
            java.lang.String r10 = java.lang.String.format(r5, r6, r7)     // Catch: java.lang.Throwable -> L9b
            r2.append(r10)     // Catch: java.lang.Throwable -> L9b
        L52:
            int r10 = r9.e     // Catch: java.lang.Throwable -> L9b
            if (r10 > 0) goto L63
            androidx.camera.core.impl.CameraInternal$State r10 = r1.a()     // Catch: java.lang.Throwable -> L9b
            boolean r10 = a(r10)     // Catch: java.lang.Throwable -> L9b
            if (r10 == 0) goto L61
            goto L63
        L61:
            r10 = r4
            goto L69
        L63:
            androidx.camera.core.impl.CameraInternal$State r10 = androidx.camera.core.impl.CameraInternal.State.OPENING     // Catch: java.lang.Throwable -> L9b
            r1.c(r10)     // Catch: java.lang.Throwable -> L9b
            r10 = r3
        L69:
            java.lang.String r1 = "CameraStateRegistry"
            boolean r1 = androidx.camera.core.Logger.isDebugEnabled(r1)     // Catch: java.lang.Throwable -> L9b
            if (r1 == 0) goto L94
            java.lang.StringBuilder r1 = r9.f695a     // Catch: java.lang.Throwable -> L9b
            java.util.Locale r2 = java.util.Locale.US     // Catch: java.lang.Throwable -> L9b
            java.lang.String r5 = " --> %s"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L9b
            if (r10 == 0) goto L7e
            java.lang.String r6 = "SUCCESS"
            goto L80
        L7e:
            java.lang.String r6 = "FAIL"
        L80:
            r3[r4] = r6     // Catch: java.lang.Throwable -> L9b
            java.lang.String r2 = java.lang.String.format(r2, r5, r3)     // Catch: java.lang.Throwable -> L9b
            r1.append(r2)     // Catch: java.lang.Throwable -> L9b
            java.lang.String r1 = "CameraStateRegistry"
            java.lang.StringBuilder r2 = r9.f695a     // Catch: java.lang.Throwable -> L9b
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L9b
            androidx.camera.core.Logger.d(r1, r2)     // Catch: java.lang.Throwable -> L9b
        L94:
            if (r10 == 0) goto L99
            r9.b()     // Catch: java.lang.Throwable -> L9b
        L99:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L9b
            return r10
        L9b:
            r10 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L9b
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.impl.CameraStateRegistry.tryOpenCamera(androidx.camera.core.Camera):boolean");
    }
}
