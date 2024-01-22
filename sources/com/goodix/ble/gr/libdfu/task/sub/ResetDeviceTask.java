package com.goodix.ble.gr.libdfu.task.sub;

import com.goodix.ble.gr.libdfu.dfu.cmd.Cmd;
import com.goodix.ble.gr.libdfu.dfu.cmd.sdu.XEmptyResponse;
import com.goodix.ble.gr.libdfu.dfu.cmd.sdu.XReset;
import com.goodix.ble.gr.libdfu.dfu.entity.DfuFile;
import com.goodix.ble.gr.libdfu.dfu.entity.ImgInfo;
import com.goodix.ble.libcomx.ILogger;
import com.goodix.ble.libcomx.event.Event;
import com.goodix.ble.libcomx.event.IEventListener;
import com.goodix.ble.libcomx.task.Task;
import com.goodix.ble.libcomx.task.TaskParameter;
import com.goodix.ble.libcomx.transceiver.IFrameSdu4Rx;
import com.goodix.ble.libcomx.transceiver.ITransceiver;
/* loaded from: classes5.dex */
public class ResetDeviceTask extends Task implements IEventListener<IFrameSdu4Rx> {
    @TaskParameter(nullable = true)
    public ImgInfo A;
    @TaskParameter(nullable = true)
    public DfuFile B;
    public Event C;
    @TaskParameter
    public ITransceiver z;

    @Override // com.goodix.ble.libcomx.task.Task
    public int doWork() {
        DfuFile dfuFile = this.B;
        if (dfuFile != null && dfuFile.isValidDfuFile()) {
            this.A = this.B.getImgInfo();
            ILogger iLogger = this.logger;
            if (iLogger != null) {
                iLogger.v("ResetDeviceTask", "reset to use boot info from DFU file.");
            }
        }
        Event<IFrameSdu4Rx> subEvent = this.z.evtRcvFrame().subEvent();
        this.C = subEvent;
        subEvent.setExecutor(getExecutor());
        this.C.register(this);
        XReset txSdu = Cmd.Reset.getTxSdu();
        ImgInfo imgInfo = this.A;
        if (imgInfo != null) {
            txSdu.info = imgInfo.getBootInfo();
            ILogger iLogger2 = this.logger;
            if (iLogger2 != null) {
                iLogger2.v("ResetDeviceTask", "reset to use boot info from specified img info.");
            }
        } else {
            ILogger iLogger3 = this.logger;
            if (iLogger3 != null) {
                iLogger3.v("ResetDeviceTask", "reset only.");
            }
        }
        if (this.z.send(Cmd.Reset.CODE, txSdu)) {
            return 5000;
        }
        finishedWithError(ResultCode.SEND_CMD_FAILD, "Failed to send command.");
        return 0;
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public void onCleanup() {
        Event event = this.C;
        if (event != null) {
            event.clear();
            this.C = null;
        }
    }

    @Override // com.goodix.ble.libcomx.event.IEventListener
    public void onEvent(Object obj, int i, IFrameSdu4Rx iFrameSdu4Rx) {
        if (i == Cmd.Reset.CODE) {
            if (((XEmptyResponse) iFrameSdu4Rx).response == 1) {
                finished(0, null);
            } else {
                finishedWithError(23, "Failed to reset device.");
            }
        }
    }
}
