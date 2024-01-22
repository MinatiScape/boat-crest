package com.coveiot.android.sportsnotification.fragment;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import com.coveiot.android.sportsnotification.R;
import com.coveiot.android.sportsnotification.model.CoinNotifications;
import com.coveiot.android.theme.BoatCoinsSportsDialog;
import com.coveiot.android.theme.utils.BoatCoinsFirebaseConfigResultListener;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class TodaysMatchFragmentSoccer$showResultDialog$1$1 implements BoatCoinsFirebaseConfigResultListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ boolean f5859a;
    public final /* synthetic */ List<CoinNotifications> b;
    public final /* synthetic */ TodaysMatchFragmentSoccer c;

    public TodaysMatchFragmentSoccer$showResultDialog$1$1(boolean z, List<CoinNotifications> list, TodaysMatchFragmentSoccer todaysMatchFragmentSoccer) {
        this.f5859a = z;
        this.b = list;
        this.c = todaysMatchFragmentSoccer;
    }

    public static final void b(TodaysMatchFragmentSoccer this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requireActivity().finish();
    }

    @Override // com.coveiot.android.theme.utils.BoatCoinsFirebaseConfigResultListener
    public void onFailure(@Nullable String str) {
        if (this.f5859a) {
            this.c.requireActivity().finish();
        }
    }

    @Override // com.coveiot.android.theme.utils.BoatCoinsFirebaseConfigResultListener
    public void onResult(boolean z) {
        if (z) {
            if (this.f5859a) {
                List<CoinNotifications> list = this.b;
                if (!(list == null || list.isEmpty())) {
                    for (CoinNotifications coinNotifications : this.b) {
                        Context mContext = this.c.getMContext();
                        Intrinsics.checkNotNull(mContext);
                        BoatCoinsSportsDialog boatCoinsSportsDialog = new BoatCoinsSportsDialog(mContext);
                        TextView textView = (TextView) boatCoinsSportsDialog.findViewById(R.id.tv_congratulations);
                        if (coinNotifications.getTitle() != null) {
                            textView.setText(coinNotifications.getTitle());
                        }
                        TextView textView2 = (TextView) boatCoinsSportsDialog.findViewById(R.id.tv_dynamic_text);
                        if (coinNotifications.getDescription() != null) {
                            textView2.setText(coinNotifications.getDescription());
                        }
                        final TodaysMatchFragmentSoccer todaysMatchFragmentSoccer = this.c;
                        ((Button) boatCoinsSportsDialog.findViewById(R.id.ok_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.a1
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                TodaysMatchFragmentSoccer$showResultDialog$1$1.b(TodaysMatchFragmentSoccer.this, view);
                            }
                        });
                        Window window = boatCoinsSportsDialog.getWindow();
                        Intrinsics.checkNotNull(window);
                        window.setBackgroundDrawable(new ColorDrawable(0));
                        boatCoinsSportsDialog.show();
                    }
                    return;
                }
            }
            this.c.requireActivity().finish();
            return;
        }
        this.c.requireActivity().finish();
    }
}
