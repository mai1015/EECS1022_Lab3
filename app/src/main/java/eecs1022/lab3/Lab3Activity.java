package eecs1022.lab3;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Lab3Activity extends AppCompatActivity
{

    private Game g;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3);
    }

    private void setTextViewById(int ID, String text) {
        TextView view = (TextView) findViewById(ID);
        view.setText(text);
    }

    private String getInputById(int ID) {
        EditText view = (EditText) findViewById(ID);
        return view.getText().toString();
    }

    private String getItemSelectedById(int ID) {
        Spinner spinner = (Spinner) findViewById(ID);
        return spinner.getSelectedItem().toString();
    }

    public void buttonConfirm(View v) {
        // It will be a mess and do not study it.
        // Get player Input
        String p1 = getInputById(R.id.inputPOne);
        String p2 = getInputById(R.id.inputPTwo);

        // validate the model, update when changed
        if (g == null) {
            g = new Game(p1, p2);
        } else if (!g.getPlayer1().equals(p1) || !g.getPlayer2().equals(p2)) {
            g = new Game(p1, p2);
        }

        // deal with data
        p1 = getItemSelectedById(R.id.spinnerPone);
        p2 = getItemSelectedById(R.id.spinnerPTwo);

        if (g.getTrun() > 4) {
            setTextViewById(R.id.labelResult, g.toString());
            setTextViewById(R.id.labelRound, String.format("Round %d", 1));
            g = null;
        } else {
            setTextViewById(R.id.labelResult, g.newTrun(p1,p2));
            setTextViewById(R.id.labelRound, String.format("Round %d", g.getTrun()));
        }
    }

    public void onInputChanged(View v) {
        // change when button updated
    }
}
