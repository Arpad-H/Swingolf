package com.example.swingolf.ui.StartGame;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.swingolf.R;
import com.example.swingolf.dataModel.AppDatabase;
import com.example.swingolf.dataModel.DatabaseModule;
import com.example.swingolf.dataModel.Game;
import com.example.swingolf.dataModel.Player;
import com.example.swingolf.ui.players.PlayerItemFragmentCheckbox;

import java.util.ArrayList;

public class StartGameNewFragment extends DialogFragment {
    AppDatabase database;

    private EditText editOrt;
    private EditText editDate;
    private EditText editNumber;
    private Spinner spinnerStrokes;
    private static final String ARG_PLAYER_ID = "player_id";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_game, container, false);
        database = DatabaseModule.getInstance(getContext());
        ArrayList<PlayerItemFragmentCheckbox> players = new ArrayList<>();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        for (Player player: database.playerInDao().getAllPlayers()) {
            PlayerItemFragmentCheckbox fragment = PlayerItemFragmentCheckbox.newInstance(player);
            transaction.add(R.id.newGamePlayer, fragment);
            players.add(fragment);
        }
        transaction.commit();

        editOrt = view.findViewById(R.id.editOrt);
        editDate = view.findViewById(R.id.editDatum);
        spinnerStrokes = view.findViewById(R.id.spinner);
        editNumber = view.findViewById(R.id.editAnzahlBahnen);

        Button buttonSubmit = view.findViewById(R.id.buttonSubmitNewGame);

        database = DatabaseModule.getInstance(getContext());

        buttonSubmit.setOnClickListener(v -> {
            String name = editOrt.getText().toString();
            String date = editDate.getText().toString();
            ArrayList<Player> players_list = new ArrayList<>();
            for (PlayerItemFragmentCheckbox fragment: players) {
                assert fragment.getArguments() != null;
                int playerID = fragment.getArguments().getInt(ARG_PLAYER_ID);
                View parent = getView();
                assert parent != null;
                CheckBox checkBox = parent.findViewById(playerID);
                if (checkBox.isChecked()) {
                    players_list.add(database.playerInDao().getPlayerById(playerID));
                }
            }
            int number = Integer.parseInt(editNumber.getText().toString());
            int strokes = Integer.parseInt(spinnerStrokes.getSelectedItem().toString());
            if (database == null) {
                Log.d("InputDialogFragment", "database is null");
            } else {
                database.gameInDao().insert(new Game(name, number, strokes, date, players_list));
                getParentFragmentManager().setFragmentResult("newGameStarted", new Bundle());
                dismiss();
            }

        });

        return view;
    }

}
