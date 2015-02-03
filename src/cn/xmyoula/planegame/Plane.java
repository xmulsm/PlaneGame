package cn.xmyoula.planegame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import cn.xmyoula.plane.GameUtil.Constant;
import cn.xmyoula.plane.GameUtil.GameUtil;

public class Plane {
	public static double x, y;
	Image img;
	boolean right, left, up, down;
	private boolean live = true;
	int width,height;

	public Plane(double x, double y, Image img) {
		super();
		this.x = x;
		this.y = y;
		this.img = img;
		this.width=img.getWidth(null);
		this.height=img.getHeight(null);
	}

	public boolean isLife() {
		return live;

	}
	public void setLive(Boolean live){
		this.live=live;
	}

	public void drawPlane(Graphics g) {
		g.drawImage(img, (int) x, (int) y, null);
		move();
	}

	public void move() {

		if (right && x <= Constant.FRAME_WIDTH - img.getWidth(null) - 20) {
			x += Constant.PLANE_SPEED;
		}
		if (left && x > 10) {
			x -= Constant.PLANE_SPEED;
		}
		if (down && y < Constant.FRAME_HEIGHT - img.getHeight(null) - 10) {
			y += Constant.PLANE_SPEED;
		}
		if (up && y > 30) {
			y -= Constant.PLANE_SPEED;
		}

	}

	// 键盘按下
	public void press(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;

		default:
			break;
		}

	}

	// 键盘松开
	public void release(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;

		default:
			break;
		}
	}
	
	public Rectangle getRect(){
		return  new Rectangle((int)x,(int) y, width, height);
	}


}
