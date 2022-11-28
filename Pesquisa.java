public class Pesquisa {
    private int idPesquisa;
    private String insituicao;
    private String data;
    private String votos;
    private String c1;
    private String v1;
    private String c2;
    private String v2;
    private String c3;
    private String v3;

    public int getId() {
        return idPesquisa;
    }
    public void setId(int id) {
        this.idPesquisa = id;
    }
    public String getInsituicao() {
        return insituicao;
    }
    public void setInsituicao(String insituicao) {
        this.insituicao = insituicao;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getVotos() {
        return votos;
    }
    public void setVotos(String votos) {
        this.votos = votos;
    }


    public String getC1() {
        return c1;
    }
    public void setC1(String C1) {
        this.c1 = C1;
    }
    public String getC2() {
        return c2;
    }
    public void setC2(String C2) {
        this.c2 = C2;
    }
    public String getC3() {
        return c3;
    }
    public void setC3(String C3) {
        this.c3 = C3;
    }

    public String getV1() {
        return v1;
    }
    public void setV1(String V1) {
        this.v1 = V1;
    }
    public String getV2() {
        return v2;
    }
    public void setV2(String V2) {
        this.v2 = V2;
    }
    public String getV3() {
        return v3;
    }
    public void setV3(String V3) {
        this.v3 = V3;
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

        Pesquisa outraPesquisa = (Pesquisa) obj;
        return idPesquisa == outraPesquisa.getId();
    }

} // fim da classe Pesquisa
