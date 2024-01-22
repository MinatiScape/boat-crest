package com.goodix.ble.gr.toolbox.app.libfastdfu.task;

import com.goodix.ble.libcomx.event.EventDisposer;
import com.goodix.ble.libcomx.event.IEventListener;
import com.goodix.ble.libcomx.task.Task;
import com.goodix.ble.libcomx.task.TaskParameter;
import com.goodix.ble.libcomx.util.HexBuilder;
/* loaded from: classes5.dex */
public class FlashSelectTask extends Task implements IEventListener<byte[]> {
    public EventDisposer A = new EventDisposer();
    public boolean B;
    @TaskParameter
    public FastDfuProfile z;

    public FlashSelectTask(boolean z) {
        this.B = z;
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public int doWork() {
        this.z.getFdsCmd().evtNotify().subEvent().setExecutor(getExecutor()).setDisposer(this.A).register2(this);
        HexBuilder hexBuilder = new HexBuilder(6);
        hexBuilder.put(1196379972, 4);
        hexBuilder.put(5, 1);
        hexBuilder.put(this.B ? 1 : 0, 1);
        this.z.getFdsCmd().writeByCommand(hexBuilder.getBuffer(), false).start(this, null);
        return 10000;
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public void onCleanup() {
        super.onCleanup();
        this.A.disposeAll(this);
    }

    @Override // com.goodix.ble.libcomx.event.IEventListener
    public void onEvent(Object obj, int i, byte[] bArr) {
        if (bArr[0] == 5) {
            finishedWithDone();
        }
    }
}
