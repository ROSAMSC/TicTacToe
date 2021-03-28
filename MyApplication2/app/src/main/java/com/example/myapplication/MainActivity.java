package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean toogle = false;
    RadioButton rbX;
    RadioButton rb0;
    String square [][] = {{"0","1","2"},{"3","4","5"},{"6","7","8"}};
    int statusGame = -1;
    TableLayout table;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rbX =findViewById(R.id.radioButton);
        rb0 =findViewById(R.id.radioButton2);
        table = findViewById(R.id.table);
    }

    public void toogleButton(View v){
        ImageButton imgB = (ImageButton) v;

        int img = rbX.isChecked() ? R.mipmap.cruz : R.mipmap.cero;

        if(updateImage(imgB,rbX.isChecked()) && statusGame == -1){
            if(rbX.isChecked()){
                rb0.setChecked(true);
            }else {
                rbX.setChecked(true);
            }
            imgB.setImageResource(img);
            statusGame = checkWinner();
            if(statusGame == 1){
                Toast.makeText(this, "El ganador es " + (!rbX.isChecked() ? "X" : "0"),Toast.LENGTH_LONG).show();
            }
            else if(statusGame == 0){
                Toast.makeText(this, "Hay empate", Toast.LENGTH_LONG).show();
            }
        }

        /*if(!imgB.getContentDescription().equals("X") && !imgB.getContentDescription().equals("0")){
            if(rbX.isChecked()){
                img = R.mipmap.cruz;
                rb0.setChecked(true);
            }else{
                img = R.mipmap.cero;
                rbX.setChecked(true);
            }
        }

        imgB.setImageResource(img);
        if(drawable.getConstantState().equals(getResources().getDrawable(R.mipmap.cero).getConstantState())) {
            imgB.setImageResource(R.mipmap.cruz);
        }else{
            imgB.setImageResource(R.mipmap.cero);
        }
        Integer res = (Integer) imgB.getTag();
        if(res == R.mipmap.cero) {
            ((ImageButton) v).setImageResource(R.mipmap.cruz);
        }else{
            ((ImageButton) v).setImageResource(R.mipmap.cero);
        }*/
    }
    
    public boolean updateImage(ImageButton img, boolean isX){
        for (int i=0; i<square[0].length; i++){
            for (int j=0; j<square[1].length;j++){
                if (img.getContentDescription().equals(square[i][j])){
                    square[i][j] = isX ? "X" : "0";
                    return true;
                }
            }
        }
        return false;
    }

    private int checkWinner(){
        if(square[0][0] == square[0][1] && square[0][1] == square[0][2])
            return 1;
        else if(square[1][0] == square[1][1] && square[1][1] == square[1][2])
            return 1;
        else if(square[2][0] == square[2][1] && square[2][1] == square[2][2])
            return 1;
        else if(square[0][0] == square[1][0] && square[1][0] == square[2][0])
            return 1;
        else if(square[0][1] == square[1][1] && square[1][1] == square[2][1])
            return 1;
        else if(square[0][2] == square[1][2] && square[1][2] == square[2][2])
            return 1;
        else if(square[0][0] == square[1][1] && square[1][1] == square[2][2])
            return 1;
        else if(square[0][2] == square[1][1] && square[1][1] == square[2][0])
            return 1;
        else if(square[0][0] != "0" && square[0][1] != "1" && square[0][2] != "2"
                && square[1][0] != "3" && square[1][1] != "4" && square[1][2] != "5"
                && square[2][0] != "6" && square[2][1] != "7" && square[2][2] != "8")
            return 0;
        else
            return -1;
    }

    public void resetGame(View v){
        statusGame = -1;
        String [][] m2 = {{"0","1","2"},{"3","4","5"},{"6","7","8"}};
        square = m2;

        for (int i=0; i<table.getChildCount(); i++){
            View tRow = table.getChildAt(i);
            if(tRow !=null && tRow instanceof TableRow){
                TableRow row = (TableRow) tRow;
                for (int j=0; j<row.getChildCount(); j++){
                    View vw = row.getChildAt(j);
                    if(vw != null && vw instanceof ImageButton){
                        ImageButton img = (ImageButton) vw;
                        img.setImageResource(R.mipmap.squere);
                    }
                }
            }
        }
    }
} 