package com.example.yeye.game2048;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeye on 2015/10/8.
 */
public class GameView extends GridLayout {
    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGameView();
    }

    public GameView(Context context) {
        super(context);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initGameView();
    }

    private void initGameView() {
        setColumnCount(4);//指明4列

        setOnTouchListener(new View.OnTouchListener() {

            private float startX, startY, offsetX, offsetY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        offsetX = event.getX() - startX;
                        offsetY = event.getY() - startY;

                        if (Math.abs(offsetX) > Math.abs(offsetY)) {
                            if (offsetX < -5) {
                                swipeLeft();
                                System.out.println("left");
                            } else if (offsetX > 5) {
                                swipeRight();
                                System.out.println("right");
                            }
                        } else {
                            if (offsetY < -5) {
                                swipeUp();
                                System.out.println("up");
                            } else if (offsetY > 5) {
                                swipeDown();
                                System.out.println("down");
                            }
                        }
                        break;
                }
                return true;
            }
        });
    }

    /*
    动态的计算方框大小
     */
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // System.out.println(w);   System.out.println(h);
        /*
        卡片位置过近。待解决
         */
        int cardWidth = (Math.max(w, h) - 10) / 4;
        System.out.println(cardWidth);
        System.out.println("kuan" + w);
        // addCard(cardWidth, cardWidth);
        addCard(110, 110);
    }

    /*
    添加卡片
     */
    private void addCard(int CardWidth, int CardHeight) {
        Card c;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                c = new Card(getContext());
                c.setNum(0);
                addView(c, CardWidth, CardHeight);
                cardsMap[x][y] = c;
            }
        }
    }

    private void addRandomNum() {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (cardsMap[x][y].getNum() <= 0) {
                    emptyPoints.add(new Point(x, y));
                }
            }
        }
    }

    private void swipeUp() {

    }

    private void swipeDown() {

    }

    private void swipeLeft() {

    }

    private void swipeRight() {

    }

    private Card[][] cardsMap = new Card[4][4];
    private List<Point> emptyPoints = new ArrayList<Point>();
}
