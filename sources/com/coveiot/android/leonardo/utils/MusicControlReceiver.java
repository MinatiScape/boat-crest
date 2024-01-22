package com.coveiot.android.leonardo.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import com.coveiot.android.bleabstract.Constants;
import com.coveiot.android.bleabstract.response.LiveMusicControlRes;
import com.coveiot.android.bleabstract.response.MusicControlState;
import com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager;
import com.coveiot.android.leonardo.model.MusicControlEvent;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.LogHelper;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class MusicControlReceiver extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public AudioManager f5428a;
    public final int b = 2147483627;
    @NotNull
    public final String c = "MusicControlReceiver";

    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MusicControlState.values().length];
            try {
                iArr[MusicControlState.TOGGLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[MusicControlState.NEXT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[MusicControlState.PREVIOUS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[MusicControlState.VOLUME_UP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[MusicControlState.VOLUME_DOWN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[MusicControlState.PLAY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[MusicControlState.PAUSE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @NotNull
    public final String getTAG() {
        return this.c;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NotNull Context context, @NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        String string = Settings.Secure.getString(context.getContentResolver(), AppConstants.ENABLE_NOTIFICATION_LISTENERS.getValue());
        if (string != null) {
            String packageName = context.getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "context.packageName");
            AudioManager audioManager = null;
            if (StringsKt__StringsKt.contains$default((CharSequence) string, (CharSequence) packageName, false, 2, (Object) null)) {
                Object systemService = context.getSystemService("audio");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
                this.f5428a = (AudioManager) systemService;
                if (intent.hasExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA)) {
                    Bundle extras = intent.getExtras();
                    Intrinsics.checkNotNull(extras);
                    Serializable serializable = extras.getSerializable(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA);
                    Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.LiveMusicControlRes");
                    MusicControlState state = ((LiveMusicControlRes) serializable).getState();
                    switch (state == null ? -1 : WhenMappings.$EnumSwitchMapping$0[state.ordinal()]) {
                        case 1:
                            AudioManager audioManager2 = this.f5428a;
                            if (audioManager2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mAudioManager");
                                audioManager2 = null;
                            }
                            if (audioManager2.isMusicActive()) {
                                LogHelper.d(this.c, "Pause");
                                AudioManager audioManager3 = this.f5428a;
                                if (audioManager3 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("mAudioManager");
                                } else {
                                    audioManager = audioManager3;
                                }
                                audioManager.dispatchMediaKeyEvent(new KeyEvent(0, 127));
                                return;
                            }
                            LogHelper.d(this.c, "Play");
                            AudioManager audioManager4 = this.f5428a;
                            if (audioManager4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mAudioManager");
                            } else {
                                audioManager = audioManager4;
                            }
                            audioManager.dispatchMediaKeyEvent(new KeyEvent(0, 126));
                            return;
                        case 2:
                            LogHelper.d(this.c, "Next");
                            CoveEventBusManager.getInstance().getEventBus().post(new MusicControlEvent(MusicControlEvent.Control.NEXT));
                            return;
                        case 3:
                            LogHelper.d(this.c, "Previous");
                            CoveEventBusManager.getInstance().getEventBus().post(new MusicControlEvent(MusicControlEvent.Control.PREVIOUS));
                            return;
                        case 4:
                            LogHelper.d(this.c, "Volume Up");
                            AudioManager audioManager5 = this.f5428a;
                            if (audioManager5 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mAudioManager");
                            } else {
                                audioManager = audioManager5;
                            }
                            audioManager.adjustVolume(1, 1);
                            return;
                        case 5:
                            LogHelper.d(this.c, "Volume Down");
                            AudioManager audioManager6 = this.f5428a;
                            if (audioManager6 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mAudioManager");
                            } else {
                                audioManager = audioManager6;
                            }
                            audioManager.adjustVolume(-1, 1);
                            return;
                        case 6:
                            AudioManager audioManager7 = this.f5428a;
                            if (audioManager7 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mAudioManager");
                            } else {
                                audioManager = audioManager7;
                            }
                            audioManager.dispatchMediaKeyEvent(new KeyEvent(0, 126));
                            return;
                        case 7:
                            AudioManager audioManager8 = this.f5428a;
                            if (audioManager8 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mAudioManager");
                            } else {
                                audioManager = audioManager8;
                            }
                            audioManager.dispatchMediaKeyEvent(new KeyEvent(0, 127));
                            return;
                        default:
                            LogHelper.d(this.c, "UNKNOWN");
                            return;
                    }
                }
                return;
            }
        }
        NotificationManager.getInstance().showNotificationAccessSettingScreenAlert(context, this.b);
    }
}
