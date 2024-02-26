package br.com.xmrtecnologia.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {
    
    public WebDriver acessarAplicacao() throws MalformedURLException {

        // WebDriver webDriver = new ChromeDriver();  // Trabalhando com o Selenium Local
        
        // Para saber esta URL tem que ver ao executar o arquivo: Start_Selenium_Server_3.141.59.bat
        // ou execute ipconfig no terminal para pegar o IP
        URL urlHub = new URL("http://192.168.56.1:4444/wd/hub"); // Trabalhando com o Selenium Grid
        DesiredCapabilities cap = DesiredCapabilities.chrome();  // Trabalhando com o Selenium Grid
        WebDriver webDriver = new RemoteWebDriver(urlHub, cap);  // Trabalhando com o Selenium Grid
        webDriver.navigate().to("http://192.168.56.1:8001/tasks");  // Trabalhando com o Selenium Grid
        
        // webDriver.navigate().to("http://localhost:8001/tasks");  // Trabalhando com o Selenium Local

        // Definindo uma estratégia de espera para o Selenium
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Esperando 10 segundos
        return webDriver;
    }

    // @Test
    // public void testeAmbiente() {
    //     WebDriver webDriver = new ChromeDriver();
    //     webDriver.navigate().to("http://www.google.com");
    // }

    @Test
    public void deveSalvarTarefaComSucessoTest() throws MalformedURLException {
        WebDriver webDriver = acessarAplicacao();
        try {
    
            // Clicar em Add Todo
            webDriver.findElement(By.id("addTodo")).click();
            
            // Escrever a Descrição
            webDriver.findElement(By.id("task")).sendKeys("Teste via Selenium");
            
            // Escrever a Data
            webDriver.findElement(By.id("dueDate")).sendKeys("10/10/2050");
            
            // Clicar no Botão Save
            webDriver.findElement(By.id("saveButton")).click();
    
            // Validar mensagem de Sucesso
            String message = webDriver.findElement(By.id("message")).getText();
            Assert.assertEquals("Success!", message);
            
        } finally {
            // Fechar o Browser
            webDriver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemDescricaoTest() throws MalformedURLException {
        WebDriver webDriver = acessarAplicacao();
        try {
    
            // Clicar em Add Todo
            webDriver.findElement(By.id("addTodo")).click();
            
            // Escrever a Descrição
            // webDriver.findElement(By.id("task")).sendKeys("Teste via Selenium");
            
            // Escrever a Data
            webDriver.findElement(By.id("dueDate")).sendKeys("10/10/2050");
            
            // Clicar no Botão Save
            webDriver.findElement(By.id("saveButton")).click();
    
            // Validar mensagem de Sucesso
            String message = webDriver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the task description", message);
            
        } finally {
            // Fechar o Browser
            webDriver.quit();
        }

    }

    @Test
    public void naoDeveSalvarTarefaSemDataTest() throws MalformedURLException {
        WebDriver webDriver = acessarAplicacao();
        try {
    
            // Clicar em Add Todo
            webDriver.findElement(By.id("addTodo")).click();
            
            // Escrever a Descrição
            webDriver.findElement(By.id("task")).sendKeys("Teste via Selenium");
            
            // Escrever a Data
            // webDriver.findElement(By.id("dueDate")).sendKeys("10/10/2050");
            
            // Clicar no Botão Save
            webDriver.findElement(By.id("saveButton")).click();
    
            // Validar mensagem de Sucesso
            String message = webDriver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the due date", message);
            
        } finally {
            // Fechar o Browser
            webDriver.quit();
        }

    }

    @Test
    public void naoDeveSalvarTarefaComDataPassadaTest() throws MalformedURLException {
        WebDriver webDriver = acessarAplicacao();
        try {
    
            // Clicar em Add Todo
            webDriver.findElement(By.id("addTodo")).click();
            
            // Escrever a Descrição
            webDriver.findElement(By.id("task")).sendKeys("Teste via Selenium");
            
            // Escrever a Data
            webDriver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
            
            // Clicar no Botão Save
            webDriver.findElement(By.id("saveButton")).click();
    
            // Validar mensagem de Sucesso
            String message = webDriver.findElement(By.id("message")).getText();
            Assert.assertEquals("Due date must not be in past", message);
            
        } finally {
            // Fechar o Browser
            webDriver.quit();
        }

    }

}
