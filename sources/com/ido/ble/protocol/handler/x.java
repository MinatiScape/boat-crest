package com.ido.ble.protocol.handler;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.ido.ble.callback.VoiceCallBack;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.VoiceData;
import com.ido.ble.protocol.model.VoiceOpusSectionData;
import com.ido.ble.protocol.model.VoicePcmSectionData;
import com.ido.ble.protocol.model.VoiceTranState;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class x {
    public static void a(int i, int i2, int i3) {
    }

    public static void a(int i, byte[] bArr, int i2) {
        String d;
        VoiceCallBack.VoiceControlType voiceControlType;
        if (i == 7500) {
            d(bArr, i2);
        } else if (i == 7503) {
            e(bArr, i2);
        } else if (i == 7504) {
            b(bArr, i2);
        } else {
            if (i == 5028) {
                d = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.f12307a, "IDO_VOICE[handle voice control alarm JsonString] " + d);
                voiceControlType = VoiceCallBack.VoiceControlType.VOICE_CONTROL_ALARM;
            } else if (i == 7602) {
                d = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.f12307a, "IDO_VOICE[handle voice control stopwatch JsonString] " + d);
                voiceControlType = VoiceCallBack.VoiceControlType.VOICE_CONTROL_STOPWATCH;
            } else if (i == 5029) {
                d = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.f12307a, "IDO_VOICE[handle voice control reminder JsonString] " + d);
                voiceControlType = VoiceCallBack.VoiceControlType.VOICE_CONTROL_REMINDER;
            } else if (i == 7508) {
                c(bArr, i2);
                return;
            } else if (i == 5025) {
                d = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.f12307a, "IDO_VOICE[handle set voice text reply info JsonString] " + d);
                voiceControlType = VoiceCallBack.VoiceControlType.VOICE_TO_TEXT;
            } else if (i == 7506) {
                d = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.f12307a, "IDO_VOICE[handle app start voice JsonString] " + d);
                voiceControlType = VoiceCallBack.VoiceControlType.APP_START_VOICE_RECOGNIZE;
            } else if (i == 7509) {
                d = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.f12307a, "IDO_VOICE[handle app stop voice JsonString] " + d);
                voiceControlType = VoiceCallBack.VoiceControlType.APP_STOP_VOICE_RECOGNIZE;
            } else if (i != 7632) {
                if (i == 7623) {
                    a(bArr, i2);
                    return;
                }
                return;
            } else {
                d = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.f12307a, "IDO_VOICE[handle set notify state JsonString] " + d);
                voiceControlType = VoiceCallBack.VoiceControlType.VOICE_NOTIFY_STATE;
            }
            a(d, voiceControlType);
        }
    }

    private static void a(String str, VoiceCallBack.VoiceControlType voiceControlType) {
        int i;
        try {
            i = new JSONObject(str).optInt("is_success");
        } catch (JSONException e) {
            LogTool.b(com.ido.ble.logs.a.f12307a, com.ido.ble.logs.a.k + e.getMessage());
            i = 0;
        }
        VoiceCallBack.onControlResult(voiceControlType, i == 1);
    }

    private static void a(byte[] bArr, int i) {
        int i2 = -1;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS == com.veryfit.multi.nativeprotocol.a.a(i)) {
            String d = com.ido.ble.common.c.d(bArr);
            if (TextUtils.isEmpty(d)) {
                LogTool.b(com.ido.ble.logs.a.f12307a, "IDO_VOICE[handleDefaultLang] json is null");
                VoiceCallBack.onGetDefaultLang(-1);
                return;
            }
            LogTool.d(com.ido.ble.logs.a.f12307a, "IDO_VOICE[handleDefaultLang] json is" + d);
            try {
                i2 = new JSONObject(d).optInt("alexa_default_language");
            } catch (JSONException e) {
                LogTool.b(com.ido.ble.logs.a.f12307a, com.ido.ble.logs.a.k + e.getMessage());
            }
        }
        VoiceCallBack.onGetDefaultLang(i2);
    }

    public static boolean a(int i) {
        if (i == 7503 || i == 7504 || i == 7508 || i == 7509 || i == 5025 || i == 5028 || i == 7500 || i == 7506 || i == 7623) {
            return true;
        }
        switch (i) {
            case com.veryfit.multi.nativeprotocol.b.O4 /* 7601 */:
            case com.veryfit.multi.nativeprotocol.b.P4 /* 7602 */:
            case com.veryfit.multi.nativeprotocol.b.Q4 /* 7603 */:
            case com.veryfit.multi.nativeprotocol.b.R4 /* 7604 */:
            case com.veryfit.multi.nativeprotocol.b.S4 /* 7605 */:
            case com.veryfit.multi.nativeprotocol.b.T4 /* 7606 */:
            case com.veryfit.multi.nativeprotocol.b.U4 /* 7607 */:
            case com.veryfit.multi.nativeprotocol.b.V4 /* 7608 */:
            case com.veryfit.multi.nativeprotocol.b.W4 /* 7609 */:
            case com.veryfit.multi.nativeprotocol.b.X4 /* 7610 */:
            case com.veryfit.multi.nativeprotocol.b.Y4 /* 7611 */:
            case com.veryfit.multi.nativeprotocol.b.Z4 /* 7612 */:
            case com.veryfit.multi.nativeprotocol.b.a5 /* 7613 */:
            case com.veryfit.multi.nativeprotocol.b.b5 /* 7614 */:
            case com.veryfit.multi.nativeprotocol.b.c5 /* 7615 */:
            case com.veryfit.multi.nativeprotocol.b.d5 /* 7616 */:
            case com.veryfit.multi.nativeprotocol.b.e5 /* 7617 */:
            case com.veryfit.multi.nativeprotocol.b.f5 /* 7618 */:
            case com.veryfit.multi.nativeprotocol.b.g5 /* 7619 */:
                return true;
            default:
                switch (i) {
                    case com.veryfit.multi.nativeprotocol.b.i5 /* 7630 */:
                    case com.veryfit.multi.nativeprotocol.b.j5 /* 7631 */:
                    case com.veryfit.multi.nativeprotocol.b.k5 /* 7632 */:
                        return true;
                    default:
                        return false;
                }
        }
    }

    private static void b(byte[] bArr, int i) {
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            VoiceCallBack.onGetVoiceOpusSectionData(null);
            return;
        }
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "IDO_VOICE[handleOpusSectionVoiceData] json is null");
            VoiceCallBack.onGetVoiceOpusSectionData(null);
            return;
        }
        VoiceOpusSectionData voiceOpusSectionData = (VoiceOpusSectionData) new Gson().fromJson(d, (Class<Object>) VoiceOpusSectionData.class);
        if (voiceOpusSectionData != null && voiceOpusSectionData.opus_voice_data != null) {
            LogTool.d(com.ido.ble.logs.a.f12307a, "IDO_VOICE[handleOpusSectionVoiceData] json is, size= " + voiceOpusSectionData.encode_data_len + ", byte length =" + voiceOpusSectionData.opus_voice_data.length);
        }
        VoiceCallBack.onGetVoiceOpusSectionData(voiceOpusSectionData);
    }

    private static void c(byte[] bArr, int i) {
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            VoiceCallBack.onGetVoicePcmSectionData(null);
            return;
        }
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "IDO_VOICE[handlePcmSectionVoiceData] json is null");
            VoiceCallBack.onGetVoicePcmSectionData(null);
            return;
        }
        VoicePcmSectionData voicePcmSectionData = (VoicePcmSectionData) new Gson().fromJson(d, (Class<Object>) VoicePcmSectionData.class);
        if (voicePcmSectionData != null && voicePcmSectionData.pcm_voice_data != null) {
            LogTool.d(com.ido.ble.logs.a.f12307a, "IDO_VOICE[handlePcmSectionVoiceData] json is, size= " + voicePcmSectionData.pcm_data_len + ", byte length =" + voicePcmSectionData.pcm_voice_data.length);
        }
        VoiceCallBack.onGetVoicePcmSectionData(voicePcmSectionData);
    }

    private static void d(byte[] bArr, int i) {
        byte[] bArr2;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            VoiceCallBack.onGetVoiceData(null);
            return;
        }
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "IDO_VOICE[handleVoiceData] json is null");
            VoiceCallBack.onGetVoiceData(null);
            return;
        }
        VoiceData voiceData = (VoiceData) new Gson().fromJson(d, (Class<Object>) VoiceData.class);
        if (voiceData != null && (bArr2 = voiceData.voiceFile) != null && bArr2.length > 0) {
            LogTool.d(com.ido.ble.logs.a.f12307a, "IDO_VOICE[handleVoiceData] json is, size= " + voiceData.size + ", byte length =" + voiceData.voiceFile.length);
        }
        VoiceCallBack.onGetVoiceData(voiceData);
    }

    private static void e(byte[] bArr, int i) {
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            VoiceCallBack.onVoiceTranState(null);
            return;
        }
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "IDO_VOICE[handleVoiceTranState] json is null");
            VoiceCallBack.onVoiceTranState(null);
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f12307a, "IDO_VOICE[handleVoiceTranState] json is" + d);
        VoiceCallBack.onVoiceTranState((VoiceTranState) new Gson().fromJson(d, (Class<Object>) VoiceTranState.class));
    }
}
