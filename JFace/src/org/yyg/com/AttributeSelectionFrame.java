package org.yyg.com;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class AttributeSelectionFrame extends JFrame{
	private int length;
	private boolean[] isChoosed;
	private int[] index;
	private JTable demoTable;
	private DefaultTableModel model;
	private JCheckBox[] checkbox;
	private JButton ok;

	private JButton cancle;
	private JScrollPane scroll;
	private JPanel panel;
	private static Vector<String> mvector;
	public AttributeSelectionFrame() {
		// TODO Auto-generated constructor stub
	}
	
	public AttributeSelectionFrame(Vector<String> attributes){
		super("属性选择");
		//setWinStyle();
		this.mvector = attributes;
		createTable();
		initGUI();
		setSize(300, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);	

	}
	public class myTable extends JTable{
		
		@Override
		public boolean isCellEditable(int row, int column) {
			// TODO Auto-generated method stub
			if( column == 0){  //可编辑行的设置
			       return true;  
			   }else{  
			       return false;  
			   } 
		}
	}
	
	private class MyTableRenderer extends JCheckBox implements TableCellRenderer {  
        //此方法可以查考JDK文档的说明  
        public Component getTableCellRendererComponent( JTable table,  
                Object value,  
                boolean isSelected,  
                boolean hasFocus,  
                int row,  
                int column ) {  
            if (value instanceof Boolean) {
				this.setSelected(((Boolean) value).booleanValue());
			}
            return this;  
        }  
   }
	//单元测试
	public static void main(String[] args) {
		Vector<String> vector = new Vector<String>();
		vector.add("hello");
		vector.add("world");
		vector.add("yes");
		vector.add("ok");
		vector.add("think");
		AttributeSelectionFrame absf = new AttributeSelectionFrame(vector);
	}
	
	public Vector<String> getMvector() {
		return mvector;
	}
	public void setMvector(Vector<String> v) {
		this.mvector = v;
	}

	private void initGUI() {
		
		ok=new JButton("确认");
		cancle=new JButton("取消");
		scroll=new JScrollPane(demoTable);
		panel=new JPanel();
		scroll.setViewportView(demoTable);
	    scroll.setSize(200,300);
	
	    this.getContentPane().add(scroll);

		panel.add(ok);
		panel.add(cancle);;
		add(scroll, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
		
		cancle.addActionListener(new java.awt.event.ActionListener() {
			//监听cancle(取消)按钮
			    public void actionPerformed(java.awt.event.ActionEvent e) {
			     isChoosed=new boolean[length];
			     for(int i=0;i<length;i++){
			    	 isChoosed[i]=true;
			     }
			     dispose();
			    }
			   });
		ok.addActionListener(new java.awt.event.ActionListener() {
			//监听cancle(取消)按钮
		    public void actionPerformed(java.awt.event.ActionEvent e) {
		    	int[] in = getIndex();
		    	String show = "您选择的属性有：";
		    	for (int i = 0; i < in.length; i++) {
					show += "\\"+in[i];
				}
		    	JOptionPane.showMessageDialog(null, show,"提示", JOptionPane.OK_OPTION);
		    	dispose();
		    }
		   });
		
	}

	/**
	 * @param attributes
	 */
	private void createTable(){
		demoTable=new myTable();
	    model = (DefaultTableModel) demoTable.getModel();
		
	    Object[] tableHeads = {"s", "索引","  属性"};
		model.setColumnIdentifiers(tableHeads);
		TableColumnModel tcm = demoTable.getColumnModel();
	    final MyTableRenderer my = new MyTableRenderer();
	    tcm.getColumn(0).setCellEditor(new DefaultCellEditor(my));
	    tcm.getColumn(0).setCellRenderer(my);
	    demoTable.getColumn("s").setHeaderRenderer(my);
	    tcm.getColumn(0).setMinWidth(1);
	    tcm.getColumn(0).setPreferredWidth(3);
	    tcm.getColumn(1).setPreferredWidth(5);
	    for (int i = 0; i < mvector.size(); i++) {
			Object[] rowObjects = {new Boolean(true),new Integer(i),new String(mvector.get(i))};
			model.addRow(rowObjects);
		}
	    
	   demoTable.getTableHeader().addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e){
	            if(demoTable.getColumnModel().getColumnIndexAtX(e.getX())==0){//如果点击的是第0列，即checkbox这一列
	                JCheckBox Checkbox = (JCheckBox)my;
	                boolean b = !my.isSelected();
	                my.setSelected(b);
	                demoTable.getTableHeader().repaint();
	                for(int i=0;i<demoTable.getRowCount();i++){
	                	demoTable.getModel().setValueAt(b, i, 0);//把这一列都设成和表头一样
	                }
	            }
	        }
	    });
	}
	
	public int[] getIndex(){//取出索引
		int j =0;
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < demoTable.getRowCount(); i++) {
			if(demoTable.getValueAt(i, 0) instanceof Boolean){
				if (((Boolean)demoTable.getValueAt(i,0)).booleanValue()) {
					j++;
					//System.err.println("==="+demoTable.getValueAt(i, 1));
					list.add((Integer)demoTable.getValueAt(i, 1));
				}
			}
		}
		index = new int[j];
		int i=0;
		for (Integer integer : list) {
			index[i++] = integer;
		}
		return index;
	}
	
}
