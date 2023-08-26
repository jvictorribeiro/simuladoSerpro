public class Pessoa {
  private String nome;
  private long cpf;
  private String cidade;
  
  public Pessoa(String nome, long cpf, String cidade){
    this.nome = nome;
    this.cpf = cpf;
    this.cidade = cidade;
  }

  public String getNome(){
    return nome;
  }

  public long getCpf() {
    return cpf;
  }

  public String getCidade(){
    return cidade;
  }
}

