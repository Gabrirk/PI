import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AppFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    private CandidatoListPanel candidatoListPanel;
    private CandidatoFormPanel candidatoFormPanel;
    private PesquisaListPanel pesquisaListPanel;
    private PesquisaFormPanel pesquisaFormPanel;

    public AppFrame() {
        super("Eleição 2022");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        cardLayout = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setLayout(cardLayout);
        add(cardPanel);

        criarCards();
    }

    public void mostrar() {
        pack();
		setLocationRelativeTo(null);
		setVisible(true);
    }

    private void criarCards() {
        candidatoListPanel = new CandidatoListPanel(this);
        cardPanel.add(candidatoListPanel, CandidatoListPanel.class.getName());

        candidatoFormPanel = new CandidatoFormPanel(this);
        cardPanel.add(candidatoFormPanel, CandidatoFormPanel.class.getName());

        pesquisaListPanel = new PesquisaListPanel(this);
        cardPanel.add(pesquisaListPanel, PesquisaListPanel.class.getName());

        pesquisaFormPanel = new PesquisaFormPanel(this);
        cardPanel.add(pesquisaFormPanel, PesquisaFormPanel.class.getName());


    }

    public void mostrarCandidatoFormPanel(Candidato candidato) {
        candidatoFormPanel.setCandidato(candidato);
        cardLayout.show(cardPanel, CandidatoFormPanel.class.getName());
    }

    public void mostrarCandidatoListPanel() {
        candidatoListPanel.recarregar();
        cardLayout.show(cardPanel, CandidatoListPanel.class.getName());
    }

    public void mostrarPesquisaFormPanel(Pesquisa pesquisa) {
        pesquisaFormPanel.setPesquisa(pesquisa);
        cardLayout.show(cardPanel, PesquisaFormPanel.class.getName());
    }

    public void mostrarPesquisaListPanel() {
        pesquisaListPanel.recarregarPesquisa();
        cardLayout.show(cardPanel, PesquisaListPanel.class.getName());
    }


} // fim da classe AppFrame