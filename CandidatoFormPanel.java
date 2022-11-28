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

public class CandidatoFormPanel extends JPanel {
    private AppFrame frame;

    private Candidato candidato;

    private GridBagLayout layout;
    private GridBagConstraints constraints;

    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtNumero;
    private JTextField txtPartido;
    private JButton btnSalvar;
    private JButton btnCancelar;

    public CandidatoFormPanel(AppFrame frame) {
        this.frame = frame;

        layout = new GridBagLayout();
        constraints = new GridBagConstraints();

        setLayout(layout);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                if (candidato == null) {
                    txtId.setText("");
                    txtNome.setText("");
                    txtNumero.setText("");
                    txtPartido.setText("");
                } else {
                    txtId.setText(Integer.toString(candidato.getId()));
                    txtNome.setText(candidato.getNome());
                    txtNumero.setText(candidato.getNumero());
                    txtPartido.setText(candidato.getPartido());
                }
            }
        });

        criarForm();
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    private void criarForm() {
        JLabel label;

        label = new JLabel("Id");
        adicionarComponente(label, 0, 0);
        txtId = new JTextField(5);
        txtId.setEditable(false);
        adicionarComponente(txtId, 0, 1);

        label = new JLabel("Nome");
        adicionarComponente(label, 1, 0);
        txtNome = new JTextField(30);
        adicionarComponente(txtNome, 1, 1);

        label = new JLabel("Numero");
        adicionarComponente(label, 2, 0);
        txtNumero = new JTextField(20);
        adicionarComponente(txtNumero, 2, 1);

        label = new JLabel("Partido");
        adicionarComponente(label, 3, 0);
        txtPartido = new JTextField(50);
        adicionarComponente(txtPartido, 3, 1);

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
                if (CandidatoFormPanel.this.candidato == null) {
                    Candidato novoCandidato = new Candidato();
                    novoCandidato.setNome(txtNome.getText());
                    novoCandidato.setNumero(txtNumero.getText());
                    novoCandidato.setPartido(txtPartido.getText());

                    CandidatoStorage.inserir(novoCandidato);
                    JOptionPane.showMessageDialog(CandidatoFormPanel.this, 
                                                  "Candidato incluido com sucesso", 
                                                  "Eleição 2022", 
                                                  JOptionPane.INFORMATION_MESSAGE);
                } else {
                    candidato.setNome(txtNome.getText());
                    candidato.setNumero(txtNumero.getText());
                    candidato.setPartido(txtPartido.getText());
                    CandidatoStorage.atualizar(CandidatoFormPanel.this.candidato);
                    JOptionPane.showMessageDialog(CandidatoFormPanel.this, 
                                                  "Candidato atualizado com sucesso", 
                                                  "Eleição 2022", 
                                                  JOptionPane.INFORMATION_MESSAGE);
                }

                    
                frame.mostrarCandidatoListPanel();
            }
        });
        btnPanel.add(btnSalvar);
    }

    private void criarBtnCancelar(JPanel btnPanel) {
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarCandidatoListPanel();
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
} // fim da classe CandidatoFormPanel