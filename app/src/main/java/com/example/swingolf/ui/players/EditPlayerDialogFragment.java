package com.example.swingolf.ui.players;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.swingolf.R;
import com.example.swingolf.dataModel.AppDatabase;
import com.example.swingolf.dataModel.DatabaseModule;
import com.example.swingolf.dataModel.Player;

public class EditPlayerDialogFragment extends DialogFragment {

    private static final String ARG_PLAYER = "player";
    private Player player;
    private EditText editTextPlayerName;
    private AppDatabase database;

    public static EditPlayerDialogFragment newInstance(Player player) {
        EditPlayerDialogFragment fragment = new EditPlayerDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PLAYER, player);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            player = (Player) getArguments().getSerializable(ARG_PLAYER);
        }
        database = DatabaseModule.getInstance(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eddit_player, container, false);

        editTextPlayerName = view.findViewById(R.id.editTextPlayerName);
        editTextPlayerName.setText(player.name);

        Button btnSave = view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(v -> savePlayer());

        return view;
    }

    private void savePlayer() {
        String newName = editTextPlayerName.getText().toString().trim();
        if (!TextUtils.isEmpty(newName)) {
            player.name = newName;
            database.playerInDao().update(player);
            getParentFragmentManager().setFragmentResult("playerUpdated", new Bundle());
            dismiss();
        } else {
            editTextPlayerName.setError("Name cannot be empty");
        }
    }
}

