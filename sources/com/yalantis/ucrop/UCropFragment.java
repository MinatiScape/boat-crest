package com.yalantis.ucrop;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.transition.AutoTransition;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.callback.BitmapCropCallback;
import com.yalantis.ucrop.model.AspectRatio;
import com.yalantis.ucrop.util.SelectedStateListDrawable;
import com.yalantis.ucrop.view.GestureCropImageView;
import com.yalantis.ucrop.view.OverlayView;
import com.yalantis.ucrop.view.TransformImageView;
import com.yalantis.ucrop.view.UCropView;
import com.yalantis.ucrop.view.widget.AspectRatioTextView;
import com.yalantis.ucrop.view.widget.HorizontalProgressWheelView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
/* loaded from: classes12.dex */
public class UCropFragment extends Fragment {
    public static final int ALL = 3;
    public static final Bitmap.CompressFormat DEFAULT_COMPRESS_FORMAT = Bitmap.CompressFormat.JPEG;
    public static final int DEFAULT_COMPRESS_QUALITY = 90;
    public static final int NONE = 0;
    public static final int ROTATE = 2;
    public static final int SCALE = 1;
    public static final String TAG = "UCropFragment";
    public View A;
    public UCropFragmentCallback h;
    public int i;
    public int j;
    @ColorInt
    public int k;
    public int l;
    public boolean m;
    public Transition n;
    public UCropView o;
    public GestureCropImageView p;
    public OverlayView q;
    public ViewGroup r;
    public ViewGroup s;
    public ViewGroup t;
    public ViewGroup u;
    public ViewGroup v;
    public ViewGroup w;
    public TextView y;
    public TextView z;
    public List<ViewGroup> x = new ArrayList();
    public Bitmap.CompressFormat B = DEFAULT_COMPRESS_FORMAT;
    public int C = 90;
    public int[] D = {1, 2, 3};
    public TransformImageView.TransformImageListener E = new a();
    public final View.OnClickListener F = new g();

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes12.dex */
    public @interface GestureTypes {
    }

    /* loaded from: classes12.dex */
    public class UCropResult {
        public int mResultCode;
        public Intent mResultData;

        public UCropResult(UCropFragment uCropFragment, int i, Intent intent) {
            this.mResultCode = i;
            this.mResultData = intent;
        }
    }

    /* loaded from: classes12.dex */
    public class a implements TransformImageView.TransformImageListener {
        public a() {
        }

        @Override // com.yalantis.ucrop.view.TransformImageView.TransformImageListener
        public void onLoadComplete() {
            UCropFragment.this.o.animate().alpha(1.0f).setDuration(300L).setInterpolator(new AccelerateInterpolator());
            UCropFragment.this.A.setClickable(false);
            UCropFragment.this.h.loadingProgress(false);
        }

        @Override // com.yalantis.ucrop.view.TransformImageView.TransformImageListener
        public void onLoadFailure(@NonNull Exception exc) {
            UCropFragment.this.h.onCropFinish(UCropFragment.this.getError(exc));
        }

        @Override // com.yalantis.ucrop.view.TransformImageView.TransformImageListener
        public void onRotate(float f) {
            UCropFragment.this.r(f);
        }

        @Override // com.yalantis.ucrop.view.TransformImageView.TransformImageListener
        public void onScale(float f) {
            UCropFragment.this.u(f);
        }
    }

