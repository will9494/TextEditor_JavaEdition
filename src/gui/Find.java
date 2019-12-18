package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

public class Find extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField tfBuscar;
	private JTextField tfReemplazar;
	private JButton btnCerrar;
	private JButton btnReemplazar;
	private JButton btnBuscar;
	private JLabel lblBuscar;
	private JLabel lblReemplazar;
	public Ventana padre;
    public JTextArea activo;
    public String select;
    public int startIndex;

	/**
	 * Create the application.
	 */
	public Find(JTextArea rich, String sel, Ventana padre) {
		initialize();
        this.activo = rich;
        this.select = sel;
        this.tfBuscar.setText(sel);
        this.padre = padre;
        this.startIndex = 0;
	}

	/**
	 * Initialize the contents of the this.
	 */
	private void initialize() {
		this.setBounds(100, 100, 451, 221);
		this.setResizable(false);
		this.getContentPane().setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane.setBounds(10, 11, 415, 161);
		this.getContentPane().add(layeredPane);
		layeredPane.setLayout(null);
		
		tfBuscar = new JTextField();
		tfBuscar.setBounds(109, 35, 137, 20);
		layeredPane.add(tfBuscar);
		tfBuscar.setColumns(10);
		
		lblBuscar = new JLabel("Buscar:");
		lblBuscar.setBounds(29, 38, 70, 14);
		layeredPane.add(lblBuscar);
		
		lblReemplazar = new JLabel("Reemplazar: ");
		lblReemplazar.setBounds(29, 87, 70, 14);
		layeredPane.add(lblReemplazar);
		
		tfReemplazar = new JTextField();
		tfReemplazar.setBounds(109, 84, 137, 20);
		layeredPane.add(tfReemplazar);
		tfReemplazar.setColumns(10);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setBounds(166, 127, 89, 23);
		btnCerrar.addActionListener(this);
		layeredPane.add(btnCerrar);
		
		btnReemplazar = new JButton("Reemplazar todo");
		btnReemplazar.setBounds(276, 83, 121, 23);
		btnReemplazar.addActionListener(this);
		layeredPane.add(btnReemplazar);
		
		btnBuscar = new JButton("Buscar siguiente");
		btnBuscar.setBounds(276, 34, 121, 23);
		btnBuscar.addActionListener(this);
		layeredPane.add(btnBuscar);
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		Object presionado = evento.getSource();
		
		if(presionado==btnCerrar){
			this.setVisible(false);
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}else if(presionado==btnBuscar){
			try
            {
                String search = tfBuscar.getText();

                int index = activo.getText().indexOf(search, startIndex);

                if (index == -1)
                {
                    this.startIndex = 0;
                    index = activo.getText().indexOf(search, startIndex);
                }
                
                activo.select(index, search.length());
                this.startIndex = index + search.length();

            }
            catch (Exception e)
            {
            	JOptionPane.showMessageDialog(null, "No se encontró ninguna coincidencia.","Error",JOptionPane.ERROR_MESSAGE);
            }
		}else if(presionado==btnReemplazar){
			try
            {
                String buscar = tfBuscar.getText();
                String reemplazar = tfReemplazar.getText();

                String txt = activo.getText();
                activo.setText(txt.replace(buscar, reemplazar));
            }
            catch (Exception e){};
		}
		
	}
}
