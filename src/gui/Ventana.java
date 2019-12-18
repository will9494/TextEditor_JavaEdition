package gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.EtchedBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JViewport;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Robot;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.awt.Toolkit;

public class Ventana implements ActionListener {
	public JFrame frmSintax;
	public Find frameFind;
	private JPanel panel;
	private JMenuBar menuBar;
	private JMenuBar menuBar_1;
	private JMenu mnNewMenu;
	private JMenu mnNewMenu_1;
	private JLayeredPane layeredPane;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JLayeredPane layeredPane_1;
	private JLabel lblFila;
	private JLabel lblColumna;
	private JMenu mnAyuda;
	private JMenuItem mbtnOpen;
	private JMenuItem mbtnNew;
	private JMenuItem mbtnSave;
	private JMenuItem mbtnSaveAs;
	private JMenuItem mbtnExit;
	private JMenuItem mbtnCut;
	private JMenuItem mbtnCopy;
	private JMenuItem mbtnPaste;
	private JMenuItem mbtnInfo;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JButton tbtnPaste;
	private JButton tbtnCopy;
	private JButton tbtnFind;
	private JButton tbtnInfo;
	private JButton tbtnOpen;
	private JButton tbtnNew;
	private JButton tbtnClose;
	private JButton tbtnSave;
	private JButton tbtnSaveAs;
	private JButton tbtnCut;
	private JLabel lblNewLabel;
	private JToolBar toolBar;
	private JTextArea areaTexto;
	private CaretListener listener;
	private KeyListener listener1;
	public static String informacion;
	public static JTabbedPane tabbedPane;
	
	/**
	 * Create the application.
	 */
	public Ventana() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Ventana.informacion="Autor:  William Fernando Valladares Muñoz\n";
		Ventana.informacion+="CustomTextEditor\n";
		Ventana.informacion+="Versión: 1.0\n";
		
