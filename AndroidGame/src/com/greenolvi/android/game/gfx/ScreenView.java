package com.greenolvi.android.game.gfx;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.greenolvi.android.game.R;

public class ScreenView extends SurfaceView implements Runnable {
	
	Thread thread = null;
	SurfaceHolder surfaceHolder;
	volatile boolean running = false;
	
	public ScreenView(Context context) {
		super(context);
		surfaceHolder = getHolder();
	}

	public void onPause() {
		boolean retry = true;
		running = false;
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void onResume() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		while (running) {
			if (surfaceHolder.getSurface().isValid()) {
				Canvas canvas = surfaceHolder.lockCanvas();
				
				drawBackground(canvas);
				
				surfaceHolder.unlockCanvasAndPost(canvas);
			}
		}
	}
	
	private Bitmap tiles = BitmapFactory.decodeResource(getResources(), R.drawable.icons);
	
	int xOffset = 0;
	int yOffset = 0;
	
	private boolean touching = false;
	
	private void drawBackground(Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		
		canvas.drawColor(Color.BLACK);
		for (int y = 0; y < canvas.getHeight(); y++) {
			for (int x = 0; x < canvas.getWidth(); x++) {
				if ((x & y) == 0) {
					int b = touching ? 200 : 0;
					paint.setColor(Color.rgb((x & 0xFF), (y & 0xFF), b));
					canvas.drawPoint(x, y, paint);
				}
			}
		}
		
		render(canvas, 100, 100, 0);
		render(canvas, 200, 100, 32);
		render(canvas, 300, 100, 10);
	}
	
	public void render(Canvas canvas, int xp, int yp, int tile) {
		xp -= xOffset;
		yp -= yOffset;
		
		int xTile = tile % 32;
		int yTile = tile / 32;
		
		canvas.drawBitmap(tiles, new Rect(xTile * 8 - 2, yTile * 8, xTile * 8 + 8 -2, yTile * 8 + 8), new Rect(xp, yp, xp + 8 * 5, yp + 8 * 5), null);
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		switch(action) {
		case MotionEvent.ACTION_DOWN:
			touching = true;
			break;
		case MotionEvent.ACTION_UP:
			touching = false;
			break;
		case MotionEvent.ACTION_CANCEL:
			touching = false;
			break;
		case MotionEvent.ACTION_OUTSIDE:
			touching = false;
			break;
		}
		this.invalidate();
		return true;
	}

}
