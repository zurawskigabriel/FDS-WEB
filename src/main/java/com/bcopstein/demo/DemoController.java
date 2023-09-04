package com.bcopstein.demo;

import java.util.LinkedList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/livros")
    @CrossOrigin(origins = "*")
    public List<Livro> getLivros() {
        return livros;
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

    @GetMapping("/autores")
    @CrossOrigin(origins = "*")
    public List<String> getLivrosAutor() {
        List<String> autores = new LinkedList<>();
        for(Livro livro : livros) {
            autores.add(livro.getAutor());
        }
        return autores;
    }
}
