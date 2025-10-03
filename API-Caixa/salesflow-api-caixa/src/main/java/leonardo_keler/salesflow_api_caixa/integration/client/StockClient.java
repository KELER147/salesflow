package leonardo_keler.salesflow_api_caixa.integration.client;

import leonardo_keler.salesflow_api_caixa.integration.dto.ProductDetailsDTO;
import leonardo_keler.salesflow_api_caixa.integration.dto.StockDebitDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StockClient {

    private final RestTemplate restTemplate;

    @Value("${api.stock.url}")
    private String stockApiUrl;


    public Map<Long, ProductDetailsDTO> getProductsDetails(List<Long> productIds) {
        String ids = productIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        String url = stockApiUrl + "/products/details?ids=" + ids;

        ProductDetailsDTO[] response = restTemplate.getForObject(url, ProductDetailsDTO[].class);

        if (response == null) {
            return Map.of();
        }

        return Arrays.stream(response)
                .collect(Collectors.toMap(ProductDetailsDTO::id, Function.identity()));
    }

    public void debitStock(List<StockDebitDTO> items) {
        String url = stockApiUrl + "/stock/debit";

        restTemplate.postForLocation(url, items);
    }
}