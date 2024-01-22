package com.goodix.ble.gr.libdfu.task;

import com.goodix.ble.gr.libdfu.dfu.entity.DfuFile;
import com.goodix.ble.gr.libdfu.task.param.SkipOverwriteCheck;
import com.goodix.ble.gr.libdfu.task.sub.CheckOverlayTask;
import com.goodix.ble.gr.libdfu.task.sub.GetCurrentBootInfoTask;
import com.goodix.ble.gr.libdfu.task.sub.GetImgListTask;
import com.goodix.ble.gr.libdfu.task.sub.TidyImgInfoListTask;
import com.goodix.ble.gr.libdfu.task.sub.UploadDfuFileTask;
import com.goodix.ble.gr.libdfu.task.util.UiExec;
import com.goodix.ble.libcomx.ILogger;
import com.goodix.ble.libcomx.event.IEventListener;
import com.goodix.ble.libcomx.task.ITaskResult;
import com.goodix.ble.libcomx.task.TaskQueue;
import com.goodix.ble.libcomx.transceiver.ITransceiver;
import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.Executor;
/* loaded from: classes5.dex */
public class DeviceFirmwareUpdate implements IEventListener {
    public final TaskQueue h;
    public final TidyImgInfoListTask i;
    public final UploadDfuFileTask j;
    public final CheckOverlayTask k;
    public Listener l;
    public ILogger m;
    public final SkipOverwriteCheck n = new SkipOverwriteCheck(false);

    /* loaded from: classes5.dex */
    public interface Listener {
        void onDfuCanceled();

        void onDfuComplete();

        void onDfuError(String str, Error error);

        void onDfuErrorFirmwareOverlap();

        void onDfuProgressUpdate(int i);

        void onDfuStart();
    }

    public DeviceFirmwareUpdate() {
        TidyImgInfoListTask tidyImgInfoListTask = new TidyImgInfoListTask();
        this.i = tidyImgInfoListTask;
        UploadDfuFileTask uploadDfuFileTask = new UploadDfuFileTask();
        this.j = uploadDfuFileTask;
        CheckOverlayTask checkOverlayTask = new CheckOverlayTask();
        this.k = checkOverlayTask;
        TaskQueue taskQueue = new TaskQueue();
        this.h = taskQueue;
        taskQueue.setAbortOnException(true);
        taskQueue.setExecutor((Executor) new UiExec());
        taskQueue.addTask(new GetCurrentBootInfoTask());
        taskQueue.addTask(checkOverlayTask);
        taskQueue.addTask(new GetImgListTask());
        taskQueue.addTask(tidyImgInfoListTask);
        taskQueue.addTask(uploadDfuFileTask);
        tidyImgInfoListTask.evtStart().register(this);
        uploadDfuFileTask.evtProgress().register(this);
        taskQueue.evtFinished().register(this);
        uploadDfuFileTask.setDebounceProgressEvent(true);
    }

    public void cancel() {
        this.h.abort();
    }

    public UploadDfuFileTask getUploadDfuFileTask() {
        return this.j;
    }

    @Override // com.goodix.ble.libcomx.event.IEventListener
    public void onEvent(Object obj, int i, Object obj2) {
        Listener listener;
        Listener listener2;
        if (obj == this.i && i == 340) {
            Listener listener3 = this.l;
            if (listener3 != null) {
                listener3.onDfuStart();
            }
        } else if (obj == this.j && i == 341) {
            if (this.l != null) {
                this.l.onDfuProgressUpdate(((Integer) obj2).intValue());
            }
        } else if (obj == this.h && i == 342) {
            ITaskResult iTaskResult = (ITaskResult) obj2;
            if (iTaskResult.getCode() == -2) {
                Listener listener4 = this.l;
                if (listener4 != null) {
                    listener4.onDfuCanceled();
                }
            } else if (iTaskResult.getCode() == 0) {
                Listener listener5 = this.l;
                if (listener5 != null) {
                    listener5.onDfuComplete();
                }
            } else if (iTaskResult.getCode() == 800 && (listener = this.l) != null) {
                listener.onDfuErrorFirmwareOverlap();
                return;
            }
            if (iTaskResult.getError() == null || (listener2 = this.l) == null) {
                return;
            }
            listener2.onDfuError(iTaskResult.getError().getRootCause().getMessage(), iTaskResult.getError());
        }
    }

    public DeviceFirmwareUpdate setAsCopyMode(int i) {
        setOverwriteFw(true);
        this.j.setAsCopyMode(i);
        this.k.setStartAddressInFlash(Integer.valueOf(i), false, false);
        return this;
    }

    public DeviceFirmwareUpdate setAsDfuMode(boolean z) {
        this.j.setAsDfuMode(z);
        this.k.setStartAddressInFlash(null, false, false);
        return this;
    }

    public DeviceFirmwareUpdate setDfuFile(File file) {
        try {
            DfuFile dfuFile = new DfuFile();
            FileInputStream fileInputStream = new FileInputStream(file);
            dfuFile.load(fileInputStream);
            this.h.setParameter(DfuFile.class, dfuFile);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            ILogger iLogger = this.m;
            if (iLogger != null) {
                iLogger.e("DeviceFirmwareUpdate", "Can not open the file: " + file.getAbsolutePath(), e);
            }
        }
        return this;
    }

    public DeviceFirmwareUpdate setListener(Listener listener) {
        this.l = listener;
        return this;
    }

    public DeviceFirmwareUpdate setLogger(ILogger iLogger) {
        this.m = iLogger;
        this.h.setLogger(iLogger);
        return this;
    }

    public DeviceFirmwareUpdate setOverwriteFw(boolean z) {
        this.n.setSkip(z);
        this.h.setParameter(this.n);
        return this;
    }

    public DeviceFirmwareUpdate setTransceiver(ITransceiver iTransceiver) {
        this.h.setParameter(ITransceiver.class, iTransceiver);
        return this;
    }

    public DeviceFirmwareUpdate start() {
        this.h.start(null, null);
        return this;
    }

    public DeviceFirmwareUpdate setDfuFile(DfuFile dfuFile) {
        this.h.setParameter(DfuFile.class, dfuFile);
        return this;
    }
}
