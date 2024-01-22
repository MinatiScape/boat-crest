package com.goodix.ble.gr.libdfu.task.sub;

import com.goodix.ble.gr.libdfu.define.MemoryLayout;
import com.goodix.ble.gr.libdfu.dfu.cmd.Cmd;
import com.goodix.ble.gr.libdfu.dfu.cmd.sdu.XSystemConfig;
import com.goodix.ble.gr.libdfu.dfu.cmd.sdu.XSystemConfigResponse;
import com.goodix.ble.gr.libdfu.dfu.entity.DfuFile;
import com.goodix.ble.gr.libdfu.task.param.ImgInfoList;
import com.goodix.ble.gr.libdfu.task.param.SkipOverwriteCheck;
import com.goodix.ble.gr.libdfu.util.ImgInfoUtil;
import com.goodix.ble.libcomx.event.Event;
import com.goodix.ble.libcomx.event.IEventListener;
import com.goodix.ble.libcomx.task.Task;
import com.goodix.ble.libcomx.task.TaskParameter;
import com.goodix.ble.libcomx.transceiver.IFrameSdu4Rx;
import com.goodix.ble.libcomx.transceiver.ITransceiver;
/* loaded from: classes5.dex */
public class TidyImgInfoListTask extends Task implements IEventListener<IFrameSdu4Rx> {
    @TaskParameter
    public ITransceiver A;
    @TaskParameter
    public ImgInfoList B;
    @TaskParameter(nullable = true)
    public SkipOverwriteCheck C;
    public Event D;
    @TaskParameter
    public DfuFile z;

    @Override // com.goodix.ble.libcomx.task.Task
    public int doWork() {
        if (!this.z.isValidDfuFile()) {
            finishedWithError(ResultCode.INVALID_FILE, "DFU file is invalid.");
            return 0;
        } else if (this.B.isEncrypted() != this.z.isEncrypted()) {
            finishedWithError(ResultCode.INVALID_FILE, "Encryption of device is not equal to file: " + this.B.isEncrypted() + " != " + this.z.isEncrypted());
            return 0;
        } else {
            if (ImgInfoUtil.checkCollision(this.z.getImgInfo(), this.B.getList())) {
                SkipOverwriteCheck skipOverwriteCheck = this.C;
                if (skipOverwriteCheck != null && skipOverwriteCheck.isSkip()) {
                    ImgInfoUtil.overwriteImgInfo(this.z.getImgInfo(), this.B.getList());
                    ImgInfoUtil.sortImgList(this.B.getList());
                    Event<IFrameSdu4Rx> subEvent = this.A.evtRcvFrame().subEvent();
                    this.D = subEvent;
                    subEvent.setExecutor(getExecutor());
                    this.D.register(this);
                    XSystemConfig txSdu = Cmd.SystemConfig.getTxSdu();
                    txSdu.address = MemoryLayout.SYSTEM_CONFIG.getAddress();
                    txSdu.opUdate = true;
                    txSdu.imgInfos = this.B.getList();
                    if (this.A.send(Cmd.SystemConfig.CODE, txSdu)) {
                        return 5000;
                    }
                    finishedWithError(ResultCode.SEND_CMD_FAILD, "Failed to send command.");
                } else {
                    finishedWithError(800, "DFU file could overwrite the firmware on device.");
                }
            } else {
                finishedWithDone();
            }
            return 0;
        }
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public void onCleanup() {
        Event event = this.D;
        if (event != null) {
            event.clear();
            this.D = null;
        }
    }

    public TidyImgInfoListTask setCanOverwrite(boolean z) {
        SkipOverwriteCheck skipOverwriteCheck = this.C;
        if (skipOverwriteCheck != null) {
            skipOverwriteCheck.setSkip(z);
        } else {
            this.C = new SkipOverwriteCheck(z);
        }
        return this;
    }

    @Override // com.goodix.ble.libcomx.event.IEventListener
    public void onEvent(Object obj, int i, IFrameSdu4Rx iFrameSdu4Rx) {
        if (i == Cmd.SystemConfig.CODE) {
            XSystemConfigResponse xSystemConfigResponse = (XSystemConfigResponse) iFrameSdu4Rx;
            if (xSystemConfigResponse.response == 1) {
                if (xSystemConfigResponse.opUdated) {
                    finished(0, null);
                    return;
                }
                return;
            }
            finishedWithError(ResultCode.SET_IMG_LIST_FAILED, "Failed to update image list.");
        }
    }
}
