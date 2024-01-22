package com.ido.ble.callback;

import com.ido.ble.protocol.model.VoiceData;
import com.ido.ble.protocol.model.VoiceOpusSectionData;
import com.ido.ble.protocol.model.VoicePcmSectionData;
import com.ido.ble.protocol.model.VoiceTranState;
/* loaded from: classes11.dex */
public class VoiceCallBack {

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onControlResult(VoiceControlType voiceControlType, boolean z);

        void onGetDefaultLang(int i);

        void onGetOpusSectionData(VoiceOpusSectionData voiceOpusSectionData);

        void onGetPcmSectionData(VoicePcmSectionData voicePcmSectionData);

        void onGetVoiceData(VoiceData voiceData);

        void onVoiceTranState(VoiceTranState voiceTranState);
    }

    /* loaded from: classes11.dex */
    public enum VoiceControlType {
        VOICE_TO_TEXT,
        VOICE_CONTROL_ALARM,
        VOICE_CONTROL_STOPWATCH,
        VOICE_CONTROL_REMINDER,
        APP_START_VOICE_RECOGNIZE,
        APP_STOP_VOICE_RECOGNIZE,
        VOICE_NOTIFY_STATE
    }

    public static void onControlResult(final VoiceControlType voiceControlType, final boolean z) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.VoiceCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().M()) {
                    iCallBack.onControlResult(VoiceControlType.this, z);
                }
            }
        });
    }

    public static void onGetDefaultLang(final int i) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.VoiceCallBack.6
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().M()) {
                    iCallBack.onGetDefaultLang(i);
                }
            }
        });
    }

    public static void onGetVoiceData(final VoiceData voiceData) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.VoiceCallBack.2
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().M()) {
                    iCallBack.onGetVoiceData(VoiceData.this);
                }
            }
        });
    }

    public static void onGetVoiceOpusSectionData(final VoiceOpusSectionData voiceOpusSectionData) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.VoiceCallBack.3
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().M()) {
                    iCallBack.onGetOpusSectionData(VoiceOpusSectionData.this);
                }
            }
        });
    }

    public static void onGetVoicePcmSectionData(final VoicePcmSectionData voicePcmSectionData) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.VoiceCallBack.4
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().M()) {
                    iCallBack.onGetPcmSectionData(VoicePcmSectionData.this);
                }
            }
        });
    }

    public static void onVoiceTranState(final VoiceTranState voiceTranState) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.VoiceCallBack.5
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().M()) {
                    iCallBack.onVoiceTranState(VoiceTranState.this);
                }
            }
        });
    }
}
