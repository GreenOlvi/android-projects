package com.greenolvi.android.game.gfx;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ScreenView extends View {

	public ScreenView(Context context) {
		super(context);
	}

	public ScreenView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ScreenView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	private Paint mPaint;
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (mPaint == null) {
			mPaint = new Paint();
			mPaint.setColor(Color.WHITE);
		}
		canvas.drawColor(Color.BLACK);
		for (int y = 0; y < canvas.getHeight(); y++) {
			for (int x = 0; x < canvas.getWidth(); x++) {
				if ((x & y) == 0) {
					canvas.drawPoint(x, y, mPaint);
				}
			}
		}
	}
	

}
