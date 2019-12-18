package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Archivo {
	private JFileChooser archivo1;
	private JFileChooser archivo2;
	private File entrada;
	protected static String buffer;
	protected static String direccion;
	protected static ArrayList<Registro> registros = new ArrayList<Registro>();

	public static class Registro {
		String titulo;
		String path;

		public Registro(String titulo, String path) {
			this.titulo = titulo;
			this.path = path;
		}

	}

	public static void agregar_registro(String nombre, String path) {
		Registro reg = new Registro(nombre, path);
		Archivo.registros.add(reg);
	}

	public static boolean eliminar_registro(String nombre) {
		for (int i = 0; i < Archivo.registros.size(); i++) {
			Registro reg = registros.get(i);
			if (reg.titulo.equals(nombre)) {
				Archivo.registros.remove(i);
				return true;
			}
		}
		return false;
	}

	public static String get_path_registro(String nombre) {
		for (int i = 0; i < Archivo.registros.size(); i++) {
			Registro reg = registros.get(i);
			if (reg.titulo.equals(nombre)) {
				return reg.path;
			}
		}
		return "";
	}

	public void leer_archivo() {
		try {
			archivo1 = new JFileChooser();
			FileFilter filtro = new FileNameExtensionFilter("Archivo TXT", "txt");
			archivo1.setFileFilter(filtro);
			int status = archivo1.showOpenDialog(archivo1);
			entrada = archivo1.getSelectedFile();

			JPanel selected = (JPanel) Ventana.tabbedPane.getSelectedComponent();
			JScrollPane scroll = (JScrollPane) selected.getComponent(0);
			JViewport view = (JViewport) scroll.getComponent(0);
			JTextArea txt = (JTextArea) view.getComponent(0);
			JTextArea textArea = new JTextArea();

			if (status == JFileChooser.APPROVE_OPTION) {
				String name = entrada.getName();
				String aux = name.replace(".txt", "");

				if (name.endsWith(".txt")) {
					Scanner lector = new Scanner(new BufferedReader(new FileReader(entrada)));
					buffer = "";
					while (lector.hasNext()) {
						String linea = lector.nextLine();
						if (buffer.isEmpty()) {
							buffer = linea;
						} else {
							buffer = buffer + "\n" + linea;
						}
					}
					lector.close();

					if (txt.getText().length() > 0) {
						JPanel pnl = new JPanel();
						pnl.setLayout(new BorderLayout(0, 0));

						JScrollPane scrollPane = new JScrollPane();
						pnl.add(scrollPane);

						TextLineNumber tln1 = new TextLineNumber(textArea);
						scrollPane.setViewportView(textArea);
						scrollPane.setRowHeaderView(tln1);

						textArea.setText(null);
						textArea.setText(buffer);

						Ventana.tabbedPane.addTab(aux, null, pnl, null);

						Archivo.agregar_registro(aux, entrada.getPath());

					} else {
						txt.setText(null);
						txt.setText(buffer);

						Ventana.tabbedPane.setTitleAt(getComponentIndex(selected), aux);

						Archivo.agregar_registro(aux, entrada.getPath());
					}
				} else {
					JOptionPane.showMessageDialog(null, "Verifique que ingres� un archivo .txt",
							"Informaci�n", JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (status == JFileChooser.CANCEL_OPTION) {
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error durante la lectura. Verifique que seleccion� el archivo correcto.",
					"Advertencia", JOptionPane.WARNING_MESSAGE);
		}
	}

	public void guardar_archivo_como() {

		JPanel selected = (JPanel) Ventana.tabbedPane.getSelectedComponent();
		JScrollPane scroll = (JScrollPane) selected.getComponent(0);
		JViewport view = (JViewport) scroll.getComponent(0);
		JTextArea txt = (JTextArea) view.getComponent(0);

		archivo2 = new JFileChooser();
		FileFilter filtro = new FileNameExtensionFilter("Archivo TXT", "txt");
		archivo2.setFileFilter(filtro);
		int status = archivo2.showSaveDialog(archivo2);
		entrada = archivo2.getSelectedFile();

		try {
			if (status == JFileChooser.APPROVE_OPTION) {
				File output;
				if (entrada.getName().contains(".txt")) {
					output = new File(entrada + "");
					entrada.delete();
				} else {
					output = new File(entrada + ".txt");
				}

				FileWriter writer = new FileWriter(output);
				BufferedWriter buf = new BufferedWriter(writer);
				buf.write(txt.getText());
				buf.flush();
				buf.close();
				writer.close();

				String name = entrada.getName();
				String aux = name.replace(".txt", "");
				aux = name.replace("*", "");

				Ventana.tabbedPane.setTitleAt(getComponentIndex(selected), aux);

				Archivo.agregar_registro(aux, entrada.getPath());

				JOptionPane.showMessageDialog(null, "El archivo se ha guardado correctamente.",
						"Informaci�n", JOptionPane.INFORMATION_MESSAGE);
			} else if (status == JFileChooser.CANCEL_OPTION) {
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error durante la lectura. Verifique que seleccion� el archivo correcto.",
					"Advertencia", JOptionPane.WARNING_MESSAGE);
		}
	}

	public void guardar_archivo() {

		JPanel selected = (JPanel) Ventana.tabbedPane.getSelectedComponent();
		JScrollPane scroll = (JScrollPane) selected.getComponent(0);
		JViewport view = (JViewport) scroll.getComponent(0);
		JTextArea txt = (JTextArea) view.getComponent(0);

		try {
			String nombre = Ventana.tabbedPane.getTitleAt(getComponentIndex(selected));
			nombre = nombre.replace("*", "");
			String path = Archivo.get_path_registro(nombre);

			File output = new File(path);
			output.delete();

			FileWriter writer = new FileWriter(output);
			BufferedWriter buf = new BufferedWriter(writer);
			buf.write(txt.getText());
			buf.flush();
			buf.close();
			writer.close();

			JOptionPane.showMessageDialog(null, "El archivo se ha guardado correctamente.",
					"Informaci�n", JOptionPane.INFORMATION_MESSAGE);
			Ventana.tabbedPane.setTitleAt(Archivo.getComponentIndex(selected), nombre);
		} catch (Exception e) {
			guardar_archivo_como();
		}
	}

	public static final int getComponentIndex(Component component) {
		if (component != null && component.getParent() != null) {
			Container c = component.getParent();
			for (int i = 0; i < c.getComponentCount(); i++) {
				if (c.getComponent(i) == component)
					return i;
			}
		}

		return -1;
	}

}