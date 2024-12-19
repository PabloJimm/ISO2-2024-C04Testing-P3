package ISOC04.PR3;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

public class TercerProblemaTest {

    @Test
    public void testValidarTemperatura() {
        // Datos de prueba para diferentes rangos de temperatura
        String input1 = "-1\n"; // Temperatura menor a 0
        String input2 = "10\n";  // Temperatura entre 0 y 15
        String input3 = "20\n";  // Temperatura entre 15 y 25
        String input4 = "30\n";  // Temperatura entre 25 y 35
        String input5 = "40\n";  // Temperatura mayor a 35

        // ByteArrayInputStream para simular la entrada del usuario
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input1.getBytes());
        Scanner scanner = new Scanner(inputStream);
        int result1 = TercerProblema.validarTemperatura(scanner);
        assertEquals(-1, result1);
        scanner.close();

        inputStream = new ByteArrayInputStream(input2.getBytes());
        scanner = new Scanner(inputStream);
        int result2 = TercerProblema.validarTemperatura(scanner);
        assertEquals(10, result2);
        scanner.close();

        inputStream = new ByteArrayInputStream(input3.getBytes());
        scanner = new Scanner(inputStream);
        int result3 = TercerProblema.validarTemperatura(scanner);
        assertEquals(20, result3);
        scanner.close();

        inputStream = new ByteArrayInputStream(input4.getBytes());
        scanner = new Scanner(inputStream);
        int result4 = TercerProblema.validarTemperatura(scanner);
        assertEquals(30, result4);
        scanner.close();

