package com.coveiot.android.tappy.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.motion.widget.Key;
import com.bumptech.glide.Glide;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.tappy.R;
import com.coveiot.android.tappy.databinding.ContactPhoneNfcStrapBinding;
import com.coveiot.android.tappy.utils.TappyCleverTapConstants;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.CleverTapConstants;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class FragmentContactPhoneNfcStrap extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public ContactPhoneNfcStrapBinding m;
    @Nullable
    public ImageView o;
    @Nullable
    public OnTryAgainClickListener p;
    @Nullable
    public Long q;
    @Nullable
    public String r;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public int n = 1;

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ FragmentContactPhoneNfcStrap newInstance$default(Companion companion, int i, OnTryAgainClickListener onTryAgainClickListener, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                onTryAgainClickListener = null;
            }
            return companion.newInstance(i, onTryAgainClickListener);
        }

        @JvmStatic
        @NotNull
        public final FragmentContactPhoneNfcStrap newInstance(int i, @Nullable OnTryAgainClickListener onTryAgainClickListener) {
            FragmentContactPhoneNfcStrap fragmentContactPhoneNfcStrap = new FragmentContactPhoneNfcStrap();
            fragmentContactPhoneNfcStrap.n = i;
            if (onTryAgainClickListener != null) {
                fragmentContactPhoneNfcStrap.p = onTryAgainClickListener;
            }
            return fragmentContactPhoneNfcStrap;
        }
    }

    /* loaded from: classes7.dex */
    public interface OnTryAgainClickListener {
        void onTryAgainClicked();
    }

    @JvmStatic
    @NotNull
    public static final FragmentContactPhoneNfcStrap newInstance(int i, @Nullable OnTryAgainClickListener onTryAgainClickListener) {
        return Companion.newInstance(i, onTryAgainClickListener);
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

    @Nullable
    public final Long getStrapId() {
        return this.q;
    }

    @Nullable
    public final String getStrapName() {
        return this.r;
    }

    public final ContactPhoneNfcStrapBinding k() {
        ContactPhoneNfcStrapBinding contactPhoneNfcStrapBinding = this.m;
        Intrinsics.checkNotNull(contactPhoneNfcStrapBinding);
        return contactPhoneNfcStrapBinding;
    }

    public final void l() {
        int i = this.n;
        if (i == 0) {
            k().tvTitle.setText(getString(R.string.move_your_strap_away_and_tap));
            TextView textView = k().tvStrapAddStatus;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvStrapAddStatus");
            gone(textView);
            TextView textView2 = k().tvStrapAddSuccess;
            Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvStrapAddSuccess");
            gone(textView2);
            TextView textView3 = k().tvDataTransferStatusSucess;
            Intrinsics.checkNotNullExpressionValue(textView3, "binding.tvDataTransferStatusSucess");
            gone(textView3);
            TextView textView4 = k().tvTitle;
            Intrinsics.checkNotNullExpressionValue(textView4, "binding.tvTitle");
            visible(textView4);
        } else if (i == 1) {
            TextView textView5 = k().tvStrapAddStatus;
            Intrinsics.checkNotNullExpressionValue(textView5, "binding.tvStrapAddStatus");
            gone(textView5);
            TextView textView6 = k().tvStrapAddSuccess;
            Intrinsics.checkNotNullExpressionValue(textView6, "binding.tvStrapAddSuccess");
            gone(textView6);
            TextView textView7 = k().tvDataTransferStatusSucess;
            Intrinsics.checkNotNullExpressionValue(textView7, "binding.tvDataTransferStatusSucess");
            gone(textView7);
            TextView textView8 = k().tvTitle;
            Intrinsics.checkNotNullExpressionValue(textView8, "binding.tvTitle");
            visible(textView8);
            k().tvTitle.setText(getString(R.string.make_sure_the_nfc_strap_and));
            m(CleverTapConstants.EventName.BC_PAY_NFC_PAIR_REQUEST);
        } else if (i == 2) {
            k().tvStrapAddStatus.setVisibility(8);
            TextView textView9 = k().tvDataTransferStatusSucess;
            Intrinsics.checkNotNullExpressionValue(textView9, "binding.tvDataTransferStatusSucess");
            gone(textView9);
            TextView textView10 = k().tvStrapAddSuccess;
            Intrinsics.checkNotNullExpressionValue(textView10, "binding.tvStrapAddSuccess");
            visible(textView10);
            TextView textView11 = k().tvTitle;
            Intrinsics.checkNotNullExpressionValue(textView11, "binding.tvTitle");
            visible(textView11);
            k().tvTitle.setAlpha(1.0f);
            k().tvTitle.setText(getString(R.string.data_transfer_is_in_progress));
        } else if (i == 3) {
            TextView textView12 = k().tvStrapAddStatus;
            Intrinsics.checkNotNullExpressionValue(textView12, "binding.tvStrapAddStatus");
            gone(textView12);
            TextView textView13 = k().tvStrapAddSuccess;
            Intrinsics.checkNotNullExpressionValue(textView13, "binding.tvStrapAddSuccess");
            gone(textView13);
            TextView textView14 = k().tvDataTransferStatusSucess;
            Intrinsics.checkNotNullExpressionValue(textView14, "binding.tvDataTransferStatusSucess");
            gone(textView14);
            TextView textView15 = k().tvTitle;
            Intrinsics.checkNotNullExpressionValue(textView15, "binding.tvTitle");
            visible(textView15);
            k().tvTitle.setText(getString(R.string.do_not_remove_your_phone_from_the_watch_and_wait));
        } else if (i != 4) {
            TextView textView16 = k().tvTitle;
            Intrinsics.checkNotNullExpressionValue(textView16, "binding.tvTitle");
            gone(textView16);
            TextView textView17 = k().tvStrapAddStatus;
            Intrinsics.checkNotNullExpressionValue(textView17, "binding.tvStrapAddStatus");
            gone(textView17);
            TextView textView18 = k().tvStrapAddSuccess;
            Intrinsics.checkNotNullExpressionValue(textView18, "binding.tvStrapAddSuccess");
            visible(textView18);
            TextView textView19 = k().tvDataTransferStatusSucess;
            Intrinsics.checkNotNullExpressionValue(textView19, "binding.tvDataTransferStatusSucess");
            visible(textView19);
        } else {
            TextView textView20 = k().tvStrapAddSuccess;
            Intrinsics.checkNotNullExpressionValue(textView20, "binding.tvStrapAddSuccess");
            gone(textView20);
            TextView textView21 = k().tvDataTransferStatusSucess;
            Intrinsics.checkNotNullExpressionValue(textView21, "binding.tvDataTransferStatusSucess");
            gone(textView21);
            TextView textView22 = k().tvStrapAddStatus;
            Intrinsics.checkNotNullExpressionValue(textView22, "binding.tvStrapAddStatus");
            visible(textView22);
            TextView textView23 = k().tvTitle;
            Intrinsics.checkNotNullExpressionValue(textView23, "binding.tvTitle");
            visible(textView23);
            k().tvTitle.setAlpha(0.5f);
            k().tvTitle.setText(getString(R.string.data_transfer));
        }
    }

    public final void m(CleverTapConstants.EventName eventName) {
        HashMap<String, Object> hashMap = new HashMap<>();
        if (eventName == CleverTapConstants.EventName.BC_PAY_NFC_PAIR_REQUEST) {
            hashMap.put(TappyCleverTapConstants.PAIRLOCATION.getValue(), TappyCleverTapConstants.PAIR_NFC_LANDING_PAGE.getValue());
        } else {
            Long l = this.q;
            if (l != null) {
                hashMap.put(TappyCleverTapConstants.STRAP_ID.getValue(), Long.valueOf(l.longValue()));
            }
            String str = this.r;
            if (str != null) {
                hashMap.put(TappyCleverTapConstants.STRAP_NAME.getValue(), str);
            }
        }
        DeviceUtils.Companion.logAnalyticsEvent(eventName.getValue(), hashMap);
    }

    public final void n(boolean z) {
        if (z) {
            k().addStrapProgress.setVisibility(0);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(k().addStrapProgress, Key.ROTATION, 0.0f, 360.0f);
            ofFloat.setDuration(Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
            ofFloat.setRepeatCount(-1);
            ofFloat.setInterpolator(new LinearInterpolator());
            ofFloat.start();
            return;
        }
        k().addStrapProgress.setVisibility(8);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = ContactPhoneNfcStrapBinding.inflate(inflater, viewGroup, false);
        return k().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        this.o = (ImageView) view.findViewById(R.id.ivStrapNfcNearby);
        TextView textView = (TextView) view.findViewById(R.id.tvTitle);
        l();
        n(true);
        Glide.with(requireContext()).m28load(Integer.valueOf(R.drawable.ftu_instruction_img)).into(k().ivStrapNfcNearby);
    }

    public final void setCurrentState(int i) {
        this.n = i;
        l();
    }

    public final void setStrapDetails(long j, @NotNull String strapName) {
        Intrinsics.checkNotNullParameter(strapName, "strapName");
        this.q = Long.valueOf(j);
        this.r = strapName;
        m(CleverTapConstants.EventName.BC_PAY_NFC_PAIR_SUCCESS);
    }

    public final void setStrapId(@Nullable Long l) {
        this.q = l;
    }

    public final void setStrapName(@Nullable String str) {
        this.r = str;
    }
}
