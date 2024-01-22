package com.clevertap.android.sdk.inbox;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.R;
import java.util.ArrayList;
/* loaded from: classes2.dex */
public class j extends RecyclerView.Adapter {

    /* renamed from: a  reason: collision with root package name */
    public CTInboxListViewFragment f2638a;
    public ArrayList<CTInboxMessage> b;

    /* loaded from: classes2.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f2639a;

        static {
            int[] iArr = new int[CTInboxMessageType.values().length];
            f2639a = iArr;
            try {
                iArr[CTInboxMessageType.SimpleMessage.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2639a[CTInboxMessageType.IconMessage.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2639a[CTInboxMessageType.CarouselMessage.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2639a[CTInboxMessageType.CarouselImageMessage.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public j(ArrayList<CTInboxMessage> arrayList, CTInboxListViewFragment cTInboxListViewFragment) {
        Logger.v("CTInboxMessageAdapter: messages=" + arrayList);
        this.b = arrayList;
        this.f2638a = cTInboxListViewFragment;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a */
    public CTInboxBaseMessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return null;
                    }
                    return new com.clevertap.android.sdk.inbox.a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inbox_carousel_layout, viewGroup, false));
                }
                return new b(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inbox_carousel_text_layout, viewGroup, false));
            }
            return new c(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inbox_icon_message_layout, viewGroup, false));
        }
        return new k(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inbox_simple_message_layout, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        int i2 = a.f2639a[this.b.get(i).getType().ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    return i2 != 4 ? -1 : 3;
                }
                return 2;
            }
            return 1;
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((CTInboxBaseMessageViewHolder) viewHolder).c(this.b.get(i), this.f2638a, i);
    }
}
