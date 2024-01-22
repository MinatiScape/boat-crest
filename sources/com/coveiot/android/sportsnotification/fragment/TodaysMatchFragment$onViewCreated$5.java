package com.coveiot.android.sportsnotification.fragment;

import android.text.Editable;
import android.text.TextWatcher;
import androidx.fragment.app.FragmentActivity;
import com.coveiot.android.sportsnotification.adapter.TodaysMatchAdapter;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class TodaysMatchFragment$onViewCreated$5 implements TextWatcher {
    public final /* synthetic */ TodaysMatchFragment h;

    public TodaysMatchFragment$onViewCreated$5(TodaysMatchFragment todaysMatchFragment) {
        this.h = todaysMatchFragment;
    }

    public static final void b(TodaysMatchFragment this$0, Editable s) {
        TodaysMatchAdapter todaysMatchAdapter;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(s, "$s");
        todaysMatchAdapter = this$0.r;
        if (todaysMatchAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("todaysMatchAdapter");
            todaysMatchAdapter = null;
        }
        todaysMatchAdapter.search(s.toString());
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(@NotNull final Editable s) {
        Intrinsics.checkNotNullParameter(s, "s");
        FragmentActivity activity = this.h.getActivity();
        if (activity != null) {
            final TodaysMatchFragment todaysMatchFragment = this.h;
            activity.runOnUiThread(new Runnable() { // from class: com.coveiot.android.sportsnotification.fragment.l0
                @Override // java.lang.Runnable
                public final void run() {
                    TodaysMatchFragment$onViewCreated$5.b(TodaysMatchFragment.this, s);
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
