package com.coveiot.android.weeklyreport.fragments;

import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.core.content.FileProvider;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.CommonMessageDialog;
import com.coveiot.android.weeklyreport.R;
import com.coveiot.android.weeklyreport.activities.ActivityWeeklyReportHistory;
import com.coveiot.android.weeklyreport.databinding.FragmentFitnessReportHistoryBinding;
import com.coveiot.coveaccess.weeklyreport.response.FitnessReportRes;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.github.barteksc.pdfviewer.PDFView;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class FragmentFitnessReportHistory extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public Bitmap bitmap;
    public FragmentFitnessReportHistoryBinding m;
    @Nullable
    public FitnessReportRes.FitnessReportItem n;
    public boolean p;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public String o = "FragmentFitnessReportHistory";

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentFitnessReportHistory newInstance(@NotNull FitnessReportRes.FitnessReportItem fitnessReportItem) {
            Intrinsics.checkNotNullParameter(fitnessReportItem, "fitnessReportItem");
            FragmentFitnessReportHistory fragmentFitnessReportHistory = new FragmentFitnessReportHistory();
            Bundle bundle = new Bundle();
            bundle.putSerializable("fitnessReportItem", fitnessReportItem);
            fragmentFitnessReportHistory.setArguments(bundle);
            return fragmentFitnessReportHistory;
        }
    }

    public static final void B(CommonMessageDialog commonMessageDialog, FragmentFitnessReportHistory this$0) {
        Intrinsics.checkNotNullParameter(commonMessageDialog, "$commonMessageDialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        commonMessageDialog.dismiss();
        this$0.dismissProgress();
    }

    @JvmStatic
    @NotNull
    public static final FragmentFitnessReportHistory newInstance(@NotNull FitnessReportRes.FitnessReportItem fitnessReportItem) {
        return Companion.newInstance(fitnessReportItem);
    }

    /* JADX WARN: Type inference failed for: r0v10, types: [T, android.net.Uri, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v16, types: [T, android.net.Uri] */
    /* JADX WARN: Type inference failed for: r5v1, types: [T, java.lang.String] */
    public static final void s(String str, Ref.ObjectRef uniqueTitle, String str2, final Ref.ObjectRef uri, final FragmentFitnessReportHistory this$0, Handler handler) {
        OutputStream openOutputStream;
        Intrinsics.checkNotNullParameter(uniqueTitle, "$uniqueTitle");
        Intrinsics.checkNotNullParameter(uri, "$uri");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(handler, "$handler");
        URLConnection openConnection = new URL(String.valueOf(str)).openConnection();
        Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
        HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
        try {
            try {
                httpURLConnection.connect();
                httpURLConnection.getContentLength();
                uniqueTitle.element = ((String) uniqueTitle.element) + str2;
                InputStream inputStream = httpURLConnection.getInputStream();
                Intrinsics.checkNotNullExpressionValue(inputStream, "connection.inputStream");
                if (Build.VERSION.SDK_INT < 29) {
                    String str3 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + '/' + ((String) uniqueTitle.element) + ".pdf";
                    Context requireContext = this$0.requireContext();
                    StringBuilder sb = new StringBuilder();
                    Context context = this$0.getContext();
                    sb.append(context != null ? context.getPackageName() : null);
                    sb.append(".provider");
                    uri.element = FileProvider.getUriForFile(requireContext, sb.toString(), new File(str3));
                    openOutputStream = new FileOutputStream(str3);
                } else {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("_display_name", String.valueOf(uniqueTitle.element));
                    contentValues.put("mime_type", "application/pdf");
                    contentValues.put("relative_path", Environment.DIRECTORY_DOWNLOADS);
                    ContentResolver contentResolver = this$0.requireContext().getContentResolver();
                    ?? insert = contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues);
                    uri.element = insert;
                    Intrinsics.checkNotNull(insert);
                    openOutputStream = contentResolver.openOutputStream((Uri) insert);
                }
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    } else if (openOutputStream != null) {
                        openOutputStream.write(bArr, 0, read);
                    }
                }
                if (openOutputStream != null) {
                    openOutputStream.flush();
                }
                if (openOutputStream != null) {
                    openOutputStream.close();
                }
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            httpURLConnection.disconnect();
            handler.post(new Runnable() { // from class: com.coveiot.android.weeklyreport.fragments.f
                @Override // java.lang.Runnable
                public final void run() {
                    FragmentFitnessReportHistory.t(FragmentFitnessReportHistory.this, uri);
                }
            });
        } catch (Throwable th) {
            httpURLConnection.disconnect();
            throw th;
        }
    }

    public static final void t(FragmentFitnessReportHistory this$0, Ref.ObjectRef uri) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(uri, "$uri");
        this$0.dismissProgress();
        if (this$0.p) {
            this$0.p = false;
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("application/pdf");
            StringBuilder sb = new StringBuilder();
            FitnessReportRes.FitnessReportItem fitnessReportItem = this$0.n;
            sb.append(fitnessReportItem != null ? fitnessReportItem.getTitle() : null);
            sb.append(" Share Subject");
            intent.putExtra("android.intent.extra.SUBJECT", sb.toString());
            intent.putExtra("android.intent.extra.STREAM", (Parcelable) uri.element);
            intent.addFlags(1);
            Context context = this$0.getContext();
            if (context != null) {
                context.startActivity(Intent.createChooser(intent, "Share PDF File"));
                return;
            }
            return;
        }
        this$0.A("Fitness report downloaded successfully!");
    }

    public static final void v(FragmentFitnessReportHistory this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0.getActivity())) {
            this$0.p = true;
            FitnessReportRes.FitnessReportItem fitnessReportItem = this$0.n;
            Intrinsics.checkNotNull(fitnessReportItem);
            String fileUrl = fitnessReportItem.getFileUrl();
            FitnessReportRes.FitnessReportItem fitnessReportItem2 = this$0.n;
            Intrinsics.checkNotNull(fitnessReportItem2);
            this$0.r(fileUrl, fitnessReportItem2.getTitle());
            return;
        }
        this$0.showNoInternetMessage();
    }

    public static final void w(FragmentFitnessReportHistory this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0.getActivity())) {
            FitnessReportRes.FitnessReportItem fitnessReportItem = this$0.n;
            Intrinsics.checkNotNull(fitnessReportItem);
            String fileUrl = fitnessReportItem.getFileUrl();
            FitnessReportRes.FitnessReportItem fitnessReportItem2 = this$0.n;
            Intrinsics.checkNotNull(fitnessReportItem2);
            this$0.r(fileUrl, fitnessReportItem2.getTitle());
            return;
        }
        this$0.showNoInternetMessage();
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [java.io.BufferedInputStream, T] */
    public static final void y(String str, final Ref.ObjectRef inputStream, Handler handler, final FragmentFitnessReportHistory this$0) {
        Intrinsics.checkNotNullParameter(inputStream, "$inputStream");
        Intrinsics.checkNotNullParameter(handler, "$handler");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        URLConnection openConnection = new URL(String.valueOf(str)).openConnection();
        Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
        HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
        try {
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                inputStream.element = new BufferedInputStream(httpURLConnection.getInputStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        handler.post(new Runnable() { // from class: com.coveiot.android.weeklyreport.fragments.e
            @Override // java.lang.Runnable
            public final void run() {
                FragmentFitnessReportHistory.z(FragmentFitnessReportHistory.this, inputStream);
            }
        });
    }

    public static final void z(FragmentFitnessReportHistory this$0, Ref.ObjectRef inputStream) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(inputStream, "$inputStream");
        this$0.dismissProgress();
        this$0.u().pdfView.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        if (inputStream.element != 0) {
            PDFView.Configurator fromStream = this$0.u().pdfView.fromStream((InputStream) inputStream.element);
            if (fromStream != null) {
                fromStream.load();
                return;
            }
            return;
        }
        LogHelper.e(this$0.o, "Failed to load PDF");
    }

    public final void A(String str) {
        Window window;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        final CommonMessageDialog commonMessageDialog = new CommonMessageDialog(requireContext, str, false, true);
        commonMessageDialog.show(getParentFragmentManager(), "");
        Dialog dialog = commonMessageDialog.getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            window.setGravity(80);
        }
        new Handler().postDelayed(new Runnable() { // from class: com.coveiot.android.weeklyreport.fragments.d
            @Override // java.lang.Runnable
            public final void run() {
                FragmentFitnessReportHistory.B(CommonMessageDialog.this, this);
            }
        }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
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

    @NotNull
    public final Bitmap getBitmap() {
        Bitmap bitmap = this.bitmap;
        if (bitmap != null) {
            return bitmap;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bitmap");
        return null;
    }

    public final boolean isShare() {
        return this.p;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        getArguments();
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentFitnessReportHistoryBinding inflate = FragmentFitnessReportHistoryBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, container, false)");
        this.m = inflate;
        Bundle arguments = getArguments();
        Serializable serializable = arguments != null ? arguments.getSerializable("fitnessReportItem") : null;
        this.n = serializable instanceof FitnessReportRes.FitnessReportItem ? (FitnessReportRes.FitnessReportItem) serializable : null;
        return u().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.weeklyreport.activities.ActivityWeeklyReportHistory");
        String string = getString(R.string.fitness_report_history);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.fitness_report_history)");
        ((ActivityWeeklyReportHistory) activity).initToolbar(string);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Object systemService = requireContext().getSystemService("download");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.DownloadManager");
        DownloadManager downloadManager = (DownloadManager) systemService;
        new ProgressDialog(getActivity());
        FitnessReportRes.FitnessReportItem fitnessReportItem = this.n;
        x(fitnessReportItem != null ? fitnessReportItem.getFileUrl() : null);
        FragmentFitnessReportHistoryBinding u = u();
        u.clShare.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.weeklyreport.fragments.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentFitnessReportHistory.v(FragmentFitnessReportHistory.this, view2);
            }
        });
        u.clDownload.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.weeklyreport.fragments.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentFitnessReportHistory.w(FragmentFitnessReportHistory.this, view2);
            }
        });
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [T, java.lang.Object, java.lang.String] */
    public final void r(final String str, String str2) {
        showProgress(true);
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(newSingleThreadExecutor, "newSingleThreadExecutor()");
        final Handler handler = new Handler(Looper.getMainLooper());
        final String format = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss", Locale.getDefault()).format(new Date());
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        FitnessReportRes.FitnessReportItem fitnessReportItem = this.n;
        Intrinsics.checkNotNull(fitnessReportItem);
        ?? title = fitnessReportItem.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "fitnessReportItem!!.title");
        objectRef.element = title;
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        newSingleThreadExecutor.execute(new Runnable() { // from class: com.coveiot.android.weeklyreport.fragments.h
            @Override // java.lang.Runnable
            public final void run() {
                FragmentFitnessReportHistory.s(str, objectRef, format, objectRef2, this, handler);
            }
        });
    }

    public final void setBitmap(@NotNull Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "<set-?>");
        this.bitmap = bitmap;
    }

    public final void setShare(boolean z) {
        this.p = z;
    }

    public final FragmentFitnessReportHistoryBinding u() {
        FragmentFitnessReportHistoryBinding fragmentFitnessReportHistoryBinding = this.m;
        if (fragmentFitnessReportHistoryBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_binding");
            return null;
        }
        return fragmentFitnessReportHistoryBinding;
    }

    public final void x(final String str) {
        showProgress(true);
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(newSingleThreadExecutor, "newSingleThreadExecutor()");
        final Handler handler = new Handler(Looper.getMainLooper());
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        newSingleThreadExecutor.execute(new Runnable() { // from class: com.coveiot.android.weeklyreport.fragments.g
            @Override // java.lang.Runnable
            public final void run() {
                FragmentFitnessReportHistory.y(str, objectRef, handler, this);
            }
        });
    }
}
