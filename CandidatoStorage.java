import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CandidatoStorage {


    public static boolean inserir(Candidato candidato) {


        String query = "INSERT INTO candidatos (nome, numero, partido) VALUES (?, ?, ?)";

        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, candidato.getNome());
            statement.setString(2, candidato.getNumero());
            statement.setString(3, candidato.getPartido());
            statement.execute();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                candidato.setId(resultSet.getInt(1));
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

    public static boolean atualizar(Candidato candidato) {


        String query = "UPDATE candidatos SET nome = ?, numero = ?, partido = ? WHERE id = ?";

        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query);
            statement.setString(1, candidato.getNome());
            statement.setString(2, candidato.getNumero());
            statement.setString(3, candidato.getPartido());
            statement.setInt(4, candidato.getId());
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

    public static boolean remover(Candidato candidato) {

        String query = "DELETE FROM candidatos WHERE id = ?";

        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query);
            statement.setInt(1, candidato.getId());
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

    public static List<Candidato> listar() {

        List<Candidato> candidatos = new ArrayList<>();

        String query = "SELECT * FROM candidatos ORDER BY id";

        Connection conexao = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Candidato candidato = new Candidato();
                candidato.setId(resultSet.getInt("id"));
                candidato.setNome(resultSet.getString("nome"));
                candidato.setNumero(resultSet.getString("numero"));
                candidato.setPartido(resultSet.getString("partido"));

                candidatos.add(candidato);
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

        return candidatos;
    }
} // fim da classe CandidatoStorage
