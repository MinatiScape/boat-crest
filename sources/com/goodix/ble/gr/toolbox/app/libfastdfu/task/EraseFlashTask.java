package com.goodix.ble.gr.toolbox.app.libfastdfu.task;

import com.goodix.ble.libcomx.event.EventDisposer;
import com.goodix.ble.libcomx.event.IEventListener;
import com.goodix.ble.libcomx.logger.Logger;
import com.goodix.ble.libcomx.task.Task;
import com.goodix.ble.libcomx.util.HexBuilder;
import com.goodix.ble.libcomx.util.HexEndian;
/* loaded from: classes5.dex */
public class EraseFlashTask extends Task implements IEventListener<byte[]> {
    public static final int SIZE_4K = 4096;
    public EventDisposer A = new EventDisposer();
    public HexBuilder B;
    public int C;
    public FastDfuProfile z;

    public EraseFlashTask(FastDfuProfile fastDfuProfile) {
        this.z = fastDfuProfile;
    }

    public EraseFlashTask(FastDfuProfile fastDfuProfile, int i, int i2) {
        this.z = fastDfuProfile;
        setTarget(i, i2);
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public int doWork() {
        this.z.getFdsCmd().evtNotify().subEvent().setExecutor(getExecutor()).setDisposer(this.A).register2(this);
        this.z.getFdsCmd().writeByCommand(this.B.getBuffer(), false).startProcedure();
        return 10000;
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public void onCleanup() {
        super.onCleanup();
        this.A.disposeAll(this);
    }

    @Override // com.goodix.ble.libcomx.event.IEventListener
    public void onEvent(Object obj, int i, byte[] bArr) {
        int i2;
        String str;
        int i3 = 0;
        if (bArr[0] == 1) {
            refreshTaskTimeout();
            switch (bArr[1] & 255) {
                case 0:
                    i2 = 112;
                    str = "Address is not 4K aligned.";
                    finishedWithError(i2, str);
                    return;
                case 1:
                    if (this.printVerboseLog) {
                        Logger.v(this.logger, "EraseFlashTask", "Start erasing...");
                    }
                    publishProgress(i3);
                    return;
                case 2:
                    i3 = (HexEndian.fromByte(bArr, 2, 2, false) * 100) / this.C;
                    publishProgress(i3);
                    return;
                case 3:
                    finishedWithDone();
                    return;
                case 4:
                    i2 = 230;
                    str = "Overlap running firmware.";
                    finishedWithError(i2, str);
                    return;
                case 5:
                    i2 = 113;
                    str = "Failed to erase.";
                    finishedWithError(i2, str);
                    return;
                case 6:
                    i2 = 114;
                    str = "No ext flash.";
                    finishedWithError(i2, str);
                    return;
                default:
                    return;
            }
        }
    }

    public EraseFlashTask setTarget(int i, int i2) {
        HexBuilder hexBuilder = this.B;
        if (hexBuilder == null) {
            HexBuilder hexBuilder2 = new HexBuilder(13);
            this.B = hexBuilder2;
            hexBuilder2.put(1196379972, 4);
            this.B.put(1, 1);
        } else {
            hexBuilder.setPos(5);
        }
        this.B.put(i, 4);
        this.B.put(i2, 4);
        int i3 = ((i2 + 4096) - 1) / 4096;
        this.C = i3;
        if (i3 <= 0) {
            this.C = 1;
        }
        return this;
    }
}
