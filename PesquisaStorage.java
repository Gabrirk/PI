import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PesquisaStorage {


    public static boolean inserir(Pesquisa pesquisa) {


        String query = "INSERT INTO pesquisa (insituicao, data, votos, c1, c2, c3, v1, v2, v3) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, pesquisa.getInsituicao());
            statement.setString(2, pesquisa.getData());
            statement.setString(3, pesquisa.getVotos());
            statement.setString(4, pesquisa.getC1());
            statement.setString(5, pesquisa.getC2());
            statement.setString(6, pesquisa.getC3());
            statement.setString(7, pesquisa.getV1());
            statement.setString(8, pesquisa.getV2());
            statement.setString(9, pesquisa.getV3());
            statement.execute();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                pesquisa.setId(resultSet.getInt(1));
            }
        } catch (SQLException e ) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }

                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    public static boolean atualizar(Pesquisa pesquisa) {


        String query = "UPDATE pesquisa SET insituicao = ?, data = ?, votos = ?, c1 = ?, c2 = ?, c3 = ?, v1 = ?, v2 = ?, v3 = ? WHERE id = ?";

        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query);
            statement.setString(1, pesquisa.getInsituicao());
            statement.setString(2, pesquisa.getData());
            statement.setString(3, pesquisa.getVotos());
            statement.setString(4, pesquisa.getC1());
            statement.setString(5, pesquisa.getC2());
            statement.setString(6, pesquisa.getC3());
            statement.setString(7, pesquisa.getV1());
            statement.setString(8, pesquisa.getV2());
            statement.setString(9, pesquisa.getV3());
            statement.setInt(10, pesquisa.getId());
            statement.execute();
        } catch (SQLException e ) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    public static boolean remover(Pesquisa pesquisa) {


        String query = "DELETE FROM pesquisa WHERE id = ?";

        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query);
            statement.setInt(1, pesquisa.getId());
            statement.execute();
        } catch (SQLException e ) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    public static List<Pesquisa> listar() {
        //return tarefas;

        List<Pesquisa> pesquisas = new ArrayList<>();

        String query = "SELECT * FROM pesquisa ORDER BY id";

        Connection conexao = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Pesquisa pesquisa = new Pesquisa();
                pesquisa.setId(resultSet.getInt("id"));
                pesquisa.setInsituicao(resultSet.getString("insituicao"));
                pesquisa.setData(resultSet.getString("data"));
                pesquisa.setVotos(resultSet.getString("votos"));
                pesquisa.setC1(resultSet.getString("c1"));
                pesquisa.setC2(resultSet.getString("c2"));
                pesquisa.setC3(resultSet.getString("c3"));
                pesquisa.setV1(resultSet.getString("v1"));
                pesquisa.setV2(resultSet.getString("v2"));
                pesquisa.setV3(resultSet.getString("v3"));

                pesquisas.add(pesquisa);
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }

                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return pesquisas;
    }
} // fim da classe PesquisaStorage
