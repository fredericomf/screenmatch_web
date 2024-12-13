package com.fredericomf.screenmatch_web.dto;

import com.fredericomf.screenmatch_web.model.Categoria;

public record SerieDTO(
        Long id,
        String titulo,
        Integer totalTemporadas,
        Double avaliacao,
        Categoria genero,
        String atores,
        String poster,
        String sinopse) {
}
