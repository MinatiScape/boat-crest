package com.goodix.ble.gr.toolbox.app.libfastdfu;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import com.goodix.ble.gr.libdfu.dfu.entity.DfuFile;
import com.goodix.ble.gr.libdfu.task.util.UiExec;
import com.goodix.ble.gr.toolbox.app.libfastdfu.task.ChecksumTask;
import com.goodix.ble.gr.toolbox.app.libfastdfu.task.DownloadDataTask;
import com.goodix.ble.gr.toolbox.app.libfastdfu.task.DownloadDataTask2;
import com.goodix.ble.gr.toolbox.app.libfastdfu.task.EraseFlashTask;
import com.goodix.ble.gr.toolbox.app.libfastdfu.task.FastDfuProfile;
import com.goodix.ble.gr.toolbox.app.libfastdfu.task.FlashSelectTask;
import com.goodix.ble.gr.toolbox.app.libfastdfu.task.GetVersionTask;
import com.goodix.ble.gr.toolbox.app.libfastdfu.task.StartCopyTask;
import com.goodix.ble.gr.toolbox.app.libfastdfu.task.WirteBootInfoTask;
import com.goodix.ble.libble.v2.gb.GBRemoteDevice;
import com.goodix.ble.libble.v2.impl.BleRemoteDevice;
import com.goodix.ble.libcomx.ILogger;
import com.goodix.ble.libcomx.event.IEventListener;
import com.goodix.ble.libcomx.task.ITask;
import com.goodix.ble.libcomx.task.ITaskResult;
import com.goodix.ble.libcomx.task.TaskError;
import com.goodix.ble.libcomx.task.TaskQueue;
import com.goodix.ble.libcomx.util.SimpleTask;
import java.io.InputStream;
/* loaded from: classes5.dex */
public class FastDfu {

    /* renamed from: a  reason: collision with root package name */
    public FastDfuProgressCallback f8004a;
    public boolean b;
    public ITask c;
    public ILogger d;

    /* loaded from: classes5.dex */
    public class a implements SimpleTask.Work {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ TaskQueue f8005a;

        public a(FastDfu fastDfu, TaskQueue taskQueue) {
            this.f8005a = taskQueue;
        }

        @Override // com.goodix.ble.libcomx.util.SimpleTask.Work
        public void onWork(SimpleTask simpleTask, Object obj) {
            TaskQueue taskQueue;
            ITask downloadDataTask;
            this.f8005a.clearTask();
            if (((GetVersionTask) simpleTask.getPreviousTask()).version == 3) {
                taskQueue = this.f8005a;
                downloadDataTask = new DownloadDataTask2();
            } else {
                taskQueue = this.f8005a;
                downloadDataTask = new DownloadDataTask();
            }
            taskQueue.addTask(downloadDataTask);
        }
    }

    /* loaded from: classes5.dex */
    public class b implements IEventListener<Void> {
        public b() {
        }

        @Override // com.goodix.ble.libcomx.event.IEventListener
        /* renamed from: a */
        public void onEvent(Object obj, int i, Void r3) {
            FastDfu.this.b = true;
            if (FastDfu.this.f8004a != null) {
                FastDfu.this.f8004a.onDfuStart();
            }
        }
    }

    /* loaded from: classes5.dex */
    public class c implements IEventListener<ITaskResult> {
        public final /* synthetic */ EraseFlashTask h;
        public final /* synthetic */ TaskQueue i;
        public final /* synthetic */ GBRemoteDevice j;

        public c(EraseFlashTask eraseFlashTask, TaskQueue taskQueue, GBRemoteDevice gBRemoteDevice) {
            this.h = eraseFlashTask;
            this.i = taskQueue;
            this.j = gBRemoteDevice;
        }

