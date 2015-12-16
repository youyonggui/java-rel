package org.yyg.com;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;

import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class mainWindow {

	private JFrame frame;
	
	private JPanel mtopJPanel;
	private JPanel mleftJPanel;
	private JTextField mFileChoosed;
	//private 
	private JPanel mcenterJPanel;
	private JPanel mbottomJPanel;
	public JFileChooser mfc;
	private JOptionPane mpaneMessage;
	
	public static File mfileChoosed;
	public static StringBuffer mfileStringBuffer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow window = new mainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(50, 50, 1050, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mtopJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		frame.getContentPane().add(mtopJPanel, BorderLayout.NORTH);
		topPanel();
		
		mleftJPanel = new JPanel();
		frame.getContentPane().add(mleftJPanel, BorderLayout.WEST);
		westPanel();
		
		mcenterJPanel = new JPanel();
		frame.getContentPane().add(mcenterJPanel, BorderLayout.CENTER);
		centerPanel();
		
		mbottomJPanel = new JPanel();
		frame.getContentPane().add(mbottomJPanel, BorderLayout.SOUTH);
		bottomPanel();
		
	}
	
	private void topPanel(){
		mtopJPanel.setBorder(new TitledBorder("选择"));
		JButton selectButton = new JButton("选择文件");
		selectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mfc=new JFileChooser();
		        mfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
		        mfc.showDialog(new JLabel(), "选择文件");  
		        File file=mfc.getSelectedFile();  
		        if(file.isFile()){
		        	mfileChoosed = file;
		        	mFileChoosed.setText(file.getAbsolutePath());
		        }else {
		        	mpaneMessage.showMessageDialog(null,"您选择的并不是文件!请选择正确的文件！", 
		        			"提示信息",JOptionPane.ERROR_MESSAGE); 
		        }  
			}
		});
		selectButton.setToolTipText("选择你要计算的文件");
		mtopJPanel.add(selectButton);
		
		mFileChoosed = new JTextField();
		mtopJPanel.add(mFileChoosed);
		mFileChoosed.setColumns(50);
		
		JButton execButton = new JButton("开始计算此文件");
		execButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mfileChoosed==null){
					mpaneMessage.showMessageDialog(null,"您没有选择文件！请先选择正确的文件！", 
		        			"提示信息",JOptionPane.ERROR_MESSAGE);
					return;
				}
				InputStream in = null;
				StringBuffer sb = new StringBuffer();
				byte[] bytes = new byte[1024*8];
				int len = -1;
				try {
					try {
						in = new BufferedInputStream(new FileInputStream(mfileChoosed));
						while((len = in.read(bytes)) != -1){
							sb.append(new String(bytes,"GBK"));
						}
						//System.out.println(sb.toString());
						mfileStringBuffer = sb;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}finally{
					if(in!=null){
						try {
							in.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		mtopJPanel.add(execButton);
	}
	
	private void westPanel(){
		mleftJPanel.setBorder(new TitledBorder("属性"));
		JButton selectButton = new JButton("选择属性");
		selectButton.setToolTipText("选择你要计算的文件");
		mleftJPanel.add(selectButton);
	}
	
	private void centerPanel(){
		mcenterJPanel.setBorder(new TitledBorder("属性值"));
		JButton selectButton = new JButton("选择属性");
		selectButton.setToolTipText("选择你要计算的文件");
		mcenterJPanel.add(selectButton);
	}
	
	private void bottomPanel(){
		JButton exitButton = new JButton("退出");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(-1);
			}
		});
		mbottomJPanel.add(exitButton);
	}

}