		frmSintax = new JFrame();		
		frmSintax.setTitle("SINTAX");
		frmSintax.setIconImage(Toolkit.getDefaultToolkit().getImage(Ventana.class.getResource("/GUI/Images/Run.png")));
		frmSintax.setBounds(100, 100, 645, 391);
		frmSintax.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		
		menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.activeCaptionBorder);
		frmSintax.setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("Archivo");
		mnNewMenu.setBackground(SystemColor.activeCaptionBorder);
		menuBar.add(mnNewMenu);
		
		mbtnOpen = new JMenuItem("Abrir");
		mbtnOpen.setIcon(new ImageIcon(Ventana.class.getResource("/GUI/Images/Open.png")));
		mbtnOpen.addActionListener(this);
		mnNewMenu.add(mbtnOpen);
		
		mbtnNew = new JMenuItem("Nuevo");
		mbtnNew.setIcon(new ImageIcon(Ventana.class.getResource("/GUI/Images/New.png")));
		mbtnNew.addActionListener(this);
		mnNewMenu.add(mbtnNew);
		
		mbtnSave = new JMenuItem("Guardar");
		mbtnSave.setIcon(new ImageIcon(Ventana.class.getResource("/GUI/Images/Save.png")));
		mbtnSave.addActionListener(this);
		mnNewMenu.add(mbtnSave);
		
		mbtnSaveAs = new JMenuItem("Guardar como...");
		mbtnSaveAs.setIcon(new ImageIcon(Ventana.class.getResource("/GUI/Images/Saveas.png")));
		mbtnSaveAs.addActionListener(this);
		mnNewMenu.add(mbtnSaveAs);
		
		mbtnExit = new JMenuItem("Salir");
		mbtnExit.setIcon(new ImageIcon(Ventana.class.getResource("/GUI/Images/Exit.png")));
		mbtnExit.addActionListener(this);
		mnNewMenu.add(mbtnExit);
		
		mnNewMenu_1 = new JMenu("Editar");
		mnNewMenu_1.setBackground(SystemColor.activeCaptionBorder);
		menuBar.add(mnNewMenu_1);
		
		mbtnCut = new JMenuItem("Cortar");
		mbtnCut.setIcon(new ImageIcon(Ventana.class.getResource("/GUI/Images/Cut.png")));
		mbtnCut.addActionListener(this);
		mnNewMenu_1.add(mbtnCut);
		
		mbtnCopy = new JMenuItem("Copiar");
		mbtnCopy.setIcon(new ImageIcon(Ventana.class.getResource("/GUI/Images/Copy.png")));
		mbtnCopy.addActionListener(this);
		mnNewMenu_1.add(mbtnCopy);
		
		mbtnPaste = new JMenuItem("Pegar");
		mbtnPaste.setIcon(new ImageIcon(Ventana.class.getResource("/GUI/Images/Paste.png")));
		mbtnPaste.addActionListener(this);
		mnNewMenu_1.add(mbtnPaste);
		
		mnAyuda = new JMenu("Ayuda");
		mnAyuda.setBackground(SystemColor.activeCaptionBorder);
		menuBar.add(mnAyuda);
		
		mbtnInfo = new JMenuItem("Acerca de...");
		mbtnInfo.setIcon(new ImageIcon(Ventana.class.getResource("/GUI/Images/About.png")));
		mbtnInfo.addActionListener(this);
		mnAyuda.add(mbtnInfo);
		
		panel = new JPanel();
		frmSintax.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		menuBar_1 = new JMenuBar();
		menuBar_1.setBackground(SystemColor.activeCaptionBorder);
		menuBar_1.setFont(new Font("Consolas", Font.PLAIN, 12));
		panel.add(menuBar_1, BorderLayout.SOUTH);
		
		lblFila = new JLabel("  Fila:   ");
		lblFila.setFont(new Font("Consolas", Font.PLAIN, 12));
		menuBar_1.add(lblFila);
		
		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		menuBar_1.add(separator_1);
		
		lblColumna = new JLabel("  Columna:  ");
		lblColumna.setFont(new Font("Consolas", Font.PLAIN, 12));
		menuBar_1.add(lblColumna);
		
		separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		menuBar_1.add(separator_2);
		
		layeredPane_1 = new JLayeredPane();
		menuBar_1.add(layeredPane_1);
		layeredPane_1.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel("  CustomTextEditor");
		menuBar_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 12));
		
		layeredPane = new JLayeredPane();
		layeredPane.setToolTipText("Entradas");
		layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane.setBackground(Color.WHITE);
		panel.add(layeredPane, BorderLayout.CENTER);
		layeredPane.setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		layeredPane.add(tabbedPane);
		
		panel_1 = new JPanel();
		panel_1.setToolTipText("");
		tabbedPane.addTab("Nuevo", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		
	    areaTexto = new JTextArea();
	    areaTexto.setFont(new Font("Consolas",Font.PLAIN,13));
	    @SuppressWarnings("unused")
		LinePainter painter = new LinePainter(areaTexto, new Color(229,229,255));
	    
	    TextLineNumber numCol = new TextLineNumber(areaTexto);	//Comentar para utilizar el WindowBuilder de Eclipse
	    //JPanel numCol = new JPanel();		//Descomentar para utilizar el WindowBuilder de Eclipse
	    numCol.setFont(new Font("Consolas",Font.PLAIN,12));
	    numCol.setForeground(Color.GRAY);
	    	    
	    scrollPane.setViewportView(areaTexto);
	    scrollPane.setRowHeaderView(numCol);
	    
	    toolBar = new JToolBar();
	    toolBar.setFont(new Font("Segoe UI", Font.PLAIN, 9));
	    toolBar.setBackground(SystemColor.inactiveCaption);
	    toolBar.setFloatable(false);
	    layeredPane.add(toolBar, BorderLayout.NORTH);
	    
	    tbtnNew = new JButton("");
	    tbtnNew.setToolTipText("Nuevo documento");
	    tbtnNew.setIcon(new ImageIcon(Ventana.class.getResource("/GUI/Images/New.png")));
	    tbtnNew.setBackground(SystemColor.inactiveCaption);
	    tbtnNew.addActionListener(this);
	    toolBar.add(tbtnNew);
	    
	    tbtnOpen = new JButton("");
	    tbtnOpen.setToolTipText("Abrir documento");
	    tbtnOpen.setIcon(new ImageIcon(Ventana.class.getResource("/GUI/Images/Open.png")));
	    tbtnOpen.setBackground(SystemColor.inactiveCaption);
	    tbtnOpen.addActionListener(this);
	    toolBar.add(tbtnOpen);
	    
	    tbtnSave = new JButton("");
	    tbtnSave.setToolTipText("Guardar");
	    tbtnSave.setIcon(new ImageIcon(Ventana.class.getResource("/GUI/Images/Save.png")));
	    tbtnSave.setBackground(SystemColor.inactiveCaption);
	    tbtnSave.addActionListener(this);
	    
	    tbtnFind = new JButton("");
	    tbtnFind.setToolTipText("Buscar/Reemplazar");
	    tbtnFind.setIcon(new ImageIcon(Ventana.class.getResource("/GUI/Images/Find.png")));
	    tbtnFind.setBackground(SystemColor.inactiveCaption);
	    tbtnFind.addActionListener(this);
	    toolBar.add(tbtnFind);
	    
	    tbtnClose = new JButton("");
	    tbtnClose.setToolTipText("Cerrar documento");
	    tbtnClose.setIcon(new ImageIcon(Ventana.class.getResource("/GUI/Images/Error.png")));
	    tbtnClose.setBackground(SystemColor.inactiveCaption);
	    tbtnClose.addActionListener(this);
	    
	    toolBar.addSeparator();
	    toolBar.add(tbtnClose);
	    
	    toolBar.addSeparator();
	    toolBar.add(tbtnSave);
	    
	    tbtnSaveAs = new JButton("");
	    tbtnSaveAs.setToolTipText("Guardar como...");
	    tbtnSaveAs.setIcon(new ImageIcon(Ventana.class.getResource("/GUI/Images/Saveas.png")));
	    tbtnSaveAs.setBackground(SystemColor.inactiveCaption);
	    tbtnSaveAs.addActionListener(this);
	    toolBar.add(tbtnSaveAs);
	    
	    toolBar.addSeparator();
	    
	    tbtnCut = new JButton("");
	    tbtnCut.setToolTipText("Cortar");
	    tbtnCut.setIcon(new ImageIcon(Ventana.class.getResource("/GUI/Images/Cut.png")));
	    tbtnCut.setBackground(SystemColor.inactiveCaption);
	    tbtnCut.addActionListener(this);
	    toolBar.add(tbtnCut);
	    
	    tbtnCopy = new JButton("");
	    tbtnCopy.setToolTipText("Copiar");
	    tbtnCopy.setIcon(new ImageIcon(Ventana.class.getResource("/GUI/Images/Copy.png")));
	    tbtnCopy.setBackground(SystemColor.inactiveCaption);
	    tbtnCopy.addActionListener(this);
	    toolBar.add(tbtnCopy);
	    
	    tbtnPaste = new JButton("");
	    tbtnPaste.setToolTipText("Pegar");
	    tbtnPaste.setIcon(new ImageIcon(Ventana.class.getResource("/GUI/Images/Paste.png")));
	    tbtnPaste.setBackground(SystemColor.inactiveCaption);
	    tbtnPaste.addActionListener(this);
	    toolBar.add(tbtnPaste);
	    
	    toolBar.addSeparator();
	    
	    tbtnInfo = new JButton("");
	    tbtnInfo.setToolTipText("Acerca de...");
	    tbtnInfo.setIcon(new ImageIcon(Ventana.class.getResource("/GUI/Images/About.png")));
	    tbtnInfo.setBackground(SystemColor.inactiveCaption);
	    tbtnInfo.addActionListener(this);
	    toolBar.add(tbtnInfo);
	    
	    listener = new CaretListener() {
            public void caretUpdate(CaretEvent e) {
            	JPanel selected = (JPanel)Ventana.tabbedPane.getSelectedComponent();
        		JScrollPane scroll = (JScrollPane)selected.getComponent(0);
        		JViewport view = (JViewport)scroll.getComponent(0);
        		JTextArea editArea = (JTextArea)view.getComponent(0);

                int linenum = 1;
                int columnnum = 1;

                try {
                    int caretpos = editArea.getCaretPosition();
                    linenum = editArea.getLineOfOffset(caretpos);
                    columnnum = caretpos - editArea.getLineStartOffset(linenum);

                    linenum += 1;
                    columnnum += 1;
                }
                catch(Exception ex) { }

                updateStatus(linenum, columnnum);
            }
	      };
	        
	    listener1 = new KeyListener() {
    	  	
            public void keyPressed(KeyEvent e) {
            	JPanel selected = (JPanel)Ventana.tabbedPane.getSelectedComponent();
	    	  	String nombre = Ventana.tabbedPane.getTitleAt(Archivo.getComponentIndex(selected));
                if (!nombre.contains("*"))
                {
                	Ventana.tabbedPane.setTitleAt(Archivo.getComponentIndex(selected), nombre+"*");
                }
            }
            
            public void keyTyped(KeyEvent e){
            	JPanel selected = (JPanel)Ventana.tabbedPane.getSelectedComponent();
	    	  	String nombre = Ventana.tabbedPane.getTitleAt(Archivo.getComponentIndex(selected));
                if (!nombre.contains("*"))
                {
                	Ventana.tabbedPane.setTitleAt(Archivo.getComponentIndex(selected), nombre+"*");
                }
            }

			public void keyReleased(KeyEvent arg0) {
				
			}
      };
      
      	areaTexto.addCaretListener(listener);
      	areaTexto.addKeyListener(listener1);
      	updateStatus(1, 1);
	}
	
	/**
	 * Updates column and line numbers in status bar
	 */
	private void updateStatus(int linenumber, int columnnumber) {
        lblColumna.setText(" Columna: " + columnnumber + "   ");
        lblFila.setText(" Fila: " + linenumber + "   ");
    }
	
	/**
	 * Performs actions when a button is pressed
	 */
	@Override
	public void actionPerformed(ActionEvent evento) {
		
		Object presionado = evento.getSource();
		
		if(presionado==mbtnExit){
			int respuesta = JOptionPane.showConfirmDialog(null,"¿Está seguro de salir?", "Atención!",
			JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_NO_OPTION){
				System.exit(0);
            }
		}else if(presionado==mbtnInfo || presionado == tbtnInfo){
			JOptionPane.showMessageDialog(null, informacion,"INFORMACION",JOptionPane.INFORMATION_MESSAGE);
		}else if(presionado==mbtnNew || presionado == tbtnNew){
			JPanel pnl = new JPanel();
		    pnl.setToolTipText("");
		    tabbedPane.addTab("Nuevo", null, pnl, null);
		    pnl.setLayout(new BorderLayout(0, 0));
		    
		    JScrollPane scrollPane = new JScrollPane();
		    pnl.add(scrollPane);
		    
		    JTextArea areaTexto = new JTextArea();
		    areaTexto.addCaretListener(listener);
		    areaTexto.addKeyListener(listener1);
		    areaTexto.setFont(new Font("Consolas",Font.PLAIN,13));
		    @SuppressWarnings("unused")
			LinePainter painter = new LinePainter(areaTexto, new Color(229,229,255));
		    
		    TextLineNumber numCol = new TextLineNumber(areaTexto);
		    numCol.setFont(new Font("Consolas",Font.PLAIN,12));
		    numCol.setForeground(Color.GRAY);
		    	    
		    scrollPane.setViewportView(areaTexto);
		    scrollPane.setRowHeaderView(numCol);
		}else if(presionado == tbtnClose){
			JPanel selected = (JPanel)tabbedPane.getSelectedComponent();
			JScrollPane scroll = (JScrollPane)selected.getComponent(0);
			JViewport view = (JViewport)scroll.getComponent(0);
			JTextArea txt = (JTextArea)view.getComponent(0);
			String texto = txt.getText();
			texto = texto.replace("\n","");
            texto = texto.replace("\t", "");
            texto = texto.replace("\r", "");
            texto = texto.replace("\f", "");
			
			if(texto.length()>0) {
				int respuesta = JOptionPane.showConfirmDialog(null,"¿Desea guardar cambios?", "Atención!",JOptionPane.YES_NO_OPTION);
				if (respuesta == JOptionPane.YES_NO_OPTION){
					tbtnSave.doClick();
				}
				
				if(tabbedPane.getComponentCount()>1){
					tabbedPane.remove(selected);
				}else {
					
					String titulo = Ventana.tabbedPane.getTitleAt(Archivo.getComponentIndex(selected));
					Archivo.eliminar_registro(titulo);
					
					tabbedPane.remove(selected);
					tbtnNew.doClick();
				}
			}else {
				if(tabbedPane.getComponentCount()>1){
					tabbedPane.remove(selected);
				}else {
					tabbedPane.remove(selected);
					tbtnNew.doClick();
				}
			}
		}else if(presionado==mbtnSave || presionado == tbtnSave){
			Archivo entrada = new Archivo();
			entrada.guardar_archivo();
		}else if(presionado==mbtnSaveAs || presionado == tbtnSaveAs){
			Archivo entrada = new Archivo();
			entrada.guardar_archivo_como();
		}else if(presionado==mbtnOpen || presionado == tbtnOpen){
			Archivo entrada = new Archivo();
			entrada.leer_archivo();
		}else if(presionado==mbtnCut){
			try {
			    Robot robot = new Robot();
			    robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_X);
			} catch (Exception e) {}
		}else if(presionado==tbtnCut){
			try {
				JPanel selected = (JPanel)tabbedPane.getSelectedComponent();
				JScrollPane scroll = (JScrollPane)selected.getComponent(0);
				JViewport view = (JViewport)scroll.getComponent(0);
				JTextArea txt = (JTextArea)view.getComponent(0);
				
				txt.requestFocus();
				
			    Robot rob = new Robot();
			    rob.keyPress(KeyEvent.VK_CONTROL);
                rob.keyPress(KeyEvent.VK_X);
			} catch (Exception e) {}
		}else if(presionado==mbtnCopy){
			try {
			    Robot rob = new Robot();
			    rob.keyPress(KeyEvent.VK_CONTROL);
                rob.keyPress(KeyEvent.VK_C);
			} catch (Exception e) {}
		}else if(presionado==tbtnCopy){
			try {
				JPanel selected = (JPanel)tabbedPane.getSelectedComponent();
				JScrollPane scroll = (JScrollPane)selected.getComponent(0);
				JViewport view = (JViewport)scroll.getComponent(0);
				JTextArea txt = (JTextArea)view.getComponent(0);
				
				txt.requestFocus();
				
			    Robot rob = new Robot();
			    rob.keyPress(KeyEvent.VK_CONTROL);
                rob.keyPress(KeyEvent.VK_C);
			} catch (Exception e) {}
		}else if(presionado==mbtnPaste){
			try {
			    Robot robot = new Robot();
			    robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_V);
			} catch (Exception e) {}
		}else if(presionado == tbtnPaste){
			try {
				JPanel selected = (JPanel)tabbedPane.getSelectedComponent();
				JScrollPane scroll = (JScrollPane)selected.getComponent(0);
				JViewport view = (JViewport)scroll.getComponent(0);
				JTextArea txt = (JTextArea)view.getComponent(0);
				
				txt.requestFocus();
				
			    Robot robot = new Robot();
			    robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_V);
			} catch (Exception e) {}
		}else if(presionado==tbtnFind){
			try
            {
                frameFind.setVisible(false);
                frameFind.setVisible(true);
            }
            catch (Exception e)
            {
            	JPanel selected = (JPanel)tabbedPane.getSelectedComponent();
    			JScrollPane scroll = (JScrollPane)selected.getComponent(0);
    			JViewport view = (JViewport)scroll.getComponent(0);
    			JTextArea txt = (JTextArea)view.getComponent(0);
                frameFind = new Find(txt,txt.getSelectedText(),this);
                frameFind.setVisible(true);
            }
		}
	}
	
	private int aux ;
	protected int incremento(){
	    return aux++;
	}

	public void Graficar(String cadena,String cad){
	    FileWriter fichero = null;
	    PrintWriter pw = null;
	    String archivo=cad+".dot";
	    try {
	        fichero = new FileWriter(archivo);
	        pw = new PrintWriter(fichero);
	        pw.println("digraph G {node[shape=box, style=filled, color=Gray95]; edge[color=blue];rankdir=UD \n");
	        pw.println(cadena);
	        pw.println("\n}");
	        fichero.close();
	    } catch (Exception e) {
	        System.out.println(e);
	    } 
	    try {
	        String cmd = "dot.exe -Tpng "+cad+".dot -o" + cad + ".png"; 
	        Runtime.getRuntime().exec(cmd); 
	    } catch (IOException ioe) {
	            System.out.println (ioe);
	    }
	}

}