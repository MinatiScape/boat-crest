package com.mappls.sdk.navigation.ui.navigation.instructioncontainer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.Keep;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import com.mappls.sdk.navigation.MapplsNavigationHelper;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.model.AdviseInfo;
import com.mappls.sdk.navigation.ui.R;
import com.mappls.sdk.navigation.ui.databinding.LayoutInstructionContainerBinding;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Keep
/* loaded from: classes11.dex */
public class InstructionContainerView extends BaseInstructionContainerView implements LifecycleObserver {
    private NavigationApplication app;
    private LayoutInstructionContainerBinding binding;
    private int currentPageLocation;
    private LifecycleOwner lifecycleOwner;
    private com.mappls.sdk.navigation.ui.navigation.instructioncontainer.b navigationPagerAdapter;
    private com.mappls.sdk.navigation.ui.navigation.a navigationViewModel;

    /* loaded from: classes11.dex */
    public class a implements View.OnClickListener {
        public final /* synthetic */ com.mappls.sdk.navigation.ui.navigation.instructioncontainer.c h;

        public a(com.mappls.sdk.navigation.ui.navigation.instructioncontainer.c cVar) {
            this.h = cVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (InstructionContainerView.this.binding.navigationInfoLayoutNew.getCurrentItem() > InstructionContainerView.this.currentPageLocation) {
                InstructionContainerView.this.binding.navigationInfoLayoutNew.setCurrentItem(InstructionContainerView.this.binding.navigationInfoLayoutNew.getCurrentItem() - 1);
                com.mappls.sdk.navigation.ui.navigation.instructioncontainer.c cVar = this.h;
                if (cVar != null) {
                    cVar.a();
                }
            }
        }
    }

    /* loaded from: classes11.dex */
    public class b implements View.OnClickListener {
        public final /* synthetic */ com.mappls.sdk.navigation.ui.navigation.instructioncontainer.c h;

        public b(com.mappls.sdk.navigation.ui.navigation.instructioncontainer.c cVar) {
            this.h = cVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (InstructionContainerView.this.app == null || InstructionContainerView.this.binding.navigationInfoLayoutNew.getCurrentItem() >= InstructionContainerView.this.app.getRouteDirections().size()) {
                return;
            }
            InstructionContainerView.this.binding.navigationInfoLayoutNew.setCurrentItem(InstructionContainerView.this.binding.navigationInfoLayoutNew.getCurrentItem() + 1);
            com.mappls.sdk.navigation.ui.navigation.instructioncontainer.c cVar = this.h;
            if (cVar != null) {
                cVar.b();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Observer<com.mappls.sdk.navigation.ui.navigation.instructioncontainer.a> {
        public c() {
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(com.mappls.sdk.navigation.ui.navigation.instructioncontainer.a aVar) {
            InstructionContainerView.this.setAdviseInfo(aVar.a());
        }
    }

    public InstructionContainerView(@NotNull Context context) {
        this(context, null);
    }

    public InstructionContainerView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.instructionContainerStyle);
    }

    public InstructionContainerView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, R.style.InstructionContainerStyle);
    }

