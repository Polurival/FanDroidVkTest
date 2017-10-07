package com.polurival.fandroidvktest.model.view.attachment;

import android.view.View;

import com.polurival.fandroidvktest.common.utils.Utils;
import com.polurival.fandroidvktest.model.attachment.doc.Doc;
import com.polurival.fandroidvktest.model.attachment.doc.Size;
import com.polurival.fandroidvktest.model.view.BaseViewModel;
import com.polurival.fandroidvktest.ui.view.holder.BaseViewHolder;
import com.polurival.fandroidvktest.ui.view.holder.attachment.DocImageAttachmentHolder;

import java.util.List;

/**
 * Created by Polurival
 * on 04.10.2017.
 */

public class DocImageAttachmentViewModel extends BaseViewModel {

    private String mTitle;
    private String mSize;
    private String mExt;

    private String mImage;

    private String mUrl;


    public DocImageAttachmentViewModel(Doc doc) {
        if ("".equals(doc.getTitle())) {
            mTitle = "Document";
        } else {
            mTitle = Utils.removeExtFromString(doc.getTitle());
        }

        mUrl = doc.getUrl();

        mSize = Utils.formatSize(doc.getSize());

        mExt = "." + doc.getExt();

        List<Size> sizes = doc.getPreview().getPhotoPreview().getSizes();
        mImage = sizes.get(sizes.size() - 1).getSrc();
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentDocImage;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new DocImageAttachmentHolder(view);
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSize() {
        return mSize;
    }

    public String getExt() {
        return mExt;
    }

    public String getImage() {
        return mImage;
    }

    public String getUrl() {
        return mUrl;
    }
}
