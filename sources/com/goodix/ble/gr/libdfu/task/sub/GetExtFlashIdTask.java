package com.goodix.ble.gr.libdfu.task.sub;

import com.goodix.ble.gr.libdfu.dfu.cmd.Cmd;
import com.goodix.ble.gr.libdfu.dfu.cmd.sdu.XGetFlashInfoResponse;
import com.goodix.ble.gr.libdfu.task.param.ExtFlashId;
import com.goodix.ble.libcomx.event.Event;
import com.goodix.ble.libcomx.event.IEventListener;
import com.goodix.ble.libcomx.task.Task;
import com.goodix.ble.libcomx.task.TaskOutput;
import com.goodix.ble.libcomx.task.TaskParameter;
import com.goodix.ble.libcomx.transceiver.IFrameSdu4Rx;
import com.goodix.ble.libcomx.transceiver.ITransceiver;
/* loaded from: classes5.dex */
public class GetExtFlashIdTask extends Task implements IEventListener<IFrameSdu4Rx> {
    @TaskOutput
    public ExtFlashId A;
    public Event B;
    @TaskParameter
    public ITransceiver z;

    @Override // com.goodix.ble.libcomx.task.Task
    public int doWork() {
        Event<IFrameSdu4Rx> subEvent = this.z.evtRcvFrame().subEvent();
        this.B = subEvent;
        subEvent.setExecutor(getExecutor());
        this.B.register(this);
        if (this.z.send(Cmd.GetExtFlashId.CODE, Cmd.GetExtFlashId.getTxSdu().setFlashType(true))) {
            return 5000;
        }
        finishedWithError(ResultCode.SEND_CMD_FAILD, "Failed to send command.");
        return 0;
    }

    public ExtFlashId getExtFlashId() {
        return this.A;
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public void onCleanup() {
        Event event = this.B;
        if (event != null) {
            event.clear();
            this.B = null;
        }
    }

    @Override // com.goodix.ble.libcomx.event.IEventListener
    public void onEvent(Object obj, int i, IFrameSdu4Rx iFrameSdu4Rx) {
        if (i == Cmd.GetExtFlashId.CODE) {
            XGetFlashInfoResponse xGetFlashInfoResponse = (XGetFlashInfoResponse) iFrameSdu4Rx;
            if (xGetFlashInfoResponse.response == 1) {
                ExtFlashId extFlashId = new ExtFlashId();
                this.A = extFlashId;
                extFlashId.setId(xGetFlashInfoResponse.id);
                setParameter(this.A);
                finished(0, null);
                return;
            }
            finishedWithError(23, "Failed to reset device.");
        }
    }
}
