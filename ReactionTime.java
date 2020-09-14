import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ReactionTime implements KeyListener, Runnable {

	JFrame mainFrame;
	JPanel mainPanel;
	final int frameWidth = 1366; // 1600, 1920
	final int frameHeight = 768; // 900, 1080

	JLabel blue;
	JLabel green;

	JLabel instructionLeft;
	JLabel instructionRight;
	JLabel incorrectAnswer;
	Random rand;

	Thread t;

	boolean incorrect = false;

	int secondsPassed = 0;
	Timer timer = new Timer();
	TimerTask task = new TimerTask() {
		public void run() {
			secondsPassed++;
		}
	};

	public void startThread() {
		if (t == null) {
			t = new Thread(this, "Thread2");
			t.start();
		}

	}

	public ReactionTime() {
		mainFrame = new JFrame("Reaction Time");
		mainPanel = new JPanel();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(frameWidth, frameHeight);
		mainPanel.setLayout(null);
		rand = new Random();

		blue = new JLabel("BLUE");
		green = new JLabel("GREEN");

		instructionLeft = new JLabel("Press 'A' for BLUE");
		instructionRight = new JLabel("Press 'L' for GREEN");
		instructionLeft.setFont(new Font("instructionFont", Font.PLAIN, 20));
		instructionLeft.setBounds(100, 50, instructionLeft.getPreferredSize().width,
				instructionLeft.getPreferredSize().height);
		instructionRight.setFont(new Font("instructionFont1", Font.PLAIN, 20));
		instructionRight.setBounds(mainFrame.getWidth() - 100 - instructionRight.getPreferredSize().width, 50,
				instructionRight.getPreferredSize().width, instructionRight.getPreferredSize().height);
		incorrectAnswer = new JLabel("X");
		incorrectAnswer.setVisible(false);
		incorrectAnswer.setFont(new Font("incorrectAnswer", Font.BOLD, 40));
		incorrectAnswer.setBounds(mainFrame.getWidth() / 2 - incorrectAnswer.getPreferredSize().width / 2,
				mainFrame.getHeight() / 2 - incorrectAnswer.getPreferredSize().height / 2 + 100,
				incorrectAnswer.getPreferredSize().width, incorrectAnswer.getPreferredSize().height);
		incorrectAnswer.setForeground(Color.RED);
		createLabel(blue, true, Color.BLUE);
		createLabel(green, false, Color.GREEN);
		switchAlg();
		mainFrame.add(mainPanel);
		mainPanel.add(blue);
		mainPanel.add(green);
		mainPanel.add(instructionLeft);
		mainPanel.add(instructionRight);
		mainPanel.add(incorrectAnswer);
		mainFrame.addKeyListener(this);
		mainFrame.setVisible(true);

	}

	public static void main(String[] args) {
		ReactionTime game = new ReactionTime();

		ReactionTime timerGame = new ReactionTime();
		timerGame.startThread();
	}

	public void createLabel(JLabel label, boolean left, Color color) {
		// label.setBorder(new LineBorder(Color.BLACK));
		label.setFont(new Font("labelFont", Font.PLAIN, 100));
		label.setForeground(color);
		Dimension labelSize = label.getPreferredSize();
		label.setBounds(mainFrame.getWidth() / 2 - labelSize.width / 2,
				mainFrame.getHeight() / 2 - labelSize.height / 2, labelSize.width, labelSize.height);
	}

	public void switchAlg() {
		// Change colors randomly
		// Set global int for counter?
		// equal number of blue and green?
		// Accuracy and time
		int ff = rand.nextInt(100);
		if (ff >= 0 && ff <= 49) {
			blue.setVisible(true);
			green.setVisible(false);
		} else {
			blue.setVisible(false);
			green.setVisible(true);
		}
	}

	public void switchLabel() {
		switchAlg();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A) {
			if (blue.isVisible()) {
				switchLabel();
			} else {
				System.out.println("Wrong its GREEN");
				run();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_L) {
			if (green.isVisible()) {
				switchLabel();
			} else {
				// WRONG
				System.out.println("Wrong its BLUE");
				run();
			}
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {

		System.out.println("Ran");
		incorrectAnswer.setVisible(true);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		incorrectAnswer.setVisible(false);
	}
}
