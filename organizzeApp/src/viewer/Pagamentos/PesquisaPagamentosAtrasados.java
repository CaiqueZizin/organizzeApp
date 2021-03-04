/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer.Pagamentos;

import viewer.Pagamentos.*;
import controler.CRUD_Pagamentos;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Pagamentos;
import personalizado.meuDefaultTableModel;
import static viewer.Pagamentos.TelaPagamentos.itensParcelas;


/**
 *
 * @author Caique
 */


public class PesquisaPagamentosAtrasados extends javax.swing.JFrame {
    //Sera usada para instanciar o Default Table Model, para que assim possamos usar a table de pesquisa
    String [] colunas = {"id" , "Nome" , "Categoria" , "Valor" , "Parcelas" , "Repetição" , "Data" };
    //Criamos a variável dados
    meuDefaultTableModel dados; 
    /**
     * Creates new form Pagamento
     */
    public PesquisaPagamentosAtrasados() {
        initComponents();
        buscarPagamentosAtrasados();     
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tbpesquisa = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela de Pesquisa");
        setAlwaysOnTop(true);
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setText("PESQUISAR");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(250, 10, 100, 20);

        tbpesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbpesquisaActionPerformed(evt);
            }
        });
        tbpesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbpesquisaKeyReleased(evt);
            }
        });
        getContentPane().add(tbpesquisa);
        tbpesquisa.setBounds(140, 40, 320, 30);

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
        tabela.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(80);
        tabela.getTableHeader().getColumnModel().getColumn(3).setPreferredWidth(60);
        tabela.getTableHeader().getColumnModel().getColumn(4).setPreferredWidth(60);
        tabela.getTableHeader().getColumnModel().getColumn(5).setPreferredWidth(70);
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
        jScrollPane1.setBounds(50, 110, 560, 250);

        jLabel2.setText("PAGAMENTOS  ATRASADOS : ");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(180, 74, 240, 30);

        setBounds(0, 0, 794, 544);
    }// </editor-fold>//GEN-END:initComponents

    private void tbpesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbpesquisaKeyReleased
        // TODO add your handling code here:
        buscarPagamentosAtrasados();
    }//GEN-LAST:event_tbpesquisaKeyReleased

    private void tabelaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseReleased
        // TODO add your handling code here:
        //Método para ao clicar em algum pagamento , ele carrege suas informações na tela de pagamentos
        preenchePagamentos();
    }//GEN-LAST:event_tabelaMouseReleased

    private void tabelaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaKeyReleased
        // TODO add your handling code here:
        preenchePagamentos();
    }//GEN-LAST:event_tabelaKeyReleased

    private void tbpesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbpesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbpesquisaActionPerformed

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
            java.util.logging.Logger.getLogger(PesquisaPagamentosAtrasados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PesquisaPagamentosAtrasados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PesquisaPagamentosAtrasados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PesquisaPagamentosAtrasados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PesquisaPagamentosAtrasados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela;
    private javax.swing.JTextField tbpesquisa;
    // End of variables declaration//GEN-END:variables
    
        public void buscarPagamentosAtrasados(){
        //Classe para buscar os pagamentos
        CRUD_Pagamentos crud  = new CRUD_Pagamentos();
        ArrayList<Pagamentos> pags = crud.buscarPagamentosAtrasados(tbpesquisa.getText());
        //Aqui vamos percorrer todo o banco, pegando os pagamentos jogando nas linhas para jogar na tabela
        String linha [] = {"", "", "", "", "", "", ""};
        
        limparTabela();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        for(Pagamentos p : pags){
            linha[0] = p.getId() + "";
            linha[1] = p.getNome();
            linha[2] = p.getCategoria();
            linha[3] = p.getValor()+ "";
            linha[4] = p.getParcelas() + "";
            linha[5] = p.getRepeticao() + "";
            linha[6] = formato.format(p.getData()) + "";
            dados.addRow(linha);
        }
        
    }
    private void limparTabela(){
        //Método para sempre limpar as linhas antigas da tabela
        //para quando ele pesquisar ele atualize a tabela
        int tamanho = tabela.getRowCount();
        for(int i = 0; i < tamanho; i++){
            dados.removeRow(0);
        }
                
    }

    private void preenchePagamentos() {
        //Método para preencher os dados do pagamento quando um deles for selecionado  
        CRUD_Pagamentos crud = new CRUD_Pagamentos();
        //
        String id = dados.getValueAt(tabela.getSelectedRow() , 0).toString();
        //
        Pagamentos pags = crud.buscarPorChave(id);
        //
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        TelaPagamentos.tbId.setText(pags.getId()+ "");
        TelaPagamentos.tbNome.setText(pags.getNome());
        TelaPagamentos.tbValor.setText(pags.getValor()+ "");
        TelaPagamentos.itensCategoria.setSelectedItem(pags.getCategoria()+ "");
        TelaPagamentos.itensParcelas.setSelectedItem(pags.getParcelas() + "");
        TelaPagamentos.itensRepeticao.setSelectedItem(pags.getRepeticao() + "");
        TelaPagamentos.calendario.setDate(LocalDate.parse(pags.getData() + ""));
        //Como o usuário selecionou um pagamento vamos setar para ser possivel paga-la
        //desabilitar  os campos de edição e habilitar o botão Editar para ser possivel editar quando ele for clicado
        TelaPagamentos.tbNome.setEnabled(false);
        TelaPagamentos.tbValor.setEnabled(false);
        TelaPagamentos.itensCategoria.setEnabled(false);
        TelaPagamentos.itensParcelas.setEnabled(false);
        TelaPagamentos.itensRepeticao.setEnabled(false);
        TelaPagamentos.calendario.setEnabled(false);
        //Os seguintes botões podem ser clicados
        TelaPagamentos.btReceber.setEnabled(true);
        TelaPagamentos.btEditar.setEnabled(true);
        TelaPagamentos.btDeletar.setEnabled(true);
        TelaPagamentos.btSalvar.setEnabled(false);
        //
        TelaPagamentos.radioFixo.setEnabled(false);
        TelaPagamentos.radioParcelado.setEnabled(false);
        //Ja carregando na tela o radio selecionado
        int opparc = TelaPagamentos.itensParcelas.getSelectedIndex();
        if(opparc == 0){
            TelaPagamentos.radioFixo.setSelected(true);
        }else{
            TelaPagamentos.radioParcelado.setSelected(true);
        } 
    }

}
