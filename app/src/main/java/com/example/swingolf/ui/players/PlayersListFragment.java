package com.example.swingolf.ui.players;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.swingolf.R;
import com.example.swingolf.dataModel.AppDatabase;
import com.example.swingolf.dataModel.DatabaseModule;
import com.example.swingolf.dataModel.Player;
import com.example.swingolf.databinding.FragmentPlayersListBinding;

import com.example.swingolf.ui.StartGame.StartGameViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PlayersListFragment extends Fragment {


    AppDatabase database;

    private FragmentPlayersListBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPlayersListBinding.inflate(inflater, container, false);

        setupAddPlayerButton();
        return binding.getRoot();
    }

    private void setupAddPlayerButton() {
        binding.btnAddPlayer.setOnClickListener(v -> {
            Log.d("PlayersFragment", "Add player clicked");
            showInputDialog();
        });
    }

    private void showInputDialog() {
        InputDialogFragment dialogFragment = new InputDialogFragment();
        dialogFragment.show(getParentFragmentManager(), "InputDialogFragment");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        database = DatabaseModule.getInstance(getContext());
        setupFragmentResultListeners();
        updatePlayerList();
    }

    private void setupFragmentResultListeners() {
        getParentFragmentManager().setFragmentResultListener("playerAdded", this, (requestKey, result) -> {
            Log.d("PlayersFragment", "Player added, updating list");
            updatePlayerList();
        });

        getParentFragmentManager().setFragmentResultListener("playerUpdated", this, (requestKey, result) -> {
            Log.d("PlayersFragment", "Player updated, updating list");
            updatePlayerList();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("PlayersFragment", "onResume");
        updatePlayerList();
    }

    private void updatePlayerList() {
        List<Player> players = database.playerInDao().getAllPlayers();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        clearExistingFragments(transaction);
        addPlayerFragments(players, transaction);
        transaction.commit();
    }

    private void clearExistingFragments(FragmentTransaction transaction) {
        for (Fragment fragment : getChildFragmentManager().getFragments()) {
            transaction.remove(fragment);
        }
    }

    private void addPlayerFragments(List<Player> players, FragmentTransaction transaction) {
        for (Player player : players) {
            PlayerItemFragment fragment = PlayerItemFragment.newInstance(player);
            transaction.add(binding.playersContainer.getId(), fragment);
        }
    }
}
