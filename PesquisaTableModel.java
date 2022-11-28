import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;


public class PesquisaTableModel extends AbstractTableModel {
    private List<Pesquisa> pesquisas = new ArrayList<>();
    private String[] colunas = new String[]{"Id", 
                                            "Instituição", 
                                            "Data",
                                            "Votos totais",
                                            "Candidato 1",
                                            "Votos",
                                            "Candidato",
                                            "Votos",
                                            "Candidato",
                                            "Votos"
                                            
                                                      };

    public PesquisaTableModel(List<Pesquisa> pesquisas) {
        this.pesquisas = pesquisas;
    }

    @Override
    public int getRowCount() {
        return pesquisas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int colIdx) {
        String colName = null;

        colName = colunas[colIdx];

        return colName;
    }

    @Override
    public Object getValueAt(int rowIdx, int colIdx) {
        String value = null;

        Pesquisa pesquisa = pesquisas.get(rowIdx);

        switch (colIdx) {
        case 0:
            value = Integer.toString(pesquisa.getId());
            break;
        case 1:
            value = pesquisa.getInsituicao();
            break;
        case 2:
            value = pesquisa.getData();
            break;
        case 3:
            value = pesquisa.getVotos();
            break;
        case 4:
            value = pesquisa.getC1();
            break;
        case 5:
            value = pesquisa.getV1();
            break;
        case 6:
            value = pesquisa.getC2();
            break;
        case 7:
            value = pesquisa.getV2();
            break;
        case 8:
            value = pesquisa.getC3();
            break;
        case 9:
            value = pesquisa.getV3();
            break;
        default:
            System.err.printf("[ERRO] Indice de coluna inválido: %d%n", 
                              colIdx);
        }

        return value;
    }

    public void carregarPesquisa(List<Pesquisa> pesquisa) {
        this.pesquisas = pesquisa;
        fireTableDataChanged();
    }

    public Pesquisa getPesquisa(int rowIdx) {
        Pesquisa pesquisa = null;

        pesquisa = pesquisas.get(rowIdx);

        return pesquisa;
    }

} // fim da classe PesquisaTableModel
