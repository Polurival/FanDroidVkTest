package com.polurival.fandroidvktest.model.view;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.polurival.fandroidvktest.R;
import com.polurival.fandroidvktest.ui.view.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Polurival
 * on 14.10.2017.
 *
 * модель для текстового поля в списке элементов создаваемой записи или комментария
 */

public class CreatePostTextViewModel extends BaseViewModel {

    private String mMessage;

    public CreatePostTextViewModel() {
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.CreatePostText;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new NewPostTextViewHolder(view);
    }

    public static class NewPostTextViewHolder extends BaseViewHolder<CreatePostTextViewModel> {

        @BindView(R.id.et_message)
        EditText message;

        public NewPostTextViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindViewHolder(CreatePostTextViewModel createPostTextViewModel) {
            message.setText(createPostTextViewModel.getMessage());

            message.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                    createPostTextViewModel.setMessage(charSequence.toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });
        }

        @Override
        public void unbindViewHolder() {
        }
    }
}
