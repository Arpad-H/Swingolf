package com.example.swingolf.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.swingolf.R;
import com.example.swingolf.ui.games.ScorecardActivity;

public class NumberSelectionDialog extends Dialog {

    private int numberOfButtons;
    private NumberSelectionDialogListener listener;

    public interface NumberSelectionDialogListener {
        void onButtonClicked(int number);
    }
    public NumberSelectionDialog(@NonNull Context context, int numberOfButtons, ScorecardActivity scorecardActivity) {
        super(context);
        this.numberOfButtons = numberOfButtons;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_number_selection);

        LinearLayout layout = findViewById(R.id.buttonLayout);

        // Dynamically add numbered buttons to the layout
        for (int i = 1; i <= numberOfButtons; i++) {
            Button button = new Button(getContext());
            button.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            button.setText(String.valueOf(i));
            int finalI = i;

            button.setOnClickListener(view -> {
                if (listener != null) {
                    listener.onButtonClicked(finalI);
                }
                Log.d("NumberSelectionDialog", "Button clicked: " + finalI);
                dismiss();
            });
            layout.addView(button);
        }
    }
}
