package com.goodix.ble.gr.libdfu.task;

import com.goodix.ble.gr.libdfu.task.DeviceFirmwareUpdate;
import com.goodix.ble.gr.libdfu.task.param.DfuFileList;
import com.goodix.ble.gr.libdfu.task.sub.CheckOverlayTask;
import com.goodix.ble.gr.libdfu.task.sub.FwSelectionTask;
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
import java.util.concurrent.Executor;
/* loaded from: classes5.dex */
public class DeviceFirmwareUpdateAB implements IEventListener {
    public final TaskQueue h;
    public final FwSelectionTask i;
    public final TidyImgInfoListTask j;
    public final UploadDfuFileTask k;
    public final CheckOverlayTask l;
    public DeviceFirmwareUpdate.Listener m;
    public ILogger n;

    public DeviceFirmwareUpdateAB() {
        FwSelectionTask fwSelectionTask = new FwSelectionTask();
        this.i = fwSelectionTask;
        TidyImgInfoListTask tidyImgInfoListTask = new TidyImgInfoListTask();
        this.j = tidyImgInfoListTask;
        UploadDfuFileTask uploadDfuFileTask = new UploadDfuFileTask();
        this.k = uploadDfuFileTask;
        CheckOverlayTask checkOverlayTask = new CheckOverlayTask();
        this.l = checkOverlayTask;
        TaskQueue taskQueue = new TaskQueue();
        this.h = taskQueue;
        taskQueue.setAbortOnException(true);
        taskQueue.setExecutor((Executor) new UiExec());
        taskQueue.addTask(fwSelectionTask);
        taskQueue.addTask(new GetCurrentBootInfoTask());
        taskQueue.addTask(checkOverlayTask);
        taskQueue.addTask(new GetImgListTask());
        taskQueue.addTask(tidyImgInfoListTask);
        taskQueue.addTask(uploadDfuFileTask);
        tidyImgInfoListTask.setCanOverwrite(true);
        uploadDfuFileTask.setAsDfuMode(true);
        uploadDfuFileTask.setDebounceProgressEvent(true);
        tidyImgInfoListTask.evtStart().register(this);
        uploadDfuFileTask.evtProgress().register(this);
        taskQueue.evtFinished().register(this);
    }

    public void cancel() {
        this.h.abort();
    }

    @Override // com.goodix.ble.libcomx.event.IEventListener
    public void onEvent(Object obj, int i, Object obj2) {
        DeviceFirmwareUpdate.Listener listener;
        DeviceFirmwareUpdate.Listener listener2;
        TaskQueue taskQueue = this.h;
        if (obj == taskQueue && i == 340) {
            DeviceFirmwareUpdate.Listener listener3 = this.m;
            if (listener3 != null) {
                listener3.onDfuStart();
            }
        } else if (obj == this.k && i == 341) {
            int intValue = ((Integer) obj2).intValue();
            DeviceFirmwareUpdate.Listener listener4 = this.m;
            if (listener4 != null) {
                listener4.onDfuProgressUpdate(intValue);
            }
        } else if (obj == taskQueue && i == 342) {
            ITaskResult iTaskResult = (ITaskResult) obj2;
            if (iTaskResult.getCode() == -2) {
                DeviceFirmwareUpdate.Listener listener5 = this.m;
                if (listener5 != null) {
                    listener5.onDfuCanceled();
                }
            } else if (iTaskResult.getCode() == 0) {
                DeviceFirmwareUpdate.Listener listener6 = this.m;
                if (listener6 != null) {
                    listener6.onDfuComplete();
                }
            } else if (iTaskResult.getCode() == 800 && (listener = this.m) != null) {
                listener.onDfuErrorFirmwareOverlap();
                return;
            }
            if (iTaskResult.getError() == null || (listener2 = this.m) == null) {
                return;
            }
            listener2.onDfuError(iTaskResult.getError().getRootCause().getMessage(), iTaskResult.getError());
        }
    }

    public DeviceFirmwareUpdateAB setDfuFile(DfuFileList dfuFileList) {
        this.h.setParameter(dfuFileList);
        return this;
    }

    public DeviceFirmwareUpdateAB setListener(DeviceFirmwareUpdate.Listener listener) {
        this.m = listener;
        return this;
    }

    public DeviceFirmwareUpdateAB setLogger(ILogger iLogger) {
        this.n = iLogger;
        this.h.setLogger(iLogger);
        return this;
    }

    public DeviceFirmwareUpdateAB setTransceiver(ITransceiver iTransceiver) {
        this.h.setParameter(ITransceiver.class, iTransceiver);
        return this;
    }

    public DeviceFirmwareUpdateAB start() {
        this.h.start(null, null);
        return this;
    }
}
