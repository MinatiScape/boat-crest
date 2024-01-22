package com.coveiot.android.leonardo.onboarding.ftu;

import android.animation.Animator;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.airbnb.lottie.LottieAnimationView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.onboarding.ftu.FragmentFirstTimeUser;
import com.coveiot.android.theme.BaseFragment;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentFirstTimeUser extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public AnimationListener listner;
    public int m;
    @Nullable
    public String n;
    @Nullable
    public String o;
    @Nullable
    public Integer p;
    @Nullable
    public LottieAnimationView q;

    /* loaded from: classes5.dex */
    public interface AnimationListener {
        void onAnimationEnded(int i);
    }

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentFirstTimeUser newInstance(int i, @NotNull String contents, @NotNull String description, int i2) {
            Intrinsics.checkNotNullParameter(contents, "contents");
            Intrinsics.checkNotNullParameter(description, "description");
            FragmentFirstTimeUser fragmentFirstTimeUser = new FragmentFirstTimeUser();
            fragmentFirstTimeUser.m = i;
            fragmentFirstTimeUser.n = contents;
            fragmentFirstTimeUser.o = description;
            fragmentFirstTimeUser.p = Integer.valueOf(i2);
            return fragmentFirstTimeUser;
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentFirstTimeUser newInstance(int i, @NotNull String str, @NotNull String str2, int i2) {
        return Companion.newInstance(i, str, str2, i2);
    }

    @Override // com.coveiot.android.theme.BaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseFragment
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

    @NotNull
    public final AnimationListener getListner() {
        AnimationListener animationListener = this.listner;
        if (animationListener != null) {
            return animationListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("listner");
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        if (context instanceof AnimationListener) {
            setListner((AnimationListener) context);
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(R.layout.fragment_first_time_user, viewGroup, false);
        LottieAnimationView lottieAnimationView = (LottieAnimationView) inflate.findViewById(R.id.imageViewHolder);
        this.q = lottieAnimationView;
        if (lottieAnimationView != null) {
            try {
                int i = this.m;
                if (i == 0) {
                    i = R.raw.new_onboarding_json1;
                }
                lottieAnimationView.setAnimation(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LottieAnimationView lottieAnimationView2 = this.q;
        if (lottieAnimationView2 != null) {
            lottieAnimationView2.addAnimatorListener(new Animator.AnimatorListener() { // from class: com.coveiot.android.leonardo.onboarding.ftu.FragmentFirstTimeUser$onCreateView$1
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(@NotNull Animator animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(@NotNull Animator animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(@NotNull Animator animation, boolean z) {
                    Integer num;
                    Intrinsics.checkNotNullParameter(animation, "animation");
                    super.onAnimationEnd(animation, z);
                    FragmentFirstTimeUser.AnimationListener listner = FragmentFirstTimeUser.this.getListner();
                    num = FragmentFirstTimeUser.this.p;
                    Intrinsics.checkNotNull(num);
                    listner.onAnimationEnded(num.intValue());
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(@NotNull Animator animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(@NotNull Animator animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(@NotNull Animator animation, boolean z) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                }
            });
        }
        return inflate;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        playAnimation();
    }

    public final void playAnimation() {
        LottieAnimationView lottieAnimationView = this.q;
        if (lottieAnimationView != null) {
            lottieAnimationView.playAnimation();
        }
    }

    public final void setListner(@NotNull AnimationListener animationListener) {
        Intrinsics.checkNotNullParameter(animationListener, "<set-?>");
        this.listner = animationListener;
    }
}
