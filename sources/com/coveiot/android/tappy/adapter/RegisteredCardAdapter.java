package com.coveiot.android.tappy.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.coveiot.android.tappy.R;
import com.coveiot.android.tappy.model.PaymentInstrumentTokenStatus;
import com.coveiot.android.tappy.model.RegCardBeanInfo;
import com.coveiot.android.tappy.model.RegStrapBeanInfo;
import com.coveiot.android.tappy.utils.Action;
import com.coveiot.utils.utility.AppUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class RegisteredCardAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6001a;
    @NotNull
    public final CardItemClickListener b;
    @NotNull
    public final ArrayList<RegStrapBeanInfo> c;

    /* loaded from: classes7.dex */
    public interface CardItemClickListener {
        void onDeleteClicked(int i);

        void onResumeClicked(int i);

        void onSuspendClicked(int i);
    }

    /* loaded from: classes7.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f6002a;
        @NotNull
        public final Button b;
        @NotNull
        public final Button c;
        @NotNull
        public final Button d;
        @NotNull
        public final ImageView e;
        @NotNull
        public final TextView f;
        public final TextView g;
        public final TextView h;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull RegisteredCardAdapter registeredCardAdapter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            View findViewById = view.findViewById(R.id.strap_name_1);
            Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(R.id.strap_name_1)");
            this.f6002a = (TextView) findViewById;
            View findViewById2 = view.findViewById(R.id.suspend_btn);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "view.findViewById(R.id.suspend_btn)");
            this.b = (Button) findViewById2;
            View findViewById3 = view.findViewById(R.id.resume_btn);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "view.findViewById(R.id.resume_btn)");
            this.c = (Button) findViewById3;
            View findViewById4 = view.findViewById(R.id.delete_btn);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "view.findViewById(R.id.delete_btn)");
            this.d = (Button) findViewById4;
            View findViewById5 = view.findViewById(R.id.card_Image);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "view.findViewById(R.id.card_Image)");
            this.e = (ImageView) findViewById5;
            View findViewById6 = view.findViewById(R.id.tvLast4);
            Intrinsics.checkNotNullExpressionValue(findViewById6, "view.findViewById(R.id.tvLast4)");
            this.f = (TextView) findViewById6;
            this.g = (TextView) view.findViewById(R.id.tvAccountHolderName);
            this.h = (TextView) view.findViewById(R.id.tvValidDate);
        }

        public final TextView getAccountHolderName() {
            return this.g;
        }

        @NotNull
        public final Button getDeleteCard() {
            return this.d;
        }

        @NotNull
        public final ImageView getImageCard() {
            return this.e;
        }

        @NotNull
        public final TextView getLast4() {
            return this.f;
        }

        @NotNull
        public final Button getResumeCard() {
            return this.c;
        }

        @NotNull
        public final TextView getStrapName() {
            return this.f6002a;
        }

        @NotNull
        public final Button getSuspendCard() {
            return this.b;
        }

        public final TextView getValidateDate() {
            return this.h;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.adapter.RegisteredCardAdapter$downloadPdfAndConvertToImage$thread$1$2", f = "RegisteredCardAdapter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Bitmap $bitmap;
        public final /* synthetic */ ViewHolder $holder;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(ViewHolder viewHolder, Bitmap bitmap, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$holder = viewHolder;
            this.$bitmap = bitmap;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$holder, this.$bitmap, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                this.$holder.getImageCard().setImageBitmap(this.$bitmap);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public RegisteredCardAdapter(@NotNull Context context, @NotNull CardItemClickListener clickListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(clickListener, "clickListener");
        this.f6001a = context;
        this.b = clickListener;
        this.c = new ArrayList<>();
    }

    public static final void f(String pdfUrl, ViewHolder holder) {
        Bitmap a2;
        Intrinsics.checkNotNullParameter(pdfUrl, "$pdfUrl");
        Intrinsics.checkNotNullParameter(holder, "$holder");
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
            a2 = RegisteredCardAdapterKt.a(absolutePath);
            if (a2 != null) {
                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new a(holder, a2, null), 2, null);
            } else {
                Log.i("RegisteredCardAdapter", "Image conversion failed");
            }
            createTempFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final void g(RegisteredCardAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.b.onSuspendClicked(i);
    }

    public static final void h(RegisteredCardAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.b.onDeleteClicked(i);
    }

    public static final void i(RegisteredCardAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.b.onResumeClicked(i);
    }

    public final void e(final String str, final ViewHolder viewHolder) {
        try {
            if (m.endsWith(str, ".pdf", true)) {
                new Thread(new Runnable() { // from class: com.coveiot.android.tappy.adapter.d
                    @Override // java.lang.Runnable
                    public final void run() {
                        RegisteredCardAdapter.f(str, viewHolder);
                    }
                }).start();
            } else {
                Log.i("RegisteredCardAdapter", "Invalid file format: " + str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NotNull
    public final CardItemClickListener getClickListener() {
        return this.b;
    }

    @NotNull
    public final Context getContext() {
        return this.f6001a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }

    public final void updateBtnResumeSuspend(@NotNull Action action, int i) {
        Intrinsics.checkNotNullParameter(action, "action");
        RegCardBeanInfo regCardBeanInfo = this.c.get(i).getRegCardBeanInfo();
        if (regCardBeanInfo != null) {
            String name = action.name();
            if (Intrinsics.areEqual(name, "resume")) {
                regCardBeanInfo.setStatus(PaymentInstrumentTokenStatus.ACTIVE.getStatus());
            } else if (Intrinsics.areEqual(name, "suspend")) {
                regCardBeanInfo.setStatus(PaymentInstrumentTokenStatus.SUSPENDED.getStatus());
            }
            notifyItemChanged(i);
        }
    }

    public final void updateData(@NotNull ArrayList<RegStrapBeanInfo> newList) {
        Intrinsics.checkNotNullParameter(newList, "newList");
        this.c.clear();
        this.c.addAll(newList);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, final int i) {
        RegStrapBeanInfo regStrapBeanInfo;
        Intrinsics.checkNotNullParameter(holder, "holder");
        RegStrapBeanInfo regStrapBeanInfo2 = this.c.get(i);
        Intrinsics.checkNotNullExpressionValue(regStrapBeanInfo2, "registerCardList[position]");
        RegStrapBeanInfo regStrapBeanInfo3 = regStrapBeanInfo2;
        RegCardBeanInfo regCardBeanInfo = regStrapBeanInfo3.getRegCardBeanInfo();
        Intrinsics.checkNotNull(regCardBeanInfo);
        boolean areEqual = Intrinsics.areEqual(regCardBeanInfo.getStatus(), PaymentInstrumentTokenStatus.ACTIVE.getStatus());
        TextView strapName = holder.getStrapName();
        Context context = this.f6001a;
        int i2 = R.string.linked_with;
        RegCardBeanInfo regCardBeanInfo2 = regStrapBeanInfo3.getRegCardBeanInfo();
        Intrinsics.checkNotNull(regCardBeanInfo2);
        strapName.setText(context.getString(i2, regCardBeanInfo2.getFriendlyName()));
        TextView last4 = holder.getLast4();
        Context context2 = this.f6001a;
        int i3 = R.string.last4_card_number;
        RegCardBeanInfo regCardBeanInfo3 = regStrapBeanInfo3.getRegCardBeanInfo();
        Intrinsics.checkNotNull(regCardBeanInfo3);
        last4.setText(context2.getString(i3, regCardBeanInfo3.getLast4()));
        RegCardBeanInfo regCardBeanInfo4 = regStrapBeanInfo3.getRegCardBeanInfo();
        Intrinsics.checkNotNull(regCardBeanInfo4);
        String expiryDate = regCardBeanInfo4.getExpiryDate();
        RegCardBeanInfo regCardBeanInfo5 = null;
        if (!(expiryDate == null || expiryDate.length() == 0)) {
            RegCardBeanInfo regCardBeanInfo6 = regStrapBeanInfo3.getRegCardBeanInfo();
            Intrinsics.checkNotNull(regCardBeanInfo6);
            String expiryDate2 = regCardBeanInfo6.getExpiryDate();
            Integer valueOf = expiryDate2 != null ? Integer.valueOf(expiryDate2.length()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.intValue() > 1) {
                TextView validateDate = holder.getValidateDate();
                RegCardBeanInfo regCardBeanInfo7 = regStrapBeanInfo3.getRegCardBeanInfo();
                Intrinsics.checkNotNull(regCardBeanInfo7);
                String expiryDate3 = regCardBeanInfo7.getExpiryDate();
                Intrinsics.checkNotNull(expiryDate3);
                String substring = expiryDate3.substring(2);
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
                validateDate.setText(substring);
            }
        }
        if (areEqual) {
            holder.getResumeCard().setVisibility(8);
            holder.getSuspendCard().setVisibility(0);
        } else {
            holder.getSuspendCard().setVisibility(8);
            holder.getResumeCard().setVisibility(0);
        }
        ArrayList<RegStrapBeanInfo> arrayList = this.c;
        if (arrayList != null && (regStrapBeanInfo = arrayList.get(i)) != null) {
            regCardBeanInfo5 = regStrapBeanInfo.getRegCardBeanInfo();
        }
        Intrinsics.checkNotNull(regCardBeanInfo5);
        String cardArtImageUrl = regCardBeanInfo5.getCardArtImageUrl();
        if (AppUtils.isNetConnected(this.f6001a)) {
            if (cardArtImageUrl != null && m.endsWith(cardArtImageUrl, ".png", true)) {
                try {
                    Glide.with(this.f6001a).m30load(cardArtImageUrl).into(holder.getImageCard());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (cardArtImageUrl != null) {
                e(cardArtImageUrl, holder);
            }
        }
        holder.getSuspendCard().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.adapter.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RegisteredCardAdapter.g(RegisteredCardAdapter.this, i, view);
            }
        });
        holder.getDeleteCard().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.adapter.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RegisteredCardAdapter.h(RegisteredCardAdapter.this, i, view);
            }
        });
        holder.getResumeCard().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.adapter.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RegisteredCardAdapter.i(RegisteredCardAdapter.this, i, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_management_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new ViewHolder(this, view);
    }
}
