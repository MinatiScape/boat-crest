package com.pierfrancescosoffritti.androidyoutubeplayer.core.player;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public final class PlayerConstantsKt {

    /* loaded from: classes9.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PlayerConstants.PlaybackRate.values().length];
            try {
                iArr[PlayerConstants.PlaybackRate.UNKNOWN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PlayerConstants.PlaybackRate.RATE_0_25.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[PlayerConstants.PlaybackRate.RATE_0_5.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[PlayerConstants.PlaybackRate.RATE_1.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[PlayerConstants.PlaybackRate.RATE_1_5.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[PlayerConstants.PlaybackRate.RATE_2.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final float toFloat(@NotNull PlayerConstants.PlaybackRate playbackRate) {
        Intrinsics.checkNotNullParameter(playbackRate, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$0[playbackRate.ordinal()]) {
            case 1:
            case 4:
                return 1.0f;
            case 2:
                return 0.25f;
            case 3:
                return 0.5f;
            case 5:
                return 1.5f;
            case 6:
                return 2.0f;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
