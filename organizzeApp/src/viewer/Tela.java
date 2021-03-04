/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer;

import java.sql.Connection;
import java.sql.SQLException;
import viewer.Contas.TelaContas;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import viewer.Contas.*;
import viewer.Metas.*;
import viewer.Relatorio.*;
import viewer.Pagamentos.TelaPagamentos;
import controler.DAO;
import controler.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import viewer.Pagamentos.PesquisaPagamentos;
import viewer.Relatorio.TelaRelatorio;
import viewer.Lancamentos.PesquisaLancamentos;
/**
 * @author Caique
 */
/*
Trabalho do Professor Edney
Alunos : Caique , João  Paschoal e Emerson
Ultimo Edit : 11/06/2019
*/
public class Tela extends javax.swing.JFrame {
    //Aqui vamos vincular a tela 2 a uma variável
    TelaContas telaContas;
    TelaPagamentos telaPagamentos;
    TelaMeta telaMeta;
    TelaRelatorio telaRelatorio;
    /**
     * Creates new form frmPrincipal
     */
    public Tela() throws SQLException {
        initComponents();
        // Aqui vamos após buscar o saldo, formata-lo para não vir 190.1200000
        DecimalFormat formatoDinheiro = new DecimalFormat("#.00");
        String sald = formatoDinheiro.format(buscaSaldo());
        double saldo = Double.parseDouble(sald.replace(",","."));
        txtSaldo.setText(saldo + "");      
        btContas.setVisible(false);
        txtDespesa.setVisible(false);
        btPagamentos.setVisible(false);
        txtReceita.setVisible(false);
        //Chamamos o método para as notificações iniciais 
        notifiTotalPagar();
        notifiTotalReceber();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        txtSaldo = new javax.swing.JLabel();
        btAtualizarSaldo = new javax.swing.JButton();
        btOcultar = new javax.swing.JToggleButton();
        btAbrirOps = new javax.swing.JToggleButton();
        btContas = new javax.swing.JButton();
        txtDespesa = new javax.swing.JLabel();
        btPagamentos = new javax.swing.JButton();
        txtReceita = new javax.swing.JLabel();
        txtNotifiPagamentos = new javax.swing.JLabel();
        txtNotifiContas = new javax.swing.JLabel();
        btResolverPagamentos = new javax.swing.JButton();
        btResolverContas = new javax.swing.JButton();
        barra = new javax.swing.JMenuBar();
        mnuControles = new javax.swing.JMenu();
        mnuLancamentos = new javax.swing.JMenuItem();
        mnuMetas = new javax.swing.JMenuItem();
        mnuRelatorio = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Programação Web II");
        setExtendedState(MAXIMIZED_BOTH);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Saldo Atual R$ :");

        txtSaldo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtSaldo.setForeground(new java.awt.Color(255, 255, 255));
        txtSaldo.setText("Saldo");

        btAtualizarSaldo.setText("Atualizar");
        btAtualizarSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizarSaldoActionPerformed(evt);
            }
        });

        btOcultar.setText("OCULTAR");
        btOcultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOcultarActionPerformed(evt);
            }
        });

        btAbrirOps.setBackground(new java.awt.Color(255, 51, 51));
        btAbrirOps.setForeground(new java.awt.Color(255, 255, 255));
        btAbrirOps.setText("+");
        btAbrirOps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAbrirOpsActionPerformed(evt);
            }
        });

        btContas.setBackground(new java.awt.Color(255, 51, 51));
        btContas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btContas.setForeground(new java.awt.Color(255, 255, 255));
        btContas.setText("+");
        btContas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btContasActionPerformed(evt);
            }
        });

        txtDespesa.setForeground(new java.awt.Color(204, 204, 204));
        txtDespesa.setText("Despesa");

        btPagamentos.setBackground(new java.awt.Color(51, 255, 51));
        btPagamentos.setForeground(new java.awt.Color(255, 255, 255));
        btPagamentos.setText("+");
        btPagamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPagamentosActionPerformed(evt);
            }
        });

        txtReceita.setForeground(new java.awt.Color(204, 204, 204));
        txtReceita.setText("Receita");

        txtNotifiPagamentos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNotifiPagamentos.setForeground(new java.awt.Color(255, 255, 255));
        txtNotifiPagamentos.setText("Notificação Total Pagamentos Hoje");

        txtNotifiContas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNotifiContas.setForeground(new java.awt.Color(255, 255, 255));
        txtNotifiContas.setText("Notificação Total Contas Hoje");

        btResolverPagamentos.setText("Resolver");
        btResolverPagamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btResolverPagamentosActionPerformed(evt);
            }
        });

        btResolverContas.setText("Resolver");
        btResolverContas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btResolverContasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(desktopLayout.createSequentialGroup()
                .addGroup(desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(desktopLayout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addGroup(desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(desktopLayout.createSequentialGroup()
                                .addComponent(btAtualizarSaldo)
                                .addGap(18, 18, 18)
                                .addComponent(btOcultar))
                            .addGroup(desktopLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btAbrirOps, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, desktopLayout.createSequentialGroup()
                                        .addComponent(txtDespesa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btContas))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, desktopLayout.createSequentialGroup()
                                .addGap(105, 105, 105)
                                .addComponent(txtReceita, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btPagamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(desktopLayout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addGroup(desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNotifiPagamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNotifiContas, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btResolverPagamentos)
                            .addComponent(btResolverContas))))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(desktopLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAtualizarSaldo)
                    .addComponent(btOcultar))
                .addGap(11, 11, 11)
                .addGroup(desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(txtNotifiContas, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btResolverContas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNotifiPagamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btResolverPagamentos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btPagamentos)
                    .addComponent(txtReceita))
                .addGap(18, 18, 18)
                .addGroup(desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btContas)
                    .addComponent(txtDespesa))
                .addGap(18, 18, 18)
                .addComponent(btAbrirOps)
                .addGap(42, 42, 42))
        );
        desktop.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktop.setLayer(txtSaldo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktop.setLayer(btAtualizarSaldo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktop.setLayer(btOcultar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktop.setLayer(btAbrirOps, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktop.setLayer(btContas, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktop.setLayer(txtDespesa, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktop.setLayer(btPagamentos, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktop.setLayer(txtReceita, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktop.setLayer(txtNotifiPagamentos, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktop.setLayer(txtNotifiContas, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktop.setLayer(btResolverPagamentos, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktop.setLayer(btResolverContas, javax.swing.JLayeredPane.DEFAULT_LAYER);

        getContentPane().add(desktop, java.awt.BorderLayout.CENTER);

        mnuControles.setText("««»»");

        mnuLancamentos.setText("Lançamentos");
        mnuLancamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLancamentosActionPerformed(evt);
            }
        });
        mnuControles.add(mnuLancamentos);

        mnuMetas.setText("Metas");
        mnuMetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuMetasActionPerformed(evt);
            }
        });
        mnuControles.add(mnuMetas);

        mnuRelatorio.setText("Relatório");
        mnuRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRelatorioActionPerformed(evt);
            }
        });
        mnuControles.add(mnuRelatorio);

        barra.add(mnuControles);

        setJMenuBar(barra);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void mnuLancamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLancamentosActionPerformed
        //Aqui vamos chamar a tela de Lançamentos a qual nos tras o historicos de contas e pagamentos
        PesquisaLancamentos tela = null;
        try {
            tela = new PesquisaLancamentos();
        } catch (ParseException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        }
        tela.setVisible(true);
    }//GEN-LAST:event_mnuLancamentosActionPerformed

    private void btAtualizarSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizarSaldoActionPerformed
        //Aqui vamos após buscar o saldo, formata-lo para não vir 190.1200000
        DecimalFormat formatoDinheiro = new DecimalFormat("#.00");
        String sald = formatoDinheiro.format(buscaSaldo());
        double saldo = Double.parseDouble(sald.replace(",","."));
        txtSaldo.setText(saldo + "");
        
        //Aqui vamos chamar o método que verifica as notificações da tela inicial
        try {
            notifiTotalPagar();
        } catch (SQLException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            notifiTotalReceber();
        } catch (SQLException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btAtualizarSaldoActionPerformed

    private void mnuMetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuMetasActionPerformed
        // TODO add your handling code here:
        telaMeta = new TelaMeta();
        desktop.add(telaMeta);
        telaMeta.setVisible(true);
        mnuMetas.setEnabled(false);
    }//GEN-LAST:event_mnuMetasActionPerformed

    private void btOcultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOcultarActionPerformed
        // Botão para ocultar o saldo , parecido com o organizze 
        if(btOcultar.isSelected()){
            txtSaldo.setVisible(false);
        }else{txtSaldo.setVisible(true);}
    }//GEN-LAST:event_btOcultarActionPerformed

    private void btAbrirOpsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAbrirOpsActionPerformed
        // Botão para abrir opções de telas , parecido com o organizze 
        if(btAbrirOps.isSelected()){
            btContas.setVisible(true);
            txtDespesa.setVisible(true);
            btPagamentos.setVisible(true);
            txtReceita.setVisible(true);

            btAbrirOps.setText("X");
            
        }else{
            btContas.setVisible(false);
            txtDespesa.setVisible(false);
            btPagamentos.setVisible(false);
            txtReceita.setVisible(false);
            btAbrirOps.setText("+");
        
        }
    }//GEN-LAST:event_btAbrirOpsActionPerformed

    private void btContasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btContasActionPerformed
        telaContas = new TelaContas();
        desktop.add(telaContas);        
        telaContas.setVisible(true);
        // Agora vamos desabilitar a chamada quando ja estiver aberta a janela
        btContas.setEnabled(false);
        
        btResolverContas.setEnabled(false);
    }//GEN-LAST:event_btContasActionPerformed

    private void btPagamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPagamentosActionPerformed
        telaPagamentos = new TelaPagamentos();
        desktop.add(telaPagamentos);        
        telaPagamentos.setVisible(true);
        // Agora vamos desabilitar a chamada quando ja estiver aberta a janela
        btPagamentos.setEnabled(false);
        mnuLancamentos.setEnabled(false);
        btResolverPagamentos.setEnabled(false);
    }//GEN-LAST:event_btPagamentosActionPerformed

    private void mnuRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRelatorioActionPerformed
        // Para abrir a tela de relatorio
        telaRelatorio = new TelaRelatorio();
        desktop.add(telaRelatorio);        
        telaRelatorio.setVisible(true);
        mnuRelatorio.setEnabled(false);
   
    }//GEN-LAST:event_mnuRelatorioActionPerformed

    private void btResolverContasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btResolverContasActionPerformed
        // Aqui ao clicar no botão resolver vamos abrir a tela de contas para que  o usuario possa pagar as contas 
        telaContas = new TelaContas();
        desktop.add(telaContas);        
        telaContas.setVisible(true);
        // Agora vamos desabilitar a chamada quando ja estiver aberta a janela
        btContas.setEnabled(false);
        
        btResolverContas.setEnabled(false);
    }//GEN-LAST:event_btResolverContasActionPerformed

    private void btResolverPagamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btResolverPagamentosActionPerformed
        // 
        telaPagamentos = new TelaPagamentos();
        desktop.add(telaPagamentos);        
        telaPagamentos.setVisible(true);
        // Agora vamos desabilitar a chamada quando ja estiver aberta a janela
        btPagamentos.setEnabled(false);
        mnuLancamentos.setEnabled(false);
        btResolverPagamentos.setEnabled(false);
    }//GEN-LAST:event_btResolverPagamentosActionPerformed

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
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Tela().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barra;
    private javax.swing.JToggleButton btAbrirOps;
    private javax.swing.JButton btAtualizarSaldo;
    public static javax.swing.JButton btContas;
    private javax.swing.JToggleButton btOcultar;
    public static javax.swing.JButton btPagamentos;
    public static javax.swing.JButton btResolverContas;
    public static javax.swing.JButton btResolverPagamentos;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu mnuControles;
    public static javax.swing.JMenuItem mnuLancamentos;
    public static javax.swing.JMenuItem mnuMetas;
    public static javax.swing.JMenuItem mnuRelatorio;
    private javax.swing.JLabel txtDespesa;
    private javax.swing.JLabel txtNotifiContas;
    private javax.swing.JLabel txtNotifiPagamentos;
    private javax.swing.JLabel txtReceita;
    private javax.swing.JLabel txtSaldo;
    // End of variables declaration//GEN-END:variables

    //Método para buscar o saldo atual no banco
    public double buscaSaldo(){
        String sql = "SELECT saldoAtual FROM saldo";
        
        try{
            DAO dao = new DAO();
            Connection conn = dao.conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            Statement st = conn.createStatement();;
            ResultSet rs = st.executeQuery(sql);
            double res = 0;           
            while(rs.next()){
                res = rs.getDouble("saldoAtual");
            }
            return res;
            
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return 0;     
    }

    //Método para ir ao banco e nos retornar quantas contas e quanto $ temos a pagar hoje
    //(Parecido com Orgazanizze)
    public void notifiTotalPagar() throws SQLException{
        int qntdade;
        //Vamos colocar em quantidade o total de contas a pagar hoje e as atrasadas
        CRUD_Contas crud = new CRUD_Contas();
        qntdade = crud.notifiTodasContasHoje();
        qntdade = qntdade + crud.notifiTodasContasAtrasadas();
        //Se tivermos no minimo uma conta a pagar hoje vamos buscar a soma total do preço dessas contas
        if(qntdade > 0){
            //Pegando a data de hoje do PC
            java.util.Date data = new java.util.Date();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

            //Comando para nos trazer a soma total do preço das contas a pagar hoje 
            String sql= "SELECT SUM(Preco) FROM contas WHERE Data <= '" + formato.format(data) + "'";
            
            DAO dao = new DAO();
            Connection conn = dao.conectar();

            Statement st = conn.createStatement();;
            ResultSet rs = st.executeQuery(sql);
            //Pegamos o resultado da soma
            double res = 0;           
            while(rs.next()){
                res = rs.getDouble("SUM(Preco)");
            }
            //Escrevemos no txtNotifiContas
            btResolverContas.setVisible(true);
            txtNotifiContas.setVisible(true);
            txtNotifiContas.setText("Você tem " + qntdade + " contas a pagar ! \n"
            +" No Total de R$: " + res);
        
        //Agora caso ele não tenha contas a pagar hoje ou atrasadas vamos deixar o txtNotifiContas e o botão de resolver contas invisiveis
        }else{
            btResolverContas.setVisible(false);
            txtNotifiContas.setVisible(false);
        }
    }
    
    /////////////////////////////////////////////////////////////
    //Método para ir ao banco e nos retornar quantos pagamentos e quanto $ temos receber hoje
    //(Parecido com Orgazanizze)
    public void notifiTotalReceber() throws SQLException{
        int qntdade;
        //Vamos colocar em qntdade o total de pagamentos a receber hoje e os atrasados
        CRUD_Pagamentos crud = new CRUD_Pagamentos();
        qntdade = crud.notifiTodosPagamentosHoje();
        qntdade = qntdade + crud.notifiTodosPagamentosAtrasados();
        //Se tivermos no minimo um pagamento receber hoje vamos buscar a soma total do valor desses pagamentos
        if(qntdade > 0){
            //Pegando a data de hoje do PC
            java.util.Date data = new java.util.Date();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

            //Comando para nos trazer a soma total do valor dos pagamentos a receber hoje 
            String sql= "SELECT SUM(Valor) FROM pagamentos WHERE Data <= '" + formato.format(data) + "'";
            
            DAO dao = new DAO();
            Connection conn = dao.conectar();

            Statement st = conn.createStatement();;
            ResultSet rs = st.executeQuery(sql);
            //Pegamos o resultado da soma
            double res = 0;           
            while(rs.next()){
                res = rs.getDouble("SUM(Valor)");
            }
            //Escrevemos no txtNotifiPagamentos
            btResolverPagamentos.setVisible(true);
            txtNotifiPagamentos.setVisible(true);
            txtNotifiPagamentos.setText("Você tem " + qntdade + " pagamentos a receber ! \n"
            +" No Total de R$: " + res);
        
        //Agora caso ele não tenha pagamentos a receber hoje ou atrasados vamos deixar o txtNotifiPagamentos e o botão de resolver pagamentos invisiveis
        }else{
            btResolverPagamentos.setVisible(false);
            txtNotifiPagamentos.setVisible(false);
        }
    }
    
    
}
