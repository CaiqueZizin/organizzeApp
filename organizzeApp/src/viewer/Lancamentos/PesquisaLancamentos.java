/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer.Lancamentos;

import viewer.Contas.*;
import controler.CRUD_Contas;
import controler.DAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Contas;
import model.Pagamentos;
import personalizado.meuDefaultTableModel;
import static viewer.Contas.TelaContas.itensParcelas;

/**
 *
 * @author Caique
 */

public class PesquisaLancamentos extends javax.swing.JFrame {
    //Sera usada para instanciar o Default Table Model, para que assim possamos usar a table de pesquisa
    String [] colunas = {"Tipo" , "Preço ou Valor" , "Data : Mês/Ano" };
    //Criamos a variável dados
    meuDefaultTableModel dados;
    /**
     * Creates new form frmPesquisar
     */
    public PesquisaLancamentos() throws ParseException {
        initComponents();
        buscarLancamentos();     
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela de Pesquisa");
        setAlwaysOnTop(true);
        setResizable(false);
        getContentPane().setLayout(null);

        //Aqui faremos o conteudo da nossa tabela
        dados = new meuDefaultTableModel(colunas , 0);
        tabela.setModel(dados);
        tabela.setModel(dados);
        //Aqui vamos dar ao cabeçalho uma reordenação como falsa
        //Ou seja o ususario n pode alterar a posição das colunas com o mouse
        tabela.getTableHeader().setReorderingAllowed(false);
        //setamos a coluna ID com 50 pixels, ou seja ira ficar pequena
        tabela.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(1);
        //Agora iremos fazer a coluna Nome ficar maior, com 250 pixels
        tabela.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(55);
        tabela.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(50);
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabelaMouseReleased(evt);
            }
        });
        tabela.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabelaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 60, 560, 280);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Lançamentos");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(220, 30, 180, 20);

        getAccessibleContext().setAccessibleName("Tela de Pesquisa Lançamentos");

        setBounds(0, 0, 794, 544);
    }// </editor-fold>//GEN-END:initComponents

    private void tabelaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseReleased
        
    }//GEN-LAST:event_tabelaMouseReleased

    private void tabelaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaKeyReleased
        
    }//GEN-LAST:event_tabelaKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PesquisaLancamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PesquisaLancamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PesquisaLancamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PesquisaLancamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new PesquisaLancamentos().setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(PesquisaLancamentos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
    
    //Método para buscar e mostrar toda a atividade feita, pagamento de contas e recebimento de pagamentos
    public void buscarLancamentos() throws ParseException{
        //Aqui vamos percorrer todo o banco, pegando os dados e jogando nas linhas para jogar na tabela
        String linha [] = {"", "", ""};
        
        String sql = "SELECT Tipo, PrecoOUValor , Data FROM historico ; ";
        Statement st;
        try{
            DAO dao = new DAO();
            Connection conn = dao.conectar();
            st = conn.createStatement();
            //Fui no banco 
            ResultSet rs = st.executeQuery(sql);
            
            //Enquanto tiver uma proxima linha no resultado eu pego suas informações
            //guardo no vetor de linha , e ao terminar de pegar vou jogando a linha para a tabela
            while(rs.next()){
                String data[];
                linha[0] = rs.getString("Tipo");
                linha[1] = "R$: " + rs.getString("PrecoOUValor");
                //Pegamos a data bruta , exemplo 2019-12-31 , e jogamos em linha[2]
                linha[2] = rs.getString("Data");
                //Agora explodimos a data bruta e jogamos no nosso vetor de data logo ficará 3 cacos 0° Ano ... 1° Mês e 2° Dia
                data = linha[2].split("-");
                //Agora jogamos de volta pra linha[2] Mês/Ano apenas  :)
                linha[2] = data[1] + "/" + data[0];
                //Mandamos escrever a linha na tabela com as informações contidas no vetor linha[]
                dados.addRow(linha);
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }

        
    }
    

}
