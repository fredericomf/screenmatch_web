package com.fredericomf.screenmatch_web.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ConsultaChatGPT {

    static boolean activated = false;

    public static String obterTraducao(String texto) {

        if (!activated) {
            return texto;
        }

        OpenAiService service = new OpenAiService(System.getenv("OPENAI_APIKEY"));

        CompletionRequest requisicao = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt("traduza para o português o texto: " + texto)
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        var resposta = service.createCompletion(requisicao);
        return resposta.getChoices().get(0).getText();
    }
}
