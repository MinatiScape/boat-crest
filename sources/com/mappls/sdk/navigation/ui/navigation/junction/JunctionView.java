package com.mappls.sdk.navigation.ui.navigation.junction;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import androidx.annotation.Keep;
import com.mappls.sdk.navigation.MapplsNavigationHelper;
import com.mappls.sdk.navigation.NavigationFormatter;
import com.mappls.sdk.navigation.iface.JunctionInfoChangedListener;
import com.mappls.sdk.navigation.model.Junction;
import com.mappls.sdk.navigation.ui.databinding.LayoutJunctionViewBinding;
import com.mappls.sdk.navigation.ui.navigation.MapplsNavigationViewHelper;
@Keep
/* loaded from: classes11.dex */
public class JunctionView extends RelativeLayout {
    public LayoutJunctionViewBinding mBinding;

    /* loaded from: classes11.dex */
    public class a implements JunctionInfoChangedListener {
        public a() {
        }

        @Override // com.mappls.sdk.navigation.iface.JunctionInfoChangedListener
        public void junctionInfoChanged(Junction junction) {
            JunctionView junctionView;
            int i;
            Bitmap bitmap;
            if (junction == null || (bitmap = junction.bitmap) == null) {
                junctionView = JunctionView.this;
                i = 4;
            } else {
                JunctionView.this.mBinding.ivJunctionImage.setImageBitmap(bitmap);
                JunctionView.this.mBinding.tvJunctionLeftDistance.setText(NavigationFormatter.getFormattedDistance((float) junction.getLeftDistance(), MapplsNavigationViewHelper.getInstance().getApplication()));
                junctionView = JunctionView.this;
                i = 0;
            }
            junctionView.setVisibility(i);
        }
    }

    public JunctionView(Context context) {
        super(context);
        this.mBinding = LayoutJunctionViewBinding.inflate(LayoutInflater.from(getContext()), this, true);
    }

    public JunctionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mBinding = LayoutJunctionViewBinding.inflate(LayoutInflater.from(getContext()), this, true);
    }

    public JunctionView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBinding = LayoutJunctionViewBinding.inflate(LayoutInflater.from(getContext()), this, true);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setVisibility(4);
        MapplsNavigationHelper.getInstance().setJunctionInfoChangedListener(new a());
    }
}
