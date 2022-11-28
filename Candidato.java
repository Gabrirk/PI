public class Candidato {
    private int id;
    private String nome;
    private String numero;
    private String partido;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
        
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getPartido() {
        return partido;
    }
    public void setPartido(String partido) {
        this.partido = partido;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Candidato outroCandidato = (Candidato) obj;
        return id == outroCandidato.getId();
    }
    


} // fim da classe Candidato