    /* loaded from: classes12.dex */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            UCropFragment.this.p.setTargetAspectRatio(((AspectRatioTextView) ((ViewGroup) view).getChildAt(0)).getAspectRatio(view.isSelected()));
            UCropFragment.this.p.setImageToWrapCropBounds();
            if (view.isSelected()) {
                return;
            }
            for (ViewGroup viewGroup : UCropFragment.this.x) {
                viewGroup.setSelected(viewGroup == view);
            }
        }
    }

    /* loaded from: classes12.dex */
    public class c implements HorizontalProgressWheelView.ScrollingListener {
        public c() {
        }

        @Override // com.yalantis.ucrop.view.widget.HorizontalProgressWheelView.ScrollingListener
        public void onScroll(float f, float f2) {
            UCropFragment.this.p.postRotate(f / 42.0f);
        }

        @Override // com.yalantis.ucrop.view.widget.HorizontalProgressWheelView.ScrollingListener
        public void onScrollEnd() {
            UCropFragment.this.p.setImageToWrapCropBounds();
        }

        @Override // com.yalantis.ucrop.view.widget.HorizontalProgressWheelView.ScrollingListener
        public void onScrollStart() {
            UCropFragment.this.p.cancelAllAnimations();
        }
    }

    /* loaded from: classes12.dex */
    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            UCropFragment.this.o();
        }
    }

    /* loaded from: classes12.dex */
    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            UCropFragment.this.p(90);
        }
    }

    /* loaded from: classes12.dex */
    public class f implements HorizontalProgressWheelView.ScrollingListener {
        public f() {
        }

        @Override // com.yalantis.ucrop.view.widget.HorizontalProgressWheelView.ScrollingListener
        public void onScroll(float f, float f2) {
            if (f > 0.0f) {
                UCropFragment.this.p.zoomInImage(UCropFragment.this.p.getCurrentScale() + (f * ((UCropFragment.this.p.getMaxScale() - UCropFragment.this.p.getMinScale()) / 15000.0f)));
            } else {
                UCropFragment.this.p.zoomOutImage(UCropFragment.this.p.getCurrentScale() + (f * ((UCropFragment.this.p.getMaxScale() - UCropFragment.this.p.getMinScale()) / 15000.0f)));
            }
        }

        @Override // com.yalantis.ucrop.view.widget.HorizontalProgressWheelView.ScrollingListener
        public void onScrollEnd() {
            UCropFragment.this.p.setImageToWrapCropBounds();
        }

        @Override // com.yalantis.ucrop.view.widget.HorizontalProgressWheelView.ScrollingListener
        public void onScrollStart() {
            UCropFragment.this.p.cancelAllAnimations();
        }
    }

    /* loaded from: classes12.dex */
    public class g implements View.OnClickListener {
        public g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view.isSelected()) {
                return;
            }
            UCropFragment.this.v(view.getId());
        }
    }

    /* loaded from: classes12.dex */
    public class h implements BitmapCropCallback {
        public h() {
        }

        @Override // com.yalantis.ucrop.callback.BitmapCropCallback
        public void onBitmapCropped(@NonNull Uri uri, int i, int i2, int i3, int i4) {
            UCropFragmentCallback uCropFragmentCallback = UCropFragment.this.h;
            UCropFragment uCropFragment = UCropFragment.this;
            uCropFragmentCallback.onCropFinish(uCropFragment.getResult(uri, uCropFragment.p.getTargetAspectRatio(), i, i2, i3, i4));
            UCropFragment.this.h.loadingProgress(false);
        }

        @Override // com.yalantis.ucrop.callback.BitmapCropCallback
        public void onCropFailure(@NonNull Throwable th) {
            UCropFragment.this.h.onCropFinish(UCropFragment.this.getError(th));
        }
    }

    public static UCropFragment newInstance(Bundle bundle) {
        UCropFragment uCropFragment = new UCropFragment();
        uCropFragment.setArguments(bundle);
        return uCropFragment;
    }

    public void cropAndSaveImage() {
        this.A.setClickable(true);
        this.h.loadingProgress(true);
        this.p.cropAndSaveImage(this.B, this.C, new h());
    }

    public UCropResult getError(Throwable th) {
        return new UCropResult(this, 96, new Intent().putExtra(UCrop.EXTRA_ERROR, th));
    }

    public UCropResult getResult(Uri uri, float f2, int i, int i2, int i3, int i4) {
        return new UCropResult(this, -1, new Intent().putExtra(UCrop.EXTRA_OUTPUT_URI, uri).putExtra(UCrop.EXTRA_OUTPUT_CROP_ASPECT_RATIO, f2).putExtra(UCrop.EXTRA_OUTPUT_IMAGE_WIDTH, i3).putExtra(UCrop.EXTRA_OUTPUT_IMAGE_HEIGHT, i4).putExtra(UCrop.EXTRA_OUTPUT_OFFSET_X, i).putExtra(UCrop.EXTRA_OUTPUT_OFFSET_Y, i2));
    }

    public final void k(View view) {
        if (this.A == null) {
            this.A = new View(getContext());
            this.A.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            this.A.setClickable(true);
        }
        ((RelativeLayout) view.findViewById(R.id.ucrop_photobox)).addView(this.A);
    }

    public final void l(int i) {
        if (getView() != null) {
            TransitionManager.beginDelayedTransition((ViewGroup) getView().findViewById(R.id.ucrop_photobox), this.n);
        }
        this.t.findViewById(R.id.text_view_scale).setVisibility(i == R.id.state_scale ? 0 : 8);
        this.r.findViewById(R.id.text_view_crop).setVisibility(i == R.id.state_aspect_ratio ? 0 : 8);
        this.s.findViewById(R.id.text_view_rotate).setVisibility(i != R.id.state_rotate ? 8 : 0);
    }

    public final void m(View view) {
        UCropView uCropView = (UCropView) view.findViewById(R.id.ucrop);
        this.o = uCropView;
        this.p = uCropView.getCropImageView();
        this.q = this.o.getOverlayView();
        this.p.setTransformImageListener(this.E);
        ((ImageView) view.findViewById(R.id.image_view_logo)).setColorFilter(this.l, PorterDuff.Mode.SRC_ATOP);
        view.findViewById(R.id.ucrop_frame).setBackgroundColor(this.k);
    }

    public final void n(@NonNull Bundle bundle) {
        String string = bundle.getString(UCrop.Options.EXTRA_COMPRESSION_FORMAT_NAME);
        Bitmap.CompressFormat valueOf = !TextUtils.isEmpty(string) ? Bitmap.CompressFormat.valueOf(string) : null;
        if (valueOf == null) {
            valueOf = DEFAULT_COMPRESS_FORMAT;
        }
        this.B = valueOf;
        this.C = bundle.getInt(UCrop.Options.EXTRA_COMPRESSION_QUALITY, 90);
        int[] intArray = bundle.getIntArray(UCrop.Options.EXTRA_ALLOWED_GESTURES);
        if (intArray != null && intArray.length == 3) {
            this.D = intArray;
        }
        this.p.setMaxBitmapSize(bundle.getInt(UCrop.Options.EXTRA_MAX_BITMAP_SIZE, 0));
        this.p.setMaxScaleMultiplier(bundle.getFloat(UCrop.Options.EXTRA_MAX_SCALE_MULTIPLIER, 10.0f));
        this.p.setImageToWrapCropBoundsAnimDuration(bundle.getInt(UCrop.Options.EXTRA_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 500));
        this.q.setFreestyleCropEnabled(bundle.getBoolean(UCrop.Options.EXTRA_FREE_STYLE_CROP, false));
        this.q.setDimmedColor(bundle.getInt(UCrop.Options.EXTRA_DIMMED_LAYER_COLOR, getResources().getColor(R.color.ucrop_color_default_dimmed)));
        this.q.setCircleDimmedLayer(bundle.getBoolean(UCrop.Options.EXTRA_CIRCLE_DIMMED_LAYER, false));
        this.q.setShowCropFrame(bundle.getBoolean(UCrop.Options.EXTRA_SHOW_CROP_FRAME, true));
        this.q.setCropFrameColor(bundle.getInt(UCrop.Options.EXTRA_CROP_FRAME_COLOR, getResources().getColor(R.color.ucrop_color_default_crop_frame)));
        this.q.setCropFrameStrokeWidth(bundle.getInt(UCrop.Options.EXTRA_CROP_FRAME_STROKE_WIDTH, getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_frame_stoke_width)));
        this.q.setShowCropGrid(bundle.getBoolean(UCrop.Options.EXTRA_SHOW_CROP_GRID, true));
        this.q.setCropGridRowCount(bundle.getInt(UCrop.Options.EXTRA_CROP_GRID_ROW_COUNT, 2));
        this.q.setCropGridColumnCount(bundle.getInt(UCrop.Options.EXTRA_CROP_GRID_COLUMN_COUNT, 2));
        this.q.setCropGridColor(bundle.getInt(UCrop.Options.EXTRA_CROP_GRID_COLOR, getResources().getColor(R.color.ucrop_color_default_crop_grid)));
        this.q.setCropGridStrokeWidth(bundle.getInt(UCrop.Options.EXTRA_CROP_GRID_STROKE_WIDTH, getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_grid_stoke_width)));
        float f2 = bundle.getFloat(UCrop.EXTRA_ASPECT_RATIO_X, 0.0f);
        float f3 = bundle.getFloat(UCrop.EXTRA_ASPECT_RATIO_Y, 0.0f);
        int i = bundle.getInt(UCrop.Options.EXTRA_ASPECT_RATIO_SELECTED_BY_DEFAULT, 0);
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(UCrop.Options.EXTRA_ASPECT_RATIO_OPTIONS);
        if (f2 > 0.0f && f3 > 0.0f) {
            ViewGroup viewGroup = this.r;
            if (viewGroup != null) {
                viewGroup.setVisibility(8);
            }
            this.p.setTargetAspectRatio(f2 / f3);
        } else if (parcelableArrayList != null && i < parcelableArrayList.size()) {
            this.p.setTargetAspectRatio(((AspectRatio) parcelableArrayList.get(i)).getAspectRatioX() / ((AspectRatio) parcelableArrayList.get(i)).getAspectRatioY());
        } else {
            this.p.setTargetAspectRatio(0.0f);
        }
        int i2 = bundle.getInt(UCrop.EXTRA_MAX_SIZE_X, 0);
        int i3 = bundle.getInt(UCrop.EXTRA_MAX_SIZE_Y, 0);
        if (i2 <= 0 || i3 <= 0) {
            return;
        }
        this.p.setMaxResultImageSizeX(i2);
        this.p.setMaxResultImageSizeY(i3);
    }

    public final void o() {
        GestureCropImageView gestureCropImageView = this.p;
        gestureCropImageView.postRotate(-gestureCropImageView.getCurrentAngle());
        this.p.setImageToWrapCropBounds();
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getParentFragment() instanceof UCropFragmentCallback) {
            this.h = (UCropFragmentCallback) getParentFragment();
        } else if (context instanceof UCropFragmentCallback) {
            this.h = (UCropFragmentCallback) context;
        } else {
            throw new IllegalArgumentException(context.toString() + " must implement UCropFragmentCallback");
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.ucrop_fragment_photobox, viewGroup, false);
        Bundle arguments = getArguments();
        setupViews(inflate, arguments);
        s(arguments);
        t();
        k(inflate);
        return inflate;
    }

    public final void p(int i) {
        this.p.postRotate(i);
        this.p.setImageToWrapCropBounds();
    }

    public final void q(int i) {
        GestureCropImageView gestureCropImageView = this.p;
        int[] iArr = this.D;
        boolean z = false;
        gestureCropImageView.setScaleEnabled(iArr[i] == 3 || iArr[i] == 1);
        GestureCropImageView gestureCropImageView2 = this.p;
        int[] iArr2 = this.D;
        if (iArr2[i] == 3 || iArr2[i] == 2) {
            z = true;
        }
        gestureCropImageView2.setRotateEnabled(z);
    }

    public final void r(float f2) {
        TextView textView = this.y;
        if (textView != null) {
            textView.setText(String.format(Locale.getDefault(), "%.1f°", Float.valueOf(f2)));
        }
    }

    public final void s(@NonNull Bundle bundle) {
        Uri uri = (Uri) bundle.getParcelable(UCrop.EXTRA_INPUT_URI);
        Uri uri2 = (Uri) bundle.getParcelable(UCrop.EXTRA_OUTPUT_URI);
        n(bundle);
        if (uri != null && uri2 != null) {
            try {
                this.p.setImageUri(uri, uri2);
                return;
            } catch (Exception e2) {
                this.h.onCropFinish(getError(e2));
                return;
            }
        }
        this.h.onCropFinish(getError(new NullPointerException(getString(R.string.ucrop_error_input_data_is_absent))));
    }

    public void setCallback(UCropFragmentCallback uCropFragmentCallback) {
        this.h = uCropFragmentCallback;
    }

    public void setupViews(View view, Bundle bundle) {
        this.j = bundle.getInt(UCrop.Options.EXTRA_UCROP_COLOR_WIDGET_ACTIVE, ContextCompat.getColor(getContext(), R.color.ucrop_color_widget_background));
        this.i = bundle.getInt(UCrop.Options.EXTRA_UCROP_COLOR_WIDGET_ACTIVE, ContextCompat.getColor(getContext(), R.color.ucrop_color_widget_active));
        this.l = bundle.getInt(UCrop.Options.EXTRA_UCROP_LOGO_COLOR, ContextCompat.getColor(getContext(), R.color.ucrop_color_default_logo));
        this.m = !bundle.getBoolean(UCrop.Options.EXTRA_HIDE_BOTTOM_CONTROLS, false);
        this.k = bundle.getInt(UCrop.Options.EXTRA_UCROP_ROOT_VIEW_BACKGROUND_COLOR, ContextCompat.getColor(getContext(), R.color.ucrop_color_crop_background));
        m(view);
        this.h.loadingProgress(true);
        if (this.m) {
            ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.controls_wrapper);
            viewGroup.setVisibility(0);
            viewGroup.setBackgroundColor(this.k);
            LayoutInflater.from(getContext()).inflate(R.layout.ucrop_controls, viewGroup, true);
            AutoTransition autoTransition = new AutoTransition();
            this.n = autoTransition;
            autoTransition.setDuration(50L);
            ViewGroup viewGroup2 = (ViewGroup) view.findViewById(R.id.state_aspect_ratio);
            this.r = viewGroup2;
            viewGroup2.setOnClickListener(this.F);
            ViewGroup viewGroup3 = (ViewGroup) view.findViewById(R.id.state_rotate);
            this.s = viewGroup3;
            viewGroup3.setOnClickListener(this.F);
            ViewGroup viewGroup4 = (ViewGroup) view.findViewById(R.id.state_scale);
            this.t = viewGroup4;
            viewGroup4.setOnClickListener(this.F);
            this.u = (ViewGroup) view.findViewById(R.id.layout_aspect_ratio);
            this.v = (ViewGroup) view.findViewById(R.id.layout_rotate_wheel);
            this.w = (ViewGroup) view.findViewById(R.id.layout_scale_wheel);
            w(bundle, view);
            x(view);
            y(view);
            z(view);
        }
    }

    public final void t() {
        if (this.m) {
            if (this.r.getVisibility() == 0) {
                v(R.id.state_aspect_ratio);
                return;
            } else {
                v(R.id.state_scale);
                return;
            }
        }
        q(0);
    }

    public final void u(float f2) {
        TextView textView = this.z;
        if (textView != null) {
            textView.setText(String.format(Locale.getDefault(), "%d%%", Integer.valueOf((int) (f2 * 100.0f))));
        }
    }

    public final void v(@IdRes int i) {
        if (this.m) {
            ViewGroup viewGroup = this.r;
            int i2 = R.id.state_aspect_ratio;
            viewGroup.setSelected(i == i2);
            ViewGroup viewGroup2 = this.s;
            int i3 = R.id.state_rotate;
            viewGroup2.setSelected(i == i3);
            ViewGroup viewGroup3 = this.t;
            int i4 = R.id.state_scale;
            viewGroup3.setSelected(i == i4);
            this.u.setVisibility(i == i2 ? 0 : 8);
            this.v.setVisibility(i == i3 ? 0 : 8);
            this.w.setVisibility(i == i4 ? 0 : 8);
            l(i);
            if (i == i4) {
                q(0);
            } else if (i == i3) {
                q(1);
            } else {
                q(2);
            }
        }
    }

    public final void w(@NonNull Bundle bundle, View view) {
        int i = bundle.getInt(UCrop.Options.EXTRA_ASPECT_RATIO_SELECTED_BY_DEFAULT, 0);
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(UCrop.Options.EXTRA_ASPECT_RATIO_OPTIONS);
        if (parcelableArrayList == null || parcelableArrayList.isEmpty()) {
            i = 2;
            parcelableArrayList = new ArrayList();
            parcelableArrayList.add(new AspectRatio(null, 1.0f, 1.0f));
            parcelableArrayList.add(new AspectRatio(null, 3.0f, 4.0f));
            parcelableArrayList.add(new AspectRatio(getString(R.string.ucrop_label_original).toUpperCase(), 0.0f, 0.0f));
            parcelableArrayList.add(new AspectRatio(null, 3.0f, 2.0f));
            parcelableArrayList.add(new AspectRatio(null, 16.0f, 9.0f));
        }
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.layout_aspect_ratio);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -1);
        layoutParams.weight = 1.0f;
        Iterator it = parcelableArrayList.iterator();
        while (it.hasNext()) {
            FrameLayout frameLayout = (FrameLayout) getLayoutInflater().inflate(R.layout.ucrop_aspect_ratio, (ViewGroup) null);
            frameLayout.setLayoutParams(layoutParams);
            AspectRatioTextView aspectRatioTextView = (AspectRatioTextView) frameLayout.getChildAt(0);
            aspectRatioTextView.setActiveColor(this.j);
            aspectRatioTextView.setAspectRatio((AspectRatio) it.next());
            linearLayout.addView(frameLayout);
            this.x.add(frameLayout);
        }
        this.x.get(i).setSelected(true);
        for (ViewGroup viewGroup : this.x) {
            viewGroup.setOnClickListener(new b());
        }
    }

    public final void x(View view) {
        this.y = (TextView) view.findViewById(R.id.text_view_rotate);
        int i = R.id.rotate_scroll_wheel;
        ((HorizontalProgressWheelView) view.findViewById(i)).setScrollingListener(new c());
        ((HorizontalProgressWheelView) view.findViewById(i)).setMiddleLineColor(this.j);
        view.findViewById(R.id.wrapper_reset_rotate).setOnClickListener(new d());
        view.findViewById(R.id.wrapper_rotate_by_angle).setOnClickListener(new e());
    }

    public final void y(View view) {
        this.z = (TextView) view.findViewById(R.id.text_view_scale);
        int i = R.id.scale_scroll_wheel;
        ((HorizontalProgressWheelView) view.findViewById(i)).setScrollingListener(new f());
        ((HorizontalProgressWheelView) view.findViewById(i)).setMiddleLineColor(this.j);
    }

    public final void z(View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.image_view_state_scale);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.image_view_state_rotate);
        ImageView imageView3 = (ImageView) view.findViewById(R.id.image_view_state_aspect_ratio);
        imageView.setImageDrawable(new SelectedStateListDrawable(imageView.getDrawable(), this.i));
        imageView2.setImageDrawable(new SelectedStateListDrawable(imageView2.getDrawable(), this.i));
        imageView3.setImageDrawable(new SelectedStateListDrawable(imageView3.getDrawable(), this.i));
    }
}
