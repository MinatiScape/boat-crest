package com.goodix.ble.gr.libdfu.task.sub;

import com.clevertap.android.sdk.Constants;
import com.goodix.ble.gr.libdfu.define.MemoryLayout;
import com.goodix.ble.gr.libdfu.dfu.cmd.Cmd;
import com.goodix.ble.gr.libdfu.dfu.cmd.sdu.XEmptyResponse;
import com.goodix.ble.gr.libdfu.dfu.cmd.sdu.XProgramEnd;
import com.goodix.ble.gr.libdfu.dfu.cmd.sdu.XProgramFlash;
import com.goodix.ble.gr.libdfu.dfu.cmd.sdu.XProgramStart;
import com.goodix.ble.gr.libdfu.dfu.cmd.sdu.XSystemConfig;
import com.goodix.ble.gr.libdfu.dfu.cmd.sdu.XSystemConfigResponse;
import com.goodix.ble.gr.libdfu.dfu.entity.BootInfo;
import com.goodix.ble.gr.libdfu.dfu.entity.DfuFile;
import com.goodix.ble.gr.libdfu.dfu.entity.ImgInfo;
import com.goodix.ble.libcomx.ILogger;
import com.goodix.ble.libcomx.event.Event;
import com.goodix.ble.libcomx.event.IEventListener;
import com.goodix.ble.libcomx.task.Task;
import com.goodix.ble.libcomx.task.TaskParameter;
import com.goodix.ble.libcomx.transceiver.IFrameSdu4Rx;
import com.goodix.ble.libcomx.transceiver.ITransceiver;
import com.goodix.ble.libcomx.util.HexBuilder;
import com.goodix.ble.libcomx.util.HexReader;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.goodix.ble.libcomx.util.IntUtil;
import java.util.Timer;
import java.util.TimerTask;
/* loaded from: classes5.dex */
public class UploadDfuFileTask extends Task implements IEventListener<IFrameSdu4Rx> {
    public static final int EVT_SPEED_UPDATED = 173;
    @TaskParameter
    public ITransceiver A;
    public BootInfo B;
    public boolean C;
    public int D;
    public long E;
    public Event F;
    public int K;
    public boolean L;
    public TimerTask M;
    public int N;
    public int O;
    public long P;
    @TaskParameter
    public DfuFile z;
    public boolean G = false;
    public boolean H = false;
    public boolean I = false;
    public boolean J = false;
    public Event<Void> Q = new Event<>(this, 173);
    public Timer R = null;

    /* loaded from: classes5.dex */
    public class a extends TimerTask {
        public a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            long currentTimeMillis = System.currentTimeMillis();
            if (UploadDfuFileTask.this.P == 0 || currentTimeMillis <= UploadDfuFileTask.this.P) {
                return;
            }
            UploadDfuFileTask uploadDfuFileTask = UploadDfuFileTask.this;
            uploadDfuFileTask.O = (int) ((uploadDfuFileTask.N * 1000) / (currentTimeMillis - UploadDfuFileTask.this.P));
            UploadDfuFileTask.this.Q.postEvent(null);
            UploadDfuFileTask.this.P = currentTimeMillis;
            UploadDfuFileTask.this.N = 0;
        }
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public int doWork() {
        byte[] firmware = this.z.getFirmware();
        if (firmware != null && firmware.length > 0) {
            Event<IFrameSdu4Rx> subEvent = this.A.evtRcvFrame().subEvent();
            this.F = subEvent;
            subEvent.setExecutor(getExecutor());
            this.F.register(this);
            this.D = 0;
            this.L = false;
            this.M = null;
            this.C = false;
            this.B = null;
            XSystemConfig txSdu = Cmd.SystemConfig.getTxSdu();
            MemoryLayout memoryLayout = MemoryLayout.BOOT_INFO;
            txSdu.address = memoryLayout.getAddress();
            txSdu.length = memoryLayout.getSize();
            txSdu.opUdate = false;
            if (this.A.send(Cmd.SystemConfig.CODE, txSdu)) {
                return 5000;
            }
            finishedWithError(ResultCode.SEND_CMD_FAILD, "Failed to send command.");
            return 5000;
        }
        finished(0, null);
        return 0;
    }

    public Event<Void> evtSpeedUpdated() {
        return this.Q;
    }

