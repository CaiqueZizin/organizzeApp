/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLDataException;
import java.sql.SQLException;
import model.Contas;
import controler.DAO;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;



/**
 *
 * @author Caique
 */
public class CRUD_Contas extends DAO{

    java.util.Date date;
    java.sql.Date sqldate;
    
    //Método para inserir uma nova conta
    public void inserir(Contas contas) {
        try {
            //Aqui vamos pegar uma conexão com nosso banco
            Connection conn = conectar();
            //Mandar guardar esse comando para ser executado
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO contas (Nome, Preco, Parcela , Repeticao, Data) "
                + "VALUES ( ?, ?, ?, ?, ?); ");
            //Preencher os  ?  ?  ? com os dados guardados na classe de entidade Contas
            ps.setString(1, contas.getNome());
            ps.setDouble(2, contas.getPreco());
            
            //Aqui veremos se ele informou parcelas para esta conta
            //Caso não tenha informado vamos setar Parcelas == Null ou seja
            //Uma  Conta "Infinita" , Exemplo: Agua,Luz,etc...
            int parc;
            if((parc = contas.getParcelas()) == 0){
               ps.setString(3, null); 
            }
            else{
                ps.setInt(3, contas.getParcelas());
            }
            ps.setString(4, contas.getRepeticao());
            //Vamos Setar a data escolhida
            date = contas.getData();
            sqldate = new java.sql.Date(date.getTime());
            ps.setDate(5, sqldate);
            //Agora mandamos executar o comando e logo depois finalizar a execução
            ps.execute();
            ps.close();
            //Caso ocorra tudo OK mandamos uma mensagem de Sucesso
            JOptionPane.showMessageDialog(null,
                    "Dados Inseridos com Sucesso"
                    ,"Contas"
                    ,JOptionPane.INFORMATION_MESSAGE);
        
        }catch (SQLException se) {
            se.printStackTrace();
        }
    }
   
    //Método para alterar uma conta 
    public void alterar(Contas contas) {
        String sql = "UPDATE contas SET Nome = ?, Preco = ?, Parcela = ?, Repeticao = ?, Data = ? "
                + "WHERE id = ?";
        
        try {
            Connection conn = conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, contas.getNome());
            ps.setDouble(2, contas.getPreco());
            //Aqui veremos se ele informou parcelas para esta conta
            //Caso não tenha informado vamos setar Parcelas == Null ou seja
            //Uma  Conta "Infinita" , Exemplo: Agua,Luz,etc
            int parc;
            if((parc = contas.getParcelas()) == 0){
               ps.setString(3, null); 
            }
            else{
                ps.setInt(3, contas.getParcelas());
            }
            //Agora vamos Setar a data escolhida
            date = contas.getData();
            sqldate = new java.sql.Date(date.getTime());
            ps.setString(4, contas.getRepeticao());
            ps.setDate(5, sqldate);
            ps.setInt(6, contas.getId());
            ps.execute();
            ps.close();
    
            JOptionPane.showMessageDialog(null,
                    "Dados ALTERADOS com Sucesso"
                    ,"Contas"
                    ,JOptionPane.WARNING_MESSAGE);
             
        }catch (SQLException se) {
            se.printStackTrace();
        }
    }
    
    //Método para excluir contas
    public void excluir(Contas contas , String msg) {
        String sql = "DELETE FROM contas "
                + "WHERE id = ?";
        
        try {
            Connection conn = conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, contas.getId());
            ps.execute();
            ps.close();
            //Aqui vamos avisa-lo que a conta foi deletada 
            //ou que suas parcela foram terminadas
            JOptionPane.showMessageDialog(null,
                    "Olá : ) \n"
                    + " " + msg
                    ,"Contas"
                    ,JOptionPane.WARNING_MESSAGE); 
        }catch (SQLException se) {
            se.printStackTrace();
        }
    }
 

    public Contas buscarPorChave(String id) {
        //Método para fazer o loop e pegar todos as contas do banco de dados
        String sql = "SELECT id, Nome , Preco , Parcela , Repeticao, Data "
                + "FROM contas "
                + "WHERE id = " + id ;
        Statement st;
        try{
            Connection conn = conectar();
            st = conn.createStatement();
            //Fui no banco 
            ResultSet rs = st.executeQuery(sql);
            
            Contas contas;            
            //Enquanto tiver uma proxima linha no resultado eu pego suas informações
            if(rs.next()){
                contas = new Contas();
                contas.setId(rs.getInt("id"));
                contas.setNome(rs.getString("Nome"));
                contas.setPreco(rs.getDouble("Preco"));
                contas.setParcelas(rs.getInt("Parcela"));
                contas.setRepeticao(rs.getString("Repeticao"));
                contas.setData(rs.getDate("Data"));
                return contas;
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return null;
    }

    //Método para buscar todas as contas    
    public ArrayList<Contas> buscarTodasContas(String nome) {
        
        String sql = "SELECT id, Nome , Preco , Parcela , Repeticao, Data "
                + "FROM contas "
                + "WHERE Nome LIKE '" + nome + "%' "
                + "ORDER BY Nome;";
        Statement st;
        try{
           
            Connection conn = conectar();
            st = conn.createStatement();
            //Fui no banco 
            ResultSet rs = st.executeQuery(sql);
            
            Contas conta;
            ArrayList<Contas> contas = new ArrayList<Contas>();
            
            //Enquanto tiver uma proxima linha no resultado contas eu pego suas informações
            while(rs.next()){
                conta = new Contas();
                conta.setId(rs.getInt("id"));
                conta.setNome(rs.getString("Nome"));
                conta.setPreco(rs.getDouble("Preco"));
                conta.setParcelas(rs.getInt("Parcela"));
                conta.setRepeticao(rs.getString("Repeticao"));
                conta.setData(rs.getDate("Data"));
                contas.add(conta);
            }
            return contas;
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return null;
    }
    
    //Método para buscar as contas a serem pagas no dia atual
    public ArrayList<Contas> buscarContasHoje(String nome) {
        //Pegando a data de hoje do PC
        java.util.Date data = new java.util.Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        
        //Método para fazer o loop e pegar todas as contas a pagar hoje
        String sql = "SELECT id, Nome , Preco , Parcela , Repeticao, Data "
                + "FROM contas "
                + "WHERE Data = '" + formato.format(data) + "' "
                + "ORDER BY Data;";
        Statement st;
        try{
           
            Connection conn = conectar();
            st = conn.createStatement();
             
            ResultSet rs = st.executeQuery(sql);
            
            Contas conta;
            ArrayList<Contas> contas = new ArrayList<Contas>();
            
            //Enquanto tiver uma proxima linha no resultado eu pego suas informações
            while(rs.next()){
                conta = new Contas();
                conta.setId(rs.getInt("id"));
                conta.setNome(rs.getString("Nome"));
                conta.setPreco(rs.getDouble("Preco"));
                conta.setParcelas(rs.getInt("Parcela"));
                conta.setRepeticao(rs.getString("Repeticao"));
                conta.setData(rs.getDate("Data"));
                contas.add(conta);
            }
            return contas;
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return null;
    }
    //Método  para buscar as contas atrasadas no banco
    public ArrayList<Contas> buscarContasAtrasadas(String nome) {
        //Pegando a data de hoje do PC
        java.util.Date data = new java.util.Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");        
        //Comando e pega as datas que são menores que o dia de hoje 
        String sql = "SELECT id, Nome , Preco , Parcela , Repeticao, Data "
                + "FROM contas "
                + "WHERE Data < '" + formato.format(data) + "' "
                + "ORDER BY Data;";
        Statement st;
        try{
           
            Connection conn = conectar();
            st = conn.createStatement();
            //Fui no banco 
            ResultSet rs = st.executeQuery(sql);
            
            Contas conta;
            ArrayList<Contas> contas = new ArrayList<Contas>();
            
            //Enquanto tiver uma proxima linha no resultado eu pego suas informações
            while(rs.next()){
                conta = new Contas();
                conta.setId(rs.getInt("id"));
                conta.setNome(rs.getString("Nome"));
                conta.setPreco(rs.getDouble("Preco"));
                conta.setParcelas(rs.getInt("Parcela"));
                conta.setRepeticao(rs.getString("Repeticao"));
                conta.setData(rs.getDate("Data"));
                contas.add(conta);
            }
            return contas;
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return null;
    }
    
    //Método para buscar as contas a serem pagas amanhâ no banco
    public ArrayList<Contas> buscarContasAmanha(String nome) {
        //Pegando a data de hoje do PC
        java.util.Date data = new java.util.Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");        
        //Comando que irá mostrar quais contas são maiores em 1 dia comparadas a data de hoje
        String sql = "SELECT id, Nome , Preco , Parcela , Repeticao, Data "
                + "FROM contas "
                + "WHERE Data = (ADDDATE('" + formato.format(data) + "' , INTERVAL 1 DAY)) "
                + "ORDER BY Data;";
        Statement st;
        try{
           
            Connection conn = conectar();
            st = conn.createStatement();
            //Fui no banco 
            ResultSet rs = st.executeQuery(sql);
            
            Contas conta;
            ArrayList<Contas> contas = new ArrayList<Contas>();
            
            //Enquanto tiver uma proxima linha no resultado eu pego suas informações
            while(rs.next()){
                conta = new Contas();
                conta.setId(rs.getInt("id"));
                conta.setNome(rs.getString("Nome"));
                conta.setPreco(rs.getDouble("Preco"));
                conta.setParcelas(rs.getInt("Parcela"));
                conta.setRepeticao(rs.getString("Repeticao"));
                conta.setData(rs.getDate("Data"));
                contas.add(conta);
            }
            return contas;
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return null;
    }
    
    //Método para diminuir uma parcela de um conta, quando a mesma tiver e for paga
    public void diminuirParcela(Contas contas){
        String sql = "UPDATE contas SET Parcela = Parcela - 1 WHERE id = ?";
        try{
            Connection conn = conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, contas.getId());
            ps.execute();
            ps.close();
                    
        }catch(Exception sqle){
            sqle.printStackTrace();
        }
    }
    
    
    //Método para efetuar o pagamento de uma conta diária ou semanal
    public void pagarDia(Contas contas , int dias){
        //Irei os dados necessários , da conta seu ID e os dias a serem acrescentados
        //1 dia para diária 7 pra semanal e 15 para quinzenal
        
        //Comando que adiciona X dias a Data da conta com o ID Y 
        String sql = "UPDATE contas SET Data = ADDDATE(Data, INTERVAL ? DAY)"
                + "WHERE id  = ?";
        try {
            Connection conn = conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, dias);
            ps.setInt(2, contas.getId());
            
            ps.execute();
            ps.close();
    
            JOptionPane.showMessageDialog(null,
                    "Conta Paga Com Sucesso !"
                    ,"Contas"
                    ,JOptionPane.WARNING_MESSAGE);
             
        }catch (SQLException se) {
            se.printStackTrace();
        }     
    }
    
    //Método para pagar uma conta mensal,bimestral,etc...
    public void pagarMes(Contas contas , int meses){
        //Basicamente comandos parecidos com o pagarDia 
        String sql = "UPDATE contas SET Data = ADDDATE(Data, INTERVAL ? MONTH)"
                + "WHERE id  = ?";
        try {
            Connection conn = conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, meses);
            ps.setInt(2, contas.getId());
            
            ps.execute();
            ps.close();
    
            JOptionPane.showMessageDialog(null,
                    "Conta Paga Com Sucesso !"
                    ,"Contas"
                    ,JOptionPane.WARNING_MESSAGE);
             
        }catch (SQLException se) {
            se.printStackTrace();
        }     
    }
    
    //Método para pagar uma conta anual
    public void pagarAno(Contas contas){
        //Basicamente comandos parecidos com o pagarDia 
        String sql = "UPDATE contas SET Data = ADDDATE(Data, INTERVAL 1 YEAR)"
                + "WHERE id  = ?";
        try {
            JOptionPane.showMessageDialog(null,"Vou Pagar  o Anualmente");
            
            Connection conn = conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, contas.getId());
            
            ps.execute();
            ps.close();
    
            JOptionPane.showMessageDialog(null,
                    "Conta Paga Com Sucesso !"
                    ,"Contas"
                    ,JOptionPane.WARNING_MESSAGE);
             
        }catch (SQLException se) {
            se.printStackTrace();
        }     
    }
    
    //Métodos para retornar Numero de contas ao lado de cada botão como notificção
    public int notifiTodasContas(){
        //Comando que irá contar quantas conta há armazenadas do usuário
        String sql = "SELECT COUNT(id) FROM contas ";
        
        try{
            Connection conn = conectar();
            PreparedStatement ps = conn.prepareStatement(sql);           
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            int res = 0;           
            while(rs.next()){
                res = rs.getInt("COUNT(id)");
            }
            return res;

        }catch(Exception se){   
           se.printStackTrace(); 
        }
        return 0;      
    }
    public int notifiTodasContasHoje(){
        //Pegando a data de hoje do PC
        java.util.Date data = new java.util.Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        
        //Comando que irá contar quantas contas cadastradas estão com a Data igual a de hoje
        String sql = "SELECT COUNT(id) FROM contas WHERE Data = '"
                + formato.format(data) + "' ";
        
        try{
            Connection conn = conectar();
            PreparedStatement ps = conn.prepareStatement(sql);           
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            int res = 0;           
            while(rs.next()){
                res = rs.getInt("COUNT(id)");
            }
            return res;

        }catch(Exception se){   
           se.printStackTrace(); 
        }
        return 0;      
    }
    
    public int notifiTodasContasAtrasadas(){
        //Pegando a data de hoje do PC
        java.util.Date data = new java.util.Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        
        //Comando para contar quantas contas cadastradas existem com a Data menor que o dia de hoje
        String sql = "SELECT COUNT(id) FROM contas WHERE Data < '"
                + formato.format(data) + "' ";
        
        try{
            Connection conn = conectar();
            PreparedStatement ps = conn.prepareStatement(sql);           
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            int res = 0;           
            while(rs.next()){
                res = rs.getInt("COUNT(id)");
            }
            return res;

        }catch(Exception se){   
           se.printStackTrace(); 
        }
        return 0;      
    }
    //Método para notificar todas as contas a pagar amanhã
    public int notifiTodasContasAmanha(){
        //Pegando a data de hoje do PC
        java.util.Date data = new java.util.Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        
        //Comando para contar quantas contas cadastradas estão com a Data maior que o data de hoje em 1 dia 
        String sql = "SELECT COUNT(id) FROM contas WHERE Data = (ADDDATE('" + formato.format(data) + "' , INTERVAL 1 DAY)) ";
        
        try{
            Connection conn = conectar();
            PreparedStatement ps = conn.prepareStatement(sql);           
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            int res = 0;           
            while(rs.next()){
                res = rs.getInt("COUNT(id)");
            }
            return res;

        }catch(Exception se){   
           se.printStackTrace(); 
        }
        return 0;      
    }
    
    //Método para atualizar o  saldo do usuário quando uma conta for paga
    public void attSaldo(Contas contas){
        //Comando que irá subtrair do saldo o valor da conta paga
        String sql = "UPDATE saldo SET saldoAtual = (saldoAtual - ?)";    
        try {
            Connection conn = conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setDouble(1, contas.getPreco());

            ps.execute();
            ps.close();
             
        }catch (SQLException se) {
            se.printStackTrace();
        }
    }
    
    //Método para atualizar o relatório , setando nele a o tipo,preçoOUValor e data
    public void attHistoricoConta(Contas contas) throws ParseException{
        //Comando que irá inserir no relatório que um CONTA , com o PREÇO X foi paga na DATA Y 
        String sql = "INSERT INTO historico (Tipo , PrecoOUValor , Data) Values (? , ? , ?)";
        //Fazemos isso para futuramente podermos visualizar nossas receitas e despesar de um mês
        //Na tela relatório
        try {
            Connection conn = conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1,"Conta");
            ps.setDouble(2,contas.getPreco());
            //
            //Aqui vamos pegar a data da nosso PC
            //formata-la pegando apenas o ano e o mês
            //e inserindo no banco , porém o banco irá colocar como padrão o dia 01
            //entretanto irá respeitar o mês e o ano
            //então quando formos buscar o relatório na telaRelatorio vamos fazer o mesmo
            //la estará inserido 2019-12-01
            //vamos mandar buscar somente o ano e mês informados e ele irá buscar pelo dia 01 tambem
            
            //Agora vamos pegar a data de hoje do PC por que mesmo que seja um pagamento de junho 2099
            //Ele foi recebido em junho 2019 e isso deve constar no relatório e lançamento
            java.util.Date data = new java.util.Date();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM");
            String dat = formato.format(data);
            date = formato.parse(dat);
            sqldate = new java.sql.Date(date.getTime());
            
            ps.setDate(3, sqldate);
            ps.execute();
            ps.close();
             
        }catch (SQLException se) {
            se.printStackTrace();
        }
    }
    
    
    
    
    
    
    
    
    
    //
}
        

   

