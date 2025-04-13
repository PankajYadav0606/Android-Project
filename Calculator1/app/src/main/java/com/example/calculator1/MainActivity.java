package com.example.calculator1;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv, solutionTv;

    MaterialButton buttonC, buttonBrackOpen, getButtonBrackClose;

    MaterialButton buttonDivide, buttonMultiply, buttonPluse, buttonMinus, buttonEqual;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot;

    boolean lastNumeric = false;  //track last input number
    boolean lastOperator = false;  //track last input operator

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        resultTv=findViewById(R.id.result_tv);
        solutionTv=findViewById(R.id.solution_tv);

        assignId(buttonC,R.id.C);
        assignId(buttonBrackOpen,R.id.br1);
        assignId(getButtonBrackClose,R.id.br2);
        assignId(buttonDivide,R.id.div);
        assignId(buttonMultiply,R.id.mult);
        assignId(buttonPluse,R.id.add);
        assignId(buttonMinus,R.id.sub);
        assignId(buttonEqual,R.id.equal);
        assignId(button0,R.id.zero);
        assignId(button1,R.id.one);
        assignId(button2,R.id.two);
        assignId(button3,R.id.three);
        assignId(button4,R.id.four);
        assignId(button5,R.id.five);
        assignId(button6,R.id.six);
        assignId(button7,R.id.seven);
        assignId(button8,R.id.eight);
        assignId(button9,R.id.nine);
        assignId(buttonAC,R.id.AC);
        assignId(buttonDot,R.id.dot);
    }

    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    public void onClick(View view){
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String currentExperession= solutionTv.getText().toString();

        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            lastNumeric=false;
            lastOperator=false;
            return;
        }

        if (buttonText.equals("=")){
            if (lastNumeric){
                solutionTv.setText(resultTv.getText());
            }
            return;
        }

        if (buttonText.equals("C")){
            if (!currentExperession.isEmpty()){
                currentExperession=currentExperession.substring(0,currentExperession.length()-1);
                solutionTv.setText(currentExperession);

                if(!currentExperession.isEmpty()){
                    resultTv.setText(getResult(currentExperession));
                }else {
                    resultTv.setText("0");
                }
            }
            return;
        }

        //Check Button Clicked is an operator
        if (isOperator(buttonText)){
            if (lastOperator){
                //Replace the last operator with the new one
                currentExperession = currentExperession.substring(0,currentExperession.length() - 1);
            }
            lastOperator=true;
            lastNumeric=false;
        } else if (buttonText.equals("(") || buttonText.equals(")")) {

            lastOperator=false;
            lastNumeric=false;
        }else {
            lastOperator=false;
            lastNumeric=true;
        }

        currentExperession += buttonText;
        solutionTv.setText(currentExperession);


        //Only Calculate if the expression is not empty
        if (!currentExperession.isEmpty()) {
            String finalResult = getResult(currentExperession);

            if (!finalResult.equals("Err")) {
                resultTv.setText(finalResult);
            }
        }
    }

    String getResult(String data){
        //Avoid calculation for empty expression
        if (data.isEmpty()){
            return "0";
        }

        try {
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String finalResult= context.evaluateString(scriptable,data,"javascript", 1,null).toString();

            //Remove "0" if its a whole number
            if (finalResult.endsWith(".0")){
                finalResult=finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e)
        {
            return "Err";
        }
    }

    private boolean isOperator(String str){
        return  str.equals("+") || str.equals("-") || str.equals("*")  || str.equals("/");
    }
}