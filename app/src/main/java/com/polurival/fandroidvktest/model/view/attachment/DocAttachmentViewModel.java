package com.polurival.fandroidvktest.model.view.attachment;

import android.view.View;

import com.polurival.fandroidvktest.common.utils.Utils;
import com.polurival.fandroidvktest.model.attachment.doc.Doc;
import com.polurival.fandroidvktest.model.view.BaseViewModel;
import com.polurival.fandroidvktest.ui.view.holder.BaseViewHolder;
import com.polurival.fandroidvktest.ui.view.holder.attachment.DocAttachmentHolder;

import java.io.File;

/**
 * Created by Polurival
 * on 04.10.2017.
 */

public class DocAttachmentViewModel extends BaseViewModel {

    private String mUrl;

    private File mFile;

    private String mTitle;
    private String mSize;
    private String mExt;

    public boolean needClick = true;


    public DocAttachmentViewModel(Doc doc) {
        if (doc.getTitle().equals("")) {
            mTitle = "Document";
        } else {
            mTitle = Utils.removeExtFromString(doc.getTitle());
        }

        mUrl = doc.getUrl();

        mSize = Utils.formatSize(doc.getSize());

        mExt = "." + doc.getExt();
    }

    public DocAttachmentViewModel(File file) {
        String filename = file.getName();
        String filenameArray[] = filename.split("\\.");
        String extension = filenameArray[filenameArray.length-1];

        mFile = file;
        mTitle = file.getName();

        mSize = Utils.formatSize(file.length());

        mExt = "." + extension;

        needClick = false;
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentDoc;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new DocAttachmentHolder(view);
    }

    public String getUrl() {
        return mUrl;
    }

    public File getFile() {
        return mFile;
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
}
