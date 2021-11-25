package com.application.mob4git;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;


public class PaintActivity extends AppCompatActivity {

    BestPaintBoard board;
    Button colorBtn;
    Button penBtn;
    Button eraserBtn;
    Button undoBtn;

    LinearLayout legendLayout;
    Button colorLegendButton;
    Button sizeLegendButton;

    int mColor = 0xff000000;
    int mSize = 2;
    int oldColor;
    int oldSize;
    boolean eraserSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paint_activity);


        LinearLayout toolsLayout = findViewById(R.id.toolsLayout);
        LinearLayout boardLayout = findViewById(R.id.boardLayout);

        colorBtn = findViewById(R.id.colorBtn);
        penBtn = findViewById(R.id.penBtn);
        eraserBtn = findViewById(R.id.eraserBtn);
        undoBtn = findViewById(R.id.undoBtn);

        // RadioButton referenced


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        board = new BestPaintBoard(this);
        board.setLayoutParams(params);
        board.setPadding(2, 2, 2, 2);

        boardLayout.addView(board);


        legendLayout = findViewById(R.id.legendLayout);
        colorLegendButton = findViewById(R.id.colorLegendButton);
        sizeLegendButton = findViewById(R.id.sizeLegendButton);


        colorBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ColorPaletteDialog.listener = new OnColorSelectedListener() {
                    public void onColorSelected(int color) {
                        mColor = color;
                        board.updatePaintProperty(mColor, mSize);
                        displayPaintProperty();
                    }
                };


                // show color palette dialog
                Intent intent = new Intent(getApplicationContext(), ColorPaletteDialog.class);
                startActivity(intent);

            }
        });

        penBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                PenPaletteDialog.listener = new OnPenSelectedListener() {
                    public void onPenSelected(int size) {
                        mSize = size;
                        board.updatePaintProperty(mColor, mSize);
                        displayPaintProperty();
                    }
                };


                // show pen palette dialog
                Intent intent = new Intent(getApplicationContext(), PenPaletteDialog.class);
                startActivity(intent);

            }
        });

        eraserBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                eraserSelected = !eraserSelected;

                if (eraserSelected) {
                    colorBtn.setEnabled(false);
                    penBtn.setEnabled(false);
                    undoBtn.setEnabled(false);

                    colorBtn.invalidate();
                    penBtn.invalidate();
                    undoBtn.invalidate();

                    oldColor = mColor;
                    oldSize = mSize;

                    mColor = Color.WHITE;
                    mSize = 15;

                    board.updatePaintProperty(mColor, mSize);
                    displayPaintProperty();

                } else {
                    colorBtn.setEnabled(true);
                    penBtn.setEnabled(true);
                    undoBtn.setEnabled(true);

                    colorBtn.invalidate();
                    penBtn.invalidate();
                    undoBtn.invalidate();

                    mColor = oldColor;
                    mSize = oldSize;

                    board.updatePaintProperty(mColor, mSize);
                    displayPaintProperty();

                }

            }
        });

        undoBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                board.undo();

            }
        });


    }


    public int getChosenColor() {
        return mColor;
    }

    public int getPenThickness() {
        return mSize;
    }

    private void displayPaintProperty() {
        colorLegendButton.setBackgroundColor(mColor);
        sizeLegendButton.setText("Size : " + mSize);

        legendLayout.invalidate();
    }

}
