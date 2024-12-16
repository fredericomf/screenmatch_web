package com.fredericomf.screenmatch_web.repository;

import com.fredericomf.screenmatch_web.model.Categoria;
import com.fredericomf.screenmatch_web.model.Episodio;
import com.fredericomf.screenmatch_web.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);

    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, double avaliacao);

    List<Serie> findTop5ByOrderByAvaliacaoDesc();

    List<Serie> findByGenero(Categoria categoria);

    List<Serie> findByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual(int totalTemporadas, double avaliacao);

    @Query("select s from Serie s WHERE s.totalTemporadas <= :totalTemporadas AND s.avaliacao >= :avaliacao")
    List<Serie> seriesPorTemporadaEAValiacao(int totalTemporadas, double avaliacao);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:trechoEpisodio%")
    List<Episodio> episodiosPorTrecho(String trechoEpisodio);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.avaliacao DESC LIMIT 5")
    List<Episodio> topEpisodiosPorSerie(Serie serie);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie AND YEAR(e.dataLancamento) >= :anoLancamento")
    List<Episodio> episodiosPorSerieEAno(Serie serie, int anoLancamento);

    /*
     * Ao executar esse código, ele pega os 5 episodios mais recentes e retorna as
     * séries correspondentes a eles, fazendo um left join. Porém, se seus episódios
     * mais recentes forem de uma única série, ele retornará apenas essa série, não
     * trazendo 5 resultados diferentes.
     * 
     * No desenvolvimento colaborativo, é muito comum encontrar problemas ao rodar
     * códigos em máquinas diferentes, com registros diferentes. Por isso é
     * importante que sejamos cuidadosos ao testar cada funcionalidade nova.
     * 
     * Para resolver esse problema, precisaremos recorrer às consultas JPQL, usando
     * um inner join, diferente do left join, agrupando os dados por série, a fim de
     * trazer 5 registros diferentes do banco. Abordaremos em vídeo sobre essa
     * correção, mas caso você queira se adiantar, utilize o código a seguir no
     * lugar da derived querie findTop5ByOrderByEpisodiosDataLancamentoDesc().
     */
    List<Serie> findTop5ByOrderByEpisodiosDataLancamentoDesc();

    @Query("SELECT s FROM Serie s " +
            "JOIN s.episodios e " +
            "GROUP BY s " +
            "ORDER BY MAX(e.dataLancamento) DESC LIMIT 5")
    List<Serie> encontrarEpisodiosMaisRecentes();

    @Query("SELECT e FROM Serie s JOIN s.episodios e where s.id = :id AND e.temporada = :temporada")
    List<Episodio> obterEpisodiosPorTemporada(Long id, Long temporada);
}
