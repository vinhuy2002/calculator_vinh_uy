package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    TextView tv_working, tv_result;
    String workingStr ="", congTh="", congThTemp="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayTextView();
    }

    private void displayTextView(){

        tv_result = (TextView) findViewById(R.id.tv_result);
        tv_working = (TextView) findViewById(R.id.tv_working);
    }

    public void ketQua(View view){


        Double kq = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        kiemTraSoMu();
        try{
            if (congTh == ""){
                kq = null;
            } else{
                kq = (double)engine.eval(congTh);
            }

        } catch (ScriptException e){
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_LONG).show();
        }

        if (kq != null){
            tv_result.setText(String.valueOf(kq.doubleValue()));
            congTh ="";
            workingStr="";
        } else {
            tv_result.setText("0");
            tv_working.setText(null);
        }

    }

    public boolean ktraSo(char c){
        if ((c<='9' && c>='0') || c=='.')
            return true;
        return false;
    }
    private void setFormula(String a){
        workingStr = workingStr + a;
        tv_working.setText(workingStr);
    }

    private void kiemTraSoMu(){
        ArrayList<Integer> soMu = new ArrayList<>();
        for (int i=0; i<workingStr.length(); i++){
            if (workingStr.charAt(i) == '^')
                soMu.add(i);
        }

        congTh = workingStr;
        congThTemp = workingStr;

        for(Integer i: soMu){
            thaydoiCT(i);
        }
        congTh = congThTemp;

    }

    private void thaydoiCT(Integer index)
    {
        String numberLeft = "";
        String numberRight = "";

        for(int i = index + 1; i< workingStr.length(); i++)
        {
            if(ktraSo(workingStr.charAt(i)))
                numberRight = numberRight + workingStr.charAt(i);
            else
                break;
        }

        for(int i = index - 1; i >= 0; i--)
        {
            if(ktraSo(workingStr.charAt(i)))
                numberLeft = numberLeft + workingStr.charAt(i);
            else
                break;
        }

        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow("+numberLeft+","+numberRight+")";
        congThTemp = congThTemp.replace(original,changed);
    }

    public void xoa(View view){
        tv_result.setText("0");
        workingStr ="";
        tv_working.setText("");

    }

    boolean leftBracket = true;

    public void ngoacDon(View view)
    {
        if(leftBracket)
        {
            setFormula("(");
            leftBracket = false;
        }
        else
        {
            setFormula(")");
            leftBracket = true;
        }
    }



    public void cong(View view){
        setFormula("+");
    }

    public void tru(View view){
        setFormula("-");
    }

    public void nhan(View view){
        setFormula("*");
    }

    public void chia(View view){
        setFormula("/");
    }
    public void soThapPhan(View view){
        setFormula(".");
    }

    public void so0(View view){
        setFormula("0");
    }
    public void so1(View view){
        setFormula("1");
    }
    public void so2(View view){
        setFormula("2");
    }
    public void so3(View view){
        setFormula("3");
    }
    public void so4(View view){
        setFormula("4");
    }
    public void so5(View view){
        setFormula("5");
    }
    public void so6(View view){
        setFormula("6");
    }
    public void so7(View view){
        setFormula("7");
    }
    public void so8(View view){
        setFormula("8");
    }
    public void so9(View view){
        setFormula("9");
    }
    public void power(View view){
        setFormula("^");
    }


}