package com.goodix.ble.gr.toolbox.app.libfastdfu.task;

import com.goodix.ble.gr.libdfu.dfu.entity.DfuFile;
import com.goodix.ble.libble.v2.gb.procedure.GBGattProcedureWrite;
import com.goodix.ble.libble.v2.impl.procedure.CharacteristicWrite;
import com.goodix.ble.libcomx.ILogger;
import com.goodix.ble.libcomx.event.EventDisposer;
import com.goodix.ble.libcomx.event.IEventListener;
import com.goodix.ble.libcomx.task.ITaskResult;
import com.goodix.ble.libcomx.task.Task;
import com.goodix.ble.libcomx.task.TaskParameter;
import com.goodix.ble.libcomx.util.HexBuilder;
/* loaded from: classes5.dex */
public class DownloadDataTask2 extends Task implements IEventListener {
    @TaskParameter
    public FastDfuProfile A;
    public GBGattProcedureWrite C;
    public GBGattProcedureWrite D;
    public ILogger F;
    @TaskParameter
    public DfuFile z;
    public final EventDisposer B = new EventDisposer();
    public int E = 5000;

    @Override // com.goodix.ble.libcomx.task.Task
    public int doWork() {
        byte[] firmware = this.z.getFirmware();
        GBGattProcedureWrite writeByCommand = this.A.getFdsDat().writeByCommand(null, false);
        this.C = writeByCommand;
        ((CharacteristicWrite) writeByCommand).setLargeValue(firmware, 0, firmware.length);
        HexBuilder hexBuilder = new HexBuilder(5);
        hexBuilder.put(1196379972, 4);
        hexBuilder.put(2, 1);
        this.D = this.A.getFdsCmd().writeByCommand(hexBuilder.getBuffer(), false);
        this.A.getFdsCmd().evtNotify().subEvent().setExecutor(getExecutor()).setDisposer(this.B).register(this);
        this.C.evtProgress().subEvent().setExecutor(getExecutor()).setDisposer(this.B).register(this);
        this.C.evtFinished().subEvent().setExecutor(getExecutor()).setDisposer(this.B).register(this);
        this.D.evtFinished().subEvent().setExecutor(getExecutor()).setDisposer(this.B).register(this);
        this.C.setDebounceProgressEvent(false);
        this.C.start(this, null);
        this.A.getSpeedometer().start();
        ILogger logger = this.A.getRemoteDevice().getLogger();
        this.F = logger;
        if (logger != null) {
            this.A.getRemoteDevice().setLogger(null);
            this.F.i(getName(), "remove logger for high speed temporarily.");
        }
        return this.E;
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public void onCleanup() {
        super.onCleanup();
        this.B.disposeAll(this);
        FastDfuProfile fastDfuProfile = this.A;
        if (fastDfuProfile != null) {
            fastDfuProfile.getSpeedometer().stop();
            ILogger iLogger = this.F;
            if (iLogger != null) {
                iLogger.i(getName(), "recover logger.");
                this.A.getRemoteDevice().setLogger(this.F);
            }
            GBGattProcedureWrite gBGattProcedureWrite = this.C;
            if (gBGattProcedureWrite != null) {
                gBGattProcedureWrite.abort();
            }
        }
    }

    @Override // com.goodix.ble.libcomx.event.IEventListener
    public void onEvent(Object obj, int i, Object obj2) {
        byte[] bArr;
        ITaskResult iTaskResult;
        if (obj == this.C) {
            if (i == 341) {
                publishProgress(((Integer) obj2).intValue());
                return;
            } else if (i != 342) {
                return;
            } else {
                iTaskResult = (ITaskResult) obj2;
                if (iTaskResult.getError() == null) {
                    refreshTaskTimeout();
                    this.D.start(this, this.C);
                    return;
                }
            }
        } else if (obj != this.D || i != 342) {
            if (obj == this.A.getFdsCmd() && i == 55 && (bArr = (byte[]) obj2) != null && bArr.length == 1 && bArr[0] == 2) {
                finishedWithDone();
                return;
            }
            return;
        } else {
            iTaskResult = (ITaskResult) obj2;
            if (iTaskResult.getError() == null) {
                return;
            }
        }
        finished(iTaskResult.getCode(), iTaskResult.getError());
    }

    public DownloadDataTask2 setTimeout(int i) {
        this.E = i;
        return this;
    }
}
