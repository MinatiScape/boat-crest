package androidx.databinding.adapters;

import android.annotation.TargetApi;
import android.os.Build;
import android.widget.SearchView;
import androidx.annotation.RestrictTo;
import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;
@BindingMethods({@BindingMethod(attribute = "android:onQueryTextFocusChange", method = "setOnQueryTextFocusChangeListener", type = SearchView.class), @BindingMethod(attribute = "android:onSearchClick", method = "setOnSearchClickListener", type = SearchView.class), @BindingMethod(attribute = "android:onClose", method = "setOnCloseListener", type = SearchView.class)})
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class SearchViewBindingAdapter {

    @TargetApi(11)
    /* loaded from: classes.dex */
    public interface OnQueryTextChange {
        boolean onQueryTextChange(String str);
    }

    @TargetApi(11)
    /* loaded from: classes.dex */
    public interface OnQueryTextSubmit {
        boolean onQueryTextSubmit(String str);
    }

    @TargetApi(11)
    /* loaded from: classes.dex */
    public interface OnSuggestionClick {
        boolean onSuggestionClick(int i);
    }

    @TargetApi(11)
    /* loaded from: classes.dex */
    public interface OnSuggestionSelect {
        boolean onSuggestionSelect(int i);
    }

    /* loaded from: classes.dex */
    public class a implements SearchView.OnQueryTextListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnQueryTextSubmit f1228a;
        public final /* synthetic */ OnQueryTextChange b;

        public a(OnQueryTextSubmit onQueryTextSubmit, OnQueryTextChange onQueryTextChange) {
            this.f1228a = onQueryTextSubmit;
            this.b = onQueryTextChange;
        }

        @Override // android.widget.SearchView.OnQueryTextListener
        public boolean onQueryTextChange(String str) {
            OnQueryTextChange onQueryTextChange = this.b;
            if (onQueryTextChange != null) {
                return onQueryTextChange.onQueryTextChange(str);
            }
            return false;
        }

        @Override // android.widget.SearchView.OnQueryTextListener
        public boolean onQueryTextSubmit(String str) {
            OnQueryTextSubmit onQueryTextSubmit = this.f1228a;
            if (onQueryTextSubmit != null) {
                return onQueryTextSubmit.onQueryTextSubmit(str);
            }
            return false;
        }
    }

    /* loaded from: classes.dex */
    public class b implements SearchView.OnSuggestionListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnSuggestionSelect f1229a;
        public final /* synthetic */ OnSuggestionClick b;

        public b(OnSuggestionSelect onSuggestionSelect, OnSuggestionClick onSuggestionClick) {
            this.f1229a = onSuggestionSelect;
            this.b = onSuggestionClick;
        }

        @Override // android.widget.SearchView.OnSuggestionListener
        public boolean onSuggestionClick(int i) {
            OnSuggestionClick onSuggestionClick = this.b;
            if (onSuggestionClick != null) {
                return onSuggestionClick.onSuggestionClick(i);
            }
            return false;
        }

        @Override // android.widget.SearchView.OnSuggestionListener
        public boolean onSuggestionSelect(int i) {
            OnSuggestionSelect onSuggestionSelect = this.f1229a;
            if (onSuggestionSelect != null) {
                return onSuggestionSelect.onSuggestionSelect(i);
            }
            return false;
        }
    }

    @BindingAdapter(requireAll = false, value = {"android:onQueryTextSubmit", "android:onQueryTextChange"})
    public static void setOnQueryTextListener(SearchView searchView, OnQueryTextSubmit onQueryTextSubmit, OnQueryTextChange onQueryTextChange) {
        if (Build.VERSION.SDK_INT >= 11) {
            if (onQueryTextSubmit == null && onQueryTextChange == null) {
                searchView.setOnQueryTextListener(null);
            } else {
                searchView.setOnQueryTextListener(new a(onQueryTextSubmit, onQueryTextChange));
            }
        }
    }

    @BindingAdapter(requireAll = false, value = {"android:onSuggestionSelect", "android:onSuggestionClick"})
    public static void setOnSuggestListener(SearchView searchView, OnSuggestionSelect onSuggestionSelect, OnSuggestionClick onSuggestionClick) {
        if (Build.VERSION.SDK_INT >= 11) {
            if (onSuggestionSelect == null && onSuggestionClick == null) {
                searchView.setOnSuggestionListener(null);
            } else {
                searchView.setOnSuggestionListener(new b(onSuggestionSelect, onSuggestionClick));
            }
        }
    }
}
