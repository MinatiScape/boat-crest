package com.goodix.ble.libcomx.task;

import com.goodix.ble.libcomx.ILogger;
import com.goodix.ble.libcomx.annotation.Nullable;
import com.goodix.ble.libcomx.event.Event;
import com.goodix.ble.libcomx.task.TaskError;
import com.goodix.ble.libcomx.util.CallUtil;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
/* loaded from: classes5.dex */
public abstract class Task implements ITask, ITaskResult {
    public static final int STATE_FINISHED = 4;
    public static final int STATE_IDLE = 0;
    public static final int STATE_RUNNING = 2;
    public static final int STATE_STARTING = 1;
    public static final int STATE_STOPPING = 3;
    public static ILogger debugLogger;
    public String h;
    public Executor i;
    public boolean isAborted;
    public boolean j;
    public boolean k;
    public final boolean l;
    @TaskParameter(nullable = true)
    public ILogger logger;
    public boolean m;
    public final Event<Void> n;
    public final Event<Integer> o;
    public final Event<ITaskResult> p;
    public ITask prevTask;
    public boolean printVerboseLog;
    public boolean q;
    @Nullable
    public HashMap<String, Object> r;
    public ITaskContext rootCtx;
    public int s;
    public TaskError t;
    public int taskState;
    @Nullable
    public Timer u;
    public boolean v;
    public long w;
    public long x;
    public int y;

    /* loaded from: classes5.dex */
    public class a extends TimerTask {
        public final /* synthetic */ int h;

        /* renamed from: com.goodix.ble.libcomx.task.Task$a$a  reason: collision with other inner class name */
        /* loaded from: classes5.dex */
        public class RunnableC0366a implements Runnable {
            public RunnableC0366a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (Task.this.j) {
                    return;
                }
                a aVar = a.this;
                int i = aVar.h;
                if (i == -277) {
                    if (System.currentTimeMillis() < Task.this.x) {
                        Task task = Task.this;
                        task.startTimer(-277, task.x - System.currentTimeMillis());
                        return;
                    }
                    Task.this.onTaskExpired();
                    if (Task.this.j) {
                        return;
                    }
                    Task.this.finished(-3, new TaskError.Timeout(Task.this));
                    return;
                }
                try {
                    Task.this.onTimeout(i);
                } catch (Exception e) {
                    Task.this.finishedWithError("Exception in onTimer().", e);
                }
            }
        }

