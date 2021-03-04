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
import model.Pagamentos;
import controler.DAO;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import model.Contas;



/**
 *
 * @author Caique
 */
public class CRUD_Pagamentos extends DAO{

    java.util.Date date;
    java.sql.Date sqldate;
    
    public void inserir(Pagamentos pags) {
        try {
            //Aqui vamos pegar uma conexão com nosso banco
            Connection conn = conectar();
            //Mandar guardar esse comando para ser executado
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO pagamentos (Nome, Categoria , Valor, Parcela , Repeticao, Data) "
                + "VALUES ( ?, ?, ?, ?, ?, ?); ");
            //Preencher os  ?  ?  ? com os dados guardados na classe de entidade Pagamentos
            ps.setString(1, pags.getNome());
            ps.setString(2, pags.getCategoria());
            ps.setDouble(3, pags.getValor());
            
            //Aqui veremos se ele informou parcelas para este pagamento
            //Caso não tenha informado vamos setar Parcelas == Null ou seja
            //Um  Pagamento "Infinita" , Exemplo: Agua,Luz,etc
            int parc;
            if((parc = pags.getParcelas()) == 0){
               ps.setString(4, null); 
            }
            else{
                ps.setInt(4, pags.getParcelas());
            }
            ps.setString(5, pags.getRepeticao());
            //Vamos setar a data escolhida
            date = pags.getData();
            sqldate = new java.sql.Date(date.getTime());
            ps.setDate(6, sqldate);
            //Agora mandamos executar o comando e logo depois finalizar a execução
            ps.execute();
            ps.close();
            //Caso ocorra tudo OK mandamos uma mensagem de Sucesso
            JOptionPane.showMessageDialog(null,
                    "Dados Inseridos com Sucesso"
                    ,"Pagamentos"
                    ,JOptionPane.INFORMATION_MESSAGE);
        
        }catch (SQLException se) {
            se.printStackTrace();
        }
    }
   
    //Método para editar um pagamento
    public void alterar(Pagamentos pags) {
        String sql = "UPDATE pagamentos SET Nome = ? ,Categoria = ? , Valor = ?, Parcela = ?, Repeticao = ?, Data = ? "
                + "WHERE id = ?";
        
        try {
            Connection conn = conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, pags.getNome());
            ps.setString(2, pags.getCategoria());
            ps.setDouble(3, pags.getValor());
            //Aqui veremos se ele informou parcelas para este Pagamento
            //Caso não tenha informado vamos setar Parcelas == Null ou seja
            //Uma  Pagamento "Infinita" , Exemplo: Agua,Luz,etc
            int parc;
            if((parc = pags.getParcelas()) == 0){
               ps.setString(4, null); 
            }
            else{
                ps.setInt(4, pags.getParcelas());
            }
            //Agora vamos setar a data escolhida
            date = pags.getData();
            sqldate = new java.sql.Date(date.getTime());
            ps.setString(5, pags.getRepeticao());
            ps.setDate(6, sqldate);
            ps.setInt(7, pags.getId());
            ps.execute();
            ps.close();
    
            JOptionPane.showMessageDialog(null,
                    "Dados ALTERADOS com Sucesso"
                    ,"Pagamentos"
                    ,JOptionPane.WARNING_MESSAGE);
             
        }catch (SQLException se) {
            se.printStackTrace();
        }
    }
    

    public void excluir(Pagamentos pags , String msg) {
        String sql = "DELETE FROM pagamentos "
                + "WHERE id = ?";
        
        try {
            Connection conn = conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, pags.getId());
            ps.execute();
            ps.close();
            //Aqui vamos avisa-lo que o Pagamento foi deletado ou que suas parcelas foram terminadas
            JOptionPane.showMessageDialog(null,
                    "Olá : ) \n"
                    + " " + msg
                    ,"Pagamentos"
                    ,JOptionPane.WARNING_MESSAGE); 
        }catch (SQLException se) {
            se.printStackTrace();
        }
    }
 

    public Pagamentos buscarPorChave(String id) {
        //Método para fazer o loop e pegar todos os Pagamentos do banco de dados
        String sql = "SELECT id, Nome , Categoria , Valor , Parcela , Repeticao, Data "
                + "FROM pagamentos "
                + "WHERE id = " + id ;
        Statement st;
        try{
           
            Connection conn = conectar();
            st = conn.createStatement();
            //Fui no banco 
            ResultSet rs = st.executeQuery(sql);
            
            Pagamentos pags;
             
            
            //Se tiver uma proxima linha no resultado eu pego suas informações
            if(rs.next()){
                pags = new Pagamentos();
                pags.setId(rs.getInt("id"));
                pags.setNome(rs.getString("Nome"));
                pags.setCategoria(rs.getString("Categoria"));
                pags.setValor(rs.getDouble("Valor"));
                pags.setParcelas(rs.getInt("Parcela"));
                pags.setRepeticao(rs.getString("Repeticao"));
                pags.setData(rs.getDate("Data"));
                return pags;
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return null;
    }

    public ArrayList<Pagamentos> buscarTodosPagamentos(String nome) {
        //Método para fazer o loop e pegar todos os pagamentos no banco de dados
        String sql = "SELECT id, Nome , Categoria, Valor , Parcela , Repeticao, Data "
                + "FROM pagamentos "
                + "WHERE Nome LIKE '" + nome + "%' "
                + "ORDER BY Nome;";
        Statement st;
        try{
           
            Connection conn = conectar();
            st = conn.createStatement();
            //Fui no banco 
            ResultSet rs = st.executeQuery(sql);
            
            Pagamentos pag;
            ArrayList<Pagamentos> pags = new ArrayList<Pagamentos>();
            
            //Enquanto tiver uma proxima linha no resultado eu pego suas informações
            while(rs.next()){
                pag = new Pagamentos();
                pag.setId(rs.getInt("id"));
                pag.setNome(rs.getString("Nome"));
                pag.setCategoria(rs.getString("Categoria"));
                pag.setValor(rs.getDouble("Valor"));
                pag.setParcelas(rs.getInt("Parcela"));
                pag.setRepeticao(rs.getString("Repeticao"));
                pag.setData(rs.getDate("Data"));
                pags.add(pag);
            }
            return pags;
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return null;
    }
    ///////////////////////////////////////////////////////
    
    public ArrayList<Pagamentos> buscarPagamentosHoje(String nome) {
        //Pegando a data de hoje do PC
        java.util.Date data = new java.util.Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        
        //Método para fazer o loop e pegar todos os pagamentos á receber hoje
        String sql = "SELECT id, Nome  , Categoria , Valor , Parcela , Repeticao, Data "
                + "FROM pagamentos "
                + "WHERE Data = '" + formato.format(data) + "' "
                + "ORDER BY Data;";
        Statement st;
        try{
           
            Connection conn = conectar();
            st = conn.createStatement();
             
            ResultSet rs = st.executeQuery(sql);
            
            Pagamentos pag;
            ArrayList<Pagamentos> pags = new ArrayList<Pagamentos>();
            
            //Enquanto tiver uma proxima linha no resultado eu pego suas informações
            while(rs.next()){
                pag = new Pagamentos();
                pag.setId(rs.getInt("id"));
                pag.setNome(rs.getString("Nome"));
                pag.setCategoria(rs.getString("Categoria"));
                pag.setValor(rs.getDouble("Valor"));
                pag.setParcelas(rs.getInt("Parcela"));
                pag.setRepeticao(rs.getString("Repeticao"));
                pag.setData(rs.getDate("Data"));
                pags.add(pag);
            }
            return pags;
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return null;
    }
    //Método para buscar os pagamentos atrasados no banco
    public ArrayList<Pagamentos> buscarPagamentosAtrasados(String nome) {
        //Pegando a data de hoje do PC
        java.util.Date data = new java.util.Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");        
        //Método para fazer o loop e pegar todas pagamentos atrasados no banco 
        String sql = "SELECT id, Nome , Categoria , Valor , Parcela , Repeticao, Data "
                + "FROM pagamentos "
                + "WHERE Data < '" + formato.format(data) + "' "
                + "ORDER BY Data;";
        Statement st;
        try{
           
            Connection conn = conectar();
            st = conn.createStatement();
            //Fui no banco 
            ResultSet rs = st.executeQuery(sql);
            
            Pagamentos pag;
            ArrayList<Pagamentos> pags = new ArrayList<Pagamentos>();
            
            //Enquanto tiver uma proxima linha no resultado eu pego suas informações
            while(rs.next()){
                pag = new Pagamentos();
                pag.setId(rs.getInt("id"));
                pag.setNome(rs.getString("Nome"));
                pag.setCategoria(rs.getString("Categoria"));
                pag.setValor(rs.getDouble("Valor"));
                pag.setParcelas(rs.getInt("Parcela"));
                pag.setRepeticao(rs.getString("Repeticao"));
                pag.setData(rs.getDate("Data"));
                pags.add(pag);
            }
            return pags;
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return null;
    }
    
    //Método para buscar os pagamentos atrasadas no banco
    public ArrayList<Pagamentos> buscarPagamentosAmanha(String nome) {
        //Pegando a data de hoje do PC
        java.util.Date data = new java.util.Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");        
        //Método para fazer o loop e pegar todos pagamentos atrasados no banco 
        String sql = "SELECT id, Nome ,Categoria , Valor , Parcela , Repeticao, Data "
                + "FROM pagamentos "
                + "WHERE Data = (ADDDATE('" + formato.format(data) + "' , INTERVAL 1 DAY)) "
                + "ORDER BY Data;";
        Statement st;
        try{
           
            Connection conn = conectar();
            st = conn.createStatement();
            //Fui no banco 
            ResultSet rs = st.executeQuery(sql);
            
            Pagamentos pag;
            ArrayList<Pagamentos> pags = new ArrayList<Pagamentos>();
            
            //Enquanto tiver uma proxima linha no resultado eu pego suas informações
            while(rs.next()){
                pag = new Pagamentos();
                pag.setId(rs.getInt("id"));
                pag.setNome(rs.getString("Nome"));
                pag.setCategoria(rs.getString("Categoria"));
                pag.setValor(rs.getDouble("Valor"));
                pag.setParcelas(rs.getInt("Parcela"));
                pag.setRepeticao(rs.getString("Repeticao"));
                pag.setData(rs.getDate("Data"));
                pags.add(pag);
            }
            return pags;
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return null;
    }
    
    //Método para diminuir uma parcela de um pagamento quando o mesmo tiver e for informado recebimento
    public void diminuirParcela(Pagamentos pags){
        String sql = "UPDATE pagamentos SET Parcela = Parcela - 1 WHERE id = ?";
        try{
            Connection conn = conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, pags.getId());
            ps.execute();
            ps.close();
                    
        }catch(Exception sqle){
            sqle.printStackTrace();
        }
    }
    
    
    //Método para efetuar recebimento de um pagamento diário ou semanal
    public void pagarDia(Pagamentos pags , int dias){
        String sql = "UPDATE pagamentos SET Data = ADDDATE(Data, INTERVAL ? DAY)"
                + "WHERE id  = ?";
        try {
            Connection conn = conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, dias);
            ps.setInt(2, pags.getId());
            
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null,
                    "Pagamento recebido com sucesso !"
                    ,"Pagamentos"
                    ,JOptionPane.WARNING_MESSAGE);
             
        }catch (SQLException se) {
            se.printStackTrace();
        }     
    }
    
    //Método para efetuar o recebimento de um pagamento mensal,bimestral,etc...
    public void pagarMes(Pagamentos pags , int meses){
        String sql = "UPDATE pagamentos SET Data = ADDDATE(Data, INTERVAL ? MONTH)"
                + "WHERE id  = ?";
        try {
            Connection conn = conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, meses);
            ps.setInt(2, pags.getId());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null,
            "Pagamento recebido com sucesso !"
            ,"Pagamentos"
            ,JOptionPane.WARNING_MESSAGE);
             
        }catch (SQLException se) {
            se.printStackTrace();
        }     
    }
    
    //Método para pagar uma o recebimer um pagamento anual
    public void pagarAno(Pagamentos pags){
        String sql = "UPDATE pagamentos SET Data = ADDDATE(Data, INTERVAL 1 YEAR)"
                + "WHERE id  = ?";
        try {

            Connection conn = conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, pags.getId());    
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null,
            "Pagamento recebido com sucesso !"
            ,"Pagamentos"
            ,JOptionPane.WARNING_MESSAGE);
             
        }catch (SQLException se) {
            se.printStackTrace();
        }     
    }
    
    //Métodos para retornar o número de pagamentos ao lado de cada botão como notificção
    public int notifiTodosPagamentos(){        
        String sql = "SELECT COUNT(id) FROM pagamentos ";
        
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
    public int notifiTodosPagamentosHoje(){
        //Pegando a data de hoje do PC
        java.util.Date data = new java.util.Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        
        String sql = "SELECT COUNT(id) FROM pagamentos WHERE Data = '"
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
    
    public int notifiTodosPagamentosAtrasados(){
        //Pegando a data de hoje do PC
        java.util.Date data = new java.util.Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        
        String sql = "SELECT COUNT(id) FROM pagamentos WHERE Data < '"
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
    //Método para notificar todos os pagamentos a receber amamha
    public int notifiTodosPagamentosAmanha(){
        //Pegando a data de hoje do PC
        java.util.Date data = new java.util.Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        
        String sql = "SELECT COUNT(id) FROM pagamentos WHERE Data = (ADDDATE('" + formato.format(data) + "' , INTERVAL 1 DAY)) ";
        
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
    
    //Método para atualizar o saldo do usuário quando ele receber um pagamento
    public void attSaldo(Pagamentos pags){
        String sql = "UPDATE saldo SET saldoAtual = (saldoAtual + ?)";    
        try {
            Connection conn = conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setDouble(1, pags.getValor());

            ps.execute();
            ps.close();
             
        }catch (SQLException se) {
            se.printStackTrace();
        }
    }
    //Método para atualizar suas metas se elas existirem
    public void attMeta(Pagamentos pags){

        String sql = "SELECT Meta FROM metas WHERE Categoria = '" + pags.getCategoria() + "' ";    
        try {
            //vamos primeiro ver se ele definiu uma meta para a categgoria informada          
            Connection conn = conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);           
            double meta = 0;
            while(rs.next()){
                 meta = rs.getDouble("Meta");           
            }
            //se SIM vamos setar o Progresso = Progresso + getValor do Pagamento
            if (meta > 0){

                String sql1 = " UPDATE metas SET Progresso = Progresso + ? WHERE Categoria = ?;";
                PreparedStatement ps1 = conn.prepareStatement(sql1);
                ps1.setDouble(1, pags.getValor());
                ps1.setString(2, pags.getCategoria());
                ps1.execute();
                ps1.close();
                JOptionPane.showMessageDialog(null, 
                        pags.getValor()
                        + " Foi acrescentado na sua Meta de " + 
                        pags.getCategoria());
            }

        }catch (SQLException se) {
            se.printStackTrace();
        }
    }
    
    //Método para inserir os dados de pagamentos na tabela relatório
    public void attHistoricoPagamento(Pagamentos pags) throws ParseException{
        String sql = "INSERT INTO historico (Tipo , PrecoOUValor , Data) Values (? , ? , ?)";
        
        try {
            Connection conn = conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1,"Pagamento");
            ps.setDouble(2,pags.getValor());
            //
            //Aqui vamos pegar a data da nosso PC
            //formata-la pegando apenas o ano e o mês
            //e inserindo no banco , porém o banco irá colocar como padrão o dia 01
            //entretanto irá respeitar o mês e o ano
            //então quando formos buscar o relatório na telaRelatorio vamos fazer o mesmo
            //la estará inserido 2019-12-01 pois não podemos inserir penas MES e ANO no banco
            //vamos mandar buscar somente o ano e mês informados e ele irá buscar pelo dia 01 também

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
        

   

