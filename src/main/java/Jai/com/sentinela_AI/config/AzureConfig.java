package Jai.com.sentinela_AI.config;


import com.azure.ai.textanalytics.TextAnalyticsClient;
import com.azure.ai.textanalytics.TextAnalyticsClientBuilder;
import com.azure.ai.textanalytics.TextAnalyticsServiceVersion;
import com.azure.core.credential.AzureKeyCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureConfig {


    @Value("${ai.language.endpoint}")
    private String endpoint;
    @Value("${ai.language.key}")
    private String key;

    @Bean
    public TextAnalyticsClient textAnalyticsClient() {
        return new TextAnalyticsClientBuilder()
                .endpoint(endpoint)
                .credential(new AzureKeyCredential(key))
                .serviceVersion(TextAnalyticsServiceVersion.V2022_05_01)
                .buildClient();
    }
}


