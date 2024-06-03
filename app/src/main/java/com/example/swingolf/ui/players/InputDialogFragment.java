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

import com.example.swingolf.R;

public class InputDialogFragment extends DialogFragment {

    private EditText editTextName;
    private Button buttonSubmit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_player, container, false);

        editTextName = view.findViewById(R.id.editTextName);
        buttonSubmit = view.findViewById(R.id.buttonSubmitNewPlayer);

        buttonSubmit.setOnClickListener(v -> {
            String name = editTextName.getText().toString();

            dismiss();
        });

        return view;
    }
}
