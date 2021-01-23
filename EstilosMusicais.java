
public class EstilosMusicais {
    private String nome;
    private int codigo;
    
    public EstilosMusicais (int codigo, String nome) {
    	this.codigo = codigo;
    	this.nome = nome;
    }
    
    public int getCodigo() {
    	return codigo;
    }
	  
    public void setCodigo(int codigo) {
    	this.codigo = codigo;
    }
    
    public String getNome() {
      return nome;
    }
    
    public void setNome(String nome) {
      this.nome = nome;
    }
  
    public String toString() {
      return this.codigo + "\t" + this.nome + "\n" ;
    }

  }
