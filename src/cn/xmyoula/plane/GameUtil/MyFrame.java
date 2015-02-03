package cn.xmyoula.plane.GameUtil;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame extends Frame {

	public void launchFrame() {
		setSize(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
		setLocation(200, 100);
		setVisible(true);
		setTitle("是男人就撑20秒");
		
		new PaintThread().start();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

	class PaintThread extends Thread {
		public void run() {
			while (true) {
				repaint();
				try {
					Thread.sleep(30);// 30ms
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	//双缓冲技术解决闪烁问题
	private Image iBuffer;
	private Graphics gBuffer;

	public void update(Graphics scr) {
		if (iBuffer == null) {
			iBuffer = createImage(this.getSize().width, this.getSize().height);
			gBuffer = iBuffer.getGraphics();
		}
		gBuffer.setColor(getBackground());
		gBuffer.fillRect(0, 0, this.getSize().width, this.getSize().height);
		paint(gBuffer);
		scr.drawImage(iBuffer, 0, 0, this);
	}

}
