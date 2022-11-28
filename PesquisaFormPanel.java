import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class PesquisaFormPanel extends JPanel {
    private AppFrame frame;

    private Pesquisa pesquisa;

    private GridBagLayout layout;
    private GridBagConstraints constraints;

    private JTextField txtId;
    private JTextField txtInsituicao;
    private JTextField txtData;
    private JTextField txtVotos;
    private JTextField txtCandidato1;
    private JTextField txtCandidato2;
    private JTextField txtCandidato3;
    private JTextField txtVotos1;
    private JTextField txtVotos2;
    private JTextField txtVotos3;


    private JButton btnSalvar;
    private JButton btnCancelar;

    


    public PesquisaFormPanel(AppFrame frame) {
        this.frame = frame;

        layout = new GridBagLayout();
        constraints = new GridBagConstraints();

        setLayout(layout);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                if (pesquisa == null) {
                    txtId.setText("");
                    txtInsituicao.setText("");
                    txtData.setText("");
                    txtVotos.setText("");
                    txtCandidato1.setText("");
                    txtCandidato2.setText("");
                    txtCandidato3.setText("");
                    txtVotos1.setText("");
                    txtVotos2.setText("");
                    txtVotos3.setText("");
                } else {
                    txtId.setText(Integer.toString(pesquisa.getId()));
                    txtInsituicao.setText(pesquisa.getInsituicao());
                    txtData.setText(pesquisa.getData());
                    txtVotos.setText(pesquisa.getVotos());
                    txtCandidato1.setText(pesquisa.getC1());
                    txtCandidato2.setText(pesquisa.getC2());
                    txtCandidato3.setText(pesquisa.getC3());
                    txtVotos1.setText(pesquisa.getV1());
                    txtVotos2.setText(pesquisa.getV2());
                    txtVotos3.setText(pesquisa.getV3());


                }
            }
        });

        criarForm();
    }

    public void setPesquisa(Pesquisa pesquisa) {
        this.pesquisa = pesquisa;
    }

    private void criarForm() {
        JLabel label;

        label = new JLabel("Id");
        adicionarComponente(label, 0, 0);
        txtId = new JTextField(5);
        txtId.setEditable(false);
        adicionarComponente(txtId, 0, 1);

        label = new JLabel("Instituição");
        adicionarComponente(label, 1, 0);
        txtInsituicao = new JTextField(30);
        adicionarComponente(txtInsituicao, 1, 1);

        label = new JLabel("Data");
        adicionarComponente(label, 2, 0);
        txtData = new JTextField(20);
        adicionarComponente(txtData, 2, 1);

        label = new JLabel("Votos totais");
        adicionarComponente(label, 3, 0);
        txtVotos = new JTextField(50);
        adicionarComponente(txtVotos, 3, 1);

        label = new JLabel("Candidato 1");
        adicionarComponente(label, 4, 0);
        txtCandidato1 = new JTextField(50);
        adicionarComponente(txtCandidato1, 4, 1);

        label = new JLabel("Candidato 2");
        adicionarComponente(label, 5, 0);
        txtCandidato2 = new JTextField(50);
        adicionarComponente(txtCandidato2, 5, 1);

        label = new JLabel("Candidato 3");
        adicionarComponente(label, 6, 0);
        txtCandidato3 = new JTextField(50);
        adicionarComponente(txtCandidato3, 6, 1);

        label = new JLabel("Votos candidato 1");
        adicionarComponente(label, 4, 2);
        txtVotos1 = new JTextField(8);
        adicionarComponente(txtVotos1, 4, 3);

        label = new JLabel("Votos candidato 2");
        adicionarComponente(label, 5, 2);
        txtVotos2 = new JTextField(8);
        adicionarComponente(txtVotos2, 5, 3);

        label = new JLabel("Votos candidato 3");
        adicionarComponente(label, 6, 2);
        txtVotos3 = new JTextField(8);
        adicionarComponente(txtVotos3, 6, 3);


        criarBotoes();
    }


    private void criarBotoes() {
        JPanel btnPanel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) btnPanel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);

        criarBtnSalvar(btnPanel);
        criarBtnCancelar(btnPanel);

        adicionarComponente(btnPanel, 7, 1, 2, 1);
    }

    private void criarBtnSalvar(JPanel btnPanel) {
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (PesquisaFormPanel.this.pesquisa == null) {
                    Pesquisa novaPesquisa = new Pesquisa();
                    novaPesquisa.setInsituicao(txtInsituicao.getText());
                    novaPesquisa.setData(txtData.getText());
                    novaPesquisa.setVotos(txtVotos.getText());
                    novaPesquisa.setC1(txtCandidato1.getText());
                    novaPesquisa.setC2(txtCandidato2.getText());
                    novaPesquisa.setC3(txtCandidato3.getText());
                    novaPesquisa.setV1(txtVotos1.getText());
                    novaPesquisa.setV2(txtVotos2.getText());
                    novaPesquisa.setV3(txtVotos3.getText());

                    PesquisaStorage.inserir(novaPesquisa);
                    JOptionPane.showMessageDialog(PesquisaFormPanel.this, 
                                                  "Pesquisa incluida com sucesso", 
                                                  "Eleição 2022", 
                                                  JOptionPane.INFORMATION_MESSAGE);
                } else {
                    pesquisa.setInsituicao(txtInsituicao.getText());
                    pesquisa.setData(txtData.getText());
                    pesquisa.setVotos(txtVotos.getText());
                    pesquisa.setC1(txtCandidato1.getText());
                    pesquisa.setC2(txtCandidato2.getText());
                    pesquisa.setC3(txtCandidato3.getText());
                    pesquisa.setV1(txtVotos1.getText());
                    pesquisa.setV2(txtVotos2.getText());
                    pesquisa.setV3(txtVotos3.getText());
                    PesquisaStorage.atualizar(PesquisaFormPanel.this.pesquisa);
                    JOptionPane.showMessageDialog(PesquisaFormPanel.this, 
                                                  "Pesquisa atualizada com sucesso", 
                                                  "Eleição 2022", 
                                                  JOptionPane.INFORMATION_MESSAGE);
                }

                    
                frame.mostrarPesquisaListPanel();
            }
        });
        btnPanel.add(btnSalvar);
    }

    private void criarBtnCancelar(JPanel btnPanel) {
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarPesquisaListPanel();
            }
        });
        btnPanel.add(btnCancelar);
    }

    private void adicionarComponente(JComponent componente, 
                                    int linha, int coluna) {
        adicionarComponente(componente, linha, coluna, 1, 1);
    }                                    

    private void adicionarComponente(JComponent componente, 
                                    int linha, int coluna, 
                                    int largura, int altura) {
        constraints.gridx = coluna;
        constraints.gridy = linha;
        constraints.gridwidth = largura;
        constraints.gridheight = altura;

        constraints.fill = GridBagConstraints.HORIZONTAL;

        layout.setConstraints(componente, constraints);
        add(componente);
    }
} // fim da classe PesquisaFormPanel