package com.example.lucrarea_3;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProgressViewModel extends ViewModel {

    private final MutableLiveData<Integer> progress = new MutableLiveData<>(0);

    public LiveData<Integer> getProgress() {
        return progress;
    }

    public void startTask() {
        new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progress.postValue(i);
            }
        }).start();
    }
}
