package com.mappls.sdk.category.fragment;

import androidx.annotation.Keep;
import com.mappls.sdk.nearby.plugin.CategoryCode;
import java.util.List;
import org.jetbrains.annotations.NotNull;
@Keep
/* loaded from: classes11.dex */
public interface ICategorySelectionListener {
    void onCancel();

    void onCategorySelected(@NotNull List<? extends CategoryCode> list);
}
