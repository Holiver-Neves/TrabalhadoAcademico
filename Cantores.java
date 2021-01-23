
public class Cantores {
    private String nome;
	private int estilo;
    private String musicaPrincipal;
    
    public Cantores (String nome, int estilo, String musicaPrincipal) {
    	this.nome = nome;
    	this.estilo = estilo;
    	this.musicaPrincipal = musicaPrincipal;
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getEstilo() {
		return estilo;
	}

	public void setEstilo(int estilo) {
		this.estilo = estilo;
	}

	public String getMusicaPrincipal() {
		return musicaPrincipal;
	}

	public void setMusicaPrincipal(String musicaPrincipal) {
		this.musicaPrincipal = musicaPrincipal;
	}
    
	public String toString() {
		return this.nome + "\t" + this.estilo + "\t" + this.musicaPrincipal + "\n" ;
	}

  }