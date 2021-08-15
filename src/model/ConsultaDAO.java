/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.io.File;
import view.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author evert
 */
public class ConsultaDAO {
    
  
  
    public void create(Consulta c) throws IOException{
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        
        ResultSet rs = null;
        
        
                try {
            stmt = con.prepareStatement("INSERT INTO ApsBiometria_informativos ( nm_fazendeiro, endereco, ds_produtoCultivado, agrotoxico, ic_restricao) "
                    + "VALUES(?,?,?,?,?)");
            stmt.setString(1, c.getNm_fazendeiro());
            stmt.setString(2, c.getEndereco());
            stmt.setString(3,c.getDs_produtoCultivado());
            stmt.setString(4, c.getAgrotoxico());
            stmt.setInt(5, c.getIc_restricao());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
            

    }

    public Consulta busca(String nm_fazendeiro) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM ApsBiometria_informativos WHERE nm_fazendeiro LIKE ?");
            stmt.setString(1, "%"+nm_fazendeiro+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                consulta = new Consulta(rs.getString("nm_fazendeiro"), rs.getString("endereco"), rs.getString("ds_produtoCultivado"), rs.getString("agrotoxico"), rs.getInt("ic_restricao"));
                consulta.setCd_registro(rs.getInt("cd_registro"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return consulta;

    }

    public void update(Consulta c) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            
            stmt = con.prepareStatement("UPDATE ApsBiometria_informativos SET nm_fazendeiro = ?, endereco = ?, ds_produtoCultivado = ?, agrotoxico = ?, ic_restricao = ? WHERE cd_registro = ?");
            stmt.setString(1, c.getNm_fazendeiro());
            stmt.setString(2, c.getEndereco());
            stmt.setString(3, c.getDs_produtoCultivado());
            stmt.setString(4, c.getAgrotoxico());
            stmt.setInt(5, c.getIc_restricao());
            stmt.setInt(6, c.getCd_registro());
        

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    
    public void delete(int codigo) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM ApsBiometria_informativos WHERE cd_registro = ?");
            stmt.setInt(1, codigo);

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "O registro foi excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    
}
