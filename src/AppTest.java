import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {
  @Test
  public void getNomeTest(){
    String nome = "teste";
    long cpf = 123456789;
    String cidade = "Fortaleza";
    Pessoa pessoa = new Pessoa(nome, cpf, cidade);
    assertEquals("teste", pessoa.getNome());
    assertEquals(123456789, pessoa.getCpf());
    assertEquals("Fortaleza", pessoa.getCidade());
    assertNotEquals("Rio de Janeiro", pessoa.getCidade());
  }
}
