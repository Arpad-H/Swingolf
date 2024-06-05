package com.example.swingolf.ui.players;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.swingolf.dataModel.AppDatabase;
import com.example.swingolf.dataModel.DatabaseModule;
import com.example.swingolf.databinding.FragmentAddPlayerBinding;
import com.example.swingolf.dataModel.Player;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class InputDialogFragment extends DialogFragment {


    AppDatabase database;

    private FragmentAddPlayerBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddPlayerBinding.inflate(inflater, container, false);
        database = DatabaseModule.getInstance(getContext());
        setupSubmitButton();
        return binding.getRoot();
    }

    private void setupSubmitButton() {
        binding.buttonSubmitNewPlayer.setOnClickListener(v -> {
            submitPlayer();
        });
    }

    private void submitPlayer() {
        String name = binding.editTextName.getText().toString().trim();
        if (name.isEmpty()) {
            Log.e("InputDialogFragment", "Player name is empty");
            return;
        }

        try {
            database.playerInDao().insert(new Player(name));
            Bundle result = new Bundle();
            getParentFragmentManager().setFragmentResult("playerAdded", result);
            dismiss();
        } catch (Exception e) {
            Log.e("InputDialogFragment", "Failed to insert player", e);
        }
    }
}
