import java.util.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class App {
    public static final String DB_URL = "jdbc:mysql://localhost:3306/pessoas";
    public static final String USER = "root";
    public static final String PSWD = "123456789";

    public static void main(String[] args) throws Exception {
        try {
            Scanner meuScanner = new Scanner(System.in);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexao = DriverManager.getConnection(DB_URL, USER, PSWD);
        
        
            while(true) {
                Menu.menu();
                int opcao = meuScanner.nextInt();

                switch(opcao){
                    case 1:
                        meuScanner.nextLine(); //pega linha gerada pelo nextInt
                        System.out.println("Digite o nome;");
                        String nome = meuScanner.nextLine();
                        System.out.println("Digite a cidade;");
                        String cidade = meuScanner.nextLine();
                        System.out.println("Digite o cpf;");
                        long cpf = meuScanner.nextLong();
                        
                        String inserirQuerry = "INSERT INTO pessoa (nome, cidade, cpf) VALUES (?,?,?)";
                        PreparedStatement inserirStatement = conexao.prepareStatement(inserirQuerry);
                        inserirStatement.setString(1, nome);
                        inserirStatement.setString(2, cidade);
                        inserirStatement.setLong(3, cpf);
                        inserirStatement.executeUpdate();
                        break;
                    case 2:
                        meuScanner.nextLine(); //pega linha gerada pelo nextInt
                        System.out.println("Digite o cpf da pessoa que deve ser atualizada;");
                        Long pessoaParaAtualizar = meuScanner.nextLong();
                        meuScanner.nextLine(); //pega linha gerada pelo nextLong
                        System.out.println("Digite o novo nome;");
                        String nomeAtualizado = meuScanner.nextLine();
                        System.out.println("Digite a cidade;");
                        String cidadeAtualizada = meuScanner.nextLine();
                        
                        String atualizarQuerry = "UPDATE pessoa SET nome = ?, cidade = ? WHERE cpf = ?";
                        PreparedStatement atualizarStatement = conexao.prepareStatement(atualizarQuerry);
                        atualizarStatement.setString(1, nomeAtualizado);
                        atualizarStatement.setString(2, cidadeAtualizada);
                        atualizarStatement.setLong(3, pessoaParaAtualizar);
                        atualizarStatement.executeUpdate();
                        break;
                    case 3:
                        String listarQuerry = "SELECT * FROM pessoa";
                        PreparedStatement listarStatement = conexao.prepareStatement(listarQuerry);
                        ResultSet resultadoListagem = listarStatement.executeQuery();
                        while(resultadoListagem.next()){
                            System.out.println("NOME: " + resultadoListagem.getString("nome") + " | CPF: " + resultadoListagem.getLong("cpf") + " | CIDADE: " + resultadoListagem.getString("cidade"));
                        }
                        break;
                    case 4:
                        meuScanner.nextLine();
                        System.out.println("Digite uma cidade para filtrar: ");
                        String cidadeParaFiltro = meuScanner.nextLine();

                        String listarFiltradoQuerry = "SELECT * FROM pessoa WHERE cidade =?";
                        PreparedStatement listarFiltradoStatement = conexao.prepareStatement(listarFiltradoQuerry);
                        listarFiltradoStatement.setString(1, cidadeParaFiltro);
                        ResultSet resutadoFiltradoListagem = listarFiltradoStatement.executeQuery();
                        while (resutadoFiltradoListagem.next()){
                            System.out.println("NOME: " + resutadoFiltradoListagem.getString("nome") + " | CPF: " + resutadoFiltradoListagem.getLong("cpf") + " | CIDADE: " + resutadoFiltradoListagem.getString("cidade"));
                        }
                        break;
                    case 5:
                        System.out.println("Saindo...");
                        conexao.close();
                        return;
                    default:
                        System.out.println("Opcao inválida");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
