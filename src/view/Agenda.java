package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;

import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Agenda extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtFone;
	private JTextField txtEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agenda frame = new Agenda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Construtor
	 */
	public Agenda() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
			}
		});
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Agenda.class.getResource("/img/favicon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 484, 255);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(341, 26, 46, 14);
		contentPane.add(lblNewLabel);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(361, 23, 93, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(10, 26, 46, 14);
		contentPane.add(lblNewLabel_1);

		txtNome = new JTextField();
		txtNome.setBounds(49, 23, 225, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Fone");
		lblNewLabel_2.setBounds(10, 65, 46, 14);
		contentPane.add(lblNewLabel_2);

		txtFone = new JTextField();
		txtFone.setBounds(49, 62, 121, 20);
		contentPane.add(txtFone);
		txtFone.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(49, 102, 326, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setBounds(10, 105, 46, 14);
		contentPane.add(lblNewLabel_3);

		btnCreate = new JButton("");
		btnCreate.setEnabled(false);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarContato();
			}
		});
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.setContentAreaFilled(false);
		btnCreate.setBorderPainted(false);
		btnCreate.setToolTipText("Adicionar contato");
		btnCreate.setIcon(new ImageIcon(Agenda.class.getResource("/img/add.png")));
		btnCreate.setBounds(200, 144, 64, 64);
		contentPane.add(btnCreate);

		btnDelete = new JButton("");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					excluirContato();
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setContentAreaFilled(false);
		btnDelete.setBorderPainted(false);
		btnDelete.setToolTipText("Excluir contato");
		btnDelete.setIcon(new ImageIcon(Agenda.class.getResource("/img/delete.png")));
		btnDelete.setBounds(367, 144, 64, 64);
		contentPane.add(btnDelete);

		btnUpdate = new JButton("");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarContato();
			}
		});
		btnUpdate.setEnabled(false);
		btnUpdate.setToolTipText("Alterar contato");
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setBorderPainted(false);
		btnUpdate.setIcon(new ImageIcon(Agenda.class.getResource("/img/update.png")));
		btnUpdate.setBounds(287, 144, 64, 64);
		contentPane.add(btnUpdate);

		btnRead = new JButton("");
		btnRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarContato();
			}
		});
		btnRead.setIcon(new ImageIcon(Agenda.class.getResource("/img/search2.png")));
		btnRead.setToolTipText("Pesquisar contatos");
		btnRead.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRead.setContentAreaFilled(false);
		btnRead.setBorderPainted(false);
		btnRead.setBounds(299, 22, 32, 32);
		contentPane.add(btnRead);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Agenda.class.getResource("/img/dboff.png")));
		lblStatus.setBounds(28, 157, 48, 48);
		contentPane.add(lblStatus);

		// Uso da tecla <Enter> junto com o botão
		getRootPane().setDefaultButton(btnRead);

		// Uso da biblioteca atxy2k
		RestrictedTextField nome = new RestrictedTextField(txtNome);
		nome.setLimit(50);
		nome.setOnlyText(true);
		nome.setAcceptSpace(true);
		RestrictedTextField fone = new RestrictedTextField(txtFone);
		fone.setLimit(15);
		fone.setOnlyNums(true);
		RestrictedTextField email = new RestrictedTextField(txtEmail);
		email.setLimit(50);

	} // Fim do Construtor

	// Criar um objeto para acessar o método conectar() da classe DAO
	DAO dao = new DAO();
	private JLabel lblStatus;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnCreate;
	private JButton btnRead;

	// Método responsável por verificar o status da conexão
	/**
	 * Caminho -> botão direito JFrame -> Add evento handler -> window ->
	 * windowActiveted
	 */
	private void status() {
		// System.out.println("Teste - Janela Ativada");
		// Uso da classe Connection (JDBC) para estabelecer a conexão
		try {
			Connection con = dao.conectar();
			if (con == null) {
				// System.out.println("Erro de conexão");
				lblStatus.setIcon(new ImageIcon(Agenda.class.getResource("/img/dboff.png")));
			} else {
				// System.out.println("Banco conectado");
				lblStatus.setIcon(new ImageIcon(Agenda.class.getResource("/img/dbon.png")));
			}
			// Nunca esquecer de encerrar a conexão (Fechar a porta da geladeira)
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	} // Fim do Status

	/**
	 * Método responsável pela pesquisa (select) de um contato no banco
	 */
	private void pesquisarContato() {
		// Validação dos campos obrigatórios
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o nome do contato");
			txtNome.requestFocus();
		} else {

			// System.out.println("Teste pesquisar");
			// Iniciar com a instrução SQL
			String read = "select* from contatos where nome = ?";
			try {
				// Estabelecer a conexão (Abrir a porta da geladeira)
				Connection con = dao.conectar();
				// Preparar o código SQL para execução
				PreparedStatement pst = con.prepareStatement(read);
				// A linha abaixo substitui o ? pelo conteúdo da caixa de texto txtNome, o 1 faz
				// referência a interrogação
				pst.setString(1, txtNome.getText());
				// Obter dados do contato
				ResultSet rs = pst.executeQuery();
				// Verificar se existe um contato cadastrado
				// rs.next significa ter um contato correspondente ao nome pesquisado
				if (rs.next()) {
					// Setar as caixas de texto com o resultado da pesquisa
					txtId.setText(rs.getString(1));
					txtFone.setText(rs.getString(3));
					txtEmail.setText(rs.getString(4));
					// Habilitar botões (alterar e excluir)
					btnUpdate.setEnabled(true);
					btnDelete.setEnabled(true);

				} else {
					JOptionPane.showMessageDialog(null, "Contato inexistente");
					// Setar os campos e o botões
					txtFone.setText(null);
					txtEmail.setText(null);
					txtFone.requestFocus();
					btnCreate.setEnabled(true);

				}
				// Fechar conexão
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * Método responsável pela cadastro (adicionar) de um novo contato
	 */
	void adicionarContato() {
		// Validação dos campos obrigatórios
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, " Preencha o nome");
			txtNome.requestFocus();
		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o telefone");
		} else {

			// System.out.println("Teste adicionar");
			String create = "insert into contatos (nome,fone,email) values (?,?,?)";
			try {
				// Abrir a conexão
				Connection con = dao.conectar();
				// Preparar a query (Substituição de parâmetros)
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtFone.getText());
				pst.setString(3, txtEmail.getText());
				// Executar a query e confirmar a inserção no banco
				int confirma = pst.executeUpdate();
				// System.out.println(confirma);
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Contato adicionado!");
					limpar();
				}
				pst.executeUpdate();
				// Encerrar a conexão
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * Método responsável pela edição do contato
	 */
	private void alterarContato() {
		// System.out.println("Teste alterar");
		// Validação dos campos obrigatórios
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, " Preencha o nome");
			txtNome.requestFocus();
		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o telefone");
		} else {
			String update = "update contatos set nome = ?, fone = ?, email = ? where id = ?";
			try {
				// Abrir a conexão
				Connection con = dao.conectar();
				// Preparar a query (a instrução SQL))
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtFone.getText());
				pst.setString(3, txtEmail.getText());
				pst.setString(4, txtId.getText());

				// Executar a query e confirmar as alterações do banco
				int confirma = pst.executeUpdate();
				// System.out.println(confirma);
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso!");
					limpar();
				}
				pst.executeUpdate();
				// Encerrar a conexão
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	/**
	 * Método usado para excluir um contato
	 */
	private void excluirContato() {
		//System.out.println("Testar excluir");
		// Validação (confirmação)
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste contato?", "ATENÇÃO!", JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from contatos where id = ?";
			try {
				// Abrir a conexão
				Connection con = dao.conectar();
				// Preparar a query
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				// Executar o comando SQL e confirmar a exclusão
				int confirmaExcluir = pst.executeUpdate();
				if (confirmaExcluir == 1) {
					JOptionPane.showMessageDialog(null, "Contato excluído com sucesso");
					limpar();
					}
				// Encerrar a conexão
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
	}

	/**
	 * Método utilizado para limpar os campos e setar os botões
	 */
	private void limpar() {
		txtId.setText(null);
		txtNome.setText(null);
		txtFone.setText(null);
		txtEmail.setText(null);
		txtNome.requestFocus();
		btnCreate.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		btnRead.setEnabled(true);
	}

} // Fim do código