    public final void g() {
        if (this.C) {
            return;
        }
        int length = this.z.getFirmware().length;
        XProgramStart txSdu = Cmd.ProgramStart.getTxSdu();
        if (this.G) {
            if (!this.H && h(this.K, length)) {
                return;
            }
            txSdu.programResourceToFlash(this.H, this.K, length);
        } else {
            ImgInfo imgInfo = this.z.getImgInfo();
            if (imgInfo != null && imgInfo.getPatern() == 18244) {
                if (this.I) {
                    int i = this.K;
                    int loadAddr = imgInfo.getBootInfo().getLoadAddr();
                    if (IntUtil.memoryOverlap(i, length, loadAddr, length)) {
                        finishedWithError(341, new HexStringBuilder(64).put("Copy address overlaps firmware: ").Ox().put(i).put(" in ").Ox().put(loadAddr).put("-").Ox().put(loadAddr + length).toString());
                        return;
                    } else if (h(i, length)) {
                        return;
                    } else {
                        HexBuilder hexBuilder = new HexBuilder(imgInfo.getSerializeSize());
                        imgInfo.serialize(hexBuilder);
                        imgInfo = new ImgInfo();
                        imgInfo.deserialize(new HexReader(hexBuilder.getBuffer()));
                        imgInfo.getBootInfo().setLoadAddr(this.K);
                    }
                } else if (h(imgInfo.getBootInfo().getLoadAddr(), length)) {
                    return;
                }
                txSdu.programInnerFlash(imgInfo);
                this.K = imgInfo.getBootInfo().getLoadAddr();
            } else {
                finishedWithError("Invalid image information in DFU file.");
                return;
            }
        }
        if (this.A.send(Cmd.ProgramStart.CODE, txSdu)) {
            this.E = System.currentTimeMillis();
            this.C = true;
            return;
        }
        finishedWithError(-1, "Failed to send start command.");
    }

    public int getInstantaneousSpeed() {
        return this.O;
    }

