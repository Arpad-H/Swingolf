package com.example.swingolf.ui.games;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.appcompat.app.AppCompatActivity;

import com.example.swingolf.R;
import com.example.swingolf.dataModel.AppDatabase;
import com.example.swingolf.dataModel.DatabaseModule;
import com.example.swingolf.util.NumberSelectionDialog;

public class ScorecardActivity extends AppCompatActivity implements NumberSelectionDialog.NumberSelectionDialogListener {

    private int numRows = 5;
    private int numCols = 4;
    private Button[][] buttons = new Button[numRows][numCols];
    AppDatabase database;
Button selectedButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorecard);
        database = DatabaseModule.getInstance(ScorecardActivity.this);

        TableLayout tableLayout = findViewById(R.id.tableLayout);

        addHeaderRow(tableLayout);

        for (int i = 0; i < numRows; i++) {
            addDataRow(tableLayout, i);
        }
    }

    private void addHeaderRow(TableLayout tableLayout) {

    }

    private void addDataRow(TableLayout tableLayout, final int rowIndex) {
        TableRow dataRow = new TableRow(this);
        dataRow.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));


        Button rowHeader = new Button(this);
        rowHeader.setText("Row " + (rowIndex + 1));
        rowHeader.setEnabled(false);
        dataRow.addView(rowHeader);


        for (int j = 0; j < numCols; j++) {
            Button button = new Button(this);

            int finalJ = j;
            button.setOnClickListener(view -> {
                selectedButton = button;
                openNumberSelectionDialog(rowIndex, finalJ);
            });
            dataRow.addView(button);
            buttons[rowIndex][j] = button;
        }


        tableLayout.addView(dataRow);
    }

    private void openNumberSelectionDialog(int row, int col) {
        NumberSelectionDialog dialog = new NumberSelectionDialog(ScorecardActivity.this, 5, this);
        dialog.show();
    }

    @Override
    public void onButtonClicked(int number) {

//        for (int i = 0; i < numRows; i++) {
//            for (int j = 0; j < numCols; j++) {
//                if (buttons[i][j] != null && buttons[i][j].isSelected()) {
//                    buttons[i][j].setText(String.valueOf(number));
//                    return;
//                }
//            }
//        }
        Log.d("ScorecardActivity", "Button clicked: " + number);
        selectedButton.setText(String.valueOf(number));
    }
}
