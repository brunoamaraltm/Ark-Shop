package gui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.json.JSONException;
import org.json.JSONObject;

public class Home {

	private JFrame frame;
	private JScrollPane scrollPane;
	private JTable table = new JTable();
	private DefaultTableModel dtm = new DefaultTableModel(0, 0);
	private JButton btnAdicionarItem = new JButton("Adicionar Item");
	private JSONObject result;
	private JButton btnSelecionarArquivo = new JButton("Selecionar Arquivo");
	private JButton btnSalvar = new JButton("Salvar");
	private JButton btnRemoverItem = new JButton("Apagar Linha");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
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
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				table.setBounds(0,0,frame.getWidth(), frame.getHeight()-80);
				scrollPane.setBounds(0, 0, frame.getWidth(), frame.getHeight()-80);
				btnSelecionarArquivo.setBounds(10, frame.getHeight()-73, 147,23);
				btnAdicionarItem.setBounds(frame.getWidth()-340, frame.getHeight()-73, 116, 23);
				btnSalvar.setBounds(frame.getWidth()-90, frame.getHeight()-73, 70, 23);
				btnRemoverItem.setBounds(frame.getWidth()-215, frame.getHeight()-73, 116, 23);
				TableColumnModel columnModel = table.getColumnModel();
			    for (int column = 0; column < table.getColumnCount(); column++) {
			        int width = 15;
			        for (int row = 0; row < table.getRowCount(); row++) {
			            TableCellRenderer renderer = table.getCellRenderer(row, column);
			            Component comp = table.prepareRenderer(renderer, row, column);
			            int rendererWidth = comp.getPreferredSize().width;
			            width = Math.max(comp.getPreferredSize().width +1 , width);
			        }
			        if(width > 300)
			            width=300;
			        columnModel.getColumn(column).setPreferredWidth(width);
			    }
			}
		});
		frame.setBounds(100, 100, 539, 382);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnSelecionarArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser abrir = new JFileChooser();
				FileNameExtensionFilter filtroJSON = new FileNameExtensionFilter("Arquivos JSON", "json");
				abrir.addChoosableFileFilter(filtroJSON);
				abrir.setAcceptAllFileFilterUsed(false);
				int retorno = abrir.showOpenDialog(null);
				if (retorno == JFileChooser.APPROVE_OPTION) {
					String caminho = abrir.getSelectedFile().getAbsolutePath();
					Scanner ler = new Scanner(System.in);
					try {
						FileReader arq = new FileReader(caminho);
						BufferedReader lerArq = new BufferedReader(arq);
						StringBuilder sb = new StringBuilder();
						String linha = lerArq.readLine();
						while (linha != null) {
							sb.append(linha);
							linha = lerArq.readLine();
						}
						try {
							result = new JSONObject(sb.toString()).getJSONObject("ShopItems");
							for (int i = 0; i < result.names().length(); i++) {
								String id = result.names().getString(i);
								String type = (String) result.getJSONObject(result.names().getString(i)).get("Type");
								String description = (String) result.getJSONObject(result.names().getString(i)).get("Description");
								Integer level = (Integer) result.getJSONObject(result.names().getString(i)).get("Level");
								Integer price = (Integer) result.getJSONObject(result.names().getString(i)).get("Price");
								String blueprint = (String) result.getJSONObject(result.names().getString(i)).get("Blueprint");
								dtm.addRow(new Object[] { id, type, description, level, price, blueprint
											
									});
							btnAdicionarItem.setEnabled(true);
							}
						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						TableColumnModel columnModel = table.getColumnModel();
					    for (int column = 0; column < table.getColumnCount(); column++) {
					        int width = 15;
					        for (int row = 0; row < table.getRowCount(); row++) {
					            TableCellRenderer renderer = table.getCellRenderer(row, column);
					            Component comp = table.prepareRenderer(renderer, row, column);
					            width = Math.max(comp.getPreferredSize().width +1 , width);
					        }
					        if(width > 300)
					            width=300;
					        columnModel.getColumn(column).setPreferredWidth(width);
					    }
					    frame.setBounds(100,100,table.getWidth(),382);
						arq.close();
					} catch (IOException ee) {
						System.err.printf("Erro na abertura do arquivo: %s.\n",
						ee.getMessage());
					}
				}
			}
		});
		btnSelecionarArquivo.setBounds(10, 309, 147, 23);
		frame.getContentPane().add(btnSelecionarArquivo);
		
		String header[] = new String[] {"ID", "Type", "Description", "Level", "Price", "Blueprint"};
		dtm.setColumnIdentifiers(header);
		table.setModel(dtm);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 523, 298);
		frame.getContentPane().add(scrollPane);
		
		scrollPane.setViewportView(table);
		
		btnAdicionarItem.setEnabled(false);
		btnAdicionarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtm.addRow(new Object[] { null, null, null, null, null, null
						
				});
			}
		});
		
		btnRemoverItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] linhas = table.getSelectedRows();
				for (int i = 0; i < linhas.length; i++) {
					dtm.removeRow(linhas[0]);
				}
			}
		});
		
		
		
		btnAdicionarItem.setBounds(319, 309, 101, 23);
		frame.getContentPane().add(btnAdicionarItem);
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tableRows = table.getRowCount();
				StringBuilder sb = new StringBuilder();
				File file = new File("arquivo.txt");
				BufferedWriter bw = null;
				try {
					file.createNewFile();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				for (int i = 0; i < tableRows; i++) {
					String tableId = table.getModel().getValueAt(i, 0).toString();
					String tableType = table.getModel().getValueAt(i, 1).toString();
					String tableDescription = table.getModel().getValueAt(i, 2).toString();
					String tableLevel = table.getModel().getValueAt(i, 3).toString();
					String tablePrice = table.getModel().getValueAt(i, 4).toString();
					String tableBlueprint = table.getModel().getValueAt(i, 5).toString();
					try {
						sb.append("\n");
						sb.append("	  " + '"'+tableId+'"'+":{\n");
						sb.append("		" + '"'+"Type"+'"'+":"+'"'+tableType+'"'+","+"\n");
						sb.append("		" + '"'+"Description"+'"'+":"+'"'+tableDescription+'"'+","+"\n");
						sb.append("		" + '"'+"Level"+'"'+":"+tableLevel+","+"\n");
						sb.append("		" + '"'+"Price"+'"'+":"+tablePrice+","+"\n");
						if (i == tableRows-1) {
							sb.append("		" + '"'+"Blueprint"+'"'+":"+'"'+tableBlueprint+'"'+"\n" + "	  " +"}");
						} else {
							sb.append("		" + '"'+"Blueprint"+'"'+":"+'"'+tableBlueprint+'"'+"\n" + "	  " +"},");
						}
						FileWriter writer = new FileWriter(file);
						bw = new BufferedWriter(writer);
						bw.write(sb.toString());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} finally {
						try {
							bw.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnSalvar.setBounds(424, 309, 89, 23);
		frame.getContentPane().add(btnSalvar);
		
		frame.getContentPane().add(btnRemoverItem);
	}
}
