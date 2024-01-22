package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.appcompat.view.CollapsibleActionView;
import androidx.core.view.ViewCompat;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.customview.view.AbsSavedState;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.actions.SearchIntents;
import java.lang.reflect.Method;
import java.util.WeakHashMap;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes.dex */
public class SearchView extends LinearLayoutCompat implements CollapsibleActionView {
    public static final l x0;
    public final ImageView A;
    public final ImageView B;
    public final ImageView C;
    public final ImageView D;
    public final View E;
    public m F;
    public Rect G;
    public Rect H;
    public int[] I;
    public int[] J;
    public final ImageView K;
    public final Drawable L;
    public final int M;
    public final int N;
    public final Intent O;
    public final Intent P;
    public final CharSequence Q;
    public OnQueryTextListener R;
    public OnCloseListener S;
    public View.OnFocusChangeListener T;
    public OnSuggestionListener U;
    public View.OnClickListener V;
    public boolean W;
    public boolean a0;
    public CursorAdapter b0;
    public boolean c0;
    public CharSequence d0;
    public boolean e0;
    public boolean f0;
    public int g0;
    public boolean h0;
    public CharSequence i0;
    public CharSequence j0;
    public boolean k0;
    public int l0;
    public SearchableInfo m0;
    public Bundle n0;
    public final Runnable o0;
    public Runnable p0;
    public final WeakHashMap<String, Drawable.ConstantState> q0;
    public final View.OnClickListener r0;
    public View.OnKeyListener s0;
    public final TextView.OnEditorActionListener t0;
    public final AdapterView.OnItemClickListener u0;
    public final AdapterView.OnItemSelectedListener v0;
    public final SearchAutoComplete w;
    public TextWatcher w0;
    public final View x;
    public final View y;
    public final View z;

    /* loaded from: classes.dex */
    public interface OnCloseListener {
        boolean onClose();
    }

    /* loaded from: classes.dex */
    public interface OnQueryTextListener {
        boolean onQueryTextChange(String str);

        boolean onQueryTextSubmit(String str);
    }

    /* loaded from: classes.dex */
    public interface OnSuggestionListener {
        boolean onSuggestionClick(int i);

        boolean onSuggestionSelect(int i);
    }

