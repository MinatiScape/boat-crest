package com.mappls.sdk.plugins.places.autocomplete.ui;

import android.content.Context;
import android.graphics.PorterDuff;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.mappls.sdk.plugins.places.R;
@Keep
/* loaded from: classes10.dex */
public class SearchView extends LinearLayout implements View.OnClickListener, TextWatcher, TextView.OnEditorActionListener, LifecycleObserver {
    private final ImageView backButton;
    @Nullable
    private a backButtonListener;
    private final ImageView clearButton;
    private final ProgressBar progressBar;
    @Nullable
    private b queryListener;
    @Nullable
    private c searchClickListener;
    private final EditText searchEditText;

    /* loaded from: classes10.dex */
    public interface a {
        void onBackButtonPress();
    }

    /* loaded from: classes10.dex */
    public interface b {
        void onQueryChange(CharSequence charSequence);
    }

    /* loaded from: classes10.dex */
    public interface c {
        void onSearchClick(String str);
    }

    public SearchView(@NonNull Context context) {
        this(context, null);
    }

    public SearchView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public SearchView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View.inflate(context, R.layout.mappls_search_view_search, this);
        this.backButton = (ImageView) findViewById(R.id.button_search_back);
        this.clearButton = (ImageView) findViewById(R.id.button_search_clear);
        this.searchEditText = (EditText) findViewById(R.id.edittext_search);
        this.progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        initialize();
    }

    private void initialize() {
        this.backButton.setOnClickListener(this);
        this.clearButton.setOnClickListener(this);
        this.searchEditText.addTextChangedListener(this);
        this.searchEditText.setOnEditorActionListener(this);
        this.searchEditText.requestFocus();
        ((InputMethodManager) getContext().getSystemService("input_method")).toggleSoftInput(2, 1);
        ((LifecycleOwner) getContext()).getLifecycle().addObserver(this);
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void hideProgress() {
        this.progressBar.setVisibility(4);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.searchEditText.requestFocus();
        com.mappls.sdk.plugins.places.common.utils.c.a(this.searchEditText);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.button_search_back) {
            this.searchEditText.getText().clear();
            return;
        }
        a aVar = this.backButtonListener;
        if (aVar != null) {
            aVar.onBackButtonPress();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        this.backButtonListener = null;
        this.queryListener = null;
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        c cVar;
        if (i != 3 || (cVar = this.searchClickListener) == null) {
            return false;
        }
        cVar.onSearchClick(this.searchEditText.getText().toString());
        return false;
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        b bVar = this.queryListener;
        if (bVar != null) {
            bVar.onQueryChange(charSequence);
        }
        this.clearButton.setVisibility(charSequence.length() > 0 ? 0 : 4);
    }

    public void setBackButtonListener(@Nullable a aVar) {
        this.backButtonListener = aVar;
    }

    public void setHint(String str) {
        this.searchEditText.setHint(str);
    }

    public void setOnSearchClick(c cVar) {
        this.searchClickListener = cVar;
        this.searchEditText.setImeOptions(3);
    }

    public void setQueryListener(@Nullable b bVar) {
        this.queryListener = bVar;
    }

    public void setText(String str) {
        this.searchEditText.setText(str);
    }

    public void setTintColor(int i) {
        this.searchEditText.setTextColor(i);
        this.backButton.setColorFilter(i, PorterDuff.Mode.SRC_IN);
        this.clearButton.setColorFilter(i, PorterDuff.Mode.SRC_IN);
    }

    public void showProgress() {
        this.progressBar.setVisibility(0);
    }
}
