package eecs1022.lab3;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
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

    private void setButtonText(int ID, String t) {
        Button b = (Button) findViewById(ID);
        b.setText(t);
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
        }

        // deal with data
        p1 = getItemSelectedById(R.id.spinnerPone);
        p2 = getItemSelectedById(R.id.spinnerPTwo);
        // run the new turn
        String result = g.newTrun(p1,p2);

        // showing result
        if (g.isOver()) {
            setTextViewById(R.id.labelResult, g.toString());
            setTextViewById(R.id.labelRound, String.format("Round %d", 1));
        } else {
            setTextViewById(R.id.labelResult, result);
            setTextViewById(R.id.labelRound, String.format("Round %d", g.getTrun()+1));
        }
    }

    public void onInputChanged(View v) {
        // When player update the name, it means change player.
        // renew game
        g = null;

        // initialize output
        setTextViewById(R.id.labelRound, "Round 1");
        setTextViewById(R.id.labelResult, "New game created. Enter names of player");
    }
}