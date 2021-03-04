/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalizado;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caique
 */
//Classe para personalizarmos nossas tabelas
public class meuDefaultTableModel extends DefaultTableModel {
    public meuDefaultTableModel(String[] colunas , int i){
        super(colunas , i);
    }
    
    //Método para não deixar o usuario clicar e alterar os dados da tebela
    @Override
    public boolean isCellEditable(int linha, int coluna){
        return false;
    }
    
    
}
