/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer.Contas;

import controler.CRUD_Contas;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Contas;
import personalizado.meuDefaultTableModel;
import static viewer.Contas.TelaContas.itensParcelas;

/**
 *
 * @author Caique
 */

public class PesquisaContas extends javax.swing.JFrame {
    //Sera usada para instanciar o Default Table Model, para que assim possamos usar a table de pesquisa
    String [] colunas = {"id" , "Nome" , "Preço" , "Parcelas" , "Repetição" , "Data" };
    //Criamos a variável dados
    meuDefaultTableModel dados;
    /**
     * Creates new form frmPesquisar
     */
    public PesquisaContas() {
        initComponents();
        buscarContas();     
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
        jLabel1.setBounds(220, 10, 100, 20);

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
        tbpesquisa.setBounds(110, 30, 320, 30);

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
        tabela.getTableHeader().getColumnModel().getColumn(3).setPreferredWidth(70);
        tabela.getTableHeader().getColumnModel().getColumn(4).setPreferredWidth(70);
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
        jScrollPane1.setBounds(10, 110, 560, 250);

        jLabel2.setText("TODAS AS CONTAS :");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(220, 70, 180, 20);

        setBounds(0, 0, 794, 544);
    }// </editor-fold>//GEN-END:initComponents

    private void tbpesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbpesquisaKeyReleased
        buscarContas();
    }//GEN-LAST:event_tbpesquisaKeyReleased

    private void tabelaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseReleased
        preencheContas();
    }//GEN-LAST:event_tabelaMouseReleased

    private void tabelaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaKeyReleased
        // TODO add your handling code here:
        //Método para ao clicar em alguma conta , ele carrege suas informações na tela de contas
        preencheContas();
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
            java.util.logging.Logger.getLogger(PesquisaContas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PesquisaContas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PesquisaContas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PesquisaContas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PesquisaContas().setVisible(true);
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
    
    public void buscarContas(){
        //Classe para buscar as contas
        CRUD_Contas crud  = new CRUD_Contas();
        ArrayList<Contas> contas = crud.buscarTodasContas(tbpesquisa.getText());
        //Aqui vamos percorrer todo o banco, pegando as contas jogando nas linhas para jogar na tabela
        String linha [] = {"", "", "", "", "", ""};
        
        limparTabela();
        
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        
        for(Contas c : contas){
            linha[0] = c.getId() + "";
            linha[1] = c.getNome();
            linha[2] = c.getPreco() + "";
            linha[3] = c.getParcelas() + "";
            linha[4] = c.getRepeticao()+ "";
            linha[5] = formato.format(c.getData()) + "";
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
    
    private void preencheContas() {
        //Método para preencher na tabela os dados da conta quando a mesma for selecionada 
        CRUD_Contas crud = new CRUD_Contas();
        //
        String id = dados.getValueAt(tabela.getSelectedRow() , 0).toString();
        //Aqui disse que 
        Contas contas = crud.buscarPorChave(id);
        //
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        TelaContas.tbId.setText(contas.getId()+ "");
        TelaContas.tbNome.setText(contas.getNome());
        TelaContas.tbPreco.setText(contas.getPreco()  + "");
        TelaContas.itensParcelas.setSelectedItem(contas.getParcelas() + "");
        TelaContas.itensRepeticao.setSelectedItem(contas.getRepeticao() + "");
        TelaContas.calendario.setDate(LocalDate.parse(contas.getData() + ""));
        //Como o  usuário selecionou uma conta vamos setar para ser possivel paga-la
        //desabilitar  os campos de edição e habilitar o botão Editar para ser possivel editar
        TelaContas.tbNome.setEnabled(false);
        TelaContas.tbPreco.setEnabled(false);
        TelaContas.itensParcelas.setEnabled(false);
        TelaContas.itensRepeticao.setEnabled(false);
        TelaContas.calendario.setEnabled(false);
        //Os seguintes botões podem ser clicados
        TelaContas.btPagar.setEnabled(true);
        TelaContas.btEditar.setEnabled(true);
        TelaContas.btDeletar.setEnabled(true);
        //
        TelaContas.btSalvar.setEnabled(false);
        TelaContas.radioFixo.setEnabled(false);
        TelaContas.radioParcelado.setEnabled(false);
        //Ja carregando na tela o radio selecionado
        int opparc = TelaContas.itensParcelas.getSelectedIndex();
        if(opparc == 0){
            TelaContas.radioFixo.setSelected(true);
        }else{
            TelaContas.radioParcelado.setSelected(true);
        }
    }

}
