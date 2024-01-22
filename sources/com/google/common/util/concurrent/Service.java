package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import kotlinx.coroutines.debug.internal.DebugCoroutineInfoImplKt;
@DoNotMock("Create an AbstractIdleService")
@GwtIncompatible
/* loaded from: classes10.dex */
public interface Service {

    /* loaded from: classes10.dex */
    public static abstract class Listener {
        public void failed(State state, Throwable th) {
        }

        public void running() {
        }

        public void starting() {
        }

        public void stopping(State state) {
        }

        public void terminated(State state) {
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes10.dex */
    public static abstract class State {
        public static final State NEW = new a("NEW", 0);
        public static final State STARTING = new b("STARTING", 1);
        public static final State RUNNING = new c(DebugCoroutineInfoImplKt.RUNNING, 2);
        public static final State STOPPING = new d("STOPPING", 3);
        public static final State TERMINATED = new e("TERMINATED", 4);
        public static final State FAILED = new f("FAILED", 5);
        private static final /* synthetic */ State[] $VALUES = $values();

        /* loaded from: classes10.dex */
        public enum a extends State {
            public a(String str, int i) {
                super(str, i);
            }

            @Override // com.google.common.util.concurrent.Service.State
            public boolean isTerminal() {
                return false;
            }
        }

        /* loaded from: classes10.dex */
        public enum b extends State {
            public b(String str, int i) {
                super(str, i);
            }

            @Override // com.google.common.util.concurrent.Service.State
            public boolean isTerminal() {
                return false;
            }
        }

        /* loaded from: classes10.dex */
        public enum c extends State {
            public c(String str, int i) {
                super(str, i);
            }

            @Override // com.google.common.util.concurrent.Service.State
            public boolean isTerminal() {
                return false;
            }
        }

        /* loaded from: classes10.dex */
        public enum d extends State {
            public d(String str, int i) {
                super(str, i);
            }

            @Override // com.google.common.util.concurrent.Service.State
            public boolean isTerminal() {
                return false;
            }
        }

        /* loaded from: classes10.dex */
        public enum e extends State {
            public e(String str, int i) {
                super(str, i);
            }

            @Override // com.google.common.util.concurrent.Service.State
            public boolean isTerminal() {
                return true;
            }
        }

        /* loaded from: classes10.dex */
        public enum f extends State {
            public f(String str, int i) {
                super(str, i);
            }

            @Override // com.google.common.util.concurrent.Service.State
            public boolean isTerminal() {
                return true;
            }
        }

        private static /* synthetic */ State[] $values() {
            return new State[]{NEW, STARTING, RUNNING, STOPPING, TERMINATED, FAILED};
        }

        private State(String str, int i) {
        }

        public static State valueOf(String str) {
            return (State) Enum.valueOf(State.class, str);
        }

        public static State[] values() {
            return (State[]) $VALUES.clone();
        }

        public abstract boolean isTerminal();
    }

    void addListener(Listener listener, Executor executor);

    void awaitRunning();

    void awaitRunning(long j, TimeUnit timeUnit) throws TimeoutException;

    void awaitTerminated();

    void awaitTerminated(long j, TimeUnit timeUnit) throws TimeoutException;

    Throwable failureCause();

    boolean isRunning();

    @CanIgnoreReturnValue
    Service startAsync();

    State state();

    @CanIgnoreReturnValue
    Service stopAsync();
}
