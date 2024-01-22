package com.jieli.jl_rcsp.model.device.health;

import com.jieli.jl_rcsp.model.device.AttrBean;
import java.nio.ByteBuffer;
/* loaded from: classes11.dex */
public class LiftWristDetection implements IHealthSettingToAttr {
    public static final byte MODE_BRIGHT = 0;
    public static final byte MODE_SHAKE = 1;
    public static final byte STATUS_ALL_DAY = 1;
    public static final byte STATUS_CLOSE = 0;
    public static final byte STATUS_CUSTOM_TIME = 2;
    public byte endHour;
    public byte endMin;
    public byte mode;
    public byte startHour;
    public byte startMin;
    public byte status;

    public LiftWristDetection(byte[] bArr) {
        parseData(bArr);
    }

    private void parseData(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return;
        }
        this.status = bArr[0];
        int i = 2;
        if (bArr.length >= 2) {
            this.mode = bArr[1];
        } else {
            i = 1;
        }
        if (bArr.length >= i + 2) {
            this.startHour = (byte) Math.max(Math.min((int) bArr[i], 23), 0);
            int i2 = i + 1;
            this.startMin = (byte) Math.max(Math.min((int) bArr[i2], 59), 0);
            i = i2 + 1;
        }
        if (bArr.length >= i + 2) {
            this.endHour = (byte) Math.max(Math.min((int) bArr[i], 23), 0);
            this.endMin = (byte) Math.max(Math.min((int) bArr[i + 1], 59), 0);
        }
    }

    private byte[] toData() {
        ByteBuffer allocate = ByteBuffer.allocate(6);
        allocate.put(this.status).put(this.mode).put(this.startHour).put(this.startMin).put(this.endHour).put(this.endMin);
        return allocate.array();
    }

    public byte getEndHour() {
        return this.endHour;
    }

    public byte getEndMin() {
        return this.endMin;
    }

    public byte getMode() {
        return this.mode;
    }

    public byte getStartHour() {
        return this.startHour;
    }

    public byte getStartMin() {
        return this.startMin;
    }

    public byte getStatus() {
        return this.status;
    }

    @Override // com.jieli.jl_rcsp.model.device.health.IHealthSettingToAttr
    public int getType() {
        return 8;
    }

    public LiftWristDetection setEndHour(byte b) {
        this.endHour = b;
        return this;
    }

    public LiftWristDetection setEndMin(byte b) {
        this.endMin = b;
        return this;
    }

    public LiftWristDetection setMode(byte b) {
        this.mode = b;
        return this;
    }

    public LiftWristDetection setStartHour(byte b) {
        this.startHour = b;
        return this;
    }

    public LiftWristDetection setStartMin(byte b) {
        this.startMin = b;
        return this;
    }

    public LiftWristDetection setStatus(byte b) {
        this.status = b;
        return this;
    }

    @Override // com.jieli.jl_rcsp.model.device.health.IHealthSettingToAttr
    public AttrBean toAttr() {
        return new AttrBean().setType((byte) getType()).setAttrData(toData());
    }

    public String toString() {
        return "MultiStateWithModeSwitch{status=" + ((int) this.status) + ", startHour=" + ((int) this.startHour) + ", startMin=" + ((int) this.startMin) + ", endHour=" + ((int) this.endHour) + ", endMin=" + ((int) this.endMin) + ", mode=" + ((int) this.mode) + '}';
    }
}
