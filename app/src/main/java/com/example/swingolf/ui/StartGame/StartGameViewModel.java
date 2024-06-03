package com.example.swingolf.ui.StartGame;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StartGameViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public StartGameViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Start your next game here!");
    }

    public LiveData<String> getText() {
        return mText;
    }
}