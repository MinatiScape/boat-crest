package com.szabh.smable3.music;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import androidx.annotation.CallSuper;
import com.coveiot.android.leonardo.phonelocator.VolumeButtonHelper;
import com.szabh.smable3.music.MusicControllerCompat$mReceiver$2;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public class MusicControllerCompat {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final String EXTRA_VOLUME_STREAM_TYPE = "android.media.EXTRA_VOLUME_STREAM_TYPE";
    @NotNull
    private static final String VOLUME_CHANGED_ACTION = "android.media.VOLUME_CHANGED_ACTION";
    @Nullable
    private AudioManager mAudioManager;
    @Nullable
    private Context mContext;
    @NotNull
    private final Lazy mReceiver$delegate = LazyKt__LazyJVMKt.lazy(new Function0<MusicControllerCompat$mReceiver$2.AnonymousClass1>() { // from class: com.szabh.smable3.music.MusicControllerCompat$mReceiver$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Type inference failed for: r0v0, types: [com.szabh.smable3.music.MusicControllerCompat$mReceiver$2$1] */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final AnonymousClass1 invoke() {
            final MusicControllerCompat musicControllerCompat = MusicControllerCompat.this;
            return new BroadcastReceiver() { // from class: com.szabh.smable3.music.MusicControllerCompat$mReceiver$2.1
                @Override // android.content.BroadcastReceiver
                public void onReceive(@NotNull Context context, @NotNull Intent intent) {
                    Intrinsics.checkNotNullParameter(context, "context");
                    Intrinsics.checkNotNullParameter(intent, "intent");
                    String action = intent.getAction();
                    if (action != null && action.hashCode() == -1940635523 && action.equals("android.media.VOLUME_CHANGED_ACTION") && intent.getIntExtra(VolumeButtonHelper.EXTRA_VOLUME_STREAM_TYPE, -1) == 3) {
                        MusicControllerCompat.this.updateVolume();
                    }
                }
            };
        }
    });

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final MusicControllerCompat newInstance() {
            return new MusicController();
        }
    }

    private final BroadcastReceiver getMReceiver() {
        return (BroadcastReceiver) this.mReceiver$delegate.getValue();
    }

    @CallSuper
    public void exit() {
        Context context = this.mContext;
        if (context != null) {
            context.unregisterReceiver(getMReceiver());
        }
        this.mContext = null;
    }

    @Nullable
    public final AudioManager getMAudioManager() {
        return this.mAudioManager;
    }

    @Nullable
    public final Context getMContext() {
        return this.mContext;
    }

    public final int getStreamMaxVolume(int i) {
        AudioManager audioManager = this.mAudioManager;
        if (audioManager != null) {
            return audioManager.getStreamMaxVolume(i);
        }
        return 15;
    }

    public final int getStreamVolume(int i) {
        AudioManager audioManager = this.mAudioManager;
        if (audioManager != null) {
            return audioManager.getStreamVolume(i);
        }
        return -1;
    }

    @CallSuper
    public void launch(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.mContext = context;
        context.registerReceiver(getMReceiver(), new IntentFilter("android.media.VOLUME_CHANGED_ACTION"));
        Object systemService = context.getSystemService("audio");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
        this.mAudioManager = (AudioManager) systemService;
    }

    public final void setMAudioManager(@Nullable AudioManager audioManager) {
        this.mAudioManager = audioManager;
    }

    public final void setMContext(@Nullable Context context) {
        this.mContext = context;
    }

    public void updateVolume() {
    }
}
