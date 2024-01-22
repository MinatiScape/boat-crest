package com.coveiot.android.leonardo.more.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.listeners.FindMyWatchNavigationListener;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentFindMyWatchIntructions extends Fragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public FindMyWatchNavigationListener listener;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentFindMyWatchIntructions newInstance() {
            return new FragmentFindMyWatchIntructions();
        }
    }

    public static final void b(FragmentFindMyWatchIntructions this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.listener != null) {
            this$0.getListener().navigateToFragment(FragmentFindMyWatch.Companion.newInstance());
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentFindMyWatchIntructions newInstance() {
        return Companion.newInstance();
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

    @NotNull
    public final FindMyWatchNavigationListener getListener() {
        FindMyWatchNavigationListener findMyWatchNavigationListener = this.listener;
        if (findMyWatchNavigationListener != null) {
            return findMyWatchNavigationListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException(ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NotNull Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        super.onAttach(activity);
        if (activity instanceof FindMyWatchNavigationListener) {
            setListener((FindMyWatchNavigationListener) activity);
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
        return inflater.inflate(R.layout.fragment_find_my_watch_intructions, viewGroup, false);
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
        ((Button) _$_findCachedViewById(R.id.btn_ok)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.s
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentFindMyWatchIntructions.b(FragmentFindMyWatchIntructions.this, view2);
            }
        });
    }

    public final void setListener(@NotNull FindMyWatchNavigationListener findMyWatchNavigationListener) {
        Intrinsics.checkNotNullParameter(findMyWatchNavigationListener, "<set-?>");
        this.listener = findMyWatchNavigationListener;
    }
}
