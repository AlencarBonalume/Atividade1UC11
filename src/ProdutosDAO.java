

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto (ProdutosDTO produto){
        String sql = "INSERT INTO produtos(nome,valor,status) VALUES(?,?,?)";

        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);

            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "O produto foi cadastrado com sucesso");
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "O produto não foi cadastrado "+ ex.getMessage());
        }
    }

    public void venderProduto(int id) {
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);

            prep.setInt(1, id);

            prep.executeUpdate();
        }catch(SQLException ex) {
            
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos(){
        String sql = "SELECT * FROM produtos";

        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            while(resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));

                listagem.add(produto);
            }
            
        }catch(SQLException ex) {
            
        }

        return listagem;
    }

    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";

        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            while(resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));

                listagem.add(produto);
            }
        }catch(SQLException ex) {
            
        }

        return listagem;
    }
}
