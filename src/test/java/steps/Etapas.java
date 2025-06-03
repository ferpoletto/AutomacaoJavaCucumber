
package steps;

import io.cucumber.java.pt.*;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.APIUtils;
import utils.DBUtils;
import java.time.Duration;
import java.util.Map;

public class Etapas {
    WebDriver driver;

    @Dado("que abro a aplicação")
    @Step("Abrindo a aplicação")
    public void abrirAplicacao() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(System.getenv("URL_QA1"));
    }

    @Quando("clico no elemento com id {string}")
    @Step("Clicando no elemento com id: {0}")
    public void clicarElemento(String id) {
        driver.findElement(By.id(id)).click();
    }

    @Quando("altero o valor do campo com id {string} para {string}")
    @Step("Alterando valor do campo {0} para {1}")
    public void alterarValor(String id, String valor) {
        WebElement el = driver.findElement(By.id(id));
        el.clear();
        el.sendKeys(valor);
    }

    @Quando("preencho o campo com id {string} com {string}")
    @Step("Preenchendo campo {0} com {1}")
    public void preencherCampo(String id, String valor) {
        driver.findElement(By.id(id)).sendKeys(valor);
    }

    @Quando("seleciono a opção {string} do dropdown com id {string}")
    @Step("Selecionando {0} do dropdown {1}")
    public void selecionarDropdown(String opcao, String id) {
        WebElement dropdown = driver.findElement(By.id(id));
        dropdown.findElement(By.xpath(".//option[text()='" + opcao + "']")).click();
    }

    @Quando("marco o botão de rádio com id {string}")
    @Step("Marcando botão de rádio {0}")
    public void marcarRadio(String id) {
        driver.findElement(By.id(id)).click();
    }

    @Quando("marco a caixa de seleção com id {string}")
    @Step("Marcando checkbox {0}")
    public void marcarCheckbox(String id) {
        WebElement el = driver.findElement(By.id(id));
        if (!el.isSelected()) {
            el.click();
        }
    }

    @Quando("navego para a URL {string}")
    @Step("Navegando para {0}")
    public void navegar(String url) {
        driver.get(System.getenv("URL_QA1") + url);
    }

    @Entao("valido que o elemento com id {string} existe")
    @Step("Validando existência do elemento {0}")
    public void validarElementoExiste(String id) {
        if (driver.findElements(By.id(id)).isEmpty()) {
            throw new AssertionError("Elemento não encontrado: " + id);
        }
    }

    @Entao("espero {int} segundos")
    @Step("Esperando por {0} segundos")
    public void esperar(int segundos) throws InterruptedException {
        Thread.sleep(segundos * 1000);
    }

    @Quando("faço uma requisição GET para {string}")
    public void requisicaoGet(String endpoint) {
        APIUtils.get(endpoint);
    }

    @Quando("executo a query {string}")
    public void executarQuery(String query) {
        DBUtils.executarQuery(query);
    }

    @Entao("valido que o campo {string} da resposta é {string}")
    public void validarCampoResposta(String campo, String valor) {
        Map<String, Object> resp = APIUtils.getUltimaResposta();
        if (!resp.get(campo).toString().equals(valor)) {
            throw new AssertionError("Valor incorreto: " + campo);
        }
    }
}
