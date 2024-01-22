package com.goodix.ble.gr.toolbox.app.libfastdfu.task;

import com.goodix.ble.gr.libdfu.dfu.entity.DfuFile;
import com.goodix.ble.libcomx.event.EventDisposer;
import com.goodix.ble.libcomx.event.IEventListener;
import com.goodix.ble.libcomx.task.Task;
import com.goodix.ble.libcomx.task.TaskParameter;
import com.goodix.ble.libcomx.util.HexBuilder;
import com.goodix.ble.libcomx.util.HexEndian;
/* loaded from: classes5.dex */
public class ChecksumTask extends Task implements IEventListener<byte[]> {
    @TaskParameter
    public FastDfuProfile A;
    public EventDisposer B = new EventDisposer();
    @TaskParameter
    public DfuFile z;

    @Override // com.goodix.ble.libcomx.task.Task
    public int doWork() {
        this.A.getFdsCmd().evtNotify().subEvent().setExecutor(getExecutor()).setDisposer(this.B).register2(this);
        HexBuilder hexBuilder = new HexBuilder(9);
        hexBuilder.put(1196379972, 4);
        hexBuilder.put(3, 1);
        hexBuilder.put(this.z.getFileChecksum(), 4);
        this.A.getFdsCmd().writeByCommand(hexBuilder.getBuffer(), false).start(this, null);
        return 10000;
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public void onCleanup() {
        super.onCleanup();
        this.B.disposeAll(this);
    }

    @Override // com.goodix.ble.libcomx.event.IEventListener
    public void onEvent(Object obj, int i, byte[] bArr) {
        if (bArr[0] == 3) {
            int fromByte = HexEndian.fromByte(bArr, 1, 4, false);
            int fileChecksum = this.z.getFileChecksum();
            if (fromByte == fileChecksum) {
                finishedWithDone();
                return;
            }
            finishedWithError("Checksum: " + fromByte + " is not equal to expected: " + fileChecksum);
        }
    }
}
