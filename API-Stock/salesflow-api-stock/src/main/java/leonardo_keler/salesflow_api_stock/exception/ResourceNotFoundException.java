package leonardo_keler.salesflow_api_stock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção customizada para ser lançada quando um recurso (como Produto, Marca, etc.)
 * não é encontrado no banco de dados.
 * A anotação @ResponseStatus garante que uma resposta HTTP 404 Not Found seja retornada.
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}