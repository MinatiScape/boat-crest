package com.coveiot.android.leonardo.more.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwnerKt;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.request.FindMyWatchRequest;
import com.coveiot.android.bleabstract.request.StopFindMyWatchRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.DeviceModelBean;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentFindMyWatch extends Fragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public CountDownTimer h;
    public boolean i;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentFindMyWatch newInstance() {
            return new FragmentFindMyWatch();
        }
    }

    public static final void d(FragmentFindMyWatch this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (Intrinsics.areEqual(((Button) this$0._$_findCachedViewById(R.id.btn_ok)).getText(), this$0.getString(R.string.stop_search))) {
            this$0.g();
            this$0.requireActivity().onBackPressed();
            return;
        }
        this$0.c();
    }

    @JvmStatic
    @NotNull
    public static final FragmentFindMyWatch newInstance() {
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

    public final void b() {
        BleApiManager.getInstance(requireContext()).getBleApi().setUserSettings(new FindMyWatchRequest(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.fragments.FragmentFindMyWatch$initiateFindMyWatch$1

            @DebugMetadata(c = "com.coveiot.android.leonardo.more.fragments.FragmentFindMyWatch$initiateFindMyWatch$1$onSettingsResponse$1", f = "FragmentFindMyWatch.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes5.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ FragmentFindMyWatch this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(FragmentFindMyWatch fragmentFindMyWatch, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = fragmentFindMyWatch;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        if (this.this$0.isAdded()) {
                            DeviceUtils.Companion companion = DeviceUtils.Companion;
                            Context requireContext = this.this$0.requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                            if (!companion.isCZDevice(requireContext)) {
                                Context requireContext2 = this.this$0.requireContext();
                                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                                if (!companion.isCADevice(requireContext2)) {
                                    Context requireContext3 = this.this$0.requireContext();
                                    Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                                    if (!companion.isCYDevice(requireContext3)) {
                                        Context requireContext4 = this.this$0.requireContext();
                                        Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                                        if (!companion.isPS1Device(requireContext4)) {
                                            Context requireContext5 = this.this$0.requireContext();
                                            Intrinsics.checkNotNullExpressionValue(requireContext5, "requireContext()");
                                            if (!companion.isBESDevice(requireContext5)) {
                                                Context requireContext6 = this.this$0.requireContext();
                                                Intrinsics.checkNotNullExpressionValue(requireContext6, "requireContext()");
                                                if (companion.isTouchELXDevice(requireContext6)) {
                                                    Toast.makeText(this.this$0.requireContext(), this.this$0.getString(R.string.band_will_ring), 1).show();
                                                } else {
                                                    Toast.makeText(this.this$0.requireContext(), this.this$0.getString(R.string.band_vibrate_for_5_times), 1).show();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LifecycleOwnerKt.getLifecycleScope(FragmentFindMyWatch.this).launchWhenResumed(new a(FragmentFindMyWatch.this, null));
            }
        });
    }

    public final void c() {
        ((Button) _$_findCachedViewById(R.id.btn_ok)).setText(R.string.stop_search);
        ((ImageView) _$_findCachedViewById(R.id.img_searching_my_watch)).setImageResource(R.drawable.find_my_searching);
        e(15000L);
        b();
        this.i = true;
        CountDownTimer countDownTimer = new CountDownTimer() { // from class: com.coveiot.android.leonardo.more.fragments.FragmentFindMyWatch$initiateWatchLocationAnimation$1
            {
                super(15000L, 1000L);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                ((Button) FragmentFindMyWatch.this._$_findCachedViewById(R.id.btn_ok)).setText(R.string.search_again);
                ((ImageView) FragmentFindMyWatch.this._$_findCachedViewById(R.id.img_searching_my_watch)).setImageResource(R.drawable.find_my_searching_failed);
                FragmentFindMyWatch.this.setDFUInitiated(false);
                FragmentFindMyWatch.this.f();
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                if (FragmentFindMyWatch.this.isAdded()) {
                    FragmentFindMyWatch.this.e(j);
                }
            }
        };
        this.h = countDownTimer;
        countDownTimer.start();
    }

    public final void e(long j) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("00:%02d Seconds", Arrays.copyOf(new Object[]{Long.valueOf(j / 1000)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        SpannableString spannableString = new SpannableString(format);
        spannableString.setSpan(new ForegroundColorSpan(requireContext().getColor(R.color.color_b3b3b3)), StringsKt__StringsKt.indexOf$default((CharSequence) format, "Seconds", 0, false, 6, (Object) null), format.length(), 33);
        int i = R.id.seconds_remaining;
        ((TextView) _$_findCachedViewById(i)).setText(spannableString);
        ((TextView) _$_findCachedViewById(i)).setMovementMethod(new LinkMovementMethod());
    }

    public final void f() {
        BleApiManager.getInstance(requireContext()).getBleApi().setUserSettings(new StopFindMyWatchRequest(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.fragments.FragmentFindMyWatch$stopFindMyWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
            }
        });
    }

    public final void g() {
        CountDownTimer countDownTimer = this.h;
        if (countDownTimer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countDownTimer");
            countDownTimer = null;
        }
        countDownTimer.cancel();
        this.i = false;
        f();
    }

    public final boolean isDFUInitiated() {
        return this.i;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_find_my_watch, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        g();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        DeviceModelBean deviceModelBean = SessionManager.getInstance(requireContext()).getDeviceModelBean();
        Intrinsics.checkNotNullExpressionValue(deviceModelBean, "getInstance(\n           …        ).deviceModelBean");
        ((ImageView) _$_findCachedViewById(R.id.watch_image)).setImageResource(companion.getWatchModelImagePref(deviceModelBean));
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(R.string.searching_for_your_watch);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.searching_for_your_watch)");
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String format = String.format(string, Arrays.copyOf(new Object[]{companion.getWatchName(requireContext)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        ((TextView) _$_findCachedViewById(R.id.tv_searching_for_watch)).setText(format);
        c();
        int i = R.id.btn_ok;
        ((Button) _$_findCachedViewById(i)).setText(R.string.stop_search);
        ((Button) _$_findCachedViewById(i)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.r
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentFindMyWatch.d(FragmentFindMyWatch.this, view2);
            }
        });
    }

    public final void setDFUInitiated(boolean z) {
        this.i = z;
    }
}
