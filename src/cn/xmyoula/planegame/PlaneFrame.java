package cn.xmyoula.planegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;

import cn.xmyoula.plane.GameUtil.Constant;
import cn.xmyoula.plane.GameUtil.GameUtil;
import cn.xmyoula.plane.GameUtil.MyFrame;

public class PlaneFrame extends MyFrame {

	ArrayList bulletList = new ArrayList();
	int bulletNumber = 20;

	double bulletX, bulletY;
	private Image bg = GameUtil.getImage("images/bg.jpg");;

	Image imgPlane = GameUtil.getImage("images/plane.png");
	Plane p = new Plane(Constant.FRAME_WIDTH / 2, Constant.FRAME_HEIGHT / 2,
			imgPlane);
	static PlaneFrame pf = new PlaneFrame();
	public static void main(String[] args) {

	
		pf.launchFrame();
		

	}

	Explode explode;
	Date startTime, endTime;
	String timeInfo;

	@Override
	public void paint(Graphics g) {
		g.drawImage(bg, 0, 0, null);
		Font f=new Font("黑体", Font.BOLD, 40);
		int countdown = (int) (startTime.getTime()-new Date().getTime() ) / 1000;
		if (Math.abs(countdown)>= 3) {
			if (p.isLife()) {
				p.drawPlane(g);

				for (int i = 0; i < bulletList.size(); i++) {
					Bullet b = (Bullet) bulletList.get(i);
					b.draw(g);
					System.out.println(p.getRect().intersects(b.getRect()));
					if (p.getRect().intersects(b.getRect())) {
						p.setLive(false);

					}
				}
				;
				int second = (int) (new Date().getTime() - startTime.getTime()-3000) / 1000;
				int ms = (int) (new Date().getTime() - startTime.getTime()) % 1000;
				timeInfo = "您已坚持了" + second + ":" + ms;
				printInfo(g, timeInfo, 380, 50, new Font("宋体", Font.BOLD, 12));

			}
			
			if (!p.isLife()) {

				if (explode == null) {
					endTime = new Date();
					explode = new Explode(p.x, p.y);
				}
				explode.draw(g);
				int runTime = (int) (endTime.getTime() - startTime.getTime()-3000) / 10000;
				
				switch (runTime) {
				case 0:
					printInfo(g, "伪娘", 210, 200, f);
					break;
				case 1:
					printInfo(g, "小男人", 210, 200, f);
					break;
				case 2:
					printInfo(g, "真男人", 210, 200, f);
					break;
				case 3:
					printInfo(g, "伟人", 210, 200, f);
					break;
				default:
					printInfo(g, "牛人", 210, 200, f);
					break;
				}
				String endInfo = "您坚持了"
						+ (double) (endTime.getTime() - startTime.getTime()-3000)
						/ 1000 + "秒";

				printInfo(g, endInfo, 190, 250, new Font("宋体", Font.BOLD, 15));
				printInfo(g, "按空格键重新开始", 190, 300, new Font("宋体", Font.BOLD, 15));
			}
		
		}
		else{
			printInfo(g, countdown+3+"", 250, 250, f);
			}
	}

	public void printInfo(Graphics g, String info, int x, int y, Font f) {
		g.setFont(f);
		g.drawString(info, x, y);

	}

	public void launchFrame() {
		super.launchFrame();
		startTime = new Date();
		double bulletX[] = new double[bulletNumber];
		double bulletY[] = new double[bulletNumber];
		for (int i = 0; i < bulletNumber; i++) {

			bulletX[i] = Math.random() * (Constant.FRAME_WIDTH - 10);
			bulletY[i] = Math.random() * (Constant.FRAME_HEIGHT - 10);
			// 初始生成的子弹如果在飞机的范围内则重新生成
			while (bulletX[i] >= p.x
					&& bulletX[i] <= p.x + imgPlane.getWidth(null)
					&& bulletY[i] >= p.y
					&& bulletY[i] <= p.y + imgPlane.getHeight(null)) {
				bulletX[i] = Math.random() * (Constant.FRAME_WIDTH - 10);
				bulletY[i] = Math.random() * (Constant.FRAME_HEIGHT - 10);
			}

			Bullet b = new Bullet((int) bulletX[i], (int) bulletY[i], 3, 6, 6,
					Color.yellow);

			bulletList.add(b);

		}
		addKeyListener(new keyMonitor());
	}

	class keyMonitor extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			p.press(e);
			if (!p.isLife() && e.getKeyCode() == KeyEvent.VK_SPACE) {
				setVisible(false);
				new PlaneFrame().launchFrame();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			p.release(e);
		}

	}

}