        @Override // com.goodix.ble.libcomx.event.IEventListener
        /* renamed from: a */
        public void onEvent(Object obj, int i, ITaskResult iTaskResult) {
            FastDfuProgressCallback fastDfuProgressCallback;
            String message;
            TaskError error;
            FastDfu.this.b = false;
            if (FastDfu.this.f8004a != null) {
                if (iTaskResult.getError() != null) {
                    int code = iTaskResult.getCode();
                    if (code == 230) {
                        fastDfuProgressCallback = FastDfu.this.f8004a;
                        error = iTaskResult.getError();
                        message = "OVERLAP";
                    } else if (code == -2) {
                        fastDfuProgressCallback = FastDfu.this.f8004a;
                        error = iTaskResult.getError();
                        message = "ABORT";
                    } else if (code == 113) {
                        fastDfuProgressCallback = FastDfu.this.f8004a;
                        error = iTaskResult.getError();
                        message = "FAILED";
                    } else if (code == 114) {
                        fastDfuProgressCallback = FastDfu.this.f8004a;
                        error = iTaskResult.getError();
                        message = "NO_EXT_FLASH";
                    } else {
                        fastDfuProgressCallback = FastDfu.this.f8004a;
                        message = iTaskResult.getError().getMessage();
                        error = iTaskResult.getError();
                    }
                    fastDfuProgressCallback.onDfuError(code, message, error);
                } else {
                    FastDfu.this.f8004a.onDfuComplete();
                }
            }
            this.h.evtProgress().clear(FastDfu.this);
            this.i.evtProgress().clear(FastDfu.this);
            if (((ITask) obj).getParameter("releaseConnection") == Boolean.TRUE) {
                this.j.disconnect(true).startProcedure();
            }
        }
    }

    /* loaded from: classes5.dex */
    public class d implements IEventListener<Integer> {
        public d() {
        }

        @Override // com.goodix.ble.libcomx.event.IEventListener
        /* renamed from: a */
        public void onEvent(Object obj, int i, Integer num) {
            if (FastDfu.this.f8004a != null) {
                FastDfu.this.f8004a.onDfuErase(num.intValue());
            }
        }
    }

    /* loaded from: classes5.dex */
    public class e implements IEventListener<Integer> {
        public e() {
        }

        @Override // com.goodix.ble.libcomx.event.IEventListener
        /* renamed from: a */
        public void onEvent(Object obj, int i, Integer num) {
            if (FastDfu.this.f8004a != null) {
                FastDfu.this.f8004a.onDfuProgress(num.intValue());
            }
        }
    }

    public final TaskQueue b(Context context, FastDfuProfile fastDfuProfile, InputStream inputStream, int i, boolean z, int i2) {
        ITask startCopyTask;
        String str;
        TaskQueue taskQueue = new TaskQueue();
        taskQueue.setAbortOnException(true);
        taskQueue.setOneshot(true);
        taskQueue.setName("FastDfuSteps");
        DfuFile dfuFile = new DfuFile();
        dfuFile.load(inputStream);
        taskQueue.setParameter(DfuFile.class, dfuFile);
        GBRemoteDevice remoteDevice = fastDfuProfile.getRemoteDevice();
        taskQueue.setParameter(FastDfuProfile.class, fastDfuProfile);
        if (!remoteDevice.isConnected()) {
            taskQueue.addTask(remoteDevice.connect(0).setRetry(10, 1000));
            taskQueue.addTask(remoteDevice.discoverServices());
            taskQueue.addTask(remoteDevice.setMtu(247));
            taskQueue.setParameter("releaseConnection", (String) Boolean.TRUE);
        }
        EraseFlashTask eraseFlashTask = new EraseFlashTask(fastDfuProfile, i != 0 ? i2 : dfuFile.getImgInfo().getBootInfo().getLoadAddr(), dfuFile.getFirmware().length);
        taskQueue.addTask(new FlashSelectTask(i == 2 && z));
        taskQueue.addTask(eraseFlashTask);
        TaskQueue taskQueue2 = new TaskQueue();
        taskQueue2.setAbortOnException(true);
        taskQueue2.setName("DownloadDataTaskWrapper");
        taskQueue.addTask(new GetVersionTask());
        taskQueue.addTask(new SimpleTask("JudgeVersion", 0, new a(this, taskQueue2)));
        taskQueue.addTask(taskQueue2);
        taskQueue.addTask(new ChecksumTask());
        if (i != 0) {
            if (i == 1) {
                startCopyTask = new StartCopyTask(fastDfuProfile, dfuFile.getImgInfo(), i2, dfuFile.getFirmware().length);
                str = "开始拷贝";
            }
            UiExec uiExec = new UiExec();
            taskQueue.evtStart().setExecutor(uiExec).register2(new b());
            taskQueue.evtFinished().setExecutor(uiExec).register2(new c(eraseFlashTask, taskQueue2, remoteDevice));
            eraseFlashTask.evtProgress().subEvent(this).setExecutor(uiExec).register2(new d());
            taskQueue2.evtProgress().subEvent(this).setExecutor(uiExec).register2(new e());
            taskQueue.setLogger(this.d);
            return taskQueue;
        }
        startCopyTask = new WirteBootInfoTask(fastDfuProfile, dfuFile.getImgInfo());
        str = "写BootInfo";
        taskQueue.addTask(startCopyTask.setName(str));
        UiExec uiExec2 = new UiExec();
        taskQueue.evtStart().setExecutor(uiExec2).register2(new b());
        taskQueue.evtFinished().setExecutor(uiExec2).register2(new c(eraseFlashTask, taskQueue2, remoteDevice));
        eraseFlashTask.evtProgress().subEvent(this).setExecutor(uiExec2).register2(new d());
        taskQueue2.evtProgress().subEvent(this).setExecutor(uiExec2).register2(new e());
        taskQueue.setLogger(this.d);
        return taskQueue;
    }

