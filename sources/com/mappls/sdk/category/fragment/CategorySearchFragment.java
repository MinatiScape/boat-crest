package com.mappls.sdk.category.fragment;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Keep;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.mappls.sdk.category.R;
import com.mappls.sdk.category.adapters.a;
import com.mappls.sdk.category.databinding.MapplsCategorySearchItemFragmentBinding;
import com.mappls.sdk.category.model.SearchCategoryUIOption;
import com.mappls.sdk.nearby.plugin.CategoryCode;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Keep
@SourceDebugExtension({"SMAP\nCategorySearchFragment.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CategorySearchFragment.kt\ncom/mappls/sdk/category/fragment/CategorySearchFragment\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,204:1\n1#2:205\n*E\n"})
/* loaded from: classes11.dex */
public final class CategorySearchFragment extends Fragment {
    @NotNull
    public static final a Companion = new a(0);
    private MapplsCategorySearchItemFragmentBinding binding;
    private ImageView cancelView;
    private com.mappls.sdk.category.adapters.a categorySearchAdapter;
    @Nullable
    private ICategorySelectionListener listener;
    private com.mappls.sdk.category.viewmodel.b mViewModel;

    /* loaded from: classes11.dex */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(int i) {
            this();
        }

        @JvmStatic
        @NotNull
        public static CategorySearchFragment a() {
            SearchCategoryUIOption build = SearchCategoryUIOption.builder().build();
            Intrinsics.checkNotNullExpressionValue(build, "builder().build()");
            return a(build);
        }

