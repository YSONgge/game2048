package com.example.yeye.game2048;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeye ..
 */
public class GameView extends GridLayout {
    static int i = 0;

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
        setColumnCount(4);//设置为4行

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
    产生card
     */


    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        super.onSizeChanged(w, h, oldw, oldh);
        // System.out.println(w);   System.out.println(h);
        /*
       真机出现问题待解决，htc T328w
         */
        if (i == 0) {
            int cardWidth = (Math.max(w, h) - 10) / 4;
//        System.out.println(cardWidth);
//        System.out.println("kuan" + w);
            // addCard(cardWidth, cardWidth);
            addCard(110, 110);
            startGame();

            i++;
        }

//        System.out.println("************************" + i);

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

    public static void startGame() {


        MainActivity.getMainActivity().clearScore();
        System.out.println(" " + MainActivity.getMainActivity().getScore());

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                cardsMap[x][y].setNum(0);
            }
        }
        addRandomNum();
        addRandomNum();


        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                System.out.print(cardsMap[x][y].getNum() + "--");
            }
            System.out.println();
        }
    }


    private static void addRandomNum() {


        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (cardsMap[x][y].getNum() <= 0) {
                    emptyPoints.add(new Point(x, y));
                }
            }
        }
        Point p = emptyPoints.remove((int) (Math.random() * emptyPoints.size()));
        cardsMap[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);
    }

    private void swipeLeft() {

        boolean marge = false;

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                System.out.print(cardsMap[x][y].getNum() + "--");

            }
            System.out.println();
        }
         /*
        合并数字
         */
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {

                for (int x1 = x + 1; x1 < 4; x1++) {
                    if (cardsMap[x1][y].getNum() > 0) {

                        if (cardsMap[x][y].getNum() <= 0) {
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            //  System.out.println("[" + x + "][" + y + "]" + cardsMap[x][y].getNum());
                            cardsMap[x1][y].setNum(0);
                            x--;

                            marge = true;
                        } else if (cardsMap[x][y].equals(cardsMap[x1][y])) {
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[x1][y].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            System.out.println();
                            marge = true;
                        }
                        break;

                    }
                }
            }
        }

        /*
        控制台输出
         */
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                System.out.print(cardsMap[x][y].getNum() + "--");

            }
            System.out.println();
        }
        /*
        插入新的随机数
         */
        if (marge) {
            addRandomNum();
        }
    }

    private void swipeRight() {

        boolean marge = false;

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                System.out.print(cardsMap[x][y].getNum() + "--");

            }
            System.out.println();
        }
         /*
        合并数字
         */
        for (int y = 0; y < 4; y++) {
            for (int x = 3; x >= 0; x--) {

                for (int x1 = x - 1; x1 >= 0; x1--) {
                    if (cardsMap[x1][y].getNum() > 0) {

                        if (cardsMap[x][y].getNum() <= 0) {
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            //  System.out.println("[" + x + "][" + y + "]" + cardsMap[x][y].getNum());
                            cardsMap[x1][y].setNum(0);
                            x++;

                            marge = true;
                        } else if (cardsMap[x][y].equals(cardsMap[x1][y])) {
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[x1][y].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());

                            marge = true;
                        }
                        break;
                    }
                }
            }

        }
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                System.out.print(cardsMap[x][y].getNum() + "--");

            }
            System.out.println();
        }

        if (marge) {
            addRandomNum();
        }

    }


    private void swipeUp() {

        boolean marge = false;

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                System.out.print(cardsMap[x][y].getNum() + "--");

            }
            System.out.println();
        }
        /*
        合并数字
         */
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {

                for (int y1 = y + 1; y1 < 4; y1++) {
                    if (cardsMap[x][y1].getNum() > 0) {

                        if (cardsMap[x][y].getNum() <= 0) {
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            //  System.out.println("[" + x + "][" + y + "]" + cardsMap[x][y].getNum());
                            cardsMap[x][y1].setNum(0);
                            y--;

                            marge = true;
                        } else if (cardsMap[x][y].equals(cardsMap[x][y1])) {
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[x][y1].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());//加分

                            marge = true;
                        }
                        break;
                    }
                }
            }
        }
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                System.out.print(cardsMap[x][y].getNum() + "--");

            }
            System.out.println();
        }

        if (marge) {
            addRandomNum();
        }

    }

    private void swipeDown() {

        boolean marge = false;

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                System.out.print(cardsMap[x][y].getNum() + "--");

            }
            System.out.println();
        }
        /*
        合并数字
         */
        for (int x = 0; x < 4; x++) {
            for (int y = 3; y >= 0; y--) {

                for (int y1 = y - 1; y1 >= 0; y1--) {
                    if (cardsMap[x][y1].getNum() > 0) {

                        if (cardsMap[x][y].getNum() <= 0) {
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            // System.out.println("[" + x + "][" + y + "]" + cardsMap[x][y].getNum());
                            cardsMap[x][y1].setNum(0);
                            y++;

                            marge = true;
                        } else if (cardsMap[x][y].equals(cardsMap[x][y1])) {
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[x][y1].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());

                            marge = true;
                            break;
                        }
                        break;
                    }
                }
            }
        }
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                System.out.print(cardsMap[x][y].getNum() + "--");

            }
            System.out.println();
        }

        if (marge) {
            addRandomNum();
        }

    }

    public static void reStartGame() {
        //if (MainActivity.getMainActivity().flg == false) {
        startGame();
        //   MainActivity.getMainActivity().flg = true;
        // }

    }

    private void checkComplete() {

        boolean complete = true;

        ALL:
//标签，用于跳出全部循环
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (cardsMap[x][y].getNum() == 0
                        || (x > 1 && cardsMap[x][y].equals(cardsMap[x - 1][y]))
                        || (x < 3 && cardsMap[x][y].equals(cardsMap[x + 1][y]))
                        || (y > 1 && cardsMap[x][y].equals(cardsMap[x][y - 1]))
                        || (y < 3 && cardsMap[x][y].equals(cardsMap[x][y + 1]))) {

                    complete = false;
                    break ALL;
                }
            }
        }

        if (complete) {
            new AlertDialog.Builder(getContext()).setTitle("hello").setMessage("game over").setPositiveButton("restart", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startGame();
                }
            }).show();
        }
    }


    private static Card[][] cardsMap = new Card[4][4];
    private static List<Point> emptyPoints = new ArrayList<>();

}
