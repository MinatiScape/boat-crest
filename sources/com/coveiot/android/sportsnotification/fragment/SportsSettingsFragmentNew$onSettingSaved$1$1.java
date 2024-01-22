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
public final class SportsSettingsFragmentNew$onSettingSaved$1$1 implements BoatCoinsFirebaseConfigResultListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ List<CoinNotifications> f5856a;
    public final /* synthetic */ SportsSettingsFragmentNew b;

    public SportsSettingsFragmentNew$onSettingSaved$1$1(List<CoinNotifications> list, SportsSettingsFragmentNew sportsSettingsFragmentNew) {
        this.f5856a = list;
        this.b = sportsSettingsFragmentNew;
    }

    public static final void b(BoatCoinsSportsDialog dialog, SportsSettingsFragmentNew this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.replaceFragment();
    }

    @Override // com.coveiot.android.theme.utils.BoatCoinsFirebaseConfigResultListener
    public void onFailure(@Nullable String str) {
        this.b.replaceFragment();
    }

    @Override // com.coveiot.android.theme.utils.BoatCoinsFirebaseConfigResultListener
    public void onResult(boolean z) {
        if (z) {
            List<CoinNotifications> list = this.f5856a;
            if (!(list == null || list.isEmpty())) {
                for (CoinNotifications coinNotifications : this.f5856a) {
                    Context mcontext = this.b.getMcontext();
                    Intrinsics.checkNotNull(mcontext);
                    final BoatCoinsSportsDialog boatCoinsSportsDialog = new BoatCoinsSportsDialog(mcontext);
                    TextView textView = (TextView) boatCoinsSportsDialog.findViewById(R.id.tv_congratulations);
                    if (coinNotifications.getTitle() != null) {
                        textView.setText(coinNotifications.getTitle());
                    }
                    TextView textView2 = (TextView) boatCoinsSportsDialog.findViewById(R.id.tv_dynamic_text);
                    if (coinNotifications.getDescription() != null) {
                        textView2.setText(coinNotifications.getDescription());
                    }
                    final SportsSettingsFragmentNew sportsSettingsFragmentNew = this.b;
                    ((Button) boatCoinsSportsDialog.findViewById(R.id.ok_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.z
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            SportsSettingsFragmentNew$onSettingSaved$1$1.b(BoatCoinsSportsDialog.this, sportsSettingsFragmentNew, view);
                        }
                    });
                    Window window = boatCoinsSportsDialog.getWindow();
                    Intrinsics.checkNotNull(window);
                    window.setBackgroundDrawable(new ColorDrawable(0));
                    boatCoinsSportsDialog.show();
                }
                return;
            }
            this.b.replaceFragment();
            return;
        }
        this.b.replaceFragment();
    }
}
