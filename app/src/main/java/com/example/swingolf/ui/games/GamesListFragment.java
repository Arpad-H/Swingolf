package com.example.swingolf.ui.games;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.swingolf.dataModel.AppDatabase;
import com.example.swingolf.dataModel.DatabaseModule;
import com.example.swingolf.dataModel.Game;
import com.example.swingolf.databinding.FragmentGamesBinding;

import java.util.List;

public class GamesListFragment extends Fragment {

    private FragmentGamesBinding binding;
    AppDatabase database;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentGamesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void updateGamesList() {
        List<Game> games = database.gameInDao().getAllGames();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        clearExistingFragments(transaction);
        addGameFragments(games, transaction);
        transaction.commit();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        database = DatabaseModule.getInstance(getContext());
        updateGamesList();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("PlayersFragment", "onResume");
        updateGamesList();
    }
    private void clearExistingFragments(FragmentTransaction transaction) {
        for (Fragment fragment : getChildFragmentManager().getFragments()) {
            transaction.remove(fragment);
        }
    }
    private void addGameFragments(List<Game> games, FragmentTransaction transaction) {
        for (Game game : games) {
            GamesItemFragment fragment = GamesItemFragment.newInstance(game);
            transaction.add(binding.gamesContainer.getId(), fragment);  // Assuming playersContainer is an ID of a container in fragment_players.xml
        }
    }

}