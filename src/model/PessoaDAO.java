/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author evert
 */
public class PessoaDAO {
    
    public boolean checkLogin(String login, String senha) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean check = false;

        try {

            stmt = con.prepareStatement("SELECT * FROM ApsBiometria WHERE nm_usuario = ? and cd_senha = ?");
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            if (rs.next()) {

                
                check = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return check;

    }
    
    public boolean checkUsuario(String login) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean check = false;

        try {

            stmt = con.prepareStatement("SELECT * FROM ApsBiometria WHERE nm_usuario = ?");
            stmt.setString(1, login);
            rs = stmt.executeQuery();

            if (rs.next()) {

                
                check = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return check;
    }
    
    public int checkRestricao(String login) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int restricao = 0;
        try {

            stmt = con.prepareStatement("SELECT * FROM ApsBiometria WHERE  nm_usuario = ? ");
            stmt.setString(1, login);
    
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                restricao = rs.getInt("ic_restricao");
            }
            
          
                
                

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return restricao;

    }
    
  
    public void create(Pessoa p) throws IOException{
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        
        ResultSet rs = null;
        
         try {

            stmt = con.prepareStatement("SELECT * FROM ApsBiometria WHERE  nm_usuario = ? ");
            stmt.setString(1, p.getLogin());
    
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Já existe um cadastro com este usuário!");
            }else{
                try {
            stmt = con.prepareStatement("INSERT INTO ApsBiometria (nm_pessoa, nm_usuario, cd_senha,ic_restricao, foto1, foto2, foto3, foto4) "
                    + "VALUES(?,?,?,?,?,?,?,?)");
            stmt.setString(1,p.getNomeCompleto());
            stmt.setString(2, p.getLogin());
            stmt.setString(3, p.getSenha());
            stmt.setInt(4,p.getIc_restricao());
            stmt.setBlob(5, p.getFotoBiometria1());
            
            stmt.setBlob(6, p.getFotoBiometria2());
            
            stmt.setBlob(7, p.getFotoBiometria3());
            
            stmt.setBlob(8, p.getFotoBiometria4());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
            }
         } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        
        //codigo do create
        

    }

    public List<Pessoa> read() {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Pessoa> pessoas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM ApsBiometria");
            rs = stmt.executeQuery();

            while (rs.next()) {
                File imagem1;
                File imagem2;
                File imagem3;
                File imagem4;
                
                Pessoa pessoa = new Pessoa(rs.getString("nm_pessoa"), rs.getString("nm_usuario"), rs.getString("cd_senha"), rs.getInt("ic_restricao"), rs.getBinaryStream("foto1"), rs.getBinaryStream("foto2"), rs.getBinaryStream("foto3"), rs.getBinaryStream("foto4"));
                pessoas.add(pessoa);
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return pessoas;

    }
    public Pessoa busca(String nomeCompleto) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Pessoa pessoa = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM ApsBiometria WHERE nm_pessoa LIKE ?");
            stmt.setString(1, "%"+nomeCompleto+"%");
            
            rs = stmt.executeQuery();
                File imagem1;
                File imagem2;
                File imagem3;
                File imagem4;

            while (rs.next()) {
                pessoa = new Pessoa(rs.getString("nm_pessoa"), rs.getString("nm_usuario"), rs.getString("cd_senha"), rs.getInt("ic_restricao"), rs.getBinaryStream("foto1"), rs.getBinaryStream("foto2"), rs.getBinaryStream("foto3"), rs.getBinaryStream("foto4"));
                pessoa.setIdentificador(rs.getInt("cd_cadastro"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return pessoa;

    }

    public void update(Pessoa p) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            
            stmt = con.prepareStatement("UPDATE ApsBiometria SET nm_pessoa = ?, nm_usuario= ?, cd_senha = ?, ic_restricao = ?, foto1 = ?, foto2 = ?, foto3 = ?, foto4 = ? WHERE nm_usuario = ?");
           // stmt = con.prepareStatement("UPDATE ApsBiometria SET nm_pessoa = ?, nm_usuario= ?, cd_senha = ?, ic_restricao = ? WHERE nm_usuario = ?");
            stmt.setString(1,p.getNomeCompleto());
            stmt.setString(2, p.getLogin());
            stmt.setString(3, p.getSenha());
            stmt.setInt(4,p.getIc_restricao());
            stmt.setBlob(5, p.getFotoBiometria1());
            stmt.setBlob(6, p.getFotoBiometria2());
            stmt.setBlob(7,p.getFotoBiometria3());
            stmt.setBlob(8, p.getFotoBiometria4());
            stmt.setString(9, p.getLogin());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    public void delete(String u) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM ApsBiometria WHERE nm_usuario = ?");
            stmt.setString(1, u);

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "O registro foi excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

        
    }
    public boolean autenticacaoImagem(String login, BufferedImage image) throws IOException, Exception {
        
      boolean validacao = false;
      Connection con = ConnectionFactory.getConnection();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      Pessoa p = null;
	
        try {

            stmt = con.prepareStatement("SELECT * FROM ApsBiometria WHERE nm_usuario = ?");
            stmt.setString(1, login);
            rs = stmt.executeQuery();
            if (rs.next()) {
               p = new Pessoa(rs.getString("nm_pessoa"), rs.getString("nm_usuario"), rs.getString("cd_senha"), rs.getInt("ic_restricao"), rs.getBinaryStream("foto1"), rs.getBinaryStream("foto2"), rs.getBinaryStream("foto3"), rs.getBinaryStream("foto4"));
            }
        Image imagemBanco1 = ImageIO.read(p.getFotoBiometria1());
        Image imagemBanco2 = ImageIO.read(p.getFotoBiometria2());
        Image imagemBanco3 = ImageIO.read(p.getFotoBiometria3());
        Image imagemBanco4 = ImageIO.read(p.getFotoBiometria4());
       BufferedImage buffered1 = toBufferedImage(imagemBanco1);
       BufferedImage buffered2 = toBufferedImage(imagemBanco2);
       BufferedImage buffered3 = toBufferedImage(imagemBanco3);
       BufferedImage buffered4 = toBufferedImage(imagemBanco4);
 
        
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ImageIO.write( image, "jpg", baos );
    baos.flush();
    byte[] autenticada = baos.toByteArray();
    baos.close();
    
    baos = new ByteArrayOutputStream();
    ImageIO.write( buffered1, "jpg", baos );
    baos.flush();
    byte[] banco1 = baos.toByteArray();
    baos.close();

    baos = new ByteArrayOutputStream();
    ImageIO.write( buffered2, "jpg", baos );
    baos.flush();
    byte[] banco2 = baos.toByteArray();
    baos.close();
    
    baos = new ByteArrayOutputStream();
    ImageIO.write( buffered3, "jpg", baos );
    baos.flush();
    byte[] banco3 = baos.toByteArray();
    baos.close();
    
    baos = new ByteArrayOutputStream();
    ImageIO.write( buffered4, "jpg", baos );
    baos.flush();
    byte[] banco4 = baos.toByteArray();
    baos.close();
        
        if(AutenticacaoBiometrica.autenticar(banco1, autenticada) || AutenticacaoBiometrica.autenticar(banco2, autenticada) || AutenticacaoBiometrica.autenticar(banco3, autenticada) || AutenticacaoBiometrica.autenticar(banco4, autenticada)){
    
           validacao = true;
}else{
            JOptionPane.showMessageDialog(null, "Autenticação biométrica inválida! Tente novamente.");
        }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        
         return validacao;
}
    
    
    BufferedImage createResizedCopy(Image originalImage, int scaledWidth, int scaledHeight, boolean preserveAlpha) {
    int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
    BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
    Graphics2D g = scaledBI.createGraphics();
    if (preserveAlpha) {
        g.setComposite(AlphaComposite.Src);
    }
    g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
    g.dispose();
    return scaledBI;
}
    private int[] getPixels(BufferedImage image) {
	final int width = image.getWidth();
	final int height = image.getHeight();
	int size = width * height;
	PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, new int[size], 0, width);
	pixelGrabber.startGrabbing();
	return (int[]) pixelGrabber.getPixels();
}
    public static BufferedImage toBufferedImage(Image img)
{
    if (img instanceof BufferedImage)
    {
        return (BufferedImage) img;
    }

    // Create a buffered image with transparency
    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

    // Draw the image on to the buffered image
    Graphics2D bGr = bimage.createGraphics();
    bGr.drawImage(img, 0, 0, null);
    bGr.dispose();

    // Return the buffered image
    return bimage;
}
}
