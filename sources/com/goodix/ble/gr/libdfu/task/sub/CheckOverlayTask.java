package com.goodix.ble.gr.libdfu.task.sub;

import com.goodix.ble.gr.libdfu.dfu.entity.BootInfo;
import com.goodix.ble.gr.libdfu.dfu.entity.DfuFile;
import com.goodix.ble.gr.libdfu.task.param.ImgInfoList;
import com.goodix.ble.libcomx.task.Task;
import com.goodix.ble.libcomx.task.TaskParameter;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.goodix.ble.libcomx.util.IntUtil;
/* loaded from: classes5.dex */
public class CheckOverlayTask extends Task {
    @TaskParameter
    public ImgInfoList A;
    public Integer B = null;
    public boolean C = false;
    public boolean D = false;
    @TaskParameter
    public DfuFile z;

    public final boolean d(BootInfo bootInfo, int i, int i2) {
        if (bootInfo == null) {
            return false;
        }
        int runAddr = bootInfo.getRunAddr();
        int appSize = bootInfo.getAppSize();
        if (IntUtil.memoryOverlap(runAddr, appSize, i, i2)) {
            finishedWithError(341, new HexStringBuilder(64).put("Running firmware is overlapped: ").Ox().put(i).put("-").Ox().put(i + i2).put(" overwrite ").Ox().put(runAddr).put("-").Ox().put(runAddr + appSize).toString());
            return true;
        }
        return false;
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public int doWork() {
        if (this.D) {
            finishedWithDone();
            return 0;
        }
        BootInfo bootInfo = this.A.getList().get(0).getBootInfo();
        int length = this.z.getFirmware().length;
        Integer num = this.B;
        if (num != null) {
            if (this.C) {
                d(bootInfo, num.intValue(), length);
                return 0;
            } else if (d(bootInfo, num.intValue(), length)) {
                return 0;
            } else {
                int intValue = this.B.intValue();
                int loadAddr = this.z.getImgInfo().getBootInfo().getLoadAddr();
                if (IntUtil.memoryOverlap(intValue, length, loadAddr, length)) {
                    finishedWithError(341, new HexStringBuilder(64).put("Copy address overlaps firmware: ").Ox().put(intValue).put(" in ").Ox().put(loadAddr).put("-").Ox().put(loadAddr + length).toString());
                }
                return 0;
            }
        }
        d(bootInfo, this.z.getImgInfo().getBootInfo().getLoadAddr(), length);
        return 0;
    }

    public CheckOverlayTask setStartAddressInFlash(Integer num, boolean z, boolean z2) {
        this.B = num;
        this.C = z;
        this.D = z2;
        return this;
    }
}
