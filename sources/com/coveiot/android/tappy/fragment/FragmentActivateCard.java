package com.coveiot.android.tappy.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.coveiot.android.tappy.R;
import com.coveiot.android.tappy.activity.CardActivationActivity;
import com.coveiot.android.tappy.databinding.FragmentActivateCardBinding;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.GlideUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class FragmentActivateCard extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public FragmentActivateCardBinding m;
    @Nullable
    public ImageView n;
    @Nullable
    public TextView o;
    @Nullable
    public Button p;
    @Nullable
    public String q;
    @Nullable
    public String r;

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentActivateCard newInstance(@Nullable String str, @Nullable String str2) {
            FragmentActivateCard fragmentActivateCard = new FragmentActivateCard();
            Bundle bundle = new Bundle();
            bundle.putString("param1", str);
            bundle.putString("param2", str2);
            fragmentActivateCard.setArguments(bundle);
            return fragmentActivateCard;
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentActivateCard newInstance(@Nullable String str, @Nullable String str2) {
        return Companion.newInstance(str, str2);
    }

    public static final void r(String pdfUrl, final FragmentActivateCard this$0) {
        final Bitmap a2;
        Intrinsics.checkNotNullParameter(pdfUrl, "$pdfUrl");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            URLConnection openConnection = new URL(pdfUrl).openConnection();
            Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
            HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            File createTempFile = File.createTempFile("temp_pdf", ".pdf");
            FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
            byte[] bArr = new byte[4096];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            }
            inputStream.close();
            fileOutputStream.close();
            httpURLConnection.disconnect();
            String absolutePath = createTempFile.getAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(absolutePath, "tempFile.absolutePath");
            a2 = FragmentActivateCardKt.a(absolutePath);
            if (a2 != null) {
                this$0.requireActivity().runOnUiThread(new Runnable() { // from class: com.coveiot.android.tappy.fragment.j
                    @Override // java.lang.Runnable
                    public final void run() {
                        FragmentActivateCard.s(FragmentActivateCard.this, a2);
                    }
                });
            } else {
                this$0.requireActivity().runOnUiThread(new Runnable() { // from class: com.coveiot.android.tappy.fragment.h
                    @Override // java.lang.Runnable
                    public final void run() {
                        FragmentActivateCard.t(FragmentActivateCard.this);
                    }
                });
                Log.i("FragmentActivateCard", "Image conversion failed");
            }
            createTempFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final void s(FragmentActivateCard this$0, Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Glide.with(this$0.requireContext()).m24load(bitmap).into(this$0.v().imageViewHolder);
        this$0.v().tvDynamicAccountNumber.setVisibility(0);
        this$0.dismissProgress();
    }

    public static final void t(FragmentActivateCard this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.v().imageViewHolder.setImageResource(R.drawable.img_empty_card_bg);
    }

    public static final void u(final FragmentActivateCard this$0, String pdfUrl) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(pdfUrl, "$pdfUrl");
        GlideUtils.loadScaledImage(this$0.getContext(), pdfUrl, new SimpleTarget<Bitmap>() { // from class: com.coveiot.android.tappy.fragment.FragmentActivateCard$downloadPdfAndConvertToImage$1$1
            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(@NotNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                FragmentActivateCardBinding v;
                Intrinsics.checkNotNullParameter(resource, "resource");
                Bitmap circleBitmap = AppUtils.getCircleBitmap(resource);
                if (circleBitmap != null) {
                    v = FragmentActivateCard.this.v();
                    v.imageViewHolder.setImageBitmap(circleBitmap);
                }
            }
        });
        Log.i("FragmentActivateCard", "Invalid file format: " + pdfUrl);
    }

    public static final void w(FragmentActivateCard this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Context context = this$0.getContext();
        if (context != null) {
            Glide.with(context).m30load(this$0.q).into(this$0.v().imageViewHolder);
        }
        this$0.v().tvDynamicAccountNumber.setVisibility(0);
        this$0.dismissProgress();
    }

    public static final void x(FragmentActivateCard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getActivity() instanceof CardActivationActivity) {
            FragmentActivity activity = this$0.getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.tappy.activity.CardActivationActivity");
            ((CardActivationActivity) activity).activateCard();
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseFragment
    @Nullable
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.q = arguments.getString("param1");
            this.r = arguments.getString("param2");
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentActivateCardBinding.inflate(inflater, viewGroup, false);
        return v().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        TextView textView;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        BaseFragment.showProgress$default(this, false, 1, null);
        this.n = (ImageView) view.findViewById(R.id.imageViewHolder);
        this.o = (TextView) view.findViewById(R.id.tv_dynamicAccountNumber);
        this.p = (Button) view.findViewById(R.id.btnActivateCard);
        v().tvDynamicAccountNumber.setVisibility(8);
        if (AppUtils.isNetConnected(getContext())) {
            String str = this.q;
            if (!(str == null || str.length() == 0)) {
                String str2 = this.q;
                Intrinsics.checkNotNull(str2);
                if (kotlin.text.m.endsWith(str2, ".pdf", true)) {
                    String str3 = this.q;
                    if (str3 != null) {
                        q(str3);
                    }
                } else {
                    requireActivity().runOnUiThread(new Runnable() { // from class: com.coveiot.android.tappy.fragment.i
                        @Override // java.lang.Runnable
                        public final void run() {
                            FragmentActivateCard.w(FragmentActivateCard.this);
                        }
                    });
                }
            }
        } else {
            v().imageViewHolder.setImageResource(R.drawable.img_empty_card_bg);
        }
        String str4 = this.r;
        if (!(str4 == null || str4.length() == 0) && (textView = this.o) != null) {
            textView.setText(getString(R.string.static_dynamic, this.r));
        }
        Button button = this.p;
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.g
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    FragmentActivateCard.x(FragmentActivateCard.this, view2);
                }
            });
        }
    }

    public final void q(final String str) {
        try {
            if (kotlin.text.m.endsWith(str, ".pdf", true)) {
                new Thread(new Runnable() { // from class: com.coveiot.android.tappy.fragment.l
                    @Override // java.lang.Runnable
                    public final void run() {
                        FragmentActivateCard.r(str, this);
                    }
                }).start();
            } else {
                requireActivity().runOnUiThread(new Runnable() { // from class: com.coveiot.android.tappy.fragment.k
                    @Override // java.lang.Runnable
                    public final void run() {
                        FragmentActivateCard.u(FragmentActivateCard.this, str);
                    }
                });
                v().imageViewHolder.setImageResource(R.drawable.img_empty_card_bg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final FragmentActivateCardBinding v() {
        FragmentActivateCardBinding fragmentActivateCardBinding = this.m;
        Intrinsics.checkNotNull(fragmentActivateCardBinding);
        return fragmentActivateCardBinding;
    }
}
