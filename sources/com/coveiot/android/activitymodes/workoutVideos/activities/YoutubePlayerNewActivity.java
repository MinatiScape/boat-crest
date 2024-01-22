package com.coveiot.android.activitymodes.workoutVideos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.databinding.ActivityYoutubePlayerNewBinding;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.coveaccess.workoutvideos.model.VideoType;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.LogHelper;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerUtils;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class YoutubePlayerNewActivity extends BaseActivity {
    @Nullable
    public AbstractYouTubePlayerListener A;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String p = "YoutubePlayerNewActivity";
    public ActivityYoutubePlayerNewBinding q;
    @Nullable
    public String r;
    @Nullable
    public String s;
    @Nullable
    public String t;
    @Nullable
    public String u;
    public boolean v;
    @Nullable
    public String w;
    public boolean x;
    public float y;
    @Nullable
    public YouTubePlayer z;

    public static final void s(YoutubePlayerNewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.r();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    @NotNull
    public final String getTAG() {
        return this.p;
    }

    public final void initToolbar() {
        String str = this.w;
        boolean z = false;
        if (str != null && m.equals(str, VideoType.SENSE_AI_COACH.getVideoType(), true)) {
            z = true;
        }
        if (z) {
            ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.sens_ai_coach));
        } else {
            ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.workout_videos));
        }
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.workoutVideos.activities.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                YoutubePlayerNewActivity.s(YoutubePlayerNewActivity.this, view);
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        r();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityYoutubePlayerNewBinding inflate = ActivityYoutubePlayerNewBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.q = inflate;
        final ActivityYoutubePlayerNewBinding activityYoutubePlayerNewBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        WorkoutUtils workoutUtils = WorkoutUtils.INSTANCE;
        workoutUtils.getMetadata(this);
        if (workoutUtils.getAPI_KEY() == null) {
            return;
        }
        Intent intent = getIntent();
        String stringExtra = intent != null ? intent.getStringExtra(WorkoutConstants.YOUTUBE_VIDEO_ID) : null;
        Intrinsics.checkNotNull(stringExtra);
        this.r = stringExtra;
        Intent intent2 = getIntent();
        String stringExtra2 = intent2 != null ? intent2.getStringExtra(WorkoutConstants.VIDEO_ID) : null;
        Intrinsics.checkNotNull(stringExtra2);
        this.s = stringExtra2;
        Intent intent3 = getIntent();
        String stringExtra3 = intent3 != null ? intent3.getStringExtra(WorkoutConstants.VIDEO_NAME) : null;
        Intrinsics.checkNotNull(stringExtra3);
        this.t = stringExtra3;
        this.u = SessionManager.getInstance(this).getVideoCategoryPassed();
        Intent intent4 = getIntent();
        if ((intent4 != null ? intent4.getStringExtra(WorkoutConstants.WORKOUT_TYPE) : null) != null) {
            Intent intent5 = getIntent();
            this.w = intent5 != null ? intent5.getStringExtra(WorkoutConstants.WORKOUT_TYPE) : null;
        }
        initToolbar();
        ActivityYoutubePlayerNewBinding activityYoutubePlayerNewBinding2 = this.q;
        if (activityYoutubePlayerNewBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityYoutubePlayerNewBinding = activityYoutubePlayerNewBinding2;
        }
        getLifecycle().addObserver(activityYoutubePlayerNewBinding.youtubePlayerView);
        final FrameLayout youtube_frame_layout = (FrameLayout) _$_findCachedViewById(R.id.youtube_frame_layout);
        Intrinsics.checkNotNullExpressionValue(youtube_frame_layout, "youtube_frame_layout");
        this.A = new AbstractYouTubePlayerListener() { // from class: com.coveiot.android.activitymodes.workoutVideos.activities.YoutubePlayerNewActivity$onCreate$1$1

            /* loaded from: classes2.dex */
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[PlayerConstants.PlayerState.values().length];
                    try {
                        iArr[PlayerConstants.PlayerState.UNKNOWN.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[PlayerConstants.PlayerState.UNSTARTED.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    try {
                        iArr[PlayerConstants.PlayerState.ENDED.ordinal()] = 3;
                    } catch (NoSuchFieldError unused3) {
                    }
                    try {
                        iArr[PlayerConstants.PlayerState.PLAYING.ordinal()] = 4;
                    } catch (NoSuchFieldError unused4) {
                    }
                    try {
                        iArr[PlayerConstants.PlayerState.PAUSED.ordinal()] = 5;
                    } catch (NoSuchFieldError unused5) {
                    }
                    try {
                        iArr[PlayerConstants.PlayerState.BUFFERING.ordinal()] = 6;
                    } catch (NoSuchFieldError unused6) {
                    }
                    try {
                        iArr[PlayerConstants.PlayerState.VIDEO_CUED.ordinal()] = 7;
                    } catch (NoSuchFieldError unused7) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener, com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
            public void onCurrentSecond(@NotNull YouTubePlayer youTubePlayer, float f) {
                Intrinsics.checkNotNullParameter(youTubePlayer, "youTubePlayer");
                super.onCurrentSecond(youTubePlayer, f);
                YoutubePlayerNewActivity.this.y = f;
            }

            @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener, com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
            public void onError(@NotNull YouTubePlayer youTubePlayer, @NotNull PlayerConstants.PlayerError error) {
                Intrinsics.checkNotNullParameter(youTubePlayer, "youTubePlayer");
                Intrinsics.checkNotNullParameter(error, "error");
                super.onError(youTubePlayer, error);
                LogHelper.i(YoutubePlayerNewActivity.this.getTAG(), error.name());
            }

            @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener, com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
            public void onReady(@NonNull @NotNull YouTubePlayer youTubePlayer) {
                String str;
                Intrinsics.checkNotNullParameter(youTubePlayer, "youTubePlayer");
                YoutubePlayerNewActivity.this.z = youTubePlayer;
                final ActivityYoutubePlayerNewBinding activityYoutubePlayerNewBinding3 = activityYoutubePlayerNewBinding;
                YouTubePlayerView youTubePlayerView = activityYoutubePlayerNewBinding3.youtubePlayerView;
                final YoutubePlayerNewActivity youtubePlayerNewActivity = YoutubePlayerNewActivity.this;
                final FrameLayout frameLayout = youtube_frame_layout;
                youTubePlayerView.addFullscreenListener(new FullscreenListener() { // from class: com.coveiot.android.activitymodes.workoutVideos.activities.YoutubePlayerNewActivity$onCreate$1$1$onReady$1
                    @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
                    public void onEnterFullscreen(@NotNull View fullscreenView, @NotNull Function0<Unit> exitFullscreen) {
                        Intrinsics.checkNotNullParameter(fullscreenView, "fullscreenView");
                        Intrinsics.checkNotNullParameter(exitFullscreen, "exitFullscreen");
                        YoutubePlayerNewActivity.this.setRequestedOrientation(0);
                        activityYoutubePlayerNewBinding3.youtubePlayerView.setVisibility(8);
                        activityYoutubePlayerNewBinding3.toolBar.setVisibility(8);
                        frameLayout.setVisibility(0);
                        frameLayout.addView(fullscreenView);
                    }

                    @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
                    public void onExitFullscreen() {
                        YoutubePlayerNewActivity.this.setRequestedOrientation(1);
                        activityYoutubePlayerNewBinding3.youtubePlayerView.setVisibility(0);
                        activityYoutubePlayerNewBinding3.toolBar.setVisibility(0);
                        frameLayout.setVisibility(8);
                    }
                });
                Lifecycle lifecycle = YoutubePlayerNewActivity.this.getLifecycle();
                Intrinsics.checkNotNullExpressionValue(lifecycle, "lifecycle");
                str = YoutubePlayerNewActivity.this.r;
                Intrinsics.checkNotNull(str);
                YouTubePlayerUtils.loadOrCueVideo(youTubePlayer, lifecycle, str, 0.0f);
            }

            @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener, com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
            public void onStateChange(@NotNull YouTubePlayer youTubePlayer, @NotNull PlayerConstants.PlayerState state) {
                Intrinsics.checkNotNullParameter(youTubePlayer, "youTubePlayer");
                Intrinsics.checkNotNullParameter(state, "state");
                super.onStateChange(youTubePlayer, state);
                switch (WhenMappings.$EnumSwitchMapping$0[state.ordinal()]) {
                    case 1:
                        Log.d("onNewState: ", "UNKNOWN");
                        return;
                    case 2:
                        Log.d("onNewState: ", "UNSTARTED");
                        return;
                    case 3:
                        YoutubePlayerNewActivity.this.v = true;
                        LogHelper.i(YoutubePlayerNewActivity.this.getTAG(), "onVideoEnded");
                        return;
                    case 4:
                        Log.d("onNewState: ", "PLAYING");
                        return;
                    case 5:
                        Log.d("onNewState: ", "PAUSED");
                        return;
                    case 6:
                        Log.d("onNewState: ", "BUFFERING");
                        return;
                    case 7:
                        Log.d("onNewState: ", "VIDEO_CUED");
                        return;
                    default:
                        Log.d("onNewState: ", "status unknown");
                        return;
                }
            }
        };
        IFramePlayerOptions build = new IFramePlayerOptions.Builder().controls(1).fullscreen(1).build();
        YouTubePlayerView youTubePlayerView = activityYoutubePlayerNewBinding.youtubePlayerView;
        AbstractYouTubePlayerListener abstractYouTubePlayerListener = this.A;
        Intrinsics.checkNotNull(abstractYouTubePlayerListener, "null cannot be cast to non-null type com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener");
        youTubePlayerView.initialize(abstractYouTubePlayerListener, build);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        YouTubePlayer youTubePlayer = this.z;
        if (youTubePlayer != null) {
            AbstractYouTubePlayerListener abstractYouTubePlayerListener = this.A;
            Intrinsics.checkNotNull(abstractYouTubePlayerListener);
            youTubePlayer.removeListener(abstractYouTubePlayerListener);
        }
        ActivityYoutubePlayerNewBinding activityYoutubePlayerNewBinding = this.q;
        if (activityYoutubePlayerNewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityYoutubePlayerNewBinding = null;
        }
        activityYoutubePlayerNewBinding.youtubePlayerView.release();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.x) {
            YouTubePlayer youTubePlayer = this.z;
            if (youTubePlayer != null) {
                youTubePlayer.play();
            }
            this.x = false;
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        YouTubePlayer youTubePlayer = this.z;
        if (youTubePlayer != null) {
            youTubePlayer.pause();
        }
        this.x = true;
    }

    public final void r() {
        if (this.v) {
            if (!m.equals(this.w, VideoType.SENSE_AI_COACH.getVideoType(), true)) {
                Intent intent = new Intent();
                intent.putExtra(WorkoutConstants.YOUTUBE_VIDEO_ID, this.r);
                intent.putExtra(WorkoutConstants.VIDEO_ID, this.s);
                intent.putExtra(WorkoutConstants.VIDEO_NAME, this.t);
                intent.putExtra(WorkoutConstants.VIDEO_CATEGORY, this.u);
                intent.putExtra(WorkoutConstants.IS_FROM_VIDEO_PLAYER_SCREEN, true);
                setResult(121, intent);
                SessionManager.getInstance(this).saveLastVideoID(null);
                SessionManager.getInstance(this).saveLastVideoName(null);
                SessionManager.getInstance(this).saveLastVideoCategory(null);
                SessionManager.getInstance(this).saveLastVideoPosition(Float.valueOf(-1.0f));
            } else {
                Intent intent2 = new Intent();
                intent2.putExtra(WorkoutConstants.YOUTUBE_VIDEO_ID, this.r);
                intent2.putExtra(WorkoutConstants.VIDEO_ID, this.s);
                intent2.putExtra(WorkoutConstants.VIDEO_NAME, this.t);
                intent2.putExtra(WorkoutConstants.VIDEO_CATEGORY, this.u);
                intent2.putExtra(WorkoutConstants.IS_FROM_VIDEO_PLAYER_SCREEN, true);
                setResult(121, intent2);
                SessionManager.getInstance(this).saveLastSensAIVideoID(null);
                SessionManager.getInstance(this).saveLastSensAIVideoName(null);
                SessionManager.getInstance(this).saveLastSensAIVideoCategory(null);
                SessionManager.getInstance(this).saveLastSensAIVideoPosition(Float.valueOf(-1.0f));
            }
            finish();
            return;
        }
        if (!m.equals(this.w, VideoType.SENSE_AI_COACH.getVideoType(), true)) {
            SessionManager.getInstance(this).saveLastVideoID(this.r);
            SessionManager.getInstance(this).saveLastVideoName(this.t);
            SessionManager.getInstance(this).saveLastVideoCategory(this.u);
            SessionManager.getInstance(this).saveLastVideoPosition(Float.valueOf(this.y));
        } else {
            SessionManager.getInstance(this).saveLastSensAIVideoID(this.r);
            SessionManager.getInstance(this).saveLastSensAIVideoName(this.t);
            SessionManager.getInstance(this).saveLastSensAIVideoCategory(this.u);
            SessionManager.getInstance(this).saveLastSensAIVideoPosition(Float.valueOf(this.y));
        }
        finish();
    }
}