    public boolean cancel() {
        ITask iTask;
        if (!this.b || (iTask = this.c) == null) {
            return false;
        }
        iTask.abort();
        return true;
    }

    public void setListener(FastDfuProgressCallback fastDfuProgressCallback) {
        this.f8004a = fastDfuProgressCallback;
    }

    public void setLogger(ILogger iLogger) {
        this.d = iLogger;
    }

    public boolean startDfu(Context context, BluetoothDevice bluetoothDevice, InputStream inputStream) {
        if (this.b) {
            return false;
        }
        BleRemoteDevice bleRemoteDevice = new BleRemoteDevice(context);
        bleRemoteDevice.setBluetoothDevice(bluetoothDevice);
        bleRemoteDevice.setLogger(this.d);
        FastDfuProfile fastDfuProfile = new FastDfuProfile();
        fastDfuProfile.bindTo(bleRemoteDevice);
        TaskQueue b2 = b(context, fastDfuProfile, inputStream, 0, false, 0);
        this.c = b2;
        b2.start(null, null);
        return true;
    }

    public boolean startDfu(Context context, FastDfuProfile fastDfuProfile, InputStream inputStream) {
        if (this.b) {
            return false;
        }
        TaskQueue b2 = b(context, fastDfuProfile, inputStream, 0, false, 0);
        this.c = b2;
        b2.start(null, null);
        return true;
    }

    public boolean startDfuInCopyMode(Context context, BluetoothDevice bluetoothDevice, InputStream inputStream, int i) {
        if (this.b) {
            return false;
        }
        BleRemoteDevice bleRemoteDevice = new BleRemoteDevice(context);
        bleRemoteDevice.setBluetoothDevice(bluetoothDevice);
        bleRemoteDevice.setLogger(this.d);
        FastDfuProfile fastDfuProfile = new FastDfuProfile();
        fastDfuProfile.bindTo(bleRemoteDevice);
        TaskQueue b2 = b(context, fastDfuProfile, inputStream, 1, false, i);
        this.c = b2;
        b2.start(null, null);
        return true;
    }

    public boolean startDfuInCopyMode(Context context, FastDfuProfile fastDfuProfile, InputStream inputStream, int i) {
        if (this.b) {
            return false;
        }
        TaskQueue b2 = b(context, fastDfuProfile, inputStream, 1, false, i);
        this.c = b2;
        b2.start(null, null);
        return true;
    }

    public boolean startUpdateResource(Context context, BluetoothDevice bluetoothDevice, InputStream inputStream, boolean z, int i) {
        if (this.b) {
            return false;
        }
        BleRemoteDevice bleRemoteDevice = new BleRemoteDevice(context);
        bleRemoteDevice.setBluetoothDevice(bluetoothDevice);
        bleRemoteDevice.setLogger(this.d);
        FastDfuProfile fastDfuProfile = new FastDfuProfile();
        fastDfuProfile.bindTo(bleRemoteDevice);
        TaskQueue b2 = b(context, fastDfuProfile, inputStream, 2, z, i);
        this.c = b2;
        b2.start(null, null);
        return true;
    }

    public boolean startUpdateResource(Context context, FastDfuProfile fastDfuProfile, InputStream inputStream, boolean z, int i) {
        if (this.b) {
            return false;
        }
        TaskQueue b2 = b(context, fastDfuProfile, inputStream, 2, z, i);
        this.c = b2;
        b2.start(null, null);
        return true;
    }
}
