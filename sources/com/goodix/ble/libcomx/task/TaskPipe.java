package com.goodix.ble.libcomx.task;

import com.goodix.ble.libcomx.event.Event;
import com.goodix.ble.libcomx.event.IEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
/* loaded from: classes5.dex */
public class TaskPipe implements ITaskContext, IEventListener {
    public static final int EVT_BUSY = 761;
    public static final int EVT_TASK_ADDED = 447;
    public static final int EVT_TASK_PROGRESS = 938;
    public static final int EVT_TASK_REMOVED = 166;
    public static final int EVT_TASK_START = 138;
    public final Event<Boolean> h = new Event<>(this, EVT_BUSY);
    public final Event<TaskItem> i = new Event<>(this, EVT_TASK_ADDED);
    public final Event<TaskItem> j = new Event<>(this, 138);
    public final Event<TaskItem> k = new Event<>(this, EVT_TASK_PROGRESS);
    public final Event<TaskItem> l = new Event<>(this, 166);
    public LinkedList<TaskItem> m = new LinkedList<>();
    public HashMap<String, Object> n = new HashMap<>();
    public Executor o;
    public boolean p;

    /* loaded from: classes5.dex */
    public static class TaskItem {
        public String name;
        public int percent;
        public ITaskResult result;
        public ITask task;
    }

    public final void a(ITask iTask) {
        TaskItem peekFirst = this.m.peekFirst();
        if (peekFirst != null) {
            this.j.postEvent(peekFirst);
            peekFirst.task.evtStart().register(this);
            peekFirst.task.evtProgress().register(this);
            peekFirst.task.evtFinished().register(this);
            peekFirst.task.start(this, iTask);
            return;
        }
        this.p = false;
        this.h.postEvent(Boolean.FALSE);
    }

    public synchronized void abortTask() {
        TaskItem peekFirst;
        if (!this.m.isEmpty() && (peekFirst = this.m.peekFirst()) != null) {
            peekFirst.task.abort();
        }
    }

    public void addTask(ITask iTask) {
        addTask(iTask, null);
    }

    public synchronized void clearTask() {
        if (!this.m.isEmpty()) {
            while (this.m.size() > 0) {
                this.m.removeLast().task.abort();
            }
            if (this.p) {
                this.p = false;
                this.h.postEvent(Boolean.FALSE);
            }
        }
    }

    public Event<Boolean> evtBusy() {
        return this.h;
    }

    public Event<TaskItem> evtTaskAdded() {
        return this.i;
    }

    public Event<TaskItem> evtTaskProgress() {
        return this.k;
    }

    public Event<TaskItem> evtTaskRemoved() {
        return this.l;
    }

    public Event<TaskItem> evtTaskStart() {
        return this.j;
    }

    public synchronized ITask getCurrentTask() {
        TaskItem peekFirst = this.m.peekFirst();
        if (peekFirst != null) {
            return peekFirst.task;
        }
        return null;
    }

    @Override // com.goodix.ble.libcomx.task.ITaskContext
    public Executor getExecutor() {
        return this.o;
    }

    @Override // com.goodix.ble.libcomx.task.ITaskContext
    public <T> T getParameter(String str) {
        return (T) this.n.get(str);
    }

    public synchronized int getTaskCount() {
        return this.m.size();
    }

    public synchronized List<ITask> getTaskList(List<ITask> list) {
        if (list == null) {
            list = new ArrayList<>(this.m.size());
        }
        Iterator<TaskItem> it = this.m.iterator();
        while (it.hasNext()) {
            list.add(it.next().task);
        }
        return list;
    }

    public boolean isBusy() {
        return this.p;
    }

    @Override // com.goodix.ble.libcomx.event.IEventListener
    public void onEvent(Object obj, int i, Object obj2) {
        TaskItem peekFirst;
        TaskItem peekFirst2;
        ITask iTask;
        if (i != 342) {
            if (i == 341) {
                synchronized (this) {
                    peekFirst = this.m.peekFirst();
                }
                if (peekFirst == null || obj != peekFirst.task) {
                    return;
                }
                peekFirst.percent = ((Integer) obj2).intValue();
                this.k.postEvent(peekFirst);
                return;
            }
            return;
        }
        synchronized (this) {
            peekFirst2 = this.m.peekFirst();
        }
        if (peekFirst2 == null || obj != (iTask = peekFirst2.task)) {
            return;
        }
        iTask.evtStart().remove(this);
        peekFirst2.task.evtProgress().remove(this);
        peekFirst2.task.evtFinished().remove(this);
        peekFirst2.result = (ITaskResult) obj2;
        this.l.postEvent(peekFirst2);
        synchronized (this) {
            a(this.m.removeFirst().task);
        }
    }

    public void setExecutor(Executor executor) {
        this.o = executor;
    }

    @Override // com.goodix.ble.libcomx.task.ITaskContext
    public <T> void setParameter(String str, T t) {
        this.n.put(str, t);
    }

    public synchronized void addTask(ITask iTask, String str) {
        if (iTask == null) {
            return;
        }
        if (!this.p) {
            this.p = true;
            this.h.postEvent(Boolean.TRUE);
        }
        TaskItem taskItem = new TaskItem();
        taskItem.task = iTask;
        if (str == null) {
            str = iTask.getName();
        }
        taskItem.name = str;
        this.m.add(taskItem);
        this.i.postEvent(taskItem);
        if (this.m.size() == 1) {
            a(null);
        }
    }
}
