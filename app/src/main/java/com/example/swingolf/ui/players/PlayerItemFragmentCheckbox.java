package com.example.swingolf.ui.players;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.swingolf.R;
import com.example.swingolf.dataModel.Player;

public class PlayerItemFragmentCheckbox extends Fragment{

    private static final String ARG_PLAYER_NAME = "player_name";
    private static final String ARG_PLAYER_ID = "player_id";

    public static PlayerItemFragmentCheckbox newInstance(Player player) {
        PlayerItemFragmentCheckbox fragment = new PlayerItemFragmentCheckbox();
        Bundle args = new Bundle();
        args.putString(ARG_PLAYER_NAME, player.name);
        args.putInt(ARG_PLAYER_ID, player.id);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_item_checkbox, container, false);
        CheckBox playerNameCheckBox = view.findViewById(R.id.checkBox);

        if (getArguments() != null) {
            String playerName = getArguments().getString(ARG_PLAYER_NAME);
            int playerId = getArguments().getInt(ARG_PLAYER_ID);
            playerNameCheckBox.setText(playerName);
            playerNameCheckBox.setId(playerId);
        }

        return view;
    }
}
