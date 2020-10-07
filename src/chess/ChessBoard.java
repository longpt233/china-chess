package chess;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChessBoard extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton newGame = new JButton("new game");
	private JButton cancel = new JButton("cancel");
	private List<int[][]> backUpData ;
	private Image[] pics = new Image[15];

	private int[][] data ={ 
			{ 8, 9,10,11,12,11,10, 9, 8 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0,13, 0, 0, 0, 0, 0, 13, 0 }, 
			{ 14,0,14, 0,14, 0,14, 0, 14 }, 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 7, 0, 7, 0, 7, 0, 7, 0, 7 },
			{ 0, 6, 0, 0, 0, 0, 0, 6, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 1, 2, 3, 4, 5, 4, 3, 2, 1 } };

	public ChessBoard() {
		

		final Board panel = new Board();
		add(panel, BorderLayout.CENTER);
		JPanel panel2 = new JPanel();
		panel2.add(newGame);
		panel2.add(cancel);
		add(panel2, BorderLayout.SOUTH);
		newGame.setBackground(new Color(216, 196, 152));
		cancel.setBackground(new Color(216, 196, 152));
		panel2.setBackground(new Color(216, 196, 152));
		panel.setBackground(new Color(216, 196, 152));
		pics[1] = Toolkit.getDefaultToolkit().getImage("images/车1.png");
		pics[2] = Toolkit.getDefaultToolkit().getImage("images/马1.png");
		pics[3] = Toolkit.getDefaultToolkit().getImage("images/相1.png");
		pics[4] = Toolkit.getDefaultToolkit().getImage("images/士12.png");
		pics[5] = Toolkit.getDefaultToolkit().getImage("images/帅.png");
		pics[6] = Toolkit.getDefaultToolkit().getImage("images/炮1.png");
		pics[7] = Toolkit.getDefaultToolkit().getImage("images/兵.png");
		pics[8] = Toolkit.getDefaultToolkit().getImage("images/车2.png");
		pics[9] = Toolkit.getDefaultToolkit().getImage("images/马2.png");
		pics[10] = Toolkit.getDefaultToolkit().getImage("images/象2.png");
		pics[11] = Toolkit.getDefaultToolkit().getImage("images/士2.png");
		pics[12] = Toolkit.getDefaultToolkit().getImage("images/将.png");
		pics[13] = Toolkit.getDefaultToolkit().getImage("images/炮2.png");
		pics[14] = Toolkit.getDefaultToolkit().getImage("images/卒.png");
		
		
		

		newGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				backUpData = new ArrayList<>();
				panel.tickSelected = false;
				panel.isSelected = false;
				data = new int[][] { 
						{ 8, 9, 10, 11, 12, 11, 10, 9, 8 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 13, 0, 0, 0, 0, 0, 13, 0 }, 
						{ 14, 0, 14, 0, 14, 0, 14, 0, 14 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 7, 0, 7, 0, 7, 0, 7, 0, 7 },
						{ 0, 6, 0, 0, 0, 0, 0, 6, 0 }, 
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
						{ 1, 2, 3, 4, 5, 4, 3, 2, 1 } };
				backUpData.add(data);
				repaint();
			}
		});

		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel.tickSelected = false;
				panel.isSelected = false;

				if (backUpData.size() <= 2) {
					JOptionPane.showMessageDialog(null, "khong quay lai dc ");
					return;
				}
				backUpData.remove(backUpData.size() - 1);
				backUpData.remove(backUpData.size() - 1);
				data = backUpData.get(backUpData.size() - 1);
				repaint();
			}
		});

	}

	public static int[][] CloneData(int[][] data) {
		int[][] sub = new int[data.length][data[0].length];
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				sub[i][j] = data[i][j];
			}
		}
		System.out.println(sub.length);
		return sub;
	}

	private class Board extends JPanel implements MouseListener {
		/** serialVersionUID */
		private static final long serialVersionUID = 1L;
		private int col;
		private int row;
		private boolean tickSelected;  // chon mot quan co thi no danh dau 
		private boolean isSelected;
		private int precol;
		private int prerow;
		private boolean preIsRed;

		private final int HEIGHT = 700;
		private final int WIDTH = 630;

		public Board() {
			// TODO Auto-generated constructor stub
			addMouseListener(this);
		}

		// ham tich dau thap cho ban co
		private void DrawCross(int row, int col, Graphics g) {
			int baseX = WIDTH * col / 11;
			int baseY = HEIGHT * row / 12;
			int wlen = WIDTH / 110;
			int hlen = HEIGHT / 120;
			int w4len = WIDTH / 44;
			int h4len = HEIGHT / 48;

			if (col != 9) {
				g.drawLine(baseX + wlen, baseY - hlen, baseX + wlen, baseY - h4len);
				g.drawLine(baseX + wlen, baseY - hlen, baseX + w4len, baseY - hlen);

				g.drawLine(baseX + wlen, baseY + hlen, baseX + w4len, baseY + hlen);
				g.drawLine(baseX + wlen, baseY + hlen, baseX + wlen, baseY + h4len);
			}

			if (col != 1) {
				g.drawLine(baseX - wlen, baseY - hlen, baseX - wlen, baseY - h4len);
				g.drawLine(baseX - wlen, baseY - hlen, baseX - w4len, baseY - hlen);

				g.drawLine(baseX - wlen, baseY + hlen, baseX - w4len, baseY + hlen);
				g.drawLine(baseX - wlen, baseY + hlen, baseX - wlen, baseY + h4len);
			}
		}

		// ham ve ban co
		private void DrawBoard(int w, int h, Graphics g) {
			g.drawLine(WIDTH / 11, HEIGHT / 12, WIDTH / 11, HEIGHT * 10 / 12);
			g.drawLine(WIDTH * 9 / 11, HEIGHT / 12, WIDTH * 9 / 11, HEIGHT * 10 / 12);
			for (int i = 0; i < 7; i++) {
				g.drawLine(WIDTH * (i + 2) / 11, HEIGHT / 12, WIDTH * (i + 2) / 11, HEIGHT * 5 / 12);
				g.drawLine(WIDTH * (i + 2) / 11, HEIGHT / 2, WIDTH * (i + 2) / 11, HEIGHT * 10 / 12);
			}
			for (int i = 0; i < 10; i++) {
				g.drawLine(WIDTH / 11, HEIGHT * (i + 1) / 12, WIDTH * 9 / 11, HEIGHT * (i + 1) / 12);
			}

			// »­Ê¿µÄÏß
			g.drawLine(WIDTH * 4 / 11, HEIGHT / 12, WIDTH * 6 / 11, HEIGHT / 4);
			g.drawLine(WIDTH * 6 / 11, HEIGHT / 12, WIDTH * 4 / 11, HEIGHT / 4);
			g.drawLine(WIDTH * 4 / 11, HEIGHT * 10 / 12, WIDTH * 6 / 11, HEIGHT * 8 / 12);
			g.drawLine(WIDTH * 6 / 11, HEIGHT * 10 / 12, WIDTH * 4 / 11, HEIGHT * 8 / 12);

			// danh dau thap cho phao
			DrawCross(3, 2,  g);
			DrawCross(3, 8,  g);
			DrawCross(8, 2,  g);
			DrawCross(8, 8,  g);
			// danh dau thap cho 10 quan tot
			DrawCross(4, 1,  g);
			DrawCross(4, 3,  g);
			DrawCross(4, 5,  g);
			DrawCross(4, 7,  g);
			DrawCross(4, 9,  g);
			DrawCross(7, 1,  g);
			DrawCross(7, 3,  g);
			DrawCross(7, 5,  g);
			DrawCross(7, 7,  g);
			DrawCross(7, 9,  g);

		}

		@Override
		protected void paintComponent(Graphics g) {
			// TODO Auto-generated method stub
			super.paintComponent(g);
			DrawBoard(WIDTH, HEIGHT, g);

			for (int i = 1; i <= 10; i++) {
				for (int j = 1; j <= 9; j++) {
					int baseX = WIDTH * j / 11;
					int baseY = HEIGHT * i / 12;
					g.drawImage(pics[data[i - 1][j - 1]], baseX - WIDTH / 22, baseY - HEIGHT / 24, WIDTH / 11,
							HEIGHT / 12, this);
				}
			}
//			System.out.println("tickSelected is " + tickSelected);
			// bien ma khi an vao no hien chu mau xanh bao quanh 
			if (tickSelected) {
				g.setColor(Color.green);
				Graphics2D g2d = (Graphics2D) g;
				Stroke st = g2d.getStroke();
				Stroke bs;
				bs = new BasicStroke(4f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 16, 4 }, 0);
				g2d.setStroke(bs);

				g2d.drawLine(col * WIDTH / 11 - WIDTH / 22, row * HEIGHT / 12 - HEIGHT / 24,
						col * WIDTH / 11 - WIDTH / 44, row * HEIGHT / 12 - HEIGHT / 24);
				g2d.drawLine(col * WIDTH / 11 - WIDTH / 22, row * HEIGHT / 12 - HEIGHT / 24,
						col * WIDTH / 11 - WIDTH / 22, row * HEIGHT / 12 - HEIGHT / 48);
				g2d.drawLine(col * WIDTH / 11 + WIDTH / 22, row * HEIGHT / 12 - HEIGHT / 24,
						col * WIDTH / 11 + WIDTH / 44, row * HEIGHT / 12 - HEIGHT / 24);
				g2d.drawLine(col * WIDTH / 11 + WIDTH / 22, row * HEIGHT / 12 - HEIGHT / 24,
						col * WIDTH / 11 + WIDTH / 22, row * HEIGHT / 12 - HEIGHT / 48);
				g2d.drawLine(col * WIDTH / 11 + WIDTH / 22, row * HEIGHT / 12 + HEIGHT / 24,
						col * WIDTH / 11 + WIDTH / 44, row * HEIGHT / 12 + HEIGHT / 24);
				g2d.drawLine(col * WIDTH / 11 + WIDTH / 22, row * HEIGHT / 12 + HEIGHT / 24,
						col * WIDTH / 11 + WIDTH / 22, row * HEIGHT / 12 + HEIGHT / 48);
				g2d.drawLine(col * WIDTH / 11 - WIDTH / 22, row * HEIGHT / 12 + HEIGHT / 24,
						col * WIDTH / 11 - WIDTH / 44, row * HEIGHT / 12 + HEIGHT / 24);
				g2d.drawLine(col * WIDTH / 11 - WIDTH / 22, row * HEIGHT / 12 + HEIGHT / 24,
						col * WIDTH / 11 - WIDTH / 22, row * HEIGHT / 12 + HEIGHT / 48);

				g2d.setStroke(st);
				g.setColor(Color.black);
			}

		}

		@Override
		public void mouseClicked(MouseEvent e) {

			int x = e.getX();
			int y = e.getY();
			for (int i = 1; i <= 9; i++) {
				if (Math.abs(WIDTH * i / 11 - x) < WIDTH / 22) {
					col = i;
					break;
				}
			}
			for (int i = 1; i <= 10; i++) {
				if (Math.abs(HEIGHT * i / 12 - y) < HEIGHT / 24) {
					row = i;
					break;
				}
			}
//			System.out.println("select="+isSelected);   // lan dau tien chon thi cai nay bang false vi k co nuoc dinao trc no 
			if (isSelected) {

				int label = data[prerow - 1][precol - 1];
				switch (label) {
//
// nuoc di co the cua quan xe
				case 1: // thuc hien luon case 8, case sau sau nua , toi khi gap break
				case 8:
					if ((preIsRed && data[row - 1][col - 1] < 8 && data[row - 1][col - 1] > 0)
							|| (!preIsRed && data[row - 1][col - 1] >= 8)) {
						isSelected = true;
						precol = col;
						prerow = row;
						preIsRed = data[prerow - 1][precol - 1] < 8 ? true : false;
						repaint();
					}
					if (row < prerow && col == precol) {
						for (int i = row + 1; i < prerow; i++) {
							if (data[i - 1][col - 1] != 0)
								return;// thoat luon ham click
						}
					} else if (col < precol && row == prerow) {
						for (int i = col + 1; i < precol; i++)
							if (data[row - 1][i - 1] != 0)
								return;
					} else if (row > prerow && col == precol) {
						for (int i = prerow + 1; i < row; i++)
							if (data[i - 1][col - 1] != 0)
								return;
					} else if (col > precol && row == prerow) {
						for (int i = precol + 1; i < col; i++)
							if (data[row - 1][i - 1] != 0)
								return;
					} else {
						if (data[row - 1][col - 1] == 0)
							return;
						else if (preIsRed) {
							isSelected = true;
							precol = col;
							prerow = row;
							preIsRed = data[prerow - 1][precol - 1] < 8 ? true : false;
							repaint();
						}
						return;
					}
					data[row - 1][col - 1] = data[prerow - 1][precol - 1];
					data[prerow - 1][precol - 1] = 0;
					repaint();
					break;
					
// nuoc di co the cua quan ma
//
				case 2:
				case 9:
					// ÊÇÂí
					if ((preIsRed && data[row - 1][col - 1] < 8 && data[row - 1][col - 1] > 0)
							|| (!preIsRed && data[row - 1][col - 1] >= 8)) {
						isSelected = true;
						precol = col;
						prerow = row;
						preIsRed = data[prerow - 1][precol - 1] < 8 ? true : false;
						repaint();
						return;
					}
					if (col - precol == 2 && prerow - row == 1) {
						// ÌÉÈÕ¶«±±·½Ïò
						if (data[prerow - 1][precol] != 0)
							return;
					} else if (col - precol == 2 && row - prerow == 1) {
						// ÌÉÈÕ¶«ÄÏ·½Ïò
						if (data[prerow - 1][precol] != 0)
							return;
					} else if (precol - col == 2 && row - prerow == 1) {
						// ÌÉÈÕÎ÷ÄÏ·½Ïò
						if (data[prerow - 1][precol - 2] != 0)
							return;
					} else if (precol - col == 2 && prerow - row == 1) {
						// ÌÉÈÕÎ÷±±·½Ïò
						if (data[prerow - 1][precol - 2] != 0)
							return;
					} else if (col - precol == 1 && prerow - row == 2) {
						// ÌøÈÕ¶«±±
						if (data[prerow - 2][precol - 1] != 0)
							return;
					} else if (col - precol == 1 && row - prerow == 2) {
						// ÌøÈÕ¶«ÄÏ
						if (data[prerow][precol - 1] != 0)
							return;
					} else if (precol - col == 1 && row - prerow == 2) {
						if (data[prerow][precol - 1] != 0)
							return;
					} else if (precol - col == 1 && prerow - row == 2) {
						if (data[prerow - 2][precol - 1] != 0)
							return;
					} else {
						if (data[row - 1][col - 1] == 0)
							return;
						else if ( preIsRed) {
							// »»×ÓÑ¡Ôñ
							isSelected = true;
							precol = col;
							prerow = row;
							preIsRed = data[prerow - 1][precol - 1] < 8 ? true : false;
							repaint();
						}
						return;
					}
					data[row - 1][col - 1] = data[prerow - 1][precol - 1];
					data[prerow - 1][precol - 1] = 0;
					repaint();
					break;
// nuoc di co the cua quan tinh 
//
				case 3:
				case 10:
					// ÊÇÏà
					if ((preIsRed && data[row - 1][col - 1] < 8 && data[row - 1][col - 1] > 0)
							|| (!preIsRed && data[row - 1][col - 1] >= 8)) {
						isSelected = true;
						precol = col;
						prerow = row;
						preIsRed = data[prerow - 1][precol - 1] < 8 ? true : false;
						repaint();
						return;
					}
					if (preIsRed) {
						if (row <= 5)
							return;// ²»ÄÜ³öÈ¥
					} else {
						if (row > 5)
							return;
					}
					if (col - precol == 2 && prerow - row == 2) {
						// Íù¶«±±·½Ïò
						if (data[prerow - 2][precol] != 0)
							return;
					} else if (col - precol == 2 && row - prerow == 2) {
						// Íù¶«ÄÏ·½Ïò
						if (data[prerow][precol] != 0)
							return;
					} else if (precol - col == 2 && row - prerow == 2) {
						// ÍùÎ÷ÄÏ·½Ïò
						if (data[prerow][precol - 2] != 0)
							return;
					} else if (precol - col == 2 && prerow - row == 2) {
						// ÍùÎ÷±±·½Ïò
						if (data[prerow - 2][precol - 2] != 0)
							return;
					} else {
						if (data[row - 1][col - 1] == 0)
							return;
						else if (preIsRed) {
							// »»×ÓÑ¡Ôñ
							isSelected = true;
							precol = col;
							prerow = row;
							preIsRed = data[prerow - 1][precol - 1] < 8 ? true : false;
							repaint();
						}
						return;
					}
					data[row - 1][col - 1] = data[prerow - 1][precol - 1];
					data[prerow - 1][precol - 1] = 0;
					repaint();
					break;
// nuoc di co the cua quan si
//
				case 4:
				case 11:
					// Ê¿
					if ((preIsRed && data[row - 1][col - 1] < 8 && data[row - 1][col - 1] > 0)
							|| (!preIsRed && data[row - 1][col - 1] >= 8)) {
						isSelected = true;
						precol = col;
						prerow = row;
						preIsRed = data[prerow - 1][precol - 1] < 8 ? true : false;
						repaint();
						return;
					}
					if (col > 6 || col < 4)
						return;
					if (preIsRed) {
						if (row < 8)
							return;
					} else {
						if (row > 3)
							return;
					}
					if (col - precol == 1 && row - prerow == 1) {
					} else if (col - precol == 1 && prerow - row == 1) {
					} else if (precol - col == 1 && row - prerow == 1) {
					} else if (precol - col == 1 && prerow - row == 1) {
					} else {
						if (data[row - 1][col - 1] == 0)
							return;
						else if (preIsRed) {
							// »»×ÓÑ¡Ôñ
							isSelected = true;
							precol = col;
							prerow = row;
							preIsRed = data[prerow - 1][precol - 1] < 8 ? true : false;
							repaint();
						}
						return;
					}
					data[row - 1][col - 1] = data[prerow - 1][precol - 1];
					data[prerow - 1][precol - 1] = 0;
					repaint();
					break;
// nuoc di co the cua quan tuong 
//
				case 5:
				case 12:
					if ((preIsRed && data[row - 1][col - 1] < 8 && data[row - 1][col - 1] > 0)
							|| (!preIsRed && data[row - 1][col - 1] >= 8)) {
						isSelected = true;
						precol = col;
						prerow = row;
						preIsRed = data[prerow - 1][precol - 1] < 8 ? true : false;
						repaint();
						return;
					}
					if (col > 6 || col < 4)
						return;
					if (preIsRed) {
						if (row < 8)
							return;
					} else {
						if (row > 3)
							return;
					}
					if (col - precol == 1 && row == prerow) {
					} else if (col == precol && prerow - row == 1) {
					} else if (precol - col == 1 && prerow == row) {
					} else if (precol == col && row - prerow == 1) {
					} else {
						if (data[row - 1][col - 1] == 0)
							return;
						else if ( preIsRed) {
							isSelected = true;
							precol = col;
							prerow = row;
							preIsRed = data[prerow - 1][precol - 1] < 8 ? true : false;
							repaint();
						}
						return;
					}
					data[row - 1][col - 1] = data[prerow - 1][precol - 1];
					data[prerow - 1][precol - 1] = 0;
					repaint();
					break;
// nuoc di co the cua quan phao
//
				case 6:
				case 13:
					if (data[row - 1][col - 1] == 0) {
						if (row < prerow && col == precol) {
							for (int i = row + 1; i < prerow; i++) {
								if (data[i - 1][col - 1] != 0)
									return;
							}
						} else if (col < precol && row == prerow) {
							// ÏëÍù×ó×ß
							for (int i = col + 1; i < precol; i++)
								if (data[row - 1][i - 1] != 0)
									return;
						} else if (row > prerow && col == precol) {
							// ÏëÍùÏÂ×ß
							for (int i = prerow + 1; i < row; i++)
								if (data[i - 1][col - 1] != 0)
									return;
						} else if (col > precol && row == prerow) {
							// ÏëÍùÓÒ×ß
							for (int i = precol + 1; i < col; i++)
								if (data[row - 1][i - 1] != 0)
									return;
						} else {
							return;
						}
					} else if ((preIsRed && data[row - 1][col - 1] < 8) || (!preIsRed && data[row - 1][col - 1] >= 8)) {
						isSelected = true;
						precol = col;
						prerow = row;
						preIsRed = data[prerow - 1][precol - 1] < 8 ? true : false;
						repaint();
						return;
					} else {
						int num = 0;
						if (row < prerow && col == precol) {
							for (int i = row + 1; i < prerow; i++) {
								if (data[i - 1][col - 1] != 0)
									num++;
							}
						} else if (row > prerow && col == precol) {
							for (int i = prerow + 1; i < row; i++)
								if (data[i - 1][col - 1] != 0)
									num++;
						} else if (row == prerow && col > precol) {
							for (int i = precol + 1; i < col; i++)
								if (data[row - 1][i - 1] != 0)
									num++;
						} else if (row == prerow && col < precol) {
							for (int i = col + 1; i < precol; i++)
								if (data[row - 1][i - 1] != 0)
									num++;
						}
						if (num != 1)
							return;
					}
					// ÏÖÔÚ¿ÉÒÔÁË
					data[row - 1][col - 1] = data[prerow - 1][precol - 1];
					data[prerow - 1][precol - 1] = 0;
					repaint();
					break;
// nuoc di co the cua quan tot
//
				case 7:
				case 14:
					// ÊÇ±ø
					if ((preIsRed && data[row - 1][col - 1] < 8 && data[row - 1][col - 1] > 0)
							|| (!preIsRed && data[row - 1][col - 1] >= 8)) {
						isSelected = true;
						precol = col;
						prerow = row;
						preIsRed = data[prerow - 1][precol - 1] < 8 ? true : false;
						repaint();
						return;
					}
					if (preIsRed) {
						if (prerow > 5) {
							// ÔÚ×Ô¼ºµÄÕóµØÉÏ ²»ÄÜ×óÓÒÒÆ¶¯
							if (prerow - row == 1 && precol == col) {
								// Ç°½ø
							} else if (data[row - 1][col - 1] < 8) {
								isSelected = true;
								precol = col;
								prerow = row;
								preIsRed = data[prerow - 1][precol - 1] < 8 ? true : false;
								repaint();
								return;
							} else {
								return;
							}
						} else {
							// ÔÚ¶Ô·½ÕóµØÉÏ
							if (prerow - row == 1 && precol == col) {
								// ½ø
							} else if (prerow == row && precol - col == 1) {
								// ×ó
							} else if (prerow == row && col - precol == 1) {
								// ÓÒ
							} else {
								isSelected = true;
								precol = col;
								prerow = row;
								preIsRed = data[prerow - 1][precol - 1] < 8 ? true : false;
								repaint();
								return;
							}
						}
					} else {
						if (prerow <= 5) {
							if (prerow - row == -1 && precol == col) {
							} else if (data[row - 1][col - 1] >= 8) {
								isSelected = true;
								precol = col;
								prerow = row;
								preIsRed = data[prerow - 1][precol - 1] < 8 ? true : false;
								repaint();
								return;
							} else {
								return;
							}
						} else {
							// ÔÚ¶Ô·½ÕóµØÉÏ
							if (prerow - row == -1 && precol == col) {
								// ½ø
							} else if (prerow == row && precol - col == 1) {
								// ×ó
							} else if (prerow == row && col - precol == 1) {
								// ÓÒ
							} else {
								isSelected = true;
								precol = col;
								prerow = row;
								preIsRed = data[prerow - 1][precol - 1] < 8 ? true : false;
								repaint();
								return;
							}
						}
					}
					data[row - 1][col - 1] = data[prerow - 1][precol - 1];
					data[prerow - 1][precol - 1] = 0;
					repaint();
					break;
// default
//
				default:
					break;
				}
				isSelected = false;
					if(true) {

					backUpData.add(CloneData(data));
					//data = new ChessJudge(data).AITurn();
					data=new ChessAI(data).Compute();
					if (data == null) {
						data = backUpData.get(backUpData.size() - 1);

						JOptionPane.showMessageDialog(null, "congratulate,you win.");
						return;
					} else {
						boolean isShuai = false;
						for (int i = 7; i <= 9; i++)
							for (int j = 3; j <= 5; j++)
								if (data[i][j] == 5) {
									isShuai = true;
								}
						if (!isShuai) {
							JOptionPane.showMessageDialog(null, "sorry,you lose.");

							backUpData.remove(backUpData.size() - 1);
							return;
						}
					}
					backUpData.add(CloneData(data));
					tickSelected = false;
					repaint();
				}
			 }

			//

			precol = col;
			prerow = row;
			if (precol == 0 || prerow == 0)
				return;
			preIsRed = data[prerow - 1][precol - 1] < 8 ? true : false;
			if (data[prerow - 1][precol - 1] == 0 || data[prerow - 1][precol - 1] >= 8)
				return;

			tickSelected = true;
			isSelected = true;
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public static void main(String[] args) {
		JFrame frame = new ChessBoard();
		frame.setTitle("cotuong");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 800);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
