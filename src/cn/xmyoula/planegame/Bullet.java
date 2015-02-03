package cn.xmyoula.planegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import cn.xmyoula.plane.GameUtil.Constant;

public class Bullet {
	double x, y;
	int nubmer;
	int speed = 10;
	double degree;
	double width;
	double height;
	Color color;
	boolean right, left, up, down;

	public Bullet(int x, int y, int speed, double width, double height,
			Color color) {
		this.speed = speed;
		this.width = width;
		this.height = height;
		this.color = color;
		this.x = x;
		this.y = y;
		this.degree = (Math.random()+0.1) * Math.PI * 2;
	}

	public void move() {

		// double k = (Plane.y - y) / (Plane.x - x);
		// if(x<Constant.FRAME_WIDTH)
		// x += speed*Math.cos(degree);
		// if(x-Plane.x>0&&x>10)
		// x -= speed*Math.sqrt(1/(k*k+1));
		// if(y-Plane.y<0&&y<Plane.y)
		// y += speed*Math.sqrt(k*k/(k*k+1));
		// if(y-Plane.y>0&&y>10)
		// y -= speed*Math.sqrt(k*k/(k*k+1));
		x += speed * Math.cos(degree);
		y += speed * Math.sin(degree);

		if (y > Constant.FRAME_HEIGHT - height || y < 30) {
			degree = -degree;
		}

		if (x < 0 || x > Constant.FRAME_WIDTH - width) {
			degree = Math.PI - degree;
		}

	}

	public void draw(Graphics g) {
		// System.out.println(planeX);
		Color c = g.getColor();
		g.setColor(color);
		g.fillOval((int) x, (int) y, (int) width, (int) height);
		move();
		g.setColor(c);
	}
	public Rectangle getRect(){
		return  new Rectangle((int)x,(int) y, (int)width, (int)height);
	}

}
