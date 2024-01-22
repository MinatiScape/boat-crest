package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public class ListFragment extends Fragment {
    public final Handler h = new Handler();
    public final Runnable i = new a();
    public final AdapterView.OnItemClickListener j = new b();
    public ListAdapter k;
    public ListView l;
    public View m;
    public TextView n;
    public View o;
    public View p;
    public CharSequence q;
    public boolean r;

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ListView listView = ListFragment.this.l;
            listView.focusableViewAvailable(listView);
        }
    }

    /* loaded from: classes.dex */
    public class b implements AdapterView.OnItemClickListener {
        public b() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            ListFragment.this.onListItemClick((ListView) adapterView, view, i, j);
        }
    }

    public final void b() {
        if (this.l != null) {
            return;
        }
        View view = getView();
        if (view != null) {
            if (view instanceof ListView) {
                this.l = (ListView) view;
            } else {
                TextView textView = (TextView) view.findViewById(16711681);
                this.n = textView;
                if (textView == null) {
                    this.m = view.findViewById(16908292);
                } else {
                    textView.setVisibility(8);
                }
                this.o = view.findViewById(16711682);
                this.p = view.findViewById(16711683);
                View findViewById = view.findViewById(16908298);
                if (!(findViewById instanceof ListView)) {
                    if (findViewById == null) {
                        throw new RuntimeException("Your content must have a ListView whose id attribute is 'android.R.id.list'");
                    }
                    throw new RuntimeException("Content has view with id attribute 'android.R.id.list' that is not a ListView class");
                }
                ListView listView = (ListView) findViewById;
                this.l = listView;
                View view2 = this.m;
                if (view2 != null) {
                    listView.setEmptyView(view2);
                } else {
                    CharSequence charSequence = this.q;
                    if (charSequence != null) {
                        this.n.setText(charSequence);
                        this.l.setEmptyView(this.n);
                    }
                }
            }
            this.r = true;
            this.l.setOnItemClickListener(this.j);
            ListAdapter listAdapter = this.k;
            if (listAdapter != null) {
                this.k = null;
                setListAdapter(listAdapter);
            } else if (this.o != null) {
                c(false, false);
            }
            this.h.post(this.i);
            return;
        }
        throw new IllegalStateException("Content view not yet created");
    }

    public final void c(boolean z, boolean z2) {
        b();
        View view = this.o;
        if (view != null) {
            if (this.r == z) {
                return;
            }
            this.r = z;
            if (z) {
                if (z2) {
                    view.startAnimation(AnimationUtils.loadAnimation(getContext(), 17432577));
                    this.p.startAnimation(AnimationUtils.loadAnimation(getContext(), 17432576));
                } else {
                    view.clearAnimation();
                    this.p.clearAnimation();
                }
                this.o.setVisibility(8);
                this.p.setVisibility(0);
                return;
            }
            if (z2) {
                view.startAnimation(AnimationUtils.loadAnimation(getContext(), 17432576));
                this.p.startAnimation(AnimationUtils.loadAnimation(getContext(), 17432577));
            } else {
                view.clearAnimation();
                this.p.clearAnimation();
            }
            this.o.setVisibility(0);
            this.p.setVisibility(8);
            return;
        }
        throw new IllegalStateException("Can't be used with a custom content view");
    }

    @Nullable
    public ListAdapter getListAdapter() {
        return this.k;
    }

    @NonNull
    public ListView getListView() {
        b();
        return this.l;
    }

    public long getSelectedItemId() {
        b();
        return this.l.getSelectedItemId();
    }

    public int getSelectedItemPosition() {
        b();
        return this.l.getSelectedItemPosition();
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Context requireContext = requireContext();
        FrameLayout frameLayout = new FrameLayout(requireContext);
        LinearLayout linearLayout = new LinearLayout(requireContext);
        linearLayout.setId(16711682);
        linearLayout.setOrientation(1);
        linearLayout.setVisibility(8);
        linearLayout.setGravity(17);
        linearLayout.addView(new ProgressBar(requireContext, null, 16842874), new FrameLayout.LayoutParams(-2, -2));
        frameLayout.addView(linearLayout, new FrameLayout.LayoutParams(-1, -1));
        FrameLayout frameLayout2 = new FrameLayout(requireContext);
        frameLayout2.setId(16711683);
        TextView textView = new TextView(requireContext);
        textView.setId(16711681);
        textView.setGravity(17);
        frameLayout2.addView(textView, new FrameLayout.LayoutParams(-1, -1));
        ListView listView = new ListView(requireContext);
        listView.setId(16908298);
        listView.setDrawSelectorOnTop(false);
        frameLayout2.addView(listView, new FrameLayout.LayoutParams(-1, -1));
        frameLayout.addView(frameLayout2, new FrameLayout.LayoutParams(-1, -1));
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        return frameLayout;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.h.removeCallbacks(this.i);
        this.l = null;
        this.r = false;
        this.p = null;
        this.o = null;
        this.m = null;
        this.n = null;
        super.onDestroyView();
    }

    public void onListItemClick(@NonNull ListView listView, @NonNull View view, int i, long j) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        b();
    }

    @NonNull
    public final ListAdapter requireListAdapter() {
        ListAdapter listAdapter = getListAdapter();
        if (listAdapter != null) {
            return listAdapter;
        }
        throw new IllegalStateException("ListFragment " + this + " does not have a ListAdapter.");
    }

    public void setEmptyText(@Nullable CharSequence charSequence) {
        b();
        TextView textView = this.n;
        if (textView != null) {
            textView.setText(charSequence);
            if (this.q == null) {
                this.l.setEmptyView(this.n);
            }
            this.q = charSequence;
            return;
        }
        throw new IllegalStateException("Can't be used with a custom content view");
    }

    public void setListAdapter(@Nullable ListAdapter listAdapter) {
        boolean z = this.k != null;
        this.k = listAdapter;
        ListView listView = this.l;
        if (listView != null) {
            listView.setAdapter(listAdapter);
            if (this.r || z) {
                return;
            }
            c(true, requireView().getWindowToken() != null);
        }
    }

    public void setListShown(boolean z) {
        c(z, true);
    }

    public void setListShownNoAnimation(boolean z) {
        c(z, false);
    }

    public void setSelection(int i) {
        b();
        this.l.setSelection(i);
    }
}
