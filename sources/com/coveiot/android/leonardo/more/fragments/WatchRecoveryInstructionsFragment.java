package com.coveiot.android.leonardo.more.fragments;

import android.app.Activity;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.fragments.WatchRecoveryInstructionsFragment;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessageTwoBtns;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class WatchRecoveryInstructionsFragment extends Fragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int h = 101;
    public FragmentInteractionListener i;
    @Nullable
    public BottomSheetDialogImageTitleMessageTwoBtns j;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final WatchRecoveryInstructionsFragment newInstance() {
            WatchRecoveryInstructionsFragment watchRecoveryInstructionsFragment = new WatchRecoveryInstructionsFragment();
            watchRecoveryInstructionsFragment.setArguments(new Bundle());
            return watchRecoveryInstructionsFragment;
        }
    }

    /* loaded from: classes5.dex */
    public interface FragmentInteractionListener {
        void onNext();
    }

    public static final void c(WatchRecoveryInstructionsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isGpsEnabled(this$0.requireContext())) {
            Toast.makeText(this$0.requireContext(), this$0.getString(R.string.please_check_gps), 0).show();
        } else if (!AppUtils.isNetConnected(this$0.requireContext())) {
            Toast.makeText(this$0.requireContext(), this$0.getString(R.string.no_internet_connection), 0).show();
        } else if (AppUtils.checkIfLocationPermissionExists(this$0.requireContext())) {
            Object systemService = this$0.requireContext().getSystemService("bluetooth");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
            if (((BluetoothManager) systemService).getAdapter().isEnabled()) {
                this$0.f();
            } else {
                Toast.makeText(this$0.getContext(), this$0.getString(R.string.bluetooth_off_message), 0).show();
            }
        } else {
            this$0.d();
        }
    }

    public static final void g(WatchRecoveryInstructionsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns = this$0.j;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns);
        bottomSheetDialogImageTitleMessageTwoBtns.dismiss();
    }

    @JvmStatic
    @NotNull
    public static final WatchRecoveryInstructionsFragment newInstance() {
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

    public final void d() {
        PermissionUtils.checkPermission(requireContext(), "android.permission.ACCESS_FINE_LOCATION", new PermissionUtils.PermissionAskListener() { // from class: com.coveiot.android.leonardo.more.fragments.WatchRecoveryInstructionsFragment$requestLocationPermission$1
            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionAsk() {
                int i;
                i = WatchRecoveryInstructionsFragment.this.h;
                ActivityCompat.requestPermissions(WatchRecoveryInstructionsFragment.this.requireActivity(), new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, i);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionDisabled() {
                WatchRecoveryInstructionsFragment.this.e();
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionGranted() {
                Object systemService = WatchRecoveryInstructionsFragment.this.requireContext().getSystemService("bluetooth");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
                if (((BluetoothManager) systemService).getAdapter().isEnabled()) {
                    WatchRecoveryInstructionsFragment.this.f();
                } else {
                    Toast.makeText(WatchRecoveryInstructionsFragment.this.requireContext(), WatchRecoveryInstructionsFragment.this.getString(R.string.bluetooth_off_message), 0).show();
                }
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionPreviouslyDenied() {
                WatchRecoveryInstructionsFragment.this.e();
            }
        });
    }

    public final void e() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = getString(R.string.need_location_permission);
        Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …_permission\n            )");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(requireContext, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(com.coveiot.an…ctivitymodes.R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.WatchRecoveryInstructionsFragment$showLocationPermissionDialog$1
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                BottomSheetDialogOneButtonOneTitle.this.dismiss();
                AppUtils.openAppSettings(this.requireActivity(), 121);
            }
        });
        bottomSheetDialogOneButtonOneTitle.setCancelable(true);
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void f() {
        if (this.j == null) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            Drawable drawable = requireContext().getDrawable(R.drawable.ic_alert_icon);
            Intrinsics.checkNotNull(drawable);
            String string = getString(R.string.watch_recovery_msg1);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.watch_recovery_msg1)");
            BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns = new BottomSheetDialogImageTitleMessageTwoBtns(requireContext, drawable, string, "", true);
            this.j = bottomSheetDialogImageTitleMessageTwoBtns;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns);
            ImageView imageView = (ImageView) bottomSheetDialogImageTitleMessageTwoBtns.getConfirmPhoneNumberDialog().findViewById(R.id.close_icon);
            if (imageView != null) {
                imageView.setVisibility(0);
            }
            if (imageView != null) {
                imageView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.h3
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        WatchRecoveryInstructionsFragment.g(WatchRecoveryInstructionsFragment.this, view);
                    }
                });
            }
            BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns2 = this.j;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns2);
            bottomSheetDialogImageTitleMessageTwoBtns2.setCancelable(false);
            BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns3 = this.j;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns3);
            String string2 = getString(R.string.proceed);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.proceed)");
            bottomSheetDialogImageTitleMessageTwoBtns3.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.WatchRecoveryInstructionsFragment$showWarningDialog$2
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    WatchRecoveryInstructionsFragment.FragmentInteractionListener fragmentInteractionListener;
                    BottomSheetDialogImageTitleMessageTwoBtns warningDialog = WatchRecoveryInstructionsFragment.this.getWarningDialog();
                    if (warningDialog != null) {
                        warningDialog.dismiss();
                    }
                    fragmentInteractionListener = WatchRecoveryInstructionsFragment.this.i;
                    if (fragmentInteractionListener == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
                        fragmentInteractionListener = null;
                    }
                    fragmentInteractionListener.onNext();
                }
            });
            BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns4 = this.j;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns4);
            String string3 = getString(R.string.cancel);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.cancel)");
            bottomSheetDialogImageTitleMessageTwoBtns4.setNegativeButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.WatchRecoveryInstructionsFragment$showWarningDialog$3
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    BottomSheetDialogImageTitleMessageTwoBtns warningDialog = WatchRecoveryInstructionsFragment.this.getWarningDialog();
                    Intrinsics.checkNotNull(warningDialog);
                    warningDialog.dismiss();
                }
            });
        }
        BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns5 = this.j;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns5);
        if (bottomSheetDialogImageTitleMessageTwoBtns5.isShowing()) {
            return;
        }
        BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns6 = this.j;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns6);
        bottomSheetDialogImageTitleMessageTwoBtns6.show();
    }

    @Nullable
    public final BottomSheetDialogImageTitleMessageTwoBtns getWarningDialog() {
        return this.j;
    }

    public final void initClickListeners() {
        ((Button) _$_findCachedViewById(R.id.btn_update)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.i3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WatchRecoveryInstructionsFragment.c(WatchRecoveryInstructionsFragment.this, view);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NotNull Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        super.onAttach(activity);
        if (activity instanceof FragmentInteractionListener) {
            this.i = (FragmentInteractionListener) activity;
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
        return inflater.inflate(R.layout.fragment_watch_recovery_instructions, viewGroup, false);
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
        initClickListeners();
    }

    public final void setWarningDialog(@Nullable BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns) {
        this.j = bottomSheetDialogImageTitleMessageTwoBtns;
    }
}
