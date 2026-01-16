package test;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import jdk.jfr.Description;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.FormPage;
import utils.ScreenshotUtil;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class NavigateTest {

    protected static Playwright playwright;
    protected static Browser browser;
    protected static Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate("https://demoqa.com/automation-practice-form");

    }

    @AfterAll
    static void tearDown() {
        browser.close();
        playwright.close();
    }

    @Test
    @Description("Registro exitoso con todos los campos completados")
    void testRegistroExitosoFull() {

        Faker faker = new Faker();

        FormPage formPage = new FormPage(page);
        formPage.navigate();

        // Uso de Faker para datos aleatorios
        formPage.fillDatosBasicos(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                "1234567890"
        );

        formPage.selectGenero("Male");
        formPage.setFecha("10", "May", "1995");
        formPage.setSubjects("Maths");
        formPage.selectHobby("Music" , "Sports");
        formPage.uploadFile("src/test/resources/Kata - QA automatizador Junior.pdf");
        formPage.setAddress("123 Main St, Anytown");
        formPage.setStateAndCity("NCR", "Delhi");

        formPage.submitForm();

        // Validación
        assertTrue(formPage.cuadroConfirmacionVisible(), "El modal de éxito debería ser visible");
        String tituloModal = page.getByText("Thanks for submitting the form").innerText();
        System.out.println("formulario enviado correctamente " + tituloModal);

        ScreenshotUtil.tomarPantallazo(page, "testRegistroExitosoFull");

    }

    @Test
    @Description("Registro exitoso con solo los campos básicos")
    void testRegistroExitosoBasico(){
        Faker faker = new Faker();
        FormPage formPage = new FormPage(page);
        formPage.navigate();

        formPage.fillDatosBasicos(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                "1234567890" // 10 dígitos exactos (Valor Límite)
        );
        formPage.selectGenero("Female");

        formPage.submitForm();

        assertTrue(formPage.cuadroConfirmacionVisible(), "El modal de éxito debería ser visible");
        String tituloModal = page.getByText("Thanks for submitting the form").innerText();
        System.out.println("formulario enviado correctamente " + tituloModal);

        ScreenshotUtil.tomarPantallazo(page, "testRegistroExitosoBasico");

    }

    @Test
    @Description("Intento de registro con celular incorrecto")
    void testCelularIncorrecto(){
        Faker faker = new Faker();
        FormPage formPage = new FormPage(page);
        formPage.navigate();

        formPage.fillDatosBasicos(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                "12345" // Celular incorrecto
        );
        formPage.selectGenero("Other");
        formPage.submitForm();
        System.out.println("Celular incorrecto ingresado");
        ScreenshotUtil.tomarPantallazo(page, "testCelularIncorrecto");

    }

    @Test
    @Description("Intento de registro con email incorrecto")
    void testEmailIncorrecto(){
        Faker faker = new Faker();
        FormPage formPage = new FormPage(page);
        formPage.navigate();

        formPage.fillDatosBasicos(
                faker.name().firstName(),
                faker.name().lastName(),
                "email_incorrecto", // Email incorrecto
                "1234567890"
        );
        formPage.selectGenero("Male");
        formPage.submitForm();
        System.out.println("email incorrecto ingresado");
        ScreenshotUtil.tomarPantallazo(page, "testEmailIncorrecto");
    }

    @Test
    @Description("Intento de registro con formulario vacío")
    void testFormVacio(){
        FormPage formPage = new FormPage(page);
        formPage.navigate();
        formPage.submitForm();
        ScreenshotUtil.tomarPantallazo(page, "testFormVacio");

        System.out.println("Formulario vacio enviado");
    }


}
