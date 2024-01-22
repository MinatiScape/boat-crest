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
import com.goodix.ble.libcomx.util.HexEndian;
import java.io.ByteArrayInputStream;
/* loaded from: classes5.dex */
public class DownloadDataTask extends Task implements IEventListener {
    @TaskParameter
    public FastDfuProfile A;
    public GBGattProcedureWrite C;
    public GBGattProcedureWrite D;
    public int G;
    public ILogger J;
    @TaskParameter
    public DfuFile z;
    public final EventDisposer B = new EventDisposer();
    public byte[] E = null;
    public ByteArrayInputStream F = null;
    public boolean H = true;
    public int I = 3000;

    public final void d() {
        CharacteristicWrite characteristicWrite;
        ByteArrayInputStream byteArrayInputStream;
        if (this.E == null) {
            synchronized (this) {
                if (this.E == null) {
                    this.E = new byte[4096];
                    this.F = new ByteArrayInputStream(this.E);
                }
            }
        }
        if (this.G == 0) {
            synchronized (this) {
                ILogger logger = this.A.getRemoteDevice().getLogger();
                this.J = logger;
                if (logger != null) {
                    this.A.getRemoteDevice().setLogger(null);
                    this.J.i(getName(), "remove logger for high speed temporarily.");
                }
            }
        }
        byte[] firmware = this.z.getFirmware();
        int i = this.G;
        if (i < firmware.length) {
            byte[] bArr = this.E;
            int length = bArr.length;
            if (i + length > firmware.length) {
                length = firmware.length - i;
            }
            System.arraycopy(firmware, i, bArr, 0, length);
            if (length == this.E.length) {
                this.F.reset();
                characteristicWrite = (CharacteristicWrite) this.C;
                byteArrayInputStream = this.F;
            } else {
                characteristicWrite = (CharacteristicWrite) this.C;
                byteArrayInputStream = new ByteArrayInputStream(this.E, 0, length);
            }
            characteristicWrite.setValue(byteArrayInputStream, false);
            this.G += length;
            this.C.start(null, null);
            publishProgress((this.G * 100) / firmware.length);
        }
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public int doWork() {
        this.G = 0;
        HexBuilder hexBuilder = new HexBuilder(5);
        hexBuilder.put(1196379972, 4);
        hexBuilder.put(2, 1);
        GBGattProcedureWrite writeByCommand = this.A.getFdsCmd().writeByCommand(hexBuilder.getBuffer(), false);
        this.D = writeByCommand;
        writeByCommand.evtFinished().subEvent().setExecutor(getExecutor()).setDisposer(this.B).register(this);
        this.A.getFdsCmd().evtNotify().subEvent().setExecutor(getExecutor()).setDisposer(this.B).register(this);
        GBGattProcedureWrite writeByCommand2 = this.A.getFdsDat().writeByCommand(null, false);
        this.C = writeByCommand2;
        writeByCommand2.evtProgress().subEvent().setExecutor(getExecutor()).setDisposer(this.B).register(this);
        this.C.evtFinished().subEvent().setExecutor(getExecutor()).setDisposer(this.B).register(this);
        this.C.setDebounceProgressEvent(false);
        startTimer(547, 200L, 100L);
        HexBuilder hexBuilder2 = new HexBuilder(5);
        hexBuilder2.put(1196379972, 4);
        hexBuilder2.put(9, 1);
        this.A.getFdsCmd().writeByCommand(hexBuilder2.getBuffer(), false).setName("GetBufferSize").start(this, null);
        this.A.getSpeedometer().start();
        return this.I;
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public void onCleanup() {
        super.onCleanup();
        this.B.disposeAll(this);
        FastDfuProfile fastDfuProfile = this.A;
        if (fastDfuProfile != null) {
            fastDfuProfile.getSpeedometer().stop();
            synchronized (this) {
                ILogger iLogger = this.J;
                if (iLogger != null) {
                    iLogger.i(getName(), "recover logger.");
                    this.A.getRemoteDevice().setLogger(this.J);
                }
            }
            GBGattProcedureWrite gBGattProcedureWrite = this.C;
            if (gBGattProcedureWrite != null) {
                gBGattProcedureWrite.abort();
            }
        }
    }

    @Override // com.goodix.ble.libcomx.event.IEventListener
    public void onEvent(Object obj, int i, Object obj2) {
        String str;
        ITaskResult iTaskResult;
        if (obj == this.C) {
            if (i == 341) {
                refreshTaskTimeout();
                return;
            } else if (i != 342) {
                return;
            } else {
                iTaskResult = (ITaskResult) obj2;
                if (iTaskResult.getError() == null) {
                    if (this.G >= this.z.getFirmware().length) {
                        synchronized (this) {
                            ILogger iLogger = this.J;
                            if (iLogger != null) {
                                iLogger.i(getName(), "recover logger after transferring all data.");
                                this.A.getRemoteDevice().setLogger(this.J);
                                this.J = null;
                            }
                        }
                        refreshTaskTimeout();
                        this.D.start(this, this.C);
                        return;
                    }
                    return;
                }
            }
        } else if (obj != this.D || i != 342) {
            if (obj == this.A.getFdsCmd() && i == 55) {
                byte[] bArr = (byte[]) obj2;
                if (bArr != null && bArr.length == 1) {
                    if (bArr[0] == 2) {
                        finishedWithDone();
                        return;
                    }
                    if (bArr[0] == 6) {
                        str = "FlowCtrl = true, buffer overflowed.";
                    } else if (bArr[0] == 7) {
                        str = "FlowCtrl = false, not allowed.";
                    } else if (bArr[0] != 10) {
                        return;
                    }
                    finishedWithError(str);
                    return;
                } else if (bArr == null || bArr[0] != 9 || bArr.length != 5) {
                    return;
                } else {
                    int fromByte = HexEndian.fromByte(bArr, 1, 4, false);
                    synchronized (this) {
                        byte[] bArr2 = this.E;
                        if (bArr2 == null || bArr2.length != fromByte) {
                            this.E = new byte[fromByte];
                            this.F = new ByteArrayInputStream(this.E);
                        }
                    }
                    this.H = false;
                }
                d();
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

    @Override // com.goodix.ble.libcomx.task.Task
    public void onTimeout(int i) {
        if (i == 547 && this.H && !this.C.isStarted()) {
            d();
        }
    }

    public DownloadDataTask setTimeout(int i) {
        this.I = i;
        return this;
    }
}
