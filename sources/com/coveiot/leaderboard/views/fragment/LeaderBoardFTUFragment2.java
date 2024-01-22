package com.coveiot.leaderboard.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.coveiot.leaderboard.LeaderBoardNavigator;
import com.coveiot.leaderboard.R;
/* loaded from: classes9.dex */
public class LeaderBoardFTUFragment2 extends Fragment {

    /* loaded from: classes9.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LeaderBoardNavigator.navigateToSelectAddressScreen(LeaderBoardFTUFragment2.this.getActivity());
            LeaderBoardFTUFragment2.this.getActivity().finish();
        }
    }

    public static LeaderBoardFTUFragment2 newInstance(String str, String str2) {
        LeaderBoardFTUFragment2 leaderBoardFTUFragment2 = new LeaderBoardFTUFragment2();
        Bundle bundle = new Bundle();
        bundle.putString("param1", str);
        bundle.putString("param2", str2);
        leaderBoardFTUFragment2.setArguments(bundle);
        return leaderBoardFTUFragment2;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            getArguments().getString("param1");
            getArguments().getString("param2");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_leader_board_ftufragment2, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.findViewById(R.id.btn_select_location).setOnClickListener(new a());
    }
}