    public InstructionContainerView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.binding = LayoutInstructionContainerBinding.inflate(LayoutInflater.from(getContext()), this, true);
        this.currentPageLocation = 1;
        context.obtainStyledAttributes(attributeSet, R.styleable.InstructionContainer, i, i2);
    }

    public int getCurrentItem() {
        return this.currentPageLocation;
    }

    public int getSelectedItem() {
        return this.binding.navigationInfoLayoutNew.getCurrentItem();
    }

    public void onPictureInPictureModeChange(boolean z) {
        ImageButton imageButton;
        int i;
        ViewGroup.LayoutParams layoutParams = this.binding.navigationInfoLayoutNew.getLayoutParams();
        if (z) {
            layoutParams.height = (int) getResources().getDimension(R.dimen.pictureInpictureModeInfobar);
            this.binding.navigationInfoLayoutNew.setLayoutParams(layoutParams);
            imageButton = this.binding.navigationStripRightImageButton;
            i = 8;
        } else {
            layoutParams.height = (int) getResources().getDimension(R.dimen.infobar_height);
            this.binding.navigationInfoLayoutNew.setLayoutParams(layoutParams);
            imageButton = this.binding.navigationStripRightImageButton;
            i = 0;
        }
        imageButton.setVisibility(i);
        this.binding.navigationStripLeftImageButton.setVisibility(i);
    }

    public void setAdviseInfo(AdviseInfo adviseInfo) {
        if (adviseInfo.isRouteBeingRecalculated() && !adviseInfo.isOnRoute()) {
            this.binding.otherInfoTextView.setVisibility(0);
            this.binding.navigationInfoLayoutNew.setVisibility(8);
            return;
        }
        this.binding.otherInfoTextView.setVisibility(8);
        this.binding.navigationInfoLayoutNew.setVisibility(0);
        if (this.binding.navigationInfoLayoutNew.getAdapter() != null) {
            ((com.mappls.sdk.navigation.ui.navigation.instructioncontainer.b) this.binding.navigationInfoLayoutNew.getAdapter()).a(adviseInfo.getDistanceToNextAdvise());
            ((com.mappls.sdk.navigation.ui.navigation.instructioncontainer.b) this.binding.navigationInfoLayoutNew.getAdapter()).a(adviseInfo.getPosition() < 1 ? 1 : adviseInfo.getPosition());
        }
        if (adviseInfo.isOnRoute()) {
            this.binding.otherInfoTextView.setVisibility(8);
            this.binding.navigationInfoLayoutNew.setVisibility(0);
        }
        this.currentPageLocation = adviseInfo.getPosition() != 0 ? adviseInfo.getPosition() : 1;
    }

    public void setCurrentItem(int i) {
        if (i >= this.currentPageLocation) {
            this.binding.navigationInfoLayoutNew.setCurrentItem(i);
        }
    }

    public void setDataContainer(String str, NavigationApplication navigationApplication, boolean z) {
        this.app = navigationApplication;
        this.binding.otherInfoTextView.setVisibility(8);
        this.binding.navigationInfoLayoutNew.setVisibility(0);
        if (this.binding.navigationInfoLayoutNew.getAdapter() == null || z) {
            com.mappls.sdk.navigation.ui.navigation.instructioncontainer.b bVar = new com.mappls.sdk.navigation.ui.navigation.instructioncontainer.b(getContext(), MapplsNavigationHelper.getInstance().getNavigationSteps(), "End Stop", navigationApplication);
            this.binding.navigationInfoLayoutNew.setAdapter(bVar);
            bVar.a(1);
            this.binding.navigationInfoLayoutNew.setCurrentItem(1);
        }
    }

    public void setFollowMe() {
        this.binding.navigationInfoLayoutNew.setCurrentItem(this.currentPageLocation);
    }

    @Override // com.mappls.sdk.navigation.ui.navigation.instructioncontainer.BaseInstructionContainerView
    public void setLeftButtonIcon(int i) {
        if (i != 0) {
            this.binding.navigationStripLeftImageButton.setImageResource(i);
        }
    }

    public void setNextPreviousIconClickListener(com.mappls.sdk.navigation.ui.navigation.instructioncontainer.c cVar) {
        this.binding.navigationStripLeftImageButton.setOnClickListener(new a(cVar));
        this.binding.navigationStripRightImageButton.setOnClickListener(new b(cVar));
    }

    @Override // com.mappls.sdk.navigation.ui.navigation.instructioncontainer.BaseInstructionContainerView
    public void setRightButtonIcon(int i) {
        if (i != 0) {
            this.binding.navigationStripRightImageButton.setImageResource(i);
        }
    }

    public void subscribe(LifecycleOwner lifecycleOwner, com.mappls.sdk.navigation.ui.navigation.a aVar) {
        this.lifecycleOwner = lifecycleOwner;
        lifecycleOwner.getLifecycle().addObserver(this);
        this.navigationViewModel = aVar;
        aVar.b.observe(this.lifecycleOwner, new c());
    }

    public void toggleTheme() {
        this.navigationPagerAdapter.notifyDataSetChanged();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void unsubscribe() {
        com.mappls.sdk.navigation.ui.navigation.a aVar = this.navigationViewModel;
        if (aVar != null) {
            aVar.b.removeObservers(this.lifecycleOwner);
        }
    }
}