    public final boolean h(int i, int i2) {
        BootInfo bootInfo = this.B;
        if (bootInfo == null) {
            return false;
        }
        int runAddr = bootInfo.getRunAddr();
        int appSize = this.B.getAppSize();
        if (IntUtil.memoryOverlap(runAddr, appSize, i, i2)) {
            finishedWithError(341, new HexStringBuilder(64).put("Running firmware is overlapped: ").Ox().put(i).put("-").Ox().put(i + i2).put(" overwrite ").Ox().put(runAddr).put("-").Ox().put(runAddr + appSize).toString());
            return true;
        }
        return false;
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public void onCleanup() {
        Event event = this.F;
        if (event != null) {
            event.clear();
            this.F = null;
        }
        TimerTask timerTask = this.M;
        if (timerTask != null) {
            timerTask.cancel();
            this.M = null;
        }
        Timer timer = this.R;
        if (timer != null) {
            timer.cancel();
            this.R = null;
        }
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public void onTimeout(int i) {
        if (i == 666 && this.L) {
            this.M = null;
            this.L = false;
            finished(0, null);
        }
    }

    public UploadDfuFileTask setAsCopyMode(int i) {
        this.G = false;
        this.H = false;
        this.I = true;
        this.J = true;
        this.K = i;
        return this;
    }

    public UploadDfuFileTask setAsDfuMode(boolean z) {
        this.G = false;
        this.H = false;
        this.I = false;
        this.J = z;
        this.K = 0;
        return this;
    }

    public UploadDfuFileTask setAsResourceMode(boolean z, int i) {
        this.G = true;
        this.H = z;
        this.I = false;
        this.J = false;
        this.K = i;
        return this;
    }

    @Override // com.goodix.ble.libcomx.event.IEventListener
    public void onEvent(Object obj, int i, IFrameSdu4Rx iFrameSdu4Rx) {
        boolean z = true;
        if (Cmd.SystemConfig.CODE == i) {
            XSystemConfigResponse xSystemConfigResponse = (XSystemConfigResponse) iFrameSdu4Rx;
            if (xSystemConfigResponse.response == 1 && !xSystemConfigResponse.imgInfos.isEmpty()) {
                this.B = xSystemConfigResponse.imgInfos.get(0).getBootInfo();
            }
            g();
        } else if (Cmd.ProgramStart.CODE == i || Cmd.ProgramFlash.CODE == i || Cmd.ProgramEnd.CODE == i) {
            XEmptyResponse xEmptyResponse = (XEmptyResponse) iFrameSdu4Rx;
            HexStringBuilder hexStringBuilder = new HexStringBuilder(128);
            hexStringBuilder.format("Response: 0x%02X", Integer.valueOf(xEmptyResponse.response));
            long currentTimeMillis = System.currentTimeMillis() - this.E;
            if (xEmptyResponse.response == 1) {
                if (Cmd.ProgramStart.CODE == i) {
                    this.P = System.currentTimeMillis();
                    this.O = 0;
                    this.N = 0;
                    Timer timer = this.R;
                    if (timer != null) {
                        timer.cancel();
                    }
                    Timer timer2 = new Timer();
                    this.R = timer2;
                    timer2.scheduleAtFixedRate(new a(), 0L, 1000L);
                }
                if (Cmd.ProgramFlash.CODE == i || Cmd.ProgramStart.CODE == i) {
                    if (this.D < this.z.getFirmware().length) {
                        XProgramFlash txSdu = Cmd.ProgramFlash.getTxSdu();
                        txSdu.setType(this.H, 1);
                        txSdu.address = this.K + this.D;
                        int length = this.z.getFirmware().length - this.D;
                        if (length > 1024) {
                            length = 1024;
                        }
                        txSdu.setData(this.z.getFirmware(), this.D, length);
                        if (this.A.send(Cmd.ProgramFlash.CODE, txSdu)) {
                            this.D += length;
                            this.N += length;
                            z = false;
                        }
                        int length2 = (this.D * 100) / this.z.getFirmware().length;
                        publishProgress(length2);
                        hexStringBuilder.newLine().put("Send Flash: 0x").put(this.D);
                        hexStringBuilder.newLine().put("Total: 0x").put(this.z.getFirmware().length);
                        StringBuilder stringBuilder = hexStringBuilder.newLine().put("Progress: ").getStringBuilder();
                        stringBuilder.append(length2);
                        stringBuilder.append("%");
                        StringBuilder stringBuilder2 = hexStringBuilder.newLine().put("Time: ").getStringBuilder();
                        stringBuilder2.append(currentTimeMillis);
                        stringBuilder2.append("ms");
                    } else {
                        XProgramEnd txSdu2 = Cmd.ProgramEnd.getTxSdu();
                        txSdu2.checksum = this.z.getFileChecksum();
                        if (this.G) {
                            txSdu2.resetFlag = this.H ? 18 : 2;
                        } else {
                            txSdu2.resetFlag = this.J ? 1 : 0;
                        }
                        if (this.A.send(Cmd.ProgramEnd.CODE, txSdu2)) {
                            this.L = true;
                            startTimer(666, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
                            z = false;
                        }
                        hexStringBuilder.newLine().put("Send End: 0x").put(this.D);
                        publishProgress(100);
                    }
                    if (z) {
                        finishedWithError(-1, "Failed to send data.");
                    }
                }
                if (Cmd.ProgramEnd.CODE == i) {
                    hexStringBuilder.newLine().put("Send Complete.");
                    StringBuilder stringBuilder3 = hexStringBuilder.newLine().put("Time: ").getStringBuilder();
                    stringBuilder3.append(currentTimeMillis);
                    stringBuilder3.append("ms");
                    finished(0, null);
                }
            } else {
                if (Cmd.ProgramFlash.CODE == i) {
                    hexStringBuilder.newLine().put("Address: 0x").put(this.K + this.D);
                    StringBuilder stringBuilder4 = hexStringBuilder.newLine().put("Time: ").getStringBuilder();
                    stringBuilder4.append(currentTimeMillis);
                    stringBuilder4.append("ms");
                }
                if (Cmd.ProgramEnd.CODE == i) {
                    hexStringBuilder.newLine().put("Send Complete Failed.");
                    StringBuilder stringBuilder5 = hexStringBuilder.newLine().put("Time: ").getStringBuilder();
                    stringBuilder5.append(currentTimeMillis);
                    stringBuilder5.append("ms");
                }
                finishedWithError(-1, hexStringBuilder.toString());
            }
            ILogger iLogger = this.logger;
            if (iLogger == null || !this.printVerboseLog) {
                return;
            }
            iLogger.v("UploadDfuFileTask", hexStringBuilder.toString());
        }
    }
}
