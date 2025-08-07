package org.example;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatCompletion;
import com.openai.models.ChatCompletionCreateParams;
import com.openai.models.ChatModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class GeradorDeTestes {
    public static void main(String[] args) throws IOException {
        // Configures using the `OPENAI_API_KEY`, `OPENAI_ORG_ID` and `OPENAI_PROJECT_ID` 
        // environment variables
        OpenAIClient client = OpenAIOkHttpClient.fromEnv();

        // Lê prompt do arquivo externo
        // Alterar caminho para a sua pasta
        String prompt = Files.readString(new File("C:/Users/Bluedata TI/Downloads/Teste_Software_LLM_2025_Queiroz_Itor-main/Teste_Software_LLM_2025_Queiroz_Itor-main/vulnerable-app/prompts/prompt.txt").toPath());

        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                .addUserMessage(prompt)
                .model(ChatModel.GPT_4O_MINI)
                .temperature(0.4)
                .build();

        // Faz chamada à API
        ChatCompletion chatCompletion = client.chat().completions().create(params);

        // Guarda o código gerado
        String generatedTest = chatCompletion.choices().get(0).message().content().orElse("");

        // Salva o teste gerado em um arquivo java para ser executado
        // Alterar caminho para a sua pasta
        File outputFile = new File(
    "C:/Users/Bluedata TI/Downloads/Teste_Software_LLM_2025_Queiroz_Itor-main/Teste_Software_LLM_2025_Queiroz_Itor-main/vulnerable-app/src/test/java/org/example/BcryptPasswordHashFunctionTest.java");
        outputFile.getParentFile().mkdirs();
        Files.writeString(outputFile.toPath(), generatedTest);

        System.out.println("Teste salvo em: " + outputFile.getAbsolutePath());
    }
}
