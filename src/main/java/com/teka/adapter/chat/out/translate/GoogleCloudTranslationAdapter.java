package com.teka.adapter.chat.out.translate;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.api.services.translate.TranslateScopes;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.v3.*;
import com.teka.domain.user.type.Language;
import com.teka.shared.config.properties.GcpProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Component
public class GoogleCloudTranslationAdapter {

    private final GcpProperties gcpProperties;
    private TranslationServiceClient client;
    private String parent;

    @PostConstruct
    private void init() {
        try (InputStream keyFile = new ClassPathResource(gcpProperties.getCredentials()).getInputStream()) {
            GoogleCredentials credentials = GoogleCredentials.fromStream(keyFile)
                    .createScoped(Collections.singletonList(TranslateScopes.CLOUD_TRANSLATION));
            TranslationServiceSettings settings = TranslationServiceSettings.newBuilder()
                    .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                    .build();
            client = TranslationServiceClient.create(settings);
            parent = LocationName.of(gcpProperties.getProjectId(), "global").toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Language detectLanguage(String text) {
        DetectLanguageRequest request = DetectLanguageRequest.newBuilder()
                .setParent(parent)
                .setMimeType("text/plain")
                .setContent(text)
                .build();

        DetectLanguageResponse response = client.detectLanguage(request);

        return Language.fromCode(response.getLanguages(0).getLanguageCode());
    }

    public Translation translateText(String targetLanguageCode, String text) {
        TranslateTextRequest request = TranslateTextRequest.newBuilder()
                .setParent(parent)
                .setMimeType("text/plain")
                .setTargetLanguageCode(targetLanguageCode)
                .addContents(text)
                .build();

        TranslateTextResponse response = client.translateText(request);

        return response.getTranslations(0);
    }

    public List<Translation> translateAllTexts(String targetLanguageCode, List<String> texts) {
        TranslateTextRequest request = TranslateTextRequest.newBuilder()
                .setParent(parent)
                .setMimeType("text/plain")
                .setTargetLanguageCode(targetLanguageCode)
                .addAllContents(texts)
                .build();

        TranslateTextResponse response = client.translateText(request);

        return response.getTranslationsList();
    }
}