    /* loaded from: classes.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public boolean i;

        /* loaded from: classes.dex */
        public class a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* renamed from: b */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: c */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.i + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeValue(Boolean.valueOf(this.i));
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.i = ((Boolean) parcel.readValue(null)).booleanValue();
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public static class SearchAutoComplete extends AppCompatAutoCompleteTextView {
        public int l;
        public SearchView m;
        public boolean n;
        public final Runnable o;

        /* loaded from: classes.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                SearchAutoComplete.this.d();
            }
        }

        public SearchAutoComplete(Context context) {
            this(context, null);
        }

        private int getSearchViewTextMinWidthDp() {
            Configuration configuration = getResources().getConfiguration();
            int i = configuration.screenWidthDp;
            int i2 = configuration.screenHeightDp;
            if (i < 960 || i2 < 720 || configuration.orientation != 2) {
                if (i < 600) {
                    return (i < 640 || i2 < 480) ? 160 : 192;
                }
                return 192;
            }
            return 256;
        }

        public void b() {
            if (Build.VERSION.SDK_INT >= 29) {
                k.b(this, 1);
                if (enoughToFilter()) {
                    showDropDown();
                    return;
                }
                return;
            }
            SearchView.x0.c(this);
        }

        public boolean c() {
            return TextUtils.getTrimmedLength(getText()) == 0;
        }

        public void d() {
            if (this.n) {
                ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this, 0);
                this.n = false;
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public boolean enoughToFilter() {
            return this.l <= 0 || super.enoughToFilter();
        }

        @Override // androidx.appcompat.widget.AppCompatAutoCompleteTextView, android.widget.TextView, android.view.View
        public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.n) {
                removeCallbacks(this.o);
                post(this.o);
            }
            return onCreateInputConnection;
        }

        @Override // android.view.View
        public void onFinishInflate() {
            super.onFinishInflate();
            setMinWidth((int) TypedValue.applyDimension(1, getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onFocusChanged(boolean z, int i, Rect rect) {
            super.onFocusChanged(z, i, rect);
            this.m.N();
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
            if (i == 4) {
                if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                    KeyEvent.DispatcherState keyDispatcherState = getKeyDispatcherState();
                    if (keyDispatcherState != null) {
                        keyDispatcherState.startTracking(keyEvent, this);
                    }
                    return true;
                } else if (keyEvent.getAction() == 1) {
                    KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                    if (keyDispatcherState2 != null) {
                        keyDispatcherState2.handleUpEvent(keyEvent);
                    }
                    if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                        this.m.clearFocus();
                        setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i, keyEvent);
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
            if (z && this.m.hasFocus() && getVisibility() == 0) {
                this.n = true;
                if (SearchView.B(getContext())) {
                    b();
                }
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public void performCompletion() {
        }

        @Override // android.widget.AutoCompleteTextView
        public void replaceText(CharSequence charSequence) {
        }

        public void setImeVisibility(boolean z) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            if (!z) {
                this.n = false;
                removeCallbacks(this.o);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else if (inputMethodManager.isActive(this)) {
                this.n = false;
                removeCallbacks(this.o);
                inputMethodManager.showSoftInput(this, 0);
            } else {
                this.n = true;
            }
        }

        public void setSearchView(SearchView searchView) {
            this.m = searchView;
        }

        @Override // android.widget.AutoCompleteTextView
        public void setThreshold(int i) {
            super.setThreshold(i);
            this.l = i;
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, R.attr.autoCompleteTextViewStyle);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.o = new a();
            this.l = getThreshold();
        }
    }

    /* loaded from: classes.dex */
    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            SearchView.this.M(charSequence);
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SearchView.this.S();
        }
    }

    /* loaded from: classes.dex */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            CursorAdapter cursorAdapter = SearchView.this.b0;
            if (cursorAdapter instanceof r) {
                cursorAdapter.changeCursor(null);
            }
        }
    }

    /* loaded from: classes.dex */
    public class d implements View.OnFocusChangeListener {
        public d() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            SearchView searchView = SearchView.this;
            View.OnFocusChangeListener onFocusChangeListener = searchView.T;
            if (onFocusChangeListener != null) {
                onFocusChangeListener.onFocusChange(searchView, z);
            }
        }
    }

    /* loaded from: classes.dex */
    public class e implements View.OnLayoutChangeListener {
        public e() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            SearchView.this.r();
        }
    }

    /* loaded from: classes.dex */
    public class f implements View.OnClickListener {
        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SearchView searchView = SearchView.this;
            if (view == searchView.A) {
                searchView.J();
            } else if (view == searchView.C) {
                searchView.G();
            } else if (view == searchView.B) {
                searchView.K();
            } else if (view == searchView.D) {
                searchView.O();
            } else if (view == searchView.w) {
                searchView.x();
            }
        }
    }

    /* loaded from: classes.dex */
    public class g implements View.OnKeyListener {
        public g() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            SearchView searchView = SearchView.this;
            if (searchView.m0 == null) {
                return false;
            }
            if (searchView.w.isPopupShowing() && SearchView.this.w.getListSelection() != -1) {
                return SearchView.this.L(view, i, keyEvent);
            }
            if (!SearchView.this.w.c() && keyEvent.hasNoModifiers() && keyEvent.getAction() == 1 && i == 66) {
                view.cancelLongPress();
                SearchView searchView2 = SearchView.this;
                searchView2.E(0, null, searchView2.w.getText().toString());
                return true;
            }
            return false;
        }
    }

    /* loaded from: classes.dex */
    public class h implements TextView.OnEditorActionListener {
        public h() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            SearchView.this.K();
            return true;
        }
    }

    /* loaded from: classes.dex */
    public class i implements AdapterView.OnItemClickListener {
        public i() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            SearchView.this.H(i, 0, null);
        }
    }

    /* loaded from: classes.dex */
    public class j implements AdapterView.OnItemSelectedListener {
        public j() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            SearchView.this.I(i);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    @RequiresApi(29)
    /* loaded from: classes.dex */
    public static class k {
        @DoNotInline
        public static void a(AutoCompleteTextView autoCompleteTextView) {
            autoCompleteTextView.refreshAutoCompleteResults();
        }

        @DoNotInline
        public static void b(SearchAutoComplete searchAutoComplete, int i) {
            searchAutoComplete.setInputMethodMode(i);
        }
    }

    /* loaded from: classes.dex */
    public static class l {

        /* renamed from: a  reason: collision with root package name */
        public Method f452a;
        public Method b;
        public Method c;

        @SuppressLint({"DiscouragedPrivateApi", "SoonBlockedPrivateApi"})
        public l() {
            this.f452a = null;
            this.b = null;
            this.c = null;
            d();
            try {
                Method declaredMethod = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
                this.f452a = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException unused) {
            }
            try {
                Method declaredMethod2 = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
                this.b = declaredMethod2;
                declaredMethod2.setAccessible(true);
            } catch (NoSuchMethodException unused2) {
            }
            try {
                Method method = AutoCompleteTextView.class.getMethod("ensureImeVisible", Boolean.TYPE);
                this.c = method;
                method.setAccessible(true);
            } catch (NoSuchMethodException unused3) {
            }
        }

        public static void d() {
            if (Build.VERSION.SDK_INT >= 29) {
                throw new UnsupportedClassVersionError("This function can only be used for API Level < 29.");
            }
        }

        public void a(AutoCompleteTextView autoCompleteTextView) {
            d();
            Method method = this.b;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }

        public void b(AutoCompleteTextView autoCompleteTextView) {
            d();
            Method method = this.f452a;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }

        public void c(AutoCompleteTextView autoCompleteTextView) {
            d();
            Method method = this.c;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, Boolean.TRUE);
                } catch (Exception unused) {
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static class m extends TouchDelegate {

        /* renamed from: a  reason: collision with root package name */
        public final View f453a;
        public final Rect b;
        public final Rect c;
        public final Rect d;
        public final int e;
        public boolean f;

        public m(Rect rect, Rect rect2, View view) {
            super(rect, view);
            this.e = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
            this.b = new Rect();
            this.d = new Rect();
            this.c = new Rect();
            a(rect, rect2);
            this.f453a = view;
        }

        public void a(Rect rect, Rect rect2) {
            this.b.set(rect);
            this.d.set(rect);
            Rect rect3 = this.d;
            int i = this.e;
            rect3.inset(-i, -i);
            this.c.set(rect2);
        }

        @Override // android.view.TouchDelegate
        public boolean onTouchEvent(MotionEvent motionEvent) {
            boolean z;
            boolean z2;
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            int action = motionEvent.getAction();
            boolean z3 = true;
            if (action != 0) {
                if (action == 1 || action == 2) {
                    z2 = this.f;
                    if (z2 && !this.d.contains(x, y)) {
                        z3 = z2;
                        z = false;
                    }
                } else {
                    if (action == 3) {
                        z2 = this.f;
                        this.f = false;
                    }
                    z = true;
                    z3 = false;
                }
                z3 = z2;
                z = true;
            } else {
                if (this.b.contains(x, y)) {
                    this.f = true;
                    z = true;
                }
                z = true;
                z3 = false;
            }
            if (z3) {
                if (z && !this.c.contains(x, y)) {
                    motionEvent.setLocation(this.f453a.getWidth() / 2, this.f453a.getHeight() / 2);
                } else {
                    Rect rect = this.c;
                    motionEvent.setLocation(x - rect.left, y - rect.top);
                }
                return this.f453a.dispatchTouchEvent(motionEvent);
            }
            return false;
        }
    }

    static {
        x0 = Build.VERSION.SDK_INT < 29 ? new l() : null;
    }

    public SearchView(@NonNull Context context) {
        this(context, null);
    }

    public static boolean B(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    private int getPreferredHeight() {
        return getContext().getResources().getDimensionPixelSize(R.dimen.abc_search_view_preferred_height);
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(R.dimen.abc_search_view_preferred_width);
    }

    public final boolean A() {
        SearchableInfo searchableInfo = this.m0;
        if (searchableInfo == null || !searchableInfo.getVoiceSearchEnabled()) {
            return false;
        }
        Intent intent = null;
        if (this.m0.getVoiceSearchLaunchWebSearch()) {
            intent = this.O;
        } else if (this.m0.getVoiceSearchLaunchRecognizer()) {
            intent = this.P;
        }
        return (intent == null || getContext().getPackageManager().resolveActivity(intent, 65536) == null) ? false : true;
    }

    public final boolean C() {
        return (this.c0 || this.h0) && !isIconified();
    }

    public final void D(Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            getContext().startActivity(intent);
        } catch (RuntimeException e2) {
            Log.e("SearchView", "Failed launch activity: " + intent, e2);
        }
    }

    public void E(int i2, String str, String str2) {
        getContext().startActivity(s("android.intent.action.SEARCH", null, null, str2, i2, str));
    }

    public final boolean F(int i2, int i3, String str) {
        Cursor cursor = this.b0.getCursor();
        if (cursor == null || !cursor.moveToPosition(i2)) {
            return false;
        }
        D(t(cursor, i3, str));
        return true;
    }

    public void G() {
        if (TextUtils.isEmpty(this.w.getText())) {
            if (this.W) {
                OnCloseListener onCloseListener = this.S;
                if (onCloseListener == null || !onCloseListener.onClose()) {
                    clearFocus();
                    X(true);
                    return;
                }
                return;
            }
            return;
        }
        this.w.setText("");
        this.w.requestFocus();
        this.w.setImeVisibility(true);
    }

    public boolean H(int i2, int i3, String str) {
        OnSuggestionListener onSuggestionListener = this.U;
        if (onSuggestionListener == null || !onSuggestionListener.onSuggestionClick(i2)) {
            F(i2, 0, null);
            this.w.setImeVisibility(false);
            w();
            return true;
        }
        return false;
    }

    public boolean I(int i2) {
        OnSuggestionListener onSuggestionListener = this.U;
        if (onSuggestionListener == null || !onSuggestionListener.onSuggestionSelect(i2)) {
            Q(i2);
            return true;
        }
        return false;
    }

    public void J() {
        X(false);
        this.w.requestFocus();
        this.w.setImeVisibility(true);
        View.OnClickListener onClickListener = this.V;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    public void K() {
        Editable text = this.w.getText();
        if (text == null || TextUtils.getTrimmedLength(text) <= 0) {
            return;
        }
        OnQueryTextListener onQueryTextListener = this.R;
        if (onQueryTextListener == null || !onQueryTextListener.onQueryTextSubmit(text.toString())) {
            if (this.m0 != null) {
                E(0, null, text.toString());
            }
            this.w.setImeVisibility(false);
            w();
        }
    }

    public boolean L(View view, int i2, KeyEvent keyEvent) {
        if (this.m0 != null && this.b0 != null && keyEvent.getAction() == 0 && keyEvent.hasNoModifiers()) {
            if (i2 == 66 || i2 == 84 || i2 == 61) {
                return H(this.w.getListSelection(), 0, null);
            }
            if (i2 == 21 || i2 == 22) {
                this.w.setSelection(i2 == 21 ? 0 : this.w.length());
                this.w.setListSelection(0);
                this.w.clearListSelection();
                this.w.b();
                return true;
            } else if (i2 == 19) {
                this.w.getListSelection();
                return false;
            }
        }
        return false;
    }

    public void M(CharSequence charSequence) {
        Editable text = this.w.getText();
        this.j0 = text;
        boolean z = !TextUtils.isEmpty(text);
        W(z);
        Y(!z);
        R();
        V();
        if (this.R != null && !TextUtils.equals(charSequence, this.i0)) {
            this.R.onQueryTextChange(charSequence.toString());
        }
        this.i0 = charSequence.toString();
    }

    public void N() {
        X(isIconified());
        P();
        if (this.w.hasFocus()) {
            x();
        }
    }

    public void O() {
        SearchableInfo searchableInfo = this.m0;
        if (searchableInfo == null) {
            return;
        }
        try {
            if (searchableInfo.getVoiceSearchLaunchWebSearch()) {
                getContext().startActivity(v(this.O, searchableInfo));
            } else if (searchableInfo.getVoiceSearchLaunchRecognizer()) {
                getContext().startActivity(u(this.P, searchableInfo));
            }
        } catch (ActivityNotFoundException unused) {
            Log.w("SearchView", "Could not find voice search activity");
        }
    }

    public final void P() {
        post(this.o0);
    }

    public final void Q(int i2) {
        Editable text = this.w.getText();
        Cursor cursor = this.b0.getCursor();
        if (cursor == null) {
            return;
        }
        if (cursor.moveToPosition(i2)) {
            CharSequence convertToString = this.b0.convertToString(cursor);
            if (convertToString != null) {
                setQuery(convertToString);
                return;
            } else {
                setQuery(text);
                return;
            }
        }
        setQuery(text);
    }

    public final void R() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.w.getText());
        if (!z2 && (!this.W || this.k0)) {
            z = false;
        }
        this.C.setVisibility(z ? 0 : 8);
        Drawable drawable = this.C.getDrawable();
        if (drawable != null) {
            drawable.setState(z2 ? ViewGroup.ENABLED_STATE_SET : ViewGroup.EMPTY_STATE_SET);
        }
    }

    public void S() {
        int[] iArr = this.w.hasFocus() ? ViewGroup.FOCUSED_STATE_SET : ViewGroup.EMPTY_STATE_SET;
        Drawable background = this.y.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        Drawable background2 = this.z.getBackground();
        if (background2 != null) {
            background2.setState(iArr);
        }
        invalidate();
    }

    public final void T() {
        CharSequence queryHint = getQueryHint();
        SearchAutoComplete searchAutoComplete = this.w;
        if (queryHint == null) {
            queryHint = "";
        }
        searchAutoComplete.setHint(z(queryHint));
    }

    public final void U() {
        this.w.setThreshold(this.m0.getSuggestThreshold());
        this.w.setImeOptions(this.m0.getImeOptions());
        int inputType = this.m0.getInputType();
        if ((inputType & 15) == 1) {
            inputType &= -65537;
            if (this.m0.getSuggestAuthority() != null) {
                inputType = inputType | 65536 | 524288;
            }
        }
        this.w.setInputType(inputType);
        CursorAdapter cursorAdapter = this.b0;
        if (cursorAdapter != null) {
            cursorAdapter.changeCursor(null);
        }
        if (this.m0.getSuggestAuthority() != null) {
            r rVar = new r(getContext(), this, this.m0, this.q0);
            this.b0 = rVar;
            this.w.setAdapter(rVar);
            ((r) this.b0).o(this.e0 ? 2 : 1);
        }
    }

    public final void V() {
        this.z.setVisibility((C() && (this.B.getVisibility() == 0 || this.D.getVisibility() == 0)) ? 0 : 8);
    }

    public final void W(boolean z) {
        this.B.setVisibility((this.c0 && C() && hasFocus() && (z || !this.h0)) ? 0 : 8);
    }

    public final void X(boolean z) {
        this.a0 = z;
        int i2 = 0;
        int i3 = z ? 0 : 8;
        boolean z2 = !TextUtils.isEmpty(this.w.getText());
        this.A.setVisibility(i3);
        W(z2);
        this.x.setVisibility(z ? 8 : 0);
        if (this.K.getDrawable() == null || this.W) {
            i2 = 8;
        }
        this.K.setVisibility(i2);
        R();
        Y(!z2);
        V();
    }

    public final void Y(boolean z) {
        int i2 = 8;
        if (this.h0 && !isIconified() && z) {
            this.B.setVisibility(8);
            i2 = 0;
        }
        this.D.setVisibility(i2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void clearFocus() {
        this.f0 = true;
        super.clearFocus();
        this.w.clearFocus();
        this.w.setImeVisibility(false);
        this.f0 = false;
    }

    public int getImeOptions() {
        return this.w.getImeOptions();
    }

    public int getInputType() {
        return this.w.getInputType();
    }

    public int getMaxWidth() {
        return this.g0;
    }

    public CharSequence getQuery() {
        return this.w.getText();
    }

    @Nullable
    public CharSequence getQueryHint() {
        CharSequence charSequence = this.d0;
        if (charSequence != null) {
            return charSequence;
        }
        SearchableInfo searchableInfo = this.m0;
        if (searchableInfo != null && searchableInfo.getHintId() != 0) {
            return getContext().getText(this.m0.getHintId());
        }
        return this.Q;
    }

    public int getSuggestionCommitIconResId() {
        return this.N;
    }

    public int getSuggestionRowLayout() {
        return this.M;
    }

    public CursorAdapter getSuggestionsAdapter() {
        return this.b0;
    }

    public boolean isIconfiedByDefault() {
        return this.W;
    }

    public boolean isIconified() {
        return this.a0;
    }

    public boolean isQueryRefinementEnabled() {
        return this.e0;
    }

    public boolean isSubmitButtonEnabled() {
        return this.c0;
    }

    @Override // androidx.appcompat.view.CollapsibleActionView
    public void onActionViewCollapsed() {
        setQuery("", false);
        clearFocus();
        X(true);
        this.w.setImeOptions(this.l0);
        this.k0 = false;
    }

    @Override // androidx.appcompat.view.CollapsibleActionView
    public void onActionViewExpanded() {
        if (this.k0) {
            return;
        }
        this.k0 = true;
        int imeOptions = this.w.getImeOptions();
        this.l0 = imeOptions;
        this.w.setImeOptions(imeOptions | 33554432);
        this.w.setText("");
        setIconified(false);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        removeCallbacks(this.o0);
        post(this.p0);
        super.onDetachedFromWindow();
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (z) {
            y(this.w, this.G);
            Rect rect = this.H;
            Rect rect2 = this.G;
            rect.set(rect2.left, 0, rect2.right, i5 - i3);
            m mVar = this.F;
            if (mVar == null) {
                m mVar2 = new m(this.H, this.G, this.w);
                this.F = mVar2;
                setTouchDelegate(mVar2);
                return;
            }
            mVar.a(this.H, this.G);
        }
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.View
    public void onMeasure(int i2, int i3) {
        int i4;
        if (isIconified()) {
            super.onMeasure(i2, i3);
            return;
        }
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == Integer.MIN_VALUE) {
            int i5 = this.g0;
            size = i5 > 0 ? Math.min(i5, size) : Math.min(getPreferredWidth(), size);
        } else if (mode == 0) {
            size = this.g0;
            if (size <= 0) {
                size = getPreferredWidth();
            }
        } else if (mode == 1073741824 && (i4 = this.g0) > 0) {
            size = Math.min(i4, size);
        }
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(getPreferredHeight(), size2);
        } else if (mode2 == 0) {
            size2 = getPreferredHeight();
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(size2, 1073741824));
    }

    public void onQueryRefine(@Nullable CharSequence charSequence) {
        setQuery(charSequence);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        X(savedState.i);
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.i = isIconified();
        return savedState;
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        P();
    }

    public void r() {
        int i2;
        if (this.E.getWidth() > 1) {
            Resources resources = getContext().getResources();
            int paddingLeft = this.y.getPaddingLeft();
            Rect rect = new Rect();
            boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
            int dimensionPixelSize = this.W ? resources.getDimensionPixelSize(R.dimen.abc_dropdownitem_icon_width) + resources.getDimensionPixelSize(R.dimen.abc_dropdownitem_text_padding_left) : 0;
            this.w.getDropDownBackground().getPadding(rect);
            if (isLayoutRtl) {
                i2 = -rect.left;
            } else {
                i2 = paddingLeft - (rect.left + dimensionPixelSize);
            }
            this.w.setDropDownHorizontalOffset(i2);
            this.w.setDropDownWidth((((this.E.getWidth() + rect.left) + rect.right) + dimensionPixelSize) - paddingLeft);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean requestFocus(int i2, Rect rect) {
        if (!this.f0 && isFocusable()) {
            if (!isIconified()) {
                boolean requestFocus = this.w.requestFocus(i2, rect);
                if (requestFocus) {
                    X(false);
                }
                return requestFocus;
            }
            return super.requestFocus(i2, rect);
        }
        return false;
    }

    public final Intent s(String str, Uri uri, String str2, String str3, int i2, String str4) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtra("user_query", this.j0);
        if (str3 != null) {
            intent.putExtra(SearchIntents.EXTRA_QUERY, str3);
        }
        if (str2 != null) {
            intent.putExtra("intent_extra_data_key", str2);
        }
        Bundle bundle = this.n0;
        if (bundle != null) {
            intent.putExtra("app_data", bundle);
        }
        if (i2 != 0) {
            intent.putExtra("action_key", i2);
            intent.putExtra("action_msg", str4);
        }
        intent.setComponent(this.m0.getSearchActivity());
        return intent;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void setAppSearchData(Bundle bundle) {
        this.n0 = bundle;
    }

    public void setIconified(boolean z) {
        if (z) {
            G();
        } else {
            J();
        }
    }

    public void setIconifiedByDefault(boolean z) {
        if (this.W == z) {
            return;
        }
        this.W = z;
        X(z);
        T();
    }

    public void setImeOptions(int i2) {
        this.w.setImeOptions(i2);
    }

    public void setInputType(int i2) {
        this.w.setInputType(i2);
    }

    public void setMaxWidth(int i2) {
        this.g0 = i2;
        requestLayout();
    }

    public void setOnCloseListener(OnCloseListener onCloseListener) {
        this.S = onCloseListener;
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.T = onFocusChangeListener;
    }

    public void setOnQueryTextListener(OnQueryTextListener onQueryTextListener) {
        this.R = onQueryTextListener;
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
        this.V = onClickListener;
    }

    public void setOnSuggestionListener(OnSuggestionListener onSuggestionListener) {
        this.U = onSuggestionListener;
    }

    public void setQuery(CharSequence charSequence, boolean z) {
        this.w.setText(charSequence);
        if (charSequence != null) {
            SearchAutoComplete searchAutoComplete = this.w;
            searchAutoComplete.setSelection(searchAutoComplete.length());
            this.j0 = charSequence;
        }
        if (!z || TextUtils.isEmpty(charSequence)) {
            return;
        }
        K();
    }

    public void setQueryHint(@Nullable CharSequence charSequence) {
        this.d0 = charSequence;
        T();
    }

    public void setQueryRefinementEnabled(boolean z) {
        this.e0 = z;
        CursorAdapter cursorAdapter = this.b0;
        if (cursorAdapter instanceof r) {
            ((r) cursorAdapter).o(z ? 2 : 1);
        }
    }

    public void setSearchableInfo(SearchableInfo searchableInfo) {
        this.m0 = searchableInfo;
        if (searchableInfo != null) {
            U();
            T();
        }
        boolean A = A();
        this.h0 = A;
        if (A) {
            this.w.setPrivateImeOptions(Constants.NOTIF_MSG);
        }
        X(isIconified());
    }

    public void setSubmitButtonEnabled(boolean z) {
        this.c0 = z;
        X(isIconified());
    }

    public void setSuggestionsAdapter(CursorAdapter cursorAdapter) {
        this.b0 = cursorAdapter;
        this.w.setAdapter(cursorAdapter);
    }

    public final Intent t(Cursor cursor, int i2, String str) {
        int i3;
        String f2;
        try {
            String f3 = r.f(cursor, "suggest_intent_action");
            if (f3 == null) {
                f3 = this.m0.getSuggestIntentAction();
            }
            if (f3 == null) {
                f3 = "android.intent.action.SEARCH";
            }
            String str2 = f3;
            String f4 = r.f(cursor, "suggest_intent_data");
            if (f4 == null) {
                f4 = this.m0.getSuggestIntentData();
            }
            if (f4 != null && (f2 = r.f(cursor, "suggest_intent_data_id")) != null) {
                f4 = f4 + MqttTopic.TOPIC_LEVEL_SEPARATOR + Uri.encode(f2);
            }
            return s(str2, f4 == null ? null : Uri.parse(f4), r.f(cursor, "suggest_intent_extra_data"), r.f(cursor, "suggest_intent_query"), i2, str);
        } catch (RuntimeException e2) {
            try {
                i3 = cursor.getPosition();
            } catch (RuntimeException unused) {
                i3 = -1;
            }
            Log.w("SearchView", "Search suggestions cursor at row " + i3 + " returned exception.", e2);
            return null;
        }
    }

    public final Intent u(Intent intent, SearchableInfo searchableInfo) {
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        Intent intent2 = new Intent("android.intent.action.SEARCH");
        intent2.setComponent(searchActivity);
        PendingIntent activity = PendingIntent.getActivity(getContext(), 0, intent2, 1107296256);
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.n0;
        if (bundle2 != null) {
            bundle.putParcelable("app_data", bundle2);
        }
        Intent intent3 = new Intent(intent);
        Resources resources = getResources();
        String string = searchableInfo.getVoiceLanguageModeId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageModeId()) : "free_form";
        String string2 = searchableInfo.getVoicePromptTextId() != 0 ? resources.getString(searchableInfo.getVoicePromptTextId()) : null;
        String string3 = searchableInfo.getVoiceLanguageId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageId()) : null;
        int voiceMaxResults = searchableInfo.getVoiceMaxResults() != 0 ? searchableInfo.getVoiceMaxResults() : 1;
        intent3.putExtra("android.speech.extra.LANGUAGE_MODEL", string);
        intent3.putExtra("android.speech.extra.PROMPT", string2);
        intent3.putExtra("android.speech.extra.LANGUAGE", string3);
        intent3.putExtra("android.speech.extra.MAX_RESULTS", voiceMaxResults);
        intent3.putExtra("calling_package", searchActivity != null ? searchActivity.flattenToShortString() : null);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", activity);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", bundle);
        return intent3;
    }

    public final Intent v(Intent intent, SearchableInfo searchableInfo) {
        Intent intent2 = new Intent(intent);
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        intent2.putExtra("calling_package", searchActivity == null ? null : searchActivity.flattenToShortString());
        return intent2;
    }

    public final void w() {
        this.w.dismissDropDown();
    }

    public void x() {
        if (Build.VERSION.SDK_INT >= 29) {
            k.a(this.w);
            return;
        }
        l lVar = x0;
        lVar.b(this.w);
        lVar.a(this.w);
    }

    public final void y(View view, Rect rect) {
        view.getLocationInWindow(this.I);
        getLocationInWindow(this.J);
        int[] iArr = this.I;
        int i2 = iArr[1];
        int[] iArr2 = this.J;
        int i3 = i2 - iArr2[1];
        int i4 = iArr[0] - iArr2[0];
        rect.set(i4, i3, view.getWidth() + i4, view.getHeight() + i3);
    }

    public final CharSequence z(CharSequence charSequence) {
        if (!this.W || this.L == null) {
            return charSequence;
        }
        int textSize = (int) (this.w.getTextSize() * 1.25d);
        this.L.setBounds(0, 0, textSize, textSize);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
        spannableStringBuilder.setSpan(new ImageSpan(this.L), 1, 2, 33);
        spannableStringBuilder.append(charSequence);
        return spannableStringBuilder;
    }

    public SearchView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.searchViewStyle);
    }

    public SearchView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.G = new Rect();
        this.H = new Rect();
        this.I = new int[2];
        this.J = new int[2];
        this.o0 = new b();
        this.p0 = new c();
        this.q0 = new WeakHashMap<>();
        f fVar = new f();
        this.r0 = fVar;
        this.s0 = new g();
        h hVar = new h();
        this.t0 = hVar;
        i iVar = new i();
        this.u0 = iVar;
        j jVar = new j();
        this.v0 = jVar;
        this.w0 = new a();
        int[] iArr = R.styleable.SearchView;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, iArr, i2, 0);
        ViewCompat.saveAttributeDataForStyleable(this, context, iArr, attributeSet, obtainStyledAttributes.getWrappedTypeArray(), i2, 0);
        LayoutInflater.from(context).inflate(obtainStyledAttributes.getResourceId(R.styleable.SearchView_layout, R.layout.abc_search_view), (ViewGroup) this, true);
        SearchAutoComplete searchAutoComplete = (SearchAutoComplete) findViewById(R.id.search_src_text);
        this.w = searchAutoComplete;
        searchAutoComplete.setSearchView(this);
        this.x = findViewById(R.id.search_edit_frame);
        View findViewById = findViewById(R.id.search_plate);
        this.y = findViewById;
        View findViewById2 = findViewById(R.id.submit_area);
        this.z = findViewById2;
        ImageView imageView = (ImageView) findViewById(R.id.search_button);
        this.A = imageView;
        ImageView imageView2 = (ImageView) findViewById(R.id.search_go_btn);
        this.B = imageView2;
        ImageView imageView3 = (ImageView) findViewById(R.id.search_close_btn);
        this.C = imageView3;
        ImageView imageView4 = (ImageView) findViewById(R.id.search_voice_btn);
        this.D = imageView4;
        ImageView imageView5 = (ImageView) findViewById(R.id.search_mag_icon);
        this.K = imageView5;
        ViewCompat.setBackground(findViewById, obtainStyledAttributes.getDrawable(R.styleable.SearchView_queryBackground));
        ViewCompat.setBackground(findViewById2, obtainStyledAttributes.getDrawable(R.styleable.SearchView_submitBackground));
        int i3 = R.styleable.SearchView_searchIcon;
        imageView.setImageDrawable(obtainStyledAttributes.getDrawable(i3));
        imageView2.setImageDrawable(obtainStyledAttributes.getDrawable(R.styleable.SearchView_goIcon));
        imageView3.setImageDrawable(obtainStyledAttributes.getDrawable(R.styleable.SearchView_closeIcon));
        imageView4.setImageDrawable(obtainStyledAttributes.getDrawable(R.styleable.SearchView_voiceIcon));
        imageView5.setImageDrawable(obtainStyledAttributes.getDrawable(i3));
        this.L = obtainStyledAttributes.getDrawable(R.styleable.SearchView_searchHintIcon);
        TooltipCompat.setTooltipText(imageView, getResources().getString(R.string.abc_searchview_description_search));
        this.M = obtainStyledAttributes.getResourceId(R.styleable.SearchView_suggestionRowLayout, R.layout.abc_search_dropdown_item_icons_2line);
        this.N = obtainStyledAttributes.getResourceId(R.styleable.SearchView_commitIcon, 0);
        imageView.setOnClickListener(fVar);
        imageView3.setOnClickListener(fVar);
        imageView2.setOnClickListener(fVar);
        imageView4.setOnClickListener(fVar);
        searchAutoComplete.setOnClickListener(fVar);
        searchAutoComplete.addTextChangedListener(this.w0);
        searchAutoComplete.setOnEditorActionListener(hVar);
        searchAutoComplete.setOnItemClickListener(iVar);
        searchAutoComplete.setOnItemSelectedListener(jVar);
        searchAutoComplete.setOnKeyListener(this.s0);
        searchAutoComplete.setOnFocusChangeListener(new d());
        setIconifiedByDefault(obtainStyledAttributes.getBoolean(R.styleable.SearchView_iconifiedByDefault, true));
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SearchView_android_maxWidth, -1);
        if (dimensionPixelSize != -1) {
            setMaxWidth(dimensionPixelSize);
        }
        this.Q = obtainStyledAttributes.getText(R.styleable.SearchView_defaultQueryHint);
        this.d0 = obtainStyledAttributes.getText(R.styleable.SearchView_queryHint);
        int i4 = obtainStyledAttributes.getInt(R.styleable.SearchView_android_imeOptions, -1);
        if (i4 != -1) {
            setImeOptions(i4);
        }
        int i5 = obtainStyledAttributes.getInt(R.styleable.SearchView_android_inputType, -1);
        if (i5 != -1) {
            setInputType(i5);
        }
        setFocusable(obtainStyledAttributes.getBoolean(R.styleable.SearchView_android_focusable, true));
        obtainStyledAttributes.recycle();
        Intent intent = new Intent("android.speech.action.WEB_SEARCH");
        this.O = intent;
        intent.addFlags(268435456);
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        Intent intent2 = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.P = intent2;
        intent2.addFlags(268435456);
        View findViewById3 = findViewById(searchAutoComplete.getDropDownAnchor());
        this.E = findViewById3;
        if (findViewById3 != null) {
            findViewById3.addOnLayoutChangeListener(new e());
        }
        X(this.W);
        T();
    }

    private void setQuery(CharSequence charSequence) {
        this.w.setText(charSequence);
        this.w.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
    }
}
