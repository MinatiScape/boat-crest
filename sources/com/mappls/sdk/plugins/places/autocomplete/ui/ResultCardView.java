package com.mappls.sdk.plugins.places.autocomplete.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.plugins.places.R;
@Keep
/* loaded from: classes10.dex */
public class ResultCardView extends ResultView {
    public ResultCardView(@NonNull Context context) {
        this(context, null);
    }

    public ResultCardView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public ResultCardView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.ui.ResultView
    public void inflateView(Context context) {
        View.inflate(context, R.layout.mappls_search_view_card_results, this);
    }
}
