package com.ido.ble.protocol.model;
/* loaded from: classes11.dex */
public class ActivitySwitch {
    public static final int SWITCH_OFF = 0;
    public static final int SWITCH_ON = 1;
    public int autoEndRemindOnOffOnOff;
    public int autoIdentifySportBicycle;
    public int autoIdentifySportElliptical;
    public int autoIdentifySportRowing;
    public int autoIdentifySportRun;
    public int autoIdentifySportSwim;
    public int autoIdentifySportWalk;
    public int autoPauseOnOff;

    public String toString() {
        return "ActivitySwitch{autoIdentifySportWalk=" + this.autoIdentifySportWalk + ", autoIdentifySportRun=" + this.autoIdentifySportRun + ", autoIdentifySportBicycle=" + this.autoIdentifySportBicycle + ", autoPauseOnOff=" + this.autoPauseOnOff + ", autoEndRemindOnOffOnOff=" + this.autoEndRemindOnOffOnOff + ", autoIdentifySportElliptical=" + this.autoIdentifySportElliptical + ", autoIdentifySportRowing=" + this.autoIdentifySportRowing + ", autoIdentifySportSwim=" + this.autoIdentifySportSwim + '}';
    }
}