        inputStream = new ByteArrayInputStream(input5.getBytes());
        scanner = new Scanner(inputStream);
        int result5 = TercerProblema.validarTemperatura(scanner);
        assertEquals(40, result5);
        scanner.close();
    }


    @Test
    public void testValidarBoolean() {
        String input = "true\n";  // Datos de prueba
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        boolean result = TercerProblema.validarBoolean(scanner); 

        assertTrue(result);

        input = "false\n";  // Datos de prueba
        inputStream = new ByteArrayInputStream(input.getBytes());
        scanner = new Scanner(inputStream);

         result = TercerProblema.validarBoolean(scanner); 

        assertFalse(result);
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
    
    @Test
    public void testMainMethod_NoPuedeRealizarActividad_Caso1() {
        // Caso: no está sano
        String input = "false\nfalse\nfalse\ntrue\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("No puede realizar ninguna actividad."));
    }

    @Test
    public void testMainMethod_NoPuedeRealizarActividad_Caso2() {
        // Caso: ha estado en contacto con un infectado
        String input = "true\ntrue\nfalse\ntrue\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("No puede realizar ninguna actividad."));
    }

    @Test
    public void testMainMethod_NoPuedeRealizarActividad_Caso3() {
        // Caso: ha pasado el COVID
        String input = "true\nfalse\ntrue\ntrue\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("No puede realizar ninguna actividad."));
    }

    @Test
    public void testMainMethod_NoPuedeRealizarActividad_Caso4() {
        // Caso: no tiene pasaporte COVID
        String input = "true\nfalse\nfalse\nfalse\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("No puede realizar ninguna actividad."));
    }

    @Test
    public void testMainMethod_NoPuedeRealizarActividad_Caso5() {
        // Caso: no está sano y ha estado en contacto con un infectado
        String input = "false\ntrue\nfalse\ntrue\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("No puede realizar ninguna actividad."));
    }

    @Test
    public void testMainMethod_NoPuedeRealizarActividad_Caso6() {
        // Caso: no está sano, ha pasado el COVID y no tiene pasaporte COVID
        String input = "false\nfalse\ntrue\nfalse\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("No puede realizar ninguna actividad."));
    }

    @Test
    public void testMainMethod_NoPuedeRealizarActividad_Caso7() {
        // Caso: ha estado en contacto con un infectado, ha pasado el COVID, y no tiene pasaporte COVID
        String input = "true\ntrue\ntrue\nfalse\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("No puede realizar ninguna actividad."));
    }

    @Test
    public void testMainMethod_NoPuedeRealizarActividad_Caso8() {
        // Caso: todas las condiciones son desfavorables
        String input = "false\ntrue\ntrue\nfalse\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("No puede realizar ninguna actividad."));
    }
    
    @Test
    public void testMainMethod_QuedarseEnCasa() {
        String input = "true\nfalse\nfalse\ntrue\n-5\n10\ntrue\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("Recomendación: Quedarse en casa."));
    }
    
    @Test
    public void testMainMethod_LluviaImpideActividad() {
        String input = "true\nfalse\nfalse\ntrue\n25\ntrue\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("No puede realizar la actividad porque va a llover"));
    }
    

    @Test
    public void testMainMethod_IrAEesquiar() {
        String input = "true\nfalse\nfalse\ntrue\n-5\n10\nfalse\n20\n15\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("Recomendación: Ir a esquiar"));
    }
    
    public void testMainMethod_IrAEesquiarFalse() {
        String input = "true\nfalse\nfalse\ntrue\n-5\n10\nfalse\n20\n300\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("No se puede realizar la actividad debido a que se supera el aforo máximo permitido."));
    }

    @Test
    public void testMainMethod_HacerSenderismo() {
        String input = "true\nfalse\nfalse\ntrue\n10\nfalse\n20\n15\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("Recomendación: Ir a hacer senderismo"));
    }

    @Test
    public void testMainMethod_AforoExcedidoSenderismo() {
        String input = "true\nfalse\nfalse\ntrue\n10\nfalse\n20\n25\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("No se puede realizar la actividad porque se supera el aforo del espacio previsto"));
    }
    
    @Test
    public void testMainMethod_TurismoAireLibre() {
        String input = "true\nfalse\nfalse\ntrue\n20\nfalse\nfalse\n50\nfalse\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("Recomendación: Ir a hacer turismo al aire libre"));
    }

    @Test
    public void testMainMethod_RestriccionesImpidenTurismo() {
        String input = "true\nfalse\nfalse\ntrue\n20\nfalse\nfalse\n50\ntrue\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("No se puede realizar la actividad por restricciones de confinamiento"));
    }
    
    @Test
    public void testMainMethod_HumedadAltaNoTurismo() {
        String input = "true\nfalse\nfalse\ntrue\n20\nfalse\nfalse\n65\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("No puede realizar la actividad porque va a llover, está nublado o la humedad relativa es superior al 60%"));
    }
    
    @Test
    public void testMainMethod_IrseDeCañas() {
        String input = "true\nfalse\nfalse\ntrue\n30\nfalse\n10\n20\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("Recomendación: Irse de cañas"));
    }

    @Test
    public void testMainMethod_IrALaPlayaOPiscina() {
        String input = "true\nfalse\nfalse\ntrue\n35\nfalse\ntrue\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("Recomendación: Irse a la playa o a la piscina"));
    }

    @Test
    public void testMainMethod_AforoExcedidoPiscina() {
        String input = "true\nfalse\nfalse\ntrue\n35\nfalse\nfalse\n20\n25\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("No puede ir a la piscina debido a que supera el aforo máximo permitido"));
    }
    
    @Test
    public void testMainMethod_IrALaPiscina() {
        String input = "true\nfalse\nfalse\ntrue\n35\nfalse\nfalse\n20\n10\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("¡Pasároslo bien en la piscina!"));
    }
    
    @Test
    public void testMainMethod_TemperaturaNegativaSinHumedad() {
        String input = "true\nfalse\nfalse\ntrue\n-5\n10\nfalse\n20\n10\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("Recomendación: Ir a esquiar"));
    }

    @Test
    public void testMainMethod_TemperaturaLimiteSenderismo() {
        String input = "true\nfalse\nfalse\ntrue\n0\nfalse\n20\n15\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("Recomendación: Ir a hacer senderismo"));
    }

    @Test
    public void testMainMethod_TurismoSinRestricciones() {
        String input = "true\nfalse\nfalse\ntrue\n22\nfalse\nfalse\n50\nfalse\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("Recomendación: Ir a hacer turismo al aire libre"));
    }

    @Test
    public void testMainMethod_TemperaturaExacta35ConLluvia() {
        String input = "true\nfalse\nfalse\ntrue\n35\ntrue\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("No puede realizar la actividad debido a que va a llover"));
    }

    @Test
    public void testMainMethod_PlayaSinProblemas() {
        String input = "true\nfalse\nfalse\ntrue\n36\nfalse\ntrue\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("¡Pasaróslo bien en la playa!"));
    }

    @Test
    public void testMainMethod_TemperaturaAltaPiscinaConAforo() {
        String input = "true\nfalse\nfalse\ntrue\n36\nfalse\nfalse\n20\n30\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("No puede ir a la piscina debido a que supera el aforo máximo permitido"));
    }

    @Test
    public void testMainMethod_TemperaturaExacta15() {
        String input = "true\nfalse\nfalse\ntrue\n15\nfalse\nfalse\n55\nfalse\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("Recomendación: Ir a hacer turismo al aire libre"));
    }
    
    @Test
    public void testValidarTemperatura_InvalidInput() {
        // Probar con entrada no numérica
        String input = "abc\n10\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        int result = TercerProblema.validarTemperatura(scanner);
        assertEquals(10, result); // Valida que se repita hasta recibir un número válido
        scanner.close();
    }

    @Test
    public void testValidarBoolean_InvalidInput() {
        // Probar con entrada no booleana
        String input = "123\ntrue\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        boolean result = TercerProblema.validarBoolean(scanner);
        assertTrue(result); // Valida que se repita hasta recibir true/false
        scanner.close();
    }

    @Test
    public void testValidarIntPositivo_InvalidInput() {
        // Probar con número negativo
        String input = "-5\n0\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        int result = TercerProblema.validarIntPositivo(scanner);
        assertEquals(0, result); // Valida que se repita hasta recibir un positivo
        scanner.close();
    }

    @Test
    public void testValidarHumedadRelativa_OutOfBounds() {
        // Probar con humedad fuera del rango permitido
        String input = "150\n50\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        int result = TercerProblema.validarHumedadRelativa(scanner);
        assertEquals(50, result); // Valida que se repita hasta recibir un valor válido
        scanner.close();
    }

    @Test
    public void testRangoTemperatura_BorderCases() {
        // Casos límite para rangoTemperatura
        assertEquals(-1, TercerProblema.rangoTemperatura(-1)); // Justo debajo de 0
        assertEquals(0, TercerProblema.rangoTemperatura(0));   // Límite inferior de 0-15
        assertEquals(0, TercerProblema.rangoTemperatura(14));  // Límite superior de 0-15
        assertEquals(1, TercerProblema.rangoTemperatura(15));  // Límite inferior de 15-25
        assertEquals(1, TercerProblema.rangoTemperatura(24));  // Límite superior de 15-25
        assertEquals(2, TercerProblema.rangoTemperatura(25));  // Límite inferior de 25-35
        assertEquals(2, TercerProblema.rangoTemperatura(34));  // Límite superior de 25-35
        assertEquals(3, TercerProblema.rangoTemperatura(35));  // Límite inferior de >35
    }

    @Test
    public void testMainMethod_SenderismoConLluvia() {
        // Caso: temperatura entre 0 y 15, pero con lluvia
        String input = "true\nfalse\nfalse\ntrue\n10\ntrue\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("No se puede realizar la actividad porque hay precipitaciones de nieve o de agua"));
    }

    @Test
    public void testMainMethod_IrDeCanasConAforoExcedido() {
        // Caso: temperatura entre 25 y 35, aforo excedido
        String input = "true\nfalse\nfalse\ntrue\n30\nfalse\n50\n30\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("No se puede realizar la actividad porque hay problemas de aforo en el establecimiento"));
    }

    @Test
    public void testMainMethod_PiscinaConAforoExacto() {
        // Caso: temperatura mayor a 30, aforo exacto
        String input = "true\nfalse\nfalse\ntrue\n36\nfalse\nfalse\n20\n20\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("No puede ir a la piscina debido a que supera el aforo máximo permitido"));
    }

    @Test
    public void testMainMethod_SinHumedadBaja() {
        // Caso: temperatura < 0, pero humedad alta
        String input = "true\nfalse\nfalse\ntrue\n-5\n30\ntrue\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("No hay actividades recomendadas para estas condiciones"));
    }

    @Test
    public void testMainMethod_TemperaturaMayor35ConLluvia() {
        // Caso: temperatura > 35, pero con lluvia
        String input = "true\nfalse\nfalse\ntrue\n36\ntrue\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("No puede realizar la actividad debido a que va a llover"));
    }

    @Test
    public void testValidarHumedadRelativa_ThrowAbove100() {
    	String input = "true\nfalse\nfalse\ntrue\n-5\n120\nfalse\n20\n15\n";
    	ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("Error: El valor no puede ser mayor a 100. Introduzca un nuevo valor."));
    }

    @Test
    public void testValidarHumedadRelativa_ThrowNegative() {
    	String input = "true\nfalse\nfalse\ntrue\n-5\n-120\nfalse\n20\n15\n";
    	ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TercerProblema.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("Error: El valor no puede ser negativo. Introduzca un nuevo valor."));
        
        }
}
