package com.example.swingolf.ui.players;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.swingolf.R;
import com.example.swingolf.dataModel.Player;

public class PlayerItemFragment extends Fragment {

    private static final String ARG_PLAYER_NAME = "player_name";

    public static PlayerItemFragment newInstance(Player player) {
        PlayerItemFragment fragment = new PlayerItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PLAYER_NAME, player.name);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_item, container, false);
        TextView playerNameTextView = view.findViewById(R.id.playerName);

        if (getArguments() != null) {
            String playerName = getArguments().getString(ARG_PLAYER_NAME);
            playerNameTextView.setText(playerName);
        }

        return view;
    }
}

