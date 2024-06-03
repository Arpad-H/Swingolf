package com.example.swingolf.ui.players;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.swingolf.R;
import com.example.swingolf.databinding.FragmentPlayersBinding;
import com.example.swingolf.ui.StartGame.StartGameViewModel;

public class PlayersFragment extends Fragment {
    private FragmentPlayersBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StartGameViewModel homeViewModel =
                new ViewModelProvider(this).get(StartGameViewModel.class);

        View view = inflater.inflate(R.layout.fragment_players, container, false);


        binding = FragmentPlayersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button openGamesFragmentButton = binding.btnAddPlayer; // view.findViewById(R.id.btnAddPlayer);
        openGamesFragmentButton.setOnClickListener(v -> {
            Log.d("PlayersFragment", "openGamesFragmentButton clicked");
            InputDialogFragment dialogFragment = new InputDialogFragment();
            dialogFragment.show(getParentFragmentManager(), "InputDialogFragment");
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
