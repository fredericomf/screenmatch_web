package com.fredericomf.screenmatch_web.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
