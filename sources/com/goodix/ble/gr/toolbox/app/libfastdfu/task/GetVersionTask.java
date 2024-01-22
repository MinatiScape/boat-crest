package com.goodix.ble.gr.toolbox.app.libfastdfu.task;

import com.goodix.ble.libcomx.event.EventDisposer;
import com.goodix.ble.libcomx.event.IEventListener;
import com.goodix.ble.libcomx.task.Task;
import com.goodix.ble.libcomx.task.TaskParameter;
import com.goodix.ble.libcomx.util.HexBuilder;
/* loaded from: classes5.dex */
public class GetVersionTask extends Task implements IEventListener<byte[]> {
    public final EventDisposer A = new EventDisposer();
    public int version = 1;
    @TaskParameter
    public FastDfuProfile z;

    @Override // com.goodix.ble.libcomx.task.Task
    public int doWork() {
        this.z.getFdsCmd().evtNotify().subEvent().setExecutor(getExecutor()).setDisposer(this.A).register2(this);
        HexBuilder hexBuilder = new HexBuilder(5);
        hexBuilder.put(1196379972, 4);
        hexBuilder.put(11, 1);
        this.z.getFdsCmd().writeByCommand(hexBuilder.getBuffer(), false).start(this, null);
        return 1000;
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public void onCleanup() {
        super.onCleanup();
        this.A.disposeAll(this);
    }

    @Override // com.goodix.ble.libcomx.event.IEventListener
    public void onEvent(Object obj, int i, byte[] bArr) {
        if (bArr[0] == 11) {
            FastDfuProfile fastDfuProfile = this.z;
            int i2 = bArr[1] & 255;
            this.version = i2;
            fastDfuProfile.version = i2;
            finishedWithDone();
        }
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public void onTaskExpired() {
        FastDfuProfile fastDfuProfile = this.z;
        this.version = 1;
        fastDfuProfile.version = 1;
        finishedWithDone();
    }
}
