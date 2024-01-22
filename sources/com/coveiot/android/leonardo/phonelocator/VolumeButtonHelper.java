package com.coveiot.android.leonardo.phonelocator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import androidx.annotation.RequiresApi;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@RequiresApi(28)
/* loaded from: classes5.dex */
public final class VolumeButtonHelper {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String EXTRA_VOLUME_STREAM_TYPE = "android.media.EXTRA_VOLUME_STREAM_TYPE";
    @NotNull
    public static final String VOLUME_CHANGE_ACTION = "android.media.VOLUME_CHANGED_ACTION";
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Context f5339a;
    @Nullable
    public Integer b;
    @Nullable
    public MediaPlayer c;
    @Nullable
    public VolumeBroadCastReceiver d;
    @Nullable
    public VolumeChangeListener e;
    @Nullable
    public final AudioManager f;
    public int g;
    public double h;
    public boolean i;
    public long j;
    public long k;
    public int l;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes5.dex */
    public enum Direction {
        Up,
        Down,
        Release
    }

    /* loaded from: classes5.dex */
    public final class VolumeBroadCastReceiver extends BroadcastReceiver {

        @DebugMetadata(c = "com.coveiot.android.leonardo.phonelocator.VolumeButtonHelper$VolumeBroadCastReceiver$buttonDown$1", f = "VolumeButtonHelper.kt", i = {}, l = {156}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes5.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ double $startVolumePushes;
            public int label;
            public final /* synthetic */ VolumeButtonHelper this$0;
            public final /* synthetic */ VolumeBroadCastReceiver this$1;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(VolumeButtonHelper volumeButtonHelper, double d, VolumeBroadCastReceiver volumeBroadCastReceiver, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = volumeButtonHelper;
                this.$startVolumePushes = d;
                this.this$1 = volumeBroadCastReceiver;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.this$0, this.$startVolumePushes, this.this$1, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    long buttonReleaseTimeout = this.this$0.getButtonReleaseTimeout();
                    this.label = 1;
                    if (DelayKt.delay(buttonReleaseTimeout, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
                if (!(this.$startVolumePushes == this.this$0.h)) {
                    if (this.this$0.h > 2.0d && !this.this$0.i) {
                        this.this$0.i = true;
                        VolumeChangeListener volumeChangeListener = this.this$0.e;
                        if (volumeChangeListener != null) {
                            volumeChangeListener.onLongPress();
                        }
                    }
                    this.this$1.a();
                } else {
                    VolumeButtonHelper volumeButtonHelper = this.this$0;
                    volumeButtonHelper.onVolumePress((int) volumeButtonHelper.h);
                    VolumeChangeListener volumeChangeListener2 = this.this$0.e;
                    if (volumeChangeListener2 != null) {
                        volumeChangeListener2.onVolumeChange(Direction.Release);
                    }
                    this.this$0.h = 0.0d;
                    this.this$0.i = false;
                }
                return Unit.INSTANCE;
            }
        }

        @DebugMetadata(c = "com.coveiot.android.leonardo.phonelocator.VolumeButtonHelper$VolumeBroadCastReceiver$onReceive$1", f = "VolumeButtonHelper.kt", i = {}, l = {142}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes5.dex */
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ VolumeButtonHelper this$0;
            public final /* synthetic */ VolumeBroadCastReceiver this$1;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(VolumeButtonHelper volumeButtonHelper, VolumeBroadCastReceiver volumeBroadCastReceiver, Continuation<? super b> continuation) {
                super(2, continuation);
                this.this$0 = volumeButtonHelper;
                this.this$1 = volumeBroadCastReceiver;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new b(this.this$0, this.this$1, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    if (DelayKt.delay(this.this$0.getDoublePressTimeout() - this.this$0.getButtonReleaseTimeout(), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
                this.this$1.a();
                return Unit.INSTANCE;
            }
        }

        public VolumeBroadCastReceiver() {
        }

        public final void a() {
            e.e(CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()), null, null, new a(VolumeButtonHelper.this, VolumeButtonHelper.this.h, this, null), 3, null);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(@NotNull Context context, @NotNull Intent intent) {
            VolumeChangeListener volumeChangeListener;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            if (VolumeButtonHelper.this.b != null) {
                int intExtra = intent.getIntExtra(VolumeButtonHelper.EXTRA_VOLUME_STREAM_TYPE, -1);
                Integer num = VolumeButtonHelper.this.b;
                if (num == null || intExtra != num.intValue()) {
                    return;
                }
            }
            VolumeButtonHelper volumeButtonHelper = VolumeButtonHelper.this;
            AudioManager audioManager = volumeButtonHelper.f;
            volumeButtonHelper.l = audioManager != null ? audioManager.getStreamVolume(2) : -1;
            AudioManager audioManager2 = VolumeButtonHelper.this.f;
            Integer valueOf = audioManager2 != null ? Integer.valueOf(audioManager2.getStreamMaxVolume(5)) : null;
            if (VolumeButtonHelper.this.getCurrentVolume() != -1) {
                if (VolumeButtonHelper.this.getCurrentVolume() != VolumeButtonHelper.this.g) {
                    int currentVolume = VolumeButtonHelper.this.getCurrentVolume();
                    if (valueOf != null && currentVolume == valueOf.intValue()) {
                        VolumeChangeListener volumeChangeListener2 = VolumeButtonHelper.this.e;
                        if (volumeChangeListener2 != null) {
                            volumeChangeListener2.onVolumeChange(Direction.Up);
                        }
                    } else if (VolumeButtonHelper.this.getCurrentVolume() > VolumeButtonHelper.this.g) {
                        VolumeChangeListener volumeChangeListener3 = VolumeButtonHelper.this.e;
                        if (volumeChangeListener3 != null) {
                            volumeChangeListener3.onVolumeChange(Direction.Up);
                        }
                    } else if (VolumeButtonHelper.this.getCurrentVolume() < VolumeButtonHelper.this.g && (volumeChangeListener = VolumeButtonHelper.this.e) != null) {
                        volumeChangeListener.onVolumeChange(Direction.Down);
                    }
                    VolumeButtonHelper volumeButtonHelper2 = VolumeButtonHelper.this;
                    volumeButtonHelper2.g = volumeButtonHelper2.getCurrentVolume();
                }
                VolumeButtonHelper.this.h += 0.5d;
                if (VolumeButtonHelper.this.h == 0.5d) {
                    e.e(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new b(VolumeButtonHelper.this, this, null), 3, null);
                }
            }
        }
    }

    /* loaded from: classes5.dex */
    public interface VolumeChangeListener {
        void onDoublePress();

        void onLongPress();

        void onSinglePress();

        void onVolumeChange(@NotNull Direction direction);

        void onVolumePress(int i);
    }

    public VolumeButtonHelper(@NotNull Context context, @Nullable Integer num) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5339a = context;
        this.b = num;
        Object systemService = context.getSystemService("audio");
        this.f = systemService instanceof AudioManager ? (AudioManager) systemService : null;
        this.g = -1;
        this.j = 350L;
        this.k = 100L;
        this.l = -1;
    }

    public final void finalizeMediaPlayer() {
        MediaPlayer mediaPlayer = this.c;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        MediaPlayer mediaPlayer2 = this.c;
        if (mediaPlayer2 != null) {
            mediaPlayer2.release();
        }
        this.c = null;
    }

    public final long getButtonReleaseTimeout() {
        return this.k;
    }

    public final int getCurrentVolume() {
        return this.l;
    }

    public final long getDoublePressTimeout() {
        return this.j;
    }

    public final void onVolumePress(int i) {
        if (i == 1) {
            VolumeChangeListener volumeChangeListener = this.e;
            if (volumeChangeListener != null) {
                volumeChangeListener.onSinglePress();
            }
        } else if (i != 2) {
            VolumeChangeListener volumeChangeListener2 = this.e;
            if (volumeChangeListener2 != null) {
                volumeChangeListener2.onVolumePress(i);
            }
        } else {
            VolumeChangeListener volumeChangeListener3 = this.e;
            if (volumeChangeListener3 != null) {
                volumeChangeListener3.onDoublePress();
            }
        }
    }

    public final void registerVolumeChangeListener(@NotNull VolumeChangeListener volumeChangeListener) {
        Intrinsics.checkNotNullParameter(volumeChangeListener, "volumeChangeListener");
        if (this.d == null) {
            this.e = volumeChangeListener;
            this.d = new VolumeBroadCastReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.media.VOLUME_CHANGED_ACTION");
            this.f5339a.registerReceiver(this.d, intentFilter);
        }
    }

    public final void setButtonReleaseTimeout(long j) {
        this.k = j;
    }

    public final void setDoublePressTimeout(long j) {
        this.j = j;
    }

    public final void unregisterReceiver() {
        VolumeBroadCastReceiver volumeBroadCastReceiver = this.d;
        if (volumeBroadCastReceiver != null) {
            this.f5339a.unregisterReceiver(volumeBroadCastReceiver);
            this.d = null;
        }
    }

    public /* synthetic */ VolumeButtonHelper(Context context, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : num);
    }
}
