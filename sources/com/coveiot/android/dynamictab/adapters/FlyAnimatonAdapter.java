package com.coveiot.android.dynamictab.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.coveiot.android.dynamictab.R;
import com.coveiot.android.dynamictab.adapters.base.BaseAdapter;
import com.coveiot.android.dynamictab.adapters.base.BaseViewHolder;
import com.coveiot.android.dynamictab.cricketmodels.OnAnimationItemClick;
import com.coveiot.android.dynamictab.sports.model.FlyAnimationModel;
import com.mappls.sdk.maps.style.layers.Property;
/* loaded from: classes4.dex */
public class FlyAnimatonAdapter extends BaseAdapter<FlyAnimationModel, FlyAnimationHolder> {

    /* renamed from: a  reason: collision with root package name */
    public OnAnimationItemClick f4318a;

    /* loaded from: classes4.dex */
    public class FlyAnimationHolder extends BaseViewHolder<FlyAnimationModel> {
        public ImageView mFlyIv;
        public RelativeLayout parent;

        /* loaded from: classes4.dex */
        public class a implements View.OnClickListener {
            public final /* synthetic */ FlyAnimationModel h;

            public a(FlyAnimationModel flyAnimationModel) {
                this.h = flyAnimationModel;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FlyAnimationHolder flyAnimationHolder = FlyAnimationHolder.this;
                FlyAnimatonAdapter.this.f4318a.onItemClick(flyAnimationHolder.mFlyIv, this.h.getImageUrl());
            }
        }

        public FlyAnimationHolder(View view) {
            super(view);
            this.mFlyIv = (ImageView) view.findViewById(R.id.image);
            this.parent = (RelativeLayout) view.findViewById(R.id.parent);
        }

        @Override // com.coveiot.android.dynamictab.adapters.base.BaseViewHolder
        public void onBindView(Context context, FlyAnimationModel flyAnimationModel, int i) {
            if (flyAnimationModel.getVisibility().equalsIgnoreCase(Property.VISIBLE)) {
                this.parent.setVisibility(0);
            } else {
                this.parent.setVisibility(8);
            }
            Glide.with(context).m30load(flyAnimationModel.getImageUrl()).apply((BaseRequestOptions<?>) RequestOptions.skipMemoryCacheOf(true)).into(this.mFlyIv);
            this.parent.setOnClickListener(new a(flyAnimationModel));
        }
    }

    public FlyAnimatonAdapter(Context context) {
        super(context);
    }

    public FlyAnimatonAdapter(Context context, OnAnimationItemClick onAnimationItemClick) {
        super(context);
        this.f4318a = onAnimationItemClick;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.coveiot.android.dynamictab.adapters.base.BaseAdapter
    public FlyAnimationHolder getViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        return new FlyAnimationHolder(layoutInflater.inflate(R.layout.fly_list_item, viewGroup, false));
    }
}
