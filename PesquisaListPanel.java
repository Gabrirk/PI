import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class PesquisaListPanel extends JPanel {
    private AppFrame frame;

    private JButton btnCriar;
    private JButton btnEditar;
    private JButton btnRemover;
    private JButton btnCandidato;
    private JButton btnPesquisa;
    private JTable tabela;
    private PesquisaTableModel tableModelPesquisa;
    private JButton btnProcurar;

    private JTextField jtf;

    public PesquisaListPanel(AppFrame frame) {
        this.frame = frame;

        setLayout(new BorderLayout());

        criarComandosPanel();
        criarOpcoesPanel();
        criarTabelaPanel();
        
    }

    private void criarDesempenhoPanel(){
        jtf = new JTextField(2);

    }

    private void criarComandosPanel() {
        JPanel panel = new JPanel();
        FlowLayout layout = (FlowLayout) panel.getLayout();
        layout.setAlignment(FlowLayout.RIGHT);

        criarBtnCriar();
        panel.add(btnCriar);

        criarBtnEditar();
        panel.add(btnEditar);

        criarBtnRemover();
        panel.add(btnRemover);

        add(panel, BorderLayout.SOUTH);

        desabilitarBtns();
    }

    private void criarBtnProcurar() {
        btnProcurar = new JButton("Procurar");
        btnProcurar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModelPesquisa.carregarPesquisa(PesquisaStorage.listar());
                JOptionPane.showMessageDialog(PesquisaListPanel.this, 
                                                  "Procura realizada com sucesso", 
                                                  "Eleição 2022", 
                                                  JOptionPane.INFORMATION_MESSAGE);
                

            }
        });
        
    }

    private void criarOpcoesPanel(){
        JPanel panel = new JPanel();
        FlowLayout layout = (FlowLayout) panel.getLayout();
        layout.setAlignment(FlowLayout.CENTER);

        criarBtnCandidato();
        panel.add(btnCandidato);

        criarBtnPesquisa();
        panel.add(btnPesquisa);

        criarDesempenhoPanel();
        panel.add(jtf);

        criarBtnProcurar();
        panel.add(btnProcurar);

        add(panel, BorderLayout.NORTH);

    }




    private void criarBtnCriar() {
        btnCriar = new JButton("Criar");
        btnCriar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarPesquisaFormPanel(null);
            }
        });
    }

    private void criarBtnCandidato() {
        btnCandidato = new JButton("Candidatos");
        btnCandidato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarCandidatoListPanel();
            }
        });
    }
    private void criarBtnPesquisa() {
        btnPesquisa = new JButton("Pesquisas");
    }



    private void criarBtnEditar() {
        btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarPesquisaFormPanel(tableModelPesquisa.getPesquisa(tabela.getSelectedRow()));
            }
        });
    }

    private void criarBtnRemover() {
        btnRemover = new JButton("Remover");
        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pesquisa pesquisa = tableModelPesquisa.getPesquisa(tabela.getSelectedRow());
                int resposta = JOptionPane.showConfirmDialog(PesquisaListPanel.this, "Deseja realmente remover?", "Eleições 2022", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (resposta == JOptionPane.YES_OPTION) {
                    PesquisaStorage.remover(pesquisa);
                    recarregarPesquisa();
                }
            }
        });
    }

    private void criarTabelaPanel() {
        JPanel panel = new JPanel();

        tableModelPesquisa = new PesquisaTableModel(PesquisaStorage.listar());
        tabela = new JTable(tableModelPesquisa);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (tabela.getSelectedRow() >= 0) {
                        habilitarBtns();
                    } else {
                        desabilitarBtns();
                    }
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(tabela);
        panel.add(scrollPane);

        add(panel, BorderLayout.CENTER);
    }

    private void habilitarBtns() {
        btnEditar.setEnabled(true);
        btnRemover.setEnabled(true);
    }

    private void desabilitarBtns() {
        btnEditar.setEnabled(false);
        btnRemover.setEnabled(false);
    }

    public void recarregarPesquisa() {
        tableModelPesquisa.carregarPesquisa(PesquisaStorage.listar());
    }


    

} // fim da classe PesquisaListPanel