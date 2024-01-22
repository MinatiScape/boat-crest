package com.coveiot.android.activitymodes.workoutVideos.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.activitymodes.workoutVideos.fragments.CultFitVideoFragment;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.LogHelper;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class CultFitVideoFragment extends Fragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public boolean j;
    public YouTubePlayer k;
    public AbstractYouTubePlayerListener l;
    @Nullable
    public String m;
    @Nullable
    public VideoStatusListener o;
    public boolean p;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public float h = -1.0f;
    public final String i = Companion.getClass().getSimpleName();
    @Nullable
    public Float n = Float.valueOf(-1.0f);

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final CultFitVideoFragment newInstance(boolean z) {
            CultFitVideoFragment cultFitVideoFragment = new CultFitVideoFragment();
            Bundle bundle = new Bundle(1);
            bundle.putBoolean("fromWorkout", z);
            cultFitVideoFragment.setArguments(bundle);
            if (cultFitVideoFragment.requireArguments() != null) {
                cultFitVideoFragment.p = cultFitVideoFragment.requireArguments().getBoolean("fromWorkout");
            }
            return cultFitVideoFragment;
        }
    }

    /* loaded from: classes2.dex */
    public interface VideoStatusListener {
        void onVideoEnded();
    }

    @JvmStatic
    @NotNull
    public static final CultFitVideoFragment newInstance(boolean z) {
        return Companion.newInstance(z);
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Nullable
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        if (context instanceof VideoStatusListener) {
            this.o = (VideoStatusListener) context;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_cult_fit_video, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        WorkoutUtils workoutUtils = WorkoutUtils.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        workoutUtils.getMetadata(requireContext);
        if (workoutUtils.getAPI_KEY() == null) {
            return;
        }
        if (this.p) {
            this.m = SessionManager.getInstance(requireContext()).getLastVideoId();
            SessionManager.getInstance(requireContext()).getLastVideoName();
            this.n = SessionManager.getInstance(requireContext()).getLastVideoPosition();
        } else {
            this.m = SessionManager.getInstance(requireContext()).getLastSensAIVideoId();
            SessionManager.getInstance(requireContext()).getLastSensAIVideoName();
            this.n = SessionManager.getInstance(requireContext()).getLastSensAIVideoPosition();
        }
        if (this.m == null) {
            return;
        }
        this.l = new AbstractYouTubePlayerListener() { // from class: com.coveiot.android.activitymodes.workoutVideos.fragments.CultFitVideoFragment$onViewCreated$1

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
                float f2;
                Intrinsics.checkNotNullParameter(youTubePlayer, "youTubePlayer");
                super.onCurrentSecond(youTubePlayer, f);
                CultFitVideoFragment.this.h = f;
                SessionManager sessionManager = SessionManager.getInstance(CultFitVideoFragment.this.getContext());
                f2 = CultFitVideoFragment.this.h;
                sessionManager.saveLastVideoPosition(Float.valueOf(f2));
            }

            @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener, com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
            public void onError(@NotNull YouTubePlayer youTubePlayer, @NotNull PlayerConstants.PlayerError error) {
                String str;
                Intrinsics.checkNotNullParameter(youTubePlayer, "youTubePlayer");
                Intrinsics.checkNotNullParameter(error, "error");
                super.onError(youTubePlayer, error);
                str = CultFitVideoFragment.this.i;
                LogHelper.i(str, error.name());
            }

            @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener, com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
            public void onReady(@NonNull @NotNull YouTubePlayer youTubePlayer) {
                String str;
                Float f;
                Intrinsics.checkNotNullParameter(youTubePlayer, "youTubePlayer");
                CultFitVideoFragment.this.k = youTubePlayer;
                CultFitVideoFragment cultFitVideoFragment = CultFitVideoFragment.this;
                int i = R.id.youtubePlayerView;
                if (((YouTubePlayerView) cultFitVideoFragment._$_findCachedViewById(i)) != null) {
                    CultFitVideoFragment.this.getLifecycle().addObserver((YouTubePlayerView) CultFitVideoFragment.this._$_findCachedViewById(i));
                }
                str = CultFitVideoFragment.this.m;
                Intrinsics.checkNotNull(str);
                f = CultFitVideoFragment.this.n;
                Intrinsics.checkNotNull(f);
                youTubePlayer.cueVideo(str, f.floatValue());
            }

            @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener, com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
            public void onStateChange(@NotNull YouTubePlayer youTubePlayer, @NotNull PlayerConstants.PlayerState state) {
                boolean z;
                CultFitVideoFragment.VideoStatusListener videoStatusListener;
                String str;
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
                        CultFitVideoFragment.this.j = true;
                        z = CultFitVideoFragment.this.p;
                        if (z) {
                            SessionManager.getInstance(CultFitVideoFragment.this.requireContext()).saveLastVideoID(null);
                            SessionManager.getInstance(CultFitVideoFragment.this.requireContext()).saveLastVideoName(null);
                            SessionManager.getInstance(CultFitVideoFragment.this.requireContext()).saveLastVideoCategory(null);
                            SessionManager.getInstance(CultFitVideoFragment.this.requireContext()).saveLastVideoPosition(Float.valueOf(-1.0f));
                        } else {
                            SessionManager.getInstance(CultFitVideoFragment.this.requireContext()).saveLastSensAIVideoID(null);
                            SessionManager.getInstance(CultFitVideoFragment.this.requireContext()).saveLastSensAIVideoName(null);
                            SessionManager.getInstance(CultFitVideoFragment.this.requireContext()).saveLastSensAIVideoCategory(null);
                            SessionManager.getInstance(CultFitVideoFragment.this.requireContext()).saveLastSensAIVideoPosition(Float.valueOf(-1.0f));
                        }
                        videoStatusListener = CultFitVideoFragment.this.o;
                        if (videoStatusListener != null) {
                            videoStatusListener.onVideoEnded();
                        }
                        str = CultFitVideoFragment.this.i;
                        LogHelper.i(str, "onVideoEnded");
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
        IFramePlayerOptions build = new IFramePlayerOptions.Builder().controls(1).build();
        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) _$_findCachedViewById(R.id.youtubePlayerView);
        AbstractYouTubePlayerListener abstractYouTubePlayerListener = this.l;
        if (abstractYouTubePlayerListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("youTubePlayerStateListener");
            abstractYouTubePlayerListener = null;
        }
        youTubePlayerView.initialize(abstractYouTubePlayerListener, build);
    }
}
