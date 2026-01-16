package utils;

import com.microsoft.playwright.Page;

import java.nio.file.Paths;

public class ScreenshotUtil {

    public static void tomarPantallazo(Page page, String nombreArchivo) {

        page.screenshot(
                new Page.ScreenshotOptions().setPath(
                        Paths.get("screenshots/", nombreArchivo + ".png")
                )
        );
    }
}
