
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JToolBar;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;

public class GUIResume2 extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldAge;
	public JTextArea textAreahobbies;
	public JTextArea textAreaQual;

	private JLabel lblQualification;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIResume2 frame = new GUIResume2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GUIResume2() {
		setTitle("Resume Saver");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 641, 646);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbNameLabel = new JLabel("Name");
		lbNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbNameLabel.setBounds(110, 74, 97, 30);
		contentPane.add(lbNameLabel);

		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldName.setBounds(285, 75, 262, 30);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);

		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAge.setBounds(110, 122, 97, 30);
		contentPane.add(lblAge);

		textFieldAge = new JTextField();
		textFieldAge.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldAge.setColumns(10);
		textFieldAge.setBounds(285, 123, 262, 30);
		contentPane.add(textFieldAge);

		lblQualification = new JLabel("Qualifications");
		lblQualification.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblQualification.setBounds(110, 170, 97, 30);
		contentPane.add(lblQualification);

		JTextArea textAreaQual = new JTextArea();
		textAreaQual.setBounds(285, 175, 262, 148);
		contentPane.add(textAreaQual);

		JLabel lblHobbies = new JLabel("Hobbies");
		lblHobbies.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHobbies.setBounds(110, 348, 97, 30);
		contentPane.add(lblHobbies);

		JTextArea textAreahobbies = new JTextArea();
		textAreahobbies.setBounds(285, 353, 262, 148);
		contentPane.add(textAreahobbies);

		JButton btnNewButtonSave = new JButton("Save");
		btnNewButtonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (textFieldName.getText().isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Name cannot be blank", "Alert",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (textFieldAge.getText().isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Age cannot be blank", "Alert",
							JOptionPane.WARNING_MESSAGE);
					return;

				}
				try {

					Integer.parseInt(textFieldAge.getText());

				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(contentPane, "Age must be a number", "Alert",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (textAreaQual.getText().isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Qualifications cannot be blank", "Alert",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (textAreahobbies.getText().isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Hobbies cannot be blank", "Alert",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				EntityResume entityResume = new EntityResume();
				entityResume.name = textFieldName.getText();
				entityResume.age = Integer.parseInt(textFieldAge.getText());
				entityResume.qualifications = textAreaQual.getText();
				entityResume.hobbies = textAreahobbies.getText();

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = fileChooser.showOpenDialog(contentPane);

				File selectedFile;
				if (result == JFileChooser.APPROVE_OPTION) {

					selectedFile = fileChooser.getSelectedFile();
					System.out.println("Selected folder: " + selectedFile.getAbsolutePath());

					try {
						FileOutputStream fileOut = new FileOutputStream(
								selectedFile.getAbsolutePath() + "\\" + entityResume.name + ".ser");
						System.out.println(
								"file saved " + selectedFile.getAbsolutePath() + "\\" + entityResume.name + ".ser");

						ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
						objectOut.writeObject(entityResume);
						objectOut.close();
						System.out.println("The Object was succesfully written to a file");

						textFieldName.setText("");
						textFieldAge.setText("");
						textAreahobbies.setText("");
						textAreaQual.setText("");
						JOptionPane.showMessageDialog(contentPane, "Resume Saved ", "Information",
								JOptionPane.INFORMATION_MESSAGE);

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

			}
		});

		btnNewButtonSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButtonSave.setBounds(64, 548, 123, 36);
		contentPane.add(btnNewButtonSave);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldName.setText("");
				textFieldAge.setText("");
				textAreahobbies.setText("");
				textAreaQual.setText("");
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnClear.setBounds(251, 548, 123, 36);
		contentPane.add(btnClear);

		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				File selectedFile = null;
				int result = fileChooser.showOpenDialog(contentPane);
				if (result == JFileChooser.APPROVE_OPTION) {

					selectedFile = fileChooser.getSelectedFile();
					System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				}

				try {

					FileInputStream fileIn = new FileInputStream(selectedFile.getAbsolutePath());
					ObjectInputStream objectIn = new ObjectInputStream(fileIn);

					EntityResume obj = (EntityResume) objectIn.readObject();

					System.out.println("The Object has been read from the file");
					System.out.println(obj);

					textFieldName.setText(obj.name);
					textFieldAge.setText(String.valueOf(obj.age));
					textAreahobbies.setText(obj.hobbies);
					textAreaQual.setText(obj.qualifications);
					objectIn.close();

				} catch (Exception ex) {
					ex.printStackTrace();

				}

			}
		});
		btnLoad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLoad.setBounds(438, 548, 123, 36);
		contentPane.add(btnLoad);
	}
}
