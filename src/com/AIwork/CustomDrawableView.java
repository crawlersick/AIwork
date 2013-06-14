/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AIwork;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.View;

/**
 *
 * @author sick
 */
      public class CustomDrawableView extends View {
      //private ShapeDrawable mDrawable;
          TextDrawable mDrawable;

      public CustomDrawableView(Context context,String saystr) {
      super(context);

      int x = 10;
      int y = 10;
      int width = 300;
      int height = 50;

      //mDrawable = new ShapeDrawable(new OvalShape());
      mDrawable = new TextDrawable(saystr);
     // mDrawable.getPaint().setColor(0xff74AC23);
      //mDrawable.setBounds(x, y, x + width, y + height);
      }
      
      public  void changetext(String tempstr)
      {
          mDrawable.setText(tempstr);
      }
      
      protected void onDraw(Canvas canvas) {
      mDrawable.draw(canvas);
      }
      }
    