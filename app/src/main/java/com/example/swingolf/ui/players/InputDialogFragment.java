package com.example.swingolf.ui.players;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.room.Database;
import androidx.room.Room;

import com.example.swingolf.R;
import com.example.swingolf.dataModel.AppDatabase;
import com.example.swingolf.dataModel.DatabaseModule;
import com.example.swingolf.dataModel.Player;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.qualifiers.ApplicationContext;

@AndroidEntryPoint
public class InputDialogFragment extends DialogFragment {

    //@Inject
    AppDatabase database;

    private EditText editTextName;
    private Button buttonSubmit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_player, container, false);

        editTextName = view.findViewById(R.id.editTextName);
        buttonSubmit = view.findViewById(R.id.buttonSubmitNewPlayer);

        database = DatabaseModule.getInstance(getContext());

        buttonSubmit.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            if (database == null) {
                Log.d("InputDialogFragment", "database is null");
            } else {
                database.playerInDao().insert(new Player(name));
                getParentFragmentManager().setFragmentResult("playerAdded", new Bundle());
                dismiss();
            }

        });

        return view;
    }
}
