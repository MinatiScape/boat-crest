package com.github.barteksc.pdfviewer.listener;

import android.view.MotionEvent;
import com.github.barteksc.pdfviewer.link.LinkHandler;
import com.github.barteksc.pdfviewer.model.LinkTapEvent;
/* loaded from: classes9.dex */
public class Callbacks {

    /* renamed from: a  reason: collision with root package name */
    public OnLoadCompleteListener f7919a;
    public OnErrorListener b;
    public OnPageErrorListener c;
    public OnRenderListener d;
    public OnPageChangeListener e;
    public OnPageScrollListener f;
    public OnDrawListener g;
    public OnDrawListener h;
    public OnTapListener i;
    public OnLongPressListener j;
    public LinkHandler k;

    public void callLinkHandler(LinkTapEvent linkTapEvent) {
        LinkHandler linkHandler = this.k;
        if (linkHandler != null) {
            linkHandler.handleLinkEvent(linkTapEvent);
        }
    }

    public void callOnLoadComplete(int i) {
        OnLoadCompleteListener onLoadCompleteListener = this.f7919a;
        if (onLoadCompleteListener != null) {
            onLoadCompleteListener.loadComplete(i);
        }
    }

    public void callOnLongPress(MotionEvent motionEvent) {
        OnLongPressListener onLongPressListener = this.j;
        if (onLongPressListener != null) {
            onLongPressListener.onLongPress(motionEvent);
        }
    }

    public void callOnPageChange(int i, int i2) {
        OnPageChangeListener onPageChangeListener = this.e;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageChanged(i, i2);
        }
    }

    public boolean callOnPageError(int i, Throwable th) {
        OnPageErrorListener onPageErrorListener = this.c;
        if (onPageErrorListener != null) {
            onPageErrorListener.onPageError(i, th);
            return true;
        }
        return false;
    }

    public void callOnPageScroll(int i, float f) {
        OnPageScrollListener onPageScrollListener = this.f;
        if (onPageScrollListener != null) {
            onPageScrollListener.onPageScrolled(i, f);
        }
    }

    public void callOnRender(int i) {
        OnRenderListener onRenderListener = this.d;
        if (onRenderListener != null) {
            onRenderListener.onInitiallyRendered(i);
        }
    }

    public boolean callOnTap(MotionEvent motionEvent) {
        OnTapListener onTapListener = this.i;
        return onTapListener != null && onTapListener.onTap(motionEvent);
    }

    public OnDrawListener getOnDraw() {
        return this.g;
    }

    public OnDrawListener getOnDrawAll() {
        return this.h;
    }

    public OnErrorListener getOnError() {
        return this.b;
    }

    public void setLinkHandler(LinkHandler linkHandler) {
        this.k = linkHandler;
    }

    public void setOnDraw(OnDrawListener onDrawListener) {
        this.g = onDrawListener;
    }

    public void setOnDrawAll(OnDrawListener onDrawListener) {
        this.h = onDrawListener;
    }

    public void setOnError(OnErrorListener onErrorListener) {
        this.b = onErrorListener;
    }

    public void setOnLoadComplete(OnLoadCompleteListener onLoadCompleteListener) {
        this.f7919a = onLoadCompleteListener;
    }

    public void setOnLongPress(OnLongPressListener onLongPressListener) {
        this.j = onLongPressListener;
    }

    public void setOnPageChange(OnPageChangeListener onPageChangeListener) {
        this.e = onPageChangeListener;
    }

    public void setOnPageError(OnPageErrorListener onPageErrorListener) {
        this.c = onPageErrorListener;
    }

    public void setOnPageScroll(OnPageScrollListener onPageScrollListener) {
        this.f = onPageScrollListener;
    }

    public void setOnRender(OnRenderListener onRenderListener) {
        this.d = onRenderListener;
    }

    public void setOnTap(OnTapListener onTapListener) {
        this.i = onTapListener;
    }
}
