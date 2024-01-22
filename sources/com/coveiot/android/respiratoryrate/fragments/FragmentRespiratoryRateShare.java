package com.coveiot.android.respiratoryrate.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.coveiot.android.respiratoryrate.databinding.FragmentRespiratoryRateShareBinding;
import com.coveiot.android.respiratoryrate.model.RespiratoryRateShareData;
import com.coveiot.android.respiratoryrate.utils.Constants;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class FragmentRespiratoryRateShare extends Fragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public FragmentRespiratoryRateShareBinding h;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public RespiratoryRateShareData i = new RespiratoryRateShareData();

    /* loaded from: classes6.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentRespiratoryRateShare newInstance(@NotNull RespiratoryRateShareData respiratoryRateShareData) {
            Intrinsics.checkNotNullParameter(respiratoryRateShareData, "respiratoryRateShareData");
            FragmentRespiratoryRateShare fragmentRespiratoryRateShare = new FragmentRespiratoryRateShare();
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constants.SHARE_DATA.getValue(), respiratoryRateShareData);
            fragmentRespiratoryRateShare.setArguments(bundle);
            return fragmentRespiratoryRateShare;
        }
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

    public final FragmentRespiratoryRateShareBinding b() {
        FragmentRespiratoryRateShareBinding fragmentRespiratoryRateShareBinding = this.h;
        Intrinsics.checkNotNull(fragmentRespiratoryRateShareBinding);
        return fragmentRespiratoryRateShareBinding;
    }

    @NotNull
    public final RespiratoryRateShareData getRespiratoryRateShareData() {
        return this.i;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            Serializable serializable = requireArguments().getSerializable(Constants.SHARE_DATA.getValue());
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.android.respiratoryrate.model.RespiratoryRateShareData");
            this.i = (RespiratoryRateShareData) serializable;
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.h = FragmentRespiratoryRateShareBinding.inflate(inflater, viewGroup, false);
        return b().getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.h = null;
        _$_clearFindViewByIdCache();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x012a  */
    @Override // androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onViewCreated(@org.jetbrains.annotations.NotNull android.view.View r4, @org.jetbrains.annotations.Nullable android.os.Bundle r5) {
        /*
            Method dump skipped, instructions count: 327
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.respiratoryrate.fragments.FragmentRespiratoryRateShare.onViewCreated(android.view.View, android.os.Bundle):void");
    }

    public final void setRespiratoryRateShareData(@NotNull RespiratoryRateShareData respiratoryRateShareData) {
        Intrinsics.checkNotNullParameter(respiratoryRateShareData, "<set-?>");
        this.i = respiratoryRateShareData;
    }
}
