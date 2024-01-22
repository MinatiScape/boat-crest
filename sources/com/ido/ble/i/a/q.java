package com.ido.ble.i.a;

import com.google.gson.Gson;
import com.ido.ble.protocol.handler.u;
import com.ido.ble.protocol.model.VoiceControlAlarm;
import com.ido.ble.protocol.model.VoiceControlBrightnessLevel;
import com.ido.ble.protocol.model.VoiceControlFuncWithNoPara;
import com.ido.ble.protocol.model.VoiceControlMusic;
import com.ido.ble.protocol.model.VoiceControlReminder;
import com.ido.ble.protocol.model.VoiceControlSports;
import com.ido.ble.protocol.model.VoiceControlSwitchFunc;
import com.ido.ble.protocol.model.VoiceCountDown;
import com.ido.ble.protocol.model.VoiceLoginState;
import com.ido.ble.protocol.model.VoiceNotifyState;
import com.ido.ble.protocol.model.VoiceRecognizeState;
import com.ido.ble.protocol.model.VoiceStopWatch;
/* loaded from: classes11.dex */
public class q {
    public static void a() {
        u.b((int) com.veryfit.multi.nativeprotocol.b.u5, (int) com.veryfit.multi.nativeprotocol.b.h5);
    }

    public static void a(VoiceControlAlarm voiceControlAlarm) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(voiceControlAlarm)), (int) com.veryfit.multi.nativeprotocol.b.G3);
    }

    public static void a(VoiceControlBrightnessLevel voiceControlBrightnessLevel) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(voiceControlBrightnessLevel)), (int) com.veryfit.multi.nativeprotocol.b.e5);
    }

    public static void a(VoiceControlFuncWithNoPara voiceControlFuncWithNoPara) {
        int i;
        int i2 = voiceControlFuncWithNoPara.funcType;
        if (i2 == 1) {
            i = com.veryfit.multi.nativeprotocol.b.S4;
        } else if (i2 == 2) {
            i = com.veryfit.multi.nativeprotocol.b.T4;
        } else if (i2 == 3) {
            i = com.veryfit.multi.nativeprotocol.b.X4;
        } else if (i2 == 4) {
            i = com.veryfit.multi.nativeprotocol.b.Y4;
        } else if (i2 != 5) {
            return;
        } else {
            i = com.veryfit.multi.nativeprotocol.b.g5;
        }
        u.b((int) com.veryfit.multi.nativeprotocol.b.t5, i);
    }

    public static void a(VoiceControlMusic voiceControlMusic) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(voiceControlMusic)), (int) com.veryfit.multi.nativeprotocol.b.Z4);
    }

    public static void a(VoiceControlReminder voiceControlReminder) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(voiceControlReminder)), (int) com.veryfit.multi.nativeprotocol.b.H3);
    }

    public static void a(VoiceControlSports voiceControlSports) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(voiceControlSports)), (int) com.veryfit.multi.nativeprotocol.b.O4);
    }

    public static void a(VoiceControlSwitchFunc voiceControlSwitchFunc) {
        int i = voiceControlSwitchFunc.type;
        if (i == 1) {
            u.b(com.ido.ble.common.c.b(new Gson().toJson(voiceControlSwitchFunc)), (int) com.veryfit.multi.nativeprotocol.b.a5);
        } else if (i == 2) {
            u.b(com.ido.ble.common.c.b(new Gson().toJson(voiceControlSwitchFunc)), (int) com.veryfit.multi.nativeprotocol.b.b5);
        } else if (i == 3) {
            u.b(com.ido.ble.common.c.b(new Gson().toJson(voiceControlSwitchFunc)), (int) com.veryfit.multi.nativeprotocol.b.f5);
        }
    }

    public static void a(VoiceCountDown voiceCountDown) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(voiceCountDown)), (int) com.veryfit.multi.nativeprotocol.b.Q4);
    }

    public static void a(VoiceLoginState voiceLoginState) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(voiceLoginState)), (int) com.veryfit.multi.nativeprotocol.b.i5);
    }

    public static void a(VoiceNotifyState voiceNotifyState) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(voiceNotifyState)), (int) com.veryfit.multi.nativeprotocol.b.k5);
    }

    public static void a(VoiceRecognizeState voiceRecognizeState) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(voiceRecognizeState)), (int) com.veryfit.multi.nativeprotocol.b.j5);
    }

    public static void a(VoiceStopWatch voiceStopWatch) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(voiceStopWatch)), (int) com.veryfit.multi.nativeprotocol.b.P4);
    }

    public static void b() {
        u.d(com.veryfit.multi.nativeprotocol.b.L4);
    }

    public static void c() {
        u.d(com.veryfit.multi.nativeprotocol.b.N4);
    }
}
