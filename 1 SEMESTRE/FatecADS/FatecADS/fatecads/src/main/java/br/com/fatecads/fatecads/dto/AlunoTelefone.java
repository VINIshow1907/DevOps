package br.com.fatecads.fatecads.dto;

public class AlunoTelefone {
    
    private String nomeAluno;
    private String telefoneAluno;
    
    public AlunoTelefone(String nomeAluno, String telefoneAluno) {
        this.nomeAluno = nomeAluno;
        this.telefoneAluno = telefoneAluno;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public String getTelefoneAluno() {
        return telefoneAluno;
    }

    
}
