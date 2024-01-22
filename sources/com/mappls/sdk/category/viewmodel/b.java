package com.mappls.sdk.category.viewmodel;

import androidx.lifecycle.ViewModel;
import com.mappls.sdk.category.model.SearchCategoryOption;
import com.mappls.sdk.category.model.SearchCategoryUIOption;
import com.mappls.sdk.nearby.plugin.CategoryCode;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes11.dex */
public final class b extends ViewModel {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public List<? extends CategoryCode> f12553a;
    @NotNull
    public SearchCategoryOption b = com.mappls.sdk.category.a.c();
    @NotNull
    public SearchCategoryUIOption c = com.mappls.sdk.category.a.d();

    @Nullable
    public final List<CategoryCode> a() {
        return this.f12553a;
    }

    public final void a(@NotNull SearchCategoryUIOption searchCategoryUIOption) {
        Intrinsics.checkNotNullParameter(searchCategoryUIOption, "<set-?>");
        this.c = searchCategoryUIOption;
    }

    public final void a(@Nullable List<? extends CategoryCode> list) {
        this.f12553a = list;
    }

    @NotNull
    public final SearchCategoryOption b() {
        return this.b;
    }

    @NotNull
    public final SearchCategoryUIOption c() {
        return this.c;
    }
}
