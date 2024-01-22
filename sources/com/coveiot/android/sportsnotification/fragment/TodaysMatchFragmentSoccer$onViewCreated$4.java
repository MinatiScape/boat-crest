package com.coveiot.android.sportsnotification.fragment;

import android.text.Editable;
import android.text.TextWatcher;
import androidx.fragment.app.FragmentActivity;
import com.coveiot.android.sportsnotification.adapter.TodaysMatchAdapterSoccer;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class TodaysMatchFragmentSoccer$onViewCreated$4 implements TextWatcher {
    public final /* synthetic */ TodaysMatchFragmentSoccer h;

    public TodaysMatchFragmentSoccer$onViewCreated$4(TodaysMatchFragmentSoccer todaysMatchFragmentSoccer) {
        this.h = todaysMatchFragmentSoccer;
    }

    public static final void b(TodaysMatchFragmentSoccer this$0, Editable s) {
        TodaysMatchAdapterSoccer todaysMatchAdapterSoccer;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(s, "$s");
        todaysMatchAdapterSoccer = this$0.r;
        if (todaysMatchAdapterSoccer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("todaysMatchAdapter");
            todaysMatchAdapterSoccer = null;
        }
        todaysMatchAdapterSoccer.search(s.toString());
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(@NotNull final Editable s) {
        Intrinsics.checkNotNullParameter(s, "s");
        FragmentActivity activity = this.h.getActivity();
        if (activity != null) {
            final TodaysMatchFragmentSoccer todaysMatchFragmentSoccer = this.h;
            activity.runOnUiThread(new Runnable() { // from class: com.coveiot.android.sportsnotification.fragment.z0
                @Override // java.lang.Runnable
                public final void run() {
                    TodaysMatchFragmentSoccer$onViewCreated$4.b(TodaysMatchFragmentSoccer.this, s);
                }
            });
        }
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(@NotNull CharSequence s, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(s, "s");
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(@NotNull CharSequence s, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(s, "s");
    }
}
