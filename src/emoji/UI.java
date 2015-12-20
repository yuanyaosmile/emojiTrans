package emoji;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.awt.Font;

public class UI extends JFrame {
	private JTextField text_search;
	private static JTextPane text_show;
	public static Map<String, String> emoji = new HashMap<String, String>();

	public UI() {

		getContentPane().setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		text_show = new JTextPane();
		text_show.setFont(new Font("Segoe UI Symbol", text_show.getFont().getStyle(), text_show.getFont().getSize()));
		text_show.setText("12345678902345678");
		panel.add(text_show);

		JScrollPane scrollPane = new JScrollPane(text_show);
		panel.add(scrollPane);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));

		JTextPane text_des = new JTextPane();
		text_des.setFont(new Font("Segoe UI Symbol", text_des.getFont().getStyle(), text_des.getFont().getSize()));
		text_des.setText("READY");
		panel_1.add(text_des, BorderLayout.CENTER);

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));

		text_search = new JTextField();
		text_search.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
		panel_2.add(text_search);
		text_search.setColumns(10);

		JButton btn_search = new JButton("查询");
		btn_search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String temp = text_search.getText().trim().toString();
				String result = emoji.get(temp);
				if (result != null) {
					text_des.setText(result);
				} else {
					text_des.setText("No result!!!");
				}
			}
		});
		panel_2.add(btn_search);

	}

	public void init() {

		String text = new String();

		try {
			Scanner in = new Scanner(new File("E:/bbb.txt"));

			int num = 0;

			while (in.hasNextLine()) {

				String str;
				str = in.nextLine();

				if (!str.equals(new String(""))) {
					String ret = splitt(str)[1];
					text = text.concat(ret);
					if (num++ / 62 == 1) {
						text = text.concat("\n");
						num = 0;
					}
				}

			}

			in.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if (text_show != null) {
			System.out.print(text);
			text_show.setText(text);
		}

		return;
	}

	public static String[] splitt(String str) {
		String strr = str.trim();
		// System.out.println("==> "+ strr);
		String[] abc = strr.split("[\\p{Space}]+");
		String str0 = abc[0];
		String str1 = abc[1];
		String str2 = strr.substring(abc[0].length() + abc[1].length() + 1);

		// System.out.println("! " + str0);
		// System.out.println("!! " + str1);
		System.out.println("!!! " + str2);

		emoji.put(str1, str0 + " " + str2);

		return abc;
	}

	public static void main(String[] args) {
		UI ui = new UI();
		ui.setSize(800, 600);
		ui.setResizable(false);
		ui.setDefaultCloseOperation(EXIT_ON_CLOSE);
		ui.setVisible(true);

		ui.init();
	}

}
