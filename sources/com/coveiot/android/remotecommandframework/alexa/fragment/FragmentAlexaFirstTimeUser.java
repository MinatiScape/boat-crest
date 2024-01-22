package com.coveiot.android.remotecommandframework.alexa.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.coveiot.android.remotecommandframework.R;
import com.coveiot.android.theme.BaseFragment;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class FragmentAlexaFirstTimeUser extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public int m;
    @Nullable
    public String n;
    @Nullable
    public String o;

    /* loaded from: classes6.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentAlexaFirstTimeUser newInstance(int i, @NotNull String contents, @NotNull String description) {
            Intrinsics.checkNotNullParameter(contents, "contents");
            Intrinsics.checkNotNullParameter(description, "description");
            FragmentAlexaFirstTimeUser fragmentAlexaFirstTimeUser = new FragmentAlexaFirstTimeUser();
            fragmentAlexaFirstTimeUser.m = i;
            fragmentAlexaFirstTimeUser.n = contents;
            fragmentAlexaFirstTimeUser.o = description;
            return fragmentAlexaFirstTimeUser;
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentAlexaFirstTimeUser newInstance(int i, @NotNull String str, @NotNull String str2) {
        return Companion.newInstance(i, str, str2);
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

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(R.layout.fragment_alexa_first_time_user, viewGroup, false);
        ((TextView) inflate.findViewById(R.id.textViewDesc1)).setText(this.n);
        ((TextView) inflate.findViewById(R.id.textViewDesc2)).setText(this.o);
        ((ImageView) inflate.findViewById(R.id.imageViewHolder)).setImageResource(this.m);
        return inflate;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }
}
