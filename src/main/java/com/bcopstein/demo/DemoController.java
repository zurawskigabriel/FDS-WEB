package com.bcopstein.demo;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/biblioteca")
public class DemoController{
    private List<Livro> livros;

    public DemoController(){
        livros = new LinkedList<>();
        livros.add(new Livro(100,"Aprendendo Spring-Boot","Huguinho Pato",2020));
        livros.add(new Livro(120,"Aprendendo Java","Zezinho Pato",2015));
        livros.add(new Livro(140,"Aprendendo Outra coisa","Luizinho Pato",2023));
        livros.add(new Livro(140,"Aprendendo Uma coisa nova","Huguinho Pato",2023));
    }

    @GetMapping("/inicio")
    @CrossOrigin(origins = "*")
    public String consultaCidadesAtendidas() {
        return "Bem vindo a biblioteca central!";
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Consultas gerais
    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/livros")
    @CrossOrigin(origins = "*")
    public List<Livro> getLivros() {
        return livros;
    }

    @GetMapping("/autores")
    @CrossOrigin(origins = "*")
    public List<String> getLivrosAutor() {
        List<String> autores = new LinkedList<>();
        for(Livro livro : livros) {
            autores.add(livro.getAutor());
        }
        return autores;
    }

    @GetMapping("/titulos")
    @CrossOrigin(origins = "*")
    public List<String> getLivrosTitulo() {
        List<String> titulos = new LinkedList<>();
        for(Livro livro : livros) {
            titulos.add(livro.getTitulo());
        }
        return titulos;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Consultas especificas
    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/livrosPorAutor")
    @CrossOrigin(origins = "*")
    public List<Livro> getLivrosPorAutor(@RequestParam(value = "autor") String autor) {
        return livros.stream()
               .filter(livro -> livro.getAutor().equals(autor))
               .toList();
    }

    @GetMapping("/livrosporautor/{autor}/ano/{ano}")
    @CrossOrigin(origins = "*")
    public List<Livro> getLivrosPorAutor(@PathVariable(value = "autor") String autor,
                                        @PathVariable(value = "ano") int ano) {
        return livros.stream()
               .filter(livro -> livro.getAutor().equals(autor))
               .filter(livro -> livro.getAno() == ano)
               .toList();
    } 

    @GetMapping("/livrosPorTitulo")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Livro> getLivroPorTitulo(@RequestParam(value = "titulo") String titulo) {
        Livro resp = livros.stream()
                     .filter(livro -> livro.getTitulo().equals(titulo))
                     .findFirst()
                     .orElse(null);
        return ResponseEntity
               .status(HttpStatus.OK)
               .body(resp);
    }

    @GetMapping("/livrosPorAno")
    @CrossOrigin(origins = "*")
    public List<Livro> getLivroPorAno(@RequestParam(value = "ano") String ano) {
        return livros.stream()
               .filter(livro -> livro.getAno() == Integer.valueOf(ano))
               .toList();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Cadastros
    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    @PostMapping("/novolivro")
    @CrossOrigin(origins = "*")
    public boolean cadastraLivroNovo(@RequestBody final Livro livro){
        livros.add(livro);
        return true;
    }
}
