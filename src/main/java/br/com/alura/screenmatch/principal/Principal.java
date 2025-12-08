package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.models.DadosEpisodio;
import br.com.alura.screenmatch.models.DadosSerie;
import br.com.alura.screenmatch.models.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private ConsumoAPI consumo = new ConsumoAPI();
    private Scanner scanner = new Scanner(System.in);
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=35ffd357";

    public void exibeMenu(){
        System.out.println("Digite o nome da série: ");
        var nomeSerie = scanner.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        List<DadosTemporada> temporadas = new ArrayList<>();

        for(int i = 1; i <= dados.totalTemporadas(); i++){
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }

        temporadas.forEach(System.out::println);

        //gilmore+girls&season=" + i +"
    }

}
