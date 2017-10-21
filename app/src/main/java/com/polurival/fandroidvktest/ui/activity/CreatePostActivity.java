package com.polurival.fandroidvktest.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.polurival.fandroidvktest.R;
import com.polurival.fandroidvktest.common.BaseAdapter;
import com.polurival.fandroidvktest.consts.ApiConstants;
import com.polurival.fandroidvktest.event.SendCommentEventOnSubscribe;
import com.polurival.fandroidvktest.event.SendCreatedPostEventOnSubscribe;
import com.polurival.fandroidvktest.event.UploadFileEventOnSubscribe;
import com.polurival.fandroidvktest.event.UploadPhotoEventOnSubscribe;
import com.polurival.fandroidvktest.model.view.CreatePostTextViewModel;
import com.polurival.fandroidvktest.model.view.attachment.DocAttachmentViewModel;
import com.polurival.fandroidvktest.model.view.attachment.ImageAttachmentViewModel;
import com.polurival.fandroidvktest.ui.dialog.AddAttachmentDialogFragment;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiDocument;
import com.vk.sdk.api.model.VKApiPhoto;
import com.vk.sdk.api.model.VKAttachments;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import droidninja.filepicker.FilePickerConst;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Polurival
 * on 14.10.2017.
 */

public class CreatePostActivity extends BaseActivity {

    private static final String TAG = CreatePostActivity.class.getSimpleName();

    private VKAttachments mAttachments;

    private CreatePostTextViewModel mCreatePostTextViewModel;

    private BaseAdapter mAdapter;
    private RecyclerView mRecyclerView;

    private String mType;
    private int mOwnerId;
    private int mId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mType = bundle.getString("type");
            mOwnerId = bundle.getInt("owner_id");
            mId = bundle.getInt("id");
        }

        mAdapter = new BaseAdapter();

        mRecyclerView = findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        mCreatePostTextViewModel = new CreatePostTextViewModel();

        mAdapter.insertItem(mCreatePostTextViewModel);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_close_white_24dp);
        getSupportActionBar().setTitle(R.string.new_message_title);

        mAttachments = new VKAttachments();

        getFab().hide();
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.activity_create_post;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_post, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;

            case R.id.action_attach:
                attach();
                break;

            case R.id.action_post:
                Log.d(TAG, "create post");
                post();
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void post() {
        if (mCreatePostTextViewModel.getMessage() == null) {
            Toast.makeText(this, R.string.add_message_text, Toast.LENGTH_LONG).show();
            return;
        }

        ObservableOnSubscribe<VKResponse> o;
        // в зависимости от типа создаваемого сообщения инициализируем переменную объектом
        // соответствующего события
        if (mType != null && mType.equals("comment")) {
            o = new SendCommentEventOnSubscribe(mOwnerId, mId, mCreatePostTextViewModel.getMessage(), mAttachments);
        } else {
            o = new SendCreatedPostEventOnSubscribe(ApiConstants.CURRENT_GROUP_ID,
                    mCreatePostTextViewModel.getMessage(), mAttachments);
        }

        Observable.create(o)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VKResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull VKResponse vkResponse) {
                        postResult(vkResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();

                        Toast.makeText(CreatePostActivity.this, R.string.message_not_published, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    // закрытие активити и передача сообщения об успехе в главное активити
    private void postResult(VKResponse vkResponse) {
        Bundle conData = new Bundle();
        conData.putString("results", "Thanks Thanks");

        Intent intent = new Intent();
        intent.putExtras(conData);

        setResult(RESULT_OK, intent);
        finish();
    }

    private void attach() {
        AddAttachmentDialogFragment dialog = new AddAttachmentDialogFragment();
        dialog.show(getFragmentManager(), AddAttachmentDialogFragment.class.getSimpleName());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "res = " + resultCode);

        // в зависимости от типа элементов, полученных от библиотеки filepicker, вызываем loadFile или loadPhoto
        switch (requestCode) {
            case FilePickerConst.REQUEST_CODE_PHOTO:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    Log.d(TAG, "onActivityResult: photo pick");
                    List<String> photoPaths = new ArrayList<>();
                    photoPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA));

                    for (String photoPath : photoPaths) {
                        loadPhoto(new ImageAttachmentViewModel(photoPath));
                    }
                }
                break;
            case FilePickerConst.REQUEST_CODE_DOC:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    List<String> docPaths = new ArrayList<>();
                    docPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_DOCS));

                    for (String docPath : docPaths) {
                        File file = new File(docPath);
                        loadDoc(new DocAttachmentViewModel(file));
                    }
                }
        }
    }

    private void loadPhoto(final ImageAttachmentViewModel imageViewModel) {

        getProgressBar().setVisibility(View.VISIBLE);

        Observable.create(new UploadPhotoEventOnSubscribe(imageViewModel.getPhotoUrl()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VKApiPhoto>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull VKApiPhoto vkApiPhoto) {
                        mAttachments.add(vkApiPhoto);
                        mAdapter.insertItem(imageViewModel);
                        Log.d(TAG, "attach photo: " + vkApiPhoto.photo_130);
                        Log.d(TAG, "attach completed");
                        Toast.makeText(CreatePostActivity.this, R.string.loading_completed, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        getProgressBar().setVisibility(View.GONE);
                        Toast.makeText(CreatePostActivity.this, R.string.loading_failed, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {
                        getProgressBar().setVisibility(View.GONE);
                    }
                });
    }

    private void loadDoc(final DocAttachmentViewModel docViewModel) {

        getProgressBar().setVisibility(View.VISIBLE);

        Observable.create(new UploadFileEventOnSubscribe(docViewModel.getFile()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VKApiDocument>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull VKApiDocument vkApiDocument) {
                        mAttachments.add(vkApiDocument);
                        mAdapter.insertItem(docViewModel);
                        Log.d(TAG, "attach completed");
                        Toast.makeText(CreatePostActivity.this, R.string.loading_completed, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        getProgressBar().setVisibility(View.GONE);
                        Toast.makeText(CreatePostActivity.this, R.string.loading_failed, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {
                        getProgressBar().setVisibility(View.GONE);
                    }
                });
    }
}
