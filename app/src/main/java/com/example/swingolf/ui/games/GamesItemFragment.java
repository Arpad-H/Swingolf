package com.example.swingolf.ui.games;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.swingolf.dataModel.AppDatabase;
import com.example.swingolf.dataModel.DatabaseModule;
import com.example.swingolf.dataModel.Game;
import com.example.swingolf.dataModel.Player;
import com.example.swingolf.databinding.FragmentGamesItemBinding;

public class GamesItemFragment extends Fragment {
    AppDatabase database;

    private static final String ARG_GAME = "game";
    private Game game;
    FragmentGamesItemBinding binding;

    public GamesItemFragment() {
    }

    public static GamesItemFragment newInstance(Game game) {
        GamesItemFragment fragment = new GamesItemFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_GAME, game);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            game = (Game) getArguments().getSerializable(ARG_GAME);
        }
    }

    @SuppressLint("DefaultLocale")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        database = DatabaseModule.getInstance(getContext());
        binding = FragmentGamesItemBinding.inflate(inflater, container, false);
        binding.gameID.setText(String.format("%d", game.id));
        binding.gamePlace.setText(game.courtName);
        String winnername = "niemand";
        if (game.winner != Integer.MAX_VALUE) {
            Player winner = database.playerInDao().getPlayerById(game.winner);
            winnername = winner.name;
        }
        binding.gameWinner.setText(winnername);
        binding.gameDate.setText(game.date);
        return binding.getRoot();
    }
}