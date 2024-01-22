package com.goodix.ble.libcomx.task;

import com.goodix.ble.libcomx.ILogger;
import com.goodix.ble.libcomx.event.Event;
import com.goodix.ble.libcomx.event.IEventListener;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes5.dex */
public class TaskQueue extends Task implements ITaskSet {
    public ITask C;
    public int z;
    public boolean abortOnException = false;
    public boolean useResultOfSubtask = false;
    public boolean requestAbortQueue = false;
    public List<ITask> A = new ArrayList();
    public int[] B = null;
    public Event<ITask> D = new Event<>(this, ITaskSet.EVT_SUBTASK_START);
    public Event<ITask> E = new Event<>(this, ITaskSet.EVT_SUBTASK_PROGRESS);
    public Event<ITask> F = new Event<>(this, ITaskSet.EVT_SUBTASK_FINISH);
    public a G = new a();
    public int H = 600000;

    /* loaded from: classes5.dex */
    public class a implements IEventListener {
        public a() {
        }

        @Override // com.goodix.ble.libcomx.event.IEventListener
        public void onEvent(Object obj, int i, Object obj2) {
            ITask iTask;
            int i2;
            TaskQueue taskQueue = TaskQueue.this;
            ILogger iLogger = taskQueue.logger;
            ITask iTask2 = (ITask) obj;
            synchronized (taskQueue) {
                iTask = TaskQueue.this.C;
                i2 = TaskQueue.this.z;
            }
            if (iTask2 != iTask) {
                if (iLogger != null) {
                    String name = TaskQueue.this.getName();
                    iLogger.w(name, "Unexpected event: src = " + iTask2.getName() + ", type = " + i + ", data = " + obj2);
                }
            } else if (i == 340) {
                TaskQueue.this.m(0);
                TaskQueue.this.D.postEvent(iTask2);
            } else if (i == 341) {
                TaskQueue.this.m(((Integer) obj2).intValue());
                TaskQueue.this.E.postEvent(iTask2);
            } else if (i == 342) {
                ITaskResult iTaskResult = (ITaskResult) obj2;
                if (iLogger != null && TaskQueue.this.printVerboseLog) {
                    if (iTaskResult.getError() != null) {
                        String name2 = TaskQueue.this.getName();
                        iLogger.v(name2, "Subtask #" + i2 + ", " + iTask2.getName() + ", is finished with error: " + iTaskResult.getError().getMessage() + ". Start next one.");
                    } else {
                        String name3 = TaskQueue.this.getName();
                        iLogger.v(name3, "Subtask #" + i2 + ", " + iTask2.getName() + ", is finished. Start next one.");
                    }
                }
                iTask2.evtStart().remove(TaskQueue.this.G);
                iTask2.evtProgress().remove(TaskQueue.this.G);
                iTask2.evtFinished().remove(TaskQueue.this.G);
                TaskQueue.this.F.postEvent(iTask2);
                TaskQueue.this.o();
            }
        }
    }

    public synchronized TaskQueue addTask(ITask iTask) {
        if (!isStarted()) {
            this.A.add(iTask);
        } else {
            throw new IllegalStateException("Task is already started.");
        }
        return this;
    }

    public <T extends ITask> T addTask2(T t) {
        addTask(t);
        return t;
    }

    @Override // com.goodix.ble.libcomx.task.Task, com.goodix.ble.libcomx.task.ITask
    public ITask clearListener(Object obj) {
        evtSubtaskStart().clear(obj);
        evtSubtaskProgress().clear(obj);
        evtSubtaskFinish().clear(obj);
        return super.clearListener(obj);
    }

