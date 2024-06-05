package com.example.swingolf.ui.StartGame;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.swingolf.dataModel.AppDatabase;
import com.example.swingolf.dataModel.DatabaseModule;
import com.example.swingolf.databinding.FragmentStartGameBinding;
import com.example.swingolf.ui.players.InputDialogFragment;

public class StartGameFragment extends Fragment {
    AppDatabase database;

    private FragmentStartGameBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StartGameViewModel homeViewModel =
                new ViewModelProvider(this).get(StartGameViewModel.class);

        database = DatabaseModule.getInstance(getContext());
        binding = FragmentStartGameBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button newGameButton = binding.btnNewGame; // view.findViewById(R.id.btnAddPlayer);
        newGameButton.setOnClickListener(v -> {
            Log.d("StartGameFragment", " clicked");
            StartGameNewFragment dialogFragment = new StartGameNewFragment();
            dialogFragment.show(getParentFragmentManager(), "StartGameNewFragment");
        });

        getParentFragmentManager().setFragmentResultListener("newGameStarted", this, (requestKey, result) -> {
            Log.d("StartGameFragment", "New Game started, updating Screen");
            newGameStarted();
        });
        return root;
    }

    private void newGameStarted() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}