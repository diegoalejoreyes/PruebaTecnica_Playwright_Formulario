package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;

public class FormPage {

    private final Page page;

    // Selectores por #id
    private final String firstNameInput = "#firstName";
    private final String lastNameInput = "#lastName";
    private final String userEmailInput = "#userEmail";
    private final String numeroInput = "#userNumber";
    private final String submitButton = "#submit";
    private final String confirmacionTitle = "#example-modal-sizes-title-lg";

    // Datepicker
    private final String dateOfBirthInput = "#dateOfBirthInput";
    private final String dateMonthSelect = ".react-datepicker__month-select";
    private final String dateYearSelect = ".react-datepicker__year-select";

    private final String subjectsInput = "#subjectsInput";
    private final String addressInput = "#currentAddress";
    private final String stateInput = "#state";
    private final String cityInput = "#city";

    public FormPage(Page page) {
        this.page = page;
    }


    public void navigate() {
        page.navigate("https://demoqa.com/automation-practice-form");
//        // Espera explícita como pide el requerimiento
        page.waitForSelector(firstNameInput);
    }

    public void fillDatosBasicos(String name, String lastName, String email, String mobile) {
        page.fill(firstNameInput, name);
        page.fill(lastNameInput, lastName);
        page.fill(userEmailInput, email);
        page.fill(numeroInput, mobile);
    }

    public void selectGenero(String gender) {
        page.click("label[for='gender-radio-" + getGenderIndex(gender) + "']");
    }

    public void setFecha(String day, String month, String year) {
        page.click(dateOfBirthInput);
        // Seleccionar Mes y Año de los dropdowns del calendario
        page.locator(dateMonthSelect).selectOption(month);
        page.locator(dateYearSelect).selectOption(year);
        // Seleccionar el día. Se utiliza una clase que asegura que no sea de otro mes visible
        String daySelector = String.format("//div[contains(@class, 'react-datepicker__day')][text()='%s'][not(contains(@class, '--outside-month'))]", day);
        page.locator(daySelector).click();
    }

    public void setSubjects(String... subjects) {
        for (String subject : subjects) {
            page.click(subjectsInput);
            page.fill(subjectsInput, subject);
            page.press(subjectsInput, "Enter");
        }
    }

    public void selectHobby(String... hobbies) {
        for (String hobby : hobbies) {
            page.getByText(hobby, new Page.GetByTextOptions().setExact(true)).click();
        }
    }

    public void uploadFile(String filePath) {
        page.setInputFiles("#uploadPicture", Paths.get(filePath));
    }

    public void setAddress(String address) {
        page.fill(addressInput, address);
    }

    // Nuevo: Estado y Ciudad (React Selects)
    public void setStateAndCity(String state, String city) {

        page.click(stateInput);
        page.locator(stateInput).locator("input").fill(state);
        page.locator(stateInput).locator("input").press("Enter");

        page.click(cityInput);
        page.locator(cityInput).locator("input").fill(city);
        page.locator(cityInput).locator("input").press("Enter");
    }

    public void submitForm() {
        // A veces el footer tapa el botón, se hace scroll
        page.locator(submitButton).scrollIntoViewIfNeeded();
//        page.click(submitButton);
        page.click(submitButton, new Page.ClickOptions().setForce(true));
    }

    public boolean cuadroConfirmacionVisible() {
        // Espera explícita para la modal
//        String titulomodal = modalTitle.toString();
        page.waitForSelector(confirmacionTitle, new Page.WaitForSelectorOptions().setTimeout(5000));
        return page.isVisible(confirmacionTitle);
    }

    // Helper interno
    private int getGenderIndex(String gender) {
        switch (gender.toLowerCase()) {
            case "male": return 1;
            case "female": return 2;
            default: return 3;
        }
    }


}
