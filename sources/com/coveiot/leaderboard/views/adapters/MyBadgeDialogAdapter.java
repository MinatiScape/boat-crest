package com.coveiot.leaderboard.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;
import com.coveiot.coveaccess.leaderboard.model.MyBadgesModel;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.base.BaseAdapter;
import com.coveiot.leaderboard.base.BaseViewHolder;
import com.coveiot.leaderboard.utils.LeaderboardConstants;
import com.coveiot.leaderboard.utils.LeaderboardUtils;
import com.goodix.ble.libcomx.util.HexStringBuilder;
/* loaded from: classes9.dex */
public class MyBadgeDialogAdapter extends BaseAdapter<MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean, MyBadgesHolder> {

    /* renamed from: a  reason: collision with root package name */
    public Context f7239a;

    /* loaded from: classes9.dex */
    public class MyBadgesHolder extends BaseViewHolder<MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean> {
        public RelativeLayout mBaseLayout;
        public TextView mLevelDescription;
        public ImageView mLevelIv;
        public TextView mObtainedDate;

        /* loaded from: classes9.dex */
        public class a implements View.OnClickListener {
            public final /* synthetic */ MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean h;

            public a(MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean badgeLevelsBean) {
                this.h = badgeLevelsBean;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (this.h.getObtainedDate() == null) {
                    MyBadgesHolder.this.mLevelDescription.setText(this.h.getLevelDescription());
                }
            }
        }

        public MyBadgesHolder(View view) {
            super(view);
            this.mBaseLayout = (RelativeLayout) view.findViewById(R.id.baseLayout);
            this.mObtainedDate = (TextView) view.findViewById(R.id.obtainedDate);
            this.mLevelDescription = (TextView) view.findViewById(R.id.levelDescription);
            this.mLevelIv = (ImageView) view.findViewById(R.id.levelIv);
        }

        @Override // com.coveiot.leaderboard.base.BaseViewHolder
        public void onBindView(Context context, MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean badgeLevelsBean, int i) {
            Glide.with(context).m30load(badgeLevelsBean.getLevelImageUrl()).into(this.mLevelIv);
            if (badgeLevelsBean.getObtainedDate() != null) {
                this.mLevelDescription.setText(badgeLevelsBean.getLevelDescription());
                this.mObtainedDate.setVisibility(0);
                TextView textView = this.mObtainedDate;
                textView.setText(context.getString(R.string.earned_on) + HexStringBuilder.DEFAULT_SEPARATOR + LeaderboardUtils.formattedDate(badgeLevelsBean.getObtainedDate(), "dd MMMM yyyy"));
                this.mLevelDescription.setTextColor(ContextCompat.getColor(MyBadgeDialogAdapter.this.f7239a, R.color.core_text_color_blue));
                this.mObtainedDate.setTextColor(ContextCompat.getColor(MyBadgeDialogAdapter.this.f7239a, R.color.core_text_color_white));
                this.mBaseLayout.setBackgroundResource(R.drawable.default_badge);
            } else {
                TextView textView2 = this.mLevelDescription;
                textView2.setText(context.getString(R.string.how_to_achieve) + HexStringBuilder.DEFAULT_SEPARATOR + badgeLevelsBean.getLevelName() + context.getString(R.string.medal_with_space));
                this.mObtainedDate.setVisibility(8);
                if (badgeLevelsBean.getLevelName().equalsIgnoreCase(LeaderboardConstants.SILVER)) {
                    this.mBaseLayout.setBackgroundResource(R.drawable.silver_medal);
                } else if (badgeLevelsBean.getLevelName().equalsIgnoreCase(LeaderboardConstants.GOLD)) {
                    this.mBaseLayout.setBackgroundResource(R.drawable.gold_medal);
                } else {
                    this.mBaseLayout.setBackgroundResource(R.drawable.bronze);
                }
            }
            this.mBaseLayout.setOnClickListener(new a(badgeLevelsBean));
        }
    }

    public MyBadgeDialogAdapter(Context context) {
        super(context);
        this.f7239a = context;
    }

    @Override // com.coveiot.leaderboard.base.BaseAdapter
    public MyBadgesHolder getViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        return new MyBadgesHolder(layoutInflater.inflate(R.layout.my_badge_info_dialog_list_item, viewGroup, false));
    }
}
