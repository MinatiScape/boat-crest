package com.goodix.ble.gr.toolbox.app.libfastdfu.task;

import com.goodix.ble.gr.libdfu.dfu.entity.ImgInfo;
import com.goodix.ble.libble.v2.gb.procedure.GBGattProcedureWrite;
import com.goodix.ble.libcomx.event.EventDisposer;
import com.goodix.ble.libcomx.event.IEventListener;
import com.goodix.ble.libcomx.task.ITaskResult;
import com.goodix.ble.libcomx.task.Task;
import com.goodix.ble.libcomx.util.HexBuilder;
/* loaded from: classes5.dex */
public class StartCopyTask extends Task implements IEventListener<ITaskResult> {
    public final EventDisposer A = new EventDisposer();
    public final HexBuilder B;
    public final FastDfuProfile z;

    public StartCopyTask(FastDfuProfile fastDfuProfile, ImgInfo imgInfo, int i, int i2) {
        this.z = fastDfuProfile;
        HexBuilder hexBuilder = new HexBuilder(imgInfo.getSerializeSize() + 4 + 1 + 4 + 4);
        this.B = hexBuilder;
        hexBuilder.put(1196379972, 4);
        hexBuilder.put(8, 1);
        imgInfo.serialize(hexBuilder);
        hexBuilder.put(i, 4);
        hexBuilder.put(i2, 4);
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public int doWork() {
        GBGattProcedureWrite writeByCommand = this.z.getFdsCmd().writeByCommand(this.B.getBuffer(), false);
        writeByCommand.evtFinished().subEvent().setExecutor(getExecutor()).setDisposer(this.A).register2(this);
        writeByCommand.start(this, null);
        return 0;
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public void onCleanup() {
        super.onCleanup();
        this.A.disposeAll(this);
    }

    @Override // com.goodix.ble.libcomx.event.IEventListener
    public void onEvent(Object obj, int i, ITaskResult iTaskResult) {
        finished(iTaskResult.getCode(), iTaskResult.getError());
    }
}
