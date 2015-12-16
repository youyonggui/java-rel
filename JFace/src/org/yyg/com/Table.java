package org.yyg.com;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.JTable;

public class Table extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private int[] index;
	private JTable table;
	private Vector<String> mvector;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Vector<String> vector = new Vector<>();
			vector.add("hello");
			vector.add("world");
			Table dialog = new Table(vector);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
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
	

	/**
	 * Create the dialog.
	 */
	public Table(Vector<String> v) {
		this.mvector = v;
		setBounds(100, 100, 315, 484);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			{
				JTable table= new JTable(); 
				DefaultTableModel dtmDemo = (DefaultTableModel) table.getModel();
				String[] tableHeads = { "序号","id","jCheckBox"};
				dtmDemo.setColumnIdentifiers(tableHeads);
				table.setPreferredSize(getSize());
				table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JCheckBox()));
				table.getColumnModel().getColumn(2).setCellRenderer(new MyTableRenderer());
				//dtmDemo.setColumnIdentifiers(tableHeads);
				//dtmDemo.addColumn({"选择","索引","属性"});
				for(int i=0;i<mvector.size();i++){
					 Object[] objdata = {new Boolean(false),i,mvector.get(i)/**这里就那个JCheckBox位置*/};
					 dtmDemo.addRow(objdata);
				}
				panel.add(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		//initListener();
	}
	
	class CheckBoxCellEditor extends AbstractCellEditor implements TableCellEditor {
		  
		  private static final long serialVersionUID = 1L;
		 
		  protected JCheckBox checkBox;
		 
		  public CheckBoxCellEditor() {
		    checkBox = new JCheckBox();
		    checkBox.setHorizontalAlignment(SwingConstants.CENTER);
		    // checkBox.setBackground( Color.white);
		  }
		 
		  @Override 
		  public Object getCellEditorValue() {
		    return Boolean.valueOf(checkBox.isSelected());
		  }
		 
		  @Override 
		  public Component getTableCellEditorComponent(
		    JTable  table,
		    Object  value,
		    boolean isSelected,
		    int     row,
		    int     column) {
		    checkBox.setSelected(((Boolean) value).booleanValue());
		    return checkBox;
		 
		  }
		}
		 
		class CWCheckBoxRenderer extends JCheckBox implements TableCellRenderer {
		 
		  private static final long serialVersionUID = 1L;
		 
		  Border border = new EmptyBorder(1, 2, 1, 2);
		 
		  public CWCheckBoxRenderer() {
		    super();
		    setOpaque(true);
		    setHorizontalAlignment(SwingConstants.CENTER);
		  }
		
		  @Override public Component getTableCellRendererComponent(
		    JTable  table,
		    Object  value,
		    boolean isSelected,
		    boolean hasFocus,
		    int     row,
		    int     column) {
		    if (value instanceof Boolean) {
		      setSelected(((Boolean) value).booleanValue());
		 
		      // setEnabled(table.isCellEditable(row, column));
		      setForeground(table.getForeground());
		      setBackground(table.getBackground());
		 
		    }
		 
		    return this;
		  }
		} 
	
	public int[] getIndex() {
		
		
		return this.index;
	}

}
