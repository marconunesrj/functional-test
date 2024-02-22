package br.com.xmrtecnologia.tasks;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
    
    public WebDriver acessarAplicacao() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.navigate().to("http://localhost:8001/tasks");

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
    public void deveSalvarTarefaComSucessoTest() {
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
    public void naoDeveSalvarTarefaSemDescricaoTest() {
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
    public void naoDeveSalvarTarefaSemDataTest() {
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
    public void naoDeveSalvarTarefaComDataPassadaTest() {
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
