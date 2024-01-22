package com.coveiot.leaderboard.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.coveiot.coveaccess.leaderboard.model.AllBadgesModel;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.base.BaseAdapter;
import com.coveiot.leaderboard.base.BaseViewHolder;
import com.coveiot.leaderboard.utils.LeaderboardUtils;
import java.util.HashMap;
/* loaded from: classes9.dex */
public class DialogBadgeLevelAdapter extends BaseAdapter<AllBadgesModel.DataBean.BadgesBean.BadgeLevelsBean, AllBadgesHolder> {

    /* loaded from: classes9.dex */
    public class AllBadgesHolder extends BaseViewHolder<AllBadgesModel.DataBean.BadgesBean.BadgeLevelsBean> {
        public ImageView mIsObtained;
        public TextView mLevelDescription;
        public ImageView mLevelIv;

        public AllBadgesHolder(DialogBadgeLevelAdapter dialogBadgeLevelAdapter, View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.mIsObtained = (ImageView) view.findViewById(R.id.isObtained);
            this.mLevelDescription = (TextView) view.findViewById(R.id.levelDescription);
            this.mLevelIv = (ImageView) view.findViewById(R.id.levelIv);
        }

        @Override // com.coveiot.leaderboard.base.BaseViewHolder
        public void onBindView(Context context, AllBadgesModel.DataBean.BadgesBean.BadgeLevelsBean badgeLevelsBean, int i) {
            Glide.with(context).m30load(badgeLevelsBean.getLevelImageUrl()).into(this.mLevelIv);
            this.mLevelDescription.setText(badgeLevelsBean.getLevelDescription());
            HashMap<String, String> loadMap = LeaderboardUtils.loadMap(context);
            if (loadMap != null && loadMap.size() > 0) {
                loadMap.get("" + badgeLevelsBean.getLevelId());
                if (loadMap.get("" + badgeLevelsBean.getLevelId()) != null) {
                    this.mIsObtained.setVisibility(0);
                    return;
                } else {
                    this.mIsObtained.setVisibility(8);
                    return;
                }
            }
            this.mIsObtained.setVisibility(8);
        }
    }

    public DialogBadgeLevelAdapter(Context context) {
        super(context);
    }

    @Override // com.coveiot.leaderboard.base.BaseAdapter
    public AllBadgesHolder getViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        return new AllBadgesHolder(this, layoutInflater.inflate(R.layout.badge_info_dialog_list_item, viewGroup, false));
    }
}
