package ISOC04.PR3;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import org.junit.jupiter.api.Test;


public class TercerProblemaTest {

    @Test
    public void testValidarTemperatura() {
        // Datos de prueba para diferentes rangos de temperatura
        String input1 = "5\n"; // Temperatura menor a 0

        // ByteArrayInputStream para simular la entrada del usuario
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input1.getBytes());
        Scanner scanner = new Scanner(inputStream);
        int result1 = TercerProblema.validarTemperatura(scanner);
        assertEquals(5, result1);
        scanner.close();


    }


    @Test
    public void testValidarBoolean() {
        String input = "true\n";  // Datos de prueba
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        boolean result = TercerProblema.validarBoolean(scanner); 

        assertTrue(result);
        scanner.close();
    }

    
    @Test
    public void testValidarIntPositivo() {
        String input = "5\n";  // Datos de prueba
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        int result = TercerProblema.validarIntPositivo(scanner); 

        assertEquals(5, result);

        scanner.close();
    }

    @Test
    public void testValidarHumedadRelativa() {
        String input = "50\n";  // Datos de prueba
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        int result = TercerProblema.validarHumedadRelativa(scanner); 

        assertEquals(50, result);

        scanner.close();
    }

    @Test
    public void testRangoTemperatura() {
        assertEquals(-1, TercerProblema.rangoTemperatura(-5));  // Temperatura < 0
        assertEquals(0, TercerProblema.rangoTemperatura(10));   // Temperatura 0-15
        assertEquals(1, TercerProblema.rangoTemperatura(20));   // Temperatura 15-25
        assertEquals(2, TercerProblema.rangoTemperatura(30));   // Temperatura 25-35
        assertEquals(3, TercerProblema.rangoTemperatura(40));   // Temperatura > 30
    }

    @Test
    public void testMainMethod() {
        // Aquí probaremos la lógica completa del método main
        String input = "true\nfalse\ntrue\ntrue\n25\n50\nfalse\nfalse\ntrue\ntrue\n";  // Datos de prueba
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);  // Simula las entradas del usuario

        TercerProblema.main(new String[0]); // Ejecutar el método main con inputs simulados

        // Verificar los mensajes de salida (imprimir en consola no es ideal para pruebas unitarias, 
        // pero si deseas algo más complejo, podrías capturar el System.out en un ByteArrayOutputStream)
    }
    
    
}