        @JvmStatic
        @NotNull
        public static CategorySearchFragment a(@NotNull SearchCategoryUIOption options) {
            Intrinsics.checkNotNullParameter(options, "options");
            CategorySearchFragment categorySearchFragment = new CategorySearchFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("com.mappls.sdk.category.CATEGORY_UI_OPTION", options);
            categorySearchFragment.setArguments(bundle);
            return categorySearchFragment;
        }
    }

    /* loaded from: classes11.dex */
    public static final class b implements a.InterfaceC0609a {
        public b() {
        }

        @Override // com.mappls.sdk.category.adapters.a.InterfaceC0609a
        public final void a(@NotNull List<? extends CategoryCode> list) {
            Intrinsics.checkNotNullParameter(list, "list");
            if (!(!list.isEmpty())) {
                Toast.makeText(CategorySearchFragment.this.getContext(), "Please select at least one category", 0).show();
                return;
            }
            ICategorySelectionListener iCategorySelectionListener = CategorySearchFragment.this.listener;
            if (iCategorySelectionListener != null) {
                iCategorySelectionListener.onCategorySelected(list);
            }
        }
    }

    /* loaded from: classes11.dex */
    public static final class c implements TextWatcher {
        public c() {
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(@Nullable Editable editable) {
        }

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
            MapplsCategorySearchItemFragmentBinding mapplsCategorySearchItemFragmentBinding = CategorySearchFragment.this.binding;
            com.mappls.sdk.category.adapters.a aVar = null;
            if (mapplsCategorySearchItemFragmentBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                mapplsCategorySearchItemFragmentBinding = null;
            }
            String obj = ((EditText) mapplsCategorySearchItemFragmentBinding.mapplsCategoryAppbar.findViewById(R.id.mappls_category_search_input)).getText().toString();
            com.mappls.sdk.category.adapters.a aVar2 = CategorySearchFragment.this.categorySearchAdapter;
            if (aVar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("categorySearchAdapter");
            } else {
                aVar = aVar2;
            }
            aVar.getFilter().filter(obj);
        }
    }

    private final void initCategory() {
        MapplsCategorySearchItemFragmentBinding mapplsCategorySearchItemFragmentBinding = this.binding;
        com.mappls.sdk.category.adapters.a aVar = null;
        if (mapplsCategorySearchItemFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            mapplsCategorySearchItemFragmentBinding = null;
        }
        RecyclerView recyclerView = mapplsCategorySearchItemFragmentBinding.mapplsCategoryCategoryRecyclerView;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "binding.mapplsCategoryCategoryRecyclerView");
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        if (getContext() != null) {
            com.mappls.sdk.category.viewmodel.b bVar = this.mViewModel;
            if (bVar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar = null;
            }
            Integer maxSelectionCount = bVar.b().maxSelectionCount();
            Intrinsics.checkNotNullExpressionValue(maxSelectionCount, "mViewModel.options.maxSelectionCount()");
            int intValue = maxSelectionCount.intValue();
            com.mappls.sdk.category.viewmodel.b bVar2 = this.mViewModel;
            if (bVar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar2 = null;
            }
            this.categorySearchAdapter = new com.mappls.sdk.category.adapters.a(intValue, bVar2.c(), new b());
        }
        com.mappls.sdk.category.adapters.a aVar2 = this.categorySearchAdapter;
        if (aVar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("categorySearchAdapter");
            aVar2 = null;
        }
        recyclerView.setAdapter(aVar2);
        com.mappls.sdk.category.viewmodel.b bVar3 = this.mViewModel;
        if (bVar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar3 = null;
        }
        List<CategoryCode> a2 = bVar3.a();
        if (a2 != null) {
            com.mappls.sdk.category.adapters.a aVar3 = this.categorySearchAdapter;
            if (aVar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("categorySearchAdapter");
            } else {
                aVar = aVar3;
            }
            aVar.a(new ArrayList(a2));
        }
    }

    @JvmStatic
    @NotNull
    public static final CategorySearchFragment newInstance() {
        Companion.getClass();
        return a.a();
    }

    @JvmStatic
    @NotNull
    public static final CategorySearchFragment newInstance(@NotNull SearchCategoryUIOption searchCategoryUIOption) {
        Companion.getClass();
        return a.a(searchCategoryUIOption);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$1(CategorySearchFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MapplsCategorySearchItemFragmentBinding mapplsCategorySearchItemFragmentBinding = this$0.binding;
        if (mapplsCategorySearchItemFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            mapplsCategorySearchItemFragmentBinding = null;
        }
        ((EditText) mapplsCategorySearchItemFragmentBinding.mapplsCategoryAppbar.findViewById(R.id.mappls_category_search_input)).setText("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$2(CategorySearchFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ICategorySelectionListener iCategorySelectionListener = this$0.listener;
        if (iCategorySelectionListener != null) {
            iCategorySelectionListener.onCancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$3(CategorySearchFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        com.mappls.sdk.category.adapters.a aVar = this$0.categorySearchAdapter;
        com.mappls.sdk.category.viewmodel.b bVar = null;
        if (aVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("categorySearchAdapter");
            aVar = null;
        }
        ArrayList a2 = aVar.a();
        int size = a2.size();
        com.mappls.sdk.category.viewmodel.b bVar2 = this$0.mViewModel;
        if (bVar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar2 = null;
        }
        Integer maxSelectionCount = bVar2.b().maxSelectionCount();
        Intrinsics.checkNotNullExpressionValue(maxSelectionCount, "mViewModel.options.maxSelectionCount()");
        if (size <= maxSelectionCount.intValue()) {
            if (!(!a2.isEmpty())) {
                Toast.makeText(this$0.getContext(), "Please select at least one category", 0).show();
                return;
            }
            ICategorySelectionListener iCategorySelectionListener = this$0.listener;
            if (iCategorySelectionListener != null) {
                iCategorySelectionListener.onCategorySelected(a2);
                return;
            }
            return;
        }
        Context context = this$0.getContext();
        StringBuilder sb = new StringBuilder();
        sb.append("Max ");
        com.mappls.sdk.category.viewmodel.b bVar3 = this$0.mViewModel;
        if (bVar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
        } else {
            bVar = bVar3;
        }
        sb.append(bVar.b().maxSelectionCount().intValue());
        sb.append(" categories are allowed");
        Toast.makeText(context, sb.toString(), 0).show();
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        MapplsCategorySearchItemFragmentBinding inflate = MapplsCategorySearchItemFragmentBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, container, false)");
        this.binding = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        View root = inflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        com.mappls.sdk.category.utils.b.a(requireActivity);
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NotNull Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        com.mappls.sdk.category.viewmodel.b bVar = this.mViewModel;
        if (bVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar = null;
        }
        outState.putParcelable("com.mappls.sdk.category.CATEGORY_UI_OPTION", bVar.c());
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        TextView textView;
        int i;
        Bundle arguments;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        com.mappls.sdk.category.viewmodel.b bVar = (com.mappls.sdk.category.viewmodel.b) new ViewModelProvider(this).get(com.mappls.sdk.category.viewmodel.b.class);
        this.mViewModel = bVar;
        MapplsCategorySearchItemFragmentBinding mapplsCategorySearchItemFragmentBinding = null;
        if (bVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar = null;
        }
        bVar.a(com.mappls.sdk.category.a.b());
        if (bundle == null && (arguments = getArguments()) != null) {
            com.mappls.sdk.category.viewmodel.b bVar2 = this.mViewModel;
            if (bVar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar2 = null;
            }
            SearchCategoryUIOption searchCategoryUIOption = (SearchCategoryUIOption) arguments.getParcelable("com.mappls.sdk.category.CATEGORY_UI_OPTION");
            if (searchCategoryUIOption == null) {
                searchCategoryUIOption = com.mappls.sdk.category.a.d();
            }
            bVar2.a(searchCategoryUIOption);
            arguments.clear();
        }
        initCategory();
        MapplsCategorySearchItemFragmentBinding mapplsCategorySearchItemFragmentBinding2 = this.binding;
        if (mapplsCategorySearchItemFragmentBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            mapplsCategorySearchItemFragmentBinding2 = null;
        }
        CoordinatorLayout coordinatorLayout = mapplsCategorySearchItemFragmentBinding2.mapplsCategoryLayoutBackground;
        com.mappls.sdk.category.viewmodel.b bVar3 = this.mViewModel;
        if (bVar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar3 = null;
        }
        Integer backgroundColor = bVar3.c().backgroundColor();
        Intrinsics.checkNotNullExpressionValue(backgroundColor, "mViewModel.searchUiOption.backgroundColor()");
        coordinatorLayout.setBackgroundColor(backgroundColor.intValue());
        MapplsCategorySearchItemFragmentBinding mapplsCategorySearchItemFragmentBinding3 = this.binding;
        if (mapplsCategorySearchItemFragmentBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            mapplsCategorySearchItemFragmentBinding3 = null;
        }
        AppBarLayout appBarLayout = mapplsCategorySearchItemFragmentBinding3.mapplsCategoryAppbar;
        com.mappls.sdk.category.viewmodel.b bVar4 = this.mViewModel;
        if (bVar4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar4 = null;
        }
        Integer backgroundColor2 = bVar4.c().backgroundColor();
        Intrinsics.checkNotNullExpressionValue(backgroundColor2, "mViewModel.searchUiOption.backgroundColor()");
        appBarLayout.setBackgroundColor(backgroundColor2.intValue());
        MapplsCategorySearchItemFragmentBinding mapplsCategorySearchItemFragmentBinding4 = this.binding;
        if (mapplsCategorySearchItemFragmentBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            mapplsCategorySearchItemFragmentBinding4 = null;
        }
        TextView textView2 = mapplsCategorySearchItemFragmentBinding4.mapplsCategoryButtonNext;
        com.mappls.sdk.category.viewmodel.b bVar5 = this.mViewModel;
        if (bVar5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar5 = null;
        }
        Integer nextButtonTextColor = bVar5.c().nextButtonTextColor();
        Intrinsics.checkNotNullExpressionValue(nextButtonTextColor, "mViewModel.searchUiOption.nextButtonTextColor()");
        textView2.setTextColor(nextButtonTextColor.intValue());
        Drawable drawable = ContextCompat.getDrawable(requireContext(), R.drawable.mappls_category_button_background);
        if (drawable != null) {
            com.mappls.sdk.category.viewmodel.b bVar6 = this.mViewModel;
            if (bVar6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                bVar6 = null;
            }
            Integer nextButtonColor = bVar6.c().nextButtonColor();
            Intrinsics.checkNotNullExpressionValue(nextButtonColor, "mViewModel.searchUiOption.nextButtonColor()");
            drawable.setColorFilter(new PorterDuffColorFilter(nextButtonColor.intValue(), PorterDuff.Mode.SRC_IN));
        }
        MapplsCategorySearchItemFragmentBinding mapplsCategorySearchItemFragmentBinding5 = this.binding;
        if (mapplsCategorySearchItemFragmentBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            mapplsCategorySearchItemFragmentBinding5 = null;
        }
        mapplsCategorySearchItemFragmentBinding5.mapplsCategoryButtonNext.setBackground(drawable);
        MapplsCategorySearchItemFragmentBinding mapplsCategorySearchItemFragmentBinding6 = this.binding;
        if (mapplsCategorySearchItemFragmentBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            mapplsCategorySearchItemFragmentBinding6 = null;
        }
        ImageView imageView = mapplsCategorySearchItemFragmentBinding6.mapplsCategoryBackIcon;
        com.mappls.sdk.category.viewmodel.b bVar7 = this.mViewModel;
        if (bVar7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar7 = null;
        }
        Integer backIcon = bVar7.c().backIcon();
        Intrinsics.checkNotNullExpressionValue(backIcon, "mViewModel.searchUiOption.backIcon()");
        imageView.setImageResource(backIcon.intValue());
        MapplsCategorySearchItemFragmentBinding mapplsCategorySearchItemFragmentBinding7 = this.binding;
        if (mapplsCategorySearchItemFragmentBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            mapplsCategorySearchItemFragmentBinding7 = null;
        }
        EditText editText = mapplsCategorySearchItemFragmentBinding7.mapplsCategorySearchInput;
        com.mappls.sdk.category.viewmodel.b bVar8 = this.mViewModel;
        if (bVar8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar8 = null;
        }
        Integer searchTextColor = bVar8.c().searchTextColor();
        Intrinsics.checkNotNullExpressionValue(searchTextColor, "mViewModel.searchUiOption.searchTextColor()");
        editText.setTextColor(searchTextColor.intValue());
        MapplsCategorySearchItemFragmentBinding mapplsCategorySearchItemFragmentBinding8 = this.binding;
        if (mapplsCategorySearchItemFragmentBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            mapplsCategorySearchItemFragmentBinding8 = null;
        }
        EditText editText2 = mapplsCategorySearchItemFragmentBinding8.mapplsCategorySearchInput;
        com.mappls.sdk.category.viewmodel.b bVar9 = this.mViewModel;
        if (bVar9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar9 = null;
        }
        Integer hintTextColor = bVar9.c().hintTextColor();
        Intrinsics.checkNotNullExpressionValue(hintTextColor, "mViewModel.searchUiOption.hintTextColor()");
        editText2.setHintTextColor(hintTextColor.intValue());
        com.mappls.sdk.category.viewmodel.b bVar10 = this.mViewModel;
        if (bVar10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            bVar10 = null;
        }
        Integer maxSelectionCount = bVar10.b().maxSelectionCount();
        if (maxSelectionCount != null && maxSelectionCount.intValue() == 1) {
            MapplsCategorySearchItemFragmentBinding mapplsCategorySearchItemFragmentBinding9 = this.binding;
            if (mapplsCategorySearchItemFragmentBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                mapplsCategorySearchItemFragmentBinding9 = null;
            }
            textView = mapplsCategorySearchItemFragmentBinding9.mapplsCategoryButtonNext;
            i = 8;
        } else {
            MapplsCategorySearchItemFragmentBinding mapplsCategorySearchItemFragmentBinding10 = this.binding;
            if (mapplsCategorySearchItemFragmentBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                mapplsCategorySearchItemFragmentBinding10 = null;
            }
            textView = mapplsCategorySearchItemFragmentBinding10.mapplsCategoryButtonNext;
            i = 0;
        }
        textView.setVisibility(i);
        MapplsCategorySearchItemFragmentBinding mapplsCategorySearchItemFragmentBinding11 = this.binding;
        if (mapplsCategorySearchItemFragmentBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            mapplsCategorySearchItemFragmentBinding11 = null;
        }
        View findViewById = mapplsCategorySearchItemFragmentBinding11.mapplsCategorySearchClearBtn.findViewById(R.id.mappls_category_search_clear_btn);
        Intrinsics.checkNotNullExpressionValue(findViewById, "binding.mapplsCategorySe…ategory_search_clear_btn)");
        ImageView imageView2 = (ImageView) findViewById;
        this.cancelView = imageView2;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cancelView");
            imageView2 = null;
        }
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.mappls.sdk.category.fragment.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CategorySearchFragment.onViewCreated$lambda$1(CategorySearchFragment.this, view2);
            }
        });
        MapplsCategorySearchItemFragmentBinding mapplsCategorySearchItemFragmentBinding12 = this.binding;
        if (mapplsCategorySearchItemFragmentBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            mapplsCategorySearchItemFragmentBinding12 = null;
        }
        ((EditText) mapplsCategorySearchItemFragmentBinding12.mapplsCategoryAppbar.findViewById(R.id.mappls_category_search_input)).addTextChangedListener(new c());
        MapplsCategorySearchItemFragmentBinding mapplsCategorySearchItemFragmentBinding13 = this.binding;
        if (mapplsCategorySearchItemFragmentBinding13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            mapplsCategorySearchItemFragmentBinding13 = null;
        }
        ((ImageView) mapplsCategorySearchItemFragmentBinding13.mapplsCategoryAppbar.findViewById(R.id.mappls_category_back_icon)).setOnClickListener(new View.OnClickListener() { // from class: com.mappls.sdk.category.fragment.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CategorySearchFragment.onViewCreated$lambda$2(CategorySearchFragment.this, view2);
            }
        });
        MapplsCategorySearchItemFragmentBinding mapplsCategorySearchItemFragmentBinding14 = this.binding;
        if (mapplsCategorySearchItemFragmentBinding14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            mapplsCategorySearchItemFragmentBinding = mapplsCategorySearchItemFragmentBinding14;
        }
        mapplsCategorySearchItemFragmentBinding.mapplsCategoryButtonNext.setOnClickListener(new View.OnClickListener() { // from class: com.mappls.sdk.category.fragment.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CategorySearchFragment.onViewCreated$lambda$3(CategorySearchFragment.this, view2);
            }
        });
    }

    public final void setCategoryCodes(@NotNull List<? extends CategoryCode> categories) {
        Intrinsics.checkNotNullParameter(categories, "categories");
        com.mappls.sdk.category.viewmodel.b bVar = this.mViewModel;
        if (bVar != null) {
            bVar.a(categories);
            com.mappls.sdk.category.adapters.a aVar = this.categorySearchAdapter;
            if (aVar != null) {
                com.mappls.sdk.category.viewmodel.b bVar2 = this.mViewModel;
                if (bVar2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                    bVar2 = null;
                }
                List<CategoryCode> a2 = bVar2.a();
                if (a2 == null) {
                    a2 = CollectionsKt__CollectionsKt.emptyList();
                }
                aVar.a(a2);
            }
        }
    }

    public final void setCategorySelectionListener(@NotNull ICategorySelectionListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }
}