        public a(int i) {
            this.h = i;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            Task.this.getExecutor().execute(new RunnableC0366a());
        }
    }

    public Task() {
        this.h = getClass().getSimpleName();
        this.logger = debugLogger;
        this.isAborted = false;
        this.j = false;
        this.k = false;
        this.m = false;
        this.taskState = 0;
        this.n = new Event<>(this, ITask.EVT_START);
        this.o = new Event<>(this, 341);
        this.p = new Event<>(this, ITask.EVT_FINISH);
        this.q = false;
        this.printVerboseLog = debugLogger != null;
        this.r = null;
        this.v = false;
        this.w = 0L;
        this.x = 0L;
        this.l = true;
    }

    @Override // com.goodix.ble.libcomx.task.ITask
    public void abort() {
        boolean z;
        int i;
        synchronized (this) {
            if (!this.isAborted && ((i = this.taskState) == 2 || i == 1)) {
                this.isAborted = true;
                z = i == 2 ? this.v : false;
            }
        }
        if (z) {
            finished(-2, new TaskError.Abort(this));
        }
    }

    public final TaskError c() {
        Field[] declaredFields;
        for (Class<?> cls = getClass(); cls != null; cls = cls.getSuperclass()) {
            for (Field field : cls.getDeclaredFields()) {
                TaskParameter taskParameter = (TaskParameter) field.getAnnotation(TaskParameter.class);
                if (taskParameter != null) {
                    try {
                        Object parameter = getParameter(field.getType().getName());
                        if (parameter == null) {
                            parameter = getOutput(field.getType().getName());
                        }
                        field.setAccessible(true);
                        if (parameter != null) {
                            field.set(this, parameter);
                            ILogger iLogger = this.logger;
                            if (iLogger != null && this.printVerboseLog) {
                                iLogger.v(getName(), "Acquire parameter: " + field.getName() + " = " + parameter);
                            }
                        } else if (field.get(this) == null && !taskParameter.nullable()) {
                            return new TaskError(this, "Parameter " + field.getName() + " is null.");
                        }
                    } catch (IllegalAccessException e) {
                        return new TaskError(this, "Failed to set parameter " + field.getName(), e);
                    }
                }
            }
            if (cls == Task.class) {
                return null;
            }
        }
        return null;
    }

    @Override // com.goodix.ble.libcomx.task.ITask
    public ITask clearListener(Object obj) {
        this.n.clear(obj);
        this.o.clear(obj);
        this.p.clear(obj);
        return this;
    }

    public abstract int doWork();

    @Override // com.goodix.ble.libcomx.task.ITask
    public final Event<ITaskResult> evtFinished() {
        return this.p;
    }

    @Override // com.goodix.ble.libcomx.task.ITask
    public final Event<Integer> evtProgress() {
        return this.o;
    }

    @Override // com.goodix.ble.libcomx.task.ITask
    public final Event<Void> evtStart() {
        return this.n;
    }

    public final synchronized void finished(int i, TaskError taskError) {
        synchronized (this) {
            if (this.taskState != 2) {
                ILogger iLogger = this.logger;
                if (iLogger != null) {
                    iLogger.w(getName(), "Task is not running. Do not call finished() again with: resultCode = [" + i + "], e = [" + taskError + "], from " + CallUtil.trace(5));
                }
                return;
            }
            this.taskState = 3;
            this.v = false;
            this.j = true;
            this.s = i;
            this.t = taskError;
            ILogger iLogger2 = this.logger;
            TaskError taskError2 = taskError;
            if (iLogger2 != null) {
                if (taskError == null) {
                    if (this.printVerboseLog) {
                        iLogger2.v(getName(), "finished with: resultCode = [" + i + "]");
                    }
                } else {
                    while (taskError2.getCause() != null) {
                        taskError2 = taskError2.getCause();
                    }
                    this.logger.e(getName(), "finished with: resultCode = [" + i + "], rootCause = [" + taskError2.getMessage() + "]");
                }
            }
            try {
                stopTimer();
                onCleanup();
                this.p.postEvent(this);
            } catch (Exception e) {
                synchronized (this) {
                    this.s = -1;
                    this.t = new TaskError(this, "Error is occurred while exiting: " + e.getMessage(), e);
                    try {
                        this.p.postEvent(this);
                    } catch (Exception e2) {
                        ILogger iLogger3 = this.logger;
                        if (iLogger3 != null) {
                            iLogger3.e(getName(), "Error is occurred in Finish Event: " + e2.getMessage(), e2);
                        } else {
                            e2.printStackTrace();
                        }
                    }
                }
            }
            if (this.q || !this.l) {
                clearListener(null);
            }
            synchronized (this) {
                if (this.l) {
                    this.taskState = 0;
                } else {
                    this.taskState = 4;
                }
            }
        }
    }

    public final void finishedWithDone() {
        finished(0, null);
    }

    public final void finishedWithError(int i, String str) {
        finished(i, new TaskError(this, str));
    }

    @Override // com.goodix.ble.libcomx.task.ITaskResult
    public int getCode() {
        return this.s;
    }

    @Override // com.goodix.ble.libcomx.task.ITaskResult
    public TaskError getError() {
        return this.t;
    }

    @Override // com.goodix.ble.libcomx.task.ITaskContext
    public final Executor getExecutor() {
        Executor executor = this.i;
        if (executor == null) {
            ITaskContext iTaskContext = this.rootCtx;
            if (iTaskContext != null) {
                executor = iTaskContext.getExecutor();
            }
            if (executor == null) {
                ITask iTask = this.prevTask;
                if (iTask != null) {
                    synchronized (this) {
                        this.prevTask = null;
                        executor = iTask.getExecutor();
                        this.prevTask = iTask;
                    }
                }
                if (executor == null) {
                    synchronized (this) {
                        if (this.i == null) {
                            this.i = TaskExecutor.getDefaultExecutor();
                        }
                        executor = this.i;
                    }
                }
            }
        }
        return executor;
    }

    @Override // com.goodix.ble.libcomx.task.ITask
    public ILogger getLogger() {
        return this.logger;
    }

    @Override // com.goodix.ble.libcomx.task.ITask
    public String getName() {
        return this.h;
    }

    @Override // com.goodix.ble.libcomx.task.ITask
    public final synchronized <T> T getOutput(String str) {
        T t;
        HashMap<String, Object> hashMap = this.r;
        t = hashMap != null ? (T) hashMap.get(str) : null;
        ITask iTask = this.prevTask;
        if (t == null && iTask != null) {
            this.prevTask = null;
            t = (T) iTask.getOutput(str);
            this.prevTask = iTask;
        }
        return t;
    }

    @Override // com.goodix.ble.libcomx.task.ITaskContext
    public final synchronized <T> T getParameter(String str) {
        T t;
        HashMap<String, Object> hashMap;
        ITaskContext iTaskContext = this.rootCtx;
        t = iTaskContext != null ? (T) iTaskContext.getParameter(str) : null;
        if (t == null && (hashMap = this.r) != null) {
            t = (T) hashMap.get(str);
        }
        return t;
    }

    @Override // com.goodix.ble.libcomx.task.ITask
    public <T> T getPreviousTask() {
        try {
            return (T) this.prevTask;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.goodix.ble.libcomx.task.ITask
    public int getProgress() {
        int i = this.y;
        if (i < 0) {
            return 0;
        }
        return i;
    }

    @Override // com.goodix.ble.libcomx.task.ITask
    public ITaskResult getResult() {
        return this;
    }

    @Override // com.goodix.ble.libcomx.task.ITaskResult
    public ITask getTask() {
        return this;
    }

    @Override // com.goodix.ble.libcomx.task.ITask
    public boolean isFinished() {
        return this.j;
    }

    @Override // com.goodix.ble.libcomx.task.ITask
    public boolean isStarted() {
        return this.taskState == 2;
    }

    public void onCleanup() {
    }

    public void onStart() {
    }

    public void onTaskExpired() {
    }

    public void onTimeout(int i) {
    }

    public final void publishProgress(int i) {
        ILogger iLogger = this.logger;
        synchronized (this) {
            if (this.taskState == 2 && !this.isAborted) {
                if (iLogger != null && this.printVerboseLog) {
                    iLogger.v(getName(), "publishProgress: " + i);
                }
                if (this.w > 0) {
                    this.x = System.currentTimeMillis() + this.w;
                }
                boolean z = true;
                if (this.m && this.y == i) {
                    z = false;
                }
                this.y = i;
                if (z) {
                    this.o.postEvent(Integer.valueOf(i));
                }
            }
        }
    }

    public final synchronized void refreshTaskTimeout() {
        if (this.taskState == 2 && this.w > 0) {
            this.x = System.currentTimeMillis() + this.w;
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        ILogger iLogger = this.logger;
        synchronized (this) {
            if (this.taskState != 2) {
                if (iLogger != null) {
                    iLogger.w(getName(), "Task is not running. Unexpected executor schedule.");
                }
            } else if (this.k) {
                if (iLogger != null) {
                    iLogger.w(getName(), "Task is pending. Unexpected executor schedule.");
                }
            } else {
                boolean z = true;
                this.k = true;
                if (this.v) {
                    if (iLogger != null) {
                        iLogger.w(getName(), "Task is pending. Unexpected executor schedule.");
                    }
                    return;
                }
                boolean z2 = this.isAborted;
                if (z2) {
                    finished(-2, new TaskError.Abort(this));
                    return;
                }
                try {
                    int doWork = doWork();
                    synchronized (this) {
                        if (this.taskState != 2) {
                            doWork = 0;
                        } else if (this.isAborted) {
                            z2 = true;
                        } else if (doWork > 0) {
                            this.v = true;
                            this.w = doWork;
                            this.x = System.currentTimeMillis() + this.w;
                        }
                    }
                    if (z2) {
                        finished(-2, new TaskError.Abort(this));
                        return;
                    }
                    if (doWork > 0) {
                        startTimer(-277, doWork);
                        if (iLogger != null && this.printVerboseLog) {
                            iLogger.v(getName(), "Pend task for waiting some callbacks.");
                        }
                    }
                    synchronized (this) {
                        if (this.taskState != 2 || this.v) {
                            z = false;
                        }
                    }
                    if (z) {
                        if (iLogger != null && this.printVerboseLog) {
                            iLogger.v(getName(), "Call finished() automatically for sync task.");
                        }
                        finishedWithDone();
                    }
                } catch (Throwable th) {
                    finishedWithError("Exception is occurred while running.", th);
                }
            }
        }
    }

    @Override // com.goodix.ble.libcomx.task.ITask
    public ITask setDebug(boolean z) {
        this.printVerboseLog = z;
        return this;
    }

    @Override // com.goodix.ble.libcomx.task.ITask
    public ITask setLogger(ILogger iLogger) {
        this.logger = iLogger;
        return this;
    }

    public final Task setOneshot(boolean z) {
        this.q = z;
        return this;
    }

    public final Task setParameter(Class cls, Object obj) {
        setParameter(cls.getName(), (String) obj);
        return this;
    }

    @Override // com.goodix.ble.libcomx.task.ITask
    public final void start(ITaskContext iTaskContext, ITask iTask) {
        ILogger iLogger = this.logger;
        synchronized (this) {
            int i = this.taskState;
            if (i != 0) {
                if (iLogger != null) {
                    if (i == 4) {
                        String name = getName();
                        iLogger.e(name, "The task is disposable and finished. It can NOT be started again. From " + CallUtil.trace(5));
                    } else {
                        String name2 = getName();
                        iLogger.w(name2, "The task is not idle. DO NOT call start() again, from " + CallUtil.trace(5));
                    }
                }
                return;
            }
            this.taskState = 1;
            this.j = false;
            this.isAborted = false;
            this.y = -1;
            this.s = 0;
            this.t = null;
            onStart();
            if (iTaskContext != this) {
                this.rootCtx = iTaskContext;
            }
            if (iTask != this) {
                this.prevTask = iTask;
            }
            TaskError c = c();
            if (iLogger != null && this.printVerboseLog) {
                iLogger.v(getName(), "Started");
            }
            this.n.postEvent(null);
            synchronized (this) {
                this.taskState = 2;
                this.k = false;
            }
            if (c != null) {
                finished(-1, c);
            } else if (this.isAborted) {
                finished(-2, new TaskError.Abort(this));
            } else {
                getExecutor().execute(this);
            }
        }
    }

    public final synchronized TimerTask startTimer(int i, long j, long j2) {
        if (this.taskState != 2) {
            return null;
        }
        if (this.u == null) {
            this.u = new Timer();
        }
        a aVar = new a(i);
        if (j2 > 0) {
            this.u.scheduleAtFixedRate(aVar, j, j2);
        } else {
            this.u.schedule(aVar, j);
        }
        return aVar;
    }

    public final synchronized void stopTimer() {
        Timer timer = this.u;
        if (timer != null) {
            timer.cancel();
            this.u = null;
        }
    }

    public final void finishedWithError(int i, String str, Throwable th) {
        finished(i, new TaskError(this, str, th));
    }

    @Override // com.goodix.ble.libcomx.task.ITask
    public final Task setDebounceProgressEvent(boolean z) {
        this.m = z;
        return this;
    }

    @Override // com.goodix.ble.libcomx.task.ITask
    public final Task setExecutor(Executor executor) {
        this.i = executor;
        return this;
    }

    @Override // com.goodix.ble.libcomx.task.ITask
    public Task setName(String str) {
        this.h = str;
        return this;
    }

    public final Task setParameter(ITaskParameter iTaskParameter) {
        setParameter(iTaskParameter.getClass().getName(), (String) iTaskParameter);
        return this;
    }

    public final void finishedWithError(String str) {
        finished(-1, new TaskError(this, str));
    }

    @Override // com.goodix.ble.libcomx.task.ITaskContext
    public final synchronized <T> void setParameter(String str, T t) {
        if (this.r == null) {
            this.r = new HashMap<>();
        }
        this.r.put(str, t);
    }

    public final void finishedWithError(String str, Throwable th) {
        finished(-1, new TaskError(this, str, th));
    }

    public final TimerTask startTimer(int i, long j) {
        return startTimer(i, j, 0L);
    }

    public Task(String str) {
        this.h = getClass().getSimpleName();
        this.logger = debugLogger;
        this.isAborted = false;
        this.j = false;
        this.k = false;
        this.m = false;
        this.taskState = 0;
        this.n = new Event<>(this, ITask.EVT_START);
        this.o = new Event<>(this, 341);
        this.p = new Event<>(this, ITask.EVT_FINISH);
        this.q = false;
        this.printVerboseLog = debugLogger != null;
        this.r = null;
        this.v = false;
        this.w = 0L;
        this.x = 0L;
        this.h = str;
        this.l = true;
    }

    public Task(boolean z) {
        this.h = getClass().getSimpleName();
        this.logger = debugLogger;
        this.isAborted = false;
        this.j = false;
        this.k = false;
        this.m = false;
        this.taskState = 0;
        this.n = new Event<>(this, ITask.EVT_START);
        this.o = new Event<>(this, 341);
        this.p = new Event<>(this, ITask.EVT_FINISH);
        this.q = false;
        this.printVerboseLog = debugLogger != null;
        this.r = null;
        this.v = false;
        this.w = 0L;
        this.x = 0L;
        this.l = z;
    }
}
