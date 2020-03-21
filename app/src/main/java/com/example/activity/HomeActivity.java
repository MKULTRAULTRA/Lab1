package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private static int billion;
    private static int million;
    private static int thousand;
    private static int toThousand;
    private static long numberA;
    private static long numberMax = 999999999999L ;

    private static String numText;

    private static int indexA;
    private static int units;
    private static int decimal;
    private static int hundreds;

    private static final String[][]sampleText ={ {"","од","дв","три","четыре","пять","шесть","семь","восемь","девять"},
            {"", "десять " ,"двадцать ","тридцать ","сорок ","пятьдесят ","шестьдесят ","семьдесят ","восемьдесят ","девяносто "},
            {"","сто ","двести ","триста ","четыреста ","пятьсот ","шестьсот ","семьсот ","восемьсот ","девятьсот "} };

    private static final String[]sample11to19 = { "десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать","пятнадцать","шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"} ;

    private static final String[][] textMillion={{"","","",""},
            {"миллиардов ","миллионов ","тысяч ",""},
            {"миллиард ","миллион ","тысяча ",""},
            {"миллиарда ","миллиона ","тысячи ",""},
            {"миллиардов ","миллионов ","тысяч ",""}};

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList <Recyclerview> content = new ArrayList<Recyclerview>();
        for(int i=1;i<=10000;i++){
            content.add(new Recyclerview(R.drawable.red, WordsRus(i)));

        }
        RecAdapter recAdapter = new RecAdapter(content);
        recyclerView.setAdapter(recAdapter);
    }

    public static String WordsRus (long number) {
        numberA = number;
        numText ="";

        billion = (int) ( numberA / 1000000000);
        million = (int) (numberA-(billion*1000000000))/ 1000000 ;
        thousand = (int) (numberA - (billion*1000000000)-(million*1000000)) / 1000;
        toThousand = (int)(numberA % 1000) ;


        numText =numText + WordsToThousand (billion , 0)+WordsToThousand (million , 1)+WordsToThousand
                (thousand , 2)+WordsToThousand (toThousand , 3);
        return  numText ;

    }

    private static String WordsToThousand ( int numericalValue , int index ){
        hundreds = numericalValue / 100;
        decimal   = (numericalValue - (hundreds*100) ) / 10;
        units = numericalValue % 10 ;


        numText = "";
        if ( decimal == 1 ) numText = sampleText [2] [hundreds] + sample11to19 [units];
        else numText = sampleText [2] [hundreds] + sampleText [1][decimal] + sampleText[0] [units];

        if (index == 2) {if (units == 1 && decimal != 1) numText = numText + "на ";
        else if (units == 2 & decimal != 1) numText = numText + "е ";
            if (units > 1 && decimal != 1) numText = numText + " ";}
        else {if (units == 1 && decimal != 1) numText = numText + "ин ";
            if (units == 2 & decimal != 1) {numText = numText + "а ";}
            else if (units != 0 & decimal != 1) numText = numText + " ";}

        indexA = 0;
        if (numericalValue != 0 ) {
            if (units == 0 || decimal == 1 )   indexA = 1;
            else if (units == 1)              indexA = 2;
            else if (units > 1 && units < 5)  indexA = 3;
            else                            indexA = 4;}
        numText = numText + textMillion [indexA][index];
        return numText;
    }


    }
