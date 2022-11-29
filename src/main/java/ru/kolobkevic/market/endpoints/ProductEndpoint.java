package ru.kolobkevic.market.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.kolobkevic.market.converters.ProductWsConverter;
import ru.kolobkevic.market.exceptions.ResourceNotFoundException;
import ru.kolobkevic.market.services.ProductService;
import ru.kolobkevic.market.ws.products.GetAllProductsRequest;
import ru.kolobkevic.market.ws.products.GetAllProductsResponse;
import ru.kolobkevic.market.ws.products.GetProductByIdRequest;
import ru.kolobkevic.market.ws.products.GetProductByIdResponse;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.kolobkevic.ru/market/ws/products";
    private final ProductService productService;
    private final ProductWsConverter productWsConverter;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductByIdResponse getProductById(@RequestPayload GetProductByIdRequest request) {
        GetProductByIdResponse response = new GetProductByIdResponse();
        response.setProductDtoWs(productWsConverter.entityToDto(productService.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"))));
        return response;
    }

/*
        Пример запроса: POST http://localhost:8189/market/ws
        Header -> Content-Type: text/xml

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.kolobkevic.ru/market/ws/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getProductByIdRequest>
                <f:id>1</f:id>
                </f:getProductByIdRequest>
            </soapenv:Body>
        </soapenv:Envelope>
*/

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts() {
        GetAllProductsResponse response = new GetAllProductsResponse();
        var page = productService.findAllInOnePage();
        for (var i : page) {
            response.getProducts().add(productWsConverter.entityToDto(i));
        }
        return response;
    }

/*
        Пример запроса: POST http://localhost:8189/market/ws
        Header -> Content-Type: text/xml

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.kolobkevic.ru/market/ws/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getAllProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
*/
}
