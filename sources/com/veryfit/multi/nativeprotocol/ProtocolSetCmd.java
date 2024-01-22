package com.veryfit.multi.nativeprotocol;
/* loaded from: classes12.dex */
public class ProtocolSetCmd {

    /* renamed from: a  reason: collision with root package name */
    public static ProtocolSetCmd f13862a;

    public static synchronized ProtocolSetCmd getInstance() {
        ProtocolSetCmd protocolSetCmd;
        synchronized (ProtocolSetCmd.class) {
            if (f13862a == null) {
                f13862a = new ProtocolSetCmd();
            }
            protocolSetCmd = f13862a;
        }
        return protocolSetCmd;
    }

    public native int ProtocolMissedCallEvt();

    public native int ProtocolSetAlarmEnd();

    public native int ProtocolSetCallEvt(byte[] bArr, byte[] bArr2);

    public native int ProtocolSetNoticeEvt(int i, byte[] bArr, byte[] bArr2, byte[] bArr3);

    public native int ProtocolStopCallEvt();

    public native int ProtooclCleanAlarm();
}