    public synchronized TaskQueue clearTask() {
        if (!isStarted()) {
            for (ITask iTask : this.A) {
                iTask.evtFinished().remove(this.G);
                iTask.evtProgress().remove(this.G);
                iTask.evtStart().remove(this.G);
            }
            this.A.clear();
        } else {
            throw new IllegalStateException("Task is already started.");
        }
        return this;
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public int doWork() {
        int[] iArr = this.B;
        if (iArr != null && iArr.length != this.A.size()) {
            finishedWithError("Must set weight for each subtask.");
            return 0;
        }
        n();
        return this.H;
    }

    @Override // com.goodix.ble.libcomx.task.ITaskSet
    public Event<ITask> evtSubtaskFinish() {
        return this.F;
    }

    @Override // com.goodix.ble.libcomx.task.ITaskSet
    public Event<ITask> evtSubtaskProgress() {
        return this.E;
    }

    @Override // com.goodix.ble.libcomx.task.ITaskSet
    public Event<ITask> evtSubtaskStart() {
        return this.D;
    }

    public synchronized ITask getTask(int i) {
        int size = this.A.size();
        if (i < 0 && (i = i + size) < 0) {
            i = 0;
        }
        if (i < size) {
            return this.A.get(i);
        }
        return null;
    }

    public synchronized int getTaskCount() {
        return this.A.size();
    }

    public final void m(int i) {
        int i2;
        int i3;
        synchronized (this) {
            int i4 = this.z;
            int size = this.A.size();
            int[] iArr = this.B;
            if (iArr != null) {
                i2 = 0;
                for (int i5 = 0; i5 < i4; i5++) {
                    i2 += iArr[i5];
                }
                i3 = iArr[i4];
            } else {
                i2 = (i4 * 100) / size;
                i3 = 100 / size;
            }
        }
        if (i > 100) {
            i = 100;
        }
        publishProgress(i2 + ((i3 * (i >= 0 ? i : 0)) / 100));
    }

    public final synchronized void n() {
        if (this.taskState != 2) {
            return;
        }
        ILogger iLogger = this.logger;
        int size = this.A.size();
        int i = this.z + 1;
        this.z = i;
        if (i < size) {
            ITask iTask = this.C;
            if (iTask == null) {
                iTask = this.prevTask;
            }
            this.C = this.A.get(i);
            if (iLogger != null) {
                try {
                    if (this.printVerboseLog) {
                        iLogger.v(getName(), "Start subtask #" + i + ": " + this.C.getName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    String str = "Exception on starting subtask #" + this.z + ", " + this.C.getName() + ": " + e.getMessage();
                    if (this.abortOnException) {
                        finished(-1, new TaskError(this, str, e));
                    } else {
                        if (iLogger != null) {
                            iLogger.e(getName(), str);
                        }
                        o();
                    }
                }
            }
            this.C.evtFinished().register(this.G);
            this.C.evtProgress().register(this.G);
            this.C.evtStart().register(this.G);
            this.C.start(this, iTask);
        } else {
            finishedWithDone();
        }
    }

    public final void o() {
        boolean z;
        ITask iTask;
        int i;
        synchronized (this) {
            z = this.taskState == 2;
            if (this.abortOnException) {
                iTask = this.C;
                i = this.z;
            } else {
                iTask = null;
                i = -1;
            }
        }
        if (z) {
            if (iTask != null) {
                ITaskResult result = iTask.getResult();
                if (result.getError() != null) {
                    if (this.useResultOfSubtask) {
                        finished(result.getCode(), result.getError());
                        return;
                    }
                    finished(-1, new TaskError(this, "Abort at subtask #" + i + ": " + iTask.getName(), result.getError()));
                    return;
                }
            }
            getExecutor().execute(new Runnable() { // from class: com.goodix.ble.libcomx.task.b
                @Override // java.lang.Runnable
                public final void run() {
                    TaskQueue.this.n();
                }
            });
        }
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public void onCleanup() {
        ITask iTask = this.C;
        if (iTask != null) {
            iTask.abort();
        }
        this.requestAbortQueue = true;
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public void onStart() {
        this.C = null;
        this.z = -1;
        this.requestAbortQueue = false;
    }

    public synchronized ITask replaceTask(int i, ITask iTask) {
        int size = this.A.size();
        if (i < 0 && (i = i + size) < 0) {
            i = 0;
        }
        if (i < size) {
            ITask iTask2 = this.A.get(i);
            this.A.set(i, iTask);
            return iTask2;
        }
        return null;
    }

    public TaskQueue setAbortOnException() {
        this.abortOnException = true;
        return this;
    }

    @Override // com.goodix.ble.libcomx.task.Task, com.goodix.ble.libcomx.task.ITask
    public ITask setDebug(boolean z) {
        synchronized (this) {
            for (ITask iTask : this.A) {
                iTask.setDebug(z);
            }
        }
        return super.setDebug(z);
    }

    @Override // com.goodix.ble.libcomx.task.Task, com.goodix.ble.libcomx.task.ITask
    public ITask setLogger(ILogger iLogger) {
        super.setLogger(iLogger);
        synchronized (this) {
            for (ITask iTask : this.A) {
                iTask.setLogger(iLogger);
            }
        }
        return this;
    }

    public TaskQueue setQueueTimeout(int i) {
        this.H = i;
        return this;
    }

    public TaskQueue setTaskPercentWeights(int... iArr) {
        if (iArr != null && iArr.length > 0) {
            int i = 0;
            for (int i2 = 0; i2 < iArr.length; i2++) {
                int i3 = iArr[i2];
                if (i3 <= 0) {
                    throw new IllegalArgumentException("Weight of subtask must > 0 at [" + i2 + "]: " + i3);
                }
                i += i3;
            }
            if (i == 100) {
                this.B = iArr;
            } else {
                throw new IllegalArgumentException("Expected total weight is 100, but actual weight is: " + i);
            }
        } else {
            this.B = null;
        }
        return this;
    }

    public TaskQueue setAbortOnException(boolean z) {
        this.abortOnException = true;
        this.useResultOfSubtask = z;
        return this;
    }
}
