package com.mappls.sdk.plugins.places.autocomplete.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.plugins.places.R;
import com.mappls.sdk.plugins.places.autocomplete.model.d;
import java.util.ArrayList;
import java.util.List;
@Keep
/* loaded from: classes10.dex */
public class ResultView extends LinearLayout {
    private b adapter;
    private final List<d> results;

    public ResultView(@NonNull Context context) {
        this(context, null);
    }

    public ResultView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public ResultView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.results = new ArrayList();
        initialize(context);
    }

    private void initialize(Context context) {
        inflateView(context);
        this.adapter = new b(getContext(), this.results);
    }

    private void initializeResultList() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_search_results);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.addItemDecoration(new a(getContext()));
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(this.adapter);
    }

    public List<d> getResultsList() {
        return this.results;
    }

    public void inflateView(Context context) {
        View.inflate(context, R.layout.mappls_search_view_results, this);
    }

    public void notifyDataSetChanged() {
        this.adapter.notifyDataSetChanged();
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        initializeResultList();
    }

    public void setOnItemClickListener(ResultClickCallback resultClickCallback) {
        b bVar = this.adapter;
        if (bVar != null) {
            bVar.a(resultClickCallback);
        }
    }
}
