
package br.com.xmrtecnologia.tasks.prod;

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

public class HealthCkeckIT {

    @Test
    public void healthCkeckTest() throws MalformedURLException {

        // WebDriver webDriver = new ChromeDriver(); // Trabalhando com o Selenium Local

        // Para saber esta URL tem que ver ao executar o arquivo:
        // Start_Selenium_Server_3.141.59.bat
        // ou execute ipconfig no terminal para pegar o IP
        URL urlHub = new URL("http://192.168.56.1:4444/wd/hub"); // Trabalhando com o Selenium Grid
        DesiredCapabilities cap = DesiredCapabilities.chrome(); // Trabalhando com o Selenium Grid
        WebDriver webDriver = new RemoteWebDriver(urlHub, cap); // Trabalhando com o Selenium Grid

        try {
            webDriver.navigate().to("http://192.168.56.1:9999/tasks"); // Trabalhando com o Selenium Grid

            // webDriver.navigate().to("http://localhost:9999/tasks"); // Trabalhando com o
            // Selenium Local

            // Definindo uma estratégia de espera para o Selenium
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Esperando 10 segundos

            // Buscando a versão correspondente ao build
            String version = webDriver.findElement(By.id("version")).getText();
            Assert.assertTrue(version.startsWith("build_"));
        } finally {
            webDriver.quit();
        }

    }
}